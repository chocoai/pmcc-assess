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
                                        <div class="col-md-3 p-0">
                                            <input id="queryProjectName" name="queryProjectName"
                                                   class="form-control input-full input-full" placeholder="项目名称"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">项目经理</label>
                                        <div class="col-md-3 p-0">
                                            <input type="hidden" id="queryUserAccount" name="queryUserAccount">
                                            <input type="text" data-rule-maxlength="50" readonly
                                                   placeholder="项目经理" onclick="personSelect()"
                                                   id="queryUserAccountName" name="queryUserAccountName"
                                                   class="form-control input-full input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">委托人</label>
                                        <div class="col-md-3 p-0">
                                            <input id="queryConsignorName" name="queryConsignorName"
                                                   class="form-control input-full input-full"
                                                   placeholder="委托人"/>
                                        </div>
                                    </div>

                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">委托目的</label>
                                        <div class="col-md-3 p-0">
                                            <select name='queryEntrustPurposeName' class='form-control input-full  search-select select2'>
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${entrustmentList}">
                                                    <option value="${item.name}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">贷款类型</label>
                                        <div class="col-md-3 p-0">
                                            <select name='queryLoanTypeName' class='form-control input-full  search-select select2'>
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${loanTypeList}">
                                                    <option value="${item.name}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">评估部门</label>
                                        <div class="col-md-3 p-0">
                                            <input id="queryDepartmentId" name="queryDepartmentId"
                                                   class="form-control input-full" type="hidden"/>
                                            <input id="queryDepartmentName" name="queryDepartmentName" class="form-control input-full"
                                                   placeholder="评估部门"
                                                   onclick="selectDepartment(this)" readonly="readonly"/>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">报告使用单位</label>
                                        <div class="col-md-3 p-0">
                                            <input id="queryReportUseUnitName" name="queryReportUseUnitName"
                                                   class="form-control input-full input-full" type="hidden"/>
                                            <input id="queryUseUnitName" name="queryUseUnitName" class="form-control input-full input-full"
                                                   placeholder="报告使用单位"
                                                   onclick="selectCustomer(this)" readonly="readonly"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">预评报告文号</label>
                                        <div class="col-md-3 p-0">
                                            <input id="preauditReportNumber" name="preauditReportNumber" class="form-control input-full"
                                                   placeholder="咨评报告文号"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结果报告文号</label>
                                        <div class="col-md-3 p-0">
                                            <input id="resultReportNumber" name="resultReportNumber" class="form-control input-full"
                                                   placeholder="结果报告文号"/>
                                        </div>

                                    </div>


                                    <div class="form-group form-inline">
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="statisticsByCondition()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 10px" class="btn btn-success  btn-sm" type="button"
                                                onclick="refresh()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            刷新
                                        </button>
                                    </div>
                                </form>
                                <iframe id="report_iframe" width="100%" height="100%"
                                        src="${pageContext.request.contextPath}/ureport/preview?_u=erp:projectDeb.ureport.xml&_i=1&_r=1"
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
        document.getElementById('report_iframe').src = "${pageContext.request.contextPath}/ureport/preview?_u=erp:projectDeb.ureport.xml&_i=1&_r=1&queryProjectName=" + data.queryProjectName + "&queryConsignorName=" + data.queryConsignorName +
            "&queryReportUseUnitName=" + data.queryReportUseUnitName + "&preauditReportNumber=" + data.preauditReportNumber + "&resultReportNumber=" + data.resultReportNumber + "&queryUserAccount=" + data.queryUserAccountName +
            "&queryEntrustPurposeName=" + data.queryEntrustPurposeName + "&queryLoanTypeName=" + data.queryLoanTypeName + "&queryDepartmentName=" + data.queryDepartmentName;
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
                $("#queryReportUseUnitName").val(nodes[0].name);
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


    //选择人员
    function personSelect() {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $("#queryUserAccountName").val(data.name);
                    $("#queryUserAccount").val(data.account);
                }
                else {
                    $("#queryUserAccountName").val('');
                    $("#queryUserAccount").val('');
                }
            }
        });
    }

    //部门
    function selectDepartment(this_) {
        var options = {
            onSelected: function (nodes) {
                $("#queryDepartmentId").val(nodes[0].id);
                $("#queryDepartmentName").val(nodes[0].name);
            }
        };
        erpDepartment.select(options);
    };

    //刷新
    function refresh() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/assessReport/projectDebtRefresh",
            type: "post",
            dataType: "json",
            data: {},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('刷新成功');
                    document.getElementById('report_iframe').src = "${pageContext.request.contextPath}/ureport/preview?_u=erp:projectDeb.ureport.xml&_i=1&_r=1"
                }
                else {
                    Alert("刷新数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }
</script>
</html>
