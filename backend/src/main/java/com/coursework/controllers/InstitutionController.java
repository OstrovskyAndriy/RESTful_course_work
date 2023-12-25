package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.modelsResponse.InstitutionResponse;
import com.coursework.models.modelsResponse.InstitutionTablesResponse;
import com.coursework.models.modelsResponse.PhotoResponse;
import com.coursework.repository.InstitutionRepository;
import com.coursework.services.InstitutionService;
import com.coursework.services.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/institutions")
@Transactional

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
    public List<InstitutionResponse> getAllInstitutionsWithDetails() {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionResponse> responses = new ArrayList<>();

        for (Institution institution : institutions) {
            List<InstitutionTablesResponse> tables = institution.getTables().stream()
                    .map(table -> new InstitutionTablesResponse(table.getId(), table.getTableNumber(), table.getCountOfChairs(), table.getInstitution().getId()))
                    .collect(Collectors.toList());

            List<PhotoResponse> photos = institution.getPhotos().stream()
                    .map(photo -> new PhotoResponse(photo.getId(), photo.getInstitution().getId(), photo.getUrl()))
                    .collect(Collectors.toList());

            InstitutionResponse response = new InstitutionResponse(
                    institution.getId(),
                    institution.getName(),
                    institution.getType(),
                    institution.getAddress(),
                    institution.getPhone(),
                    institution.getMail(),
                    institution.getDescription(),
                    tables,
                    photos
            );

            responses.add(response);
        }

        return responses;
    }


    @GetMapping("/{id}")
    public Institution getInstitutionById(@PathVariable Long id) {
        return institutionService.getInstitutionById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Institution createInstitution(@RequestBody Institution institution) {
        System.out.println(institution.toString());
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