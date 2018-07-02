<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/excludes/assets/plugins/laydate/laydate.js"
            type="text/javascript"></script>
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
                                    委托目的
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="queryEntrustmentPurpose"
                                            name="queryEntrustmentPurpose">
                                        <option value="">-请选择-</option>
                                        <c:forEach var="item" items="${entrustmentPurposeList}">
                                            <option value="${item.id}">${item.name}</option>
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
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">阶段权重占比管理</h3>
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
                                            委托目的<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-8">
                                            <select class="form-control" required id="entrustPurpose"
                                                    name="entrustPurpose">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${entrustmentPurposeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <c:forEach var="item" items="${stageList}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label id="stage" name="stage" class="col-sm-3 control-label"
                                                   temp="${item.id}">
                                                    ${item.name}——》占比<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-8">
                                                <input type="text" required data-rule-maxlength="50"
                                                       placeholder="占比" data-rule-digits="true"
                                                       name="proportionList${item.id}" temp-id="${item.id}"
                                                       class="form-control" value="">
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divEdit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="editTitle"></h3>
            </div>
            <form id="frm_edit" class="form-horizontal">
                <input type="hidden" id="id1" name="id" value="">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            委托目的
                                        </label>
                                        <div class="col-sm-8">
                                            <label id="entrustPurpose1" name="entrustPurpose" class="control-label">

                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <c:forEach var="item" items="${stageList}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label id="stage1" name="stage" class="col-sm-3 control-label"
                                                   temp="${item.id}">
                                                    ${item.name}——》占比<span class="symbol required"></span>
                                            </label>

                                            <div class="col-sm-8">
                                                <input type="text" required data-rule-maxlength="50"
                                                       placeholder="占比" data-rule-digits="true"
                                                       name="proportionList${item.id}" temp-id="${item.id}"
                                                       class="form-control" value="">
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveEditData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })
    //加载 阶段权重占比 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'entrustPurposeName', title: '委托目的'});
        cols.push({field: 'stageProportionName', title: '阶段:占比'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editData(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.entrustPurpose + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/stageWeightProportion/list", cols, {
            entrustmentPurpose: $("#queryEntrustmentPurpose").val(),
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 阶段权重占比数据
    function delData(entrustPurpose, tbId) {
        console.log(entrustPurpose);
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/stageWeightProportion/delete",
                type: "post",
                dataType: "json",
                data: {entrustPurpose: entrustPurpose},
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

    //对新增 阶段权重占比数据处理
    function addData() {
        $("#frm").clearAll();
    }
    //新增 阶段权重占比数据
    function saveData() {
        if (!$("#frm").valid()) {
            return false;
        }
        var total = 0;
        var data = {};
        data.id = $("#frm").find('[name="id"]').val();
        data.entrustPurpose = $("#frm").find('[name="entrustPurpose"]').val();
        data.proportionList = '';
        $("#frm").find('[name^="proportionList"]').each(function () {
            total += parseInt($(this).val());
            data.proportionList += $(this).val() + ',';
        })
        data.proportionList = data.proportionList.replace(/,$/, '');
        if (total != 100) {
            toastr.error('占比之和不为100!');
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/stageWeightProportion/save",
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
    //新增 阶段权重占比数据
    function saveEditData() {
        if (!$("#frm_edit").valid()) {
            return false;
        }
        var total = 0;
        var data = {};
        data.id = $("#frm_edit").find('[name="id"]').val();
        data.proportionList = '';
        $("#frm_edit").find('[name^="proportionList"]').each(function () {
            total += parseInt($(this).val());
            data.proportionList += $(this).val() + ',';
        })
        data.proportionList = data.proportionList.replace(/,$/, '');
        if (total != 100) {
            toastr.error('占比之和不为100!');
            return false
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/stageWeightProportion/save",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    loadDataDicList();
                    $('#divEdit').modal('hide');
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


    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        var data = {};
        data.entrustPurpose = row.entrustPurpose;
        document.getElementById('editTitle').innerHTML = row.entrustPurposeName;
        $("#frm_edit").clearAll();
        $.ajax({
            url: "${pageContext.request.contextPath}/stageWeightProportion/edit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                $("#frm_edit").find('[name="entrustPurpose"]').text(row.entrustPurposeName);
                $("#frm_edit").find('[name="id"]').val(row.entrustPurpose);
                $.each(result, function (i, temp) {
                    $("#frm_edit").find('[temp-id=' + temp.stage + ']').val(temp.proportion);
                })
                $('#divEdit').modal();
            },
            error: function (result) {
                console.info(result);
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>


</html>


