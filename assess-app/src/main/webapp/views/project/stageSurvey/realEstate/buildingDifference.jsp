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
            <div class="page-inner">
                <div class="row">
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
                                                onclick="showProjectQuoteModal();">引用备选案例
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
                                                                    <select name="propertyCategory"
                                                                            class="form-control input-full propertyCategory"
                                                                            required>
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    户型区间
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" placeholder="户型区间"
                                                                           name="unitInterval"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.unitInterval}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">
                                                                    首层位置<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" placeholder="首层位置(数字)"
                                                                           data-rule-number='true' required
                                                                           name="firstFloor"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.firstFloor}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    最高层<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" placeholder="最高层(数字)"
                                                                           data-rule-number='true' required
                                                                           name="maxFloor"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.maxFloor}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    层高<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" placeholder="层高(数字)"
                                                                           data-rule-number='true' required
                                                                           name="floorHeight"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.floorHeight}">
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
                                                                    <input type="text" placeholder="建筑面积(数字)"
                                                                           data-rule-number='true'
                                                                           name="buildingArea"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.buildingArea}">
                                                                </div>
                                                                <label class="col-sm-1">
                                                                    物业费<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-md-3">
                                                                    <input type="text" placeholder="物业费(数字)"
                                                                           name="propertyFee" data-rule-number='true'
                                                                           required
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.propertyFee}">
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
                                                                    <input id="building_floor_plan" placeholder="上传附件"
                                                                           class="form-control input-full" type="file">
                                                                    <div id="_building_floor_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1">外装图<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-md-3">
                                                                    <input id="building_figure_outside"
                                                                           placeholder="上传附件"
                                                                           class="form-control input-full" type="file">
                                                                    <div id="_building_figure_outside"></div>
                                                                </div>
                                                                <label class="col-sm-1">外观图<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-md-3">
                                                                    <input id="building_floor_Appearance_figure"
                                                                           placeholder="上传附件"
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
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
                            </button>
                            <button type="button" class="btn btn-warning" onclick="saveDataInfo();">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>
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
            caseEstateId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                caseFun.caseBuild.showModel('${quoteId}', name);
            }
        });
        buildingCommon.autocompleteStart();
    })

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
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
</script>