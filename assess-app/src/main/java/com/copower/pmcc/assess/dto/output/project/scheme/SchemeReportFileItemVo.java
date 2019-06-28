package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;

public class SchemeReportFileItemVo extends SchemeReportFileItem {
    private String fileViewName;

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
