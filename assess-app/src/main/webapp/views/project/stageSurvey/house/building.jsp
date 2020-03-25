<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form class="form-horizontal" id="basicBuildingFrm">
        <div class="row">
            <div class="col-md-12">
                <div class="card-body">
        <input type="hidden" name="id" value="${basicBuilding.id}">
        <input type="hidden" name="quoteId" value="${basicBuilding.quoteId}">
        <input type="hidden" name="estateId" value="${basicBuilding.estateId}">
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    名称<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <div class="input-group">
                        <input type="text" id="txt_building_search" data-rule-maxlength="100" placeholder="名称"
                               required="required"
                               name="buildingNumber" class="form-control"
                               onblur="buildingCommon.buildingNumberBlur(this);"
                               value="${basicBuilding.buildingNumber}">
                        <span class="input-group-btn">
                             <input type="hidden" name="mapId" value="${basicBuilding.mapId}">
                             <div onclick="buildingCommon.mapMarker(false);" class="btn btn-info">
                                 <i class="fa fa-map-marker"></i> 标注
                             </div>
                        </span>
                    </div>
                </div>
                <label class="col-sm-1">
                    物业类型
                </label>
                <div class="col-md-3">
                    <select name="propertyType"
                            class="form-control input-full propertyType">
                    </select>
                </div>
                <label class="col-sm-1">
                    物业类别<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <select name="propertyCategory" class="form-control input-full propertyCategory" required>
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    土地使用年限
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control input-full" value="${basicBuilding.landUseYear}">
                </div>
                <label class="col-sm-1">
                    户型区间
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="户型区间" name="unitInterval" class="form-control input-full"
                           value="${basicBuilding.unitInterval}">
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    所在位置<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control input-full" required
                           value="${basicBuilding.location}">
                </div>
                <label class="col-sm-1">
                    首层位置<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="首层位置(数字)" data-rule-number='true' required
                           name="firstFloor" class="form-control input-full" value="${basicBuilding.firstFloor}">
                </div>
                <label class="col-sm-1">
                    最高层<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="最高层(数字)" data-rule-number='true' required
                           name="maxFloor" class="form-control input-full" value="${basicBuilding.maxFloor}">
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    总层数<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="总层数" data-rule-number="true"
                           name="floorCount" class="form-control input-full" required="required"
                           value="${basicBuilding.floorCount}">
                </div>
                <label class="col-sm-1">
                    层高<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true' required
                           name="floorHeight" class="form-control input-full" value="${basicBuilding.floorHeight}">
                </div>
                <label class="col-sm-1">
                    建筑高度
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control input-full" value="${basicBuilding.buildingHeight}">
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    建筑面积
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control input-full" value="${basicBuilding.buildingArea}">
                </div>
                <label class="col-sm-1">
                    占地面积
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control input-full" value="${basicBuilding.coverAnArea}">
                </div>
                <label class="col-sm-1">单元说明</label>
                <div class="col-md-3">
                    <input type="text" name="remark" class="form-control input-full" value="${basicBuilding.remark}">
                </div>
            </div>
            </div>
            <%--<div class="form-inline x-valid">--%>
            <%--<label class="col-sm-1">--%>
            <%--套内面积--%>
            <%--</label>--%>
            <%--<div class="col-md-3">--%>
            <%--<input type="text" placeholder="套内面积"--%>
            <%--name="inJacketArea" class="form-control input-full" value="${basicBuilding.inJacketArea}">--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-inline x-valid">--%>
            <%--<label class="col-sm-1">--%>
            <%--使用面积--%>
            <%--</label>--%>
            <%--<div class="col-md-3">--%>
            <%--<input type="text" placeholder="使用面积"--%>
            <%--name="useArea" class="form-control input-full" value="${basicBuilding.useArea}">--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    建筑结构类型
                </label>
                <div class="col-md-3">
                    <select name="buildingStructureType"
                            class="form-control input-full buildingStructureType">
                    </select>
                </div>
                <label class="col-sm-1">
                    建筑结构类别
                </label>
                <div class="col-md-3">
                    <select name="buildingStructureCategory" class="form-control input-full buildingStructureCategory">
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    建筑使用年限<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
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
                <label class="col-sm-1">
                    外观风格<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <select name="appearanceStyle" required
                            class="form-control input-full appearanceStyle">
                    </select>
                </div>
                <label class="col-sm-1">
                    外观新旧<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <select name="appearanceNewAndOld" required
                            class="form-control input-full appearanceNewAndOld">
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    楼间距<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <select name="betweenDistance" required class="form-control input-full betweenDistance search-select select2">
                    </select>
                </div>
                <label class="col-sm-1">楼间距描述</label>
                <div class="col-md-3">
                    <input type="text" placeholder="楼间距描述" class="form-control input-full" name="betweenDistanceDescription"
                           value="${basicBuilding.betweenDistanceDescription}">
                </div>
                <label class="col-sm-1">
                    竣工时间获取方式<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <select name="completedTimeType" required
                            class="form-control input-full completedTimeType search-select select2">
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" required data-date-format="yyyy-mm-dd"
                           class="form-control input-full date-picker dbdate beCompletedTime">
                </div>
                <label class="col-sm-1">
                    开盘时间
                </label>
                <div class="col-md-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control input-full date-picker dbdate openTime">
                </div>
            <%--<div class="form-inline x-valid">--%>
            <%--<label class="col-sm-1">--%>
            <%--交房时间--%>
            <%--</label>--%>
            <%--<div class="col-md-3">--%>
            <%--<input placeholder="交房时间"--%>
            <%--name="roomTime" data-date-format="yyyy-mm-dd"--%>
            <%--class="form-control input-full date-picker dbdate roomTime">--%>
            <%--</div>--%>
            <%--</div>--%>

                <label class="col-sm-1">
                    工程质量
                </label>
                <div class="col-md-3">
                    <select name="constructionQuality"
                            class="form-control input-full constructionQuality">
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    建筑公司
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="建筑公司" class="form-control input-full" name="builderName"
                           value="${basicBuilding.builderName}">
                    <input type="hidden" placeholder="建筑公司" class="form-control input-full" name="builder"
                           value="${basicBuilding.builder}">
                </div>
                <label class="col-sm-1">
                    物业费<span class="symbol required"></span>
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee" data-rule-number='true' required
                           class="form-control input-full" value="${basicBuilding.propertyFee}">
                </div>
                <label class="col-sm-1">
                    配套公共设施使用费
                </label>
                <div class="col-md-3">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control input-full" value="${basicBuilding.facilitiesUseFee}">
                </div>
            </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    物业公司名称
                </label>
                <div class="col-md-3">
                    <input type="text" name="propertyName" placeholder="物业公司名称" class="form-control input-full"
                           value="${basicBuilding.propertyName}">
                    <input type="hidden" name="property" placeholder="物业公司 " class="form-control input-full"
                           value="${basicBuilding.property}">
                </div>
                <label class="col-sm-1">
                    物业公司性质
                </label>
                <div class="col-md-3">
                    <select name="propertyCompanyNature" class="form-control input-full">
                        <option value="">-请选择-</option>
                        <c:forEach items="${unitPropertiesList}" var="item">
                            <c:choose>
                                <c:when test="${item.id == basicBuilding.propertyCompanyNature}">
                                    <option value="${item.id}"
                                            selected="selected">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.id}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-1">
                    物业公司社会信誉
                </label>
                <div class="col-md-3">
                    <select name="propertySocialPrestige" class="form-control input-full">
                        <option value="">-请选择-</option>
                    </select>
                </div>
            </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">
                    物业服务
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <!-- 这个id是不能更改的bootstrap3 强制如此 toolbar -->
                    <div id="toolbarBuildingPropertyServiceItemTable" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning btn-sm"
                                    onclick="buildingCommon.deleteBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable')">
                                <span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>删除
                            </button>
                            <button type="button" class="btn btn-primary btn-sm"
                                    onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',true)">
                                <span class="btn-label">
												<i class="fa fa-pen"></i>
											</span>编辑
                            </button>
                            <button type="button" class="btn btn-success btn-sm"
                                    onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',false)">
                                <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>添加
                            </button>
                        </div>
                    </div>

                    <table class="table table-bordered" id="basicBuildingPropertyServiceItemTable"
                           data-toolbar="#toolbarBuildingPropertyServiceItemTable">

                    </table>
                </div>
                <div class="col-md-3 col-lg-offset-1 ">
                    <button type="button" class="btn btn-info btn-sm"
                            onclick="buildingCommon.constructionInstallationEngineeringFeeEvent.loadHtml();">
                        建筑安装完工度调查
                    </button>
                </div>
            </div>
            </div>
        </div>


        <div class="row form-group" id="navButtonBuildGroupFileId">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1">平面图<span
                        class="symbol required"></span></label>
                <div class="col-md-3">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control input-full" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
                <label class="col-sm-1">外装图<span
                        class="symbol required"></span></label>
                <div class="col-md-3">
                    <input id="building_figure_outside" placeholder="上传附件" class="form-control input-full" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
                <label class="col-sm-1">外观图<span
                        class="symbol required"></span></label>
                <div class="col-md-3">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control input-full" type="file">
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
<div id="basicBuildingPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编辑服务内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <input type="hidden" name="buildingId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    服务类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <select required="required" name="serviceType"
                                                            class="form-control input-full serviceType"
                                                            onchange="">
                                                        <option value="">-请选择-</option>
                                                    </select>
                                                </div>
                                                <label class="col-sm-2">
                                                    服务内容<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <select required="required" name="serviceContent" class="form-control input-full">
                                                        <option value="">请先选择类型</option>
                                                    </select>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    服务时间
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control input-full" name="serviceTime"
                                                           placeholder="服务时间" required="required">
                                                </div>
                                                <label class="col-sm-2">
                                                    等级评价<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <select required="required" name="gradeEvaluation"
                                                            class="form-control input-full search-select select2">
                                                        <option value="">-请选择-</option>
                                                    </select>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    收费标准<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control input-full" name="chargesNotes"
                                                           placeholder="收费标准" required="required">
                                                </div>
                                            </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="buildingCommon.addBasicBuildingPropertyServiceItem(this);">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<div id="basicBuildingPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">编辑服务内容</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <input type="hidden" name="buildingId">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12">
                            <div class="panel-body">
                                <div class="row form-group">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1">
                                            服务类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="serviceType"
                                                    class="form-control input-full serviceType"
                                                    onchange="">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1">
                                            服务内容<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="serviceContent" class="form-control input-full">
                                                <option value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1">
                                            服务时间
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control input-full" name="serviceTime"
                                                   placeholder="服务时间" required="required">
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1">
                                            等级评价<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="gradeEvaluation"
                                                    class="form-control input-full search-select select2">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1">
                                            收费标准<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10">
                                            <input type="text" class="form-control input-full" name="chargesNotes"
                                                   placeholder="收费标准" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="buildingCommon.addBasicBuildingPropertyServiceItem(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<div class="x_content">
    <%@include file="/views/project/stageSurvey/common/buildingFunction.jsp" %>
    <%@include file="/views/project/stageSurvey/common/buildingOutfit.jsp" %>
    <c:if test="${formType eq 'industry'}">
        <%@include file="/views/project/stageSurvey/common/buildingMaintenance.jsp" %>
        <%@include file="/views/project/stageSurvey/common/buildingSurface.jsp" %>
    </c:if>
</div>

<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>

<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
        $("#txt_building_search").apBuilding({
            caseEstateId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                caseFun.caseBuild.showModel('${quoteId}', name);
            }
        });
        buildingCommon.autocompleteStart();
    })
</script>