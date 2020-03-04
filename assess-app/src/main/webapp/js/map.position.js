/**
 * Created by kings on 2018-11-29.
 */
document.write('<script type="text/javascript" src="//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar"></script>');
document.write('<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>');
document.write('<div id="containerMap" style="display: none;" tabindex="0"></div>');
var mapPosition = {};

//获取当前所在城市
mapPosition.getCurrentCity = function (callback) {
    var province, city, district;
    //1.先到cookie中读取，如果存在则直接返回，不存在则通过地图定位处理
    province = mapPosition.readCookie("province");
    city = mapPosition.readCookie("city");
    district = mapPosition.readCookie("district");
    if (province && city && district) {
        province = province.replace('省', '');
        city = city.replace('市', '');
    } else {
        mapPosition.complete(function (data) {
            try {
                var province, city, district;
                if (data && data.addressComponent) {
                    province = data.addressComponent.province;
                    city = data.addressComponent.city;
                    district = data.addressComponent.district;
                } else {
                    province = "四川";
                    city = "成都";
                }
                province = province.replace('省', '');
                city = city.replace('市', '');
                if (province && city && district) {
                    mapPosition.writeCookie("province", province, 24);
                    mapPosition.writeCookie("city", city, 24);
                    mapPosition.writeCookie("district", district, 24);

                }
            } catch (e) {
            }
        })
    }
    if (callback) {
        callback(province, city, district);
    }
};


/**
 * 根据定位获取区域在数据库中的id
 * @param callback
 */
mapPosition.getCurrentCityByArea = function (callback) {
    var areaJson = mapPosition.readCookie("areaJson");
    if (areaJson) {
        callback(JSON.parse(areaJson));
    } else {
        callback({province: null, city: null, district: null});
    }
};

mapPosition.readCookie = function (name) {
    var cookieValue = "";
    var search = name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            cookieValue = unescape(document.cookie.substring(offset, end))
        }
    }
    return cookieValue;
}

mapPosition.writeCookie = function (name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = "; expires=" + expire.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire;
}


mapPosition.complete = function (callback) {

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
            AMap.event.addListener(geolocation, 'complete', callback);//返回定位信息
            AMap.event.addListener(geolocation, 'error', callback);      //返回定位出错信息
        });
    });
}