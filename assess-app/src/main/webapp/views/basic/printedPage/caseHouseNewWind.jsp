<%--
 新风情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4>新风情况
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
            <table class="table table-bordered" id="HouseNewWindList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>

<script type="application/javascript">
    $(function () {
        houseNewWind.prototype.loadDataDicList();
    })
    var houseNewWind;
    (function () {
        houseNewWind = function () {

        };
        houseNewWind.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseNewWindList";
                data.type = "houseNewWind";//根据 ExamineHouseEquipmentTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseNewWindColumn();
                $("#" + houseNewWind.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseNewWind.prototype.config().table, "${pageContext.request.contextPath}/print/getBasicHouseEquipmentByHouseId", cols, {
                    type: houseNewWind.prototype.config().type,
                    houseId: '${empty caseHouse.id?0:caseHouse.id}'
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
    })();

</script>


</html>





