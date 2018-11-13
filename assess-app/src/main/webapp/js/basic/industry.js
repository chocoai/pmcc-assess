/**非工业与仓储 与 工业与仓储 处理*/

var industry = new Object();

//doc
/*
* 1:工业与仓储 有 层面结构 和  维护结构
* 2:非工业与仓储 供排水情况,供电信息,供热信息,供气信息 只填写有无
* 3:供水平面图,供电平面图,供气平面图,采暖平面图 工业与仓储 才有
* */


industry.config = {
    id: "industry",
    one: {key: "1", name: "非工业与仓储"},
    two: {key: "2", name: "工业与仓储"},
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
        supply: "supplyPower,supplyWater,supplyGas,supplyHeating",//供排水情况,供电信息,供热信息,供气信息 配置在楼盘中的有无  id
        water_supply_plan: "industry_water_supply_plan",//供水平面图
        power_supply_plan: "industry_power_supply_plan",//供电平面图
        air_supply_plan: "industry_air_supply_plan",//供气平面图
        heating_plan: "industry_heating_plan",//采暖平面图
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
        $.each(industry.config.estate.supply.split(","), function (i, n) {
            $("." + n).show();
        });
        $("#" + industry.config.estate.water_supply_plan).hide();
        $("#" + industry.config.estate.power_supply_plan).hide();
        $("#" + industry.config.estate.air_supply_plan).hide();
        $("#" + industry.config.estate.heating_plan).hide();
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
        $.each(industry.config.estate.supply.split(","), function (i, n) {
            $("." + n).hide();
        });
        $("#" + industry.config.estate.water_supply_plan).show();
        $("#" + industry.config.estate.power_supply_plan).show();
        $("#" + industry.config.estate.air_supply_plan).show();
        $("#" + industry.config.estate.heating_plan).show();
    }
};

industry.changeEvent = function () {
    $("#" + industry.config.id).find("input[type='radio']").change(function () {
        var item = $("#" + industry.config.id).find(":radio:checked").val();
        if (industry.isNotBlank(item)) {
            if (item == industry.config.one.key) {
                industry.industryOne.init();
            }
            if (item == industry.config.two.key) {
                industry.industryTwo.init();
            }
        }
    });
};

/**
 * 默认选择
 */
industry.firstEvent = function () {
    if (industry.config.defaultKey == industry.config.one.key) {
        industry.industryOne.init();
    }
    if (industry.config.defaultKey == industry.config.two.key) {
        industry.industryTwo.init();
    }
};

$(function () {
    industry.changeEvent();
    industry.firstEvent();
});
