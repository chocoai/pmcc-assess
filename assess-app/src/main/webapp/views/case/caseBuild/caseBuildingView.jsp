<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">

        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">

                <div class="x_content">
                    <div class="x_title">
                        <h3>楼栋基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="basicBuildFrm">
                        <div class="form-group" id="navButtonBuild">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                </label>
                                <div class="col-sm-2">
                                    <div class="btn-group" data-toggle="buttons">
                                        <button class="btn btn-default"
                                                onclick="navButtonBuild.one(this,1)">楼栋基础
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                </label>
                                <div class="col-sm-2">
                                    <div class="btn-group" data-toggle="buttons">
                                        <button class="btn btn-default"
                                                onclick="navButtonBuild.two(this,2)">第二部分
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                </label>
                                <div class="col-sm-2">
                                    <div class="btn-group" data-toggle="buttons">
                                        <button class="btn btn-default"
                                                onclick="navButtonBuild.three(this,3)">第三部分
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                </label>
                                <div class="col-sm-2">
                                    <div class="btn-group" data-toggle="buttons">
                                        <div class="btn-group" data-toggle="buttons">
                                            <button class="btn btn-default"
                                                    onclick="navButtonBuild.four(this,4)">第四部分
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋号
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋名称
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼栋名称" name="buildingName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    户型区间
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="户型区间" name="unitInterval"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业费
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                                           class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    配套公共设施使用费
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="配套公共设施使用费" name="facilitiesUseFee"
                                           data-rule-number='true' class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    竣工时间
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="竣工时间" name="beCompletedTimeName"
                                           data-rule-number='true' class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层起
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                                           class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层止
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                                           class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    总层数
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="总层数(数字)"
                                           name="floorCount" class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑高度
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑高度(数字)"
                                           name="buildingHeight" class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑面积
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑面积(数字)"
                                           name="buildingArea" class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占地面积
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="占地面积(数字)"
                                           name="coverAnArea" class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    层高
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="层高(数字)"
                                           name="floorHeight" class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    径深
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="径深(数字)"
                                           name="diameterDepth" class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用年限
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                           name="landUseYear" class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    所在位置
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="所在位置" name="location"
                                           class="form-control"
                                           readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开盘时间<span class="symbol readonly"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="开盘时间" name="openTimeName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交房时间<span class="symbol readonly"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="交房时间" name="roomTimeName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业类型
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="物业类型" name="propertyTypeName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构上级
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑结构上级" name="buildingStructureName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构(下级)
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑结构下级"
                                           name="buildingStructureLowerName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑类别
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑类别" name="buildingCategoryName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑公司
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑公司" name="dataBuildingName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业公司
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="物业公司" name="propertyName"
                                           class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group" id="navButtonBuildGroupFileId">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">平面图<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div id="_building_floor_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外装图<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div id="_building_figure_outside"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外观图<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div id="_building_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <%@include file="/views/case/caseBuild/sonBuildDetail.jsp" %>


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
            if (objectData.isNotBlank('${oneCaseBuildingJson}')) {
                var data = JSON.parse('${oneCaseBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第二栋
    navButtonBuild.two = function (target, number) {
        if (number == '2') {
            if (objectData.isNotBlank('${twoCaseBuildingJson}')) {
                var data = JSON.parse('${twoCaseBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第三栋
    navButtonBuild.three = function (target, number) {
        if (number == '3') {
            if (objectData.isNotBlank('${threeCaseBuildingJson}')) {
                var data = JSON.parse('${threeCaseBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第四栋
    navButtonBuild.four = function (target, number) {
        if (number == '4') {
            if (objectData.isNotBlank('${fourCaseBuildingJson}')) {
                var data = JSON.parse('${fourCaseBuildingJson}');
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

    $(function () {
        navButtonBuild.one($("#navButtonBuild").find("button").eq(0)[0], 1);
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
