<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2019-05-31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="row">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>意见稿信息</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class='x-valid'>
                                    <label class='col-sm-1 control-label'>
                                        意见稿标题<span class="symbol required"></span>
                                    </label>
                                    <div class='col-sm-11'>
                                        <label class="form-control">${documentOpinion.title}</label>
                                    </div>
                                </div>
                            </div>
                            ${fieldsHtml}
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告
                                    </label>
                                    <div class="col-sm-11">

                                        <div id="_file_report">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        合同
                                    </label>
                                    <div class="col-sm-11">

                                        <div id="_file_upload">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="/views/share/form_approval.jsp" %>
                <%@include file="/views/share/form_log.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "file_report",
            formData: {
                fieldsName: '${fieldsName}',
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: '${documentOpinion.generationId}'
            },
            deleteFlag: false
        })
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_opinion",
                tableId: ${documentOpinion.id},
                proectId:${documentOpinion.projectId}
            },
            editFlag: true,
            signatureFlag: '${activityCnName}'.indexOf("盖章") > -1,
            deleteFlag: false
        });
    })
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/documentOpinion/approvalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

</script>

</html>

