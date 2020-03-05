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
                                        <c:if test="${not empty planDetailsId}">
                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showHistoryModal();">历史记录
                                            </button>

                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showCaseQuoteModal();">引用案例
                                            </button>
                                        </c:if>
                                        <c:if test="${empty planDetailsId}">
                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showProjectQuoteModal();">引用备选案例
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <c:if test="${tbType eq 'estate'}">
                                    <%@include file="/views/project/stageSurvey/land/estate.jsp" %>
                                </c:if>
                                <c:if test="${tbType eq 'building'}">
                                    <%@include file="/views/project/stageSurvey/land/building.jsp" %>
                                </c:if>
                                <c:if test="${tbType eq 'unit'}">
                                    <%@include file="/views/project/stageSurvey/land/unit.jsp" %>
                                </c:if>
                                <c:if test="${tbType eq 'house'}">
                                    <%@include file="/views/project/stageSurvey/land/house.jsp" %>
                                </c:if>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
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

    function showCaseQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            caseFun.caseEstate.showModel();
        }
        //打开楼栋modal
        if ("building" == "${tbType}") {
            caseFun.caseBuild.showModel(${quoteId});
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            caseFun.caseUnit.showModel(${quoteId});
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            caseFun.caseHouse.showModel(${quoteId});
        }
    }

    //打开历史数据modal
    function showHistoryModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            historyInfo.caseEstate.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }

        //打开楼栋modal
        if ("building" == "${tbType}") {
            historyInfo.caseBuild.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            historyInfo.caseUnit.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }

    };
</script>

