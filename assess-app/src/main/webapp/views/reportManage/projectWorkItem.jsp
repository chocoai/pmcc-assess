<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="query_form" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    项目名称
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryProjectName" name="queryProjectName" class="form-control"
                                           placeholder="项目名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    阶段名称
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryWorkStageName" name="queryWorkStageName" class="form-control"
                                           placeholder="阶段名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    事项名称
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryPhaseName" name="queryPhaseName" class="form-control"
                                           placeholder="事项名称"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    项目经理
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input type="hidden" id="queryUserAccount" name="queryUserAccount">
                                    <input type="text" data-rule-maxlength="50" readonly
                                           placeholder="项目经理" onclick="personSelect(this)"
                                           id="queryUserAccountName" name="queryUserAccountName" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    责任人
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input type="hidden" id="queryExecuteUserAccount" name="queryExecuteUserAccount">
                                    <input type="text" data-rule-maxlength="50" readonly
                                           placeholder="项目经理" onclick="personSelect(this)"
                                           id="queryExecuteUserAccountName" name="queryExecuteUserAccountName" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <button type="button" class="btn btn-success" onclick="statisticsByCondition()">
                                        查询
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <iframe id="report_iframe" width="100%" height="100%"
                            src="${pageContext.request.contextPath}/ureport/preview?_u=erp:projectWorkItem.ureport.xml&_i=1&_r=1"
                            frameborder="0" scrolling="auto"></iframe>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script type="application/javascript">
    $(function () {
        changeFrameHeight();
    });


    function statisticsByCondition() {
        var data = formParams("query_form");
        document.getElementById('report_iframe').src = "${pageContext.request.contextPath}/ureport/preview?_u=erp:projectWorkItem.ureport.xml&_i=1&_r=1&queryProjectName=" + data.queryProjectName + "&queryWorkStageName=" + data.queryWorkStageName +
            "&queryPhaseName=" + data.queryPhaseName + "&queryUserAccount=" + data.queryUserAccount + "&queryExecuteUserAccount=" + data.queryExecuteUserAccount;
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
