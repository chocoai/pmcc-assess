<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/20
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 建设周期 -->

<!-- 商业 -->
<div class="form-group strategyBusiness" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">策略性商业 建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="策略性商业建筑面积" class="form-control" name="strategyBusinessBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="strategyBusinessMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="strategyBusinessUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="strategyBusinessTotalPrice">
        </div>
    </div>
</div>
<div class="form-group operateBusiness" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">运营性商业 建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="运营性商业建筑面积" class="form-control" name="operateBusinessBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="operateBusinessMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="operateBusinessUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="operateBusinessTotalPrice">
        </div>
    </div>
</div>


<!-- 住宅 -->
<div class="form-group villaResidence" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">别墅建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="别墅建筑面积" class="form-control" name="villaResidenceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="villaResidenceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="villaResidenceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="villaResidenceTotalPrice">
        </div>
    </div>
</div>
<div class="form-group apartmentResidence" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">公寓式住宅 建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="公寓式住宅建筑面积" class="form-control" name="apartmentResidenceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="apartmentResidenceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="apartmentResidenceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="apartmentResidenceTotalPrice">
        </div>
    </div>
</div>
<div class="form-group ordinaryResidence" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">普通住宅建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="普通住宅建筑面积" class="form-control" name="ordinaryResidenceBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="ordinaryResidenceMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="ordinaryResidenceUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="ordinaryResidenceTotalPrice">
        </div>
    </div>
</div>

<!-- 地下商业 -->
<div class="form-group undergroundBusinessShop" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">地下商业购物商场面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="地下商业购物商场面积" class="form-control" name="undergroundBusinessShopBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="undergroundBusinessShopMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="undergroundBusinessShopUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="undergroundBusinessShopTotalPrice">
        </div>
    </div>
</div>

<!-- 办公 -->
<div class="form-group unitWork" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单元式办公面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单元式办公面积" class="form-control" name="unitWorkBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="unitWorkMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="unitWorkUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="unitWorkTotalPrice">
        </div>
    </div>
</div>
<div class="form-group sceneryWork" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">景观式办公面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="开敝式办公面积" class="form-control" name="sceneryWorkBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="sceneryWorkMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="sceneryWorkUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="sceneryWorkTotalPrice">
        </div>
    </div>
</div>
<div class="form-group openWork" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">开敝式办公面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="开敝式办公面积" class="form-control" name="openWorkBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="openWorkMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="openWorkUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="openWorkTotalPrice">
        </div>
    </div>
</div>
<div class="form-group closeWork" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">闭式员工办公面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="闭式员工办公面积" class="form-control" name="closeWorkBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="closeWorkMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="单位售价" class="form-control" name="closeWorkUnitPrice">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">销售合价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="销售合价" class="form-control" name="closeWorkTotalPrice">
        </div>
    </div>
</div>

<!-- 车库 -->
<div class="form-group bigGarage" style="display:none;">
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">大车库建筑面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="大车库建筑面积" class="form-control" name="bigGarageBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="bigGarageMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
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
            <input type="text" readonly="readonly"
                   placeholder="小车库建筑面积" class="form-control" name="smallGarageBuildArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">可售面积</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
                   placeholder="可售面积" class="form-control" name="smallGarageMaySaleArea">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-md-1 col-sm-1 control-label">单位售价</label>
        <div class="col-md-2 col-sm-2">
            <input type="text" readonly="readonly"
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