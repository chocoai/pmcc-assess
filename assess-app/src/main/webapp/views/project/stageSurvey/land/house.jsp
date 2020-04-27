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
                                <form id="basicHouseFrm" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicHouse.id}">
                                    <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
                                </form>
                                <div class="x_content">
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">财产范围<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 scopeProperty"
                                                                name="scopeProperty"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 control-label">范围包括</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="范围包含" name="scopeInclude"
                                                               class="form-control input-full"
                                                               value="${basicHouseTrading.scopeInclude}">
                                                    </div>
                                                    <label class="col-sm-1 control-label">范围不包括</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="范围不包含" name="scopeNotInclude"
                                                               class="form-control input-full"
                                                               value="${basicHouseTrading.scopeNotInclude}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">税费负担<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 taxBurden"
                                                                name="taxBurden"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 control-label">交易情况<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full transactionSituation"
                                                                name="transactionSituation"
                                                                required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 control-label">价格类型<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full " name="priceType"
                                                                required>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group" id="abnormalTransaction" style="display: none;">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">说明事项类型</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full descriptionType"
                                                                name="descriptionType">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1 control-label">说明事项内容</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" placeholder="说明事项内容"
                                                               name="descriptionContent"
                                                               class="form-control input-full"
                                                               value="${basicHouseTrading.descriptionContent}">
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="x_title">
                                            <div class="clearfix"></div>
                                        </div>
                                        <%@include file="/views/project/stageSurvey/common/houseTradingBody.jsp" %>
                                    </form>
                                </div>
                                <%@include file="/views/project/stageSurvey/common/houseTradingBodyBox.jsp" %>
                                <%@include file="/views/project/tool/landLevelModalView.jsp" %>
                                <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
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
        //打开楼盘modal
        historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            caseFun.caseHouse.showModel();
        }
    }

    function showProjectQuoteModal() {
        projectHouse.prototype.showModel();
    }
</script>


