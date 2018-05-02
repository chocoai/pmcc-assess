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
                <div class="x_title">
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
                                    大类
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="queryAssessClass" name="queryAssessClass">
                                        <option value="">-请选择-</option>
                                        <c:forEach var="item" items="${assessClassList}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success" onclick="addData()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">

                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">申报表单管理</h4>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            大类<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <select class="form-control" required id="assessClass" name="assessClass">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${assessClassList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            申报表名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" required data-rule-maxlength="50" placeholder="申报表名称"
                                                   id="name" name="name" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            表单名称
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="hidden" name="formId" id="formId" value="0">
                                            <input type="text" id="formIdName" name="formIdName" class="form-control"
                                                   required readonly="readonly" onclick="assessForm.select();">
                                        </div>
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
                    <button type="button" class="btn btn-primary" onclick="saveDataDic()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/assess-form-utils.js" type="text/javascript"></script>
<script type="application/javascript">
    $(function () {
        loadList();

        $("#formIdName").click(function () {
            assessForm.select(function (row) {
                if(row){
                    $("#formId").val(row.id);
                    $("#formIdName").val(row.cnName);
                }
            });
        })
    })
    //加载代理数据列表
    function loadList() {
        var cols = [];
        cols.push({field: 'assessClassName', title: '大类'});
        cols.push({field: 'name', title: '申报表名称'});
        cols.push({field: 'formIdName', title: '表单名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:editData(' + index + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delData(' + row.id + ');">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/dataDeclareForm/list", cols, {
            assessClass: $("#queryAssessClass").val(),
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //保存数据
    function saveDataDic() {
        if ($("#frm").valid()) {
            var data = formParams("frm");
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDeclareForm/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadList();
                        $('#divBox').modal('hide');
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

    //新增字典数据
    function addData() {
        $("#frm").clearAll();
        $("#id").val("0");
    }
    //编辑字典数据
    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $('#divBox').modal();
    }
    //删除字典数据
    function delData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDeclareForm/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadList();
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
</script>


</html>
