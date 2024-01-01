package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.Photo;
import com.coursework.models.modelsResponse.PhotoResponse;
import com.coursework.repository.InstitutionRepository;
import com.coursework.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    private final InstitutionRepository institutionRepository;


    @Autowired
    public PhotoController(PhotoService photoService, InstitutionRepository institutionRepository) {
        this.photoService = photoService;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public List<PhotoResponse> getAllPhotos() {
        List<Photo> institutionPhotos = photoService.getAllPhotos();

        return institutionPhotos.stream()
                .map(photo -> new PhotoResponse(
                        photo.getId(),
                        photo.getUrl(),
                        photo.getInstitution().getId()  // Додавте отримання ідентифікатора закладу
                ))
                .collect(Collectors.toList());

    }


    @GetMapping("/{id}")
    public PhotoResponse getPhotoById(@PathVariable Long id) {
        Photo photo = photoService.getPhotoById(id);

        if (photo != null) {
            return new PhotoResponse(
                    photo.getId(),
                    photo.getUrl(),
                    photo.getInstitution().getId()
            );
        } else {
            return null;
        }
    }

    @GetMapping("/byInstitution/{institutionId}")
    public List<PhotoResponse> getPhotosByInstitutionId(@PathVariable Long institutionId) {
        List<Photo> institutionPhotos = photoService.getPhotosByInstitutionId(institutionId);

        return institutionPhotos.stream()
                .map(photo -> new PhotoResponse(
                        photo.getId(),
                        photo.getUrl(),
                        photo.getInstitution().getId()
                ))
                .collect(Collectors.toList());
    }


    @PostMapping
    public PhotoResponse addPhoto(@RequestBody PhotoResponse request) {
        Long institutionId = request.getInstitutionId();
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new RuntimeException("Institution with id " + institutionId + " not found"));

        Photo photo = new Photo();
        photo.setUrl(request.getUrl());
        photo.setInstitution(institution);

        Photo savedPhoto = photoService.addPhoto(photo);

        return new PhotoResponse(
                savedPhoto.getId(),
                savedPhoto.getUrl(),
                savedPhoto.getInstitution().getId()
        );
    }

    @PutMapping("/{id}")
    public PhotoResponse updatePhoto(@PathVariable Long id, @RequestBody PhotoResponse request) {
        Photo existingPhoto = photoService.getPhotoById(id);

        if (existingPhoto != null) {
            Long institutionId = request.getInstitutionId();

            Institution institution = institutionRepository.findById(institutionId)
                    .orElseThrow(() -> new RuntimeException("Institution with id " + institutionId + " not found"));

            existingPhoto.setUrl(request.getUrl());
            existingPhoto.setInstitution(institution);

            Photo updatedPhoto = photoService.updatePhoto(existingPhoto, id);

            return new PhotoResponse(
                    updatedPhoto.getId(),
                    updatedPhoto.getUrl(),
                    updatedPhoto.getInstitution().getId()
            );
        } else {

            return null;
        }
    }


    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}

