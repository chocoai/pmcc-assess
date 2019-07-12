<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
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
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryTitle" name="queryTitle"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataBuilder.prototype.loadDataDicList()">
                                    查询
                                </button>

                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="transaction_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataBuilder.prototype.loadDataDicList();
    });
    var dataBuilder = function () {

    };
    dataBuilder.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'title', title: '名称'});
            cols.push({field: 'type', title: '类型'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({field: 'consultPrice', title: '估算价'});
            cols.push({field: 'initPrice', title: '起始价'});
            cols.push({
                field: 'end', title: '结束时间', formatter: function (value, row, index) {
                    return formatDate(row.end, false);
                }
            });
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看详情" onclick="dataBuilder.prototype.openItem('  + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataBuilder.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataBuilder.prototype.config().table, "${pageContext.request.contextPath}/netAuctionInfoController/getAuctionInfoList", cols, {
                queryTitle: $("#queryTitle").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        openItem:function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            console.log(row);
            if(row.itemUrl) {
                window.open(row.itemUrl, "");
            }
        }
    }

</script>


</html>
