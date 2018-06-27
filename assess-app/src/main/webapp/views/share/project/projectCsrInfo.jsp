<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-4-17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2> 项目信息</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目说明<span class="symbol required"></span></label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarks}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <input placeholder="评估基准日" id="valuationDate"
                               name="valuationDate" data-date-format="yyyy-mm-dd"
                               class="form-control date-picker dbdate" readonly="readonly"
                               value="<fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目类别</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectTypeName}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
