package com.coursework.repository;

import com.coursework.models.InstitutionTables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionTablesRepository extends JpaRepository<InstitutionTables, Long> {
    List<InstitutionTables> findByInstitutionId(Long institutionId);
}