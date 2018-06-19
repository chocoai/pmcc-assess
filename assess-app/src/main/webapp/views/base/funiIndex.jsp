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
            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-xs-3" id="container1">
                        <p id="toolbar">
                            <%--<a class="btn btn-success" onclick="updateFuni()">--%>
                                <%--<i class="fa fa-plus"></i>--%>
                                <%--更新楼盘--%>
                            <%--</a>--%>
                        </p>
                        <table id="tb_List">

                        </table>
                    </div>
                    <div class="col-xs-9">
                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#tab_content1" role="tab" data-toggle="tab" aria-expanded="true">楼盘</a>
                                </li>
                                <li role="presentation" class=""><a href="#tab_content2" role="tab" data-toggle="tab" aria-expanded="false">户型</a>
                                </li>
                                <li role="presentation" class=""><a href="#tab_content3" role="tab" data-toggle="tab" aria-expanded="false">物业</a>
                                </li>
                                <li role="presentation" class=""><a href="#tab_content4" role="tab" data-toggle="tab" aria-expanded="false">地图</a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                                    <form id="frm_content1">
                                        <table class="table table-condensed table-hover">
                                            <thead>
                                            <tr>
                                                <th colspan="2"><label name="lpmc"></label></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td class="col-sm-2">建筑面积</td>
                                                <td><label name="jzmj"></label></td>
                                            </tr>
                                            <tr>
                                                <td>占地面积</td>
                                                <td><label name="zdmj"></label></td>
                                            </tr>
                                            <tr>
                                                <td>容积率</td>
                                                <td><label name="rjl"></label></td>
                                            </tr>
                                            <tr>
                                                <td>绿化率</td>
                                                <td><label name="lhl"></label></td>
                                            </tr>
                                            <tr>
                                                <td>车位信息</td>
                                                <td><label name="cwxx"></label></td>
                                            </tr>
                                            <tr>
                                                <td>项目地址</td>
                                                <td><label name="lpdz"></label></td>
                                            </tr>
                                            <tr>
                                                <td>售楼地址</td>
                                                <td><label name="sldz"></label></td>
                                            </tr>
                                            <tr>
                                                <td>开发商</td>
                                                <td><label name="kfsbh"></label></td>
                                            </tr>
                                            <tr>
                                                <td>销售许可证</td>
                                                <td><label name="ssskzh"></label></td>
                                            </tr>
                                            <tr>
                                                <td>楼盘介绍</td>
                                                <td><label name="lpjs"></label></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>

                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                                    <h3>没有上传楼盘户型信息</h3>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                                    <h3>物业信息未填写</h3>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="tab_content4 " aria-labelledby="profile-tab">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadHouseList();

    })

    function showImage(obj) {
        var url = $(obj).attr("src");
        url = url.replace(".240x1000.jpg", "");
        window.open(url);
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
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },
            onClickRow: function (row) {
                $("#frm_content1").initForm(row);
                loadHousesTypeList(row.id);
                loadHousesPropertyList(row.id);
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
                    wylx.push({cnName: "层高", fieldName: "jzlb"});
                    wylx.push({cnName: "开盘时间", fieldName: "kpsj"});
                    wylx.push({cnName: "交房时间", fieldName: "jfsj"});
                    wylx.push({cnName: "户型区间", fieldName: "fxqj"});
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
                            if (j[wylx[k].fieldName] != "" && j[wylx[k].fieldName] != null) {

                                html += "<tr>";
                                html += "<td>" + wylx[k].cnName + "</td>";
                                html += "<td>" + j[wylx[k].fieldName] + "</td>";
                                html += "</tr>";
                            }
                        }
                        //html += "</tbody>";

                    });
                    html += "</table>";
                    if (data.length<=0) {
                        html = "<h3>物业信息未填写</h3>";
                    }
                    $("#tab_content3").html(html);
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


</script>


</html>
