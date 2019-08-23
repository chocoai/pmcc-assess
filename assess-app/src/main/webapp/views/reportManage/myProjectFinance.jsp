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
                                    委托人
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryConsignorName" name="queryConsignorName" class="form-control"
                                           placeholder="委托人"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    委托目的
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <select name='queryEntrustment' class='form-control  search-select select2'>
                                        <option value="0">-请选择-</option>
                                        <c:forEach var="item" items="${entrustmentList}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    贷款类型
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <select name='queryLoanType' class='form-control  search-select select2'>
                                        <option value="0">-请选择-</option>
                                        <c:forEach var="item" items="${loanTypeList}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    评估部门
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryDepartmentId" name="queryDepartmentId"
                                           class="form-control" type="hidden"/>
                                    <input id="queryDepartmentName" name="queryDepartmentName" class="form-control"
                                           placeholder="评估部门"
                                           onclick="selectDepartment(this)" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    报告使用单位
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryReportUseUnitName" name="queryReportUseUnitName"
                                           class="form-control" type="hidden"/>
                                    <input id="queryUseUnitName" name="queryUseUnitName" class="form-control"
                                           placeholder="报告使用单位"
                                           onclick="selectCustomer(this)" readonly="readonly"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    报告文号
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryReportNumber" name="queryReportNumber" class="form-control"
                                           placeholder="报告文号"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    开始时间
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryStartTime" name="queryStartTime"
                                           class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    结束时间
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryEndTime" name="queryEndTime" class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    业务来源说明
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input id="queryServiceExplain" name="queryServiceExplain" class="form-control"
                                           placeholder="业务来源说明"/>
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
                            src="${pageContext.request.contextPath}/ureport/preview?_u=erp:myProjectFinance.ureport.xml&_i=1&_r=1&_t=9"
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
        document.getElementById('report_iframe').src = "${pageContext.request.contextPath}/ureport/preview?_u=erp:myProjectFinance.ureport.xml&_t=9&_i=1&_r=1&queryProjectName=" + data.queryProjectName + "&queryConsignorName=" + data.queryConsignorName +
            "&queryReportUseUnitName=" + data.queryReportUseUnitName + "&queryReportNumber=" + data.queryReportNumber + "&queryStartTime=" + data.queryStartTime + "&queryEndTime=" + data.queryEndTime +
            "&queryEntrustment=" + data.queryEntrustment+"&queryLoanType=" + data.queryLoanType+"&queryDepartmentId=" + data.queryDepartmentId+"&queryServiceExplain=" + data.queryServiceExplain;
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
    };


    //部门
    function selectDepartment(this_) {
        var options = {
            onSelected: function (nodes) {
                $("#queryDepartmentId").val(nodes[0].id);
                $("#queryDepartmentName").val(nodes[0].text);
            }
        };
        erpDepartment.select(options);
    };
</script>
</html>
