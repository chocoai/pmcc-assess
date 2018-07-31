<%--
 供排水情况
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
    <div class="x_title collapse-link" onclick="houseWater.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3> 供排水情况
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="houseWater.prototype.showModel()"
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
                    <table class="table table-bordered" id="HouseWaterList">
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

    var houseWater;
    (function () {
        var flag = true;
        houseWater = function () {

        };
        houseWater.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseWater.prototype.loadDataDicList();
                if (houseWater.prototype.getFlag()) {
                    houseWater.prototype.init();
                    houseWater.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "HouseWaterList";
                data.box = "divBoxHouseWater";
                data.frm = "frmHouseWater";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'natrueIntakePointNumber', title: '自然区间取水点数'});
                cols.push({field: 'intakePointNumber', title: '取水点数'});
                cols.push({field: 'supplyErectionMethodName', title: '供水管架设方式'});
                cols.push({field: 'pretreatedWaterName', title: '前置净水'});
                cols.push({field: 'drainageCircuitName', title: '排水回路'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/examineHouseWater/getExamineHouseWaterList", cols, {
                    type: houseWater.prototype.config().type,
                    declareId : $("#declareId").val(),
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
                    url: "${pageContext.request.contextPath}/examineHouseWater/deleteExamineHouseWaterById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            houseWater.prototype.loadDataDicList();
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
                $("#" + houseWater.prototype.config().frm).clearAll();
                $("#" + houseWater.prototype.config().frm + " .type").val(houseWater.prototype.config().type);
                $('#' + houseWater.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + houseWater.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(houseWater.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/saveAndUpdateExamineHouseWater",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + houseWater.prototype.config().box).modal('hide');
                            houseWater.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineHouseWater/getExamineHouseWaterById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + houseWater.prototype.config().frm).clearAll();
                            $("#" + houseWater.prototype.config().frm).initForm(result.data);
                            if (result.data.waterIntakeEquipmentPrice == null || result.data.waterIntakeEquipmentPrice == '') {
                                $("#" + houseWater.prototype.config().frm + " .waterIntakeEquipmentPrice").val(null).trigger("change");
                            } else {
                                $("#" + houseWater.prototype.config().frm + " .waterIntakeEquipmentPrice").val(result.data.waterIntakeEquipmentPrice).trigger("change");
                            }
                            if (result.data.purificationEquipmentPrice == null || result.data.purificationEquipmentPrice == '') {
                                $("#" + houseWater.prototype.config().frm + " .purificationEquipmentPrice").val(null).trigger("change");
                            } else {
                                $("#" + houseWater.prototype.config().frm + " .purificationEquipmentPrice").val(result.data.purificationEquipmentPrice).trigger("change");
                            }
                            if (result.data.drainageCircuit == null || result.data.drainageCircuit == '') {
                                $("#" + houseWater.prototype.config().frm + " .drainageCircuit").val(null).trigger("change");
                            } else {
                                $("#" + houseWater.prototype.config().frm + " .drainageCircuit").val(result.data.drainageCircuit).trigger("change");
                            }
                            if (result.data.supplyErectionMethod == null || result.data.supplyErectionMethod == '') {
                                $("#" + houseWater.prototype.config().frm + " .supplyErectionMethod").val(null).trigger("change");
                            } else {
                                $("#" + houseWater.prototype.config().frm + " .supplyErectionMethod").val(result.data.supplyErectionMethod).trigger("change");
                            }
                            if (result.data.pretreatedWater == null || result.data.pretreatedWater == '') {
                                $("#" + houseWater.prototype.config().frm + " .pretreatedWater").val(null).trigger("change");
                            } else {
                                $("#" + houseWater.prototype.config().frm + " .pretreatedWater").val(result.data.pretreatedWater).trigger("change");
                            }
                            $('#' + houseWater.prototype.config().box).modal("show");//
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/examine_house_pretreated_water",
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
                                $("#" + houseWater.prototype.config().frm + " .pretreatedWater").html(option);
                                $("#" + houseWater.prototype.config().frm + " .pretreatedWater").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/examine_house_supply_erection_method",
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
                                $("#" + houseWater.prototype.config().frm + " .supplyErectionMethod").html(option);
                                $("#" + houseWater.prototype.config().frm + " .supplyErectionMethod").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/examine_house_water_intake_equipment_price",
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
                                $("#" + houseWater.prototype.config().frm + " .waterIntakeEquipmentPrice").html(option);
                                $("#" + houseWater.prototype.config().frm + " .waterIntakeEquipmentPrice").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/examine_house_purification_equipment_price",
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
                                $("#" + houseWater.prototype.config().frm + " .purificationEquipmentPrice").html(option);
                                $("#" + houseWater.prototype.config().frm + " .purificationEquipmentPrice").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseWater/examine_house_water_drainage_circuit",
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
                                $("#" + houseWater.prototype.config().frm + " .drainageCircuit").html(option);
                                $("#" + houseWater.prototype.config().frm + " .drainageCircuit").select2({minimumResultsForSearch: -1});//加载样式
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

<div id="divBoxHouseWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供排水情况</h3>
            </div>
            <form id="frmHouseWater" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            取水设备
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="取水设备" name="waterIntakeEquipment"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            采水点数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="采水点数" name="intakePointNumber"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自然区间取水点数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="自然区间取水点数" name="natrueIntakePointNumber"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水回路
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="drainageCircuit"
                                                    class="form-control search-select select2 drainageCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水设备价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="purificationEquipmentPrice"
                                                    class="form-control search-select select2 purificationEquipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            取水设备价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="waterIntakeEquipmentPrice"
                                                    class="form-control search-select select2 waterIntakeEquipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水管架设方式
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="supplyErectionMethod"
                                                    class="form-control search-select select2 supplyErectionMethod">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="pretreatedWater"
                                                    class="form-control search-select select2 pretreatedWater">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

