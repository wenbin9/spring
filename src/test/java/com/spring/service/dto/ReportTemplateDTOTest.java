package com.spring.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.spring.web.rest.TestUtil;

public class ReportTemplateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportTemplateDTO.class);
        ReportTemplateDTO reportTemplateDTO1 = new ReportTemplateDTO();
        reportTemplateDTO1.setId(1L);
        ReportTemplateDTO reportTemplateDTO2 = new ReportTemplateDTO();
        assertThat(reportTemplateDTO1).isNotEqualTo(reportTemplateDTO2);
        reportTemplateDTO2.setId(reportTemplateDTO1.getId());
        assertThat(reportTemplateDTO1).isEqualTo(reportTemplateDTO2);
        reportTemplateDTO2.setId(2L);
        assertThat(reportTemplateDTO1).isNotEqualTo(reportTemplateDTO2);
        reportTemplateDTO1.setId(null);
        assertThat(reportTemplateDTO1).isNotEqualTo(reportTemplateDTO2);
    }
}
