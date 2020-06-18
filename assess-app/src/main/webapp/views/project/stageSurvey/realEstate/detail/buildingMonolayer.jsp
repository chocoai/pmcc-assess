<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>楼栋</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        楼栋信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="basicBuildingFrm">
                                    <input type="hidden" name="id" value="${basicBuilding.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <label class="form-control" name="buildingNumber">${basicBuilding.buildingNumber}</label>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-info btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="buildingCommon.mapMarker(true);">
                                                            标注
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                    
                                            <label class="col-sm-1 control-label">
                                                物业类型
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="propertyTypeName">${basicBuilding.propertyTypeName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                物业档次
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="propertyCategoryName">${basicBuilding.propertyCategoryName}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地使用年限
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="landUseYear">${basicBuilding.landUseYear}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">单元数</label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="unitCount">${basicBuilding.unitCount}</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-info btn-sm"
                                                        onclick="buildingCommon.constructionInstallationEngineeringFeeEvent.loadHtml();">
                                                    建筑安装完工度调查
                                                </button>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                楼栋所在位置
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <label class="form-control form-control-sm"
                                                           name="reference">${basicBuilding.reference}</label>
                                                    <label class="form-control form-control-sm"
                                                           name="orientation">${basicBuilding.orientation}</label>
                                                </div>                                            </div>
                                            <label class="col-sm-1 control-label">
                                                层高
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="floorHeight">${basicBuilding.floorHeight}</label>
                                            </div>

                                            <label class="col-sm-1 control-label">
                                                建筑高度
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="buildingHeight">${basicBuilding.buildingHeight}</label>
                                            </div>

                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑面积(平方米)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="buildingArea">${basicBuilding.buildingArea}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                占地面积(平方米)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="coverAnArea">${basicBuilding.coverAnArea}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                建筑结构类型
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="buildingStructureTypeName">${basicBuilding.buildingStructureTypeName}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑结构类别
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="buildingStructureCategoryName">${basicBuilding.buildingStructureCategoryName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                建筑使用年限
                                            </label>
                                            <div class="col-sm-3">
                                                <c:if test="${!empty basicBuilding.residenceUseYearName}">
                                                    <label class="form-control input-full"
                                                           name="residenceUseYearName">${basicBuilding.residenceUseYearName}</label>
                                                </c:if>
                                                <c:if test="${!empty basicBuilding.industryUseYearName}">
                                                    <label class="form-control input-full"
                                                           name="industryUseYearName">${basicBuilding.industryUseYearName}</label>
                                                </c:if>
                                            </div>

                                            <label class="col-sm-1 control-label">
                                                外观风格
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="appearanceStyleName">${basicBuilding.appearanceStyleName}</label>
                                            </div>

                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                外观新旧
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="appearanceNewAndOldName">${basicBuilding.appearanceNewAndOldName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                楼间距
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="betweenDistanceName">${basicBuilding.betweenDistanceName}</label>
                                            </div>

                                            <label class="col-sm-1 control-label">最小楼间距倍数</label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="minimumFloorDistance">${basicBuilding.minimumFloorDistance}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                竣工时间获取方式
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="completedTimeTypeName">${basicBuilding.completedTimeTypeName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                竣工时间
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full dbdate" name="beCompletedTime"><fmt:formatDate
                                                        value='${basicBuilding.beCompletedTime}' pattern='yyyy-MM-dd'/></label>

                                            </div>
                                            <label class="col-sm-1 control-label">
                                                开盘时间
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full dbdate" name="openTime">
                                                    <fmt:formatDate
                                                            value='${basicBuilding.openTime}' pattern='yyyy-MM-dd'/>
                                                </label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                工程质量
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="constructionQualityName">${basicBuilding.constructionQualityName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                建筑公司
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="builderName">${basicBuilding.builderName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                物业费(平方米)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="propertyFee">${basicBuilding.propertyFee}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                配套公共设施使用费
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="facilitiesUseFee">${basicBuilding.facilitiesUseFee}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                物业公司名称
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="propertyName">${basicBuilding.propertyName}</label>
                                            </div>

                                            <label class="col-sm-1 control-label">
                                                物业公司性质
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="propertyCompanyNatureName">${basicBuilding.propertyCompanyNatureName}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                物业公司社会信誉
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="propertySocialPrestigeName">${basicBuilding.propertySocialPrestigeName}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                街道号
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"
                                                       name="streetNumber">${basicBuilding.streetNumber}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                物业服务
                                            </label>
                                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                                <table class="table table-bordered" id="basicBuildingPropertyServiceItemTable">

                                                </table>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div id="basicBuilding"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${empty isHistory}">
                            <%@include file="/views/project/stageSurvey/commonDetail/buildingOutfit.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/buildingFunction.jsp" %>
                            <c:if test="${formType eq 'industry'}">
                                <%@include file="/views/project/stageSurvey/commonDetail/buildingSurface.jsp" %>
                                <%@include file="/views/project/stageSurvey/commonDetail/buildingMaintenance.jsp" %>
                            </c:if>
                    </c:if>
                    <%@include file="/views/project/chksCustomize/chksSurvey.jsp" %>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>
<%@include file="/views/share/chksCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<script type="text/javascript">
    $(function () {
        buildingCommon.initDetailById('${basicBuilding.id}', '', false);
    })
</script>
</html>
