package com.apod.controller;

import com.apod.service.ApiService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/api")
public class ApiController {

    private final ApiService apiService;


    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(path = "")
    public String getApi() {
        return apiService.getTodayApod();
    }

    @GetMapping(path = "/range")
    public String getApodRange(@RequestParam String from, @RequestParam String to) {
        return apiService.getRangeApod(from, to);
    }

    @PostMapping(path = "/scrap/today")
    public String saveApod() {
        return apiService.scrapeTodayApod();
    }

    @PostMapping(path = "/scrap/range")
    public String scrapRange(@RequestParam String from, @RequestParam String to) {
        return apiService.scrapeRangeApod(from, to);
    }

}
