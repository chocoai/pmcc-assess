package com.copower.pmcc.assess.dto.output.project;


import com.copower.pmcc.assess.dal.entity.ProjectWorkStage;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/06 14:34
 */
public class ProjectWorkStageVo extends ProjectWorkStage {

    private String boxCnName;

    private String reviewBoxCnName;

    public String getBoxCnName() {
        return boxCnName;
    }

    public void setBoxCnName(String boxCnName) {
        this.boxCnName = boxCnName;
    }

    public String getReviewBoxCnName() {
        return reviewBoxCnName;
    }

    public void setReviewBoxCnName(String reviewBoxCnName) {
        this.reviewBoxCnName = reviewBoxCnName;
    }
}
