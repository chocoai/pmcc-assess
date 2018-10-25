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

AssessDBKey.CaseEstate = "tb_case_estate";
AssessDBKey.CaseEstateLandState = "tb_case_estate_land_state";
AssessDBKey.CaseBuilding = "tb_case_building";
AssessDBKey.CaseHouse = "tb_case_house";
AssessDBKey.CaseHouseTrading = "tb_case_house_trading";
AssessDBKey.CaseEstateParking = "tb_case_estate_parking";
AssessDBKey.CaseHouseCorollaryEquipment = "tb_case_house_corollary_equipment";
AssessDBKey.CaseUnitHuxing = "tb_case_unit_huxing";

AssessDBKey.BasicEstate = "tb_basic_estate";

AssessDBKey.SurveyAssetInventory = "tb_survey_asset_inventory";
AssessDBKey.SurveyAssetInventoryContent = "tb_survey_asset_inventory_content";
AssessDBKey.SurveyAssetInventoryRight = "tb_survey_asset_inventory_right";

AssessDBKey.BaseFileTemplate = "tb_base_file_template";


AssessDBKey.DeclareRealtyHouseCert = "tb_declare_realty_house_cert";
AssessDBKey.DeclareRealtyLandCert = "tb_declare_realty_land_cert";
AssessDBKey.DeclareRealtyRealEstateCert = "tb_declare_realty_real_estate_cert";
AssessDBKey.DeclareBuildEngineering = "tb_declare_build_engineering";
AssessDBKey.DeclareBuildEquipmentInstall = "tb_declare_build_equipment_install";
AssessDBKey.DeclareBuildingConstructionPermit = "tb_declare_building_construction_permit";
AssessDBKey.DeclarePreSalePermit = "tb_declare_pre_sale_permit";
AssessDBKey.DeclareLandUsePermit = "tb_declare_land_use_permit";
AssessDBKey.DeclareBuildingPermit = "tb_declare_building_permit";

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

AssessProjectClassifyKey.singleHousePropertyCertificateType = "single.house.property.certificate.type";//房产证书类型
AssessProjectClassifyKey.singleDeclareBuildingCertificateType = "single.declare.building.certificate.type";//在建工程
AssessProjectClassifyKey.singleHousePropertyCertificateTypeCategory = "single.house.property.certificate.type.house.category";//房产证 类别
AssessProjectClassifyKey.singleLandPropertyCertificateTypeCategory = "single.land.property.certificate.type.land.category";//土地证 类别

/**
 * 数据字典key
 */
var AssessDicKey = {}
//基础数据
AssessDicKey.dataTaxRateAllocation = "data.tax.rate.allocation";//税率配置
AssessDicKey.dataEntrustmentPurpose = "data.entrustment.purpose";//委托目的

AssessDicKey.examineBlockRegionalNature = "examine.block.regionalNature";
AssessDicKey.estateTotalBuildingType = "estate.total_building_type";//总栋数 (0一栋 1多栋)
AssessDicKey.examine_building_property_type = "building.property_type";//物业类型
AssessDicKey.examine_building_property_structure = "building.building_structure";//建筑结构
AssessDicKey.examine_building_property_category = "building.building_category";//建筑类别
AssessDicKey.examine_building_decorating_material = "building.decorating_material";//装修材料
AssessDicKey.examine_building_material_price = "building.material_price";//材料价格区间
AssessDicKey.examine_building_construction_technology = "building.construction_technology";//施工工艺
AssessDicKey.examine_building_decoration_part = "building.decoration_part";//装修部位
AssessDicKey.examine_building_function_type = "building.function.type";//建筑功能类型

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

AssessDicKey.estate_total_land_level = "estate.land_level";//土地级别
AssessDicKey.estate_total_land_use = "estate.land_use";//土地用途

AssessDicKey.build_addedvalueadditionaltaxrate = "build.addedValueAdditionalTaxRate";//增值及附加税率
AssessDicKey.build_landAcquisitionTaxRate = "build.landAcquisitionTaxRate";//土地取得税率

AssessDicKey.mdIncomeHistoryTypeIncome = "md.income.history.type.income";//收入类(历史数据)
AssessDicKey.mdIncomeHistoryTypeCost = "md.income.history.type.cost";//成本类(历史数据)

AssessDicKey.workProgrammeSetUse = "work.programme.set.use";//设定用途

AssessDicKey.mdHypothesisDevelopment = "md.hypothesis.development";//建设、住宅、商业、办公、车库、地下商业、建设周期

//案例数据维护
AssessDicKey.casesHouseTransactionType = "cases.common.transaction.type";//交易类型
AssessDicKey.casesEstateOther = "cases.estate.other";//案例楼盘 其它信息
AssessDicKey.casesEstateMatching = "cases.estate.matching";//案例楼盘 配套信息
AssessDicKey.casesHouseMatching = "cases.house.matching";//案例房屋 配套信息


/**
 * 文件模板key
 */
var AssessFTKey = {}
AssessFTKey.ftAssetInventoryRight = "ft.asset.inventory.right";//他项权利
AssessFTKey.ftHouseOwnershipCertificate = "ft.house.ownership.certificate";//房产证数据导入模型
AssessFTKey.ftLandOwnershipCertificate = "ft.land.ownership.certificate";//土地证数据导入模型
AssessFTKey.ftRealEstateOwnershipCertificate = "ft.real.estate.ownership.certificate";//不动产模板
AssessFTKey.ftMethodIncomeHistory = "ft.method.income.history";//收益法历史数据模板
AssessFTKey.ftDeclareBuildEngineering = "ft.declare.build.engineering";//在建工程土建模板
AssessFTKey.ftDeclareBuildEquipmentInstall = "ft.declare.build.equipmentInstall";//在建工程设备安装模板