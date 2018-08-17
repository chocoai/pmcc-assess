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
AssessDicKey.examineBlockRegionalNature = "examine.block.regionalNature";
AssessDicKey.estateTotalBuildingType = "estate.total_building_type";//总栋数 (0一栋 1多栋)

AssessDicKey.examineHouseLoadUtility = "examine.house.load.utility";//证载用途
AssessDicKey.examineHousePracticalUse = "examine.house.practical.use";//实际用途
AssessDicKey.examineHouseEnvironmentUse = "examine.house.environment.use";//实际用途

AssessDicKey.examineHouseTransactionType = "examine.house.transaction.type";//交易类型
AssessDicKey.examineHouseDescriptionType = "examine.house.description_type";//说明事项类型

AssessDicKey.build_addedvalueadditionaltaxrate = "build.addedValueAdditionalTaxRate" ;//增值及附加税率
AssessDicKey.build_landAcquisitionTaxRate = "build.landAcquisitionTaxRate" ;//土地取得税率

AssessDicKey.mdIncomeSelfSupportCostTypeIncome = "md.income.self.support.cost.type.income" ;//收入类
AssessDicKey.mdIncomeSelfSupportCostTypeCost = "md.income.self.support.cost.type.cost" ;//成本类
AssessDicKey.mdIncomeSelfSupportCostTypeExpense = "md.income.self.support.cost.type.expense" ;//费用类

AssessDicKey.mdHypothesisDevelopment = "md.hypothesis.development" ;//建设、住宅、商业、办公、车库、地下商业、建设周期