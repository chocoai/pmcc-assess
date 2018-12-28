<%--
 电力通讯网络
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4>电力通讯网络
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
            <table class="table table-bordered" id="HouseIntelligentList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        houseIntelligent.prototype.loadDataDicList();
    })
    var houseIntelligent;
    (function () {
        houseIntelligent = function () {

        };
        houseIntelligent.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseIntelligentList";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseIntelligentColumn();
                $("#" + houseIntelligent.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseIntelligent.prototype.config().table, "${pageContext.request.contextPath}/print/getBasicHouseIntelligentByHouseId", cols, {
                    type: houseIntelligent.prototype.config().type,
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




