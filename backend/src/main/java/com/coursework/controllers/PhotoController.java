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

//    @GetMapping("/institution/{id}")
//    public List<Photo> getPhotosByInstitution(@PathVariable Long id) {
//        return photoService.getPhotosByInstitution(id);
//    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id);
    }

    @PostMapping
    public PhotoResponse addPhoto(@RequestBody PhotoResponse request) {
        //return photoService.addPhoto(photo);
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

//
//        // Збережіть стілець
//        InstitutionTables savedTable = institutionTablesService.createTable(table);
//
//        // Поверніть відповідь, використовуючи клас InstitutionTablesResponse
//        return new InstitutionTablesResponse(
//                savedTable.getId(),
//                savedTable.getTableNumber(),
//                savedTable.getCountOfChairs(),
//                savedTable.getInstitution().getId()
//        );
//    }


    @PutMapping("/{id}")
    public Photo updatePhoto(@PathVariable Long id, @RequestBody Photo photo) {
        return photoService.updatePhoto(photo, id);
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}

