package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/7
 * @time: 18:01
 */
public class BaseReportTemplateVo extends BaseReportTemplate {
    private String entrustPurposeName;
    private List<String> report;

    public String getEntrustPurposeName() {
        return entrustPurposeName;
    }

    public void setEntrustPurposeName(String entrustPurposeName) {
        this.entrustPurposeName = entrustPurposeName;
    }

    public List<String> getReport() {
        return report;
    }

    public void setReport(List<String> report) {
        this.report = report;
    }

}
