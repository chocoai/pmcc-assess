
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.provinceName}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.cityName}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.districtName}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <div class="input-group">
                <label class="form-control">${basicEstate.name}</label>
                <span class="input-group-btn">
                            <div onclick="estateCommon.mapMarker(true)" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
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
            <label class="form-control">${basicEstate.positionName}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础版块<span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.blockName}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.street}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.streetNumber}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.attachNumber}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占地面积</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.coverAnArea}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <c:if test="${!empty basicEstate.volumetricRate}">
                <c:choose>
                    <c:when test="${basicEstate.volumetricRate.matches('[0-9.]+')}">
                        <label class="form-control">${basicEstate.volumetricRate}%</label>
                    </c:when>
                    <c:otherwise>
                        <label class="form-control">${basicEstate.volumetricRate}</label>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿化率</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <c:if test="${!empty basicEstate.greeningRate}">
                <c:choose>
                    <c:when test="${basicEstate.greeningRate.matches('[0-9.]+')}">
                        <label class="form-control">${basicEstate.greeningRate*100}%</label>
                    </c:when>
                    <c:otherwise>
                        <label class="form-control">${basicEstate.greeningRate}</label>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总楼栋数</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.buildingNumber}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开发商</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.developer}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.floorArea}</label>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">均价</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.averagePrice}</label>
        </div>
    </div>

    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价格区间</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicEstate.priceRange}</label>
        </div>
    </div>

</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘区位描述</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control">${basicEstate.locationDescribe}</label>
        </div>
    </div>
</div>
