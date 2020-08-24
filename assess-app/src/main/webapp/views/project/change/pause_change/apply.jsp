<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目暂停变更</title>

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

            <%@include file="/views/project/change/pause_change/modules/info_form.jsp" %>

            <!-- 公共尾部模块引用 -->
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-md-4 col-sm-4 col-xs-12 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:if test="${not empty processInsId and processInsId ne '0'}">
                            <button type="button" class="btn btn-warning" style="margin-left: 10px;" onclick="masterObj.closeProcess()">
                                撤销
                            </button>
                        </c:if>
                        <button id="commit_btn" class="btn btn-success" style="margin-left: 10px;" onclick="masterObj.commit();">
                            提交
                        </button>
                    </div>
                </div>
            </div>
            <%--返回修改--%>
            <c:if test="${processInsId != 0}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="process_variable_form">
                    <%@include file="/views/share/form_edit.jsp" %>
                </form>
            </c:if>
            <!-- 尾部end -->

        </div>

    </div>

</div>


<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    var masterObj = {

    };



    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.commit = function() {
        if ("${processInsId}" == "0") {
            //申请
            projectPauseApplyObj.commit();
        } else {
            //修改提交
            projectPauseApplyObj.editCommit();
        }
    }

    //撤销
    masterObj.closeProcess = function () {
        AlertConfirm("是否确认撤销", "流程撤销后将不可恢复", function () {
            var approvalModelDto = formSerializeArray($("#process_variable_form"));
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/public/closeProcess",
                type: "post",
                data: approvalModelDto,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "流程撤销成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        })
    };


    $(function () {

    });
</script>
