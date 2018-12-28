<%--
 空调情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4>空调情况
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
            <table class="table table-bordered" id="HouseAirConditionerList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        houseAirConditioner.prototype.loadDataDicList();
    })
    var houseAirConditioner;
    (function () {
        var flag = true;
        houseAirConditioner = function () {

        };
        houseAirConditioner.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseAirConditionerList";
                data.type = "houseAirConditioner";//根据 ExamineHouseEquipmentTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseAirConditionerColumn();
                $("#" + houseAirConditioner.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseAirConditioner.prototype.config().table, "${pageContext.request.contextPath}/print/getBasicHouseEquipmentByHouseId", cols, {
                    type: houseAirConditioner.prototype.config().type,
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


