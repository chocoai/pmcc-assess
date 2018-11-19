<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>楼栋基本信息 </h3>
        <div class="clearfix"></div>
    </div>

    <form class="form-horizontal" id="basicBuildFrm">
        <input type="hidden" name="id">
        <div class="form-group" id="navButtonBuild">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="navButtonBuild.one(this,1)">楼栋基础
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="navButtonBuild.two(this,2)">第二部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="navButtonBuild.three(this,3)">第三部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <div class="btn-group" data-toggle="buttons">
                            <button class="btn btn-default"
                                    onclick="navButtonBuild.four(this,4)">第四部分
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="配套公共设施使用费" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="竣工时间" name="beCompletedTimeName"
                           data-rule-number='true' class="form-control"
                           readonly="readonly">
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                           class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                           class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)"
                           name="floorCount" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)"
                           name="buildingHeight" class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)"
                           name="buildingArea" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)"
                           name="coverAnArea" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)"
                           name="floorHeight" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="径深(数字)"
                           name="diameterDepth" class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location"
                           class="form-control"
                           readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间<span class="symbol readonly"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="开盘时间" name="openTimeName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间<span class="symbol readonly"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="交房时间" name="roomTimeName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业类型" name="propertyTypeName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类型
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑类型" name="buildingStructureName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑类别"
                           name="buildingStructureLowerName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑类别" name="buildingCategoryName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑公司" name="dataBuildingName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业公司" name="propertyName"
                           class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_plan" name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外装图<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_figure_outside"
                           name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_Appearance_figure"
                           name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>

    </form>
</div>
<%@include file="/views/basic/modelView/build/sonBuildDetail.jsp" %>