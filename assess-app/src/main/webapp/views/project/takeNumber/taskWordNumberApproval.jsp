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
                    <form class="form-horizontal">
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
                    </form>
                </div>

                <c:forEach items="${projectTakeNumberDetailList}" var="projectTakeNumberDetail" varStatus="userStatus">
                    <div class="x_content">
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                        </div>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <div class="row panel">
                                <form>
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12  panel-heading  text-center">
                                        <h2>第${userStatus.index+1}拿号
                                        </h2>
                                    </div>
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12  panel-body ">
                                        <div class="row">
                                            <input type="hidden" name="id" value="${projectTakeNumberDetail.id}">
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                二维码:<div id="_ProjectTakeNumber_BaseOrCode${projectTakeNumberDetail.id}"></div>
                                            </div>
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                <label class="form-control">报告类型:${projectTakeNumberDetail.reportTypeName}</label>
                                            </div>
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                上传的文档:<div id="_projectTakeNumberDetailSysAttachmentDto${projectTakeNumberDetail.id}"></div>
                                            </div>
                                            <div class="x-valid">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <label class="form-control">文号:${projectTakeNumberDetail.numberValue}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <script>
                        $(document).ready(function () {
                            var fileArr = ["ProjectTakeNumber_BaseOrCode${projectTakeNumberDetail.id}" , "projectTakeNumberDetailSysAttachmentDto${projectTakeNumberDetail.id}" ];
                            $.each(fileArr, function (i, n) {
                                baseTakeNumber.showFile(n, AssessDBKey.ProjectTakeNumberDetail, '${projectTakeNumberDetail.id}', false);
                            });
                        });
                    </script>
                </c:forEach>

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



</script>

<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>
</html>

