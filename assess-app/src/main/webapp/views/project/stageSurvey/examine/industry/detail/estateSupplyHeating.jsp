<%--
  供热
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industrySupplyHeating">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供热信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">

        <table class="table table-bordered" id="EstateSupplyHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var estateSupplyHeating;
    (function () {
        estateSupplyHeating = function () {
        };
        estateSupplyHeating.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyHeatingList";
                data.box = "divBoxEstateSupplyHeating";
                data.frm = "frmEstateSupplyHeating";
                data.type = "estateSupplyHeating";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateSupplyHeatingColumn();
                $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyHeating.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyHeating.prototype.config().type,
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
        $('#' + estateSupplyHeating.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            estateSupplyHeating.prototype.loadDataDicList();
        })
    })();
</script>