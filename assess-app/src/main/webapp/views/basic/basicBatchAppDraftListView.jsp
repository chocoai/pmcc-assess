<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js?v=${assessVersion}'></script>
    <style>
        .ui-autocomplete { z-index: 215000000 !important; }
    </style>
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
                                <div class="btn btn-success"
                                     onclick="dataObjFun.openModal()">
                                    新增
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
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘信息</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="province" name="province"
                                                    class="form-control search-select select2" required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="city" name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            楼盘名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="estateName"
                                                   placeholder="楼盘名称" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.verification()">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>

<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>

<script type="text/javascript">
    $(function () {
        dataObjFun.loadDataList();
        $("#" + dataObjFun.config.father.frm() + " input[name='estateName']").apEstate({
            getProvince: function () {
                return $("#" + dataObjFun.config.father.frm()).find("select[name='province']").val();
            },
            getCity: function () {
                return $("#" + dataObjFun.config.father.frm()).find("select[name='city']").val();
            },
            onSelect: function (id, name) {
                $(this).val(name);
            }
        });
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
        var href = "${pageContext.request.contextPath}/basicApplyBatch/applyAgain";
        href += "?id=" + id;
        window.open(href, "");
    };


    //查看
    dataObjFun.checkData = function (id) {
        var href = "${pageContext.request.contextPath}/basicApplyBatch/draftDetail";
        href += "?id=" + id;
        window.open(href, "");
    };

    dataObjFun.openModal = function () {
        $("#" + dataObjFun.config.father.frm()).clearAll();
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
        });
        $("#" + dataObjFun.config.father.box()).modal("show");
    };

    dataObjFun.verification = function () {
        if (!$("#" + dataObjFun.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.father.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/verification",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.applyIndex(result.data,data.estateName);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };


    //jin申请
    dataObjFun.applyIndex = function (estateId,estateName) {
        var id = estateId ? estateId : 0;
        var href = "${pageContext.request.contextPath}/basicApplyBatch/basicBatchApplyIndex?estateId=" + id+"&estateName="+encodeURI(estateName);
        window.open(href, "");
    };

    //删除
    dataObjFun.delete = function (id) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/deleteBasicBatchApply',
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('删除成功！');
                    dataObjFun.loadDataList();
                }
            }
        })
    }

    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({
            field: 'typeName', title: '类型'
        });
        cols.push({
            field: 'finishName', title: '状态', formatter: function (value, row, index) {
                if (row.status=='finish') {
                    return "结束";
                }else if(row.status=='runing'){
                    return "进行中";
                }else{
                    return "草稿";
                }
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.draftFlag) {
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="继续申请" onclick="dataObjFun.temporary(' + row.id + ')">继续申请</a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="dataObjFun.delete(' + row.id + ')">删除</a>';
                }else{
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="dataObjFun.checkData(' + row.id + ')">查看</a>';
                }
                str += '</div>';
                return str;
            }
        });
        var estateName = $("#queryName").val();
        if (!dataObjFun.isNotBlank(estateName)) {
            estateName = null;
        }
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/basicApplyBatch/getBasicAppBatchDraftList", cols, {
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
