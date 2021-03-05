package com.spring.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spring.domain.Report} entity.
 */
public class ReportDTO implements Serializable {
    
    private Long id;

    private String reportName;

    private Long reportSize;

    private String createdBy;

    private LocalDate createdDt;

    private String status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Long getReportSize() {
        return reportSize;
    }

    public void setReportSize(Long reportSize) {
        this.reportSize = reportSize;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportDTO)) {
            return false;
        }

        return id != null && id.equals(((ReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDTO{" +
            "id=" + getId() +
            ", reportName='" + getReportName() + "'" +
            ", reportSize=" + getReportSize() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDt='" + getCreatedDt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
