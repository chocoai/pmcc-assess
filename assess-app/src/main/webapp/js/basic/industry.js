/**非工业与仓储 与 工业与仓储 处理*/

var industry = new Object();

//doc
/*
 * 1:工业与仓储 有 屋面结构 和  围护结构
 * 2:非工业与仓储 供排水情况,供电信息,供热信息,供气信息 只填写有无
 * 3:供水平面图,供电平面图,供气平面图,采暖平面图 工业与仓储 才有
 * 4:户型 跨长,跨数,跨宽 工业与仓储 才有
 * */


industry.config = {
    id: "industry",
    notIndustry: {key: 0, name: "非工业与仓储"},
    industry: {key: 1, name: "工业与仓储"},
    defaultKey: "0",
    build: {
        surface: "industrySurface",//屋面结构
        maintenance: "industryMaintenance",//围护结构
    },
    estate: {
        supplyGas: "industrySupplyGas",//供气信息
        supplyHeating: "industrySupplyHeating",//供热信息
        supplyPower: "industrySupplyPower",//供电信息
        supplyWater: "industrySupplyWater", //供水情况
        drainWater: "industryDrainWater", //排水情况
        supply: "industrySupplyInfo",//供水情况,排水情况,供电信息,供热信息,供气信息 配置在楼盘中的有无  id
        matchingInfo: "industryMatchingInfo",//教育、娱乐、参与、购物、医养
        water_supply_plan: "industry_water_supply_plan",//供水平面图
        power_supply_plan: "industry_power_supply_plan",//供电平面图
        air_supply_plan: "industry_air_supply_plan",//供气平面图
        heating_plan: "industry_heating_plan",//采暖平面图
        material: "industryMaterial"
    },
    unit: {
        huxingType: "huxingType",//户型类别
        huxingTypeStay: "huxingTypeStay",//住宿(招待所)用房
        huxingTypeProduction: "huxingTypeProduction",//生产用房
        huxingTypeOffice: "huxingTypeOffice"//行政办公用房
    },
    house: {
        airConditioner: "industryAirConditioner",//空调情况
        newWind: "industryNewWind",//新风情况
        heating: "industryHeating",//供暖情况
        intelligent: "industryIntelligent",//电力通讯网络
        water: "industryWater",//供排水情况
        corollaryEquipment: "industryCorollaryEquipment",//配套设备设施
        useEnvironment: "industryUseEnvironment",//使用环境
        houseWaterDrain: "industryHouseWaterDrain"//供排水情况
    }
};

/**
 * 判断字符串以及null等
 */
industry.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};

/**
 * 非工业与仓储
 * @type {{init: industry.industryOne.init, build: industry.industryOne.build}}
 */
industry.industryOne = {
    init: function () {
        this.build();
        this.estate();
        this.unit();
        this.house();
    },
    build: function () {
        $("#" + industry.config.build.maintenance).hide();
        $("#" + industry.config.build.surface).hide();
    },
    estate: function () {
        $("#" + industry.config.estate.supplyGas).hide();
        $("#" + industry.config.estate.supplyHeating).hide();
        $("#" + industry.config.estate.supplyPower).hide();
        $("#" + industry.config.estate.supplyWater).hide();
        $("#" + industry.config.estate.drainWater).hide();
        $("#" + industry.config.estate.material).hide();
        $("#" + industry.config.estate.water_supply_plan).hide();
        $("#" + industry.config.estate.power_supply_plan).hide();
        $("#" + industry.config.estate.air_supply_plan).hide();
        $("#" + industry.config.estate.heating_plan).hide();

        $("#" + industry.config.estate.supply).show();
        $("#" + industry.config.estate.matchingInfo).show();
    },
    unit: function () {
        $("#" + industry.config.unit.huxingType).hide();
        $("#" + industry.config.unit.huxingTypeProduction).hide();
        $("#" + industry.config.unit.huxingTypeOffice).hide();
        $("#" + industry.config.unit.huxingTypeStay).show();
    },
    house: function () {
        $("#" + industry.config.house.useEnvironment).hide();
        $("#" + industry.config.house.corollaryEquipment).hide();

        $("#" + industry.config.house.airConditioner).show();
        $("#" + industry.config.house.newWind).show();
        $("#" + industry.config.house.heating).show();
        $("#" + industry.config.house.intelligent).show();
        $("#" + industry.config.house.water).show();
        $("#" + industry.config.house.houseWaterDrain).show();
    }
};

/**
 * 工业与仓储
 * @type {{init: industry.industryTwo.init, build: industry.industryTwo.build}}
 */
industry.industryTwo = {
    init: function () {
        this.build();
        this.estate();
        this.unit();
        this.house();
    },
    build: function () {
        $("#" + industry.config.build.maintenance).show();
        $("#" + industry.config.build.surface).show();
    },
    estate: function () {
        $("#" + industry.config.estate.supplyGas).show();
        $("#" + industry.config.estate.supplyHeating).show();
        $("#" + industry.config.estate.supplyPower).show();
        $("#" + industry.config.estate.supplyWater).show();
        $("#" + industry.config.estate.drainWater).show();
        $("#" + industry.config.estate.material).show();
        $("#" + industry.config.estate.water_supply_plan).show();
        $("#" + industry.config.estate.power_supply_plan).show();
        $("#" + industry.config.estate.air_supply_plan).show();
        $("#" + industry.config.estate.heating_plan).show();

        $("#" + industry.config.estate.supply).hide();
        $("#" + industry.config.estate.matchingInfo).hide();
    },
    unit: function () {
        $("#" + industry.config.unit.huxingType).show();
        $("#" + industry.config.unit.huxingTypeProduction).hide();
        $("#" + industry.config.unit.huxingTypeOffice).hide();
        $("#" + industry.config.unit.huxingTypeStay).hide();
    },
    house: function () {
        $("#" + industry.config.house.corollaryEquipment).show();
        $("#" + industry.config.house.useEnvironment).show();

        $("#" + industry.config.house.airConditioner).hide();
        $("#" + industry.config.house.newWind).hide();
        $("#" + industry.config.house.heating).hide();
        $("#" + industry.config.house.intelligent).hide();
        $("#" + industry.config.house.water).hide();
        $("#" + industry.config.house.houseWaterDrain).hide();
    }
};

industry.changeEvent = function () {
    basicCommon.basicApplyForm.find("input[type='radio']").change(function () {
        var item = $(this).val();
        if (item == industry.config.notIndustry.key) {
            industry.industryOne.init();
        }
        if (item == industry.config.industry.key) {
            industry.industryTwo.init();
        }
    });
};

/**
 * 默认选择
 */
industry.firstEvent = function () {
    if (industry.config.defaultKey == industry.config.notIndustry.key) {
        industry.industryOne.init();
    }
    if (industry.config.defaultKey == industry.config.industry.key) {
        industry.industryTwo.init();
    }
};

industry.keyApp = function (key) {
    if (key == industry.config.notIndustry.key) {
        industry.industryOne.init();
    }
    if (key == industry.config.industry.key) {
        industry.industryTwo.init();
    }
};

$(function () {
    industry.changeEvent();
    industry.firstEvent();
});
