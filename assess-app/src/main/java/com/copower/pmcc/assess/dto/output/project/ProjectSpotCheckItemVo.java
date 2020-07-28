package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Date;
import java.util.List;

public class ProjectSpotCheckItemVo extends ProjectSpotCheckItem {
    private String examineName;
    private Date examineDate;
    private String content;

    public String getExamineName() {
        return examineName;
    }

    public void setExamineName(String examineName) {
        this.examineName = examineName;
    }

    public void setExamineDate(Date examineDate) {
        this.examineDate = examineDate;
    }

    public Date getExamineDate() {
        return examineDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
