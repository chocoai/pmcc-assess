<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        楼栋
                    </h2>
                </div>
            </div>
            <div class="x_panel">


                <div class="x_content">
                    <form class="form-horizontal" id="basicBuildingFrm">
                        <input type="hidden" name="id" value="${basicBuilding.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    楼栋号<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control"
                                           name="buildingNumber">${basicBuilding.buildingNumber}</label>
                                    <span class="input-group-btn">
                                        <div onclick="buildingCommon.mapMarker2(true,${tableId});" class="btn btn-info"><i
                                                class="fa fa-map-marker"></i> 标注</div>
                                    </span>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    楼栋名称<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="buildingName">${basicBuilding.buildingName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    总层数<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="floorCount">${basicBuilding.floorCount}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    物业类型
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="propertyTypeName">${basicBuilding.propertyTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    物业类别
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="propertyCategoryName">${basicBuilding.propertyCategoryName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    首层位置
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="firstFloor">${basicBuilding.firstFloor}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    最高层
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="maxFloor">${basicBuilding.maxFloor}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    套内面积
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="inJacketArea">${basicBuilding.inJacketArea}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    使用面积
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="useArea">${basicBuilding.useArea}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    所在位置
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="location">${basicBuilding.location}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    建筑使用年限<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <c:if test="${!empty basicBuilding.residenceUseYearName}">
                                        <label class="form-control" name="residenceUseYearName">${basicBuilding.residenceUseYearName}</label>
                                    </c:if>
                                    <c:if test="${!empty basicBuilding.industryUseYearName}">
                                        <label class="form-control" name="industryUseYearName">${basicBuilding.industryUseYearName}</label>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    土地使用年限
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="landUseYear">${basicBuilding.landUseYear}</label>

                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    开盘时间
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control dbdate" name="openTime">
                                        <fmt:formatDate
                                                value='${basicBuilding.openTime}' pattern='yyyy-MM-dd'/>
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    交房时间
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control dbdate" name="roomTime">
                                        <fmt:formatDate
                                                value='${basicBuilding.roomTime}' pattern='yyyy-MM-dd'/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    建筑结构类型
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="buildingStructureTypeName">${basicBuilding.buildingStructureTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    建筑结构类别
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="buildingStructureCategoryName">${basicBuilding.buildingStructureCategoryName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    外观风格
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="appearanceStyleName">${basicBuilding.appearanceStyleName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    外观新旧
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="appearanceNewAndOldName">${basicBuilding.appearanceNewAndOldName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元说明</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="remark">${basicBuilding.remark}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    竣工时间获取方式<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="completedTimeTypeName">${basicBuilding.completedTimeTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    竣工时间<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control dbdate" name="beCompletedTime"><fmt:formatDate
                                            value='${basicBuilding.beCompletedTime}' pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 col-lg-offset-1 ">
                                    <button type="button" class="btn btn-xs btn-primary"
                                            onclick="buildingCommon.constructionInstallationEngineeringFeeEvent.loadHtml();">
                                        建筑安装完工度调查
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="navButtonBuildGroupFileId">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">平面图</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_building_floor_plan"></div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外装图</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_building_figure_outside"></div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div id="_building_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/common/buildingFunction.jsp" %>
                    <%@include file="/views/project/stageSurvey/common/buildingOutfit.jsp" %>
                    <c:if test="${basicApplyBatch.type == 1}">
                        <%@include file="/views/project/stageSurvey/common/buildingMaintenance.jsp" %>
                        <%@include file="/views/project/stageSurvey/common/buildingSurface.jsp" %>
                    </c:if>
                </div>


            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
    })
</script>

