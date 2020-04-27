<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">审批人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" name="approver">
                                            <input type="text" readonly="readonly"
                                                   onclick="estateReport.approverSelect(this)"
                                                   placeholder="审批人" name="approverName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">申请人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" name="creator">
                                            <input type="text" readonly="readonly"
                                                   onclick="estateReport.createSelect(this)"
                                                   placeholder="申请人" name="creatorName"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">

                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input name="startDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input name="endDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                        <button class="btn btn-info  btn-sm" type="button" style="margin-left: 10px"
                                                onclick="estateReport.loadReportGroupList(formSerializeArray($('#frmQuery')));">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px" onclick="estateReport.exportData(formSerializeArray($('#frmQuery')))">
                                            <span class="btn-label">
												<i class="fa fa-cloud-download-alt"></i>
											</span>
                                            导出
                                        </button>
                                    </div>
                                </form>
                            </div>

                            <div class="card-body">
                                <ul class="nav nav-pills nav-secondary" role="tablist">
                                    <li class="nav-item submenu">
                                        <!-- 设置为默认选中 -->
                                        <a class="nav-link active show" data-toggle="pill"
                                           href="#reportBasicDataTab" role="tab" aria-selected="true">基础数据</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#reportApplyStatisticsTab" role="tab"
                                           aria-selected="false">申请人统计数据</a>
                                    </li>
                                    <li class="nav-item submenu">
                                        <a class="nav-link" data-toggle="pill"
                                           href="#reportAuditStatisticsTab" role="tab"
                                           aria-selected="false">审核人统计数据</a>
                                    </li>
                                </ul>

                                <div class="tab-content mt-2 mb-3">
                                    <div class="tab-pane fade show active" id="reportBasicDataTab" role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <form class="form-horizontal">
                                            </form>
                                        </div>
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <table class="table table-bordered"
                                                           id="baseHouseCaseSummaryTableList">
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="reportApplyStatisticsTab"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <form class="form-horizontal">
                                            </form>
                                        </div>
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <table class="table table-bordered"
                                                           id="reportApplyStatisticsTableList">
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="tab-pane fade" id="reportAuditStatisticsTab"
                                         role="tabpanel">
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <form class="form-horizontal">
                                            </form>
                                        </div>
                                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <div class="row">
                                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                                    <table class="table table-bordered"
                                                           id="reportAuditStatisticsTableList">
                                                    </table>
                                                </div>
                                            </div>
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

<script type="text/javascript">


    $(function () {
        estateReport.loadReportGroupList();
    });

    var estateReport = {};

    estateReport.approverSelect = function (_this) {
        estateReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=approver]").val(data.account);
        });
    };

    estateReport.createSelect = function (_this) {
        estateReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=creator]").val(data.account);
        });
    };

    /**
     * erp 人员选择
     */
    estateReport.selectExecuteUserAccount = function (multi, callback) {
        erpEmployee.select({
            multi: multi,
            currOrgId: '${companyId}',
            onSelected: function (data) {
                if (callback) {
                    callback(data);
                }
            }
        });
    };

    estateReport.loadReportGroupList = function (query) {
        if (query) {
            var arr = Object.keys(query);
            $.each(arr, function (i, item) {
                if (!query[item]) {
                    query[item] = undefined;
                }
            });
        } else {
            query = {};
        }
        estateReport.loadBasicHouseCaseSummaryList(query);
        estateReport.loadApplyStatisticsTableList(query);
        estateReport.loadAuditStatisticsTableList(query);
    };

    estateReport.loadBasicHouseCaseSummaryList = function (query) {
        var table = $("#baseHouseCaseSummaryTableList");
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县/区'});

        cols.push({field: 'blockName', title: '版块'});
        cols.push({field: 'street', title: '街'});
        cols.push({field: 'estateName', title: '楼盘'});
        cols.push({
            field: 'creatorName', title: '申请人', formatter: function (value, row, index) {
                if (value) {
                    return value;
                }
                return row.creator;
            }
        });
        cols.push({
            field: 'approverName', title: '审批人', formatter: function (value, row, index) {
                if (value) {
                    return value;
                }
                return row.approver;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/basicHouseCaseSummary/getBootstrapTableVo", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    estateReport.loadApplyStatisticsTableList = function (query) {
        var table = $("#reportApplyStatisticsTableList");
        var cols = [];
        cols.push({field: 'name', title: '账户名称'});
        cols.push({field: 'number', title: '统计数量'});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/basicHouseCaseSummary/findReportApplyStatisticsList", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    estateReport.loadAuditStatisticsTableList = function (query) {
        var table = $("#reportAuditStatisticsTableList");
        var cols = [];
        cols.push({field: 'name', title: '账户名称'});
        cols.push({field: 'number', title: '统计数量'});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/basicHouseCaseSummary/findReportAuditStatisticsList", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    estateReport.exportData = function (query) {
        var href = "${pageContext.request.contextPath}/basicHouseCaseSummary/reportDownloadData?" +estateReport.parseParam(query);
        window.open(href, "");
    } ;

    estateReport.parseParam = function (param) {
        var arr = [];
        var keys = Object.keys(param);
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            var value = param[key];
            if (!value) {
                // continue ;
            }
            var paramStr = key + "=" + value;
            arr.push(paramStr)
        }
        return arr.join("&");
    }


</script>


</html>
