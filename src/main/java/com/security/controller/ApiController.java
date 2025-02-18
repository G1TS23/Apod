package com.security.controller;

import com.security.dto.ApodDTO;
import com.security.service.ApodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController()
@RequestMapping(path = "/api")
public class ApiController {

    @Value("${api.url}")
    private String TARGET_HOST;
    @Value("${api.key}")
    private String API_KEY;

    private final RestClient restClient;
    private final ApodService apodService;


    public ApiController(RestClient restClient, ApodService apodService) {
        this.restClient = restClient;
        this.apodService = apodService;
    }

    @GetMapping(path = "")
    public String getApi() {
        return restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY)
                .retrieve()
                .body(String.class);
    }

    @GetMapping(path = "/range")
    public String getApodRange(@RequestParam String from, @RequestParam String to) {
        return restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY + "&start_date=" + from + "&end_date=" + to)
                .retrieve()
                .body(String.class);
    }

    @PostMapping(path = "/scrap/today")
    public String saveApod() {
        ApodDTO apodDTO = restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY)
                .retrieve()
                .body(ApodDTO.class);
        apodService.add(apodDTO);
        return apodDTO.toString();
    }

    @PostMapping(path = "/scrap/range")
    public String scrapRange(@RequestParam String from, @RequestParam String to) {
        List<ApodDTO> apodDTOList = restClient
                .get()
                .uri(TARGET_HOST + "?api_key=" + API_KEY + "&start_date=" + from + "&end_date=" + to)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ApodDTO>>() {});

        apodDTOList.forEach(apodDTO -> apodService.add(apodDTO));
        return "Scrapped !!!";
    }

}
