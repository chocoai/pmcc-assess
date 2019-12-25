<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>考核信息</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form id="chksDataFrm">
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <c:if test="${!empty assessmentProjectPerformanceDtoList}">
                    <c:forEach items="${assessmentProjectPerformanceDtoList}" var="entryItem"
                               varStatus="userStatus">
                        <div class="row">
                            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                <h2 class="text-center">${userStatus.index+1} : ${entryItem.key}

                                    <!-- 抽查标识 -->
                                    <c:if test="${spotCheck}">
                                        <c:forEach items="${entryItem.value}" var="item" varStatus="userStatus">
                                            <c:if test="${userStatus.last}">
                                                <input type="button" class="btn btn-xs btn-primary" value="抽查"
                                                       onclick="showChkSpotAssessmentParent({projectId:'${item.projectId}',processInsId:'${item.processInsId}',boxId:'${item.boxId}',activityId:'${item.activityId}',activityName:'${item.activityName}',byExaminePeople:'${item.examinePeople}'});">
                                            </c:if>
                                        </c:forEach>
                                    </c:if>


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
            </div>

            <c:if test="${!empty assessmentItemList}">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
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
                        <c:forEach items="${assessmentItemList}" var="assessmentItem" varStatus="userStatus">
                            <tr>
                                <th scope="row">${userStatus.index+1} <input data-name="id" type="hidden"
                                                                             name="id${assessmentItem.id}"
                                                                             value="${assessmentItem.id}"></th>
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
                        </tbody>
                    </table>
                </div>
                <div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        考核说明<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="x-valid">
                                       <textarea required placeholder="考核说明" name="chksRemarks"
                                                 class="form-control"></textarea>
                        </div>
                    </div>
                </div>

                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <c:if test="${!empty boxReActivityDto}">
                        <table class="table" id="boxReActivityDtoTableList">
                        </table>
                        <script>
                            $(document).ready(function () {
                                boxReActivityDtoTableList();
                            });
                        </script>
                    </c:if>
                </div>
            </c:if>

        </form>
    </div>

    <c:if test="${flog == 'details'}">
        <c:if test="${!spotCheck}">
            <div class="x_content">
                <div class="form-group" style="text-align: center;">
                    <div class="btn-group">
                        <button class="btn btn-success">
                            保存考核记录
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
    </c:if>
</div>


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
                <input type="button" class="btn btn-primary" value="考核" onclick="showChkSpotAssessment();">
                <!-- 把页面上待考核人的节点信息 带入到这里来 -->
                <form>
                    <input type="hidden" name="projectId">
                    <input type="hidden" name="processInsId">
                    <input type="hidden" name="boxId">
                    <input type="hidden" name="activityName">
                    <input type="hidden" name="activityId">
                    <input type="hidden" name="byExaminePeople">
                    <input type="hidden" name="spotActivityId">
                </form>
                <table class="table">
                    <thead>
                    <tr>
                        <th width="10%">被考核人</th>
                        <th width="10%">考核人</th>
                        <th width="60%">考核项</th>
                        <th width="10%">得分</th>
                        <th width="10%">考核时间</th>
                        <th width="10%">说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>

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
                    <c:if test="${!empty spotCheckAssessmentItemList}">
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
                                <c:forEach items="${spotCheckAssessmentItemList}" var="assessmentItem"
                                           varStatus="userStatus">
                                    <tr>
                                        <th scope="row">${userStatus.index+1} <input data-name="id" type="hidden"
                                                                                     name="id${assessmentItem.id}"
                                                                                     value="${assessmentItem.id}"></th>
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


<script type="text/javascript">

    function boxReActivityDtoTableList() {
        var target = $("#boxReActivityDtoTableList");
        var query = {boxId:'${boxReActivityDto.boxId}',activityId:'${boxReActivityDto.id}'};
        getChksBootstrapTableVoBase(target, query);
        var obj = JSON.parse('${el:toJsonString(boxReActivityDto)}');
        console.log(obj) ;
    }


    function getChksBootstrapTableVoBase(table, query) {
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

    function getChkSpotAssessmentBySpotActivityId(query, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getChkSpotAssessmentBySpotActivityId",
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
        var box = $("#divChksContentModal");
        var boxRecord = $("#divChksRecordModal");
        var form = box.find("form");
        if (!form.valid()) {
            return false;
        }
        var frm = boxRecord.find("form");
        var target = box.find("tbody");
        var data = [];
        getChksSonData(target, data);
        var parentData = formSerializeArray(frm);
        $.ajax({
            url: "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/saveChkSpotAssessment",
            type: "post",
            dataType: "json",
            data: {
                chksScore: JSON.stringify(data),
                fomData: JSON.stringify(parentData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    box.modal("hide");
                    writeChkSpotAssessmentParent(parentData);
                } else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });

    }

    /*抽查数据添加页面*/
    function showChkSpotAssessment() {
        var box = $("#divChksContentModal");
        var frm = box.find("form");
        frm.find("tr").each(function (i, tr) {
            var ele = $(tr);
            ele.find("[data-name='actualScore']").val('');
            ele.find("[data-name='remark']").val('');
        });
        box.modal("show");
    }

    /*显示抽查数据页面*/
    function showChkSpotAssessmentParent(data) {
        var box = $("#divChksRecordModal");
        var from = box.find("form");
        from.clearAll();
        var obj = JSON.parse('${el:toJsonString(spotCheckAssessmentItemList[0])}');
        data.spotActivityId = obj.boxReActivitiId;
        from.initForm(data);
        box.modal("show");
        writeChkSpotAssessmentParent(data);
    }

    function writeChkSpotAssessmentParent(data) {
        var box = $("#divChksRecordModal");
        getChkSpotAssessmentBySpotActivityId(data, function (objData) {
            var html = "";
            $.each(objData, function (i, item) {
                var tr = "<tr>";
                tr += "<td>" + item.byExaminePeopleName + "</td>";
                tr += "<td>" + item.examinePeopleName + "</td>";
                tr += "<td>" + item.performanceDetailDto.content + "</td>";
                tr += "<td>" + item.examineScore + "</td>";
                tr += "<td>" + formatDate(item.examineDate) + "</td>";
                tr += "<td>" + item.performanceDetailDto.remark + "</td>";
                tr += "</tr>";
                html += tr;
            });
            box.find("tbody").empty();
            box.find("tbody").append(html);
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

    //数据非空校验
    function validChks() {
        return $("#chksDataFrm").valid();
    }

    function getChksSonData(target, data) {
        target.find("tr").each(function (i, tr) {
            var ele = $(tr);
            var obj = {};
            obj.contentId = ele.find("[data-name='id']").val();
            obj.actualScore = ele.find("[data-name='actualScore']").val();
            obj.remark = ele.find("[data-name='remark']").val();
            data.push(obj);
        });
    }

    //收集数据
    function getChksData() {
        var target = $("#chksTableList").find("tbody");
        var frm = $("#chksDataFrm");
        var data = [];
        getChksSonData(target, data);
        var result = {};
        result.chksScore = JSON.stringify(data);
        $.each($("#tb_log").bootstrapTable("getData"), function (i, item) {
            //确定申请人
            if (item.bisApply) {
                result.byExaminePeople = item.creator;
            }
        });
        result.chksRemarks = frm.find("textarea[name='chksRemarks']").val();
        return result;
    }


</script>