package com.apod.service;

import com.apod.dto.ApodDTO;
import com.apod.util.DateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiService {
    @Value("${api.url}")
    private String TARGET_HOST;
    @Value("${api.key}")
    private String API_KEY;

    private final RestClient restClient;
    private final ApodService apodService;
    private final DateValidator dateValidator;

    public ApiService(RestClient restClient, ApodService apodService, DateValidator dateValidator) {
        this.restClient = restClient;
        this.apodService = apodService;
        this.dateValidator = dateValidator;
    }

    public String getTodayApod(){
        return restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY)
                .retrieve()
                .body(String.class);
    }

    public String getRangeApod(String from, String to){
        if(dateValidator.isValidDate(from) && dateValidator.isValidDate(to)) {
            return restClient
                    .get()
                    .uri(TARGET_HOST + "?api_key=" + API_KEY + "&start_date=" + from + "&end_date=" + to)
                    .retrieve()
                    .body(String.class);
        }
        return "Date format not valid (YYYY-MM-DD)";
    }

    public String scrapeTodayApod(){
        ApodDTO apodDTO = restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY)
                .retrieve()
                .body(ApodDTO.class);
        apodService.add(apodDTO);
        return "Scrapped today\'s picture";
    }

    public String scrapeRangeApod(String from, String to){
        if(dateValidator.isValidDate(from) && dateValidator.isValidDate(to)) {
            List<ApodDTO> apodDTOList = restClient
                    .get()
                    .uri(TARGET_HOST + "?api_key=" + API_KEY + "&start_date=" + from + "&end_date=" + to)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ApodDTO>>() {
                    });

            apodDTOList.forEach(apodDTO -> apodService.add(apodDTO));
            return "Scrapped from " + from + " to " + to;
        }
        return "Date format not valid (YYYY-MM-DD)";
    }

}
