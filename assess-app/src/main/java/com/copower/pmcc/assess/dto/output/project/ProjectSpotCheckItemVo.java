package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

public class ProjectSpotCheckItemVo extends ProjectSpotCheckItem {
    private String creatorName;
    private List<KeyValueDto> keyValueDtos;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<KeyValueDto> getKeyValueDtos() {
        return keyValueDtos;
    }

    public void setKeyValueDtos(List<KeyValueDto> keyValueDtos) {
        this.keyValueDtos = keyValueDtos;
    }
}
