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
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.showModel();"
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
    }
    DataObjFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var dataObjFun = new DataObjFun();

    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({
            field: 'area', title: '区域', formatter: function (value, row, index) {
                var result = '';
                if (row.provinceName) {
                    result = row.provinceName;
                }
                if (row.cityName) {
                    result += row.cityName;
                }
                if (row.districtName) {
                    result += row.districtName;
                }
                return result;
            }
        });
        cols.push({
            field: 'publishTime', title: '发布日期', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'content', title: '内容'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="dataObjFun.editDataById(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/dataBuildingInstallCost/getDataBuildingInstallCostList", cols, {
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
    /**
     * @author:  zch
     * 描述:删除
     * @date:
     **/
    dataObjFun.deleteDataById = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataBuildingInstallCost/deleteDataBuildingInstallCostById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.loadDataList();
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        });
    };
    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    dataObjFun.editDataById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataBuildingInstallCost/getDataBuildingInstallCostById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + dataObjFun.config.father.frm()).clearAll();
                    var data = result.data;
                    if (dataObjFun.isEmpty(data)) {
                        $("#" + dataObjFun.config.father.frm()).initForm(result.data);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#province"),
                            cityTarget: $("#city"),
                            districtTarget: $("#district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        })
                    }
                    $('#' + dataObjFun.config.father.box()).modal("show");
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    /**
     * @author:  zch
     * 描述:保存或者更新
     * @date:
     **/
    dataObjFun.saveAndUpdateData = function () {
        if (!$("#" + dataObjFun.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.father.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/dataBuildingInstallCost/saveAndUpdateDataBuildingInstallCost",
            type: "post",
            dataType: "json",
            data: {formData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.loadDataList();
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    dataObjFun.showModel = function () {
        $("#" + dataObjFun.config.father.frm()).clearAll();
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district")
        })
        $('#' + dataObjFun.config.father.box()).modal("show");
    }
</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑安装费计费维护</h4>
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
                                                县
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="district" name="district"
                                                        class="form-control input-full search-select select2">
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
                                                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                       name="publishTime" placeholder="发布日期">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型
                                            </label>
                                            <div class="col-sm-10">
                                                <select  name="type"
                                                         class="form-control input-full search-select select2">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${dataBuildingInstallCostType}" var="item">
                                                        <option value="${item.key}">${item.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                内容
                                            </label>
                                            <div class="col-sm-11">
                                                <textarea name="content" class="form-control input-full" placeholder="内容"></textarea>

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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.saveAndUpdateData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


</html>
