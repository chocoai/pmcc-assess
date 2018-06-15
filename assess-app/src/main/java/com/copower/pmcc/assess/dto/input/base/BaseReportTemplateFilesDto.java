package com.copower.pmcc.assess.dto.input.base;

import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.entity.BaseReportTemplateFiles;

import java.util.List;

/**
 * Created by kings on 2018-6-15.
 */
public class BaseReportTemplateFilesDto {
    private BaseReportTemplateFiles baseReportTemplateFiles;
    private List<BaseReportTemplate> baseReportTemplateList;

    public BaseReportTemplateFiles getBaseReportTemplateFiles() {
        return baseReportTemplateFiles;
    }

    public void setBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        this.baseReportTemplateFiles = baseReportTemplateFiles;
    }

    public List<BaseReportTemplate> getBaseReportTemplateList() {
        return baseReportTemplateList;
    }

    public void setBaseReportTemplateList(List<BaseReportTemplate> baseReportTemplateList) {
        this.baseReportTemplateList = baseReportTemplateList;
    }
}
