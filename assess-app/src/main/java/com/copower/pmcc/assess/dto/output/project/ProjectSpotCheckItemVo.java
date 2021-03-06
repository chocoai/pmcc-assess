package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemScore;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Date;
import java.util.List;

public class ProjectSpotCheckItemVo extends ProjectSpotCheckItem {
    private String examineName;
    private Date examineDate;
    private List<ProjectSpotCheckItemScore> projectSpotCheckItemScoreList;

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

    public List<ProjectSpotCheckItemScore> getProjectSpotCheckItemScoreList() {
        return projectSpotCheckItemScoreList;
    }

    public void setProjectSpotCheckItemScoreList(List<ProjectSpotCheckItemScore> projectSpotCheckItemScoreList) {
        this.projectSpotCheckItemScoreList = projectSpotCheckItemScoreList;
    }
}
