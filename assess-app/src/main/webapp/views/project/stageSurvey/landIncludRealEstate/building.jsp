<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <div class="row">
            <div class="col-md-12">
                <div class="card-body">
                    <input type="hidden" name="id" value="${basicBuilding.id}">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋号<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" id="txt_building_search" data-rule-maxlength="100"
                                               placeholder="楼栋号" required="required"
                                               name="buildingNumber" class="form-control"
                                               onblur="buildingCommon.buildingNumberBlur(this);"
                                               value="${basicBuilding.buildingNumber}">
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
                                <label class="col-sm-1 control-label">
                                    楼栋名称<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼栋名称" name="buildingName"
                                           class="form-control input-full" required="required"
                                           value="${basicBuilding.buildingName}">
                                </div>
                                <label class="col-sm-1 control-label">
                                    总层数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="总层数" data-rule-number="true"
                                           name="floorCount" class="form-control input-full" required="required"
                                           value="${basicBuilding.floorCount}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    物业类型
                                </label>
                                <div class="col-sm-3">
                                    <select name="propertyType"
                                            class="form-control input-full propertyType">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">
                                    物业类别
                                </label>
                                <div class="col-sm-3">
                                    <select name="propertyCategory" class="form-control input-full propertyCategory">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">
                                    首层位置
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="首层位置(数字)" data-rule-number='true'
                                           name="firstFloor" class="form-control input-full"
                                           value="${basicBuilding.firstFloor}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    最高层
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="最高层(数字)" data-rule-number='true'
                                           name="maxFloor" class="form-control input-full"
                                           value="${basicBuilding.maxFloor}">
                                </div>
                                <label class="col-sm-1 control-label">
                                    套内面积
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="套内面积"
                                           name="inJacketArea" class="form-control input-full"
                                           value="${basicBuilding.inJacketArea}">
                                </div>
                                <label class="col-sm-1 control-label">
                                    使用面积
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="使用面积"
                                           name="useArea" class="form-control input-full"
                                           value="${basicBuilding.useArea}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    所在位置
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="所在位置" name="location"
                                           class="form-control input-full"
                                           value="${basicBuilding.location}">
                                </div>
                                <label class="col-sm-1 control-label">
                                    建筑使用年限<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <c:if test="${basicApply.type == 0 || basicApply.type==null || basicApply.type == 3}">
                                        <select name="residenceUseYear" required
                                                class="form-control input-full residenceUseYear search-select select2">
                                        </select>
                                    </c:if>
                                    <c:if test="${basicApply.type == 1}">
                                        <select name="industryUseYear" required
                                                class="form-control input-full industryUseYear search-select select2">
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用年限
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                           name="landUseYear" class="form-control input-full"
                                           value="${basicBuilding.landUseYear}">
                                </div>
                                <label class="col-sm-1 control-label">
                                    开盘时间
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="开盘时间"
                                           name="openTime" data-date-format="yyyy-mm-dd"
                                           class="form-control input-full date-picker dbdate openTime">
                                </div>
                                <label class="col-sm-1 control-label">
                                    交房时间
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="交房时间"
                                           name="roomTime" data-date-format="yyyy-mm-dd"
                                           class="form-control input-full date-picker dbdate roomTime">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构类型
                                </label>
                                <div class="col-sm-3">
                                    <select name="buildingStructureType"
                                            class="form-control input-full buildingStructureType">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">
                                    建筑结构类别
                                </label>
                                <div class="col-sm-3">
                                    <select name="buildingStructureCategory"
                                            class="form-control input-full buildingStructureCategory">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    外观风格
                                </label>
                                <div class="col-sm-3">
                                    <select name="appearanceStyle"
                                            class="form-control input-full appearanceStyle">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">
                                    外观新旧
                                </label>
                                <div class="col-sm-3">
                                    <select name="appearanceNewAndOld"
                                            class="form-control input-full appearanceNewAndOld">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">单元说明</label>
                                <div class="col-sm-3">
                                    <input type="text" name="remark" class="form-control input-full"
                                           value="${basicBuilding.remark}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    竣工时间获取方式<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="completedTimeType" required
                                            class="form-control input-full completedTimeType search-select select2">
                                    </select>
                                </div>
                                <label class="col-sm-1 control-label">
                                    竣工时间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="竣工时间"
                                           name="beCompletedTime" required data-date-format="yyyy-mm-dd"
                                           class="form-control input-full date-picker dbdate beCompletedTime">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group" id="navButtonBuildGroupFileId">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">平面图</label>
                                <div class="col-sm-3">
                                    <input id="building_floor_plan" placeholder="上传附件" class="form-control input-full"
                                           type="file">
                                    <div id="_building_floor_plan"></div>
                                </div>
                                <label class="col-sm-1 control-label">外装图</label>
                                <div class="col-sm-3">
                                    <input id="building_figure_outside" placeholder="上传附件"
                                           class="form-control input-full"
                                           type="file">
                                    <div id="_building_figure_outside"></div>
                                </div>
                                <label class="col-sm-1 control-label">外观图</label>
                                <div class="col-sm-3">
                                    <input id="building_floor_Appearance_figure" placeholder="上传附件"
                                           class="form-control input-full" type="file">
                                    <div id="_building_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>
                    </div>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
    })
</script>