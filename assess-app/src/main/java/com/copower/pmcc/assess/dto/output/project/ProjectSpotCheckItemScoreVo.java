package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

public class ProjectSpotCheckItemScoreVo extends ProjectReviewScoreItem {
    private String creatorName;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
