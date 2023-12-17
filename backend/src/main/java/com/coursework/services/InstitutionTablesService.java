package com.coursework.services;

import com.coursework.models.InstitutionTables;

import java.util.List;

public interface InstitutionTablesService {
    List<InstitutionTables> getAllTables();

    InstitutionTables getTableById(Long id);

    InstitutionTables createTable(InstitutionTables table);

    InstitutionTables updateTable(Long id, InstitutionTables table);

    void deleteTable(Long id);
}
