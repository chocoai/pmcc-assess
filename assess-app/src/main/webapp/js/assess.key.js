/**
 * 数据库表及字段key
 */
var AssessDBKey = {}

//图片识别 key 根据此地址配置 com.copower.pmcc.erp.api.enums.AliOcrMethodEnum
AssessDBKey.HouseOcrkey = "houseCert";//房产证

AssessDBKey.DataImgTwoDimensional = "tb_data_img_two_dimensional";
AssessDBKey.DataLandLevel = "tb_data_land_level";

AssessDBKey.BaseReportField = "tb_base_report_field";
AssessDBKey.ReportTemplate = "tb_report_template";
AssessDBKey.ProjectPlanDetails = "tb_project_plan_details";
AssessDBKey.ProjectInfo = "tb_project_info";
AssessDBKey.InitiatePossessor = "tb_initiate_possessor";
AssessDBKey.InitiateConsignor = "tb_initiate_consignor";
AssessDBKey.BoxApprovalLog = "tb_box_approval_log";
AssessDBKey.SurveyLocaleExploreDetail = "tb_survey_locale_explore_detail";

AssessDBKey.CaseEstate = "tb_case_estate";
AssessDBKey.CaseEstateLandState = "tb_case_estate_land_state";
AssessDBKey.CaseBuilding = "tb_case_building";
AssessDBKey.CaseHouse = "tb_case_house";
AssessDBKey.CaseHouseTrading = "tb_case_house_trading";
AssessDBKey.CaseEstateParking = "tb_case_estate_parking";
AssessDBKey.CaseHouseCorollaryEquipment = "tb_case_house_corollary_equipment";
AssessDBKey.CaseUnitHuxing = "tb_case_unit_huxing";

AssessDBKey.BasicEstate = "tb_basic_estate";
AssessDBKey.BasicBuilding = "tb_basic_building";
AssessDBKey.BasicHouse = "tb_basic_house";
AssessDBKey.BasicUnit = "tb_basic_unit";
AssessDBKey.BasicHouseTrading = "tb_basic_house_trading";
AssessDBKey.BasicUnitHuxing = "tb_basic_unit_huxing";
AssessDBKey.BasicEstateParking = "tb_basic_estate_parking";
AssessDBKey.BasicHouseCorollaryEquipment = "tb_basic_house_corollary_equipment";

AssessDBKey.SurveyAssetInventory = "tb_survey_asset_inventory";
AssessDBKey.SurveyAssetInventoryContent = "tb_survey_asset_inventory_content";
AssessDBKey.SurveyAssetInventoryRight = "tb_survey_asset_inventory_right";

AssessDBKey.SchemeJudgeObject = "tb_scheme_judge_object";
AssessDBKey.SchemeReimbursement = "tb_scheme_reimbursement";
AssessDBKey.SchemeReportFileCustom = "tb_scheme_report_file_custom";

AssessDBKey.BaseFileTemplate = "tb_base_file_template";
AssessDBKey.GenerateReportGeneration = "tb_generate_report_generation";


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
AssessProjectClassifyKey.singleHouseBuildingCertificateType = "single.house.building.certificate.type";//在建工程

/**
 * 数据字典key
 */
var AssessDicKey = {}
//项目申报
AssessDicKey.projectDeclareHouseCertificateType = "project.declare.house.certificate.type";//房产证类型
AssessDicKey.projectDeclareCommonSituation = "project.declare.common.situation";//共有情况
AssessDicKey.projectDeclareLandCertificateType = "project.declare.land.certificate.type";//土地证类型
AssessDicKey.projectDeclareUseRightType = "project.declare.use.right.type";//使用权类型

//基础数据
AssessDicKey.dataTaxRateAllocation = "data.tax.rate.allocation";//税率配置
AssessDicKey.dataEntrustmentPurpose = "data.entrustment.purpose";//委托目的
AssessDicKey.value_type = "value.type";//价值类型
AssessDicKey.project_initiate_urgency = "project.initiate.urgency";//紧急程度

//楼盘
AssessDicKey.estate_position = "estate.position";//楼盘方位
AssessDicKey.estate_total_land_level = "estate.land_level";//土地级别
AssessDicKey.estate_total_land_use = "estate.land_use";//土地用途
AssessDicKey.estatePlaneness = "estate.planeness";//地形
AssessDicKey.estateDevelopment_degree = "estate.development_degree";//土地开发程度
AssessDicKey.estateDevelopment_degreePrepared_land = "estate.development_degree.prepared_land";//熟地
AssessDicKey.estateShape_state = "estate.shape_state";//土地形状
AssessDicKey.estateTopographic_terrain = "estate.topographic_terrain";//地势
AssessDicKey.estateSupplySituation = "estate.supply.situation";//供应情况

AssessDicKey.estate_network_supplier = "estate.network.supplier";//	通信网络-供应商名称
AssessDicKey.estate_network_service_content = "estate.network.service.content";//通信网络-服务内容
AssessDicKey.estate_distance = "estate.distance";//交通距离
AssessDicKey.estate_car_location = "estate.car.location";//车位位置
AssessDicKey.estate_car_type = "estate.car.type";//车位类型
AssessDicKey.estate_traffic_nature = "estate.traffic.nature";//交通枢纽性质
AssessDicKey.estate_line_water_supply_pipe_grade = "estate.line.water.supply.pipe.grade";//供应保障等级
AssessDicKey.estate_supplier_reputation = "estate.supplier.reputation";//供应商信誉
AssessDicKey.estate_supplier_grade = "estate.supplier.grade";//供应商等级
AssessDicKey.estate_school_nature = "estate.school.nature";//学校性质
AssessDicKey.estate_school_gradation = "estate.school.gradation";//学校级次
AssessDicKey.estate_school_level = "estate.school.level";//学校等级
AssessDicKey.estate_environment_category = "estate.environment.category";//影响因素
AssessDicKey.estate_environment_type = "estate.environment.type";//环境类型
AssessDicKey.estate_environment_influence_degree = "estate.environment.Influence.degree";//影响程度
AssessDicKey.estate_finance_service_content = "estate.finance.service.content";//服务类别
AssessDicKey.estate_finance_nature = "estate.finance.nature";//金融机构性质
AssessDicKey.estate_finance_category = "estate.finance.category";//金融类别
AssessDicKey.estate_entertainment_category = "estate.entertainment.category";//休闲娱乐场所类别
AssessDicKey.estate_shop_or_entertainment_or_dining_distance = "estate.shop_or_entertainment_or_dining.distance";//购物场所,餐饮场所,休闲娱乐距离
AssessDicKey.estate_dining_category = "estate.dining.category";//餐饮场所类别
AssessDicKey.estate_dining_grade = "estate.dining.grade";//餐饮场所档次
AssessDicKey.estate_shop_category = "estate.shop.category";//购物场所类别
AssessDicKey.estate_shop_grade = "estate.shop.grade";//购物商场档次
AssessDicKey.estate_supply_new_distance = "estate.supply.new.distance";//购物场所距离
AssessDicKey.estate_supply_new_scale = "estate.supply.new.scale";//购物场所规模
AssessDicKey.estate_supply_new_type = "estate.supply.new.type";//购物场所类别
AssessDicKey.estate_examinematchingmedical_level = "estate.examineMatchingMedical.level";//医养机构等级
AssessDicKey.estate_examinematchingmedical_bedNumber = "estate.examineMatchingMedical.bedNumber";//医养机构床位数
AssessDicKey.estate_examinematchingmedical_distance = "estate.examineMatchingMedical.distance";//医养机构距离

//楼栋
AssessDicKey.examine_building_residence_data = "examine.building.residence.data";//非工业仓储(建筑使用寿命)
AssessDicKey.examine_building_property_type = "building.property_type";//物业类型
AssessDicKey.examine_building_property_structure = "building.building_structure";//建筑结构
AssessDicKey.examine_building_property_category = "building.building_category";//建筑类别
AssessDicKey.examine_building_decorating_material = "building.decorating_material";//装修材料
AssessDicKey.examine_building_material_price = "building.material_price";//材料价格区间
AssessDicKey.examine_building_construction_technology = "building.construction_technology";//施工工艺
AssessDicKey.examine_building_decoration_part = "building.decoration_part";//装修部位
AssessDicKey.examine_building_structure = "examine.building.structure";//屋面结构
AssessDicKey.examine_building_maintenance_type = "examine.building.maintenance.type";//围护结构类型
AssessDicKey.examine_building_maintenance_category = "examine.building.maintenance.category";//围护结构分类
AssessDicKey.examine_building_materialquality = "examine.building.maintenance.materialQuality";//围护结构材质
AssessDicKey.examine_building_function_type = "building.function.type";//建筑功能类型

//单元
AssessDicKey.examineUnitInteriorDecorationPart = "unit.interior.decoration.part";//内装装修部位
AssessDicKey.examineUnitInteriorDecorationMaterial = "unit.interior.decoration.material";//内装装修材料
AssessDicKey.examineUnitElevatorMaintenance = "unit.elevator.maintenance";//电梯维护情况
AssessDicKey.examineUnitElevatorType = "unit.elevator.type";//电梯类型
AssessDicKey.examineUnitHuxingType = "unit.huxing.type";//户型类别
AssessDicKey.examineUnitHuxingTypeProduction = "unit.huxing.type.production";//户型类别-生产用房
AssessDicKey.examineUnitHuxingTypeStay = "unit.huxing.type.stay";//户型类别-住宿(招待所)用房
AssessDicKey.examineUnitHuxingTypeOffice = "unit.huxing.type.office";//户型类别-行政办公用房

//房屋
AssessDicKey.examineHouseFinancingConditions = "examine.house.financing.conditions";//融资条件
AssessDicKey.examineHouseScopeProperty = "examine.house.scope.property";//财产范围
AssessDicKey.examineHouseLoadUtility = "examine.house.load.utility";//证载用途
AssessDicKey.examineHousePracticalUse = "examine.house.practical.use";//实际用途
AssessDicKey.examineHouseEnvironmentUse = "examine.house.environment.use";//使用环境
AssessDicKey.examineHouseHouse_layout = "unit.house_layout";//房型
AssessDicKey.examineCommonOrientation = "examine.common.orientation";//朝向
AssessDicKey.examineHouseTransactionType = "examine.house.transaction.type";//交易类型
AssessDicKey.examineHouseTransactionTypeSell = "examine.house.transaction.type.sell";//交易类型-出售
AssessDicKey.examineHouseTransactionTypeLease = "examine.house.transaction.type.lease";//交易类型-出租
AssessDicKey.examineHouseDescriptionType = "examine.house.description_type";//说明事项类型
AssessDicKey.examineHousetaxBurden = "examine.house.tax.burden";//税费负担
AssessDicKey.examineHouseTransactionSituation = "examine.house.transaction.situation";//交易情况
AssessDicKey.examineHouseTransactionAbnormal = "examine.house.transaction.situation.abnormal";//交易情况-非正常
AssessDicKey.examineHousePaymentMethod = "examine.house.payment.method";//付款方式
AssessDicKey.examineHousePaymentMethodInstallment = "examine.house.payment.method.installment";//付款方式-分期付款
AssessDicKey.examineHousePaymentMethodLease = "examine.house.payment.method.lease";//出租付款方式
AssessDicKey.examineHouseInformationSourceType = "examine.house.information.source.type";//信息来源类型
AssessDicKey.examineHouseInformationSourceTypeOpen = "examine.house.information.source.type.open";//信息来源类型-公开信息
AssessDicKey.examineHouseInformationSourceCategory = "examine.house.information.source.category";//信息来源类别

AssessDicKey.examine_house_room_part = "examine.house.room.part";//房间装修部位
AssessDicKey.examine_house_room_material = "examine.house.room.material";//房间装修材料
AssessDicKey.examineHouseIntelligent_wireMaterial = "examine.house.wire_material";//电线材质
AssessDicKey.examine_house_supply_erection_method = "examine.house.water.supply_erection_method";//供水管架设方式
AssessDicKey.examine_house_pretreated_water = "examine.house.water.pretreated_water";//前置净水
AssessDicKey.examine_house_fire_water_supply = "examine.house.water.fire_water_supply";//供水_消防给水
AssessDicKey.examine_house_piping_layout = "examine.house.water.piping_layout";//供水_给水管道布置
AssessDicKey.examine_house_pipe_material = "examine.house.water.pipe_material";//	供水_给水管材料
AssessDicKey.examine_house_supply_mode = "examine.house.water.supply_mode";//供水_给水方式
AssessDicKey.examine_house_purification_equipment_price = "examine.house.water.purification_equipment_price";//前置净水设备价格区间
AssessDicKey.examine_house_booster_equipment = "examine.house.water.booster_equipment";//供水_给水升压设备
AssessDicKey.examine_house_water_drainage_circuit = "examine.house.water.drainage_circuit";//排水回路
AssessDicKey.examine_house_water_drain_type = "examine.house.water.drain.type";//排水_类别
AssessDicKey.examine_house_water_drain_system = "examine.house.water.drain.system";//排水_系统
AssessDicKey.examine_house_water_drain_processing_mode = "examine.house.water.drain.processing_mode";//排水_处理方式
AssessDicKey.examine_house_intelligent_system = "examine.house.intelligent_system";//智能系统
AssessDicKey.examine_house_lamps_lanterns = "examine.house.lamps_lanterns";//灯具
AssessDicKey.examine_house_switch_circuit = "examine.house.switch_circuit";//开关回路
AssessDicKey.examineHouseLayingMethod = "examine.house.laying.method";//电线架设方式
AssessDicKey.examine_house_street_level = "examine.house.street_level";//街道级别
AssessDicKey.examine_house_traffic_flow = "examine.house.traffic_flow";//交通流量
AssessDicKey.examine_house_visitors_flowrate = "examine.house.visitors_flowrate";//人流量
AssessDicKey.examine_house_wind_brand = "examine.house.wind.brand";//新风品牌
AssessDicKey.examine_house_way_wind = "examine.house.way.wind";//供风系统类型
AssessDicKey.examine_house_wind_equipment_price_range = "examine.house.wind.equipment_price_range";//供风设备价格区间
AssessDicKey.examine_house_air_conditioning_mode = "examine.house.air.conditioning_mode";//空调制式
AssessDicKey.examine_house_air_equipment_price_range = "examine.house.air.equipment_price_range";//空调设备价格区间
AssessDicKey.examine_house_heating_brand = "examine.house.heating.brand";//暖气品牌
AssessDicKey.examine_house_heating_method = "examine.house.heating.method";//供暖方式
AssessDicKey.examine_house_heating_equipment_price_range = "examine.house.heating.equipment_price_range";//供暖设备价格区间
AssessDicKey.examine_house_corollary_equipment_type = "examine.house.corollary.equipment.type";//配套设备设施 类型

//房屋完损度
AssessDicKey.damaged_degree_structural_part = "structural.part";//房屋完损度-结构部分
AssessDicKey.damaged_degree_decoration_part = "decoration.part";//房屋完损度-装修部分
AssessDicKey.damaged_degree_equipment_part = "equipment.part";//房屋完损度-设备部分
AssessDicKey.damaged_degree_other = "other";//房屋完损度-其它

//其它
AssessDicKey.build_addedvalueadditionaltaxrate = "build.addedValueAdditionalTaxRate";//增值及附加税率
AssessDicKey.build_landAcquisitionTaxRate = "build.landAcquisitionTaxRate";//土地取得税率
AssessDicKey.mdIncomeHistoryTypeIncome = "md.income.history.type.income";//收入类(历史数据)
AssessDicKey.mdIncomeHistoryTypeCost = "md.income.history.type.cost";//成本类(历史数据)
AssessDicKey.workProgrammeSetUse = "work.programme.set.use";//设定用途
AssessDicKey.mdHypothesisDevelopment = "md.hypothesis.development";//建设、住宅、商业、办公、车库、地下商业、建设周期


/**
 * 文件模板key
 */
var AssessFTKey = {}
AssessFTKey.ftAssetInventoryRight = "ft.asset.inventory.right";//他项权利
AssessFTKey.ftHouseOwnershipCertificate = "ft.house.ownership.certificate";//房产证数据导入模型
AssessFTKey.ftLandOwnershipCertificate = "ft.land.ownership.certificate";//土地证数据导入模型
AssessFTKey.ftRealEstateOwnershipCertificate = "ft.real.estate.ownership.certificate";//不动产模板
AssessFTKey.ftMethodIncomeHistory = "ft.method.income.history";//收益法历史数据模板
AssessFTKey.ftMethodIncomeHistoryRestaurant = "ft.method.income.history.restaurant";//收益法餐饮、酒店、宾馆历史数据模板
AssessFTKey.ftDeclareBuildEngineering = "ft.declare.build.engineering";//在建工程土建模板
AssessFTKey.ftDeclareBuildEquipmentInstall = "ft.declare.build.equipmentInstall";//在建工程设备安装模板
AssessFTKey.ftOperationManualCaseBase = "ft.operation.manual.case.base";//案例库操作手册