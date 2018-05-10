<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2> 项目信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_project_info" class="form-horizontal">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${projectInfo.projectName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">大类<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                   <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理下级<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">样式</label>
                                </div>
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">上传附件</label>
                                <div class="col-sm-3">
                                    <input id="attachmentProjectInfoId" name="attachmentProjectInfoId" required="required" placeholder="上传附件"  class="form-control" type="file">
                                    <div id="_attachmentProjectInfoId"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">
    $(function () {

    })
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formParams("frm_approval");
        var dataManager = formParams("frm_set_project_manager");
        data = $.extend({}, dataManager, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectApprovalSubmit",
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
</html>
