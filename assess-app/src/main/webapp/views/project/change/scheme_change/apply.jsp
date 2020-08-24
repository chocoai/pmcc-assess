<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>方案信息修改</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%--项目基本信息--%>
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>

                    <c:forEach items="${areaGroups}" var="item">
                        <div class="col-md-12 area_panel">
                            <input type="hidden" name="areaGroupId" value="${item.id}">
                            <input type="hidden" name="areaName" value="${item.areaName}">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                                ${item.areaName}
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form id="frmJudgeObject${item.id}" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        评估基准日<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <input type="text" name="valueTimePoint" required="required"
                                                               placeholder="评估基准日"
                                                               data-date-format="yyyy-mm-dd"
                                                               class="form-control input-full date-picker dbdate"
                                                               readonly="readonly" pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}"
                                   pattern="yyyy-MM-dd"/>">

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        基准日说明<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <input type="text" name="timePointExplain" required="required"
                                                               placeholder="基准日说明" class="form-control input-full"
                                                               value="${empty item.timePointExplain?valueDateExplain:item.timePointExplain}">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        委托目的<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <select name="entrustPurpose" class="form-control input-full" required>
                                                            <option value="">-请选择-</option>
                                                            <c:forEach items="${entrustmentPurposes}" var="entrustmentPurpose">
                                                                <c:choose>
                                                                    <c:when test="${entrustmentPurpose.id eq item.entrustPurpose}">
                                                                        <option value="${entrustmentPurpose.id}"
                                                                                selected="selected">${entrustmentPurpose.name}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:if test="${entrustmentPurpose.id eq projectInfo.entrustPurpose}">
                                                                            <option value="${entrustmentPurpose.id}"
                                                                                    selected="selected">${entrustmentPurpose.name}</option>
                                                                        </c:if>
                                                                        <c:if test="${entrustmentPurpose.id ne projectInfo.entrustPurpose}">
                                                                            <option value="${entrustmentPurpose.id}">${entrustmentPurpose.name}</option>
                                                                        </c:if>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        委托目的描述<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <input type="text" name="remarkEntrustPurpose" required="required"
                                                               placeholder="委托目的描述" class="form-control input-full"
                                                               value="${empty item.remarkEntrustPurpose?projectInfo.remarkEntrustPurpose:item.remarkEntrustPurpose}">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        价值类型<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <select name="valueDefinition" class="form-control input-full" required>
                                                            <option value="">-请选择-</option>
                                                            <c:forEach items="${valueTypes}" var="valueDefinition">
                                                                <c:choose>
                                                                    <c:when test="${valueDefinition.id eq item.valueDefinition}">
                                                                        <option value="${valueDefinition.id}"
                                                                                selected="selected">${valueDefinition.name}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:if test="${valueDefinition.id eq projectInfo.valueType}">
                                                                            <option value="${valueDefinition.id}"
                                                                                    selected="selected">${valueDefinition.name}</option>
                                                                        </c:if>
                                                                        <c:if test="${valueDefinition.id ne projectInfo.valueType}">
                                                                            <option value="${valueDefinition.id}">${valueDefinition.name}</option>
                                                                        </c:if>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产范围<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <select class="form-control input-full" name="propertyScope" required></select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产包括<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control input-full" name="scopeInclude"
                                                               placeholder="财产包括" value="${item.scopeInclude}" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-2 control-label">
                                                        财产不包括<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control input-full" name="scopeNotInclude"
                                                               placeholder="财产不包括" value="${item.scopeNotInclude}" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <script type="text/javascript">
                                        $(function () {
                                            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, '${item.propertyScope}', function (html, data) {
                                                $("#frmJudgeObject${item.id}").find("[name=propertyScope]").empty().html(html);
                                            });
                                        })
                                    </script>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        变更原因
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    方案信息变更原因
                                                </label>
                                                <div class="col-sm-11">
                                          <textarea class="form-control input-full" id="changeReason" name="changeReason"
                                                    rows="4" required data-rule-maxlength="255"
                                                    placeholder="方案信息变更原因">${costsProjectChangeLog.changeReason}</textarea>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:if test="${not empty processInsId and processInsId ne '0'}">
                                <button type="button" class="btn btn-warning" style="margin-left: 10px;" onclick="masterObj.closeProcess()">
                                    撤销
                                </button>
                            </c:if>
                            <button id="commit_btn" class="btn btn-primary" style="margin-left: 10px;" onclick="masterObj.submit();">
                                提交
                            </button>
                        </div>
                    </div>


                    <c:if test="${processInsId ne '0'}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="frm_approval">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
</body>
</html>

<script type="text/javascript">
    var masterObj = {};
    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.submit = function () {
        //前端验证
        var isPass = true;
        $("form[id^=frmJudgeObject]").each(function () {
            var that = $(this);
            var options = {
                msg: "请检查【" + that.closest('.area_panel').find('h2').find('label').text() + "】填写的信息",
                hiddenValid: true
            };
            if (!$(this).valid(options)) {
                isPass = false;
                return false;
            }
        })
        if (!isPass) return false;

        var data = {}
        var newData = [];
        $(".area_panel").each(function () {
            newData.push(masterObj.getProgrammeAreaData($(this)));
        })
        data.newRecord = JSON.stringify(newData);
        data.changeReason = $("#changeReason").val();
        data.projectId = "${projectInfo.id}";
        data.id = "${costsProjectChangeLog.id}";

        if ("${processInsId}" == "0") {
            data.oldRecord = JSON.stringify(${oldData});
            //申请
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: '${pageContext.request.contextPath}/project.scheme.change/applyCommit',
                data: data,
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功",function(){
                            window.close();
                        });
                    } else {
                        AlertError("提交失败,失败原因:"result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        } else {
            var approvalData = formParams("frm_approval");
            data = $.extend({}, approvalData, data);
            //修改提交
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/project.information.change/editCommit",
                type: "post",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };

    //获取区域下的方案数据
    masterObj.getProgrammeAreaData = function (areaPanel) {
        var data = {}; //找出需要保存的数据
        data.id = $(areaPanel).find('[name="areaGroupId"]').val();
        data.valueTimePoint = $(areaPanel).find('[name="valueTimePoint"]').val();
        data.timePointExplain = $(areaPanel).find('[name="timePointExplain"]').val();
        data.entrustPurpose = $(areaPanel).find('[name="entrustPurpose"]').val();
        data.remarkEntrustPurpose = $(areaPanel).find('[name="remarkEntrustPurpose"]').val();
        data.valueDefinition = $(areaPanel).find('[name="valueDefinition"]').val();
        data.propertyScope = $(areaPanel).find('[name="propertyScope"]').val();
        data.scopeInclude = $(areaPanel).find('[name="scopeInclude"]').val();
        data.scopeNotInclude = $(areaPanel).find('[name="scopeNotInclude"]').val();
        data.areaName = $(areaPanel).find('[name="areaName"]').val();

        return data;
    };

    //撤销
    masterObj.closeProcess = function () {
        AlertConfirm("是否确认撤销", "流程撤销后将不可恢复", function () {
            var approvalModelDto = formSerializeArray($("#process_variable_form"));
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/public/closeProcess",
                type: "post",
                data: approvalModelDto,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "流程撤销成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        })
    };
</script>

