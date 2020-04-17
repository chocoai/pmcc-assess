package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;

import java.util.List;

/**
 * Created by kings on 2019-1-25.
 */
public class SchemeLiquidationAnalysisApplyDto {
    private Integer id;
    private String liquidRatios;
    private String liquidTime;
    private String remark;
    private List<SchemeLiquidationAnalysisGroupDto> taskLiquidationAnalysisGroups;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiquidRatios() {
        return liquidRatios;
    }

    public void setLiquidRatios(String liquidRatios) {
        this.liquidRatios = liquidRatios;
    }

    public String getLiquidTime() {
        return liquidTime;
    }

    public void setLiquidTime(String liquidTime) {
        this.liquidTime = liquidTime;
    }

    public List<SchemeLiquidationAnalysisGroupDto> getTaskLiquidationAnalysisGroups() {
        return taskLiquidationAnalysisGroups;
    }

    public void setTaskLiquidationAnalysisGroups(List<SchemeLiquidationAnalysisGroupDto> taskLiquidationAnalysisGroups) {
        this.taskLiquidationAnalysisGroups = taskLiquidationAnalysisGroups;
    }
}
