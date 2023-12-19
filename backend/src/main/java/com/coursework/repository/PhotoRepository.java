package com.coursework.repository;

import com.coursework.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    //List<Photo> findGalleryByInstitutionId(Long institutionId);
}
