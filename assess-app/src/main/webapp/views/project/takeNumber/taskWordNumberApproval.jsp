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

            <div class="x_panel area_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>拿取文号</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="frmProjectTakeNumber">
                        <input type="hidden" name="id" value="${projectTakeNumber.id}">
                        <input type="hidden" name="assessProjectType" value="${projectTakeNumber.assessProjectType}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    报告出具日期<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <label class="form-control"><fmt:formatDate
                                            value='${projectTakeNumber.reportIssuanceDate}'
                                            pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    作业结束时间<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <label class="form-control"><fmt:formatDate
                                            value='${projectTakeNumber.homeWorkEndTime}'
                                            pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div>
                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                        资质类型
                                    </label>
                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                        <label class="form-control">${projectTakeNumber.qualificationTypeName}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    现场查勘开始日期
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <label class="form-control"><fmt:formatDate
                                            value='${projectTakeNumber.investigationsStartDate}'
                                            pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    现场查勘结束日期
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <label class="form-control"><fmt:formatDate
                                            value='${projectTakeNumber.investigationsEndDate}'
                                            pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">估价师</label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <label class="form-control">${projectTakeNumber.realEstateAppraiserName}</label>
                                </div>
                            </div>
                        </div>



                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    二维码
                                </label>
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                    <div id="_ProjectTakeNumber_BaseOrCode"></div>
                                    <img src="">
                                </div>

                            </div>

                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    文档
                                </label>
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                    <div id="_ProjectTakeNumber_Document"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    报告类型<span class="symbol required"></span>
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-12">
                                    <label class="form-control"
                                           name="reportType">${projectTakeNumber.reportTypeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    文号
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-12">
                                    <label class="form-control"
                                           name="numberValue">${projectTakeNumber.numberValue}</label>
                                </div>
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

<script>
    var baseTakeNumber = {};

    baseTakeNumber.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    baseTakeNumber.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    baseTakeNumber.config = {
        frm: $("#frmProjectTakeNumber")
    };

    baseTakeNumber.showFile = function (target, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };


    baseTakeNumber.initFormData = function (data) {
        var fileArr = ["ProjectTakeNumber_BaseOrCode","ProjectTakeNumber_Document"];
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        frm.find("img").attr({src: data.imgPath});
        if (fileArr) {
            $.each(fileArr, function (i, n) {
                baseTakeNumber.showFile(n, AssessDBKey.ProjectTakeNumber, baseTakeNumber.isNotBlank(data.id) ? data.id : '0', false);
            });
        }
    };

    $(function () {
        if ("${projectTakeNumber}") {
            var obj = '${el:toJsonString(projectTakeNumber)}';
            baseTakeNumber.initFormData(JSON.parse(obj));
        }
    });

</script>

<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>
</html>

