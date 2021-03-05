package com.spring.service;

import com.spring.service.dto.ReportTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.spring.domain.ReportTemplate}.
 */
public interface ReportTemplateService {

    /**
     * Save a reportTemplate.
     *
     * @param reportTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    ReportTemplateDTO save(ReportTemplateDTO reportTemplateDTO);

    /**
     * Get all the reportTemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reportTemplate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" reportTemplate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
