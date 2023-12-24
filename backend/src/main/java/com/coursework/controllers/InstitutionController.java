package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.repository.InstitutionRepository;
import com.coursework.services.InstitutionService;
import com.coursework.services.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;  // Додайте це поле
    private PhotoService photoService;

    @Autowired
    public InstitutionController(InstitutionService institutionService, InstitutionRepository institutionRepository, PhotoService photoService) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
        this.photoService = photoService;  // Ініціалізуйте це поле
    }

    @GetMapping
    public List<Institution> getAllInstitutionsWithDetails() {
        List<Institution> institutions = institutionRepository.findAll();
        institutions.forEach(institution -> {
            // Вибирайте дані про столики та фотографії для кожного закладу окремо
            institution.setTables(institutionService.getTablesByInstitution(institution.getId()));
            institution.setPhotos(photoService.getPhotosByInstitution(institution.getId()));
        });
        return institutions;
    }


    @GetMapping("/{id}")
    public Institution getInstitutionById(@PathVariable Long id) {
        return institutionService.getInstitutionById(id);
    }

    @PostMapping
    public Institution createInstitution(@RequestBody Institution institution) {
        return institutionService.createInstitution(institution);
    }

    @PutMapping("/{id}")
    public Institution updateInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        return institutionService.updateInstitution(id, institution);
    }

    @DeleteMapping("/{id}")
    public void deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitution(id);
    }
}