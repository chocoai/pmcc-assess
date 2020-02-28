<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                <div class="row">
                                    <div class="col-md-12">
                                        <button id="report-tools" style="margin-left: 5px" class="btn btn-success btn-sm" type="button" data-toggle="modal" onclick="addReport()"><span class="btn-label"><i class="fa fa-plus"></i></span>新加报表</button>
                                        <table id="report_list" class="table table-striped jambo_table bulk_action table-bordered"></table>
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

<script type="application/javascript">
    var reportDesignerObj = {
        report_list: $("#report_list"),
        report_provider: $("#report-provider"),
        reportData: [],
        renderReportTable: function (data) {
            reportDesignerObj.report_list.bootstrapTable('destroy');

            var cols=[];
            cols.push({field: 'uuid', title: 'uuid'});
            cols.push({field: 'reportName', title: '报表全名'});
            cols.push({field: 'modified', title: '更新时间', formatter: function (value, row, index) {
                    return formatDate(value, true);
                }});

            cols.push({
                title: '操作',
                formatter: function (value, row, index) {
                    <%--return str;--%>
                    var str = '<button onclick="editReport(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="reportDesignerObj.deleteReport(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                },
                // events: {
                //     'click #item_del': function (e, value, row, index) {
                //         //删除
                //         reportDesignerObj.deleteReport(row.reportName);
                //     }
                // }
            });

            TableClient(reportDesignerObj.report_list, cols, data, {
                toolbar: "#report-tools",
                pageSize: 20
            }, false);
        },


        fetchReportProvider: function () {

            $.ajax({
                url: "${pageContext.request.contextPath}/assessReport/fetchReportProvider",
                type: "get",
                dataType: "json",
                success: function (result) {

                    if (result.ret) {
                        reportDesignerObj.reportData = result.data;
                        reportDesignerObj.renderReportTable(result.data);
                    } else {
                        AlertError("获取数据失败:" + result.errmsg);
                    }
                },
                error: function (result) {

                    lertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        },

        reportProviderChange: function () {
            var provider = reportDesignerObj.report_provider.val();

            var tableData = [];
            if (reportDesignerObj.reportData && reportDesignerObj.reportData.length > 0) {
                $.each(reportDesignerObj.reportData, function (i, item) {
                    if (item.prefix === provider) {
                        tableData = item.reportFiles;
                    }
                });

                $.each(tableData, function (i, item) {
                    item.prefix = provider;
                });
                reportDesignerObj.renderReportTable(tableData);
            }
        },
        deleteReport:function (index) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                var row = $(reportDesignerObj.report_list).bootstrapTable('getData')[index];
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/assessReport/deleteReport",
                    type: "get",
                    dataType: "json",
                    data: {reportName: row.reportName},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            reportDesignerObj.report_list.bootstrapTable('refresh');
                        }
                        else {
                            AlertError("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            })
        }
    };
    function addReport() {
        var href = "${pageContext.request.contextPath}/ureport/designer";
        window.open(href, "");
    }
    function editReport(index) {
        var row = $(reportDesignerObj.report_list).bootstrapTable('getData')[index];
        var href = "${pageContext.request.contextPath}/ureport/designer?_u=";
        href += "erp:" + row.reportName;
        window.open(href, "");
    }

    // document init
    $(function () {
        reportDesignerObj.fetchReportProvider();

    });
</script>


</html>
