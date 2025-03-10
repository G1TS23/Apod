package com.apod.mapping;

import com.apod.dto.ApodDTO;
import com.apod.entity.Apod;
import org.springframework.stereotype.Component;

@Component
public class ApodMapping {

    public Apod dtoToEntity(ApodDTO dto) {
        return Apod.builder()
                .copyright(dto.copyright)
                .date(dto.date)
                .explanation(dto.explanation)
                .hdurl(dto.hdurl)
                .media_type(dto.media_type)
                .service_version(dto.service_version)
                .title(dto.title)
                .url(dto.url)
                .build();
    }

    public ApodDTO entityToDTO(Apod entity) {
        return ApodDTO.builder()
                .id(entity.getId())
                .copyright(entity.getCopyright())
                .date(entity.getDate())
                .explanation(entity.getExplanation())
                .hdurl(entity.getHdurl())
                .media_type(entity.getMedia_type())
                .service_version(entity.getService_version())
                .title(entity.getTitle())
                .url(entity.getUrl())
                .build();
    }

    public void updateEntityFromDTO(ApodDTO apodDTO, Apod apod) {
        apod.setCopyright(apodDTO.getCopyright());
        apod.setDate(apodDTO.getDate());
        apod.setExplanation(apodDTO.getExplanation());
        apod.setHdurl(apodDTO.getHdurl());
        apod.setMedia_type(apodDTO.getMedia_type());
        apod.setService_version(apodDTO.getService_version());
        apod.setTitle(apodDTO.getTitle());
        apod.setUrl(apodDTO.getUrl());
    }
}
