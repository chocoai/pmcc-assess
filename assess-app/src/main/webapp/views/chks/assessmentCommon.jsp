<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--抽查记录列表弹窗--%>
<div id="divSpotListModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">抽查记录列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table class="table" id="assessmentSpotList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<%--调整记录列表弹窗--%>
<div id="divAdjustListModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">调整记录列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table class="table" id="assessmentAdjustList">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divAssessmentPerformanceModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="isSpot">
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <table class="table table-bordered"
                                           id="tbAssessmentPerformance">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessmentCommonHandle.savePerformanceData();">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divAssessmentPerformanceModalDetail" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
                <form class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="form-inline x-valid">
                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <table class="table table-bordered"
                                           id="tbAssessmentPerformanceDetail">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<%--考核html模板--%>
<script type="text/html" id="assessmentItemTemplateHTML">
    <tr>
        <td>{index}
            <input data-name="id" type="hidden" name="id{id}" value="{id}">
            <input data-name="performanceId" type="hidden" name="performanceId{performanceId}" value="{performanceId}">
            <input data-name="contentId" type="hidden" name="contentId{contentId}" value="{contentId}">
        </td>
        <td>{assessmentDes} 【范围:{minScore}~{maxScore} 标准值:{standardScore}】</td>
        <td>
            <input type="text" required="required" data-rule-number='true'
                   data-name="actualScore" data-rule-range="[{minScore},{maxScore}]"
                   class="form-control input-full" name="actualScore{id}"
                   placeholder="" value="{actualScore}">
        </td>
        <td><input type="text" data-name="remark" class="form-control input-full"
                   name="remark{id}" value="{remark}"
                   placeholder=""></td>
    </tr>
</script>
<script type="text/html" id="assessmentItemTemplateHTMLReadonly">
    <tr>
        <td>{index}
            <input data-name="id" type="hidden" name="id{id}" value="{id}">
            <input data-name="performanceId" type="hidden" name="performanceId{performanceId}" value="{performanceId}">
            <input data-name="contentId" type="hidden" name="contentId{contentId}" value="{contentId}">
        </td>
        <td>{assessmentDes} 【范围:{minScore}~{maxScore} 标准值:{standardScore}】</td>
        <td>
            <label name="actualScore{id}" class="form-control input-full">{actualScore}</label>
        </td>
        <td>
            <label name="remark{id}" class="form-control input-full">{remark}</label>
    </tr>
</script>
<script type="text/html" id="assessmentItemTemplateRemarksHTML">
    <tr>
        <td colspan="5"><textarea class="form-control input-full" name="remarks"
                                  placeholder="考核综合说明">{remarks}</textarea></td>
    </tr>
</script>
<script type="text/html" id="assessmentItemTemplateRemarksHTMLReadonly">
    <tr>
        <td colspan="5">
            <label name="remarks" class="form-control input-full">{remarks}</label>
        </td>
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

    assessmentCommonHandle.saveAssessmentServer = function (data, callback) {
        jQuery.extend(data, {planDetailsId: '${projectPlanDetails.id}'});
        data.processInsId = '${processInsId}';
        assessmentCommonHandle.ajaxServerMethod(data, "/assessmentPerformance/saveAssessmentServer", "post", callback, null);
    };

    assessmentCommonHandle.updatePerformanceDto = function (data, callback) {
        assessmentCommonHandle.ajaxServerMethod({fomData: JSON.stringify(data)}, "/assessmentPerformance/updatePerformanceDto", "post", callback, null);
    };

    assessmentCommonHandle.getPerformanceDetailsByPerformanceId = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({performanceId: id}, "/assessmentPerformance/getPerformanceDetailsByPerformanceId", "get", callback, null);
    };

    assessmentCommonHandle.getActivityIdByUserAccountList = function (activityId, callback) {
        assessmentCommonHandle.ajaxServerMethod({activityId: activityId}, "/assessmentPerformance/getActivityIdByUserAccountList", "get", callback, null);
    };

    assessmentCommonHandle.getPerformanceById = function (id, callback) {
        assessmentCommonHandle.ajaxServerMethod({id: id}, "/assessmentPerformance/getPerformanceById", "get", callback, null);
    };

    //选择人员
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

    //替换考核项信息
    assessmentCommonHandle.replaceAssessmentItem = function (html, item) {
        html = html.replace(/{index}/g, item.index);
        html = html.replace(/{id}/g, item.id);
        html = html.replace(/{performanceId}/g, item.performanceId);
        html = html.replace(/{contentId}/g, item.contentId);
        html = html.replace(/{name}/g, AssessCommon.toString(item.name));
        html = html.replace(/{assessmentDes}/g, AssessCommon.toString(item.assessmentDes));
        html = html.replace(/{minScore}/g, AssessCommon.toString(item.minScore));
        html = html.replace(/{maxScore}/g, AssessCommon.toString(item.maxScore));
        html = html.replace(/{standardScore}/g, AssessCommon.toString(item.standardScore));
        html = html.replace(/{actualScore}/g, AssessCommon.toString(item.actualScore));
        html = html.replace(/{remark}/g, AssessCommon.toString(item.remark));
        return html;
    };

    //生成考核内容html
    assessmentCommonHandle.writeAssessmentItemHtml = function (target, performance, performanceDetails, readonly) {
        var restHtml = "";
        $.each(performanceDetails, function (i, item) {
            if (!item.assessmentDes) {
                item.assessmentDes = "";
            }
            var templateId = "assessmentItemTemplateHTML";
            if (readonly && readonly == 'true') {
                templateId += "Readonly";
            }
            restHtml += assessmentCommonHandle.replaceAssessmentItem($("#" + templateId).html(), {
                index: i + 1,
                contentId: item.contentId,
                id: item.id,
                actualScore: item.actualScore,
                remark: item.remark,
                performanceId: performance.id,
                name: performance.activityName,
                assessmentDes: item.content,
                minScore: item.minScore,
                maxScore: item.maxScore,
                standardScore: item.standardScore
            });
        });
        if (performanceDetails.length >= 1) {
            var remarksHtmlId = "assessmentItemTemplateRemarksHTML";
            if (readonly && readonly == 'true') {
                remarksHtmlId += "Readonly";
            }
            var remarksHtml = $("#" + remarksHtmlId).html();
            remarksHtml = remarksHtml.replace(/{remarks}/g, performance.remarks);
            restHtml += remarksHtml;
        }
        target.empty().append(restHtml);
    };

    //拷贝
    assessmentCommonHandle.copyData = function (_this) {
        var table = $("#assessmentPerformanceTableList");
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
        var table = $("#assessmentPerformanceTableList");
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
        cols.push({
            field: 'businessKey', title: '名称', formatter: function (value, row, index) {
                var str = value;
                if(row.sourceViewUrl){
                    str='<a target="_blank" href="${pageContext.request.contextPath}'+row.sourceViewUrl+'">'+value+'</a>';
                }
                str += "【" + row.assessmentTypeName + "】";
                str += '<button type="button" class="btn  btn-xs btn-info" style="margin-left: 5px;" data-placement="bottom" data-original-title="调整记录" onclick="assessmentCommonHandle.showAdjustRecordListModal(' + row.id + ')" > <i class="fa fa-tasks fa-white"></i></button>';
                str += '<button type="button" class="btn  btn-xs btn-info" style="margin-left: 5px;" data-placement="bottom" data-original-title="抽查记录" onclick="assessmentCommonHandle.showSpotRecordListModal(' + row.id + ')" > <i class="fa fa-cubes fa-white"></i></button>';
                return str;
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
        cols.push({
            field: 'examineStatus', title: '操作', formatter: function (value, row, index) {
                var str = "";
                var btnClass = 'btn-success';
                //完成之后查看
                if (value == 'finish') {
                    if (row.examineUrl) { //进入一个地址查看考核内容
                        str = "<button type='button' onclick='window.open(\"${pageContext.request.contextPath}" + row.examineDetailUrl + "\")'  style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    } else { //使用弹窗查看考核
                        str = "<button type='button' onclick='assessmentCommonHandle.showPerformanceDetailModal(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    }
                }
                var detailsFlag = row.examinePeople === '${currUserAccount}' && 'details' === '${flog}';//详情页面权限控制
                var approvalFlag = (row.examinePeople === '${currUserAccount}' || !row.examinePeople) && 'approval' === '${flog}';//审批页面权限控制
                //考核未完成
                if (value == 'runing' && (detailsFlag || approvalFlag)) { //进入一个地址来考核
                    if (row.examineUrl) {
                        str += '<button type="button" class="btn  btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.taskOpenExamineUrl(' + "'" + row.id + "'" + "," + "'" + table.selector + "'" + ')" > <i class="fa fa-pen fa-white"></i></button>';
                    } else { //使用弹窗考核
                        str += '<button type="button" class="btn  btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="考核填写" onclick="assessmentCommonHandle.showPerformanceModal(' + row.id + ',\'' + table.selector + '\')" > <i class="fa fa-pen fa-white"></i></button>';
                    }
                }

                //抽查考核
                if (row.canSpot) {
                    str += '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-placement="bottom" data-original-title="抽查考核填写" onclick="assessmentCommonHandle.showSpotPerformanceModal(' + row.id + ')" > <i class="fa fa-cube fa-white"></i></button>';
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

    //打开自定义考核页面
    assessmentCommonHandle.taskOpenExamineUrl = function (id, selector) {
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
     * 抽查考核填写 弹窗方式
     * @param id
     * @param selector
     */
    assessmentCommonHandle.showSpotPerformanceModal = function (performanceId) {
        var table = $("#tbAssessmentPerformance").find("tbody");
        $.ajax({
            url: '${pageContext.request.contextPath}/assessmentPerformance/getSpotPerformanceDetailList',
            type: 'get',
            data: {performanceId: performanceId},
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    var restHtml='';
                    $.each(result.data, function (i, item) {
                        var html = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                            index: i + 1,
                            id: item.id,
                            contentId: item.contentId,
                            assessmentDes: item.content,
                            actualScore: '',
                            remark: '',
                            performanceId: performanceId,
                            minScore: item.minScore,
                            maxScore: item.maxScore,
                            standardScore: item.standardScore
                        });
                        restHtml += html;
                    });
                    if (result.data.length >= 1) {
                        var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                        remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                        restHtml += remarksHtml;
                    }
                    table.empty().append(restHtml);
                }
            }
        });
        var form = $("#tbAssessmentPerformance").closest('form');
        form.find("input[name=id]").val(performanceId);
        form.find("input[name=isSpot]").val('true');
        $("#divAssessmentPerformanceModal").modal();
    };

    /**
     * 考核填写 弹窗方式
     * @param id
     */
    assessmentCommonHandle.showPerformanceModal = function (id) {
        if (id == undefined || id == null) {
            notifyWaring('提示', '数据不存在');
            return false;
        }
        var target = $("#assessmentPerformanceTableList");
        var box = $("#divAssessmentPerformanceModal");
        var table = $("#tbAssessmentPerformance").find("tbody");
        var itemA = target.bootstrapTable('getRowByUniqueId', id);
        box.modal("show");
        box.find("input[name=id]").val(id);
        assessmentCommonHandle.getPerformanceDetailsByPerformanceId(id, function (performanceDetails) {
            var restHtml = "";
            $.each(performanceDetails, function (i, item) {
                var html = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
                    index: i + 1,
                    id: item.id,
                    contentId: item.contentId,
                    assessmentDes: item.content,
                    actualScore: '',
                    remark: '',
                    performanceId: item.performanceId,
                    minScore: item.minScore,
                    maxScore: item.maxScore,
                    standardScore: item.standardScore
                });
                restHtml += html;
            })
            if (performanceDetails.length >= 1) {
                var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                restHtml += remarksHtml;
            }
            table.empty().append(restHtml);
        })
    };

    /**
     * 考核查看详情 弹窗方式
     * @param id
     */
    assessmentCommonHandle.showPerformanceDetailModal = function (id) {
        var box = $("#divAssessmentPerformanceModalDetail");
        var table = $("#tbAssessmentPerformanceDetail").find("tbody");
        assessmentCommonHandle.getPerformanceById(id, function (obj) {
            box.modal("show");
            var restHtml = "";
            if (obj.performanceDetailDtoList) {
                $.each(obj.performanceDetailDtoList, function (i, item) {
                    var htmlB = assessmentCommonHandle.replaceAssessmentItem($("#assessmentItemTemplateHTMLReadonly").html(), {
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
            var remarksHtml = $("#assessmentItemTemplateRemarksHTMLReadonly").html();
            if (obj.remarks) {
                remarksHtml = remarksHtml.replace(/{remarks}/g, obj.remarks);
            } else {
                remarksHtml = remarksHtml.replace(/{remarks}/g, '');
            }
            restHtml += remarksHtml;
            table.empty().append(restHtml);
        });
    };

    /**
     * 考核保存
     */
    assessmentCommonHandle.savePerformanceData = function () {
        var form = $("#tbAssessmentPerformance").closest('form');
        if (!form.valid()) {
            return false;
        }
        var data = [];
        var filterData = [];
        var remarks = form.find("textarea[name=remarks]").val();
        var isSpot = form.find("input[name=isSpot]").val();
        assessmentCommonHandle.getChksSonData(form, data);
        for (var i = 0; i < data.length; i++) {
            if (data[i].actualScore) {
                filterData.push(data[i]);
            }
        }
        var parentData = {
            id: form.find("input[name=id]").val(),
            remarks: remarks,
            examineStatus: 'finish'
        };
        assessmentCommonHandle.saveAssessmentServer({
            chksScore: JSON.stringify(filterData),
            fomData: JSON.stringify(parentData),
            isSpot: isSpot
        }, function (data) {
            notifySuccess("成功", "考核成功!");
            $("#divAssessmentPerformanceModal").modal("hide");
            $("#assessmentPerformanceTableList").bootstrapTable('refresh');
        });
    };

    //一键完成
    assessmentCommonHandle.batchSetFinish = function () {
        var table = $("#assessmentPerformanceTableList");
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

    //考核调整列表窗口显示
    assessmentCommonHandle.showAdjustRecordListModal = function (performanceId) {
        assessmentCommonHandle.loadAdjustRecodeList(performanceId);
        $('#divAdjustListModal').modal();
    }

    //考核调整列表加载
    assessmentCommonHandle.loadAdjustRecodeList = function (performanceId) {
        var table = $("#assessmentAdjustList");
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
        cols.push({field: 'examineScore', title: '考核得分'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({
            field: 'examineStatus', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (value == 'finish') {
                    if (row.examineUrl) { //进入一个地址查看考核内容
                        str = "<button type='button' onclick='window.open(\"${pageContext.request.contextPath}" + row.examineDetailUrl + "\")'  style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    } else { //使用弹窗查看考核
                        str = "<button type='button' onclick='assessmentCommonHandle.showPerformanceDetailModal(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    }
                }
                return str;
            }
        });

        var query = {
            performanceId: performanceId
        }
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/assessmentPerformance/getAdjustRecordListByPerformanceId", cols, query, method);
    }

    //考核抽查列表窗口显示
    assessmentCommonHandle.showSpotRecordListModal = function (performanceId) {
        assessmentCommonHandle.loadSpotRecodeList(performanceId);
        $('#divSpotListModal').modal();
    }

    //考核抽查列表加载
    assessmentCommonHandle.loadSpotRecodeList = function (performanceId) {
        var table = $("#assessmentSpotList");
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
        cols.push({field: 'examineScore', title: '抽查得分'});
        cols.push({
            field: 'examineDate', title: '抽查时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, true);
                }
                return "";
            }
        });
        cols.push({
            field: 'examineStatus', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (value == 'finish') {
                    if (row.examineUrl) { //进入一个地址查看考核内容
                        str = "<button type='button' onclick='window.open(\"${pageContext.request.contextPath}" + row.examineDetailUrl + "\")'  style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    } else { //使用弹窗查看考核
                        str = "<button type='button' onclick='assessmentCommonHandle.showPerformanceDetailModal(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></button>";
                    }
                }
                return str;
            }
        });

        var query = {
            performanceId: performanceId
        }
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/assessmentPerformance/getSpotRecordListByPerformanceId", cols, query, method);
    }
</script>
