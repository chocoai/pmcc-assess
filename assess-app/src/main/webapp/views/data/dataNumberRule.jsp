<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                    报告类型
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="queryReportType" name="queryReportType">
                                        <option value="">-请选择-</option>
                                        <c:forEach var="items" items="${reportTypeList}">
                                            <option value="${items.id}">${items.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success" onclick="addData()"
                                        data-toggle="modal" href="#divBox"> 新增
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
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">文号规则管理</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        报告类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <select class="form-control" required id="reportType" name="reportType">
                                            <option value="">-请选择-</option>
                                            <c:forEach var="items" items="${reportTypeList}">
                                                <option value="${items.id}">${items.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        前缀<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="text" required data-rule-maxlength="50" placeholder="前缀"
                                               id="prefix" name="prefix" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        文号规则<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="text" required placeholder="文号规则"
                                                name="numberRule" value="" class="form-control">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        位数<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="text" data-rule-digits="true" placeholder="位数"
                                               id="figures" name="figures" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        起始年份
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="text" data-rule-digits="true" placeholder="起始年份"
                                               id="startYear" name="startYear" class="form-control">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        起始编号
                                    </label>
                                    <div class="col-sm-4">
                                        <input type="text" data-rule-digits="true" placeholder="起始编号"
                                               id="startNumber" name="startNumber" class="form-control">
                                    </div>
                                </div>

                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                       文号分组
                                    </label>
                                    <div class="col-sm-4">
                                        <select class="form-control"  name="groupName">
                                            <option value="">-请选择-</option>
                                            <option value="result">结果报告文号</option>
                                            <option value="preaudit">预评报告文号</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })
    //加载 文号规则 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'reportTypeName', title: '报告类型'});
        cols.push({field: 'prefix', title: '前缀'});
        cols.push({field: 'numberRule', title: '文号规则'});
        cols.push({field: 'figures', title: '位数'});
        cols.push({field: 'startYear', title: '起始年份'});
        cols.push({field: 'startNumber', title: '起始编号'});
        cols.push({
            field: 'groupName', title: '文号分组', formatter: function (value, row, index) {
                if(row.groupName=='result'){
                    return '结果报告文号';
                }else if(row.groupName=='preaudit'){
                    return '预评报告文号';
                }

            }
        });

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editHrProfessional(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/numberRule/list", cols, {
            assessClass: $("#queryAssessClass").val(),
            reportType: $("#queryReportType").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //删除 文号规则数据
    function delData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/numberRule/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
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

    //对新增 文号规则数据处理
    function addData() {
        $("#frm").clearAll().find('[name=numberRule]').val('{prefix}（{year}）{number}号');
    }
    //新增 文号规则数据
    function saveData() {
        if ($("#frm").valid()) {
            var data = formParams("frm");
            $.ajax({
                url: "${pageContext.request.contextPath}/numberRule/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadDataDicList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }


    function editHrProfessional(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll().initForm(row);
        $('#divBox').modal();
    }
</script>


</html>

