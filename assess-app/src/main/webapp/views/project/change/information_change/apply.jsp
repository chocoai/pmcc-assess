<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目信息修改</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson" value='${projectInfoVoJson}'>
                        项目信息
                        <small>${projectInfo.projectCategoryName}</small>
                    </h2>
                    <input type="hidden" value="${projectInfo.id}">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <%@include file="/views/project/stageInit/stageInitModel/projectInfo.jsp" %>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2> 委托人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <!-- 委托人 start -->
                <%@include file="/views/project/stageInit/stageInitModel/projectConsignor.jsp" %>
                <!-- 委托人 end -->
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2> 占有人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <!-- 占有人 start -->
                <%@include file="/views/project/stageInit/stageInitModel/projectPossessor.jsp" %>
                <!-- 占有人 end -->
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <!-- 报告使用单位 start -->
                <%@include file="/views/project/stageInit/stageInitModel/projectUnit_information.jsp" %>
                <!-- 报告使用单位 end -->
            </div>
            <%@include file="/views/project/stageInit/stageInitModel/otherProjectIndexJs.jsp" %>
            <!-- 公共模块end -->
            <%@include file="apply/project_info_apply.jsp" %>
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

            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
            <!-- 尾部end -->
        </div>
    </div>


    <%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    (function () {
        objProject.loadInit();

        $("#" + objProject.config.info.frm).find("input[name='userAccountMember']").parent().parent().parent().hide();
        $("#" + objProject.config.info.frm).find("input[name='userAccountManager']").parent().parent().parent().hide();
    }());

    var masterObj = {};
    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.commit = function () {
        if (!$("#" + objProject.config.info.frm).valid()) {
            return false;
        }
        if (!$("#project_info_form").valid()) {
            return false;
        }
        var data = {};

        if("${CurrentStep}"!="0"){
            var projectInfoVoJson = $("#projectInfoVoJson").val() ;
            if (projectInfoVoJson){
                try {
                    data.oldRecord = JSON.stringify(JSON.parse(projectInfoVoJson));
                } catch (e) {
                }
                <%--data.oldRecord = JSON.stringify(JSON.parse('${el:toJsonString(projectInfo)}')) ;--%>
            }
        }
        data.newRecord = JSON.stringify(objProject.getFormData());
        data.changeReason = $("#changeReason").val();
        data.projectId = "${projectInfo.id}";
        data.id = "${costsProjectChangeLog.id}"


        if ("${processInsId}" == "0") {
            //申请
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/project.information.change/applyCommit",
                type: "post",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });
                    }
                    else {
                        Alert("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            var approvalData = formParams("frm_approval");
            data = $.extend({}, approvalData, data);
            //修改提交
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/project.information.change/editCommit",
                type: "post",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });
                    }
                    else {
                        Alert("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };

</script>

