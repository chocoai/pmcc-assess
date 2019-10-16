<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_content">
        <div class="col-md-5 col-sm-5 col-lg-5 col-xs-5 pull-left ">
            <c:if test="${not empty projectPlan.planDisplayUrl and projectPlan.bisAutoComplete eq false}">
                <div class="btn-group">
                    <div class="btn  btn-dark">计划编制</div>

                    <c:if test="${empty projectPlan.planExecutUrl}">

                        <div class="btn  btn-warning" data-placement="top"
                             data-toggle="tooltip" data-original-title="重启"
                             onclick="projectTaskOpenWin('${projectPlan.planExecutUrl}')">
                            <i class="fa fa-reply"></i>
                        </div>

                        <div class="btn  btn-warning" data-placement="top"
                             data-toggle="tooltip" data-original-title="查看"
                             onclick="window.open('${projectPlan.planDisplayUrl}')"><i
                                class="fa fa-search"></i>
                        </div>

                    </c:if>

                    <c:if test="${not empty projectPlan.planExecutUrl and projectPlan.planCanExecut eq true}">
                        <div class="btn  btn-success" data-placement="top"
                             data-toggle="tooltip"
                             onclick="projectTaskOpenWin('${projectPlan.planExecutUrl}')"
                             data-original-title="处理"><i class="fa fa-arrow-right"></i>
                        </div>
                    </c:if>

                </div>
            </c:if>
        </div>

        <div class="col-md-5 col-sm-5 col-lg-5 col-xs-5 pull-right ">
            <div class="btn-group">
                <a target="_blank" class="btn btn-primary"
                   href="${pageContext.request.contextPath}/projectReportFile/index?projectId=${projectInfo.id}">估价委托书及相关证明</a>
            </div>
            <div class="btn-group">
                <button type="button" onclick="projectDetailsEnterNextStage();"
                        class="btn btn-primary">
                    进入下阶段
                </button>
            </div>
        </div>
    </div>
</div>


<script>
    function projectDetailsEnterNextStage() {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectInfo/enterNextStage',
            data: {
                projectId: '${projectInfo.id}'
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('操作成功');
                    try {
                        projectStagePlan.stageTable.bootstrapTable('refresh');
                    } catch (e) {
                        console.log(e);
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    function projectTaskOpenWin(url) {
        console.log(url);
        layer.closeAll();
        openWin(url, function () {
            try {
                projectStagePlan.stageTable.bootstrapTable('refresh');
            } catch (e) {
                console.log(e);
            }
        })
    }
</script>