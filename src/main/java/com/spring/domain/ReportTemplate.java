package com.spring.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ReportTemplate.
 */
@Entity
@Table(name = "report_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_id")
    private String templateId;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "template_file_name")
    private String templateFileName;

    @Lob
    @Column(name = "template_file")
    private byte[] templateFile;

    @Column(name = "template_file_content_type")
    private String templateFileContentType;

    @Column(name = "effective_dt")
    private LocalDate effectiveDt;

    @Column(name = "expiry_dt")
    private LocalDate expiryDt;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_dt")
    private LocalDate createdDt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_dt")
    private LocalDate updatedDt;

    @Column(name = "version")
    private Integer version;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public ReportTemplate templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public ReportTemplate templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public ReportTemplate templateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
        return this;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public byte[] getTemplateFile() {
        return templateFile;
    }

    public ReportTemplate templateFile(byte[] templateFile) {
        this.templateFile = templateFile;
        return this;
    }

    public void setTemplateFile(byte[] templateFile) {
        this.templateFile = templateFile;
    }

    public String getTemplateFileContentType() {
        return templateFileContentType;
    }

    public ReportTemplate templateFileContentType(String templateFileContentType) {
        this.templateFileContentType = templateFileContentType;
        return this;
    }

    public void setTemplateFileContentType(String templateFileContentType) {
        this.templateFileContentType = templateFileContentType;
    }

    public LocalDate getEffectiveDt() {
        return effectiveDt;
    }

    public ReportTemplate effectiveDt(LocalDate effectiveDt) {
        this.effectiveDt = effectiveDt;
        return this;
    }

    public void setEffectiveDt(LocalDate effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public LocalDate getExpiryDt() {
        return expiryDt;
    }

    public ReportTemplate expiryDt(LocalDate expiryDt) {
        this.expiryDt = expiryDt;
        return this;
    }

    public void setExpiryDt(LocalDate expiryDt) {
        this.expiryDt = expiryDt;
    }

    public String getStatus() {
        return status;
    }

    public ReportTemplate status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReportTemplate createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public ReportTemplate createdDt(LocalDate createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public ReportTemplate updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDt() {
        return updatedDt;
    }

    public ReportTemplate updatedDt(LocalDate updatedDt) {
        this.updatedDt = updatedDt;
        return this;
    }

    public void setUpdatedDt(LocalDate updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Integer getVersion() {
        return version;
    }

    public ReportTemplate version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportTemplate)) {
            return false;
        }
        return id != null && id.equals(((ReportTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportTemplate{" +
            "id=" + getId() +
            ", templateId='" + getTemplateId() + "'" +
            ", templateName='" + getTemplateName() + "'" +
            ", templateFileName='" + getTemplateFileName() + "'" +
            ", templateFile='" + getTemplateFile() + "'" +
            ", templateFileContentType='" + getTemplateFileContentType() + "'" +
            ", effectiveDt='" + getEffectiveDt() + "'" +
            ", expiryDt='" + getExpiryDt() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDt='" + getCreatedDt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedDt='" + getUpdatedDt() + "'" +
            ", version=" + getVersion() +
            "}";
    }
}
