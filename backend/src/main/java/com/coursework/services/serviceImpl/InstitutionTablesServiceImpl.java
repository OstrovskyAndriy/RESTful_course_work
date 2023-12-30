package com.coursework.services.serviceImpl;

import com.coursework.models.InstitutionTables;
import com.coursework.repository.InstitutionTablesRepository;
import com.coursework.services.InstitutionTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

@Service
public class InstitutionTablesServiceImpl implements InstitutionTablesService {

    private final InstitutionTablesRepository institutionTablesRepository;

    @Autowired
    public InstitutionTablesServiceImpl(InstitutionTablesRepository institutionTablesRepository) {
        this.institutionTablesRepository = institutionTablesRepository;
    }

    @Cacheable(value = "institutionTablesCache", key = "#id")
    public InstitutionTables getTableById(Long id) {
        return institutionTablesRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "institutionTablesCache")
    public List<InstitutionTables> getAllTables() {
        return institutionTablesRepository.findAll();
    }

    @CachePut(value = "institutionTablesCache", key = "#result.id")
    public InstitutionTables createTable(InstitutionTables table) {
        return institutionTablesRepository.save(table);
    }

    @CachePut(value = "institutionTablesCache", key = "#id")
    public InstitutionTables updateTable(Long id, InstitutionTables table) {
        if (institutionTablesRepository.existsById(id)) {
            table.setId(id);
            return institutionTablesRepository.save(table);
        }
        return null;
    }

    @CacheEvict(value = "institutionTablesCache", key = "#id")
    public void deleteTable(Long id) {
        institutionTablesRepository.deleteById(id);
    }

    @Cacheable(value = "institutionTablesCache", key = "#institutionId")
    public List<InstitutionTables> getTablesByInstitution(Long institutionId) {
        return institutionTablesRepository.findByInstitutionId(institutionId);
    }
}