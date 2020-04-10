<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                    <!-- 填写表单 start -->
                    <c:forEach items="${generationVos}" var="generationVo">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                                ${generationVo.areaGroupName}
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form class="form-horizontal" id="groupForm${generationVo.id}">
                                        <input type="hidden" name="id">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        报告出具日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value='${generationVo.reportIssuanceDate}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        作业结束时间<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value='${generationVo.homeWorkEndTime}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>

                                                    <label class="col-sm-1 control-label">
                                                        资质类型
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${generationVo.qualificationTypeName}</label>
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
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value='${generationVo.investigationsStartDate}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        现场查勘结束日期
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value='${generationVo.investigationsEndDate}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">估价师</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${generationVo.realEstateAppraiserName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group" style="display: none;">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">查询码</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${generationVo.queryCode}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">备案号</label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${generationVo.recordNo}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        备案日期
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value='${generationVo.recordDate}'
                                                                pattern='yyyy-MM-dd'/></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">
                                                                ${reportType.name}
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <script>
                            $(function () {
                                getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                                    $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                                        fileShow(n + '${generationVo.areaGroupId}', false, '${generationVo.id}');
                                    });
                                });
                            })
                        </script>
                    </c:forEach>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>

</html>
<script type="text/javascript">
    function fileShow(fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: id == undefined ? 0 : id
            },
            editFlag: true,
            signatureFlag: '${activityCnName}'.indexOf("盖章") > -1,
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    /**
     * @author:  zch
     * 描述:报告附件 数组  拼接为REPORT_TYPE_PREAUDIT
     * @date:  2019-05-27
     **/
    function getSchemeReportGenerationFileControlIdArray(callback) {
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '', function (html, data) {
            var fileArray = [];
            var underline = "_";
            data.forEach(function (value, index) {
                var fieldName = value.fieldName;
                if (fieldName) {
                    var strArray = fieldName.split(".");
                    var tempArray = [];
                    if (strArray.length >= 1) {
                        strArray.forEach(function (item, i) {
                            tempArray.push(item.toUpperCase());
                        });
                    }
                    if (tempArray.length >= 1) {
                        fileArray.push(tempArray.join(underline));
                    }
                }
            });
            if (callback) {
                callback(fileArray);
            }
        });
    }

    function saveform() {
        saveApprovalform("");
    }

</script>