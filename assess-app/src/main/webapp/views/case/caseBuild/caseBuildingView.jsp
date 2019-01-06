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
                <div class="title_left">
                    <h2>
                        ${caseEstate.name}${caseBuilding.buildingName}
                        <small>
                            <a class="btn btn-xs btn-success" target="_blank"
                               href="${pageContext.request.contextPath}/caseEstate/detailView?id=${caseEstate.id}">楼盘信息</a>
                        </small>
                    </h2>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/case/caseBuild/buildDetail.jsp" %>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
