<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/20
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group build" style="display: none">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">建设周期</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="建设周期" class="form-control" name="constructionCycle">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">开发周期</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="开发周期" class="form-control" name="developedTime">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">调用市场比较法</label>
        <div class="col-md-2 col-sm-2">
            <label class="control-label btn">调用市场比较法<i class="fa fa-plus-circle"></i></label>
        </div>
    </div>
</div>

<div class="form-group residence" style="display: none">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">住宅建面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="住宅建筑面积" class="form-control" name="residenceCommerceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="residenceCommerceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="residenceCommerceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="合价" class="form-control" name="residenceCommerceTotalPrice">
        </div>
    </div>
</div>

<div class="form-group work" style="display: none">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">办公建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="办公建筑面积" class="form-control" name="workBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="workMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="workUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="合价" class="form-control" name="workTotalPrice">
        </div>
    </div>
</div>
<div class="form-group undergroundCommerce" style="display: none">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">地下商业建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="地下商业建筑面积" class="form-control" name="undergroundCommerceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="undergroundCommerceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="undergroundCommerceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="合价" class="form-control" name="undergroundCommerceTotalPrice">
        </div>
    </div>
</div>
<div class="form-group Garage" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">车库建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="车库建筑面积" class="form-control" name="garageBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="garageMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="garageUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="合价" class="form-control" name="garageTotalPrice">
        </div>
    </div>
</div>
<div class="form-group business" style="display: none">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">商业建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="商业建筑面积" class="form-control" name="businessCommerceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="businessCommerceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="businessCommerceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="合价" class="form-control" name="businessCommerceTotalPrice">
        </div>
    </div>
</div>

<!-- 车库 -->
<div class="form-group bigGarage" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">大车库建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="大车库建筑面积" class="form-control" name="bigGarageBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="bigGarageMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="bigGarageUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="bigGarageTotalPrice">
        </div>
    </div>
</div>
<div class="form-group smallGarage" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">小车库建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="小车库建筑面积" class="form-control" name="smallGarageBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="可售面积" class="form-control" name="smallGarageMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text"
                   placeholder="单位售价" class="form-control" name="smallGarageUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="smallGarageTotalPrice">
        </div>
    </div>
</div>