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
    SYS_URL_KEY("sys.url.key", "系统链接地址key", "sys"),
    SYS_ASSESS_VERSION("sys.assess.version", "系统更新版本号", "sys"),
    SYS_CLIMBING_NET_CASE("sys.climbing.net.case", "自动爬取网络案例数据", "sys"),
    LEGWORK_BONUS_ASSESSMENT("legwork.bonus.assessment", "外勤加分考核任务", "sys"),
    PROJECTINITVIEWURL("project.init.view.url", "项目立项视图路径", "sys"),
    COMPANY_NAME("company.name", "公司名称", "sys"),

    PROJECT_AUTOMATIC_GENERATE_CASE_KEY("project.automatic.generate.case.key", "自动处理案例", "sys"),
    CSR_PROJECT_APPLY_PROCESS_KEY("csr.project.apply.process.key", "债权项目立项流程key", "box"),
    PROJECT_FORM_TASK_PROCESS_KEY("project.form.task.process.key", "项目立项审批流程key", "box"),
    PROJECT_FORM_PROGRAMME_PROCESS_KEY("project.from.programme.process.key", "方案审批流程key", "box"),

    PROJECT_APPLY_ASSIGN_PROCESS_KEY("project.apply.assign.process.key", "任务再分派流程key", "box"),
    PROJECT_INFORMATION_CHANGE_PROCESS_KEY("project.information.change.process.key", "项目信息变更流程key", "box"),
    PROJECT_STOP_CHANGE_PROCESS_KEY("project.stop.change.process.key", "项目终止变更流程key", "box"),
    PROJECT_PAUSE_CHANGE_PROCESS_KEY("project.pause.change.process.key", "项目暂停变更流程key", "box"),
    PROJECT_RESTART_CHANGE_PROCESS_KEY("project.restart.change.process.key", "项目重启变更流程key", "box"),
    PROJECT_MEMBER_CHANGE_PROCESS_KEY("project.member.change.process.key", "项目成员变更流程模型", "box"),
    PROJECT_SCHEME_CHANGE_PROCESS_KEY("project.scheme.change.process.key", "项目方案变更流程模型", "box"),
    PROJECT_DETAILS_RESTART_PROCESS_KEY("project.details.restart.process.key", "项目阶段重启流程模型", "box"),
    PROJECT_DETAILS_DOCUMENT_SEND_PROCESS_KEY("project.details.document.send.process.key", "公司盖章发文审批流程", "box"),
    PROJECT_DETAILS_DOCUMENT_SEND_CLIENT_PROCESS_KEY("project.details.document.send.client.process.key", "委托方盖章发文审批流程", "box"),
    PROJECT_DETAILS_DOCUMENT_OPINION_PROCESS_KEY("project.details.document.opinion.process.key", "项目意见稿审批流程", "box"),
    PROJECT_DETAILS_DOCUMENT_SIGN_BILL_PROCESS_KEY("project.details.document.sign.bill.process.key", "报告签收单审批流程", "box"),

    PROJECT_SUBSEQUENT_PROCESS_KEY("project.subsequent.process.key", "项目后续事项", "box"),
    PROJECT_TAKE_NUMBER_PROCESS_KEY("project.take.number.process.key", "项目拿号", "box"),
    PROJECT_COMMISSION_PROCESS_KEY("project.commission.process.key", "项目提成流程", "box"),
    PROJECT_PIGEONHOLE_PROCESS_KEY("project.pigeonhole.process.key", "项目归档流程", "box"),

    PROJECT_ARCHIVES_ADMINISTRATIVE_NODE_KEY("project.archives.administrative.node.key", "项目归档-行政节点", "box"),

    ASSESSMENT_TASK_GENERATE_DATE("assessment.task.generate.date", "生成考核任务时间", "sys"),
    ASSESSMENT_TASK_GENERATE_PROJECT_ID("assessment.task.generate.project.id", "生成考核任务项目id", "box"),

    PROJECT_REVIEW_PROCESS_KEY("project.review.process.key", "复核工时考核流程", "box"),
    PROJECT_LEGWORK_PROCESS_KEY("project.legwork.process.key", "外勤加分考核流程", "box"),
    PROJECT_SPOT_CHECK_PROCESS_KEY("project.spot.check.process.key", "项目抽查考核流程", "box"),

    NET_INFO_COMPLEMENT_PROCESS_KEY("net.info.complement.process.key", "拍卖信息补全", "box"),
    NET_INFO_UPGRADE_PROCESS_KEY("net.info.upgrade.process.key", "拍卖信息升级", "box"),
    CASE_BASE_INFO_APPLY_KEY("case.base.info.apply.key", "案例信息申请key", "box"),
    DATA_LAND_LEVEL_APPLY_KEY("data.land.level.apply.key", "土地级别流程申请key", "box"),
    CASE_BASE_INFO_BATCH_APPLY_KEY("case.base.info.batch.apply.key", "案例信息批量申请key", "box");

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
