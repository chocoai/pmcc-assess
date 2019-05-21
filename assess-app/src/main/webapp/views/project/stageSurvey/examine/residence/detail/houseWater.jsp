<%--
 供排水情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industryWater">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供水情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    var houseWater;
    (function () {
        houseWater = function () {

        };
        houseWater.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseWaterList";
                data.box = "divBoxHouseWater";
                data.frm = "frmHouseWater";
                data.type = "null";//
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseWaterColumn();
                $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/basicHouseWater/getBootstrapTableVo", cols, {
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
            }
        }

        //绑定事件
        $('#' + houseWater.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseWater.prototype.loadDataDicList();
        })
    })();

</script>
