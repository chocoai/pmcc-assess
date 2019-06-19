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
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        <small>
                            <div type="button" class="btn btn-xs btn-primary" onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftOperationManualCaseBase);">操作手册</div>
                        </small>
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
                                <div type="button" class="btn btn-primary"
                                        onclick="dataObjFun.loadDataList()">
                                    查询
                                </div>
                                <a class="btn btn-success" target="_blank"
                                   href="${pageContext.request.contextPath}/basicApply/basicApplyIndex">新增</a>
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

    dataObjFun.findData = function (id) {
        var href = "${pageContext.request.contextPath}/basicApply/detailView";
        href += "?id=" + id;
        window.open(href, "");
    };

    dataObjFun.temporary = function (id) {
        var href = "${pageContext.request.contextPath}/basicApply/basicApplyStart";
        href += "?applyId=" + id;
        window.open(href, "");
    };

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
            field: 'status', title: '状态', formatter: function (value, row, index) {
                if (dataObjFun.isNotBlank(row.status)){
                    if (row.status == 'finish'){
                        return "完成";
                    }
                    if (row.status == 'runing'){
                        return "正在进行";
                    }
                    if (row.status == 'close'){
                        return "关闭";
                    }
                }else {
                    return "其它状态:如异常等";
                }
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="详情" onclick="dataObjFun.findData(' + row.id + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        var estateName = $("#queryName").val() ;
        if (!dataObjFun.isNotBlank(estateName)){
            estateName = null;
        }
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/basicApply/getBootstrapTableVo", cols, {
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
