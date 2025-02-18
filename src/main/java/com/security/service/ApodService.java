package com.security.service;

import com.security.dto.ApodDTO;
import com.security.entity.Apod;
import com.security.mapping.ApodMapping;
import com.security.repository.ApodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApodService {

    private final ApodRepository apodRepository;
    private final ApodMapping apodMapping;

    public ApodDTO getApodById(Long id) {
        Apod apod = apodRepository.findById(id).orElse(null);
        if (apod == null) {
            return null;
        }
        return apodMapping.entityToDTO(apod);
    }

    public Iterable<Apod> getAllApods() {
        return apodRepository.findAll();
    }

    public String add(ApodDTO apod) {
        Apod newApod = apodMapping.dtoToEntity(apod);
        apodRepository.save(newApod);
        return "Apod added successfully";
    }

    public String delete(Long id) {
        Apod apod = apodRepository.findById(id).orElse(null);
        if (apod == null) {
            return "Apod not found";
        }
        apodRepository.delete(apod);
        return "Apod deleted";
    }

    public String update(ApodDTO apod) {
        Apod updatedApod = apodRepository.findById(apod.getId()).orElse(null);
        if (updatedApod == null) {
            return "Apod not found";
        }
        updatedApod.setCopyright(apod.getCopyright());
        updatedApod.setDate(apod.getDate());
        updatedApod.setExplanation(apod.getExplanation());
        updatedApod.setHdurl(apod.getHdurl());
        updatedApod.setMedia_type(apod.getMedia_type());
        updatedApod.setService_version(apod.getService_version());
        updatedApod.setTitle(apod.getTitle());
        updatedApod.setUrl(apod.getUrl());
        apodRepository.save(updatedApod);
        return "Apod updated successfully";
    }
}
