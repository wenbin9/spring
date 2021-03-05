package com.spring.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "report_size")
    private Long reportSize;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_dt")
    private LocalDate createdDt;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public Report reportName(String reportName) {
        this.reportName = reportName;
        return this;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Long getReportSize() {
        return reportSize;
    }

    public Report reportSize(Long reportSize) {
        this.reportSize = reportSize;
        return this;
    }

    public void setReportSize(Long reportSize) {
        this.reportSize = reportSize;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Report createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDt() {
        return createdDt;
    }

    public Report createdDt(LocalDate createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public void setCreatedDt(LocalDate createdDt) {
        this.createdDt = createdDt;
    }

    public String getStatus() {
        return status;
    }

    public Report status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        return id != null && id.equals(((Report) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", reportName='" + getReportName() + "'" +
            ", reportSize=" + getReportSize() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDt='" + getCreatedDt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
