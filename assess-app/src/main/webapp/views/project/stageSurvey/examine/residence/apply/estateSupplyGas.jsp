<%--
 供气
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateSupplyGas.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <h3>供气信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none;">
        <div>
            <button type="button" class="btn btn-success" onclick="estateSupplyGas.prototype.showModel()"
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
                    <table class="table table-bordered" id="EstateSupplyGasList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var estateSupplyGas;

    (function () {
        var flag = true;
        estateSupplyGas = function () {

        };
        estateSupplyGas.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                if (estateSupplyGas.prototype.getFlag()) {
                    estateSupplyGas.prototype.init();
                    estateSupplyGas.prototype.setFlag(false);
                }
                estateSupplyGas.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "EstateSupplyGasList";
                data.box = "divBoxEstateSupplyGas";
                data.frm = "frmEstateSupplyGas";
                data.type = "estateSupplyGas";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'reputation', title: '供气商信誉'});
                // cols.push({field: 'type', title: '类型'});
                cols.push({field: 'gradeName', title: '供气商等级'});
                // cols.push({field: 'lineGradeName', title: '线路等级'});
                cols.push({field: 'power', title: '功率'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyGas.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyGas.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyGas.prototype.config().table, "${pageContext.request.contextPath}/examineEstateSupply/getExamineEstateSupplyList", cols, {
                    type: estateSupplyGas.prototype.config().type,
                    declareId : $("#declareId").val(),
                    planDetailsId:$("#planDetailsId").val(),
                    examineType : $("#examineType").val()
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
                    url: "${pageContext.request.contextPath}/examineEstateSupply/deleteExamineEstateSupplyById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateSupplyGas.prototype.loadDataDicList();
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
                $("#" + estateSupplyGas.prototype.config().frm).clearAll();
                $("#" + estateSupplyGas.prototype.config().frm + " .type").val(estateSupplyGas.prototype.config().type);
                $('#' + estateSupplyGas.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateSupplyGas.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateSupplyGas.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#planDetailsId").size() > 0) {
                    data.planDetailsId = $("#planDetailsId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineEstateSupply/saveAndUpdateExamineEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateSupplyGas.prototype.config().box).modal('hide');
                            estateSupplyGas.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineEstateSupply/getExamineEstateSupplyById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + estateSupplyGas.prototype.config().frm).clearAll();
                            $("#" + estateSupplyGas.prototype.config().frm).initForm(result.data);
                            if (result.data.lineGrade == null || result.data.lineGrade == '') {
                                $("#" + estateSupplyGas.prototype.config().frm + " .lineGrade").val(null).trigger("change");
                            } else {
                                $("#" + estateSupplyGas.prototype.config().frm + " .lineGrade").val(result.data.lineGrade).trigger("change");
                            }
                            if (result.data.grade == null || result.data.grade == '') {
                                $("#" + estateSupplyGas.prototype.config().frm + " .grade").val(null).trigger("change");
                            } else {
                                $("#" + estateSupplyGas.prototype.config().frm + " .grade").val(result.data.grade).trigger("change");
                            }
                            $('#' + estateSupplyGas.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineEstateSupply/line_water_supply_pipe_grade",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + estateSupplyGas.prototype.config().frm ).find("select.lineGrade").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineEstateSupply/supplier_grade",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + estateSupplyGas.prototype.config().frm ).find("select.grade").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            }
        }
    })();

</script>

<div id="divBoxEstateSupplyGas" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供气</h3>
            </div>
            <form id="frmEstateSupplyGas" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气线路等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="reputation" class="form-control"
                                                   placeholder="供应商信誉" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="功率" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyGas.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>