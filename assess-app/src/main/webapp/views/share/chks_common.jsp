<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divChksRecordModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">考核记录填写</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="marsterId">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <div class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                <table class="table-bordered" id="tableChkSpotAssessment">
                                                    <thead>
                                                    <tr>
                                                        <th width="3%">序号</th>
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
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessmentCommonHandle.saveChkSpotAssessment();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divAssessmentProjectPerformanceBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">考核记录填写</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="id">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <div class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                <table class="table-bordered"
                                                       id="tableAssessmentProjectPerformanceBox">
                                                    <thead>
                                                    <tr>
                                                        <th width="3%">序号</th>
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
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessmentCommonHandle.saveAssessmentProjectPerformanceBoxData();">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divAssessmentProjectPerformanceBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">考核详情记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <div class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                <table class="table table-bordered"
                                                       id="tableAssessmentProjectPerformanceBoxDetail">
                                                    <thead>
                                                    <tr>
                                                        <th width="3%">序号</th>
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
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divAssessmentWorkHoursSpotListModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">工时抽查记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="id">
                <input type="hidden" name="boxId">
                <input type="hidden" name="boxReActivitiId">
                <button type="button" data-toggle="modal" class="btn btn-success btn-sm"
                        onclick="assessmentCommonHandle.workingHoursModal(
                        $(this).closest('.modal-body').find('[name=id]').val(),
                        $(this).closest('.modal-body').find('[name=boxId]').val(),
                        $(this).closest('.modal-body').find('[name=boxReActivitiId]').val(),'spot');">新增
                </button>
                <table id="tbAssessmentWorkHoursSpotList"></table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divAssessmentWorkHoursHistoryListModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">历史记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table id="tbAssessmentWorkHoursHistoryList"></table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divAssessmentWorkHoursModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">工时考核</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="assessmentWorkHoursForm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="spotId">
                    <input type="hidden" name="adjustId">
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                    考核标准
                                </label>
                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                    <span data-name="examineStandard" class="form-control input-full"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                    工时得分<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                    <input type="text" class="form-control input-full" name="examineScore"
                                           data-rule-number="true" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                    得分说明
                                </label>
                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                    <textarea class="form-control input-full" name="remarks"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessmentCommonHandle.saveWorkingHours()">
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
        <td>{assessmentDes} 范围:{minScore}~{maxScore} 标准值:{standardScore}</td>
        <td>
            <input type="text" required="required" data-rule-number='true'
                   data-name="actualScore"
                   data-minScore="{minScore}"
                   data-maxScore="{maxScore}"
                   class="form-control input-full" name="actualScore{id}"
                   placeholder="" value="{actualScore}"
                   onblur="assessmentCommonHandle.chksVerification(this);">
        </td>
        <td><input type="text" data-name="remark" class="form-control input-full"
                   name="remark{id}" value="{remark}"
                   placeholder=""></td>
    </tr>
</script>

<script type="text/html" id="assessmentItemTemplateRemarksHTML">
    <tr>
        <td colspan="5"><textarea class="form-control input-full" name="remarks"
                                  placeholder="考核综合说明">{remarks}</textarea></td>
    </tr>
</script>


<script type="text/javascript">
    var assessmentCommonHandle = {};
    assessmentCommonHandle.run = function (data, url, type, callback, funParams, errorCallback) {
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };
    assessmentCommonHandle.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                assessmentCommonHandle.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            assessmentCommonHandle.run(data, url, type, callback, funParams, errorCallback);
        }
    };
    assessmentCommonHandle.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        assessmentCommonHandle.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };
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
            notifyInfo('提示', "请输入数字");
            return false;
        }
        var minScore = target.attr("data-minScore");
        var maxScore = target.attr("data-maxScore");
        value = Number(value);
        minScore = Number(minScore);
        maxScore = Number(maxScore);
        if (value > maxScore || value < minScore) {
            notifyInfo('提示', "请在考核范围内打分");
            target.val('');
        }
    };

    assessmentCommonHandle.saveAssessmentServer = function (data, callback) {
        jQuery.extend(data, {planDetailsId: '${projectPlanDetails.id}'});
        data.processInsId = '${processInsId}';
        assessmentCommonHandle.ajaxServerMethod(data, "/assessmentPerformance/saveAssessmentServer", "post", callback, null);
    };

    assessmentCommonHandle.updateAssessmentProjectPerformance = function (data, callback) {
        assessmentCommonHandle.ajaxServerMethod({fomData: JSON.stringify(data)}, "/assessmentPerformance/updateAssessmentProjectPerformance", "post", callback, null);
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({performanceId: id}, "/assessmentPerformance/getAssessmentProjectPerformanceDetailByPerformanceIdList", "get", callback, null);
    };

    assessmentCommonHandle.deleteResponsibilityById = function (id) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/assessmentPerformance/deleteResponsibilityById", "post", function () {
            notifySuccess("成功", "任务删除成功");
            window.close();

        }, null);
    };

    assessmentCommonHandle.selectUserAccountMember = function (this_) {
        erpEmployee.select({
            currOrgId: '${baseViewDto.thisUser.companyId}',
            onSelected: function (data) {
                var target = $(this_).closest('.input-group');
                if (target.find("input[name='byExaminePeople']").size() != 0) {
                    target.find("input[name='byExaminePeople']").val(data.account);
                    target.find("input[name='byExaminePeopleName']").val(data.name);
                }
                if (target.find("input[name='examinePeople']").size() != 0) {
                    target.find("input[name='examinePeople']").val(data.account);
                    target.find("input[name='examinePeopleName']").val(data.name);
                }
            }
        });
    };

    /*
     考核收集数据
     */
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
            var eleActualScore = ele.find("[data-name='actualScore']");
            obj.actualScore = eleActualScore.val();
            obj.remark = ele.find("[data-name='remark']").val();
            if (eleActualScore.size() != 0) {
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
        html = html.replace(/{standardScore}/g, item.standardScore);
        html = html.replace(/{actualScore}/g, item.actualScore);
        html = html.replace(/{remark}/g, item.remark);
        return html;
    };

    assessmentCommonHandle.writeAssessmentItemHtml = function (target, parentData, childData, templateData) {
        var restHtml = "";
        if (childData.length == templateData.length) {
            $.each(childData, function (i, item) {
                if (!item.assessmentDes) {
                    item.assessmentDes = "";
                }
                restHtml += assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                    index: i + 1,
                    contentId: item.contentId,
                    id: item.id,
                    actualScore: item.actualScore,
                    remark: item.remark,
                    performanceId: parentData.id,
                    name: parentData.activityName,
                    assessmentDes: item.assessmentDes,
                    minScore: item.minScore,
                    maxScore: item.maxScore,
                    standardScore: item.standardScore
                });
            });
            if (childData.length >= 1) {
                var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                remarksHtml = remarksHtml.replace(/{remarks}/g, parentData.remarks);
                restHtml += remarksHtml;
            }
        }
        if (childData.length != templateData.length) {
            $.each(templateData, function (i, item) {
                restHtml += assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
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
            });
            if (templateData.length >= 1) {
                var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                restHtml += remarksHtml;
            }
        }
        target.empty().append(restHtml);
    };

    assessmentCommonHandle.getActivityIdByUserAccountList = function (activityId, callback) {
        assessmentCommonHandle.ajaxServerMethod({activityId: activityId}, "/assessmentPerformance/getActivityIdByUserAccountList", "get", callback, null);
    };

    assessmentCommonHandle.getAssessmentItemTemplate = function (query, callback) {
        assessmentCommonHandle.ajaxServerMethod(query, "/assessmentPerformance/getAssessmentItemTemplate", "get", callback, null);
    };

    assessmentCommonHandle.deleteSpotAssessment = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/assessmentPerformance/deleteAssessmentProjectPerformanceByIds", "post", callback, null);
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceById = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/assessmentPerformance/getAssessmentProjectPerformanceById", "get", callback, null);
    };

    //拷贝
    assessmentCommonHandle.copyData = function (_this) {
        var table = $("#assessmentQualityTableList");
        var form = $(_this).closest("form");
        var rows = table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo("提示", "请选择要拷贝的数据!");
        } else if (rows.length == 1) {
            if (rows[0].examineStatus != 'finish') {
                notifyWarning("警告", "此条考核数据没有填写!");
                table.bootstrapTable('uncheckAll');
                return false;
            }
            AlertConfirm("是否确认要拷贝", "", function () {
                form.find("input[name='name']").val(rows[0].businessKey);
                form.find("input[name='id']").val(rows[0].id);
                notifySuccess("成功", "拷贝数据成功!");
                table.bootstrapTable('uncheckAll');
            });
        } else {
            notifyWarning("警告", "只能选择一行数据进行拷贝!");
        }
    };

    //粘贴
    assessmentCommonHandle.pasteAll = function (_this) {
        var form = $(_this).closest("form");
        var data = formSerializeArray(form);
        var table = $("#assessmentQualityTableList");
        var copyId = data.id;
        if (!copyId) {
            notifyWarning("警告", "请先拷贝一个数据作为被粘贴的模板!");
            return false;
        }
        var rows = table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("警告", "请选择要粘贴的数据!");
        } else if (rows.length >= 1) {
            var idArray = [];
            rows.forEach(function (ele, index) {
                idArray.push(ele.id);
            });
            //过滤一次
            var filtered = idArray.filter(function (element, index, array) {
                return element == copyId;
            });
            //判断
            if (filtered.length == 1) {
                notifyWarning("警告", "需要粘贴的从数据包含了自身,这样情况是不被允许的!");
                table.bootstrapTable('uncheckAll');
                return false;
            }
            AlertConfirm("确认要粘贴", "", function () {
                assessmentCommonHandle.ajaxServerFun({
                    copyId: copyId,
                    ids: idArray.join(",")
                }, "/assessmentPerformance/pasteAll", "post", function (message) {
                    AlertSuccess("粘贴情况 ", message);
                    table.bootstrapTable('uncheckAll');
                    form.clearAll();
                    table.bootstrapTable('refresh');
                });
            });
        }
    };

    /*
     考核列表
     */
    assessmentCommonHandle.loadAssessmentPerformanceList = function (form) {
        var table = $("#assessmentPerformanceTableList");
        var cols = [];
        cols.push({
            field: 'ckeckbox',
            checkbox: true
        });
        cols.push({field: 'businessKey', title: '名称'});
        cols.push({
            field: 'examineStatus', title: '状态', formatter: function (value, row, index) {
                if (value == 'runing') {
                    return "<span class=\"label label-info\">进行中</span> ";
                }
                if (value == 'finish') {
                    return "<span class=\"label label-success\">已完成</span>";
                }
            }
        });
        cols.push({
            field: 'byExaminePeopleName', title: '被考核人/考核人', formatter: function (value, row, index) {
                var str = value;
                if (row.examinePeopleName) {
                    str += "/" + row.examinePeopleName;
                }
                return str;
            }
        });
        cols.push({field: 'examineScore', title: '考核得分'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });

        //下面涉及到权限,如果要优化请慎重操作
        cols.push({
            field: 'examineStatus', title: '操作', formatter: function (value, row, index) {
                var str = "";
                var btnClass = 'btn-success';
                //完成之后查看
                if (value == 'finish') {
                    if (row.examineUrl) { //进入一个地址查看考核内容
                        str = "<button type='button' onclick='assessmentCommonHandle.taskOpenWin(\"" + row.examineUrl + "\")'  style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    } else { //使用弹窗查看考核
                        str = "<button type='button' onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    }
                }
                var detailsFlag = row.examinePeople === '${currUserAccount}' && 'details' === '${flog}';//详情页面权限控制
                var approvalFlag = (row.examinePeople === '${currUserAccount}' || !row.examinePeople) && 'approval' === '${flog}';//审批页面权限控制
                //考核未完成
                if (value == 'runing' && (detailsFlag || approvalFlag)) { //进入一个地址来考核
                    if (row.examineUrl) {
                        str += '<button type="button" class="btn  btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.taskOpenWin2(' + "'" + row.id + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa fa-pen fa-white"></i></button>';
                    } else { //使用弹窗考核
                        str += '<button type="button" class="btn  btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.openAssessmentProjectPerformanceBox(' + row.id + ',\'' + table.selector + '\')" > <i class="fa fa-pen fa-white"></i></button>';
                    }
                }

                //抽查考核
                if (row.canSpot) {
                    if (row.spotId) {
                        str += "<button type='button' onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + row.spotId + ")' style='margin-left: 5px;' data-placement='top' data-original-title='抽查考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-envelope-open fa-white'></i></button>";
                    } else {
                        str += '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="抽查考核填写" onclick="assessmentCommonHandle.showChkSpotAssessmentParent(' + row.id + ',' + row.boxId + ',' + row.spotActivityId + ')" > <i class="fa fa-envelope fa-white"></i></button>';
                    }
                }
                return str;
            }
        });

        var query = {
            processInsId: '${processInsId}',
            activityId: '${activityId}',
            userAccount: '${currUserAccount}',
            reActivityName: '${reActivityName}',
            flog: '${flog}',
            boxId: '${boxId}'
        }
        query = $.extend({}, query, formSerializeArray($(form)));
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/assessmentPerformance/getPerformanceList", cols, query, method);
    };

    /**
     * 抽查考核保存
     */
    assessmentCommonHandle.saveChkSpotAssessment = function () {
        var target = $("#tableChkSpotAssessment").find("tbody");
        var box = $("#divChksRecordModal");
        var table = $("#assessmentQualityTableList");
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
        var marsterId = box.find("input[name=marsterId]").val();
        var itemObj = table.bootstrapTable('getRowByUniqueId', marsterId);
        var parentData = {
            remarks: remarks,
            examineStatus: 'finish',
            activityId: itemObj.spotActivityId,
            boxId: itemObj.boxId,
            activityName: "抽查节点"
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData)
        }, function (spotId) {
            notifySuccess("成功", "考核成功!");
            box.modal("hide");
            assessmentCommonHandle.getAssessmentProjectPerformanceById(marsterId, function (tempData) {
                tempData.spotId = spotId;
                assessmentCommonHandle.updateAssessmentProjectPerformance(tempData, function () {
                    table.bootstrapTable('refresh');
                });
            });
        });
    };

    /**
     * 抽查考核填写 弹窗方式
     * @param id
     * @param selector
     */
    assessmentCommonHandle.showChkSpotAssessmentParent = function (id, boxId, spotActivityId) {
        var box = $("#divChksRecordModal");
        var table = $("#tableChkSpotAssessment").find("tbody");
        assessmentCommonHandle.getActivityIdByUserAccountList(spotActivityId, function (accountList) {
            box.modal("show");
            box.find("input[name=marsterId]").val(id);
            assessmentCommonHandle.getAssessmentItemTemplate({
                boxReActivitiId: spotActivityId,
                boxId: boxId
            }, function (data) {
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
                table.empty().append(restHtml);
            });
        });
    };

    //废弃
    assessmentCommonHandle.taskOpenWin = function (url) {
        url = "${pageContext.request.contextPath}" + url;
        openWin(url, function () {
            $("#assessmentQualityTableList").bootstrapTable('refresh');
        })
    };

    assessmentCommonHandle.taskOpenWin2 = function (id, selector) {
        var target = $(selector);
        var item = target.bootstrapTable('getRowByUniqueId', id);
        assessmentCommonHandle.getActivityIdByUserAccountList(item.activityId, function (accountList) {
            var url = "${pageContext.request.contextPath}" + item.examineUrl;
            if (jQuery.inArray('${currUserAccount}', accountList) != -1) {
                try {
                    openWin(url, function () {
                        target.bootstrapTable('refresh');
                    })
                } catch (e) {
                }
            } else {
                notifyWarning('警告', "当前用户不属于此节点组的考核人员!");
            }
        });
    };

    /**
     * 考核保存
     */
    assessmentCommonHandle.saveAssessmentProjectPerformanceBoxData = function () {
        var target = $("#tableAssessmentProjectPerformanceBox").find("tbody");
        var box = $("#divAssessmentProjectPerformanceBox");
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
            id: box.find("input[name=id]").val(),
            remarks: remarks,
            examineStatus: 'finish'
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData)
        }, function (data) {
            notifySuccess("成功", "考核成功!");
            box.modal("hide");
            $("#assessmentQualityTableList").bootstrapTable('refresh');
        });
    };

    //一键完成
    assessmentCommonHandle.batchSetFinish = function () {
        var table = $("#assessmentQualityTableList");
        var rows = table.bootstrapTable('getSelections');
        if (rows.length <= 0) {
            notifyInfo("提示", "请选择要完成的数据");
            return;
        }
        var ids = '';
        $.each(rows, function (i, item) {
            ids += item.id + ',';
        })
        $.ajax({
            url: '${pageContext.request.contextPath}/assessmentPerformance/batchSetFinish',
            data: {ids: ids.replace(/,$/, '')},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '操作成功');
                    assessmentCommonHandle.loadAssessmentPerformanceList($('#assessmentPerformanceForm'));
                } else {
                    AlertError("失败", result.errmsg);
                }
            }
        })
    }


    /**
     * 考核填写 弹窗方式
     * @param id
     */
    assessmentCommonHandle.openAssessmentProjectPerformanceBox = function (id) {
        var target = $("#assessmentPerformanceTableList");
        var box = $("#divAssessmentProjectPerformanceBox");
        var table = $("#tableAssessmentProjectPerformanceBox").find("tbody");
        var itemA = target.bootstrapTable('getRowByUniqueId', id);
        box.modal("show");
        box.find("input[name=id]").val(id);
        assessmentCommonHandle.getAssessmentItemTemplate({
            boxReActivitiId: itemA.activityId,
            boxId: itemA.boxId
        }, function (data) {
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
            table.empty().append(restHtml);
        });
    };

    /**
     * 考核查看详情 弹窗方式
     * @param id
     */
    assessmentCommonHandle.findAssessmentProjectPerformanceBox = function (id) {
        var box = $("#divAssessmentProjectPerformanceBoxDetail");
        var table = $("#tableAssessmentProjectPerformanceBoxDetail").find("tbody");
        assessmentCommonHandle.getAssessmentProjectPerformanceById(id, function (obj) {
            box.modal("show");
            var restHtml = "";
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
                        standardScore: item.standardScore,
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
            table.empty().append(restHtml);
            table.find("input").attr({readonly: 'readonly'});
            table.find("textarea").attr({readonly: 'readonly'});
        });
    };

    assessmentCommonHandle.loadAssessmentWorkHoursList = function () {
        var cols = [];
        cols.push({
            field: 'activityName', title: '名称', formatter: function (value, row, index) {
                var s = value;
                return s;
            }
        });
        cols.push({
            field: 'examineStatus', title: '状态', formatter: function (value, row, index) {
                if (value == 'runing') {
                    return "<span class=\"label label-info\">进行中</span> ";
                }
                if (value == 'finish') {
                    return "<span class=\"label label-success\">已完成</span>";
                }
            }
        });
        cols.push({
            field: 'byExaminePeopleName', title: '被考核人/考核人', formatter: function (value, row, index) {
                var str = value;
                if (row.examinePeopleName) {
                    str += "/" + row.examinePeopleName;
                }
                if(row.adjustId){
                    str += "<button type='button'  class='btn btn-xs btn-primary'  style='margin-left: 5px;' data-placement='top' title='历史'  onclick='assessmentCommonHandle.workingHoursHistoryListModal(\"" + row.processInsId + "\"," + row.activityId +");'><i class='fa fa-history fa-white'></i></button>";
                }
                return str;
            }
        });
        cols.push({field: 'examineScore', title: '得分'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({field: 'remarks', title: '说明'});
        cols.push({
            field: 'examineStatus', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (row.canFill) {//填写
                    str = "<button type='button'  class='btn btn-xs btn-primary'  style='margin-left: 5px;' data-placement='top' title='填写'  onclick='assessmentCommonHandle.workingHoursModal(" + row.id + "," + row.boxId + "," + row.activityId + ");'><i class='fa fa-pen fa-white'></i></button>";
                }
                if (row.canAdjust) {//调整
                    str += '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" title="调整" onclick="assessmentCommonHandle.workingHoursModal(' + row.id + ',' + row.boxId + ',' + row.spotActivityId + ',\'adjust\')" > <i class="fa fa-pen-nib fa-white"></i></button>';
                }
                if (row.canSpot) {//抽查
                    str += '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" title="抽查" onclick="assessmentCommonHandle.workingHoursSpotListModal(' + row.id + ',' + row.boxId + ',' + row.spotActivityId + ')" > <i class="fa fa-pencil-ruler fa-white"></i></button>';
                }
                return str;
            }
        });
        var query = {
            processInsId: '${processInsId}',
            userAccount: '${currUserAccount}',
            reActivityName: '${reActivityName}',
            flog: '${flog}'
        };
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        $("#assessmentWorkHoursTableList").bootstrapTable('destroy');
        TableInit($("#assessmentWorkHoursTableList"), "${pageContext.request.contextPath}/assessmentPerformance/getWorkingHoursList", cols, query, method);
    }

    //加载工时抽查列表数据
    assessmentCommonHandle.loadAssessmentWorkHoursSpotList = function (id) {
        var cols = [];
        cols.push({
            field: 'byExaminePeopleName', title: '被抽查人/抽查人', formatter: function (value, row, index) {
                var str = value;
                if (row.examinePeopleName) {
                    str += "/" + row.examinePeopleName;
                }
                return str;
            }
        });
        cols.push({field: 'examineScore', title: '得分'});
        cols.push({
            field: 'examineDate', title: '抽查时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({field: 'remarks', title: '说明'});
        var query = {
            id: id
        };
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        $("#tbAssessmentWorkHoursSpotList").bootstrapTable('destroy');
        TableInit($("#tbAssessmentWorkHoursSpotList"), "${pageContext.request.contextPath}/assessmentPerformance/getWorkHoursSpotListById", cols, query, method);
    }

    assessmentCommonHandle.workingHoursSpotListModal = function (id, boxId, boxReActivitiId) {
        assessmentCommonHandle.loadAssessmentWorkHoursSpotList(id);
        $('#divAssessmentWorkHoursSpotListModal').find('[name=id]').val(id);
        $('#divAssessmentWorkHoursSpotListModal').find('[name=boxId]').val(boxId);
        $('#divAssessmentWorkHoursSpotListModal').find('[name=boxReActivitiId]').val(boxReActivitiId);
        $('#divAssessmentWorkHoursSpotListModal').modal();
    }

    //加载工时历史列表数据
    assessmentCommonHandle.loadAssessmentWorkHoursHistoryList = function (processInsId,activityId) {
        var cols = [];
        cols.push({
            field: 'byExaminePeopleName', title: '被考核人/考核人', formatter: function (value, row, index) {
                var str = value;
                if (row.examinePeopleName) {
                    str += "/" + row.examinePeopleName;
                }
                return str;
            }
        });
        cols.push({field: 'examineScore', title: '得分'});
        cols.push({
            field: 'examineDate', title: '抽查时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({field: 'remarks', title: '说明'});
        var query = {
            processInsId: processInsId,
            activityId:activityId
        };
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        $("#tbAssessmentWorkHoursHistoryList").bootstrapTable('destroy');
        TableInit($("#tbAssessmentWorkHoursHistoryList"), "${pageContext.request.contextPath}/assessmentPerformance/getAssessmentProjectWorkHoursHistoryList", cols, query, method);
    }

    assessmentCommonHandle.workingHoursHistoryListModal = function (processInsId, activityId) {
        assessmentCommonHandle.loadAssessmentWorkHoursHistoryList(processInsId,activityId);
        $('#divAssessmentWorkHoursHistoryListModal').modal();
    }

    assessmentCommonHandle.workingHoursModal = function (id, boxId, boxReActivitiId, type) {
        //需先获取考核标准
        assessmentCommonHandle.getAssessmentItemTemplate({
            boxId: boxId,
            boxReActivitiId: boxReActivitiId,
            assessmentKey: 'work.hours'
        }, function (data) {
            $("#assessmentWorkHoursForm").clearAll().find('[data-name="examineStandard"]').text('');
            if (data && data.length > 0) {
                $("#assessmentWorkHoursForm").find('[data-name="examineStandard"]').text(data[0].assessmentDes + " (标准分：" + data[0].standardScore + ")");
            }
            if (type == 'spot') {
                $("#assessmentWorkHoursForm").find('[name=spotId]').val(id);
            } else if (type == 'adjust') {
                $("#assessmentWorkHoursForm").find('[name=adjustId]').val(id);
            }
            $('#divAssessmentWorkHoursModal').modal();
        })
    }

    //保存工时考核
    assessmentCommonHandle.saveWorkingHours = function () {
        if (!$("#assessmentWorkHoursForm").valid()) {
            return false;
        }
        var data = formSerializeArray($('#assessmentWorkHoursForm'));
        data.processInsId = '${processInsId}';
        $.ajax({
            url: '${pageContext.request.contextPath}/assessmentPerformance/saveWorkingHours',
            data: data,
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', "保存成果");
                    $('#divAssessmentWorkHoursModal').modal('hide');
                    if (data.spotId) {
                        assessmentCommonHandle.loadAssessmentWorkHoursSpotList(data.spotId);
                    } else {
                        assessmentCommonHandle.loadAssessmentWorkHoursList();
                    }
                } else {
                    AlertError("失败", "保存异常：" + result.errmsg)
                }
            }
        })
    }


    /**
     * 考核验证
     * @param target
     * @returns {boolean}
     */
    function vaildChksData(target) {
        var table = $("#chksTableList");
        if (target == null || target == undefined || target == '') {
            target = table.find("tbody");
        } else {
            table = target.closest("table");
        }
        if (target.find("tr").size() == 0) {//这种情况是抽查情况或者没有配模板情况
            return true;
        }
        var remarks = table.find("textarea[name=remarks]").val();
        var data = [];
        var filterData = [];
        assessmentCommonHandle.getChksSonData(target, data);
        if (data.length == 0) {
            notifyWarning('警告', "请确定考核数据填写情况!或者咨询管理员配置考核数据模板!");
            return false;
        }
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        if (filterData.length != 0) {
            if (data.length != filterData.length) {
                notifyWarning("错误", "请填写完整!");
                return false;
            }
        }
        return true;
    }
</script>
