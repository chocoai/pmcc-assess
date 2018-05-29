<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-29
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <title>${title}</title>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div  style="min-height: 950px;">
            <div class="">
                <div class="clearfix"></div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>${title}</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                ${message}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
