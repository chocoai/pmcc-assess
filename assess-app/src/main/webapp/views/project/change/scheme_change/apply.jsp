<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>方案信息修改</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!-- 公共模块end -->

            <c:forEach items="${areaGroups}" var="item">
                <div class="x_panel area_panel">
                    <input type="hidden" name="areaGroupId" value="${item.id}">
                    <input type="hidden" name="areaName" value="${item.areaName}">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>
                                ${item.areaName}
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form id="frmJudgeObject${item.id}" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        评估基准日<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="valueTimePoint" required="required"
                                               placeholder="评估基准日"
                                               data-date-format="yyyy-mm-dd"
                                               class="form-control date-picker dbdate"
                                               readonly="readonly" pattern='yyyy-MM-dd'
                                               value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}"
                                   pattern="yyyy-MM-dd"/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        基准日说明<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="timePointExplain" required="required"
                                               placeholder="基准日说明" class="form-control"
                                               value="${empty item.timePointExplain?valueDateExplain:item.timePointExplain}">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select name="entrustPurpose" class="form-control" required>
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
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的描述<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" name="remarkEntrustPurpose" required="required"
                                               placeholder="委托目的描述" class="form-control"
                                               value="${empty item.remarkEntrustPurpose?projectInfo.remarkEntrustPurpose:item.remarkEntrustPurpose}">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select name="valueDefinition" class="form-control" required>
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
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产范围<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control" name="propertyScope" required></select>

                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产包括<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="scopeInclude"
                                               placeholder="财产包括" value="${item.scopeInclude}" required>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产不包括
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="scopeNotInclude"
                                               placeholder="财产不包括" value="${item.scopeNotInclude}" required>
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
            </c:forEach>

            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa fa-info-circle"></i>
                        <small>变更原因</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                            方案信息变更原因
                                        </label>
                                        <div class="col-md-11 col-sm-11 col-xs-12">
                                            <textarea class="form-control" id="changeReason" name="changeReason"
                                                      rows="4" required data-rule-maxlength="255"
                                                      placeholder="方案信息变更原因">${costsProjectChangeLog.changeReason}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 公共尾部模块引用 -->
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-md-4 col-sm-4 col-xs-12 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消<i style="margin-left: 10px" class="fa fa-times-circle"></i>
                        </button>

                        <button id="commit_btn" class="btn btn-primary" onclick="masterObj.submit();">
                            提交<i style="margin-left: 10px" class="fa fa-check-circle"></i>
                        </button>
                    </div>
                </div>
            </div>

            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
            <!-- 尾部end -->
        </div>
    </div>


    <%@include file="/views/share/main_footer.jsp" %>
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
                        Alert("提交方案成功", 1, null, function () {
                            window.close();
                        });
                    } else {
                        Alert(result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });
                    }
                    else {
                        Alert("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
</script>

