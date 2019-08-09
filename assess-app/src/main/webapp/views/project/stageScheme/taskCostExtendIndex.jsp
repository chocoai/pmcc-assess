<!-- 成本法 -->
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
                    <h3>成本法</h3>
                </div>
                <div class="x_content" >
                    <form class="form-horizontal" id="md_cost_form">
                        <input type="hidden" name="id" value="${mdCost.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" data-rule-number="true" required name="price" value="${mdCost.price}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                附件<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <input id="report_file" name="report_file" type="file" multiple="false">
                                <div id="_report_file"></div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCost.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCost.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });

    //提交
    function submit() {
        if (!$("#md_cost_form").valid()) {
            return false;
        }
        var data = formParams("md_cost_form");
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

</script>

</html>

