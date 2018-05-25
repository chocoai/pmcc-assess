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

            <div class="x_panel">
                <div class="x_title">
                    <h2>收益法</h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form id="frm_scheme_info_detail" class="form-horizontal">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    经营方式
                                </label>
                                <div class="col-sm-3" id="operationMode">
                                    <input type="radio" name="operationMode" value="0">自营
                                    <input type="radio" name="operationMode" value="1">租赁
                                </div>
                            </div>

                            <div class="x-valid" id="Leasehold">
                                <label class="col-sm-1 control-label">
                                    租赁方式
                                </label>
                                <div class="col-sm-3">
                                    <input type="radio" name="Leasehold" value="0">无限制
                                    <input type="radio" name="Leasehold" value="1">限制
                                </div>
                            </div>

                            <div class="x-valid" id="XLeasehold">
                                <label class="col-sm-1 control-label">租约限制说明<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="租约限制说明"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group" id="YLeasehold">
                            <div class="col-sm-12 panel-footer">
                                xxx
                            </div>
                        </div>


                    </form>
                </div>
            </div>

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

                        <button id="btn_submit" class="btn btn-success" onclick="saveTask();">
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
<script>
    $("#Leasehold").hide();
    $("#XLeasehold").hide();
    $("#YLeasehold").hide();
    $("#operationMode input").each(function () {
        $(this).click(function () {
            if ($(this).val()==1){
                $("#Leasehold").show();
                $("#YLeasehold").hide();
            }else {
                $("#Leasehold").hide();
                $("#XLeasehold").hide();
                $("#YLeasehold").hide();
            }
        });
    });
    $("#Leasehold input[name='Leasehold']").each(function () {
        $(this).click(function () {
            if ($(this).val()==1){
                $("#XLeasehold").show();
                $("#YLeasehold").hide();
            }else {
                $("#YLeasehold").show();
                $("#XLeasehold").hide();
            }
        });
    });
</script>
<script type="application/javascript">
    function saveTask() {
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
        var basisTemple = $("#basisTemple").val();
        var hypothesisTemple = $("#hypothesisTemple").val();
        var principleTemple = $("#principleTemple").val();
        var evaluationPrincipleSelectID = $("#evaluationPrincipleSelectID option:selected").val();
        var hypothesisSelectID = $("#hypothesisSelectID option:selected").val();
        var BasisSelectID = $("#BasisSelectID option:selected").val();

        var content = basisTemple+","+hypothesisTemple+","+principleTemple;
        var DataID = BasisSelectID+","+hypothesisSelectID+","+evaluationPrincipleSelectID;
        var  data = {};
        data.formData = content +"-"+DataID;
        data.id = "${projectPlanDetails.id}";
        if (!$("#frm_task").valid()) {
            return false;
        }

        if ("${processInsId}" != "0") {
            submitEditToServer("", $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer("", $("#taskRemarks").val(), $("#actualHours").val());
        }
    }

</script>

</html>

