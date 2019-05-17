<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/share/main_css.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>土地标注画区块</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
</head>
<body>
<div id="container" style="width: 100%;height: 100%;" tabindex="0"></div>
<input type="hidden" id="pathArrayJSON" value='${pathArray}'>
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
        <c:if test="${!empty readonly && readonly==false}">
            <tr>
                <td>
                    <button class="btn btn-default" onclick="handleEvent(true)">清除标注的区块</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button class="btn btn-default" onclick="handleEvent(false)">关闭标注</button>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<!-- 请不要改成v1.4版本 ,假如要改的话必须把下面的的参数 调整 -->
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.3&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.MouseTool"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<script src="${pageContext.request.contextPath}/assembly/js/other/demoutils.js"></script>
<script src="${pageContext.request.contextPath}/excludes/assets/js/comm/bootbox.js"></script>
<script type="text/javascript">

    //必要的变量
    var map = null;
    var placeSearch = new AMap.PlaceSearch({});

    var mouseTool = null;
    var pathArray = [];
    var pathArrayJson = null;
    //监听draw事件可获取画好的覆盖物
    var overlays = [];

    $(document).ready(function () {
        //初始化地图对象，加载地图
        map = new AMap.Map("container", {
            resizeEnable: true,
            rotateEnable: true,
            zoom: 19
        });

        // 地图 加载完成
        map.on("complete", function () {
            //输入提示
            var autoOptions = {
                input: "tipinput"
            };
            var auto = new AMap.Autocomplete(autoOptions);
            //注册监听，当选中某条记录时会触发
            AMap.event.addListener(auto, "select", function (e) {
                placeSearch.setCity(e.poi.adcode);
                searchByName(e.poi.name);
            });

            //加载
            if (${!empty pathArray}) {
                readyPath($("#pathArrayJSON").val());
            }

            mouseTool = new AMap.MouseTool(map);

            //开启标记
            if ("${readonly}" == 'false') {
                draw('polyline');
            }
            //标记事件
            mouseTool.on('draw', function (e) {
                //删除目前画的这种折线
                map.remove(e.obj);
                var path = [];
                var storagePath = [];
                e.obj.Qi.path.forEach(function (c) {
                    var obj = {lng: c.lng, lat: c.lat};
                    path.push(new AMap.LngLat(obj.lng, obj.lat));
                    storagePath.push(obj);
                });
                //转成另一种折现
                showPolyline(path);
                setPathArrayJson(storagePath);
            });
        });
    });

    //清除和确定标记完成
    function handleEvent(flag) {
        if (flag) {
            map.remove(overlays);
            overlays = [];
            pathArray = [];
            pathArrayJson = null;
        } else {
            mouseTool.close(true);//关闭
        }
    }

    /**
     * @author:  zch
     * 描述: 只读的时候  绘制图形
     **/
    function readyPath(obj) {
        if (obj == undefined) {
            return false;
        }
        if (obj == null || obj == 'null') {
            return false;
        }
        try {
            JSON.parse(obj)
        } catch (e) {
            return false;
        }
        var arrPath = JSON.parse(obj);
        if (arrPath == null || arrPath == 'null') {
            return false;
        }
        arrPath.forEach(function (o) {
            var path = [] ;
            o.forEach(function (obj) {
                path.push(new AMap.LngLat(obj.lng, obj.lat));
            });
            showPolyline(path);
            setPathArrayJson(o);
        });
    }


    function draw(type) {
        switch (type) {
            case 'marker': {
                mouseTool.marker({
                    //同Marker的Option设置
                });
                break;
            }
            //折线
            case 'polyline': {
                mouseTool.polyline({
                    strokeColor: '#0000FF'
                    //同Polyline的Option设置
                });
                break;
            }
            case 'polygon': {
                mouseTool.polygon({
                    fillColor: '#00b0ff',
                    strokeColor: '#80d8ff'
                    //同Polygon的Option设置
                });
                break;
            }
            case 'rectangle': {
                mouseTool.rectangle({
                    fillColor: '#00b0ff',
                    strokeColor: '#80d8ff'
                    //同Polygon的Option设置
                });
                break;
            }
            case 'circle': {
                mouseTool.circle({
                    fillColor: '#00b0ff',
                    strokeColor: '#80d8ff'
                    //同Circle的Option设置
                });
                break;
            }
        }
    }

    /**
     * @author:  zch
     * 描述: path 折线的节点坐标数组，每个元素为 AMap.LngLat 对象 ,然后map绘制出数组path的路径
     **/
    function showPolyline(path) {
        var polyline = new AMap.Polyline({
            path: path,
            borderWeight: 2, // 线条宽度，默认为 1
            strokeColor: 'red', // 线条颜色
            lineJoin: 'round' // 折线拐点连接处样式
        });
        polyline.setMap(map) ;
        // 缩放地图到合适的视野级别
        map.setFitView([ polyline ]) ;
        // 将折线添加至地图实例
        map.add(polyline);
        overlays.push(polyline);
        //包裹一下
        // new AMap.Polygon({
        //     map: map,
        //     fillOpacity: 0.4,
        //     path: path
        // });
    }

    //根据名称查询
    function searchByName(name) {
        placeSearch.search(name, function (status, result) {
            if (result.info == 'OK') {
                if (result.poiList.pois.length > 0) {
                    var poi = result.poiList.pois[0];
                    map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
                }
            }
        })
    }

    function setPathArrayJson(path) {
        pathArray.push(path);
        //去重复
        // pathArray = uniquel(pathArray);
        pathArrayJson = JSON.stringify(pathArray);
        console.log(pathArray) ;
        if ("${readonly}" == 'true') {
            pathArrayJson = null;
        }
    }

    //数组去重复
    function uniquel(arr) {
        var result = [];
        var obj = {};
        for (var i = 0; i < arr.length; i++) {
            if (!obj[arr[i].key]) {
                result.push(arr[i]);
                obj[arr[i].key] = true;
            }
        }
        return result;
    }


</script>
</body>
</html>