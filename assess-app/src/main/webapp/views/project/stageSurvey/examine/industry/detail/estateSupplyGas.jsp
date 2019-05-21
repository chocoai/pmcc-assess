<%--
 供气
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industrySupplyGas">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供气信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">

        <table class="table table-bordered" id="EstateSupplyGasList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var estateSupplyGas;
    (function () {
        estateSupplyGas = function () {
        };
        estateSupplyGas.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyGasList";
                data.box = "divBoxEstateSupplyGas";
                data.frm = "frmEstateSupplyGas";
                data.type = "estateSupplyGas";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateSupplyGasColumn();
                $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyGas.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
                    type: estateSupplyGas.prototype.config().type,
                    approval: true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        }

        //绑定事件
        $('#' + estateSupplyGas.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            estateSupplyGas.prototype.loadDataDicList();
        })
    })();
</script>
