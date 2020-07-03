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
                                                <label class="col-sm-1 control-label">
                                                    单元数
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="unitCount">${basicBuilding.unitCount}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    首层位置
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="firstFloor">${basicBuilding.firstFloor}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    最高层
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="maxFloor">${basicBuilding.maxFloor}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    层高
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="floorHeight">${basicBuilding.floorHeight}</label>
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
                                                    物业费(平方米)
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full" name="propertyFee">${basicBuilding.propertyFee}</label>
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
<%@include file="/views/chks/assessmentCommon.jsp" %>
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
