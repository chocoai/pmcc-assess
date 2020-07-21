/**
 * Created by kings on 2018-11-9.
 */

var houseHuxingPrice;
(function () {
    houseHuxingPrice = function () {

    };
    houseHuxingPrice.num = 0;
    houseHuxingPrice.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseHuxingPriceList";
            data.box = "divBoxHouseHuxingPrice";
            data.frm = "frmHouseHuxingPrice";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseHuxingPriceColumn();
            var temp = [];
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            if (tenementType == '住宅' || tenementType == '办公') {
                temp = commonColumn.houseRoomResidence();
            } else if (tenementType == '商铺' || tenementType == '商场') {
                temp = commonColumn.houseRoomStore();
            } else if (tenementType == '餐饮酒店') {
                temp = commonColumn.houseRoomHotel();
            } else if (tenementType == '生产') {
                temp = commonColumn.houseRoomProduction();
            } else if (tenementType == '仓储') {
                temp = commonColumn.houseRoomStorage();
            }
            $.each(temp, function (i, item) {
                cols.push(item);
            })
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseHuxingPrice.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseHuxingPrice.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHuxingPrice.prototype.config().table, getContextPath() + "/basicHouseHuxingPrice/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseHuxingPrice/deleteBasicHouseHuxingPrice",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseHuxingPrice.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            houseHuxingPrice.prototype.init({}, tenementType);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
            });
            $('#' + houseHuxingPrice.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseHuxingPrice.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseHuxingPrice.prototype.config().frm);
            data.houseId = houseCommon.getHouseId();
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            if (tenementType == '商铺' || tenementType == '商场') {
                var adjacentPosition = '';
                $("#" + houseHuxingPrice.prototype.config().frm).find('[name^=adjacentPosition]').each(function () {
                    adjacentPosition += $(this).val() + ',';
                })
                data.adjacentPosition = adjacentPosition;
                var distance = '';
                $("#" + houseHuxingPrice.prototype.config().frm).find('[name^=distance]').each(function () {
                    distance += $(this).val() + ',';
                })
            }
            $.ajax({
                url: getContextPath() + "/basicHouseHuxingPrice/saveAndUpdateBasicHouseHuxingPrice",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseHuxingPrice.prototype.config().box).modal('hide');
                        houseHuxingPrice.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseHuxingPrice/getBasicHouseHuxingPriceById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
                        $("#" + houseHuxingPrice.prototype.config().frm).clearAll();
                        if (houseHuxingPrice.prototype.isNotBlank(result.data)) {
                            houseHuxingPrice.prototype.init(result.data, tenementType);
                            if (tenementType == '商铺' || tenementType == '商场') {
                                if (houseHuxingPrice.prototype.isNotBlank(result.data.adjacentPosition)) {
                                    houseHuxingPrice.prototype.writeHTMLData(result.data.adjacentPosition, result.data.distance);
                                } else {
                                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                                        $("#" + houseRoom.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
                                    }, false);
                                }
                            }
                        } else {
                            houseHuxingPrice.prototype.init({}, tenementType);
                        }

                        $('#' + houseHuxingPrice.prototype.config().box).modal("show");//
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item, tenementType) {
            $("#" + houseHuxingPrice.prototype.config().frm).clearAll();
            if (houseHuxingPrice.prototype.isNotBlank(tenementType)) {
                $("#" + houseHuxingPrice.prototype.config().frm).find(".form-group").attr("style", "display:none");
                $("#" + houseHuxingPrice.prototype.config().frm).find(".form-group").find("input").attr("disabled", true);

                $("#" + houseHuxingPrice.prototype.config().frm).find(".common").show();
                $("#" + houseHuxingPrice.prototype.config().frm).find(".common").find("input").attr("disabled", false);

                if (tenementType == '住宅' || tenementType == '办公') {
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".residence").show();
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".residence").find("input").attr("disabled", false);
                }
                if (tenementType == '商铺' || tenementType == '商场') {
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".store").show();
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".store").find("input").attr("disabled", false);

                }
                if (tenementType == '餐饮酒店') {
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".hotel").show();
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".hotel").find("input").attr("disabled", false);
                }
                if (tenementType == '生产') {
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".production").show();
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".production").find("input").attr("disabled", false);
                }
                if (tenementType == '仓储') {
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".storage").show();
                }
            } else {
                $("#" + houseHuxingPrice.prototype.config().frm).find(".common").show();
            }
            $("#" + houseHuxingPrice.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_orientation, item.orientation, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("select.orientation2").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_standard_measure, item.standardMeasure, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("select.standardMeasure2").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_storage_request, item.storageRequest, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("select.storageRequest2").empty().html(html).trigger('change');
            });
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#residenceAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#residenceLightingList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#residenceSunshineList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#residenceSoundInsulationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#productionAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#hotelAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#hotelLightingList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("#productionLightingList").empty().html(html).trigger('change');
            }, true);

        },
        importData: function (planDetailsId) {
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/basicHouseHuxingPrice/importData",
                data: {
                    houseId: houseCommon.getHouseId(),
                    planDetailsId: planDetailsId
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUpload',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    if (result.ret) {
                        houseHuxingPrice.prototype.loadDataDicList();
                        notifySuccess("成功", result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        appendHTML: function () {
            var html = "<div class='row form-group store base'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';

            html += "<label class='col-sm-2 control-label'>相邻位置<span class='symbol required'></span></label>";
            html += "<div class='col-sm-4'>";
            html += '<select class="form-control input-full adjacentPosition" name="adjacentPosition' + houseHuxingPrice.num + '" required>';
            html += "</select>";
            html += "</div>";

            html += "<label class='col-sm-2 control-label'>距离(m)<span class='symbol required'></span></label>";
            html += "<div class='col-sm-3'>";
            html += '<input type="text" placeholder="距离" name="distance' + houseHuxingPrice.num + '" class="form-control input-full" required>';
            html += "</div>";

            html += "<div class='col-sm-1'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseHuxingPrice.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + houseHuxingPrice.prototype.config().frm).find(".streetNumbers").append(html);
            var name = "select[name='adjacentPosition" + houseHuxingPrice.num + "']";
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find(name).empty().html(html).trigger('change');
            });
            houseHuxingPrice.num++;
        },
        cleanHTMLData: function (item) {
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (adjacentPositions, distances) {
            $("#" + houseHuxingPrice.prototype.config().frm).find(".streetNumbers").empty();

            var strs = adjacentPositions.split(",");
            var strs2 = distances.split(",");
            var length = strs.length;
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, strs[0], function (html, data) {
                $("#" + houseHuxingPrice.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
            }, false);
            $("#" + houseHuxingPrice.prototype.config().frm).find("input[name='distance']").val(strs2[0]);
            for (var j = 1; j < length; j++) {
                if (houseHuxingPrice.prototype.isNotBlank(strs[j])) {
                    var html = "<div class='row form-group'>";
                    html += '<div class="col-sm-12">';
                    html += '<div class="form-inline x-valid">';

                    html += "<label class='col-sm-2 control-label'>相邻位置<span class='symbol required'></span></label>";
                    html += "<div class='col-sm-4'>";
                    html += '<select class="form-control input-full adjacentPosition' + j + '" name="adjacentPosition' + j + '" required >';
                    html += "</select>";
                    html += "</div>";

                    html += "<label class='col-sm-2 control-label'>距离(m)<span class='symbol required'></span></label>";
                    html += "<div class='col-sm-3'>";
                    html += '<input type="text" placeholder="距离" name="distance' + j + '" class="form-control input-full" required value="' + strs2[j] + '">';
                    html += "</div>";

                    html += "<div class='col-sm-1'>";
                    html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseHuxingPrice.prototype.cleanHTMLData(this)'>" + "</span>";
                    html += "</div>";

                    html += "</div>";
                    html += "</div>";
                    html += "</div>";
                    $("#" + houseHuxingPrice.prototype.config().frm).find(".streetNumbers").append(html);
                    var name = "select[name='adjacentPosition" + j + "']";
                    if (j > 0) {
                        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, strs[j], function (html, data) {
                            $("#" + houseHuxingPrice.prototype.config().frm).find(name).empty().html(html).trigger('change');
                        }, false);
                    }
                }

            }

            $("#" + houseHuxingPrice.prototype.config().frm).find(".streetNumbers").show();
        }
    }

    //绑定事件
    $('#' + houseHuxingPrice.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseHuxingPrice.prototype.loadDataDicList();
    })
})();

var houseHeating;
(function () {
    houseHeating = function () {

    };
    houseHeating.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseHeatingList";
            data.box = "divBoxHouseHeating";
            data.frm = "frmHouseHeating";
            data.type = "houseHeating";//根据 ExamineHouseEquipmentTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseHeatingColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseHeating.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseHeating.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseHeating.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHeating.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseHeating.prototype.config().type,
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/deleteBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseHeating.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseHeating.prototype.init({});
            $('#' + houseHeating.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseHeating.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseHeating.prototype.config().frm, true);
            data.type = houseHeating.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseHeating.prototype.config().box).modal('hide');
                        houseHeating.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/getBasicHouseEquipmentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseHeating.prototype.isNotBlank(result.data)) {
                            houseHeating.prototype.init(result.data);
                        } else {
                            houseHeating.prototype.init({});
                        }
                        $('#' + houseHeating.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseHeating.prototype.config().frm).clearAll();
            $("#" + houseHeating.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_heating_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_heating_method, item.category, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonSupplyMode, item.supplyMode, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.supplyMode").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, item.grade, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseHeating.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseHeating.prototype.loadDataDicList();
    })
})();

var houseAirConditioner;
(function () {
    houseAirConditioner = function () {

    };
    houseAirConditioner.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseAirConditionerList";
            data.box = "divBoxHouseAirConditioner";
            data.frm = "frmHouseAirConditioner";
            data.type = "houseAirConditioner";//根据 ExamineHouseEquipmentTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseAirConditionerColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseAirConditioner.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseAirConditioner.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseAirConditioner.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseAirConditioner.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseAirConditioner.prototype.config().type,
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/deleteBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseAirConditioner.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseAirConditioner.prototype.init({});
            $('#' + houseAirConditioner.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseAirConditioner.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseAirConditioner.prototype.config().frm, true);
            data.type = houseAirConditioner.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseAirConditioner.prototype.config().box).modal('hide');
                        houseAirConditioner.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/getBasicHouseEquipmentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseAirConditioner.prototype.isNotBlank(result.data)) {
                            houseAirConditioner.prototype.init(result.data);
                        } else {
                            houseAirConditioner.prototype.init({});
                        }
                        $('#' + houseAirConditioner.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseAirConditioner.prototype.config().frm).clearAll();
            $("#" + houseAirConditioner.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_air_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_air_conditioning_mode, item.category, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonSupplyMode, item.supplyMode, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.supplyMode").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, item.grade, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseAirConditioner.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseAirConditioner.prototype.loadDataDicList();
    })
})();

var houseNewWind;
(function () {
    houseNewWind = function () {

    };
    houseNewWind.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseNewWindList";
            data.box = "divBoxHouseNewWind";
            data.frm = "frmHouseNewWind";
            data.type = "houseNewWind";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseNewWindColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseNewWind.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseNewWind.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseNewWind.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseNewWind.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseNewWind.prototype.config().type,
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/deleteBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseNewWind.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseNewWind.prototype.init({});
            $('#' + houseNewWind.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseNewWind.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseNewWind.prototype.config().frm, true);
            data.type = houseNewWind.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseNewWind.prototype.config().box).modal('hide');
                        houseNewWind.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/getBasicHouseEquipmentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseNewWind.prototype.isNotBlank(result.data)) {
                            houseNewWind.prototype.init(result.data);
                        } else {
                            houseNewWind.prototype.init({});
                        }
                        $('#' + houseNewWind.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseNewWind.prototype.config().frm).clearAll().initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_wind_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_way_wind, item.category, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonSupplyMode, item.supplyMode, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.supplyMode").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, item.grade, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }


    //绑定事件
    $('#' + houseNewWind.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseNewWind.prototype.loadDataDicList();
    })
})();

var houseCorollaryEquipment;
(function () {
    houseCorollaryEquipment = function () {

    };
    houseCorollaryEquipment.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseCorollaryEquipmentList";
            data.box = "divBoxHouseCorollaryEquipment";
            data.frm = "frmHouseCorollaryEquipment";
            data.FileID = "positionDiagramFileID";// ExamineFileUpLoadTwoFieldEnum
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseCorollaryEquipmentColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseCorollaryEquipment.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseCorollaryEquipment.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseCorollaryEquipment.prototype.config().table, getContextPath() + "/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseCorollaryEquipment/deleteBasicHouseCorollaryEquipment",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseCorollaryEquipment.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseCorollaryEquipment.prototype.init({});
            $('#' + houseCorollaryEquipment.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseCorollaryEquipment.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseCorollaryEquipment.prototype.config().frm, true);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseCorollaryEquipment/saveAndUpdateBasicHouseCorollaryEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseCorollaryEquipment.prototype.config().box).modal('hide');
                        houseCorollaryEquipment.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseCorollaryEquipment/getBasicHouseCorollaryEquipmentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseCorollaryEquipment.prototype.isNotBlank(result.data)) {
                            houseCorollaryEquipment.prototype.init(result.data);
                        } else {
                            houseCorollaryEquipment.prototype.init({});
                        }
                        $('#' + houseCorollaryEquipment.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseCorollaryEquipment.prototype.config().frm).clearAll().initForm(item);
            $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.type").off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.category, function (html, data) {
                    $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
                });
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_corollary_equipment_type, item.type, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.type").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaintenanceSituation, item.maintenanceStatus, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.maintenanceStatus").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_corollary_equipment_use, item.equipmentUse, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.equipmentUse").empty().html(html).trigger('change');
            });

            FileUtils.uploadFiles({
                target: houseCorollaryEquipment.prototype.config().FileID,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: houseCorollaryEquipment.prototype.config().FileID,
                    tableName: AssessDBKey.BasicHouseCorollaryEquipment,
                    tableId: houseCorollaryEquipment.prototype.isNotBlank(item.id) ? item.id : "0"
                },
                deleteFlag: true
            });

            FileUtils.getFileShows({
                target: houseCorollaryEquipment.prototype.config().FileID,
                formData: {
                    fieldsName: houseCorollaryEquipment.prototype.config().FileID,
                    tableName: AssessDBKey.BasicHouseCorollaryEquipment,
                    tableId: houseCorollaryEquipment.prototype.isNotBlank(item.id) ? item.id : "0"
                },
                deleteFlag: true
            })
        }
    }

    //绑定事件
    $('#' + houseCorollaryEquipment.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseCorollaryEquipment.prototype.loadDataDicList();
    })
})();

var houseFaceStreet;
(function () {
    houseFaceStreet = function () {

    };
    houseFaceStreet.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseFaceStreetList";
            data.box = "divBoxHouseFaceStreet";
            data.frm = "frmHouseFaceStreet";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseFaceStreetColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseFaceStreet.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseFaceStreet.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseFaceStreet.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseFaceStreet.prototype.config().table, getContextPath() + "/basicHouseFaceStreet/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseFaceStreet/deleteBasicHouseFaceStreet",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseFaceStreet.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseFaceStreet.prototype.init({});
            $("#" + houseFaceStreet.prototype.config().frm + " .name").empty();
            var lableValue = "街道（道路）名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-9'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'streetName' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-1'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
            $('#' + houseFaceStreet.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseFaceStreet.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseFaceStreet.prototype.config().frm, true);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseFaceStreet/saveAndUpdateBasicHouseFaceStreet",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseFaceStreet.prototype.config().box).modal('hide');
                        houseFaceStreet.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseFaceStreet/getBasicHouseFaceStreetById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseFaceStreet.prototype.isNotBlank(result.data)) {
                            houseFaceStreet.prototype.init(result.data);
                        } else {
                            houseFaceStreet.prototype.init({});
                        }
                        $('#' + houseFaceStreet.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseFaceStreet.prototype.config().frm).clearAll();
            $("#" + houseFaceStreet.prototype.config().frm).initForm(item);
            if (houseFaceStreet.prototype.isNotBlank(item.streetName)) {
                houseFaceStreet.prototype.writeHTMLData(item.streetName);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_street_level, item.streetLevel, function (html, data) {
                $("#" + houseFaceStreet.prototype.config().frm).find("select.streetLevel").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_traffic_flow, item.trafficFlow, function (html, data) {
                $("#" + houseFaceStreet.prototype.config().frm).find("select.trafficFlow").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_visitors_flowrate, item.visitorsFlowrate, function (html, data) {
                $("#" + houseFaceStreet.prototype.config().frm).find("select.visitorsFlowrate").empty().html(html).trigger('change');
            });
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_position, item.position, function (html, data) {
                $("#" + houseFaceStreet.prototype.config().frm).find('select.position').empty().html(html).trigger('change');
            }, true);
        },
        appendHTML: function (item, this_) {
            var lableValue = "街道（道路）名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-9'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-1'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
        },
        cleanHTMLData: function (item) {
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + houseFaceStreet.prototype.config().frm + " .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "街道（道路）名称";
            var item = "streetName";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-9'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-1'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
            }
        }
    }

    //绑定事件
    $('#' + houseFaceStreet.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseFaceStreet.prototype.loadDataDicList();
    })
})();

var houseIntelligent;
(function () {
    houseIntelligent = function () {

    };
    var arr = [];
    var num = 0;
    houseIntelligent.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseIntelligentList";
            data.box = "divBoxHouseIntelligent";
            data.frm = "frmHouseIntelligent";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseIntelligentColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseIntelligent.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseIntelligent.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseIntelligent.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseIntelligent.prototype.config().table, getContextPath() + "/basicHouseIntelligent/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseIntelligent/deleteBasicHouseIntelligent",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseIntelligent.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseIntelligent.prototype.init({});
            $('#' + houseIntelligent.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseIntelligent.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseIntelligent.prototype.config().frm, true);
            data.houseId = houseCommon.getHouseId();
            var tempArr = [];
            $.each(arr, function (i, n) {
                var intelligentSystem = "data." + n.intelligentSystem.key;
                var layingMethod = "data." + n.layingMethod.key;
                var intelligenceGrade = "data." + n.intelligenceGrade.key;
                tempArr.push({
                    intelligentSystem: {key: n.intelligentSystem.key, value: eval(intelligentSystem)},
                    layingMethod: {key: n.layingMethod.key, value: eval(layingMethod)},
                    intelligenceGrade: {key: n.intelligenceGrade.key, value: eval(intelligenceGrade)}
                });
            });
            var new_arr = [];
            for (var i = 0; i < tempArr.length; i++) {
                var items = tempArr[i];
                //判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
                if ($.inArray(items, new_arr) == -1) {
                    new_arr.push(items);
                }
            }
            data.intelligentSystem = JSON.stringify(new_arr);
            $.ajax({
                url: getContextPath() + "/basicHouseIntelligent/saveAndUpdateBasicHouseIntelligent",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseIntelligent.prototype.config().box).modal('hide');
                        houseIntelligent.prototype.loadDataDicList();
                        num = 0;
                        arr.length = 0;
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseIntelligent/getBasicHouseIntelligentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseIntelligent.prototype.isNotBlank(result.data)) {
                            houseIntelligent.prototype.init(result.data);
                        } else {
                            houseIntelligent.prototype.init({});
                        }
                        $('#' + houseIntelligent.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseIntelligent.prototype.config().frm).find(".system").empty();
            $("#" + houseIntelligent.prototype.config().frm).clearAll();
            $("#" + houseIntelligent.prototype.config().frm).initForm(item);
            $.ajax({
                url: getContextPath() + "/baseDataDic/getDataDicListByFieldName",
                type: "get",
                dataType: "json",
                async: true,
                data: {
                    fieldName: AssessDicKey.examine_house_lamps_lanterns
                },
                success: function (result) {
                    if (result.ret&&result.data) {
                        var retHtml = '';
                        $.each(result.data, function (i, item) {
                            retHtml += '<option key="' + item.fieldName + '" title="' + item.remark + '" value="' + item.id + '"';
                            retHtml += '>' + item.name + '</option>';
                        });
                        $("#" + houseIntelligent.prototype.config().frm).find("select.lampsLanterns").empty().html(retHtml).trigger('change');
                        if (houseIntelligent.prototype.isNotBlank(item.lampsLanterns)) {
                            $("#" + houseIntelligent.prototype.config().frm).find("select.lampsLanterns").val(item.lampsLanterns.split(",")).trigger("change");
                        }
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_switch_circuit, item.switchCircuit, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.switchCircuit").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLayingMethod, item.layingMethod, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.layingMethod").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, item.grade, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
            if (houseIntelligent.prototype.isNotBlank(item.id)) {
                houseIntelligent.prototype.writeHTMLData(item.intelligentSystem);
            } else {
                houseIntelligent.prototype.appendHTML("", "");
                $.each(arr, function (i, n) {
                    AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_intelligent_system, n.intelligentSystem.value, function (html, data) {
                        $("#" + houseIntelligent.prototype.config().frm).find("select." + n.intelligentSystem.key).empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLayingMethod, n.layingMethod.value, function (html, data) {
                        $("#" + houseIntelligent.prototype.config().frm).find("select." + n.layingMethod.key).empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, n.intelligenceGrade.value, function (html, data) {
                        $("#" + houseIntelligent.prototype.config().frm).find("select." + n.intelligenceGrade.key).empty().html(html).trigger('change');
                    });
                });
            }
        },
        appendHTML: function (item, this_) {
            num++;
            var intelligentSystem = "intelligentSystem" + num;
            var layingMethod = "layingMethod" + num;
            var intelligenceGrade = "intelligenceGrade" + num;
            var html = houseIntelligent.prototype.createHTML(intelligentSystem, layingMethod, intelligenceGrade);
            $("#" + houseIntelligent.prototype.config().frm).find(".system").append(html);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_intelligent_system, null, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("." + intelligentSystem).empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLayingMethod, null, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("." + layingMethod).empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, null, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("." + intelligenceGrade).empty().html(html).trigger('change');
            });
            arr.push({
                intelligentSystem: {key: intelligentSystem, value: ""},
                layingMethod: {key: layingMethod, value: ""},
                intelligenceGrade: {key: intelligenceGrade, value: ""}
            });
        },
        createHTML: function (intelligentSystem, layingMethod, intelligenceGrade) {
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-md-12">';
            html += ' <div class="form-inline x-valid">';

            html += "<label class='col-sm-2  control-label'>" + '智能系统' + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<select required='required' name='" + intelligentSystem + "' class='form-control input-full search-select select2 " + intelligentSystem + "'>" + "</select>";
            html += "</div>";

            html += "<div class='col-sm-3'>";
            html += "<select required='required' name='" + layingMethod + "' class='form-control input-full search-select select2 " + layingMethod + "'>" + "</select>";
            html += "</div>";

            html += "<div class='col-sm-3'>";
            html += "<select required='required' name='" + intelligenceGrade + "' class='form-control input-full search-select select2 " + intelligenceGrade + "'>" + "</select>";
            html += "</div>";

            html += "<div class='col-sm-1'>";
            html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseIntelligent.prototype.cleanHTMLData(this)'>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            return html;
        },
        cleanHTMLData: function (this_) {
            $(this_).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {//修复保存了不完整数据异常情况
            var form = $("#" + houseIntelligent.prototype.config().frm);
            if (houseIntelligent.prototype.isNotBlank(str)) {
                var data = JSON.parse(str);
                arr = [];
                form.find(".system").empty();
                $.each(data, function (i, n) {
                    var intelligentSystem = "";
                    var layingMethod = "";
                    var intelligenceGrade = "";
                    var number = "";
                    if (n.intelligentSystem) {
                        intelligentSystem = n.intelligentSystem.key + "";
                        number = houseIntelligent.prototype.getNumber(n.intelligentSystem.key);
                    }
                    if (n.layingMethod) {
                        layingMethod = n.layingMethod.key + "";
                        number = houseIntelligent.prototype.getNumber(n.layingMethod.key);
                    }
                    if (n.intelligenceGrade) {
                        intelligenceGrade = n.intelligenceGrade.key + "";
                        number = houseIntelligent.prototype.getNumber(n.intelligenceGrade.key);
                    }
                    if (!houseIntelligent.prototype.isNotBlank(intelligentSystem)) {
                        intelligentSystem = "intelligentSystem" + number;
                    }
                    if (!houseIntelligent.prototype.isNotBlank(layingMethod)) {
                        layingMethod = "layingMethod" + number;
                    }
                    if (!houseIntelligent.prototype.isNotBlank(intelligenceGrade)) {
                        intelligenceGrade = "intelligenceGrade" + number;
                    }
                    var html = houseIntelligent.prototype.createHTML(intelligentSystem, layingMethod, intelligenceGrade);
                    form.find(".system").append(html);

                    if (n.intelligentSystem) {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_intelligent_system, n.intelligentSystem.value, function (html, data) {
                            form.find("." + intelligentSystem).empty().html(html).trigger('change');
                        });
                    } else {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_intelligent_system, null, function (html, data) {
                            form.find("." + intelligentSystem).empty().html(html).trigger('change');
                        });
                    }

                    if (n.layingMethod) {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLayingMethod, n.layingMethod.value, function (html, data) {
                            form.find("." + layingMethod).empty().html(html).trigger('change');
                        });
                    } else {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLayingMethod, null, function (html, data) {
                            form.find("." + layingMethod).empty().html(html).trigger('change');
                        });
                    }

                    if (n.intelligenceGrade) {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, n.intelligenceGrade.value, function (html, data) {
                            form.find("." + intelligenceGrade).empty().html(html).trigger('change');
                        });
                    } else {
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, null, function (html, data) {
                            form.find("." + intelligenceGrade).empty().html(html).trigger('change');
                        });
                    }

                    arr.push(n);
                });
            }
        },
        getNumber: function (str) {
            if (str) {
                var reg = /[1-9][0-9]*/g;
                return str.match(reg)[0];
            }
        }
    }


    //绑定事件
    $('#' + houseIntelligent.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseIntelligent.prototype.loadDataDicList();
    })
})();

var houseWater;
(function () {
    houseWater = function () {

    };
    houseWater.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseWaterList";
            data.box = "divBoxHouseWater";
            data.frm = "frmHouseWater";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseWaterColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseWater.prototype.config().table, getContextPath() + "/basicHouseWater/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseWater/deleteBasicHouseWater",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseWater.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseWater.prototype.init({});
            $('#' + houseWater.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseWater.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseWater.prototype.config().frm, true);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseWater/saveAndUpdateBasicHouseWater",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseWater.prototype.config().box).modal('hide');
                        houseWater.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseWater/getBasicHouseWaterById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseWater.prototype.isNotBlank(result.data)) {
                            houseWater.prototype.init(result.data);
                        } else {
                            houseWater.prototype.init({});
                        }
                        $('#' + houseWater.prototype.config().box).modal("show");//
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseWater.prototype.config().frm).clearAll();
            $("#" + houseWater.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_pipe_material, item.pipeMaterial, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.pipeMaterial").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_piping_layout, item.pipingLayout, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.pipingLayout").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_supply_mode, item.supplyMode, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.supplyMode").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_booster_equipment, item.boosterEquipment, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.boosterEquipment").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_pretreated_water, item.pretreatedWater, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.pretreatedWater").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_purification_equipment_price, item.purificationEquipmentPrice, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.purificationEquipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_fire_water_supply, item.fireWaterSupply, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.fireWaterSupply").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonGrade, item.grade, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
            houseWater.prototype.showOtherContent(item);
        },
        showOtherContent: function (data) {
            if (houseWater.prototype.isNotBlank(data.supplyMode)) {
                var strArr = ["生活供水"];//来自于实体描述1(1).docx中的规则
                var supplyModeId = data.supplyMode;
                if (supplyModeId) {
                    AssessCommon.getDataDicInfo(supplyModeId, function (data) {
                        var str = strArr.join(",");
                        //当属于数组中的任意一项时显示
                        if (str.indexOf(data.name) > -1) {
                            $("#" + houseWater.prototype.config().frm).find(".otherContent").show();
                        } else {
                            $("#" + houseWater.prototype.config().frm).find(".otherContent").hide();
                        }
                    });
                }
            } else {
                $("#" + houseWater.prototype.config().frm).find(".otherContent").hide();
            }

            //绑定变更事件
            $("#" + houseWater.prototype.config().frm).find("select.supplyMode").off('change').on('change', function () {
                var strArr = ["生活供水"];//来自于实体描述1(1).docx中的规则
                var supplyModeId = $("#" + houseWater.prototype.config().frm).find("select.supplyMode").val();
                if (supplyModeId) {
                    AssessCommon.getDataDicInfo(supplyModeId, function (data) {
                        var str = strArr.join(",");
                        //当属于数组中的任意一项时显示
                        if (str.indexOf(data.name) > -1) {
                            $("#" + houseWater.prototype.config().frm).find(".otherContent").show();
                        } else {
                            $("#" + houseWater.prototype.config().frm).find(".otherContent").hide();
                        }
                    });
                }
            });
        }
    }

    //绑定事件
    $('#' + houseWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseWater.prototype.loadDataDicList();
    })
})();

var houseRoomDecorate;
(function () {
    houseRoomDecorate = function () {

    };
    houseRoomDecorate.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseRoomDecorateList";
            data.box = "divBoxHouseRoomDecorate";
            data.frm = "frmHouseRoomDecorate";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseRoomDecorateColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoomDecorate.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoomDecorate.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseRoomDecorate.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseRoomDecorate.prototype.config().table, getContextPath() + "/basicHouseRoomDecorate/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoomDecorate/deleteBasicHouseRoomDecorate",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseRoomDecorate.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseRoomDecorate.prototype.init({});
            $('#' + houseRoomDecorate.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseRoomDecorate.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseRoomDecorate.prototype.config().frm, true);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseRoomDecorate/saveAndUpdateBasicHouseRoomDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseRoomDecorate.prototype.config().box).modal('hide');
                        houseRoomDecorate.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoomDecorate/getBasicHouseRoomDecorateById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (houseRoomDecorate.prototype.isNotBlank(result.data)) {
                            houseRoomDecorate.prototype.init(result.data);
                        } else {
                            houseRoomDecorate.prototype.init({});
                        }
                        $('#' + houseRoomDecorate.prototype.config().box).modal("show");//
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseRoomDecorate.prototype.config().frm).clearAll();
            $("#" + houseRoomDecorate.prototype.config().frm).initForm(item);
            $("#" + houseRoomDecorate.prototype.config().frm).find('select.material').off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.constructionTechnology, function (html, data) {
                    $("#" + houseRoomDecorate.prototype.config().frm).find('select.constructionTechnology').empty().html(html).trigger('change');
                });
                item.constructionTechnology = null;
            });
            $("#" + houseRoomDecorate.prototype.config().frm).find('select.constructionTechnology').off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.materialPrice, function (html, data) {
                    $("#" + houseRoomDecorate.prototype.config().frm).find('select.materialPrice').empty().html(html).trigger('change');
                });
                item.materialPrice = null;
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_material, item.material, function (html, data) {
                $("#" + houseRoomDecorate.prototype.config().frm).find('select.material').empty().html(html).trigger('change');
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_part, item.part, function (html, data) {
                $("#" + houseRoomDecorate.prototype.config().frm).find('select.part').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_level, item.level, function (html, data) {
                $("#" + houseRoomDecorate.prototype.config().frm).find('select.level').empty().html(html).trigger('change');
            });
        },
        openLocationModal: function (_this) {
            var houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/basicHouseRoomList",
                type: "get",
                dataType: "json",
                data: {houseId: houseId},
                success: function (result) {
                    if (result.ret) {
                        if (houseRoomDecorate.prototype.isNotBlank(result.data)) {
                            houseRoomDecorate.prototype.getLocationHtml(result.data)
                        } else {

                        }
                        $('#' + houseRoomDecorate.prototype.config().box).modal("show");//
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })

        },
        getLocationHtml(resultData) {
            var target = $("#frmLocation").find(".card-body");
            target.empty();
            var resultHtml = '<div>';
            var divLength = Math.ceil(resultData.length / 6);
            for (var j = 0; j < divLength; j++) {
                resultHtml += '<div class="row form-group">';
                resultHtml += '<div class="col-md-12">';
                resultHtml += "<div class='form-check' style='justify-content:left'>";
                var length = (j + 1) * 6 > resultData.length ? resultData.length : (j + 1) * 6;
                for (var i = j * 6; i < length; i++) {
                    resultHtml += "<label class='form-check-label'>";
                    resultHtml += "<input class='form-check-input' type='checkbox' name='locationCheckBox' ";
                    resultHtml += 'value="' + resultData[i].name + '">';
                    resultHtml += "<span class='form-check-sign'>" + resultData[i].name + "</span></label>";
                }
                resultHtml += "</div>";
                resultHtml += "</div>";
                resultHtml += "</div>";
            }

            target.append(resultHtml);


            var value = $("#" + houseRoomDecorate.prototype.config().frm).find("input[name='location']").val();
            if (houseRoomDecorate.prototype.isNotBlank(value)) {
                var valueArray = value.split(",");
                var checkboxs = $("#frmLocation").find("input[name='locationCheckBox']");
                AssessCommon.checkboxToChecked(checkboxs, valueArray);
            }
            $("#divBoxLocation").modal("show");
        },
        saveDecoratePart: function () {
            var checkedBoxs = $("#frmDecoratePart").find('input:checkbox:checked');
            if (checkedBoxs.length <= 0) {
                notifyInfo('提示', '至少选择一个');
                return false;
            }
            var data = [];
            checkedBoxs.each(function () {
                data.push($(this).val());
            })
            $("#" + houseRoomDecorate.prototype.config().frm).find('input[name="decoratePart"]').val(data.join());
            $("#divBoxDecoratePart").modal("hide");
        },
        spliceLocation: function () {
            var value = [];
            $("#frmLocation").find("input[name='locationCheckBox']:checked").each(function (i) {
                value.push($(this).val());
            });
            $("#" + houseRoomDecorate.prototype.config().frm).find("input[name='location']").val(value);
            $("#divBoxLocation").modal("hide");
        }
    }

    //绑定事件
    $('#' + houseRoomDecorate.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseRoomDecorate.prototype.loadDataDicList();
    })
})();

var houseWaterDrain = {};
houseWaterDrain.config = {
    table: "HouseWaterDrainList",
    box: "divBoxHouseWaterDrain",
    frm: "frmHouseWaterDrain",
    type: "null"
};
houseWaterDrain.isNotBlank = function (item) {
    if (item) {
        return true;
    }
    return false;
};
houseWaterDrain.loadDataDicList = function () {
    var cols = commonColumn.houseWaterDrainColumn();
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseWaterDrain.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
            str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseWaterDrain.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
            str += '</div>';
            return str;
        }
    });
    $("#" + this.config.table).bootstrapTable('destroy');
    TableInit(this.config.table, getContextPath() + "/basicHouseWaterDrain/getBootstrapTableVo", cols, {
        houseId: houseCommon.getHouseId()
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};
houseWaterDrain.removeData = function (id) {
    AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
        $.ajax({
            url: getContextPath() + "/basicHouseWaterDrain/delete",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    houseWaterDrain.loadDataDicList();
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    });
};
houseWaterDrain.getAndInit = function (id) {
    $.ajax({
        url: getContextPath() + "/basicHouseWaterDrain/get",
        type: "get",
        dataType: "json",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                if (houseWaterDrain.isNotBlank(result.data)) {
                    houseWaterDrain.init(result.data);
                } else {
                    houseWaterDrain.init({});
                }
                $('#' + houseWaterDrain.config.box).modal("show");
            }
        },
        error: function (result) {
            AlertError("调用服务端方法失败，失败原因:" + result);
        }
    })
};
houseWaterDrain.showModel = function () {
    this.init({});
    $('#' + this.config.box).modal("show");
};
houseWaterDrain.init = function (item) {
    var frm = this.config.frm;
    $("#" + frm).clearAll();
    $("#" + frm).initForm(item);
    AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_drain_type, item.type, function (html, data) {
        $("#" + frm).find("select.type").empty().html(html).trigger('change');
    });
    // AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_organization, item.organization, function (html, data) {
    //     $("#" + frm).find("select.organization").empty().html(html).trigger('change');
    // });
    AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_drain_system, item.drainSystem, function (html, data) {
        $("#" + frm).find("select.drainSystem").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_drain_processing_mode, item.processingMode, function (html, data) {
        $("#" + frm).find("select.processingMode").empty().html(html).trigger('change');
    });
};
houseWaterDrain.saveData = function () {
    var frm = this.config.frm;
    if (!$("#" + frm).valid()) {
        return false;
    }
    var data = formParams(frm);
    data.houseId = houseCommon.getHouseId();
    $.ajax({
        url: getContextPath() + "/basicHouseWaterDrain/saveAndUpdate",
        type: "post",
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.ret) {
                notifySuccess("成功", "保存成功");
                $('#' + houseWaterDrain.config.box).modal('hide');
                houseWaterDrain.loadDataDicList();
            } else {
                AlertError("保存数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("调用服务端方法失败，失败原因:" + result);
        }
    });
};
(function () {
    //绑定事件
    $('#' + houseWaterDrain.config.table).closest('.full-height').find('.card-header').bind('click', function () {
        houseWaterDrain.loadDataDicList();
    })
})();


var houseRoom;
(function () {
    houseRoom = function () {

    };
    houseRoom.num = 0;
    houseRoom.beCopyObjectId = undefined;
    houseRoom.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "HouseRoomList";
            data.box = "divBoxHouseRoom";
            data.frm = "frmHouseRoom";
            data.tableSubclass = "SubclassHouseRoomList";
            data.boxSubclass = "SubclassDivBoxHouseRoom";
            data.boxSubclassSaveView = "boxSubclassSaveViewHouseRoom";
            data.frmSubclass = "SubclassFrmHouseRoom";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.houseRoomColumn();
            var temp = [];
            if (houseCommon.houseHuxingForm.find('select[name="spatialDistribution"]').find("option:selected").text() == "多层") {
                cols.push({field: 'currentFloor', title: '所在楼层'});
            }
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            if (tenementType == '住宅' || tenementType == '办公') {
                temp = commonColumn.houseRoomResidence();
            } else if (tenementType == '商铺' || tenementType == '商场') {
                temp = commonColumn.houseRoomStore();
            } else if (tenementType == '餐饮酒店') {
                temp = commonColumn.houseRoomHotel();
            } else if (tenementType == '生产') {
                temp = commonColumn.houseRoomProduction();
            } else if (tenementType == '仓储') {
                temp = commonColumn.houseRoomStorage();
            }
            $.each(temp, function (i, item) {
                cols.push(item);
            })
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoom.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="复制" onclick="houseRoom.prototype.dataCopy(' + row.id + ')"><i class="fa fa-copy fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="粘贴" onclick="houseRoom.prototype.dataPaste(' + row.id + ')"><i class="fa fa-paste fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseRoom.prototype.config().table, getContextPath() + "/basicHouseRoom/getBootstrapTableVo", cols, {
                houseId: houseCommon.getHouseId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/deleteBasicHouseRoom",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseRoom.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            $("#" + houseRoom.prototype.config().frm).clearAll();
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            houseRoom.prototype.init({}, tenementType);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
            });
            $('#' + houseRoom.prototype.config().box).modal("show");
        },
        showModelSubclassSaveView: function () {
            var roomId = $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val();
            houseRoom.prototype.subclassInit({roomId: roomId});
            $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal("show");
        },
        showModelSubclass: function (id) {
            houseRoom.prototype.subclassLoadList(id);
            if ($('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").size() > 0) {
                $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val(id);
            }
            $('#' + houseRoom.prototype.config().boxSubclass).modal("show");
        },
        subclassSave: function () {
            if (!$("#" + houseRoom.prototype.config().frmSubclass).valid()) {
                return false;
            }
            var data = formParams(houseRoom.prototype.config().frmSubclass);
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/saveAndUpdateBasicHouseRoomDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal('hide');
                        var item = result.data;
                        if (houseRoom.prototype.isEmpty(item)) {
                            houseRoom.prototype.subclassLoadList(item);
                        }
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        subclassRemoveData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/deleteBasicHouseRoomDecorate",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseRoom.prototype.subclassLoadList(result.data);
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        subclassInit: function (item) {
            $("#" + houseRoom.prototype.config().frmSubclass).clearAll().initForm(item);
            $("#" + houseRoom.prototype.config().frmSubclass).find('select.material').off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.constructionTechnology, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.constructionTechnology').empty().html(html).trigger('change');
                });
                item.constructionTechnology = null;
            });
            $("#" + houseRoom.prototype.config().frmSubclass).find('select.constructionTechnology').off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.materialPrice, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.materialPrice').empty().html(html).trigger('change');
                });
                item.materialPrice = null;
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_material, item.material, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.material').empty().html(html).trigger('change');
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_part, item.part, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.part').empty().html(html).trigger('change');
            });

        },
        subclassLoadList: function (id) {
            var cols = commonColumn.houseRoomDecorateColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoom.prototype.subclassGetAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.subclassRemoveData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
            TableInit(houseRoom.prototype.config().tableSubclass, getContextPath() + "/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
                roomId: id,
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        saveData: function () {
            if (!$("#" + houseRoom.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseRoom.prototype.config().frm);
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            data.houseId = houseCommon.getHouseId();
            if (tenementType == '商铺' || tenementType == '商场') {
                var adjacentPosition = '';
                $("#" + houseRoom.prototype.config().frm).find('[name^=adjacentPosition]').each(function () {
                    adjacentPosition += $(this).val() == "null" ? "" : $(this).val();
                    adjacentPosition += ',';
                })
                data.adjacentPosition = adjacentPosition;
                var distance = '';
                $("#" + houseRoom.prototype.config().frm).find('[name^=distance]').each(function () {
                    distance += $(this).val() == "null" ? "" : $(this).val();
                    distance += ',';
                })
                data.distance = distance;
            }
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/saveAndUpdateBasicHouseRoom",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseRoom.prototype.config().box).modal('hide');
                        houseRoom.prototype.loadDataDicList();
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/getBasicHouseRoomById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
                        $("#" + houseRoom.prototype.config().frm).clearAll();
                        houseRoom.prototype.init(result.data, tenementType);
                        var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
                        if (tenementType == '商铺' || tenementType == '商场') {
                            if (houseRoom.prototype.isNotBlank(result.data.adjacentPosition)) {
                                houseRoom.prototype.writeHTMLData(result.data.adjacentPosition, result.data.distance);
                            } else {
                                AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                                    $("#" + houseRoom.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
                                }, false);
                            }
                        }
                        $('#' + houseRoom.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        subclassGetAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/getBasicHouseRoomDecorateById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        houseRoom.prototype.subclassInit(result.data);
                        $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        dataCopy: function (sourceId) {
            houseRoom.prototype.beCopyObjectId = sourceId;
            notifySuccess("成功", "复制成功");
        },
        dataPaste: function (targetId) {
            if (houseRoom.prototype.isNotBlank(houseRoom.prototype.beCopyObjectId)) {
                if (houseRoom.prototype.beCopyObjectId == targetId) {
                    notifyInfo('提示', "不能粘贴自身");
                    return false;
                }
                $.ajax({
                    url: getContextPath() + "/basicHouseRoom/copyBasicHouseRoom",
                    type: "post",
                    dataType: "json",
                    data: {
                        sourceId: houseRoom.prototype.beCopyObjectId,
                        targetId: targetId,
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess('成功', '粘贴成功');
                            houseRoom.prototype.loadDataDicList();
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })

            } else {
                notifyInfo('提示', "选择需要复制的数据");
                return false;
            }

        },
        init: function (item, tenementType) {
            $("#" + houseRoom.prototype.config().frm).clearAll();
            if (houseRoom.prototype.isNotBlank(tenementType)) {
                $("#" + houseRoom.prototype.config().frm).find(".content").find(".form-group").attr("style", "display:none");
                $("#" + houseRoom.prototype.config().frm).find(".form-group").find("input").attr("disabled", true);

                $("#" + houseRoom.prototype.config().frm).find(".common").show();
                $("#" + houseRoom.prototype.config().frm).find(".common").find("input").attr("disabled", false);
                if (houseRoom.prototype.isNotBlank(tenementType)) {
                    if (tenementType == '住宅' || tenementType == '办公') {
                        $("#" + houseRoom.prototype.config().frm).find(".residence.base").show();
                        $("#" + houseRoom.prototype.config().frm).find(".residence.base").find("input").attr("disabled", false);
                    }
                    if (tenementType == '商铺' || tenementType == '商场') {
                        $("#" + houseRoom.prototype.config().frm).find(".store.base").show();
                        $("#" + houseRoom.prototype.config().frm).find(".store.base").find("input").attr("disabled", false);

                    }
                    if (tenementType == '餐饮酒店') {
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.base").show();
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.base").find("input").attr("disabled", false);
                    }
                    if (tenementType == '生产') {
                        $("#" + houseRoom.prototype.config().frm).find(".production.base").show();
                        $("#" + houseRoom.prototype.config().frm).find(".production.base").find("input").attr("disabled", false);
                    }
                    if (tenementType == '仓储') {
                        $("#" + houseRoom.prototype.config().frm).find(".storage.base").show();
                    }
                }

            } else {
                notifyInfo("提示", "请先选择户型的物业类型")
            }
            $("#" + houseRoom.prototype.config().frm).initForm(item);

            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_orientation, item.orientation, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("select.orientation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_standard_measure, item.standardMeasure, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("select.standardMeasure").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_storage_request, item.storageRequest, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("select.storageRequest").empty().html(html).trigger('change');
            });

            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_names, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#nameList").empty().html(html).trigger('change');
            }, true);

            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#residenceAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#residenceLightingList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#residenceSunshineList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#residenceSoundInsulationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#productionAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#hotelAerationList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#hotelLightingList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_room_status, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#productionLightingList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_floor_high, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#layerHeightList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_house_clear_high, null, function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("#clearHeightList").empty().html(html).trigger('change');
            }, true);
            houseRoom.prototype.getFilePartHtml(AssessDicKey.examineHouseRoomFilePart, item);

            //所在楼层
            var spatialDistributionId = houseCommon.houseHuxingForm.find("select.spatialDistribution").val();
            if (spatialDistributionId) {
                var strArr = ["多层"];
                AssessCommon.getDataDicInfo(spatialDistributionId, function (spatialDistributionData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(spatialDistributionData.name) > -1) {
                        $("#" + houseRoom.prototype.config().frm).find(".currentFloor").show();
                    } else {
                        $("#" + houseRoom.prototype.config().frm).find(".currentFloor").hide();
                    }
                });
            }
            houseRoom.prototype.houseShapeChange();
        },
        houseShapeChange() {
            var tenementType = houseCommon.houseHuxingForm.find('[name="tenementType"]').val();
            var houseShape = $("#" + houseRoom.prototype.config().frm).find('select[name="houseShape"]').val();
            if (houseRoom.prototype.isNotBlank(tenementType) && houseRoom.prototype.isNotBlank(tenementType)) {
                if (tenementType == '住宅' || tenementType == '办公') {
                    if (houseShape == '不规则') {
                        $("#" + houseRoom.prototype.config().frm).find(".residence.rule").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".residence.rule").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").show();
                        $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").find("input").attr("disabled", false);
                    } else {
                        $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".residence.rule").show();
                        $("#" + houseRoom.prototype.config().frm).find(".residence.rule").find("input").attr("disabled", false);
                    }
                }
                if (tenementType == '商铺' || tenementType == '商场') {
                    if (houseShape == '不规则') {
                        $("#" + houseRoom.prototype.config().frm).find(".store.rule").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".store.rule").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".store.unruled").show();
                        $("#" + houseRoom.prototype.config().frm).find(".store.unruled").find("input").attr("disabled", false);
                    } else {
                        $("#" + houseRoom.prototype.config().frm).find(".store.unruled").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".store.unruled").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".store.rule").show();
                        $("#" + houseRoom.prototype.config().frm).find(".store.rule").find("input").attr("disabled", false);
                    }
                }
                if (tenementType == '餐饮酒店') {
                    if (houseShape == '不规则') {
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").show();
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").find("input").attr("disabled", false);
                    } else {
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").show();
                        $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").find("input").attr("disabled", false);
                    }
                }
                if (tenementType == '生产') {
                    if (houseShape == '不规则') {
                        $("#" + houseRoom.prototype.config().frm).find(".production.rule").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".production.rule").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".production.unruled").show();
                        $("#" + houseRoom.prototype.config().frm).find(".production.unruled").find("input").attr("disabled", false);
                    } else {
                        $("#" + houseRoom.prototype.config().frm).find(".production.unruled").hide();
                        $("#" + houseRoom.prototype.config().frm).find(".production.unruled").find("input").attr("disabled", true);
                        $("#" + houseRoom.prototype.config().frm).find(".production.rule").show();
                        $("#" + houseRoom.prototype.config().frm).find(".production.rule").find("input").attr("disabled", false);
                    }
                }
            }
        },
        //附件上传
        fileUpload: function (fieldsName, id) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: AssessDBKey.BasicHouseRoom,
                    tableId: houseRoom.prototype.isNotBlank(id) ? id : "0"
                },
                deleteFlag: true,
                onUploadComplete: function () {
                    houseRoom.prototype.fileShow(fieldsName, id);
                }
            });
        },
        fileShow: function (fieldsName, id) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: AssessDBKey.BasicHouseRoom,
                    tableId: houseRoom.prototype.isNotBlank(id) ? id : "0"
                },
                deleteFlag: true
            })
        },
        //自动生成
        autoGenerate() {
            var huxingData = houseCommon.houseHuxingFormView.find("[name=huxingData]").val();
            var houseId = houseCommon.getHouseId();
            if (houseRoom.prototype.isNotBlank(huxingData)) {
                $.ajax({
                    url: getContextPath() + "/basicHouseRoom/autoGenerate",
                    type: "post",
                    dataType: "json",
                    data: {
                        huxingData: huxingData,
                        houseId: houseId,
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess('成功', '生成成功');
                            houseRoom.prototype.loadDataDicList();
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            } else {
                notifyInfo("提示", "没有户型数据")
            }
        },
        getFilePartHtml: function (fieldName, item) {
            var fileDiv = $('#' + fieldName);
            fileDiv.empty();
            var houseFileHtml = '';
            AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
                houseFileHtml += '<div class="row form-group common">';
                houseFileHtml += '<div class="col-md-12">';
                houseFileHtml += '<div class="form-inline x-valid">';
                for (var i = 0; i < resultData.length; i++) {
                    houseFileHtml += '<label class="col-sm-2">' + resultData[i].name + '</label>';
                    houseFileHtml += '<div class="col-sm-10">';
                    houseFileHtml += '<input id="' + resultData[i].fieldName + '" placeholder="上传附件" class="form-control input-full" type="file">';
                    houseFileHtml += '<div id="_' + resultData[i].fieldName + '"></div>';
                    houseFileHtml += '</div>';
                }
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";

            }, false);
            fileDiv.append(houseFileHtml);
            AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
                $.each(resultData, function (i, fileData) {
                    houseRoom.prototype.fileUpload(fileData.fieldName, item.id);
                    houseRoom.prototype.fileShow(fileData.fieldName, item.id);
                });
            }, false);
        },
        appendHTML: function () {
            var html = "<div class='row form-group store base'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';

            html += "<label class='col-sm-2 control-label'>相邻位置<span class='symbol required'></span></label>";
            html += "<div class='col-sm-4'>";
            html += '<select class="form-control input-full adjacentPosition" name="adjacentPosition' + houseRoom.num + '" required>';
            html += "</select>";
            html += "</div>";

            html += "<label class='col-sm-2 control-label'>距离(m)<span class='symbol required'></span></label>";
            html += "<div class='col-sm-3'>";
            html += '<input type="text" placeholder="距离" name="distance' + houseRoom.num + '" class="form-control input-full" required>';
            html += "</div>";

            html += "<div class='col-sm-1'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseRoom.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + houseRoom.prototype.config().frm).find(".streetNumbers").append(html);
            var name = "select[name='adjacentPosition" + houseRoom.num + "']";
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, '', function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find(name).empty().html(html).trigger('change');
            });
            houseRoom.num++;
        },
        cleanHTMLData: function (item) {
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (adjacentPositions, distances) {
            $("#" + houseRoom.prototype.config().frm).find(".streetNumbers").empty();

            var strs = adjacentPositions.split(",");
            var strs2 = distances.split(",");
            var length = strs.length;
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, strs[0], function (html, data) {
                $("#" + houseRoom.prototype.config().frm).find("select[name='adjacentPosition']").empty().html(html).trigger('change');
            }, false);
            $("#" + houseRoom.prototype.config().frm).find("input[name='distance']").val(strs2[0]);
            for (var j = 1; j < length; j++) {
                if (houseRoom.prototype.isNotBlank(strs[j])) {
                    var html = "<div class='row form-group'>";
                    html += '<div class="col-sm-12">';
                    html += '<div class="form-inline x-valid">';

                    html += "<label class='col-sm-2 control-label'>相邻位置<span class='symbol required'></span></label>";
                    html += "<div class='col-sm-4'>";
                    html += '<select class="form-control input-full adjacentPosition' + j + '" name="adjacentPosition' + j + '" required >';
                    html += "</select>";
                    html += "</div>";

                    html += "<label class='col-sm-2 control-label'>距离(m)<span class='symbol required'></span></label>";
                    html += "<div class='col-sm-3'>";
                    html += '<input type="text" placeholder="距离" name="distance' + j + '" class="form-control input-full" required value="' + strs2[j] + '">';
                    html += "</div>";

                    html += "<div class='col-sm-1'>";
                    html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='houseRoom.prototype.cleanHTMLData(this)'>" + "</span>";
                    html += "</div>";

                    html += "</div>";
                    html += "</div>";
                    html += "</div>";
                    $("#" + houseRoom.prototype.config().frm).find(".streetNumbers").append(html);
                    var name = "select[name='adjacentPosition" + j + "']";
                    if (j > 0) {
                        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, strs[j], function (html, data) {
                            $("#" + houseRoom.prototype.config().frm).find(name).empty().html(html).trigger('change');
                        }, false);
                    }
                }

            }

            $("#" + houseRoom.prototype.config().frm).find(".streetNumbers").show();
        }

    }

    //绑定事件
    $('#' + houseRoom.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseRoom.prototype.loadDataDicList();
    })
})();


var damagedDegree = {};

//加载完损度数据列表
damagedDegree.loadDamagedDegreeList = function () {
    $.ajax({
        url: getContextPath() + '/basicHouseDamagedDegree/getDamagedDegreeList',
        type: 'get',
        data: {
            houseId: houseCommon.getHouseId()
        },
        success: function (result) {
            if (result.ret && result.data) {
                $("#damagedDegreeTab,#damagedDegreeTabContent").empty();
                var tabContentHtml = '';
                var groupArray = [];
                $.each(result.data, function (i, item) { //循环数据分组
                    if ($.inArray(item.type, groupArray) < 0) {
                        groupArray.push(item.type);
                        var tabHtml = '<li class="nav-item"><a href="#tab_content_' + item.type + '" role="tab" data-toggle="tab" aria-expanded="true" class="nav-link">' + item.typeName + '</a></li>';
                        $("#damagedDegreeTab").append(tabHtml);
                    }
                })

                $.each(groupArray, function (i, group) {//循环分组
                    var contentHtmlT = $('#damagedDegreeTabContentHtml').html();
                    if (contentHtmlT) {
                        var contentHtml = $('#damagedDegreeTabContentHtml').html().replace(/{type}/g, group);
                        $("#damagedDegreeTabContent").append(contentHtml);
                        var tbodyContentHtml = '';
                        $.each(result.data, function (i, item) {
                            if (item.type == group) {
                                var trHtml = $("#damagedDegreeTabTrHtml").html();
                                trHtml = trHtml.replace(/{id}/g, item.id).replace(/{category}/g, AssessCommon.toString(item.category)).replace(/{categoryName}/g, AssessCommon.toString(item.categoryName));
                                trHtml = trHtml.replace(/{intact}/g, AssessCommon.toString(item.intact));
                                trHtml = trHtml.replace(/{basicallyIntact}/g, AssessCommon.toString(item.basicallyIntact)).replace(/{generalDamage}/g, AssessCommon.toString(item.generalDamage));
                                trHtml = trHtml.replace(/{seriousDamage}/g, AssessCommon.toString(item.seriousDamage)).replace(/{isShow}/g, item.hasChildren ? '' : 'style="display: none"');
                                trHtml = trHtml.replace(/{standardScore}/g, AssessCommon.toString(item.standardScore));
                                trHtml = trHtml.replace(/{score}/g, AssessCommon.toString(item.score));
                                $("#damagedDegreeTabContent").find('.tab-pane:last').find('tbody').append(trHtml);
                                var trElement = $("#damagedDegreeTabContent").find('.tab-pane:last').find('tbody').find('tr:last');
                                if (!item.entityCondition && item.hasChildren) {
                                    trElement.find('[name=entityCondition]').hide();
                                    trElement.find('[name=entityConditionContent]').hide();
                                } else {
                                    trElement.find('[name=entityCondition]').val(item.entityCondition);
                                    trElement.find('[name=entityConditionContent]').val(item.entityConditionContent);
                                }
                            }
                        })
                    }
                })
                $('#damagedDegreeTab a').eq(0).tab('show');
            }
        }
    })
};

//现状change
damagedDegree.entityConditionChange = function (_this) {
    var group = $(_this).closest('.group');
    var text = $(_this).attr('data-' + $(_this).val());
    group.find('[data-name=entityConditionContent]').text(text);
    group.find('[name=entityConditionContent]').val(text);
    group.find("textarea").attr("required", "required");
};

damagedDegree.valid = function (remark) {
    var i = 0;
    var target = $("#damagedDegreeTabContent");
    target.find(".tab-pane").each(function () {
        $(this).tab('show');
        var checkArr = ["textarea", "select"];
        for (var k = 0; k < checkArr.length; k++) {
            var input = checkArr[k];
            $(this).find(input).each(function () {
                var required = $(this).attr('required');
                if ($(this).is(":visible")) {
                    //必须是已经选择了的
                    if (required) {
                        var text = $(this).val();
                        var name = $(this).attr('name');
                        var html = "<span class='help-block' for='" + name + "'" + ">" + "此字段必须填写 </span>";
                        if (text) {
                        } else {
                            $(this).after(html);
                            i++;
                        }
                    }
                }
            });
        }
    });
    if (i != 0) {
        if (remark) {
            notifySuccess('成功', remark);
        } else {
            notifySuccess('成功', '请检查房屋完好度');
        }
    }
    return i == 0;
};

//显示modal
damagedDegree.damagedDegreeDetailModalShow = function (damagedDegreeId, category) {
    $("#damagedDegreeId").val(damagedDegreeId);
    damagedDegree.initDamagedDegreeDetailType(category);
    damagedDegree.loadDamagedDegreeDetailList();
    $("#damagedDegreeDetailListModal").modal();
};

//加载明细项数据列表
damagedDegree.loadDamagedDegreeDetailList = function () {
    var cols = [];
    cols.push({field: 'typeName', title: '类型', width: '20%'});
    cols.push({field: 'entityConditionName', title: '实体状况', width: '30%'});
    cols.push({field: 'entityConditionContent', title: '状况内容', width: '40%'});
    cols.push({
        field: 'id', width: '10%', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="damagedDegree.editDamagedDegreeDetail(' + index + ')"><i class="fa fa-pen"></i></button>';
            str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="damagedDegree.deleteDamagedDegreeDetail(' + row.id + ')"><i class="fa fa-minus"></i></button>';
            str += '</div>';
            return str;
        }
    });
    $("#damagedDegreeDetailList").bootstrapTable('destroy');
    TableInit("damagedDegreeDetailList", getContextPath() + "/basicHouseDamagedDegree/getDamagedDegreeDetailList", cols, {
        damagedDegreeId: $("#damagedDegreeId").val()
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function (data) {
            $('.tooltips').tooltip();
            var damagedDegreeId = $("#damagedDegreeId").val();
            var currTr = $("#damagedDegreeTabContent").find('[name=id][value=' + damagedDegreeId + ']').closest('tr');
            var entityConditionEle = currTr.find('[name=entityCondition]');
            var entityConditionContentEle = currTr.find('[name=entityConditionContent]');
            if (data.total && data.total > 0) {
                entityConditionEle.show();
                entityConditionContentEle.show();
            } else {
                currTr.find('[data-name=entityConditionContent]').text('');
                entityConditionEle.val('').hide();
                entityConditionContentEle.val('').hide();
            }
        }
    });
};

//新增
damagedDegree.addDamagedDegreeDetail = function () {
    $("#damagedDegreeDetailForm").clearAll().find('[data-name=entityConditionContent]').text('');
    $("#damagedDegreeDetailModal").modal();
};

//编辑
damagedDegree.editDamagedDegreeDetail = function (index) {
    var row = $("#damagedDegreeDetailList").bootstrapTable('getData')[index];
    $("#damagedDegreeDetailForm").clearAll().initForm(row).find('[data-name=entityConditionContent]').text('');
    $("#damagedDegreeDetailModal").modal();
};

//删除
damagedDegree.deleteDamagedDegreeDetail = function (id) {
    $.ajax({
        url: getContextPath() + "/basicHouseDamagedDegree/deleteDamagedDegreeDetail",
        data: {id: id},
        success: function (result) {
            if (result.ret) {
                damagedDegree.loadDamagedDegreeDetailList();
            } else {
                AlertError(result.errmsg);
            }
        }
    })
};

//保存
damagedDegree.saveDamagedDegreeDetail = function () {
    if (!$("#damagedDegreeDetailForm").valid()) {
        return false;
    }
    var data = formParams("damagedDegreeDetailForm");
    data.houseId = houseCommon.getHouseId();
    data.damagedDegreeId = $("#damagedDegreeId").val();
    $.ajax({
        url: getContextPath() + "/basicHouseDamagedDegree/saveAndUpdateDamagedDegreeDetail",
        data: data,
        success: function (result) {
            if (result.ret) {
                damagedDegree.loadDamagedDegreeDetailList();
                $("#damagedDegreeDetailModal").modal('hide');
            } else {
                AlertError(result.errmsg);
            }
        }
    })
};

//初始化类型
damagedDegree.initDamagedDegreeDetailType = function (damagedDegreeId) {
    $.ajax({
        url: getContextPath() + "/dataDamagedDegree/getCacheDamagedDegreeListByPid",
        type: 'get',
        data: {
            pid: damagedDegreeId
        },
        success: function (result) {
            if (result.ret && result.data) {
                var typeElement = $("#damagedDegreeDetailForm").find('[name=type]');
                typeElement.empty().append('<option value="">-请选择-</option>');
                $.each(result.data, function (i, item) {
                    var optionHtml = '<option value="' + item.id + '" ';
                    optionHtml += 'data-intact="' + item.intact + '" ';
                    optionHtml += 'data-basicallyIntact="' + item.basicallyIntact + '" ';
                    optionHtml += 'data-generalDamage="' + item.generalDamage + '" ';
                    optionHtml += 'data-seriousDamage="' + item.seriousDamage + '" ';
                    optionHtml += '>' + item.name;
                    optionHtml += '</option>';
                    typeElement.append(optionHtml);
                })
            }
        }
    })
}

//自动填充明细项状况内容
damagedDegree.autoFillEntityConditionContent = function () {
    var option = $("#damagedDegreeDetailForm").find('[name=type]').find("option:selected");
    var entityCondition = $("#damagedDegreeDetailForm").find('[name=entityCondition]').val();
    if (option && entityCondition) {
        var text = $(option).attr('data-' + entityCondition);
        $("#damagedDegreeDetailForm").find('[data-name=entityConditionContent]').text(text);
        $("#damagedDegreeDetailForm").find('[name=entityConditionContent]').val(text);
    }
}

//获取需保存的数据
damagedDegree.getFormData = function () {
    var dataArray = [];
    $("#damagedDegreeTabContent").find('.group').each(function () {
        var data = {};
        data.id = $(this).find('[name=id]').val();
        data.category = $(this).find('[name=category]').val();
        data.entityCondition = $(this).find('[name=entityCondition]').val();
        data.entityConditionContent = $(this).find('[name=entityConditionContent]').val();
        data.score = $(this).find('[name=score]').val();
        dataArray.push(data);
    })
    return dataArray;
}

