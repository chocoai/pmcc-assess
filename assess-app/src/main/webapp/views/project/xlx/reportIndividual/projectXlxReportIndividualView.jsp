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
                                <form id="query_form" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-2 p-0">
                                            <input name="projectName"
                                                   class="form-control input-full" placeholder="项目名称"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">文号</label>
                                        <div class="col-md-2 p-0">
                                            <input name="numberValue" class="form-control input-full"
                                                   placeholder="文号"/>
                                        </div>
                                        <div class="col-md-2 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                        </div>
                                    </div>

                                </form>

                                <table class="table table-bordered" id="tb_List">
                                    <!-- cerare document add ajax data-->
                                </table>

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

    $(function () {
        loadDataDicList();
    });

    //加载代理数据列表
    function loadDataDicList() {
        var data = formParams("query_form");
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '5%'});
        cols.push({field: 'entrustedUnit', title: '委托单位', width: '5%'});
        cols.push({field: 'reportScore', title: '报告份数', width: '5%'});
        cols.push({
            field: 'reportModificationDate', title: '报告修改日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.reportModificationDate, true);
            }
        });
        cols.push({
            field: 'reportBindingDate', title: '报告装订日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.reportBindingDate, true);
            }
        });
        cols.push({
            field: 'filingDate', title: '归档日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.filingDate, true);
            }
        });
        cols.push({field: 'reportPeople', title: '报告人', width: '5%'});
        cols.push({field: 'numberValue', title: '文号', width: '5%'});
        cols.push({field: 'assessTotalToll', title: '收费', width: '5%'});
        var target = $("#tb_List");
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/projectXlxReportIndividual/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }


</script>
</html>
