// InstitutionRepository.java
package com.coursework.repository;

import com.coursework.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
