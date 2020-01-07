<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="divChksRecordModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核记录</h3>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" name="activityId">
                    <input type="hidden" name="byExaminePeople">
                </form>
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table-striped table" id="tableChkSpotAssessment"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="saveChkSpotAssessment(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="assessmentItemTemplateHTML">
    <tr>
        <td>{index}
            <input data-name="id" type="hidden" name="id{id}" value="{id}">
            <input data-name="performanceId" type="hidden" name="performanceId{performanceId}" value="{performanceId}">
            <input data-name="contentId" type="hidden" name="contentId{contentId}" value="{contentId}">
        </td>
        <td>{name}</td>
        <td>{assessmentDes} 范围:{minScore}~{maxScore}</td>
        <td>
            <input type="text" required="required" data-rule-number='true'
                   data-name="actualScore"
                   data-minScore="{minScore}"
                   data-maxScore="{maxScore}"
                   class="form-control" name="actualScore{id}"
                   placeholder="(请输入数字)" value="{actualScore}"
                   onblur="assessmentCommonHandle.chksVerification(this);">
        </td>
        <td><input type="text" data-name="remark" class="form-control"
                   name="remark{id}" value="{remark}"
                   placeholder="说明"></td>
    </tr>
</script>

<script type="text/html" id="assessmentItemTemplateRemarksHTML">
    <tr>
        <td colspan="5"><textarea class="form-control" name="remarks" placeholder="考核综合说明">{remarks}</textarea></td>
    </tr>
</script>

<script type="text/javascript">


    var assessmentCommonHandle = {};


    /**
     * 范围校验
     * @param _this
     * @returns {boolean}
     */
    assessmentCommonHandle.chksVerification = function (_this) {
        var target = $(_this);
        var value = target.val();
        if (!value) {
            return false;
        }
        if (!$.isNumeric(value)) {
            target.val('');
            toastr.warning("请输入数字!");
            return false;
        }
        var minScore = target.attr("data-minScore");
        var maxScore = target.attr("data-maxScore");
        value = Number(value);
        minScore = Number(minScore);
        maxScore = Number(maxScore);
        if (value > maxScore || value < minScore) {
            toastr.warning("请在考核范围内打分");
            target.val('');
        }
    };

    assessmentCommonHandle.conversionProjectPerformanceDtoMap = function (query, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/conversionProjectPerformanceDtoMap",
            type: "get",
            dataType: "json",
            data: query,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceDtoList = function (query, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getAssessmentProjectPerformanceDtoList",
            type: "get",
            dataType: "json",
            data: query,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    assessmentCommonHandle.deleteResponsibilityById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/deleteResponsibilityById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    toastr.warning("任务删除成功!");
                    window.close();
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    assessmentCommonHandle.getChksSonData = function (target, data) {
        target.find("tr").each(function (i, tr) {
            var ele = $(tr);
            var obj = {};
            obj.contentId = ele.find("[data-name='contentId']").val();
            var id = ele.find("[data-name='id']").val();
            var performanceId = ele.find("[data-name='performanceId']").val();
            if (id) {
                obj.id = id;
            }
            if (performanceId) {
                obj.performanceId = performanceId;
            }
            obj.actualScore = ele.find("[data-name='actualScore']").val();
            obj.remark = ele.find("[data-name='remark']").val();
            if (obj.actualScore) {
                data.push(obj);
            }
        });
    };

    assessmentCommonHandle.replaceAssessmentItem = function (html, item) {
        html = html.replace(/{index}/g, item.index);
        html = html.replace(/{id}/g, item.id);
        html = html.replace(/{performanceId}/g, item.performanceId);
        html = html.replace(/{contentId}/g, item.contentId);
        html = html.replace(/{name}/g, item.name);
        html = html.replace(/{assessmentDes}/g, item.assessmentDes);
        html = html.replace(/{minScore}/g, item.minScore);
        html = html.replace(/{maxScore}/g, item.maxScore);
        html = html.replace(/{actualScore}/g, item.actualScore);
        html = html.replace(/{remark}/g, item.remark);
        return html;
    };

    assessmentCommonHandle.getAssessmentItemTemplate = function (query, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getAssessmentItemTemplate",
            type: "get",
            dataType: "json",
            data: query,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    assessmentCommonHandle.deleteSpotAssessment = function (id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/deleteAssessmentProjectPerformanceByIds",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    assessmentCommonHandle.getChksBootstrapTableVoBase = function (table, query, data) {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({field: 'processInsId', title: '流程实例'});
        cols.push({field: 'remarks', title: '综合说明'});
        cols.push({field: 'businessKey', title: '业务名称'});
        cols.push({field: 'activityName', title: '考核节点'});
        cols.push({field: 'examinePeopleName', title: '考核人'});
        cols.push({field: 'byExaminePeopleName', title: '被考核人'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({
            field: 'id', title: '考核详细', formatter: function (value, row, index) {
                var html = "<div class='list-group'>";
                $.each(row.detailList, function (i, item) {
                    html += "<p class='list-group-item-text'>";
                    html += "考核项" + item.content;
                    html += "实际得分" + item.actualScore;
                    html += "说明" + item.remark;
                    html += "</p>";
                });
                html += "</div>";
                return html;
            }
        });
        cols.push({field: 'examineScore', title: '考核分值'});
        cols.push({field: 'validScore', title: '实际分值'});
        if (data) {
            $.each(data, function (i, item) {
                cols.push(item);
            });
        }
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getChksBootstrapTableVo", cols, query, method);
    };

    assessmentCommonHandle.loadChksServerBase = function (option, query, target) {
        assessmentCommonHandle.getAssessmentProjectPerformanceDtoList(option, function (data) {
            if (data.length == 0) {
                assessmentCommonHandle.getAssessmentItemTemplate(query, function (data) {
                    var restHtml = "";
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
                            maxScore: item.maxScore
                        });
                        restHtml += html;
                    });
                    if (data.length >= 1){
                        var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                        remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                        restHtml += remarksHtml;
                    }
                    target.empty().append(restHtml);
                });
            } else {
                var restHtml = "";
                $.each(data, function (i, obj) {
                    if (obj.detailList) {
                        $.each(obj.detailList, function (i, item) {
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
                                remark: item.remark
                            });
                            restHtml += htmlB;
                        });
                    }
                    var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                    if (obj.remarks) {
                        remarksHtml = remarksHtml.replace(/{remarks}/g, obj.remarks);
                    } else {
                        remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                    }
                    restHtml += remarksHtml;
                });
                target.empty().append(restHtml);
            }
        });
    };

    assessmentCommonHandle.loadChksServerViewTable = function () {
        var target = $("#boxReActivityDtoTableView");
        var options = {
            boxId: '${boxReDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            planId: '${projectPlanDetails.planId}',
            planDetailsId: '${projectPlanDetails.id}',
            spotActivityId: 0,
            isEffective:true
        };
        if ('${processInsId}') {
            options.processInsId = '${processInsId}';
        }
        if (!options.processInsId) {
            if ('${projectPlanDetails.processInsId}') {
                options.processInsId = '${projectPlanDetails.processInsId}';
            }
        }
        if ('${activityDtoList}') {
            var activityIds = [];
            var data = null;
            try {
                data = JSON.parse('${el:toJsonString(activityDtoList)}');
            } catch (e) {
                console.log(e);
            }
            if (data) {
                $.each(data, function (i, item) {
                    activityIds.push(item.id);
                });
            }
            if (activityIds.length != 0) {
                options.activityIdList = activityIds.join(",");
            }
        }
        var headHtml = "<caption>参考考核数据(包含历史数据)</caption>";
        headHtml += "<thead>";
        headHtml += "<tr><td width='5%' align='center' >被考核人</td> <td width='5%' align='center'>考核人</td> <td width='60%' align='center'>考核详情</td> <td width='5%' align='center'>得分</td> <td width='5%' align='center'>考核时间</td> <td width='20%' align='center'>综合说明</td></tr>";
        headHtml += "</thead>";
        var bodyHtml = "<tbody>";
        assessmentCommonHandle.getAssessmentProjectPerformanceDtoList(options, function (arrData) {
            for (var i = 0; i < arrData.length; i++) {
                var data = arrData[i];
                var tr = "<tr>";
                tr += "<td width='5%' align='center'>" + data.byExaminePeopleName + "</td>";
                tr += "<td width='5%' align='center'>" + data.examinePeopleName + "</td>";
                tr += "<td width='60%' align='center'>";
                if (data.detailList.length > 1) {
                    var childHtml = "";
                    childHtml += "<table class='table table-striped' >";
                    $.each(data.detailList, function (j, item) {
                        childHtml += "<tr>";
                        childHtml += "<td width='50%' align='center'> 考核标准: " + item.content + "</td>";
                        childHtml += "<td width='10%' align='center'> 实际得分: " + item.actualScore + "</td>";
                        childHtml += "<td width='40%' align='center'> 考核说明: " + item.remark + "</td>";
                        childHtml += "</tr>";
                    });
                    childHtml += "</table>";
                    tr += childHtml;
                }
                tr += "</td>";
                tr += "<td width='5%' align='center'>" + data.examineScore + "</td>";
                tr += "<td width='5%' align='center'>" + formatDate(data.examineDate, true) + "</td>";
                tr += "<td width='20%' align='center'>" + data.remarks + "</td>";
                tr += "</tr>";
                bodyHtml += tr;
            }
            bodyHtml += "</tbody>";
            target.empty().append(headHtml + bodyHtml);
        });
    };

    assessmentCommonHandle.saveAssessmentServer = function (data, callback) {
        jQuery.extend(data, {planDetailsId: '${projectPlanDetails.id}'});
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/saveAssessmentServer",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    function saveAssessmentItem() {
        var target = $("#chksTableList").find("tbody");
        var frm = target.closest("form");
        var remarks = frm.find("textarea[name=remarks]").val();
        var data = [];
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var parentData = {
            spotActivityId: '0',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}',
            activityId: '${boxReActivityDto.id}',
            activityName: '${boxReActivityDto.cnName}',
            remarks: remarks
        };
        if ($("#tb_log").size() != 0) {
            var appData = $("#tb_log").bootstrapTable("getData");
            if (appData.length != 0) {
                $.each(appData, function (i, item) {
                    //确定申请人
                    if (item.bisApply) {
                        parentData.byExaminePeople = item.creator;
                    }
                });
            }
        }
        assessmentCommonHandle.getChksSonData(target, data);
        if (data.length == 0){
            toastr.warning("请确定考核数据填写情况!或者咨询管理员配置考核数据模板!");
            return false;
        }
        if (!frm.valid()) {
            return false;
        }
        assessmentCommonHandle.saveAssessmentServer({chksScore: JSON.stringify(data), fomData: JSON.stringify(parentData)}, function (data) {
            toastr.warning("考核成功!");
            assessmentCommonHandle.loadChksServerViewTable();
        });
    }


    function saveChkSpotAssessment() {
        var box = $("#divChksRecordModal");
        var form = box.find("form");
        var target = $("#tableChkSpotAssessment");
        var remarks = target.find("textarea[name=remarks]").val();
        var data = [];
        assessmentCommonHandle.getChksSonData(target, data);
        if (data.length == 0){
            toastr.warning("请确定考核数据填写情况!或者咨询管理员配置考核数据模板!");
            return false;
        }
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var parentData = {
            spotActivityId: '${spotReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}',
            remarks: remarks,
            activityId: form.find("input[name='activityId']").val(),
            byExaminePeople: form.find("input[name='byExaminePeople']").val()
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(data),
            fomData: JSON.stringify(parentData)
        }, function () {
            toastr.success("考核成功!");
            assessmentCommonHandle.loadChksServerViewTable();
            assessmentCommonHandle.loadChksServerBase(parentData, {
                boxId: parentData.boxId,
                boxReActivitiId: parentData.spotActivityId
            }, target);
        });


//        box.modal("hide");
    }

</script>
