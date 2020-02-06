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
                                <a id="btn_update_house" class="btn btn-info" onclick="updateFuni()">
                                    <i class="fa fa-refresh"></i>
                                    更新楼盘
                                </a>
                                <a class="btn btn-success" onclick="addHouse()">
                                    <i class="fa fa-plus"></i>
                                    新增楼盘
                                </a>
                                共有<label class="label label-warning" id="lab_total"></label>个案例
                            </p>
                            <div id="div_house_list" class="row">

                            </div>
                            <div class="row" style="text-align: center">
                                <ul id='bp-element' class="pagination pagination-lg  pagination-bricky"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>


<div id="model_house" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">新增楼盘</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_house" class="form-horizontal" onsubmit="return false;">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼盘名称
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="楼盘名称" name="lpmc" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            区域
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="区域" name="lpdz" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼盘地址
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="楼盘地址" name="xmdz" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" onclick="saveHouse()" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script src="/assets/plugins/bootstrap-paginator/src/bootstrap-paginator.js?v=${assessVersion}"></script>
<script type="application/javascript">
    var element = $('#bp-element');
    $(function () {
        loadHouseListAjax(1);
    })

    function loadHouseListAjax(pages) {
        var pageSize = 12;
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/getHousesList",
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
                        html += "<h4 class='brief'><i><i class='fa fa-cny'></i>" + j.lpjj + "</i></h4>";
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
                        html += "<a  target='_blank'  class='btn btn-primary btn-xs' href='${pageContext.request.contextPath}/caseFuniViewer/caseFuniDetails?lpbh=" + j.id + "'>";
                        html += "<i class='fa fa-info-circle'> </i> 查看详情";
                        html += "</a>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                    });
                    $("#div_house_list").html(html);
                    $("#lab_total").html(result.total);
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
    //保存数据
    function updateFuni() {
        Alert("根据楼盘将花费较长时间，确认要现在更新么？", 2, null, function () {
            $("#btn_update_house").hide();
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseFuniViewer/updateHouses",
                type: "post",
                dataType: "json",
                data: {
                    page: 1
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
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
        });
    }
    function addHouse() {
        $("#frm_house").clearAll();
        $('#model_house').modal({backdrop: 'static', keyboard: false});
    }

    function saveHouse() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/newHouse",
            type: "post",
            dataType: "json",
            data: formParams("frm_house"),
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $('#model_house').modal('hide');
                    toastr.success('保存成功');
                    loadHouseListAjax(1);
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
