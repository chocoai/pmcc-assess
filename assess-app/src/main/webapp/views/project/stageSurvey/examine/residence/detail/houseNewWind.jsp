<%--
 新风情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industryNewWind">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>新风情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseNewWindList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>


<script>

    var houseNewWind;
    (function () {
        houseNewWind = function () {

        };
        houseNewWind.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseNewWindList";
                data.box = "divBoxHouseNewWind";
                data.frm = "frmHouseNewWind";
                data.type = "houseNewWind";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseNewWindColumn();
                $("#" + houseNewWind.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseNewWind.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseNewWind.prototype.config().type,
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
        $('#' + houseNewWind.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseNewWind.prototype.loadDataDicList();
        })
    })();

</script>