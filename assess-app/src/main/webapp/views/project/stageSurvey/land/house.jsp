<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房屋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicHouseFrm">
        <input type="hidden" name="id" value="${basicHouse.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required placeholder="房号" name="houseNumber"
                           class="form-control" value="${basicHouse.houseNumber}" id="txt_House_search">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在楼层<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="所在楼层" name="floor" required
                           class="form-control" value="${basicHouse.floor}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" class="form-control" name="floorDesc" value="${basicHouse.floorDesc}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="huxingId" value="${basicHouse.huxingId}">
                        <c:if test="${empty isApplyBatch}">
                        <input type="text" readonly="readonly" onclick="houseCommon.selectHuxing(this);"
                               placeholder="户型" class="form-control" name="huxingName"
                               value="${basicHouse.huxingName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="houseCommon.selectHuxing(this);">
                             <i class="fa fa-search"></i>
                        </button>
                        </c:if>
                        <c:if test="${isApplyBatch eq 'show'}">
                             <input type="text" readonly="readonly" onclick="houseCommon.selectHuxing2(this,${tableId});"
                                    placeholder="户型" class="form-control" name="huxingName"
                                    value="${basicHouse.huxingName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="houseCommon.selectHuxing2(this,${tableId});">
                             <i class="fa fa-search"></i>
                        </button>
                        </c:if>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_huxing_plan"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control spatialDistribution" name="spatialDistribution" >
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="255" placeholder="空间布局描述"
                           name="spatialDistributionDesc"
                           class="form-control" value="${basicHouse.spatialDistributionDesc}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">朝向<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 orientation" name="orientation" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="面积" name="area" data-rule-number="true" required
                           class="form-control" value="${basicHouse.area}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="255" placeholder="面积描述"
                           name="areaDesc" class="form-control" value="${basicHouse.areaDesc}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">调查方式<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 researchType" name="researchType" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">实际用途<span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control search-select select2 practicalUse" name="practicalUse" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control useCondition" name="useCondition" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid" style="display: none;">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用情况描述<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" required
                              name="useConditionDescription" id="useConditionDescription">${basicHouse.useConditionDescription}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋平面图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="house_img_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="x_content">
    <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
    <%@include file="/views/project/stageSurvey/common/houseRoom.jsp" %>
    <c:if test="${formType eq 'residence'}">
        <%@include file="/views/project/stageSurvey/common/houseWater.jsp" %>
        <%@include file="/views/project/stageSurvey/common/houseWaterDrain.jsp" %>
        <%@include file="/views/project/stageSurvey/common/houseNewWind.jsp" %>
        <%@include file="/views/project/stageSurvey/common/houseAirConditioner.jsp" %>
        <%@include file="/views/project/stageSurvey/common/houseHeating.jsp" %>
        <%@include file="/views/project/stageSurvey/common/houseIntelligent.jsp" %>
    </c:if>
    <c:if test="${formType eq 'industry'}">
        <%@include file="/views/project/stageSurvey/common/houseCorollaryEquipment.jsp" %>
    </c:if>
    <%@include file="/views/project/stageSurvey/common/houseDamagedDegree.jsp" %>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonHouseView.js"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })
</script>