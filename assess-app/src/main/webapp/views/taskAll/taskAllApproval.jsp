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
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="application/javascript">
    $(function () {
        treeGridload();
    });
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
            onDblClickRow: function (row) {
                editPlan(row.id);
            },
            columns: [[
                {
                    field: 'projectPhaseName', align: 'left', title: '工作内容', width: '20%', formatter: function (value, row) {
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
                    width: '10%',
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: 'planEndDate',
                    align: 'center',
                    title: '结束时间',
                    width: '10%',
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
                    width: '10%',
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {field: 'executeUserName', align: 'center', title: '责任人', width: '10%'},
                {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                {
                    field: 'tasks', align: 'left', title: '工作成果', width: '15%', formatter: function (value, row) {
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
                    field: 'returnDetailsReason', align: 'center', title: '退回原因', width: '20%'
                },
                {
                    field: 'processInsId', align: 'center', title: '查看详情', width: '10%', formatter: function (value, row) {
                    var str = "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?planDetailsId=" + row.id + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-search fa-white'></i></a>";
                    return str;
                }
                }
            ]]
        });
    }
    function saveform() {


        if (!$("#frm_approval").valid()) {
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskAll/projectTaskAllApprovalSubmit",
            type: "post",
            dataType: "json",
            data: formApproval.getFormData(),
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
</html>
