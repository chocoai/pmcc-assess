<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        信息填写
                        <c:if test="${not empty planDetailsId}">
                            <small>
                                <input type="button" class="btn btn-xs btn-primary" value="历史记录"
                                       onclick="showHistoryModal();">
                            </small>
                            <small>
                                <input type="button" class="btn btn-xs btn-primary" value="引用案例"
                                       onclick="showCaseQuoteModal();">
                            </small>
                        </c:if>
                        <c:if test="${empty planDetailsId}">
                            <small>
                                <input type="button" class="btn btn-xs btn-primary" value="引用项目"
                                       onclick="showProjectQuoteModal();">
                            </small>
                        </c:if>

                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <c:if test="${tbType eq 'estate'}">
                    <%@include file="/views/project/stageSurvey/house/estate.jsp" %>
                </c:if>
                <c:if test="${tbType eq 'building'}">
                    <%@include file="/views/project/stageSurvey/house/building.jsp" %>
                </c:if>
                <c:if test="${tbType eq 'unit'}">
                    <%@include file="/views/project/stageSurvey/house/unit.jsp" %>
                </c:if>
                <c:if test="${tbType eq 'house'}">
                    <%@include file="/views/project/stageSurvey/house/house.jsp" %>
                </c:if>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
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
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
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
                planDetailsId: '${planDetailsId}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
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

    //引用项目时打开对应的modal
    function showProjectQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            projectData.prototype.showProjectDataModel();
        }

        //打开楼栋modal
        if ("building" == "${tbType}") {
            projectBuild.prototype.showModel(${quoteId});
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            projectUnit.prototype.showModel(${quoteId});
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            projectHouse.prototype.showModel(${quoteId});
        }

    };

    //打开历史数据modal
    function showHistoryModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            historyInfo.caseEstate.showModel('${tbId}','${formClassify}','${tbType}','${basicApplyBatch.id}');
        }

        //打开楼栋modal
        if ("building" == "${tbType}") {
            historyInfo.caseBuild.showModel('${tbId}','${formClassify}','${tbType}','${basicApplyBatch.id}');
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            historyInfo.caseUnit.showModel('${tbId}','${formClassify}','${tbType}','${basicApplyBatch.id}');
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            historyInfo.caseHouse.showModel('${tbId}','${formClassify}','${tbType}','${basicApplyBatch.id}');
        }

    };
</script>

