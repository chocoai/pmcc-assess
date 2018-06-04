<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-4-17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title">
        <h2> 项目信息</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${csrProjectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-11">
                        <label class="form-control">${csrProjectInfo.name}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
