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
                    <table id="explore_list" class="table table-bordered" style="max-height: auto;"></table>
                    <form id="frm_explore" class="form-horizontal">
                        <fieldset>
                            <legend>同步数据到其它权证</legend>
                            <input type="button" class="btn btn-primary" onclick="taskExploreIndex.selectAll();" value="全选" />
                            <input type="button" class="btn btn-primary" onclick="taskExploreIndex.unSelectAll();" value="全不选"/>
                            <input type="button" class="btn btn-primary" onclick="taskExploreIndex.invertSelect();" value="反选"/>
                            <div class="form-group">
                                <div class="col-sm-10" id="declareCertContent">

                                </div>
                            </div>
                        </fieldset>
                    </form>
                    <input type="hidden" id="jsonContentExplore" value='${surveySceneExplore.jsonContent}'>
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

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        taskExploreIndex.getExploreTaskList();
        taskExploreIndex.loadDeclareCert();
    });



    //任务提交
    function submit() {
        if (!taskExploreIndex.isAllFinish()) {
            Alert("还有未完成的任务，请检查！");
            return false;
        }
        var formData = {};
        formData.id = "${surveySceneExplore.id}";
        formData.jsonContent = taskExploreIndex.getDeclareCertData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    var taskExploreIndex = {};

    //加载现场查勘数据
    taskExploreIndex.getExploreTaskList = function () {
        $("#explore_list").treegrid({
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
                                s += taskExploreIndex.getOperationHtml(row.status, row.id);
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
    }

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

    //加载申报权证
    taskExploreIndex.loadDeclareCert = function () {
        var jsonContent = $("#jsonContentExplore").val();
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

    //全选
    taskExploreIndex.selectAll = function () {
        $('#declareCertContent').find(':checkbox').prop('checked',true);
    }
    //全不选
    taskExploreIndex.unSelectAll = function () {
        $('#declareCertContent').find(':checkbox').prop('checked',false);
    }
    //反选
    taskExploreIndex.invertSelect = function () {
        $('#declareCertContent').find(':checkbox').each(function () {
            $(this).prop('checked',!$(this).prop('checked'));
        })
    }

</script>


</html>

