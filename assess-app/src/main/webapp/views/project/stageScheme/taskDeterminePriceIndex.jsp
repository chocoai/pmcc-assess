<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <c:forEach items="${areaJudgeObjectVos}" var="item">
                <div class="x_panel area_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h2>
                            <label>${item.areaGroupName}</label>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content collapse">
                        <form class="form-horizontal">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width: 5%">编号</th>
                                    <th style="width: 10%">权证号</th>
                                    <th style="width: 5%">所有权人</th>
                                    <th style="width: 15%">坐落</th>
                                    <th style="width: 10%">证载用途</th>
                                    <th style="width: 10%">实际用途</th>
                                    <th style="width: 10%">设定用途</th>
                                    <th style="width: 10%">最佳利用描述</th>
                                    <th style="width: 5%">证载面积</th>
                                    <th style="width: 5%">评估面积</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${item.judgeObjectVoList}" var="judgeObject">
                                    <tr>
                                        <td>
                                            <label class="form-control">${judgeObject.number}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.name}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.ownership}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.seat}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.certUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.practicalUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.setUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.floorArea}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.evaluationArea}</label>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>


<script type="text/javascript">
    $(function () {

    })
</script>
<script type="application/javascript">
    function submit() {

        var data = {};

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

</script>

</html>

