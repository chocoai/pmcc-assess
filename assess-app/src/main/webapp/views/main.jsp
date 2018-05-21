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
                <div class="col-md-6">
                    <div class="x_panel ">
                        <div class="x_title">
                            <h2>活动项目</h2>
                            <div class="clearfix"></div>
                        </div>
<div class="tile">
    <h2 style="text-align: right">231,809</h2>
</div>
                        <div class="x_content" id="container">
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
</html>

<script type="text/javascript">
    Highcharts.chart('container', {
        legend: {
            enabled: false
        },
        exporting: {enabled: false},
        chart: {
            type: 'column',
            useHTML: true
        },
        title: {
            text: null
        },
        xAxis: {
            categories: [
                '房产', '土地', '生物资产', '油气', '车辆', '矿权', '无形知识', '机器设备', '其它资产', '综合资产'
            ],
            crosshair: false
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr>' +
            '<td style="padding:0"><b>{point.y:.1f}个 </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        series: [{
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1]

        }]
    });
</script>
