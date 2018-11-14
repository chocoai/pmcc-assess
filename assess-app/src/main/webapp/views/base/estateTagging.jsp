<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>标注</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <style type="text/css">
        .amap-icon img,
        .amap-marker-content img {
            width: 25px;
            height: 34px;
        }
    </style>
</head>
<body>
<div id="container" style="width: 100%;height: 100%;" tabindex="0"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
                <label>请输入关键字：</label>
            </td>
        </tr>
        <tr>
            <td>
                <input id="tipinput"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.10&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript">
    //初始化地图对象，加载地图
    var map = new AMap.Map("container", {resizeEnable: true, zoom: 19});
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({});

    //注册监听，当选中某条记录时会触发
    AMap.event.addListener(auto, "select", function (e) {
        placeSearch.setCity(e.poi.adcode);
        placeSearch.search(e.poi.name, function (status, result) {
            if (result.info == 'OK') {
                if (result.poiList.pois.length > 0) {
                    var poi = result.poiList.pois[0];
                    map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
                }
            }

        })
    });

    //地图点击事件
    map.on('click', function (e) {
        parent.addTagging(e.lnglat.getLng(), e.lnglat.getLat());
    });

    var markerArray = [];//标记数组
    //加载所有标注信息
    function loadMarkerList(taggingArray) {
        for (var i = 0; i < markerArray.length; i++) {
            markerArray[i].setMap(null);
        }
        markerArray.length = 0;
        if (taggingArray && taggingArray.length > 0) {
            var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
            for (var i = 0; i < taggingArray.length; i++) {
                var tagging = taggingArray[i];
                var marker = new AMap.Marker({
                    position: [tagging.lng, tagging.lat],
                    title: tagging.content,
                    map: map
                });
                marker.content = tagging.content;
                marker.on('click', function (e) {
                    infoWindow.setContent(e.target.content);
                    infoWindow.open(map, e.target.getPosition());
                });
                markerArray.push(marker);
            }
            map.setFitView();
        }
    }
</script>
</body>
</html>