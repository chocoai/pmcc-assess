<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/views/share/chksCommon.jsp" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>考核信息</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <c:if test="${!empty boxReActivityDto}">
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <form>
                    <table class="table" id="chksTableList">
                        <thead>
                        <tr>
                            <th width="3%">序号</th>
                            <th width="7%">节点名称</th>
                            <th width="50%">考核标准</th>
                            <th width="10%">打分</th>
                            <th width="10%">说明</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </form>
            </div>


            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                <c:if test="${flog == 'details'}">

                    <c:if test="${boxReActivityDto != null && bisCheck==1}">
                        <button class="btn btn-success" onclick="saveAssessmentItem();">
                            保存考核记录
                        </button>
                    </c:if>


                </c:if>
            </div>

            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                <c:if test="${flog == 'details'}">

                    <c:if test="${projectResponsibilityDto != null }">
                        <button class="btn btn-success"
                                onclick="assessmentCommonHandle.deleteResponsibilityById('${projectResponsibilityDto.id}');">
                            关闭此任务
                        </button>
                    </c:if>
                </c:if>
            </div>
        </c:if>

        <c:if test="${!empty spotAssessmentProjectPerformanceList}">
            <c:forEach items="${spotAssessmentProjectPerformanceList}" var="entryItem"
                       varStatus="userStatus">

                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <h2 class="text-center">${userStatus.index+1} : ${entryItem.key.key}
                            <input type="button" class="btn btn-xs btn-primary" value="抽查"
                                   onclick="showChkSpotAssessmentParent('${entryItem.key.value}','${entryItem.key.explain}');">
                        </h2>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td width='5%' align='center'>被考核人</td>
                            <td width='5%' align='center'>考核人</td>
                            <td width='60%' align='center'>考核详情</td>
                            <td width='5%' align='center'>得分</td>
                            <td width='5%' align='center'>考核时间</td>
                            <td width='20%' align='center'>综合说明</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${entryItem.value}" var="item">
                            <tr>
                                <td width='5%' align='center'>${item.byExaminePeopleName}</td>
                                <td width='5%' align='center'>${item.examinePeopleName}</td>
                                <td width='60%' align='center'>
                                    <table class="table table-striped">
                                        <tbody>
                                        <c:forEach items="${item.detailList}" var="assessmentItem">
                                            <tr>
                                                <td width="50%" align="center">考核标准: ${assessmentItem.content}</td>
                                                <td width="10%" align="center">实际得分: ${assessmentItem.actualScore}</td>
                                                <td width="40%" align="center">考核说明: ${assessmentItem.remark}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </td>
                                <td width='5%' align='center'>${item.examineScore}</td>
                                <td width='5%' align='center'>
                                    <fmt:formatDate value="${item.examineDate}"
                                                    pattern="yyyy-MM-dd"/>
                                </td>
                                <td width='20%' align='center'>${item.remarks}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </c:if>

        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table" id="boxReActivityDtoTableView">
            </table>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {
        if ('${boxReActivityDto}') {
            if ("${bisCheck}" == 1) {
                loadChksServerData();
            }
        }
        assessmentCommonHandle.loadChksServerViewTable();
    });

    function loadChksServerData() {
        var target = $("#chksTableList").find("tbody");
        var option = {
            boxId: '${boxReDto.id}',
            activityId: '${boxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            isEffective: true //数据必须有效
        };
        if ('${processInsId}') {
            option.processInsId = '${processInsId}';
        }
        if (!option.processInsId) {
            if ('${projectPlanDetails.processInsId}') {
                option.processInsId = '${projectPlanDetails.processInsId}';
            }
        }
        assessmentCommonHandle.loadChksServerBase(option, {
            boxId: option.boxId,
            boxReActivitiId: option.activityId
        }, target);
    }


    /*显示抽查数据页面*/
    function showChkSpotAssessmentParent(activityId, byExaminePeople) {
        var box = $("#divChksRecordModal");
        var from = box.find("form");
        from.clearAll();
        var data = {activityId: activityId, byExaminePeople: byExaminePeople};
        from.initForm(data);
        box.modal("show");
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var option = {
            spotActivityId: '${spotReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}',
            activityId: activityId,
            byExaminePeople: byExaminePeople
        };
        var target = $("#tableChkSpotAssessment");
        assessmentCommonHandle.loadChksServerBase(option, {
            boxId: option.boxId,
            boxReActivitiId: option.spotActivityId
        }, target);
    }


    //收集数据 审批的时候
    function getChksData() {
        var table = $("#chksTableList");
        var form = table.closest("form");
        var result = {};
        var target = table.find("tbody");
        var remarks = form.find("textarea[name=remarks]").val();
        var data = [];
        var filterData = [];
        assessmentCommonHandle.getChksSonData(target, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        if (filterData.length == 0) {
            return result;
        }
        result.chksScore = JSON.stringify(filterData);
        result.chksRemarks = remarks;
        if ($("#tb_log").size() != 0) {
            var appData = $("#tb_log").bootstrapTable("getData");
            if (appData.length != 0) {
                $.each(appData, function (i, item) {
                    //确定申请人
                    if (item.bisApply) {
                        result.byExaminePeople = item.creator;
                    }
                });
            }
        }
        return result;
    }

    function vaildChksData() {
        var table = $("#chksTableList");
        var form = table.closest("form");
        var remarks = form.find("textarea[name=remarks]").val();
        var target = table.find("tbody");
        var data = [];
        var filterData = [];
        assessmentCommonHandle.getChksSonData(target, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        //当填写 了说明，却又不填写考核分值 是不允许的
        if (remarks) {
            if (filterData.length == 0) {
                Alert("当填写了考核综合说明,那么就必须对考核子项进行打分，或者不填考核说明。");
                return false;
            }

        }
        return true;
    }


</script>