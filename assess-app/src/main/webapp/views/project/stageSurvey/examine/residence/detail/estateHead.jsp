
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="provinceName">${basicEstate.provinceName}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="cityName">${basicEstate.cityName}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="districtName">${basicEstate.districtName}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <div class="input-group">
                <label class="form-control" name="name">${basicEstate.name}</label>
                <span class="input-group-btn">
                            <c:if test="${empty isApplyBatch}">
                            <div onclick="estateCommon.mapMarker(true);" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </c:if>
                            <c:if test="${isApplyBatch eq 'show'}">
                            <div onclick="estateCommon.mapMarker2(true,${tableId});" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </c:if>
                        </span>

                <span class="input-group-btn" style="display: none">
                            <div onclick="estateCommon.mapLandMarker(true)" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
            </div>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘方位</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="positionName">${basicEstate.positionName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="blockName">${basicEstate.blockName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块描述</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 " name="locationDescribe">
            <label class="form-control">${basicEstate.blockDescription}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="street">${basicEstate.street}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="streetNumber">${basicEstate.streetNumber}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="attachNumber">${basicEstate.attachNumber}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="coverAnArea">${basicEstate.coverAnArea}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="volumetricRate">${basicEstate.volumetricRate}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="greeningRate">${basicEstate.greeningRate}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="buildingNumber">${basicEstate.buildingNumber}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开发商</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="developerName">${basicEstate.developerName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="floorArea">${basicEstate.floorArea}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">均价</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="averagePrice">${basicEstate.averagePrice}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格区间</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control" name="priceRange">${basicEstate.priceRange}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            开盘时间
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control dbdate" name="openTime">
                <fmt:formatDate value='${basicEstate.openTime}' pattern='yyyy-MM-dd'/>
            </label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘区位分析</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 " name="locationDescribe">
            <label class="form-control">${basicEstate.locationDescribe}</label>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js"></script>
<%@include file="/views/method/module/developmentCommon.jsp" %>
