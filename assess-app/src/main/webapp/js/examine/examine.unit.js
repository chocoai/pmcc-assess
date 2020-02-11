/**
 *
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#basicUnitFrm');
    unitCommon.unitMapiframe = undefined;
    unitCommon.applyId = undefined;
    unitCommon.tableId = undefined;
    unitCommon.tableName = AssessDBKey.BasicUnit;

    //附件上传控件id数组
    unitCommon.unitFileControlIdArray = [
        AssessUploadKey.UNIT_APPEARANCE
    ];

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val() != null ? unitCommon.unitForm.find('[name=id]').val() : unitCommon.tableId;
    };

    unitCommon.getUnitNumber = function () {
        return unitCommon.unitForm.find('[name=unitNumber]').val();
    };


    //单元明细
    unitCommon.detail = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    unitCommon.initForm = function (data) {
        try {
            data.applyBatchId = unitCommon.unitForm.find("input[name='applyBatchId']").val();
        } catch (e) {
        }
        unitCommon.unitForm.clearAll();
        unitCommon.unitForm.initForm(data);
        //初始化上传控件
        $.each(unitCommon.unitFileControlIdArray, function (i, item) {
            unitCommon.fileUpload(item);
            unitCommon.fileShow(item);
        });
    };

    //添加单元
    unitCommon.add = function (_this, callback) {
        var unitNumber = $(_this).closest('form').find('[name=unitNumber]').val();
        if (!unitNumber) {
            toastr.info('请填写单元编号！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicUnit/addUnit',
            data: {
                unitNumber: unitNumber
            },
            success: function (result) {
                if (result.ret) {
                    unitCommon.showUnitView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    };

    //升级单元
    unitCommon.upgrade = function (_this, callback) {
        var caseUnitId = $(_this).closest('form').find("input[name='caseUnitId']").val();
        var unitPartInMode = $(_this).attr('data-mode');
        if (!caseUnitId) {
            toastr.info('请选择系统中已存在的单元信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicUnit/appWriteUnit',
            data: {
                caseUnitId: caseUnitId,
                unitPartInMode: unitPartInMode
            },
            success: function (result) {
                if (result.ret) {
                    unitCommon.showUnitView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    };

    //单元初始化by applyId
    unitCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    unitCommon.showUnitView(result.data);
                    unitCommon.applyId = applyId;
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //单元初始化by id
    unitCommon.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    unitCommon.showUnitView(result.data);
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };


    //楼盘标注（通过tableId）
    unitCommon.mapMarker2 = function (readonly, tableId) {
        unitCommon.tableId = tableId;
        var contentUrl = getContextPath() + '/map/mapMarkerEstateByTableId?tableId=' + unitCommon.tableId + '&tableName=' + unitCommon.tableName;
        if (readonly != true) {
            contentUrl += '&click=unitCommon.addMarker2';
        }
        layer.open({
            type: 2,
            title: '楼盘标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                unitCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
                unitCommon.loadMarkerList2(tableId);
            }
        });
    };

    //添加标注（通过tableId）
    unitCommon.addMarker2 = function (lng, lat) {
        examineCommon.addBasicEstateTagging({
            tableId: unitCommon.tableId,
            type: 'unit',
            lng: lng,
            lat: lat,
            name: unitCommon.getUnitNumber()
        },function () {
            unitCommon.loadMarkerList2(unitCommon.tableId);
        }) ;
    };

    //加载标注（通过tableId）
    unitCommon.loadMarkerList2 = function (tableId) {
        examineCommon.getApplyBatchEstateTaggingsByTableId({
            tableId: tableId,
            type: 'unit'
        },function (data) {
            if (unitCommon.estateMapiframe){
                //标注成功后，刷新地图上的标注
                unitCommon.estateMapiframe.loadMarkerList(data);
            }
        });
    };

    unitCommon.mapNewMarker = function (_this, readonly) {
        examineCommon.getBasicApplyBatchDetailList({
            tableId: unitCommon.getUnitId(),
            applyBatchId: unitCommon.unitForm.find("input[name='applyBatchId']").val()
        }, function (itemA) {
            if (itemA.length == 0) {
                return false;
            }
            examineCommon.getBasicApplyBatchDetailList({id: itemA[0].pid}, function (itemB) {
                if (itemB.length == 0) {
                    return false;
                }
                ///-----------------------||----------------------------///
                var tempObj = {type: "building", tableId: itemB[0].tableId};
                toolMapHandleFun.getToolMapHandleListByExample(tempObj, function (result) {
                    var objArray = [];
                    if (result.length != 0) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i].type != tempObj.type) {
                                continue;
                            }
                            if (result[i].tableId != tempObj.tableId) {
                                continue;
                            }
                            objArray.push(result[i]);
                        }
                    }
                    if (!readonly){
                        if (objArray.length == 0) {
                            Alert("请先标注楼栋");
                            return false;
                        }
                    }
                    var data = {};
                    data.drawState = 'marker';
                    data.id = $(_this).closest('.form-group').find("[name='mapId']").val();
                    data.readonly = readonly;
                    data.multiple = false;//不允许多个标记
                    data.type = "unit";//兼容以前数据
                    data.tableId = unitCommon.getUnitId();
                    data.callback = function (item, result) {
                        $(_this).closest('.form-group').find("[name='mapId']").val(item.id);
                    };
                    if (objArray.length != 0) {
                        data.center = {lng: objArray[0].lng, lat: objArray[0].lat};
                    }
                    toolMapHandleFun.loadMap(data);
                });
            });
        });
    };

    unitCommon.showUnitView = function (data) {
        unitCommon.initForm(data);
    };

    //附件上传
    unitCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicUnit,
                tableId: unitCommon.getUnitId()
            },
            deleteFlag: true,
        });
    };

    //附件显示
    unitCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicUnit,
                tableId: unitCommon.getUnitId()
            },
            deleteFlag: true
        })
    };

    //单元标注
    unitCommon.mapMarker = function (readonly) {
        examineCommon.getBasicApplyBatchDetailList({
            tableId: unitCommon.getUnitId(),
            applyBatchId: unitCommon.unitForm.find("input[name='applyBatchId']").val()
        }, function (itemA) {
            if (itemA.length == 0) {
                return false;
            }
            examineCommon.getBasicApplyBatchDetailList({id: itemA[0].pid}, function (itemB) {
                if (itemB.length == 0) {
                    return false;
                }
                var select = {tableId:itemB[0].tableId,type:"building"} ;
                examineCommon.getApplyBatchEstateTaggingsByTableId(select,function (data) {
                    if (data.length == 0){
                        Alert("请先标注楼栋");
                        return false;
                    }
                    var item = data[0] ;
                    var params = {estateName:item.name,lng:item.lng , lat:item.lat} ;
                    var contentUrl = getContextPath() + '/map/mapMarkerEstate?' + examineCommon.parseParam(params);
                    if (readonly != true) {
                        contentUrl += '&click=unitCommon.addMarker';
                    }
                    layer.open({
                        type: 2,
                        title: '单元标注',
                        shadeClose: true,
                        shade: true,
                        maxmin: true, //开启最大化最小化按钮
                        area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
                        content: contentUrl,
                        success: function (layero) {
                            unitCommon.unitMapiframe = window[layero.find('iframe')[0]['name']];
                            unitCommon.loadMarkerList();
                        }
                    });
                }) ;
            });
        });
    };

    //添加标注
    unitCommon.addMarker = function (lng, lat) {
        examineCommon.addBasicEstateTagging({
            tableId: unitCommon.getUnitId(),
            type: 'unit',
            lng: lng,
            lat: lat,
            name: unitCommon.getUnitNumber()
        },function () {
            unitCommon.loadMarkerList();
        }) ;
    };

    //加载标注
    unitCommon.loadMarkerList = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
            data: {
                tableId: unitCommon.getUnitId(),
                type: 'unit'
            },
            success: function (result) {
                if (result.ret && unitCommon.unitMapiframe) {//标注成功后，刷新地图上的标注
                    unitCommon.unitMapiframe.loadMarkerList(result.data);
                }
            }
        })
    };

    window.unitCommon = unitCommon;
})(jQuery);