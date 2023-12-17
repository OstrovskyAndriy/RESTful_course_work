package com.coursework.controllers;

import com.coursework.models.Gallery;
import com.coursework.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public List<Gallery> getAllGalleries() {
        return galleryService.getAllGalleries();
    }

    @GetMapping("/{id}")
    public Gallery getGalleryById(@PathVariable Long id) {
        return galleryService.getGalleryById(id);
    }

    @PostMapping
    public Gallery createGallery(@RequestBody Gallery gallery) {
        return galleryService.createGallery(gallery);
    }

    @PutMapping("/{id}")
    public Gallery updateGallery(@PathVariable Long id, @RequestBody Gallery gallery) {
        return galleryService.updateGallery(id, gallery);
    }

    @DeleteMapping("/{id}")
    public void deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
    }
}
