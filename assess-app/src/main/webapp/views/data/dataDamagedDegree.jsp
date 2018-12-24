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
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    字段名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                           placeholder="字段名称" id="queryFieldName" name="queryFieldName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div type="button" class="btn btn-primary" onclick="reloadDamagedDegreeList()">
                                    查询
                                </div>
                                <div type="button" class="btn btn-success" onclick="addDamagedDegree()"
                                     data-toggle="modal" href="#divBox"> 新增
                                </div>
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
                <h3 class="modal-title">完整度管理</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <input type="hidden" id="pid" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="name" name="name" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-maxlength="50" placeholder="字段名称"
                                                   id="fieldName" name="fieldName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            权重<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-maxlength="50" placeholder="权重"
                                                   required name="weight" class="form-control x-percent">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否启用
                                        </label>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="sorting" name="sorting" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            备注
                                        </label>
                                        <div class="col-sm-10">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="saveDamagedDegree()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--子项管理-->
<div id="divSubDamagedDegree" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">子项数据</h3>
            </div>
            <div class="panel-body">
                <span id="toolbarSub">
                    <div type="button" class="btn btn-success" onclick="addSubDamagedDegree()"
                         data-toggle="modal" href="#divSubDamagedDegreeManage"> 新增
                    </div>
                </span>
                <table class="table table-bordered" id="tbDamagedDegreeList">
                </table>
            </div>
        </div>
    </div>
</div>
<div id="divSubDamagedDegreeManage" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子项管理</h3>
            </div>
            <form id="frmSub" class="form-horizontal">
                <input type="hidden" name="id" value="0">
                <input type="hidden" name="pid" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="subName" name="name" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-maxlength="100" placeholder="字段名称"
                                                   id="subFieldName" name="fieldName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            标准得分
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-maxlength="100" placeholder="标准得分"
                                                   name="standardScore" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            权重
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-maxlength="100" placeholder="权重"
                                                   name="weight" class="form-control x-percent">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            完好标准
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="intact" class="form-control" placeholder="完好标准"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            基本完好标准
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="basicallyIntact" class="form-control"
                                                      placeholder="基本完好标准"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            一般损坏标准
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="generalDamage" class="form-control"
                                                      placeholder="一般损坏标准"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            严重损坏标准
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="seriousDamage" class="form-control"
                                                      placeholder="严重损坏标准"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否启用<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="subBisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="subSorting" name="sorting" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            备注
                                        </label>
                                        <div class="col-sm-10">
                                         <textarea placeholder="备注" id="subRemark" name="remark"
                                                   class="form-control"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="saveSubDamagedDegree()">
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
        loadDamagedDegreeList();
    })
    //加载代理数据列表
    function loadDamagedDegreeList() {
        var cols = [];
        cols.push({field: 'fieldName', title: '字段名称'});
        cols.push({field: 'name', title: '名称'});
        cols.push({
            field: 'weight', title: '权重', formatter: function (value) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDamagedDegree(' + row.id + ');" >查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editDamagedDegree(' + index + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDamagedDegree(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/dataDamagedDegree/getDamagedDegreeList", cols, {
            fieldName: $("#queryFieldName").val(),
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }
    //刷新数据列表
    function reloadDamagedDegreeList() {
        var opt = {
            url: "${pageContext.request.contextPath}/dataDamagedDegree/getDamagedDegreeList",
            query: {
                fieldName: $("#queryFieldName").val(),
                name: $("#queryName").val()
            }
        };
        $("#tb_List").bootstrapTable("refresh", opt);
    }

    //保存数据
    function saveDamagedDegree() {
        if ($("#frm").valid()) {
            var data = formParams("frm");
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDamagedDegree/saveDamagedDegree",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        TableReload("tb_List");
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

    //新增完整度数据
    function addDamagedDegree() {
        $("#frm").clearAll();
        $("#id").val("0");
        $("#bisEnable").prop("checked", true);
    }
    //编辑完整度数据
    function editDamagedDegree(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll().initForm(row, function () {
            var weight = $("#frm").find('[name=weight]');
            weight.attr('data-value', row.weight);
            AssessCommon.elementParsePercent(weight);
        });
        $('#divBox').modal();
    }
    //删除完整度数据
    function delDamagedDegree(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDamagedDegree/delDamagedDegree",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
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

    //重置查询条件
    function queryReset() {
        $("#queryId").val("");
        $("#groupKey").val("");
        $("#queryName").val("");
    }
    //设置子项数据
    function setSubDamagedDegree(pid) {
        $("#frmSub").find('[name=pid]').val(pid);
        getDamagedDegreeLevel(pid);
        loadSubDamagedDegreeList(pid, function () {
            $('#divSubDamagedDegree').modal("show");
        });
    }

    //加载节点角色数据
    function loadSubDamagedDegreeList(pid, fn) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'fieldName', title: '字段名称'});
        cols.push({field: 'standardScore', title: '标准分'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDamagedDegree(' + row.id + ');" ><i class="fa fa-edit">查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editSubDamagedDegree(' + row.id + ');" ><i class="fa fa-edit">编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDamagedDegree(' + row.id + ',\'tbDamagedDegreeList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tbDamagedDegreeList").bootstrapTable("destroy");
        TableInit("tbDamagedDegreeList", "${pageContext.request.contextPath}/dataDamagedDegree/getDamagedDegreeListByPid",
            cols, {pid: pid}, {
                showRefresh: false,                  //是否显示刷新按钮
                toolbar: '#toolbarSub',
                uniqueId: "id",
                onLoadSuccess: function () {
                    if (fn) {
                        fn();
                    }
                }
            });
    }
    //新增子项
    function addSubDamagedDegree() {
        $("#frmSub").clearAll(["pid"]);
        $("#subId").val("0");
        $("#subBisEnable").prop("checked", true);
    }

    //编辑子项
    function editSubDamagedDegree(id) {
        $("#frmSub").clearAll();
        var row = $("#tbDamagedDegreeList").bootstrapTable("getRowByUniqueId", id);
        if (row) {
            $("#frmSub").initForm(row);
            $("#subBisEnable").prop("checked", row.bisEnable);
        }
        $('#divSubDamagedDegreeManage').modal();
    }


    //保存子项
    function saveSubDamagedDegree() {
        if ($("#frmSub").valid()) {
            var data = formParams("frmSub");
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDamagedDegree/saveDamagedDegree",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        TableReload("tbDamagedDegreeList");
                        $('#divSubDamagedDegreeManage').modal('hide');
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

    var strLevelHtml = "";
    //获取完整度层级
    function getDamagedDegreeLevel(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataDamagedDegree/getDamagedDegreeLevel",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    strLevelHtml = "";
                    if (result.data) {
                        if (result.data.keyValueDto) {
                            getDamagedDegreeLevelRecursion(result.data.keyValueDto);
                        }
                        strLevelHtml += '<a href="javascript:setSubDamagedDegree(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                        $("#titleContent").html(strLevelHtml.replace(/>$/, ""));
                    }
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

    //获取层级
    function getDamagedDegreeLevelRecursion(keyValueDto) {
        if (keyValueDto) {
            getDamagedDegreeLevelRecursion(keyValueDto.keyValueDto);
            strLevelHtml += '<a href="javascript:setSubDamagedDegree(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
        }
    }
</script>


</html>
