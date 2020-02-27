<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 公共模块end -->

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        申报编辑文件
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="declareApplyForm">

                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <input type="hidden" name="assetsSettingId" value="${projectPhase.assetsSettingId}">
                                    <%@include file="./assetsAppraisalDicApplyModel.jsp" %>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- 公共尾部模块引用 -->
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:choose>
                                <c:when test="${projectPhase.bisUseBox eq false}">
                                    <button id="btn_submit" class="btn btn-success"
                                            onclick="submit(false);">
                                        直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                    </button>
                                    <button id="btn_submit" class="btn btn-primary"
                                            onclick="submit(true);">
                                        提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button id="btn_submit" class="btn btn-success"
                                            onclick="submit();">
                                        提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <%--返回修改--%>
                    <c:if test="${processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>
<script type="application/javascript">
    //提交表单
    function submit(mustUseBox) {
        var frm = $("#declareApplyForm");
        if (!frm.valid()) {
            return false;
        }
        if (!dataAssetsAppraisalDic.valid(frm)) {
            return false;
        }
        var formData = formSerializeArray(frm);
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }
</script>

</html>

