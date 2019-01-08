<%--
 供排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseWaterVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="7" style="font-size: 14px;">供排水情况</th>
            </tr>
            <tr>
                <td width="10%">给水方式</td>
                <td width="10%">给水管道布置</td>
                <td width="10%">给水管材料</td>
                <td width="10%">给水升压设备</td>
                <td width="10%">前置净水</td>
                <td width="10%">前置净水设备价格区间</td>
                <td width="10%">消防给水</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseWaterVos}">
                <tr>
                    <td>${item.supplyModeName}</td>
                    <td>${item.pipingLayoutName}</td>
                    <td>${item.pipeMaterialName}</td>
                    <td>${item.boosterEquipmentName}</td>
                    <td>${item.pretreatedWaterName}</td>
                    <td>${item.purificationEquipmentPriceName}</td>
                    <td>${item.fireWaterSupplyName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>





