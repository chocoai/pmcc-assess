<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房间
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="houseRoom.prototype.showModel()"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="HouseRoomList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供排水情况
        <button type="button" class="btn btn-success" onclick="houseWater.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>电力通讯网络
        <button type="button" class="btn btn-success" onclick="houseIntelligent.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>临街（路）状况
        <button type="button" class="btn btn-success" onclick="houseFaceStreet.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseFaceStreetList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>房屋配套设备设施信息
        <button type="button" class="btn btn-success" onclick="houseCorollaryEquipment.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseCorollaryEquipmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>新风情况
        <button type="button" class="btn btn-success" onclick="houseNewWind.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseNewWindList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>空调情况
        <button type="button" class="btn btn-success" onclick="houseAirConditioner.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseAirConditionerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供暖情况
        <button type="button" class="btn btn-success" onclick="houseHeating.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="HouseHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="text/javascript">

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
                cols.push({field: 'equipment', title: '名称'});
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
                TableInit(houseHeating.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseHeating.prototype.config().type,
                    houseId:'0'
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/deleteBasicHouseEquipment",
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
                data.houseId = '0' ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/getBasicHouseEquipmentById",
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
                cols.push({field: 'equipment', title: '名称'});
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
                TableInit(houseAirConditioner.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseAirConditioner.prototype.config().type,
                    houseId:'0'
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/deleteBasicHouseEquipment",
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
                data.houseId = '0' ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/getBasicHouseEquipmentById",
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
                cols.push({field: 'equipment', title: '名称'});
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
                TableInit(houseNewWind.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseNewWind.prototype.config().type,
                    houseId:'0'
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/deleteBasicHouseEquipment",
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
                data.houseId = '0' ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/saveAndUpdateBasicHouseEquipment",
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
                    url: "${pageContext.request.contextPath}/basicHouseEquipment/getBasicHouseEquipmentById",
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
    })();
</script>

<script type="application/javascript">

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
                data.FileID = "positionDiagramFileID" ;// ExamineFileUpLoadTwoFieldEnum
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
                TableInit(houseCorollaryEquipment.prototype.config().table, "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
                    houseId:'0'
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
                    url: "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/deleteBasicHouseCorollaryEquipment",
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
                data.houseId = '0' ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/saveAndUpdateBasicHouseCorollaryEquipment",
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
                    url: "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/getBasicHouseCorollaryEquipmentById",
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
                objectData.showFile(houseCorollaryEquipment.prototype.config().FileID, AssessDBKey.BasicHouseCorollaryEquipment, houseCorollaryEquipment.prototype.isNotBlank(item.id) ? item.id : "0");
                objectData.uploadFile(houseCorollaryEquipment.prototype.config().FileID, AssessDBKey.BasicHouseCorollaryEquipment, houseCorollaryEquipment.prototype.isNotBlank(item.id) ? item.id : "0");
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
                TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/basicHouseFaceStreet/getBootstrapTableVo", cols, {
                    houseId: '0'
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
                    url: "${pageContext.request.contextPath}/basicHouseFaceStreet/deleteBasicHouseFaceStreet",
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
                data.houseId = '0';
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseFaceStreet/saveAndUpdateBasicHouseFaceStreet",
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
                    url: "${pageContext.request.contextPath}/basicHouseFaceStreet/getBasicHouseFaceStreetById",
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
                TableInit(houseIntelligent.prototype.config().table, "${pageContext.request.contextPath}/basicHouseIntelligent/getBootstrapTableVo", cols, {
                    houseId: '0'
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
                    url: "${pageContext.request.contextPath}/basicHouseIntelligent/deleteBasicHouseIntelligent",
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
                data.houseId = '0';
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseIntelligent/saveAndUpdateBasicHouseIntelligent",
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
                    url: "${pageContext.request.contextPath}/basicHouseIntelligent/getBasicHouseIntelligentById",
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
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_internal_communication, item.internalCommunication, function (html, data) {
                    $("#" + houseIntelligent.prototype.config().frm).find("select.internalCommunication").empty().html(html).trigger('change');
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
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/basicHouseWater/getBootstrapTableVo", cols, {
                    houseId: '0'
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
                    url: "${pageContext.request.contextPath}/basicHouseWater/deleteBasicHouseWater",
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
                data.houseId = '0';
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseWater/saveAndUpdateBasicHouseWater",
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
                    url: "${pageContext.request.contextPath}/basicHouseWater/getBasicHouseWaterById",
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
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_intake_equipment_price, item.waterIntakeEquipmentPrice, function (html, data) {
                    $("#" + houseWater.prototype.config().frm).find("select.waterIntakeEquipmentPrice").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_purification_equipment_price, item.purificationEquipmentPrice, function (html, data) {
                    $("#" + houseWater.prototype.config().frm).find("select.purificationEquipmentPrice").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_water_drainage_circuit, item.drainageCircuit, function (html, data) {
                    $("#" + houseWater.prototype.config().frm).find("select.drainageCircuit").empty().html(html).trigger('change');
                });
            }
        }
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
                cols.push({field: 'name', title: '房间名称'});
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
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-search-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/basicHouseRoom/getBootstrapTableVo", cols, {
                    houseId: 0
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/deleteBasicHouseRoom",
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/saveAndUpdateBasicHouseRoomDecorate",
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/deleteBasicHouseRoomDecorate",
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
                TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
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
                data.houseId = "0";
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseRoom/saveAndUpdateBasicHouseRoom",
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/getBasicHouseRoomById",
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
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseType, item.roomType, function (html, data) {
                    $("#" + houseRoom.prototype.config().frm).find('select.roomType').empty().html(html).trigger('change');
                });
            }
        }
    })();

</script>

<div id="divBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间</h3>
            </div>
            <form id="frmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间名称
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="房间名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通风
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="通风" name="aeration" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            日照
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="日照" name="sunshine" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            采光
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="采光" name="lighting" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            层高
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="层高" name="layerHeight" class="form-control"
                                            >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开间
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="开间" name="opening" class="form-control"
                                            >
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            进深
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="进深" name="depth" class="form-control"
                                            >
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔音
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="隔音" name="soundInsulation"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间类型
                                        </label>
                                        <div class="col-sm-4">
                                            <select required="required" name="roomType"
                                                    class="form-control search-select select2 roomType">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">房间装修数据</h3>
                <input type="hidden" name="roomId" class="roomId">
            </div>
            <div class="panel-body">
                <span id="toolbarSub">
                    <button type="button" class="btn btn-success"
                            onclick="houseRoom.prototype.showModelSubclassSaveView()"
                            data-toggle="modal" href="#divSubDataDicManage"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="boxSubclassSaveViewHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间装修</h3>
            </div>
            <form id="SubclassFrmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="roomId" class="roomId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="part"
                                                    class="form-control search-select select2 part">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="material"
                                                    class="form-control search-select select2 material">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.subclassSave()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供排水情况</h3>
            </div>
            <form id="frmHouseWater" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            取水设备<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="取水设备" name="waterIntakeEquipment"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            采水点数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="采水点数" name="intakePointNumber"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自然区间取水点数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="自然区间取水点数" name="natrueIntakePointNumber"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水回路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="drainageCircuit"
                                                    class="form-control search-select select2 drainageCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="purificationEquipmentPrice"
                                                    class="form-control search-select select2 purificationEquipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            取水设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="waterIntakeEquipmentPrice"
                                                    class="form-control search-select select2 waterIntakeEquipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水管架设方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="supplyErectionMethod"
                                                    class="form-control search-select select2 supplyErectionMethod">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="pretreatedWater"
                                                    class="form-control search-select select2 pretreatedWater">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseIntelligent" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">电力通讯网络</h3>
            </div>
            <form id="frmHouseIntelligent" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开关回路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="switchCircuit"
                                                    class="form-control search-select select2 switchCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            灯具<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lampsLanterns"
                                                    class="form-control search-select select2 lampsLanterns">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            屋内通讯<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="internalCommunication"
                                                    class="form-control search-select select2 internalCommunication">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            监控系统<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="monitoringSystem"
                                                    class="form-control search-select select2 monitoringSystem">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            智能系统<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="intelligentSystem"
                                                    class="form-control search-select select2 intelligentSystem">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电线架设方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="wireErection"
                                                    class="form-control search-select select2 wireErection">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseIntelligent.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseFaceStreet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">临街（路）状况</h3>
            </div>
            <form id="frmHouseFaceStreet" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道（道路）名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="houseFaceStreet.prototype.appendHTML('streetName',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">街道（道路）名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="streetName" required="required"
                                                   type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="houseFaceStreet.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            人流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="visitorsFlowrate"
                                                    class="form-control search-select select2 visitorsFlowrate">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            交通流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="trafficFlow"
                                                    class="form-control search-select select2 trafficFlow">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道级别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="streetLevel"
                                                    class="form-control search-select select2 streetLevel">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseFaceStreet.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseCorollaryEquipment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房屋配套设备设施</h3>
            </div>
            <form id="frmHouseCorollaryEquipment" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            参数指标<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="parameterIndexH" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            维护状况<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="maintenanceStatus" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备用途<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="useH" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            价格<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="price"
                                                    class="form-control search-select select2 price_select">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型图<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="positionDiagramFileID" name="positionDiagramFileID"
                                                   required="required" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_positionDiagramFileID"></div>
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
                    <button type="button" class="btn btn-primary" onclick="houseCorollaryEquipment.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseNewWind" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">新风情况</h3>
            </div>
            <form id="frmHouseNewWind" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备名称" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseNewWind.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseAirConditioner" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">空调情况</h3>
            </div>
            <form id="frmHouseAirConditioner" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备名称" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseAirConditioner.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供暖情况</h3>
            </div>
            <form id="frmHouseHeating" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备名称" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="houseHeating.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>