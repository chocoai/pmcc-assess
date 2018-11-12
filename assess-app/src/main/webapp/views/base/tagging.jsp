<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>设置点标注的文本标签</title>

</head>
<body>
<div id="container" style="width: 100%;height: 100%;" tabindex="0"></div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=ac9fb0371e0405ef74cb1ca003fd0eef"></script>
<script type="text/javascript">
    //初始化地图对象，加载地图
    var map = new AMap.Map("container", {resizeEnable: true});
    var lnglats = [
        [104.078706, 30.592154],
        [104.078492, 30.591595],
        [104.079269, 30.59166],
        [104.08001, 30.591641],
        [104.080444, 30.592075]
    ];
    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
    for (var i = 0, marker; i < lnglats.length; i++) {
        var marker = new AMap.Marker({
            position: lnglats[i],
            map: map
        });
        marker.content = '我是第' + (i + 1) + '个Marker<a target="_blank" href="http://www.baidu.com">http://www.baidu.com</a>';
        marker.on('click', markerClick);
        marker.emit('click', {target: marker});
    }
    function markerClick(e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }
    map.setFitView();
</script>

<%--<script type="text/javascript">--%>


<%--var infoWindow;--%>
<%--var map = new AMap.Map("container", {--%>
<%--resizeEnable: true,--%>
<%--center: [104.078706, 30.592154],--%>
<%--zoom: 19--%>
<%--});--%>

<%--//在指定位置打开信息窗体--%>
<%--function openInfo() {--%>
<%--//构建信息窗体中显示的内容--%>
<%--var info = [];--%>
<%--//        info.push("<div class='input-card content-window-card'><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");--%>
<%--//        info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>高德软件</h4>");--%>
<%--//        info.push("<p class='input-item'>电话 : 010-84107000   邮编 : 100102</p>");--%>
<%--//        info.push("<p class='input-item'>地址 :北京市朝阳区望京阜荣街10号首开广场4层</p></div></div>");--%>

<%--info.push("1栋");--%>
<%--infoWindow = new AMap.InfoWindow({--%>
<%--content: info.join("")  //使用默认信息窗体框样式，显示信息内容--%>
<%--});--%>

<%--infoWindow.open(map, map.getCenter());--%>
<%--}--%>

<%--openInfo();--%>
<%--</script>--%>
</body>
</html>