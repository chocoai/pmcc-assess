<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
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
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">字段名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                   placeholder="字段名称" id="queryFieldName" name="queryFieldName"
                                                   class="form-control input-full">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="reloadDamagedDegreeList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="addDamagedDegree()"
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
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">完整度管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <input type="hidden" id="pid" name="pid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                字段名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-maxlength="50" placeholder="字段名称"
                                                       id="fieldName" name="fieldName" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                权重<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="50" placeholder="权重"
                                                       required name="weight" class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-digits="true" placeholder="排序"
                                                       id="sorting" name="sorting" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea placeholder="备注" id="remark" name="remark"
                                                      class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveDamagedDegree()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!--子项管理-->
<div id="divSubDamagedDegree" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子项数据</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <P id="toolbarSub">
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="addSubDamagedDegree()"
                                        href="#divSubDamagedDegreeManage">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </P>
                            <table class="table table-bordered" id="tbDamagedDegreeList">
                            </table>


                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
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
                <h4 class="modal-title">子项管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmSub" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <input type="hidden" name="pid" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="subName" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                字段名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="100" placeholder="字段名称"
                                                       id="subFieldName" name="fieldName" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                标准得分
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="100" placeholder="标准得分"
                                                       name="standardScore" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                权重
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="100" placeholder="权重"
                                                       name="weight" class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                完好标准
                                            </label>
                                            <div class="col-sm-11">
                                           <textarea name="intact" class="form-control input-full"
                                                     placeholder="完好标准"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                基本完好标准
                                            </label>
                                            <div class="col-sm-11">
                                               <textarea name="basicallyIntact" class="form-control input-full"
                                                         placeholder="基本完好标准"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                一般损坏标准
                                            </label>
                                            <div class="col-sm-11">
                                              <textarea name="generalDamage" class="form-control input-full"
                                                        placeholder="一般损坏标准"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                严重损坏标准
                                            </label>
                                            <div class="col-sm-11">
                                               <textarea name="seriousDamage" class="form-control input-full"
                                                         placeholder="严重损坏标准"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="subBisEnable"
                                                               name="subBisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-digits="true" placeholder="排序"
                                                       id="subSorting" name="sorting" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                           <textarea placeholder="备注" id="subRemark" name="remark"
                                                     class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSubDamagedDegree()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


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
                var str = '<button onclick="setSubDamagedDegree(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="editDamagedDegree(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delDamagedDegree(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        TableReload("tb_List");
                        $('#divBox').modal('hide');
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
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
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDamagedDegree/delDamagedDegree",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
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
                var str = '<button onclick="setSubDamagedDegree(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="editSubDamagedDegree(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delDamagedDegree(' + row.id + ',\'tbDamagedDegreeList\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        TableReload("tbDamagedDegreeList");
                        $('#divSubDamagedDegreeManage').modal('hide');
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
                    notifyWarning("获取失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
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
