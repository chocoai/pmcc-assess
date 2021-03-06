<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-21
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">

    <script type="text/javascript"
            src="//webapi.amap.com/maps?v=1.4.15&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.ToolBar,AMap.PlaceSearch,AMap.MouseTool,AMap.RectangleEditor,AMap.CitySearch"></script>
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <link rel='stylesheet' type='text/css' href='https://a.amap.com/jsapi_demos/static/demo-center/css/prety-json.css'>
    <script type='text/javascript'
            src='https://a.amap.com/jsapi_demos/static/demo-center/js/underscore-min.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/backbone-min.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'></script>


    <script src="${pageContext.request.contextPath}/assets/html2canvas/html2canvas.js?v=${assessVersion}"></script>

</head>
<body>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                                    <div id="toolMapHandleContainer" style="height:650px;">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script>

    var drawPolygon = {};

    drawPolygon.map = null;
    drawPolygon.mouseTool = null;
    drawPolygon.placeSearch = null;
    drawPolygon.defaultObj = null;
    drawPolygon.box = $("#divPolygonBox");
    drawPolygon.textBox = $("#divTextBox");


    drawPolygon.fillColor = '#00b0ff'; //多边形填充颜色，使用16进制颜色代码赋值，如：#00B2D5
    drawPolygon.fillOpacity  = '0.3';//多边形填充透明度，取值范围 [0,1] ，0表示完全透明，1表示不透明。默认为0.5
    drawPolygon.strokeWeight  = '2';//轮廓线宽度
    drawPolygon.strokeColor  = '#80d8ff';//线条颜色，使用16进制颜色代码赋值。默认值为#006600


    drawPolygon.jsonData = [];
    drawPolygon.overlays = [];

    drawPolygon.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    drawPolygon.loadMap = function (callback) {
        //初始化地图对象，加载地图
        drawPolygon.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            zoom: 24,
            viewMode: "2D",
            center: [116.403322, 39.920255]
        });
        // 地图 加载完成 load
        drawPolygon.map.on("complete", function () {


            //输入提示
            var autoOptions = {
                input: "tipinput"
            };
            var auto = new AMap.Autocomplete(autoOptions);
            //注册监听，当选中某条记录时会触发
            AMap.event.addListener(auto, "select", function (e) {
                var tempLocation = e.poi.location ;
                if (tempLocation) {
                    drawPolygon.map.setCenter([tempLocation.lng, tempLocation.lat]);
                }else {
                    drawPolygon.autoCompleteSearch(e.poi.name);
                }
            });

            (function (estateName) {
                var basicEstateTaggingStr = '${basicEstateTagging}';
                var center = null;
                if (basicEstateTaggingStr) {
                    try {
                        var center2 = JSON.parse(basicEstateTaggingStr);
                        center2 = new AMap.LngLat(center2.lng, center2.lat);
                        center = center2;
                    } catch (e) {
                        console.log(e);
                    }
                }
                if (center) {
                    drawPolygon.map.setCenter(center); //设置地图中心点
                } else {
                    if (estateName && estateName != 'undefined') {
                        drawPolygon.autoCompleteSearch(estateName);
                    }
                }
            }('${estateName}'));

            (function (tt) {
                if (!tt) {
                    return false;
                }
                var item = [];
                try {
                    item = JSON.parse(tt);
                } catch (e) {
                }
                drawPolygon.createOverlay(item);
            }('${formData}'));

            if (callback) {
                callback();
            }
        });
    };

    /**
     * 3d 和 2d 切换
     */
    drawPolygon.switch3DMap = function (_this) {
        var value = $(_this).attr("data-value");
        if (value == '2D') {
            var layers = [
                // 高德默认标准图层
                new AMap.TileLayer.Satellite(),
                // 楼块图层
                new AMap.Buildings({
                    zooms: [16, 18],
                    zIndex: 10,
                    heightFactor: 2//2倍于默认高度，3D下有效
                })
            ];
            drawPolygon.map.setLayers(layers);
            drawPolygon.map.setPitch(75, false, 1);
            $(_this).attr("data-value", "3D");
        } else {
            var overlays = drawPolygon.map.getAllOverlays();
            drawPolygon.map.destroy();
            drawPolygon.loadMap(function () {
                drawPolygon.map.add(overlays);
            });
            $(_this).attr("data-value", "2D");
        }
    };

    drawPolygon.autoCompleteSearch = function (name) {
        var placeSearch = new AMap.PlaceSearch({});
        placeSearch.search(name, function (status, result) {
            if (result.info == 'OK') {
                if (result.poiList.pois.length > 0) {
                    var poi = result.poiList.pois[0];
                    drawPolygon.map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
                }
            }else {
                var citySearch = new AMap.CitySearch();
                citySearch.getLocalCity(function (status, result) {
                    if (status === 'complete' && result.info === 'OK') {
                        var bounds = result.bounds;
                        drawPolygon.map.setCenter(bounds); //设置地图中心点
                    }
                });
            }
        })
    };



    /**
     * 创建文本标记
     */
    drawPolygon.createLabelMarker = function (lnglat, item, map) {
        // 创建一个 LabelMarker 实例
        var Text = new AMap.Text({
            position: lnglat,
            anchor: 'bottom-center',//位置
            text: item.name,
            extData: item,
            draggable: false,//是否可以拖动
//            style: {'background-color': 'red'}
        });
        map.add(Text);
    };



    /**
     * 清除覆盖物
     */
    drawPolygon.clearMap = function () {
        drawPolygon.map.clearMap(); // 使用clearMap方法删除所有覆盖物
        drawPolygon.overlays.length = 0;
        drawPolygon.jsonData.length = 0;
    };


    /**
     * 创建覆盖物
     */
    drawPolygon.createOverlay = function (data) {
        if (!$.isArray(data)) {
            return false;
        }
        $.each(data, function (i, item) {
            var fillColor = drawPolygon.fillColor;
            if (item.fillColor) {
                fillColor = item.fillColor;
            }
            var polygon = new AMap.Polygon({
                path: item.path,
                fillColor: fillColor,
                fillOpacity: drawPolygon.fillOpacity,
                strokeWeight: drawPolygon.strokeWeight,
                strokeColor: drawPolygon.strokeColor ,
                map: drawPolygon.map
            });
            var title = item.extData.title;
            if ($.isArray(title)) {
                $.each(title, function (j, n) {
                    n.pid = polygon._amap_id;
                    n.id = null;
                    drawPolygon.createLabelMarker(new AMap.LngLat(n.lng, n.lat), n, drawPolygon.map);
                });
            }
        });
    };




    /**
     * 去除重复
     * @param arr
     * @returns {*}
     */
    drawPolygon.unique = function (arr) {
        for (var i = 0; i < arr.length; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i]._amap_id == arr[j]._amap_id) {
                    //如果第一个等于第二个，splice方法删除第二个
                    arr.splice(j, 1);
                    j--;
                }
            }
        }
        return arr;
    };

    drawPolygon.html2canvas = function () {
        var target = $("#toolMapHandleContainer");
        $(document).ready(function () {
            Loading.progressShow();
            html2canvas(target[0]).then(function (canvas) {
                var dataUrl = canvas.toDataURL();
                var type = 'png';
                var imgData = canvas.toDataURL(type);//canvas转换为图片
                // 加工image data，替换mime type，方便以后唤起浏览器下载
                imgData = imgData.replace(drawPolygon.onFixType(type), 'image/octet-stream');
                drawPolygon.fileDownload(imgData);
                Loading.progressHide();
            });
        });
    };

    /**
     * 唤起浏览器下载
     * @param downloadUrl
     */
    drawPolygon.fileDownload = function (downloadUrl) {
        var date = new Date();
        var title = "图片截取时间:";
        title += date.getFullYear().toString() + "年" + "_";
        title += (date.getMonth() + 1).toString() + "月" + "_";
        title += date.getDate().toString() + "日" + "_";
        title += date.getHours().toString() + "时" + "_";
        title += date.getMinutes().toString() + "分" + "_";
        title += date.getSeconds().toString() + "秒" + "";
        var aLink = document.createElement('a');
        aLink.style.display = 'none';
        aLink.href = downloadUrl;
        aLink.download = title + ".png";
        // 触发点击-然后移除
        document.body.appendChild(aLink);
        aLink.click();
        document.body.removeChild(aLink);
    };

    /**
     * 图片类型转换
     * @param type
     * @returns {string}
     */
    drawPolygon.onFixType = function (type) {
        type = type.toLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };


    $(document).ready(function () {
        drawPolygon.loadMap();
    });


</script>

<script type="application/javascript">


</script>

</html>
