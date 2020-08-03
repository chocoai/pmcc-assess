<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>外勤考核</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        外勤考核
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <input type="hidden" name="bonusId" value="${projectAssessmentBonus.id}">
                                </form>
                                <form id="frmBonusItem">
                                    <table class="table table-bordered" id="tbAssessmentBonusList"></table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<%--历史列表窗口--%>
<div id="assementBonusItemHistoryListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">历史记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tbAssessmentBonusHistoryList"></table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        loadAssementBonusItemList();
    })

    //加载数据列表
    function loadAssementBonusItemList() {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '20%'});
        cols.push({field: 'totalScore', title: '总得分', width: '10%'});
        cols.push({
            field: 'status', title: '状态', width: '10%', formatter: function (value, row, index) {
                var str = "";
                switch (value) {
                    case "runing": {
                        str = "<span class='label label-info'>进行中</span>";
                        break;
                    }
                    case "finish": {
                        str = "<span class='label label-success'>已完成</span>";
                        break;
                    }
                    case "close": {
                        str = "<span class='label label-warning'>关闭</span>";
                        break;
                    }
                    case "wait": {
                        str = "<span class='label label-default'>待提交</span>";
                        break;
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'memberScoreCondition', title: '成员得分', width: '30%', formatter: function (value, row, index) {
                var str = '<input type="hidden" data-name="id" value="' + row.id + '"> ';
                if (value) {
                    var array = JSON.parse(value);
                    $.each(array, function (i, item) {
                        str += '<div class="form-group memberScoreCondition"><div class="input-group mb-3">';
                        str += '<input type="hidden" data-name="key" value="' + item.key + '"> ';
                        str += '<input type="hidden" data-name="explain" value="' + item.explain + '"> ';
                        str += '<div class="input-group-prepend"><span class="input-group-text">' + item.explain + '</span></div>';
                        str += '<input type="text" class="form-control" required  data-name="value" readonly="readonly" name="value' + row.id + '" value="' + item.value + '"> ';
                        str += '</div></div>';
                    })
                }
                return str;
            }
        });
        cols.push({field: 'remark', title: '说明', width: '50%'});
        cols.push({
            field: 'remark', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="showAssementBonusItemHistoryList(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-info btn-xs tooltips"  data-placement="bottom" data-original-title="历史数据">';
                str += '<i class="fa fa-history"></i>';
                str += '</button>';
                return str;
            }
        });
        var selectObj = formSerializeArray($("#frmQuery"));
        $("#tbAssessmentBonusList").bootstrapTable('destroy');
        TableInit("tbAssessmentBonusList", "${pageContext.request.contextPath}/projectAssessmentBonus/getAssessmentBonusItems", cols, selectObj, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //显示历史数据
    function showAssementBonusItemHistoryList(itemId) {
        loadAssementBonusItemHistoryList(itemId);
        $('#assementBonusItemHistoryListModal').modal();
    }

    //加载数据列表
    function loadAssementBonusItemHistoryList(itemId) {
        var cols = [];
        cols.push({field: 'examineUserName', title: '考核人', width: '10%'});
        cols.push({field: 'totalScore', title: '总得分', width: '10%'});
        cols.push({
            field: 'memberScoreCondition', title: '成员得分', width: '30%', formatter: function (value, row, index) {
                var str = '<input type="hidden" data-name="id" value="' + row.id + '"> ';
                if (value) {
                    var array = JSON.parse(value);
                    $.each(array, function (i, item) {
                        str += '<div class="form-group memberScoreCondition"><div class="input-group mb-3">';
                        str += '<div class="input-group-prepend"><span class="input-group-text">' + item.explain + '</span></div>';
                        str += '<input type="text" class="form-control" readonly="readonly"  data-name="value" name="value' + row.id + '" value="' + item.value + '"> ';
                        str += '</div></div>';
                    })
                }
                return str;
            }
        });
        cols.push({field: 'remark', title: '说明', width: '50%'});
        var selectObj = {itemId: itemId};
        $("#tbAssessmentBonusHistoryList").bootstrapTable('destroy');
        TableInit("tbAssessmentBonusHistoryList", "${pageContext.request.contextPath}/projectAssessmentBonus/getAssessmentBonusItemHistorys", cols, selectObj, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectAssessmentBonus/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }
</script>