package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;

public class ProjectReviewScoreGroupVo extends ProjectReviewScoreGroup {
    private String creatorName;
    private List<ProjectReviewScoreItem> reviewScoreItemList;
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<ProjectReviewScoreItem> getReviewScoreItemList() {
        return reviewScoreItemList;
    }

    public void setReviewScoreItemList(List<ProjectReviewScoreItem> reviewScoreItemList) {
        this.reviewScoreItemList = reviewScoreItemList;
    }
}
