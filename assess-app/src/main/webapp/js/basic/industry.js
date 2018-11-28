/**非工业与仓储 与 工业与仓储 处理*/

var industry = new Object();

//doc
/*
 * 1:工业与仓储 有 层面结构 和  维护结构
 * 2:非工业与仓储 供排水情况,供电信息,供热信息,供气信息 只填写有无
 * 3:供水平面图,供电平面图,供气平面图,采暖平面图 工业与仓储 才有
 * 4:户型 跨长,跨数,跨宽 工业与仓储 才有
 * */


industry.config = {
    id: "industry",
    notIndustry: {key: 0, name: "非工业与仓储"},
    industry: {key: 1, name: "工业与仓储"},
    defaultKey: "1",
    build: {
        surface: "industrySurface",//层面结构
        maintenance: "industryMaintenance",//维护结构
    },
    estate: {
        supplyGas: "industrySupplyGas",//供气信息
        supplyHeating: "industrySupplyHeating",//供热信息
        supplyPower: "industrySupplyPower",//供电信息
        supplyWater: "industrySupplyWater", //供排水情况
        supply: "industrySupplyInfo",//供排水情况,供电信息,供热信息,供气信息 配置在楼盘中的有无  id
        matchingInfo:"industryMatchingInfo",//教育、娱乐、参与、购物、医养
        water_supply_plan: "industry_water_supply_plan",//供水平面图
        power_supply_plan: "industry_power_supply_plan",//供电平面图
        air_supply_plan: "industry_air_supply_plan",//供气平面图
        heating_plan: "industry_heating_plan",//采暖平面图
        material: "industryMaterial"
    },
    unit: {
        span: "industrySpan"//跨长 跨宽 跨数
    },
    house: {
        airConditioner: "industryAirConditioner",//空调情况
        newWind: "industryNewWind",//新风情况
        heating: "industryHeating",//供暖情况
        intelligent: "industryIntelligent",//电力通讯网络
        water: "industryWater",//供排水情况
        corollaryEquipment: "industryCorollaryEquipment",//配套设备设施
        useEnvironment: "industryUseEnvironment"//使用环境
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
        $("#" + industry.config.estate.material).hide();
        $("#" + industry.config.estate.water_supply_plan).hide();
        $("#" + industry.config.estate.power_supply_plan).hide();
        $("#" + industry.config.estate.air_supply_plan).hide();
        $("#" + industry.config.estate.heating_plan).hide();

        $("#" + industry.config.estate.supply).show();
        $("#" + industry.config.estate.matchingInfo).show();
    },
    unit: function () {
        $("#" + industry.config.unit.span).hide();
    },
    house: function () {
        $("#" + industry.config.house.useEnvironment).hide();
        $("#" + industry.config.house.corollaryEquipment).hide();

        $("#" + industry.config.house.airConditioner).show();
        $("#" + industry.config.house.newWind).show();
        $("#" + industry.config.house.heating).show();
        $("#" + industry.config.house.intelligent).show();
        $("#" + industry.config.house.water).show();
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
        $("#" + industry.config.estate.material).show();
        $("#" + industry.config.estate.water_supply_plan).show();
        $("#" + industry.config.estate.power_supply_plan).show();
        $("#" + industry.config.estate.air_supply_plan).show();
        $("#" + industry.config.estate.heating_plan).show();

        $("#" + industry.config.estate.supply).hide();
        $("#" + industry.config.estate.matchingInfo).hide();
    },
    unit: function () {
        $("#" + industry.config.unit.span).show();
    },
    house: function () {
        $("#" + industry.config.house.corollaryEquipment).show();
        $("#" + industry.config.house.useEnvironment).show();

        $("#" + industry.config.house.airConditioner).hide();
        $("#" + industry.config.house.newWind).hide();
        $("#" + industry.config.house.heating).hide();
        $("#" + industry.config.house.intelligent).hide();
        $("#" + industry.config.house.water).hide();
    }
};

industry.changeEvent = function () {
    $("#" + industry.config.id).find("input[type='radio']").change(function () {
        var item = $("#" + industry.config.id).find(":radio:checked").val();
        if (industry.isNotBlank(item)) {
            if (item == industry.config.notIndustry.key) {
                industry.industryOne.init();
            }
            if (item == industry.config.industry.key) {
                industry.industryTwo.init();
            }
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
