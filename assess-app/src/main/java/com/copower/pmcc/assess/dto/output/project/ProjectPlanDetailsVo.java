package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 17:00
 */
public class ProjectPlanDetailsVo extends ProjectPlanDetails {
    private String _parentId;

    private String executeUserName;

    private String executeDepartmentName;

    private String displayUrl;

    private List<String> executeUrlList;

    private String declareFormName;

    private Boolean canAssignment;

    private List<KeyValueDto> tasks;

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public String getExecuteDepartmentName() {
        return executeDepartmentName;
    }

    public void setExecuteDepartmentName(String executeDepartmentName) {
        this.executeDepartmentName = executeDepartmentName;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public List<String> getExecuteUrlList() {
        return executeUrlList;
    }

    public void setExecuteUrlList(List<String> executeUrlList) {
        this.executeUrlList = executeUrlList;
    }

    public String getDeclareFormName() {
        return declareFormName;
    }

    public void setDeclareFormName(String declareFormName) {
        this.declareFormName = declareFormName;
    }

    public Boolean getCanAssignment() {
        return canAssignment;
    }

    public void setCanAssignment(Boolean canAssignment) {
        this.canAssignment = canAssignment;
    }

    public List<KeyValueDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<KeyValueDto> tasks) {
        this.tasks = tasks;
    }
}
