package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckScore;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

public class ProjectSpotCheckItemGroupVo extends ProjectSpotCheckItemGroup {
    private String creatorName;
    private List<ProjectSpotCheckItemScore> projectSpotCheckItemScoreList;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<ProjectSpotCheckItemScore> getProjectSpotCheckItemScoreList() {
        return projectSpotCheckItemScoreList;
    }

    public void setProjectSpotCheckItemScoreList(List<ProjectSpotCheckItemScore> projectSpotCheckItemScoreList) {
        this.projectSpotCheckItemScoreList = projectSpotCheckItemScoreList;
    }
}
