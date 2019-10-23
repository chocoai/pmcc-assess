<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <c:forEach items="${generationVos}" var="generationVo">
                <div class="x_panel area_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h3>${generationVo.areaGroupName}</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form class="form-horizontal" id="groupForm${generationVo.id}">
                            <input type="hidden" name="id">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告出具日期<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control"><fmt:formatDate
                                                value='${generationVo.investigationsStartDate}'
                                                pattern='yyyy-MM-dd'/></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        作业结束时间<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control"><fmt:formatDate
                                                value='${generationVo.investigationsStartDate}'
                                                pattern='yyyy-MM-dd'/></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            资质类型
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${generationVo.qualificationTypeName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        现场查勘开始日期
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control"><fmt:formatDate
                                                value='${generationVo.investigationsStartDate}'
                                                pattern='yyyy-MM-dd'/></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        现场查勘结束日期
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control"><fmt:formatDate
                                                value='${generationVo.investigationsStartDate}'
                                                pattern='yyyy-MM-dd'/></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">估价师</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${generationVo.realEstateAppraiserName}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">查询码</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${generationVo.queryCode}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">备案号</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${generationVo.recordNo}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        备案日期
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control"><fmt:formatDate
                                                value='${generationVo.recordDate}'
                                                pattern='yyyy-MM-dd'/></label>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                                ${reportType.name}
                                        </label>
                                        <div class="col-sm-3">
                                            <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </form>
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
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
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