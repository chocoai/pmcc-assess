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
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%--项目基本信息--%>
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>

                    <c:forEach items="${areaGroups}" var="item">
                        <div class="col-md-12 area_panel">
                            <input type="hidden" name="areaGroupId" value="${item.id}">
                            <input type="hidden" name="areaName" value="${item.areaName}">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                                ${item.areaName}
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form id="frmJudgeObject${item.id}" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        评估基准日<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">
                                                            <fmt:formatDate value="${item.valueTimePoint}" pattern="yyyy-MM-dd"/>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        基准日说明<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.timePointExplain}</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        委托目的<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.entrustPurposeName}</label>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        委托目的描述<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.remarkEntrustPurpose}</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        价值类型<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.valueDefinitionName}</label>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产范围<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.propertyScopeName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产包括<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.scopeInclude}</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产不包括<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <label class="form-control input-full">${item.scopeNotInclude}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                       变更原因
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    方案信息变更原因
                                                </label>
                                                <div class="col-sm-11">
                                                    <label class="form-control input-full">${costsProjectChangeLog.changeReason}</label>                                        </div>

                                            </div>
                                            </div>
                                        </div>

                                </form>
                            </div>
                        </div>
                    </div>
                        <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

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

                    AlertSuccess("成功", "提交数据成功",function(){
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