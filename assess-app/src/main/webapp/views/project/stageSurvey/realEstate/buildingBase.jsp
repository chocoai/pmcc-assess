<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/common_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        信息填写
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showHistoryModal();">历史记录
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showCaseQuoteModal();">引用案例
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="applyInfoQuote.showCaseAlternativeModal('${applyBatchDetail.id}');">引用备选案例
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <form class="form-horizontal" id="basicBuildingFrm">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <input type="hidden" name="id" value="${basicBuilding.id}">
                                                    <input type="hidden" name="quoteId"
                                                           value="${basicBuilding.quoteId}">
                                                    <input type="hidden" name="estateId"
                                                           value="${basicBuilding.estateId}">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">
                                                                    名称<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <div class="input-group">
                                                                        <input type="text" id="txt_building_search"
                                                                               data-rule-maxlength="100"
                                                                               placeholder="名称"
                                                                               required="required"
                                                                               name="buildingNumber"
                                                                               class="form-control"
                                                                               onblur="buildingCommon.buildingNumberBlur(this);"
                                                                               value="${basicBuilding.buildingNumber}">
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-info btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="buildingCommon.mapMarker(false);">
                                                                                标注
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    土地使用年限
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="number" placeholder="土地使用年限(数字)"
                                                                           data-rule-number='true'
                                                                           name="landUseYear"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.landUseYear}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    楼栋所在位置<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <div class="input-group">
                                                                        <select class="form-control form-control" required
                                                                                name="reference">
                                                                            <option value="" selected="">-请选择-</option>
                                                                            <option value="楼盘内">楼盘内</option>
                                                                            <option value="楼盘大门">楼盘大门</option>
                                                                        </select>
                                                                        <select class="form-control form-control" required
                                                                                name="orientation">
                                                                            <option value="" selected="">-请选择-</option>
                                                                            <option value="左面">左面</option>
                                                                            <option value="右面">右面</option>
                                                                            <option value="正面">正面</option>
                                                                            <option value="南面">南面</option>
                                                                            <option value="北面">北面</option>
                                                                            <option value="东面">东面</option>
                                                                            <option value="东面">东面</option>
                                                                            <option value="西面">西面</option>
                                                                            <option value="东北">东北</option>
                                                                            <option value="东南面">东南面</option>
                                                                            <option value="东北面">东北面</option>
                                                                            <option value="西南面">西南面</option>
                                                                            <option value="西北面">西北面</option>
                                                                        </select>
                                                                    </div>
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
                                                                    <input type="number" placeholder="总层数"
                                                                           data-rule-number="true"
                                                                           name="floorCount"
                                                                           class="form-control input-full"
                                                                           required="required"
                                                                           value="${basicBuilding.floorCount}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    建筑高度
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="number" placeholder="建筑高度(数字)"
                                                                           data-rule-number='true'
                                                                           name="buildingHeight"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.buildingHeight}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    占地面积(平方米)
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="number" placeholder="占地面积(数字)"
                                                                           data-rule-number='true'
                                                                           name="coverAnArea"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.coverAnArea}">
                                                                </div>
                                                            </div>
                                                        </div>
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
                                                                    <select name="buildingStructureCategory"
                                                                            class="form-control input-full buildingStructureCategory">
                                                                    </select>
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
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">
                                                                    建筑使用年限<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <c:if test="${basicApply.type == 0 || basicApply.type==null || basicApply.type == 3}">
                                                                        <select name="residenceUseYear" required
                                                                                class="form-control input-full residenceUseYear  ">
                                                                        </select>
                                                                    </c:if>
                                                                    <c:if test="${basicApply.type == 1}">
                                                                        <select name="industryUseYear" required
                                                                                class="form-control input-full industryUseYear  ">
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
                                                                    <select name="betweenDistance" required
                                                                            class="form-control input-full betweenDistance  ">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1">最小楼间距倍数</label>
                                                                <div class="col-md-3">
                                                                    <div class="input-group">
                                                                        <input type="text" required="required"
                                                                               name="minimumFloorDistance"
                                                                               placeholder="最小楼间距倍数"
                                                                               class="form-control form-control"
                                                                               onblur="buildingCommon.minimumFloorDistanceEvent(this);"
                                                                               list="build_minimumFloorDistance_data"
                                                                               value="${basicBuilding.minimumFloorDistance}">

                                                                        <div class="input-group-append">
                                                                            <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">选择</button>
                                                                            <div class="dropdown-menu" id="build_minimumFloorDistance_data">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <input type="hidden" placeholder="楼间距描述"
                                                                           class="form-control input-full"
                                                                           name="betweenDistanceDescription"
                                                                           value="${basicBuilding.betweenDistanceDescription}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    竣工时间获取方式<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <select name="completedTimeType" required
                                                                            class="form-control input-full completedTimeType  ">
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
                                                                           name="beCompletedTime" required
                                                                           data-date-format="yyyy-mm-dd"
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
                                                                    <input type="text" placeholder="建筑公司"
                                                                           class="form-control input-full"
                                                                           name="builderName"
                                                                           value="${basicBuilding.builderName}">
                                                                    <input type="hidden" placeholder="建筑公司"
                                                                           class="form-control input-full"
                                                                           name="builder"
                                                                           value="${basicBuilding.builder}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    配套公共设施使用费
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="number" placeholder="配套公共设施使用费(数字)"
                                                                           name="facilitiesUseFee"
                                                                           data-rule-number='true'
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.facilitiesUseFee}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    物业公司名称
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" name="propertyName"
                                                                           placeholder="物业公司名称"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.propertyName}">
                                                                    <input type="hidden" name="property"
                                                                           placeholder="物业公司 "
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.property}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">
                                                                    物业公司性质
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <select name="propertyCompanyNature"
                                                                            class="form-control input-full">
                                                                        <option value="">-请选择-</option>
                                                                        <c:forEach items="${unitPropertiesList}"
                                                                                   var="item">
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
                                                                    <select name="propertySocialPrestige"
                                                                            class="form-control input-full">
                                                                        <option value="">-请选择-</option>
                                                                    </select>
                                                                </div>
                                                                <label class="col-md-1">
                                                                    街道号
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <select name="streetInfoId"
                                                                            class="form-control input-full">
                                                                        <c:forEach var="item" items="${streetInfoList}">
                                                                            <option value="${item.id}">${item.streetNumber}</option>
                                                                        </c:forEach>
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
                                                                    <div id="toolbarBuildingPropertyServiceItemTable"
                                                                         style="display: none">
                                                                        <div class="btn-group">
                                                                            <button type="button"
                                                                                    class="btn btn-warning btn-sm"
                                                                                    onclick="buildingCommon.deleteBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable')">
                                <span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>删除
                                                                            </button>
                                                                            <button type="button"
                                                                                    class="btn btn-primary btn-sm"
                                                                                    onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',true)">
                                <span class="btn-label">
												<i class="fa fa-pen"></i>
											</span>编辑
                                                                            </button>
                                                                            <button type="button"
                                                                                    class="btn btn-success btn-sm"
                                                                                    onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',false)">
                                <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>添加
                                                                            </button>
                                                                        </div>
                                                                    </div>

                                                                    <table class="table table-bordered"
                                                                           id="basicBuildingPropertyServiceItemTable"
                                                                           data-toolbar="#toolbarBuildingPropertyServiceItemTable">

                                                                    </table>
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
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include file="/views/project/stageSurvey/common/buildingMaintenance.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/buildingSurface.jsp" %>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
                            </button>
                            <button type="button" class="btn btn-warning" style="margin-left: 10px;"
                                    onclick="saveDataInfo();">保存
                            </button>
                        </div>
                    </div>
                    <%@include file="/views/project/stageSurvey/common/canvasQRcode.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<div id="basicBuildingPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
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
                                                        <select required="required" name="serviceContent"
                                                                class="form-control input-full">
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
                                                        <input type="text" class="form-control input-full"
                                                               name="serviceTime"
                                                               placeholder="服务时间" required="required">
                                                    </div>
                                                    <label class="col-sm-2">
                                                        等级评价<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-4">
                                                        <select required="required" name="gradeEvaluation"
                                                                class="form-control input-full  ">
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
                                                        <input type="text" class="form-control input-full"
                                                               name="chargesNotes"
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="buildingCommon.addBasicBuildingPropertyServiceItem(this);">
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
                                            <select required="required" name="serviceContent"
                                                    class="form-control input-full">
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
                                                    class="form-control input-full  ">
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/building.case.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/property.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/builder.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>

<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
        $("#txt_building_search").apBuilding({
            quoteId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                applyInfoQuote.showCaseOtherModal('${quoteId}','${applyBatchDetail.id}');
            }
        });
        buildingCommon.autocompleteStart();
    })

    //保存数据信息
    function saveDataInfo() {
        var item = {};
        item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
        item.basicBuilding.vSpecifications = [];
        buildingCommon.buildingForm.find('.form-group').each(function () {
            var vSpecification = {};
            var specificationName = $(this).find('[name^=specificationName]').val();
            var specificationContent = $(this).find('[name^=specificationContent]').val();
            if (specificationName && specificationContent) {
                vSpecification.specificationName = specificationName;
                vSpecification.specificationContent = specificationContent;
                item.basicBuilding.vSpecifications.push(vSpecification);
            }
        });
        var formData = JSON.stringify(examineCommon.getFormData());
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                formData: formData,
                formClassify: '${tbType}',
                planDetailsId: '${planDetailsId}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "保存数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败,失败原因:" + result.errmsg);
                }
            }
        });
    }

    function showHistoryModal() {
        historyInfo.caseBuild.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        applyInfoQuote.showCaseOtherModal('${quoteId}','${applyBatchDetail.id}');
    }
</script>