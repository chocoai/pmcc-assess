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
                    <div id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    楼盘名称
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="楼盘名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <div class="btn btn-primary"
                                        onclick="dataObjFun.loadDataList()">
                                    查询
                                </div>
                            </div>
                        </div>

                    </div>
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
        dataObjFun.loadDataList();
    });
    var DataObjFun = function () {

    };
    DataObjFun.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_FatherList";
            },
            box: function () {
                return "divBoxFather";
            }
        }
    };

    /**
     * 判断字符串以及null等
     */
    DataObjFun.prototype.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };


    var dataObjFun = new DataObjFun();

    //继续申请
    dataObjFun.temporary = function (id) {
        var href = "${pageContext.request.contextPath}/basicApply/basicApplyStart";
        href += "?applyId=" + id;
        window.open(href, "");
    };

    //删除
    dataObjFun.delete = function (id) {
        Alert('确定要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/basicApply/deleteBasicApply',
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功！');
                        dataObjFun.loadDataList();
                    }
                }
            })
        })
    }

    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({
            field: 'type', title: '类型', formatter: function (value, row, index) {
                if (value == 0) {
                    return "非工业交通仓储";
                }
                if (value == 1) {
                    return "工业交通仓储";
                }
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="继续申请" onclick="dataObjFun.temporary(' + row.id + ')">继续申请</a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="dataObjFun.delete(' + row.id + ')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        var estateName = $("#queryName").val();
        if (!dataObjFun.isNotBlank(estateName)) {
            estateName = null;
        }
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/basicApply/getBasicAppDraftList", cols, {
            estateName: estateName
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

</script>


</html>
