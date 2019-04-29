<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_estate" class="form-horizontal">
    <input type="hidden" name="id" value="${basicEstate.id}">

    <div class="x_content">
        <div class="x_title">
            <h3>
                楼盘基本信息
            </h3>
            <div class="clearfix"></div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.provinceName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.cityName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.districtName}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${basicEstate.name}</label>
                        <span class="input-group-btn">
                            <div onclick="estateCommon.mapMarker(true);" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.positionName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.blockName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道名称</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.street}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.streetNumber}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.attachNumber}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.coverAnArea}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <c:if test="${!empty basicEstate.volumetricRate}">
                        <c:choose>
                            <c:when test="${basicEstate.volumetricRate.matches('[0-9.]+')}">
                                <label class="form-control">${basicEstate.volumetricRate*100}%</label>
                            </c:when>
                            <c:otherwise>
                                <label class="form-control">${basicEstate.volumetricRate}</label>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
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
                <label class="col-sm-1 control-label">总楼栋数</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.buildingNumber}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.developerName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.floorArea}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.averagePrice}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.priceRange}</label>
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_floor_total_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-5">
                    <div id="_estate_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">供水平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_power_supply_plan"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">采暖平面图</label>
                <div class="col-sm-5">
                    <div id="_estate_heating_plan"></div>
                </div>
            </div>
        </div>
    </div>
</form>
