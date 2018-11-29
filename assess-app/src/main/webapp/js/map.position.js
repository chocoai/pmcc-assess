/**
 * Created by kings on 2018-11-29.
 */
document.write('<script type="text/javascript" src="//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar"></script>');
document.write('<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>');
document.write('<div id="containerMap" style="display: none;" tabindex="0"></div>');

var mapPosition={};

mapPosition.complete=function (callback) {
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
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition: 'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', callback);//返回定位信息
            AMap.event.addListener(geolocation, 'error', function () {});      //返回定位出错信息
        });
    });
}