package com.security.controller;

import com.security.dto.ApodDTO;
import com.security.entity.Apod;
import com.security.service.ApodService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apod")
public class ApodController {

    private final ApodService apodService;

    public ApodController(ApodService apodService) {
        this.apodService = apodService;
    }

    @GetMapping
    public Iterable<Apod> getApods() {
        return apodService.getAllApods();
    }

    @GetMapping("/{id}")
    public String apod(@PathVariable Long id) {
        ApodDTO apodDTO = apodService.getApodById(id);
        if (apodDTO == null) {
            return "Apod not found";
        }
        return apodDTO.toString();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return apodService.delete(id).toString();
    }

    @PutMapping("/update")
    public String update(@RequestBody ApodDTO apod) {
        return apodService.update(apod);
    }

    @PostMapping("/add")
    public String add(@RequestBody ApodDTO apod) {
        return apodService.add(apod);
    }
}
