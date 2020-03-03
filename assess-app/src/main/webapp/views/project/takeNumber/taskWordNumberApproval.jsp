<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 公共模块end -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        拿取文号
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <input type="hidden" name="id" value="${projectTakeNumber.id}">
                                    <input type="hidden" name="assessProjectType"
                                           value="${projectTakeNumber.assessProjectType}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    报告出具日期<span class="symbol required"></span>
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value='${projectTakeNumber.reportIssuanceDate}'
                                                            pattern='yyyy-MM-dd'/></label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    作业结束时间<span class="symbol required"></span>
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value='${projectTakeNumber.homeWorkEndTime}'
                                                            pattern='yyyy-MM-dd'/></label>
                                                </div>

                                                <label class="col-sm-1 control-label">
                                                    资质类型
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full">${projectTakeNumber.qualificationTypeName}</label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    现场查勘开始日期
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value='${projectTakeNumber.investigationsStartDate}'
                                                            pattern='yyyy-MM-dd'/></label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    现场查勘结束日期
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full"><fmt:formatDate
                                                            value='${projectTakeNumber.investigationsEndDate}'
                                                            pattern='yyyy-MM-dd'/></label>
                                                </div>
                                                <label class="col-sm-1 control-label">估价师</label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <label class="form-control input-full">${projectTakeNumber.realEstateAppraiserName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${projectTakeNumberDetailList}" var="projectTakeNumberDetail"
                               varStatus="userStatus">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                            <h2>第${userStatus.index+1}拿号
                                            </h2>
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form class="form-horizontal">
                                        <input type="hidden" name="id"
                                               value="${projectTakeNumberDetail.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class=" col-md-1 control-label">
                                                        文档
                                                    </div>
                                                    <div class="col-sm-5 ">
                                                        <div id="_projectTakeNumberDetailSysAttachmentDto${projectTakeNumberDetail.id}"></div>
                                                    </div>
                                                    <div class=" col-md-1 control-label">
                                                        报告类型
                                                    </div>
                                                    <div class="col-sm-5 ">
                                                        <label class="form-control input-full">报告类型:${projectTakeNumberDetail.reportTypeName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class=" col-md-1 control-label">
                                                        文号
                                                    </div>
                                                    <div class="col-sm-5 ">
                                                        <label class="form-control input-full">${projectTakeNumberDetail.numberValue}</label>
                                                    </div>
                                                    <div class=" col-md-1 control-label">
                                                        二维码
                                                    </div>
                                                    <div class="col-sm-5 ">
                                                        <div id="_ProjectTakeNumber_BaseOrCode${projectTakeNumberDetail.id}"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <script>
                                        $(document).ready(function () {
                                            var fileArr = ["ProjectTakeNumber_BaseOrCode${projectTakeNumberDetail.id}", "projectTakeNumberDetailSysAttachmentDto${projectTakeNumberDetail.id}"];
                                            $.each(fileArr, function (i, n) {
                                                baseTakeNumber.showFile(n, AssessDBKey.ProjectTakeNumberDetail, '${projectTakeNumberDetail.id}', false);
                                            });
                                        });
                                    </script>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


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

