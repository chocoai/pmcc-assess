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
                    <form id="master" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                假定未设立法定优先受偿权单价(元/㎡)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="假定未设立法定优先受偿权单价" data-rule-number='true'
                                           name="notSetUpUnitPrice" class="form-control"
                                           value="${master.notSetUpUnitPrice}">
                                </div>
                            </div>
                            <label class="col-sm-1 control-label">
                                假定未设立法定优先受偿权总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="假定未设立法定优先受偿权总价" data-rule-number='true'
                                           name="notSetUpTotalPrice" class="form-control"
                                           value="${master.notSetUpTotalPrice}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                估价师知悉的法定优先受偿款总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="估价师知悉的法定优先受偿款总价" data-rule-number='true'
                                           name="knowTotalPrice" class="form-control" value="${master.knowTotalPrice}">
                                </div>
                            </div>
                            <label class="col-sm-1 control-label">
                                已抵押担保的债权数额总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="已抵押担保的债权数额总价" data-rule-number='true'
                                           name="mortgagedTotalPrice" class="form-control"
                                           value="${master.mortgagedTotalPrice}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                拖欠的建设工程价款总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="拖欠的建设工程价款总价" data-rule-number='true'
                                           name="owedTotalPrice" class="form-control" value="${master.owedTotalPrice}">
                                </div>
                            </div>
                            <label class="col-sm-1 control-label">
                                其它法定优先受偿款总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="其它法定优先受偿款总价" data-rule-number='true'
                                           name="otherTotalPrice" class="form-control"
                                           value="${master.otherTotalPrice}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                抵押价值单价(元/㎡)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="抵押价值单价" data-rule-number='true'
                                           name="mortgageUnitPrice" class="form-control"
                                           value="${master.mortgageUnitPrice}">
                                </div>
                            </div>
                            <label class="col-sm-1 control-label">
                                抵押价值总价(元)
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-5">
                                    <input type="text" required placeholder="抵押价值总价" data-rule-number='true'
                                           name="mortgageTotalPrice" class="form-control"
                                           value="${master.mortgageTotalPrice}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    附件
                                </label>
                                <div class="col-md-11 col-sm-11 col-xs-12">
                                    <input id="apply_file" type="file" multiple="false">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${master.id}">
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-5 col-sm-offset-5">
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
                target: "apply_file",
                formData: {
                    tableName: "tb_scheme_reimbursement",
                    processInsId: "${empty processInsId?'0':processInsId}",
                    creater: "${currUserInfo.userAccount}",
                    tableId: "${master.id}",
                    fieldsName: "apply"
                },
                deleteFlag: true
            },
            {
                onUploadComplete: function (file, result) {
                    loadFiles();
                }
            }
        );
        loadFiles();
    })

    function loadFiles() {
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_scheme_reimbursement",
                processInsId: "${empty processInsId?'0':processInsId}",
                creater: "${currUserInfo.userAccount}",
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: true
        })
    }

    function submit() {
        if ($("#master").length > 0) {
            $("#master").validate();
            if (!$("#master").valid()) {
                return false;
            }
        }
        var formData = formParams("master");

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

</script>

</html>

