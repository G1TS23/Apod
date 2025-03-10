package com.apod.service;

import com.apod.dto.ApodDTO;
import com.apod.entity.Apod;
import com.apod.exception.NoApodException;
import com.apod.mapping.ApodMapping;
import com.apod.repository.ApodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApodService {

    private final ApodRepository apodRepository;
    private final ApodMapping apodMapping;

    public ApodDTO getApodById(Long id) throws NoApodException {
        Apod apod = apodRepository.findById(id)
                .orElseThrow(() -> new NoApodException("Apod pas trouvé"));
        return apodMapping.entityToDTO(apod);
    }

    public List<ApodDTO> getAllApods() {
        Iterable<Apod> apods = apodRepository.findAll();
        List<ApodDTO> apodDTOS = new ArrayList<>();
        for (Apod apod : apods) {
            apodDTOS.add(apodMapping.entityToDTO(apod));
        }
        return apodDTOS;
    }

    public String add(ApodDTO apod) {
        Apod newApod = apodMapping.dtoToEntity(apod);
        apodRepository.save(newApod);
        return "Apod added successfully";
    }

    public String delete(Long id) throws NoApodException {
        Apod apod = apodRepository.findById(id)
                .orElseThrow(() -> new NoApodException("Apod pas trouvé"));
        apodRepository.delete(apod);
        return "Apod deleted";
    }

    public String update(ApodDTO apod) throws NoApodException {
        Apod updatedApod = apodRepository.findById(apod.getId())
                .orElseThrow(() -> new NoApodException("Apod pas trouvé"));
        apodMapping.updateEntityFromDTO(apod, updatedApod);
        apodRepository.save(updatedApod);
        return "Apod updated successfully";
    }
}
