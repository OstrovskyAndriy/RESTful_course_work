package com.coursework.services;

import com.coursework.models.Gallery;

import java.util.List;

public interface GalleryService {
    List<Gallery> getAllGalleries();

    Gallery getGalleryById(Long id);

    Gallery createGallery(Gallery gallery);

    Gallery updateGallery(Long id, Gallery gallery);

    void deleteGallery(Long id);
}