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
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>项目日历
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <%@include file="/views/project/projectCenterHeader.jsp" %>
                            <div id='calendar'></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<link rel="stylesheet" href="/assets/plugins/fullcalendar/fullcalendar/fullcalendar.css">
<script src="/assets/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>
<script src="/assets/plugins/fullcalendar/fullcalendar/fullcalendar.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {

        Calendar.init();
    });


    var Calendar = function () {
        var runCalendar = function () {
            var $modal = $('#event-management');
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            var form = '';
            var calendar = $('#calendar_list').fullCalendar({
                buttonText: {
                    prev: '<i class="fa fa-chevron-left"></i>',
                    next: '<i class="fa fa-chevron-right"></i>',
                    today: '本月',
                    month: '月视图',
                    week: '周视图',
                    day: '日视图'
                },
                allDayText: "全天",
                timeFormat: {
                    '': 'H:mm{-H:mm}'
                },
                weekMode: "variable",
                columnFormat: {
                    month: 'dddd',
                    week: 'dddd M-d',
                    day: 'dddd M-d'
                },
                titleFormat: {
                    month: 'yyyy年 MMMM月',
                    week: "[yyyy年] MMMM月d日 { '&#8212;' [yyyy年] MMMM月d日}",
                    day: 'yyyy年 MMMM月d日 dddd'
                },
                monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                events: function (start, end, callback) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/projectCenter/getProjectListByMonth",
                        type: "get",
                        dataType: "json",
                        data: {
                            dates: formatDate(start, false),
                            datee: formatDate(end, false)
                        },
                        success: function (result) {
                            var eventData = [];
                            if (result.ret) {

                                $.each(result.data, function (i, item) {
                                    var className = "";
                                    var title = item.projectName;
                                    if (item.status == 'finish') {
                                        className = 'label-purple';
                                    }
                                    else {
                                        var date = new Date();
                                        var projectDate = new Date(formatDate(item.completeDatePlan, false));
                                        if (addDay(date, -1) > projectDate) {
                                            className = 'label-orange';
                                        }
                                        else {
                                            if (addDay(date, 7) > projectDate) {
                                                className = 'label-yellow';
                                            }
                                            else {
                                                className = 'label-green';
                                            }
                                        }
                                    }

                                    eventData.push({
                                        title: title,
                                        start: new Date(formatDate(item.completeDatePlan, false)),
                                        className: className,
                                        id: item.id
                                    });

                                });
                            }
                            callback(eventData);
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                },
                eventClick: function (calEvent, jsEvent, view) {
                    window.open('${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=' + calEvent.id);
                }
            });
        };
        return {
            init: function () {
                runCalendar();
            }
        };
    }();
</script>
</html>
