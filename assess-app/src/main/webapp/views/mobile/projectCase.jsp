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
    <div id='list' class="mui-indexed-list">
        <div class="mui-indexed-list-search mui-input-row mui-search">
            <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索">
        </div>
        <ul class="mui-table-view mui-table-view-striped mui-table-view-condensed">
            <li class="mui-table-view-cell">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-10">
                        <h4 class="mui-ellipsis">四川百盛瑞康生物科技有限公司拟在交通银行蜀汉支行抵押武侯区金江路11号2栋6层601-607号、650-652号办公用房评</h4>
                        <h5>项目成员：郭东</h5>
                        <p class="mui-h6 mui-ellipsis">
                            交通银行股份有限公司成都蜀汉支行#成都本部#抵押评估#对公初评
                        </p>
                    </div>
                    <div class="mui-table-cell mui-col-xs-2 mui-text-right">
                        <span class="mui-h5">09-03</span>
                    </div>
                </div>
            </li>
            <li class="mui-table-view-cell">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-10">
                        <h4 class="mui-ellipsis">四川蓝天药业有限公司拟在天府银行成都分行抵押金牛区金府路799号2栋4楼部分办公用房评</h4>
                        <h5>项目成员：郭东</h5>
                        <p class="mui-h6 mui-ellipsis">
                            交通银行股份有限公司成都蜀汉支行#成都本部#抵押评估#对公初评
                        </p>
                    </div>
                    <div class="mui-table-cell mui-col-xs-2 mui-text-right">
                        <span class="mui-h5">09-03</span>
                    </div>
                </div>
            </li>
            <li class="mui-table-view-cell">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-10">
                        <h4 class="mui-ellipsis">四川达州经济开发区财政投资评审中心对达州高新区房屋征收与补偿中心征收补偿涉及宛俊位于达县南外镇石河村8组土地范围上的实物资产（含租赁方）及土地市场价值咨询评估</h4>
                        <h5>项目成员：郭东</h5>
                        <p class="mui-h6 mui-ellipsis">
                            交通银行股份有限公司成都蜀汉支行#成都本部#抵押评估#对公初评
                        </p>
                    </div>
                    <div class="mui-table-cell mui-col-xs-2 mui-text-right">
                        <span class="mui-h5">09-03</span>
                    </div>
                </div>
            </li>
            <li class="mui-table-view-cell">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-10">
                        <h4 class="mui-ellipsis">蒲茵拟在四川天府银行股份有限公司成都分行抵押成都市高新区紫荆西路6号7栋2单元5层504号住宅评估</h4>
                        <h5>项目成员：郭东</h5>
                        <p class="mui-h6 mui-ellipsis">
                            交通银行股份有限公司成都蜀汉支行#成都本部#抵押评估#对公初评
                        </p>
                    </div>
                    <div class="mui-table-cell mui-col-xs-2 mui-text-right">
                        <span class="mui-h5">09-03</span>
                    </div>
                </div>
            </li>
            <li class="mui-table-view-cell">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-10">
                        <h4 class="mui-ellipsis">达州高新区房屋征收与补偿中心咨询市场价值涉及达州市本盛农业发展有限公司所属达州南弃土场剩余经营权评估项目</h4>
                        <h5>项目成员：郭东</h5>
                        <p class="mui-h6 mui-ellipsis">
                            交通银行股份有限公司成都蜀汉支行#成都本部#抵押评估#对公初评
                        </p>
                    </div>
                    <div class="mui-table-cell mui-col-xs-2 mui-text-right">
                        <span class="mui-h5">09-03</span>
                    </div>
                </div>
            </li>
        </ul>
    </div>
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
