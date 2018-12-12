<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/share/main_css.jsp" %>
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
        <tr>
            <td>
                <button class="btn btn-default" onclick="rotateTransform(true)">右旋转</button>
                <button class="btn btn-default" onclick="rotateTransform(false)">左旋转</button>
            </td>
        </tr>
    </table>
    <img id="oImg" src="${pageContext.request.contextPath}${huxingImg}" style="display: none">
</div>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.10&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>

<script type="text/javascript">

    var config = {};
    config.imageUrl = "${pageContext.request.contextPath}${huxingImg}";
    config.position = {lng: 104.084335, lat: 30.590403};
    console.log("${pageContext.request.contextPath}${huxingImg}");
    var imgMarker = null;
    var map = null;
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({});

    $(document).ready(function () {
        //初始化地图对象，加载地图
        map = new AMap.Map("container", {
            resizeEnable: true,
            zoom: 19,
            center: [config.position.lng, config.position.lat],
            complete: function () {

            }
        });
        //地图点击事件
        map.on('click', function (e) {

        });
        {
            var dimensions = getImgNaturalDimensions(document.getElementById("oImg"));
            // 创建一个 Icon
            var imgIcon = new AMap.Icon({
                // 图标尺寸
                size: new AMap.Size(Number(dimensions.w) * 10, Number(dimensions.h) * 10),
                // 图标的取图地址
                image: config.imageUrl,
                // 图标所用图片大小
                imageSize: new AMap.Size(Number(dimensions.w) / 2.5, Number(dimensions.h) / 2.5),
                // 图标取图偏移量
                imageOffset: new AMap.Pixel(-1, -1)
            });
            imgMarker = new AMap.Marker({
                position: [config.position.lng, config.position.lat], // 经纬度对象
                icon: imgIcon,
                offset: new AMap.Pixel(-13, -30),
                autoRotation: true,
                extData: {id: 'startMarker'},
                angle: 20
            });
            map.add(imgMarker);
        }
    });

    //根据名称查询
    function searchByName(name) {
        if (name) {
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
        searchByName(e.poi.name);
    });

    function getImgNaturalDimensions(oImg) {

        var nWidth, nHeight;
        if (oImg.naturalWidth) { // 现代浏览器
            nWidth = oImg.naturalWidth;
            nHeight = oImg.naturalHeight;
            return {w: nWidth, h: nHeight};
        } else { // IE6/7/8
            var nImg = new Image();
            nImg.src = oImg.src;
            nImg.onload = function () {
                nWidth = nImg.width;
                nHeight = nImg.height;
                return {w: nWidth, h: nHeight};
            };
        }
    }

    function rotateTransform(flag) {
        var angle = Number(imgMarker.getAngle());
        console.log(imgMarker);
        console.log(imgMarker.getIcon());
        if (flag) {
            imgMarker.setAngle(angle + 5);
        } else {
            imgMarker.setAngle(angle - 5);
        }
    }

</script>
</body>
</html>