package com.copower.pmcc.assess.common.enums;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0 枚举
 * @date: 2018/05/11 15:18
 */
public enum BaseParameterEnum {
    /**
     * 说明==
     * <p>
     * 第一个为:参数唯一键
     * 第二个为:参数说明
     * 第三个为:参数分类(box表示是流程模型,其他自定义(如系统级sys等))
     */
    CSR_PROJECT_APPLY_PROCESS_KEY("csr.project.apply.process.key", "债权项目立项流程key", "box"),
    PROJECT_APPLY_ASSIGN_PROCESS_KEY("project.apply.assign.process.key", "任务再分派流程key", "box"),

    PROJECT_INFORMATION_CHANGE_PROCESS_KEY("project.information.change.process.key", "项目信息变更流程key", "box"),
    PROJECT_STOP_CHANGE_PROCESS_KEY("project.stop.change.process.key", "项目终止变更流程key", "box"),
    PROJECT_PAUSE_CHANGE_PROCESS_KEY("project.pause.change.process.key", "项目暂停变更流程key", "box"),
    PROJECT_RESTART_CHANGE_PROCESS_KEY("project.restart.change.process.key", "项目重启变更流程key", "box"),
    PROJECT_MEMBER_CHANGE_PROCESS_KEY("project.member.change.process.key", "项目成员变更流程模型", "box"),
    PROJECT_DETAILS_RESTART_PROCESS_KEY("project.details.restart.process.key", "项目阶段重启流程模型", "box"),

    CASE_BASE_INFO_APPLY_KEY("case.base.info.apply.key", "案例信息申请key", "box");

    private String parameterKey;
    private String remarks;
    private String className;
    private String defaultValue;

    BaseParameterEnum(String parameterKey, String remarks, String className) {
        this.parameterKey = parameterKey;
        this.remarks = remarks;
        this.className = className;
    }

    BaseParameterEnum(String parameterKey, String remarks, String className, String defaultValue) {
        this.parameterKey = parameterKey;
        this.remarks = remarks;
        this.className = className;
        this.defaultValue = defaultValue;
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return parameterKey;
    }

}
