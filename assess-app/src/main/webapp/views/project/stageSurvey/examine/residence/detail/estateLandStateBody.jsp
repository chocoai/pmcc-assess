
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<input type="hidden" name="id" value="${basicEstateLandState.id}">
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地名称</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="name">${basicEstateLandState.name}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="landUseTypeName">${basicEstateLandState.landUseTypeName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类别</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="landUseCategoryName">${basicEstateLandState.landUseCategoryName}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地级别</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="landLevelName">${basicEstateLandState.landLevelName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">东至</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="eastTo">${basicEstateLandState.eastTo}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">南至</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="southTo">${basicEstateLandState.southTo}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">西至</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="westTo">${basicEstateLandState.westTo}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">北至</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="northTo">${basicEstateLandState.northTo}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="shapeStateName">${basicEstateLandState.shapeStateName}</label>
        </div>
    </div>
</div>
<%--<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地形状备注</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control">${basicEstateLandState.shapeStateRemark}</label>
        </div>
    </div>
</div>--%>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地面积</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="landArea">${basicEstateLandState.landArea}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地形</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="planenessName">${basicEstateLandState.planenessName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地势</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="topographicTerrainName">${basicEstateLandState.topographicTerrainName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地利用现状</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="presentSituationLandUse">${basicEstateLandState.presentSituationLandUse}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="developmentDegreeName">${basicEstateLandState.developmentDegreeName}</label>
        </div>
    </div>
    <div class="x-valid">
        <c:if test="${basicEstateLandState.developmentDegreeName == '熟地'}">
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 col-lg-offset-1">
                <label class="form-control" name="developmentDegreeContentName">${basicEstateLandState.developmentDegreeContentName}</label>
            </div>
        </c:if>
        <c:if test="${basicEstateLandState.developmentDegreeName != '熟地'}">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地开发程度备注</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="developmentDegreeRemark">${basicEstateLandState.developmentDegreeRemark}</label>
            </div>
        </c:if>
    </div>
</div>

<div class="form-group">

</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地实体结论</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control" name="conclusion">${basicEstateLandState.conclusion}</label>
        </div>
    </div>
</div>
<div class="x_title">开发限制条件</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="plotRatio">${basicEstateLandState.plotRatio}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="buildingDensity">${basicEstateLandState.buildingDensity}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="greenSpaceRate">${basicEstateLandState.greenSpaceRate}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">兼容比例</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="compatibleRatio">${basicEstateLandState.compatibleRatio}</label>
        </div>
    </div>
    <c:if test="${!empty basicEstateLandState.investmentIntensity}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">投资强度（万元/亩）</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="investmentIntensity">${basicEstateLandState.investmentIntensity}</label>
            </div>
        </div>
    </c:if>
    <c:if test="${!empty basicEstateLandState.buildingHeightLimit}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="buildingHeightLimit">${basicEstateLandState.buildingHeightLimit}</label>
            </div>
        </div>
    </c:if>
</div>
<div class="x_title">土壤</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">污染</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="contaminatedName">${basicEstateLandState.contaminatedName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">酸碱度</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="phName">${basicEstateLandState.phName}</label>
        </div>
    </div>
    <c:if test="${not empty basicEstateLandState.fertilityName}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">肥力</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="fertilityName">${basicEstateLandState.fertilityName}</label>
            </div>
        </div>
    </c:if>
</div>
<div class="form-group">
    <c:if test="${not empty basicEstateLandState.bearingCapacityName}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">承载力</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="bearingCapacityName">${basicEstateLandState.bearingCapacityName}</label>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty basicEstateLandState.holdOnName}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">稳定性</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="holdOnName">${basicEstateLandState.holdOnName}</label>
            </div>
        </div>
    </c:if>
</div>

<div class="form-group">
    <c:if test="${not empty basicEstateLandState.specialRestrictions}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">投资强度（万元/亩）</label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                <label class="form-control" name="specialRestrictions">${basicEstateLandState.specialRestrictions}</label>
            </div>
        </div>
    </c:if>
</div>

<div class="form-group">
    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
        <table class="table table-striped table-bordered" style="display: none">
            <thead>
            <tr>
                <th width="20%">土地级别类别</th>
                <th width="20%">土地级别类型</th>
                <th width="15%">等级</th>
                <th width="10%">分值</th>
            </tr>
            </thead>
            <tbody id="landLevelTabContent">

            </tbody>
        </table>
    </div>
</div>

<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td  class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {landLevelCategoryName}
        </td>



        <td>
            <label name="gradeName" class="form-control">{gradeName}</label>
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control">{landFactorTotalScore}</label>
        </td>

    </tr>
</script>