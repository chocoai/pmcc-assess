package com.copower.pmcc.assess.dto.input.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateFiles;
import com.copower.pmcc.assess.dto.output.report.BaseReportTemplateVo;

import java.util.List;

/**
 * Created by kings on 2018-6-15.
 */
public class BaseReportTemplateFilesDto {
    private BaseReportTemplateFiles baseReportTemplateFiles;
    private List<BaseReportTemplateVo> baseReportTemplateVoList;

    public BaseReportTemplateFiles getBaseReportTemplateFiles() {
        return baseReportTemplateFiles;
    }

    public void setBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        this.baseReportTemplateFiles = baseReportTemplateFiles;
    }

    public List<BaseReportTemplateVo> getBaseReportTemplateVoList() {
        return baseReportTemplateVoList;
    }

    public void setBaseReportTemplateVoList(List<BaseReportTemplateVo> baseReportTemplateVoList) {
        this.baseReportTemplateVoList = baseReportTemplateVoList;
    }
}
