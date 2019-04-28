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
    public static final String DATA_ENTRUSTMENT_PURPOSE = "data.entrustment.purpose";//委托目的
    public static final String DATA_ENTRUSTMENT_PURPOSE_MORTGAGE = "data.entrustment.purpose.mortgage";//委托目的-抵押评估
    public static final String DATA_ENTRUSTMENT_PURPOSE_IMPOSE = "data.entrustment.purpose.impose";//委托目的-征收评估
    public static final String DATA_EVALUATION_METHOD = "data.evaluation.method";//评估方法
    public static final String DATA_BUILDING_NEW_RATE_USE = "data.building.new.rate.use";//建筑成新率用途
    public static final String DATA_EARLYWARNING_TYPE = "data.earlywarning.type";  //预警类型
    public static final String DATA_EARLYWARNING_MODE = "data.earlywarning.mode";  //预警方式
    public static final String DATA_BUILDER_QUALIFICATION_LEVEL = "data.builder.qualification_level";//建造商资质等级
    public static final String DATA_COMPANY_REPUTATION = "data.company.reputation";//公司信誉
    public static final String DATA_SERVICE_CONTENT = "data.service.content";//物业服务

    /*
     * 报告配置
     */
    public static final String REPORT_TYPE = "report.type"; //报告类型
    public static final String REPORT_TYPE_PREAUDIT = "report.type.preaudit"; //预评报告
    public static final String REPORT_TYPE_TECHNOLOGY = "report.type.technology"; //技术报告
    public static final String REPORT_TYPE_RESULT = "report.type.result"; //结果报告
    public static final String REPORT_ANALYSIS_CATEGORY = "report.analysis.category";  //报告分析类别
    public static final String REPORT_ANALYSIS_CATEGORY_LIQUIDITY = "report.analysis.category.liquidity";  //变现能力分析
    public static final String REPORT_ANALYSIS_CATEGORY_RISK = "report.analysis.category.risk";  //风险提示
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND = "report.analysis.category.background";  //市场背景描述与分析
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_DEVELOPMENT = "report.analysis.category.background.development"; //社会经济发展概况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_GENERAL= "report.analysis.category.background.general"; //房地产市场总体状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_MARKET = "report.analysis.category.background.market"; //同类房地产市场状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_BLOCK = "report.analysis.category.background.block"; //同类房地产市场板块状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_PROPERTY = "report.analysis.category.background.property"; //背景估价对象区域物业总体状况

    /*
     * 项目立项
     */
    public static final String PROJECT_INITIATE_URGENCY = "project.initiate.urgency";  //紧急程度
    public static final String VALUE_TYPE = "value.type";  //价值类型

     /*
     * 项目申报
     */
     public static final String PROJECT_DECLARE_ECONOMIC_INDICATORS = "project.declare.economic.indicators";  //紧急程度
     public static final String PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE = "project.declare.house.certificate.type";//房产证类型
     public static final String PROJECT_DECLARE_COMMON_SITUATION = "project.declare.common.situation";//共有情况

    /*
     * 项目查勘
     */
    public static final String INVENTORY_CONTENT = "project.survey.inventory.content"; //清查内容
    public static final String INVENTORY_CONTENT_DEFAULT = "project.survey.inventory.content.default"; //清查内容默认
    public static final String INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS = "project.survey.inventory.content.default.actual.address"; //登记地址与实际地址
    public static final String INVENTORY_CONTENT_DEFAULT_STRUCTURE = "project.survey.inventory.content.default.structure"; //登记结构与实际结构
    public static final String INVENTORY_CONTENT_DEFAULT_USE = "project.survey.inventory.content.default.use"; //登记用途与实际用途
    public static final String INVENTORY_CONTENT_DEFAULT_AREA = "project.survey.inventory.content.default.area"; //登记面积与实际面积
    public static final String INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS = "project.survey.inventory.content.default.house.land.address"; //房产证与土地证证载地址


    public static final String INVENTORY_RIGHT_TYPE = "project.survey.inventory.right.type";//他权类型
    public static final String INVENTORY_RIGHT_TYPE_HOUSE_LEASEHOLD = "project.survey.inventory.right.type.house.leasehold";//租赁权
    public static final String INVENTORY_RIGHT_TYPE_HOUSE_GUARANTEE = "project.survey.inventory.right.type.house.guarantee";//担保权
    public static final String INVENTORY_RIGHT_TYPE_HOUSE_PLEDGE = "project.survey.inventory.right.type.house.pledge";//房产抵押权
    public static final String INVENTORY_RIGHT_TYPE_HOUSE_OTHER = "project.survey.inventory.right.type.house.other";//房产其他

    public static final String CERTIFICATE_HANDLING_TYPE = "certificate.handling.type"; //是否办证
    public static final String CERTIFICATE_HANDLING_TYPE_PASS = "certificate.handling.type.pass"; //可办证
    public static final String CERTIFICATE_HANDLING_TYPE_REFUSE = "certificate.handling.type.refuse"; //不可办证

    /**
     * 评估方法
     */
    public static final String MD_MARKET_COMPARE = "md.market.compare"; //市场比较法
    public static final String MD_INCOME = "md.income"; //收益法
    public static final String MD_COST = "md.cost"; //成本法
    public static final String MD_DEVELOPMENT = "md.development"; //假设开发法
    public static final String MD_BASE_LAND_PRICE = "md.base.land.price"; //基准地价修正法
    public static final String MD_COST_APPROACH = "md.cost.approach"; //成本逼近法
    public static final String MD_STANDARD_ADJUSTMENT_PRICE = "md.standard.adjustment.price"; //标准价格调整法

    /*
     * 测算方法配置
     */
    public static final String MD_INCOME_HISTORY_TYPE_INCOME = "md.income.history.type.income"; //收入类
    public static final String MD_INCOME_HISTORY_TYPE_COST = "md.income.history.type.cost"; //成本类
    public static final String MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_EDITABLE = "md.income.rental_growth_rate_explain.editable"; //租金增长率描述（可调整）
    public static final String MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_READONLY = "md.income.rental_growth_rate_explain.readonly"; //租金增长率描述（固定
    public static final String MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_EDITABLE = "md.income.transaction_taxe_fee_ratio.editable"; //其它相关费用说明（可调整）
    public static final String MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_READONLY = "md.income.transaction_taxe_fee_ratio.readonly"; //其它相关费用说明（固定）

    /**
     * 税率配置
     */
    public static final String DATA_TAX_RATE_ALLOCATION_DEED_TAX = "data.tax.rate.allocation.deed.tax"; //土地取得契税
    public static final String DATA_TAX_RATE_ALLOCATION_TRANSACTION_COST = "data.tax.rate.allocation.transaction.cost"; //交易费用
    public static final String DATA_TAX_RATE_ALLOCATION_MANAGEMENT_COST = "data.tax.rate.allocation.management.cost"; //管理费用
    public static final String DATA_TAX_RATE_ALLOCATION_SALES_TAX = "data.tax.rate.allocation.sales.tax"; //增值税
    public static final String DATA_TAX_RATE_ALLOCATION_CONSTRUCTION_TAX = "data.tax.rate.allocation.construction.tax"; //城建税
    public static final String DATA_TAX_RATE_ALLOCATION_LOCAL_EDUCATION_TAX_ADDITIONAL = "data.tax.rate.allocation.local.education.tax.additional"; //地方教育税附加
    public static final String DATA_TAX_RATE_ALLOCATION_EDUCATION_FEE_PLUS = "data.tax.rate.allocation.education.fee.plus"; //教育费附加
    public static final String DATA_TAX_RATE_ALLOCATION_STAMP_DUTY = "data.tax.rate.allocation.stamp.duty"; //印花税
    public static final String DATA_TAX_RATE_ALLOCATION_PROPERTY_TAX = "data.tax.rate.allocation.property.tax"; //房产税
    public static final String DATA_TAX_RATE_ALLOCATION_LAND_INCREMENT_TAX = "data.tax.rate.allocation.land.increment.tax"; //土地增值税
    public static final String DATA_TAX_RATE_ALLOCATION_LAND_REPLACEMENT_VALUE = "data.tax.rate.allocation.land.replacement.value"; //重置价格
    public static final String DATA_TAX_RATE_ALLOCATION_TRANSACTION_CHARGES = "data.tax.rate.allocation.transaction.charges"; //交易手续费
    public static final String DATA_TAX_RATE_ALLOCATION_OTHER_TAXES_FEE = "data.tax.rate.allocation.other.taxes.fee"; //其它税费
    public static final String DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX = "data.tax.rate.allocation.corporate.income.tax"; //企业所得税

    /**
     * 工作方案
     */
    public static final String PROGRAMME_VALUE_CONNOTATION = "programme.value.connotation"; //价值内涵
    public static final String WORK_PROGRAMME_SET_USE = "work.programme.set.use"; //设定用途


}
