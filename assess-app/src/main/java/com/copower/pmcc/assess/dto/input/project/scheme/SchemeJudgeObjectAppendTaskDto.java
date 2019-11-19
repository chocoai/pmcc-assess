package com.copower.pmcc.assess.dto.input.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.*;

import java.util.Map;

/**
 * Created by wangpc on 2019-11-18.
 */
public class SchemeJudgeObjectAppendTaskDto {
    private ProjectInfo projectInfo;
    private ProjectWorkStage projectWorkStage;
    private ProjectPlan projectPlan;
    private Integer sorting;
    private SchemeAreaGroup schemeAreaGroup;
    private SchemeJudgeObject schemeJudgeObject;
    private ProjectPhase phaseSurePrice;
    private String projectManager;
    private Map<Integer, ProjectPhase> phaseMap;

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public ProjectWorkStage getProjectWorkStage() {
        return projectWorkStage;
    }

    public void setProjectWorkStage(ProjectWorkStage projectWorkStage) {
        this.projectWorkStage = projectWorkStage;
    }

    public ProjectPlan getProjectPlan() {
        return projectPlan;
    }

    public void setProjectPlan(ProjectPlan projectPlan) {
        this.projectPlan = projectPlan;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public SchemeAreaGroup getSchemeAreaGroup() {
        return schemeAreaGroup;
    }

    public void setSchemeAreaGroup(SchemeAreaGroup schemeAreaGroup) {
        this.schemeAreaGroup = schemeAreaGroup;
    }

    public SchemeJudgeObject getSchemeJudgeObject() {
        return schemeJudgeObject;
    }

    public void setSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        this.schemeJudgeObject = schemeJudgeObject;
    }

    public ProjectPhase getPhaseSurePrice() {
        return phaseSurePrice;
    }

    public void setPhaseSurePrice(ProjectPhase phaseSurePrice) {
        this.phaseSurePrice = phaseSurePrice;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public Map<Integer, ProjectPhase> getPhaseMap() {
        return phaseMap;
    }

    public void setPhaseMap(Map<Integer, ProjectPhase> phaseMap) {
        this.phaseMap = phaseMap;
    }
}
