<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${!empty assessmentProjectPerformanceDto}">

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
            </div>
        </div>
    </div>

    <c:if test="${assessmentProjectPerformanceDto.examineStatus == 'runing'}">
        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
            <div class="card full-height">
                <div class="card-body">
                    <div class="form-horizontal">
                        <div class="row form-group">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="form-inline x-valid">
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                    </div>
                                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                        <button class="btn btn-primary" type="button"
                                                onclick="saveAssessmentSurveyItem(this);">
                                            保存考核记录
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </c:if>

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
        assessmentCommonHandle.getAssessmentProjectPerformanceById('${assessmentProjectPerformanceDto.id}',function (obj) {
            assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList(obj.id, function (data) {
                var restHtml = "";
                $.each(data, function (i, item) {
                    var htmlB = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                        index: i + 1,
                        contentId: item.contentId,
                        id: item.id,
                        performanceId: obj.id,
                        name: obj.activityName,
                        assessmentDes: item.content,
                        actualScore: item.actualScore,
                        minScore: item.minScore,
                        maxScore: item.maxScore,
                        standardScore: item.standardScore,
                        remark: item.remark
                    });
                    restHtml += htmlB;
                });
                var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                if (obj.remarks) {
                    remarksHtml = remarksHtml.replace(/{remarks}/g, obj.remarks);
                } else {
                    remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                }
                restHtml += remarksHtml;
                target.empty().append(restHtml);
                target.find("input").attr({readonly: 'readonly'});
                target.find("textarea").attr({readonly: 'readonly'});
            });
        }) ;
    }

    $(document).ready(function () {
        var target = $("#chksTableList").find("tbody");

        if ('${assessmentProjectPerformanceDto.examineStatus}' == 'runing') {

            assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList('${assessmentProjectPerformanceDto.id}', function (parentData) {
                assessmentCommonHandle.getAssessmentItemTemplate({
                    boxReActivitiId: '${assessmentProjectPerformanceDto.activityId}',
                    boxId: '${assessmentProjectPerformanceDto.boxId}' ,
                    assessmentKey: '${assessmentProjectPerformanceDto.assessmentKey}'
                }, function (data) {
                    var restHtml = "";
                    if (parentData.length == data.length){
                        $.each(parentData, function (i, item) {
                            if (! item.assessmentDes){
                                item.assessmentDes = "" ;
                            }
                            var html = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                                index: i + 1,
                                contentId: item.contentId,
                                id: item.id,
                                actualScore: item.actualScore,
                                remark: item.remark,
                                performanceId: '${assessmentProjectPerformanceDto.id}',
                                name: '${assessmentProjectPerformanceDto.activityName}',
                                assessmentDes: item.assessmentDes,
                                minScore: item.minScore,
                                maxScore: item.maxScore,
                                standardScore: item.standardScore
                            });
                            restHtml += html;
                        });
                        if (data.length >= 1) {
                            var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                            remarksHtml = remarksHtml.replace(/{remarks}/g, '${assessmentProjectPerformanceDto.remarks}');
                            restHtml += remarksHtml;
                        }
                    }
                    if (parentData.length != data.length){
                        $.each(data, function (i, item) {
                            var html = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                                index: i + 1,
                                contentId: item.id,
                                id: 0,
                                actualScore: '',
                                remark: '',
                                performanceId: 0,
                                name: item.boxReActivitiNameCn,
                                assessmentDes: item.assessmentDes,
                                minScore: item.minScore,
                                maxScore: item.maxScore,
                                standardScore: item.standardScore
                            });
                            restHtml += html;
                        });
                        if (data.length >= 1) {
                            var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                            remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                            restHtml += remarksHtml;
                        }
                    }
                    target.empty().append(restHtml);
                });
            });
        }

        if ('${assessmentProjectPerformanceDto.examineStatus}' == 'finish') {
            finishAssessmentSurveyItem();
        }
    });


</script>
