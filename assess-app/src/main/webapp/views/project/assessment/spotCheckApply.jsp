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
    <title>抽查考核</title>
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
                                        项目抽查
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmSpotCheck" class="form-horizontal">
                                    <input type="hidden" name="id" value="${projectSpotCheck.id}">
                                    <div class="form-group form-inline">
                                        <label class="col-sm-1 col-form-label">抽查月份<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="50" placeholder="抽查月份"
                                                   name="spotMonth" readonly="readonly" required
                                                   class="form-control input-full date-picker dbdate-month"
                                                   data-date-format="yyyy-mm"
                                                   value="${projectSpotCheck.spotMonth}">
                                        </div>
                                        <label class="col-sm-1 col-form-label">被抽查人<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="hidden" name="bySpotUser" data-title="account"
                                                   value="${projectSpotCheck.bySpotUser}">
                                            <input type="text" data-rule-maxlength="50" placeholder="被抽查人"
                                                   name="bySpotUserName" required data-title="name"
                                                   readonly="readonly" onclick="spotCheck.selectUser(this);"
                                                   class="form-control input-full"
                                                   value="${projectSpotCheck.bySpotUserName}">
                                        </div>
                                        <label class="col-sm-1 col-form-label">标题<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="50"
                                                   onfocus="spotCheck.titleFocus(this);"
                                                   placeholder="标题" name="title" value="${projectSpotCheck.title}"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </form>
                                <p id="spotCheckBar">
                                    <button class="btn btn-success btn-sm" type="button"
                                            onclick="spotCheck.showProjectListModal();"><span class="btn-label"><i
                                            class="fa fa-plus"></i></span>
                                        选择项目
                                    </button>
                                </p>
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
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="commit_btn" style="margin-left: 10px;" class="btn btn-primary"
                                    onclick="spotCheck.applySumit();">
                                提交
                            </button>
                        </div>
                    </div>
                    <c:if test="${not empty processInsId and processInsId != 0}">
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
<%--选择项目窗口--%>
<div id="projectListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">项目列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmQuery" class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1 col-form-label">项目名称</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="项目名称" name="projectName"
                                           class="form-control input-full">
                                </div>
                                <label class="col-md-1 col-form-label">项目经理</label>
                                <div class="col-md-2 p-0">
                                    <input type="hidden" name="queryManager" data-title="account">
                                    <input type="text" readonly="readonly" onclick="spotCheck.selectUser(this);"
                                           placeholder="项目经理" name="queryManagerName"
                                           class="form-control input-full" data-title="name">
                                </div>
                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                        type="button"
                                        onclick="spotCheck.loadProjectList();">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                    查询
                                </button>
                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                        type="button"
                                        onclick="$('#frmQuery').clearAll()">
                                                    <span class="fa fa-undo-alt" aria-hidden="true"
                                                          class="-space"></span>
                                    重置
                                </button>
                            </div>
                        </div>
                    </div>
                    <div id="showOtherQuery">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-md-1 col-form-label">开始时间</label>
                                    <div class="col-md-2 p-0">
                                        <input type="text"
                                               class="form-control input-full date-picker dbdate"
                                               data-date-format="yyyy-mm-dd" name="queryTimeStart"
                                               placeholder="开始时间"/>
                                    </div>
                                    <label class="col-md-1 col-form-label">结束时间</label>
                                    <div class="col-md-2 p-0">
                                        <input type="text"
                                               class="form-control input-full date-picker dbdate"
                                               data-date-format="yyyy-mm-dd" name="queryTimeEnd"
                                               placeholder="结束时间"/>
                                    </div>
                                    <label class="col-md-1 col-form-label">状态</label>
                                    <div class="col-md-2 p-0">
                                        <select name="projectStatus" class="form-control input-full">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="item" items="${statusEnumList}">
                                                <option value="${item.key}">${item.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-md-1 col-form-label">委托目的</label>
                                    <div class="col-md-2 p-0">
                                        <select name="entrustPurpose" class="form-control input-full">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="item" items="${entrustPurposeList}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-md-1 col-form-label">报告使用单位</label>
                                    <div class="col-md-2 p-0">
                                        <input type="text"
                                               placeholder="单位" class="form-control input-full"
                                               name="queryUseUnitName">
                                    </div>
                                    <label class="col-md-1 col-form-label">评估部门</label>
                                    <div class="col-md-2 p-0">
                                        <input name="queryDepartmentId" class="form-control input-full"
                                               type="hidden"/>
                                        <input name="queryDepartmentName"
                                               class="form-control input-full"
                                               placeholder="评估部门"
                                               onclick="spotCheck.selectDepartment(this);" readonly="readonly"/>
                                    </div>
                                    <label class="col-md-1 col-form-label">贷款类型</label>
                                    <div class="col-md-2 p-0">
                                        <select name="queryLoanType" class="form-control input-full">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="item" items="${loanTypeList}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-md-1 col-form-label">项目类型</label>
                                    <div class="col-md-2 p-0">
                                        <select class="form-control input-full"
                                                name="queryProjectCategoryId">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="classItem" items="${projectCategoryList}">
                                                <c:forEach var="typeItem"
                                                           items="${classItem.keyValueDtos}">
                                                    <c:if test="${not empty typeItem.keyValueDtos}">
                                                        <c:forEach var="categoryItem"
                                                                   items="${typeItem.keyValueDtos}">
                                                            <option value="${categoryItem.key}">${categoryItem.value}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="tb_projectList"></table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="spotCheck.selectProject();">
                    选择
                </button>
            </div>
        </div>
    </div>
</div>

<%--选择要抽查的阶段--%>
<div id="selectSpotPlanModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">选择抽查阶段</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="id">
                <form id="frmSpotPlan" class="form-horizontal">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="spotCheck.saveSelectedSpotPlan();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

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
        //月份选择处理
        DatepickerUtils.initDate($('.dbdate-month'), {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true,
            format: 'yyyy-mm',
            startView: 4,
            minView: 3
        });
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
            field: 'opt', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="spotCheck.showSpotPlanModal(' + row.projectId + ',' + row.id + ');"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="选择抽查阶段">';
                str += '<i class="fa fa-object-group"></i>';
                str += '</button>';
                str += '<button type="button" onclick="spotCheck.openSpotCheckProjectUrl(' + row.projectId + ',' + row.id + ');"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="填写">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
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

    //选择人员
    spotCheck.selectUser = function (_this) {
        var div = $(_this).closest("div");
        erpEmployee.select({
            currOrgId: '${companyId}',
            showAllUser: 2,
            onSelected: function (data) {
                div.find("input[data-title='name']").val(data.name);
                div.find("input[data-title='account']").val(data.account);
            }
        });
    }

    //选择部门
    spotCheck.selectDepartment = function (this_) {
        var div = $(this_).closest("div");
        var options = {
            onSelected: function (nodes) {
                div.find("[name='queryDepartmentId']").val(nodes[0].id);
                div.find("[name='queryDepartmentName']").val(nodes[0].name);
            }
        };
        erpDepartment.select(options);
    };

    //标题文本框focus
    spotCheck.titleFocus = function (_this) {
        var form = $(_this).closest('form');
        var spotMonth = form.find('[name=spotMonth]').val();
        var name = form.find('[name=bySpotUserName]').val();
        form.find('[name=title]').val(spotMonth + name + "项目抽查考核");
    }

    //显示项目列表弹窗
    spotCheck.showProjectListModal = function () {
        spotCheck.loadProjectList();
        $('#projectListModal').modal();
    }

    //加载项目列表
    spotCheck.loadProjectList = function () {
        var selectObj = formSerializeArray($("#frmQuery"));
        var cols = [];
        cols.push({
            field: 'projectName', title: '项目名称', width: '30%', formatter: function (value, row, index) {
                var str = value;
                str += '<span style="margin-left: 2px;background-color: #868b9e;" class="label label-default">' + row.useUnitName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #9ed2a0;" class="label label-success">' + row.departmentName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #b3b0e2;" class="label label-secondary">' + row.entrustPurposeName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #accfea;" class="label label-info">' + row.loanTypeName + '</span>';
                return str;
            }
        });
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '15%', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<span class='label label-primary'>" + row.userAccountManagerName.split("_")[0] + "</span>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '类型', width: '8%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '状态', width: '10%'});
        cols.push({
            field: 'finishPre', title: '项目进度', width: '15%', formatter: function (value, row, index) {
                var s = "<div class='progress progress-sm' style='margin-bottom: 0px;'>";
                s += "<div class='progress-bar bg-success' role='progressbar'  style='width: " + value + "%;'></div>";
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '立项时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'id', title: '操作', width: '7%', formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="checkDetail(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tb_projectList").bootstrapTable('destroy');
        TableInit("tb_projectList", "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, selectObj, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        }, true);
    }

    //选择项目
    spotCheck.selectProject = function () {
        var projectIds = [];
        var $table = $("#tb_projectList")
        var rows = $table.bootstrapTable('getSelections');
        $.each(rows, function (i, item) {
            projectIds.push(item.id);
        })
        $.post('${pageContext.request.contextPath}/projectSpotCheck/selectProject', {
            projectIds: projectIds.join(','),
            spotId: $('#frmSpotCheck').find('[name=id]').val()
        }, function (result) {
            if (result.ret) {
                notifySuccess('提示', '选择成功');
                $table.bootstrapTable('uncheckAll');
                spotCheck.loadSpotCheckItemList();
            } else {
                AlertError('错误', result.errmsg);
            }
        }, 'json');
    }

    //加载质量考核数据
    spotCheck.loadQualityList = function () {
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

    //显示抽查阶段窗口
    spotCheck.showSpotPlanModal = function (projectId, itemId) {
        $('#frmSpotPlan').empty();
        $('#selectSpotPlanModal').find('[name=id]').val(itemId);
        $.getJSON('${pageContext.request.contextPath}/projectSpotCheck/getProjectPlanListByProjectId', {
            projectId: projectId,
            itemId: itemId
        }, function (result) {
            if (result.ret && result.data) {
                $.each(result.data, function (i, item) {
                    var html = '<div class="form-check">';
                    html += '<label class="form-check-label">';
                    html += '<input class="form-check-input" type="checkbox"';
                    if (item.checked) {
                        html += ' checked="checked" ';
                    }
                    html += ' value="' + item.id + '">';
                    html += '<span class="form-check-sign">' + item.planName + '</span>';
                    html += '</label></div>';
                    $('#frmSpotPlan').append(html);
                })
            }
        })
        $('#selectSpotPlanModal').modal();
    }

    //保存已选的抽查阶段
    spotCheck.saveSelectedSpotPlan = function () {
        var planIdArray = [];
        $('#frmSpotPlan').find(':checkbox:checked').each(function () {
            planIdArray.push($(this).val());
        });
        var data = {};
        data.id = $('#selectSpotPlanModal').find('[name=id]').val();
        data.planId = planIdArray.join();
        $.ajax({
            url: '${pageContext.request.contextPath}/projectSpotCheck/saveSpotCheckItem',
            type: 'post',
            data: {formData: JSON.stringify(data)},
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    notifySuccess('提示', '选择成功');
                    spotCheck.loadSpotCheckItemList();
                    $('#selectSpotPlanModal').modal('hide');
                } else {
                    AlertError('错误', result.errmsg);
                }
            }
        })
    }

    //项目抽查工分填写
    spotCheck.openSpotCheckProjectUrl = function (projectId, itemId) {
        layer.open({
            type: 2,
            area: ['97%', '97%'],
            fixed: false, //不固定
            maxmin: true,
            content: '${pageContext.request.contextPath}/projectSpotCheck/projectSpotModify?projectId=' + projectId + '&itemId=' + itemId
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

    //提交流程
    spotCheck.applySumit = function () {
        if (!$('#frmSpotCheck').valid()) {
            return false;
        }
        var data = formSerializeArray($('#frmSpotCheck'));
        if ('${processInsId}' == '' || '${processInsId}' == '0') {
            $.post('${pageContext.request.contextPath}/projectSpotCheck/applyCommit', {
                formData: JSON.stringify(data)
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
            var jsonData = formSerializeArray($("#process_variable_form"));
            jsonData.formData = JSON.stringify(data);
            $.post('${pageContext.request.contextPath}/projectSpotCheck/editCommit', jsonData, function (result) {
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
