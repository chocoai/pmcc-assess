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
                                                   onclick="landReport.approverSelect(this)"
                                                   placeholder="审批人" name="approverName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">申请人</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" name="creator">
                                            <input type="text" readonly="readonly"
                                                   onclick="landReport.createSelect(this)"
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
                                                onclick="landReport.loadReportGroupList(formSerializeArray($('#frmQuery')));">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px" onclick="landReport.exportData(formSerializeArray($('#frmQuery')))">
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
        landReport.loadReportGroupList();
    });

    var landReport = {};

    landReport.approverSelect = function (_this) {
        landReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=approver]").val(data.account);
        });
    };

    landReport.createSelect = function (_this) {
        landReport.selectExecuteUserAccount(false, function (data) {
            $(_this).val(data.name);
            $(_this).closest(".form-group").find("[name=creator]").val(data.account);
        });
    };

    /**
     * erp 人员选择
     */
    landReport.selectExecuteUserAccount = function (multi, callback) {
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

    landReport.loadReportGroupList = function (query) {
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
        landReport.loadBasicHouseCaseSummaryList(query);
        landReport.loadApplyStatisticsTableList(query);
        landReport.loadAuditStatisticsTableList(query);
    };

    landReport.loadBasicHouseCaseSummaryList = function (query) {
        var table = $("#baseHouseCaseSummaryTableList");
        var cols = [];
        cols.push({
            field: 'parcelSite', title: '宗地位置', formatter: function (value, row, index) {
                var s = '';
                if (value) {
                    s += value;
                }
                if (row.creatorName) {
                    s += "<span style='margin-left: 5px;' class='label label-info'>" + row.creatorName + "</span>"
                }
                if (row.approverName) {
                    s += "<span style='margin-left: 5px;' class='label label-danger'>" + row.approverName + "</span>"
                }
                return s;
            }
        });
        cols.push({field: 'belongType', title: '土地类型'});
        cols.push({field: 'belongCategory', title: '土地类别'});
        cols.push({field: 'dealTypeName', title: '交易方式'});

        cols.push({
            field: 'negotiatedDate', title: '交易时间', formatter: function (value, row, index) {
                return formatDate(row.negotiatedDate, false);
            }
        });
        cols.push({field: 'landAreaCentiare', title: '净用地面积(㎡)'});
        cols.push({field: 'landAreaMu', title: '净用地面积(亩)'});
        cols.push({field: 'unitPrice', title: '成交单价（元/㎡）'});
        cols.push({field: 'unitPriceMu', title: '成交单价（元/亩）'});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/netInfoRecordLand/getNetInfoRecordLandVoList", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    landReport.loadApplyStatisticsTableList = function (query) {
        var table = $("#reportApplyStatisticsTableList");
        var cols = [];
        cols.push({field: 'name', title: '账户名称'});
        cols.push({field: 'number', title: '统计数量'});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/netInfoRecordLand/findLandReportApplyStatistics", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    landReport.loadAuditStatisticsTableList = function (query) {
        var table = $("#reportAuditStatisticsTableList");
        var cols = [];
        cols.push({field: 'name', title: '账户名称'});
        cols.push({field: 'number', title: '统计数量'});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/netInfoRecordLand/findLandReportAuditStatistics", cols, query, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    landReport.exportData = function (query) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicHouseCaseSummary/reportDownload",
            type: "get",
            dataType: "json",
            data: query,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    FileUtils.downAttachments(result.data) ;
                    FileUtils.deleteFile({attachmentId:result.data}) ;
                } else {
                    AlertError("失败", result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        }) ;
        <%--var href = "${pageContext.request.contextPath}/basicHouseCaseSummary/reportDownloadData?" +landReport.parseParam(query);--%>
        <%--window.open(href, "");--%>
    } ;

    landReport.parseParam = function (param) {
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
