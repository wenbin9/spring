package com.spring.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportTemplateMapperTest {

    private ReportTemplateMapper reportTemplateMapper;

    @BeforeEach
    public void setUp() {
        reportTemplateMapper = new ReportTemplateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reportTemplateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reportTemplateMapper.fromId(null)).isNull();
    }
}
