package com.spring.service.impl;

import com.spring.service.ReportTemplateService;
import com.spring.domain.ReportTemplate;
import com.spring.repository.ReportTemplateRepository;
import com.spring.service.dto.ReportTemplateDTO;
import com.spring.service.mapper.ReportTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReportTemplate}.
 */
@Service
@Transactional
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final Logger log = LoggerFactory.getLogger(ReportTemplateServiceImpl.class);

    private final ReportTemplateRepository reportTemplateRepository;

    private final ReportTemplateMapper reportTemplateMapper;

    public ReportTemplateServiceImpl(ReportTemplateRepository reportTemplateRepository, ReportTemplateMapper reportTemplateMapper) {
        this.reportTemplateRepository = reportTemplateRepository;
        this.reportTemplateMapper = reportTemplateMapper;
    }

    @Override
    public ReportTemplateDTO save(ReportTemplateDTO reportTemplateDTO) {
        log.debug("Request to save ReportTemplate : {}", reportTemplateDTO);
        ReportTemplate reportTemplate = reportTemplateMapper.toEntity(reportTemplateDTO);
        reportTemplate = reportTemplateRepository.save(reportTemplate);
        return reportTemplateMapper.toDto(reportTemplate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReportTemplates");
        return reportTemplateRepository.findAll(pageable)
            .map(reportTemplateMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReportTemplateDTO> findOne(Long id) {
        log.debug("Request to get ReportTemplate : {}", id);
        return reportTemplateRepository.findById(id)
            .map(reportTemplateMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportTemplate : {}", id);
        reportTemplateRepository.deleteById(id);
    }
}
