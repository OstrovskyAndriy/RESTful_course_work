package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;
import com.coursework.models.Photo;
import com.coursework.models.modelsResponse.InstitutionResponse;
import com.coursework.models.modelsResponse.InstitutionTablesResponse;
import com.coursework.models.modelsResponse.PhotoResponse;
import com.coursework.repository.InstitutionRepository;
import com.coursework.services.InstitutionService;
import com.coursework.services.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/institutions")
@Transactional
public class InstitutionController {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    private final PhotoService photoService;

    @Autowired
    public InstitutionController(InstitutionService institutionService, InstitutionRepository institutionRepository, PhotoService photoService) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
        this.photoService = photoService;
    }

    @GetMapping
    public List<InstitutionResponse> getAllInstitutions(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<Institution> institutionPage = institutionRepository.findAll(pageable);
        List<Institution> institutions = institutionPage.getContent();

        return institutions.stream()
                .map(this::createInstitutionResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InstitutionResponse getInstitutionById(@PathVariable Long id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        return institution.map(this::createInstitutionResponse).orElse(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    private InstitutionResponse createInstitutionResponse(Institution institution) {
        List<InstitutionTablesResponse> tables = institution.getTables().stream()
                .map(this::createTablesResponse)
                .collect(Collectors.toList());

        List<PhotoResponse> photos = institution.getPhotos().stream()
                .map(this::createPhotoResponse)
                .collect(Collectors.toList());

        return new InstitutionResponse(
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
    }

    private InstitutionTablesResponse createTablesResponse(InstitutionTables table) {
        return new InstitutionTablesResponse(
                table.getId(),
                table.getTableNumber(),
                table.getCountOfChairs(),
                table.getInstitution().getId()
        );
    }

    private PhotoResponse createPhotoResponse(Photo photo) {
        return new PhotoResponse(
                photo.getId(),
                photo.getUrl(),
                photo.getInstitution().getId()
        );
    }
}
