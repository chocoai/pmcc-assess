<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/project-init.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        项目信息
                        <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
                    </h2>
                    <input type="hidden" value="${projectInfo.id}" id="projectInfoId">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <!-- 项目基本信息 start -->
                    <%@include file="/views/project/stageInit/initModel/info.jsp" %>
                    <!-- 项目基本信息 end -->
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
                <%@include file="/views/project/stageInit/initModel/consignor.jsp" %>
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
                <%@include file="/views/project/stageInit/initModel/possessor.jsp" %>
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
                <%@include file="/views/project/stageInit/initModel/unit_information.jsp" %>
                <!-- 报告使用单位 end -->
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="projectApply();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    function getFormData() {
        var data = {};
        var projectInfo = formParams("frm_project_info");//项目信息
        var consignor = formParams("frm_consignor"); //委托人信息
        var possessor = formParams("frm_possessor"); //占有人信息
        var unitinformation = formParams("frm_unitinformation"); //报告使用单位信息
        data.projectInfo = projectInfo;
        data.consignor = consignor;
        data.possessor = possessor;
        data.unitinformation = unitinformation;
        return data;
    }

    function projectApply() {
        //js校验
        if (!$("#frm_project_info").valid()) {
            return false;
        }
        if (!$("#frm_consignor").valid()) {
            return false;
        }
        if (!hasLinkman(Contacts.prototype.CONSIGNOR().getData().table)) {
            Alert('还未填写委托人联系人信息');
            return false;
        }
        if (!$("#frm_possessor").valid()) {
            return false;
        }
        if (!hasLinkman(Contacts.prototype.POSSESSOR().getData().table)) {
            Alert('还未填写占有人联系人信息');
            return false;
        }
        if (!$("#frm_unitinformation").valid()) {
            return false;
        }
        if (!hasLinkman(Contacts.prototype.UNIT_INFORMATION().getData().table)) {
            Alert('还未填写报告使用单位联系人信息');
            return false;
        }
        var data = {};
        data.formData = JSON.stringify(getFormData());
        data.projectInfoId = $("#projectInfoId").val();
        var url = "${pageContext.request.contextPath}/projectInfo/projectApplySubmit";
        if ("${empty processInsId?"0":processInsId}" != "0") {
            url = "${pageContext.request.contextPath}/projectInfo/projectEditSubmit";
            var approvalData = formParams("frm_approval");
            data = $.extend({}, approvalData, data);
        }
        Loading.progressShow();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    //是否填写了联系人
    function hasLinkman(tbListId) {
        var rows = $("#" + tbListId).bootstrapTable('getData');
        if (rows == null || rows.length <= 0) return false;
        return true;
    }

    $(function () {
        Contacts.prototype.getUrl = function () {
            return "${pageContext.request.contextPath}";
        };
        //载入选项框
        POSSESSOR.prototype.tabControl();
        CONSIGNOR.prototype.tabControl();

        Contacts.prototype.UNIT_INFORMATION().loadDataList("${projectInfo.unitInformationVo.id}", null);
        Contacts.prototype.CONSIGNOR().loadDataList("${projectInfo.consignorVo.id}");
        Contacts.prototype.POSSESSOR().loadDataList("${projectInfo.possessorVo.id}");

        /**返回修改页面**/
        var projectInfo = "${projectInfo.consignorVo}";
        if (projectInfo != null && projectInfo != '') {//返回修改页面
            POSSESSOR.prototype.tabControlUpdate("${projectInfo.possessorVo.pType}");
            CONSIGNOR.prototype.tabControlUpdate("${projectInfo.consignorVo.csType}")
        }
    });
</script>
