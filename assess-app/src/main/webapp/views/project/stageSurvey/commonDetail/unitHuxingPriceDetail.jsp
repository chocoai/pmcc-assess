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
                <h3 class="modal-title">调查信息</h3>
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
                var cols = commonColumn.estateInvestigation();
                $("#" + unitHuxingPriceDetail.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxingPriceDetail.prototype.config().table, getContextPath() + "/basicEstateInvestigation/getEstateInvestigationList", cols, {
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

