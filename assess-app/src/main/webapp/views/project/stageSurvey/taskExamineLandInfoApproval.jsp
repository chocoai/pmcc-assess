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
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <div class="x_panel">

                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${empty declareRecord?projectPlanDetails.projectPhaseName: declareRecord.name} 土地的查勘表</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="surveyExaminePurenessLandFrm">
                        <input type="hidden" name="id" value="${surveyExaminePurenessLand.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.provinceName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.cityName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.districtName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地名称</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.name}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.landUseTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类别</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.landUseCategoryName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地级别</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.landLevelName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">东至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.eastTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">南至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.southTo}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">西至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.westTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">北至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.northTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地形状</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.shapeStateName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地形状备注</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyExaminePurenessLand.shapeStateRemark}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.landArea}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">地形</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.planenessName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">地势</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.topographicTerrainName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">基础设施完备度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.infrastructureCompletenessName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地开发程度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.developmentDegreeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <c:if test="${surveyExaminePurenessLand.developmentDegreeName == '熟地'}">
                                    <label class="col-sm-1 control-label"></label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyExaminePurenessLand.developmentDegreeContentName}</label>
                                    </div>
                                </c:if>
                                <c:if test="${surveyExaminePurenessLand.developmentDegreeName != '熟地'}">
                                    <label class="col-sm-1 control-label">土地开发程度备注</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyExaminePurenessLand.developmentDegreeRemark}</label>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">土地实体结论</label>
                            <div class="col-sm-11">
                                <label class="form-control">${surveyExaminePurenessLand.conclusion}</label>
                            </div>
                        </div>
                        <div class="x_title">开发限制条件</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">容积率</label>
                                <div class="col-sm-3">
                                    <c:if test="${!empty surveyExaminePurenessLand.plotRatio}">
                                        <c:choose>
                                            <c:when test="${surveyExaminePurenessLand.plotRatio.matches('[0-9.]+')}">
                                                <label class="form-control">${surveyExaminePurenessLand.plotRatio*100}%</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label class="form-control">${surveyExaminePurenessLand.plotRatio}</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">建筑密度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.buildingDensity}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">绿地率</label>
                                <div class="col-sm-3">
                                    <c:if test="${!empty surveyExaminePurenessLand.greenSpaceRate}">
                                        <c:choose>
                                            <c:when test="${surveyExaminePurenessLand.greenSpaceRate.matches('[0-9.]+')}">
                                                <label class="form-control">${surveyExaminePurenessLand.greenSpaceRate*100}%</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label class="form-control">${surveyExaminePurenessLand.greenSpaceRate}</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">兼容比例</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.compatibleRatio}</label>
                                </div>
                            </div>
                        </div>
                        <div class="x_title">土壤</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">污染</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.contaminatedName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">酸碱度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.phName}</label>
                                </div>
                            </div>
                            <c:if test="${not empty surveyExaminePurenessLand.fertilityName}">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">肥力</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyExaminePurenessLand.fertilityName}</label>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty surveyExaminePurenessLand.bearingCapacityName}">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">承载力</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyExaminePurenessLand.bearingCapacityName}</label>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${not empty surveyExaminePurenessLand.holdOnName}">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">稳定性</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyExaminePurenessLand.holdOnName}</label>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="x_title">供应信息</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供气信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.supplyGasName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供电信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.supplyPowerName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供水情况</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.supplyWaterName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">排水情况</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.drainWaterName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供热信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.supplyHeatingName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="x_title">交易信息</div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control dbdate" name="tradingTime"><fmt:formatDate
                                            value='${surveyExaminePurenessLand.tradingTime}' pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易总价（元）</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.tradingTotalPrice}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">单价内涵<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.priceConnotationName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易单价（元）</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.tradingUnitPrice}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.informationTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源类别</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyExaminePurenessLand.informationCategoryName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附件</label>
                                <div class="col-sm-5">
                                    <div id="_SurveyExaminePurenessLandFileA"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/survey/examinePurenessLand.js"></script>
<script type="text/javascript">

    var obj = JSON.parse('${surveyExaminePurenessLandJSON}');
    purenessLand.initForm(obj);

    //审批提交
    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>

