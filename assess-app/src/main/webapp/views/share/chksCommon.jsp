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
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
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

<div id="assessmentItemToolbar" class=" col-xs-12  col-sm-12  col-md-12  col-lg-12" style="display: none;">
    <form class="form-horizontal">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        节点名称
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <input type="text" placeholder="节点名称" name="activityName" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        被考核人
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <div class="input-group">
                            <input type="hidden" name="byExaminePeople">
                            <input type="text" class="form-control" readonly="readonly" name="byExaminePeopleName"
                                   onclick="assessmentCommonHandle.selectUserAccountMember(this);">
                            <div class="input-group-prepend">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                </button>
                            </div>
                            <div class="input-group-prepend">
                                <button class="btn btn-primary btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="assessmentCommonHandle.selectUserAccountMember(this);">选择
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        考核人
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <select name="examinePeople" class="form-control input-full search-select select2">
                            <option value="">请选择</option>
                            <c:forEach items="${chksExaminePeopleList}" var="item">
                                <option value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        状态
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <div class="form-check" style="justify-content:left">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="examineStatus" value="finish">
                                <span class="form-check-sign">完成</span>
                            </label>
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="examineStatus" value="runing">
                                <span class="form-check-sign">进行中</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        业务名称
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <input type="text" placeholder="业务名称" name="businessKey" class="form-control input-full">
                    </div>


                    <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                        <button type="button"
                                onclick="assessmentCommonHandle.queryChksAssessmentProjectPerformance(this);"
                                class="btn btn-info btn-sm">查询<i class="fa fa-search"></i></button>
                    </div>
                    <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                        <button type="button" class="btn btn-primary btn-sm"
                                onclick="$(this).closest('form').clearAll()">清空查询
                        </button>
                    </div>

                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <button type="button" class="btn btn-info btn-sm"
                                onclick="assessmentCommonHandle.copyData(this);"><i
                                class="fa fa-copy" aria-hidden="true"></i> 拷贝
                        </button>
                        <button type="button" class="btn btn-warning btn-sm"
                                onclick="assessmentCommonHandle.pasteAll(this);"><i
                                class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                        </button>
                    </div>

                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                        <div class="input-group ">
                            <input type="hidden" name="id">
                            <input type="text" readonly="readonly" name="name"
                                   class="form-control form-control-sm"
                                   placeholder="无拷贝数据">
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    <i class="fa "></i>
                                    清空拷贝
                                </button>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript">


    var assessmentCommonHandle = {};

    assessmentCommonHandle.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
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
        assessmentCommonHandle.ajaxServerMethod(data, "/chksAssessmentProjectPerformance/saveAssessmentServer", "post", callback, null);
    };

    assessmentCommonHandle.updateAssessmentProjectPerformance = function (data, callback) {
        assessmentCommonHandle.ajaxServerMethod({fomData: JSON.stringify(data)}, "/chksAssessmentProjectPerformance/updateAssessmentProjectPerformance", "post", callback, null);
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceDtoList = function (query, callback) {
        assessmentCommonHandle.ajaxServerMethod(query, "/chksAssessmentProjectPerformance/getAssessmentProjectPerformanceDtoList", "get", callback, null);
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({performanceId: id}, "/chksAssessmentProjectPerformance/getAssessmentProjectPerformanceDetailByPerformanceIdList", "get", callback, null);
    };

    assessmentCommonHandle.deleteResponsibilityById = function (id) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/chksAssessmentProjectPerformance/deleteResponsibilityById", "post", function () {
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
        assessmentCommonHandle.ajaxServerMethod({activityId: activityId}, "/chksAssessmentProjectPerformance/getActivityIdByUserAccountList", "get", callback, null);
    };

    assessmentCommonHandle.getAssessmentItemTemplate = function (query, callback) {
        assessmentCommonHandle.ajaxServerMethod(query, "/chksAssessmentProjectPerformance/getAssessmentItemTemplate", "get", callback, null);
    };

    assessmentCommonHandle.deleteSpotAssessment = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/chksAssessmentProjectPerformance/deleteAssessmentProjectPerformanceByIds", "post", callback, null);
    };

    assessmentCommonHandle.getAssessmentProjectPerformanceById = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/chksAssessmentProjectPerformance/getAssessmentProjectPerformanceById", "get", callback, null);
    };


    //拷贝
    assessmentCommonHandle.copyData = function (_this) {
        var table = $("#assessmentTableList");
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
                form.find("input[name='name']").val(rows[0].activityName + " " + rows[0].businessKey);
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
        var table = $("#assessmentTableList");
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
                }, "/chksAssessmentProjectPerformance/pasteAll", "post", function (message) {
                    AlertSuccess("粘贴情况 ", message);
                    table.bootstrapTable('uncheckAll');
                    form.clearAll();
                    table.bootstrapTable('refresh');
                });
            });
        }
    };

    /**
     * 查询
     */
    assessmentCommonHandle.queryChksAssessmentProjectPerformance = function (_this) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        if ('${processInsId}') {
            data.processInsId = '${processInsId}';
        }
        if (!data.processInsId) {
            if ('${projectPlanDetails.processInsId}') {
                data.processInsId = '${projectPlanDetails.processInsId}';
            }
            if ('${activityId}') {
                data.activityId = '${activityId}';
            }
        }
        if ('${activityDtoList}') {
            var activityIds = [];
            var obj = null;
            try {
                obj = JSON.parse('${el:toJsonString(activityDtoList)}');
            } catch (e) {
                console.log(e);
            }
            if (obj) {
                $.each(obj, function (i, item) {
                    activityIds.push(item.id);
                });
            }
            if (activityIds.length != 0) {
                data.activityIds = activityIds.join(",");
            }
        }
        var keys = Object.keys(data);
        var newObj = {};
        $.each(keys, function (i, key) {
            if (data[key]) {
                newObj[key] = data[key];
            }
        });
        assessmentCommonHandle.getChksBootstrapTableVoBase($("#assessmentTableList"), newObj);
    };

    /*
     考核列表
     */
    assessmentCommonHandle.getChksBootstrapTableVoBase = function (table, query) {
        var cols = [];
        cols.push({
            field: 'ckeckbox',
            checkbox: true
        });
        cols.push({
            field: 'activityName', title: '考核节点', formatter: function (value, row, index) {
                var s = value;
                if (row.businessKey) {
                    s += "【" + row.businessKey + "】";
                }
                return s;
            }
        });
        cols.push({field: 'businessKey', title: '业务名称'});
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
        cols.push({field: 'byExaminePeopleName', title: '被考核人'});
        cols.push({field: 'examinePeopleName', title: '考核人'});
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
            field: 'examineStatus', title: '考核操作', formatter: function (value, row, index) {
                var str = "";
                var btnClass = 'btn-success';
//                str = "<button type='button' style='margin-left: 5px;' data-placement='top' data-original-title='无权限' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-unlock-alt fa-white'></i></button>";
                //这里涉及到权限,如果要优化请慎重操作
                //完成之后查看
                if (value == 'finish') {
                    if (row.examineUrl) {
                        //进入一个地址查看考核内容
                        str = "<button type='button' onclick='assessmentCommonHandle.taskOpenWin(\"" + row.examineUrl + "\")'  style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info tooltips'  ><i class='fa fa-search fa-white'></i></button>";
                    } else {
                        //使用弹窗查看考核
                        str = "<button type='button' onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info tooltips'  ><i class='fa fa-search fa-white'></i></button>";
                    }
                }
                //考核未完成
                if (value == 'runing') {
                    //进入一个地址来考核
                    if (row.examineUrl) {
                        str += '<button type="button" class="btn  btn-xs btn-success tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.taskOpenWin2(' + "'" + row.id + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa fa-arrow-right fa-white"></i></button>';
                    } else {
                        //使用弹窗考核
                        str += '<button type="button" class="btn  btn-xs btn-success tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.openAssessmentProjectPerformanceBox(' + "'" + row.id + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa fa-arrow-right fa-white"></i></button>';
                    }
                }
                //抽查考核
                if (row.spotActivityId) {
                    if (row.spotId) {
                        //使用弹窗查看考核
                        str += "<button type='button' onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + row.spotId + ")' style='margin-left: 5px;' data-placement='top' data-original-title='抽查考核查看' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-search fa-white'></i></button>";
                    } else {
                        str += '<button type="button" class="btn btn-xs btn-primary tooltips" style="margin-left: 5px;" data-placement="bottom" data-original-title="抽查考核填写" onclick="assessmentCommonHandle.showChkSpotAssessmentParent(' + "'" + row.id + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa fa-arrow-right fa-white"></i></button>';
                    }
                }
                return str;
            }
        });

        //cols.push({field: 'remarks', title: '综合评价'});
        var toolbar = $("#assessmentItemToolbar");
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },
            toolbar: toolbar.selector
        };
        toolbar.show();
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getChksBootstrapTableVo", cols, query, method);
    };

    /**
     * 抽查考核保存
     */
    assessmentCommonHandle.saveChkSpotAssessment = function () {
        var target = $("#tableChkSpotAssessment").find("tbody");
        var box = $("#divChksRecordModal");
        var table = $("#assessmentTableList");
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
    assessmentCommonHandle.showChkSpotAssessmentParent = function (id, selector) {
        var target = $(selector);
        var itemObj = target.bootstrapTable('getRowByUniqueId', id);
        var box = $("#divChksRecordModal");
        var table = $("#tableChkSpotAssessment").find("tbody");
        var query = {
            boxReActivitiId: itemObj.spotActivityId,
            boxId: itemObj.boxId
        };
        if (!itemObj.examineScore) {
            notifyWarning('警告', "此条记录未考核,暂时不能抽查!");
            return false;
        }
        assessmentCommonHandle.getActivityIdByUserAccountList(itemObj.spotActivityId, function (accountList) {
            var examinePeople = '${sysUserDto.userAccount}';
            if (examinePeople) {
                if (jQuery.inArray(examinePeople, accountList) != -1) {
                    box.modal("show");
                    box.find("input[name=marsterId]").val(id);
                    assessmentCommonHandle.getAssessmentItemTemplate(query, function (data) {
                        assessmentCommonHandle.writeAssessmentItemHtml(table, {}, [], data);
                    });
                } else {
                    notifyWarning('警告', "当前用户不属于此节点组的考核人员!");
                }
            }
        });
    };

    //废弃
    assessmentCommonHandle.taskOpenWin = function (url) {
        url = "${pageContext.request.contextPath}" + url;
        openWin(url, function () {
            $("#assessmentTableList").bootstrapTable('refresh');
        })
    };

    assessmentCommonHandle.taskOpenWin2 = function (id, selector) {
        var target = $(selector);
        var item = target.bootstrapTable('getRowByUniqueId', id);
        assessmentCommonHandle.getActivityIdByUserAccountList(item.activityId, function (accountList) {
            var url = "${pageContext.request.contextPath}" + item.examineUrl;
            var examinePeople = '${sysUserDto.userAccount}';
            console.log(accountList);
            if (examinePeople) {
                if (jQuery.inArray(examinePeople, accountList) != -1) {
                    try {
                        openWin(url, function () {
                            target.bootstrapTable('refresh');
                        })
                    } catch (e) {
                    }
                } else {
                    notifyWarning('警告', "当前用户不属于此节点组的考核人员!");
                }
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
            $("#assessmentTableList").bootstrapTable('refresh');
        });
    };


    /**
     * 考核填写 弹窗方式
     * @param id
     */
    assessmentCommonHandle.openAssessmentProjectPerformanceBox = function (id) {
        var target = $("#assessmentTableList");
        var box = $("#divAssessmentProjectPerformanceBox");
        var table = $("#tableAssessmentProjectPerformanceBox").find("tbody");
        var itemA = target.bootstrapTable('getRowByUniqueId', id);
        box.modal("show");
        box.find("input[name=id]").val(id);
        assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList(id, function (parentData) {
            assessmentCommonHandle.getAssessmentItemTemplate({
                boxReActivitiId: itemA.activityId,
                boxId: itemA.boxId
            }, function (data) {
                assessmentCommonHandle.writeAssessmentItemHtml(table, {
                    activityName: itemA.activityName,
                    id: itemA.id,
                    remarks: itemA.remarks
                }, parentData, data);
            });
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
            assessmentCommonHandle.writeAssessmentItemHtml(table, obj, obj.detailList, obj.detailList);
            table.find("input").attr({readonly: 'readonly'});
            table.find("textarea").attr({readonly: 'readonly'});
        });
    };


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
        //当填写 了说明，却又不填写考核分值 是不允许的
        if (remarks) {
            if (filterData.length == 0) {
                AlertError("错误", "当填写了考核综合说明,那么就必须对考核子项进行打分，或者不填考核说明。");
                return false;
            }

        }
        if (filterData.length != 0) {
            if (data.length != filterData.length) {
                AlertError("错误", "请填写完整!");
                return false;
            }
            if (!remarks) {
                AlertError("错误", "请填写考核说明!");
                return false;
            }
        }
        return true;
    }


</script>
