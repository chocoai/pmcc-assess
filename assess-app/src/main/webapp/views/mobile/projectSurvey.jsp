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
    <h5 class="mui-content-padded" style="margin: 15px 10px;">拖拽(滑动)显示多功能菜单</h5>
    <ul id="OA_task_2" class="mui-table-view">
        <li class="mui-table-view-cell">
            <div class="mui-slider-right mui-disabled">
                <a class="mui-btn mui-btn-grey mui-icon mui-icon-person"></a>
                <a class="mui-btn mui-btn-yellow mui-icon mui-icon-phone"></a>
                <a class="mui-btn mui-btn-red mui-icon mui-icon-email"></a>
            </div>
            <div class="mui-slider-handle">
                <div class="mui-table-cell">
                    左滑显示多功能菜单
                </div>
            </div>
        </li>
    </ul>
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
