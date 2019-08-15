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
        return unitCommon.unitForm.find('[name=id]').val()!=null?unitCommon.unitForm.find('[name=id]').val():unitCommon.tableId;
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

    //单元明细
    unitCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    unitCommon.unitForm.initForm(result.data,function () {
                        //附件显示
                        $.each(unitCommon.unitFileControlIdArray, function (i, item) {
                            unitCommon.fileShow(item);
                        })
                    });
                }
            }
        })
    }

    unitCommon.initForm = function (data) {
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

    //项目中引用数据
    unitCommon.getDataFromProject = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getDataFromProject',
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

    //项目中引用楼盘(批量)
    unitCommon.batchGetDataFromProject = function (applyId, tableId, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/batchGetDataFromProject',
            type: 'get',
            data: {
                applyId: applyId,
                tableId: tableId
            },
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
            area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                unitCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
                unitCommon.loadMarkerList2(tableId);
            }
        });
    };

    //添加标注（通过tableId）
    unitCommon.addMarker2 = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTaggingByTableId',
            data: {
                tableId: unitCommon.tableId,
                type: 'unit',
                lng: lng,
                lat: lat,
                name: unitCommon.getUnitNumber()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    unitCommon.loadMarkerList2(unitCommon.tableId);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    //加载标注（通过tableId）
    unitCommon.loadMarkerList2 = function (tableId) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
            data: {
                tableId: tableId,
                type: 'unit'
            },
            success: function (result) {
                if (result.ret && unitCommon.estateMapiframe) {//标注成功后，刷新地图上的标注
                    unitCommon.estateMapiframe.loadMarkerList(result.data);
                }
            }
        })
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

    //楼栋标注
    unitCommon.mapMarker = function (readonly) {
        var contentUrl = getContextPath() + '/map/mapMarkerEstate?estateName=' + estateCommon.getEstateName();
        if (readonly != true) {
            contentUrl += '&click=unitCommon.addMarker';
        }
        layer.open({
            type: 2,
            title: '单元标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                unitCommon.unitMapiframe = window[layero.find('iframe')[0]['name']];
                unitCommon.loadMarkerList();
            }
        });
    };

    //添加标注
    unitCommon.addMarker = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'unit',
                lng: lng,
                lat: lat,
                name: unitCommon.getUnitNumber()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    unitCommon.loadMarkerList();
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    //加载标注
    unitCommon.loadMarkerList = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'unit'
            },
            success: function (result) {
                if (result.ret && unitCommon.unitMapiframe) {//标注成功后，刷新地图上的标注
                    unitCommon.unitMapiframe.loadMarkerList(result.data);
                }
            }
        })
    };

    unitCommon.onSelect = function (id) {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicUnit/appWriteUnit',
            data: {
                applyId: basicCommon.getApplyId(),
                caseUnitId: id
            },
            type: 'post',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    basicCommon.update({caseUnitId: id, id: basicCommon.getApplyId()}, function () {
                        unitCommon.detail(basicCommon.getApplyId(), function (data) {
                            unitCommon.initForm(data);
                        });
                        basicCommon.basicApplyForm.find("input[name='caseUnitId']").val(id);
                    });
                } else {
                    console.log(result.errmsg);
                    Alert("转移失败!");
                }
            }
        })
    };

    unitCommon.autocompleteStart = function () {
        $("#txt_Unit_search").apUnit({
            caseBuildingId: function () {
                return basicCommon.getcaseBuildingId();
            },
            onSelect: function (id, name) {
                unitCommon.onSelect(id);
            }
        });
    };


    window.unitCommon = unitCommon;
})(jQuery);