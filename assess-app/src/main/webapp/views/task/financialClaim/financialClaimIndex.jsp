<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>

            <c:forEach var="item" items="${projectDetailsTask}">
                <c:if test="${item.returnDetailsId>0}">
                    <c:if test="${projectPlanDetailsVo.declareFormName!='loanCustomInfoAssist'}">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>客户信息</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <jsp:include page="/views/task/financialClaim/share/loanCustomInfoAssistDisplay.jsp"></jsp:include>
                            </div>
                        </div>
                    </c:if>
                </c:if>
                <div class="x_panel">
                    <div class="x_title">
                        <h2>${item.projectPhaseName}</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <input type="hidden" class="css_detailsId" id="${item.declareFormName}_details_id" value="${item.id}">
                        <jsp:include page="/views/task/financialClaim/share/${item.declareFormName}.jsp"></jsp:include>

                    </div>
                </div>
            </c:forEach>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {

        $("#frm_task").validate();

        loadUploadFiles();
        //上传附件
        FileUtils.uploadFiles({
            target: "apply_file",
            showFileList: false,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        }, {
            onUploadComplete: function () {
                loadUploadFiles();
            }
        });
    });
    //显示附件
    function loadUploadFiles() {
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    }


    function submit() {

        var tf;
        <c:forEach var="item" items="${projectDetailsTask}">
        tf = ${item.declareFormName}Submit();
        if (tf == false) {

            return false;
        }

        </c:forEach>

        Loading.progressShow();

        var objs = $(".css_detailsId");
        var projectDetailsId = "";
        $.each(objs, function (i, j) {
            projectDetailsId += $(j).val() + ",";
        });
        if (projectDetailsId != "") {
            projectDetailsId = projectDetailsId.substring(0, projectDetailsId.length - 1);
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/planFinancialClaim/submitTask",
            data: {
                projectDetailsIds: projectDetailsId,
                projectDetailsId: "${projectPlanDetails.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

</script>

</html>

