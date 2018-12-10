/**
 * Created by kings on 2018-11-9.
 */

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
            var cols = [];
            cols.push({field: 'equipment', title: '设备品牌'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseHeating.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseHeating.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseHeating.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseHeating.prototype.config().frm);
            data.type = houseHeating.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseHeating.prototype.config().box).modal('hide');
                        houseHeating.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseHeating.prototype.config().frm).clearAll();
            $("#" + houseHeating.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_heating_method, item.category, function (html, data) {
                $("#" + houseHeating.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseHeating.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
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
            var cols = [];
            cols.push({field: 'equipment', title: '设备品牌'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseAirConditioner.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseAirConditioner.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseAirConditioner.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseAirConditioner.prototype.config().frm);
            data.type = houseAirConditioner.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseAirConditioner.prototype.config().box).modal('hide');
                        houseAirConditioner.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseAirConditioner.prototype.config().frm).clearAll();
            $("#" + houseAirConditioner.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_air_conditioning_mode, item.category, function (html, data) {
                $("#" + houseAirConditioner.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseAirConditioner.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
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
            var cols = [];
            cols.push({field: 'equipment', title: '设备品牌'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseNewWind.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseNewWind.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseNewWind.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseNewWind.prototype.config().frm);
            data.type = houseNewWind.prototype.config().type;
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseNewWind.prototype.config().box).modal('hide');
                        houseNewWind.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseNewWind.prototype.config().frm).clearAll();
            $("#" + houseNewWind.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_equipment_price_range, item.equipmentPrice, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.equipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_way_wind, item.category, function (html, data) {
                $("#" + houseNewWind.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseNewWind.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
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
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'parameterIndexH', title: '参数指标'});
            cols.push({field: 'useH', title: '用途'});
            cols.push({field: 'maintenanceStatus', title: '维护状况'});
            cols.push({field: 'typeName', title: '类型'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'priceName', title: '价格'});
            cols.push({field: 'fileViewName', title: '位置图'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseCorollaryEquipment.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseCorollaryEquipment.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseCorollaryEquipment.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseCorollaryEquipment.prototype.config().frm);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseCorollaryEquipment/saveAndUpdateBasicHouseCorollaryEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseCorollaryEquipment.prototype.config().box).modal('hide');
                        houseCorollaryEquipment.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseCorollaryEquipment.prototype.config().frm).clearAll();
            $("#" + houseCorollaryEquipment.prototype.config().frm).initForm(item);

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
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_corollary_equipment_price, item.price_select, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.price_select").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_corollary_equipment_type, item.type, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.type").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_corollary_equipment_category, item.category, function (html, data) {
                $("#" + houseCorollaryEquipment.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseCorollaryEquipment.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
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
            var cols = [];
            cols.push({field: 'streetName', title: '名称'});
            cols.push({field: 'streetLevelName', title: '街道级别'});
            cols.push({field: 'trafficFlowName', title: '交通流量'});
            cols.push({field: 'visitorsFlowrateName', title: '人流量'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseFaceStreet.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseFaceStreet.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseFaceStreet.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            houseFaceStreet.prototype.init({});
            $("#" + houseFaceStreet.prototype.config().frm + " .name").empty();
            var lableValue = "街道（道路）名称";
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + 'streetName' + "'" + ">";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";
            $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
            $('#' + houseFaceStreet.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseFaceStreet.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(houseFaceStreet.prototype.config().frm);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseFaceStreet/saveAndUpdateBasicHouseFaceStreet",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseFaceStreet.prototype.config().box).modal('hide');
                        houseFaceStreet.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
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
        },
        appendHTML: function (item, this_) {
            var lableValue = "街道（道路）名称";
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + item + "'" + ">";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";
            $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
        },
        cleanHTMLData: function (item) {
            $(item).parent().prev().parent().parent().empty();
        },
        writeHTMLData: function (str) {
            $("#" + houseFaceStreet.prototype.config().frm + " .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "街道（道路）名称";
            var item = "streetName";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='form-group' style='margin-top:8px;'>";
                html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
                html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
                html += "<input type='text' required class='form-control'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";
                html += "</div>";
                $("#" + houseFaceStreet.prototype.config().frm + " .name").append(html);
            }
        }
    }

    //绑定事件
    $('#' + houseFaceStreet.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
        houseFaceStreet.prototype.loadDataDicList();
    })
})();

var houseIntelligent;
(function () {
    houseIntelligent = function () {

    };
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
            var cols = [];
            cols.push({field: 'wireErectionName', title: '电线架设方式'});
            cols.push({field: 'switchCircuitName', title: '开关回路'});
            cols.push({field: 'lampsLanternsName', title: '灯具'});
            cols.push({field: 'internalCommunicationName', title: '屋内通讯'});
            cols.push({field: 'monitoringSystemName', title: '监控系统'});
            cols.push({field: 'intelligentSystemName', title: '智能系统'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseIntelligent.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseIntelligent.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseIntelligent.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseIntelligent.prototype.config().frm);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseIntelligent/saveAndUpdateBasicHouseIntelligent",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseIntelligent.prototype.config().box).modal('hide');
                        houseIntelligent.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseIntelligent.prototype.config().frm).clearAll();
            $("#" + houseIntelligent.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_intelligent_system, item.intelligentSystem, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.intelligentSystem").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_monitoring_system, item.monitoringSystem, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.monitoringSystem").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_lamps_lanterns, item.lampsLanterns, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.lampsLanterns").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_switch_circuit, item.switchCircuit, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.switchCircuit").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_wire_erection_method, item.wireErection, function (html, data) {
                $("#" + houseIntelligent.prototype.config().frm).find("select.wireErection").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseIntelligent.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
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
            var cols = [];
            cols.push({field: 'natrueIntakePointNumber', title: '自然区间取水点数'});
            cols.push({field: 'intakePointNumber', title: '取水点数'});
            cols.push({field: 'supplyErectionMethodName', title: '供水管架设方式'});
            cols.push({field: 'pretreatedWaterName', title: '前置净水'});
            cols.push({field: 'drainageCircuitName', title: '排水回路'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseWater.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
            var data = formParams(houseWater.prototype.config().frm);
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseWater/saveAndUpdateBasicHouseWater",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseWater.prototype.config().box).modal('hide');
                        houseWater.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseWater.prototype.config().frm).clearAll();
            $("#" + houseWater.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_pretreated_water, item.pretreatedWater, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.pretreatedWater").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_supply_erection_method, item.supplyErectionMethod, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.supplyErectionMethod").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_purification_equipment_price, item.purificationEquipmentPrice, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.purificationEquipmentPrice").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_drainage_circuit, item.drainageCircuit, function (html, data) {
                $("#" + houseWater.prototype.config().frm).find("select.drainageCircuit").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + houseWater.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
        houseWater.prototype.loadDataDicList();
    })
})();

var houseRoom;
(function () {
    houseRoom = function () {

    };
    houseRoom.prototype = {
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
            var cols = [];
            cols.push({field: 'roomTypeName', title: '房间类型'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'sunshine', title: '日照'});
            cols.push({field: 'lighting', title: '采光'});
            cols.push({field: 'layerHeight', title: '层高'});
            cols.push({field: 'opening', title: '开间'});
            cols.push({field: 'depth', title: '进深'});
            cols.push({field: 'aeration', title: '通风'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoom.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="装修情况" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-gavel fa-white"></i></a>';
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
                        toastr.success('删除成功');
                        houseRoom.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            $("#" + houseRoom.prototype.config().frm).clearAll();
            $('#' + houseRoom.prototype.config().box).modal("show");
            houseRoom.prototype.init({});
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
                        toastr.success('保存成功');
                        $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal('hide');
                        var item = result.data;
                        if (houseRoom.prototype.isEmpty(item)) {
                            houseRoom.prototype.subclassLoadList(item);
                        }
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                        toastr.success('删除成功');
                        houseRoom.prototype.subclassLoadList(result.data);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        subclassInit: function (item) {
            $("#" + houseRoom.prototype.config().frmSubclass).clearAll();
            $("#" + houseRoom.prototype.config().frmSubclass).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.material, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.material').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.materialPrice').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.constructionTechnology').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.part, function (html, data) {
                $("#" + houseRoom.prototype.config().frmSubclass).find('select.part').empty().html(html).trigger('change');
            });
        },
        subclassLoadList: function (id) {
            var cols = [];
            cols.push({field: 'materialName', title: '装修材料'});
            cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
            cols.push({field: 'partName', title: '房间装修部位'});
            cols.push({field: 'materialPriceName', title: '装修材料价格区间'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.subclassRemoveData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
            data.houseId = houseCommon.getHouseId();
            $.ajax({
                url: getContextPath() + "/basicHouseRoom/saveAndUpdateBasicHouseRoom",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + houseRoom.prototype.config().box).modal('hide');
                        houseRoom.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                        houseRoom.prototype.init(result.data);
                        $('#' + houseRoom.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + houseRoom.prototype.config().frm).clearAll();
            $("#" + houseRoom.prototype.config().frm).initForm(item);
            var practicalUse = houseCommon.houseForm.find('[name=practicalUse]').val();
            if (houseRoom.prototype.isEmpty(practicalUse)) {
                AssessCommon.loadDataDicByPid(practicalUse, item.roomType, function (html, data) {
                    $("#" + houseRoom.prototype.config().frm).find('select.roomType').empty().html(html).trigger('change');
                });
            } else {
                Alert("请先选择房屋下的实际用途");
            }
        }
    }

    //绑定事件
    $('#' + houseRoom.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
        houseRoom.prototype.loadDataDicList();
    })
})();

