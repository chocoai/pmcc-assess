<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divBoxUseTypeFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地用途类型</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">土地用途类型</label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="landUseType"
                                                           class="form-control" list="landUseTypeList">
                                                    <datalist id="landUseTypeList">

                                                    </datalist>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="landUseType.saveAndUpdateData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="LandUseCategoryListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细数据列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="LandUseCategoryListBoxFrm">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="col-md-12">
                                    <p>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                onclick="landUseType.showSonModel()">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </p>
                                    <table class="table table-bordered" id="tb_LandUseCategoryList">
                                    </table>
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
            </div>

        </div>
    </div>
</div>

<div id="divBoxUseCategorySon" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">填写信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmSon" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地用途类别</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="landUseCategory"
                                                           class="form-control" list="landUseCategoryList"
                                                           placeholder="土地用途类别">
                                                    <datalist id="landUseCategoryList">

                                                    </datalist>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1 control-label">土地级别</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="hidden" name="landLevel">

                                                    <input type="text" readonly="readonly" name="landLevelName"
                                                           onclick="examineCommon.landLevelSelect(this);"
                                                           placeholder="土地级别" class="form-control">

                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="examineCommon.landLevelSelect(this);">
                                                            选择
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1 control-label">土地取得时间</label>
                                            <div class="col-sm-3">
                                                <input name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                                       placeholder="土地取得时间"
                                                       class="form-control input-full date-picker dbdate">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地使用年限</label>
                                            <div class="col-sm-3">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="landUseYear" class="form-control input-full"
                                                       placeholder="土地使用年限">
                                            </div>
                                            <label class="col-sm-1 control-label">土地形状</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="landShape" class="form-control input-full"
                                                       placeholder="土地形状">
                                            </div>
                                            <label class="col-sm-1 control-label">开发时间</label>
                                            <div class="col-sm-3">
                                                <input name="developTime" data-date-format="yyyy-mm-dd" placeholder="开发时间"
                                                       class="form-control input-full date-picker dbdate">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">容积率</label>
                                            <div class="col-sm-3">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       placeholder="容积率" name="plotRatio"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">建筑密度</label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="建筑密度" name="buildingDensity"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">绿地率</label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="绿化率" name="greeningRate"
                                                        class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">兼容类型</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="compatibilityType"
                                                           class="form-control" list="compatibilityTypeList"
                                                           placeholder="兼容类型">
                                                    <datalist id="compatibilityTypeList">

                                                    </datalist>
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1 control-label">兼容比例</label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="兼容比例" name="compatibilityRate"
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-1 control-label">建筑限高</label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="建筑限高" name="heightPermitted"
                                                        class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="landUseType.saveAndUpdateSonData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        landUseType.loadDataList();
    });

    var LandUseType = function () {

    };
    LandUseType.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_LandUseTypeList";
            },
            box: function () {
                return "divBoxUseTypeFather";
            }
        },
        son: {
            frm: function () {
                return "frmSon";
            },
            table: function () {
                return "tb_LandUseCategoryList";
            },
            box: function () {
                return "divBoxUseCategorySon";
            },
            tableBox: function () {
                return "LandUseCategoryListBox";
            },
            tableFrm: function () {
                return "LandUseCategoryListBoxFrm";
            },
        }
    }
    LandUseType.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var landUseType = new LandUseType();

    landUseType.loadDataList = function () {
        var cols = [];
        cols.push({field: 'landUseType', title: '土地用途类型'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landUseType.showSonBoxModel(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button type="button" onclick="landUseType.editDataById(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="landUseType.deleteDataById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#" + landUseType.config.father.table()).bootstrapTable('destroy');
        TableInit(landUseType.config.father.table(), "${pageContext.request.contextPath}/basicEstateLandUseType/getBootstrapTableVo", cols, {
            estateId: estateCommon.getEstateId()
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
    landUseType.deleteDataById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/deleteBasicEstateLandUseType",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '删除成功');
                    landUseType.loadDataList();
                }
                else {
                    ("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    landUseType.editDataById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/getBasicEstateLandUseTypeById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + landUseType.config.father.frm()).clearAll();
                    $("#" + landUseType.config.father.frm()).initForm(result.data);
                    AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, result.data.landUseType, function (html, data) {
                        $("#" + landUseType.config.father.frm()).find("#landUseTypeList").empty().html(html).trigger('change');
                    }, true);
                    $('#' + landUseType.config.father.box()).modal("show");
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    /**
     * @author:  zch
     * 描述:保存或者更新
     * @date:
     **/
    landUseType.saveAndUpdateData = function () {
        if (!$("#" + landUseType.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(landUseType.config.father.frm());
        data.estateId = estateCommon.getEstateId();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/saveAndUpdateBasicEstateLandUseType",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    $('#' + landUseType.config.father.box()).modal('hide');
                    landUseType.loadDataList();
                }
                else {
                    AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    landUseType.showModel = function () {
        $("#" + landUseType.config.father.frm()).clearAll();
        AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, '', function (html, data) {
            $("#" + landUseType.config.father.frm()).find("#landUseTypeList").empty().html(html).trigger('change');
        }, true);
        $('#' + landUseType.config.father.box()).modal("show");
    }

    landUseType.showSonBoxModel = function (id) {
        $("#" + landUseType.config.son.tableFrm()).clearAll();
        $("#" + landUseType.config.son.tableFrm()).find("input[name='landUseTypeId']").val(id);
        landUseType.loadSonDataList(id);
        $('#' + landUseType.config.son.tableBox()).modal("show");

    }


    landUseType.loadSonDataList = function (landUseTypeId) {
        var cols = [];
        cols.push({field: 'landUseCategory', title: '土地用途类型'});
        cols.push({field: 'landLevelName', title: '土地级别'});
        cols.push({
            field: 'acquisitionTime', title: '土地取得时间',formatter: function (value, row, index) {
                return formatDate(row.acquisitionTime, false);
            }
        });
        cols.push({field: 'landUseYear', title: '土地使用年限'});
        cols.push({field: 'landShape', title: '土地形状'});
        cols.push({
            field: 'developTime', title: '开发时间',formatter: function (value, row, index) {
                return formatDate(row.developTime, false);
            }
        });
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'buildingDensity', title: '建筑密度'});
        cols.push({
            field: 'greeningRate', title: '绿地率', formatter: function (value, row, index) {
                if (value != null || value != undefined) {
                    return AssessCommon.pointToPercent(value);
                }
            }
        });
        cols.push({field: 'compatibilityType', title: '兼容类型'});
        cols.push({
            field: 'compatibilityRate', title: '兼容比例', formatter: function (value, row, index) {
                if (value != null || value != undefined) {
                    return AssessCommon.pointToPercent(value);
                }
            }
        });
        cols.push({field: 'heightPermitted', title: '建筑限高'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landUseType.editSonDataById(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="landUseType.deleteSonDataById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#" + landUseType.config.son.table()).bootstrapTable('destroy');
        TableInit(landUseType.config.son.table(), "${pageContext.request.contextPath}/basicEstateLandUseCategory/getBootstrapTableVo", cols, {
            landUseTypeId: landUseTypeId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    landUseType.showSonModel = function () {
        var landUseTypeId = $("#" + landUseType.config.son.tableFrm()).find("input[name='landUseTypeId']").val();
        $("#" + landUseType.config.son.frm()).clearAll();
        $("#" + landUseType.config.son.frm()).find("input[name='landUseTypeId']").val(landUseTypeId);
        AssessCommon.loadDataListHtml(AssessDicKey.estate_compatibility_rate, '', function (html, data) {
            $("#" + landUseType.config.son.frm()).find("#compatibilityTypeList").empty().html(html).trigger('change');
        }, true);
        landUseType.loadCategoryList(landUseTypeId,'');
        $('#' + landUseType.config.son.box()).modal("show");
    }

    landUseType.saveAndUpdateSonData = function () {
        if (!$("#" + landUseType.config.son.frm()).valid()) {
            return false;
        }
        var data = formParams(landUseType.config.son.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/saveAndUpdateBasicEstateLandUseCategory",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    $('#' + landUseType.config.son.box()).modal('hide');
                    landUseType.loadSonDataList(data.landUseTypeId);
                }
                else {
                    AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    landUseType.editSonDataById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/getBasicEstateLandUseCategoryById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + landUseType.config.son.frm()).clearAll();
                    $("#" + landUseType.config.son.frm()).initForm(result.data);
                    AssessCommon.loadDataListHtml(AssessDicKey.estate_compatibility_rate, result.data.compatibilityType, function (html, data) {
                        $("#" + landUseType.config.son.frm()).find("#compatibilityTypeList").empty().html(html).trigger('change');
                    }, true);
                    landUseType.loadCategoryList(result.data.landUseTypeId,result.data.landUseCategory);
                    //百分字段
                    $("#" + landUseType.config.son.frm()).find('[name=greeningRate]').attr('data-value', result.data.greeningRate);
                    AssessCommon.elementParsePercent($("#" + landUseType.config.son.frm()).find('[name=greeningRate]'));
                    $("#" + landUseType.config.son.frm()).find('[name=compatibilityRate]').attr('data-value', result.data.compatibilityRate);
                    AssessCommon.elementParsePercent($("#" + landUseType.config.son.frm()).find('[name=compatibilityRate]'));
                    $('#' + landUseType.config.son.box()).modal("show");
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    landUseType.loadCategoryList = function (id,categoryValue) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/getBasicEstateLandUseTypeById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, result.data.landUseType, categoryValue, function (html, data) {
                        $("#" + landUseType.config.son.frm()).find("#landUseCategoryList").empty().html(html).trigger('change');
                    });
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    landUseType.deleteSonDataById = function (id) {
        var landUseTypeId = $("#" + landUseType.config.son.tableFrm()).find("input[name='landUseTypeId']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/deleteBasicEstateLandUseCategory",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '删除成功');
                    landUseType.loadSonDataList(landUseTypeId);
                }
                else {
                    ("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
</script>


