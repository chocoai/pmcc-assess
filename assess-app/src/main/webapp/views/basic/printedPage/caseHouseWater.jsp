<%--
 供排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4> 供水情况
    </h4>
    <div class="clearfix"></div>
</div>
<form class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <table class="table table-bordered" id="HouseWaterList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        houseWater.prototype.loadDataDicList();
    })
    var houseWater;
    (function () {
        houseWater = function () {

        };
        houseWater.prototype = {

            viewInit: function () {


            },
            config: function () {
                var data = {};
                data.table = "HouseWaterList";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseWaterColumn();

                $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/print/getBasicHouseWaterByHouseId", cols, {
                    type: houseWater.prototype.config().type,
                    houseId: '${empty caseHouse.id?0:caseHouse.id}'
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
    })();

</script>


</html>

