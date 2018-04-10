<!DOCTYPE html>
<%@ page import="org.omg.CORBA.Request" %><%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/06/13
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
    <title>${orgSystemName}首页</title>
    <%@include file="share/main_css.jsp" %>
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="share/main_navigation.jsp" %>
        <%@include file="share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-4 col-sm-4 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <input id="file_upload_IDs" name="file_upload" type="file" multiple="false">
                            <div id="_file_upload_IDs">
                            </div>
                            <a href="/testFtp/downloadFileFromServer">下载文件</a>
                            <a href="javascript:;" onclick="updateArea()">更新区域</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="share/main_footer.jsp" %>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>

</body>
<!-- end: BODY -->
<%--<script type="application/javascript">--%>
    <%--//------------------------document init----------------------//--%>
    <%--$(function () {--%>
        <%--$(".tooltips").tooltip();--%>
        <%--uploadFiles("file_upload_IDs", "", {},--%>
            <%--{--%>
                <%--tableName: "tb_org_user",--%>
                <%--fieldsName: "IDs"--%>
            <%--}--%>
            <%--, function (data) {--%>
                <%--//alert(data);--%>
            <%--});--%>
    <%--});--%>


    <%--function updateArea() {--%>
        <%--$.ajax({--%>
            <%--url: "/home/updateArea",--%>
            <%--type: 'post',--%>
            <%--dataType: 'json',--%>
            <%--success: function (result) {--%>
                <%--Alert("成功");--%>
            <%--}--%>
        <%--});--%>

    <%--}--%>
<%--</script>--%>
</html>
