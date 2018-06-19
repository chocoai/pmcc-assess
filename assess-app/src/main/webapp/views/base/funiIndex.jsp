<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">

            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                </div>

                <div class="title_right">
                    <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                        <div class="input-group">
                            <input type="text" id="txt_house_search" class="form-control" placeholder="案例查询">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="loadHouseListAjax(1)">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>

            <div class="row">
                <div class="col-md-12">
                    <div class="x_panel">
                        <div class="x_content">
                            <p id="toolbar">
                                <a class="btn btn-success" onclick="updateFuni()">
                                    <i class="fa fa-plus"></i>
                                    更新楼盘
                                </a>
                            </p>
                            <div id="div_house_list" class="row">


                                <%--<div id="div_house_list">--%>
                                <%--<div class="col-md-4 col-sm-4 col-xs-12 profile_details"><div class="well profile_view"><div class="col-sm-12"><h4 class="brief"><i><i class="fa fa-cny"></i>0.00</i></h4><div class="left col-xs-8"><h2>源滩城1期</h2><p><i class="fa fa-bell-o"></i>成都市成华区圣灯街办事处乐华社区1组、人民塘社区2组 </p></div><div class="right col-xs-4 text-center"><img src="/assets/images/timg.jpg" alt="" class="img-circle img-responsive"></div></div><div class="col-xs-12 bottom text-center"><div class="col-xs-12 col-sm-8 emphasis"><p class="ratings"><a>0.0</a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star-o"></span></a></p></div><div class="col-xs-12 col-sm-4 emphasis"><button type="button" class="btn btn-primary btn-xs"><i class="fa fa-info-circle"> </i> 查看详情</button></div></div></div></div>--%>
                                <%--<div class="col-md-4 col-sm-4 col-xs-12 profile_details"><div class="well profile_view"><div class="col-sm-12"><h4 class="brief"><i><i class="fa fa-cny"></i>0.00</i></h4><div class="left col-xs-8"><h2>源滩城1期</h2><p><i class="fa fa-bell-o"></i>成都市成华区圣灯街办事处乐华社区1组、人民塘社区2组 </p></div><div class="right col-xs-4 text-center"><img src="/assets/images/timg.jpg" alt="" class="img-circle img-responsive"></div></div><div class="col-xs-12 bottom text-center"><div class="col-xs-12 col-sm-8 emphasis"><p class="ratings"><a>0.0</a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star-o"></span></a></p></div><div class="col-xs-12 col-sm-4 emphasis"><button type="button" class="btn btn-primary btn-xs"><i class="fa fa-info-circle"> </i> 查看详情</button></div></div></div></div>--%>
                                <%--<div class="col-md-4 col-sm-4 col-xs-12 profile_details"><div class="well profile_view"><div class="col-sm-12"><h4 class="brief"><i><i class="fa fa-cny"></i>0.00</i></h4><div class="left col-xs-8"><h2>源滩城1期</h2><p><i class="fa fa-bell-o"></i>成都市成华区圣灯街办事处乐华社区1组、人民塘社区2组 </p></div><div class="right col-xs-4 text-center"><img src="/assets/images/timg.jpg" alt="" class="img-circle img-responsive"></div></div><div class="col-xs-12 bottom text-center"><div class="col-xs-12 col-sm-8 emphasis"><p class="ratings"><a>0.0</a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star"></span></a><a href="#"><span class="fa fa-star-o"></span></a></p></div><div class="col-xs-12 col-sm-4 emphasis"><button type="button" class="btn btn-primary btn-xs"><i class="fa fa-info-circle"> </i> 查看详情</button></div></div></div></div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="row" style="text-align: center">
                                <ul id='bp-element' class="pagination pagination-lg  pagination-bricky"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div class="x_panel">--%>
            <%--<div class="x_content">--%>


            <%--<div class="col-xs-3" id="container1">--%>

            <%--<table id="tb_List" class="table table-striped jambo_table bulk_action">--%>

            <%--</table>--%>
            <%--</div>--%>
            <%--<div class="col-xs-9">--%>
            <%--<div class="x_title">--%>
            <%--<h3 id="h3_houseName">--%>
            <%--${baseViewDto.currentMenu.name} &lt;%&ndash;这是用来显示标题的，固定格式&ndash;%&gt;--%>
            <%--</h3>--%>
            <%--<div class="clearfix"></div>--%>
            <%--</div>--%>
            <%--<div class="" role="tabpanel" data-example-id="togglable-tabs">--%>
            <%--<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">--%>
            <%--<li role="presentation" class="active"><a href="#tab_content1" role="tab" data-toggle="tab" aria-expanded="true">楼盘</a>--%>
            <%--</li>--%>
            <%--<li role="presentation" class=""><a href="#tab_content2" role="tab" data-toggle="tab" aria-expanded="false">户型</a>--%>
            <%--</li>--%>
            <%--<li role="presentation" class=""><a href="#tab_content3" role="tab" data-toggle="tab" aria-expanded="false">物业</a>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--<div id="myTabContent" class="tab-content">--%>
            <%--<div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">--%>

            <%--</div>--%>

            <%--<div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">--%>
            <%--<h3>没有上传楼盘户型信息</h3>--%>
            <%--</div>--%>
            <%--<div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">--%>
            <%--<h3>物业信息未填写</h3>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script src="/assets/plugins/bootstrap-paginator/src/bootstrap-paginator.js"></script>
<script type="application/javascript">
    var element = $('#bp-element');
    $(function () {
        // loadHouseList();
//        lpxx();
//        xEditChange();
        loadHouseListAjax(1);

    })

    function loadHouseListAjax(pages) {
        var pageSize = 12;
        $.ajax({
            url: "${pageContext.request.contextPath}/funiViewer/getHousesList",
            type: "get",
            dataType: "json",
            data: {
                offset: pages,
                limit: pageSize,
                search: $("#txt_house_search").val()
            },
            success: function (result) {
                var data = result.rows;
                if (data.length > 0) {
                    var html = "";
                    html += " <div class='col-md-12 col-sm-12 col-xs-12 text-center'>";
                    html += "</div>";
                    html += "<div class='clearfix'></div>";

                    $.each(data, function (i, j) {
                        html += "<div class='col-md-4 col-sm-4 col-xs-12 profile_details'>";
                        html += "<div class='well profile_view'>";
                        html += "<div class='col-sm-12'>";
                        html += "<h4 class='brief'><i><i class='fa fa-cny'></i>0.00</i></h4>";
                        html += "<div class='left col-xs-8'>";
                        html += "<h2>" + j.lpmc + "</h2>";
                        html += "<p><i class='fa fa-bell-o'></i>" + j.lpdz + " </p>";
                        html += "<p><i class='fa fa-building'></i>" + j.xmdz + " </p>";
                        html += "</div>";
                        html += "<div class='right col-xs-4 text-center'>";
                        html += "<img src='" + j.lptp + ".160x120.jpg' alt='' class='img-circle img-responsive'>";
                        html += "</div>";
                        html += "</div>";
                        html += "<div class='col-xs-12 bottom text-center'>";
                        html += "<div class='col-xs-12 col-sm-8 emphasis'>";
                        html += "<p class='ratings'>";
                        html += "<a>0.0</a>";
                        html += "<a href='#'><span class='fa fa-star'></span></a>";
                        html += "<a href='#'><span class='fa fa-star'></span></a>";
                        html += "<a href='#'><span class='fa fa-star'></span></a>";
                        html += "<a href='#'><span class='fa fa-star'></span></a>";
                        html += "<a href='#'><span class='fa fa-star-o'></span></a>";
                        html += "</p>";
                        html += "</div>";
                        html += "<div class='col-xs-12 col-sm-4 emphasis'>";
                        html += "<a  target='_blank'  class='btn btn-primary btn-xs' href='${pageContext.request.contextPath}/funiViewer/funiDetails?lpbh="+j.id+"'>";
                        html += "<i class='fa fa-info-circle'> </i> 查看详情";
                        html += "</a>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                    });
                    $("#div_house_list").html(html);
                    options = {
                        bootstrapMajorVersion: 3,
                        currentPage: pages, //当前页数，这里是用的EL表达式，获取从后台传过来的值
                        numberOfPages: 5, //每页显示按钮个数
                        totalPages: Math.ceil(result.total / pageSize), //总页数，这里是用的EL表达式，获取从后台传过来的值
                        shouldShowPage: true,//是否显示该按钮
                        //点击事件
                        onPageClicked: function (event, originalEvent, type, page) {
                            loadHouseListAjax(page);
                        }
                    };

                    element.bootstrapPaginator(options);
                }
                else {
                    $("#div_house_list").html("<h3>没有找到合适的案例信息.</h3>");
                    $(element).hide();
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function lpxx() {
        var lpxxData = [];
        lpxxData.push({table: "tb_funi_houses", cnName: "建筑面积", fieldName: "jzmj"});
        lpxxData.push({table: "tb_funi_houses", cnName: "占地面积", fieldName: "zdmj"});
        lpxxData.push({table: "tb_funi_houses", cnName: "容积率", fieldName: "rjl"});
        lpxxData.push({table: "tb_funi_houses", cnName: "绿化率", fieldName: "lhl"});
        lpxxData.push({table: "tb_funi_houses", cnName: "车位信息", fieldName: "cwxx"});
        lpxxData.push({table: "tb_funi_houses", cnName: "项目地址", fieldName: "lpdz"});
        lpxxData.push({table: "tb_funi_houses", cnName: "售楼地址", fieldName: "sldz"});
        lpxxData.push({table: "tb_funi_houses", cnName: "开发商", fieldName: "kfsbh"});
        lpxxData.push({table: "tb_funi_houses", cnName: "销售许可证", fieldName: "xsxkz"});
        lpxxData.push({table: "tb_funi_houses", cnName: "楼盘介绍", fieldName: "lpjs"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "公交", fieldName: "gj"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "地铁", fieldName: "dt"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "幼儿园", fieldName: "yey"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "小学", fieldName: "xx"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "中学", fieldName: "zx"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "大学", fieldName: "dx"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "医院", fieldName: "yy"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "药店", fieldName: "yd"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "商场", fieldName: "sc"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "超市", fieldName: "cs"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "市场", fieldName: "csc"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "银行", fieldName: "yh"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "ATM", fieldName: "atm"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "餐厅", fieldName: "ct"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "咖啡馆", fieldName: "kfg"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "公园", fieldName: "gy"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "电影院", fieldName: "dyy"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "健身房", fieldName: "jsf"});
        lpxxData.push({table: "tb_funi_houses_mating", cnName: "体育馆", fieldName: "tyg"});

        var html = "<table class='table table-condensed table-hover'>";
        html += "<input type='hidden' id='lpxx_id' name='id'>";
        for (var k = 0; k < lpxxData.length; k++) {
            html += "<tr>";
            html += "<td class='col-sm-2'>" + lpxxData[k].cnName + "</td>";
            if (lpxxData[k].table == "tb_funi_houses_mating") {
                html += "<td><a href='#' id='lppt_'  name='" + lpxxData[k].fieldName + "'  data-type='textarea' data-pk='1' data-original-title='" + lpxxData[k].cnName + "' class='editable editable-click'></a></td>";
            }
            else {
                html += "<td><a href='#'  name='" + lpxxData[k].fieldName + "'  data-type='textarea' data-pk='1' data-original-title='" + lpxxData[k].cnName + "' class='editable editable-click'></a></td>";
            }
            html += "</tr>";
        }
        html += "</table>";
        $("#tab_content1").html(html);
    }

    function showImage(obj) {
        var url = $(obj).attr("src");
        url = url.replace(".240x1000.jpg", "");
        window.open(url);
    }

    function xEditChange() {
        $(".editable").editable({
            url: function (params) {
                var sName = $(this).attr("name");
                var ids = $(this).attr("id");
                if (ids) {
                    var ids = $(this).attr("id").split('_');
                    if (ids[0] == "wyxx") {
                        updateFuniData(sName, params.value, ids[0], ids[1]);
                    }
                    else {
                        updateFuniData(sName, params.value, ids[0], $("#lpxx_id").val());
                    }
                }
                else {
                    updateFuniData(sName, params.value, "lpxx", $("#lpxx_id").val());
                }

            }
        });

    }

    //加载代理数据列表
    function loadHouseList() {
        var cols = [];
        cols.push({field: 'lpmc', title: '楼盘名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='" + row.funiweb + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' ><i class='fa fa-paper-plane-o fa-white'></i></a>";
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/funiViewer/getHousesList", cols, {}, {
            singleSelect: true,
            clickToSelect: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },
            onClickRow: function (row) {
                $("#tab_content1").initForm(row);

                $("#h3_houseName").html(row.lpmc);
                loadHousesTypeList(row.id);
                loadHousesPropertyList(row.id);
                var objs = $(".editable");
                $.each(objs, function (i, j) {
                    $(j).editable('setValue', $(j).html());
                });
            }
        });
    }

    function loadHousesTypeList(lpbh) {
        $.ajax({
            url: "${pageContext.request.contextPath}/funiViewer/getHousesType",
            type: "get",
            dataType: "json",
            data: {
                lpbh: lpbh
            },
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var html = "";
                    $.each(data, function (i, j) {
                        html += "<div class='col-md-55'>";
                        html += "<div class='thumbnail'>";
                        html += "<div class='image view view-first'>";
                        html += "<img style='width: 100%; display: block;' onclick='showImage(this)' src='" + j.hxt + ".240x1000.jpg' alt='image'>";
                        html += "</div>";
                        html += "<div class='caption'>";
                        html += "<p>" + j.fx + "</p>";
                        html += "<p>" + j.mj + "</p>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                    });
                    if (html == "") {
                        html = "<h3>没有上传楼盘户型信息</h3>";
                    }
                    $("#tab_content2").html(html);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function loadHousesPropertyList(lpbh) {
        $.ajax({
            url: "${pageContext.request.contextPath}/funiViewer/getHousesProperty",
            type: "get",
            dataType: "json",
            data: {
                lpbh: lpbh
            },
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var html = "";
                    var wylx = [];
                    wylx.push({cnName: "建筑类别", fieldName: "jzlb"});
                    wylx.push({cnName: "装修情况", fieldName: "zxqk"});
                    wylx.push({cnName: "土地使用年限", fieldName: "tdsynx"});
                    wylx.push({cnName: "总户数", fieldName: "zhs"});
                    wylx.push({cnName: "物业费", fieldName: "wyf"});
                    wylx.push({cnName: "物业公司", fieldName: "wygs"});
                    wylx.push({cnName: "营销代理", fieldName: "yxdl"});
                    wylx.push({cnName: "占地面积", fieldName: "zdmj"});
                    wylx.push({cnName: "建筑面积", fieldName: "jzmj"});
                    wylx.push({cnName: "供暖方式", fieldName: "glfs"});
                    wylx.push({cnName: "供水方式", fieldName: "gsfs"});
                    wylx.push({cnName: "通讯", fieldName: "tx"});
                    wylx.push({cnName: "网络", fieldName: "wl"});
                    wylx.push({cnName: "层高", fieldName: "cg"});
                    wylx.push({cnName: "开盘时间", fieldName: "kpsj"});
                    wylx.push({cnName: "交房时间", fieldName: "jfsj"});
                    wylx.push({cnName: "户型区间", fieldName: "hxqj"});
                    wylx.push({cnName: "客梯数", fieldName: "kts"});
                    wylx.push({cnName: "货梯数", fieldName: "hts"});
                    html += "<table class='table table-condensed table-hover'>";
                    $.each(data, function (i, j) {

                        //html += "<thead>";
                        html += "<tr>";
                        html += "<th colspan='2'>" + j.wylx + "</th>";
                        html += "</tr>";
                        // html += "</thead>";
                        // html += "<tbody>";
                        for (var k = 0; k < wylx.length; k++) {
                            html += "<tr>";
                            html += "<td>" + wylx[k].cnName + "</td>";
                            var values = j[wylx[k].fieldName];
                            if (values == null) {
                                values = "";
                            }
                            html += "<td><a href='#' id='wyxx_" + j.id + "' name='" + wylx[k].fieldName + "'  data-type='textarea' data-pk='1' data-original-title='" + wylx[k].cnName + "' class='editable editable-click'>" + values + "</a></td>";
                            html += "</tr>";
                        }
                        //html += "</tbody>";

                    });
                    html += "</table>";
                    if (data.length <= 0) {
                        html = "<h3>物业信息未填写</h3>";
                    }
                    $("#tab_content3").html(html);
                    xEditChange();
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //刷新数据列表
    function reloadFuniList() {
        TableReload("tb_List");
    }
    //保存数据
    function updateFuni() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/funiViewer/updateHouses",
            type: "post",
            dataType: "json",
            data: {
                page: 1
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    reloadFuniList();
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //保存数据



</script>


</html>
