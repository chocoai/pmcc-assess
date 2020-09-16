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
<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        发文信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="cmsContractInfo" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    发文标题<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-7">
                                                    <label class="form-control input-full">${documentSend.title}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    ${fieldsHtml}
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-11">
                                                    <div id="_file_upload">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        回传资料
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                回传资料
                                            </label>
                                            <div class="col-sm-7">
                                                <c:if test="${flog=='approval'and activityReName eq lastActivity.name}">
                                                    <input id="post_back_file_upload" name="post_back_file_upload"
                                                           type="file"
                                                           class="uploadifive-button" multiple="false">
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            FileUtils.uploadFiles({
                                                                target: "post_back_file_upload",
                                                                disabledTarget: "btn_submit",
                                                                formData: {
                                                                    tableName: "tb_document_send",
                                                                    tableId: ${documentSend.id},
                                                                    fieldsName: 'postBack',
                                                                    projectId:${documentSend.projectId}
                                                                },
                                                                deleteFlag: true,
                                                                editFlag: true
                                                            });
                                                        })
                                                    </script>
                                                </c:if>
                                                <div id="_post_back_file_upload"></div>
                                                <script type="text/javascript">
                                                    $(function () {
                                                        FileUtils.getFileShows({
                                                            target: "post_back_file_upload",
                                                            formData: {
                                                                tableName: "tb_document_send",
                                                                tableId: ${documentSend.id},
                                                                fieldsName: 'postBack',
                                                                projectId:${documentSend.projectId}
                                                            },
                                                            editFlag: ${flog=='approval' and activityReName eq lastActivity.name},
                                                            deleteFlag: ${flog=='approval' and activityReName eq lastActivity.name}
                                                        });
                                                    })
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_send",
                tableId: ${documentSend.id},
                fieldsName: 'document',
                projectId:${documentSend.projectId}
            },
            editFlag: ${flog=='approval'},
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
            url: "${pageContext.request.contextPath}/documentSend/approvalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

</script>

</html>

