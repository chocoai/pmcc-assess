<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="query_form" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryProjectName" class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryProjectName" name="queryProjectName" class="form-control input-full"  placeholder="项目名称"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">阶段名称</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryWorkStageName" name="queryWorkStageName" class="form-control input-full"
                                                   placeholder="阶段名称"/>
                                        </div>

                                        <label class="col-md-1 col-form-label">事项名称</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryPhaseName" name="queryPhaseName" class="form-control input-full"
                                                   placeholder="事项名称"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">项目经理</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" id="queryUserAccount" name="queryUserAccount">
                                            <input type="text" data-rule-maxlength="50" readonly
                                                   placeholder="项目经理" onclick="personSelect(this)"
                                                   id="queryUserAccountName" name="queryUserAccountName" class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">责任人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" id="queryExecuteUserAccount" name="queryExecuteUserAccount">
                                            <input type="text" data-rule-maxlength="50" readonly
                                                   placeholder="责任人" onclick="personSelect(this)"
                                                   id="queryExecuteUserAccountName" name="queryExecuteUserAccountName" class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryStartTime" name="queryStartTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryEndTime" name="queryEndTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="statisticsByCondition()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>
                                </form>
                                <iframe id="report_iframe" width="100%" height="100%"
                                        src="${pageContext.request.contextPath}/ureport/preview?_u=erp:projectWorkItem.ureport.xml&_i=1&_r=1"
                                        frameborder="0" scrolling="auto"></iframe>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script type="application/javascript">
    $(function () {
        changeFrameHeight();
    });


    function statisticsByCondition() {
        var data = formParams("query_form");
        document.getElementById('report_iframe').src = "${pageContext.request.contextPath}/ureport/preview?_u=erp:projectWorkItem.ureport.xml&_i=1&_r=1&queryProjectName=" + data.queryProjectName + "&queryWorkStageName=" + data.queryWorkStageName +
            "&queryPhaseName=" + data.queryPhaseName + "&queryUserAccount=" + data.queryUserAccount + "&queryExecuteUserAccount=" + data.queryExecuteUserAccount+ "&queryStartTime=" + data.queryStartTime+ "&queryEndTime=" + data.queryEndTime;
    }


    function changeFrameHeight() {
        var ifm = document.getElementById("report_iframe");
        ifm.height = document.documentElement.clientHeight - 56;
    }

    window.onresize = function () {
        changeFrameHeight();
    };

    function selectCustomer(this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId: "${companyId}",
            onSelected: function (nodes) {
                $("#queryReportUseUnitName").val(nodes[0].id);
                $("#queryUseUnitName").val(nodes[0].name);
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/initiateCrmCustomer/getCrmCustomerDto",
                    data: "crmId=" + nodes[0].id,
                    success: function (msg) {

                    }
                });
            }
        });
    }


    //选择人员
    function personSelect (_this) {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $(_this).val(data.name);
                    $(_this).prev().val(data.account);
                }
                else {
                    $(_this).val("");
                    $(_this).prev().val("");
                }
            }
        });
    }
</script>
</html>
