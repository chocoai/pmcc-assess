<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                楼盘基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicEstateFrm">
        <%@include file="/views/basic/modelView/estateSave.jsp" %>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class="col-sm-5">
                    <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_total_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-5">
                    <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control"
                           type="file">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid" id="industry_water_supply_plan">
                <label class="col-sm-1 control-label">供水平面图</label>
                <div class="col-sm-5">
                    <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_power_supply_plan">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_power_supply_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid" id="industry_air_supply_plan">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid" id="industry_heating_plan">
                <label class="col-sm-1 control-label">采暖平面图</label>
                <div class="col-sm-5">
                    <input id="heating_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_heating_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                土地交易基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicLandState">
        <%@include file="/views/basic/modelView/estateLandSave.jsp" %>
    </form>
</div>

<%@include file="/views/basic/modelView/estate/sonEstateView.jsp" %>