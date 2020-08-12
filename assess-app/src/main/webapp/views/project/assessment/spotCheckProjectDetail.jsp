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
    <title>${projectInfo.projectName}</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/common_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
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
                                                   onclick="spotCheckProject.loadProjectPhaseList($('#tbPlanPhaseList${item.id}'),${item.id})"
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
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>

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

    var spotCheckProject = {};
    spotCheckProject.currProjectPhase = undefined;//当前工作事项

    //加载工作事项
    spotCheckProject.loadProjectPhaseList = function ($list, planId) {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'projectPhaseName', title: '名称', width: '10%'});
        cols.push({field: 'standard', title: '标准', width: '30%'});
        cols.push({field: 'score', title: '得分', width: '10%'});
        cols.push({
            field: 'remark', title: '说明', width: '30%', formatter: function (value, row, index) {
                if (value && value.indexOf('<img') >= 0) {
                    return '<button type="button" onclick="spotCheckProject.showRemarkInfo(' + row.id + ');" class="btn btn-sm btn-info">查看说明</button>';
                } else {
                    return value;
                }
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '15%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="spotCheckProject.showHistoryItemModal(' + row.projectPhaseId + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="历史数据">';
                str += '<i class="fa fa-history"></i>';
                str += '</button>';
                str += '<button type="button" onclick="spotCheckProject.showPlanDetailsList(' + row.projectPhaseId + ',' + planId + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="填写">';
                str += '<i class="fa fa-align-justify"></i>';
                str += '</button>';
                return str;
            }
        });
        $list.bootstrapTable('destroy');
        TableInit($list, "${pageContext.request.contextPath}/projectSpotCheck/getSpotCheckItemScoreVoListByPlanId", cols, {
            itemId: '${projectSpotCheckItem.id}',
            planId: planId
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

    //加载工作事项
    spotCheckProject.showPlanDetailsList = function (projectPhaseId, planId) {
        if (projectPhaseId && planId) {
            commPlanDetail.showPlanDetailsListModal(planId, projectPhaseId);
        }else if(spotCheckProject.currProjectPhase){
            var planId = spotCheckProject.currProjectPhase.planId;
            var projectPhaseId = spotCheckProject.currProjectPhase.projectPhaseId;
            commPlanDetail.showPlanDetailsListModal(planId, projectPhaseId);
        }
    }

    //显示历史数据弹窗
    spotCheckProject.showHistoryItemModal = function (projectPhaseId) {
        spotCheckProject.loadHistoryList(projectPhaseId);
        $('#historyScoreListModal').modal();
    }

    //加载历史记录
    spotCheckProject.loadHistoryList = function (projectPhaseId) {
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
                    return '<button type="button" onclick="spotCheckProject.showRemarkInfo(' + row.id + ');" class="btn btn-sm btn-info">查看说明</button>';
                } else {
                    return value;
                }
            }
        });
        $("#tbHistoryScoreList").bootstrapTable('destroy');
        TableInit("tbHistoryScoreList", "${pageContext.request.contextPath}/projectSpotCheck/getHistroyList", cols, {
            spotId: '${projectSpotCheckItem.id}',
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
    spotCheckProject.showRemarkInfo = function (id) {
        $.getJSON('${pageContext.request.contextPath}/projectSpotCheck/getSpotCheckItemScoreById', {id: id}, function (result) {
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
