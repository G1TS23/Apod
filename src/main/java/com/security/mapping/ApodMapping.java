package com.security.mapping;

import com.security.dto.ApodDTO;
import com.security.entity.Apod;
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
}
