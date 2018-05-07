<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/excludes/assets/plugins/laydate/laydate.js" type="text/javascript"></script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script type="text/javascript" src='//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar'></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <%--<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>--%>
    <style>

        html,
        body {
            height: 100%;
            margin: 0;
            width: 100%;
            padding: 0;
            overflow: hidden;
            font-size: 13px;
        }

        .map {
            height: 40%;
            width: 50%;
            float: left;
        }

        #right input {
            margin: 4px;
            margin-left: 15px;
        }

        .title {
            width: 100%;
            background-color: #dadada
        }

        button {
            border: solid 1px;
            margin-left: 15px;
            background-color: #dadafa;
        }

        .c {
            font-weight: 600;
            padding-left: 15px;
            padding-top: 4px;
        }

        #lnglat,
        #address,
        #nearestJunction,
        #nearestRoad,
        #nearestPOI,
        .title {
            padding-left: 15px;
        }
    </style>


</head>

<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
            </div>

            <div id="container" class="map" tabindex="0"></div>
            <div id="tip"></div>
            <div id='right' class="map">
                <div>
                    <div class='title'>选址结果</div>
                    <div class='c'>经纬度:</div>
                    <div id='lnglat'></div>
                    <div class='c'>地址:</div>
                    <div id='address'></div>
                    <div class='c'>最近的路口:</div>
                    <div id='nearestJunction'></div>
                    <div class='c'>最近的路:</div>
                    <div id='nearestRoad'></div>
                    <div class='c'>最近的POI:</div>
                    <div id='nearestPOI'></div>
                </div>
            </div>
            <%--<div>
                <a href=http://restapi.amap.com/v3/staticmap?location=116.481485,39.990464&zoom=12&size=750*400&markers=mid,,A:116.481485,39.990464&key=4a0d4efa2b780342409ff261c5e2fac1>生成图片</a>;
            </div>--%>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>

</body>



<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">
    AMapUI.loadUI(['misc/PositionPicker'], function(PositionPicker) {

        var map, geolocation;
        //加载地图，调用浏览器定位服务
        map = new AMap.Map('container', {
            resizeEnable: true
        });
        map.plugin('AMap.Geolocation', function() {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition:'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
        });
        //解析定位结果
        function onComplete(data) {
            var str=['定位成功'];
            str.push('经度：' + data.position.getLng());
            str.push('纬度：' + data.position.getLat());
            if(data.accuracy){
                str.push('精度：' + data.accuracy + ' 米');
            }//如为IP精确定位结果则没有精度信息
            str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
            document.getElementById('tip').innerHTML = str.join('<br>');
        }
        //解析定位错误信息
        function onError(data) {
            document.getElementById('tip').innerHTML = '定位失败';
        }


        var positionPicker = new PositionPicker({
            mode: 'dragMap',
            map: map
        });

        positionPicker.on('success', function(positionResult) {
            document.getElementById('lnglat').innerHTML = positionResult.position;
            document.getElementById('address').innerHTML = positionResult.address;
            document.getElementById('nearestJunction').innerHTML = positionResult.nearestJunction;
            document.getElementById('nearestRoad').innerHTML = positionResult.nearestRoad;
            document.getElementById('nearestPOI').innerHTML = positionResult.nearestPOI;
        });
        positionPicker.on('fail', function(positionResult) {
            document.getElementById('lnglat').innerHTML = ' ';
            document.getElementById('address').innerHTML = ' ';
            document.getElementById('nearestJunction').innerHTML = ' ';
            document.getElementById('nearestRoad').innerHTML = ' ';
            document.getElementById('nearestPOI').innerHTML = ' ';
        });
        positionPicker.start();
        map.panBy(0, 1);

    });
</script>

<%--<script>

    $(function(){

    })
</script>--%>


</html>