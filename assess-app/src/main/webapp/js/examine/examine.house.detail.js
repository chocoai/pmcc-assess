/**
 * Created by kings on 2019-5-30.
 */
var houseHuxingPrice = function () {
};
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
        var tenementType = houseCommon.houseHuxingForm.find('label[name="tenementType"]').text();
        if (tenementType) {
            AssessCommon.ajaxServerMethod({tenementType: tenementType,num:2}, "/basicTenementType/getTenementTypeValue", "get", function (value) {
                temp = eval(value) ;
                $.each(temp, function (i, item) {
                    cols.push(item);
                }) ;
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-info tooltips" data-placement="top" data-original-title="装修" onclick="houseHuxingPrice.prototype.showDecorateListModal(' + row.id + ')"><i class="fa fa-store-alt"></i></button>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseHuxingPrice.prototype.config().table, getContextPath() + "/basicHouseHuxingPrice/getBootstrapTableVo", cols, {
                    houseId: houseCommon.getHouseId(),
                    approval: true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            });
        }
    },

    //显示装修列表弹窗
    showDecorateListModal: function (huxingPriceId) {
        this.loadDecorateList(huxingPriceId);
        $('#huxingPriceDecorateListModal').modal();
    },

    //加载装修信息
    loadDecorateList: function (huxingPriceId) {
        var cols = commonColumn.houseRoomDecorateColumn();
        $("#housePriceDecorateList").bootstrapTable('destroy');
        TableInit("housePriceDecorateList", getContextPath() + "/basicHouseRoomDecorate/getBootstrapTableVo", cols, {
            huxingPriceId: huxingPriceId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
};

var houseRoom = function () {
};
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
        data.fileIDName = "house_room_file";
        return data;
    },
    loadDataDicList: function () {
        var cols = commonColumn.houseRoomColumn();
        var temp = [];
        var tenementType = houseCommon.houseHuxingForm.find('label[name="tenementType"]').text();
        if (houseCommon.houseHuxingForm.find('label[name="spatialDistributionName"]').text() == "多层") {
            cols.push({field: 'currentFloor', title: '所在楼层'});
        }
        if (tenementType) {
            AssessCommon.ajaxServerMethod({tenementType: tenementType,num:2}, "/basicTenementType/getTenementTypeValue", "get", function (value) {
                temp = eval(value) ;
                $.each(temp, function (i, item) {
                    cols.push(item);
                }) ;
                cols.push({field: 'fileViewName', title: '附件'});
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, getContextPath() + "/basicHouseRoom/getBootstrapTableVo", cols, {
                    houseId: houseCommon.getHouseId(),
                    approval: true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            });
        }
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
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    },
    init: function (item) {
        $("#" + houseRoom.prototype.config().frm).clearAll();
        var tenementType = houseCommon.houseHuxingForm.find('label[name="tenementType"]').text();
        if (houseRoom.prototype.isNotBlank(tenementType)) {
            $("#" + houseRoom.prototype.config().frm).find(".form-group").attr("style", "display:none");
            $("#" + houseRoom.prototype.config().frm).find(".common").show();

            if (tenementType) {
                AssessCommon.ajaxServerMethod({tenementType: tenementType,num:4}, "/basicTenementType/getTenementTypeValue", "get", function (value) {
                    $("#" + houseRoom.prototype.config().frm).find("."+value +".base").show();
                });
            }
        }
        houseRoom.prototype.showField(item.houseShape);
        $("#" + houseRoom.prototype.config().frm).initForm(item);
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_adjacent_position, item.adjacentPosition, function (html, data) {
            $("#" + houseRoom.prototype.config().frm).find("select.adjacentPosition").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_orientation, item.orientation, function (html, data) {
            $("#" + houseRoom.prototype.config().frm).find("select.orientation").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_standard_measure, item.standardMeasure, function (html, data) {
            $("#" + houseRoom.prototype.config().frm).find("select.standardMeasure").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_room_storage_request, item.storageRequest, function (html, data) {
            $("#" + houseRoom.prototype.config().frm).find("select.storageRequest").empty().html(html).trigger('change');
        });

        FileUtils.getFileShows({
            target: houseRoom.prototype.config().fileIDName,
            formData: {
                fieldsName: houseRoom.prototype.config().fileIDName,
                tableName: AssessDBKey.BasicHouseRoom,
                tableId: houseRoom.prototype.isNotBlank(item.id) ? item.id : "0"
            },
            deleteFlag: false
        })
    },
    showField:function(houseShape) {
        var tenementType = houseCommon.houseHuxingForm.find('label[name="tenementType"]').text();
        if (houseRoom.prototype.isNotBlank(tenementType) && houseRoom.prototype.isNotBlank(tenementType)) {
            if (tenementType == '住宅' || tenementType == '办公') {
                if (houseShape == '规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".residence.rule").show();
                } else if (houseShape == '不规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".residence.rule").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".residence.unruled").show();
                }
            }
            if (tenementType == '商铺' || tenementType == '商场'|| tenementType == '车位') {
                if (houseShape == '规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".store.unruled").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".store.rule").show();
                } else if (houseShape == '不规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".store.rule").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".store.unruled").show();
                }
            }
            if (tenementType == '餐饮酒店') {
                if (houseShape == '规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").show();
                } else if (houseShape == '不规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".hotel.rule").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".hotel.unruled").show();
                }
            }
            if (tenementType == '生产') {
                if (houseShape == '规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".production.unruled").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".production.rule").show();
                } else if (houseShape == '不规则') {
                    $("#" + houseRoom.prototype.config().frm).find(".production.rule").hide();
                    $("#" + houseRoom.prototype.config().frm).find(".production.unruled").show();
                }
            }
        }
    }
}

var houseFaceStreet = function () {
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
        TableInit(houseFaceStreet.prototype.config().table, getContextPath() + "/basicHouseFaceStreet/getBootstrapTableVo", cols, {
            houseId: houseCommon.getHouseId(),
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

var houseCorollaryEquipment = function () {
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
        TableInit(houseCorollaryEquipment.prototype.config().table, getContextPath() + "/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
            houseId: houseCommon.getHouseId(),
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

var houseIntelligent = function () {
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
        TableInit(houseIntelligent.prototype.config().table, getContextPath() + "/basicHouseIntelligent/getBootstrapTableVo", cols, {
            houseId: houseCommon.getHouseId(),
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

var houseWater = function () {
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
        TableInit(houseWater.prototype.config().table, getContextPath() + "/basicHouseWater/getBootstrapTableVo", cols, {
            houseId: houseCommon.getHouseId(),
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

var houseRoomDecorate = function () {
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
        $("#" + houseRoomDecorate.prototype.config().table).bootstrapTable('destroy');
        TableInit(houseRoomDecorate.prototype.config().table, getContextPath() + "/basicHouseRoomDecorate/getBootstrapTableVo", cols, {
            houseId: houseCommon.getHouseId(),
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
        houseId: houseCommon.getHouseId(),
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

var houseNewWind = function () {
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
        TableInit(houseNewWind.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: houseNewWind.prototype.config().type,
            houseId: houseCommon.getHouseId(),
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

var houseAirConditioner = function () {
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
        TableInit(houseAirConditioner.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: houseAirConditioner.prototype.config().type,
            houseId: houseCommon.getHouseId(),
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

var houseHeating = function () {
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
        TableInit(houseHeating.prototype.config().table, getContextPath() + "/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: houseHeating.prototype.config().type,
            houseId: houseCommon.getHouseId(),
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
                        var tabHtml = '<li class="nav-item"><a href="#tab_content_' + item.type + '" role="tab" data-toggle="tab" aria-expanded="true" class="nav-link">' + item.typeName + '</a></li>';
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

$(function () {
    //绑定事件
    $('#' + houseHuxingPrice.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseHuxingPrice.prototype.loadDataDicList();
    });

    //绑定事件
    $('#' + houseRoom.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseRoom.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseFaceStreet.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseFaceStreet.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseCorollaryEquipment.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseCorollaryEquipment.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseIntelligent.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseIntelligent.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseWater.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseRoomDecorate.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseRoomDecorate.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseWaterDrain.config.table).closest('.full-height').find('.card-header').bind('click', function () {
        houseWaterDrain.loadDataDicList();
    })

    //绑定事件
    $('#' + houseNewWind.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseNewWind.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseAirConditioner.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseAirConditioner.prototype.loadDataDicList();
    })

    //绑定事件
    $('#' + houseHeating.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        houseHeating.prototype.loadDataDicList();
    })
});
