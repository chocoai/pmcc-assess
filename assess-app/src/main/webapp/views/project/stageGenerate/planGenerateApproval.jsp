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
                    <h3>报告生成</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="panel-body">
                        <form id="frm_content" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <c:forEach items="${reportTypeList}" var="item">
                                            <span class="checkbox-inline">
                                                <input type="checkbox" name="reportType" value="${item.id}">
                                                <label>${item.name}</label>
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <c:forEach items="${schemeAreaGroupList}" var="item">
                        <div class="x_panel area_panel">
                            <input type="hidden" name="areaGroupId" value="${item.id}">
                            <div class="x_title collapse-link" onclick="loadJudgeObjectList(this);">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                </ul>
                                <h3>
                                    <label>${item.areaName}</label>
                                </h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content collapse">
                                <form class="form-horizontal" id="groupForm${item.id}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                报告出具日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                                       class="form-control date-picker dbdate" pattern='yyyy-MM-dd'
                                                       data-date-format="yyyy-mm-dd">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                作业结束时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                                       class="form-control date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd'>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">-注册房地产估价师<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-3">
                                                <select name="realEstateAppraiser"
                                                        class="form-control search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                现场查勘开始日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="investigationsStartDate" placeholder="现场查勘开始日期"
                                                       class="form-control date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd'
                                                       value="<fmt:formatDate value='${schemeReportGeneration.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                现场查勘结束日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="investigationsEndDate" placeholder="现场查勘结束日期"
                                                       class="form-control date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd'
                                                       value="<fmt:formatDate value='${schemeReportGeneration.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 报告下载 -->
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                预评报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttypepreaudit${item.id}"></div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                技术报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttypetechnology${item.id}"></div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                结果报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttyperesult${item.id}"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                生成报告
                                            </label>
                                            <div class="col-sm-3">
                                                <a class="btn-primary btn active"
                                                   onclick="generateReport('${item.id}',this)">生成报告<i
                                                        class="fa fa-file-word-o"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="x_content collapse">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 5%">编号</th>
                                        <th style="width: 5%">所有权人</th>
                                        <th style="width: 15%">坐落</th>
                                        <th style="width: 10%">证载用途</th>
                                        <th style="width: 10%">实际用途</th>
                                        <th style="width: 10%">设定用途</th>
                                        <th style="width: 10%">最佳利用描述</th>
                                        <th style="width: 5%">证载面积</th>
                                        <th style="width: 5%">评估面积</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:forEach>
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
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }


</script>