/**
 * Created by kings on 2019-5-30.
 */
var detailHouseId = $('#basicHouseFrm').find('[name=id]').val();
detailHouseId=detailHouseId?detailHouseId:$('#frm_house').find('[name=id]').val();


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
            TableInit(houseRoom.prototype.config().table, getContextPath()+"/basicHouseRoom/getBootstrapTableVo", cols, {
                houseId: detailHouseId,
                approval: true
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
            TableInit(houseRoom.prototype.config().tableSubclass, getContextPath()+"/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
                roomId: id,
                approval: true
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
            TableInit(houseFaceStreet.prototype.config().table, getContextPath()+"/basicHouseFaceStreet/getBootstrapTableVo", cols, {
                houseId: detailHouseId,
                approval: true
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
            $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseCorollaryEquipment.prototype.config().table, getContextPath()+"/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
                houseId: detailHouseId,
                approval: true
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
            TableInit(houseIntelligent.prototype.config().table, getContextPath()+"/basicHouseIntelligent/getBootstrapTableVo", cols, {
                houseId: detailHouseId,
                approval: true
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
            TableInit(houseWater.prototype.config().table, getContextPath()+"/basicHouseWater/getBootstrapTableVo", cols, {
                houseId: detailHouseId,
                approval: true
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
        houseId: detailHouseId,
        approval: true
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
            TableInit(houseNewWind.prototype.config().table, getContextPath()+"/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseNewWind.prototype.config().type,
                houseId: detailHouseId,
                approval: true
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
            TableInit(houseAirConditioner.prototype.config().table, getContextPath()+"/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseAirConditioner.prototype.config().type,
                houseId: detailHouseId,
                approval: true
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
            TableInit(houseHeating.prototype.config().table, getContextPath()+"/basicHouseEquipment/getBootstrapTableVo", cols, {
                type: houseHeating.prototype.config().type,
                houseId: detailHouseId,
                approval: true
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


var damagedDegree = {};

//加载完损度数据列表
damagedDegree.loadDamagedDegreeList = function () {
    Loading.progressShow();
    $.ajax({
        url: getContextPath() + '/basicHouseDamagedDegree/getDamagedDegreeList',
        type: 'get',
        data: {
            houseId: houseCommon.getHouseId()
        },
        success: function (result) {
            Loading.progressHide();
            if (result.ret && result.data) {
                $("#damagedDegreeTab,#damagedDegreeTabContent").empty();
                var tabContentHtml = '';
                var groupArray = [];
                $.each(result.data, function (i, item) { //循环数据分组
                    if ($.inArray(item.type, groupArray) < 0) {
                        groupArray.push(item.type);
                        var tabHtml = '<li role="presentation"><a href="#tab_content_' + item.type + '" role="tab" data-toggle="tab" aria-expanded="true">' + item.typeName + '</a></li>';
                        $("#damagedDegreeTab").append(tabHtml);
                    }
                })

                $.each(groupArray, function (i, group) {//循环分组
                    var contentHtml = $('#damagedDegreeTabContentHtml').html().replace(/{type}/g, group);
                    $("#damagedDegreeTabContent").append(contentHtml);
                    var tbodyContentHtml = '';
                    $.each(result.data, function (i, item) {
                        if (item.type == group) {
                            var trHtml = $("#damagedDegreeTabTrHtml").html();
                            trHtml = trHtml.replace(/{id}/g, item.id).replace(/{categoryName}/g, AssessCommon.toString(item.categoryName));
                            trHtml = trHtml.replace(/{entityConditionName}/g, AssessCommon.toString(item.entityConditionName));
                            trHtml = trHtml.replace(/{entityConditionContent}/g, AssessCommon.toString(item.entityConditionContent));
                            trHtml = trHtml.replace(/{isShow}/g, item.hasChildren ? '' : 'style="display: none"');
                            trHtml = trHtml.replace(/{standardScore}/g, AssessCommon.toString(item.standardScore));
                            trHtml = trHtml.replace(/{score}/g, AssessCommon.toString(item.score));
                            $("#damagedDegreeTabContent").find('.tab-pane:last').find('tbody').append(trHtml);
                        }
                    })
                })
                $('#damagedDegreeTab a').eq(0).tab('show');
            }
        }
    })
};

//显示modal
damagedDegree.damagedDegreeDetailModalShow = function (damagedDegreeId) {
    damagedDegree.loadDamagedDegreeDetailList(damagedDegreeId);
    $("#damagedDegreeDetailListModal").modal();
};

//加载明细项数据列表
damagedDegree.loadDamagedDegreeDetailList = function (damagedDegreeId) {
    var cols = [];
    cols.push({field: 'typeName', title: '类型', width: '15%'});
    cols.push({field: 'entityConditionName', title: '实体状况', width: '15%'});
    cols.push({field: 'entityConditionContent', title: '状况内容', width: '60%'});
    $("#damagedDegreeDetailList").bootstrapTable('destroy');
    TableInit("damagedDegreeDetailList", getContextPath() + "/basicHouseDamagedDegree/getDamagedDegreeDetailList", cols, {
        damagedDegreeId: damagedDegreeId
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};
