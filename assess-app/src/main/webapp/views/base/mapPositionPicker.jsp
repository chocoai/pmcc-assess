<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-17
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src='//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar'></script>
<!-- UI组件库 1.0 -->
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<div id="containerMap" style="display: none;" tabindex="0"></div>
<script type="text/javascript">
    var gaoDeMap = {};
    //高德地图接入定位
    AMapUI.loadUI(['misc/PositionPicker'], function (PositionPicker) {
        var map, geolocation;
        //加载地图，调用浏览器定位服务
        map = new AMap.Map('containerMap', {
            zoom: 17,
            resizeEnable: true,

        });
        map.plugin('AMap.Geolocation', function () {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 0,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition: 'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', function () {});      //返回定位出错信息
        });

        //解析定位结果
        function onComplete(data) {
            gaoDeMap.location = data;
            //执行定位成功方法
            console.log('定位成功');
            if(gaoDeMap.positionSuccess){
                gaoDeMap.positionSuccess(data);
            }
        }
    });

</script>