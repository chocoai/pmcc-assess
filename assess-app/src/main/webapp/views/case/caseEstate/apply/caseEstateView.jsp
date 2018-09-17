<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/11
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">
                <%--<div class="x_title collapse-link">--%>
                <%--<ul class="nav navbar-right panel_toolbox">--%>
                <%--<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>--%>
                <%--</ul>--%>
                <%--<h2>--%>
                <%--楼盘--%>
                <%--</h2>--%>
                <%--<div class="clearfix"></div>--%>
                <%--</div>--%>
                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘基本信息 <label class="btn btn-primary" onclick="caseEstate.other.showBoxDiv();">其它信息</label>
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_estate">
                        <input type="hidden" name="id" value="${caseEstate.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                                           name="name" class="form-control" value="${caseEstate.name}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select name="blockId" class="form-control search-select select2 blockId"
                                            required="required">
                                        <option value="" name="blockId">-请选择-</option>
                                        <c:forEach items="${dataBlocks}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘方位<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="楼盘方位" required
                                           name="position" class="form-control" value="${caseEstate.position}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省
                                    <span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select name="province" id="province"
                                            class="form-control search-select select2"
                                            required="required">
                                        <option value="" name="province">-请选择-</option>
                                        <c:forEach items="${ProvinceList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.areaId == projectInfo.province}">
                                                    <option value="${item.areaId}"
                                                            selected="selected">${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.areaId}">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="district" name="district" class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">编号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="编号(请输入数字)" name="number" class="form-control"
                                           required="required" value="${caseEstate.number}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landLevel" name="landLevel"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">街道</label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="街道" readonly="readonly"
                                           name="street" class="form-control" value="${caseEstate.street}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">占地面积<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="占地面积(请输入数字)" name="coverAnArea"
                                           required="required" class="form-control" value="${caseEstate.coverAnArea}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">容积率<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="容积率(请输入数字)" value="${caseEstate.volumetricRate}"
                                           name="volumetricRate" required="required" class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">绿化率<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="绿化率(请输入数字)" required="required"
                                           value="${caseEstate.greeningRate}"
                                           name="greeningRate" class="form-control">
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘概况<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" required="required" name="description"
                                              placeholder="楼盘概况">${caseEstate.description}</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼栋数</label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="楼栋数(请输入数字)" required="required" name="buildingNumber"
                                           class="form-control" value="${caseEstate.buildingNumber}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">开发商<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 developerId" name="developerId"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="建筑面积(请输入数字)" name="floorArea" required="required"
                                           class="form-control" value="${caseEstate.floorArea}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">均价<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="均价(请输入数字)" name="averagePrice" required="required"
                                           class="form-control" value="${caseEstate.averagePrice}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价格区间<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="价格区间"
                                           name="priceRange" required="required" class="form-control"
                                           value="${caseEstate.priceRange}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="附号(请输入数字)" value="${caseEstate.attachNumber}"
                                           name="attachNumber" required="required" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">总平面图</label>
                                <div class="col-sm-5">
                                    <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control"
                                           type="file">
                                    <div id="_estate_floor_total_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外观图</label>
                                <div class="col-sm-5">
                                    <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control"
                                           type="file">
                                    <div id="_estate_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供水平面图</label>
                                <div class="col-sm-5">
                                    <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_water_supply_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供电平面图</label>
                                <div class="col-sm-5">
                                    <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_power_supply_plan"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供气平面图</label>
                                <div class="col-sm-5">
                                    <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_air_supply_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">采暖平面图</label>
                                <div class="col-sm-5">
                                    <input id="heating_plan" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_heating_plan"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘土地实体情况 </h3>
                        <div class="clearfix"></div>
                    </div>

                    <form id="frm_estateLandState" class="form-horizontal">
                        <input type="hidden" name="id" value="${caseEstateLandState.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input type="text" class="form-control" required="required" name="name"
                                           placeholder="名称" value="${caseEstateLandState.soil}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类型<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landUseType" name="landUseType"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类别<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landUseCategory"
                                            name="landUseCategory" required="required">
                                        <option>请先选择土地用途类型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landLevel" name="landLevel"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="东至" required="required"
                                           name="eastTo" value="${caseEstateLandState.eastTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="南至" required="required"
                                           name="southTo" value="${caseEstateLandState.southTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="西至" required="required"
                                           name="westTo" value="${caseEstateLandState.westTo}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="北至" required="required"
                                           name="northTo" value="${caseEstateLandState.northTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地形状状况<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地形状状况" required="required"
                                           name="shapeState" value="${caseEstateLandState.shapeState}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地平整度" required="required"
                                           name="planeness" value="${caseEstateLandState.planeness}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地开发程度<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地开发程度" required="required"
                                           name="developmentDegree" value="${caseEstateLandState.developmentDegree}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">开发限制条件<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="开发限制条件" required="required"
                                           name="restrictiveCondition"
                                           value="${caseEstateLandState.restrictiveCondition}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土壤" required="required"
                                           name="soil" value="${caseEstateLandState.soil}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control"
                                           value="${caseEstateLandState.topographicTerrain}" placeholder="地形地势"
                                           required="required" name="topographicTerrain">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" data-rule-number='true'
                                           placeholder="土地面积(请输入数字)" required="required" name="landArea"
                                           value="${caseEstateLandState.landArea}">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 网络通信 -->
            <div class="network" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseNetwork.jsp" %>
            </div>

            <!-- 车位信息 -->
            <div class="parking" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseParking.jsp" %>
            </div>

            <!-- 供气 -->
            <div class="supplygas" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseSupplyGas.jsp" %>
            </div>

            <!-- 供热 -->
            <div class="supplyheating" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseSupplyHeating.jsp" %>
            </div>

            <!-- 供电 -->
            <div class="supplypower" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseSupplyPower.jsp" %>
            </div>

            <!-- 供水 -->
            <div class="supplywater" style="display: none;">
                <%@include file="/views/case/caseEstate/apply/caseSupplyWater.jsp" %>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="caseEstate.submit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var CaseEstateFun = function () {

    }

    CaseEstateFun.prototype.config = {
        estate: {
            frm: function () {
                return "frm_estate";//楼盘基本信息frm
            },
            filePlanTotal: function () {
                return "estate_floor_total_plan";//总平面图id和字段
            },
            waterSupplyPlan: function () {
                return "water_supply_plan";//供水平面图id和字段
            },
            powerSupplyPlan: function () {
                return "power_supply_plan";//供电平面图id和字段
            },
            airSupplyPlan: function () {
                return "air_supply_plan";//供气平面图id和字段
            },
            heatingPlan: function () {
                return "heating_plan";//采暖平面图id和字段
            },
            fileAppearance: function () {
                return "estate_floor_Appearance_figure";//外观图id和字段
            }
        },
        landState: {
            frm: function () {
                return "frm_estateLandState";//土地实体情况
            }
        },
        other: {
            box: function () {
                return "otherDivBox";//其它信息box
            },
            frm: function () {
                return "frmOther";//其它信息frm
            }
        }
    }

    CaseEstateFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    CaseEstateFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseEstateFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    CaseEstateFun.prototype.select2Event = {
        estate: function () {
            $("#province").select2();
            $("#city").select2();
            $("#district").select2();
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });
            $("#" + caseEstate.config.estate.frm() + " .blockId").select2();//加载样式
            //主要是载入select2
            $.ajax({
                url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                type: "get",
                dataType: "json",
                async: false,
                data: {type: "DataDeveloper"},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            if ($("#" + caseEstate.config.estate.frm() + " .developerId").size() > 0) {
                                $("#" + caseEstate.config.estate.frm() + " .developerId").html(option);
                                $("#" + caseEstate.config.estate.frm() + " .developerId").select2();//加载样式
                            }
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            //主要是载入select2
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/listDataLandLevel",
                type: "get",
                dataType: "json",
                async: false,
                data: {
                    province: "${projectInfo.province}",
                    city: "${projectInfo.city}",
                    district: "${projectInfo.district}"
                },
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].leve + "</option>";
                            }
                            if ($("#" + caseEstate.config.estate.frm() + " .landLevel").size() > 0) {
                                $("#" + caseEstate.config.estate.frm() + " .landLevel").html(option);
                                $("#" + caseEstate.config.estate.frm() + " .landLevel").select2();//加载样式
                            }
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $("#" + caseEstate.config.estate.frm() + " .landLevel").change(function () {
                var id = $("#" + caseEstate.config.estate.frm() + " .landLevel").eq(1).val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelById",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            if (caseEstate.isEmpty(data)) {
                                $("#" + caseEstate.config.estate.frm() + " input[name='street']").val(data.street);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            });
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/caseEstate/initAndUpdateSon",
                data: {},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('初始化成功');
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        },
        landState: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
                if ($("#" + caseEstate.config.landState.frm() + " .landUseType").size() > 0) {
                    $("#" + caseEstate.config.landState.frm() + " .landUseType").html(html);
                    $("#" + caseEstate.config.landState.frm() + " .landUseType").select2({minimumResultsForSearch: -1});//加载样式
                }
            });
            $("#" + caseEstate.config.landState.frm() + " .landUseCategory").select2();//加载样式
            $("#" + caseEstate.config.landState.frm() + " .landUseType").change(function () {
                var id = $("#" + caseEstate.config.landState.frm() + " .landUseType").eq(1).val();
                $("#" + caseEstate.config.landState.frm() + " .landUseCategory").eq(0).remove();
                AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                    if ($("#" + caseEstate.config.landState.frm() + " .landUseCategory").size() > 0) {
                        $("#" + caseEstate.config.landState.frm() + " .landUseCategory").html(html);
                        $("#" + caseEstate.config.landState.frm() + " .landUseCategory").select2();//加载样式
                    }
                });
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/listDataLandLevel",
                type: "get",
                dataType: "json",
                async: false,
                data: {
                    province: "${projectInfo.province}",
                    city: "${projectInfo.city}",
                    district: "${projectInfo.district}"
                },
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].leve + "</option>";
                            }
                            if ($("#" + caseEstate.config.landState.frm() + " .landLevel").size() > 0) {
                                $("#" + caseEstate.config.landState.frm() + " .landLevel").html(option);
                                $("#" + caseEstate.config.landState.frm() + " .landLevel").select2();//加载样式
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
    CaseEstateFun.prototype.uploadFile = function (fieldsName, table) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: ${empty caseEstate.id?0:caseEstate.id},
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
    }
    CaseEstateFun.prototype.showFile = function (fieldsName, table) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: ${empty caseEstate.id?0:caseEstate.id},
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    }

    var caseEstate = new CaseEstateFun();

    //模块 楼盘基本信息
    caseEstate.estateModel = {
        init: function () {
            //总平面图
            caseEstate.uploadFile(caseEstate.config.estate.filePlanTotal(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.filePlanTotal(), AssessDBKey.CaseEstate);
            //外观图
            caseEstate.uploadFile(caseEstate.config.estate.fileAppearance(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.fileAppearance(), AssessDBKey.CaseEstate);
            //供水平面图
            caseEstate.uploadFile(caseEstate.config.estate.waterSupplyPlan(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.waterSupplyPlan(), AssessDBKey.CaseEstate);
            //供电平面图
            caseEstate.uploadFile(caseEstate.config.estate.powerSupplyPlan(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.powerSupplyPlan(), AssessDBKey.CaseEstate);
            //供气平面图
            caseEstate.uploadFile(caseEstate.config.estate.airSupplyPlan(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.airSupplyPlan(), AssessDBKey.CaseEstate);
            //采暖平面图
            caseEstate.uploadFile(caseEstate.config.estate.heatingPlan(), AssessDBKey.CaseEstate);
            caseEstate.showFile(caseEstate.config.estate.heatingPlan(), AssessDBKey.CaseEstate);
            //编辑时:
            caseEstate.estateModel.edit();
            //使校验生效
            $("#" + caseEstate.config.estate.frm()).validate();
        },
        edit: function () {
            var caseEstateObj = "${caseEstate}";
            if (caseEstate.isEmpty(caseEstateObj)) {//修改页面显示部分数据
                caseEstate.writeSelectData(caseEstate.writeSelectData(caseEstate.config.estate.frm(), '${caseEstate.landLevel}', 'landLevel'));
                caseEstate.writeSelectData(caseEstate.writeSelectData(caseEstate.config.estate.frm(), '${caseEstate.developerId}', 'developerId'));
                caseEstate.writeSelectData(caseEstate.writeSelectData(caseEstate.config.estate.frm(), '${caseEstate.blockId}', 'blockId'));
            }
        }
    }

    //模块 楼盘的土地实体
    caseEstate.landStateModel = {
        init: function () {
            //编辑时:
            caseEstate.landStateModel.edit();
            //使校验生效
            $("#" + caseEstate.config.landState.frm()).validate();
        },
        edit: function () {
            var caseEstateLandState = "${caseEstateLandState}";
            if (caseEstate.isEmpty(caseEstateLandState)) {//修改页面显示部分数据
                <%--caseEstate.writeSelectData(caseEstate.config.landState.frm(),'${caseEstateLandState.landUseType}',"landUseType");--%>
                caseEstate.writeSelectData(caseEstate.config.landState.frm(), '${caseEstateLandState.landLevel}', "landLevel");
            }
        }
    }

    var otherFlag = true;
    caseEstate.other = {
        /**
         * @author:  zch
         * 描述:获取选择的子类数据
         * @date:2018-09-17
         **/
        tempSave: function () {
            if (!$("#" + caseEstate.config.other.frm()).valid()) {
                return false;
            }
            var data = formParams(caseEstate.config.other.frm());
            //处理子类显示问题
            if (caseEstate.isEmpty(data)) {
                var matching = data.matching.split(",");
                var other = data.other.split(",");
                $.each(matching, function (i, n) {
                    AssessCommon.getDataDicInfo(n, function (item) {
                        var fieldName = caseEstate.other.subFileName(item.fieldName);
                        var jq = $("."+fieldName);
                        if (jq.size() > 0){
                            jq.toggle();
                        }
                        console.log(fieldName);
                    });
                });
                $.each(other, function (i, n) {
                    AssessCommon.getDataDicInfo(n, function (item) {
                        var fieldName = caseEstate.other.subFileName(item.fieldName);
                        var jq = $("."+fieldName);
                        if (jq.size() > 0){
                            jq.toggle();
                        }
                        console.log(fieldName);
                    });
                });
            }
            $("#" + caseEstate.config.other.box()).modal("hide");
        },
        showBoxDiv: function () {
            if (caseEstate.other.getOtherFlag()) {
                caseEstate.other.selectInit();
                caseEstate.other.setOtherFlag(false);
            }
            $("#" + caseEstate.config.other.box()).modal("show");
        },
        getOtherFlag: function () {
            return otherFlag;
        },
        setOtherFlag: function (item) {
            otherFlag = item;
        },
        /**
         * @author:  zch
         * 描述:载入需要选择的子类信息
         * @date:2018-09-17
         **/
        selectInit: function () {
            var num = 6;
            AssessCommon.loadDataDicByKey(AssessDicKey.casesEstateOther, "", function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += data[j].name;
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + caseEstate.config.other.frm() + "HTMLOther").append(resetHtml);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.casesEstateMatching, "", function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' required='required' name='matching' value='" + data[j].id + "'" + ">";
                            resetHtml += data[j].name;
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + caseEstate.config.other.frm() + "HTMLMatching").append(resetHtml);
            });
        },
        subFileName: function (upFileName) {
            var index1 = upFileName.lastIndexOf(".");
            var index2 = upFileName.length;
            var suffix = upFileName.substring(index1 + 1, index2);//后缀名
            return suffix;
        }
    }

    //总提交
    caseEstate.submit = function () {
        if (!$("#" + caseEstate.config.landState.frm()).valid()) {
            return false;
        }
        if (!$("#" + caseEstate.config.estate.frm()).valid()) {
            return false;
        }
        var landState = formParams(caseEstate.config.landState.frm());
        var estate = formParams(caseEstate.config.estate.frm());
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/caseEstate/saveAndUpdateCaseEstate",
            data: {formData: JSON.stringify({estate: estate, landState: landState})},
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    $(function () {
        caseEstate.select2Event.estate();
        caseEstate.select2Event.landState();
        caseEstate.estateModel.init();
        caseEstate.landStateModel.init();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<div id="otherDivBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘下属子类</h3>
            </div>
            <form id="frmOther" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <h3 class="modal-title">其它信息</h3>
                                    </div>
                                </div>
                                <!--xxx -->
                                <div id="frmOtherHTMLOther">

                                </div>
                                <!--xxx -->
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <h3 class="modal-title">配套信息</h3>
                                    </div>
                                </div>
                                <!--xxx -->
                                <div id="frmOtherHTMLMatching">

                                </div>
                                <!--xxx -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="caseEstate.other.tempSave();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
