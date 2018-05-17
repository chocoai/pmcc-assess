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



            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}记录表</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <%--<button type="button" class="btn btn-success" onclick="addData()"
                                        data-toggle="modal" href="#divBox"> 新增</button>--%>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/surveyLocale/index?responsibilityId=${responsibilityId}" target="_blank">
                                    新增
                                </a>
                            </div>
                        </div>
                </div>
            </div>

            <div class="x_panel">
                <table class="table table-bordered" id="tb_List">
                    <!-- cerare document add ajax data-->
                </table>
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
        loadDataDicList();

        $("#frm_task").validate();

        FileUtils.uploadFiles({
            target: "apply_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

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

        //查勘图片
        FileUtils.uploadFiles({
            target: "surveyPicture",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: 0,
                fieldsName: "surveyPicture",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "surveyPicture",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: 0,
                fieldsName: "surveyPicture",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "surveyImage",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: 0,
                fieldsName: "surveyImage",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "surveyImage",
            formData: {
                tableName: "tb_survey_locale_explore_detail",
                tableId: 0,
                fieldsName: "surveyImage",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    });


    function submit() {
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

    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'id', title: '查勘单编号'});
        cols.push({field: 'surveyPeople', title: '查勘人'});

        cols.push({
            field: 'surveyTime', title: '查勘时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });

        cols.push({field: 'belongWarrant', title: '所属权证'});
        cols.push({field: 'ledLuminousPeople', title: '领勘人'});
        cols.push({field: 'surveyPicture', title: '查勘图片'});
        cols.push({field: 'surveyImage', title: '查勘图像'});
        cols.push({field: 'locationPicture', title: '位置图片'});
        cols.push({field: 'surveyLocaltion', title: '查勘位置'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                /*str += '<a class="btn btn-xs btn-success" href="javascript:editHrProfessional(' + index + ');" >编辑</i></a>';*/
                str += '<a class="btn btn-xs btn-warning" href="javascript:delData(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyLocale/list", cols, {
            mainId: ${empty surveyLocaleExplore.id?0:surveyLocaleExplore.id}

        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    function delData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyLocale/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

</script>

</html>

