<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                楼栋基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingMainFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" data-rule-maxlength="100" placeholder="楼栋号" required="required"
                               name="buildingNumber" class="form-control" >
                        <span class="input-group-btn">
                            <div onclick="buildingCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control"
                           required="required">
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
                    <input type="text" placeholder="楼层起(数字)" name="floorStart" data-rule-number='true' class="form-control" >
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                           data-rule-number='true' class="form-control" >
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="径深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="propertyType" required="required"
                            class="form-control search-select select2 propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类型<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructure"
                            class="form-control search-select select2 buildingStructure">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类别<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructureLower"
                            class="form-control search-select select2 buildingStructureLower">
                        <option>建筑类别</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="buildingCategory" required="required"
                            class="form-control search-select select2 buildingCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="builderId">
                        <input type="text" readonly="readonly"
                               placeholder="建筑公司" class="form-control" name="dataBuildingName">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicCommon.builderSelect(this)">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="propertyId">
                        <input type="text" readonly="readonly" name="propertyName"
                               placeholder="物业公司" class="form-control">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicCommon.propertySelect(this)">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外装图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_figure_outside"  placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/build/sonBuildView.jsp" %>
