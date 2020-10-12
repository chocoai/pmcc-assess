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
        <ul class="mui-table-view">
            <li class="mui-table-view-cell mui-collapse mui-active">
                <a class="mui-navigate-right" href="#">现场查勘1</a>
                <div class="mui-collapse-content">
                    xxxxx
                </div>
            </li>
            <li class="mui-table-view-cell mui-collapse">
                <a class="mui-navigate-right" href="#">现场查勘2</a>
                <div class="mui-collapse-content">
                    ggggggg
                </div>
            </li>
        </ul>
    </div>
    <%@include file="/views/mobile/common/projectTabbar.jsp" %>
</div>
<script src="${pageContext.request.contextPath}/assets/mui/js/mui.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/assets/mui/js/mui.indexedlist.js?v=${assessVersion}"></script>
<script type="text/javascript" charset="utf-8">
    mui.init();
    mui.ready(function () {
        var header = document.querySelector('header.mui-bar');
        var list = document.getElementById('list');
        //calc hieght
        list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
    });
</script>
<script type="text/javascript">
    $('#tree').treeview({
        data: getTree(), // 获取数据节点
        levels: 5,//节点层级数
        color: "#000",//每一级通用的 节点字体颜色
        backColor: "#fff",//每一级通用的 节点字背景色
        onhoverColor: "orange",//选中浮动颜色
        borderColor: "red",//设置组件的边框颜色; 设置showBorder为false，如果你不想要一个可见的边框
        showBorder: false,
        showTags: true,//是否在每个节点的右侧显示标签。 其值必须在每个节点的数据结构中提供

        highlightSelected: true,//是否突出显示选定的节点
        selectedColor: "#fff",//设置选定节点的前景色
        selectedBackColor: "darkorange",//设置选定节点的背景色
    });

    function getTree() {
        var tree = [
            {
                text: "Parent 1",
                nodes: [
                    {
                        text: "Child 1",
                        nodes: [
                            {
                                text: "Grandchild 1"
                            },
                            {
                                text: "Grandchild 2"
                            }
                        ]
                    },
                    {
                        text: "Child 2"
                    }
                ]
            },
            {
                text: "Parent 2"
            },
            {
                text: "Parent 3"
            },
            {
                text: "Parent 4"
            },
            {
                text: "Parent 5"
            }
        ];
        return tree;
    }
</script>
</body>
</html>
