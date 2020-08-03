<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>外勤考核</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">
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
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="commit_btn" class="btn btn-primary" onclick="applySumit();">
                                提交
                            </button>
                        </div>
                    </div>
                    <c:if test="${processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
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
        cols.push({
            field: 'totalScore', title: '总得分', width: '10%', formatter: function (value, row, index) {
                var str = '<input class="form-control" data-name="totalScore" required name="totalScore' + row.id + '" value="' + value + '"></input>';
                return str;
            }
        });
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
                        str += '<input type="text" class="form-control" required  data-name="value" name="value' + row.id + '" value="' + item.value + '"> ';
                        str += '</div></div>';
                    })
                }
                return str;
            }
        });
        cols.push({
            field: 'remark', title: '说明', width: '50%', formatter: function (value, row, index) {
                var str = '<textarea class="form-control remark" data-name="remark" required name="remark' + row.id + '">' + AssessCommon.toString(row.remark) + '</textarea>';
                return str;
            }
        });
        cols.push({
            field: 'remark', title: '操作', width: '6%', formatter: function (value, row, index) {
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
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    function applySumit() {
        if (!$('#frmBonusItem').valid()) {
            return false;
        }
        var bonusItemArray = [];
        $('#tbAssessmentBonusList').find('tbody tr').each(function () {
            var bonusItem = {};
            bonusItem.id = $(this).find('[data-name=id]').val();
            bonusItem.totalScore = $(this).find('[data-name=totalScore]').val();
            bonusItem.remark = $(this).find('[data-name=remark]').val();
            var keyValueArray = [];
            $(this).find('.memberScoreCondition').each(function (i, item) {
                var keyValue = {};
                keyValue.key = $(item).find('[data-name=key]').val();
                keyValue.value = $(item).find('[data-name=value]').val();
                keyValue.explain = $(item).find('[data-name=explain]').val();
                keyValueArray.push(keyValue);
            })
            bonusItem.memberScoreCondition = JSON.stringify(keyValueArray);
            bonusItemArray.push(bonusItem);
        })

        if ('${processInsId}' == '' || '${processInsId}' == '0') {
            $.post('${pageContext.request.contextPath}/projectAssessmentBonus/applyCommit', {
                formData: JSON.stringify(bonusItemArray),
                responsibilityId: '${responsibilityId}',
                bonusId: '${projectAssessmentBonus.id}'
            }, function (result) {
                if (result.ret) {
                    AlertSuccess('成功', '提交成功', function () {
                        window.close();
                    })
                } else {
                    AlertError('失败', result.errmsg);
                }
            }, 'json');
        } else {
            var data = formSerializeArray($("#process_variable_form"));
            data.bonusId = '${projectAssessmentBonus.id}';
            data.formData = JSON.stringify(bonusItemArray);
            $.post('${pageContext.request.contextPath}/projectAssessmentBonus/editCommit', data, function (result) {
                if (result.ret) {
                    AlertSuccess('成功', '提交成功', function () {
                        window.close();
                    })
                } else {
                    AlertError('失败', result.errmsg);
                }
            }, 'json');
        }
    }
</script>
