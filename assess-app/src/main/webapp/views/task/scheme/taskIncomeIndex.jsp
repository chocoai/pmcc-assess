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
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <%@include file="/views/share/evaluationPrinciplePublic.jsp" %>
            <%@include file="/views/share/evaluationHypothesisPublic.jsp" %>
            <%@include file="/views/share/evaluationBasisPublic.jsp" %>



            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}成果提交</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_task" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="实际工时" data-rule-number='true'
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" id="taskRemarks" name="taskRemarks"
                                                  class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <input id="apply_file" name="apply_file" type="file" multiple="false">
                                <div id="_apply_file">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>

                        <input type="button" onclick="frmTaskSave()" value="save" class="btn btn-succcess">
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
    function frmTaskSave() {
        //js校验
        if (!$("#frm_task_evaluationPrinciPleTemple").valid()) {
            return false;
        }
        if (!$("#frm_task_evaluationHypothesis").valid()) {
            return false;
        }
        if (!$("#frm_task_evaluationBasis").valid()) {
            return false;
        }
        var json = "";
        //数据收集
        var princiPle = formParams("frm_task_evaluationPrinciPleTemple");
        var hypothesis = formParams("frm_task_evaluationHypothesis");
        var basis = formParams("frm_task_evaluationBasis");
        var data = {};
        data.princiPle = princiPle;
        data.hypothesis = hypothesis;
        data.basis = basis;
        //合并json
        json = JSON.stringify(data);
        console.log(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeInfo/save",
            type: "POST",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                console.log(result);
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

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
        },{
            onUploadComplete:function () {
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
        if (!$("#frm_task").valid()) {
            return false;
        }
        //js校验
        if (!$("#frm_task_evaluationPrinciPleTemple").valid()) {
            return false;
        }
        if (!$("#frm_task_evaluationHypothesis").valid()) {
            return false;
        }
        if (!$("#frm_task_evaluationBasis").valid()) {
            return false;
        }
        var json = "";
        //数据收集
        var princiPle = formParams("frm_task_evaluationPrinciPleTemple");
        var hypothesis = formParams("frm_task_evaluationHypothesis");
        var basis = formParams("frm_task_evaluationBasis");
        var data = {};
        data.princiPle = princiPle;
        data.hypothesis = hypothesis;
        data.basis = basis;
        //合并json
        json = JSON.stringify(data);

        if ("${processInsId}" != "0") {
            submitEditToServer("", $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer("formData="+json, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }

</script>

</html>

