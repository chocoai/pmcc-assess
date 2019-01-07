<%--
 空调情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>空调情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseAirConditionerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    var houseAirConditioner;
    (function () {
        houseAirConditioner = function () {

        };
        houseAirConditioner.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseAirConditionerList";
                data.box = "divBoxHouseAirConditioner";
                data.frm = "frmHouseAirConditioner";
                data.type = "houseAirConditioner";//根据 ExamineHouseEquipmentTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseAirConditionerColumn();
                $("#" + houseAirConditioner.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseAirConditioner.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseAirConditioner.prototype.config().type,
                    houseId: '${empty basicHouse.id?0:basicHouse.id}',
                    approval: true
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

        //绑定事件
        $('#' + houseAirConditioner.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseAirConditioner.prototype.loadDataDicList();
        })
    })();

</script>