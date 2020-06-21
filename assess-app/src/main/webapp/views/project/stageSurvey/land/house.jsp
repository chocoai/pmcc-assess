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
                                    <div class="card-header">
                                        <div class="card-title">土地类型</div>
                                    </div>
                                    <%@include file="/views/project/stageSurvey/common/estateLandUseCategory.jsp" %>
                                </div>
                                <div class="x_content">
                                    <div class="card-header">
                                        <div class="card-title">
                                            交易信息
                                            <button class="btn btn-sm btn-success" style="margin-left: 5px"
                                                    type="button" onclick="houseLandTrading.appendHtml(false)">
                                                <span class="btn-label"><i
                                                        class="fa fa-plus"></i>
                                                </span>
                                                添加交易信息
                                            </button>
                                        </div>
                                    </div>
                                    <form id="basicHouseFrm" class="form-horizontal">
                                        <input type="hidden" name="id" value="${basicHouse.id}">
                                        <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
                                    </form>
                                </div>
                                <div class="x_content">
                                    <c:if test="${projectPhase eq 'caseStudyExtend'}">
                                        <div id="basicHouseLandTradingAppend"></div>
                                        <%@include file="/views/project/stageSurvey/common/houseTradingLandCase.jsp" %>
                                    </c:if>
                                    <c:if test="${projectPhase ne 'caseStudyExtend'}">
                                        <%@include file="/views/project/stageSurvey/common/houseTradingLandSurvey.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                            <button class="btn btn-warning" onclick="saveDataInfo();">
                                保存<i style="margin-left: 10px" class="fa fa-save"></i>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/house.case.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/select/huxing.select.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/autocomplete/roomType.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

</html>

<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
        if(${projectPhase eq 'caseStudyExtend'}){
            houseLandTrading.init();
        }
    })

    //保存数据信息
    function saveDataInfo() {
        if (!houseCommon.houseForm.valid()) {
            return false;
        }
        if (!houseCommon.landCategoryInfoForm.valid()) {
            return false;
        }
        Loading.progressShow();
        var data = examineCommon.getFormData();
        data.basicHouse.area = houseCommon.houseTradingForm.find('[id=tempLandArea]').val();
        data.basicHouseTradingGroups = [];
        var forms = $("#basicHouseLandTradingAppend").find("form");
        $.each(forms, function (i, n) {
            var text = $(n).attr("id");
            if (text.indexOf(houseLandTrading.basicHouseTradingFrm) != -1) {
                var item = formParams(text);
                item.bisMark = $(this).closest('.col-md-12').find("input[name='bisMark']").prop("checked");
                data.basicHouseTradingGroups.push(item);
            }
        });
        var formData = JSON.stringify(data);
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

    //打开历史数据modal
    function showHistoryModal() {
        historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        caseFun.caseHouse.showModel(${quoteId});
    }

    function showProjectQuoteModal() {
        projectHouse.prototype.showModel();
    }


</script>



