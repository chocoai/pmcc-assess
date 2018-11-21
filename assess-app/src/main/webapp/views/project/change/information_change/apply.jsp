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
                        项目信息
                        <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
                    </h2>
                    <input type="hidden" value="${projectInfo.id}">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <%@include file="/views/project/stageInit/other/projectInfo.jsp" %>
                </div>
            </div>
            <%@include file="/views/project/stageInit/other/otherProjectIndexJs.jsp" %>
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


    <%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    (function () {
        objProject.info.loadInit({
            province: objProject.isNotBlank("${projectInfo.province}") ? "${projectInfo.province}" : null,
            city: objProject.isNotBlank("${projectInfo.city}") ? "${projectInfo.city}" : null,
            district: objProject.isNotBlank("${projectInfo.district}") ? "${projectInfo.district}" : null,
            valueType: objProject.isNotBlank("${projectInfo.valueType}") ? "${projectInfo.valueType}" : null,
            entrustPurpose: objProject.isNotBlank("${projectInfo.entrustPurpose}") ? "${projectInfo.entrustPurpose}" : null,
            urgency: objProject.isNotBlank("${projectInfo.urgency}") ? "${projectInfo.urgency}" : null
        });
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
        var data = {
            oldRecord:JSON.stringify(JSON.parse('${el:toJsonString(projectInfo)}')),
            newRecord:JSON.stringify(formParams(objProject.config.info.frm)),
            changeReason:$("#changeReason").val(),
            projectId:"${projectInfo.id}"
        };

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

