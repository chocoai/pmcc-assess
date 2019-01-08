<%--
 临街（路）状况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseFaceStreetVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="4" style="font-size: 14px;"> 临街（路）状况</th>
            </tr>
            <tr>
                <td width="25%">名称</td>
                <td width="25%">街道级别</td>
                <td width="25%">交通流量</td>
                <td width="25%">人流量</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseFaceStreetVos}">
                <tr>
                    <td>${item.streetName}</td>
                    <td>${item.streetLevelName}</td>
                    <td>${item.trafficFlowName}</td>
                    <td>${item.visitorsFlowrateName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
