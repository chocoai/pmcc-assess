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
        <div class="right_col" id="basicApplyId" role="main" style="margin-left: 0">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        案例申请
                    </h2>
                </div>
            </div>
            <div class="x_panel" style="display: ${basicApply.id eq 0?'block':'none'}">
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
                    <form id="basicApplyFrm" class="form-horizontal">
                        <input type="hidden" name="id" value="${basicApply.id}">
                        <div class="form-group" id="industry">
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="type0" name="type" value="0">
                                <label for="type0">非工业与仓储</label>
                            </span>

                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="type1" name="type" value="1">
                                <label for="type1">工业与仓储</label>
                            </span>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-4">
                                    <input type="hidden" name="caseEstateId" value="${basicApply.caseEstateId}">
                                    <input type="text" class="form-control" name="estateName" placeholder="楼盘名称"
                                           value="${basicApply.estateName}"
                                           onkeydown="objectData.autocompleteEstate(this);">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="estateCommon.add($(this).closest('form'),basicIndexCommon.showEstateTab());"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="estateCommon.edit($(this).closest('form'),basicIndexCommon.showEstateTab());"
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
                                    <input type="hidden" name="caseBuildingMainId"
                                           value="${basicApply.caseBuildingMainId}">
                                    <input type="text" class="form-control" name="buildingNumber" placeholder="楼栋编号"
                                           value="${basicApply.buildingNumber}"
                                           onkeydown="objectData.autocompleteBuilding(this)">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="buildingCommon.add($(this).closest('form'),basicIndexCommon.showBuildingTab);" value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="buildingCommon.edit($(this).closest('form'),basicIndexCommon.showBuildingTab);" value="修改">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单元编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
                                    <input type="text" class="form-control" name="unitNumber" placeholder="单元编号"
                                           value="${basicApply.unitNumber}"
                                           onkeydown="objectData.autocompleteUnit(this);">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="unitCommon.add($(this).closest('form'),basicIndexCommon.showUnitTab);"
                                           value="添加">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" onclick="unitCommon.add($(this).closest('form'),basicIndexCommon.showUnitTab);"
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
                                    <input type="hidden" name="caseHouseId" value="${basicApply.caseHouseId}">
                                    <input type="text" class="form-control" name="houseNumber" placeholder="房屋编号"
                                           value="${basicApply.houseNumber}"
                                           onkeydown="objectData.autocompleteHouse(this)">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" value="添加"
                                           onclick="houseCommon.add($(this).closest('form'),basicIndexCommon.showHouseTab);">
                                </div>
                            </div>
                            <div class="x-valid" style="display: none;">
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-success" value="修改"
                                           onclick="houseCommon.add($(this).closest('form'),basicIndexCommon.showHouseTab);">
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
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-warning" onclick="saveDraft();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
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
    objectData.config = {
        id: "basicApplyId",
        basicApply: {
            frm: "basicApplyFrm"
        },
        basicEstate: {
            key: "estateName",
            name: "楼盘",
            frm: "basicEstateFrm",
            frmLandState: "basicLandState"
        },
        basicBuilding: {
            key: "buildingNumber",
            name: "楼栋",
            frm: "basicBuildingFrm",
            mainFrm: "basicBuildingMainFrm"
        },
        basicUnit: {
            key: "unitNumber",
            name: "单元",
            frm: "basicUnitFrm"
        },
        basicHouse: {
            key: "houseNumber",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm"
        }
    };

    /**
     * 楼盘 信息自动补全
     */
    objectData.autocompleteEstate = function (_this) {
        var that = _this;
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        $(that).autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $(that).val();
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
                minLength: 2,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    childs.eq(1).hide();
                    childs.eq(2).show();
                    $(that).closest('div').find('[name=caseEstateId]').val(ele.item.key);
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
        var that = _this;
        var estateId = $("#" + objectData.config.basicApply.frm).find("input[name='caseEstateId']").val();
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        $(that).autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $(that).val();
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
                    $(that).closest('div').find('[name=caseBuildingMainId]').val(ele.item.key);
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
        var that = _this;
        var buildingId = $("#" + objectData.config.basicApply.frm).find("input[name='caseBuildingMainId']").val();
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        $(that).autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $(that).val();
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
                    $(that).closest('div').find('[name=caseUnitId]').val(ele.item.key);
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
        var that = _this;
        var unitId = $("#" + objectData.config.basicApply.frm).find("input[name='caseUnitId']").val();
        var childs = $(_this).closest('.form-group').children();
        childs.eq(1).show();
        childs.eq(2).hide();
        $(that).autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $(that).val();
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
                    $(that).closest('div').find('[name=caseHouseId]').val(ele.item.key);
                },
                focus: function (event, ui) {
                }
            }
        );
    };


    //收集数据
    objectData.formParams = function () {
        var item = {};
        var basicApply = formParams(objectData.config.basicApply.frm);
        basicApply.estatePartInFlag = basicIndexCommon.partInFlag.estate;
        basicApply.buildingPartInFlag = basicIndexCommon.partInFlag.building;
        basicApply.unitPartInFlag = basicIndexCommon.partInFlag.unit;
        basicApply.housePartInFlag = basicIndexCommon.partInFlag.house;
        item.basicApply = basicApply;
        if (basicApply.estatePartInFlag) {
            item.basicEstate = formParams(objectData.config.basicEstate.frm);
            item.basicEstateLandState = formParams(objectData.config.basicEstate.frmLandState);
        }
        if (basicApply.buildingPartInFlag) {
            item.basicBuildingMain = formParams(objectData.config.basicBuilding.mainFrm);
            item.basicBuilding = formParams(objectData.config.basicBuilding.frm);
        }
        if (basicApply.unitPartInFlag) {
            item.basicUnit = formParams(objectData.config.basicUnit.frm);
        }
        if (basicApply.housePartInFlag) {
            item.basicHouse = formParams(objectData.config.basicHouse.frm);
            item.basicTrading = formParams(objectData.config.basicHouse.tradingFrm);
        }
        item.industry = $("#" + industry.config.id).find(":radio:checked").val();
        return item;
    };

    //检测是否为 草稿重新申请
    objectData.startApply = function () {
        if ('${basicApply.id}' != '0') {
            //初始楼盘信息
            if ('${basicApply.estatePartInFlag}' == 'true') {
                estateCommon.init('${basicApply.id}',basicIndexCommon.showEstateTab);
            }
            //初始楼栋信息
            if ('${basicApply.buildingPartInFlag}' == 'true') {
                buildingCommon.init('${basicApply.id}',basicIndexCommon.showBuildingTab);
            }
            //初始单元信息
            if ('${basicApply.unitPartInFlag}' == 'true') {
                unitCommon.init('${basicApply.id}',basicIndexCommon.showUnitTab);
            }
            //初始房屋信息
            if ('${basicApply.housePartInFlag}' == 'true') {
                houseCommon.init('${basicApply.id}',basicIndexCommon.showHouseTab);
            }
            $("#" + objectData.config.basicApply.frm).find('[id=type${basicApply.type}]').trigger('click');
            $("#" + objectData.config.basicApply.frm).find('input').attr('readonly', 'readonly');
        } else {
            $("#" + objectData.config.basicApply.frm).find('[name=type]:eq(0)').trigger('click');
        }
    };


    $(function () {
        objectData.startApply();
    });
</script>

<script type="text/javascript">

    //提交
    function submit() {
        Loading.progressShow();
        var data = objectData.formParams();
        var formData = JSON.stringify(data);
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
                        window.close();
                    });
                }else{
                    Alert("提交数据失败!");
                }
            }
        });
    }

    //保存草稿
    function saveDraft() {
        Loading.progressShow();
        var data = objectData.formParams();
        var formData = JSON.stringify(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {formData: formData},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存草稿成功!", 1, null, function () {
                        window.close();
                    });
                }else{
                    Alert("保存草稿失败!");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
</script>