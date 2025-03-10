package com.apod.repository;

import com.apod.entity.Apod;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ApodRepository extends CrudRepository<Apod, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Apod a set a.copyright = ?1, a.date = ?2, a.explanation = ?3, a.hdurl = ?4, a.media_type = ?5, a.service_version = ?6, a.title = ?7, a.url = ?8
            where a.id = ?9""")
    void update(String copyright, String date, String explanation, String hdurl, String media_type, String service_version, String title, String url, Long id);
}
