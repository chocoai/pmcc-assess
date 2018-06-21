<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>
<body>
<div class="main-content" style="margin:0px;">
    <div class="container" style="min-height:0">
        <div class="panel-body">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-external-link-square"></i> 项目计划
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-hover table-bordered">
                        <thead>
                        <tr>
                            <th class="hidden-xs">计划名称</th>
                            <th class="hidden-xs">开始日期</th>
                            <th class="hidden-xs">结束时间</th>
                            <th class="hidden-xs">说明</th>
                            <th class="hidden-xs">开始日期(变更前)</th>
                            <th class="hidden-xs">结束时间(变更前)</th>
                            <th class="hidden-xs">说明(变更前)</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${projectPlanHistories}">


                            <tr>
                                <td class="hidden-xs">${item.projectPhaseName}</td>
                                <td class="hidden-xs"><fmt:formatDate value="${item.afterPlanStart}"
                                                                      pattern="yyyy-MM-dd"/></td>
                                <td class="hidden-xs"><fmt:formatDate value="${item.afterPlanEnd}"
                                                                      pattern="yyyy-MM-dd"/></td>
                                <td class="hidden-xs">${item.afterPlanRemarks}</td>
                                <td class="hidden-xs"><fmt:formatDate value="${item.beforePlanStart}"
                                                                      pattern="yyyy-MM-dd"/></td>
                                <td class="hidden-xs"><fmt:formatDate value="${item.beforePlanEnd}"
                                                                      pattern="yyyy-MM-dd"/></td>
                                <td class="hidden-xs">${item.beforePlanRemarks}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">
                            附件
                        </label>
                        <div class="col-sm-11">
                            <div id="_apply_file"></div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">

    $(function () {
        GetFileShows("apply_file",
            {
                tableName: "tb_project_plan_history",
                processInsId: "${processInsId}",
                tableId: 0,
                projectId: ${projectId}
            }, 0);
    })
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectPlanHistory/submitProjectPlanHistory",
            type: "post",
            dataType: "json",
            data: formParams("frm_approval"),
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

</body>
</html>
