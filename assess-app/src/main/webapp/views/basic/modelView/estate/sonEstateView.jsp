<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <h3>通信网络
        <button type="button" class="btn btn-success" onclick="estateNetwork.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="examineEstateNetworkList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>
        车位
        <button type="button" class="btn btn-success" onclick="estateParking.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="estateParkingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供排水情况
        <button type="button" class="btn btn-success" onclick="estateSupplyWater.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供电信息
        <button type="button" class="btn btn-success" onclick="estateSupplyPower.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyPowerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供热信息
        <button type="button" class="btn btn-success" onclick="estateSupplyHeating.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供气信息
        <button type="button" class="btn btn-success" onclick="estateSupplyGas.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyGasList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="text/javascript">
    var estateNetwork;
    (function () {
        estateNetwork = function () {

        };
        estateNetwork.prototype = {
            config: function () {
                var data = {};
                data.table = "examineEstateNetworkList";
                data.box = "divBoxExamineEstateNetwork";
                data.frm = "frmExamineEstateNetwork";
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
                cols.push({field: 'name', title: '通讯名称'});
                cols.push({field: 'serviceContent', title: '服务内容'});
                cols.push({field: 'indexParameter', title: '通信网络指标参数'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateNetwork.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateNetwork.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateNetwork.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateNetwork.prototype.config().table, "${pageContext.request.contextPath}/basicEstateNetwork/getBootstrapTableVo", cols, {
                    estateId: estateId
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
                    url: "${pageContext.request.contextPath}/basicEstateNetwork/deleteBasicEstateNetwork",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateNetwork.prototype.loadDataDicList();
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
                estateNetwork.prototype.init({});
                $('#' + estateNetwork.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateNetwork.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateNetwork.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateNetwork.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateNetwork/saveAndUpdateBasicEstateNetwork",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateNetwork.prototype.config().box).modal('hide');
                            estateNetwork.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateNetwork/getBasicEstateNetworkById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            estateNetwork.prototype.init(result.data);
                            $('#' + estateNetwork.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateNetwork.prototype.config().frm).clearAll();
                $("#" + estateNetwork.prototype.config().frm).initForm(item);
            }
        }
    })();

    var estateParking;
    (function () {
        estateParking = function () {

        };
        estateParking.prototype = {
            config: function () {
                var data = {};
                data.table = "estateParkingList";
                data.box = "divBoxEstateParking";
                data.frm = "frmEstateParking";
                data.fileIDName = "house_estateParking";
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
                cols.push({field: 'parkingTypeName', title: '车位类型'});
                cols.push({field: 'location', title: '车辆位置'});
                cols.push({field: 'fileViewName', title: '上传的附件'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateParking.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateParking.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateNetwork.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateParking.prototype.config().table, "${pageContext.request.contextPath}/basicEstateParking/getBootstrapTableVo", cols, {
                    estateId: estateId
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
                    url: "${pageContext.request.contextPath}/basicEstateParking/deleteBasicEstateParking",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateParking.prototype.loadDataDicList();
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
                $('#' + estateParking.prototype.config().box).modal("show");
                estateParking.prototype.init({});
            },
            saveData: function () {
                if (!$("#" + estateParking.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateParking.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateParking.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateParking/saveAndUpdateBasicEstateParking",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateParking.prototype.config().box).modal('hide');
                            estateParking.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateParking/getBasicEstateParkingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (estateParking.prototype.isNotBlank(result.data)) {
                                estateParking.prototype.init(result.data);
                            } else {
                                estateParking.prototype.init({});
                            }
                            $('#' + estateParking.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateParking.prototype.config().frm).clearAll();
                $("#" + estateParking.prototype.config().frm).initForm(item);
                objectData.showFile(estateParking.prototype.config().fileIDName, AssessDBKey.BasicEstateParking, estateParking.prototype.isNotBlank(item.id) ? item.id : "0");
                objectData.uploadFile(estateParking.prototype.config().fileIDName, AssessDBKey.BasicEstateParking, estateParking.prototype.isNotBlank(item.id) ? item.id : "0");
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_car_type, item.parkingType, function (html, data) {
                    $("#" + estateParking.prototype.config().frm).find("select.parkingType").empty().html(html).trigger('change');
                });
            }
        }
    })();


    var estateSupplyWater;
    (function () {
        estateSupplyWater = function () {

        };
        estateSupplyWater.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyWaterList";
                data.box = "divBoxEstateSupplyWater";
                data.frm = "frmEstateSupplyWater";
                data.type = "estateSupplyWater";//根据 ExamineEstateSupplyEnumType 配置
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
                cols.push({field: 'name', title: '供水名称'});
                cols.push({field: 'reputation', title: '供水商信誉'});
                cols.push({field: 'gradeName', title: '供水商等级'});
                cols.push({field: 'lineGradeName', title: '供水线路等级'});
                cols.push({field: 'power', title: '功率'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateSupplyWater.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateSupplyWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyWater.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyWater.prototype.config().type,
                    estateId: estateId
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/deleteBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateSupplyWater.prototype.loadDataDicList();
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
                estateSupplyWater.prototype.init({});
                $("#" + estateSupplyWater.prototype.config().frm).find(".type").val(estateSupplyWater.prototype.config().type);
                $('#' + estateSupplyWater.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateSupplyWater.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateSupplyWater.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateSupplyWater.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateSupplyWater.prototype.config().box).modal('hide');
                            estateSupplyWater.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/getBasicEstateSupplyById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (estateSupplyWater.prototype.isNotBlank(result.data)) {
                                estateSupplyWater.prototype.init(result.data);
                            } else {
                                estateSupplyWater.prototype.init({});
                            }
                            $('#' + estateSupplyWater.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateSupplyWater.prototype.config().frm).clearAll();
                $("#" + estateSupplyWater.prototype.config().frm).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                    $("#" + estateSupplyWater.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                    $("#" + estateSupplyWater.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
                });
            }
        }
    })();

    var estateSupplyPower;
    (function () {
        estateSupplyPower = function () {

        };
        estateSupplyPower.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyPowerList";
                data.box = "divBoxEstateSupplyPower";
                data.frm = "frmEstateSupplyPower";
                data.type = "estateSupplyPower";//根据 ExamineEstateSupplyEnumType 配置
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
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'reputation', title: '供电商信誉'});
                cols.push({field: 'gradeName', title: '供电商等级'});
                cols.push({field: 'lineGradeName', title: '供电线路等级'});
                cols.push({field: 'power', title: '功率'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyPower.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyPower.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateSupplyPower.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyPower.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyPower.prototype.config().type,
                    estateId: estateId
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/deleteBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateSupplyPower.prototype.loadDataDicList();
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
                estateSupplyPower.prototype.init({});
                $("#" + estateSupplyPower.prototype.config().frm + " .type").val(estateSupplyPower.prototype.config().type);
                $('#' + estateSupplyPower.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateSupplyPower.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateSupplyPower.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateParking.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateSupplyPower.prototype.config().box).modal('hide');
                            estateSupplyPower.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/getBasicEstateSupplyById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (estateSupplyPower.prototype.isNotBlank(result.data)) {
                                estateSupplyPower.prototype.init(result.data);
                            } else {
                                estateSupplyPower.prototype.init({});
                            }
                            $('#' + estateSupplyPower.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateSupplyPower.prototype.config().frm).clearAll();
                $("#" + estateSupplyPower.prototype.config().frm).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                    $("#" + estateSupplyPower.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                    $("#" + estateSupplyPower.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
                });
            }
        }
    })();

    var estateSupplyHeating;
    (function () {
        estateSupplyHeating = function () {
        };
        estateSupplyHeating.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyHeatingList";
                data.box = "divBoxEstateSupplyHeating";
                data.frm = "frmEstateSupplyHeating";
                data.type = "estateSupplyHeating";//根据 ExamineEstateSupplyEnumType 配置
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
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'reputation', title: '供热商信誉'});
                cols.push({field: 'gradeName', title: '供热商等级'});
                cols.push({field: 'power', title: '功率'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyHeating.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyHeating.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateSupplyHeating.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyHeating.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyHeating.prototype.config().type,
                    estateId: estateId
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/deleteBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateSupplyHeating.prototype.loadDataDicList();
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
                estateSupplyHeating.prototype.init({});
                $("#" + estateSupplyHeating.prototype.config().frm + " .type").val(estateSupplyHeating.prototype.config().type);
                $('#' + estateSupplyHeating.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateSupplyHeating.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateSupplyHeating.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateSupplyHeating.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateSupplyHeating.prototype.config().box).modal('hide');
                            estateSupplyHeating.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/getBasicEstateSupplyById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (estateSupplyHeating.prototype.isNotBlank(result.data)) {
                                estateSupplyHeating.prototype.init(result.data);
                            } else {
                                estateSupplyHeating.prototype.init({});
                            }
                            $('#' + estateSupplyHeating.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateSupplyHeating.prototype.config().frm).clearAll();
                $("#" + estateSupplyHeating.prototype.config().frm).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                    $("#" + estateSupplyHeating.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                    $("#" + estateSupplyHeating.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
                });
            }
        }
    })();

    var estateSupplyGas;
    (function () {
        estateSupplyGas = function () {
        };
        estateSupplyGas.prototype = {
            config: function () {
                var data = {};
                data.table = "EstateSupplyGasList";
                data.box = "divBoxEstateSupplyGas";
                data.frm = "frmEstateSupplyGas";
                data.type = "estateSupplyGas";//根据 ExamineEstateSupplyEnumType 配置
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
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'reputation', title: '供气商信誉'});
                cols.push({field: 'gradeName', title: '供气商等级'});
                cols.push({field: 'power', title: '功率'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyGas.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyGas.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                var estateId = "";
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    estateId = estateSupplyGas.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyGas.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    estateId: estateId,
                    type: estateSupplyGas.prototype.config().type
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/deleteBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateSupplyGas.prototype.loadDataDicList();
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
                estateSupplyGas.prototype.init({});
                $("#" + estateSupplyGas.prototype.config().frm + " .type").val(estateSupplyGas.prototype.config().type);
                $('#' + estateSupplyGas.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + estateSupplyGas.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateSupplyGas.prototype.config().frm);
                try {
                    var basicEstate = formParams(objectData.config.basicEstate.frm);
                    data.estateId = estateSupplyGas.prototype.isNotBlank(basicEstate.id) ? basicEstate.id : "0";
                } catch (e) {
                    console.log("函数失效");
                    console.error(e);
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateSupplyGas.prototype.config().box).modal('hide');
                            estateSupplyGas.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicEstateSupply/getBasicEstateSupplyById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (estateSupplyGas.prototype.isNotBlank(result.data)) {
                                estateSupplyGas.prototype.init(result.data);
                            } else {
                                estateSupplyGas.prototype.init({});
                            }
                            $('#' + estateSupplyGas.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + estateSupplyGas.prototype.config().frm).clearAll();
                $("#" + estateSupplyGas.prototype.config().frm).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                    $("#" + estateSupplyGas.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                    $("#" + estateSupplyGas.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
                });
            }
        }
    })();

</script>

<div id="divBoxEstateSupplyGas" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供气</h3>
            </div>
            <form id="frmEstateSupplyGas" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气线路等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="reputation" class="form-control"
                                                   placeholder="供应商信誉" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="功率" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyGas.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供热</h3>
            </div>
            <form id="frmEstateSupplyHeating" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热线路等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="reputation" class="form-control"
                                                   placeholder="供应商信誉" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="功率" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyHeating.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyPower" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供电</h3>
            </div>
            <form id="frmEstateSupplyPower" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电线路管等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="reputation" class="form-control"
                                                   placeholder="供应商信誉" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="功率" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyPower.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供水</h3>
            </div>
            <form id="frmEstateSupplyWater" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水线路管等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="reputation" class="form-control"
                                                   placeholder="供应商信誉" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="功率" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateParking" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">车位</h3>
            </div>
            <form id="frmEstateParking" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位位置
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="location"
                                                   placeholder="车位位置" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="parkingType"
                                                    class="form-control search-select select2 parkingType">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车辆图
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_estateParking" name="house_estateParking"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_house_estateParking"></div>
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
                    <button type="button" class="btn btn-primary" onclick="estateParking.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineEstateNetwork" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">通信网络</h3>
            </div>
            <form id="frmExamineEstateNetwork" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="通信网络名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="serviceContent"
                                                   placeholder="服务内容" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络指标参数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="indexParameter"
                                                   placeholder="通信网络指标参数" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateNetwork.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
