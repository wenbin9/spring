package com.spring.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.spring.domain.ReportTemplate} entity.
 */
public class ReportTemplateDTO implements Serializable {
    
    private Long id;

    private String templateId;

    private String templateName;

    private String templateFileName;

    @Lob
    private byte[] templateFile;

    private String templateFileContentType;
    private LocalDate effectiveDt;

    private LocalDate expiryDt;

    private String status;

    private String createdBy;

    private LocalDate createdDt;

    private String updatedBy;

    private LocalDate updatedDt;

    private Integer version;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public byte[] getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(byte[] templateFile) {
        this.templateFile = templateFile;
    }

    public String getTemplateFileContentType() {
        return templateFileContentType;
    }

    public void setTemplateFileContentType(String templateFileContentType) {
        this.templateFileContentType = templateFileContentType;
    }

    public LocalDate getEffectiveDt() {
        return effectiveDt;
    }

    public void setEffectiveDt(LocalDate effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public LocalDate getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(LocalDate expiryDt) {
        this.expiryDt = expiryDt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(LocalDate updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportTemplateDTO)) {
            return false;
        }

        return id != null && id.equals(((ReportTemplateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportTemplateDTO{" +
            "id=" + getId() +
            ", templateId='" + getTemplateId() + "'" +
            ", templateName='" + getTemplateName() + "'" +
            ", templateFileName='" + getTemplateFileName() + "'" +
            ", templateFile='" + getTemplateFile() + "'" +
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
