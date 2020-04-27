<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
                                        <label for="queryType" class="col-md-1 col-form-label">类别</label>
                                        <div class="col-md-3 p-0">
                                            <select name="type"
                                                    class="form-control search-select select2 input-full"
                                                    id="queryType">
                                            </select>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataObjFun.listMaster()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.initFormMaster({})"
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
<input type="file" id="ajaxFileUpload" name="file" style="display: none;"
       onchange="dataObjFun.importData();">

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    var dataObjFun = {};

    dataObjFun.config = {
        frm: "#frmFather",
        box: "#divBoxFather",
        table: "#tb_FatherList",
        indexDetailTableBox: "#indexDetailTableBox",
        indexDetailTable: "#indexDetailTable",
        indexDetailBox: "#indexDetailBox",
        indexDetailFrm: "#indexDetailFrm"
    };

    dataObjFun.initFormMaster = function (data) {
        var frm = $(dataObjFun.config.frm);
        frm.clearAll();
        frm.initForm(data);
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name='province']"),
            cityTarget: frm.find("select[name='city']"),
            districtTarget: frm.find("select[name='district']"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.dataTypeIndex, data.type, function (html, data) {
            frm.find("select[name='type']").empty().html(html).trigger('change');
        });
        frm.find("select[name='type']").change(function () {
            AssessCommon.getDataDicInfo(frm.find("select[name='type']").val(), function (item) {
                var name = item.name;
                var key = null;
                if (name) {
                    if (name.indexOf('土地') != -1) {
                        key = AssessDicKey.estate_total_land_use;
                    }
                    if (name.indexOf('房价') != -1) {
                        key = AssessDicKey.examineHouseLoadUtility;
                    }
                }
                if (key) {
                    AssessCommon.loadDataDicByKey(key, data.purpose, function (html, data) {
                        frm.find("select[name='purpose']").empty().html(html).trigger('change');
                    });
                }
            });
        });
    };

    dataObjFun.editMasterById = function (index) {
        var row = $(dataObjFun.config.table).bootstrapTable('getData')[index];
        $.ajax({
            url: "${pageContext.request.contextPath}/housePriceIndex/get/" + row.id,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    dataObjFun.initFormMaster(result.data);
                    $(dataObjFun.config.box).modal("show");
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    dataObjFun.deleteMasterById = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/housePriceIndex/delete/' + id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.listMaster();
                    } else {
                        AlertError("删除数据失败", result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.listMaster = function () {
        var cols = [];
        cols.push({field: 'areaName', title: '区域'});
        cols.push({field: 'typeName', title: '类别'});
        cols.push({field: 'purposeName', title: '用途'});
        cols.push({
            field: 'releaseDate', title: '发布时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'evaluationDate', title: '报告期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'basePeriod', title: '基期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="dataObjFun.showDataHousePriceIndexDetail(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.editMasterById(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteMasterById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $(dataObjFun.config.table).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.table).prop("id"), "${pageContext.request.contextPath}/housePriceIndex/getBootstrapTableVo", cols, {
            type: $("#queryType").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    dataObjFun.saveMaster = function () {
        var frm = $(dataObjFun.config.frm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/housePriceIndex/save",
            data: {formData: JSON.stringify(data)},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    dataObjFun.listMaster();
                    $(dataObjFun.config.box).modal("hide");
                } else {
                    AlertError("错误", "保存数据失败");
                }
            }
        })
    };

    dataObjFun.showDataHousePriceIndexDetail = function (id) {
        $(dataObjFun.config.indexDetailTableBox).find("input[name='housePriceId']").val(id);
        $(dataObjFun.config.indexDetailTableBox).modal("show");
        dataObjFun.showDataHousePriceIndexDetailList(id);
    };

    dataObjFun.showDataHousePriceIndexDetailBox = function () {
        $(dataObjFun.config.indexDetailFrm).clearAll();
        var housePriceId = $(dataObjFun.config.indexDetailTableBox).find("input[name='housePriceId']").val();
        $(dataObjFun.config.indexDetailFrm).find('input[name=housePriceId]').val(housePriceId);
    };

    dataObjFun.deleteDataHousePriceIndexDetail = function (index) {
        var row = $(dataObjFun.config.indexDetailTable).bootstrapTable('getData')[index];
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataHousePriceIndexDetail/delete/' + row.id,
                type: "post",
                data: {_method: "DELETE"},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.showDataHousePriceIndexDetailList(row.housePriceId);
                    } else {
                        AlertError("删除数据失败", result.errmsg);
                    }
                }
            })
        })
    };

    dataObjFun.editDataHousePriceIndexDetail = function (index) {
        var row = $(dataObjFun.config.indexDetailTable).bootstrapTable('getData')[index];
        var frm = $(dataObjFun.config.indexDetailFrm);
        frm.clearAll();
        frm.initForm(row);
        frm.find('[name=startDate]').val(dataObjFun.formatDateMonth(row.startDate));
        frm.find('[name=endDate]').val(dataObjFun.formatDateMonth(row.endDate));
        $(dataObjFun.config.indexDetailBox).modal("show");
    };

    dataObjFun.saveDataHousePriceIndexDetail = function () {
        var frm = $(dataObjFun.config.indexDetailFrm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        data.startDate = data.startDate + "-01 00:00:00";
        data.endDate = data.endDate + "-01 00:00:00";
        $.ajax({
            url: '${pageContext.request.contextPath}/dataHousePriceIndexDetail/save',
            data: {formData: JSON.stringify(data)},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    dataObjFun.showDataHousePriceIndexDetailList(data.housePriceId);
                    $(dataObjFun.config.indexDetailBox).modal("hide");
                } else {
                    AlertError("错误", "保存数据失败")
                }
            }
        })
    };

    dataObjFun.showDataHousePriceIndexDetailList = function (housePriceId) {
        var cols = [];
        cols.push({
            field: 'startDate', title: '开始月份', formatter: function (value, row, index) {
                return dataObjFun.formatDateMonth(value);
            }
        });
        cols.push({
            field: 'endDate', title: '结束月份', formatter: function (value, row, index) {
                return dataObjFun.formatDateMonth(value);
            }
        });
        cols.push({field: 'unitPremium', title: '单位地价'});
        cols.push({field: 'floorPremium', title: '楼面地价'});
        cols.push({field: 'indexNumber', title: '指数'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="dataObjFun.editDataHousePriceIndexDetail(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteDataHousePriceIndexDetail(' + index + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $(dataObjFun.config.indexDetailTable).bootstrapTable('destroy');
        TableInit($(dataObjFun.config.indexDetailTable).prop("id"), "${pageContext.request.contextPath}/dataHousePriceIndexDetail/getBootstrapTableVo", cols, {
            housePriceId: housePriceId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    dataObjFun.formatDateMonth = function (v) {
        if (!v) {
            return "";
        }
        if (/^(-)?\d{1,10}$/.test(v)) {
            v = v * 1000;
        } else if (/^(-)?\d{1,13}$/.test(v)) {
            v = v * 1;
        }
        var dateObj = new Date(v);
        var month = dateObj.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var UnixTimeToDate = dateObj.getFullYear() + '-' + month;

        return UnixTimeToDate;
    };

    //生成并下载模板
    dataObjFun.generateTemplate = function () {
        var href = "${pageContext.request.contextPath}/dataHousePriceIndexDetail/generateTemplate";
        window.open(href, "");
    };

    dataObjFun.importData = function () {
        var housePriceId = $(dataObjFun.config.indexDetailTableBox).find("input[name='housePriceId']").val();
        $.ajaxFileUpload({
            type: "POST",
            url: getContextPath() + "/dataHousePriceIndexDetail/importData",
            data: {
                housePriceId: housePriceId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUpload',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    dataObjFun.showDataHousePriceIndexDetailList(housePriceId);
                    notifySuccess("成功", result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    $(function () {
        dataObjFun.listMaster();
        AssessCommon.loadDataDicByKey(AssessDicKey.dataTypeIndex, null, function (html, data) {
            $("#frmQuery").find("select[name='type']").empty().html(html).trigger('change');
        });
        DatepickerUtils.initDate($('.dbdate-month'), {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true,
            format: 'yyyy-mm',
            startView: 4,
            minView: 3
        });
    })
</script>


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">指数</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="templateId" name="id" required>
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
                                                <select name="province"
                                                        class="form-control search-select select2 input-full"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                市<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="city"
                                                        class="form-control search-select select2 input-full"
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
                                                县
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="district"
                                                        class="form-control search-select select2 input-full">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="type" required="required"
                                                        class="form-control search-select select2 input-full">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                用途
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="purpose"
                                                        class="form-control search-select select2 input-full">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                发布时间
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       name="releaseDate" placeholder="发布日期">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                报告期
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       name="evaluationDate" placeholder="报告期">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                基期
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       name="basePeriod" placeholder="基期">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.saveMaster()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="indexDetailTableBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">指数详情</h4>
                <input type="hidden" name="housePriceId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <div class="form-inline form-inline x-valid">
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="dataObjFun.showDataHousePriceIndexDetailBox({})"
                                        href="#indexDetailBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                                <div class="btn-group">
                                    <button type="button" style="margin-left: 5px"
                                            class="btn btn-info btn-sm dropdown-toggle"
                                            data-toggle="dropdown">
                                                     <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
                                                         </span>导入数据
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="btn"
                                               onclick="dataObjFun.generateTemplate()">下载模板</a>
                                        </li>
                                        <li>
                                            <a class="btn"
                                               onclick="$('#ajaxFileUpload').val('').trigger('click')"><span>导入</span></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row row form-group">
                                <div class="col-md-12">
                                    <table class="table table-bordered" id="indexDetailTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<div id="indexDetailBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房价指数</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="indexDetailFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="housePriceId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                开始月份<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly" required
                                                       class="form-control input-full date-picker dbdate-month"
                                                       data-date-format="yyyy-mm-dd"
                                                       name="startDate" placeholder="开始月份">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                结束月份<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly" required
                                                       class="form-control input-full date-picker dbdate-month"
                                                       data-date-format="yyyy-mm"
                                                       name="endDate" placeholder="结束月份">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                单位地价
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       class="form-control input-full" name="unitPremium"
                                                       placeholder="单位地价">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                楼面地价
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       class="form-control input-full" name="floorPremium"
                                                       placeholder="楼面地价">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                指数<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required
                                                       class="form-control input-full"
                                                       name="indexNumber" placeholder="指数">
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="dataObjFun.saveDataHousePriceIndexDetail()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
