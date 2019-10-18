<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id" value="${basicBuilding.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="text" id="txt_building_search" data-rule-maxlength="100" placeholder="楼栋号" required="required"
                               name="buildingNumber" class="form-control" onblur="buildingCommon.buildingNumberBlur(this);" value="${basicBuilding.buildingNumber}">
                        <span class="input-group-btn">
                          <c:if test="${empty isApplyBatch}">
                            <div onclick="buildingCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                          </c:if>
                                <c:if test="${isApplyBatch eq 'show'}">
                                 <div onclick="buildingCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                         class="fa fa-map-marker"></i> 标注</div>
                                </c:if>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required" value="${basicBuilding.buildingName}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="总层数" data-rule-number="true"
                           name="floorCount" class="form-control" required="required" value="${basicBuilding.floorCount}">
                </div>
            </div>
        </div>
        <div  class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyType"
                            class="form-control propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyCategory" class="form-control propertyCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    首层位置
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="首层位置(数字)" data-rule-number='true'
                           name="firstFloor" class="form-control" value="${basicBuilding.firstFloor}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    最高层
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="最高层(数字)" data-rule-number='true'
                           name="maxFloor" class="form-control" value="${basicBuilding.maxFloor}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    套内面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="套内面积"
                           name="inJacketArea" class="form-control" value="${basicBuilding.inJacketArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    使用面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="使用面积"
                           name="useArea" class="form-control" value="${basicBuilding.useArea}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在位置
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="所在位置" name="location" class="form-control" value="${basicBuilding.location}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑使用年限<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <c:if test="${basicApply.type == 0 || basicApply.type==null || basicApply.type == 3}">
                        <select name="residenceUseYear" required class="form-control residenceUseYear search-select select2">
                        </select>
                    </c:if>
                    <c:if test="${basicApply.type == 1}">
                        <select name="industryUseYear" required class="form-control industryUseYear search-select select2">
                        </select>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" value="${basicBuilding.landUseYear}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开盘时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    交房时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureType"
                            class="form-control buildingStructureType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureCategory" class="form-control buildingStructureCategory">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    外观风格
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="appearanceStyle"
                            class="form-control appearanceStyle">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    外观新旧
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="appearanceNewAndOld"
                            class="form-control appearanceNewAndOld">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元说明</label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" name="remark" class="form-control" value="${basicBuilding.remark}">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    竣工时间获取方式<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="completedTimeType" required class="form-control completedTimeType search-select select2">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" required data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime">
                </div>
            </div>
            <div class="x-valid">
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 col-lg-offset-1 ">
                    <button type="button" class="btn btn-xs btn-primary" onclick="buildingCommon.constructionInstallationEngineeringFeeEvent.loadHtml();">
                        建筑安装完工度调查
                    </button>
                </div>
            </div>
        </div>
        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">平面图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外装图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_figure_outside" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="x_content">
    <%@include file="/views/project/stageSurvey/common/buildingFunction.jsp" %>
    <%@include file="/views/project/stageSurvey/common/buildingOutfit.jsp" %>
    <c:if test="${formType eq 'industry'}">
        <%@include file="/views/project/stageSurvey/common/buildingMaintenance.jsp" %>
        <%@include file="/views/project/stageSurvey/common/buildingSurface.jsp" %>
    </c:if>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonBuildView.js"></script>
<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
    })
</script>