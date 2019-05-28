<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
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
                            <label class="col-sm-1 control-label">
                                楼盘名称
                            </label>
                            <div class="col-sm-3">
                                <input type="text" id="queryLpName" placeholder="楼盘名称" class="form-control">
                            </div>
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-3">
                                <input type="hidden" id="queryExecutor">
                                <input type="text" readonly="readonly" id="queryExecutorName"
                                       placeholder="查询人员" class="form-control"
                                       onclick="taskCaseAssign.prototype.querySelectEmployee();">
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="taskCaseAssign.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="taskCaseAssign.prototype.showModel()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
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
        taskCaseAssign.prototype.loadDataDicList();
    });
    var taskCaseAssign = function () {

    };
    taskCaseAssign.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'lpmc', title: '楼盘名称'});
            cols.push({field: 'executorName', title: "认领人"});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick=""><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + taskCaseAssign.prototype.config().table).bootstrapTable('destroy');
            TableInit(taskCaseAssign.prototype.config().table, "${pageContext.request.contextPath}/taskCaseAssign/getTaskCaseAssignList", cols, {
                executor: $("#queryExecutor").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/taskCaseAssign/deleteTaskCaseAssignById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            taskCaseAssign.prototype.loadDataDicList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            var href = "${pageContext.request.contextPath}/taskCaseAssign/applyIndex";
            href += "?id=" + "";
            window.open(href, "");
        },
        saveData: function () {
            if (!$("#" + taskCaseAssign.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(taskCaseAssign.prototype.config().frm);
            var result = $("#tb_funiHouses").bootstrapTable('getSelections');
            var len = result.length;
            if (len == 0) {
                toastr.warning('请选择楼盘');
                return;
            }
            var selectIds = "";
            for (var i = 0; i < len; i++) {
                if (i == len - 1) {
                    selectIds += result[i].id;
                } else {
                    selectIds += result[i].id + ",";
                }
            }
            data.lpbh = selectIds;
            $.ajax({
                url: "${pageContext.request.contextPath}/taskCaseAssign/saveAndUpdateTaskCaseAssign",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + taskCaseAssign.prototype.config().box).modal('hide');
                        taskCaseAssign.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/taskCaseAssign/getTaskCaseAssignById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + taskCaseAssign.prototype.config().frm).clearAll();
                        $("#" + taskCaseAssign.prototype.config().frm).initForm(result.data);
                        $('#' + taskCaseAssign.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        //选择人员
        querySelectEmployee: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {

                        $("#queryExecutorName").val(data.name);
                        $("#queryExecutor").val(data.account);
                    }
                    else {
                        $("#queryExecutorName").val('');
                        $("#queryExecutor").val('');
                    }
                }
            });
        },
        //选择人员
        selectEmployee: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {

                        $("#executorName").val(data.name);
                        $("#executor").val(data.account);
                    }
                    else {
                        $("#executorName").val('');
                        $("#executor").val('');
                    }
                }
            });
        },
        loadHousesList: function () {
            var cols = [];
            cols.push({field: 'lpmc', title: '楼盘名称'});
            cols.push({field: 'lpdz', title: '楼盘地址'});
            cols.push({field: 'sldz', title: '销售地址'});
            cols.push({field: 'kfs', title: '开发商'});
            $("#tb_funiHouses").bootstrapTable('destroy');
            TableInit("tb_funiHouses", "${pageContext.request.contextPath}/taskCaseAssign/getHousesList", cols, {}, {
                showColumns: false,
                showRefresh: false,
                search: false,
                singleSelect: false
            }, true);
        },
    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">任务分配</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        人员选择
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="hidden" id="executor" name="executor">
                                        <input type="text" readonly="readonly" id="executorName" name="executorName"
                                               placeholder="选择人员" class="form-control" required
                                               onclick="taskCaseAssign.prototype.selectEmployee();">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered" id="tb_funiHouses">
                                            <!-- cerare document add ajax data-->
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="taskCaseAssign.prototype.saveData()">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
