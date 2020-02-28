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
                                    内容
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="内容" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataBuilder.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="updateLandTransaction()"> 今日信息
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
<%--结果公告窗口--%>
<div id="resultListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="300">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">结果公告</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <table id="result_List"
                           class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<%--挂告窗口--%>
<div id="hangTagListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="300">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">挂牌公告</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <table id="hang_tag_List"
                           class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<%--拍告窗口--%>
<div id="auctionListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="300">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">拍卖公告</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <table id="auction_List"
                           class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<%--土地预公告窗口--%>
<div id="beforehandListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="300">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地预公告</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <table id="beforehand_List"
                           class="table table-striped jambo_table bulk_action table-bordered"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
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
            cols.push({field: 'address', title: '地点'});
            cols.push({field: 'content', title: '内容'});
            cols.push({
                field: 'publishtime', title: '发表时间', formatter: function (value, row, index) {
                    return formatDate(row.publishtime, false);
                }
            });
            cols.push({field: 'status', title: '状态'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="openBox(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="详情" onclick="openItemUrl(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataBuilder.prototype.config().tabletable).bootstrapTable('destroy');
            TableInit(dataBuilder.prototype.config().table, "${pageContext.request.contextPath}/netLandTransaction/getLandTransactionList", cols, {
                content: $("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    }


    //打开列表窗口
    function openBox(index) {
        var row = $("#transaction_List").bootstrapTable('getData')[index];
        if (row.content.indexOf('结果一览表') != -1) {
            getResultList(row.id);
            $("#resultListBox").modal();
        }
        if (row.content.indexOf('雅公资') != -1) {
            getYGZResultList(row.id);
            $("#resultListBox").modal();
        }
        if (row.content.indexOf('挂告') != -1) {
            getHangTagList(row.id);
            $("#hangTagListBox").modal();
        }
        if (row.content.indexOf('拍告') != -1 && row.content.indexOf('结果一览表') == -1) {
            getAuctionList(row.id);
            $("#auctionListBox").modal();
        }
        if (row.content.indexOf('预公告') != -1) {
            getBeforehandList(row.id);
            $("#beforehandListBox").modal();
        }
    }

    //结果公告
    function getResultList(id) {
        var cols = [];
        cols.push({field: 'zdbh', title: '宗地编号'});
        cols.push({field: 'zdwz', title: '宗地位置'});
        cols.push({field: 'jydmj', title: '净用地面积（平方米）'});
        cols.push({field: 'qsj', title: '起始价'});
        cols.push({field: 'ccj', title: '成交价'});
        cols.push({field: 'jdr', title: '竞得人'});

        $("#result_List").bootstrapTable('destroy');
        TableInit("result_List", "${pageContext.request.contextPath}/netLandTransaction/getResultList", cols, {
            mainId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //雅公资结果公告
    function getYGZResultList(id) {
        var cols = [];
        cols.push({field: 'bdmc', title: '标的名称'});
        cols.push({field: 'zdwz', title: '地块位置'});
        cols.push({field: 'jydmj', title: '土地面积'});
        cols.push({field: 'tdyt', title: '土地用途'});
        cols.push({field: 'crfs', title: '出让方式'});
        cols.push({field: 'rjl', title: '容积率'});
        cols.push({field: 'qsj', title: '起始价'});
        cols.push({field: 'ccj', title: '成交价'});
        cols.push({field: 'jdr', title: '竞得人'});

        $("#result_List").bootstrapTable('destroy');
        TableInit("result_List", "${pageContext.request.contextPath}/netLandTransaction/getResultList", cols, {
            mainId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //挂告
    function getHangTagList(id) {
        var cols = [];
        cols.push({field: 'zdbh', title: '宗地编号'});
        cols.push({field: 'zdwz', title: '宗地位置'});
        cols.push({field: 'jydmj', title: '净用地面积（平方米）'});
        cols.push({field: 'tdytjsyqx', title: '土地用途及使用年限'});
        cols.push({field: 'qpjj', title: '拍卖起叫价'});
        cols.push({field: 'jmbxj', title: '竞买保证金（万元）'});
        cols.push({field: 'pmcrsj', title: '拍卖出让时间'});
        cols.push({field: 'rjl', title: '容积率'});
        cols.push({field: 'jzmd', title: '建筑密度'});
        cols.push({field: 'jzgd', title: '建筑高度'});
        cols.push({field: 'crr', title: '出让人'});

        $("#hang_tag_List").bootstrapTable('destroy');
        TableInit("hang_tag_List", "${pageContext.request.contextPath}/netLandTransaction/getHangTagList", cols, {
            mainId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //拍告
    function getAuctionList(id) {
        var cols = [];
        cols.push({field: 'zdbh', title: '宗地编号'});
        cols.push({field: 'zdwz', title: '宗地位置'});
        cols.push({field: 'jydmj', title: '净用地面积（平方米）'});
        cols.push({field: 'tdytjsyqx', title: '土地用途及使用年限'});
        cols.push({field: 'qpjj', title: '拍卖起叫价'});
        cols.push({field: 'jmbxj', title: '竞买保证金（万元）'});
        cols.push({field: 'pmcrsj', title: '拍卖出让时间'});
        cols.push({field: 'rjl', title: '容积率'});
        cols.push({field: 'jzmd', title: '建筑密度'});
        cols.push({field: 'jzgd', title: '建筑高度'});
        cols.push({field: 'crr', title: '出让人'});

        $("#auction_List").bootstrapTable('destroy');
        TableInit("auction_List", "${pageContext.request.contextPath}/netLandTransaction/getAuctionList", cols, {
            mainId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //土地预公告
    function getBeforehandList(id) {
        var cols = [];
        cols.push({field: 'dkwz', title: '地块位置'});
        cols.push({field: 'jydmj', title: '净用地面积(亩)'});
        cols.push({field: 'rjl', title: '计入容积率总建筑面积/容积率'});
        cols.push({field: 'jzmd', title: '建筑密度'});
        cols.push({field: 'jzgd', title: '建筑高度'});
        cols.push({field: 'ldl', title: '绿地率'});
        cols.push({field: 'ydxz', title: '用地性质'});
        cols.push({field: 'yjsssj', title: '预计上市时间'});

        $("#beforehand_List").bootstrapTable('destroy');
        TableInit("beforehand_List", "${pageContext.request.contextPath}/netLandTransaction/getBeforehandList", cols, {
            mainId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

   function  openItemUrl(index) {
        var row = $("#transaction_List").bootstrapTable('getData')[index];
        if(row.detailLink) {
            window.open(row.detailLink, "");
        }
    }
    //保存数据
    function updateLandTransaction() {
        AlertConfirm("确认要现在更新么？", "", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/netLandTransaction/updateLandTransaction",
                type: "post",
                dataType: "json",
                data: {
                    page: 1
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','保存成功');
                        dataBuilder.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        });
    }
</script>


</html>
