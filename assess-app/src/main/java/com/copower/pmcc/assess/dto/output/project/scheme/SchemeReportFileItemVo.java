package com.copower.pmcc.assess.dto.output.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;

public class SchemeReportFileItemVo extends SchemeReportFileItem {
    private String fileViewName;
    private String certifyPartName;
    private String certifyPartCategoryName;
    private String bisEnableName;

    public String getCertifyPartName() {
        return certifyPartName;
    }

    public void setCertifyPartName(String certifyPartName) {
        this.certifyPartName = certifyPartName;
    }

    public String getCertifyPartCategoryName() {
        return certifyPartCategoryName;
    }

    public void setCertifyPartCategoryName(String certifyPartCategoryName) {
        this.certifyPartCategoryName = certifyPartCategoryName;
    }

    public String getBisEnableName() {
        return bisEnableName;
    }

    public void setBisEnableName(String bisEnableName) {
        this.bisEnableName = bisEnableName;
    }

    public String getFileViewName() {
        return fileViewName;
    }

    public void setFileViewName(String fileViewName) {
        this.fileViewName = fileViewName;
    }
}
