<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3><small>楼栋基本信息</small> </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingMainFrm">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="floorCount"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="location"></label>
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
                    <label class="form-control" data-name="unitInterval"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="propertyFee"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="facilitiesUseFee"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingHeight"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="coverAnArea"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="floorHeight"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="diameterDepth"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="landUseYear"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="openTime"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="roomTime"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="beCompletedTime"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="propertyTypeName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingStructureName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类别
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingStructureLowerName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="buildingCategoryName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="dataBuildingName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <label class="form-control" data-name="propertyName"></label>
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
<%@include file="/views/basic/modelView/build/sonBuildDetail.jsp" %>
<script src="${pageContext.request.contextPath}/js/basic/building/building.common.js"></script>