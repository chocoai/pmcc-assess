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
                                        <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="cmsContractInfo" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    发文标题<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-10">
                                                    <label class="form-control">${documentSend.title}</label>
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
                    <%@include file="/views/share/form_approval.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


<%--<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="row">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>发文信息</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class='x-valid'>
                                    <label class='col-sm-1 control-label'>
                                        发文标题<span class="symbol required"></span>
                                    </label>
                                    <div class='col-sm-11'>
                                        <label class="form-control">${documentSend.title}</label>
                                    </div>
                                </div>
                            </div>
                            ${fieldsHtml}
                            <div class="form-group">
                                <div class="x-valid">
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
</div>--%>
</body>

<script type="text/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_send",
                tableId: ${documentSend.id},
                proectId:${documentSend.projectId}
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
            url: "${pageContext.request.contextPath}/documentSend/approvalSubmit",
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

