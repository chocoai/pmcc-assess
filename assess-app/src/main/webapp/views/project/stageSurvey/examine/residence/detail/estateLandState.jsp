<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 土地实体情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">

    <div class="x_title">
        <h3>土地实体情况 </h3>
        <div class="clearfix"></div>
    </div>

    <form id="frm_estateLandState" class="form-horizontal">
        <input type="hidden" name="id" value="${basicEstateLandState.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.name}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landUseTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landUseCategoryName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landLevelName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.eastTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.southTo}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.westTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.northTo}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.shapeStateName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状备注</label>
                <div class="col-sm-11">
                    <label class="form-control">${basicEstateLandState.shapeStateRemark}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landArea}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.planenessName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">地势</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.topographicTerrainName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">基础设施完备度</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.infrastructureCompletenessName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.developmentDegreeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <c:if test="${basicEstateLandState.developmentDegreeName == '熟地'}">
                    <label class="col-sm-1 control-label"></label>
                    <div class="col-sm-3">
                        <label class="form-control">${basicEstateLandState.developmentDegreeContentName}</label>
                    </div>
                </c:if>
                <c:if test="${basicEstateLandState.developmentDegreeName != '熟地'}">
                    <label class="col-sm-1 control-label">土地开发程度备注</label>
                    <div class="col-sm-3">
                        <label class="form-control">${basicEstateLandState.developmentDegreeRemark}</label>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地实体结论</label>
                <div class="col-sm-11">
                    <label class="form-control">${basicEstateLandState.conclusion}</label>
                </div>
            </div>
        </div>
        <div class="x_title">开发限制条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <c:if test="${!empty basicEstateLandState.plotRatio}">
                        <c:choose>
                            <c:when test="${basicEstateLandState.plotRatio.matches('[0-9.]+')}">
                                <label class="form-control">${basicEstateLandState.plotRatio*100}%</label>
                            </c:when>
                            <c:otherwise>
                                <label class="form-control">${basicEstateLandState.plotRatio}</label>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑密度</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.buildingDensity}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">绿地率</label>
                <div class="col-sm-3">
                    <c:if test="${!empty basicEstateLandState.greenSpaceRate}">
                        <c:choose>
                            <c:when test="${basicEstateLandState.greenSpaceRate.matches('[0-9.]+')}">
                                <label class="form-control">${basicEstateLandState.greenSpaceRate*100}%</label>
                            </c:when>
                            <c:otherwise>
                                <label class="form-control">${basicEstateLandState.greenSpaceRate}</label>
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
                    <label class="form-control">${basicEstateLandState.compatibleRatio}</label>
                </div>
            </div>
        </div>
        <div class="x_title">土壤</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">污染</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.contaminatedName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">酸碱度</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.phName}</label>
                </div>
            </div>
            <c:if test="${not empty basicEstateLandState.fertilityName}">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">肥力</label>
                    <div class="col-sm-3">
                        <label class="form-control">${basicEstateLandState.fertilityName}</label>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="form-group">
            <c:if test="${not empty basicEstateLandState.bearingCapacityName}">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">承载力</label>
                    <div class="col-sm-3">
                        <label class="form-control">${basicEstateLandState.bearingCapacityName}</label>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty basicEstateLandState.holdOnName}">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">稳定性</label>
                    <div class="col-sm-3">
                        <label class="form-control">${basicEstateLandState.holdOnName}</label>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别详情分值</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landLevelAchievement}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别详情等级</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstateLandState.landLevelGradeName}</label>
                </div>
            </div>
        </div>
    </form>

</div>
