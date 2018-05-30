<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/06/13
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
    <title>${orgSystemName}首页</title>
    <%@include file="share/main_css.jsp" %>
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="share/main_navigation.jsp" %>
        <%@include file="share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row tile_count">
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-user"></i> 年度计划项目</span>
                    <div class="count">120</div>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-user"></i> 库存项目</span>
                    <div class="count aero">50</div>
                    <span class="count_bottom"><i class="green">41% </i> </span>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-clock-o"></i> 在审项目</span>
                    <div class="count blue">40</div>
                    <span class="count_bottom"><i class="green">33% </i> </span>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-user"></i> 完结项目</span>
                    <div class="count green">23</div>
                    <span class="count_bottom"><i class="green">19% </i></span>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-user"></i> 暂停</span>
                    <div class="count red">2</div>
                    <span class="count_bottom"><i class="red">2% </i> </span>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa fa-user"></i> 终止</span>
                    <div class="count purple">5</div>
                    <span class="count_bottom"><i class="green">4% </i></span>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>项目分布</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="col-xs-6" id="container1">
                            </div>
                            <div class="col-xs-6" id="container3">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>项目公告</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <ul class="list-unstyled timeline">
                                <li>
                                    <div class="block">
                                        <div class="tags">
                                            <a href="" class="tag">
                                                <span>完成</span>
                                            </a>
                                        </div>
                                        <div class="block_content">
                                            <h2 class="title">
                                                <a>XX一般工程建设项目</a>
                                            </h2>
                                            <div class="byline">
                                                <span>2018-01-01</span> <a>张小三</a>
                                            </div>
                                            <p class="excerpt">项目已完成所有工作内容，完成相应的审计工作，报告已送审到申报单位。等待相应的反馈意见结果
                                            </p>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="block">
                                        <div class="tags">
                                            <a href="" class="tag" style="background-color: #ac2925">
                                                <span>终止</span>
                                            </a>
                                        </div>
                                        <div class="block_content">
                                            <h2 class="title">
                                                <a>XX一般工程建设项目</a>
                                            </h2>
                                            <div class="byline">
                                                <span>2018-01-01</span> <a>张小三</a>
                                            </div>
                                            <p class="excerpt">工作重点转移，同时对提交的资料一直不能有效提交，已上报通过，确认终止项目
                                            </p>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="block">
                                        <div class="tags">
                                            <a href="" class="tag" style="background-color: #f0ad4e">
                                                <span>暂停</span>
                                            </a>
                                        </div>
                                        <div class="block_content">
                                            <h2 class="title">
                                                <a>XX一般工程建设项目</a>
                                            </h2>
                                            <div class="byline">
                                                <span>2018-01-01</span> <a>张小三</a>
                                            </div>
                                            <p class="excerpt">证明材料出现问题，正在加紧补相关的资料信息
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>关注项目</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="col-xs-12 bg-white progress_summary">

                                <div class="row">
                                    <div class="progress_title">
                                        <span class="left">XXX公路工程项目</span>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="col-xs-2">
                                        <span>结算审计</span>
                                    </div>
                                    <div class="col-xs-8">
                                        <div class="progress progress_sm">
                                            <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="89" aria-valuenow="88" style="width: 89%;"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-2 more_info">
                                        <span>89%</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="progress_title">
                                        <span class="left">XXX项目</span>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="col-xs-2">
                                        <span>结算审计</span>
                                    </div>
                                    <div class="col-xs-8">
                                        <div class="progress progress_sm">
                                            <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="79" aria-valuenow="78" style="width: 79%;"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-2 more_info">
                                        <span>79%</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="progress_title">
                                        <span class="left">XXXX项目</span>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="col-xs-2">
                                        <span>跟踪项目</span>
                                    </div>
                                    <div class="col-xs-8">
                                        <div class="progress progress_sm">
                                            <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="69" aria-valuenow="68" style="width: 69%;"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-2 more_info">
                                        <span>69%</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>材料价格分析表</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content" >
                            <div class="col-xs-3">
                                <table class="table" style="width: auto">
                                    <thead>
                                    <tr>
                                        <th>材料名称</th>
                                        <th>规格</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>热扎光圆钢筋</td>
                                        <td>HPB3006.5~10</td>
                                    </tr>
                                    <tr>
                                        <td>白水泥</td>
                                        <td>二级</td>
                                    </tr>
                                    <tr>
                                        <td>锯材</td>
                                        <td>一等</td>
                                    </tr>
                                    <tr>
                                        <td>竹胶板</td>
                                        <td>2000*1000*3</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-xs-8" >
                                <div id="container2">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="share/main_footer.jsp" %>
    </div>
    <!-- end: MAIN CONTAINER -->

</div>

</body>
</html>
<script src='/assets/plugins/Highcharts-6.1.0/code/modules/sunburst.js'></script>
<script type="text/javascript">
    Highcharts.chart('container2', {
        chart: {
            type: 'spline'
        },
        title: {
            text: '热扎光圆钢筋（HPB3006.5~10）价格变化分析表'
        },
        subtitle: {
            text: '数据来源: 四川省工程造价信息网'
        },
        xAxis: {
            categories: ['2017/09','2017/10','2017/11', '2017/12','2018/01']
        },
        yAxis: {
            title: {
                text: '不含税价'
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },

        series: [{
            name: '绵阳市区',
            marker: {
                symbol: 'square'
            },
            data: [3580,3550, 3670, 4150, 4100 ]
        }, {
            name: '梓潼县',
            marker: {
                symbol: 'diamond'
            },
            data: [3600, 3570, 3690, 4170, 4120 ]
        }
            , {
                name: '三台县',
                marker: {
                    symbol: 'diamond'
                },
                data: [3595, 3565, 3685, 4165, 4115 ]
            }
            , {
                name: '盐亭县',
                marker: {
                    symbol: 'diamond'
                },
                data: [3600,3570, 3690, 4170, 4120 ]
            }
            , {
                name: '安州区',
                marker: {
                    symbol: 'diamond'
                },
                data: [3590, 3560, 3680, 4160, 4110 ]
            }
            , {
                name: '江油市',
                marker: {
                    symbol: 'diamond'
                },
                data: [3600, 3570, 3690, 4170, 4120 ]
            }
            , {
                name: '北川县',
                marker: {
                    symbol: 'diamond'
                },
                data: [3595, 3565, 3685, 4165, 4115 ]
            }]
    });

    Highcharts.setOptions({
        lang: {
            thousandsSep: ','
        }
    });


    Highcharts.chart('container3', {
        title: {
            text: '投资对比'
        },
        tooltip: {
            pointFormat: '{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true // 设置饼图是否在图例中显示
            }
        },
        series: [{
            type: 'pie',
            data: [
                ['0-500万元',   45.0],
                ['500-1000万元',       26.8],
                {
                    name: '1,000-3,000万元',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['3,000-5,000万元',    8.5],
                ['5,000-10,000万元',     6.2],
                ['10,000万元以上',   0.7]
            ]
        }]
    });



    Highcharts.setOptions({
        lang: {
            thousandsSep: ','
        }
    });
    var data = [{
        'id': '0.0',
        'parent': '',
        'name': '年度计划项目'
    }, {
        'id': '1.1',
        'parent': '0.0',
        'name': '跟踪'
    }, {
        'id': '1.2',
        'parent': '0.0',
        'name': '结算复核'
    }, {
        'id': '1.3',
        'parent': '0.0',
        'name': '竣工决算'
    },

        {
            'parent': '1.1',
            'name': '库存项目',
            'value': 20
        },{
            'parent': '1.1',
            'name': '在审项目',
            'value': 10
        },
        {
            'parent': '1.1',
            'name': '完结项目',
            'value': 7
        },
        {
            'parent': '1.1',
            'name': '暂停',
            'value': 1
        },
        {
            'parent': '1.1',
            'name': '终止',
            'value': 2
        },
        {
            'parent': '1.2',
            'name': '库存项目',
            'value': 20
        },{
            'parent': '1.2',
            'name': '在审项目',
            'value': 10
        },
        {
            'parent': '1.2',
            'name': '完结项目',
            'value': 6
        },
        {
            'parent': '1.2',
            'name': '暂停',
            'value': 0
        },
        {
            'parent': '1.1',
            'name': '终止',
            'value': 0
        },
        {
            'parent': '1.3',
            'name': '库存项目',
            'value': 10
        },{
            'parent': '1.3',
            'name': '在审项目',
            'value': 20
        },
        {
            'parent': '1.3',
            'name': '完结项目',
            'value': 10
        },
        {
            'parent': '1.3',
            'name': '暂停',
            'value': 1
        },
        {
            'parent': '1.3',
            'name': '终止',
            'value': 3
        }
    ];

    // Splice in transparent for the center circle
    Highcharts.getOptions().colors.splice(0, 0, 'transparent');


    Highcharts.chart('container1', {

        chart: {
            height: '100%'
        },

        title: {
            text: '分类项目进度情况'
        },
        series: [{
            type: "sunburst",
            data: data,
            allowDrillToNode: true,
            cursor: 'pointer',
            dataLabels: {
                /**
                 * A custom formatter that returns the name only if the inner arc
                 * is longer than a certain pixel size, so the shape has place for
                 * the label.
                 */
                formatter: function () {
                    var shape = this.point.node.shapeArgs;

                    var innerArcFraction = (shape.end - shape.start) / (2 * Math.PI);
                    var perimeter = 2 * Math.PI * shape.innerR;

                    var innerArcPixels = innerArcFraction * perimeter;

                    if (innerArcPixels > 16) {
                        return this.point.name;
                    }
                }
            },
            levels: [{
                level: 2,
                colorByPoint: true,
                dataLabels: {
                    rotationMode: 'parallel'
                }
            },
                {
                    level: 3,
                    colorVariation: {
                        key: 'brightness',
                        to: -0.5
                    }
                }, {
                    level: 4,
                    colorVariation: {
                        key: 'brightness',
                        to: 0.5
                    }
                }]

        }],
        tooltip: {
            headerFormat: "",
            pointFormat: '<b>{point.name}</b>项目数量 ：<b>{point.value}</b>'
        }
    });
</script>
