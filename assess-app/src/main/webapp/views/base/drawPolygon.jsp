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
<div id="divPolygonBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">描述内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="lng">
                    <input type="hidden" name="lat">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                标题<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-9  col-sm-9  col-md-9  col-lg-9">
                                                <input type="text" placeholder="标题"
                                                       name="name" required="required"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm"
                        onclick="drawPolygon.cancelSaveTitle();">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="drawPolygon.saveTitleData() ;">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divTextBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">描述内容</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="lng">
                    <input type="hidden" name="lat">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-9  col-sm-9  col-md-9  col-lg-9">
                                                <input type="text" placeholder="名称"
                                                       name="name" required="required"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm"
                        onclick="drawPolygon.cancelSaveTitle();">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="drawPolygon.saveTextData() ;">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-3 col-md-2">
                                        <ul class="nav nav-pills nav-secondary" id="pills-tab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active show" data-toggle="pill"
                                                   href="#tab_content_drawing" role="tab" id="tab_content_drawing_nav"
                                                   onclick="drawPolygon.handleMouseTool(false);"
                                                   aria-selected="true">绘图状态</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="pill"
                                                   href="#tab_content_description" role="tab"
                                                   id="tab_content_description_nav"
                                                   onclick="drawPolygon.handleMouseTool(true);"
                                                   aria-selected="false">描述状态</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-9 col-md-10">
                                        <div class="tab-content">
                                            <div class="tab-pane fade show active" id="tab_content_drawing"
                                                 role="tabpanel">
                                                <div class="form-horizontal">
                                                    <div class="row form-group">
                                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                            <input type="text" class="form-control input-full" value=""
                                                                   placeholder="搜索...." name="mapSearchName"
                                                                   id="tipinput">
                                                        </div>
                                                        <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                                                            <button style="margin-left: 5px"
                                                                    class="btn btn-secondary btn-sm" type="button"
                                                                    onclick="drawPolygon.clearMap() ;">
                                                                清除所有标记
                                                            </button>
                                                            <button style="margin-left: 5px"
                                                                    class="btn btn-secondary btn-sm"
                                                                    type="button"
                                                                    onclick="drawPolygon.html2canvas(this) ;">
                                                                截取到平面图
                                                            </button>
                                                            <button style="margin-left: 5px"
                                                                    class="btn btn-secondary btn-sm"
                                                                    data-value="2D"
                                                                    type="button"
                                                                    onclick="drawPolygon.switch3DMap(this) ;">
                                                                平面/卫星图切换
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade " id="tab_content_description"
                                                 role="tabpanel">
                                                <div class="form-horizontal">
                                                    <div class="row form-group">
                                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                            <input type="text" class="form-control input-full" value=""
                                                                   placeholder="搜索...." name="mapSearchName"
                                                                   id="tipinputDescription">
                                                        </div>
                                                        <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                                                            <button style="margin-left: 5px"
                                                                    class="btn btn-secondary btn-sm"
                                                                    type="button"
                                                                    onclick="drawPolygon.html2canvas(this) ;">
                                                                截取到平面图
                                                            </button>
                                                            <button style="margin-left: 5px"
                                                                    class="btn btn-secondary btn-sm"
                                                                    data-value="2D"
                                                                    type="button"
                                                                    onclick="drawPolygon.switch3DMap(this) ;">
                                                                平面/卫星图切换
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<script type="text/javascript">
    var drawPolygon = {};
    drawPolygon.map = null;
    drawPolygon.mouseTool = null;
    drawPolygon.placeSearch = null;
    drawPolygon.defaultObj = null;
    drawPolygon.box = $("#divPolygonBox");
    drawPolygon.textBox = $("#divTextBox");
    drawPolygon.colorBox = $("#divPolygonColorBox");

    drawPolygon.tabDescription = $("#tab_content_description_nav");
    drawPolygon.tabDrawing = $("#tab_content_drawing_nav");

    drawPolygon.fillColor = '#00b0ff'; //多边形填充颜色，使用16进制颜色代码赋值，如：#00B2D5
    drawPolygon.fillOpacity = '0.3';//多边形填充透明度，取值范围 [0,1] ，0表示完全透明，1表示不透明。默认为0.5
    drawPolygon.strokeWeight = '2';//轮廓线宽度
    drawPolygon.strokeColor = '#80d8ff';//线条颜色，使用16进制颜色代码赋值。默认值为#006600


    drawPolygon.jsonData = [];
    drawPolygon.overlays = [];

    drawPolygon.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    drawPolygon.loadMap = function (callback , flagTitle) {
        //初始化地图对象，加载地图
        drawPolygon.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            zoom: 24,
            city: "${cityName}",
            viewMode: "2D"
        });
        // 地图 加载完成 load
        drawPolygon.map.on("complete", function () {
            //输入提示
            var tipIds = ["tipinputDescription", "tipinput"];
            $.each(tipIds, function (m, input) {
                var autoOptions = {
                    input: input
                };
                var auto = new AMap.Autocomplete(autoOptions);
                //注册监听，当选中某条记录时会触发
                AMap.event.addListener(auto, "select", function (e) {
                    drawPolygon.autoCompleteSearch(e.poi.name);
                });
            });

            if(flagTitle){
                (function (tt) {
                    if (!tt) {
                        //地图加载完成后要做的事
                        drawPolygon.completeEvent();
                        return false;
                    }
                    var item = [];
                    try {
                        item = JSON.parse(tt);
                    } catch (e) {
                    }
                    drawPolygon.createOverlay(item);
                    drawPolygon.handleJquery(drawPolygon.tabDescription).trigger('click');
                    var overlays = drawPolygon.map.getAllOverlays();
                    console.log(overlays) ;
                    console.log(item) ;
                }('${formData}'));
            }

            if (callback) {
                callback();
            }

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

        });
    };

    /**
     * 3d 和 2d 切换
     */
    drawPolygon.switch3DMap = function (_this) {
        var value = $(_this).attr("data-value");
        var overlays = drawPolygon.map.getAllOverlays();
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
            var polygons = drawPolygon.map.getAllOverlays('polygon');
            var markers = drawPolygon.map.getAllOverlays('marker');
            drawPolygon.map.destroy();
            drawPolygon.loadMap(function () {
                drawPolygon.map.add(overlays);
            } ,false);
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
                } else {
                    var citySearch = new AMap.CitySearch();
                    citySearch.getLocalCity(function (status, result) {
                        if (status === 'complete' && result.info === 'OK') {
                            var bounds = result.bounds;
                            drawPolygon.map.setCenter(bounds); //设置地图中心点
                        }
                    });
                }
            }
        })
    };

    /**
     * 鼠标工具开启和关闭
     */
    drawPolygon.handleMouseTool = function (param) {
        var polygons = drawPolygon.map.getAllOverlays('polygon');
        if (param) {
            if (drawPolygon.mouseTool) {
                drawPolygon.mouseTool.close(true);//关闭
                drawPolygon.mouseTool = undefined;
            }
            drawPolygon.showOverlay(polygons);
        } else {
            if (!drawPolygon.mouseTool) {
                drawPolygon.completeEvent();
                //关闭描述 事件
                $.each(polygons, function (j, polygon) {
                    polygon.off('click', drawPolygon.eventOverlay);
                });
            }
        }
    };

    drawPolygon.completeEvent = function () {
        drawPolygon.mouseTool = new AMap.MouseTool(drawPolygon.map);
        drawPolygon.mouseTool.polygon({
            fillColor: drawPolygon.fillColor,
            strokeWeight: drawPolygon.strokeWeight,
            strokeColor: drawPolygon.strokeColor,
            fillOpacity: drawPolygon.fillOpacity
        });
        //添加事件
        AMap.event.addListener(drawPolygon.mouseTool, 'draw', function (e) {
        });
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
            draggable: true,//是否可以拖动
//            style: {'background-color': 'red'}
        });
        map.add(Text);
        item.id = Text._amap_id;
        Text.setExtData(item);
        //单击事件
        Text.on('click', function (e) {
            var box = drawPolygon.handleJquery(drawPolygon.textBox);
            box.modal("show");
            var target = e.target;
            var data = target.getExtData();//target.getText();
            var frm = box.find("form");
            frm.clearAll().initForm(data);
            drawPolygon.tempData = target;
        });
        //拖拽  事件
        Text.on('dragend', function (e) {
            var target = e.target;
            var data = target.getExtData();//target.getText();
            var position = target.getPosition();
            $.extend(data, {lng: position.getLng(), lat: position.getLat()});
            drawPolygon.updateExtData(data);
        });
        drawPolygon.updateExtData(item);
    };

    /**
     * 保存标题
     */
    drawPolygon.saveTextData = function () {
        var box = drawPolygon.handleJquery(drawPolygon.textBox);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        var target = drawPolygon.tempData;
        target.setText(data.name);
        target.setExtData(data);
        box.modal("hide");
        drawPolygon.tempData = null;
        drawPolygon.updateExtData(data);
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
     * 显示覆盖物
     */
    drawPolygon.showOverlay = function (overlays) {
        overlays = drawPolygon.unique(overlays);
        if (overlays.length != 0) {
            $.each(overlays, function (i, overlay) {
                overlay.on('click', drawPolygon.eventOverlay);
                overlay.on('rightclick', function (e) {
                    // drawPolygon.setOverlayFillColor(e);
                });
                drawPolygon.map.add(overlay);
            });
        }
    };

    drawPolygon.selectColor = function () {
        var box = drawPolygon.handleJquery(drawPolygon.colorBox);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        box.modal("hide");
        var overlays = drawPolygon.map.getAllOverlays();
        $.each(overlays, function (i, overlay) {
            if (overlay._amap_id == data.pid) {
                var attributeData = overlay.getOptions();
                attributeData.fillColor = data.color;
                overlay.setOptions(attributeData);
            }
        });
    };

    /**
     * 覆盖物 背景颜色选择填充
     */
    drawPolygon.setOverlayFillColor = function (e) {
        var box = drawPolygon.handleJquery(drawPolygon.colorBox);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll().initForm({pid: e.target._amap_id});
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
                strokeColor: drawPolygon.strokeColor,
                map: drawPolygon.map
            });

            // polygon.on('click', function (e) {
            //     drawPolygon.eventOverlay(e);
            // });

            polygon.on('click', drawPolygon.eventOverlay);

            polygon.on('rightclick', function (e) {
                // drawPolygon.setOverlayFillColor(e);
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
     * 覆盖物事件
     * @param e
     */
    drawPolygon.eventOverlay = function (e) {
        var box = drawPolygon.handleJquery(drawPolygon.box);
        box.modal("show");
        var frm = box.find("form");
        var lnglat = e.lnglat;
        frm.clearAll().initForm({pid: e.target._amap_id, lng: lnglat.lng, lat: lnglat.lat});
        drawPolygon.tempData = lnglat;
    };

    /**
     * 更新数据
     */
    drawPolygon.updateExtData = function (data) {
        var overlays = drawPolygon.map.getAllOverlays();
        $.each(overlays, function (i, overlay) {
            if (overlay._amap_id == data.pid) {
                var item = overlay.getExtData();
                if (item.title) {
                    var k = 1;
                    $.each(item.title, function (j, n) {
                        if (n.id == data.id) {
                            n.name = data.name;
                            k++;
                        }
                    });
                    if (k == 1) {
                        item.title.push(data);
                    }
                } else {
                    item.title = [data];
                }
                overlay.setExtData(item);
            }
        });
    };

    /**
     * 收集数据
     */
    drawPolygon.getFormData = function () {
        var polygon = drawPolygon.map.getAllOverlays('polygon');
        var jsonData = [];
        if (polygon.length != 0) {
            $.each(polygon, function (i, overlay) {
                var path = [];
                $.each(overlay.getPath(), function (i, item) {
                    path.push([item.lng, item.lat]);
                });
                var attributeData = overlay.getOptions();
                var obj = {path: path, extData: overlay.getExtData(), fillColor: attributeData.fillColor};
                jsonData.push(obj);
            });
        }
        drawPolygon.jsonData = jsonData;
        return jsonData;
    };

    //layui 无法直接调用 drawPolygon.getFormData
    function getFormDrawPolygonData() {
        var data = drawPolygon.getFormData() ;
        console.log(JSON.stringify(data)) ;
        return data;
    }

    function getFormDrawCenterData() {
        return drawPolygon.map.getCenter();
    }


    drawPolygon.saveTitleData = function () {
        var box = drawPolygon.handleJquery(drawPolygon.box);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        box.modal("hide");
        drawPolygon.createLabelMarker(drawPolygon.tempData, data, drawPolygon.map);
        drawPolygon.tempData = null;
    };

    drawPolygon.cancelSaveTitle = function () {
        drawPolygon.tempData = null;
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
                // drawPolygon.fileDownload(imgData);
                if ('${callback}') {
                    var excuteString = 'if (parent && parent.${callback}) {';
                    var dataResult = drawPolygon.getFormData() ;
                    excuteString += 'parent.${callback}(' + "'" + canvas.toDataURL('image/jpeg') + "'" + ')' + ';';
                    <%--excuteString += 'parent.${callback}(' + "'" + JSON.stringify(dataResult) + "'" + ')' + ';';--%>
                    excuteString += '}';
                    try {
                        eval(excuteString);
                        AlertSuccess("成功", "图片成功截取并且保存到楼盘总平面图");
                    } catch (e) {
                        console.log(excuteString);
                        console.log(e);
                    }
                }
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
        drawPolygon.loadMap(null,true);
    });


</script>

<script type="application/javascript">


</script>

</html>
