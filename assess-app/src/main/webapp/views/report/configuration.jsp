<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <%@include file="/views/share/navigation/systemSetup.jsp" %>
            <div class="col-xs-12  col-sm-12  col-md-10  col-lg-10 ">
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <a type="button" class="btn btn-success" target="_blank" href="${pageContext.request.contextPath}/ureport/designer"><i class="fa fa-plus-circle"></i>新加报表</a>
                        <table id="report_list" class="table table-striped jambo_table bulk_action table-bordered">

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
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
                    var str = '<div class="btn-margin">';
                    str += String.format('<a id="item_design" target="_blank" class="re btn btn-xs btn-primary" href="${pageContext.request.contextPath}/ureport/designer?_u={0}{1}"><i class="fa fa-edit"></i>设计报表</a>', 'erp:', row.reportName);
                    str += '<a id="item_del" class="btn btn-xs btn-warning" href="javascript:void(0)" >删除</a>';
                    str += '</div>';
                    return str;
                },
                events: {
                    'click #item_del': function (e, value, row, index) {
                        //删除
                        reportDesignerObj.deleteReport(row.reportName);
                    }
                }
            });

            TableClient(reportDesignerObj.report_list, cols, data, {
                toolbar: "report-tools",
                pageSize: 20
            }, false);
        },


        fetchReportProvider: function () {
            Loading.progressShow("正在加载数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/assessReport/fetchReportProvider",
                type: "get",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        reportDesignerObj.reportData = result.data;
                        reportDesignerObj.renderReportTable(result.data);
                    } else {
                        Alert("获取数据失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Loading.progressHide();
                    console.log("调用服务失败，失败原因:" + e);
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
        deleteReport:function (name) {
            Alert("确认要删除么？", 2, null, function () {
                Loading.progressShow("正在删除...");
                $.ajax({
                    url: "${pageContext.request.contextPath}/assessReport/deleteReport",
                    type: "get",
                    dataType: "json",
                    data: {reportName: name},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            reportDesignerObj.report_list.bootstrapTable('refresh');
                        }
                        else {
                            Alert("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            })
        }
    };


    // document init
    $(function () {
        reportDesignerObj.fetchReportProvider();

    });
</script>


</html>
