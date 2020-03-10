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

<div id="assessmentItemToolbar" class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
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
                        <input type="text" placeholder="被考核人" name="byExaminePeople" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        考核人
                    </label>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <input type="text" placeholder="考核人" name="examinePeople" class="form-control input-full">
                    </div>
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
                    <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                        <div class="input-group">
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="assessmentCommonHandle.queryChksAssessmentProjectPerformance(this);">
                                    查询<i class="fa fa-search"></i>
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

    /**
     * 抽查列表 单元项
     */
    assessmentCommonHandle.getSpotCol = function () {
        var col = {
            field: 'spotActivityId', title: '抽查考核', formatter: function (value, row, index) {
                var str = "";
                if (row.spotActivityId) {
                    if (row.spotId) {
                        //使用弹窗查看考核
                        str += "<button type='button' onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + value + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-info tooltips'  ><i class='fa fa-search fa-white'></i></button>";
                    } else {
                        str += "<button type='button' onclick='assessmentCommonHandle.showChkSpotAssessmentParent(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='抽查考核填写' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-arrow-right fa-white'></i></button>";
                    }

                }
                return str;
            }
        };
        return col
    };

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
        var newObj = {} ;
        $.each(keys, function (i, key) {
            if (data[key]){
                newObj[key] = data[key] ;
            }
        });
        console.log(newObj) ;
        assessmentCommonHandle.getChksBootstrapTableVoBase($("#assessmentTableList"),newObj) ;
    };

    /*
     考核列表
     */
    assessmentCommonHandle.getChksBootstrapTableVoBase = function (table, query) {
        var cols = [];
        cols.push({
            field: 'activityName', title: '考核节点', formatter: function (value, row, index) {
                var s = value;
                if (row.businessKey) {
                    s += "【" + row.businessKey + "】";
                }
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
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },
            toolbar: '#assessmentItemToolbar'
        };
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
            console.log(accountList);
            if (examinePeople) {
                if (jQuery.inArray(examinePeople, accountList) != -1) {
                    box.modal("show");
                    box.find("input[name=marsterId]").val(id);
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
        var item = target.bootstrapTable('getRowByUniqueId', id);
        box.modal("show");
        box.find("input[name=id]").val(id);
        assessmentCommonHandle.getAssessmentItemTemplate({
            boxReActivitiId: item.activityId,
            boxId: item.boxId
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
