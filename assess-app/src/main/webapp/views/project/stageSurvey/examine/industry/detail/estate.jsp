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
                    <label class="form-control">${basicEstate.volumetricRate}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicEstate.greeningRate}</label>
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
                    <label class="form-control">${basicEstate.developer}</label>
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
    </div>

    <div class="x_content">
        <div class="x_title">楼盘供应信息</div>
        <div class="form-horizontal">
            <div class="x_content">
                <div class="x_title">楼盘供应信息</div>
                <div class="form-horizontal">
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
                </div>
            </div>
        </div>
    </div>
</form>
