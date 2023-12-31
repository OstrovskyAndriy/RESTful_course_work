package com.coursework.services;

import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;

import java.util.List;

public interface InstitutionService {
    //List<Institution> getAllInstitutions();
    List<Institution> getAllInstitutions(int page, int size);

    Institution getInstitutionById(Long id);

    Institution createInstitution(Institution institution);

    Institution updateInstitution(Long id, Institution institution);

    void deleteInstitution(Long id);

    List<InstitutionTables> getTablesByInstitution(Long id);
}