package com.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Injection du cors allowed origins. Cela rend mon application plus flexible,
    // Car les cors ne seront pas les mêmes en fonction de l'environnement
    @Value("${cors.allowedOrigins}")
    private String[] allowedOrigins;

    // Encodeur de mot de passe utilisant bcrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Gestionnaire d'entrée pour l'authentification JWT
    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    // Filtre d'authentification JWT pour les requêtes
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // Configuration CORS pour autoriser des origines spécifiques
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Gestionnaire d'authentification central
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuration de la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Désactivation de CSRF, nécésaire pour JWT
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Application de la config CORS
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/apod/**").hasRole("ADMIN") // Accès CRUD
                        .requestMatchers("/api/*","/api").hasRole("USER") // Accès rebond
                        .requestMatchers("/api/scrap/range", "/api/scrap/today").hasRole("SCRAPPER") // Accès scrapping
                        // Accès public a certaines routes, notamment la page d'accueil, l'inscription et le login
                        .requestMatchers( "/credentials/*", "/index", "/users/register", "/users/login", "/users/logout").permitAll()
                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Ajout du filtre JWT, permettant de vérifier le token et le rôle de l'utilisateur

        return http.build();
    }
}

