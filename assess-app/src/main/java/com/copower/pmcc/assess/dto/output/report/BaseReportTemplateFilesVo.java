package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateFiles;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/7
 * @time: 18:01
 */
public class BaseReportTemplateFilesVo extends BaseReportTemplateFiles {

    private String classifyName;

    private List<String> reportFiles;

    private List<String> exportFiles;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<String> getReportFiles() {
        return reportFiles;
    }

    public void setReportFiles(List<String> reportFiles) {
        this.reportFiles = reportFiles;
    }

    public List<String> getExportFiles() {
        return exportFiles;
    }

    public void setExportFiles(List<String> exportFiles) {
        this.exportFiles = exportFiles;
    }
}
