
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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-1">
                                <input type="text" name="name" id="queryName" class="form-control">
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="landLevel.loadLandLevelList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="landLevel.showModel()"
                                        data-toggle="modal"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        landLevel.loadLandLevelList();
    });

    var landLevel = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadLandLevelList: function () {
            var cols = [];
            cols.push({field: 'deg', title: '旋转角度'});
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'backgroundUrl', title: '背景url'});
            cols.push({field: 'imgUrl', title: '图片url'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + landLevel.config().table).bootstrapTable('destroy');
            TableInit(landLevel.config().table, "${pageContext.request.contextPath}/dataImgTwoDimensional/getBootstrapTableVo", cols, {
                name: $("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel:function () {
            var href = "${pageContext.request.contextPath}/dataImgTwoDimensional/index";
            window.open(href, "");
        },
        editData:function (id) {
            var href = "${pageContext.request.contextPath}/dataImgTwoDimensional/index";
            href += "&id=" +id;
            window.open(href, "");
        },
        removeData:function (id) {
            Alert('确认要删除么？', 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/dataImgTwoDimensional/deleteDataImgTwoDimensionalById',
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.loadLandLevelList();
                        } else {
                            Alert(result.msg);
                        }
                    }
                })
            })
        }
    }
</script>




</html>
