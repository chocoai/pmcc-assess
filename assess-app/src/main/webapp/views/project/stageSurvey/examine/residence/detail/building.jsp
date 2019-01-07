
<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="x_content">

    <form class="form-horizontal" id="basicBuildingMainFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control" name="buildingNumber">${basicBuildingMain.buildingNumber}</label>
                        <span class="input-group-btn">
                            <div onclick="buildingCommon.mapMarker(true);" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingName">${basicBuildingMain.buildingName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorCount">${basicBuildingMain.floorCount}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="location">${basicBuildingMain.location}</label>
                </div>
            </div>
        </div>
    </form>
    <div style="margin-bottom: 10px; border-bottom:2px solid #E6E9ED;"></div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id">
        <div class="form-group" style="display: none;">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorStart">${basicBuilding.floorStart}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorEnd">${basicBuilding.floorEnd}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="unitInterval">${basicBuilding.unitInterval}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="propertyFee">${basicBuilding.propertyFee}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="facilitiesUseFee">${basicBuilding.facilitiesUseFee}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingHeight">${basicBuilding.buildingHeight}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingArea">${basicBuilding.buildingArea}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="coverAnArea">${basicBuilding.coverAnArea}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorHeight">${basicBuilding.floorHeight}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    进深
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="diameterDepth">${basicBuilding.diameterDepth}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="landUseYear">${basicBuilding.landUseYear}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control dbdate" name="openTime">
                        <fmt:formatDate
                                value='${basicBuilding.openTime}' pattern='yyyy-MM-dd'/>
                        </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control dbdate" name="roomTime">
                      <fmt:formatDate
                                value='${basicBuilding.roomTime}' pattern='yyyy-MM-dd'/>
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control dbdate" name="beCompletedTime"><fmt:formatDate
                            value='${basicBuilding.beCompletedTime}' pattern='yyyy-MM-dd'/></label>

                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="propertyTypeName">${basicBuilding.propertyTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类别
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="propertyCategoryName">${basicBuilding.propertyCategoryName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="property">${basicBuilding.property}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingStructureTypeName">${basicBuilding.buildingStructureTypeName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类别
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingStructureCategoryName">${basicBuilding.buildingStructureCategoryName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="builder">${basicBuilding.builder}</label>
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

<c:if test="${dataExamineTask.fieldName == 'fc.residence'}">
    <%@include file="./buildingOutfit.jsp" %>
    <%@include file="./buildingFunction.jsp" %>
</c:if>

<c:if test="${dataExamineTask.fieldName == 'fc.industry'}">
    <%@include file="./buildingOutfit.jsp" %>
    <%@include file="./buildingFunction.jsp" %>
    <%@include file="../../industry/detail/buildingMaintenance.jsp" %>
    <%@include file="../../industry/detail/buildingSurface.jsp" %>
</c:if>


</html>

