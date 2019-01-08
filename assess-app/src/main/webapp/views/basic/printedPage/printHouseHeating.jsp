<%--
 供暖情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseEquipmentHeatingVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="3" style="font-size: 14px;">供暖情况</th>
            </tr>
            <tr>
                <td width="10%">设备品牌</td>
                <td width="10%">类别</td>
                <td width="10%">设备价格区间</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseEquipmentHeatingVos}">
                <tr>
                    <td>${item.equipment}</td>
                    <td>${item.categoryName}</td>
                    <td>${item.equipmentPriceName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>











