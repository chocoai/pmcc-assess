<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">

                <div class="x_content">
                    <div class="x_title">
                        <div class="nav navbar-right" id="navBarRight" style="display: none">
                            <div class="btn  btn-default" onclick="areaMap.zoom(true)">放大</div>
                            <div class="btn  btn-default" onclick="areaMap.zoom(false)">缩小</div>
                        </div>
                        <h3>${caseEstate.name}
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                </div>

                <img id="oImg" style="display: none">

                <div class="x_content">
                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10" style="margin-top:10px;height: 700px;" id="container">
                    </div>
                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                        <div class="easyui-panel">
                            <ul id="treeId" class="easyui-tree">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6  col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/easyloader.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
<script type="text/javascript">
    var map = null;
    var imgMarker = null;
    var imgIcon = null;
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


    areaMap.getName = function (n) {
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
        map.add(new AMap.Marker({
            position: new AMap.LngLat(lng, lat), // 经纬度对象
            icon: "https://a.amap.com/jsapi_demos/static/images/green.png",
            offset: new AMap.Pixel(-13, -30)
        }));
        areaMap.event();
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
        var div = "";
        div += '<div class="" style="width:15px;height:135px;">';
        div += "<span class='label label-info' style='padding-top:-10px;'>" + name + "</span>";
        div += '<img style="height:12.75px;width:10px;" src="http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png">';
        div += '</div>';
        return div;
    };

    //创建一个覆盖物
    areaMap.getMarker = function (n) {
        var marker = new AMap.Marker({
            position: [Number(n.lng), Number(n.lat)], // 经纬度对象
            // icon: areaMap.getIcon(), // 添加 Icon 实例
            // icon: "http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
            title: areaMap.getName(n),
            extData: n,
            content: areaMap.getContent(areaMap.getName(n)) //会使icon无效
        });
        marker.on('click', areaMap.onSonLoadMap);
        return marker;
    };

    /**
     * 图片覆盖物
     */
    areaMap.createImgMarker = function (n, map) {
        var imageUrl = '${pageContext.request.contextPath}' + n.huxingImg;
        document.getElementById("oImg").src = imageUrl;
        //console.log("imageUrl:"+imageUrl) ;
        areaMap.getImgNaturalDimensions(document.getElementById("oImg"), function (data) {
            var max = 10;
            var min = 2;
            console.log(n);
            // 创建一个 Icon
            imgIcon = new AMap.Icon({
                // 图标尺寸
                size: new AMap.Size(data.w * max, data.h * max),
                // 图标的取图地址
                image: imageUrl,
                // 图标所用图片大小
                imageSize: new AMap.Size(data.w / min, data.h / min),
                // 图标取图偏移量
                imageOffset: new AMap.Pixel(-1, -1)
            });
            imgMarker = new AMap.Marker({
                position: [Number(n.lng), Number(n.lat)], // 经纬度对象
                icon: imgIcon,
                offset: new AMap.Pixel(-1, -1),
                autoRotation: true,
                extData: n,
                angle: areaMap.isNotBlank(n.deg) ? n.deg : 0
            });
            map.add(imgMarker);
        });
    };

    areaMap.onSonLoadMap = function (e) {

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

    /*
     加载树
     */
    areaMap.loadTree = function () {
        var mapTree = '${mapTree}';
        if (this.isNotBlank(mapTree)) {
            try {
                mapTree = JSON.parse(mapTree);
            } catch (e) {
            }
            areaMap.createMap(mapTree.lng, mapTree.lat, 17);
            //异步加载
            $('#treeId').tree({
                    data: [mapTree],//json格式数据
                    checkbox: false,  //显示勾选框
                    dnd: false,
                    onClick: function (node) {//单击事件
                        map.clearMap();
                        map.add(new AMap.Marker({
                            position: new AMap.LngLat(mapTree.lng, mapTree.lat), // 经纬度对象
                            icon: "https://a.amap.com/jsapi_demos/static/images/green.png",
                            offset: new AMap.Pixel(-13, -30)
                        }));
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseEstateTagging/queryCaseEstateTagging",
                            type: "get",
                            dataType: "json",
                            data: {dataId: node.dataId, type: node.type},
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    switch (node.type) {
                                        case config.house:
                                            $("#navBarRight").show();
                                            areaMap.createImgMarker(node, map);
                                            break;
                                        case config.estate:
                                            $("#navBarRight").hide();
                                            if (areaMap.isNotBlank(data)) {
                                                if (data) {
                                                    $.each(data, function (i, item) {
                                                        if (item) {
                                                            map.add(areaMap.getMarker(item));
                                                        }
                                                    });
                                                }
                                                areaMap.appendChild(data, node);
                                            }
                                            break;
                                        case config.building:
                                            $("#navBarRight").hide();
                                            if (areaMap.isNotBlank(data)) {
                                                if (data) {
                                                    $.each(data, function (i, item) {
                                                        if (item) {
                                                            map.add(areaMap.getMarker(item));
                                                        }
                                                    });
                                                }
                                                areaMap.appendChild(data, node);
                                            }
                                            break;
                                        case config.unit:
                                            $("#navBarRight").hide();
                                            if (data) {
                                                $.each(data, function (i, item) {
                                                    if (item) {
                                                        map.add(areaMap.getMarker(item));
                                                    }
                                                });
                                            }
                                            areaMap.appendChild(data, node);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                else {
                                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        });
                        map.setCenter([node.lng, node.lat]); //设置地图中心点
                        map.setZoom(18);
                    },
                    onLoadSuccess: function (data) {

                    },
                    onDblClick: function (node) {

                    },
                    formatter: function (node) {
                        return areaMap.getName(node);
                    }
                }
            )
        }
    };

    /**
     * 插入子节点
     */
    areaMap.appendChild = function (data, node) {
        var childrenNodes = $('#treeId').tree('getChildren', node.target);
        if (childrenNodes.length >= 1) {
            //说明已经添加过了子节点
        } else {
            $.each(data, function (i, item) {
                if (childrenNodes.length >= 1) {
                    //说明已经添加过了子节点
                } else {
                    //在楼栋节点添加单元子节点
                    $('#treeId').tree('append', {
                        parent: node.target,
                        data: item
                    });
                }
            });
        }
    };

    /**
     * 获取原始图片的长度和宽度
     * @param oImg
     * @param callback
     */
    areaMap.getImgNaturalDimensions = function (oImg, callback) {
        var data = {};
        oImg.onload = function () {
            data.w = parseFloat(oImg.width);
            data.h = parseFloat(oImg.height);
            callback(data);
        };
    };

    /**
     * 修改旋转角度
     */
    areaMap.rotateTransform = function (flag) {
        var angle = Number(imgMarker.getAngle());
        if (flag) {
            imgMarker.setAngle(angle + 5);
        } else {
            imgMarker.setAngle(angle - 5);
        }
    };

    /*修改覆盖物的大小*/
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


    $(document).ready(function () {
        areaMap.loadTree();
    });

</script>

</html>
