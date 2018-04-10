<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/26
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-title" style="margin: 0px">
    <div class="title_left">
        <h3>
            <i class="fa ${boxprocessIcon}" style="margin-right: 20px;"></i>
            ${boxCnName}
            <small>
                <label>${boxdescription}</label>
                <label class="label label-success"><i class="fa fa-flag"
                                                      style="margin-right: 8px"></i>${currentStepName}</label>
                <label class="label label-primary"><i class="fa fa-user"
                                                      style="margin-right: 8px"></i>${currUserName}</label>
            </small>
        </h3>
    </div>
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
</div>
<div class="clearfix"></div>
