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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>假设开发法</h3>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="md_development_form">
                        <input type="hidden" name="id" value="${mdDevelopment.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${mdDevelopment.price}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                报告附件
                            </label>
                            <div class="col-sm-3">
                                <div id="_report_file"></div>
                            </div>
                        </div>
                    </form>
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
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdDevelopment,
                tableId: '${mdDevelopment.id}'
            },
            editFlag: false,
            deleteFlag: false
        })
    })
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

