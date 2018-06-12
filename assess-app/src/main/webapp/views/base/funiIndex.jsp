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
                            <a class="btn btn-success" onclick="updateFuni()">
                                <i class="fa fa-plus"></i>
                                更新楼盘
                            </a>
                        </p>
                        <table id="tb_List">

                        </table>
                    </div>
                    <div class="col-xs-9" id="container3">
                        <%@include file="/views/base/mapPositionPicker.jsp" %>
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
    //加载代理数据列表
    function loadHouseList() {
        var cols = [];
        cols.push({field: 'lpmc', title: '楼盘名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/funiViewer/getHousesList", cols, {}, {
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
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
