<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        区域楼盘案例
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBaseHouse">
                        <div class="form-group">
                            <div class="col-sm-12" style="margin-top:10px;height: 700px;" id="container">

                            </div>
                        </div>
                    </form>
                </div>
                <div class="x_content">
                    <img id="oImg" src="${pageContext.request.contextPath}/image/B69B3AFB.png" style="display: none">
                    <div class="form-group" style="display: none">
                        <div class="col-sm-12" style="margin-top:10px;height:50px;">
                            <button class="btn btn-default" onclick="areaMap.rotateTransform(true)">右旋转</button>
                            <button class="btn btn-default" onclick="areaMap.rotateTransform(false)">左旋转</button>
                            <button class="btn btn-default" onclick="areaMap.zoom(true)">放大</button>
                            <button class="btn btn-default" onclick="areaMap.zoom(false)">缩小</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
</html>
<script type="text/javascript">
    var map = null;
    var imgMarker = null;
    var imgIcon = null;
    var areaMap = {};

    /**
     * 判断字符串以及null等
     */
    areaMap.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * 判断对象
     */
    areaMap.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    /**
     * 创建地图
     */
    areaMap.createMap = function (lng, lat, zoom) {
        map = new AMap.Map('container', {
            resizeEnable: true, //是否监控地图容器尺寸变化
            center: [lng, lat], //初始地图中心点
            zoom: zoom,// //初始地图级别
            showIndoorMap: false,//关闭室内地图
            lang: "zh_cn" //可选值：en，zh_en, zh_cn
        });
        areaMap.event();
    };

    /**
     * 销毁地图
     */
    areaMap.destroyMap = function (map) {
        map && map.destroy();
    };

    //解析定位结果
    areaMap.onComplete = function (data) {
        var str = ['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        if (data.accuracy) {
            str.push('精度：' + data.accuracy + ' 米');
        }//如为IP精确定位结果则没有精度信息
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        toastr.success(str.join('<br>'));
    };

    areaMap.onError = function (data) {
        toastr.success('定位失败!');
    };

    /**
     * @author:  zch
     * 描述:获取类型 (com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum)
     * @date:2018-12-07
     **/
    areaMap.getType = function (type) {
        var config = {
            estate: "estate",
            building: "building",
            unit: "unit",
            house: "house"
        };
        if (this.isNotBlank(type)) {
            if (type == config.estate) {
                return config.building;
            }
            if (type == config.building) {
                return config.unit;
            }
            if (type == config.unit) {
                return config.house;
            }
        } else {
            return null;
        }
    };

    /**
     * 加载子地图
     */
    areaMap.onSonLoadMap = function (e) {
        var lng = Number(e.lnglat.getLng());
        var lat = Number(e.lnglat.getLat());
        var extData = e.target.F.extData;
        var markerList = [];
        $.ajax({
            url: "${pageContext.request.contextPath}/caseEstateTagging/listCaseEstateTagging",
            type: "get",
            dataType: "json",
            data: {estateId: extData.estateId, type: areaMap.getType(extData.type)},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        areaMap.destroyMap(map);
                        areaMap.createMap(lng, lat, 18);
                        $.each(data, function (i, n) {
                            if (AssessCommon.isNumber(n.lat) && AssessCommon.isNumber(n.lon)) {
                                var marker = areaMap.getMarker(n);
                                markerList.push(marker);
                            }
                        });
                        if (markerList.length >= 1) {
                            if (areaMap.getType(extData.type) == 'house') {
                                console.log(data[0]);
                                map.add(areaMap.createImgMarker(data[0]));
                            } else {
                                map.add(markerList);
                            }
                        } else {
                            Alert("数据不符合约定!或者经纬度有非数字的字符!");
                        }
                    } else {
                        Alert("无子标记");
                    }
                }
                else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    // 创建一个 icon
    areaMap.getIcon = function () {
        var icon = new AMap.Icon({
            size: new AMap.Size(53, 68),    // 图标尺寸
            image: 'http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',  // Icon的图像
            imageOffset: new AMap.Pixel(0, 0),  // 图像相对展示区域的偏移量，适于雪碧图等
            imageSize: new AMap.Size(53, 68)   // 根据所设置的大小拉伸或压缩图片
        });
        return icon;
    };

    //自定义icon (主要是便于识别)
    areaMap.getContent = function (name) {
        var markerContent = '' +
            '<div class="panel panel-body" style="width:60px;height:110px;">' +
            '<img style="height:34px;width:26.5px;" src="http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png">' + name +
            // '<div class="btn btn-default" onclick=""></div>' +
            '</div>';
        return markerContent;
    };

    function getName(n) {
        var config = {
            estate: "estate",
            building: "building",
            unit: "unit",
            house: "house"
        };
        if (n.type == config.estate) {
            return areaMap.isNotBlank(n.name) ? n.name : "暂无名称";
        }
        if (n.type == config.building) {
            return areaMap.isNotBlank(n.name) ? n.name + "栋" : "暂无名称";
        }
        if (n.type == config.unit) {
            return areaMap.isNotBlank(n.name) ? n.name + "单元" : "暂无名称";
        }
        if (n.type == config.house) {
            return areaMap.isNotBlank(n.name) ? n.name + "房屋" : "暂无名称";
        }
        return "";
    }


    //创建一个覆盖物
    areaMap.getMarker = function (n) {
        var marker = new AMap.Marker({
            position: [Number(n.lon), Number(n.lat)], // 经纬度对象
            icon: areaMap.getIcon(), // 添加 Icon 实例
            // icon: "http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
            title: getName(n),
            extData: n,
            content: areaMap.getContent(getName(n)) //会使icon无效
        });
        marker.on('click', areaMap.onSonLoadMap);
        return marker;
    };

    //创建一个图片覆盖物
    areaMap.createImgMarker = function (n) {
        var dimensions = areaMap.getImgNaturalDimensions(document.getElementById("oImg"));
        $("#oImg").next().show();
        // 创建一个 Icon
        imgIcon = new AMap.Icon({
            // 图标尺寸
            size: new AMap.Size(Number(dimensions.w) / 2, Number(dimensions.h) / 2),
            // 图标的取图地址
            image: "${pageContext.request.contextPath}/image/B69B3AFB.png",
            // 图标所用图片大小
            imageSize: new AMap.Size(Number(dimensions.w) / 2, Number(dimensions.h) / 2),
            // 图标取图偏移量
            imageOffset: new AMap.Pixel(-1, -1)
        });
        imgMarker = new AMap.Marker({
            position: new AMap.LngLat(Number(n.lon), Number(n.lat)), // 经纬度对象
            icon: imgIcon,
            offset: new AMap.Pixel(-13, -30),
            autoRotation: true,
            extData: {id: 'startMarker'},
            angle: 20
        });
        return imgMarker;
    };

    /**
     * 创建标记物并且对标记物添加事件
     */
    areaMap.createMarker = function () {
        var mapList = null;
        var markerList = [];
        try {
            mapList = JSON.parse('${mapList}');
        } catch (e) {
            console.error(e);
        }

        if (this.isNotBlankObject(mapList)) {
            $.each(mapList, function (i, n) {
                if (AssessCommon.isNumber(n.lat) && AssessCommon.isNumber(n.lon)) {
                    var marker = areaMap.getMarker(n);
                    markerList.push(marker);
                }
            });
            if (markerList.length >= 1) {
                map.add(markerList);
            }
        }
    };

    areaMap.event = function () {
        /**
         * 鼠标右键单击事件
         */
        map.on('rightclick', function (e) {
            var str = "<div class='panel panel-default'>";
            str += "<div class='panel-body'>";
            str += "经度:" + e.lnglat.getLng() + "<br>";
            str += "纬度:" + e.lnglat.getLat() + "<br>";
            map.getCity(function (info) {
                str += "省:" + info.province + "<br>";
                str += "市:" + info.city + "<br>";
                str += "区/县/县级市:" + info.district + "<br>";
                str += "</div>";
                str += "</div>";
                Alert(str);
            });
        });
    };

    areaMap.rotateTransform = function (flag) {
        var angle = Number(imgMarker.getAngle());
        if (flag) {
            imgMarker.setAngle(angle + 5);
        } else {
            imgMarker.setAngle(angle - 5);
        }
    };

    areaMap.zoom = function (flag) {
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
    };

    /**
     * 获取原始图片的长度和宽度
     * @param oImg
     * @param callback
     */
    areaMap.getImgNaturalDimensions = function (oImg) {
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
    };


    $(document).ready(function () {
        areaMap.createMap(104.083199, 30.593365, 12);
        //自动定位
        map.plugin('AMap.Geolocation', function () {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition: 'LT'  //定位按钮可停靠的位置 [“LT”：左上角] [“LB”：左下角] [“RT”：右上角] [“RB”：右下角]
            });
            map.addControl(geolocation);//获取用户当前的精确位置信息
            // map.watchPosition();//移动端有效
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', areaMap.onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', areaMap.onError);      //返回定位出错信息
        });
        areaMap.createMarker();
    });
</script>
