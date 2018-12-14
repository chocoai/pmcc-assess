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
        <h4>供气信息
        </h4>
        <div class="clearfix"></div>
    </div>

    <div class="x_content collapse">
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
                var cols = commonColumn.estateSupplyGasColumn();
                $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyGas.prototype.config().table, "${pageContext.request.contextPath}/caseEstateSupply/getCaseEstateSupplyList", cols, {
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
                    type:estateSupplyGas.prototype.config().type
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },

        }
    })();

</script>


</html>