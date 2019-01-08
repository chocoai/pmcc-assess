<%--
 电力通讯网络
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty houseIntelligentVos}">
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="5" style="font-size: 14px;">电力通讯网络</th>
            </tr>
            <tr>
                <td width="10%">开关回路</td>
                <td width="10%">铺设方式</td>
                <td width="10%">灯具</td>
                <td width="10%">智能系统</td>
                <td width="10%">备注</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${houseIntelligentVos}">
                <tr>
                    <td>${item.switchCircuitName}</td>
                    <td>${item.layingMethodName}</td>
                    <td>${item.lampsLanternsName}</td>
                    <td>${item.intelligentSystemName}</td>
                    <td>${item.remark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>








