<%--
排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4>排水情况</h4>
</div>
<form class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <table class="table table-bordered" id="HouseWaterDrainList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        houseWaterDrain.loadDataDicList();
    })
    var houseWaterDrain = {};
    houseWaterDrain.config = {
        table: "HouseWaterDrainList",
        type: "null"
    };
    houseWaterDrain.loadDataDicList = function () {
        var cols = commonColumn.houseWaterDrainColumn();
        $("#" + this.config.table).bootstrapTable('destroy');
        TableInit(this.config.table, getContextPath() + "/print/getBasicHouseWaterDrainByHouseId", cols, {
            houseId: '${empty caseHouse.id?0:caseHouse.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


</script>


</html>

