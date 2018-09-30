<%--
 电力通讯网络
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseIntelligent.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>电力通讯网络
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none;">
        <div>
            <%--<button type="button" class="btn btn-success" onclick="houseIntelligent.prototype.showModel()"--%>
                    <%--data-toggle="modal" href="#divBox"> 新增--%>
            <%--</button>--%>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="HouseIntelligentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseIntelligent;
    (function () {
        var flag = true;
        houseIntelligent = function () {

        };
        houseIntelligent.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseIntelligent.prototype.loadDataDicList();
                if (houseIntelligent.prototype.getFlag()) {
                    houseIntelligent.prototype.setFlag(false);
                    houseIntelligent.prototype.init();
                }
            },
            config: function () {
                var data = {};
                data.table = "HouseIntelligentList";
                data.box = "divBoxHouseIntelligent";
                data.frm = "frmHouseIntelligent";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'wireErectionName', title: '电线架设方式'});
                cols.push({field: 'switchCircuitName', title: '开关回路'});
                cols.push({field: 'lampsLanternsName', title: '灯具'});
                cols.push({field: 'internalCommunicationName', title: '屋内通讯'});
                cols.push({field: 'monitoringSystemName', title: '监控系统'});
                cols.push({field: 'intelligentSystemName', title: '智能系统'});
                // cols.push({
                //     field: 'id', title: '操作', formatter: function (value, row, index) {
                //         var str = '<div class="btn-margin">';
                //         str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseIntelligent.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                //         str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseIntelligent.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                //         str += '</div>';
                //         return str;
                //     }
                // });
                $("#" + houseIntelligent.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseIntelligent.prototype.config().table, "${pageContext.request.contextPath}/examineHouseIntelligent/getExamineHouseIntelligentList", cols, {
                    type: houseIntelligent.prototype.config().type,
                    declareId : $("#declareId").val(),
                    planDetailsId : $("#planDetailsId").val(),
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
                    url: "${pageContext.request.contextPath}/examineHouseIntelligent/deleteExamineHouseIntelligentById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            houseIntelligent.prototype.loadDataDicList();
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
                $("#" + houseIntelligent.prototype.config().frm).clearAll();
                $("#" + houseIntelligent.prototype.config().frm + " .type").val(houseIntelligent.prototype.config().type);
                $('#' + houseIntelligent.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + houseIntelligent.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(houseIntelligent.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseIntelligent/saveAndUpdateExamineHouseIntelligent",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + houseIntelligent.prototype.config().box).modal('hide');
                            houseIntelligent.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineHouseIntelligent/getExamineHouseIntelligentById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + houseIntelligent.prototype.config().frm).clearAll();
                            $("#" + houseIntelligent.prototype.config().frm).initForm(result.data);
                            if (result.data.internalCommunication == null || result.data.internalCommunication == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .internalCommunication").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .internalCommunication").val(result.data.internalCommunication).trigger("change");
                            }
                            if (result.data.lampsLanterns == null || result.data.lampsLanterns == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .lampsLanterns").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .lampsLanterns").val(result.data.lampsLanterns).trigger("change");
                            }
                            if (result.data.switchCircuit == null || result.data.switchCircuit == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .switchCircuit").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .switchCircuit").val(result.data.switchCircuit).trigger("change");
                            }
                            if (result.data.monitoringSystem == null || result.data.monitoringSystem == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .monitoringSystem").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .monitoringSystem").val(result.data.monitoringSystem).trigger("change");
                            }
                            if (result.data.intelligentSystem == null || result.data.intelligentSystem == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .intelligentSystem").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .intelligentSystem").val(result.data.intelligentSystem).trigger("change");
                            }
                            if (result.data.wireErection == null || result.data.wireErection == '') {
                                $("#" + houseIntelligent.prototype.config().frm + " .wireErection").val(null).trigger("change");
                            } else {
                                $("#" + houseIntelligent.prototype.config().frm + " .wireErection").val(result.data.wireErection).trigger("change");
                            }
                            $('#' + houseIntelligent.prototype.config().box).modal("show");//
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {

            }
        }
    })();

</script>

<div id="divBoxHouseIntelligent" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">电力通讯网络</h3>
            </div>
            <form id="frmHouseIntelligent" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开关回路
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="switchCircuit"
                                                    class="form-control search-select select2 switchCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            灯具
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lampsLanterns"
                                                    class="form-control search-select select2 lampsLanterns">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            屋内通讯
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="internalCommunication"
                                                    class="form-control search-select select2 internalCommunication">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            监控系统
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="monitoringSystem"
                                                    class="form-control search-select select2 monitoringSystem">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            智能系统
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="intelligentSystem"
                                                    class="form-control search-select select2 intelligentSystem">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电线架设方式
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="wireErection"
                                                    class="form-control search-select select2 wireErection">
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
                    <button type="button" class="btn btn-primary" onclick="houseIntelligent.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>




