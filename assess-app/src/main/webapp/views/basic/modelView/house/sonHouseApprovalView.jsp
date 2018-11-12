
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <div class="x_title">
        <h3>
            房间
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
    </h3>
    <div>
        <table class="table table-bordered" id="HouseWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>电力通讯网络
    </h3>
    <div>
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>临街（路）状况
    </h3>
    <div>
        <table class="table table-bordered" id="HouseFaceStreetList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>房屋配套设备设施信息
    </h3>
    <div>
        <table class="table table-bordered" id="HouseCorollaryEquipmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>新风情况
    </h3>
    <div>
        <table class="table table-bordered" id="HouseNewWindList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>空调情况
    </h3>
    <div>
        <table class="table table-bordered" id="HouseAirConditionerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>供暖情况
    </h3>
    <div>
        <table class="table table-bordered" id="HouseHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

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
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-search-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/basicHouseRoom/getBootstrapTableVo", cols, {
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            showModelSubclass: function (id) {
                houseRoom.prototype.subclassLoadList(id);
                if ($('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").size() > 0) {
                    $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val(id);
                }
                $('#' + houseRoom.prototype.config().boxSubclass).modal("show");
            },
            subclassLoadList: function (id) {
                var cols = [];
                cols.push({field: 'materialName', title: '装修材料'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                cols.push({field: 'partName', title: '房间装修部位'});
                cols.push({field: 'materialPriceName', title: '装修材料价格区间'});
                $("#" + houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
                    roomId: id,
                    approval:true
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
                $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseCorollaryEquipment.prototype.config().table, "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
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
                $("#" + houseFaceStreet.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/basicHouseFaceStreet/getBootstrapTableVo", cols, {
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
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
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
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
                $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/basicHouseWater/getBootstrapTableVo", cols, {
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
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
                $("#" + houseHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseHeating.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseHeating.prototype.config().type,
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
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
                $("#" + houseAirConditioner.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseAirConditioner.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseAirConditioner.prototype.config().type,
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
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
                $("#" + houseNewWind.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseNewWind.prototype.config().table, "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
                    type: houseNewWind.prototype.config().type,
                    houseId:'${empty basicHouse.id?0:basicHouse.id}',
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
        }
    })();
</script>

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
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
            </div>
        </div>
    </div>
</div>

