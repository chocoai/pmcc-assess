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


            <c:forEach items="${compileReportDetailsList}" var="item">
                <form id="frm_compile" class="form-horizontal">
                    <input type="hidden" name="evaluationType" value="${item.categoryFieldName}">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>${item.categoryFieldName}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                内容
                            </label>
                            <div class="col-sm-11">
                                <input type="hidden" name="reportAnalysisId">
                                <input type="hidden" name="template" value="${item.template}">
                                <textarea class="form-control"
                                          name="content">${empty item.content?item.template:item.content}</textarea>
                            </div>
                        </div>
                        <div class="content-field">
                            <label class="col-sm-1 control-label">
                                字段
                            </label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>

            <script type="text/javascript">
                //找出所有模板具有
                function getAllField() {

                }

                //方法适用原因字段替换
                function methodApplicableFieldReplace(_this) {
                    //1.先找到模板 2.再依次找到字段填写的信息
                    var tabPane = $(_this).closest(".tab-pane");
                    var template = tabPane.find('[name="methodTemplate"]').find('option:selected').attr("data-applicable");
                    tabPane.find('.applicableReason-field').find('input:text').each(function () {
                        if ($(this).val()) {
                            template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
                        }
                    })
                    tabPane.find('[name="applicableReason"]').val(template);
                }
            </script>
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

        $("#btn_select_customer").click(function () {
            crmCustomer.select({
                multi: false,//是否允许多选
                onSelected: function (nodes) {
                    console.log(nodes);
                }
            });
        })

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

    //替换自定义字段
    var textId; //被替换文本id
    function textReplaces(name, template, id1, id2) {
        var value = document.getElementById(id2).value; //被替换文本内容
        var newName = 'newName' + id2;    //替换字段id
        var tempName = document.getElementById(newName).innerHTML;  //替换字段内容
        textId = id1;
        if (value != "") {
            if (tempName != name) {
                //第二次替换走这里
                name = tempName;
                var regex = '/' + name + '/g';
                var temp = document.getElementById(id1).innerHTML;
                var text = temp.replace(eval(regex), value);
                document.getElementById(id1).innerHTML = text;  //更新被替换内容
                document.getElementById(newName).innerHTML = value;     //更新替换字段内容
            } else {
                //初始状态的替换
                var regex = '/\{' + name + '\}/g';
                var temp = document.getElementById(id1).innerHTML;
                var text = temp.replace(eval(regex), value);
                document.getElementById(id1).innerHTML = text;
                document.getElementById(newName).innerHTML = value;
            }
        } else {
            document.getElementById(id1).innerHTML = template;
            document.getElementById(newName).innerHTML = name;
        }
    }

    //封装参数
    var formData = {};
    function param() {
        var data = formParams('frm_compile');
        var id = textId;
        var text = document.getElementById(id).innerHTML;
        data.textReplace = text;
        formData = JSON.stringify(data);
        console.info(formData);
    }

    //提交
    function submit() {
        if (!$("#frm_task").valid()) {
            return false;
        }
        if ("${processInsId}" != "0") {
            param();
            submitEditToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            param();
            submitToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }


</script>

</html>

