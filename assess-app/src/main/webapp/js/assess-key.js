/**
 * 数据库表及字段key
 */
var AssessDBKey = {}
AssessDBKey.ReportTemplate = "tb_report_template";
AssessDBKey.ProjectPlanDetails = "tb_project_plan_details";
AssessDBKey.ProjectInfo = "tb_project_info";
AssessDBKey.InitiatePossessor = "tb_initiate_possessor";
AssessDBKey.InitiateConsignor = "tb_initiate_consignor";
AssessDBKey.BoxApprovalLog = "tb_box_approval_log";
AssessDBKey.SurveyLocaleExploreDetail = "tb_survey_locale_explore_detail";
AssessDBKey.ExamineEstate = "tb_examine_estate";
AssessDBKey.ExamineHouse = "tb_examine_house";
AssessDBKey.ExamineBuilding = "tb_examine_building";
AssessDBKey.ExamineUnitHuxing = "tb_examine_unit_huxing";
AssessDBKey.ExamineEstateParking = "tb_examine_estate_parking";
AssessDBKey.ExamineHouseCorollaryEquipment = "tb_examine_house_corollary_equipment";

AssessDBKey.CaseEstate = "tb_case_estate" ;
AssessDBKey.CaseEstateLandState = "tb_case_estate_land_state" ;
AssessDBKey.CaseBuilding = "tb_case_building" ;
AssessDBKey.CaseHouse = "tb_case_house" ;
AssessDBKey.CaseHouseTrading = "tb_case_house_trading" ;


/**
 * 项目分类key
 */
var AssessProjectClassifyKey = {}
AssessProjectClassifyKey.declare = "declare";//申报表
AssessProjectClassifyKey.explore = "explore";//查勘表
AssessProjectClassifyKey.case = "case";//案例表
AssessProjectClassifyKey.single = "single";//单项资产
AssessProjectClassifyKey.singleCsr = "single.csr";//不良债权评估
AssessProjectClassifyKey.comprehensive = "comprehensive";//综合资产


/**
 * 数据字典key
 */
var AssessDicKey = {}
//基础数据
AssessDicKey.dataTaxRateAllocation = "data.tax.rate.allocation";//	委托目的
AssessDicKey.dataEntrustmentPurpose = "data.entrustment.purpose";//税率配置

AssessDicKey.examineBlockRegionalNature = "examine.block.regionalNature";
AssessDicKey.estateTotalBuildingType = "estate.total_building_type";//总栋数 (0一栋 1多栋)
AssessDicKey.examine_building_property_type = "building.property_type";//物业类型
AssessDicKey.examine_building_property_structure = "building.building_structure";//建筑结构
AssessDicKey.examine_building_property_category = "building.building_category";//建筑类别
AssessDicKey.examine_building_decorating_material = "building.decorating_material";//装修材料
AssessDicKey.examine_building_material_price = "building.material_price";//材料价格区间
AssessDicKey.examine_building_construction_technology = "building.construction_technology";//施工工艺
AssessDicKey.examine_building_decoration_part = "building.decoration_part";//装修部位

AssessDicKey.examineHouseLoadUtility = "examine.house.load.utility";//证载用途
AssessDicKey.examineHousePracticalUse = "examine.house.practical.use";//实际用途
AssessDicKey.examineHouseEnvironmentUse = "examine.house.environment.use";//实际用途
AssessDicKey.examineHouseNewsHuxing = "examine.house.newsHuxing";//最新户型

AssessDicKey.examineHouseTransactionType = "examine.house.transaction.type";//交易类型
AssessDicKey.examineHouseDescriptionType = "examine.house.description_type";//说明事项类型
AssessDicKey.examineHousetaxBurden = "examine.house.tax.burden";//税费负担
AssessDicKey.examineHouseNormalTransaction = "examine.house.normal.transaction";//正常交易
AssessDicKey.examineHousePaymentMethod = "examine.house.payment.method";//付款方式
AssessDicKey.examineHouseClassificationInformationSources = "examine.house.classification.information.sources";//信息来源分类

AssessDicKey.estate_total_land_level = "estate.land_level" ;//土地级别
AssessDicKey.estate_total_land_use = "estate.land_use" ;//土地用途

AssessDicKey.build_addedvalueadditionaltaxrate = "build.addedValueAdditionalTaxRate" ;//增值及附加税率
AssessDicKey.build_landAcquisitionTaxRate = "build.landAcquisitionTaxRate" ;//土地取得税率

AssessDicKey.mdIncomeSelfSupportCostTypeIncome = "md.income.self.support.cost.type.income" ;//收入类
AssessDicKey.mdIncomeSelfSupportCostTypeCost = "md.income.self.support.cost.type.cost" ;//成本类
AssessDicKey.mdIncomeSelfSupportCostTypeExpense = "md.income.self.support.cost.type.expense" ;//费用类

AssessDicKey.workProgrammeSetUse = "work.programme.set.use" ;//设定用途

AssessDicKey.mdHypothesisDevelopment = "md.hypothesis.development" ;//建设、住宅、商业、办公、车库、地下商业、建设周期

//案例数据维护
AssessDicKey.casesHouseTransactionType = "cases.common.transaction.type";//交易类型
AssessDicKey.casesEstateOther = "cases.estate.other";//案例楼盘 其它信息
AssessDicKey.casesEstateMatching = "cases.estate.matching";//案例楼盘 配套信息