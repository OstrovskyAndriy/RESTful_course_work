package com.coursework.controllers;

import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;
import com.coursework.models.modelsResponse.InstitutionTablesResponse;
import com.coursework.repository.InstitutionRepository;
import com.coursework.services.InstitutionTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class InstitutionTablesController {

    private final InstitutionTablesService institutionTablesService;
    private final InstitutionRepository institutionRepository;


    @Autowired
    public InstitutionTablesController(InstitutionTablesService institutionTablesService, InstitutionRepository institutionRepository) {
        this.institutionTablesService = institutionTablesService;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public List<InstitutionTablesResponse> getAllTables() {
        List<InstitutionTables> institutionTables = institutionTablesService.getAllTables();

        return institutionTables.stream()
                .map(table -> new InstitutionTablesResponse(
                        table.getId(),
                        table.getTableNumber(),
                        table.getCountOfChairs(),
                        table.getInstitution().getId()  // Додавте отримання ідентифікатора закладу
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InstitutionTablesResponse getTableById(@PathVariable Long id) {
        InstitutionTables table = institutionTablesService.getTableById(id);

        if (table != null) {
            return new InstitutionTablesResponse(
                    table.getId(),
                    table.getTableNumber(),
                    table.getCountOfChairs(),
                    table.getInstitution().getId()
            );
        } else {
            return null;
        }
    }


    @PostMapping
    public InstitutionTablesResponse createTable(@RequestBody InstitutionTablesResponse request) {
        // Отримайте ідентифікатор закладу з запиту
        Long institutionId = request.getInstitutionId();

        // Переконайтеся, що заклад існує
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new RuntimeException("Institution with id " + institutionId + " not found"));

        // Створіть об'єкт InstitutionTables і встановіть посилання на заклад
        InstitutionTables table = new InstitutionTables();
        table.setTableNumber(request.getTableNumber());
        table.setCountOfChairs(request.getCountOfChairs());
        table.setInstitution(institution);

        // Збережіть стілець
        InstitutionTables savedTable = institutionTablesService.createTable(table);

        // Поверніть відповідь, використовуючи клас InstitutionTablesResponse
        return new InstitutionTablesResponse(
                savedTable.getId(),
                savedTable.getTableNumber(),
                savedTable.getCountOfChairs(),
                savedTable.getInstitution().getId()
        );
    }



@PutMapping("/{id}")
public InstitutionTablesResponse updateTable(@PathVariable Long id, @RequestBody InstitutionTablesResponse request) {
    InstitutionTables existingTable = institutionTablesService.getTableById(id);

    if (existingTable != null) {
        Long institutionId = request.getInstitutionId();

        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new RuntimeException("Institution with id " + institutionId + " not found"));

        existingTable.setTableNumber(request.getTableNumber());
        existingTable.setCountOfChairs(request.getCountOfChairs());
        existingTable.setInstitution(institution);

        InstitutionTables updatedTable = institutionTablesService.updateTable(id, existingTable);

        return new InstitutionTablesResponse(
                updatedTable.getId(),
                updatedTable.getTableNumber(),
                updatedTable.getCountOfChairs(),
                updatedTable.getInstitution().getId()
        );
    } else {

        return null;
    }
}


    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        institutionTablesService.deleteTable(id);
    }
}