<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/17
  Time: 16:29
  To change this MergeRule use File | Settings | File MergeRules.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>报告合并规则</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="queryReportType" id="queryReportType" class="form-control">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadMergeRuleList()">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success" data-toggle="modal"
                                                href="#modalMergeRule"
                                                onclick="addMergeRule();"> 新增
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <table id="tbList" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type='hidden' id='MergeRuleId' name='MergeRuleId' value="0">
    <!-- end: MAIN CONTAINER -->
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>

<!-- start: org modal -->
<div id="modalMergeRule" class="modal fade bs-example-modal-lg" data-height="200"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                <h3 class='modal-title'>报告合并规则</h3></div>
            <form id='frm' class='form-horizontal'>
                <input type='hidden' id='id' name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>报告类型<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-4'>
                                            <select name='reportType' required class='form-control'>
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>
                                            报告模板<span class="symbol required"></span>
                                        </label>
                                        <div class='col-sm-4'>
                                            <div class="input-group">
                                                <input type="hidden" name="templateId">
                                                <input name='templateName' required class='form-control' required
                                                       readonly="readonly" maxlength="200">
                                                <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="reportTeamplateSelect(this);" data-toggle="tooltip" data-original-title="选择">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                                 <button type="button" class="btn btn-default docs-tooltip"
                                                         onclick="$(this).closest('.input-group').find('input').val('');"
                                                         data-toggle="tooltip" data-original-title="清除">
                                                    <i class="fa fa-trash-o"></i>
                                                </button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input name='sorting' required class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>备注</label>
                                        <div class='col-sm-10'>
                                         <textarea id="remark" name="remark"
                                                   placeholder="备注"
                                                   class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveMergeRule();"
                        id='btn_save_bid_organization_agency'>保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/report-template-select.js"></script>
<script type="application/javascript">
    $(function () {
        loadMergeRuleList();
    })

    //选择模板
    function reportTeamplateSelect(_this) {
        reportTemplate.select(function (row) {
            $(_this).closest('.input-group').find('[name=templateId]').val(row.id);
            $(_this).closest('.input-group').find('[name=templateName]').val(row.name);
        })
    }


    //加载列表数据
    function loadMergeRuleList() {
        var cols = [];
        cols.push({field: 'reportTypeName', title: '报告类型'});
        cols.push({field: 'templateName', title: '模板名称'});
        cols.push({field: 'sorting', title: '排序'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = "";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editMergeRule(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips'  onclick='delMergeRule(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                return str;
            }
        });
        $("#tbList").bootstrapTable('destroy');
        TableInit("tbList", "${pageContext.request.contextPath}/reportMergeRule/getMergeRuleList", cols, {
            type: $("#queryType").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //新增模板
    function addMergeRule() {
        $("#frm").clearAll();
        $("#id").val("0");
    }
    //编辑模板
    function editMergeRule(index) {
        var row = $("#tbList").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $('#modalMergeRule').modal();
    }

    //删除模板
    function delMergeRule(id) {
        bootbox.confirm("确认要删除么？", function (result) {
            if (result) {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/reportMergeRule/deleteMergeRule",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            $('#tbList').bootstrapTable("refresh");
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
            }
        });
    }

    //保存模板
    function saveMergeRule() {
        if ($("#frm").valid()) {
            Loading.progressShow();
            var data = formParams("frm");
            $.ajax({
                url: "${pageContext.request.contextPath}/reportMergeRule/saveMergeRule",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#tbList').bootstrapTable("refresh");
                        $('#modalMergeRule').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }


</script>

</body>
</html>


