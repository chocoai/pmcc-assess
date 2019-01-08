<%--
排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseWaterDrainVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="3" style="font-size: 14px;">排水情况</th>
            </tr>
            <tr>
                <td width="10%">排水系统</td>
                <td width="10%">类别</td>
                <td width="10%">排水处理方式</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseWaterDrainVos}">
                <tr>
                    <td>${item.drainSystemName}</td>
                    <td>${item.typeName}</td>
                    <td>${item.processingModeName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>






