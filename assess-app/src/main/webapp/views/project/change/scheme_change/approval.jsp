<%--
  Created by IntelliJ IDEA.
  User: zly
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目信息变更</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!-- 公共模块end -->
            <c:forEach items="${areaGroups}" var="item">
                <div class="x_panel area_panel">
                    <input type="hidden" name="areaGroupId" value="${item.id}">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>
                             ${item.areaName}
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form id="frmJudgeObject${item.id}" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        评估基准日
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">
                                            <fmt:formatDate value="${item.valueTimePoint}" pattern="yyyy-MM-dd"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        基准日说明
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.timePointExplain}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.entrustPurposeName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的描述
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.remarkEntrustPurpose}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值类型
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.valueDefinitionName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产范围
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.propertyScopeName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产包括
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.scopeInclude}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产不包括
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.scopeNotInclude}</label>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>

            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa fa-info-circle"></i>
                        <small>变更原因</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                            方案信息变更原因
                                        </label>
                                        <div class="col-md-11 col-sm-11 col-xs-12">
                                            <label class="form-control">${costsProjectChangeLog.changeReason}</label>                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 公共尾部模块引用 -->
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>



<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script type="text/javascript">


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/project.information.change/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
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