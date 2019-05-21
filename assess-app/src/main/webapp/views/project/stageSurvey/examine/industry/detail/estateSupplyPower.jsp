<%--
 供电
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industrySupplyPower">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供电信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">

        <table class="table table-bordered" id="EstateSupplyPowerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var estateSupplyPower;
    (function () {
        estateSupplyPower = function () {

        };
        estateSupplyPower.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyPowerList";
                data.box = "divBoxEstateSupplyPower";
                data.frm = "frmEstateSupplyPower";
                data.type = "estateSupplyPower";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateSupplyPowerColumn();
                $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyPower.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyPower.prototype.config().type,
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
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
        $('#' + estateSupplyPower.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            estateSupplyPower.prototype.loadDataDicList();
        })
    })();
</script>