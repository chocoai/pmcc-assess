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
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table-striped table" id="tableChkSpotAssessment">
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
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="divAssessmentProjectPerformanceBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核记录填写</h3>
                <input type="hidden" name="id">
            </div>
            <div class="modal-body">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table-striped table" id="tableAssessmentProjectPerformanceBox">
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
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessmentCommonHandle.saveAssessmentProjectPerformanceBoxData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="divAssessmentProjectPerformanceBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核详情记录</h3>
            </div>
            <div class="modal-body">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table-striped table" id="tableAssessmentProjectPerformanceBoxDetail">
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
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
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

    assessmentCommonHandle.getAssessmentProjectPerformanceDetailByPerformanceIdList = function (id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getAssessmentProjectPerformanceDetailByPerformanceIdList",
            type: "get",
            dataType: "json",
            data: {performanceId:id},
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
        cols.push({field: 'activityName', title: '考核节点'});
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
        cols.push({
            field: 'examineStatus', title: '考核操作', formatter: function (value, row, index) {
                var str = "";

                //完成之后查看
                if (value == 'finish') {
                    if (row.examineUrl) {
                        str += "<a onclick='assessmentCommonHandle.taskOpenWin(\"" + row.examineUrl + "\")' href='javascript://' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-warning tooltips'  ><i class='fa fa-search fa-white'></i></a>";
                    } else {
                        //使用弹窗考核
                        str += "<a onclick='assessmentCommonHandle.findAssessmentProjectPerformanceBox(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核查看' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-search fa-white'></i></a>";
                    }
                }

                //考核未完成
                if (value == 'runing') {
                    var btnClass = 'btn-success';
                    //进入一个地址来考核
                    if (row.examineUrl) {
                        str += "<a onclick='assessmentCommonHandle.taskOpenWin(\"" + row.examineUrl + "\")' href='javascript://' style='margin-left: 5px;' data-placement='top' data-original-title='考核填写' class='btn btn-xs " + btnClass + " tooltips'  ><i class='fa fa-arrow-right fa-white'></i></a>";
                    } else {
                        //使用弹窗考核
                        str += "<a onclick='assessmentCommonHandle.openAssessmentProjectPerformanceBox(" + row.id + ")' style='margin-left: 5px;' data-placement='top' data-original-title='考核填写' class='btn btn-xs btn-primary tooltips'  ><i class='fa fa-edit fa-white'></i></a>";
                    }

                }
                return str;
            }
        });
        cols.push({field: 'remarks', title: '综合评价'});
        if (data) {
            $.each(data, function (i, item) {
                cols.push(item);
            });
        }
        cols.push({field: 'businessKey', title: '业务类型'});
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

    assessmentCommonHandle.taskOpenWin = function (url) {
        url = "${pageContext.request.contextPath}"+url ;
        openWin(url, function () {
            $("#assessmentTableList").bootstrapTable('refresh');
        })
    };

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
            toastr.warning("考核需要填写全部数据!");
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
            toastr.warning("考核成功!");
            box.modal("hide");
            $("#assessmentTableList").bootstrapTable('refresh');
        });
    };

    assessmentCommonHandle.openAssessmentProjectPerformanceBox = function (id) {
        var target = $("#assessmentTableList");
        var box = $("#divAssessmentProjectPerformanceBox");
        var table = $("#tableAssessmentProjectPerformanceBox").find("tbody");
        var item = target.bootstrapTable('getRowByUniqueId', id);
        box.modal("show");
        box.find("input[name=id]").val(id);
        assessmentCommonHandle.getAssessmentItemTemplate({boxReActivitiId: item.activityId,boxId:item.boxId}, function (data) {
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

    assessmentCommonHandle.findAssessmentProjectPerformanceBox = function (id) {
        var target = $("#assessmentTableList");
        var box = $("#divAssessmentProjectPerformanceBoxDetail");
        var table = $("#tableAssessmentProjectPerformanceBoxDetail").find("tbody");
        var obj = target.bootstrapTable('getRowByUniqueId', id);
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
        table.find("input").attr({readonly:'readonly'});
        table.find("textarea").attr({readonly:'readonly'});
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
                    console.log(result);
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };




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
            toastr.warning("请确定考核数据填写情况!或者咨询管理员配置考核数据模板!");
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
                Alert("当填写了考核综合说明,那么就必须对考核子项进行打分，或者不填考核说明。");
                return false;
            }

        }
        if (filterData.length != 0) {
            if (data.length != filterData.length) {
                Alert("请填写完整!");
                return false;
            }
            if (!remarks) {
                Alert("请填写考核说明!");
                return false;
            }
        }
        return true;
    }


</script>
