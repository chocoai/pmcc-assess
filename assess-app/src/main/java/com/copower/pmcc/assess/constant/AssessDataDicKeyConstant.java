package com.copower.pmcc.assess.constant;

/**
 * 描述:数据配置定义
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/3
 * @time: 13:45
 */
public class AssessDataDicKeyConstant {

    public static final String PROJECT_DECLARE_ACQUISITION_TYPE = "project.declare.acquisition.type";//土地取得方式
    public static final String PROJECT_DECLARE_ROOM_TYPE = "project.declare.room.type";//房屋性质

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
    public static final String DATA_INDEX_LAND_TYPE = "data.index.land.type";//土地指数
    public static final String DATA_INDEX_HOUSE_TYPE = "data.index.house.type";//房产指数
    public static final String DATA_LOAN_TYPE = "data.loan.type";//贷款类型
    public static final String DATA_TAXES_BURDEN = "data.taxes.burden";//税费承担方
    public static final String DATA_TAXES_BURDEN_SELLER = "data.taxes.burden.seller";//卖方承担
    public static final String DATA_TAXES_BURDEN_BUYER = "data.taxes.burden.buyer";//买方承担
    public static final String DATA_TAXES_BURDEN_BOTH = "data.taxes.burden.both";//双方承担
    public static final String DATA_LOCALE_SURVEY_PICTURE_TEMPLATE = "data.locale.survey.picture.template";//现场查勘图片模板
    public static final String DATA_TEMPLATE_TYPE = "data.template.type";//模板类型
    public static final String DATA_TEMPLATE_TYPE_DISPATCH = "data.template.type.dispatch";//公司发文模板
    public static final String DATA_TEMPLATE_TYPE_DISPATCH_CLIENT = "data.template.type.dispatch.client";//委托方发文模板
    public static final String DATA_TEMPLATE_TYPE_OPINION = "data.template.type.opinion";//模板（意见稿）
    public static final String DATA_TEMPLATE_TYPE_REPORT_SIGNFOR = "data.template.type.report.signfor";//模板（报告签收单）
    public static final String DATA_BUILD_AREA_UNIT_PRICE = "data.build.area.unit.price";//单价内涵（建筑面积单价）
    public static final String DATA_INTERIOR_AREA_UNIT_PRICE = "data.interior.area.unit.price";//单价内涵（套内面积单价）
    public static final String DATA_OTHER_UNIT_PRICE = "data.other.unit.price";//单价内涵（其他）
    public static final String DATA_INITIATE_UNIT_TYPE = "project.initiate.unit.properties";//单位性质 暂时这么配置的


    /*
     * 土地逼近法补偿配置
     */
    public static final String DATA_LAND_APPROXIMATION_METHOD_SETTING = "data.land.approximation.method.setting"; //土地逼近法补偿配置
    public static final String DATA_LAND_APPROXIMATION_METHOD_LAND_COMPENSATE = "data.land.approximation.method.land.compensate"; //土地补偿费
    public static final String DATA_LAND_APPROXIMATION_METHOD_PLACEMENT_COMPENSATE = "data.land.approximation.method.placement.compensate"; //安置补助费
    public static final String DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE = "data.land.approximation.method.crops.compensate"; //青苗补偿费
    public static final String DATA_LAND_APPROXIMATION_METHOD_HOUSE_COMPENSATE = "data.land.approximation.method.house.compensate"; //住房安置费
    public static final String DATA_LAND_APPROXIMATION_METHOD_REMOVAL_AWARD = "data.land.approximation.method.removal.award"; //农房搬迁奖励基金
    public static final String DATA_LAND_APPROXIMATION_METHOD_VEGETABLE_BUILD = "data.land.approximation.method.vegetable.build"; //菜田建设金
    public static final String DATA_LAND_APPROXIMATION_METHOD_OCCUPATION_LAND = "data.land.approximation.method.occupation.land"; //耕地占用税
    public static final String DATA_LAND_APPROXIMATION_METHOD_PLOUGH_RECLAIM = "data.land.approximation.method.plough.reclaim"; //耕地开垦费
    public static final String DATA_LAND_APPROXIMATION_METHOD_LAND_MANAGER = "data.land.approximation.method.land.manager"; //土地管理费
    public static final String DATA_LAND_APPROXIMATION_METHOD_CANNOT_FORESEE = "data.land.approximation.method.cannot.foresee"; //不可预见费
    public static final String DATA_LAND_APPROXIMATION_METHOD_LAND_ACQUISITION = "data.land.approximation.method.land.acquisition"; //代征地比例

    /*
     * 报告配置
     */
    public static final String REPORT_TYPE = "report.type"; //报告类型
    public static final String REPORT_TYPE_PREAUDIT = "report.type.preaudit"; //预评报告
    public static final String REPORT_TYPE_CONSULTATION = "report.type.consultation"; //咨评报告
    public static final String REPORT_TYPE_TECHNOLOGY = "report.type.technology"; //技术报告
    public static final String REPORT_TYPE_RESULT = "report.type.result"; //结果报告
    public static final String REPORT_TYPE_CONFIRMATION_HOUSING_VALUATION = "report.type.confirmation.housing.valuation"; //房屋评估价值确认书
    public static final String REPORT_TYPE_PREAUDIT_PROPOSAL = "report.type.preaudit.proposal"; //预评意见书
    public static final String REPORT_ANALYSIS_CATEGORY = "report.analysis.category";  //报告分析类别
    public static final String REPORT_ANALYSIS_CATEGORY_LIQUIDITY = "report.analysis.category.liquidity";  //变现能力分析
    public static final String REPORT_ANALYSIS_CATEGORY_RISK = "report.analysis.category.risk";  //风险提示
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND = "report.analysis.category.background";  //市场背景描述与分析
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_DEVELOPMENT = "report.analysis.category.background.development"; //社会经济发展概况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_GENERAL = "report.analysis.category.background.general"; //房地产市场总体状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_MARKET = "report.analysis.category.background.market"; //同类房地产市场状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_BLOCK = "report.analysis.category.background.block"; //同类房地产市场板块状况
    public static final String REPORT_ANALYSIS_CATEGORY_BACKGROUND_PROPERTY = "report.analysis.category.background.property"; //背景估价对象区域物业总体状况


    /*
     * 报告模板附件
     */
    public static final String REPORT_ATTACHMENT_PREAUDIT = "report.house.attachment.preaudit"; //预评报告 附件
    public static final String REPORT_ATTACHMENT_CONSULTATION = "report.house.attachment.consultation"; //咨评报告 附件
    public static final String REPORT_ATTACHMENT_TECHNOLOGY = "report.house.attachment.technology"; //技术报告 附件
    public static final String REPORT_ATTACHMENT_RESULT = "report.house.attachment.result"; //结果报告附件 附件


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
    public static final String PROJECT_DECLARE_LAND = "project.declare.land";//土地申报类型
    public static final String PROJECT_DECLARE_LAND_BASE_TRANSACTION = "project.declare.land.base.transaction";//纯土地(交易)
    public static final String PROJECT_DECLARE_LAND_BASE_LEASE = "project.declare.land.base.lease";//纯土地(出租)
    public static final String PROJECT_DECLARE_LAND_INCLUDE_HOUSE_TRANSACTION = "project.declare.land.includeHouse.transaction";//	非纯土地(交易)  包含房产
    public static final String PROJECT_DECLARE_LAND_INCLUDE_HOUSE_LEASE = "project.declare.land.includeHouse.lease";//	非纯土地(出租) 包含房产
    public static final String projectDeclareCertificate_YES = "project.declare.certificate.yes";//	有权证
    public static final String projectDeclareCertificate_NO = "project.declare.certificate.not";//	无权证


    /*
     * 项目查勘
     */
    public static final String PROJECT_SURVEY_FORM_CLASSIFY = "project.survey.form.classify";//查勘案例表单大类
    public static final String PROJECT_SURVEY_FORM_CLASSIFY_SINGEL = "project.survey.form.classify.singel";//查勘案例表单大类-单套房产
    public static final String PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE = "project.survey.form.classify.multiple";//查勘案例表单大类-多套房产
    public static final String PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY = "project.survey.form.classify.land.only";//查勘案例表单大类-纯土地
    public static final String PROJECT_SURVEY_FORM_CLASSIFY_LAND = "project.survey.form.classify.land";//查勘案例表单大类-土地带房产
    public static final String PROJECT_SURVEY_BUILDING_STATUS = "project.survey.building.status";//建筑状态

    public static final String INVENTORY_CONTENT = "project.survey.inventory.content"; //清查内容
    public static final String INVENTORY_CONTENT_DEFAULT = "project.survey.inventory.content.default"; //清查内容默认
    public static final String INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS = "project.survey.inventory.content.default.actual.address"; //登记地址与实际地址
    public static final String INVENTORY_CONTENT_DEFAULT_STRUCTURE = "project.survey.inventory.content.default.structure"; //登记结构与实际结构
    public static final String INVENTORY_CONTENT_DEFAULT_USE = "project.survey.inventory.content.default.use"; //登记用途与实际用途
    public static final String INVENTORY_CONTENT_DEFAULT_SPACE = "project.survey.inventory.content.default.space"; //登记空间位置与实际空间位置
    public static final String INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS = "project.survey.inventory.content.default.house.land.address"; //房产证与土地证证载地址
    public static final String INVENTORY_CONTENT_DEFAULT_AREA = "project.survey.inventory.content.default.area"; //登记面积与实际面积
    public static final String INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND = "project.survey.inventory.content.default.four.to.land"; //土地四至


    public static final String HOUSE_INVENTORY_RIGHT_CATEGORY = "project.survey.house.inventory.right.category";//他权类型
    public static final String HOUSE_INVENTORY_RIGHT_CATEGORY_PLEDGE = "project.survey.house.inventory.right.category.pledge";//房产抵押权
    public static final String HOUSE_INVENTORY_RIGHT_CATEGORY_LEASEHOLD = "project.survey.house.inventory.right.category.leasehold";//房产租赁权
    public static final String HOUSE_INVENTORY_RIGHT_CATEGORY_GUARANTEE = "project.survey.house.inventory.right.category.guarantee";//房产担保权
    public static final String HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER = "project.survey.house.inventory.right.category.other";//房产其他

    public static final String CERTIFICATE_HANDLING_TYPE = "certificate.handling.type"; //是否办证
    public static final String CERTIFICATE_HANDLING_TYPE_PASS = "certificate.handling.type.pass"; //可办证
    public static final String CERTIFICATE_HANDLING_TYPE_REFUSE = "certificate.handling.type.refuse"; //不可办证

    /**
     * 评估方法
     */
    public static final String MD_MARKET_COMPARE = "md.market.compare"; //市场比较法
    public static final String MD_LAND_COMPARE = "md.land.compare"; //土地比较法
    public static final String MD_INCOME = "md.income"; //收益法
    public static final String MD_COST = "md.cost"; //成本法
    public static final String MD_DEVELOPMENT = "md.development"; //假设开发法
    public static final String MD_BASE_LAND_PRICE = "md.base.land.price"; //基准地价修正法
    public static final String MD_COST_APPROACH = "md.cost.approach"; //成本逼近法

    /**
     * 评估方法公式
     */
    public static final String COMPARE_FORMULA = "compare.formula"; //市场比较法公式
    public static final String INCOME_RENTIN_USE_COMPARE = "income.rentin.use.compare"; //收益法+比较法公式
    public static final String INCOME_RENTIN_NOT_USE_COMPARE = "income.rentin.not.use.compare"; //收益法+不使用比较法公式
    public static final String INCOME_SELF_SUPPORT = "income.self.support"; //收益法自营公式
    public static final String COST_BUILDING = "cost.building"; //成本法建筑物公式
    public static final String COST_ENGINEERING = "cost.engineering"; //成本法建筑物公式
    public static final String HYPOTHESIS_DEVELOP_LAND = "hypothesis.develop.land"; //假设开发法土地公式
    public static final String HYPOTHESIS_DEVELOP_ENGINEERING = "hypothesis.develop.engineering"; //假设开发法在建工程公式

    /*
     * 测算方法配置
     */
    public static final String MD_INCOME_HISTORY_TYPE_INCOME = "md.income.history.type.income"; //收入类
    public static final String MD_INCOME_HISTORY_TYPE_INCOME_MAIN = "md.income.history.type.income.main"; //主营业务收入类
    public static final String MD_INCOME_HISTORY_TYPE_INCOME_OTHER = "md.income.history.type.income.other"; //其他业务收入类
    public static final String MD_INCOME_HISTORY_TYPE_COST = "md.income.history.type.cost"; //成本类
    public static final String MD_INCOME_HISTORY_TYPE_MAIN_MANAGER_COST = "md.income.history.type.main.manager.cost"; //主经营成本
    public static final String MD_INCOME_HISTORY_TYPE_OTHER_MANAGER_COST = "md.income.history.type.other.manager.cost"; //其他经营成本
    public static final String MD_INCOME_HISTORY_TYPE_ENGAGE_COST = "md.income.history.type.engage.cost"; //经营费用
    public static final String MD_INCOME_HISTORY_TYPE_OPERATING_COST = "md.income.history.type.operating.cost"; //增值税及其附加
    public static final String MD_INCOME_HISTORY_TYPE_MANAGER_COST = "md.income.history.type.manager.cost"; //管理费用
    public static final String MD_INCOME_HISTORY_TYPE_FINANCE_COST = "md.income.history.type.finance.cost"; //财务费用
    public static final String MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_EDITABLE = "md.income.rental_growth_rate_explain.editable"; //租金增长率描述（可调整）
    public static final String MD_INCOME_RENTAL_GROWTH_RATE_EXPLAIN_READONLY = "md.income.rental_growth_rate_explain.readonly"; //租金增长率描述（固定
    public static final String MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_EDITABLE = "md.income.transaction_taxe_fee_ratio.editable"; //其它相关费用说明（可调整）
    public static final String MD_INCOME_TRANSACTION_TAXE_FEE_RATIO_READONLY = "md.income.transaction_taxe_fee_ratio.readonly"; //其它相关费用说明（固定）

    /**
     * 税率配置
     */
    public static final String DATA_TAX_RATE_ALLOCATION_DEED_TAX = "data.tax.rate.allocation.deed.tax"; //契税
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
    public static final String DATA_TAX_RATE_ALLOCATION_CORPORATE_INCOME_TAX = "data.tax.rate.allocation.corporate.income.tax"; //所得税
    public static final String DATA_TAX_RATE_ALLOCATION_DISPOSAL_FEE = "data.tax.rate.allocation.disposal.fee"; //	预计处置费用

    /**
     * 工作方案
     */
    public static final String PROGRAMME_VALUE_CONNOTATION = "programme.value.connotation"; //价值内涵
    public static final String WORK_PROGRAMME_SET_USE = "work.programme.set.use"; //设定用途
    public static final String programmeMarketCostapproachGrade = "programme.market.costApproach.grade"; //土地级别 等级
    public static final String programmeMarketCostapproachFactor = "programme.market.costApproach.factor"; //土地级别 类型
    public static final String DATA_LAND_LEVEL_ROMAN = "data.land.level.roman"; //土地级别(罗马数字)
    public static final String DATA_LAND_LEVEL_CLASSIFY = "data.land.level.classify"; //土地级别 大类


    public static final String AD_PLACE_FILE_MARK = "ad.place.file.mark"; //档案分类【房产】
    public static final String AD_PLACE_FILE_MARK_COMPREHENSIVE = "ad.place.file.mark.comprehensive"; //档案分类【房产】-综合类
    public static final String AD_PLACE_FILE_MARK_PROFESSION = "ad.place.file.mark.profession"; //档案分类【房产】-专业类

    public static final String AD_PLACE_FILE_MARK_LAND = "ad.place.file.mark.land"; //档案分类【土地】
    public static final String AD_PLACE_FILE_MARK_COMPREHENSIVE_LAND = "ad.place.file.mark.comprehensive.land"; //档案分类【房产】-综合类
    public static final String AD_PLACE_FILE_MARK_PROFESSION_LAND = "ad.place.file.mark.profession.land"; //档案分类【房产】-专业类
}
