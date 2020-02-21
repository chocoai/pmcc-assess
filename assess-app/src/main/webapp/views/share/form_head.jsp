<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel-header bg-primary-gradient">
    <div class="page-inner py-5" style="padding-top: 2rem !important; padding-bottom: 2rem !important;">
        <div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
            <div>
                <h2 class="text-white pb-2 fw-bold">${boxCnName}</h2>
                <h5 class="text-white op-7 mb-2">
                    <i class="fa fa-flag" style="margin-right: 8px"></i>${currentStepName}
                    <i class="fa fa-user" style="margin-right: 8px"></i>${currUserName}
                </h5>
            </div>
            <div class="ml-md-auto py-2 py-md-0">
                <c:if test="${Steps!=null && Steps!='' && Steps!='[]'}">
                    <div class="title_right">
                        <div class="col-md-12 col-sm-12 col-xs-12 pull-right" style="margin: 0px">
                            <div class="form_wizard wizard_horizontal">
                                <ul class="wizard_steps anchor" style="margin: 0px;">
                                    <li>
                                        <c:if test="${flog=='approval'}">
                                        <a class="done">
                                            </c:if>

                                            <c:if test="${flog=='details'}">
                                            <a class="done">
                                                </c:if>

                                                <c:if test="${flog=='apply'}">
                                                <a class="selected">
                                                    </c:if>

                                                    <span class="step_no">0</span>
                                                    <span class="step_descr">
                                              <small>申请</small>
                                          </span>
                                                </a>
                                    </li>
                                    <c:forEach var="item" items="${Steps}">
                                        <li>
                                            <a class="${item.explain}">
                                                <div class="step_no">
                                                        ${item.key}
                                                </div>
                                                <span class="step_descr"> ${item.value}</span>
                                            </a>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
            <a href="${pageContext.request.contextPath}/home/main" class="btn btn-white btn-border btn-round mr-2">回首页</a>
        </div>
    </div>

</div>


