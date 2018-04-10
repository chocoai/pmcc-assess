<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left" style="width: auto;">
                    <h3>
                        【${chksApprovalBusinessVo.processCnName}】
                        <small>${chksApprovalBusinessVo.processTitle}</small>
                    </h3>

                </div>
            </div>
            <div class="clearfix"></div>
            <c:forEach var="item" items="${chksApprovalAssessVos}">
                <div class="accordion" id="accordion${item.id}" role="tablist" aria-multiselectable="true">
                    <div class="panel">
                        <a class="panel-heading" role="tab" id="heading${item.id}" data-toggle="collapse" data-parent="#accordion${item.id}"
                           href="#collapse${item.id}"
                           aria-expanded="true" aria-controls="collapse${item.id}">
                            <h4 class="panel-title">${item.creatorName}->${item.userName}(${item.activityName})
                                得分：<label class="label label-warning">${item.totalScore}</label>;
                                有效分：<label class="label label-warning">${item.totalVaildScore}</label>;
                            </h4>
                        </a>
                        <div id="collapse${item.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${item.id}"
                             aria-expanded="true"
                             style="height: 0px;">
                            <div class="panel-body">
                                                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width:50%">考核项</th>
                                        <th  style="width:20%">得分</th>
                                        <th  style="width:30%">考核说明</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="assess" items="${item.chksApprovalAssessDetails}">

                                        <tr>
                                            <td>${assess.assessModelTitle}<label class="label label-success">${assess.assessModelMin}~${assess.assessModelMax}</label></td>
                                            <td>${assess.score}</td>
                                            <td>${assess.scoreRemarks}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td>合计</td>
                                        <td colspan="2" >
                                            得分：<label class="label label-warning">${item.totalScore}</label>;
                                            有效分：<label class="label label-warning">${item.totalVaildScore}</label>;
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="x_panel">
                <div class="x_title">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <a target="_blank" href="${chksApprovalBusinessVo.businessUrl}" class="btn btn-success">查看业务表单</a>
                        <button class="btn btn-warning" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>


</html>
