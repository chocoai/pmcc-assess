<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="divChksContentModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核</h3>
            </div>
            <div class="modal-body">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <c:if test="${!empty spotAssessmentItemDtoList}">
                        <form>
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
                                <c:forEach items="${spotAssessmentItemDtoList}" var="assessmentItem"
                                           varStatus="userStatus">
                                    <tr>
                                        <th scope="row">${userStatus.index+1}
                                            <input data-name="id" type="hidden" name="id${assessmentItem.id}" value="0">
                                            <input data-name="contentId" type="hidden"
                                                   name="contentId${assessmentItem.id}" value="${assessmentItem.id}">
                                        </th>
                                        <td>${assessmentItem.boxReActivitiNameCn}</td>
                                        <td>${assessmentItem.assessmentDes}
                                            (${assessmentItem.minScore}~${assessmentItem.maxScore})
                                        </td>

                                        <td><input type="text" required="required" data-rule-number='true'
                                                   data-name="actualScore"
                                                   data-minScore="${assessmentItem.minScore}"
                                                   data-maxScore="${assessmentItem.maxScore}"
                                                   class="form-control" name="actualScore${assessmentItem.id}"
                                                   placeholder="(请输入数字)"
                                                   onblur="chksVerification(this);"></td>

                                        <td><input type="text" data-name="remark" class="form-control"
                                                   name="remark${assessmentItem.id}"
                                                   placeholder="说明"></td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td colspan="5"><textarea class="form-control" name="remarks"
                                                              placeholder="考核综合说明"></textarea></td>
                                </tr>
                                </tbody>
                            </table>
                        </form>


                    </c:if>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveChkSpotAssessment(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divChksRecordModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核记录

                    <button type="button" class="btn btn-success"
                            onclick="$('#divChksContentModal').find('[data-name=actualScore]').val('').end().find('[data-name=remark]').val('').end().find('textarea[name=remarks]').val('') ;"
                            data-toggle="modal" href="#divChksContentModal"> 新增考核记录
                    </button>
                </h3>
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

<script type="text/html" id="assessmentItemTemplateRemarksHTML">
    <tr>
        <td colspan="5"><textarea class="form-control" name="remarks" placeholder="考核综合说明">{remarks}</textarea></td>
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

        <c:if test="${! spotCheck}">
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

            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                <c:if test="${flog == 'details'}">

                    <c:if test="${projectResponsibilityDto != null}">
                        <button class="btn btn-success" onclick="deleteResponsibilityById('${projectResponsibilityDto.id}');">
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
                            <td>考核标准</td>
                            <td>综合分值</td>
                            <td>实际得分</td>
                            <td>被考核人</td>
                            <td>考核人</td>
                            <td>考核项说明</td>
                            <td>考核时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${entryItem.value}" var="item">
                            <tr>
                                <td>${item.performanceDetailDto.content}</td>
                                <td>${item.examineScore}</td>
                                <td>${item.validScore}</td>
                                <td>${item.byExaminePeopleName}</td>
                                <td>${item.examinePeopleName}</td>
                                <td>${item.performanceDetailDto.remark} ${item.remarks}</td>
                                <td>
                                    <fmt:formatDate value="${item.examineDate}"
                                                    pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>

        </c:if>

        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table" id="boxReActivityDtoTableList">
            </table>
        </div>
    </div>


</div>


<script type="text/javascript">

    $(document).ready(function () {
        var spotCheck = "${spotCheck}";
        if (spotCheck == 'true') {

        }
        if (spotCheck == 'false') {
            boxReActivityDtoTableList();
            loadChksServerData();
        }
    });

    function deleteResponsibilityById(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/deleteResponsibilityById",
            type: "post",
            dataType: "json",
            data: {id:id},
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
    }


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
            if (obj.actualScore) {
                data.push(obj);
            }
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
                    var remarksHtml = $("#assessmentItemTemplateRemarksHTML").html();
                    remarksHtml = remarksHtml.replace(/{remarks}/g, '');
                    restHtml += remarksHtml;
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
    }


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
            planDetailsId: '${projectPlanDetails.id}',
            spotActivityId:0
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

    function saveChkSpotAssessment() {
        var box = $("#divChksContentModal");
        var divChks = $("#divChksRecordModal");
        var form = box.find("form");
        var target = box.find("tbody");
        if (!form.valid()) {
            return false;
        }
        var remarks = form.find("textarea[name=remarks]").val();
        var data = [];
        getChksSonData(target, data);
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var parentData = {
            spotActivityId: '${boxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}',
            remarks: remarks,
            activityId: divChks.find("input[name='activityId']").val(),
            byExaminePeople: divChks.find("input[name='byExaminePeople']").val()
        };
//        console.log(parentData);
//        console.log(data);

        saveAssessmentServer({chksScore: JSON.stringify(data), fomData: JSON.stringify(parentData)}, function () {
            toastr.success("考核成功!");
            $("#tableChkSpotAssessment").bootstrapTable('refresh');
        });


        box.modal("hide");
    }

    function deleteSpotAssessment(id,activityId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/deleteAssessmentProjectPerformanceByIds",
            type: "post",
            dataType: "json",
            data: {id:id},
            success: function (result) {
                if (result.ret) {
                    toastr.success("删除成功!");
                    $("#tableChkSpotAssessment").bootstrapTable('refresh');
                } else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }




    function tableChkSpotAssessmentList(activityId) {
        var processInsId = '${processInsId}';
        if (!processInsId) {
            processInsId = '${projectPlanDetails.processInsId}';
        }
        var options = {
            spotActivityId: '${boxReActivityDto.id}',
            projectId: '${projectPlanDetails.projectId}',
            processInsId: processInsId,
            planDetailsId: '${projectPlanDetails.id}',
            planId: '${projectPlanDetails.planId}',
            boxId: '${boxReDto.id}'
        };
        if (activityId) {
            options.activityId = activityId;
        }
        var target = $("#tableChkSpotAssessment");
        var cols = [];
        cols.push({
            field: 'id', title: '删除', width: "20%", formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:deleteSpotAssessment(' + row.id + "," + row.activityId + ');" >删除 <i class="fa fa-remove"></i></a>';
                str += '</div>';
                return str;
            }
        });

        getChksBootstrapTableVoBase(target, options,cols);
    }

    /*显示抽查数据页面*/
    function showChkSpotAssessmentParent(activityId, byExaminePeople) {
        var box = $("#divChksRecordModal");
        var from = box.find("form");
        from.clearAll();
        var data = {activityId: activityId, byExaminePeople: byExaminePeople};
        from.initForm(data);
        box.modal("show");
        tableChkSpotAssessmentList(activityId);
    }


    function getChksBootstrapTableVoBase(table, query, data) {

        var cols = [];
        cols.push({field: 'projectName', title: '项目名称'});
//        cols.push({field: 'processInsId', title: '流程实例'});
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
        getChksSonData(target, data);
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
        getChksSonData(target, data);
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