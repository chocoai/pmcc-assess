package com.copower.pmcc.assess.constant;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/12/25
 * @time: 15:23
 */
public class AssessCacheConstant {
    //项目类型
    public static final String PMCC_ASSESS_BASE_CATEGORY_ID = "pmcc:basis:base:category:id";
    public static final String PMCC_ASSESS_BASE_CATEGORY_PID = "pmcc:basis:base:category:pid";
    public static final String PMCC_ASSESS_BASE_CATEGORY_KEY = "pmcc:basis:base:category:key";

    //项目分类
    public static final String PMCC_ASSESS_PROJECT_CLASSIFY = "pmcc:basis:project:classify";
    public static final String PMCC_ASSESS_PROJECT_CLASSIFY_ID = "pmcc:basis:project:classify:id";
    public static final String PMCC_ASSESS_PROJECT_CLASSIFY_PID = "pmcc:basis:project:classify:pid";
    public static final String PMCC_ASSESS_PROJECT_CLASSIFY_FIELD = "pmcc:basis:project:classify:field";

    //项目阶段
    public static final String PMCC_ASSESS_WORK_STAGE = "pmcc:basis:work:stage";
    public static final String PMCC_ASSESS_WORK_STAGE_ID = "pmcc:basis:work:stage:id";
    public static final String PMCC_ASSESS_WORK_STAGE_TYPEID = "pmcc:basis:work:stage:typeid";


    //阶段事项
    public static final String PMCC_ASSESS_WORK_PHASE = "pmcc:basis:work:phase";
    public static final String PMCC_ASSESS_WORK_PHASE_ID = "pmcc:basis:work:phase:id";
    public static final String PMCC_ASSESS_WORK_PHASE_KEY = "pmcc:basis:work:phase:key";
    public static final String PMCC_ASSESS_WORK_PHASE_CATEGORYID = "pmcc:basis:work:phase:categoryid";


    //数据字典
    public static final String PMCC_ASSESS_DATA_DIC = "pmcc:basis:data:dic";
    public static final String PMCC_ASSESS_DATA_DIC_ID = "pmcc:basis:data:dic:id";
    public static final String PMCC_ASSESS_DATA_DIC_PID = "pmcc:basis:data:dic:pid";
    public static final String PMCC_ASSESS_DATA_DIC_FIELD = "pmcc:basis:data:dic:field";
    public static final String PMCC_ASSESS_DATA_DIC_FIELD_ITEM = "pmcc:basis:data:dic:field:item";

    //参数
    public static final String PMCC_ASSESS_PARAMETER_KEY = "pmcc:basis:parameter:key";

    //系统参数配置变量
    public static final String DETAILS_PROJECT_SUSPEND = "details:project:suspend";//项目暂停流程模型
    public static final String DETAILS_PROJECT_STOP = "details:project:stop";//项目终止流程模型
    public static final String DETAILS_PROJECT_RESTART = "details:project:restart";//阶段重启流程模型
    public static final String DETAILS_PROJECT_PLAN = "details:project:plan";//总计划变更流程
    public static final String PHASE_RESTART_EXECUTION_RULE = "phase:restart:execution:rule";//阶段重启执行规则

    //工作事项模板文件
    public static final String PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_WORK = "pmcc:basis:attachment:phase:template:work";
    public static final String PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_PROCESS = "pmcc:basis:attachment:phase:template:process";

    //动态表单
    public static final String PMCC_ASSESS_DYNAMIC_FORM_MODULE = "pmcc:basis:dynamic:form:module";
    public static final String PMCC_ASSESS_DYNAMIC_FORM_MODULE_ID = "pmcc:basis:dynamic:form:module:id";
    public static final String PMCC_ASSESS_DYNAMIC_FORM_MODULE_NAME = "pmcc:basis:dynamic:form:module:name";
    public static final String PMCC_ASSESS_DYNAMIC_FORM_DATASOURCE = "pmcc:basis:dynamic:form:datasource"; //数据源
    public static final String PMCC_ASSESS_DYNAMIC_FORM_TEXT = "pmcc:basis:dynamic:form:text"; //显示值

    //申报表单
    public static final String PMCC_ASSESS_DECLARE_FORM = "pmcc:basis:declare:form"; //申报表单

}
