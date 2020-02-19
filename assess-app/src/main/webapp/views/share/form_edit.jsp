<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2018/2/5
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="opinions" name="opinions" value="0">
<input type="hidden" id="bisNext" name="bisNext" value="0">
<input type="hidden" id="nextApproval" name="nextApproval" value="">
<input type="hidden" id="appointUserAccount" name="appointUserAccount" value="">
<input type="hidden" name="boxId" value="${boxId}">
<input type="hidden" name="processInsId" value="${processInsId}">
<input type="hidden" name="taskId" value="${taskId}">
<input type="hidden" name="activityKey" value="${activityReName}">
<input type="hidden" name="activityCnName" value="${activityCnName}">
<input type="hidden" name="CurrentStep" value="-1">
<input type="hidden" name="StepCount" value="${StepCount}">
<input type="hidden" name="reId" value="${reId}">
<input type="hidden" name="activityId" value="${activityId}">
<input type="hidden" name="lastNodes" value="${lastNodes}">
<input type="hidden" name="agentUserAccount" value="${agentUserAccount}">
<c:choose>
    <c:when test="${projectInfo != null}">
        <input type="hidden" name="projectId" value="${projectInfo.id}">
    </c:when>
    <c:otherwise>
        <input type="hidden" name="projectId" value="${projectId}">
    </c:otherwise>
</c:choose>
<input type="hidden" name="viewUrl" value="${viewUrl}">
<input type="hidden" name="conclusion" value="approval">
