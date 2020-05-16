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
                                        <div class="card-title">土地类型类别</div>
                                    </div>
                                    <%@include file="/views/project/stageSurvey/common/estateLandUseCategory.jsp" %>
                                </div>
                                <div class="x_content">
                                    <div class="card-header">
                                        <div class="card-title">交易信息</div>
                                    </div>
                                <form id="basicHouseFrm" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicHouse.id}">
                                    <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
                                </form>
                                </div>
                                <div class="x_content">
                                <c:if test="${projectPhase eq 'caseStudyExtend'}">
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
<div id="divBoxTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">&times;</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTradingLeaseAndSell" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <!--lease -->
                                <div class="lease">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    租金支付时间止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="租金支付时间起"
                                                           name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    租金增长比率<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="text" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="rentGrowthRate"
                                                           placeholder="租金增长比率(请输入数字)" required="required">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--sell -->
                                <div class="sell">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付时间起<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起"
                                                           name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-2">
                                                    分期支付时间起止<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input required="required" placeholder="分期支付时间起止"
                                                           name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2">
                                                    分期支付利息<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-4">
                                                    <input type="text" data-rule-number='true'
                                                           class="form-control input-full"
                                                           name="instalmentInterest"
                                                           placeholder="分期支付利息(请输入数字)" required="required">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseCommon.saveTradingSellAndLease()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
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
    })

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var item = {};
        item.basicHouse = formSerializeArray(houseCommon.houseForm);
        item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
        item.basicDamagedDegree = damagedDegree.getFormData();
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

    //打开历史数据modal
    function showHistoryModal() {
        historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        caseFun.caseHouse.showModel();
    }

    function showProjectQuoteModal() {
        projectHouse.prototype.showModel();
    }
    

   
</script>



