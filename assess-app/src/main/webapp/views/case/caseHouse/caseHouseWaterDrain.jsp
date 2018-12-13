<%--
排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel" id="industryHouseWaterDrain">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>排水情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseWaterDrainList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="application/javascript">

    var houseWaterDrain = {};
    houseWaterDrain.config = {
        table: "HouseWaterDrainList",
        box: "divBoxHouseWaterDrain",
        frm: "frmHouseWaterDrain",
        type: "null"
    };
    houseWaterDrain.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    houseWaterDrain.loadDataDicList = function () {
        var cols = [];
        cols.push({field: 'drainSystemName', title: '排水_系统'});
        cols.push({field: 'typeName', title: '类别'});
        cols.push({field: 'processingModeName', title: '排水_处理方式'});
        $("#" + this.config.table).bootstrapTable('destroy');
        TableInit(this.config.table, getContextPath() + "/caseHouseWaterDrain/getBootstrapTableVo", cols, {
            houseId:'${empty caseHouse.id?0:caseHouse.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
    (function () {
        //绑定事件
        $('#' + houseWaterDrain.config.table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseWaterDrain.loadDataDicList();
        })
    })();

</script>


</html>

