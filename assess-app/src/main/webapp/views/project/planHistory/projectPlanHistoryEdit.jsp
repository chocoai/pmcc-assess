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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-external-link-square"></i> 项目计划
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="hidden-xs">计划名称</th>
                            <th class="hidden-xs">开始日期</th>
                            <th class="hidden-xs">结束时间</th>
                            <th class="hidden-xs">说明</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${projectPlanHistories}">


                            <tr>
                                <td class="hidden-xs">
                                        ${item.projectPhaseName}
                                    <input type="hidden" value="${item.planId}" class="plan_id">
                                </td>
                                <td class="hidden-xs"><input type="text" class="form-control dbdate"
                                                             readonly="readonly"
                                                             data-date-format="yyyy-mm-dd"
                                                             id="projectPlanStart${item.planId}" value="<fmt:formatDate value="${item.afterPlanStart}"
                                                                                      pattern="yyyy-MM-dd"/>"></td>
                                <td class="hidden-xs"><input type="text" class="form-control dbdate"
                                                             readonly="readonly"
                                                             data-date-format="yyyy-mm-dd"
                                                             id="projectPlanEnd${item.planId}" value="<fmt:formatDate value="${item.afterPlanEnd}"
                                                                                      pattern="yyyy-MM-dd"/>"></td>
                                <td class="hidden-xs"><input type="text" class="form-control"
                                                             id="planRemarks${item.planId}"
                                                             value="${item.afterPlanRemarks}"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                附件
                            </label>
                            <div class="col-sm-11">
                                <input id="apply_file" name="apply_file" type="file" multiple="false">
                                <div id="_apply_file">
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_apply.jsp" %>
                </div>
            </div>

            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">

    $(function () {
        uploadFiles("apply_file", "saveform", {},
            {
                tableName: "tb_project_plan_history",
                processInsId: ${processInsId},
                tableId: 0,
                fieldsName: "planHistory",
                projectId: ${projectId}
            }
            , function (data) {
                //alert(data);
            });
        GetFileShows("apply_file",
            {
                tableName: "tb_project_plan_history",
                processInsId: "${processInsId}",
                tableId: 0,
                projectId: ${projectId}
            }, 1);
    })
    //保存数据到数据库
    function saveform() {
        var plans = [];
        $(".plan_id").each(function () {
            var id = $(this).val();
            if ($("#projectPlanStart" + id).length > 0) {
                plans.push({
                    planId: id,
                    planStart: $("#projectPlanStart" + id).val(),
                    planEnd: $("#projectPlanEnd" + id).val(),
                    planRemarks: $("#planRemarks" + id).val()
                });
            }
        });
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectPlanHistory/saveEditProjectPlanHistory",
            type: "post",
            dataType: "json",
            data: {
                boxId:${boxId},
                processInsId: "${processInsId}",
                taskId: "${taskId}",
                activityName: "${activityName}",
                activityCnName: "${activityCnName}",
                agentUserAccount: "${agentUserAccount}",
                projectId: "${projectId}",
                planString: JSON.stringify(plans)
            },
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
            }
            ,
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }


</script>

</body>
</html>
