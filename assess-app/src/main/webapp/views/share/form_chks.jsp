<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zly
  Date: 2019/9/20
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">

</div>

<div class="form-group">
    <label class="col-sm-1 control-label">
        考核<span class="symbol required"></span>
    </label>
    <div class="col-sm-11">
        <div class="form-group">
            <button type="button" id="addData" class="btn btn-primary"
                    href="#divBox" onclick="historyChksData();"> 历史考核
            </button>
        </div>
        <c:forEach var="item" items="${assessmentItemList}">
            <div class="x-valid">
                <label class="col-sm-12" for="chks_${item.id}">
                        ${item.assessmentDes}(${item.minScore}~${item.maxScore})
                </label>
            </div>
            <div class="col-sm-2">
                <input type="number" required name="chks_${item.id}" id="chks_${item.id}"
                       class="form-control">
            </div>
        </c:forEach>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-1 control-label">
        考核说明<span class="symbol required"></span>
    </label>
    <div class="col-sm-11">
        <div class="x-valid">
                           <textarea required placeholder="考核说明" id="chksRemarks" name="chksRemarks"
                                     class="form-control"></textarea>
        </div>
    </div>
</div>


<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">历史考核</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <table id="tb_list"
                                   class="table table-striped jambo_table bulk_action table-bordered"></table>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function checkChks(data) {
        var json = {};
        <c:forEach var="item" items="${assessmentItemList}">
        var score = Number($("#chks_${item.id}").val());
        var maxScore = Number("${item.maxScore}");
        var minScore = Number("${item.minScore}");
        if (score > maxScore || score < minScore) {
            toastr.warning("请在考核范围内打分");
            return false;
        }
        json["${item.id}"] = $("#chks_${item.id}").val();
        </c:forEach>
        data["chksScore"] = JSON.stringify(json);
        var row = undefined;
        $.each($("#tb_log").bootstrapTable("getData"),function (i,item) {
            //确定申请人
            if (item.bisApply){
                row = item ;
            }
        }) ;
        if (row) {
            data["byExaminePeople"] = row.creator;
        }
    }

    function historyChksData() {
        var cols = [];
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
        $("#tb_list").bootstrapTable('destroy');
        TableInit("tb_list", "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getHistoryChksData", cols, {
            processInsId: "${processInsId}"
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $("#divBox").modal();
            }
        });
    }
</script>