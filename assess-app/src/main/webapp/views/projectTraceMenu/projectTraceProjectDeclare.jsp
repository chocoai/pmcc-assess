<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="./trace_project_menu.jsp" %>
        <div class="right_col" role="main">

            <%@include file="/views/share/project/projectInfoSimple.jsp" %>

            <%@include file="./stagePlan.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div id="projectDeclareToolbar" style="display: none">
                        <div class="input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary"
                                            onclick="declareRecordGroup.initForm({});" data-toggle="modal"
                                            data-target="#tb_project_declare_group_model">创建组</button>
                                    <button type="button" class="btn btn-danger"
                                            onclick="declareRecordGroup.deleteData();">删除组</button>
                                    <button type="button" class="btn btn-default"
                                            onclick="declareRecordGroup.editData();">编辑组</button>
                                </span>
                            <label class="label label-warning">先创建组，然后再给组分配申报数据</label>
                            <span class="input-group-btn"></span>
                        </div>
                    </div>
                    <table id="tb_project_declare_group_table" class="table table-bordered">
                    </table>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <button type="button" class="btn btn-primary"
                            onclick="declareRecordGroup.switchAutoGroup(2)">按楼栋自动分组
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="declareRecordGroup.switchAutoGroup(3)">按单元自动分组
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="declareRecordGroup.switchAutoGroup(1)">按区域(省市区)自动分组
                    </button>
                    <label class="label label-info">建议自己创建组，然后自己对申报数据进行分组</label>
                </div>
                <div class="x_content">
                    <p class="text-info">系统自动分组创建的组依旧会在上面组列表里面显示,用户还可以继续手动更改分组信息,还有一点就是系统自动分组会创建新组</p>
                    <p class="text-primary">自动分组首先必须得自己创建分组,否则系统不会自动分的</p>
                    <p class="text-danger">
                        按照区域分系统会自动确认申报信息里面区域相同的有多少个,然后自动创建相同区域数量的组,
                        注意假如之前你创建过组并且你还分组过申报数据那么系统分自动从剩下没有分组完的申报数据里面继续分组</p>
                    <p class="text-danger">
                        按照楼栋分系统会自动确认申报信息里面楼栋号相同的有多少个,然后自动创建相同楼栋号数量的组,
                        注意假如之前你创建过组并且你还分组过申报数据那么系统分自动从剩下没有分组完的申报数据里面继续分组</p>
                    <p class="text-danger">
                        按照单元分(暂时不予实现)</p>
                </div>
            </div>

            <%@include file="/views/share/form_details.jsp" %>

        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<div id="tb_project_declare_group_model" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">申报组创建</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <select name="province" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <select name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">县</label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <select name="district" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            名称
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" name="name" class="form-control" placeholder="名称">
                                            <label class="label label-info">自动生成的名称建议自己修改下</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            说明
                                        </label>
                                        <div class=" col-xs-7  col-sm-7  col-md-7  col-lg-7 ">
                                            <textarea class="form-control" name="remarks" placeholder="说明"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="declareRecordGroup.save();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="tb_project_declare_group_dispatch_model" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">申报分组</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="groupId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">

                                    <div class="x-valid ">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="权证名称" name="name" class="form-control">
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                   class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="text" placeholder="单元号" name="unit" class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="button" class="btn btn-primary" value="查询"
                                                   onclick="declareRecordGroup.selectRecordData(this,null);">
                                        </div>
                                    </div>


                                    <div class="x-valid ">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="权证名称" name="name" class="form-control">
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                   class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="text" placeholder="单元号" name="unit" class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="button" class="btn btn-primary" value="查询"
                                                   onclick="declareRecordGroup.selectRecordData(this,$(this).closest('form').find('[name=groupId]').val());">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <table class="table table-bordered" id="cancelDeclareRecordTableList">
                                                <caption>未分组</caption>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2 bs-glyphicons"
                                             style="padding-top:140px;padding-left:30px;">
                                            <a class="btn btn-primary" onclick="declareRecordGroup.submitGroup()"><i
                                                    class="fa fa-chevron-right"></i></a>
                                            <a class="btn btn-primary" onclick="declareRecordGroup.cancelGroup()"><i
                                                    class="fa fa-chevron-left"></i></a>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                            <table class="table table-bordered" id="sureDeclareRecordTableList">
                                                <caption>已经分组</caption>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

<script type="text/javascript">

    var declareRecordGroup = {};

    declareRecordGroup.groupTargetTable = $("#tb_project_declare_group_table");
    declareRecordGroup.groupTargetModel = $("#tb_project_declare_group_model");
    declareRecordGroup.groupDispatchModel = $("#tb_project_declare_group_dispatch_model");
    declareRecordGroup.sureDeclareRecordTable = $("#sureDeclareRecordTableList");
    declareRecordGroup.cancelDeclareRecordTable = $("#cancelDeclareRecordTableList");

    /*申报记录 table list*/
    declareRecordGroup.baseDeclareRecordTable = function (table, data) {
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证名称', width: "12%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unit', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "9%"});
        cols.push({field: 'floorArea', title: '证载面积', width: "9%"});
        cols.push({field: 'practicalArea', title: '实际面积', width: "9%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table.attr("id"), "${pageContext.request.contextPath}/declareRecord/getBootstrapTableVoDispatch", cols, data, method);
    };

    declareRecordGroup.loadTable = function () {
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({
            field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
            }, width: "15%"
        });
        cols.push({field: 'name', title: '名称', width: "6%"});
        cols.push({
            field: 'id', title: '分组操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareRecordGroup.dispatchData(' + value + ');" >分组操作</i></a>';
                str += "<i class='fa fa-hand-o-right' aria-hidden='true'></i>";
                str += '</div>';
                return str;
            }, width: "10%"
        });
        cols.push({field: 'remarks', title: '备注', width: "35%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        var quarm = {
            projectId: '${projectInfo.id}',
            planId: '${projectPlan.id}'
        };
        declareRecordGroup.groupTargetTable.bootstrapTable('destroy');
        TableInit(declareRecordGroup.groupTargetTable.attr("id"), "${pageContext.request.contextPath}/declareRecordGroup/getBootstrapTableVo", cols, quarm, method);
        var bootstrapTable = declareRecordGroup.groupTargetTable.closest(".bootstrap-table");
        if (bootstrapTable.size() != 0) {
            var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
            if (fixedTableToolbar.size() != 0) {
                fixedTableToolbar.append($("#projectDeclareToolbar").html());
            }
        }
    };

    /*申报记录 查询*/
    declareRecordGroup.selectRecordData = function (_this, groupId) {
        var select = {};
        var target = $(_this).closest('.x-valid');
        select.planId = '${projectPlan.id}';
        select.projectId = '${projectInfo.id}';
        var name = target.find("[name='name']").val();
        if (name) {
            select.name = name;
        }
        var buildingNumber = target.find("[name='buildingNumber']").val();
        if (buildingNumber) {
            select.buildingNumber = buildingNumber;
        }
        var unit = target.find("[name='unit']").val();
        if (unit) {
            select.unit = unit;
        }
        if (groupId) {
            select.groupId = groupId;
            declareRecordGroup.baseDeclareRecordTable(declareRecordGroup.sureDeclareRecordTable, select);
        } else {
            declareRecordGroup.baseDeclareRecordTable(declareRecordGroup.cancelDeclareRecordTable, select);
        }
    };

    /*自动分组*/
    declareRecordGroup.switchAutoGroup = function (key) {
        if (key == 3){
            toastr.info("未实现此功能!");
            return false ;
        }
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/declareRecordGroup/switchAutoGroup",
            data: {projectId:'${projectInfo.id}' , key:key , planId:'${projectPlan.id}'},
            success: function (result) {
                if (result.ret) {
                    declareRecordGroup.groupTargetTable.bootstrapTable('refresh');
                } else {
                    Alert("失败原因:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
     *确认分组
     */
    declareRecordGroup.submitGroup = function () {
        var rows = declareRecordGroup.cancelDeclareRecordTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要分组的数据");
        } else {
            var groupId = declareRecordGroup.groupDispatchModel.find("[name='groupId']").val();
            var data = [];
            $.each(rows, function (i, row) {
                var obj = JSON.parse(JSON.stringify(row));
                obj.groupId = groupId;
                data.push(obj);
            });
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/declareRecord/saveAndUpdateDeclareRecord",
                data: {fomData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        declareRecordGroup.cancelDeclareRecordTable.bootstrapTable('refresh');
                        declareRecordGroup.sureDeclareRecordTable.bootstrapTable('refresh');
                    } else {
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
    };

    /*取消分组*/
    declareRecordGroup.cancelGroup = function () {
        var rows = declareRecordGroup.sureDeclareRecordTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要取消分组的数据");
        } else {
            var data = [];
            $.each(rows, function (i, row) {
                var obj = JSON.parse(JSON.stringify(row));
                obj.groupId = 0;
                data.push(obj);
            });
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/declareRecord/saveAndUpdateDeclareRecord",
                data: {fomData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        declareRecordGroup.cancelDeclareRecordTable.bootstrapTable('refresh');
                        declareRecordGroup.sureDeclareRecordTable.bootstrapTable('refresh');
                    } else {
                        Alert("失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
    };

    /**
     * 显示分组model
     */
    declareRecordGroup.dispatchData = function (groupId) {
        declareRecordGroup.groupDispatchModel.modal("show");
        var data = {};
        data.planId = '${projectPlan.id}';
        data.projectId = '${projectInfo.id}';
        var obj = JSON.parse(JSON.stringify(data));
        data.groupId = groupId;
        declareRecordGroup.groupDispatchModel.find("[name='groupId']").val(groupId);
        declareRecordGroup.baseDeclareRecordTable(declareRecordGroup.cancelDeclareRecordTable, obj);
        declareRecordGroup.baseDeclareRecordTable(declareRecordGroup.sureDeclareRecordTable, data);
    };

    /**
     * 赋值
     */
    declareRecordGroup.initForm = function (data) {
        var form = declareRecordGroup.groupTargetModel.find("form");
        form.clearAll();
        if (!data.name) {
            var item = declareRecordGroup.groupTargetTable.bootstrapTable('getData');
            data.name = "组" + (item.length + 1);
        }
        form.initForm(data);
        AssessCommon.initAreaInfo({
            provinceTarget: form.find("select[name='province']"),
            cityTarget: form.find("select[name='city']"),
            districtTarget: form.find("select[name='district']"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        })
    };

    /**
     * 删除
     */
    declareRecordGroup.deleteData = function () {
        var rows = declareRecordGroup.groupTargetTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/declareRecordGroup/deleteDeclareRecordGroupById/" + idArray.join(","),
                    data: {_method: "DELETE"},
                    success: function (result) {
                        if (result.ret) {
                            declareRecordGroup.groupTargetTable.bootstrapTable('refresh');
                        } else {
                            declareRecordGroup.groupTargetTable.bootstrapTable('uncheckAll');
                            Alert("失败原因:" + result.errmsg);
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
            })
        }
    };

    /**
     * 编辑
     */
    declareRecordGroup.editData = function () {
        var rows = declareRecordGroup.groupTargetTable.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            if (rows.length == 1) {
                var data = rows[0];
                declareRecordGroup.initForm(data);
                declareRecordGroup.groupTargetModel.modal("show");
                declareRecordGroup.groupTargetTable.bootstrapTable('uncheckAll');
            } else {
                toastr.success('勾选一个!');
            }
        }
    };

    /**
     * 保存
     * @returns {boolean}
     */
    declareRecordGroup.save = function () {
        var form = declareRecordGroup.groupTargetModel.find("form");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        data.planId = '${projectPlan.id}';
        data.projectId = '${projectInfo.id}';
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/declareRecordGroup/saveDeclareRecordGroup",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    declareRecordGroup.groupTargetModel.modal("hide");
                    declareRecordGroup.groupTargetTable.bootstrapTable('refresh');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    $(document).ready(function () {
        declareRecordGroup.loadTable();
    });


</script>