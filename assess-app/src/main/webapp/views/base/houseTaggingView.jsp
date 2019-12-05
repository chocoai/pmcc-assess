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
        <c:if test="${readonly ne true}">
            <tr>
                <td>
                    <button class="btn btn-default" onclick="rotateTransform(true)">右旋转</button>
                    <button class="btn btn-default" onclick="rotateTransform(false)">左旋转</button>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <button class="btn btn-default" onclick="zoom(true)">放大</button>
                <button class="btn btn-default" onclick="zoom(false)">缩小</button>
            </td>
        </tr>
    </table>
    <img id="oImg" src="${pageContext.request.contextPath}${huxingImg}" style="display: none;">
</div>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.10&key=ac9fb0371e0405ef74cb1ca003fd0eef"></script>
<script type="text/javascript">

    var config = {};
    /**
     * 判断字符串以及null等
     */
    config.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    //必要的变量
    var imgMarker = null;
    var map = null;
    var imgIcon = null;
    var lng = config.isNotBlank('${lng}') ? '${lng}' : 100;
    var lat = config.isNotBlank('${lat}') ? '${lat}' : 30;
    var deg = config.isNotBlank('${deg}') ? '${deg}' : 0;


    config.imageUrl = "${pageContext.request.contextPath}${huxingImg}";
    config.position = {
        lng: lng,
        lat: lat
    };
    config.deg = deg;
    config.attachmentId = '${attachmentId}';


    $(document).ready(function () {
        //初始化地图对象，加载地图
        map = new AMap.Map("container", {
            resizeEnable: true,
            rotateEnable: true,
            zoom: 19,
            center: [config.position.lng, config.position.lat],
            complete: function () {

            }
        });
        var dimensions = {};
        try {
            var oImg = $("#oImg");
            dimensions.w = oImg.width();
            dimensions.h = oImg.height();
        } catch (e) {
        }
        var width = config.isNotBlank(dimensions.w) ? parseFloat(dimensions.w) : 500;
        var height = config.isNotBlank(dimensions.h) ? parseFloat(dimensions.h) : 500;
        // 创建一个 Icon
        imgIcon = new AMap.Icon({
            // 图标尺寸
            size: new AMap.Size(width * 10, height * 10),
            // 图标的取图地址
            image:oImg.attr("src"),
            // 图标所用图片大小
            imageSize: new AMap.Size(width / 2.5, height / 2.5),
            // 图标取图偏移量
            imageOffset: new AMap.Pixel(-1, -1)
        });
        imgMarker = new AMap.Marker({
            position: [config.position.lng, config.position.lat], // 经纬度对象
            icon: imgIcon,
            offset: new AMap.Pixel(-13, -30),
            autoRotation: ${!readonly},
            extData: {id: 'startMarker'},
            angle: config.deg,
            draggable: ${!readonly} //开启拖拽
        });
        imgMarker.on('dragging', draggingFun);
        map.add(imgMarker);
    });

    /**
     * 拖拽事件监听函数
     */
    function draggingFun(e) {
        if (e.lnglat.lng) {
            config.position.lng = e.lnglat.lng;
        } else {
            config.position.lng = lng;
        }
        if (e.lnglat.lat) {
            config.position.lat = e.lnglat.lat;
        } else {
            config.position.lat = lat;
        }
    }

    //获取图片的宽度和高度
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


    /**
     *旋转监听函数
     */
    function rotateTransform(flag) {
        var angle = Number(imgMarker.getAngle());
        if (flag) {
            angle = angle + 5;
        } else {
            angle = angle - 5;
        }
        config.deg = angle;
        imgMarker.setAngle(config.deg);
    }


    /**
     * 放大和缩小
     * @param flag
     */
    function zoom(flag) {
        var size = imgIcon.getImageSize();
        if (flag) {
            imgIcon.setImageSize({
                height: Number(size.height) * 1.2,
                width: Number(size.width) * 1.2
            });
            imgMarker.setIcon(imgIcon);
        } else {
            imgIcon.setImageSize({
                height: Number(size.height) / 1.2,
                width: Number(size.width) / 1.2
            });
            imgMarker.setIcon(imgIcon);
        }
    }

</script>
</body>
</html>