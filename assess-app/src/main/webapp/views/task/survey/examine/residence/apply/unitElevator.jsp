<%--
 电梯
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <!-- 包含此文件时需要删除掉css -->
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link" onclick="unitElevator.prototype.viewInit()"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>电梯信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="unitElevator.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="ExamineUnitElevatorList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<%--<%@include file="/views/share/main_footer.jsp" %>--%>

<script type="application/javascript">

    var unitElevator = function () {

    };
    unitElevator.prototype = {
        viewInit:function () {
            unitElevator.prototype.loadDataDicList();
            // unitElevator.prototype.init();
        },
        config: function () {
            var data = {};
            data.table = "ExamineUnitElevatorList";
            data.box = "divBoxExamineUnitElevator";
            data.frm = "frmExamineUnitElevator";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'number', title: '电梯数量'});
            cols.push({field: 'quasiLoadNumber', title: '准载人数'});
            cols.push({field: 'quasiLoadWeight', title: '准载重量'});
            cols.push({field: 'runningSpeed', title: '运行速度'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitElevator.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitElevator.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitElevator.prototype.config().table, "${pageContext.request.contextPath}/examineUnitElevator/getExamineUnitElevatorList", cols, {
                name: $("#queryName").val()
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
            $.ajax({
                url: "${pageContext.request.contextPath}/examineUnitElevator/deleteExamineUnitElevatorById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        unitElevator.prototype.loadDataDicList();
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
        showModel: function () {
            $("#" + unitElevator.prototype.config().frm).clearAll();
            $('#' + unitElevator.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitElevator.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(unitElevator.prototype.config().frm);
            if ($("#declareId").size() > 0) {
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0) {
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/examineUnitElevator/saveAndUpdateExamineUnitElevator",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + unitElevator.prototype.config().box).modal('hide');
                        unitElevator.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/examineUnitElevator/getExamineUnitElevatorById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + unitElevator.prototype.config().frm).clearAll();
                        $("#" + unitElevator.prototype.config().frm).initForm(result.data);
                        $('#' + unitElevator.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
    /**
     * 初始化
     */
    $(function () {
        // unitElevator.prototype.loadDataDicList();
    });

</script>

<div id="divBoxExamineUnitElevator" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">通信网络</h3>
            </div>
            <form id="frmExamineUnitElevator" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯维护情况
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="maintenance"
                                                   placeholder="电梯维护情况" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯类型
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="type"
                                                   placeholder="电梯类型" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯品牌
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="brand"
                                                   placeholder="电梯品牌" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯数量
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="电梯数量(数字)" data-rule-number='true'
                                                   name="number" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载人数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载人数(数字)" data-rule-number='true'
                                                   name="quasiLoadNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载重量
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载重量(数字)" data-rule-number='true'
                                                   name="quasiLoadWeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            运行速度
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="runningSpeed"
                                                   placeholder="运行速度" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="unitElevator.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

