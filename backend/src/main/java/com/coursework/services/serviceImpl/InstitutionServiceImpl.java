package com.coursework.services.serviceImpl;


import com.coursework.models.Institution;
import com.coursework.models.InstitutionTables;
import com.coursework.repository.InstitutionRepository;
import com.coursework.repository.InstitutionTablesRepository;
import com.coursework.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionTablesRepository institutionTablesRepository;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository, InstitutionTablesRepository institutionTablesRepository) {
        this.institutionRepository = institutionRepository;
        this.institutionTablesRepository = institutionTablesRepository;
    }

    @Override
    @Cacheable(value = "institutionsCache", key = "#id")
    public Institution getInstitutionById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

//    @Override
//    @Cacheable("institutionsCache")
//    public List<Institution> getAllInstitutions() {
//        return institutionRepository.findAll();
//    }

    @Override
    public List<Institution> getAllInstitutions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Institution> institutionPage = institutionRepository.findAll(pageable);
        return institutionPage.getContent();
    }

    @Override
    @CachePut(value = "institutionsCache", key = "#result.id")
    public Institution createInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    @CachePut(value = "institutionsCache", key = "#id")
    public Institution updateInstitution(Long id, Institution institution) {
        if (institutionRepository.existsById(id)) {
            institution.setId(id);
            return institutionRepository.save(institution);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "institutionsCache", key = "#id")
    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public List<InstitutionTables> getTablesByInstitution(Long id) {
        return institutionTablesRepository.findByInstitutionId(id);
    }
}
