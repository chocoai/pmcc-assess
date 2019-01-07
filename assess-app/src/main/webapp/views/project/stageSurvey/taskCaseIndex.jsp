<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${declareRecord.name}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table id="case_list" class="table table-bordered" style="max-height: auto;"></table>
                    <form id="frm_case" class="form-horizontal">
                        <fieldset>
                            <legend>同步数据到其它权证</legend>
                            <input type="button" class="btn btn-primary" onclick="taskCaseIndex.selectAll();"
                                   value="全选"/>
                            <input type="button" class="btn btn-primary" onclick="taskCaseIndex.unSelectAll();"
                                   value="全不选"/>
                            <input type="button" class="btn btn-primary" onclick="taskCaseIndex.invertSelect();"
                                   value="反选"/>
                            <div class="form-group">
                                <div class="col-sm-10" id="declareCertContent">

                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <input type="hidden" id="jsonContentCase" value='${surveyCaseStudy.jsonContent}'>
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

<div id="plan_details_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">案例管理</h3>

            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form id="frm_planDetails" class="form-horizontal">

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="taskCaseIndex.saveCaseTask()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<span style="display: none;"><%@include file="/views/base/mapPositionPicker.jsp" %></span>
<script type="application/javascript">

    $(function () {
        taskCaseIndex.getCaseTaskList();
        taskCaseIndex.loadDeclareCert();
    });

    //任务提交
    function submit() {
        if (!taskCaseIndex.isAllFinish()) {
            Alert("还有未完成的任务，请检查！");
            return false;
        }
        var formData = {};
        formData.id = "${surveyCaseStudy.id}";
        formData.jsonContent = taskCaseIndex.getDeclareCertData();
        if (gaoDeMap.location)
            formData.location = gaoDeMap.location;
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    var taskCaseIndex = {};

    //加载案例数据
    taskCaseIndex.getCaseTaskList = function () {
        $("#case_list").treegrid({
                url: '${pageContext.request.contextPath}/surveyCaseStudy/getPlanTaskExamineList?planDetailsId=${projectPlanDetails.id}',
                method: 'get',
                idField: 'id',
                treeField: 'projectPhaseName',
                datatype: 'json',
                lines: true,
                width: 'auto',
                rownumbers: true,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                },

                columns: [[
                    {field: "projectPhaseName", title: "工作内容", width: "30%", align: "left"},
                    {
                        field: "planStartDate",
                        title: "开始时间",
                        width: "10%",
                        align: "center",
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {
                        field: "planEndDate",
                        title: "结束时间",
                        width: "10%",
                        align: "center",
                        formatter: function (value, row) {
                            return formatDate(value, false);
                        }
                    },
                    {
                        field: "executeUserName",
                        title: "责任人",
                        width: "10%",
                        align: "center"
                    },
                    {
                        field: "executeDepartmentName",
                        title: "责任部门",
                        width: "10%",
                        align: "center"
                    },
                    {
                        field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                            if (row.bisEnable) {
                                var s = "";
                                if (row.id == '${projectPlanDetails.id}') {
                                    s += "<a  data-placement='top' data-original-title='新增' class='btn btn-xs btn-success tooltips' target='_blank' onclick='taskCaseIndex.addCaseTask(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                } else if (row.pid == '${projectPlanDetails.id}') {
                                    s += taskCaseIndex.getOperationHtml(row.status, row.id);
                                } else {
                                    //只用于处理任务
                                    if (row.excuteUrl) {
                                        s += "<a  data-placement='top' data-original-title='提交' class='btn btn-xs btn-success tooltips' target='_blank' onclick='taskCaseIndex.openTaskUrl(\"" + row.excuteUrl + "\")'   ><i class='fa fa-arrow-right fa-white'></i></a>";
                                    } else if (row.displayUrl) {
                                        s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                                    }
                                }
                                return s;
                            }

                        }
                    }
                ]]
            }
        );
    };

    //获取案例任务可操作权限
    taskCaseIndex.getOperationHtml = function (status, id) {
        //none 可编辑、删除、分派 runing 查看 分派 finish 查看
        var editHtml = "<a  data-placement='top' data-original-title='编辑' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='taskCaseIndex.editCaseTask(" + id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
        var deleteHtml = "<a  data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='taskCaseIndex.deleteCaseTask(" + id + ")'><i class='fa fa-minus fa-white'></i></a>";
        var assignmentHtml = "<a  data-placement='top' data-original-title='分派' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='taskCaseIndex.assignment(" + id + ")'><i class='fa fa-arrows-alt fa-white'></i></a>";
        assignmentHtml = "" ;
        var copyHtml = " <a href='javascript://;' onclick='taskCaseIndex.copyCaseStudy(" + id + ")' data-placement='top' data-original-title='复制案例' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-copy fa-white'></i></a>";
        copyHtml = "" ;
        var viewHtml = "";
        var resultHtml = "";
        switch (status) {
            case "none":
                resultHtml = editHtml + deleteHtml + assignmentHtml;
                break
            case "runing":
                resultHtml = viewHtml + assignmentHtml + copyHtml;
                break
            case "finish":
                resultHtml = viewHtml + copyHtml;
                break
        }
        return resultHtml;
    };

    //新增案例任务
    taskCaseIndex.addCaseTask = function (pid) {
        $("#frm_planDetails").empty();
        $("#frm_planDetails").append($("#plan_details_modal_html").html());
        $("#examineFormTypeList").show() ;
        var node = $("#case_list").treegrid('find', pid);
        $("#pid").val(node.id);
        $("#planStartDate").val(formatDate(node.planStartDate, false));
        $("#planEndDate").val(formatDate(node.planEndDate, false));
        $("#executeUserAccount").val(node.executeUserAccount);
        $("#executeUserName").val(node.executeUserName);
        $("#executeDepartmentId").val(node.executeDepartmentId);
        $("#executeDepartmentName").val(node.executeDepartmentName);
        $("#planHours").val(node.planHours);
        $("#proportion").val(node.proportion);
        $("#plan_details_modal").modal('show');
    };

    //编辑案例任务
    taskCaseIndex.editCaseTask = function (id) {
        $("#frm_planDetails").empty();
        $("#frm_planDetails").append($("#plan_details_modal_html").html());
        $("#examineFormTypeList").hide() ;
        var node = $("#case_list").treegrid('find', id);
        $("#frm_planDetails").initForm(node);
        $("#planStartDate").val(formatDate(node.planStartDate, false));
        $("#planEndDate").val(formatDate(node.planEndDate, false));
        $("#plan_details_modal").modal('show');
    };

    //保存案例任务
    taskCaseIndex.saveCaseTask = function (id) {
        var data = formParams('frm_planDetails');
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyCaseStudy/saveCaseTask",
            data: {
                planDetailsId: "${projectPlanDetails.id}",
                formData: JSON.stringify(data),
                examineFormType: data.examineFormType
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    //保存完后其他动作
                    toastr.success("保存成功");
                    taskCaseIndex.getCaseTaskList();
                    $('#plan_details_modal').modal('hide');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };


    //删除案例任务
    taskCaseIndex.deleteCaseTask = function (id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyCaseStudy/deleteCaseTask",
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("删除成功");
                        taskCaseIndex.getCaseTaskList();
                    } else {
                        Alert("删除失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        })
    };

    //选择责任人
    taskCaseIndex.selectEmployee = function () {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $("#executeUserAccount").val(data.account);
                    $("#executeUserName").val(data.name);
                    AssessCommon.getUserDepartmentInfo(data.account, function (retData) {
                        $("#executeDepartmentId").val(retData.id);
                        $("#executeDepartmentName").val(retData.name);
                    })
                }
                else {
                    $("#executeUserAccount").val("");
                    $("#executeUserName").val("");
                }
            }
        });
    };

    //选择责任部门
    taskCaseIndex.selectDepartment = function () {
        erpDepartment.select({
            value: $("#executeDepartmentId").val(),
            onSelected: function (node) {
                $("#executeDepartmentId").val(node[0].id);
                $("#executeDepartmentName").val(node[0].text);
            }
        });
    };

    //打开提交任务链接
    taskCaseIndex.openTaskUrl = function (url) {
        openWin(url, function () {
            taskCaseIndex.getCaseTaskList();
        })
    };

    //任务分派
    taskCaseIndex.assignment = function (id) {
        var url = '${pageContext.request.contextPath}/surveyExamine/assignment?planDetailsId=' + id;
        openWin(url, function () {
            taskCaseIndex.getCaseTaskList();
        })
    };

    //所有任务完成
    taskCaseIndex.isAllFinish = function () {
        var isFinish = true;
        $.ajax({
            url: "${pageContext.request.contextPath}/projectPlanDetails/isAllFinish",
            data: {planDetailsId: "${projectPlanDetails.id}"},
            dataType: 'json',
            type: 'post',
            async: false,
            success: function (result) {
                if (result.ret) {
                    isFinish = result.data;
                }
            }
        })
        return isFinish;
    };

    //加载申报权证
    taskCaseIndex.loadDeclareCert = function () {
        var jsonContent = $("#jsonContentCase").val();
        if (jsonContent) {
            jsonContent = JSON.parse(jsonContent);
            var html = '';
            $.each(jsonContent, function (i, item) {
                html += '<span class="checkbox-inline">';
                html += '<input type="checkbox" id="declareCert' + item.key + '" ';
                if (item.isChecked) {
                    html += ' checked="checked" ';
                }
                html += 'name="declareCert" value="' + item.key + '" class="form-inline">';
                html += '<label for="declareCert' + item.key + '">' + item.value + '</label></span>';
            })
            $('#declareCertContent').append(html);
        }
    };

    //获取申报权证数据
    taskCaseIndex.getDeclareCertData = function () {
        var keyValueArray = [];
        $('#declareCertContent').find(':checkbox').each(function () {
            var keyValue = {};
            keyValue.key = $(this).val();
            keyValue.value = $(this).closest('span').find('label').text();
            keyValue.isChecked = $(this).prop('checked');
            keyValueArray.push(keyValue);
        })
        return JSON.stringify(keyValueArray);
    };

    //复制案例
    taskCaseIndex.copyCaseStudy = function (id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyCaseStudy/copyCaseStudy",
            data: {planDetailsId: id},
            dataType: 'json',
            type: 'post',
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("复制成功");
                    taskCaseIndex.getCaseTaskList();
                }
            }
        })
    };

    //全选
    taskCaseIndex.selectAll = function () {
        $('#declareCertContent').find(':checkbox').prop('checked', true);
    };
    //全不选
    taskCaseIndex.unSelectAll = function () {
        $('#declareCertContent').find(':checkbox').prop('checked', false);
    };
    //反选
    taskCaseIndex.invertSelect = function () {
        $('#declareCertContent').find(':checkbox').each(function () {
            $(this).prop('checked', !$(this).prop('checked'));
        })
    }

</script>

<script type="text/html" id="plan_details_modal_html">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-2 control-label">
                案例名称
            </label>
            <div class="col-sm-10">
                <input type="hidden" id="planDetailsId" name="id"/>
                <input type="hidden" id="pid" name="pid"/>
                <input type="text" placeholder="案例名称" required maxlength="50"
                       id="projectPhaseName" name="projectPhaseName"
                       class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group" id="examineFormTypeList" style="display: none">

        <div class="x-valid">
            <label class="col-sm-2 control-label">
                工业仓储类型
            </label>
        </div>

        <div class="x-valid">
            <c:forEach var="item" items="${examineFormTypeList}">
        <span class="col-sm-2">
        <input type="radio" name="examineFormType" value='${item.key}'>
        <label for="examineFormType_${item.key}">&nbsp;${item.value}</label>
    </span>
            </c:forEach>
        </div>
    </div>

    <%--这部分功能暂不开放 1--%>
    <div style="display:none;">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-2 control-label">
                    开始时间
                </label>
                <div class="col-sm-4">
                    <input type="text" placeholder="开始时间" data-date-format='yyyy-mm-dd'
                           id="planStartDate" name="planStartDate"
                           class="form-control dbdate">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-2 control-label">
                    结束时间
                </label>
                <div class="col-sm-4">
                    <input type="text" placeholder="结束时间" data-date-format='yyyy-mm-dd'
                           id="planEndDate" name="planEndDate"
                           class="form-control dbdate">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">
                责任人
            </label>
            <div class="col-sm-4">
                <input type="hidden" placeholder="责任人" maxlength="50"
                       id="executeUserAccount"
                       name="executeUserAccount" class="form-control">
                <input type="text" placeholder="责任人" maxlength="50" id="executeUserName"
                       name="executeUserName" class="form-control" readonly="readonly"
                       onclick="taskCaseIndex.selectEmployee()">
            </div>
            <label class="col-sm-2 control-label">
                责任部门
            </label>
            <div class="col-sm-4">
                <input type="hidden" placeholder="责任部门" maxlength="50"
                       id="executeDepartmentId"
                       name="executeDepartmentId" class="form-control">
                <input type="text" placeholder="责任部门" maxlength="50"
                       id="executeDepartmentName"
                       name="executeDepartmentName" class="form-control"
                       onclick="taskCaseIndex.selectDepartment()"
                       readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-2 control-label">
                    计划工时
                </label>
                <div class="col-sm-4">
                    <input type="text" placeholder="计划工时" data-rule-number='true'
                           maxlength="5"
                           id="planHours" name="planHours"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-2 control-label">
                    权重占比
                </label>
                <div class="col-sm-4">
                    <input type="text" placeholder="权重占比" data-rule-number='true'
                           maxlength="5"
                           id="proportion" name="proportion"
                           class="form-control">
                </div>
            </div>
        </div>
    </div>
    <!-- . -->
</script>

</html>

