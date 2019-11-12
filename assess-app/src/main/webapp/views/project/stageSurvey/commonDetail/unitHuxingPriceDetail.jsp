<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divBoxUnitHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">单价调查</h3>
            </div>
            <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                <input type="hidden" name="unitHuxingId">
                <div class="modal-body">
                    <table class="table table-bordered" id="UnitHuxingPriceList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>





<div id="divBoxUnitHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">单价调查</h3>
            </div>
            <form id="frmUnitHuxingPrice" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="huxingId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                房号
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="房号" name="houseNumber"class="form-control" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                价格
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="价格" name="price" data-rule-number='true' readonly
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                备注
                            </label>
                            <div class="col-sm-10">
                                    <textarea placeholder="备注" id="remark" name="remark" readonly
                                              class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    var unitHuxingPriceDetail;
    (function () {
        unitHuxingPriceDetail = function () {
        };
        unitHuxingPriceDetail.prototype = {
            config: function () {
                var data = {};
                data.table = "UnitHuxingPriceList";
                data.box = "divBoxUnitHuxingPrice";
                data.frm = "frmUnitHuxingPrice";
                data.tableBox = "divBoxUnitHuxingPriceTable";
                data.tableFrm = "frmUnitHuxingPriceTable";
                return data;
            },
            loadDataDicList: function (unitHuxingId) {
                var cols = [];
                cols.push({field: 'houseNumber', title: '房号'});
                cols.push({field: 'price', title: '价格'});
                cols.push({field: 'remark', title: '备注'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="unitHuxingPriceDetail.prototype.getAndInit(' + row.id + ',\''+unitHuxingId+'\',\'tb_List\')"><i class="fa fa-search fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + unitHuxingPriceDetail.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxingPriceDetail.prototype.config().table, getContextPath() + "/basicUnitHuxingPrice/getUnitHuxingPriceList", cols, {
                    unitHuxingId: unitHuxingId
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }, showTableModel: function (unitHuxingId) {
                unitHuxingPriceDetail.prototype.loadDataDicList(unitHuxingId);
                $("#" + unitHuxingPriceDetail.prototype.config().tableFrm).find("input[name='unitHuxingId']").val(unitHuxingId);
                $('#' + unitHuxingPriceDetail.prototype.config().tableBox).modal("show");
            },
            showModel: function () {
                $("#" + unitHuxingPriceDetail.prototype.config().frm).clearAll();
                var unitHuxingId = $("#" + unitHuxingPriceDetail.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
                $("#" + unitHuxingPriceDetail.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);
                $('#' + unitHuxingPriceDetail.prototype.config().box).modal("show");
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            getAndInit: function (id,unitHuxingId) {
                $.ajax({
                    url: getContextPath() + "/basicUnitHuxingPrice/getBasicUnitHuxingPriceById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            if (unitHuxingPriceDetail.prototype.isNotNull(data)) {
                                unitHuxingPriceDetail.prototype.init(data,unitHuxingId);
                            } else {
                                unitHuxingPriceDetail.prototype.init({});
                            }
                            $('#' + unitHuxingPriceDetail.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item,unitHuxingId) {
                $("#" + unitHuxingPriceDetail.prototype.config().frm).clearAll();
                $("#" + unitHuxingPriceDetail.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);
                $("#" + unitHuxingPriceDetail.prototype.config().frm).initForm(item);
            }
        }

    })();
</script>
</html>

