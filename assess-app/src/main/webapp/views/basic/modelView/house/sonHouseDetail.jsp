
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房间</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseRoomList">
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>临街（路）状况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseFaceStreetList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryCorollaryEquipment">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房屋配套设备设施信息</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseCorollaryEquipmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryIntelligent">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>电力通讯网络</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryWater">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供水情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryHouseWaterDrain">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>排水情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseWaterDrainList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryNewWind">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>新风情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseNewWindList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryAirConditioner">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>空调情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseAirConditionerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryHeating">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供暖情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<%@include file="/views/basic/modelView/house/damagedDegreeDetail.jsp" %>
<script type="text/javascript">

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
                var cols = commonColumn.houseRoomColumn();
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-gavel fa-white"></i></a>';
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
                var cols = commonColumn.houseRoomDecorateColumn();
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

        //绑定事件
        $('#' + houseRoom.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseRoom.prototype.loadDataDicList();
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
                data.FileID = "positionDiagramFileID" ;// ExamineFileUpLoadTwoFieldEnum
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseCorollaryEquipmentColumn();
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
                var cols = commonColumn.houseFaceStreetColumn();
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
                var cols = commonColumn.houseIntelligentColumn();
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
                var cols = commonColumn.houseWaterColumn();
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

        //绑定事件
        $('#' + houseWater.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseWater.prototype.loadDataDicList();
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
        $("#" + this.config.table).bootstrapTable('destroy');
        TableInit(this.config.table, getContextPath() + "/basicHouseWaterDrain/getBootstrapTableVo", cols, {
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
    };
    (function () {
        //绑定事件
        $('#' + houseWaterDrain.config.table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseWaterDrain.loadDataDicList();
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
                var cols = commonColumn.houseAirConditionerColumn();
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
                var cols = commonColumn.houseNewWindColumn();
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

        //绑定事件
        $('#' + houseNewWind.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseNewWind.prototype.loadDataDicList();
        })
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

