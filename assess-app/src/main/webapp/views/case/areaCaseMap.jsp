<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js?v=${assessVersion}'></script>
    <script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="page-title">
                <div class="title_left">
                    <h2>
                        <i class="fa fa-map"></i>
                        案例地图
                    </h2>
                </div>
                <div class="title_right">
                    <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5 form-group pull-right">
                        <input type="text" id="txt_estate_search" class="form-control" placeholder="楼盘查询....">
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBaseHouse">
                        <div class="form-group">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="margin-top:10px;height: 700px;" id="container">

                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <div class="x_panel">
                <div class="x_content">

                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    var map = null;
    var areaMap = {};
    var config = {
        estate: "estate",
        building: "building",
        unit: "unit",
        house: "house"
    };

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
        // toastr.success(str.join('<br>'));
    };

    areaMap.onError = function (data) {
        toastr.success('定位失败!');
    };


    /**
     * 加载子地图
     */
    areaMap.onSonLoadMap = function (e) {
        var lng = Number(e.lnglat.getLng());
        var lat = Number(e.lnglat.getLat());
        var dataId = e.target.F.extData.dataId;
        var href = "${pageContext.request.contextPath}/basic/estateCaseMap";
        href += "?estateId=" + dataId;
        if (areaMap.isNotBlank(dataId)) {
            window.open(href, "");
        } else {
            Alert("无数据!");
        }

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
            '<div class="panel panel-body" style="width:95px;height:55px;">' +
            '<p style="text-overflow: ellipsis; white-space: nowrap;">' + name +
            '<img style="height:25.5px;width:19.8px;float: left;" src="http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png">' +
            '<div style="clear:both"></div>' +
            '</p>' +
            '</div>';
        return markerContent;
    };

    function getName(n) {
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

    /**
     * 楼盘搜索
     * @param item
     */
    areaMap.searchMarker = function (item) {
        var lat = null;
        var lon = null;
        if (this.isNotBlank(item)) {
            var data = map.getAllOverlays('marker');
            for (var i = 0; i < data.length; i++) {
                var marker = data[i];
                var extData = marker.getExtData();
                if (extData.dataId == item) {
                    lon = extData.lon;
                    lat = extData.lat;
                    break;
                }
            }
        }
        if (this.isNotBlank(lon)) {
            map.setCenter([lon, lat]); //设置地图中心点
            map.setZoom(18);
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

        $("#txt_estate_search").apEstate({
            onSelect: function (id, name) {
                areaMap.searchMarker(id);
            }
        });
    });
</script>
