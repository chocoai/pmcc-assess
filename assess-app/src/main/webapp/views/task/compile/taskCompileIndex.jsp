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

            <form id="frm_compile" class="form-horizontal">
                <c:forEach items="${compileReportDetailsList}" var="item">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>${item.categoryFieldName}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                内容
                            </label>
                            <div class="col-sm-11">
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="hidden" name="template" value="${item.template}">
                                <textarea class="form-control"
                                          name="content">${empty item.content?item.template:item.content}</textarea>
                            </div>
                        </div>
                        <div class="content-field">
                        </div>
                    </div>
                </c:forEach>
            </form>
            <script type="text/javascript">
                $(function () {
                    getAllField();
                })
                //找出所有模板具有的字段
                function getAllField() {
                    $("#frm_compile").find('[name="template"]').each(function () {
                        var panel = $(this).closest('.x_panel');
                        var fieldArray = AssessCommon.extractField($(this).val());
                        if (fieldArray && fieldArray.length > 0) {
                            var html = createDynaicFieldHtml(fieldArray, 'fieldReplace');
                            panel.find('.content-field').empty().append(html);
                        }
                    })
                }

                //创建动态字段html
                function createDynaicFieldHtml(fieldArray, functionName) {
                    if (fieldArray) {
                        var resultHtml = '<div class="form-group">';
                        $.each(fieldArray, function (i, item) {
                            if (i > 0 && i % 3 == 0) {
                                resultHtml += '</div><div class="form-group">';
                            }
                            var templateHtml = $("#dynamicFieldHtml").html();
                            templateHtml = templateHtml.replace(/{name}/g, item).replace(/{functionName}/, functionName);
                            resultHtml += templateHtml;
                        })
                        resultHtml += '</div>';
                        return resultHtml;
                    } else {
                        return '';
                    }
                }

                //字段替换
                function fieldReplace(_this) {
                    //1.先找到模板 2.再依次找到字段填写的信息
                    var tabPane = $(_this).closest(".x_panel");
                    var template = tabPane.find('[name="template"]').val();
                    tabPane.find('.content-field').find('input:text').each(function () {
                        if ($(this).val()) {
                            template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
                        }
                    })
                    tabPane.find('[name="content"]').val(template);
                }
            </script>

            <!--动态字段-->
            <script type="text/html" id="dynamicFieldHtml">
                <label class="col-sm-1 control-label">
                    {name}
                </label>
                <div class="x-valid">t
                    <div class="col-sm-3">
                        <input type="text" class="form-control" data-name="{name}" onkeyup="{functionName}(this);">
                    </div>
                </div>
            </script>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
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
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3"
                                           value="${projectPlanDetails.actualHours}">
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
                                                  class="form-control">${projectPlanDetails.taskRemarks}</textarea>
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
<%--<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>--%>
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


    //提交
    function submit() {
        if (!$("#frm_task").valid()) {
            return false;
        }
        var data = {};
        data.compileReportDetailsList = [];
        $('#frm_compile').find('.x_panel').each(function () {
            var compileReportDetails = {};
            compileReportDetails.id = $(this).find('[name="id"]').val();
            compileReportDetails.content = $(this).find('[name="content"]').val();
            data.compileReportDetailsList.push(compileReportDetails);
        })
        var formData = JSON.stringify(data);
        if ("${processInsId}" != "0") {
            submitEditToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }


</script>

</html>

