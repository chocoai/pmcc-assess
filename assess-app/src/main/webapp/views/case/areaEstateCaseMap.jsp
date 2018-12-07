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
    areaMap.createMap = function (lng,lat) {
        map = new AMap.Map('container', {
            resizeEnable: true,
            center: [lng, lat],
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
        console.log(extData);
        $.ajax({
            url: "${pageContext.request.contextPath}/caseEstateTagging/listCaseEstateTagging",
            type: "get",
            dataType: "json",
            data: {pid: extData.id, type: areaMap.getType(extData.type)},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        areaMap.destroyMap(map);
                        areaMap.createMap(lng,lat);
                        $.each(data, function (i, n) {
                            if (AssessCommon.isNumber(n.lat) && AssessCommon.isNumber(n.lon)) {
                                var marker = areaMap.getMarker(n);
                                markerList.push(marker);
                            }
                        });
                        if (markerList.length >= 1) {
                            map.add(markerList);
                        }else {
                            Alert("数据不符合约定!或者经纬度有非数字的字符!");
                        }
                    }else {
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

    //创建一个覆盖物
    areaMap.getMarker = function (n) {
        var marker = new AMap.Marker({
            position: [Number(n.lon), Number(n.lat)], // 经纬度对象
            icon: areaMap.getIcon(), // 添加 Icon 实例
            // icon: "http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
            title: areaMap.isNotBlank(n.name) ? n.name : "未录入名称",
            extData: n
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
         * 鼠标右键单击事件 并且把此点标记为覆盖物
         */
        map.on('rightclick', function (e) {
            var str = e.lnglat.getLng() + " " + e.lnglat.getLat();
            Alert(str);
        });
    };

    $(document).ready(function () {
        areaMap.createMap(104.083199,30.593365);
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
