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
                                                onclick="applyInfoQuote.showCaseAlternativeModal('${applyBatchDetailId}');">引用备选案例
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <form class="form-horizontal" id="basicUnitFrm">
                                        <input type="hidden" name="id" value="${basicUnit.id}">
                                        <input type="hidden" name="quoteId" value="${basicUnit.quoteId}">
                                        <input type="hidden" name="estateId" value="${basicUnit.estateId}">
                                        <input type="hidden" name="buildingId" value="${basicUnit.buildingId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1">名称<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="text" data-rule-maxlength="100"
                                                                               placeholder="名称"
                                                                               required="required"
                                                                               name="unitNumber" class="form-control"
                                                                               value="${basicUnit.unitNumber}"
                                                                               id="txt_Unit_search">
                                                                        <div class="input-group-prepend">
                                                                            <button class="btn btn-info btn-sm "
                                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                    type="button"
                                                                                    onclick="unitCommon.mapMarker(false);">
                                                                                标注
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1">梯户比<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="梯户比"
                                                                           name="elevatorHouseholdRatio"
                                                                           class="form-control input-full"
                                                                           required="required"
                                                                           value="${basicUnit.elevatorHouseholdRatio}">
                                                                </div>
                                                                <label class="col-sm-1">户型数<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="户型数"
                                                                           name="huxingNum"
                                                                           class="form-control input-full"
                                                                           required="required"
                                                                           value="${basicUnit.huxingNum}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="basicUnit"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <%@include file="/views/project/stageSurvey/common/unitCommonPart.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitStairs.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitElevator.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitDecorate.jsp" %>
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
<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/unit.case.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
        $("#txt_Unit_search").apUnit({
            quoteId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                applyInfoQuote.showCaseOtherModal('${quoteId}','${applyBatchDetailId}');
            }
        });
    })

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var item = {};
        item.basicUnit = formSerializeArray(unitCommon.unitForm);
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

    function showHistoryModal() {
        historyInfo.caseUnit.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        applyInfoQuote.showCaseOtherModal('${quoteId}','${applyBatchDetailId}');
    }
</script>