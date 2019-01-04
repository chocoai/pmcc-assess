<%--
 供排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    供水情况
</div>
<form class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <table class="table table-bordered" id="HouseWaterList">
            </table>
        </div>
    </div>
</form>
<script type="application/javascript">
    $(function () {
        houseWater.loadDataDicList();
    })
    var houseWater = {};
    houseWater.loadDataDicList = function () {
        var cols = commonColumn.houseWaterColumn();
        $("#HouseWaterList").bootstrapTable('destroy');
        TableInit("HouseWaterList", "${pageContext.request.contextPath}/basicHouseWater/getBootstrapTableVo", cols, {
            houseId: '${empty caseHouse.id?0:caseHouse.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
</script>


</html>

