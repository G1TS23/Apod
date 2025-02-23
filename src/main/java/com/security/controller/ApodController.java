package com.security.controller;

import com.security.dto.ApodDTO;
import com.security.exception.NoApodException;
import com.security.service.ApodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apod")
public class ApodController {

    private final ApodService apodService;

    public ApodController(ApodService apodService) {
        this.apodService = apodService;
    }

    @GetMapping
    public List<ApodDTO> getApods() {
        return apodService.getAllApods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> apod(@PathVariable Long id) {
        try {
            ApodDTO apodDTO = apodService.getApodById(id);
            return ResponseEntity.ok(apodDTO);
        } catch (NoApodException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            return apodService.delete(id);
        } catch (NoApodException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody ApodDTO apod) {
        try {
            return apodService.update(apod);
        } catch (NoApodException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody ApodDTO apod) {
        return apodService.add(apod);
    }
}
