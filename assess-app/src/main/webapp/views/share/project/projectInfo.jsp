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
                        <label class="form-control">${projectInfo.remarks}</label>                                </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.entrustPurpose}</label>
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
                        <label class="form-control">${projectInfo.projectCategoryId}</label>
                    </div>
                </div>
            </div>

            <!-- 判断到底属于哪个项目 -->
            <!-- 默认项目 -->
            <c:if test="${projectInfo.projectCategoryId == 3}">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.urgencyName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.projectTypeName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">价值类型备注<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.remarkValuetype}</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.provinceName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.cityName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">县<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.districtName}</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.departmentName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">委托目的备注<span class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.remarkEntrustpurpose}</label>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- 债券项目 -->
            <c:if test="${projectInfo.projectCategoryId == 4}">


            </c:if>

        </div>
    </div>
</div>
