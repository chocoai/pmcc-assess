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
                        <div class="form-group" id="industry">
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" name="industry" value="1" checked="checked">
                                <label>非工业与仓储</label>
                            </span>

                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" name="industry" value="2">
                                <label>工业与仓储</label>
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
                                    <input type="button" class="btn btn-success"
                                           onclick="objectData.building.show({},{})"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="objectData.building.edit()"
                                           value="修改">
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
                        </div>
                    </form>
                </div>
            </div>

            <%@include file="/views/basic/basicIndexCommon.jsp" %>

            <div class="x_panel">

                <div class="x_content">
                    <div class="col-sm-5 col-sm-offset-5">
                        <button class="btn btn-success" onclick="temporary();">
                            保存草稿<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
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
    //注入id
    basicIndexCommon.setAppId(objectData.config.id);

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
            //跳过下面四个input
            if (key != 'name' && key != 'houseNumber' && key != 'buildingNumber' && key != 'unitNumber') {
                if (objectData.isNotBlank(obj[key])) {
                    return true;
                }
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
            $("#" + frm).find("select[name='" + name + "']").val(data).trigger("change");
            $("#" + frm).find("select[name='" + name + "']").val(data);
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
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
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
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
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
        //显示 楼盘页面
        show: function (itemA, itemB) {
            basicIndexCommon.estateShow();
            if (objectData.estateFlag) {
                basicIndexCommon.estateInit(itemA);
                basicIndexCommon.estateLandStateInit(itemB);
                objectData.estateFlag = false;
            }
            $("#" + objectData.config.basicEstate.frm).initForm({name: $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").val()});
            basicIndexCommon.estateLoadList();
        },
        //编辑楼盘页面
        edit: function () {
            var estateId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicEstate.key + "']").attr("data-id");
            if (!objectData.isNotBlank(estateId)) {
                Alert("请查询楼盘!");
                return false;
            }
            $("#" + objectData.config.basicEstate.frm).clearAll();
            $("#" + objectData.config.basicEstate.frmLandState).clearAll();
            objectData.firstRemove.estateFirst();

            objectData.estate.appWriteEstate(estateId,
                function (data) {
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
                },
                function (item) {
                    objectData.estate.show({}, {});
                    Alert("失败!" + item);
                });
        },
        //转移数据
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
    objectData.buildingFlag = true;
    objectData.building = {
        show: function (item) {
            if (objectData.buildingFlag) {
                basicIndexCommon.buildingInit(item);
                objectData.buildingFlag = false;
            }
            basicIndexCommon.buildingShow();
            basicIndexCommon.buildMainWrite({identifier: $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").val()});
            $("#" + objectData.config.basicBuilding.frm).initForm({buildingNumber: $("#" + objectData.config.id).find("input[name='" + objectData.config.basicBuilding.key + "']").val()});
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
                        objectData.firstRemove.buildFirst();
                        var caseBuildingMain = result.data.caseBuildingMain;
                        var caseBuildingList = result.data.caseBuildingList;

                        objectData.building.appWriteBuilding(caseBuildingMain.id,
                            function () {
                                toastr.success('数据转移成功!');
                                basicIndexCommon.buildMainWrite(caseBuildingMain);
                                $.each(caseBuildingList, function (i, n) {
                                    if (objectData.isNotBlank(n.part)) {
                                        navButtonBuild.setObjArrayElement(n.part, n);
                                    }
                                });
                                basicIndexCommon.buildingInit({});
                                basicIndexCommon.buildingShow();
                            },
                            function (data) {
                                Alert("失败!" + data);
                            });
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        //数据转移
        appWriteBuilding: function (id, callback, callbackError) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicBuilding/appWriteBuilding",
                type: "post",
                data: {caseMainBuildId: id},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (result.ret && callback) {
                            callback(result.data);
                        }
                        if (!result.ret && callbackError) {
                            callbackError("未获取到数据!");
                        }
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

    //处理单元
    objectData.unit = {
        show: function () {
            basicIndexCommon.unitShow();
            basicIndexCommon.unitLoadList();
            $("#" + objectData.config.basicUnit.frm).initForm({unitNumber: $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").val()});
        },
        edit: function () {
            var unitId = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicUnit.key + "']").attr("data-id");
            if (!objectData.isNotBlank(unitId)) {
                Alert("请查询单元!");
                return false;
            }
            objectData.firstRemove.unitFirst();

            objectData.unit.appWriteUnit(unitId,
                function (data) {
                    toastr.success('数据转移成功!');
                    $("#" + objectData.config.basicUnit.frm).initForm(data);
                    objectData.unit.show();
                },
                function () {
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
            basicIndexCommon.houseShow();
            if (objectData.houseFlag) {
                basicIndexCommon.houseInit({}, {});
                objectData.houseFlag = false;
            }
            basicIndexCommon.houseLoadList();
            $("#" + objectData.config.basicHouse.frm).initForm({houseNumber: $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").val()});
        },
        edit: function () {
            var id = $("#" + objectData.config.id).find("input[name='" + objectData.config.basicHouse.key + "']").attr("data-id");
            if (!objectData.isNotBlank(id)) {
                Alert("请先查询房屋");
                return false;
            }
            objectData.firstRemove.houseFirst();

            objectData.house.appWriteHouse(id,
                function (data) {
                    var CaseHouseTrading = data.CaseHouseTrading;
                    var CaseHouse = data.CaseHouse;
                    if (!objectData.isNotBlank(CaseHouseTrading)) {
                        CaseHouseTrading = {};
                    }
                    if (!objectData.isNotBlank(CaseHouse)) {
                        CaseHouse = {};
                    }
                    objectData.house.show();
                    basicIndexCommon.houseInit(CaseHouse, CaseHouseTrading);
                    toastr.success('数据转移成功!');
                },
                function (item) {
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
                async: false,
                dataType: "json",
                success: function (result) {
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
                async: false,
                success: function (result) {
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
                async: false,
                success: function (result) {
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
                async: false,
                dataType: "json",
                success: function (result) {
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };


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
        if (objectData.isNotBlankObjectProperty(basicEstate)) {//楼盘检测到有数据

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
                if (!objectData.isNotBlank(identifier)) {
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
        item.industry = $("#" + industry.config.id).find(":radio:checked").val();
        return item;
    };

    /**
     * 成功申请后清除数据
     */
    objectData.successClear = function () {
        window.location.reload();
    };

    //检测是否为 草稿重新申请
    objectData.startApply = function () {
        if (this.isNotBlank("${startApply}")){
            objectData.estate.show({},{});
            objectData.building.show({});
            objectData.house.show({},{});
            objectData.unit.show({},{});

        }
    };


    $(function () {
        objectData.startApply();
    });
</script>

<script>

    //提交
    function submit() {
        if (!objectData.valid()) {
            return false;
        }
        var data = objectData.formParams();
        var formData = JSON.stringify(data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/basicApplySubmit",
            type: "post",
            dataType: "json",
            async: false,
            data: {formData: formData},
            success: function (result) {
                Loading.progressHide();
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

    //保存草稿
    function temporary() {
        if (!objectData.valid()) {
            return false;
        }
        var data = objectData.formParams();
        var formData = JSON.stringify(data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/temporary",
            type: "post",
            dataType: "json",
            async: false,
            data: {formData: formData},
            success: function (result) {
                Loading.progressHide();
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