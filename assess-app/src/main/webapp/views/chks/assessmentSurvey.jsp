<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${!empty assessmentPerformanceDto}">
    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
        <div class="card full-height">
            <div class="card-header collapse-link">
                <div class="card-head-row">
                    <div class="card-title">
                        考核数据
                    </div>
                    <div class="card-tools">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <table class="table table-bordered" id="chksTableList">
                                        <thead>
                                        <tr>
                                            <th width="3%">序号</th>
                                            <th width="7%">节点名称</th>
                                            <th width="50%">考核标准</th>
                                            <th width="10%">打分(分值)</th>
                                            <th width="10%">说明</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${assessmentPerformanceDto.examineStatus == 'runing'}">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="text-align: center;">
                        <button class="btn btn-primary" type="button" onclick="saveAssessmentSurveyItem(this);">保存考核</button>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</c:if>
<script type="text/javascript">
    function saveAssessmentSurveyItem(_this) {
        var target = $("#chksTableList").find("tbody");
        if (!vaildChksData(target)) {
            return false;
        }
        var data = [];
        var filterData = [];
        var remarks = target.find("textarea[name=remarks]").val();
        assessmentCommonHandle.getChksSonData(target, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        if (filterData.length == 0) {
            notifyWarning('警告', "考核需要填写全部数据!");
            return false;
        }
        var parentData = {
            id: '${assessmentProjectPerformanceDto.id}',
            remarks: remarks,
            examineStatus: 'finish'
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData)
        }, function (data) {
            AlertSuccess("成功", "考核成功", function () {
                finishAssessmentSurveyItem();
                $(_this).closest('.card').parent().hide();
            });
        });
    };

    function finishAssessmentSurveyItem() {
        var target = $("#chksTableList").find("tbody");
        assessmentCommonHandle.getPerformanceById('${assessmentProjectPerformanceDto.id}', function (obj) {
            assessmentCommonHandle.getPerformanceDetailsByPerformanceId(obj.id, function (data) {
                assessmentCommonHandle.writeAssessmentItemHtml(target, obj, data, data);
                target.find("input").attr({readonly: 'readonly'});
                target.find("textarea").attr({readonly: 'readonly'});
            });
        });
    }

    $(document).ready(function () {
        var target = $("#chksTableList").find("tbody");
        if ('${assessmentProjectPerformanceDto.examineStatus}' == 'runing') {
            assessmentCommonHandle.getPerformanceDetailsByPerformanceId('${assessmentProjectPerformanceDto.id}', function (parentData) {
                assessmentCommonHandle.getAssessmentItemTemplate({
                    boxReActivitiId: '${assessmentProjectPerformanceDto.activityId}',
                    boxId: '${assessmentProjectPerformanceDto.boxId}',
                    assessmentKey: '${assessmentProjectPerformanceDto.assessmentKey}'
                }, function (data) {
                    assessmentCommonHandle.writeAssessmentItemHtml(target, {
                        activityName: '${assessmentProjectPerformanceDto.activityName}',
                        id: '${assessmentProjectPerformanceDto.id}',
                        remarks: '${assessmentProjectPerformanceDto.remarks}'
                    }, parentData, data);
                });
            });
        }
        if ('${assessmentProjectPerformanceDto.examineStatus}' == 'finish') {
            finishAssessmentSurveyItem();
        }
    });


</script>
