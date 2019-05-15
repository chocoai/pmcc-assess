
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">供气信息</label>
        <div class="col-sm-3">
            <label class="form-control">${basicEstate.supplyGasName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">供电信息</label>
        <div class="col-sm-3">
            <label class="form-control">${basicEstate.supplyPowerName}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">供水情况</label>
        <div class="col-sm-3">
            <label class="form-control">${basicEstate.supplyWaterName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">排水情况</label>
        <div class="col-sm-3">
            <label class="form-control">${basicEstate.drainWaterName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">供热信息</label>
        <div class="col-sm-3">
            <label class="form-control">${basicEstate.supplyHeatingName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <c:if test="${!empty basicEstate.supplyCommunicationName}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">通讯情况</label>
            <div class="col-sm-3">
                <label class="form-control">${basicEstate.supplyCommunicationName}</label>
            </div>
        </div>
    </c:if>
    <c:if test="${!empty basicEstate.supplyRoadName}">
        <div class="x-valid">
            <label class="col-sm-1 control-label">道路信息</label>
            <div class="col-sm-3">
                <label class="form-control">${basicEstate.supplyRoadName}</label>
            </div>
        </div>
    </c:if>
</div>