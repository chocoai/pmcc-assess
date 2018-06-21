<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>
<body>
<div class="main-content" style="margin:0px;">
    <div class="container" style="min-height:0">
        <div class="panel-body">
            <div class="row">
                <%@include file="/views/share/form_head.jsp" %>
                <%@include file="/views/share/project/projectInfo.jsp" %>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-external-link-square"></i> 申请信息
                    </div>
                    <div class="panel-body">
                        <form id="frm_apply" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        项目名称
                                    </label>
                                    <div class="col-sm-11">
                                        <input type="hidden" id="projectId" name="projectId"
                                               value="${projectClose.projectId}">
                                        <label class="form-control">${projectClose.projectName}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        终止原因
                                    </label>
                                    <div class="col-sm-11">
                                        <textarea required placeholder="终止原因" id="reason" name="reason"
                                                  class="form-control">${projectClose.reason}</textarea>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id="id" name="id" value="${projectClose.id}">
                            <input type="hidden" id="boxId" name="boxId" value="${boxId}">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">
                                    附件
                                </label>
                                <div class="col-sm-11">
                                    <input id="apply_file" name="apply_file" type="file" multiple="false">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </form>
                        <%@include file="/views/share/form_apply.jsp" %>
                    </div>
                </div>
                <c:if test="${taskId!=''}">
                    <%@include file="/views/share/form_log.jsp" %>
                </c:if>


            </div>
        </div>
    </div>
    <%@include file="/views/share/main_footer.jsp" %>
    <script type="application/javascript">
        $(function () {
            uploadFiles("apply_file", "saveform", {},
                {
                    tableName: "tb_project_close",
                    processInsId: "${processInsId}",
                    tableId: "${projectClose.id}",
                    reActivityName: "${activityReName}",
                    projectId: "${projectClose.projectId}"
                }
                , function (data) {
                    //alert(data);
                });
            GetFileShows("apply_file",
                {
                    tableName: "tb_project_close",
                    processInsId: "${processInsId}",
                    tableId: ${projectClose.id},
                    projectId: "${projectClose.projectId}"
                }, 0);
            $("#frm_apply").validate();
        })
        //保存数据到数据库
        function saveform() {
            if ($("#frm_apply").valid()) {
                Loading.progressShow();
                var url = "";
                if ("${processInsId}" == "0") {
                    url = "${pageContext.request.contextPath}/projectClose/saveClose";
                }
                else {
                    url = "${pageContext.request.contextPath}/projectClose/saveEditClose";
                }
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: formParams("frm_apply"),
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
                })
            }
        }
    </script>

</body>
</html>
