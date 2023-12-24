package com.coursework.services;

import com.coursework.models.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> getAllPhotos();

//    List<Photo> getPhotosByInstitution(Long id);

    Photo updatePhoto(Photo photo, Long id);

    Photo addPhoto(Photo photo);

    Photo getPhotoById(Long id);

    void deletePhoto(Long id);
    public List<Photo> getPhotosByInstitution(Long institutionId);
}
