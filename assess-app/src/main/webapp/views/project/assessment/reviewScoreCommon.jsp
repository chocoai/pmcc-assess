<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--复核与指导工时考核--%>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">
                    复核与指导工时考核
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-2 col-md-2">
                    <div id="navPlanList"
                         class="nav flex-column nav-pills nav-secondary nav-pills-no-bd"
                         role="tablist" aria-orientation="vertical">
                        <c:forEach items="${projectPlanList}" var="item"
                                   varStatus="status">
                            <a class="nav-link show" data-toggle="pill" href="#tabPane${item.id}"
                               role="tab"
                               onclick="reviewScore.loadProjectPhaseList($('#tbPlanPhaseList${item.id}'),${item.id})"
                               aria-controls="v-pills-home-nobd"
                               aria-selected="true">${item.planName}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-10 col-md-10">
                    <div class="tab-content" id="v-pills-without-border-tabContent">
                        <c:forEach items="${projectPlanList}" var="item" varStatus="status">
                            <div class="tab-pane fade" id="tabPane${item.id}" role="tabpanel"
                                 aria-labelledby="v-pills-messages-tab-nobd">
                                <div class="card-body">
                                    <table class="table table-bordered"
                                           id="tbPlanPhaseList${item.id}"></table>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--项目组织与实施考核--%>
<div class="col-md-12">
    <div class="card">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    项目组织与实施考核
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frmReviewScore" class="form-horizontal">
                <input type="hidden" name="id" value="${projectReviewScore.id}">
                <table class="table mt-3">
                    <thead>
                    <tr>
                        <th scope="col" width="10%">项目</th>
                        <th scope="col" width="30%">标准</th>
                        <th scope="col" width="10%">得分</th>
                        <th scope="col" width="50%">说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>合同洽谈、合同签署及管理</td>
                        <td>执行统一合同或只完成合同签署时，工时为标准分值的1/3；完成合同全过程管理为标准分值。</td>
                        <td><input type="text" data-rule-number="true" placeholder="得分"
                                   name="contractNegotiation" required
                                   class="form-control input-full"
                                   value="${empty projectReviewScore.contractNegotiation?0.3:projectReviewScore.contractNegotiation}">
                        </td>
                        <td><input type="text" placeholder="说明"
                                   name="contractNegotiationExplain"
                                   class="form-control input-full"
                                   value="${projectReviewScore.contractNegotiationExplain}"></td>
                    </tr>
                    <tr>
                        <td>客户活动、客户拜访、客户设诉、客户关怀</td>
                        <td>参与具体客户的客户活动、客户拜访、客户设诉、客户关怀，参与其中的任意一项且符合要求，得标准分值的1/4。</td>
                        <td><input type="text" data-rule-number="true" placeholder="得分"
                                   name="customerActivities" required
                                   class="form-control input-full"
                                   value="${empty projectReviewScore.customerActivities?0.2:projectReviewScore.customerActivities}">
                        </td>
                        <td><input type="text" placeholder="说明"
                                   name="customerActivitiesExplain"
                                   class="form-control input-full"
                                   value="${projectReviewScore.customerActivitiesExplain}"></td>
                    </tr>
                    <tr>
                        <td>人员分工、时间安排、费用管理</td>
                        <td>
                            人员分工、时间安排、外勤、费用管理各占标准工时分值的1/4,如果项目超时、或不符合费用管理要求，本项工时分为零；如果项目组长、或项目经理实施全部工作、助理人员仅实施项目辅助工作，如申报、清查、他权、客户客理中的合同、客户关系等，本项目不计分值。
                        </td>
                        <td><input type="text" data-rule-number="true" placeholder="得分"
                                   name="workDivision" required
                                   class="form-control input-full"
                                   value="${empty projectReviewScore.workDivision?0.4:projectReviewScore.workDivision}">
                        </td>
                        <td><input type="text" placeholder="说明"
                                   name="workDivisionExplain"
                                   class="form-control input-full"
                                   value="${projectReviewScore.workDivisionExplain}"></td>
                    </tr>
                    <tr>
                        <td>开票、收款完成且与会计核对一致</td>
                        <td>开标40%，送票或推送20%，崔收与核对40%。</td>
                        <td><input type="text" data-rule-number="true" placeholder="得分"
                                   name="invoiceCollection" required
                                   class="form-control input-full"
                                   value="${empty projectReviewScore.invoiceCollection?0.2:projectReviewScore.invoiceCollection}">
                        </td>
                        <td><input type="text" placeholder="说明"
                                   name="invoiceCollectionExplain"
                                   class="form-control input-full"
                                   value="${projectReviewScore.invoiceCollectionExplain}"></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<%--填写工时考核窗口--%>
<div id="editReviewScoreItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form id="frmReviewScoreItem" class="form-horizontal">
                    <div class="form-group form-inline">
                        <label class="col-sm-1 col-form-label">得分<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <input type="text" data-rule-number="true" placeholder="得分" name="score"
                                   class="form-control input-full">
                        </div>
                        <div class="col-sm-3">
                            <button type="button" class="btn btn-primary btn-sm"
                                    onclick="reviewScore.showPlanDetailsList();">
                                工作事项
                            </button>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-1 col-form-label">说明</label>
                        <div class="col-sm-11">
                            <textarea id="UERemark" name="remark"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="reviewScore.saveReivewScoreItem();">
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
<%@include file="/views/project/assessment/commPlanDetail.jsp" %>
<script type="text/javascript">
    var UERemark;
    $(function () {
        $('#navPlanList a:first').trigger('click');

        UERemark = UE.getEditor('UERemark', {
            toolbars: [
                ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
            ],
            zIndex: 11009,
            initialFrameWidth: '80%',
            initialFrameHeight: 220,
            elementPathEnabled: false,//是否启用元素路径，默认是true显示
            wordCount: false, //是否开启字数统计
            autoHeightEnabled: false,
            autoFloatEnabled: true
        });
    })

    var reviewScore = {};
    reviewScore.currProjectPhase = undefined;//当前工作事项

    //加载工作事项
    reviewScore.loadProjectPhaseList = function ($list, planId) {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'projectPhaseName', title: '名称', width: '10%'});
        cols.push({field: 'standard', title: '标准', width: '30%'});
        cols.push({field: 'score', title: '得分', width: '10%'});
        cols.push({
            field: 'remark', title: '说明', width: '30%', formatter: function (value, row, index) {
                if (value && value.indexOf('<img') >= 0) {
                    return '<button type="button" onclick="reviewScore.showRemarkInfo(' + row.id + ');" class="btn btn-sm btn-info">查看说明</button>';
                } else {
                    return value;
                }
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '15%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="reviewScore.showReivewScoreItemModal(' + row.projectPhaseId + ',' + planId + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="填写">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="reviewScore.showHistoryItemModal(' + row.projectPhaseId + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="历史数据">';
                str += '<i class="fa fa-history"></i>';
                str += '</button>';
                str += '<button type="button" onclick="reviewScore.showPlanDetailsList(' + row.projectPhaseId + ',' + planId + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="填写">';
                str += '<i class="fa fa-align-justify"></i>';
                str += '</button>';
                return str;
            }
        });
        $list.bootstrapTable('destroy');
        TableInit($list, "${pageContext.request.contextPath}/projectReviewScore/getReviewScoreItemVoListByPlanId", cols, {
            reviewId: '${projectReviewScore.id}',
            planId: planId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            uniqueId: 'projectPhaseId',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载工作事项
    reviewScore.showPlanDetailsList = function (projectPhaseId, planId) {
        if (projectPhaseId && planId) {
            commPlanDetail.showPlanDetailsListModal(planId, projectPhaseId);
        }else if(reviewScore.currProjectPhase){
            var planId = reviewScore.currProjectPhase.planId;
            var projectPhaseId = reviewScore.currProjectPhase.projectPhaseId;
            commPlanDetail.showPlanDetailsListModal(planId, projectPhaseId);
        }
    }

    //显示弹窗
    reviewScore.showReivewScoreItemModal = function (projectPhaseId, planId) {
        var row = $('#tbPlanPhaseList' + planId).bootstrapTable('getRowByUniqueId', projectPhaseId);
        reviewScore.currProjectPhase = row;
        $('#frmReviewScoreItem').clearAll().initForm(row);
        if (row.score == undefined || row.score == null) {
            $('#frmReviewScoreItem').find('[name=score]').val(row.standardScore);
        }
        UERemark.setContent(AssessCommon.toString(row.remark));
        $('#editReviewScoreItemModal').modal();
    }

    //保存数据
    reviewScore.saveReivewScoreItem = function () {
        if (!$('#frmReviewScoreItem').valid()) {
            return false;
        }
        var data = reviewScore.currProjectPhase;
        data.reviewId = '${projectReviewScore.id}';
        data.score = $('#frmReviewScoreItem').find('[name=score]').val();
        data.remark = UERemark.getContent();
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReviewScore/saveReviewScoreItem',
            type: 'post',
            data: {formData: JSON.stringify(data)},
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    notifySuccess('提示', '保存成功');
                    var planId = reviewScore.currProjectPhase.planId;
                    reviewScore.loadProjectPhaseList($('#tbPlanPhaseList' + planId), planId);
                    $('#editReviewScoreItemModal').modal('hide');
                } else {
                    AlertError('失败', result.errmsg);
                }
            }
        })
    }

    //显示历史数据弹窗
    reviewScore.showHistoryItemModal = function (projectPhaseId) {
        reviewScore.loadHistoryList(projectPhaseId);
        $('#historyScoreListModal').modal();
    }

    //加载历史记录
    reviewScore.loadHistoryList = function (projectPhaseId) {
        var cols = [];
        cols.push({field: 'creatorName', title: '填写人', width: '10%'});
        cols.push({
            field: 'gmtCreated', title: '填写时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({field: 'score', title: '得分', width: '10%'});
        cols.push({
            field: 'remark', title: '说明', width: '30%', formatter: function (value, row, index) {
                if (value && value.indexOf('<img') >= 0) {
                    return '<button type="button" onclick="reviewScore.showRemarkInfo(' + row.id + ');" class="btn btn-sm btn-info">查看说明</button>';
                } else {
                    return value;
                }
            }
        });
        $("#tbHistoryScoreList").bootstrapTable('destroy');
        TableInit("tbHistoryScoreList", "${pageContext.request.contextPath}/projectReviewScore/getHistroyList", cols, {
            reviewId: '${projectReviewScore.id}',
            projectPhaseId: projectPhaseId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            uniqueId: 'id',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //显示说明信息
    reviewScore.showRemarkInfo = function (id) {
        $.getJSON('${pageContext.request.contextPath}/projectReviewScore/getReviewScoreItemById', {id: id}, function (result) {
            if (result.ret) {
                layer.open({
                    type: 1,
                    area: ['80%', '70%'],
                    maxmin: true,
                    content: result.data.remark
                })
            }
        })

    }
</script>
