<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>楼栋信息</title>
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
                                                    楼栋号
                                                </label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <label class="form-control "
                                                               name="buildingNumber">${basicBuilding.buildingNumber}</label>
                                                        <span class="input-group-btn">
                                        <div onclick="buildingCommon.mapMarker2(true,${tableId});" class="btn btn-info"><i
                                                class="fa fa-map-marker"></i> 标注</div>
                                    </span>
                                                    </div>
                                                </div>

                                                <label class="col-sm-1 control-label">
                                                    楼栋名称
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="buildingName">${basicBuilding.buildingName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    总层数
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="floorCount">${basicBuilding.floorCount}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    物业类型
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="propertyTypeName">${basicBuilding.propertyTypeName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    物业类别
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="propertyCategoryName">${basicBuilding.propertyCategoryName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    首层位置
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="firstFloor">${basicBuilding.firstFloor}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    最高层
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="maxFloor">${basicBuilding.maxFloor}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    套内面积
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="inJacketArea">${basicBuilding.inJacketArea}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    使用面积
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="useArea">${basicBuilding.useArea}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    所在位置
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="location">${basicBuilding.location}</label>
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
                                                    <label class="form-control input-full"
                                                           name="landUseYear">${basicBuilding.landUseYear}</label>

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
                                                <label class="col-sm-1 control-label">
                                                    交房时间
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full dbdate" name="roomTime">
                                                        <fmt:formatDate
                                                                value='${basicBuilding.roomTime}' pattern='yyyy-MM-dd'/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑结构类型
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="buildingStructureTypeName">${basicBuilding.buildingStructureTypeName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    建筑结构类别
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="buildingStructureCategoryName">${basicBuilding.buildingStructureCategoryName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    外观风格
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="appearanceStyleName">${basicBuilding.appearanceStyleName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    外观新旧
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="appearanceNewAndOldName">${basicBuilding.appearanceNewAndOldName}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">单元说明</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="remark">${basicBuilding.remark}</label>
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
                                                    <label class="form-control input-full dbdate"
                                                           name="beCompletedTime"><fmt:formatDate
                                                            value='${basicBuilding.beCompletedTime}'
                                                            pattern='yyyy-MM-dd'/></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group" id="navButtonBuildGroupFileId">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">平面图</label>
                                                <div class="col-sm-3">
                                                    <div id="_building_floor_plan"></div>
                                                </div>
                                                <label class="col-sm-1 control-label">外装图</label>
                                                <div class="col-sm-3">
                                                    <div id="_building_figure_outside"></div>
                                                </div>
                                                <label class="col-sm-1 control-label">外观图</label>
                                                <div class="col-sm-3">
                                                    <div id="_building_floor_Appearance_figure"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${empty isHistory}">
                        <%@include file="/views/project/stageSurvey/commonDetail/buildingFunction.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/buildingOutfit.jsp" %>
                        <c:if test="${basicApplyBatch.type == 1}">
                            <%@include file="/views/project/stageSurvey/commonDetail/buildingMaintenance.jsp" %>
                            <%@include file="/views/project/stageSurvey/commonDetail/buildingSurface.jsp" %>
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

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    $(function () {
        buildingCommon.initDetailById('${basicBuilding.id}', '', false);
    })
</script>

