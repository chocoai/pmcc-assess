<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseRoomVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="9" style="font-size: 14px;">房间</th>
            </tr>
            <tr>
                <td width="10%">房间类型</td>
                <td width="10%">面积</td>
                <td width="10%">层高(m)</td>
                <td width="10%">净高(m)</td>
                <td width="15%">开间/宽(m)</td>
                <td width="15%">进深/长(m)</td>
                <td width="10%">日照</td>
                <td width="10%">采光</td>
                <td width="10%">通风</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseRoomVos}">
                <tr>
                    <td>${item.roomTypeName}</td>
                    <td>${item.area}</td>
                    <td>${item.layerHeight}</td>
                    <td>${item.clearHeight}</td>
                    <td>${item.opening}</td>
                    <td>${item.depth}</td>
                    <td>${item.sunshine}</td>
                    <td>${item.lighting}</td>
                    <td>${item.aeration}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>




