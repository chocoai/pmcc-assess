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
                    <h3>${declareRecord.name}-${projectPlanDetails.projectPhaseName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <input type="button" class="btn btn-success" value="新增" onclick="addExamineItem();">
                    <table id="explore_list" class="table table-bordered" style="max-height: auto;"></table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="taskExploreIndex.checkAssignmentTask(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary"
                                        onclick="taskExploreIndex.checkAssignmentTask(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="taskExploreIndex.checkAssignmentTask();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        taskExploreIndex.getExploreTaskList();
    });


    //任务提交
    function submit(mustUseBox) {
//        if (!taskExploreIndex.isAllFinish()) {
//            Alert("还有未完成的任务，请检查！");
//            return false;
//        }
        var formData = {};
        formData.id = "${surveySceneExplore.id}";
        formData.jsonContent = taskExploreIndex.getDeclareCertData();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }

    var taskExploreIndex = {};

    //检查是否添加任务
    taskExploreIndex.checkAssignmentTask = function (mustUseBox) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyExamine/checkAssignmentTask",
            data: {
                planDetailsId: "${projectPlanDetails.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.data) {
                    submit(mustUseBox);
                    $('#plan_details_modal').modal('hide');
                } else {
                    Alert("请添加一条任务");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //加载现场查勘数据
    taskExploreIndex.getExploreTaskList = function () {
        $("#explore_list").treegrid({
                url: '${pageContext.request.contextPath}/surveySceneExplore/getSceneExploreList?projectId=${projectPlanDetails.projectId}&pid=${projectPlanDetails.pid}',
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
                    {
                        field: "projectPhaseName",
                        title: "工作内容",
                        width: "20%",
                        align: "left",
                        formatter: function (value, row) {
                            return value
                        }
                    },
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
                        field: "planHours",
                        title: "计划工时",
                        width: "5%",
                        align: "center"
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
                        field: "proportion",
                        title: "权重占比",
                        width: "5%",
                        align: "center"
                    },

                    {
                        field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                        if (row.bisEnable) {
                            var s = "";
                            if (row.id == '${projectPlanDetails.id}') {
                                s += taskExploreIndex.getExamineFormTypeHtml(row.status, row.id);
                            } else if (!row.projectPhaseId && row.pid > 0) {
                                s += "<a data-placement='top' href='javascript://' data-original-title='删除' class='btn btn-xs btn-warning tooltips'  onclick='deleteExamineItem(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                            } else {
                                //只用于处理任务
                                if (row.excuteUrl) {
                                    s += "<a data-placement='top' data-original-title='提交' class='btn btn-xs btn-success tooltips' target='_blank' onclick='taskExploreIndex.openTaskUrl(\"" + row.excuteUrl + "\")'   ><i class='fa fa-arrow-right fa-white'></i></a>";
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
    }

    //获取案例任务可操作权限
    taskExploreIndex.getOperationHtml = function (status, id) {
        //none 可编辑、删除、分派 runing 查看 分派 finish 查看
        var assignmentHtml = "<a  data-placement='top' data-original-title='分派' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='taskExploreIndex.assignment(" + id + ")'><i class='fa fa-arrows-alt fa-white'></i></a>";
        var viewHtml = "";
        var resultHtml = "";
        switch (status) {
            case "none":
                resultHtml = assignmentHtml;
                break
            case "runing":
                resultHtml = viewHtml + assignmentHtml;
                break
            case "finish":
                resultHtml = viewHtml;
                break
        }
        return resultHtml;
    };

    taskExploreIndex.getExamineFormTypeHtml = function (status, id) {
        var html = "<a  data-placement='top' data-original-title='工业与非工业选择' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='taskExploreIndex.showModel()'>选择<i class='fa'></i></a>";
        return html;
    };

    taskExploreIndex.showModel = function () {
        $('#plan_details_modal').find('[name=operationType]').val("select");
        $('#plan_details_modal').modal('show');
    };

    taskExploreIndex.assignmentTask = function () {
        var data = formParams('frm_planDetails');
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyExamine/examineTaskAssignment",
            data: {
                planDetailsId: "${projectPlanDetails.id}",
                operationType: data.operationType,
                examineFormType: data.examineFormType
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("成功");
                    taskExploreIndex.getExploreTaskList();
                    $('#plan_details_modal').modal('hide');
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    };

    //打开提交任务链接
    taskExploreIndex.openTaskUrl = function (url) {
        openWin(url, function () {
            taskExploreIndex.getExploreTaskList();
        })
    }

    //任务分派
    taskExploreIndex.assignment = function (id) {
        var url = '${pageContext.request.contextPath}/surveyExamine/assignment?planDetailsId=' + id;
        openWin(url, function () {
            taskExploreIndex.getExploreTaskList();
        })
    }

    //所有任务完成
    taskExploreIndex.isAllFinish = function () {
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
    }

    //获取申报权证数据
    taskExploreIndex.getDeclareCertData = function () {
        var keyValueArray = [];
        $('#declareCertContent').find(':checkbox').each(function () {
            var keyValue = {};
            keyValue.key = $(this).val();
            keyValue.value = $(this).closest('span').find('label').text();
            keyValue.isChecked = $(this).prop('checked');
            keyValueArray.push(keyValue);
        })
        return JSON.stringify(keyValueArray);
    }

    //新增现场查勘内容项
    function addExamineItem() {
        $('#plan_details_modal').find('[name=operationType]').val("add");
        $('#plan_details_modal').modal('show');
    }

    //删除添加的查勘任务
    function deleteExamineItem(id) {
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
                        taskExploreIndex.getExploreTaskList();
                    } else {
                        Alert("删除失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        })
    }
</script>

<div id="plan_details_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">类型选择</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form id="frm_planDetails" class="form-horizontal">
                                <input type="hidden" name="operationType"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <c:forEach var="item" items="${examineFormTypeList}">
                                            <span class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="radio" id="examineFormType_${item.key}" name="examineFormType"
                                                   value='${item.key}'>
                                            <label for="examineFormType_${item.key}">&nbsp;${item.value}</label>
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="taskExploreIndex.assignmentTask()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

</html>

