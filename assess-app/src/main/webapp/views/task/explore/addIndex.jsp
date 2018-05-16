<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PositionPicker/examples/positionPicker.html -->
    <!-- <base href="//webapi.amap.com/ui/1.0/ui/misc/PositionPicker/examples/" /> -->
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script type="text/javascript"
            src='//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar'></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>

    <style>
        .map {
            height: 30%;
            width: 20%;
        }
        #right {
            color: #444;
            background-color: #f8f8f8;
            width: 20%;
            height: 60%;
        }

        #right input {
            margin: 4px;
            margin-left: 15px;
        }

        /* button {
             border: solid 1px;
             margin-left: 15px;
             background-color: #dadafa;
         }*/

        .c {
            font-weight: 600;
            padding-left: 15px;
            padding-top: 4px;
        }

    </style>
</head>

<body>

<div id="containerMap" class="map" tabindex="0"></div>

<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">相关权证<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <select id="evaluator" name="evaluator" class="form-control" required="required">
                                        <option selected="selected"
                                                value="${thisUserInfo.id}">${thisUserInfo.userName}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <select id="evaluator1" name="evaluator1" class="form-control" required="required">
                                        <option selected="selected"
                                                value="${thisUserInfo.id}">${thisUserInfo.userName}</option>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>



            <div id="tip"></div>
            <div id='right'>
                <div>
                    <div class='c'>经纬度:</div>
                    <div id='lnglat'></div>
                    <div class='c'>地址:</div>
                    <div id='surveyLocaltion' name="surveyLocaltion"></div>
                    <div class='c'>最近的路口:</div>
                    <div id='nearestJunction'></div>
                    <div class='c'>最近的路:</div>
                    <div id='nearestRoad'></div>
                    <div class='c'>最近的POI:</div>
                    <div id='nearestPOI'></div>
                </div>
            </div>

            <div class="x_content">
                <form id="frm_upload" class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-1 control-label">
                            查勘图片上传
                        </label>
                        <div class="col-sm-11">
                            <input id="apply_file" name="apply_file" type="file" multiple="false">
                            <div id="_apply_file">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-1 control-label">
                            查勘图像上传
                        </label>
                        <div class="col-sm-11">
                            <input id="apply_file1" name="apply_file1" type="file" multiple="false">
                            <div id="_apply_file1">
                            </div>
                        </div>
                    </div>

                </form>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="saveFrm" type="button" class="btn btn-primary" onclick="saveData()">
                            保存
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
    AMapUI.loadUI(['misc/PositionPicker'], function (PositionPicker) {
        //var map = new AMap.Map('container', {
        //     zoom: 16,
        //     scrollWheel: false
        // })


        var map, geolocation;
        //加载地图，调用浏览器定位服务
        map = new AMap.Map('containerMap', {
            zoom: 14,
            resizeEnable: true,

        });
        map.plugin('AMap.Geolocation', function () {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,//是否使用高精度定位，默认:true
                timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                buttonPosition: 'RB'
            });
            map.addControl(geolocation);
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
            AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
        });
        //解析定位结果
        function onComplete(data) {
            var str = ['定位成功'];
            str.push('经度：' + data.position.getLng());
            str.push('纬度：' + data.position.getLat());
            if (data.accuracy) {
                str.push('精度：' + data.accuracy + ' 米');
            }//如为IP精确定位结果则没有精度信息
            str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
            document.getElementById('tip').innerHTML = str.join('<br>');
        }

        //解析定位错误信息
        function onError(data) {
            document.getElementById('tip').innerHTML = '定位失败';
        }


        var positionPicker = new PositionPicker({
            mode: 'dragMap',
            map: map
        });

        positionPicker.on('success', function (positionResult) {
            document.getElementById('lnglat').innerHTML = positionResult.position;
            document.getElementById('surveyLocaltion').innerHTML = positionResult.address;
            document.getElementById('nearestJunction').innerHTML = positionResult.nearestJunction;
            document.getElementById('nearestRoad').innerHTML = positionResult.nearestRoad;
            document.getElementById('nearestPOI').innerHTML = positionResult.nearestPOI;
        });
        positionPicker.on('fail', function (positionResult) {
            document.getElementById('lnglat').innerHTML = ' ';
            document.getElementById('address').innerHTML = ' ';
            document.getElementById('nearestJunction').innerHTML = ' ';
            document.getElementById('nearestRoad').innerHTML = ' ';
            document.getElementById('nearestPOI').innerHTML = ' ';
        });
        /* var onModeChange = function(e) {
         positionPicker.setMode(e.target.value)
         }
         var startButton = document.getElementById('start');
         var stopButton = document.getElementById('stop');
         var dragMapMode = document.getElementsByName('mode')[0];
         var dragMarkerMode = document.getElementsByName('mode')[1];
         AMap.event.addDomListener(startButton, 'click', function() {
         positionPicker.start(map.getBounds().getSouthWest())
         })
         AMap.event.addDomListener(stopButton, 'click', function() {
         positionPicker.stop();
         }) */
        //AMap.event.addDomListener(dragMapMode, 'change', onModeChange)
        //AMap.event.addDomListener(dragMarkerMode, 'change', onModeChange);
        positionPicker.start();
        map.panBy(0, 1);

        /* map.addControl(new AMap.ToolBar({
         liteStyle: true
         })) */
    });

    function saveData() {
        var flag = false;
        //var data = formParams("frm");
//        var data = $("#frm").serialize();
        /*var data = formParams("frm");*/
        var surveyLocaltion = document.getElementById("surveyLocaltion").innerHTML;
        alert(surveyLocaltion);

        $.ajax({
            url: "${pageContext.request.contextPath}/surveyLocale/save",
            type: "post",
            dataType: "json",
            data: {surveyLocaltion:surveyLocaltion},
            success: function (result) {
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }
</script>

<script type="text/javascript">
    $(function(){
        FileUtils.uploadFiles({
            target: "apply_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: 0,
                fieldsName: "apply",
                projectId: "0"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: 0,
                fieldsName: "apply",
                projectId: "0"
            },
            deleteFlag: true
        })

        FileUtils.uploadFiles({
            target: "apply_file1",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: 0,
                fieldsName: "apply",
                projectId: "0"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "apply_file1",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: 0,
                fieldsName: "apply",
                projectId: "0"
            },
            deleteFlag: true
        })

    });


</script>
</html>
