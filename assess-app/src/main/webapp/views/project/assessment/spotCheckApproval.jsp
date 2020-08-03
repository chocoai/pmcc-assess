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
    <title>抽查考核</title>
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
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        抽查考核
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmSpotCheck" class="form-horizontal">
                                    <input type="hidden" name="id" value="${projectSpotCheck.id}">
                                    <div class="form-group form-inline">
                                        <label class="col-sm-1 col-form-label">抽查月份</label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectSpotCheck.spotMonth}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">被抽查人</label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectSpotCheck.bySpotUserName}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">标题</label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectSpotCheck.title}</label>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tbSpotCheckItemList"></table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        质量考核数据
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered" id="tbQualityList"></table>
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

<%--填写工时考核窗口--%>
<div id="editSpotCheckItemGroupModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">工时考核</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmSpotCheckItemGroup" class="form-horizontal">
                    <input type="hidden" name="itemId">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" width="20%">阶段</th>
                            <th scope="col" width="20%">得分</th>
                            <th scope="col" width="60%">说明</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="spotCheck.saveSpotCheckScore();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%--历史考核列表窗口--%>
<div id="historyScoreListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <table class="table table-bordered" id="tbHistoryScoreList"></table>
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
        spotCheck.loadSpotCheckItemList();
        spotCheck.loadQualityList();
    })

    var spotCheck = {};
    spotCheck.ueContainer = [];
    spotCheck.initUEditor = function (id, index) {
        UE.delEditor(id);
        var ueItem = UE.getEditor(id, {
            toolbars: [
                ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
            ],
            zIndex: 11009,
            initialFrameWidth: 500,
            initialFrameHeight: 120,
            elementPathEnabled: false,//是否启用元素路径，默认是true显示
            wordCount: false, //是否开启字数统计
            autoHeightEnabled: false,
            autoFloatEnabled: true
        });
        spotCheck.ueContainer[index] = ueItem;
    }
    spotCheck.loadSpotCheckItemList = function () {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '20%'});
        cols.push({
            field: 'projectSpotCheckItemScoreList', title: '内容', width: '50%', formatter: function (value, row, index) {
                var str = '';
                if (value) {
                    $.each(value, function (i, item) {
                        str += item.planName + "【" + item.score + "】";
                        str += AssessCommon.toString(item.remark) + '<br/>';
                    })
                }
                return str;
            }
        });
        cols.push({field: 'examineName', title: '考核人', width: '10%'});
        cols.push({
            field: 'examineDate', title: '考核时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.examineDate, true);
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '14%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="spotCheck.showEditScoreModal(' + row.id + ',' + row.projectId + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="填写">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="spotCheck.showHistoryScoreListModal(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="历史数据">';
                str += '<i class="fa fa-history"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbSpotCheckItemList").bootstrapTable('destroy');
        TableInit("tbSpotCheckItemList", "${pageContext.request.contextPath}/projectSpotCheck/getProjectSpotCheckItemList", cols, {
            spotId: '${projectSpotCheck.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            toolBar: '#spotCheckBar',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //显示填写工分窗口
    spotCheck.showEditScoreModal = function (itemId, projectId) {
        var modal = $('#editSpotCheckItemGroupModal');
        modal.find('tbody').empty();
        $.getJSON('${pageContext.request.contextPath}/projectSpotCheck/getSpotCheckScoreContent', {
            itemId: itemId,
            projectId: projectId
        }, function (result) {
            if (result.ret && result.data) {
                spotCheck.ueContainer = [];
                $.each(result.data, function (i, item) {
                    var html = '';
                    html += '<tr class="reviewRow"><td scope="col">' + item.planName + '<input type="hidden" name="planId" value="' + item.planId + '"><input type="hidden" name="planName" value="' + item.planName + '"></td>';
                    html += '<td scope="col"><input type="text" data-rule-number="true" required class="form-control input-full" name="score" value="' + AssessCommon.toString(item.score) + '"></td>';
                    html += '<td scope="col"><textarea type="text" id="remark' + i + '" name="remark" >' + AssessCommon.toString(item.remark) + '</textarea></td></tr>';
                    modal.find('tbody').append(html);

                })
                $.each(result.data, function (i, item) {
                    spotCheck.initUEditor("remark" + i, i);//初始化ueditor
                })
            }
        })
        $('#frmSpotCheckItemGroup').find('[name=itemId]').val(itemId);
        modal.modal();
    }

    //保存工分
    spotCheck.saveSpotCheckScore = function () {
        if (!$('#frmSpotCheckItemGroup').valid()) {
            return false;
        }
        var trs = $('#frmSpotCheckItemGroup').find('.reviewRow');
        var data = {};
        var projectSpotCheckItemScoreList = [];
        var totalScore = null;
        trs.each(function (i, item) {
            var projectSpotCheckItemScore = {};
            projectSpotCheckItemScore.planId = $(item).find('[name=planId]').val();
            projectSpotCheckItemScore.planName = $(item).find('[name=planName]').val();
            projectSpotCheckItemScore.score = $(item).find('[name=score]').val();
            projectSpotCheckItemScore.remark = spotCheck.ueContainer[i].getContent();
            if (projectSpotCheckItemScore.score) {
                totalScore += parseFloat(projectSpotCheckItemScore.score);
            }
            projectSpotCheckItemScoreList.push(projectSpotCheckItemScore);
        });
        data.itemId = $('#frmSpotCheckItemGroup').find('[name=itemId]').val();
        data.projectSpotCheckItemScoreList = projectSpotCheckItemScoreList;
        data.totalScore = totalScore;
        $.post('${pageContext.request.contextPath}/projectSpotCheck/saveSpotCheckScore', {
            formData: JSON.stringify(data)
        }, function (result) {
            if (result.ret) {
                notifySuccess('提示', '保存成功');
                spotCheck.loadSpotCheckItemList();
                $('#editSpotCheckItemGroupModal').modal('hide');
            } else {
                AlertError('失败', result.errmsg);
            }
        }, 'json');
    }

    //显示历史得分列表弹窗
    spotCheck.showHistoryScoreListModal = function (itemId) {
        spotCheck.loadHistoryScoreList(itemId);
        $('#historyScoreListModal').modal();
    }

    //加载历史工分数据
    spotCheck.loadHistoryScoreList = function (itemId) {
        var cols = [];
        cols.push({field: 'creatorName', title: '考核人', width: '10%'});
        cols.push({
            field: 'gmtCreated', title: '考核时间', width: '20%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'projectSpotCheckItemScoreList', title: '内容', width: '50%', formatter: function (value, row, index) {
                var str = '';
                if (value) {
                    $.each(value, function (i, item) {
                        str += item.planName + "【" + item.score + "】";
                        str += AssessCommon.toString(item.remark) + '<br/>';
                    })
                }
                return str;
            }
        });
        $("#tbHistoryScoreList").bootstrapTable('destroy');
        TableInit("tbHistoryScoreList", "${pageContext.request.contextPath}/projectSpotCheck/getHistoryGroupsByItemId", cols, {
            itemId: itemId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载质量考核数据
    spotCheck.loadQualityList=function(){
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '20%'});
        cols.push({field: 'planName', title: '阶段事项名称', width: '20%'});
        cols.push({field: 'businessKey', title: '名称', width: '20%'});
        cols.push({field: 'byExaminePeopleName', title: '被抽查人', width: '10%'});
        cols.push({field: 'examineScore', title: '得分', width: '10%'});
        cols.push({
            field: 'bisQualified', title: '是否合格', width: '10%', formatter: function (value, row, index) {
                if (value) {
                    return '是';
                } else {
                    return false;
                }
            }
        });
        $("#tbQualityList").bootstrapTable('destroy');
        TableInit("tbQualityList", "${pageContext.request.contextPath}/assessmentPerformance/getPerformanceListBySpotBatchId", cols, {
            spotBatchId: '${projectSpotCheck.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //提交流程
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectReviewScore/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }
</script>