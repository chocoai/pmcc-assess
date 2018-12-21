/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingMainForm = $('#basicBuildingMainFrm');
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    buildingCommon.buildingMapiframe = undefined;
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = ['building_floor_plan', 'building_figure_outside', 'building_floor_Appearance_figure'];

    buildingCommon.getBuildingId = function () {
        return buildingCommon.buildingForm.find('[name=id]').val();
    }
    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingMainForm.find('[name=buildingNumber]').val();
    }

    //添加楼栋
    buildingCommon.add = function (_this, callback) {
        var buildingNumber = $(_this).closest('form').find('[name=buildingNumber]').val();
        if (!buildingNumber) {
            toastr.info('请填写楼栋编号！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicBuilding/addBuildingMainAndBuilding',
            data: {
                buildingNumber: buildingNumber
            },
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingView(buildings[0].id);
                    }
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //升级楼栋
    buildingCommon.upgrade = function (_this, callback) {
        var caseBuildingMainId = $(_this).closest('form').find("input[name='caseBuildingMainId']").val();
        var buildingPartInMode = $(_this).closest('form').find("input[name='buildingPartInMode']").val();
        if (!caseBuildingMainId) {
            toastr.info('请选择系统中已存在的楼栋信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicBuilding/appWriteBuilding',
            data: {
                caseMainBuildingId: caseBuildingMainId,
                buildingPartInMode: buildingPartInMode
            },
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingView(buildings[0].id);
                    }
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //楼栋初始化by applyId
    buildingCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingMainByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingView(buildings[0].id);
                    }
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼栋明细
    buildingCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingMainByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingDetail(buildings[0].id);
                    }
                }
            }
        })
    }

    //获取楼栋下所有部分
    buildingCommon.getBuildingList = function (mainId) {
        var buildings = undefined;
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingListByMainId',
            type: 'get',
            async: false,
            data: {basicBuildingMainId: mainId},
            success: function (result) {
                if (result.ret) {
                    buildings = result.data;
                }
            }
        })
        return buildings;
    }

    //显示楼栋对应部分信息
    buildingCommon.showBuildingView = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            async: false,
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingForm.initForm(result.data, function () {
                        //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
                        buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
                            AssessCommon.loadDataDicByPid($(this).val(), result.data.buildingStructureCategory, function (html, data) {
                                buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
                            });
                            result.data.buildingStructureCategory = null;
                        });
                        buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
                            AssessCommon.loadDataDicByPid($(this).val(), result.data.propertyCategory, function (html, data) {
                                buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
                            });
                            result.data.propertyCategory = null;
                        });
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, result.data.propertyType, function (html, data) {
                            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
                        });
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, result.data.buildingStructureType, function (html, data) {
                            buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
                        });

                        //初始化上传控件
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileUpload(item);
                        })

                        //附件显示
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileShow(item);
                        })
                        buildingModelView.prototype.viewInit(); //加载从表数据
                    });
                }
            }
        })
    }

    //显示楼栋对应部分信息
    buildingCommon.showBuildingDetail = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            async: false,
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingForm.initForm(result.data, function () {
                        //附件显示
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileShow(item);
                        })
                        buildingModelDetail.prototype.viewInit(); //加载从表数据
                    });
                }
            }
        })
    }

    //附件上传
    buildingCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.buildingForm.find('[name=id]').val()
            },
            deleteFlag: true
        });
    }

    //附件显示
    buildingCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.buildingForm.find('[name=id]').val()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    //楼栋标注
    buildingCommon.mapMarker = function (readonly) {
        var contentUrl = getContextPath() + '/map/mapMarkerEstate?estateName=' + estateCommon.getEstateName();
        if (readonly != true) {
            contentUrl += '&click=buildingCommon.addMarker';
        }
        layer.open({
            type: 2,
            title: '楼盘标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: contentUrl,
            success: function (layero) {
                buildingCommon.buildingMapiframe = window[layero.find('iframe')[0]['name']];
                buildingCommon.loadMarkerList();
            }
        });
    }

    //添加标注
    buildingCommon.addMarker = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'building',
                lng: lng,
                lat: lat,
                name: buildingCommon.getBuildingNumber()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    buildingCommon.loadMarkerList();
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载标注
    buildingCommon.loadMarkerList = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'building'
            },
            success: function (result) {
                if (result.ret && buildingCommon.buildingMapiframe) {//标注成功后，刷新地图上的标注
                    buildingCommon.buildingMapiframe.loadMarkerList(result.data);
                }
            }
        })
    }

    window.buildingCommon = buildingCommon;
})(jQuery);