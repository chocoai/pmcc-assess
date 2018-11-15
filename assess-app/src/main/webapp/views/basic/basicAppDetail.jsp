<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%--<%@include file="/views/share/form_head.jsp" %>--%>

            <div class="x_panel" id="basicApplyId">
                <div class="x_title">
                    <h2>
                        <small>案例信息</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <li role="presentation" class=""><a href="#basicEstate" role="tab" id="profile-tab1"
                                                                aria-expanded="true"
                                                                onclick="objectData.estate.init()">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                                aria-expanded="false"
                                                                onclick="objectData.build.init(1)">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                                aria-expanded="false"
                                                                onclick="objectData.unit.init()">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                                aria-expanded="false"
                                                                onclick="objectData.house.init();">房屋</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="basicEstate" aria-labelledby="profile-tab1">
                                <%@include file="/views/basic/modelView/estateDetail.jsp" %>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
                                <%@include file="/views/basic/modelView/buildDetail.jsp" %>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                <%@include file="/views/basic/modelView/unitDetail.jsp" %>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <%@include file="/views/basic/modelView/houseDetail.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var objectData = new Object();

    objectData.config = {
        id: "basicApplyId",
        option: {},
        basicEstate: {
            key: "basicEstate",
            name: "楼盘",
            frm: "basicEstateFrm",
            frmLandState: "basicLandState",
            files: {
                filePlanTotal: "estate_floor_total_plan",//总平面图id和字段
                waterSupplyPlan: "water_supply_plan",//供水平面图id和字段
                powerSupplyPlan: "power_supply_plan",//供电平面图id和字段
                airSupplyPlan: "air_supply_plan",//供气平面图id和字段
                heatingPlan: "heating_plan",//采暖平面图id和字段
                fileAppearance: "estate_floor_Appearance_figure" //外观图id和字段
            }
        },
        basicBuilding: {
            key: "basicBuilding",
            name: "楼栋",
            frm: "basicBuildFrm",
            files: {
                building_floor_plan: "building_floor_plan",//平面图id和字段 (楼栋)
                building_figure_outside: "building_figure_outside",//外装图id和字段
                building_floor_Appearance_figure: "building_floor_Appearance_figure"//外观图id和字段
            }
        },
        basicUnit: {
            key: "basicUnit",
            name: "单元"
        },
        basicHouse: {
            key: "basicHouse",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm",
            leaseID: "BasicHouseTradingLease",//房屋出租
            sellID: "BasicHouseTradingSell",//房屋出售
            totalSale: "totalSale",//出售总额
            divBoxSon: "divBoxTradingLeaseAndSell",
            tableSon: "tableTradingLeaseAndSell",
            frmSon: "frmTradingLeaseAndSell",
            houseFileId: "house_img_plan"
        }
    };

    /**
     * 判断字符串以及null等
     */
    objectData.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * 判断对象 属性
     */
    objectData.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (objectData.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    objectData.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    objectData.select2Assignment = function (frm, data, name) {
        if (objectData.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };


    objectData.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objectData.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        })
    };

    objectData.estate = {
        init: function () {
            $.each(objectData.config.basicEstate.files, function (i, n) {
                objectData.showFile(n, AssessDBKey.BasicEstate, "${basicEstate.id}");
            });
            estateNetwork.prototype.loadDataDicList();
            estateParking.prototype.loadDataDicList();
            estateSupplyWater.prototype.loadDataDicList();
            estateSupplyPower.prototype.loadDataDicList();
            estateSupplyHeating.prototype.loadDataDicList();
            estateSupplyGas.prototype.loadDataDicList();
            matchingEducation.prototype.loadDataDicList();
            matchingEnvironment.prototype.loadDataDicList();
            matchingFinance.prototype.loadDataDicList();
            matchingRecreation.prototype.loadDataDicList();
            matchingRestaurant.prototype.loadDataDicList();
            matchingMarket.prototype.loadDataDicList();
            matchingMaterial.prototype.loadDataDicList();
            matchingMedical.prototype.loadDataDicList();
            matchingTransit.prototype.loadDataDicList();
            matchingTrafficHub.prototype.loadDataDicList();
            matchingMetro.prototype.loadDataDicList();
            matchingMainRoad.prototype.loadDataDicList();
            matchingMainConversion.prototype.loadDataDicList();
        }
    };

    var navButtonBuild;
    (function () {
        navButtonBuild = new Object();
        navButtonBuild.groupFileId = "navButtonBuildGroupFileId";
        navButtonBuild.isNotBlank = function (item) {
            if (item) {
                return true;
            }
            return false;
        };

        function writeUpdateFileId(num) {
            var fieldsName = "";
            var labelName = "";
            if (num == 0) {
                labelName = "平面图";
                fieldsName = objectData.config.basicBuilding.files.building_floor_plan + "" + navButtonBuild.switchNumber;
            }
            if (num == 1) {
                labelName = "外装图";
                fieldsName = objectData.config.basicBuilding.files.building_figure_outside + "" + navButtonBuild.switchNumber;
            }
            if (num == 2) {
                labelName = "外观图";
                fieldsName = objectData.config.basicBuilding.files.building_floor_Appearance_figure + "" + navButtonBuild.switchNumber;
            }
            var label = "<label class='col-sm-1 control-label'>" + labelName + "</label>";
            var div = "<div class='col-sm-3'>";
            div += "<div id='" + "_" + fieldsName + "'>" + "</div>";
            div += "</div>";
            return label.concat(div);
        };

        //每次切换更改附件 id
        navButtonBuild.updateFileId = function () {
            var html = "";
            for (var i = 0; i <= 2; i++) {
                html += "<div class='x-valid'>";
                html += writeUpdateFileId(i);
                html += "</div>";
            }
            $("#" + navButtonBuild.groupFileId).empty().append(html);
        };
        //赋值
        navButtonBuild.initData = function (data) {
            $("#" + objectData.config.basicBuilding.frm).initForm(data);
            $.each(objectData.config.basicBuilding.files, function (i, n) {
                objectData.showFile(n + "" + navButtonBuild.switchNumber, AssessDBKey.BasicBuilding, data.id);
            });
        };
        navButtonBuild.clearAll = function () {
            $("#" + objectData.config.basicBuilding.frm).clearAll();
        };
    })();


    navButtonBuild.switchNumber = 0;
    navButtonBuild.switchInit = function (target, data, number) {
        if (objectData.isNotBlank(data)) {
            navButtonBuild.clearAll();
            navButtonBuild.switchNumber = number;
            navButtonBuild.updateFileId();
            navButtonBuild.initData(data);
            navButtonBuild.dataButtonWrite(target);
            buildingModel.prototype.viewInit();
        } else {
            toastr.success('无数据!');
        }
    };
    //第一栋
    navButtonBuild.one = function (target, number) {
        if (number == '1') {
            if (objectData.isNotBlank('${oneBasicBuildingJson}')) {
                var data = JSON.parse('${oneBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第二栋
    navButtonBuild.two = function (target, number) {
        if (number == '2') {
            if (objectData.isNotBlank('${twoBasicBuildingJson}')) {
                var data = JSON.parse('${twoBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第三栋
    navButtonBuild.three = function (target, number) {
        if (number == '3') {
            if (objectData.isNotBlank('${threeBasicBuildingJson}')) {
                var data = JSON.parse('${threeBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第四栋
    navButtonBuild.four = function (target, number) {
        if (number == '4') {
            if (objectData.isNotBlank('${fourBasicBuildingJson}')) {
                var data = JSON.parse('${fourBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    navButtonBuild.dataButtonWrite = function (target) {
        $.each($("#navButtonBuild button"), function (i, n) {
            $(n).removeClass();
            $(n).addClass("btn btn-default");
        });
        //改变按钮颜色
        $(target).removeClass();
        $(target).addClass("btn btn-primary");
        $("." + buildingModel.prototype.config().sonTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingSurfaceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingMaintenanceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingFunctionTable).html(navButtonBuild.switchNumber + "部分");
    };

    objectData.build = {
        init: function (number) {
            var target = $("#navButtonBuild button").eq(0)[0];
            navButtonBuild.one(target, number);
        }
    };

    objectData.unit = {
        init: function () {
            unitDecorate.prototype.loadDataDicList();
            unitHuxing.prototype.loadDataDicList();
            unitElevator.prototype.loadDataDicList();
        }
    };

    objectData.house = {
        init: function () {
            houseRoom.prototype.loadDataDicList();
            houseWater.prototype.loadDataDicList();
            houseIntelligent.prototype.loadDataDicList();
            houseFaceStreet.prototype.loadDataDicList();
            houseCorollaryEquipment.prototype.loadDataDicList();
            houseNewWind.prototype.loadDataDicList();
            houseAirConditioner.prototype.loadDataDicList();
            houseHeating.prototype.loadDataDicList();
            objectData.showFile(objectData.config.basicHouse.houseFileId, AssessDBKey.BasicHouse, '${empty basicHouse.id?0:basicHouse.id}');
            var tradingID = "${basicHouseTrading.tradingType}";
            var tradingType = null;
            AssessCommon.getDataDicInfo(tradingID, function (data) {
                tradingType = data.fieldName;
                if (tradingType == objectData.config.basicHouse.leaseID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).show();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.leaseID);
                }
                if (tradingType == objectData.config.basicHouse.sellID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
            });
            AssessCommon.getDataDicInfo("${basicHouseTrading.paymentMethod}", function (data) {
                if (data.name == '一次性') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='installmentInterestRate']").parent().parent().hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
                if (data.name == '分期付款') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='installmentInterestRate']").parent().parent().show();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.sellID);
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/basicUnitHuxing/getBasicUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id: '${empty basicHouse.huxingId?0:basicHouse.huxingId}'},
                success: function (result) {
                    if (result.ret) {
                        if (objectData.isNotBlank(result.data)) {
                            if (objectData.isNotBlank(result.data.fileViewName)) {
                                $("#" + objectData.config.basicHouse.frm).find(".house_latest_family_plan").html(result.data.fileViewName);
                            }
                        }
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        subLoadList: function (type_) {
            var cols = [];
            if (type_ == objectData.config.basicHouse.leaseID) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            if (type_ == objectData.config.basicHouse.sellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
            }
            $("#" + objectData.config.basicHouse.tableSon).bootstrapTable('destroy');
            TableInit(objectData.config.basicHouse.tableSon, "${pageContext.request.contextPath}/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
                type: type_, houseId: '${empty basicHouse.id?0:basicHouse.id}', approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };


    $(function () {
        //选项卡处理
        var basicUnit = "${basicUnit}";
        var basicApply = "${basicApply}";
        var basicEstate = "${basicEstate}";
        var basicHouse = "${basicHouse}";
        var basicBuildingMain = "${basicBuildingMain}";

        if (objectData.isNotBlank(basicApply)) {
            var industryA = "${basicApply.industry}";
            if (objectData.isNotBlank(industryA)) {
                industry.keyApp(industryA);
            }
        }

        if (objectData.isNotBlank(basicUnit)) {
            $("#profile-tab3").attr("data-toggle", "tab");
            objectData.unit.init();
        }

        if (objectData.isNotBlank(basicEstate)) {
            $("#profile-tab1").attr("data-toggle", "tab");
            objectData.estate.init();
        }

        if (objectData.isNotBlank(basicHouse)) {
            $("#profile-tab4").attr("data-toggle", "tab");
            objectData.house.init();
        }

        if (objectData.isNotBlank(basicBuildingMain)) {
            $("#profile-tab2").attr("data-toggle", "tab");
            navButtonBuild.one($("#navButtonBuild").find("button").eq(0)[0], 1);
        }
//============================================================||======================================================
        if (objectData.isNotBlank(basicHouse) && objectData.isNotBlank(basicUnit) && objectData.isNotBlank(basicEstate) && objectData.isNotBlank(basicBuildingMain)) {
            $('#caseTab a').eq(0).tab('show');
        }
        if (objectData.isNotBlank(basicEstate)) {
            if (!objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicBuildingMain)) {
                $('#caseTab a').eq(0).tab('show');
            }
        }
        if (objectData.isNotBlank(basicEstate)) {
            if (!objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicUnit) && objectData.isNotBlank(basicBuildingMain)) {
                $('#caseTab a').eq(0).tab('show');
            }
        }
        if (objectData.isNotBlank(basicEstate)) {
            if (!objectData.isNotBlank(basicHouse) && objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicBuildingMain)) {
                $('#caseTab a').eq(0).tab('show');
            }
        }
        if (objectData.isNotBlank(basicEstate)) {
            if (objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicBuildingMain)) {
                $('#caseTab a').eq(0).tab('show');
            }
        }
        if (objectData.isNotBlank(basicBuildingMain)) {
            if (!objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicEstate)) {
                $('#caseTab a').eq(1).tab('show');
            }
        }
        if (objectData.isNotBlank(basicBuildingMain)) {
            if (!objectData.isNotBlank(basicHouse) && objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicEstate)) {
                $('#caseTab a').eq(1).tab('show');
            }
        }
        if (objectData.isNotBlank(basicUnit)) {
            if (!objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicBuildingMain) && !objectData.isNotBlank(basicEstate)) {
                $('#caseTab a').eq(2).tab('show');
            }
        }
        if (objectData.isNotBlank(basicUnit)) {
            if (objectData.isNotBlank(basicHouse) && !objectData.isNotBlank(basicBuildingMain) && !objectData.isNotBlank(basicEstate)) {
                $('#caseTab a').eq(2).tab('show');
            }
        }
        if (objectData.isNotBlank(basicHouse)) {
            if (!objectData.isNotBlank(basicUnit) && !objectData.isNotBlank(basicBuildingMain) && !objectData.isNotBlank(basicEstate)) {
                $('#caseTab a').eq(3).tab('show');
            }
        }
    });
</script>
<script type="application/javascript">


</script>
</html>
