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
                    <h3> ${projectPlanDetails.projectPhaseName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="basicBatchApplyFrm" class="form-horizontal">
                        <input type="hidden" name="id" value="${applyBatch.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    楼盘名称<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <input type="hidden" id="estateId" name="estateId" value="${applyBatch.estateId}">
                                    <input type="text" class="form-control" name="estateName" placeholder="楼盘名称"
                                           required value="${applyBatch.estateName}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4 radio-inline">
                                    <c:forEach var="item" items="${examineFormTypeList}">
                                        <span class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="radio" id="examineFormType_${item.key}" name="type"
                                                   value='${item.key}' ${item.key eq applyBatch.type?'checked="checked"':''} required>
                                            <label for="examineFormType_${item.key}">&nbsp;${item.value}</label>
                                        </span>
                                    </c:forEach>
                                </div>
                            </div>
                            <c:if test="${empty applyBatch}">
                                <a id="saveApplyInfoBtn" class="btn btn-warning" onclick="saveApplyInfo(this);">添加</a>
                            </c:if>
                            <c:if test="${!empty applyBatch}">
                                <script type="text/javascript">
                                    $(function () {
                                        showApplyInfo();
                                    })
                                </script>
                            </c:if>
                        </div>
                    </form>
                    <div id="showTree" style="display: none">
                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4 col-lg-offset-1">
                            <%@include file="/views/basic/basicBatchTool/batchTreeTool.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary"
                                        onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

</html>
<script type="application/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            batchTreeTool.ztreeInit(JSON.parse('${el:toJsonString(applyBatch)}'));
        }
    });

    //任务提交
    function submit(useBox) {
        var count = getStandardCount();
        if (count && count > 0) {
            var formData = {};
            formData.declareId = "${declareRecord.id}";
            formData.projectId = "${projectInfo.id}";
            formData.planDetailsId = "${projectPlanDetails.id}";
            if ("${processInsId}" != "0") {
                submitEditToServer(JSON.stringify(formData));
            }
            else {
                submitToServer(JSON.stringify(formData), useBox);
            }
        } else {
            Alert("申请中至少包含一个标准对象");
        }
    }

    //获取标准对象数量
    function getStandardCount() {
        var count = 0;
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskCIP/getStandardCount",
            data: {
                planDetailsId: '${projectPlanDetails.id}'
            },
            type: "post",
            async: false,
            dataType: "json",
            success: function (result) {
                count = result.data;
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        return count;
    }
</script>
<script type="text/javascript">
    //添加楼栋等信息
    function saveApplyInfo(_this) {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var formData = formParams("basicBatchApplyFrm");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskCIP/saveApplyInfo",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(formData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    $(_this).hide();
                    var data = result.data;
                    $("#basicBatchApplyFrm").find("input[name='id']").val(data.basicApplyBatch.id);
                    $("#estateId").val(data.basicApplyBatch.estateId);
                    showApplyInfo();
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    function showApplyInfo() {
        var basicApplyBatch = formSerializeArray($("#basicBatchApplyFrm"));
        $("#basicBatchApplyFrm").find("input").attr("disabled", "disabled");
        $("#showTree").show();
        batchTreeTool.ztreeInit(basicApplyBatch);
    }
</script>
