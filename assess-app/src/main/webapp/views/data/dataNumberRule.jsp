<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>文号规则</title>
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
                                <div class="row">
                                    <div class="col-md-2">
                                        <%@include file="/views/share/navigation/reportSetup.jsp" %>
                                    </div>
                                    <div class="col-md-10">
                                        <form id="frmQuery" class="form-horizontal">
                                            <div class="form-group form-inline">
                                                <label class="col-md-1 col-form-label">报告类型</label>
                                                <div class="col-md-3 p-0">
                                                    <select class="form-control input-full" id="queryReportType" name="queryReportType">
                                                        <option value="">-请选择-</option>
                                                        <c:forEach var="items" items="${reportTypeList}">
                                                            <option value="${items.id}">${items.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                        onclick="loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                        data-toggle="modal" onclick="addData()"
                                                        href="#divBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                    新增
                                                </button>
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
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->

</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">文号规则管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full" required id="assessProjectType" name="assessProjectType">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach var="items" items="${assessProjectTypeList}">
                                                        <option value="${items.key}">${items.explain}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                报告类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full" required id="reportType" name="reportType">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach var="items" items="${reportTypeList}">
                                                        <option value="${items.id}">${items.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                文号规则
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="hidden" name="erpNumberRule" >
                                                <select class="form-control input-full"  name="erpRuleId" onchange="numberRuleChange(this);">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${sysSymbolRuleList}" var="item">
                                                        <option value="${item.id}">${item.numberRule}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })

    function numberRuleChange(_this) {
        $(_this).closest('div').find('[name=erpNumberRule]').val($(_this).find("option:selected").text());
    }

    //加载 文号规则 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'assessProjectTypeName', title: '项目类型'});
        cols.push({field: 'reportTypeName', title: '报告类型'});
        cols.push({field: 'erpNumberRule', title: '文号规则'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editHrProfessional(' + index + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/numberRule/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        loadDataDicList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
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

