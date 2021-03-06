<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
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
                    <h2>${projectPlan.planName}阶段工作计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div id="frm_plan" class="form-horizontal">
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                开始日期
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control"><fmt:formatDate value="${projectPlan.projectPlanStart}"
                                                                            pattern="yyyy-MM-dd"/></label>
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                结束日期
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control"><fmt:formatDate value="${projectPlan.projectPlanEnd}"
                                                                            pattern="yyyy-MM-dd"/></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                说明
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <label class="form-control">${projectPlan.planRemarks}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>详情计划</h2>
                    <div class="clearfix"></div>
                </div>
                <div class='treeGrid panel-body x_content' style="padding: 0px;">
                    <table id="PlanItemListed" class="table table-bordered" style="max-height: 400px;"></table>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js?v=${assessVersion}"></script>

</html>


<script type="text/javascript">
    var treeGridJson = {};
    $(function () {
        GetPlanItemList();
    });


    function GetPlanItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/getProjectPlan",
            data: {
                planId: ${projectPlan.id}
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                result.rows = sortObjectArray(result.rows, ["_parentId", "sorting"]);
                treeGridJson = result;
                treeGridload();

            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });


    }
    function treeGridload() {
        $("#PlanItemListed").treegrid({
                data: treeGridJson,
                idField: 'id',
                treeField: 'projectPhaseName',
                datatype: 'json',
                lines: true,
                width: 'auto',
                toolbar: "#tb",
                rownumbers: true,
                columns: [[
                    {field: 'projectPhaseName', title: '工作内容', width: '20%', align: 'left'},
                    {
                        field: 'planStartDate', title: '开始时间', width: '10%', align: 'left', formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                    },
                    {
                        field: 'planEndDate', title: '结束时间', width: '10%', align: 'left', formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                    },
                    {field: 'planHours', title: '计划工时', width: '10%', align: 'left'},
                    {field: 'executeUserName', title: '责任人', width: '10%', align: 'left'},
                    {field: 'executeDepartmentName', title: '责任部门', width: '10%', align: 'left'},
                    {field: 'proportion', title: '权重占比', width: '10%', align: 'left'},
                    {field: 'planRemarks', title: '说明', width: '20%', align: 'left'},
                    {field: 'firstPid', title: 'firstPid', align: 'center', hidden: true},
                    {field: 'sorting', title: 'sorting', align: 'center', hidden: true},
                    {field: 'id', title: 'PlanItemId', align: 'center', hidden: true},
                    {field: 'projectPhaseId', title: 'projectPhaseId', align: 'center', hidden: true}
                ]]
            }
        )
        ;
    }


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPlan/submitPlanApproval",
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }


</script>
