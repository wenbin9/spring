package com.spring.web.rest;

import com.spring.service.ReportTemplateService;
import com.spring.web.rest.errors.BadRequestAlertException;
import com.spring.service.dto.ReportTemplateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.spring.domain.ReportTemplate}.
 */
@RestController
@RequestMapping("/api")
public class ReportTemplateResource {

    private final Logger log = LoggerFactory.getLogger(ReportTemplateResource.class);

    private static final String ENTITY_NAME = "reportTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportTemplateService reportTemplateService;

    public ReportTemplateResource(ReportTemplateService reportTemplateService) {
        this.reportTemplateService = reportTemplateService;
    }

    /**
     * {@code POST  /report-templates} : Create a new reportTemplate.
     *
     * @param reportTemplateDTO the reportTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportTemplateDTO, or with status {@code 400 (Bad Request)} if the reportTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/report-templates")
    public ResponseEntity<ReportTemplateDTO> createReportTemplate(@RequestBody ReportTemplateDTO reportTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save ReportTemplate : {}", reportTemplateDTO);
        if (reportTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportTemplateDTO result = reportTemplateService.save(reportTemplateDTO);
        return ResponseEntity.created(new URI("/api/report-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-templates} : Updates an existing reportTemplate.
     *
     * @param reportTemplateDTO the reportTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the reportTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/report-templates")
    public ResponseEntity<ReportTemplateDTO> updateReportTemplate(@RequestBody ReportTemplateDTO reportTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update ReportTemplate : {}", reportTemplateDTO);
        if (reportTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportTemplateDTO result = reportTemplateService.save(reportTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /report-templates} : get all the reportTemplates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportTemplates in body.
     */
    @GetMapping("/report-templates")
    public ResponseEntity<List<ReportTemplateDTO>> getAllReportTemplates(Pageable pageable) {
        log.debug("REST request to get a page of ReportTemplates");
        Page<ReportTemplateDTO> page = reportTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-templates/:id} : get the "id" reportTemplate.
     *
     * @param id the id of the reportTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/report-templates/{id}")
    public ResponseEntity<ReportTemplateDTO> getReportTemplate(@PathVariable Long id) {
        log.debug("REST request to get ReportTemplate : {}", id);
        Optional<ReportTemplateDTO> reportTemplateDTO = reportTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportTemplateDTO);
    }

    /**
     * {@code DELETE  /report-templates/:id} : delete the "id" reportTemplate.
     *
     * @param id the id of the reportTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/report-templates/{id}")
    public ResponseEntity<Void> deleteReportTemplate(@PathVariable Long id) {
        log.debug("REST request to delete ReportTemplate : {}", id);
        reportTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
