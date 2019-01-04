<%--
 电力通讯网络
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    电力通讯网络
    <table class="table table-bordered" id="HouseIntelligentList">
    </table>
</div>


<script type="application/javascript">
    $(function () {
        houseIntelligent.loadDataDicList();
    })
    var houseIntelligent={};
    houseIntelligent.loadDataDicList=function () {
        var cols = commonColumn.houseIntelligentColumn();
        $("#HouseIntelligentList").bootstrapTable('destroy');
        TableInit("HouseIntelligentList", "${pageContext.request.contextPath}/basicHouseIntelligent/getBootstrapTableVo", cols, {
            houseId: '${empty basicHouse.id?0:basicHouse.id}'
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




