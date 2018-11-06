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
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province">
                        <option value="" name="province">-请选择-</option>
                        <c:forEach items="${ProvinceList}" var="item">
                            <c:choose>
                                <c:when test="${item.areaId == projectInfo.province}">
                                    <option value="${item.areaId}"
                                            selected="selected">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.areaId}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city" class="form-control search-select select2 city">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="district" class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                           name="name" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘方位<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="楼盘方位"
                           name="position" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="blockId" class="form-control search-select select2 blockId"
                            required="required">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(请输入数字)" name="number" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landLevel" name="landLevel">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="街道" readonly="readonly"
                           name="street" class="form-control">
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占地面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="占地面积(请输入数字)" name="coverAnArea" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="容积率(请输入数字)" name="volumetricRate" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">绿化率<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="绿化率(请输入数字)" name="greeningRate" class="form-control">
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼盘概况<span class="symbol required"></span></label>
                <div class="col-sm-11">
                        <textarea class="form-control" required="required" name="description"
                                  placeholder="楼盘概况"></textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼栋数</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="楼栋数(请输入数字)" name="buildingNumber"
                           class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发商<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 developerId" name="developerId">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">均价<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="均价(请输入数字)" name="averagePrice" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">价格区间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="价格区间"
                           name="priceRange" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="附号(请输入数字)"
                           name="attachNumber" class="form-control">
                </div>
            </div>
        </div>

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
            <div class="x-valid">
                <label class="col-sm-1 control-label">供水平面图</label>
                <div class="col-sm-5">
                    <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_water_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">供电平面图</label>
                <div class="col-sm-5">
                    <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_power_supply_plan"></div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">供气平面图</label>
                <div class="col-sm-5">
                    <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_air_supply_plan"></div>
                </div>
            </div>

            <div class="x-valid">
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
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <input type="text" class="form-control" required="required" name="name"
                           placeholder="名称">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseType" name="landUseType"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseCategory"
                            name="landUseCategory" required="required">
                        <option>请先选择土地用途类型</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landLevel" name="landLevel"
                            required="required">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至" required="required"
                           name="eastTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至" required="required"
                           name="southTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至" required="required"
                           name="westTo">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至" required="required"
                           name="northTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状状况" required="required"
                           name="shapeState">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地平整度" required="required"
                           name="planeness">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度" required="required"
                           name="developmentDegree">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="开发限制条件" required="required"
                           name="restrictiveCondition">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土壤" required="required"
                           name="soil">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="地形地势"
                           required="required" name="topographicTerrain">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="土地面积(请输入数字)" required="required" name="landArea">
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/estate/sonEstateView.jsp" %>