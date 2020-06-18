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
<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">楼盘名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="楼盘名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataObjFun.loadBatchApplyList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.openModal()"
                                                href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                省<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="province" name="province"
                                                        class="form-control input-full search-select select2" required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                市<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="city" name="city" class="form-control input-full search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                楼盘名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="estateName"
                                                       placeholder="楼盘名称" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.verification()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>
<script type="text/javascript">
    $(function () {
        dataObjFun.loadBatchApplyList();
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
            useDefaultText: true,
            provinceDefaultText: '四川',
            provinceTarget: $("#province"),
            cityTarget: $("#city")
        });
        $("#" + dataObjFun.config.father.box()).modal("show");
    };

    dataObjFun.verification = function () {
        if (!$("#" + dataObjFun.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.father.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/recordBatchApply",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    var href = "${pageContext.request.contextPath}/basicApplyBatch/applyAgain?id=" + result.data;
                    openWin(href);
                    dataObjFun.loadBatchApplyList();
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    //删除
    dataObjFun.delete = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/basicApplyBatch/deleteBasicBatchApply',
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.loadBatchApplyList();
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                }
            })
        })
    }

    dataObjFun.loadBatchApplyList = function () {
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
            field: 'gmtCreated', title: '时间', formatter: function (value, row, index) {
                return formatDate(value,true);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.draftFlag) {
                    //str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="继续申请" onclick="dataObjFun.temporary(' + row.id + ')">继续申请</a>';
                    str += '<button onclick="dataObjFun.temporary(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="继续申请">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    //str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="dataObjFun.delete(' + row.id + ')">删除</a>';
                    str += '<button onclick="dataObjFun.delete(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                }else{
                    //str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="dataObjFun.checkData(' + row.id + ')">查看</a>';
                    str += '<button onclick="dataObjFun.checkData(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
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
