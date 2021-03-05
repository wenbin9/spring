package com.spring.repository;

import com.spring.domain.ReportTemplate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReportTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {
}
