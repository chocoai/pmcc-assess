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
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${planName}成果提交</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered" style="max-height: 400px;"></table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>确认信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="treeGrid panel-body x_content" style="padding: 0px;">
                    <form id="frm_approval" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                结论
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input type="radio" value="Approval" required name="conclusion"
                                               checked="checked">
                                        提交上级复核
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" value="Decline" required name="conclusion">
                                        退回重新提交成果
                                    </label>
                                </div>
                            </div>
                        </div>
                        <c:if test="${bisSelectUser=='1'}">
                            <div class="form-group" id="form_approval_nextApproval">
                                <label class="col-sm-1 control-label">
                                    下级审批人
                                </label>
                                <div class="col-sm-11">
                                    <input type="hidden" id="nextApproval" name="nextApproval"
                                           value="${selectNextUser}">
                                    <input type="text" required value="${selectNextUserName}"
                                           placeholder="下级审批人"
                                           id="nextApprovalName" name="nextApprovalName"
                                           class="form-control" readonly="readonly"
                                           maxlength="200" onclick="nextApprovalSelect()">
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:if test="${processInsId!='0'}">
                            <button class="btn btn-success" onclick="saveToServiceEdit()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </c:if>
                        <c:if test="${processInsId=='0'}">
                            <button class="btn btn-success" onclick="saveToService()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
            <%@include file="/views/share/model_employee.jsp" %>
        </div>
    </div>
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="application/javascript">
    $(function () {
        treeGridload();
    })
    function nextApprovalSelect() {
        var thisUser = "";
        if ($("#nextApproval").val()) {
            var userName = $("#nextApprovalName").val();
            var userAccount = $("#nextApproval").val();
            thisUser = userName + "_" + userAccount;
        }
        loadSelectEmployee(1, thisUser, true, function (data) {
            if (data.account) {
                $("#nextApproval").val(data.account);
                $("#nextApprovalName").val(data.name);
            }
            else {
                $("#nextApproval").val("");
                $("#nextApprovalName").val("");
            }
        });
    }
    function treeGridload() {
        $("#PlanItemListed").treegrid({
            url: "${pageContext.request.contextPath}/ProjectPlan/getProjectPlan",
            queryParams: {planId: ${planId}},
            idField: 'id',
            treeField: 'projectPhaseName',
            datatype: 'json',
            lines: true,
            width: 'auto',
            method: "get",
            rownumbers: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },
            onDblClickRow: function (row) {
                editPlan(row.id);
            },
            columns: [[
                {
                    field: 'bisLastLayer', align: 'center', title: '', width: '3%', formatter: function (value, row) {
                    if (value && row.bisNew) {   //text 名称前面加复选框
                        return "<input type='checkbox' class='backTask' id='chk_" + row.id + "'/>";
                    }
                }
                },
                {
                    field: 'projectPhaseName',
                    align: 'left',
                    title: '工作内容',
                    width: '20%',
                    formatter: function (value, row) {
                        var s = value;
                        if (row.bisNew) {
                            s += "<i class='fa fa-star-o' style='font-size: 15px;color: red'></i>";
                        }
                        return s;
                    }
                },
                {
                    field: 'planStartDate',
                    align: 'center',
                    title: '开始时间',
                    width: '5%',
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: 'planEndDate',
                    align: 'center',
                    title: '结束时间',
                    width: '5%',
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {field: 'planHours', align: 'center', title: '计划工时(h)', width: '5%',},
                {field: 'actualHours', align: 'center', title: '实际工时(h)', width: '5%',},
                {
                    field: 'taskSubmitTime',
                    align: 'center',
                    title: '提交时间',
                    width: '5%',
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {field: 'executeUserName', align: 'center', title: '责任人', width: '5%'},
                {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                {
                    field: 'tasks', align: 'center', title: '工作成果', width: '15%', formatter: function (value, row) {
                    var s = "";
                    if (value) {
                        $.each(value, function (i, j) {
                            s += "<a onclick='showAttachment(" + j.key + ",\"" + j.explain + "\")'  class='fileupload-preview'>" + j.value + "</a>";
                            s += "<a>";
                            s += "<i class='fa fa-download' onclick='downAttachments(" + j.key + ")'style='margin-left: 15px;font-size: 15px;'></i>";
                            s += "<br/>";
                            s += "</a>";
                        })
                    }
                    return s;
                }
                },
                {
                    field: 'processInsId',
                    align: 'center',
                    title: '查看详情',
                    width: '10%',
                    formatter: function (value, row) {
                        var str = "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?projectDetailsId=" + row.id + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-search fa-white'></i></a>";

                        return str;
                    }
                }
                ,
                {
                    field: 'planId', align: 'center', title: '退回原因', width: '20%', formatter: function (value, row) {
                    var returnDetailsReason = row.returnDetailsReason == undefined ? "" : row.returnDetailsReason;
                    if (row.bisNew) {
                        return "<input type='text'  class='backtext form-control' id='back_text_" + row.id + "' value='" + returnDetailsReason + "'/>";
                    }
                    else {
                        return returnDetailsReason;
                    }
                }
                }
            ]]
        });
    }
    function chkRadioClick() {
        var rdoValue = $("input[name='conclusion']:checked").val();
        if (rdoValue == "Approval") {
            $("#opinions").attr("required", false);//审批意见不必填
            if ($("#form_approval_nextApproval").length > 0) {
                $("#form_approval_nextApproval").attr("required", true);//必须选择下级审批人
                $("#form_approval_nextApproval").show();
            }
        }
        else {
            $("#opinions").attr("required", true);//审批意见必填
            if ($("#form_approval_nextApproval").length > 0) {
                $("#form_approval_nextApproval").attr("required", false);//可不选择下级审批人
                $("#form_approval_nextApproval").hide();

            }
        }
    }
    function saveToService() {
        var objs = $('input:checkbox[class=backTask]:checked');
        var rdoValue = $("input[name='conclusion']:checked").val();
        var backData = [];
        if (rdoValue != "Approval") {
            if (objs.length == 0) {
                Alert("请至少选择一个需要回退的工作成果");
                return false;
            }
            else {
                $.each(objs, function (i, item) {
                    var id = $(item).attr("id").split('_')[1];
                    backData.push({
                        detailsId: id,
                        backText: $("#back_text_" + id).val()
                    });
                });
            }
        }

        var appointUserAccount = "";
        if ("${bisSelectUser}" == "1") {
            appointUserAccount = $("#nextApproval").val()
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskAll/projectTaskAllSubmit",
            data: {
                conclusion: rdoValue,
                planId: ${planId},
                backData: JSON.stringify(backData),
                appointUserAccount: appointUserAccount
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });

    }
    function saveToServiceEdit() {
        var objs = $('input:checkbox[class=backTask]:checked');
        var rdoValue = $("input[name='conclusion']:checked").val();
        var backData = [];
        if (rdoValue != "Approval") {
            if (objs.length == 0) {
                Alert("请至少选择一个需要回退的工作成果");
                return false;
            }
            else {

                $.each(objs, function (i, item) {
                    var id = $(item).attr("id").split('_')[1];
                    backData.push({
                        detailsId: id,
                        backText: $("#back_text_" + id).val()
                    });
                });
            }
        }
        var appointUserAccount = "";
        if ("${bisSelectUser}" == "1") {
            appointUserAccount = $("#nextApproval").val()
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskAll/projectTaskAllEditSubmit",
            data: {
                boxId: "${boxId}",
                processInsId: "${processInsId}",
                taskId: "${taskId}",
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                CurrentStep: "${CurrentStep}",
                StepCount: "${StepCount}",
                viewUrl: "${viewUrl}",
                projectId: "${projectId}",
                workStageId: "${projectPhase.workStageId}",
                conclusion: rdoValue,
                planId: ${planId},
                backData: JSON.stringify(backData),
                appointUserAccount: appointUserAccount
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });

    }
</script>
</body>
</html>

