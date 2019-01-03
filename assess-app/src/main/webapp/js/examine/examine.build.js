/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingMainForm = $('#basicBuildingMainFrm');
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = ['building_floor_plan', 'building_figure_outside', 'building_floor_Appearance_figure'];

    buildingCommon.getBuildingId = function () {
        var id = buildingCommon.buildingForm.find('[name=id]').val();
        if (id){
            return id;
        }
        return 0;
    };

    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingMainForm.find('[name=buildingNumber]').val();
    };

    buildingCommon.detail = function (id,callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingMainByApplyId',
            type: 'get',
            data: {applyId: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };


    buildingCommon.initForm = function (data) {
        buildingCommon.buildingMainForm.clearAll();
        buildingCommon.buildingForm.clearAll();
        buildingCommon.buildingMainForm.initForm(data.main);
        buildingCommon.buildingForm.initForm(data.build);
        //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
        buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.build.buildingStructureCategory, function (html, data) {
                buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
            });
        });
        buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.build.propertyCategory, function (html, data) {
                buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, data.build.propertyType, function (html, data) {
            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, data.build.buildingStructureType, function (html, data) {
            buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
        });

        //初始化上传控件
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileUpload(item);
        });

        //附件显示
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileShow(item);
        });
        buildingModelView.prototype.viewInit() ;
    };

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
    };

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
    };

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
    };


    //附件上传
    buildingCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ExamineBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        });
    };

    //附件显示
    buildingCommon.fileShow = function (fieldsName) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ExamineBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        })
    };


    window.buildingCommon = buildingCommon;
})(jQuery);