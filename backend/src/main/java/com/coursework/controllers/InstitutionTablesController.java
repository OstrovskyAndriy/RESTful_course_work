package com.coursework.controllers;

import com.coursework.models.InstitutionTables;
import com.coursework.services.InstitutionTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class InstitutionTablesController {

    private final InstitutionTablesService institutionTablesService;

    @Autowired
    public InstitutionTablesController(InstitutionTablesService institutionTablesService) {
        this.institutionTablesService = institutionTablesService;
    }

    @GetMapping
    public List<InstitutionTables> getAllTables() {
        return institutionTablesService.getAllTables();
    }

    @GetMapping("/{id}")
    public InstitutionTables getTableById(@PathVariable Long id) {
        return institutionTablesService.getTableById(id);
    }

    @PostMapping
    public InstitutionTables createTable(@RequestBody InstitutionTables table) {
        return institutionTablesService.createTable(table);
    }

    @PutMapping("/{id}")
    public InstitutionTables updateTable(@PathVariable Long id, @RequestBody InstitutionTables table) {
        return institutionTablesService.updateTable(id, table);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        institutionTablesService.deleteTable(id);
    }
}
