/**
 * Created by kings on 2018-12-12.
 */
var commonColumn = {};
//楼盘-通信网络信息
commonColumn.estateNetworkColumn = function () {
    var cols = [];
    cols.push({field: 'supplierName', title: '供应商名称'});
    cols.push({field: 'serviceContentName', title: '服务内容'});
    cols.push({field: 'remark', title: '服务内容描述'});
    return cols;
}

//楼盘-车位信息
commonColumn.estateParkingColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '停车场名称'});
    cols.push({field: 'locationName', title: '车位位置'});
    cols.push({field: 'parkingTypeName', title: '车位类型'});
    cols.push({field: 'parkingEstateName', title: '停车场类别'});
    cols.push({field: 'number', title: '车位数量'});
    cols.push({field: 'fileViewName', title: '附件'});
    return cols;
}

//楼盘-教育条件信息
commonColumn.matchingEducationColumn = function () {
    var cols = [];
    cols.push({field: 'schoolName', title: '学校名称'});
    cols.push({field: 'schoolNatureName', title: '学校性质'});
    cols.push({field: 'schoolGradationName', title: '学校级次'});
    cols.push({field: 'schoolLevelName', title: '学校等级'});
    cols.push({field: 'distanceName', title: '距离'});
    return cols;
}

//楼盘-环境因素信息
commonColumn.matchingEnvironmentColumn = function () {
    var cols = [];
    cols.push({field: 'typeName', title: '环境类型'});
    cols.push({field: 'categoryName', title: '影响要素'});
    cols.push({field: 'influenceDegreeName', title: '影响程度'});
    cols.push({field: 'remark', title: '影响源描述'});
    return cols;
}

//楼盘-金融服务信息
commonColumn.matchingFinanceColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '金融名称'});
    cols.push({field: 'distanceName', title: '与金融机构的距离'});
    cols.push({field: 'categoryName', title: '金融类别'});
    cols.push({field: 'natureName', title: '金融机构性质'});
    cols.push({field: 'serviceContentName', title: '服务类别'});
    cols.push({field: 'autoServiceContent', title: '服务内容'});
    return cols;
}

//楼盘-购物商场信息
commonColumn.matchingMarketColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '购物商场名称'});
    cols.push({field: 'categoryName', title: '购物商场类别'});
    cols.push({field: 'gradeName', title: '购物商场档次'});
    cols.push({field: 'distanceName', title: '购物商场距离'});
    return cols;
}

//楼盘-休闲娱乐信息
commonColumn.matchingRecreationColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '休闲娱乐名称'});
    cols.push({field: 'categoryName', title: '休闲娱乐类别'});
    cols.push({field: 'distanceName', title: '休闲娱乐距离'});
    return cols;
}

//楼盘-餐饮信息
commonColumn.matchingRestaurantColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '餐饮名称'});
    cols.push({field: 'categoryName', title: '餐饮类别'});
    cols.push({field: 'gradeName', title: '餐饮档次'});
    cols.push({field: 'distanceName', title: '餐饮距离'});
    return cols;
}

//楼盘-医养条件信息
commonColumn.matchingMedicalColumn = function () {
    var cols = [];
    cols.push({field: 'organizationName', title: '机构名称'});
    cols.push({field: 'organizationLevelName', title: '机构等级'});
    cols.push({field: 'bedNumberName', title: '机构床位数'});
    cols.push({field: 'distanceName', title: '机构距离'});
    return cols;
}

//楼盘-公交信息
commonColumn.matchingTransitColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'distanceName', title: '距离'});
    cols.push({field: 'theLine', title: '所在线路'});
    cols.push({field: 'costStandard', title: '收费标准'});
    return cols;
}

//楼盘-地铁信息
commonColumn.matchingMetroColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'distanceName', title: '距离'});
    cols.push({field: 'theLine', title: '所在线路'});
    cols.push({field: 'costStandard', title: '收费标准'});
    return cols;
}

//楼盘-交通枢纽信息
commonColumn.matchingTrafficHubColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'natureName', title: '性质'});
    cols.push({field: 'distanceName', title: '距离'});
    cols.push({field: 'costStandard', title: '收费标准'});
    return cols;
}

//楼盘-主干道信息
commonColumn.matchingMainRoadColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'distanceName', title: '距离'});
    cols.push({field: 'flagName', title: '是否限行'});
    cols.push({field: 'costStandard', title: '收费标准'});
    cols.push({field: 'positionName', title: '方位'});
    cols.push({field: 'trafficFlowName', title: '交通流量'});
    cols.push({field: 'visitorsFlowrateName', title: '人流量'});
    cols.push({
        field: 'id', title: '限行时间,限行速度,特殊限行', formatter: function (value, row, index) {
            var str = row.limitTime+row.limitSpeed+row.limitSpeialName;
            return str;
        }
    });
    return cols;
}

//楼盘-主要转换互通桥信息
commonColumn.matchingMainConversionColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'costStandard', title: '收费标准'});
    cols.push({field: 'distanceName', title: '距离'});
    return cols;
}

//楼盘-原材料信息
commonColumn.matchingMaterialColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'categoryName', title: '类别'});
    cols.push({field: 'scaleName', title: '规模'});
    cols.push({field: 'distanceName', title: '距离'});
    return cols;
}

//楼盘-供水信息
commonColumn.estateSupplyWaterColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '供水商名称'});
    cols.push({field: 'lineGradeName', title: '供水保障等级'});
    cols.push({field: 'reputationName', title: '供水商信誉'});
    cols.push({field: 'gradeName', title: '供水商等级'});
    cols.push({field: 'power', title: '供应量或功率'});
    return cols;
}

//楼盘-排水信息
commonColumn.estateDrainWaterColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '废水处理商名称'});
    cols.push({field: 'lineGradeName', title: '排水保障等级'});
    cols.push({field: 'reputationName', title: '废水处理商信誉'});
    cols.push({field: 'gradeName', title: '废水处理商等级'});
    cols.push({field: 'power', title: '排水量或功率'});
    return cols;
}

//楼盘-供电信息
commonColumn.estateSupplyPowerColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '供应商名称'});
    cols.push({field: 'lineGradeName', title: '供电保障等级'});
    cols.push({field: 'reputationName', title: '供电商信誉'});
    cols.push({field: 'gradeName', title: '供电商等级'});
    cols.push({field: 'power', title: '供应量或功率'});
    return cols;
}

//楼盘-供热信息
commonColumn.estateSupplyHeatingColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '供热商名称'});
    cols.push({field: 'lineGradeName', title: '供热保障等级'});
    cols.push({field: 'reputationName', title: '供热商信誉'});
    cols.push({field: 'gradeName', title: '供热商等级'});
    cols.push({field: 'power', title: '供应量或功率'});
    return cols;
}

//楼盘-供气信息
commonColumn.estateSupplyGasColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '供应商名称'});
    cols.push({field: 'lineGradeName', title: '供气保障等级'});
    cols.push({field: 'reputationName', title: '供气商信誉'});
    cols.push({field: 'gradeName', title: '供气商等级'});
    cols.push({field: 'power', title: '供应量或功率'});
    return cols;
}

//楼栋-楼栋外装
commonColumn.buildingOutfitColumn = function () {
    var cols = [];
    cols.push({field: 'decorationPart', title: '装修部位'});
    cols.push({field: 'decoratingMaterialName', title: '装修材料'});
    cols.push({field: 'materialGradeName', title: '材料档次'});
    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
    cols.push({field: 'materialPriceName', title: '材料价格区间'});
    return cols;
}

//楼栋-屋面结构
commonColumn.buildingSurfaceColumn = function () {
    var cols = [];
    cols.push({field: 'structureName', title: '屋面结构'});
    cols.push({field: 'description', title: '描述'});
    return cols;
}

//楼栋-围护结构
commonColumn.buildingMaintenanceColumn = function () {
    var cols = [];
    cols.push({field: 'typeName', title: '类型'});
    cols.push({field: 'materialQualityName', title: '材质'});
    cols.push({field: 'categoryName', title: '分类'});
    return cols;
}

//楼栋-建筑功能
commonColumn.buildingFunctionColumn = function () {
    var cols = [];
    cols.push({field: 'typeName', title: '类型'});
    cols.push({field: 'decorationPartName', title: '装修部位'});
    cols.push({field: 'remark', title: '现状描述'});
    return cols;
}

//单元-楼栋内装
commonColumn.unitDecorateColumn = function () {
    var cols = [];
    cols.push({field: 'decorationPartName', title: '装修部位'});
    cols.push({field: 'decoratingMaterialName', title: '装修材料'});
    cols.push({field: 'materialGradeName', title: '材料档次'});
    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
    cols.push({field: 'materialPriceName', title: '材料价格区间'});
    return cols;
}

//单元-户型信息
commonColumn.unitHuxingColumn = function () {
    var cols = [];
    cols.push({field: 'name', title: '户型'});
    cols.push({field: 'area', title: '面积'});
    cols.push({field: 'orientationName', title: '朝向'});
    cols.push({field: 'description', title: '描述'});
    cols.push({field: 'fileViewName', title: '户型图'});
    return cols;
}

//单元-电梯信息
commonColumn.unitElevatorColumn = function () {
    var cols = [];
    cols.push({field: 'maintenanceName', title: '电梯维护情况'});
    cols.push({field: 'typeName', title: '电梯类型'});
    cols.push({field: 'brand', title: '电梯品牌'});
    cols.push({field: 'number', title: '电梯数量'});
    cols.push({field: 'quasiLoadNumber', title: '准载人数'});
    cols.push({field: 'quasiLoadWeight', title: '准载重量'});
    cols.push({field: 'runningSpeed', title: '运行速度'});
    return cols;
}

//房屋-房间
commonColumn.houseRoomColumn = function () {
    var cols = [];
    cols.push({field: 'roomType', title: '房间类型'});
    cols.push({field: 'area', title: '面积'});
    cols.push({field: 'layerHeight', title: '层高(m)'});
    cols.push({field: 'clearHeight', title: '净高(m)'});
    cols.push({field: 'opening', title: '开间/宽(m)'});
    cols.push({field: 'depth', title: '进深/长(m)'});
    cols.push({field: 'sunshine', title: '日照'});
    cols.push({field: 'lighting', title: '采光'});
    cols.push({field: 'aeration', title: '通风'});
    cols.push({field: 'soundInsulation', title: '隔音'});
    return cols;
}

//房屋-房间-装修
commonColumn.houseRoomDecorateColumn = function () {
    var cols = [];
    cols.push({field: 'partName', title: '房间装修部位'});
    cols.push({field: 'materialName', title: '装修材料'});
    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
    cols.push({field: 'materialPriceName', title: '装修材料价格区间'});
    cols.push({field: 'remark', title: '部位描述'});
    cols.push({field: 'fileViewName', title: '附件'});
    return cols;
}

//房屋-电力通信网络
commonColumn.houseIntelligentColumn = function () {
    var cols = [];
    cols.push({field: 'switchCircuitName', title: '开关回路'});
    cols.push({field: 'layingMethodName', title: '铺设方式'});
    cols.push({field: 'lampsLanternsName', title: '灯具'});
    cols.push({field: 'gradeName', title: '档次'});
    cols.push({field: 'intelligentSystemName', title: '智能系统'});
    cols.push({field: 'remark', title: '备注'});
    return cols;
}

//房屋-供水
commonColumn.houseWaterColumn = function () {
    var cols = [];
    cols.push({field: 'supplyModeName', title: '给水方式'});
    cols.push({field: 'pipingLayoutName', title: '给水管道布置'});
    cols.push({field: 'pipeMaterialName', title: '给水管材料'});
    cols.push({field: 'gradeName', title: '档次'});
    cols.push({field: 'boosterEquipmentName', title: '给水升压设备'});
    cols.push({field: 'pretreatedWaterName', title: '前置净水'});
    cols.push({field: 'purificationEquipmentPriceName', title: '前置净水设备价格区间'});
    cols.push({field: 'fireWaterSupplyName', title: '消防给水'});
    return cols;
}

//房屋-排水
commonColumn.houseWaterDrainColumn = function () {
    var cols = [];
    cols.push({field: 'drainSystemName', title: '排水系统'});
    cols.push({field: 'typeName', title: '类别'});
    // cols.push({field: 'organizationName', title: '体系'});
    cols.push({field: 'processingModeName', title: '排水处理方式'});
    // cols.push({field: 'evaluate', title: '排水系统评价'});
    return cols;
}

//房屋-供热
commonColumn.houseHeatingColumn = function () {
    var cols = [];
    cols.push({field: 'equipment', title: '设备品牌'});
    cols.push({field: 'gradeName', title: '档次'});
    // cols.push({field: 'supplyWeast', title: '供应方式'});
    cols.push({field: 'categoryName', title: '类别'});
    cols.push({field: 'supplyModeName', title: '供应方式'});
    cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
    return cols;
}

//房屋-空调
commonColumn.houseAirConditionerColumn = function () {
    var cols = [];
    cols.push({field: 'equipment', title: '设备品牌'});
    cols.push({field: 'gradeName', title: '档次'});
    // cols.push({field: 'supplyWeast', title: '供应方式'});
    cols.push({field: 'categoryName', title: '类别'});
    cols.push({field: 'supplyModeName', title: '供应方式'});
    cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
    return cols;
}

//房屋-新风
commonColumn.houseNewWindColumn = function () {
    var cols = [];
    cols.push({field: 'equipment', title: '设备品牌'});
    cols.push({field: 'categoryName', title: '类别'});
    // cols.push({field: 'supplyWeast', title: '供应方式'});
    cols.push({field: 'gradeName', title: '档次'});
    cols.push({field: 'supplyModeName', title: '供应方式'});
    cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
    return cols;
}

//房屋-配套设置
commonColumn.houseCorollaryEquipmentColumn = function () {
    var cols = [];
    cols.push({field: 'typeName', title: '类型'});
    cols.push({field: 'categoryName', title: '类别'});
    cols.push({field: 'name', title: '名称'});
    cols.push({field: 'equipmentUseName', title: '用途'});
    cols.push({field: 'maintenanceStatusName', title: '维护状况'});
    cols.push({field: 'parameterIndex', title: '参数指标'});
    cols.push({field: 'price', title: '价格'});
    cols.push({field: 'fileViewName', title: '附件'});
    return cols;
}

//房屋-临街状况
commonColumn.houseFaceStreetColumn = function () {
    var cols = [];
    cols.push({field: 'streetName', title: '名称'});
    cols.push({field: 'positionName', title: '方位'});
    cols.push({field: 'streetLevelName', title: '街道级别'});
    cols.push({field: 'trafficFlowName', title: '交通流量'});
    cols.push({field: 'visitorsFlowrateName', title: '人流量'});
    return cols;
}




