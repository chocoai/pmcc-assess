<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
            <div class="x_panel" ${basicApply.id eq 0?'':'style="display:none"'}>
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
                        <input type="hidden" name="estatePartInMode" value="${basicApply.estatePartInMode}">
                        <input type="hidden" name="buildingPartInMode" value="${basicApply.buildingPartInMode}">
                        <input type="hidden" name="unitPartInMode" value="${basicApply.unitPartInMode}">
                        <input type="hidden" name="housePartInMode" value="${basicApply.housePartInMode}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-1">
                                    <select name="province" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-1">
                                    <select name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="applyFormType0" name="type" value="0">
                                <label for="applyFormType0">非工业与仓储</label>
                            </span>

                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="applyFormType1" name="type" value="1">
                                <label for="applyFormType1">工业与仓储</label>
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
                                           value="${basicApply.estateName}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-success" data-mode="add"
                                           onclick="estateCommon.add(this,basicCommon.showEstateTab);"
                                           value="添加">
                                    <input type="button" class="btn btn-warning btn-reference" data-mode="reference"
                                           style="display: none;"
                                           onclick="estateCommon.upgrade(this,basicCommon.showEstateTab);"
                                           value="引用">
                                    <input type="button" class="btn btn-warning btn-upgrade" data-mode="upgrade"
                                           style="display: none;"
                                           onclick="estateCommon.upgrade(this,basicCommon.showEstateTab);"
                                           value="升版本">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="hidden" name="caseBuildingId"
                                           value="${basicApply.caseBuildingId}">
                                    <input type="text" class="form-control" name="buildingNumber" placeholder="楼栋编号"
                                           value="${basicApply.buildingNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-success" data-mode="add"
                                           onclick="buildingCommon.add(this,basicCommon.showBuildingTab);"
                                           value="添加">
                                    <input type="button" class="btn btn-warning btn-reference" data-mode="reference"
                                           style="display: none;"
                                           onclick="buildingCommon.upgrade(this,basicCommon.showBuildingTab);"
                                           value="引用">
                                    <input type="button" class="btn btn-warning btn-upgrade" data-mode="upgrade"
                                           style="display: none;"
                                           onclick="buildingCommon.upgrade(this,basicCommon.showBuildingTab);"
                                           value="升版本">
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
                                           value="${basicApply.unitNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-success" data-mode="add"
                                           onclick="unitCommon.add(this,basicCommon.showUnitTab);"
                                           value="添加">
                                    <input type="button" class="btn btn-warning btn-reference" data-mode="reference"
                                           style="display: none;"
                                           onclick="unitCommon.upgrade(this,basicCommon.showUnitTab);"
                                           value="引用">
                                    <input type="button" class="btn btn-warning btn-upgrade" data-mode="upgrade"
                                           style="display: none;"
                                           onclick="unitCommon.upgrade(this,basicCommon.showUnitTab);"
                                           value="升版本">
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
                                           value="${basicApply.houseNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <input type="button" class="btn btn-success" data-mode="add"
                                           value="添加"
                                           onclick="houseCommon.add(this,basicCommon.showHouseTab);">
                                    <input type="button" class="btn btn-warning btn-reference" data-mode="reference"
                                           style="display: none;"
                                           value="引用"
                                           onclick="houseCommon.upgrade(this,basicCommon.showHouseTab);">
                                    <input type="button" class="btn btn-warning btn-upgrade" data-mode="upgrade"
                                           style="display: none;"
                                           value="升版本"
                                           onclick="houseCommon.upgrade(this,basicCommon.showHouseTab);">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%@include file="/views/basic/basicIndexCommon.jsp" %>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">
    var basicApplyIndex = new Object();

    /**
     * 楼盘 信息自动补全
     */
    basicApplyIndex.autocompleteEstate = function () {
        var that = basicCommon.basicApplyForm.find('[name=estateName]');
        var group = $(that).closest('.form-group');
        var defaults = AssessDefault.autocomplete();
        defaults.source = function (request, response) {
            group.find('[name=caseEstateId]').val('');
            group.find('.btn-reference,.btn-upgrade').hide();
            basicApplyIndex.clearBuilding();
            basicApplyIndex.clearUnit();
            basicApplyIndex.clearHouse();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseEstate/autoCompleteCaseEstate",
                type: "get",
                dataType: "json",
                data: {
                    offset: 1,
                    limit: 10,
                    province: basicCommon.basicApplyForm.find('[name=province]').val(),
                    city: basicCommon.basicApplyForm.find('[name=city]').val(),
                    name: $(that).val()
                },
                success: function (result) {
                    if (result.ret) {
                        var responseArray = [];
                        $.each(result.data, function (i, item) {
                            responseArray.push({
                                label: item.name,
                                type: item.type,
                                id: item.id
                            });
                        })
                        response(responseArray);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
        defaults.select = function (event, ele) {
            group.find('[name=caseEstateId]').val(ele.item.id);
            group.find('.btn-reference,.btn-upgrade').show();

            //处理auto相同值不触发问题
            basicCommon.basicApplyForm.find('[name=buildingNumber]').autocomplete("destroy");
            basicApplyIndex.autocompleteBuilding();
            //选择楼盘的类型
            basicCommon.basicApplyForm.find('[name=type][value=' + ele.item.type + ']').trigger('click');
        }
        $(that).autocomplete(defaults);
    };

    /**
     * 楼栋 信息自动补全
     */
    basicApplyIndex.autocompleteBuilding = function () {
        var that = basicCommon.basicApplyForm.find('[name=buildingNumber]');
        var group = $(that).closest('.form-group');
        var defaults = AssessDefault.autocomplete();
        defaults.source = function (request, response) {
            group.find('[name=caseBuildingId]').val('');
            group.find('.btn-reference,.btn-upgrade').hide();
            basicApplyIndex.clearUnit();
            basicApplyIndex.clearHouse();
            var estateId = basicCommon.basicApplyForm.find("input[name='caseEstateId']").val();
            if (!estateId) return;
            var itemVal = $(that).val();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseBuilding/autoCompleteCaseBuilding",
                type: "get",
                dataType: "json",
                data: {
                    offset: 1,
                    limit: 10,
                    buildingNumber: itemVal,
                    estateId: estateId
                },
                success: function (result) {
                    if (result.ret) {
                        var responseArray = [];
                        $.each(result.data, function (i, item) {
                            responseArray.push({
                                label: item.name,
                                type: item.type,
                                id: item.id
                            });
                        })
                        response(responseArray);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
        defaults.select = function (event, ele) {
            group.find('[name=caseBuildingId]').val(ele.item.id);
            group.find('.btn-reference,.btn-upgrade').show();

            //处理auto相同值不触发问题
            basicCommon.basicApplyForm.find('[name=unitNumber]').autocomplete("destroy");
            basicApplyIndex.autocompleteUnit();
        }
        defaults.minLength = 0;
        $(that).autocomplete(defaults);
    };

    /**
     * 单元信息自动补全
     */
    basicApplyIndex.autocompleteUnit = function () {
        var that = basicCommon.basicApplyForm.find('[name=unitNumber]');
        var group = $(that).closest('.form-group');
        var defaults = AssessDefault.autocomplete();
        defaults.source = function (request, response) {
            group.find('[name=caseUnitId]').val('');
            group.find('.btn-reference,.btn-upgrade').hide();
            basicApplyIndex.clearHouse();
            var buildingId = basicCommon.basicApplyForm.find("input[name='caseBuildingId']").val();
            if (!buildingId) return;
            var itemVal = $(that).val();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseUnit/autoCompleteCaseUnit",
                type: "get",
                dataType: "json",
                data: {
                    offset: 1,
                    limit: 10,
                    unitNumber: itemVal,
                    caseBuildingId: buildingId
                },
                success: function (result) {
                    if (result.ret) {
                        var responseArray = [];
                        $.each(result.data, function (i, item) {
                            responseArray.push({
                                label: item.name,
                                type: item.type,
                                id: item.id
                            });
                        })
                        response(responseArray);
                    } else {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            });
        }
        defaults.select = function (event, ele) {
            group.find('[name=caseUnitId]').val(ele.item.id);
            group.find('.btn-reference,.btn-upgrade').show();

            //处理auto相同值不触发问题
            basicCommon.basicApplyForm.find('[name=houseNumber]').autocomplete("destroy");
            basicApplyIndex.autocompleteHouse();
        }
        defaults.minLength = 0;
        $(that).autocomplete(defaults);
    };

    /**
     * 房屋信息自动补全
     */
    basicApplyIndex.autocompleteHouse = function () {
        houseCommon.houseForm.find('input[name=practicalUse]').apPracticalUse({
            onSelect:function (id, name) {
                houseCommon.houseForm.find('input[name=practicalUseId]').val(id);
                houseCommon.houseForm.find('input[name=practicalUse]').val(name);
            }
        });
        var that = basicCommon.basicApplyForm.find('[name=houseNumber]');
        var group = $(that).closest('.form-group');
        var defaults = AssessDefault.autocomplete();
        defaults.source = function (request, response) {
            group.find('[name=caseHouseId]').val('');
            group.find('.btn-reference,.btn-upgrade').hide();
            var unitId = basicCommon.basicApplyForm.find("input[name='caseUnitId']").val();
            if (!unitId) return;
            var itemVal = $(that).val();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseHouse/autoCompleteCaseHouse",
                type: "get",
                dataType: "json",
                data: {
                    offset: 1,
                    limit: 10,
                    houseNumber: itemVal,
                    unitId: unitId
                },
                success: function (result) {
                    if (result.ret) {
                        var responseArray = [];
                        $.each(result.data, function (i, item) {
                            responseArray.push({
                                label: item.name,
                                type: item.type,
                                id: item.id
                            });
                        })
                        response(responseArray);
                    } else {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            });
        }
        defaults.select = function (event, ele) {
            group.find('[name=caseHouseId]').val(ele.item.id);
            group.find('.btn-reference,.btn-upgrade').show();
        }
        defaults.minLength = 0;
        $(that).autocomplete(defaults);
    };

    //清空楼盘查询数据
    basicApplyIndex.clearEstate = function () {
        basicCommon.basicApplyForm.find('[name=estatePartInMode]').val('');
        basicCommon.basicApplyForm.find('[name=caseEstateId]').val('');
        var estateName = basicCommon.basicApplyForm.find('[name=estateName]');
        estateName.val('');
        estateName.closest('.form-group').find('.btn-reference,.btn-upgrade').hide();
    }

    //清空楼栋查询数据
    basicApplyIndex.clearBuilding = function () {
        basicCommon.basicApplyForm.find('[name=buildingPartInMode]').val('');
        basicCommon.basicApplyForm.find('[name=caseBuildingId]').val('');
        var buildingNumber = basicCommon.basicApplyForm.find('[name=buildingNumber]');
        buildingNumber.val('');
        buildingNumber.closest('.form-group').find('.btn-reference,.btn-upgrade').hide();
    }

    //清空单元查询数据
    basicApplyIndex.clearUnit = function () {
        basicCommon.basicApplyForm.find('[name=unitPartInMode]').val('');
        basicCommon.basicApplyForm.find('[name=caseUnitId]').val('');
        var unitNumber = basicCommon.basicApplyForm.find('[name=unitNumber]');
        unitNumber.val('');
        unitNumber.closest('.form-group').find('.btn-reference,.btn-upgrade').hide();
    }

    //清空房屋查询数据
    basicApplyIndex.clearHouse = function () {
        basicCommon.basicApplyForm.find('[name=housePartInMode]').val('');
        basicCommon.basicApplyForm.find('[name=caseHouseId]').val('');
        var houseNumber = basicCommon.basicApplyForm.find('[name=houseNumber]');
        houseNumber.val('');
        houseNumber.closest('.form-group').find('.btn-reference,.btn-upgrade').hide();
    }

    //检测是否为 草稿重新申请
    basicApplyIndex.startApply = function () {
        if ('${basicApply.id}' != '0') {
            //初始楼盘信息
            if ('${basicApply.estatePartInMode}') {
                estateCommon.init('${basicApply.id}', basicCommon.showEstateTab);
            }
            //初始楼栋信息
            if ('${basicApply.buildingPartInMode}') {
                buildingCommon.init('${basicApply.id}', basicCommon.showBuildingTab);
            }
            //初始单元信息
            if ('${basicApply.unitPartInMode}') {
                unitCommon.init('${basicApply.id}', basicCommon.showUnitTab);
            }
            //初始房屋信息
            if ('${basicApply.housePartInMode}') {
                houseCommon.init('${basicApply.id}', basicCommon.showHouseTab);
            }
            basicCommon.basicApplyForm.find('[id=applyFormType${basicApply.type}]').trigger('click');
        } else {
            basicCommon.basicApplyForm.find('[name=type]:eq(0)').trigger('click');
            basicApplyIndex.autocompleteEstate();
            basicApplyIndex.autocompleteBuilding();
            basicApplyIndex.autocompleteUnit();
            basicApplyIndex.autocompleteHouse();
        }
    };


    $(function () {
        basicApplyIndex.startApply();
        //定位成功回调方法
        mapPosition.getCurrentCity(function (province, city) {
            AssessCommon.initAreaInfo({
                provinceTarget: basicCommon.basicApplyForm.find('[name=province]'),
                cityTarget: basicCommon.basicApplyForm.find('[name=city]'),
                provinceDefaultText: province,
                cityDefaultText: city
            });
        });
    });

</script>

<script type="text/javascript">

    //提交
    function submit() {
        if (!basicCommon.submitFormValid()) {
            return false;
        }
        Loading.progressShow();
        var formData = JSON.stringify(basicCommon.getFormData());
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
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    //保存草稿
    function saveDraft() {
        if (!basicCommon.saveDraftValid()) {
            return false;
        }
        Loading.progressShow();
        var formData = JSON.stringify(basicCommon.getFormData());
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
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    //打印页
    function printedPage() {
        var applyForm = formSerializeArray(basicCommon.basicApplyForm);
        if (!applyForm.housePartInMode) {
            Alert("房屋信息未参与到申请中不允许打印");
            return false;
        }
        Loading.progressShow();
        var data = {};
        data.basicHouse = formSerializeArray(houseCommon.houseForm);
        data.basicDamagedDegree = damagedDegree.getFormData();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicHousePrint/saveData",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    window.open('${pageContext.request.contextPath}/basicHousePrint/printedPage?basicApplyId=' + basicCommon.getApplyId() + "&houseId=" + houseCommon.getHouseId());
                }
                else {
                    Alert("操作失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

</script>