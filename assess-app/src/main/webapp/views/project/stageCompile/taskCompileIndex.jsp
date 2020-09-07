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
                    <!-- 填写表单 start -->
                    <jsp:include page="/views/project/stageCompile/module/compileInfoModule.jsp"></jsp:include>
                    <c:if test="${empty projectPlanDetails.areaId}">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-body ">
                                    <div class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline">
                                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                        区域
                                                    </label>
                                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                                        <select name="areaGroup" class="form-control input-full">
                                                            <option value="">-请选择-</option>
                                                            <c:forEach items="${areaGroupList}" var="item">
                                                                <option value="${item.id}">${item.areaName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <button type="button" onclick="bindAreaGroup(this);"
                                                            class="btn btn-sm btn-primary">绑定区域
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty projectPlanDetails.areaId}">
                        <%@include file="/views/share/form_apply.jsp" %>
                    </c:if>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<input type="hidden" id="compileReportDetailsJSON" value='${compileReportDetailsJSON}'>

<script type="text/javascript">
    $(function () {
        //初始化
        compileInfoModule.init({
            compileInfo: JSON.parse($("#compileReportDetailsJSON").val())
        });
    })
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if (!compileInfoModule.valid()) {
            return false;
        }
        var data = {};
        data.compileReportDetailList = compileInfoModule.getData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        } else {
            submitToServer(JSON.stringify(data));
        }
    }

    function bindAreaGroup(_this) {
        var planDetailsId = '${projectPlanDetails.id}';
        var areaGroup = $(_this).closest('.form-group').find('[name=areaGroup]');
        var areaGroupId = areaGroup.val();
        var areaName = areaGroup.find("option:selected").text();
        if (!areaGroupId) {
            notifyWarning('提示', '请选择区域');
            return false;
        }
        $.post('${pageContext.request.contextPath}/projectPlanDetails/bindAreaGroup', {
            planDetailsId: planDetailsId,
            areaGroupId: areaGroupId,
            areaName: areaName
        }, function (result) {
            if (result.ret) {
                AlertSuccess('成功', '绑定区域成功', function () {
                    window.location.href = window.location.href;
                })
            }
        }, 'json')
    }
</script>

</html>

