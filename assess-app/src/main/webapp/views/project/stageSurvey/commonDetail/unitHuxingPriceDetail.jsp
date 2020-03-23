<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="divBoxUnitHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">调查信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                    <input type="hidden" name="unitHuxingId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="col-md-12">
                                    <table class="table table-bordered" id="UnitHuxingPriceList">
                                        <!-- cerare document add ajax data-->
                                    </table>
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
                var cols = commonColumn.unitHuxingPriceColumn();
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
            },
            showTableModel: function (unitHuxingId) {
                unitHuxingPriceDetail.prototype.loadDataDicList(unitHuxingId);
                $("#" + unitHuxingPriceDetail.prototype.config().tableFrm).find("input[name='unitHuxingId']").val(unitHuxingId);
                $('#' + unitHuxingPriceDetail.prototype.config().tableBox).modal("show");
            }
        }

    })();
</script>
</html>

