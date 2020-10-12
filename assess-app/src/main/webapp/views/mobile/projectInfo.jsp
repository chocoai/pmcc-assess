<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <title></title>
    <link href="${pageContext.request.contextPath}/assets/mui/css/mui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/mui/css/mui.indexedlist.css" rel="stylesheet">
    <style>
        html,
        body {
            height: 100%;
            overflow: hidden;
        }

        .mui-bar {
            -webkit-box-shadow: none;
            box-shadow: none;
        }
    </style>
    <style></style>
</head>
<body>
<header class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <h1 class="mui-title">indexed list（索引列表）</h1>
</header>
<div class="mui-content">
    <div class="mui-card">
        <div class="mui-card-content">
            <div class="mui-card-content-inner">
                四川百盛瑞康生物科技有限公司拟在交通银行蜀汉支行抵押武侯区金江路11号2栋6层601-607号、650-652号办公用房评
            </div>
            <div class="mui-input-row">
                <label>委托目的:</label>
                <label>抵押评估</label>
            </div>
            <div class="mui-input-row">
                <label>紧急程度:</label>
                <label>中</label>
            </div>
            <div class="mui-input-row">
                <label>执业部门:</label>
                <label>雅安办事处</label>
            </div>
            <div class="mui-input-row">
                <label>项目负责人:</label>
                <label>袁野</label>
            </div>
            <div class="mui-input-row">
                <label>项目成员:</label>
                <label>袁野</label>
            </div>
            <div class="mui-input-row">
                <label>评估基准日:</label>
                <label>2020-10-10</label>
            </div>
        </div>
    </div>
    <%@include file="/views/mobile/common/projectTabbar.jsp" %>
</div>
<script src="${pageContext.request.contextPath}/assets/mui/js/mui.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/assets/mui/js/mui.indexedlist.js?v=${assessVersion}"></script>
<script type="text/javascript" charset="utf-8">
    mui.init();
    mui.ready(function() {
        var header = document.querySelector('header.mui-bar');
        var list = document.getElementById('list');
        //calc hieght
        list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
    });
</script>
</body>
</html>
