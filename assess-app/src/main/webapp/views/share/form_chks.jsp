<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <input type="hidden" name="tableId">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <table class="table">
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
                    </div>
                </form>

                <%--<div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">--%>
                    <%----%>
                <%--</div>--%>

                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <table class="table-striped table" id="tableChkSpotAssessment"></table>
                </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-success" onclick="saveChkSpotAssessment();">
                    保存或者修改考核记录
                </button>
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
        <td>{assessmentDes} 范围:{minScore}~{maxScore}</td>
        <td>
            <input type="text" required="required" data-rule-number='true'
                   data-name="actualScore"
                   data-minScore="{minScore}"
                   data-maxScore="{maxScore}"
                   class="form-control" name="actualScore{id}"
                   placeholder="(请输入数字)" value="{actualScore}"
                   onblur="chksVerification(this);">
        </td>
        <td><input type="text" data-name="remark" class="form-control"
                   name="remark{id}" value="{remark}"
                   placeholder="说明"></td>
    </tr>
</script>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>考核信息</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
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

                <c:if test="${boxReActivityDto != null}">
                    <button class="btn btn-success" onclick="saveAssessmentItem();">
                        保存考核记录
                    </button>
                </c:if>


            </c:if>
        </div>

        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table" id="boxReActivityDtoTableList">
            </table>
        </div>
    </div>


</div>


<script type="text/javascript">


    function getChksSonData(target, data) {
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
            data.push(obj);
        });
    }

    function replaceAssessmentItem(html, item) {
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
    }

    function loadChksServerData() {
        var target = $("#chksTableList").find("tbody");
        var option = {
            boxId: '${boxReDto.id}',
            activityId: '${boxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}'
        };
        getAssessmentProjectPerformanceDtoList(option, function (data) {
            if (data.length == 0) {
                getAssessmentItemTemplate({
                    boxId: '${boxReActivityDto.boxId}', boxReActivitiId: '${boxReActivityDto.id}'
                }, function (data) {
                    var restHtml = "";
                    $.each(data, function (i, item) {
                        var html = replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
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
                    target.empty().append(restHtml);
                });
            } else {
                var restHtml = "";
                $.each(data, function (i, obj) {
                    if (obj.detailList) {
                        $.each(obj.detailList, function (i, item) {
                            var htmlB = replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
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
                });
                target.empty().append(restHtml);
            }
        });
    }

    $(document).ready(function () {
        console.log("test") ;
        if ("${spotBoxReActivityDto}") {
            var spotCheck = "${spotCheck}";
            if (spotCheck == 'true') {
                boxReActivityDtoTableList();
            }
        }

        if ("${boxReActivityDto}") {
            boxReActivityDtoTableList();
            loadChksServerData();
        }
    });

    function saveAssessmentItem() {
        var target = $("#chksTableList").find("tbody");
        var frm = target.closest("form");
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
            activityName: '${boxReActivityDto.cnName}'
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
        getChksSonData(target, data);
        if (!frm.valid()) {
            return false;
        }
        saveAssessmentServer({chksScore: JSON.stringify(data), fomData: JSON.stringify(parentData)}, function (data) {
            toastr.warning("考核成功!");
            boxReActivityDtoTableList();
            loadChksServerData();
        });
    }

    function saveAssessmentServer(data, callback) {
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
    }

    function boxReActivityDtoTableList() {
        var target = $("#boxReActivityDtoTableList");
        var options = {
            boxId: '${boxReDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            planId: '${projectPlanDetails.planId}',
            planDetailsId: '${projectPlanDetails.id}'
        };
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
                options.activityIds = activityIds.join(",");
            }
        }
        getChksBootstrapTableVoBase(target, options);
    }



    function getAssessmentItemTemplate(query, callback) {
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
    }

    /**
     * 抽查数据保存
     */
    function saveChkSpotAssessment() {
        var box = $("#divChksRecordModal");
        var data = [];
        var form = box.find("form");
        if (!form.valid()) {
            return false;
        }
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var parentData = {
            spotActivityId: '${spotBoxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}',
            activityId: form.find("input[name='activityId']").val(),
            byExaminePeople: form.find("input[name='byExaminePeople']").val(),
            tableId: form.find("input[name='tableId']").val()
        };
        var target = box.find("form").find("tbody");
        getChksSonData(target, data);
//        box.modal("hide");

        saveAssessmentServer({chksScore: JSON.stringify(data), fomData: JSON.stringify(parentData)}, function (data) {
            toastr.warning("考核成功!");
            boxReActivityDtoTableList();
            tableChkSpotAssessmentList() ;
            writeChkSpotAssessmentParent({activityId:parentData.activityId,tableId:parentData.tableId,byExaminePeople:parentData.byExaminePeople}) ;
        });
    }

    function tableChkSpotAssessmentList() {
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var options = {
            spotActivityId: '${spotBoxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}'
        };
        var target = $("#tableChkSpotAssessment") ;
        getChksBootstrapTableVoBase(target, options);
    }

    /*显示抽查数据页面*/
    function showChkSpotAssessmentParent(activityId,byExaminePeople,tableId) {
        var box = $("#divChksRecordModal");
        var from = box.find("form");
        from.clearAll();
//        var data = {activityId:activityId,byExaminePeople:byExaminePeople,tableId:tableId} ;
        var data = {activityId:'${spotBoxReActivityDto.id}',byExaminePeople:byExaminePeople,tableId:tableId} ;
        from.initForm(data);
        box.modal("show");
        writeChkSpotAssessmentParent(data) ;
        tableChkSpotAssessmentList() ;
    }

    /*抽查数据 插入*/
    function writeChkSpotAssessmentParent(initData) {
        var option = {
            boxId: '${boxReDto.id}',
            spotActivityId: '${spotBoxReActivityDto.id}',
            activityId: initData.activityId,
            projectId: '${projectPlanDetails.projectId}',
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            tableId:initData.tableId,
            byExaminePeople:initData.byExaminePeople
        };
        var box = $("#divChksRecordModal");
        getAssessmentProjectPerformanceDtoList(option,function (data) {
            var target = box.find("form").find("tbody");
            if (data.length == 0){
                getAssessmentItemTemplate({boxId:option.boxId,spotCheck:true},function (result) {
                    var restHtml = "";
                    $.each(result, function (i, item) {
                        var html = replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
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
                    target.empty().append(restHtml);
                }) ;
            }
            if (data.length != 0){
                var restHtml = "";
                $.each(data, function (i, obj) {
                    if (obj.detailList) {
                        $.each(obj.detailList, function (i, item) {
                            var htmlB = replaceAssessmentItem($("#assessmentItemTemplateHTML").html(), {
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
                });
                target.empty().append(restHtml);
            }
        }) ;
    }


    function getChksBootstrapTableVoBase(table, query) {
        var spotCheck = "${spotCheck}";//当节点是抽查节点的情况
        if (spotCheck == 'true') {
            spotCheck = true;
        }else {
            spotCheck = false;
        }
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({field: 'businessKey', title: '业务名称'});
        cols.push({field: 'activityName', title: '考核节点'});
        cols.push({field: 'examinePeopleName', title: '考核人'});
        cols.push({field: 'byExaminePeopleName', title: '被考核人'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, false);
                }
                return "";
            }
        });
        cols.push({field: 'examineScore', title: '考核分值'});
        cols.push({field: 'validScore', title: '实际分值'});
        if (spotCheck){
            cols.push({
                field: 'id', title: '抽查考核', width: "20%", formatter: function (value, row, index) {
                    if (! row.spotActivityId){
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success" href="javascript:showChkSpotAssessmentParent(' + row.activityId +",'"+row.examinePeople+ "','"+row.id+ "'"+');" >抽查考核 <i class="fa fa-check-circle"></i></a>';
                        str += '</div>';
                        return str;
                    }else {
                        return "此条节点是抽查数据不可考核!" ;
                    }
                }
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
    }

    function getAssessmentProjectPerformanceDtoList(query, callback) {
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
    }


    /**
     * 范围校验
     * @param _this
     * @returns {boolean}
     */
    function chksVerification(_this) {
        var target = $(_this);
        var value = target.val();
        if (!value) {
            return false;
        }
        if (!$.isNumeric(value)) {
            target.val('');
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
        // console.info('value:'+value+" minScore:"+minScore+" maxScore:"+maxScore) ;
    }


    //收集数据 审批的时候
    function getChksData() {
        var result = {};
        var target = $("#chksTableList").find("tbody");
        var data = [];
        getChksSonData(target, data);
        if (data.length == 0) {
            return result;
        }
        result.chksScore = JSON.stringify(data);
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


</script>