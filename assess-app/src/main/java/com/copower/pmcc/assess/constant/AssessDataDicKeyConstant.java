package com.copower.pmcc.assess.constant;

/**
 * 描述:数据配置定义
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/3
 * @time: 13:45
 */
public class AssessDataDicKeyConstant {
    /*
     * 基础数据
     */
    public static final String ENTRUSTMENT_PURPOSE = "data.entrustment.purpose";//委托目的
    public static final String EVALUATION_METHOD = "data.evaluation.method";//评估方法
    public static final String BUILDING_NEW_RATE_USE = "data.building.new.rate.use";//建筑成新率用途
    public static final String EARLYWARNING_TYPE = "data.earlywarning.type";  //预警类型
    public static final String EARLYWARNING_MODE = "data.earlywarning.mode";  //预警方式

    /*
     * 报告配置
     */
    public static final String REPORT_TYPE = "report.type"; //报告类型
    public static final String REPORT_TYPE_PREAUDIT = "report.type.preaudit"; //预评报告
    public static final String REPORT_TYPE_TECHNOLOGY = "report.type.technology"; //技术报告
    public static final String REPORT_TYPE_RESULT = "report.type.result"; //结果报告
    public static final String REPORT_ANALYSIS_CATEGORY = "report.analysis.category";  //报告分析类别

    /*
     * 项目立项
     */
    public static final String PROJECT_INITIATE_URGENCY = "project.initiate.urgency";  //紧急程度
    public static final String VALUE_TYPE = "value.type";  //价值类型

    /*
     * 项目查勘
     */
    public static final String INVENTORY_CONTENT = "project.survey.inventory.content"; //清查内容
    public static final String INVENTORY_CONTENT_DEFAULT = "project.survey.inventory.content.default"; //清查内容默认
    public static final String INVENTORY_RIGHT_TYPE = "project.survey.inventory.right.type";//他权类型

    /**
     * 评估方法
     */
    public static final String MD_MARKET_COMPARE = "md.market.compare"; //市场比较法
    public static final String MD_INCOME = "md.income"; //收益法
    public static final String MD_COST = "md.cost"; //成本法
    public static final String MD_HYPOTHESIS = "md.hypothesis"; //假设开发法

    /*
     * 测算方法配置
     */
    public static final String MD_INCOME_HISTORY_TYPE_INCOME = "md.income.history.type.income"; //收入类
    public static final String MD_INCOME_HISTORY_TYPE_COST = "md.income.history.type.cost"; //收入类

}
