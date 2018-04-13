package com.copower.pmcc.assess.dto.output.project;


import com.copower.pmcc.assess.dal.entity.ProjectPhase;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/04 10:04
 */
public class ProjectPhaseVo extends ProjectPhase {

    private String workStageName;
    //需要转义的
    private String boxCnName; //模型名称

    public String getWorkStageName() {
        return workStageName;
    }

    public void setWorkStageName(String workStageName) {
        this.workStageName = workStageName;
    }

    public String getBoxCnName() {
        return boxCnName;
    }

    public void setBoxCnName(String boxCnName) {
        this.boxCnName = boxCnName;
    }
}
