
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <h3>通信网络
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
    </h3>
    <div>
        <table class="table table-bordered" id="estateParkingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供排水情况
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供电信息
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyPowerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供热信息
    </h3>
    <div>
        <table class="table table-bordered" id="EstateSupplyHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供气信息
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
                $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateNetwork.prototype.config().table, "${pageContext.request.contextPath}/basicEstateNetwork/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id}
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
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
                $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateParking.prototype.config().table, "${pageContext.request.contextPath}/basicEstateParking/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id}
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
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
                $("#" + estateSupplyWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyWater.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyWater.prototype.config().type,
                    estateId: ${empty basicEstate.id?0:basicEstate.id}
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
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
                $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyPower.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyPower.prototype.config().type,
                    estateId: ${empty basicEstate.id?0:basicEstate.id}
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
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
                $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyHeating.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    type: estateSupplyHeating.prototype.config().type,
                    estateId: ${empty basicEstate.id?0:basicEstate.id}
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
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
                $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyGas.prototype.config().table, "${pageContext.request.contextPath}/basicEstateSupply/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
                    type: estateSupplyGas.prototype.config().type
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        }
    })();
</script>