<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/11/6
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>楼盘基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="frm_estate">
        <input type="hidden" name="id" value="${basicEstate.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                </label>
                <div class="col-sm-3">
                    <label data-name="provinceName" class="form-control">
                    </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市</label>
                <div class="col-sm-3">
                    <label data-name="cityName" class="form-control">
                    </label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县</label>
                <div class="col-sm-3">
                    <label data-name="districtName" class="form-control">
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称</label>
                <div class="col-sm-3">
                    <label data-name="name" class="form-control">
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位</label>
                <div class="col-sm-3">
                    <label data-name="position" class="form-control">
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块</label>
                <div class="col-sm-3">
                    <label data-name="blockName" class="form-control">
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号</label>
                <div class="col-sm-3">
                    <label data-name="number" class="form-control">
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别</label>
                <div class="col-sm-3">
                    <label data-name="landLevelName" class="form-control">
                    </label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道</label>
                <div class="col-sm-3">
                    <label data-name="street" class="form-control">
                    </label>
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="占地面积" name="coverAnArea" class="form-control"
                           value="${basicEstate.coverAnArea}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="容积率" value="${basicEstate.volumetricRate}"
                           name="volumetricRate" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率</label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly"
                           placeholder="绿化率" value="${basicEstate.greeningRate}"
                           name="greeningRate" class="form-control">
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘概况</label>
                <div class="col-sm-11">
                                                    <textarea class="form-control" readonly="readonly"
                                                              name="description"
                                                              placeholder="楼盘概况">${basicEstate.description}</textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼栋数</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼栋数" readonly="readonly" name="buildingNumber"
                           class="form-control" value="${basicEstate.buildingNumber}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="开发商" readonly="readonly" name="developerName"
                           class="form-control" value="${basicEstate.developerName}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑面积" readonly="readonly" name="floorArea"
                           class="form-control" value="${basicEstate.floorArea}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="均价(请输入数字)" name="averagePrice"
                           readonly="readonly"
                           class="form-control" value="${basicEstate.averagePrice}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" readonly="readonly" class="form-control"
                           value="${basicEstate.priceRange}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附号(请输入数字)" value="${basicEstate.attachNumber}"
                           name="attachNumber" readonly="readonly" class="form-control">
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
                    <div id="_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <div id="_power_supply_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <div id="_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">采暖平面图</label>
                <div class="col-sm-5">
                    <div id="_heating_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>