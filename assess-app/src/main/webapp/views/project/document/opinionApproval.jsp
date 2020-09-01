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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
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
                                        意见稿信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="opinionApprovalFrm" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class='form-inline x-valid'>
                                            <label class='col-sm-1 control-label'>
                                                标题<span class="symbol required"></span>
                                            </label>
                                            <div class='col-sm-11'>
                                                <label class="form-control input-full">${documentOpinion.title}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    ${fieldsHtml}
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                报告
                                            </label>
                                            <div class="col-sm-11">
                                                <div id="_file_report">
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                意见稿
                                            </label>
                                            <div class="col-sm-11">
                                                <div id="_file_upload">
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${fn:contains(activityCnName,'项目经理') eq true}">
                                            <input type="hidden" id="id" name="id" value="${documentOpinion.id}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                                        处理意见<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control input-full" id="suggestion" name="suggestion" rows="4" required
                                          data-rule-maxlength="255"
                                          placeholder="">${documentOpinion.suggestion}</textarea>
                                                    </div>
                                                </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">回函附件</label>
                                                    <div class="col-sm-11">
                                                        <input id="reply_letter" type="file" multiple="false">
                                                        <div id="_reply_letter"></div>
                                                    </div>
                                                </div>
                                                </div>
                                            </div>
                                            <script type="text/javascript">
                                                $(function () {
                                                    loadUploadFiles("reply_letter", "tb_document_opinion", '${documentOpinion.id}', "reply_letter", true);
                                                    FileUtils.uploadFiles({
                                                        target: "reply_letter",
                                                        disabledTarget: "btn_submit",
                                                        formData: {
                                                            tableName: "tb_document_opinion",
                                                            tableId: ${documentOpinion.id},
                                                            fieldsName: "reply_letter",
                                                            projectId: "${projectPlanDetails.projectId}"
                                                        },
                                                        deleteFlag: true
                                                    }, {
                                                        onUploadComplete: function () {
                                                            loadUploadFiles("reply_letter", "tb_document_opinion", '${documentOpinion.id}', "reply_letter", true);
                                                        }
                                                    });
                                                })
                                            </script>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                                        处理意见<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-md-11 col-sm-11 col-xs-12">
                                                        <label class="form-control input-full">${documentOpinion.suggestion}</label>
                                                    </div>
                                                </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">回函附件</label>
                                                    <div class="col-sm-11">
                                                        <div id="_reply_letter_d"></div>
                                                    </div>
                                                </div>
                                                </div>
                                            </div>
                                            <script type="text/javascript">
                                                $(function () {
                                                    loadUploadFiles("reply_letter_d", "tb_document_opinion", '${documentOpinion.id}', "reply_letter", false);
                                                })
                                            </script>
                                        </c:otherwise>
                                    </c:choose>
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
        //显示报告
        loadUploadFiles("file_report", AssessDBKey.GenerateReportInfo, '${documentOpinion.generationId}', '${fieldsName}', false);
        //合同
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_opinion",
                tableId: ${documentOpinion.id},
                fieldsName: '${fieldsName}',
                projectId:${documentOpinion.projectId}
            },
            editFlag: true,
            deleteFlag: false
        });
    })

    //显示附件
    function loadUploadFiles(target, tableName, tableId, fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: tableId,
                fieldsName: fieldsName,
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: deleteFlag
        })
    }

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }

        var data = {};
        data.formData = JSON.stringify(formParams("opinionApprovalFrm"));
        var approvalData = formApproval.getFormData();
        data = $.extend({}, approvalData, data);
        console.log(data)
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/documentOpinion/approvalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

</script>

</html>

