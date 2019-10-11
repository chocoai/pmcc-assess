/**
 * Created by zch on 2019-10-8.
 * 地图插件
 */

document.write('<script type="text/javascript" src="//webapi.amap.com/maps?v=1.4.15&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.ToolBar,AMap.PlaceSearch,AMap.MouseTool,AMap.RectangleEditor"></script>');
document.write('<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>');
document.write("<link rel='stylesheet' type='text/css' href='https://a.amap.com/jsapi_demos/static/demo-center/css/prety-json.css'> </link>");
document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/underscore-min.js'> </script>");
document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/backbone-min.js'> </script>");
document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'> </script>");
document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js'> </script>");
document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'> </script>");


var toolMapHandleFun = {};

toolMapHandleFun.map = null;
toolMapHandleFun.mouseTool = null;
toolMapHandleFun.placeSearch = null;
toolMapHandleFun.defaultObj = null;

toolMapHandleFun.config = {
    draw: {
        marker: {key: "marker", name: "点标记"},
        polyline: {key: "polyline", name: "折线"},
        polygon: {key: "polygon", name: "多边形"},
        circle: {key: "circle", name: "圆形"},
        rectangle: {key: "rectangle", name: "矩形"}
    },
    //地图存储的类型
    storage: {
        data: {key: "map.storage.state.data", name: "地图数据状态"},
        img: {key: "map.storage.state.img", name: "地图图像状态"},
        instantaneousLife: {key: "map.storage.state.instantaneous.life", name: "地图瞬时状态"},
        select: {key: "map.storage.state.select", name: "自己选择"}
    },
    //地图显示方式 (目前只处理这两类当然还有3D固定视角,三维模型等待日后处理)
    view: {
        d3: {key: "map.view.3d", name: "地图3D显示"},
        ordinary: {key: "map.view.ordinary", name: "地图普通显示"}
    }
};

toolMapHandleFun.searchByName = function (placeSearch, name, map) {
    placeSearch.search(name, function (status, result) {
        if (result.info == 'OK') {
            if (result.poiList.pois.length > 0) {
                var poi = result.poiList.pois[0];
                map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
            }
        }
    })
};

/**
 * 初始化地图以及加载事件
 * @param options 参数对象
 */
toolMapHandleFun.loadMap = function (options) {
    //默认设置
    var defaultObj = {
        drawState: toolMapHandleFun.config.draw.rectangle.key,
        viewState: toolMapHandleFun.config.view.ordinary.key,
        storageState: toolMapHandleFun.config.storage.select.key,
        readonly: false,
        radius: 10,
        zoom: 19,
        center: {lng: 104.084569, lat: 30.589714},
        viewMode: "2D", // 默认2D
        id: 0
    };
    defaultObj = $.extend(defaultObj, options);
    toolMapHandleFun.defaultObj = defaultObj;
    $(document).ready(function () {
        //初始化地图对象，加载地图
        toolMapHandleFun.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            zoom: defaultObj.zoom,
            viewMode: defaultObj.viewMode,
            center:[defaultObj.center.lng,defaultObj.center.lat]
        });
        // 地图 加载完成 load
        toolMapHandleFun.map.on("complete", function () {
            //地图加载完成后要做的事
            toolMapHandleFun.completeEvent();
        });
    });
    var fileId = "toolMapHandleFileId";
    toolMapHandleFun.fileUpload(fileId, AssessDBKey.ToolMapHandle, defaultObj.id, true, false, fileId);
    toolMapHandleFun.showFile(fileId, AssessDBKey.ToolMapHandle, defaultObj.id, true, false, false, fileId);
};

/**
 * 删除所有覆盖物
 */
toolMapHandleFun.reset = function () {
    //  toolMapHandleFun.map.destroy();//注销地图对象，并清空地图容器
    //  var oldObject = toolMapHandleFun.defaultObj ;
    //  var options = jQuery.extend(true, {}, oldObject);
    //  this.loadMap(options) ;

    toolMapHandleFun.map.clearMap(); // 使用clearMap方法删除所有覆盖物
};


/**
 * 切换为3D或者2d
 */
toolMapHandleFun.switch3DMap = function () {
    var map = toolMapHandleFun.map;
    var overlays = map.getAllOverlays();//获取覆盖物
    var zoom = map.getZoom();
    var center = map.getCenter();
    // var pitch = map.getPitch();
    var rotation = map.getRotation();
    var viewMode = map.view.type;
    map.destroy();//注销地图对象，并清空地图容器
    //当地图为2d那么切换为3D
    if (viewMode == '2D') {
        toolMapHandleFun.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            viewMode: '3D',
            // pitch: pitch,//俯仰角
            buildingAnimation: true,//楼块出现是否带动画
            expandZoomRange: true,
            zooms: [3, 20],
            pitch: 80,
            rotation: -15,
            // 隐藏默认楼块
            // features: ['bg', 'road', 'point'],
            // mapStyle: 'amap://styles/light',
            //图层可以根据需要调整或者注释
            // layers: [
            //     // 高德默认标准图层
            //     new AMap.TileLayer.Satellite(),
            //     // 楼块图层
            //     new AMap.Buildings({
            //         zooms: [16, 18],
            //         zIndex: 10,
            //         heightFactor: 2//2倍于默认高度，3D下有效
            //     })
            // ],
            zoom: zoom
        });
    }
    //当地图为3d那么切换为2D
    if (viewMode == '3D') {
        //加载3D地图
        toolMapHandleFun.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            viewMode: '2D',
            // pitch: pitch,//俯仰角
            rotation: rotation,
            zoom: zoom
        });
    }
    //地图 加载完成 load
    toolMapHandleFun.map.on("complete", function () {
        //地图加载完成后要做的事
        toolMapHandleFun.completeEvent();

        //恢复中心点
        toolMapHandleFun.map.setCenter(center);
        //恢复覆盖物
        if (overlays.length != 0) {
            var result = toolMapHandleFun.getOverlayByType(overlays);
            toolMapHandleFun.createOverlay(result);
            // toolMapHandleFun.map.add(overlays);
        }
    });
};


/**
 * 地图加载完成后要做的事
 */
toolMapHandleFun.completeEvent = function () {
    var defaultObj = toolMapHandleFun.defaultObj;
    if (defaultObj.readonly) {
        return false;
    }
    if (!toolMapHandleFun.mouseTool) {
        toolMapHandleFun.mouseTool = new AMap.MouseTool(toolMapHandleFun.map);
    }
    //线条绘制
    switch (defaultObj.drawState) {
        case toolMapHandleFun.config.draw.polyline.key: { // var tempArr = map.getAllOverlays('polyline') ;
            toolMapHandleFun.mouseTool.polyline({
                strokeColor: '#0000FF'
            });
            break
        }
        case toolMapHandleFun.config.draw.polygon.key: {
            toolMapHandleFun.mouseTool.polygon({
                fillColor: '#80d8ff',
                strokeColor: '#0000FF'
            });
            break
        }
        //圆 根据鼠标拖动,无半径
        case toolMapHandleFun.config.draw.circle.key: {
            toolMapHandleFun.mouseTool.circle({
                fillColor: '#80d8ff',
                strokeColor: '#0000FF'
            });
            break;
        }
        case toolMapHandleFun.config.draw.rectangle.key: {
            toolMapHandleFun.mouseTool.rectangle({
                fillColor: '#80d8ff',
                strokeColor: '#0000FF'
            });
            break;
        }
        default:
            break;
    }

    //点绘制
    toolMapHandleFun.map.on('click', function (e) {
        switch (defaultObj.drawState) {
            case toolMapHandleFun.config.draw.marker.key: {
                toolMapHandleFun.createOverlay([e.lnglat]);
                break
            }
            default:
                break;
        }
    });
};

/**
 * 选择搜索到的记录
 * @param _this
 */
toolMapHandleFun.autoCompleteSelect = function (_this) {
    var target = $(_this);
    var adcode = target.attr("data-code");
    var value = target.attr("data-value");
    if (!toolMapHandleFun.placeSearch) {
        toolMapHandleFun.placeSearch = new AMap.PlaceSearch({});
    }
    toolMapHandleFun.placeSearch.setCity(adcode);
    this.searchByName(toolMapHandleFun.placeSearch, value, toolMapHandleFun.map);
    $(_this).closest(".form-group").find("input[name='mapSearchName']").val(value);
    target.closest(".list-group").empty();
};

/**
 * 获取搜索结果
 * @param _this
 * @returns {boolean}
 */
toolMapHandleFun.autoCompleteSearch = function (_this) {
    var keyword = $(_this).val();
    var target = $("#tipInputToolMapHandle");
    if (!keyword) {
        return false;
    }
    AMap.plugin('AMap.Autocomplete', function () {
        // 实例化Autocomplete
        var autoOptions = {
            city: '全国' //city 限定城市，默认全国
        };
        var autoComplete = new AMap.Autocomplete(autoOptions);
        autoComplete.search(keyword, function (status, result) {// 搜索成功时，result即是对应的匹配数据
            if (status == 'complete') {
                if (result.count > 0) {
                    var tempArr = [];
                    $.each(result.tips, function (i, item) {
                        var html = '<a class="list-group-item" onclick="toolMapHandleFun.autoCompleteSelect(this)" data-code="{adcode}" data-value="{name}">{name}</a> ';
                        html = html.replace(/{adcode}/g, item.adcode);
                        html = html.replace(/{name}/g, item.name);
                        tempArr.push(html);
                    });
                    target.empty().append(tempArr.join(" "));
                }
            }
            if (status == 'no_data') {
                alert("无结果,您可以尝试扩大搜索范围或者咨询管理员!");
            }
            if (status == 'error') {
                console.error("高德地图报错!");
                alert("咨询管理员!");
            }
        })
    })
};


toolMapHandleFun.html2canvas = function () {
    var target = $("#toolMapHandleContainer");
    var imgTarget = $("#toolHtml2Img");
    $(document).ready(function () {
        html2canvas(target[0]).then(function (canvas) {
            var dataUrl = canvas.toDataURL();
            imgTarget.find("img").attr({"src": dataUrl});
            imgTarget.show();
            // imgTarget.height(Number(target.height()) / 3) ;
            // imgTarget.width(Number(target.width()) / 3) ;
            var type = 'png';
            var imgData = canvas.toDataURL(type);//canvas转换为图片
            // 加工image data，替换mime type，方便以后唤起浏览器下载
            imgData = imgData.replace(toolMapHandleFun.onFixType(type), 'image/octet-stream');
            toolMapHandleFun.fileDownload(imgData);
        });
    });
};

toolMapHandleFun.save = function () {
    var target = $("#modelToolMapHandleView");
    var form = target.find("form");
    var data = formSerializeArray(form);
    data.drawState = toolMapHandleFun.defaultObj.drawState;
    data.viewState = toolMapHandleFun.defaultObj.viewState;
    data.storageState = toolMapHandleFun.defaultObj.storageState;
    var center = toolMapHandleFun.map.getCenter();
    data.viewMode = toolMapHandleFun.map.view.type;
    data.zoom = toolMapHandleFun.map.getZoom();
    data.centerLat = center.lat;
    data.centerLng = center.lng;
    var overlays = toolMapHandleFun.map.getAllOverlays();
    var result = toolMapHandleFun.getOverlayByType(overlays);//获取覆盖物
    if (result.length != 0) {
        data.instantaneousLifeData = JSON.stringify(result);
    }
    toolMapHandleFun.saveData(data, function () {
        target.modal("hide");
    });
    console.log(data);
};

/**
 * 根据覆盖物类型创建覆盖物
 * @param result
 */
toolMapHandleFun.createOverlay = function (result) {
    if (result.length == 0) {
        return false;
    }
    $.each(result, function (i, data) {
        switch (toolMapHandleFun.defaultObj.drawState) {
            case toolMapHandleFun.config.draw.polyline.key:
                new AMap.Polyline({
                    path: data,
                    map: toolMapHandleFun.map,
                    borderWeight: 2, // 线条宽度，默认为 1
                    strokeColor: '#0000FF', // 线条颜色
                    lineJoin: 'round' // 折线拐点连接处样式
                });
                break;
            case toolMapHandleFun.config.draw.polygon.key:
                new AMap.Polygon({
                    path: data,
                    map: toolMapHandleFun.map,
                    borderWeight: 2, // 线条宽度，默认为 1
                    strokeColor: '#0000FF', // 线条颜色
                    lineJoin: 'round' // 折线拐点连接处样式
                });
                break;
            case toolMapHandleFun.config.draw.rectangle.key:
                var southWest = new AMap.LngLat(data[0].lng, data[0].lat);
                var northEast = new AMap.LngLat(data[data.length - 2].lng, data[data.length - 2].lat);
                var bounds = new AMap.Bounds(southWest, northEast) ;
                var rectangle = new AMap.Rectangle({
                    bounds: bounds,
                    strokeColor: '#0000FF', // 线条颜色
                    strokeWeight: 2,
                    strokeOpacity:0.5,
                    strokeDasharray: [10,10],
                    strokeStyle: 'solid',
                    fillColor:'blue',
                    fillOpacity:0.1,
                    cursor:'pointer',
                    zIndex:10
                });
                rectangle.setMap(toolMapHandleFun.map) ;
                // 缩放地图到合适的视野级别
                // toolMapHandleFun.map.setFitView([ rectangle ]) ;
                var rectangleEditor = new AMap.RectangleEditor(toolMapHandleFun.map, rectangle) ;
                break;
            case toolMapHandleFun.config.draw.circle.key:
                new AMap.Circle({
                    center: data.center,
                    map: toolMapHandleFun.map,
                    radius: data.radius, // 半径
                    fillColor: data.fillColor,
                    strokeColor: data.strokeColor
                });
                break;
            case toolMapHandleFun.config.draw.marker.key:
                var marker = new AMap.Marker({
                    position: data,
                    map: toolMapHandleFun.map,
                    icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                    offset: new AMap.Pixel(-13, -30)
                });
                break;
        }
    });

};

/**
 * 获取覆盖物中类型的数据
 * @param overlays
 * @returns {Array}
 */
toolMapHandleFun.getOverlayByType = function (overlays) {
    var result = [];
    switch (toolMapHandleFun.defaultObj.drawState) {
        case toolMapHandleFun.config.draw.polyline.key:
            $.each(overlays, function (i, overlay) {
                result.push(overlay.B.path);
            });
            break;
        case toolMapHandleFun.config.draw.polygon.key:
            $.each(overlays, function (i, overlay) {
                result.push(overlay.B.path);
            });
            break;
        case toolMapHandleFun.config.draw.rectangle.key:
            $.each(overlays, function (i, overlay) {
                result.push(overlay.Je.path);
            });
            break;
        case toolMapHandleFun.config.draw.circle.key:
            $.each(overlays, function (i, overlay) {
                var circle = {
                    center: overlay.Je.center,
                    radius: overlay.Je.radius,
                    fillColor: overlay.Je.fillColor,
                    strokeColor: overlay.Je.strokeColor
                };
                result.push(circle);
            });
            break;
        case toolMapHandleFun.config.draw.marker.key:
            $.each(overlays, function (i, overlay) {
                result.push(overlay.B.position);
            });
            break;
    }
    return result;
};

/**
 * 图片类型转换
 * @param type
 * @returns {string}
 */
toolMapHandleFun.onFixType = function (type) {
    type = type.toLowerCase().replace(/jpg/i, 'jpeg');
    var r = type.match(/png|jpeg|bmp|gif/)[0];
    return 'image/' + r;
};

/**
 * 唤起浏览器下载
 * @param downloadUrl
 */
toolMapHandleFun.fileDownload = function (downloadUrl) {
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

toolMapHandleFun.showFile = function (target, tableName, id, deleteFlag, editFlag, signatureFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        signatureFlag: signatureFlag,
        deleteFlag: deleteFlag,
        editFlag: editFlag
    })
};

toolMapHandleFun.fileUpload = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag,
        editFlag: editFlag
    });
    // FileUtils.uploadFiles({
    //     target: target,
    //     disabledTarget: "btn_submit",
    //     onUpload: function (file) {
    //         var formData = {
    //             fieldsName: target,
    //             tableName: tableName,
    //             tableId: id
    //         };
    //         return formData;
    //     }, onUploadComplete: function (result, file) {
    //
    //     },
    //     deleteFlag: true
    // });
};

toolMapHandleFun.saveData = function (data, callback) {
    if (callback) {
        callback();
    }
};

toolMapHandleFun.getDataById = function (id, callback) {
    if (callback) {
        callback();
    }
};


