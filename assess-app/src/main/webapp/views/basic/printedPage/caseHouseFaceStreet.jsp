<%--
 临街（路）状况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4>临街（路）
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
            <table class="table table-bordered" id="HouseFaceStreetList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        houseFaceStreet.prototype.loadDataDicList();
    })
    var houseFaceStreet;
    (function () {
        houseFaceStreet = function () {

        };
        houseFaceStreet.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseFaceStreetList";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseFaceStreetColumn();
                $("#" + houseFaceStreet.prototype.config().table).bootstrapTable('destroy');//examineHouseFaceStreet
                TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/print/getBasicHouseFaceStreetByHouseId", cols, {
                    type: houseFaceStreet.prototype.config().type,
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


