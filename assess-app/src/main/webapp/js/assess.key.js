/**
 * 数据库表及字段key
 */
var AssessDBKey = {}

//图片识别 key 根据此地址配置 com.copower.pmcc.erp.api.enums.AliOcrMethodEnum
AssessDBKey.HouseOcrkey = "houseCert";//房产证

AssessDBKey.ToolMapHandle = "tb_tool_map_handle";
AssessDBKey.DataLandLevel = "tb_data_land_level";
AssessDBKey.DataContractCalculateTool = "tb_data_contract_calculate_tool";

AssessDBKey.AssetsCustomizeDataField = "tb_assets_customize_data_field";
AssessDBKey.BaseReportField = "tb_base_report_field";
AssessDBKey.ReportTemplate = "tb_report_template";
AssessDBKey.ProjectPlanDetails = "tb_project_plan_details";
AssessDBKey.ProjectInfo = "tb_project_info";
AssessDBKey.ProjectTaskReturnRecord = "tb_project_task_return_record";
AssessDBKey.ProjectPhase = "tb_project_phase";
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
AssessDBKey.BasicHouseRoom = "tb_basic_house_room";
AssessDBKey.BasicEstateStreetInfo = "tb_basic_estate_street_info";
AssessDBKey.BasicEstateLandCategoryInfo = "tb_basic_estate_land_category_info";
AssessDBKey.BasicUnitCommonPart = "tb_basic_unit_common_part";
AssessDBKey.BasicUnitStairs = "tb_basic_unit_stairs";
AssessDBKey.BasicUnitElevator = "tb_basic_unit_elevator";
AssessDBKey.BasicUnitDecorate = "tb_basic_unit_decorate";

AssessDBKey.SurveyExaminePurenessLand = "tb_survey_examine_pureness_land";
AssessDBKey.SurveyAssetInventory = "tb_survey_asset_inventory";
AssessDBKey.SurveyAssetInventoryContent = "tb_survey_asset_inventory_content";
AssessDBKey.SurveyAssetInventoryRight = "tb_survey_asset_inventory_right";
AssessDBKey.SurveyAssetInventoryRightRecord = "tb_survey_asset_inventory_right_record";

AssessDBKey.SurveyAssetRightItem = "tb_survey_asset_right_item";
AssessDBKey.SurveyAssetRightGroup = "tb_survey_asset_right_group";

AssessDBKey.SchemeJudgeObject = "tb_scheme_judge_object";
AssessDBKey.SchemeReimbursement = "tb_scheme_reimbursement";
AssessDBKey.SchemeReportFileCustom = "tb_scheme_report_file_custom";

AssessDBKey.BaseFileTemplate = "tb_base_file_template";
AssessDBKey.GenerateReportInfo = "tb_generate_report_info";
AssessDBKey.GenerateReportGroup = "tb_generate_report_group";


AssessDBKey.DeclareRecord = "tb_declare_record";
AssessDBKey.DeclareApplyExtension = "tb_declare_apply_extension";
AssessDBKey.DeclareRealtyHouseCert = "tb_declare_realty_house_cert";
AssessDBKey.DeclareRealtyLandCert = "tb_declare_realty_land_cert";
AssessDBKey.DeclareRealtyRealEstateCert = "tb_declare_realty_real_estate_cert";
AssessDBKey.DeclareBuildEngineering = "tb_declare_build_engineering";
AssessDBKey.DeclareBuildEquipmentInstall = "tb_declare_build_equipment_install";
AssessDBKey.DeclareBuildingConstructionPermit = "tb_declare_building_construction_permit";
AssessDBKey.DeclarePreSalePermit = "tb_declare_pre_sale_permit";
AssessDBKey.DeclareLandUsePermit = "tb_declare_land_use_permit";
AssessDBKey.DeclareBuildingPermit = "tb_declare_building_permit";
AssessDBKey.DeclareRealtyCheckList = "tb_declare_realty_check_list";

AssessDBKey.MdCalculatingMethodEngineeringCost = "tb_md_calculating_method_engineering_cost";
AssessDBKey.MdCost = "tb_md_cost";
AssessDBKey.MdDevelopment = "tb_md_development";
AssessDBKey.MdIncome = "tb_md_income";

AssessDBKey.ProjectChangeLog = "tb_project_change_log";
AssessDBKey.ProjectSubsequent = "tb_project_subsequent";
AssessDBKey.ProjectTakeNumber = "tb_project_take_number";
AssessDBKey.ProjectTakeNumberDetail = "tb_project_take_number_detail";
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
AssessProjectClassifyKey.singleHousetaskRightCategory = "single.house.property.taskRight.category";//	他项权类别

AssessProjectClassifyKey.singleHousePropertyCertificateTypeSimple = "single.house.property.certificate.type.simple";//	房产
AssessProjectClassifyKey.singleHouseLandCertificateTypeSimple = "single.house.land.certificate.type.simple";//	土地

/**
 * 数据字典key
 */
var AssessDicKey = {};
//项目申报
AssessDicKey.DECLARE_ASSETS_CUSTOMIZE_TYPE = "declare.assets.customize.type";//资产评估 自定义类型
AssessDicKey.REPORT_TYPE = "report.type";//报告类型
AssessDicKey.projectDeclareHouseCertificateType = "project.declare.house.certificate.type";//房产证类型
AssessDicKey.projectDeclareCommonSituation = "project.declare.common.situation";//共有情况
AssessDicKey.projectDeclareLandCertificateType = "project.declare.land.certificate.type";//土地证类型
AssessDicKey.projectDeclareUseRightType = "project.declare.use.right.type";//权利性质
AssessDicKey.projectDeclareRoomType = "project.declare.room.type";//房屋性质

//基础数据
AssessDicKey.DATA_LAND_LEVEL_CLASSIFY = "data.land.level.classify";//土地级别类型
AssessDicKey.DATA_LAND_LEVEL_ROMAN = "data.land.level.roman";//土地级别(罗马数字)
AssessDicKey.DATA_SERVICE_CONTENT = "data.service.content";//物业服务
AssessDicKey.dataTaxRateAllocation = "data.tax.rate.allocation";//税率配置
AssessDicKey.dataEntrustmentPurpose = "data.entrustment.purpose";//委托目的
AssessDicKey.value_type = "value.type";//价值类型
AssessDicKey.serviceComeFrom = "service.come.from";//价值类型
AssessDicKey.dataEntrustmentPurposeMortgage = "data.entrustment.purpose.mortgage";//委托目的-抵押评估
AssessDicKey.property_scope = "examine.house.scope.property";//评估范围
AssessDicKey.project_initiate_urgency = "project.initiate.urgency";//紧急程度
AssessDicKey.data_company_reputation = "data.company.reputation";//公司信誉
AssessDicKey.dataLoanType = "data.loan.type";//贷款类型
AssessDicKey.certifyPart = "certify.part";//相关证明对应查勘部位
AssessDicKey.houseInventoryRightCategory = "project.survey.house.inventory.right.category";//房产他权类别
AssessDicKey.build_security_engineering_project_market_cost = "build.security.engineering.project.market.cost";//建安工程项目
AssessDicKey.DATA_LOCALE_SURVEY_PICTURE_TEMPLATE = "data.locale.survey.picture.template";//现场查勘图片模板
AssessDicKey.projectSurveyFormClassifySingel = "project.survey.form.classify.singel";//单套房产
AssessDicKey.projectSurveyFormClassifyMultiple = "project.survey.form.classify.multiple";//多套房产
AssessDicKey.projectSurveyFormClassifyLandOnly = "project.survey.form.classify.land.only";//纯土地
AssessDicKey.projectSurveyFormClassifyLand = "project.survey.form.classify.land";//土地带房产
AssessDicKey.dataDealType = "data.deal.type";//土地带房产
AssessDicKey.dataAverageWageUrbanEmployeesItemCategory = "data.averageWageUrbanEmployeesItem.category";//城镇就业人员平均工资 类别
AssessDicKey.dataAverageWageUrbanEmployeesItemType = "data.averageWageUrbanEmployeesItem.type";//城镇就业人员平均工资 类型


//楼盘
AssessDicKey.estateIndustrialFile = "industrialFile";//工业附件
AssessDicKey.estateNonIndustrialFile = "nonIndustrialFile";//非工业附件
AssessDicKey.estateLandEstateFile = "landEstateFile";//土地楼盘附件
AssessDicKey.estate_position = "estate.position";//楼盘方位
AssessDicKey.estate_total_land_level = "estate.land_level";//土地级别
AssessDicKey.estateLandContaminated = "estate.land.contaminated";//	污染
AssessDicKey.estateLandPh = "estate.land.ph";//	酸碱度
AssessDicKey.estateLandFertility = "estate.land.fertility";//	肥力
AssessDicKey.estateLandBearingCapacity = "estate.land.bearingCapacity";//	承载力
AssessDicKey.estateLandBearingHoldOn = "estate.land.holdOn";//	稳定性
AssessDicKey.estate_total_land_use = "estate.land_use";//土地用途
AssessDicKey.estate_compatibility_rate = "estate.compatibility.rate";//兼容类型
AssessDicKey.estatePlaneness = "estate.planeness";//地形
AssessDicKey.estateDevelopment_degree = "estate.development_degree";//土地开发程度
AssessDicKey.estateDevelopment_degreePrepared_land = "estate.development_degree.prepared_land";//熟地
AssessDicKey.estateShape_state = "estate.shape_state";//土地形状
AssessDicKey.estateTopographic_terrain = "estate.topographic_terrain";//地势
AssessDicKey.estateSupplySituation = "estate.supply.situation";//供应情况
AssessDicKey.estateLandInfrastructure = "estate.land.infrastructure";//基础设施情况

AssessDicKey.estate_network_supplier = "estate.network.supplier";//	通信网络-供应商名称
AssessDicKey.estate_network_service_content = "estate.network.service.content";//通信网络-服务内容
AssessDicKey.estate_distance = "estate.distance";//交通距离
AssessDicKey.estate_mainRoad_limitSpeial = "estate.mainRoad.limitSpeial";//	特殊限行
AssessDicKey.estate_infrastructureCompleteness = "estate.mainRoad.infrastructureCompleteness";//基础设施完备度
AssessDicKey.estate_car_location = "estate.car.location";//车位位置
AssessDicKey.estate_car_type = "estate.car.type";//车位类型
AssessDicKey.estate_car_parkingEstate = "estate.car.parkingEstate";//楼盘车位
AssessDicKey.estate_traffic_nature = "estate.traffic.nature";//交通枢纽性质
AssessDicKey.estate_line_water_supply_pipe_grade = "estate.line.water.supply.pipe.grade";//供应保障等级

AssessDicKey.estate_supplier_grade = "estate.supplier.grade";//供应商等级
AssessDicKey.estate_school_nature = "estate.school.nature";//学校性质
AssessDicKey.estate_school_gradation = "estate.school.gradation";//学校级次
AssessDicKey.estate_school_level = "estate.school.level";//学校等级
AssessDicKey.estate_environment_category = "estate.environment.category";//影响因素
AssessDicKey.estate_environment_type = "estate.environment.type";//环境类型
AssessDicKey.estate_environment_result = "estate.environment.result";//影响结论
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

AssessDicKey.project_survey_building_status_in_progress = "project.survey.building.status.in.progress";//建筑物状态 在建
AssessDicKey.project_survey_building_status_in_finish = "project.survey.building.status.finish";//建筑物状态 已完

//楼栋
AssessDicKey.examineBasicBuilding = "basicBuilding";//楼栋附件
AssessDicKey.examine_building_residence_data = "examine.building.residence.data";//非工业仓储(建筑使用寿命)
AssessDicKey.examine_building_property_type = "building.property_type";//物业类型
AssessDicKey.examine_building_property_category = "building.property_category";//物业类别
AssessDicKey.examine_building_property_structure = "building.building_structure";//建筑结构
AssessDicKey.examine_building_construction_quality = "examine.building.construction.quality";//工程质量
AssessDicKey.examine_building_appearance_style = "examine.building.appearance.style";//外观风格
AssessDicKey.examine_building_appearance_new_and_old = "examine.building.appearance_new_and_old";//外观新旧
AssessDicKey.examine_building_decorating_material = "building.decorating_material";//装修材料
AssessDicKey.examine_building_material_price = "building.material_price";//材料价格区间
AssessDicKey.examine_building_construction_technology = "building.construction_technology";//施工工艺
AssessDicKey.examine_building_decoration_part = "building.decoration_part";//装修部位
AssessDicKey.examine_building_structure = "examine.building.structure";//屋面结构
AssessDicKey.examine_building_maintenance_type = "examine.building.maintenance.type";//围护结构类型
AssessDicKey.examine_building_maintenance_category = "examine.building.maintenance.category";//围护结构分类
AssessDicKey.examine_building_materialquality = "examine.building.maintenance.materialQuality";//围护结构材质
AssessDicKey.examine_building_function_type = "building.function.type";//建筑功能类型
AssessDicKey.examine_building_between_distance = "examine.building.between.distance";//楼间距
AssessDicKey.examine_building_minimumFloorDistance = "examine.building.minimumFloorDistance";//最小楼间距倍数
AssessDicKey.completed_time_type = "completed.time.type";//建筑功能类型

//单元
AssessDicKey.examineBasicUnit = "basicUnit";//单元附件
AssessDicKey.examineUnitInteriorDecorationPart = "unit.interior.decoration.part";//内装装修部位
AssessDicKey.examineUnitInteriorDecorationMaterial = "unit.interior.decoration.material";//内装装修材料
AssessDicKey.examineUnitElevatorType = "unit.elevator.type";//电梯类型
AssessDicKey.examineUnitHuxingType = "unit.huxing.type";//户型类别
AssessDicKey.examineUnitHuxingTypeProduction = "unit.huxing.type.production";//户型类别-生产用房
AssessDicKey.examineUnitHuxingTypeStay = "unit.huxing.type.stay";//户型类别-住宿(招待所)用房
AssessDicKey.examineUnitHuxingTypeOffice = "unit.huxing.type.office";//户型类别-行政办公用房

AssessDicKey.examineUnitCommonPart = "unit.common.part";//公共部位
AssessDicKey.examineUnitLocation = "unit.location";//所在位置
AssessDicKey.examineUnitMonad = "unit.monad";//单位
AssessDicKey.examineUnitQuantity = "unit.quantity";//数量
AssessDicKey.unit_commonPart_description = "unit.commonPart.description";//公共部位 描述
AssessDicKey.examineUnitStairs_type = "unit.unitStairs.type";//楼梯类型
AssessDicKey.examineUnitStairs_use = "unit.unitStairs.use";//楼梯用途
AssessDicKey.examineUnitCommonPart_stairs = "unit.common.part.stairs";//公共部位 楼梯间

//房屋
AssessDicKey.examineBasicHouse = "basicHouse";//房屋附件
AssessDicKey.examineHouseFilePart = "houseFilePart";//房屋附件部分
AssessDicKey.examineHouseHuxingFilePart = "houseHuxingFilePart";//户型附件部分
AssessDicKey.examineHouseTradingFilePart = "houseTradingFilePart";//交易附件部分
AssessDicKey.examineHouseRoomFilePart = "houseRoomFilePart";//房间附件部分
AssessDicKey.examineHouseSpatialDistribution = "examine.house.spatial.distribution";//空间布局
AssessDicKey.examineHouseUtilitiesMeasure = "examine.house.utilities.measure";//水电费标准
AssessDicKey.examineHouseUtilitiesType = "examine.house.utilities.type";//水电费类型
AssessDicKey.examineHouseTenementType = "examine.house.tenement.type";//空间布局
AssessDicKey.examineHouseFinancingConditions = "examine.house.financing.conditions";//融资条件
AssessDicKey.examineHouseScopeProperty = "examine.house.scope.property";//财产范围
AssessDicKey.examineHouseLoadUtility = "examine.house.load.utility";//证载用途
AssessDicKey.examineHousePracticalUse = "examine.house.practical.use";//实际用途
AssessDicKey.examineHouseEnvironmentUse = "examine.house.environment.use";//使用环境
AssessDicKey.examineHouseHouse_layout = "unit.house_layout";//房型
AssessDicKey.examineHouseTransactionType = "examine.house.transaction.type";//交易类型
AssessDicKey.examineHouseTransactionTypeSell = "examine.house.transaction.type.sell";//交易类型-出售
AssessDicKey.examineHouseTransactionTypeLease = "examine.house.transaction.type.lease";//交易类型-出租
AssessDicKey.examineHouseDescriptionType = "examine.house.description_type";//说明事项类型
AssessDicKey.examineHousetaxBurden = "examine.house.tax.burden";//税费负担
AssessDicKey.examineHouseTransactionSituation = "examine.house.transaction.situation";//交易情况
AssessDicKey.examineHouseTransactionAbnormal = "examine.house.transaction.situation.abnormal";//交易情况-非正常
AssessDicKey.examineHousePaymentMethod = "examine.house.payment.method";//付款方式
AssessDicKey.examineHousePaymentMethodInstallment = "examine.house.payment.method.installment";//付款方式-分期付款
AssessDicKey.examineHousePaymentMethodDisposable = "examine.house.payment.method.disposable";//付款方式- 一次性
AssessDicKey.examineHousePaymentMethodLeaseDisposable = "examine.house.payment.method.lease.disposable";//出租方式- 一次性支付
AssessDicKey.examineHousePaymentMethodLease = "examine.house.payment.method.lease";//出租付款方式
AssessDicKey.examineHouseInformationSourceType = "examine.house.information.source.type";//信息来源类型
AssessDicKey.examineHouseInformationSourceTypeOpen = "examine.house.information.source.type.open";//信息来源类型-公开信息
AssessDicKey.examineHouseInformationSourceCategory = "examine.house.information.source.category";//信息来源类别
AssessDicKey.examineHouseResearchType = "examine.house.research.type";//调查方式
AssessDicKey.examineHousePriceConnotation = "examine.house.price.connotation";//单价内涵
AssessDicKey.examineHouse_transaction_price_type = "examine.house.transaction.price.type";//价格类型
AssessDicKey.examineHouseUseCondition = "examine.house.use.condition";//使用情况
AssessDicKey.examineHouseDecorateSituation = "examine.house.decorate.situation";//使用情况

AssessDicKey.examineBasicHouseRoom = "basicHouseRoom";//房间附件
AssessDicKey.examine_house_room_part = "examine.house.room.part";//房间装修部位
AssessDicKey.examine_house_room_names = "examine.house.room.names";//房间名称
AssessDicKey.examine_house_room_status = "examine.house.room.status";//房间通风、采光等状态
AssessDicKey.examine_house_floor_high = "examine.house.floor.high";//房间层高
AssessDicKey.examine_house_clear_high = "examine .house.clear.high";//房间净高
AssessDicKey.examine_house_room_level = "examine.house.room.level";//房间装修档次
AssessDicKey.examine_house_room_material = "examine.house.room.material";//房间装修材料
AssessDicKey.examine_house_room_adjacent_position = "examine.house.room.adjacent.position";//相邻位置
AssessDicKey.examine_house_room_orientation = "examine.house.room.orientation";//方位
AssessDicKey.examine_house_room_standard_measure = "examine.house.room.standard.measure";//计量标准
AssessDicKey.examine_house_room_storage_request= "examine.house.room.storage.request";//仓储要求
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
// AssessDicKey.examine_house_water_organization = "examine.house.water.drain.organization";//排水_体系
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
AssessDicKey.examine_house_corollary_equipment_use = "examine.house.corollary.equipment.use";//配套设备设施 用途
AssessDicKey.examine_house_room_price_export_columns = "examine.house.room.price.export.columns";//房屋户型单价列名

AssessDicKey.examineCommonOrientation = "examine.common.orientation";//朝向(板块)
AssessDicKey.examineCommonHouseOrientation = "examine.common.house.orientation";//朝向(房屋)
AssessDicKey.examineCommonMaterialGrade = "examine.common.material.grade";//材料档次
AssessDicKey.examineCommonGrade = "examine.common.grade";//档次
AssessDicKey.examineCommonSupplyMode = "examine.common.supply.mode";//供应方式
AssessDicKey.examineCommonMaintenanceSituation = "examine.common.maintenance situation";//设备维护情况

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
AssessDicKey.programmeMarketCostapproachGrade = "programme.market.costApproach.grade";//优，良，劣
AssessDicKey.programmeMarketCostapproachFactor = "programme.market.costApproach.factor";//土地地价因素
AssessDicKey.dataLandApproximationMethodSetting = "data.land.approximation.method.setting";//土地逼近法补偿配置
AssessDicKey.dataTypeIndex = "data.index.type";//指数类型
AssessDicKey.projectDeclareCertificateIsnull = "project.declare.certificate.isNull";//有权证以及无权证
AssessDicKey.projectDeclareCertificate_YES = "project.declare.certificate.yes";//有权证
AssessDicKey.projectDeclareCertificate_NO = "project.declare.certificate.not";//无权证

//资产清查
AssessDicKey.projectSurveyInventoryContentDefaultFindMethod = "project.survey.inventory.content.default.findMethod";//查看方法
AssessDicKey.projectSurveyInventoryContentDefaultFindMethodNetwork = "project.survey.inventory.content.default.findMethod.network";//查看方法_网络查询
AssessDicKey.projectSurveyInventoryContentDefaultAffected = "project.survey.inventory.content.default.affected";//影响对象
AssessDicKey.projectSurveyInventoryContentDefaultInfluenceFactor = "project.survey.inventory.content.default.influenceFactor";//影响要素
AssessDicKey.projectSurveyInventoryContentDefaultCheckOriginal = "project.survey.inventory.content.default.checkOriginal";//查看原件
AssessDicKey.CERTIFICATE_HANDLING_TYPE = "certificate.handling.type";//是否办证


/**
 * 文件模板key
 */
var AssessFTKey = {}
AssessFTKey.ftAssetInventoryRight = "ft.asset.inventory.right";//他项权利
AssessFTKey.ftRealDeclareRealtyCheckList = "ft.real.declareRealtyCheckList";//不动产清单
AssessFTKey.ftHouseOwnershipCertificate = "ft.house.ownership.certificate";//房产证数据导入模型
AssessFTKey.ftLandOwnershipCertificate = "ft.land.ownership.certificate";//土地证数据导入模型 (有权证)
AssessFTKey.ftLandOwnershipCertificateNot = "ft.land.ownership.certificate.not";//土地证数据导入模型 (无权证)
AssessFTKey.ftRealEstateOwnershipCertificate = "ft.real.estate.ownership.certificate";//不动产模板 (有权证)
AssessFTKey.ftRealEstateOwnershipCertificate2 = "ft.real.estate.ownership.certificate2";//不动产模板 (无权证)
AssessFTKey.ftMethodIncomeHistory = "ft.method.income.history";//收益法历史数据模板
AssessFTKey.ftMethodIncomeHistoryRestaurant = "ft.method.income.history.restaurant";//收益法餐饮、酒店、宾馆历史数据模板
AssessFTKey.ftDeclareBuildEngineering = "ft.declare.build.engineering";//在建工程土建模板
AssessFTKey.ftDeclareBuildEquipmentInstall = "ft.declare.build.equipmentInstall";//在建工程设备安装模板
AssessFTKey.ftOperationManualCaseBase = "ft.operation.manual.case.base";//案例库操作手册
AssessFTKey.ftProjectDispatchRegister = "ft.project.dispatch.register";//项目发文登记表模板
AssessFTKey.ftBasicEstateInvestigation = "ft.basic.estate.investigation";//楼盘调查信息模板
AssessFTKey.ftBasicUnitPriceInvestigation = "ft.basic.unit.price.investigation";//户型差异调查表模板

AssessFTKey.ftpLandLevelAreaBaseTemplate = "ftp.land.level.area.base.template";//	基准地价 区域
AssessFTKey.ftpLandLevelDetailBaseTemplate = "ftp.land.level.detail.base.template";//基准地价 级别 详情
AssessFTKey.ftpLandLevelDetailBaseAchievementTemplate = "ftp.land.level.detail.base.achievement.template";//	基准地价 土地因素
AssessFTKey.ftpLandLevelDetailBaseCoefficientTemplate = "ftp.land.level.detail.base.coefficient.template";//	基准地价 容积率
AssessFTKey.ftpDataLoanBenchmarkInterestRateTemplate = "ftp.DataLoanBenchmarkInterestRate.template";//	贷款基准利率
AssessFTKey.ftpDataDepositBenchmarkInterestRateTemplate = "ftp.DataDepositBenchmarkInterestRate.template";//	存款基准利率
AssessFTKey.ftpDataAverageWageUrbanEmployeesTemplate = "ftp.DataAverageWageUrbanEmployees.template";//	城镇就业人员平均工资
AssessFTKey.ftpDataAverageWageUrbanEmployeesItemTemplate = "ftp.DataAverageWageUrbanEmployeesItem.template";//	城镇就业人员平均工资 子类