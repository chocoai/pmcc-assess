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
    private String nodeName;//ztree时显示的节点名称
    private String _parentId;

    private String executeUserName;

    private String executeDepartmentName;

    private String displayUrl;

    private String excuteUrl;

    private String declareFormName;

    private Boolean canAssignment;

    private Boolean canReplay;//是否允许重启

    private Boolean canCopy;//是否允许复制

    private Boolean canPaste;//是否允许粘贴

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

    public String getExcuteUrl() {
        return excuteUrl;
    }

    public void setExcuteUrl(String excuteUrl) {
        this.excuteUrl = excuteUrl;
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

    public Boolean getCanReplay() {
        return canReplay;
    }

    public void setCanReplay(Boolean canReplay) {
        this.canReplay = canReplay;
    }

    public Boolean getCanCopy() {
        return canCopy;
    }

    public void setCanCopy(Boolean canCopy) {
        this.canCopy = canCopy;
    }

    public Boolean getCanPaste() {
        return canPaste;
    }

    public void setCanPaste(Boolean canPaste) {
        this.canPaste = canPaste;
    }

    public List<KeyValueDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<KeyValueDto> tasks) {
        this.tasks = tasks;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
