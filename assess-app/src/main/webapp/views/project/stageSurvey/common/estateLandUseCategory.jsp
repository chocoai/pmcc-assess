<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="categoryDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        土地类型类别（_number）
                        <small>
                            <button href="javascript://;" class="btn btn-sm btn-warning" type="button"
                                    onclick="landUseType.saveAndUpdateSonData('categoryFrm_number');">保存
                            </button>
                            <button type="button" href="javascript://;" class="btn btn-sm btn-warning"
                                    onclick="landUseType.cleanHTMLData(this)">
                                移除
                            </button>
                        </small>
                    </div>
                    <div class="card-tools">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="categoryFrm_number">
                    <input type="hidden" name="id">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地用途类型<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="landUseType"
                                                           placeholder="土地用途类型"
                                                           class="form-control" list="landUseTypeList_number">
                                                    <datalist id="landUseTypeList_number">

                                                    </datalist>
                                                    <div class="input-group-prepend .cleanUseTypeBtn">
                                                        <button class="btn btn-warning btn-sm cleanUseTypeBtn"
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-1 control-label">土地用途类别<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="landUseCategory"
                                                           class="form-control" list="landUseCategoryList_number"
                                                           placeholder="土地用途类别">
                                                    <datalist id="landUseCategoryList_number">

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
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地取得时间</label>
                                            <div class="col-sm-3">
                                                <input name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                                       placeholder="土地取得时间"
                                                       class="form-control input-full date-picker dbdate">
                                            </div>
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
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">开发时间</label>
                                            <div class="col-sm-3">
                                                <input name="developTime" data-date-format="yyyy-mm-dd"
                                                       placeholder="开发时间"
                                                       class="form-control input-full date-picker dbdate">
                                            </div>
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
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">绿地率</label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="绿化率" name="greeningRate"
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-1 control-label">兼容类型</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" name="compatibilityType"
                                                           class="form-control" list="compatibilityTypeList_number"
                                                           placeholder="兼容类型">
                                                    <datalist id="compatibilityTypeList_number">

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
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
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
        </div>

    </div>
</script>



<script type="text/javascript">
    $(function () {
        landUseType.loadHtml();
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


    var num = 1;

    landUseType.loadSonDataListHtml = function (landUseTypeId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/basicEstateLandUseCategoryList",
            type: "get",
            dataType: "json",
            data: {landUseTypeId: landUseTypeId},
            success: function (result) {
                if (result.ret) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                console.log(item.id + "==")
                                var html = $("#categoryDiv").html();
                                var number = num;
                                html = html.replace(/_number/g, number);
                                $("#LandUseTypeContent").append(html);
                                $("#categoryFrm" + number).initForm(item);

                                AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, item.landUseType, item.landUseCategory, function (html, data) {
                                    $("#categoryFrm" + number).find("#landUseCategoryList" + number).empty().html(html).trigger('change');
                                });
                                AssessCommon.loadDataListHtml(AssessDicKey.estate_compatibility_rate, item.compatibilityType, function (html, data) {
                                    $("#categoryFrm" + number).find("#compatibilityTypeList" + number).empty().html(html).trigger('change');
                                }, false);
                                AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, item.landUseType, function (html, data) {
                                    $("#categoryFrm" + number).find("#landUseTypeList" + number).empty().html(html).trigger('change');
                                }, false);
                                //百分字段
                                $("#categoryFrm" + number).find('[name=greeningRate]').attr('data-value', item.greeningRate);
                                AssessCommon.elementParsePercent($("#categoryFrm" + number).find('[name=greeningRate]'));
                                $("#categoryFrm" + number).find('[name=compatibilityRate]').attr('data-value', item.compatibilityRate);
                                AssessCommon.elementParsePercent($("#categoryFrm" + number).find('[name=compatibilityRate]'));
                                $("#categoryFrm" + number).find("input[name='landUseType']").off('change').on('change', function () {
                                    AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), item.landUseCategory, function (html, data) {
                                        $("#categoryFrm" + number).find("#landUseCategoryList" + number).empty().html(html).trigger('change');
                                    });
                                });
                                num++;
                            });
                        }
                    }
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

    landUseType.appendHtml = function () {
        var html = $("#categoryDiv").html();
        var number = num;
        html = html.replace(/_number/g, number);
        $("#LandUseTypeContent").append(html);

        AssessCommon.loadDataListHtml(AssessDicKey.estate_compatibility_rate, '', function (html, data) {
            $("#categoryFrm" + number).find("#compatibilityTypeList" + number).empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, '', function (html, data) {
            $("#categoryFrm" + number).find("#landUseTypeList" + number).empty().html(html).trigger('change');
        }, false);
        $("#categoryFrm" + number).find("input[name='landUseType']").off('change').on('change', function () {
            AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), '', function (html, data) {
                $("#categoryFrm" + number).find("#landUseCategoryList" + number).empty().html(html).trigger('change');
            });
        });

        num++;
    }

    landUseType.saveAndUpdateSonData = function (_that) {
        if (!$("#"+_that).valid()) {
            return false;
        }
        var data = formParams(_that);
        data.estateId = estateCommon.getEstateId();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/saveAndUpdateBasicEstateLandUseCategory",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
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


    landUseType.loadHtml = function () {
        //找到所有主表数据，一条一个table
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/basicEstateLandUseTypeList",
            type: "get",
            dataType: "json",
            data: {estateId: estateCommon.getEstateId()},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $.each(result.data, function (i, item) {
                            landUseType.loadSonDataListHtml(item.id);
                        })
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })

    }

    landUseType.cleanHTMLData= function(_this) {
        var x_panel = $(_this).closest(".col-md-12");
        var form = x_panel.find("form").eq(0);
        var id= form.find("input[name='id']").val()
        if(id){
            $.ajax({
                url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/deleteBasicEstateLandUseCategory",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        x_panel.remove();
                        notifySuccess('成功', '删除成功');
                    }
                    else {
                        ("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }else{
            x_panel.remove();
            notifySuccess("成功", "删除数据成功");
        }
    }
</script>


