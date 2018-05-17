<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">

</head>

<body>

<%--<div id="containerMap" class="map" tabindex="0"></div>--%>

<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>案例调查详情</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_survey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    相关权证
                                </label>
                                <div class="col-sm-2">
                                            <label class="form-control">${surveyCaseStudyDetailName.correlationCardName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    案例类型
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetailName.caseTypeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    信息来源
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetailName.informationSourceName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.houseName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.price}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易情况
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.dealCaondition}</label>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易时间
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control"><fmt:formatDate value="${surveyCaseStudyDetail.dealTime}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    付款方式
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.paymentMethod}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系人
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.linkman}</label>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    联系方式
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${surveyCaseStudyDetail.contactWay}</label>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
            </div>

            <%--<div id="tip"></div>--%>
            <%--<div id='right'>--%>
            <%--<div>--%>
            <%--<div class='c'>经纬度:</div>--%>
            <%--<div id='lnglat'></div>--%>
            <%--<div class='c'>地址:</div>--%>
            <%--<div id='surveyLocaltion' name="surveyLocaltion"></div>--%>
            <%--<div class='c'>最近的路口:</div>--%>
            <%--<div id='nearestJunction'></div>--%>
            <%--<div class='c'>最近的路:</div>--%>
            <%--<div id='nearestRoad'></div>--%>
            <%--<div class='c'>最近的POI:</div>--%>
            <%--<div id='nearestPOI'></div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">

    //高德地图接入定位
    //    AMapUI.loadUI(['misc/PositionPicker'], function (PositionPicker) {
    //        //var map = new AMap.Map('container', {
    //        //     zoom: 16,
    //        //     scrollWheel: false
    //        // })
    //
    //        var map, geolocation;
    //        //加载地图，调用浏览器定位服务
    //        map = new AMap.Map('containerMap', {
    //            zoom: 14,
    //            resizeEnable: true,
    //
    //        });
    //        map.plugin('AMap.Geolocation', function () {
    //            geolocation = new AMap.Geolocation({
    //                enableHighAccuracy: true,//是否使用高精度定位，默认:true
    //                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
    //                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
    //                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
    //                buttonPosition: 'RB'
    //            });
    //            map.addControl(geolocation);
    //            geolocation.getCurrentPosition();
    //            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
    //            AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    //        });
    //        //解析定位结果
    //        function onComplete(data) {
    //            var str = ['定位成功'];
    //            str.push('经度：' + data.position.getLng());
    //            str.push('纬度：' + data.position.getLat());
    //            if (data.accuracy) {
    //                str.push('精度：' + data.accuracy + ' 米');
    //            }//如为IP精确定位结果则没有精度信息
    //            str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    //            document.getElementById('tip').innerHTML = str.join('<br>');
    //        }
    //
    //        //解析定位错误信息
    //        function onError(data) {
    //            document.getElementById('tip').innerHTML = '定位失败';
    //        }
    //
    //
    //        var positionPicker = new PositionPicker({
    //            mode: 'dragMap',
    //            map: map
    //        });
    //
    //        positionPicker.on('success', function (positionResult) {
    //            document.getElementById('lnglat').innerHTML = positionResult.position;
    //            document.getElementById('surveyLocaltion').innerHTML = positionResult.address;
    //            document.getElementById('nearestJunction').innerHTML = positionResult.nearestJunction;
    //            document.getElementById('nearestRoad').innerHTML = positionResult.nearestRoad;
    //            document.getElementById('nearestPOI').innerHTML = positionResult.nearestPOI;
    //        });
    //        positionPicker.on('fail', function (positionResult) {
    //            document.getElementById('lnglat').innerHTML = ' ';
    //            document.getElementById('address').innerHTML = ' ';
    //            document.getElementById('nearestJunction').innerHTML = ' ';
    //            document.getElementById('nearestRoad').innerHTML = ' ';
    //            document.getElementById('nearestPOI').innerHTML = ' ';
    //        });
    //
    //        positionPicker.start();
    //        map.panBy(0, 1);
    //
    //
    //    });

</script>

</html>
