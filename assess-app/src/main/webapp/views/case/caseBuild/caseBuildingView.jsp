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
            <div class="x_panel">

                <div class="x_content">
                    <div class="x_title">
                        <h3>楼栋基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="basicBuildingMainFrm">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingNumber">${caseBuildingMain.buildingNumber}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋名称
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingName">${caseBuildingMain.buildingName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    总层数
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="floorCount">${caseBuildingMain.floorCount}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    所在位置
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="location">${caseBuildingMain.location}</label>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div style="margin-bottom: 10px; border-bottom:2px solid #E6E9ED;"></div>
                    <form class="form-horizontal" id="basicBuildingFrm">
                        <input type="hidden" name="id" value="${caseBuilding.id}">
                        <div class="form-group" style="display: none;">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层起
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="floorStart"></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层止
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="floorEnd"></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    户型区间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="unitInterval">${caseBuilding.unitInterval}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业费
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="propertyFee">${caseBuilding.propertyFee}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    配套公共设施使用费
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="facilitiesUseFee">${caseBuilding.facilitiesUseFee}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑高度
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingHeight">${caseBuilding.buildingHeight}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑面积
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingArea">${caseBuilding.buildingArea}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占地面积
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="coverAnArea">${caseBuilding.coverAnArea}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    层高
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="floorHeight">${caseBuilding.floorHeight}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    径深
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="diameterDepth">${caseBuilding.diameterDepth}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用年限
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="landUseYear">${caseBuilding.landUseYear}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开盘时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="openTime"><fmt:formatDate value="${caseBuilding.openTime}"></fmt:formatDate> </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交房时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="roomTime"><fmt:formatDate value="${caseBuilding.roomTime}"></fmt:formatDate></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    竣工时间
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="beCompletedTime"><fmt:formatDate value="${caseBuilding.beCompletedTime}"></fmt:formatDate></label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业类型
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="propertyTypeName">${caseBuilding.propertyTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构类型
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingStructureName">${caseBuilding.buildingStructureName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构类别
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingStructureLowerName">${caseBuilding.buildingStructureLowerName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑类别
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="buildingCategoryName">${caseBuilding.buildingCategoryName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑公司
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="dataBuildingName">${caseBuilding.dataBuildingName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业公司
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control" data-name="propertyName">${caseBuilding.propertyName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group" id="navButtonBuildGroupFileId">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">平面图</label>
                                <div class="col-sm-3">
                                    <div id="_building_floor_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外装图</label>
                                <div class="col-sm-3">
                                    <div id="_building_figure_outside"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外观图</label>
                                <div class="col-sm-3">
                                    <div id="_building_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <%@include file="/views/case/caseBuild/sonBuildDetail.jsp" %>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        buildingModel.prototype.viewInit();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
