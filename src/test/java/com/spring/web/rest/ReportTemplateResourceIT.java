package com.spring.web.rest;

import com.spring.SpringApp;
import com.spring.domain.ReportTemplate;
import com.spring.repository.ReportTemplateRepository;
import com.spring.service.ReportTemplateService;
import com.spring.service.dto.ReportTemplateDTO;
import com.spring.service.mapper.ReportTemplateMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReportTemplateResource} REST controller.
 */
@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReportTemplateResourceIT {

    private static final String DEFAULT_TEMPLATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_FILE_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TEMPLATE_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TEMPLATE_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TEMPLATE_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TEMPLATE_FILE_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_EFFECTIVE_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_DT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRY_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRY_DT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    @Autowired
    private ReportTemplateRepository reportTemplateRepository;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Autowired
    private ReportTemplateService reportTemplateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportTemplateMockMvc;

    private ReportTemplate reportTemplate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportTemplate createEntity(EntityManager em) {
        ReportTemplate reportTemplate = new ReportTemplate()
            .templateId(DEFAULT_TEMPLATE_ID)
            .templateName(DEFAULT_TEMPLATE_NAME)
            .templateFileName(DEFAULT_TEMPLATE_FILE_NAME)
            .templateFile(DEFAULT_TEMPLATE_FILE)
            .templateFileContentType(DEFAULT_TEMPLATE_FILE_CONTENT_TYPE)
            .effectiveDt(DEFAULT_EFFECTIVE_DT)
            .expiryDt(DEFAULT_EXPIRY_DT)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDt(DEFAULT_CREATED_DT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedDt(DEFAULT_UPDATED_DT)
            .version(DEFAULT_VERSION);
        return reportTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportTemplate createUpdatedEntity(EntityManager em) {
        ReportTemplate reportTemplate = new ReportTemplate()
            .templateId(UPDATED_TEMPLATE_ID)
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateFileName(UPDATED_TEMPLATE_FILE_NAME)
            .templateFile(UPDATED_TEMPLATE_FILE)
            .templateFileContentType(UPDATED_TEMPLATE_FILE_CONTENT_TYPE)
            .effectiveDt(UPDATED_EFFECTIVE_DT)
            .expiryDt(UPDATED_EXPIRY_DT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDt(UPDATED_CREATED_DT)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDt(UPDATED_UPDATED_DT)
            .version(UPDATED_VERSION);
        return reportTemplate;
    }

    @BeforeEach
    public void initTest() {
        reportTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportTemplate() throws Exception {
        int databaseSizeBeforeCreate = reportTemplateRepository.findAll().size();
        // Create the ReportTemplate
        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(reportTemplate);
        restReportTemplateMockMvc.perform(post("/api/report-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the ReportTemplate in the database
        List<ReportTemplate> reportTemplateList = reportTemplateRepository.findAll();
        assertThat(reportTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        ReportTemplate testReportTemplate = reportTemplateList.get(reportTemplateList.size() - 1);
        assertThat(testReportTemplate.getTemplateId()).isEqualTo(DEFAULT_TEMPLATE_ID);
        assertThat(testReportTemplate.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testReportTemplate.getTemplateFileName()).isEqualTo(DEFAULT_TEMPLATE_FILE_NAME);
        assertThat(testReportTemplate.getTemplateFile()).isEqualTo(DEFAULT_TEMPLATE_FILE);
        assertThat(testReportTemplate.getTemplateFileContentType()).isEqualTo(DEFAULT_TEMPLATE_FILE_CONTENT_TYPE);
        assertThat(testReportTemplate.getEffectiveDt()).isEqualTo(DEFAULT_EFFECTIVE_DT);
        assertThat(testReportTemplate.getExpiryDt()).isEqualTo(DEFAULT_EXPIRY_DT);
        assertThat(testReportTemplate.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReportTemplate.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testReportTemplate.getCreatedDt()).isEqualTo(DEFAULT_CREATED_DT);
        assertThat(testReportTemplate.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testReportTemplate.getUpdatedDt()).isEqualTo(DEFAULT_UPDATED_DT);
        assertThat(testReportTemplate.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createReportTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportTemplateRepository.findAll().size();

        // Create the ReportTemplate with an existing ID
        reportTemplate.setId(1L);
        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(reportTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportTemplateMockMvc.perform(post("/api/report-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportTemplate in the database
        List<ReportTemplate> reportTemplateList = reportTemplateRepository.findAll();
        assertThat(reportTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReportTemplates() throws Exception {
        // Initialize the database
        reportTemplateRepository.saveAndFlush(reportTemplate);

        // Get all the reportTemplateList
        restReportTemplateMockMvc.perform(get("/api/report-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateId").value(hasItem(DEFAULT_TEMPLATE_ID)))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].templateFileName").value(hasItem(DEFAULT_TEMPLATE_FILE_NAME)))
            .andExpect(jsonPath("$.[*].templateFileContentType").value(hasItem(DEFAULT_TEMPLATE_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].templateFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_TEMPLATE_FILE))))
            .andExpect(jsonPath("$.[*].effectiveDt").value(hasItem(DEFAULT_EFFECTIVE_DT.toString())))
            .andExpect(jsonPath("$.[*].expiryDt").value(hasItem(DEFAULT_EXPIRY_DT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDt").value(hasItem(DEFAULT_CREATED_DT.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedDt").value(hasItem(DEFAULT_UPDATED_DT.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getReportTemplate() throws Exception {
        // Initialize the database
        reportTemplateRepository.saveAndFlush(reportTemplate);

        // Get the reportTemplate
        restReportTemplateMockMvc.perform(get("/api/report-templates/{id}", reportTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportTemplate.getId().intValue()))
            .andExpect(jsonPath("$.templateId").value(DEFAULT_TEMPLATE_ID))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.templateFileName").value(DEFAULT_TEMPLATE_FILE_NAME))
            .andExpect(jsonPath("$.templateFileContentType").value(DEFAULT_TEMPLATE_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.templateFile").value(Base64Utils.encodeToString(DEFAULT_TEMPLATE_FILE)))
            .andExpect(jsonPath("$.effectiveDt").value(DEFAULT_EFFECTIVE_DT.toString()))
            .andExpect(jsonPath("$.expiryDt").value(DEFAULT_EXPIRY_DT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDt").value(DEFAULT_CREATED_DT.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedDt").value(DEFAULT_UPDATED_DT.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingReportTemplate() throws Exception {
        // Get the reportTemplate
        restReportTemplateMockMvc.perform(get("/api/report-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportTemplate() throws Exception {
        // Initialize the database
        reportTemplateRepository.saveAndFlush(reportTemplate);

        int databaseSizeBeforeUpdate = reportTemplateRepository.findAll().size();

        // Update the reportTemplate
        ReportTemplate updatedReportTemplate = reportTemplateRepository.findById(reportTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedReportTemplate are not directly saved in db
        em.detach(updatedReportTemplate);
        updatedReportTemplate
            .templateId(UPDATED_TEMPLATE_ID)
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateFileName(UPDATED_TEMPLATE_FILE_NAME)
            .templateFile(UPDATED_TEMPLATE_FILE)
            .templateFileContentType(UPDATED_TEMPLATE_FILE_CONTENT_TYPE)
            .effectiveDt(UPDATED_EFFECTIVE_DT)
            .expiryDt(UPDATED_EXPIRY_DT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDt(UPDATED_CREATED_DT)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDt(UPDATED_UPDATED_DT)
            .version(UPDATED_VERSION);
        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(updatedReportTemplate);

        restReportTemplateMockMvc.perform(put("/api/report-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the ReportTemplate in the database
        List<ReportTemplate> reportTemplateList = reportTemplateRepository.findAll();
        assertThat(reportTemplateList).hasSize(databaseSizeBeforeUpdate);
        ReportTemplate testReportTemplate = reportTemplateList.get(reportTemplateList.size() - 1);
        assertThat(testReportTemplate.getTemplateId()).isEqualTo(UPDATED_TEMPLATE_ID);
        assertThat(testReportTemplate.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testReportTemplate.getTemplateFileName()).isEqualTo(UPDATED_TEMPLATE_FILE_NAME);
        assertThat(testReportTemplate.getTemplateFile()).isEqualTo(UPDATED_TEMPLATE_FILE);
        assertThat(testReportTemplate.getTemplateFileContentType()).isEqualTo(UPDATED_TEMPLATE_FILE_CONTENT_TYPE);
        assertThat(testReportTemplate.getEffectiveDt()).isEqualTo(UPDATED_EFFECTIVE_DT);
        assertThat(testReportTemplate.getExpiryDt()).isEqualTo(UPDATED_EXPIRY_DT);
        assertThat(testReportTemplate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReportTemplate.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testReportTemplate.getCreatedDt()).isEqualTo(UPDATED_CREATED_DT);
        assertThat(testReportTemplate.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testReportTemplate.getUpdatedDt()).isEqualTo(UPDATED_UPDATED_DT);
        assertThat(testReportTemplate.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingReportTemplate() throws Exception {
        int databaseSizeBeforeUpdate = reportTemplateRepository.findAll().size();

        // Create the ReportTemplate
        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(reportTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportTemplateMockMvc.perform(put("/api/report-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportTemplate in the database
        List<ReportTemplate> reportTemplateList = reportTemplateRepository.findAll();
        assertThat(reportTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportTemplate() throws Exception {
        // Initialize the database
        reportTemplateRepository.saveAndFlush(reportTemplate);

        int databaseSizeBeforeDelete = reportTemplateRepository.findAll().size();

        // Delete the reportTemplate
        restReportTemplateMockMvc.perform(delete("/api/report-templates/{id}", reportTemplate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportTemplate> reportTemplateList = reportTemplateRepository.findAll();
        assertThat(reportTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
