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
                <button onclick="switch3DMap(false,this)">切换3D</button>
                <button style="display: none" onclick="switch3DMap(true,this)">切换普通地图</button>
            </td>
        </tr>
        <tr>
            <td>
                <input id="tipinput"/>
            </td>
        </tr>
    </table>
</div>
<script src="http://code.jquery.com/jquery-2.1.1.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.10&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.MouseTool"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js?v=${assessVersion}"></script>

<script type="text/javascript">
    //初始化地图对象，加载地图
    var map = new AMap.Map("container", {resizeEnable: true, zoom: 19});
    //输入提示
    var autoOptions = {
        city: "成都",
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({});
    searchByName('${estateName}', '${center}');

    //根据名称查询
    function searchByName(name, point) {
        if (point) {
            try {
                var center = JSON.parse(point);
                map.setCenter([center.key, center.value]); //设置地图中心点
            } catch (e) {
                console.log(e);
            }
        } else if (name) {
            placeSearch.search(name, function (status, result) {
                if (result.info == 'OK') {
                    if (result.poiList.pois.length > 0) {
                        var poi = result.poiList.pois[0];
                        map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
                    }
                }
            })
        }
    }

    //注册监听，当选中某条记录时会触发
    AMap.event.addListener(auto, "select", function (e) {
        placeSearch.setCity(e.poi.adcode);
        searchByName(e.poi.name, null);
    });

    //地图点击事件
    map.on('click', function (e) {
        if ('${click}') {
            var excuteString = 'if (parent && parent.${click}) {';
            excuteString += 'parent.${click}(e.lnglat.getLng(), e.lnglat.getLat());}';
            eval(excuteString);
        }
    });


    var markerArray = [];//标记数组
    //加载所有标注信息
    function loadMarkerList(taggingArray) {
        map.remove(markerArray);
        markerArray = [];
        if (taggingArray && taggingArray.length > 0) {
            var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
            for (var i = 0; i < taggingArray.length; i++) {
                var tagging = taggingArray[i];
                var marker = new AMap.Marker({
                    position: [tagging.lng, tagging.lat],
                    title: tagging.name,
                    map: map
                });
                marker.on('click', function (e) {
                    infoWindow.setContent(e.target.content);
                    infoWindow.open(map, e.target.getPosition());
                });
                markerArray.push(marker);
            }
            map.setFitView();
        }
    }

    function switch3DMap(flag, that) {
        if (!flag) {
            map = new AMap.Map('container', {
                resizeEnable: true,
                viewMode: '3D',
                pitch: 60,
                rotation: -35,
                // 隐藏默认楼块
                features: ['bg', 'road', 'point'],
                mapStyle: 'amap://styles/light',
                layers: [
                    // 高德默认标准图层
                    new AMap.TileLayer.Satellite(),
                    // 楼块图层
                    new AMap.Buildings({
                        zooms: [16, 18],
                        zIndex: 10,
                        heightFactor: 2//2倍于默认高度，3D下有效
                    })
                ],
                zoom: 16
            });
        }

        if (flag) {
            map = new AMap.Map("container", {resizeEnable: true, zoom: 19});
        }

        var taggingArray = [];
        if (markerArray.length >= 1) {
            markerArray.forEach(function (n, i) {
                if (n.C) {
                    if (n.C.position) {
                        taggingArray.push(n.C.position);
                    }
                }
            });
        }
        if (taggingArray.length >= 1) {
            loadMarkerList(taggingArray);
        }
        if (that) {
            var text = $(that).text();
            $(that).closest("td").find("button").each(function (i, btn) {
                if (text == $(btn).text()) {
                    $(btn).hide();
                } else {
                    $(btn).show();
                }
            });
        }

        // 地图 加载完成
        map.on("complete", function () {
            //地图点击事件
            //地图点击事件
            map.on('click', function (e) {
                if ('${click}') {
                    var excuteString = 'if (parent && parent.${click}) {';
                    excuteString += 'parent.${click}(e.lnglat.getLng(), e.lnglat.getLat());}';
                    eval(excuteString);
                }
            });
        });

    }
</script>
</body>
</html>