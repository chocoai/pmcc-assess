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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        楼栋
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘基本信息 </h3>
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
                                <label class="col-sm-1 control-label">楼盘方位<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="楼盘方位" required
                                           name="position" class="form-control" value="${caseEstate.position}">
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
                                <label class="col-sm-1 control-label">编号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="编号(请输入数字)" name="number" class="form-control"
                                           required="required" value="${caseEstate.number}">
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
                                           placeholder="绿化率(请输入数字)" required="required" value="${caseEstate.greeningRate}"
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
                                           name="priceRange" required="required" class="form-control" value="${caseEstate.priceRange}">
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
                                    <input type="text" class="form-control" required="required" name="name" placeholder="名称" value="${caseEstateLandState.soil}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landUseType" name="landUseType" required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类别<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landUseCategory" name="landUseCategory" required="required">
                                        <option>请先选择土地用途类型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 landLevel" name="landLevel" required="required">
                                    </select>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="东至" required="required" name="eastTo" value="${caseEstateLandState.eastTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="南至" required="required" name="southTo" value="${caseEstateLandState.southTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="西至" required="required" name="westTo" value="${caseEstateLandState.westTo}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="北至" required="required" name="northTo" value="${caseEstateLandState.northTo}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地形状状况<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地形状状况" required="required" name="shapeState" value="${caseEstateLandState.shapeState}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地平整度" required="required" name="planeness" value="${caseEstateLandState.planeness}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地开发程度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土地开发程度" required="required" name="developmentDegree" value="${caseEstateLandState.developmentDegree}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">开发限制条件<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="开发限制条件" required="required" name="restrictiveCondition" value="${caseEstateLandState.restrictiveCondition}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="土壤" required="required" name="soil" value="${caseEstateLandState.soil}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" value="${caseEstateLandState.topographicTerrain}" placeholder="地形地势" required="required" name="topographicTerrain">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" data-rule-number='true' placeholder="土地面积(请输入数字)" required="required" name="landArea" value="${caseEstateLandState.landArea}">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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
            filePlanTotal:function () {
                return "estate_floor_total_plan";//总平面图id和字段
            },
            waterSupplyPlan:function () {
                return "water_supply_plan";//供水平面图id和字段
            },
            powerSupplyPlan:function () {
                return "power_supply_plan";//供电平面图id和字段
            },
            airSupplyPlan:function () {
                return "air_supply_plan";//供气平面图id和字段
            },
            heatingPlan:function () {
                return "heating_plan";//采暖平面图id和字段
            },
            fileAppearance:function () {
                return "estate_floor_Appearance_figure";//外观图id和字段
            }
        },
        landState:{
            frm:function () {
                return "frm_estateLandState" ;//土地实体情况
            }
        }
    }


</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
