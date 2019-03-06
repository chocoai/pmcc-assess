<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="row">
                <div class="col-md-12 ">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>xxx申请</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p id="toolbar">
                                共有<label class="label label-warning" id="lab_total"></label>个案例需补充信息
                            </p>
                            <div id="div_house_list" class="row">

                            </div>
                            <div class="row" style="text-align: right">
                                <ul id='bp-element' class="pagination pagination-lg  pagination-bricky"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_content">
                            <div style="text-align: center;">
                                <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                    取消
                                </button>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
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
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script src="/assets/plugins/bootstrap-paginator/src/bootstrap-paginator.js"></script>
<script type="application/javascript">
    var element = $('#bp-element');
    $(function () {
        loadHouseListAjax(1);
    })

    function loadHouseListAjax(pages) {
        var pageSize = 12;
        $.ajax({
            url: "${pageContext.request.contextPath}/taskCaseAssign/getApplyHousesList",
            type: "get",
            dataType: "json",
            data: {
                offset: pages,
                limit: pageSize,
                lpbh: "${lpbh}"
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
                        html += "<div class='col-xs-12 col-sm-4 emphasis'>";
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
                        html += "<label>已完成";
                        html += "<input type='radio' name='complete" + j.id + "' value='true' >";
                        html += "</label>";
                        html += "<label>未完成";
                        html += "<input type='radio' checked name='complete" + j.id + "' value='false'>";
                        html += "</label>";
                        html += "</div>";
                        html += "<div class='col-xs-12 col-sm-4 emphasis'>";
                        html += "<a  target='_blank'  class='btn btn-primary btn-xs' href='${pageContext.request.contextPath}/taskCaseAssign/basicApplyIndex?lpbh=" + j.id + "'>";
                        html += "<i class='fa fa-info-circle'> </i> 补充信息";
                        html += "</a>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                    });
                    $("#div_house_list").html(html);
                    $("#lab_total").html(data.length);
                    options = {
                        alignment: "left",
                        bootstrapMajorVersion: 3,
                        currentPage: pages, //当前页数，这里是用的EL表达式，获取从后台传过来的值
                        numberOfPages: 5, //每页显示按钮个数
                        totalPages: Math.ceil(data.length / pageSize), //总页数，这里是用的EL表达式，获取从后台传过来的值
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
    function submit() {
        var radio = $("#div_house_list").find('[name^=complete]:checked');
        for (var i = 0; i < radio.length; i++) {
            if (radio[i].value == 'false') {
                Alert("全部完成后提交");
                return;
            };
        };
        $.ajax({
            url: "${pageContext.request.contextPath}/taskCaseAssign/applySubmit",
            type: "post",
            dataType: "json",
            data: {id: "${id}"},
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("数据提交成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });

    }


</script>


</html>
