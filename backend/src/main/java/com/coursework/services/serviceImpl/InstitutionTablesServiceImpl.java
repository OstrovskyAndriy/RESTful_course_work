package com.coursework.services.serviceImpl;

import com.coursework.models.InstitutionTables;
import com.coursework.repository.InstitutionTablesRepository;
import com.coursework.services.InstitutionTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionTablesServiceImpl implements InstitutionTablesService {

    private final InstitutionTablesRepository institutionTablesRepository;

    @Autowired
    public InstitutionTablesServiceImpl(InstitutionTablesRepository institutionTablesRepository) {
        this.institutionTablesRepository = institutionTablesRepository;
    }

    @Override
    public List<InstitutionTables> getAllTables() {
        return institutionTablesRepository.findAll();
    }

    @Override
    public InstitutionTables getTableById(Long id) {
        return institutionTablesRepository.findById(id).orElse(null);
    }

    @Override
    public InstitutionTables createTable(InstitutionTables table) {
        return institutionTablesRepository.save(table);
    }

    @Override
    public InstitutionTables updateTable(Long id, InstitutionTables table) {
        if (institutionTablesRepository.existsById(id)) {
            table.setId(id);
            return institutionTablesRepository.save(table);
        }
        return null;
    }

    @Override
    public void deleteTable(Long id) {
        institutionTablesRepository.deleteById(id);
    }

    public List<InstitutionTables> getTablesByInstitution(Long institutionId) {
        return institutionTablesRepository.findByInstitutionId(institutionId);
    }
}