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
                            <div class="card-header collapse-link">
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
                                        <button class="btn  btn-link btn-primary btn-xs"><span
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

<script type="text/javascript">
    $(function () {
        spotCheck.loadSpotCheckItemList();
        spotCheck.loadQualityList();
    })

    var spotCheck = {};

    spotCheck.loadSpotCheckItemList = function () {
        var cols = [];
        cols.push({
            field: 'projectName', title: '项目名称', width: '20%', formatter: function (value, row, index) {
                return '<a href="${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=' + row.projectId + '" target="_blank">' + value + '</a>';
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '14%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="spotCheck.openSpotCheckProjectDetailUrl(' + row.projectId + ',' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="历史数据">';
                str += '<i class="fa fa-search"></i>';
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

    //项目抽查工分详情
    spotCheck.openSpotCheckProjectDetailUrl = function (projectId, itemId) {
        layer.open({
            type: 2,
            area: ['97%', '97%'],
            fixed: false, //不固定
            maxmin: true,
            content: '${pageContext.request.contextPath}/projectSpotCheck/projectSpotDetail?projectId=' + projectId + '&itemId=' + itemId
        });
    }


    //加载质量考核数据
    spotCheck.loadQualityList = function () {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '20%'});
        cols.push({field: 'planName', title: '阶段事项名称', width: '20%'});
        cols.push({field: 'businessKey', title: '名称', width: '20%'});
        cols.push({field: 'byExaminePeopleName', title: '被抽查人', width: '10%'});
        cols.push({
            field: 'examineScore', title: '得分/标准分', width: '10%', formatter: function (value, row, index) {
                return value+"/"+row.standardScore;
            }
        });
        cols.push({
            field: 'ratio', title: '得分率', width: '10%', formatter: function (value, row, index) {
                if(row.examineScore&&row.standardScore){
                    return ((row.examineScore/row.standardScore)*100).toFixed(2)+"%";
                }
            }
        });
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
</script>