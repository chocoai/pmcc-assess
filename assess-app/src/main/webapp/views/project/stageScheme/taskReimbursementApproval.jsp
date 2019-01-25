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
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${judgeObjectName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                假定未设立法定优先受偿权单价(元/㎡)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.notSetUpUnitPrice}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                假定未设立法定优先受偿权总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.notSetUpTotalPrice}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                估价师知悉的法定优先受偿款总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.knowTotalPrice}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                已抵押担保的债权数额总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.mortgagedTotalPrice}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                拖欠的建设工程价款总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.owedTotalPrice}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                其它法定优先受偿款总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.otherTotalPrice}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                抵押价值单价(元/㎡)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.mortgageUnitPrice}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                抵押价值总价(元)
                            </label>
                            <div class="col-sm-5">
                                <label class="form-control">${master.mortgageTotalPrice}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    附件
                                </label>
                                <div class="col-md-11 col-sm-11 col-xs-12">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.SchemeReimbursement,
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: false
        })
    })

    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>

