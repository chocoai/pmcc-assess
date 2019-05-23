<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="text" data-rule-maxlength="100" placeholder="楼栋号" required="required"
                               name="buildingNumber" class="form-control" onblur="$(this).val($(this).val().replace('栋',''));">
                        <span class="input-group-btn">
                            <div onclick="buildingCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在位置
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="所在位置" name="location" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑使用年限<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="residenceUseYearModel_A">
                        <select name="residenceUseYear"  class="form-control residenceUseYear search-select select2">
                        </select>
                    </div>
                    <div id="industryUseYearModel_A">
                        <select name="industryUseYear"  class="form-control industryUseYear search-select select2">
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    户型区间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="户型区间" name="unitInterval" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业费
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee" data-rule-number='true'
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    配套公共设施使用费
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑高度
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    占地面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    层高
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control" >
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    进深
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="进深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开盘时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    交房时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="竣工时间" required
                           name="beCompletedTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyType"
                            class="form-control propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyCategory" class="form-control propertyCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业公司
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="hidden" name="property" placeholder="物业公司" class="form-control" value="${basicBuilding.property}">
                    <input type="text" name="propertyName" placeholder="物业公司" class="form-control" value="${basicBuilding.propertyName}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureType"
                            class="form-control buildingStructureType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureCategory" class="form-control buildingStructureCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑公司
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑公司" class="form-control" name="builderName" value="${basicBuilding.builderName}">
                    <input type="hidden" placeholder="建筑公司" class="form-control" name="builder" value="${basicBuilding.builder}">
                </div>
            </div>
        </div>

        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">平面图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外装图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_figure_outside" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/build/sonBuildView.jsp" %>
