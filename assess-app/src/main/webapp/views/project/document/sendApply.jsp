
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                <input type="hidden" id="id" name="id" value="${documentSend.id}">
                                <input type="hidden" id="contractType" name="contractType"
                                       value="${documentSend.contractType}">
                                <input type="hidden" id="projectId" name="projectId" value="${documentSend.projectId}">

                                <form id="cmsContractInfo" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    发文标题<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-7">
                                                    <input type="text" id="title" name="title"
                                                           value="${documentSend.title}"
                                                           required class='form-control input-full'>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    ${fieldsHtml}
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    <button type="button" class="btn btn-primary btn-sm" onclick="previewContract()" data-placement="bottom" data-original-title="生成文件">
                                                         <span class="btn-label">
                                                            <i class="fa fa-file-invoice"></i>
                                                         </span>
                                                        生成文件
                                                    </button>
                                                </label>
                                                <div class="col-sm-11">
                                                    <div id="_file_upload">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-4">
                                                <label class="col-sm-1 control-label">
                                                    <input id="file_upload" name="file_upload" type="file"
                                                         class="uploadifive-button"  multiple="false">
                                                </label>
                                           
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="btn_submit" class="btn btn-primary" style="margin-left: 10px;" onclick="submit();">
                                提交
                            </button>
                        </div>
                    </div>
                    <c:if test="${bisEdit==1}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="frm_approval">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function () {
        loadContractAttachment();
        $("#cmsContractInfo").validate();

        FileUtils.uploadFiles({
            target: "file_upload",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_document_send",
                tableId: $("#id").val(),
                proectId:${documentSend.projectId}
            },
            deleteFlag: true,
            editFlag: true
        });
    })

    function previewContract() {
        if (!$("#cmsContractInfo").valid()) {
            return false;
        }
        var customCmsContractInfo = formSerializeArray($("#cmsContractInfo"));
        var extendConten = JSON.stringify(customCmsContractInfo);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/documentSend/buildDoc",
            data: {
                extendConten: extendConten,
                contractType:${documentSend.contractType},
                projectId:${documentSend.projectId},
                processInsId: '${empty documentSend.processInsId?0:documentOpinion.processInsId}',
                id: $("#id").val(),
                title: $("#title").val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#id").val(result.data);
                    loadContractAttachment();
                }
                else {
                    AlertError("生成合同失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function loadContractAttachment() {
        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: "tb_document_send",
                tableId: $("#id").val(),
                proectId:${documentSend.projectId}
            },
            editFlag: true,
            deleteFlag: true
        });
    }

    function submit() {
        var customCmsContractInfo = formSerializeArray($("#cmsContractInfo"));
        var extendConten = JSON.stringify(customCmsContractInfo);
        if ("${bisEdit}" == "1") {
            saveEditformToServer(extendConten);
        }
        else {
            saveformToServer(extendConten);
        }
    }

    function saveEditformToServer(extendConten) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/documentSend/editSubmit",
            data: {
                appointUserAccount: "",
                boxId: "${boxId}",
                processInsId: "${processInsId}",
                taskId: "${taskId}",
                activityName: "${activityName}",
                activityKey: "${activityReName}",
                activityCnName: "${activityCnName}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                viewUrl: "${viewUrl}",
                workPhaseId: "${phaseId}",
                activityId: "${activityId}",
                extendConten: extendConten,
                contractType:${documentSend.contractType},
                projectId:${documentSend.projectId},
                id: $("#id").val()

            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {

                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function saveformToServer(extendConten) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/documentSend/applySubmit",
            data: {
                appointUserAccount: "",//指定审批人
                boxId: "${boxId}",
                extendConten: extendConten,
                contractType:${documentSend.contractType},
                projectId:${documentSend.projectId},
                id: $("#id").val(),
                title: $("#title").val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }
</script>

</html>

