<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main" id="basicApplyId">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        案例基础数据维护
                    </h2>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        查询信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="industry_no" name="industry" value="1" checked="checked">
                                <label for="industry_no">非工业与仓储</label>
                            </span>

                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="industry" name="industry" value="2">
                                <label for="industry">工业与仓储</label>
                            </span>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicEstate" placeholder="楼盘名称"
                                           onkeydown="objectData.autocompleteEstate(this)">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.estate.show({},{})"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.estate.edit()"
                                           value="修改">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.estate.details()"
                                           value="详情">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicBuilding" placeholder="楼栋编号"
                                           onkeydown="objectData.autocompleteBuilding(this)">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.building.show({},{})"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.building.edit()"
                                           value="修改">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.building.details()"
                                           value="详情">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单元编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicUnit" placeholder="单元编号"
                                           onkeydown="objectData.autocompleteUnit(this);">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.unit.show({},{})"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.unit.edit()"
                                           value="修改">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.unit.details()"
                                           value="详情">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    房屋编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicHouse" placeholder="房屋编号"
                                           onkeydown="objectData.autocompleteHouse(this)">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" value="添加"
                                           onclick="objectData.house.show({},{})">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" value="修改"
                                           onclick="objectData.house.edit()">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" value="详情"
                                           onclick="objectData.house.details()">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title">
                    <h2>
                        <small>信息</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <li role="presentation" class=""><a href="#caseEstate" role="tab" id="profile-tab1"
                                                                aria-expanded="true">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                                aria-expanded="false">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                                aria-expanded="false">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                                aria-expanded="false">房屋</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                                <div>
                                    <%@include file="/views/basic/modelView/estateView.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
                                <div>
                                    <%@include file="/views/basic/modelView/buildingView.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                <div>
                                    <%@include file="/views/basic/modelView/unitView.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <div>
                                    <%@include file="/views/basic/modelView/houseView.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">

    var objectData = new Object();
    objectData.flag = true;
    objectData.config = {
        id: "basicApplyId",
        view: {save: "saveView", detail: "detailView"},
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
            name: "单元",
            frm: "basicUnitFrm"
        },
        basicHouse: {
            key: "basicHouse",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm"
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

    /**
     * form label 赋值
     **/
    objectData.initLabelForm = function (frm, data) {
        if (!this.isNotBlank(frm)) {
            return false;
        }
        if (!this.isNotBlankObject(data)) {
            return false;
        }
        $("#" + frm).find("label").each(function (i, n) {
            var name = $(n).attr("data-name");
            if (objectData.isNotBlank(name)) {
                $(n).html(eval("data." + name));
            }
        });
    };


    objectData.uploadFile = function (fieldsName, table, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objectData.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
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
            deleteFlag: true
        })
    };

    /**
     * 楼盘 信息自动补全
     */
    objectData.autocompleteEstate = function (_this) {
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        childs.eq(3).hide();
        $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/caseEstate/autoCompleteCaseEstate",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            name: itemVal
                        },
                        success: function (result) {
                            if (result.ret) {
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            } else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    childs.eq(1).hide();
                    childs.eq(2).show();
                    childs.eq(3).show();
                    $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id", ele.item.key);
                },
                /*当焦点移动到一个条目上（未选择）时触发。默认的动作是把文本域中的值替换为获得焦点的条目的值，即使该事件是通过键盘交互触发的。取消该事件会阻止值被更新，但不会阻止菜单项获得焦点。*/
                focus: function (event, ui) {
                }
            }
        );
    };

    /**
     * 楼栋 信息自动补全
     */
    objectData.autocompleteBuilding = function (_this) {
        var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
        if (!objectData.isNotBlank(estateId)) {
            // Alert("请先选择楼盘然后在选择楼栋!");
            // return false;
        }
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        childs.eq(3).hide();
        $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/caseBuildingMain/autoCompleteCaseBuilding",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            identifier: itemVal,
                            estateId: estateId
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (objectData.isNotBlank(result.data)) {
                                    if (result.data.length == 0) {
                                        // Alert("此楼盘下无楼栋信息!");
                                    }
                                }
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            } else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    childs.eq(1).hide();
                    childs.eq(2).show();
                    childs.eq(3).show();
                    $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id", ele.item.key);
                },
                focus: function (event, ui) {
                }
            }
        );
    };

    /**
     * 单元信息自动补全
     */
    objectData.autocompleteUnit = function (_this) {
        var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
        if (!objectData.isNotBlank(buildingId)) {
            // Alert("请先选择楼栋然后在选择单元!");
            // return false;
        }
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        childs.eq(3).hide();
        $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/caseUnit/autoCompleteCaseUnit",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            unitNumber: itemVal,
                            caseBuildingMainId: buildingId
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (objectData.isNotBlank(result.data)) {
                                    if (result.data.length == 0) {
                                        // Alert("此楼栋下无单元信息!");
                                    }
                                }
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            } else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    childs.eq(1).hide();
                    childs.eq(2).show();
                    childs.eq(3).show();
                    $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id", ele.item.key);
                },
                focus: function (event, ui) {
                }
            }
        );
    };

    /**
     * 房屋信息自动补全
     */
    objectData.autocompleteHouse = function (_this) {
        var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
        if (!objectData.isNotBlank(unitId)) {
        }
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        childs.eq(3).hide();
        $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/caseHouse/autoCompleteCaseHouse",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            houseNumber: itemVal,
                            unitId: unitId
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (objectData.isNotBlank(result.data)) {
                                    if (result.data.length == 0) {
                                        // Alert("此单元下无房屋信息!");
                                    }
                                }
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            } else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    childs.eq(1).hide();
                    childs.eq(2).show();
                    childs.eq(3).show();
                    $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").attr("data-id", ele.item.key);
                },
                focus: function (event, ui) {
                }
            }
        );
    };


    objectData.estateFlag = true;
    //处理 楼盘
    objectData.estate = {
        show: function (itemA, itemB) {
            $('#caseTab a:first').tab('show');
            $("#" + objectData.config.basicEstate.frm).find("." + objectData.config.view.save).show();
            $("#" + objectData.config.basicEstate.frm).find("." + objectData.config.view.detail).hide();
            $("#" + objectData.config.basicEstate.frmLandState).find("." + objectData.config.view.save).show();
            $("#" + objectData.config.basicEstate.frmLandState).find("." + objectData.config.view.detail).hide();
            if (objectData.estateFlag) {
                objectData.estate.init(itemA);
                objectData.estate.landStateInit(itemB);
                objectData.estateFlag = false;
            }
            $("#profile-tab1").attr("data-toggle","tab");
            // $("#" + objectData.config.basicEstate.frm).initForm({name:$("#"+objectData.config.id).find("input[name='"+objectData.config.basicEstate.key+"']").val()});
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
        },
        details: function () {
            var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
            if (!objectData.isNotBlank(estateId)) {
                toastr.success('未找到查询的数据');
                return false;
            }
            $("#" + objectData.config.basicEstate.frm).clearAll();
            $("#" + objectData.config.basicEstate.frmLandState).clearAll();
            if (objectData.isNotBlank(estateId)) {
                objectData.firstRemove.estateFirst();
            }
            objectData.estate.appWriteEstate(estateId, function (data) {
                toastr.success('数据转移成功!');
                var CaseEstate = data.CaseEstate;
                var CaseEstateLandState = data.CaseEstateLandState;
                if (!objectData.isNotBlank(CaseEstate)) {
                    CaseEstate = {};
                }
                if (!objectData.isNotBlank(CaseEstateLandState)) {
                    CaseEstateLandState = {};
                }
                objectData.estate.show(CaseEstate, CaseEstateLandState);
                objectData.initLabelForm(objectData.config.basicEstate.frm, CaseEstate);
                objectData.initLabelForm(objectData.config.basicEstate.frmLandState, CaseEstateLandState);
                $("#" + objectData.config.basicEstate.frm).find("." + objectData.config.view.save).hide();
                $("#" + objectData.config.basicEstate.frm).find("." + objectData.config.view.detail).show();
                $("#" + objectData.config.basicEstate.frmLandState).find("." + objectData.config.view.save).hide();
                $("#" + objectData.config.basicEstate.frmLandState).find("." + objectData.config.view.detail).show();
            }, function (item) {
                objectData.estate.show({}, {});
                Alert("失败!" + item);
            });
        },
        edit: function () {
            var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
            if (!objectData.isNotBlank(estateId)) {
                Alert("请查询楼盘!");
                return false;
            }
            $("#" + objectData.config.basicEstate.frm).clearAll();
            $("#" + objectData.config.basicEstate.frmLandState).clearAll();
            objectData.firstRemove.estateFirst();
            objectData.estate.appWriteEstate(estateId, function (data) {
                toastr.success('数据转移成功!');
                var CaseEstate = data.CaseEstate;
                var CaseEstateLandState = data.CaseEstateLandState;
                if (!objectData.isNotBlank(CaseEstate)) {
                    CaseEstate = {};
                }
                if (!objectData.isNotBlank(CaseEstateLandState)) {
                    CaseEstateLandState = {};
                }
                $("#" + objectData.config.basicEstate.frm).initForm(CaseEstate);
                $("#" + objectData.config.basicEstate.frmLandState).initForm(CaseEstateLandState);
                objectData.estate.show(CaseEstate, CaseEstateLandState);
            }, function (item) {
                objectData.estate.show({}, {});
                Alert("失败!" + item);
            });
        },
        init: function (item) {
            $.each(objectData.config.basicEstate.files, function (i, n) {
                objectData.uploadFile(n, AssessDBKey.BasicEstate, objectData.isNotBlank(item.id) ? item.id : 0);
            });
            AssessCommon.initAreaInfo({
                provinceTarget: $("#" + objectData.config.id).find("#" + objectData.config.basicEstate.frm).find("select.province"),
                cityTarget: $("#" + objectData.config.id).find("#" + objectData.config.basicEstate.frm).find("select.city"),
                districtTarget: $("#" + objectData.config.id).find("#" + objectData.config.basicEstate.frm).find("select.district"),
                provinceValue: item.province,
                cityValue: item.city,
                districtValue: item.district
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataProperty/dataPropertyList",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#" + objectData.config.id).find("#" + objectData.config.basicEstate.frm).find("select.developerId").empty().html(option);
                            objectData.select2Assignment(objectData.config.basicEstate.frm, item.developerId, "developerId");
                        }
                    }
                },
                error: function (result) {
                    console.info(result);
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataBlock/dataBlockList",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#" + objectData.config.id).find("#" + objectData.config.basicEstate.frm).find("select.blockId").empty().html(option);
                            objectData.select2Assignment(objectData.config.basicEstate.frm, item.blockId, "blockId");
                        }
                    }
                },
                error: function (result) {
                    console.info(result);
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });


        },
        landStateInit: function (item) {
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, item.landUseType, function (html, data) {
                $("#" + objectData.config.basicEstate.frmLandState).find('select.landUseType').empty().html(html);
            });
            $("#" + objectData.config.basicEstate.frmLandState).find("select.landUseType").change(function () {
                var id = $("#" + objectData.config.basicEstate.frmLandState).find("select.landUseType").val();
                AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                    $("#" + objectData.config.basicEstate.frmLandState).find('select.landUseCategory').empty().html(html);
                    objectData.select2Assignment(objectData.config.basicEstate.frmLandState, item.landUseCategory, "landUseCategory");
                });
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/listDataLandLevel",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].leve + "</option>";
                            }
                            $("#" + objectData.config.basicEstate.frmLandState).find("select.landLevel").empty().html(option);
                            objectData.select2Assignment(objectData.config.basicEstate.frmLandState, item.landLevel, "landLevel");
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        appWriteEstate: function (id, callback, callbackError) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicEstate/appWriteEstate",
                type: "POST",
                data: {caseEstateId: id},
                dataType: "json",
                success: function (result) {
                    if (result.ret && callback) {
                        callback(result.data);
                    }
                    if (!result.ret && callbackError) {
                        callbackError("未获取到数据!");
                    }
                },
                error: function (result) {
                    if (!result.ret && callbackError) {
                        callbackError(result.errmsg);
                    }
                }
            });
        }
    };

    //处理 楼栋
    objectData.building = {
        show: function (item) {
            var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
            if (!objectData.isNotBlank(estateId)) {
            }
            objectData.building.init(item);
            $("#profile-tab2").attr("data-toggle","tab");
            $("#" + objectData.config.basicBuilding.frm).find("input").each(function (i, n) {
                var readonly = $(n).attr("readonly");
                if (objectData.isNotBlank(readonly)) {
                    $(n).removeAttr("readonly");
                }
            });
        },
        details: function () {
            var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
            if (!objectData.isNotBlank(buildingId)) {
                toastr.success("无查询到楼栋数据,请添加!");
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/caseBuildingMain/getcaseBuildingMainById",
                type: "get",
                dataType: "json",
                data: {id: buildingId},
                success: function (result) {
                    if (result.ret) {
                        if (objectData.isNotBlankObjectProperty(result.data)) {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/caseBuilding/listCaseBuilding",
                                type: "get",
                                dataType: "json",
                                data: {caseBuildingMainId: result.data.id},
                                success: function (resultA) {
                                    if (resultA.ret) {
                                        if (objectData.isNotBlank(resultA.data)) {
                                            $.each(resultA.data, function (i, n) {
                                                if (objectData.isNotBlank(n.part)) {
                                                    navButtonBuild.setObjArrayElement(n.part, n);
                                                }
                                            });
                                            $("#identifier").removeAttr("readonly").val(result.data.identifier);
                                            $("#caseBuildingMainId").val(result.data.id);

                                            $("#" + objectData.config.basicBuilding.frm).find("input").each(function (i, n) {
                                                var readonly = $(n).attr("readonly");
                                                if (!objectData.isNotBlank(readonly)) {
                                                    $(n).attr("readonly", "readonly");
                                                }
                                            });
                                            var temp = resultA.data[0];
                                            if (objectData.isNotBlankObjectProperty(temp)) {
                                                objectData.firstRemove.buildFirst();
                                                objectData.building.appWriteBuilding(temp);
                                            } else {
                                                objectData.building.show({});
                                            }
                                        }
                                    }
                                },
                                error: function (result) {
                                    Alert("调用服务端方法失败，失败原因:" + result);
                                }
                            });
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        edit: function () {
            var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
            if (!objectData.isNotBlank(buildingId)) {
                Alert("请查询楼栋!");
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/caseBuildingMain/getcaseBuildingMainById",
                type: "get",
                dataType: "json",
                data: {id: buildingId},
                success: function (result) {
                    if (result.ret) {
                        if (objectData.isNotBlankObjectProperty(result.data)) {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/caseBuilding/listCaseBuilding",
                                type: "get",
                                dataType: "json",
                                data: {caseBuildingMainId: result.data.id},
                                success: function (resultA) {
                                    if (resultA.ret) {
                                        if (objectData.isNotBlank(resultA.data)) {
                                            $.each(resultA.data, function (i, n) {
                                                if (objectData.isNotBlank(n.part)) {
                                                    navButtonBuild.setObjArrayElement(n.part, n);
                                                }
                                            });

                                            $("#identifier").removeAttr("readonly").val(result.data.identifier);
                                            $("#caseBuildingMainId").val(result.data.id);

                                            $("#" + objectData.config.basicBuilding.frm).find("input").each(function (i, n) {
                                                var readonly = $(n).attr("readonly");
                                                if (objectData.isNotBlank(readonly)) {
                                                    $(n).removeAttr("readonly");
                                                }
                                            });
                                            var temp = resultA.data[0];
                                            $("#profile-tab2").attr("data-toggle","tab");
                                            if (objectData.isNotBlankObjectProperty(temp)) {
                                                objectData.firstRemove.buildFirst();
                                                objectData.building.appWriteBuilding(temp);
                                            } else {
                                                objectData.building.show({});
                                            }
                                        }
                                    }
                                },
                                error: function (result) {
                                    Alert("调用服务端方法失败，失败原因:" + result);
                                }
                            });
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        appWriteBuilding: function (temp) {
            var caseMainBuildId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
            $.ajax({
                url: "${pageContext.request.contextPath}/basicBuilding/appWriteBuilding",
                type: "post",
                data: {caseMainBuildId: caseMainBuildId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        objectData.building.show(temp);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        init: function (item) {
            $('#caseTab a').eq(1).tab('show');
            $(function () {
                navButtonBuild.init();
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, item.buildingCategory, function (html, data) {
                $("#" + objectData.config.basicBuilding.frm).find('select.buildingCategory').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, item.buildingStructure, function (html, data) {
                $("#" + objectData.config.basicBuilding.frm).find('select.buildingStructure').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, item.propertyType, function (html, data) {
                $("#" + objectData.config.basicBuilding.frm).find('select.propertyType').empty().html(html).trigger('change');
            });
            $("#" + objectData.config.basicBuilding.frm).find('select.buildingStructure').val(item.buildingStructure).trigger("change");
            $("#" + objectData.config.basicBuilding.frm).find("select.buildingStructure").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + objectData.config.basicBuilding.frm).find("select.buildingStructure").val();
                if (objectData.isNotBlank(id)) {
                    AssessCommon.loadDataDicByPid(id, item.buildingStructureLower, function (html, data) {
                        $("#" + objectData.config.basicBuilding.frm).find("select.buildingStructureLower").html(html);
                    });
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataBuilder/dataBuilderList",
                type: "get",
                dataType: "json",
                data: {name: null},
                success: function (result) {
                    if (result.ret) {
                        var item = result.data;
                        var option = "<option value=''>请选择</option>";
                        if (item.length > 0) {
                            for (var i = 0; i < item.length; i++) {
                                option += "<option value='" + item[i].id + "'>" + item[i].name + "</option>";
                            }
                            $("#" + objectData.config.basicBuilding.frm).find('select.builderId').empty().html(option).trigger('change');
                            objectData.select2Assignment(objectData.config.basicBuilding.frm, item.builderId, "builderId");
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataProperty/dataPropertyList",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var item = result.data;
                        var option = "<option value=''>请选择</option>";
                        if (item.length > 0) {
                            for (var i = 0; i < item.length; i++) {
                                option += "<option value='" + item[i].id + "'>" + item[i].name + "</option>";
                            }
                            $("#" + objectData.config.basicBuilding.frm).find('select.propertyId').empty().html(option).trigger('change');
                            objectData.select2Assignment(objectData.config.basicBuilding.frm, item.propertyId, "propertyId");
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            navButtonBuild.one($("#navButtonBuild button").eq(0)[0], 1)
        }
    };

    //处理单元
    objectData.unit = {
        show: function () {
            var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
            if (!objectData.isNotBlank(buildingId)) {
            }
            $("#profile-tab3").attr("data-toggle","tab");
            $('#caseTab a').eq(2).tab('show');
            $("#" + objectData.config.basicUnit.frm).find("." + objectData.config.view.save).show();
            $("#" + objectData.config.basicUnit.frm).find("." + objectData.config.view.detail).hide();
            unitDecorate.prototype.loadDataDicList();
            unitHuxing.prototype.loadDataDicList();
            unitElevator.prototype.loadDataDicList();
            $("#" + objectData.config.basicUnit.frm).initForm({unitNumber:$("#"+objectData.config.id).find("input[name='"+objectData.config.basicUnit.key+"']").val()});
        },
        edit: function () {
            var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
            if (!objectData.isNotBlank(unitId)) {
                Alert("请查询单元!");
                return false;
            }
            objectData.firstRemove.unitFirst();
            objectData.unit.appWriteUnit(unitId, function (data) {
                toastr.success('数据转移成功!');
                $("#" + objectData.config.basicUnit.frm).initForm(data);
                objectData.unit.show();
            }, function () {
                objectData.unit.show();
            });
        },
        details: function () {
            var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
            if (!objectData.isNotBlank(unitId)) {
                toastr.success('未找到查询的数据');
                return false;
            }
            objectData.firstRemove.unitFirst();
            objectData.unit.appWriteUnit(unitId, function (data) {
                toastr.success('数据转移成功!');
                objectData.unit.show();
                objectData.initLabelForm(objectData.config.basicUnit.frm, data);
                $("#" + objectData.config.basicUnit.frm).find("." + objectData.config.view.save).hide();
                $("#" + objectData.config.basicUnit.frm).find("." + objectData.config.view.detail).show();
            }, function (item) {
                Alert("失败!" + item);
                objectData.unit.show();
            });
        },
        appWriteUnit: function (id, callback, callbackError) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicUnit/appWriteUnit",
                type: "POST",
                data: {caseUnitId: id},
                dataType: "json",
                success: function (result) {
                    if (result.ret && callback) {
                        callback(result.data);
                    }
                    if (!result.ret && callbackError) {
                        callbackError("未获取到数据!");
                    }
                },
                error: function (result) {
                    if (!result.ret && callbackError) {
                        callbackError(result.errmsg);
                    }
                }
            });
        }
    };

    //处理房屋
    objectData.houseFlag = true;
    objectData.house = {
        show: function () {
            var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
            if (!objectData.isNotBlank(unitId)) {
            }
            $('#caseTab a').eq(3).tab('show');
            $("#profile-tab4").attr("data-toggle","tab");
            $("#" + objectData.config.basicHouse.frm).find("." + objectData.config.view.save).show();
            $("#" + objectData.config.basicHouse.frm).find("." + objectData.config.view.detail).hide();
            $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.view.save).show();
            $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.view.detail).hide();
            if (objectData.houseFlag) {
                houseModelFun.houseInit({});
                houseModelFun.tradingInit({});
                objectData.houseFlag = false;
            }
            houseRoom.prototype.loadDataDicList();
            houseWater.prototype.loadDataDicList();
            houseIntelligent.prototype.loadDataDicList();
            houseFaceStreet.prototype.loadDataDicList();
            houseCorollaryEquipment.prototype.loadDataDicList();
            houseNewWind.prototype.loadDataDicList();
            houseAirConditioner.prototype.loadDataDicList();
            houseHeating.prototype.loadDataDicList();
            $("#" + objectData.config.basicHouse.frm).initForm({houseNumber:$("#"+objectData.config.id).find("input[name='"+objectData.config.basicHouse.key+"']").val()});
        },
        edit: function () {
            var id = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").attr("data-id");
            if (!objectData.isNotBlank(id)) {
                Alert("请先查询房屋");
                return false;
            }
            objectData.firstRemove.houseFirst();
            objectData.house.appWriteHouse(id, function (data) {
                var CaseHouseTrading = data.CaseHouseTrading;
                var CaseHouse = data.CaseHouse;
                if (!objectData.isNotBlank(CaseHouseTrading)) {
                    CaseHouseTrading = {};
                }
                if (!objectData.isNotBlank(CaseHouse)) {
                    CaseHouse = {};
                }
                objectData.house.show();
                houseModelFun.houseInit(CaseHouse);
                houseModelFun.tradingInit(CaseHouseTrading);
                toastr.success('数据转移成功!');
            }, function (item) {
                objectData.house.show();
                Alert("失败!" + item);
            });
        },
        details: function () {
            var id = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").attr("data-id");
            if (!objectData.isNotBlank(id)) {
                toastr.success('请先查询房屋');
                return false;
            }
            if (objectData.isNotBlank(id)) {
                objectData.firstRemove.houseFirst();
            }
            objectData.house.appWriteHouse(id, function (data) {
                var CaseHouseTrading = data.CaseHouseTrading;
                var CaseHouse = data.CaseHouse;
                if (!objectData.isNotBlank(CaseHouseTrading)) {
                    CaseHouseTrading = {};
                }
                if (!objectData.isNotBlank(CaseHouse)) {
                    CaseHouse = {};
                }
                objectData.house.show();
                objectData.initLabelForm(objectData.config.basicHouse.frm,CaseHouse);
                objectData.initLabelForm(objectData.config.basicHouse.tradingFrm,CaseHouseTrading);
                $("#" + objectData.config.basicHouse.frm).find("." + objectData.config.view.save).hide();
                $("#" + objectData.config.basicHouse.frm).find("." + objectData.config.view.detail).show();
                $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.view.save).hide();
                $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.view.detail).show();
                toastr.success('数据转移成功!');
            }, function (item) {
                objectData.house.show();
                Alert("失败!" + item);
            });
        },
        appWriteHouse: function (id, callback, callbackError) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouse/appWriteHouse",
                type: "POST",
                data: {caseHouseId: id},
                dataType: "json",
                success: function (result) {
                    if (result.ret && callback) {
                        callback(result.data);
                    }
                    if (!result.ret && callbackError) {
                        callbackError("未获取到数据!");
                    }
                },
                error: function (result) {
                    if (!result.ret && callbackError) {
                        callbackError(result.errmsg);
                    }
                }
            });
        }
    };


    //删除 楼盘 楼盘 单元 房屋 下 子类的临时数据!
    objectData.firstRemove = {
        houseFirst: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouse/initHouse",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        // toastr.success('房屋删除临时数据!');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        estateFirst: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicEstate/initEstate",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        // toastr.success('楼盘删除临时数据!');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        buildFirst: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicBuilding/initBuilding",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        // toastr.success('楼栋删除临时数据!');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        unitFirst: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicUnit/initUnit",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        // toastr.success('单元删除临时数据!');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        init: function () {
            if (objectData.flag) {
                objectData.flag = false;
                objectData.firstRemove.buildFirst();
                objectData.firstRemove.unitFirst();
                objectData.firstRemove.estateFirst();
                objectData.firstRemove.houseFirst();
            }
        }
    }

    $(function () {
        objectData.firstRemove.init();
    });

    /**
     * 校验
     **/
    objectData.valid = function () {
        var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
        var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
        var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
        var basicEstate = formParams(objectData.config.basicEstate.frm);
        var basicUnit = formParams(objectData.config.basicUnit.frm);
        var basicHouse = formParams(objectData.config.basicHouse.frm);
        var basicTrading = formParams(objectData.config.basicHouse.tradingFrm);
        var forms = $("#" + objectData.config.id).find("form");
        $.each(forms, function (i, n) {

        });
        var basicBuildings = new Array();
        for (var i = 1; i <= 4; i++) {
            if (objectData.isNotBlankObjectProperty(navButtonBuild.getObjArray(i))) {
                basicBuildings.unshift(navButtonBuild.getObjArray(i))
            }
        }
        if (objectData.isNotBlankObjectProperty(basicEstate)){//楼盘检测到有数据
            // if (!$("#" + objectData.config.basicEstate.frm).valid()) {
            //     toastr.success('楼盘有必须的数据未填写!');
            //     return false;
            // }
        }
        //确定收集过楼栋信息
        var num = 1;
        $.each(basicBuildings, function (i, obj) {
            if (objectData.isNotBlankObjectProperty(obj)) {
                num++;
            }
        });
        if (num > 1) {//楼栋检测到有数据  ==> 选择了楼盘或者说楼盘添加了楼盘数据的情况下验证通过
            if (objectData.isNotBlank(estateId) || objectData.isNotBlankObjectProperty(basicEstate)) {
                var identifier = $("#identifier").val();
                if (!objectData.isNotBlank(identifier)){
                    Alert("楼栋编号 (必要的查询下面楼栋所需)");
                    return false;
                }
            } else {
                Alert("未选择楼盘或者是没添加新的楼盘数据!");
                return false;
            }
        }
        if (objectData.isNotBlankObjectProperty(basicUnit)) {//单元检测到有数据 ==> 选择了楼栋 或者说是添加了楼栋数据的情况下才进行赋值
            if (objectData.isNotBlank(buildingId) || objectData.isNotBlank(num > 1)) {
                if (!$("#" + objectData.config.basicUnit.frm).valid()) {
                    toastr.success('单元有必须的数据未填写!');
                    return false;
                }
            } else {
                Alert("未选择楼栋或者是没添加新的楼栋数据!");
                return false;
            }
        }
        if (objectData.isNotBlankObjectProperty(basicHouse)) {//检测到 房屋有数据 ==> 选择了单元 或者说是单元数据
            if (objectData.isNotBlank(unitId) || objectData.isNotBlankObjectProperty(basicUnit)) {
                if (!$("#" + objectData.config.basicHouse.frm).valid()) {
                    toastr.success('房屋有必须的数据未填写!');
                    return false;
                }
            } else {
                Alert("未选择单元或者是没添加新的单元数据!");
                return false;
            }
        }
        if (num <= 1 && !objectData.isNotBlankObjectProperty(basicEstate) && !objectData.isNotBlankObjectProperty(basicUnit) && !objectData.isNotBlankObjectProperty(basicHouse)) {
            Alert("未添加任何数据!");
            return false;
        }
        return true;
    };

    //收集数据
    objectData.formParams = function () {
        var item = {};
        var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
        var buildingId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").attr("data-id");
        var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
        var basicEstate = formParams(objectData.config.basicEstate.frm);
        var basicEstateLandState = formParams(objectData.config.basicEstate.frmLandState);
        var basicUnit = formParams(objectData.config.basicUnit.frm);
        var basicHouse = formParams(objectData.config.basicHouse.frm);
        var basicTrading = formParams(objectData.config.basicHouse.tradingFrm);

        item.basicEstate = objectData.isNotBlankObjectProperty(basicEstate) ? basicEstate : null;
        item.basicEstateLandState = objectData.isNotBlankObjectProperty(basicEstateLandState) ? basicEstateLandState : null;

        var basicBuildings = new Array();
        for (var i = 1; i <= 4; i++) {
            if (objectData.isNotBlankObjectProperty(navButtonBuild.getObjArray(i))) {
                basicBuildings.unshift(navButtonBuild.getObjArray(i))
            }
        }
        //确定收集过楼栋信息
        var num = 1;
        $.each(basicBuildings, function (i, obj) {
            if (objectData.isNotBlankObjectProperty(obj)) {
                num++;
            }
        });

        if (num > 1) {//楼栋检测到有数据  ==> 选择了楼盘或者说楼盘添加了楼盘数据的情况下才进行赋值
            if (objectData.isNotBlank(estateId) || objectData.isNotBlankObjectProperty(basicEstate)) {
                item.basicBuildingMain = {
                    id: $("#caseBuildingMainId").val(),
                    identifier: $("#identifier").val(),
                    estateId: objectData.isNotBlank(estateId) ? estateId : null
                };
                item.basicBuildings = basicBuildings;
            }
        }

        if (objectData.isNotBlankObjectProperty(basicUnit)) {//单元检测到有数据 ==> 选择了楼栋 或者说是添加了楼栋数据的情况下才进行赋值
            if (num <= 1) {
                //说明 楼栋未添加数据,假如楼盘有数据那么 楼栋必须是选择的
                if (objectData.isNotBlank(buildingId) && !objectData.isNotBlank(item.basicBuildings)) {
                    basicUnit.basicBuildingMainId = buildingId;
                }
            }
            //说明 楼栋未添加数据,假如楼盘有数据那么 楼栋必须是选择的
            if (objectData.isNotBlank(buildingId) || objectData.isNotBlank(basicBuildings)) {
                item.basicUnit = objectData.isNotBlankObjectProperty(basicUnit) ? basicUnit : null;
            }
        }

        if (objectData.isNotBlankObjectProperty(basicHouse)) {//房屋检测到有数据 ==? 选择了单元或者添加了单元信息
            if (objectData.isNotBlank(unitId) || objectData.isNotBlank(item.basicUnit)) {
                basicHouse.unitId = unitId;
                item.basicHouse = objectData.isNotBlankObjectProperty(basicHouse) ? basicHouse : null;
                item.basicTrading = objectData.isNotBlankObjectProperty(basicTrading) ? basicTrading : null;
            }
        }

        return item;
    };

    /**
     * 成功申请后清除数据
     */
    objectData.successClear = function () {
        window.location.reload();

        // $("#" + objectData.config.basicEstate.frmLandState).clearAll();
        // $("#" + objectData.config.basicEstate.frm).clearAll();
        // $("#" + objectData.config.basicUnit.frm).clearAll();
        // $("#" + objectData.config.basicHouse.frm).clearAll();
        // $("#" + objectData.config.basicHouse.tradingFrm).clearAll();
        // objectData.firstRemove.buildFirst();
        // objectData.firstRemove.unitFirst();
        // objectData.firstRemove.estateFirst();
        // objectData.firstRemove.houseFirst();
        // $('#caseTab a').tab('show');
        // for (var i = 1; i <= 4; i++) {
        //     navButtonBuild.setObjArrayElement(i,{});
        //     $("#profile-tab"+i).removeAttr("data-toggle");
        // }
    };

</script>

<script>

    //提交
    function submit() {
        if (!objectData.valid()) {
            return false;
        }
        var data = objectData.formParams();
        var formData = JSON.stringify(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/basicApplySubmit",
            type: "post",
            dataType: "json",
            async: false,
            data: {formData: formData},
            success: function (result) {
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        objectData.successClear();
                    });
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
</script>