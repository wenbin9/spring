package com.spring.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.spring.web.rest.TestUtil;

public class ReportTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportTemplate.class);
        ReportTemplate reportTemplate1 = new ReportTemplate();
        reportTemplate1.setId(1L);
        ReportTemplate reportTemplate2 = new ReportTemplate();
        reportTemplate2.setId(reportTemplate1.getId());
        assertThat(reportTemplate1).isEqualTo(reportTemplate2);
        reportTemplate2.setId(2L);
        assertThat(reportTemplate1).isNotEqualTo(reportTemplate2);
        reportTemplate1.setId(null);
        assertThat(reportTemplate1).isNotEqualTo(reportTemplate2);
    }
}
