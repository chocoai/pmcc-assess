/**
 * Created by kings on 2018-12-12.
 */
var commonColumn = {};
//房屋-房间
commonColumn.houseRoomColumn = function () {
    var cols = [];
    cols.push({field: 'roomTypeName', title: '房间类型'});
    cols.push({field: 'area', title: '面积'});
    cols.push({field: 'layerHeight', title: '层高(m)'});
    cols.push({field: 'clearHeight', title: '净高(m)'});
    cols.push({field: 'opening', title: '开间/宽(m)'});
    cols.push({field: 'depth', title: '进深/长(m)'});
    cols.push({field: 'sunshine', title: '日照'});
    cols.push({field: 'lighting', title: '采光'});
    cols.push({field: 'aeration', title: '通风'});
    return cols;
}

//房屋-房间-装修
commonColumn.houseRoomDecorateColumn = function () {
    var cols = [];
    cols.push({field: 'part', title: '房间装修部位'});
    cols.push({field: 'materialName', title: '装修材料'});
    cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
    cols.push({field: 'materialPriceName', title: '装修材料价格区间'});
    return cols;
}

//房屋-电力通信网络
commonColumn.houseIntelligentColumn = function () {
    var cols = [];
    cols.push({field: 'wireErectionName', title: '电线架设方式'});
    cols.push({field: 'switchCircuitName', title: '开关回路'});
    cols.push({field: 'lampsLanternsName', title: '灯具'});
    cols.push({field: 'intelligentSystemName', title: '智能系统'});
    return cols;
}

//房屋-供水
commonColumn.houseWaterColumn = function () {
    var cols = [];
    cols.push({field: 'supplyModeName', title: '给水方式'});
    cols.push({field: 'pipingLayoutName', title: '给水管道布置'});
    cols.push({field: 'pipeMaterialName', title: '给水管材料'});
    cols.push({field: 'boosterEquipmentName', title: '给水升压设备'});
    cols.push({field: 'pretreatedWaterName', title: '前置净水'});
    cols.push({field: 'purificationEquipmentPriceName', title: '前置净水设备价格区间'});
    cols.push({field: 'fireWaterSupplyName', title: '消防给水'});
    return cols;
}