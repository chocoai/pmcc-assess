package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.BaseReportTemplateFiles;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/7
 * @time: 18:01
 */
public class BaseReportTemplateFilesVo extends BaseReportTemplateFiles {

    private List<KeyValueDto> reportFiles;

    private List<KeyValueDto> exportFiles;

    public List<KeyValueDto> getReportFiles() {
        return reportFiles;
    }

    public void setReportFiles(List<KeyValueDto> reportFiles) {
        this.reportFiles = reportFiles;
    }

    public List<KeyValueDto> getExportFiles() {
        return exportFiles;
    }

    public void setExportFiles(List<KeyValueDto> exportFiles) {
        this.exportFiles = exportFiles;
    }
}
