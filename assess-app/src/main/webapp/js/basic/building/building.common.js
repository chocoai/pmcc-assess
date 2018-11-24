/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingMainForm = $('#basicBuildingMainFrm');
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = ['building_floor_plan', 'building_figure_outside', 'building_floor_Appearance_figure'];

    //添加楼栋
    buildingCommon.add = function ($form) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/addBuildingMainAndBuilding',
            data: {
                buildingNumber: $form.find('[name=buildingNumber]').val()
            },
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingView(buildings[0].id);
                    }
                }
            }
        })
    }

    //编辑楼栋
    buildingCommon.edit = function ($form) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/appWriteBuilding',
            data: {caseMainBuildingId: $form.find("input[name='caseBuildingMainId']").val()},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingMainForm.initForm(result.data);
                    var buildings = buildingCommon.getBuildingList(result.data.id);
                    if (buildings && buildings.length > 0) {
                        buildingCommon.showBuildingView(buildings[0].id);
                    }
                }
            }
        })
    }

    //楼栋初始化by applyId
    buildingCommon.init = function (applyId) {
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
                    buildingCommon.buildingMainForm.initLabel(result.data);
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
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, result.data.buildingCategory, function (html, data) {
                            buildingCommon.buildingForm.find('select.buildingCategory').empty().html(html).trigger('change');
                        });
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, result.data.buildingStructure, function (html, data) {
                            buildingCommon.buildingForm.find('select.buildingStructure').empty().html(html).trigger('change');
                            buildingCommon.buildingForm.find('select.buildingStructure').val(result.data.buildingStructure).trigger("change");
                        });
                        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, result.data.propertyType, function (html, data) {
                            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
                        });
                        buildingCommon.buildingForm.find("select.buildingStructure").change(function () {
                            var id = buildingCommon.buildingForm.find("select.buildingStructure").val();
                            if (basicIndexCommon.isNotBlank(id)) {
                                AssessCommon.loadDataDicByPid(id, result.data.buildingStructureLower, function (html, data) {
                                    buildingCommon.buildingForm.find("select.buildingStructureLower").empty().html(html);
                                });
                            }
                        });
                        AssessCommon.loadDataDicByPid(result.data.buildingStructure, result.data.buildingStructureLower, function (html, data) {
                            buildingCommon.buildingForm.find("select.buildingStructureLower").empty().html(html).trigger('change');
                        });
                        //初始化上传控件
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileUpload(item);
                        })
                        //------------------------以上部分可只初始化一次

                        //附件显示
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileShow(item);
                        })

                        //数据特殊处理
                        buildingCommon.buildingForm.find('[name=openTime]').val(formatDate(result.data.openTime));
                        buildingCommon.buildingForm.find('[name=roomTime]').val(formatDate(result.data.roomTime));
                        buildingCommon.buildingForm.find('[name=beCompletedTime]').val(formatDate(result.data.beCompletedTime));

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
                    buildingCommon.buildingForm.initLabel(result.data);
                    buildingCommon.buildingForm.find('[data-name=openTime]').text(formatDate(result.data.openTime));
                    buildingCommon.buildingForm.find('[data-name=roomTime]').text(formatDate(result.data.roomTime));
                    buildingCommon.buildingForm.find('[data-name=beCompletedTime]').text(formatDate(result.data.beCompletedTime));
                    buildingCommon.buildingForm.initForm(result.data,function () {
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

    window.buildingCommon = buildingCommon;
})(jQuery);