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
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    项目名称
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="项目名称" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    文号
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="文号" name="queryNumberRecord"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    项目经理
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="hidden" name="queryManager">
                                    <input type="text" readonly="readonly"
                                           onclick="selectProjectPhaseExecuteUserAccount(this);"
                                           placeholder="项目经理" class="form-control">
                                </div>
                            </div>

                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <button type="button" class="btn btn-primary"
                                        onclick="statisticsByCondition()">
                                    查询
                                </button>
                            </div>
                        </div>
                    </form>
                    <iframe id="report_iframe" width="100%" height="100%"
                            src="${pageContext.request.contextPath}/ureport/preview?_u=erp:financialReferenceProject.ureport.xml&_i=1&_r=1"
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
        var select = {};
        var query = formSerializeArray($("#frmQuery"));
        //只获取有值的对象属性
        var tempObj = Object.keys(query);
        for (var i = 0; i < tempObj.length; i++) {
            var key = tempObj[i];
            var value = query[key];
            //判断是否存在值
            if (value) {
                select[key] = value;//动态添加属性
            }
        }
        var url = "${pageContext.request.contextPath}/ureport/preview?_u=erp:financialReferenceProject.ureport.xml&_i=1&_r=1";
        if (Object.keys(query).length != 0) {
            url += "&" + parseParam(select);
        }
        document.getElementById('report_iframe').src = url;
    }


    function changeFrameHeight() {
        var ifm = document.getElementById("report_iframe");
        ifm.height = document.documentElement.clientHeight - 56;
    }

    window.onresize = function () {
        changeFrameHeight();
    };


    function selectProjectPhaseExecuteUserAccount(_this) {
        selectExecuteUserAccount(false, function (data) {
            if (data && data.account) {
                $(_this).val(data.name);
                $(_this).closest("form").find("[name='queryManager']").val(data.account);
            } else {
                Alert("还未选择任何人员");
            }
        });
    }

    function selectExecuteUserAccount(multi, callback) {
        erpEmployee.select({
            multi: multi,
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (callback) {
                    callback(data);
                }
            }
        });
    }

    function parseParam(param) {
        var arr = [];
        var keys = Object.keys(param);
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            var value = param[key];
            if (!value) {
                // continue ;
            }
            var paramStr = key + "=" + value;
            arr.push(paramStr)
        }
        return arr.join("&");
    }

</script>
</html>
