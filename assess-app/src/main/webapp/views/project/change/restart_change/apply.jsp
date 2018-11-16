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
    <title>项目重启变更</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <div style="display: none;">
                <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            </div>
            <!-- 公共模块end -->

            <%@include file="/views/project/change/restart_change/modules/info_form.jsp" %>

            <!-- 公共尾部模块引用 -->
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-md-4 col-sm-4 col-xs-12 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消<i style="margin-left: 10px" class="fa fa-times-circle"></i>
                        </button>

                        <button id="commit_btn" class="btn btn-primary" onclick="masterObj.commit();">
                            提交<i style="margin-left: 10px" class="fa fa-check-circle"></i>
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
            projectRestartApplyObj.commit();
        } else {
            //修改提交
            projectRestartApplyObj.editCommit();
        }
    }


    $(function () {

    });
</script>
