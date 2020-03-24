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
                                        报告信息
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
                                    <input type="hidden" name="id" value="${projectXlxReportIndividual.id}">
                                    <input type="hidden" name="projectName" value="${projectInfo.projectName}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectCategory"
                                           value="${projectInfo.projectCategoryId}">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1  control-label">
                                                    项目名称
                                                </label>
                                                <div class="col-md-3">
                                                    <label class="form-control input-full">${projectXlxReportIndividual.projectName}</label>
                                                </div>

                                                <label class="col-md-1  control-label">
                                                    文号
                                                </label>
                                                <div class="col-md-3">
                                                    <label class="form-control input-full">${projectXlxReportIndividual.numberValue}</label>
                                                </div>
                                                <label class="col-md-1  control-label">
                                                    委托单位
                                                </label>
                                                <div class="col-md-3">
                                                    <label class="form-control input-full">${projectXlxReportIndividual.entrustedUnit}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <%--<hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"--%>
                                        <%--color="#6f5499" size="10"/>--%>

                                    <c:if test="${assessProjectType == 'assets'}">
                                        <%@include file="detail/assets.jsp" %>
                                    </c:if>


                                    <c:if test="${assessProjectType == 'house'}">
                                        <%@include file="detail/house.jsp" %>
                                    </c:if>


                                    <c:if test="${assessProjectType == 'land'}">
                                        <%@include file="detail/land.jsp" %>
                                    </c:if>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1  control-label">
                                                    承办表
                                                </label>
                                                <div class="col-md-3">
                                                    <label class="form-control input-full">${projectXlxReportIndividual.undertakeSheet}</label>
                                                </div>
                                                <label class="col-md-1  control-label">
                                                    备注
                                                </label>
                                                <div class="col-md-7">
                                                    <label class="form-control input-full">${projectXlxReportIndividual.remark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        自定义名称
                                    </div>
                                    <div class="card-tools">
                                        <button type="button" class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form class="form-horizontal" id="declareApplyForm">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">


                                    <div id="assetsCustomizeDataOther_fieldId">
                                        <c:forEach items="${customizeDataField}" var="item">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class=" col-sm-1 control-label">
                                                            自定义名称<span class="symbol required"></span>
                                                        </label>
                                                        <div class="col-sm-2">
                                                            <label class=" form-control input-full">
                                                                    ${item.name}
                                                            </label>
                                                        </div>

                                                        <label class=" col-sm-1 control-label">
                                                            附件
                                                        </label>
                                                        <div class="col-sm-7">
                                                            <div id="_other_Enclosure${item.id}"></div>
                                                        </div>
                                                    </div>
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            var fileId = 'other_Enclosure${item.id}';
                                                            xlxReportIndividual.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false);
                                                        });
                                                    </script>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>


                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class=" col-sm-1 control-label">
                                                    备注
                                                </label>
                                                <div class="col-sm-11">
                                                    <label class=" form-control input-full">
                                                        ${assetsCustomizeDataField.remark}
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>


                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script>

    var xlxReportIndividual = {};

    xlxReportIndividual.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    xlxReportIndividual.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    xlxReportIndividual.showFile = function (target, tableName, id, deleteFlag) {
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

