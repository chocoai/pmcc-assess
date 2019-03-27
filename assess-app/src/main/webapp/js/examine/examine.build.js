/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = [
        AssessUploadKey.BUILDING_FLOOR_PLAN,
        AssessUploadKey.BUILDING_FIGURE_OUTSIDE,
        AssessUploadKey.BUILDING_FLOOR_APPEARANCE_FIGURE
    ];

    buildingCommon.getBuildingId = function () {
        var id = buildingCommon.buildingForm.find('[name=id]').val();
        if (id) {
            return id;
        }
        return 0;
    };

    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingForm.find('[name=buildingNumber]').val();
    };

    buildingCommon.detail = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingByApplyId',
            type: 'get',
            data: {applyId: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        buildingCommon.initForm(result.data);
                    }
                }
            }
        })
    };


    buildingCommon.initForm = function (data) {
        buildingCommon.buildingForm.clearAll();
        buildingCommon.buildingForm.initForm(data);
        //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
        buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.buildingStructureCategory, function (html, data) {
                buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
            });
        });
        buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.propertyCategory, function (html, data) {
                buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_quality, data.constructionQuality, function (html, data) {
            buildingCommon.buildingForm.find('[name=constructionQuality]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_appearance_style, data.appearanceStyle, function (html, data) {
            buildingCommon.buildingForm.find('[name=appearanceStyle]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_appearance_new_and_old, data.appearanceNewAndOld, function (html, data) {
            buildingCommon.buildingForm.find('[name=appearanceNewAndOld]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, data.propertyType, function (html, data) {
            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, data.buildingStructureType, function (html, data) {
            buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.completed_time_type, data.completedTimeType, function (html, data) {
            buildingCommon.buildingForm.find('select.completedTimeType').empty().html(html).trigger('change');
        });

        //建筑使用寿命 ---
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_building_residence_data, data.residenceUseYear, function (html, data) {
            buildingCommon.buildingForm.find('select.residenceUseYear').empty().html(html).trigger('change');
        }, false);
        $.ajax({
            url: getContextPath() + '/architecture/dataBuildingNewRateList',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        retHtml += '<option key="' + item.buildingStructure + '" title="' + item.buildingStructure + '" value="' + item.id + '"' ;
                        if (item.id == data.industryUseYear) {
                            retHtml += 'selected="selected"'
                        }
                        retHtml += '>' + item.buildingStructure + '</option>' ;
                    });
                    buildingCommon.buildingForm.find('select.industryUseYear').empty().html(retHtml).trigger('change');
                }
            }
        });
        //建筑使用寿命 ---

        //初始化上传控件
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileUpload(item);
        });

        //附件显示
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileShow(item);
        });
        buildingModelView.prototype.viewInit();
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
                tableName: AssessDBKey.BasicBuilding,
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
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        })
    };

    buildingCommon.onSelect = function (id) {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicBuilding/appWriteBuilding',
            data: {
                applyId: basicCommon.getApplyId(),
                caseBuildingId: id
            },
            type: 'post',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    basicCommon.update({caseBuildingId: id, id: basicCommon.getApplyId()}, function () {
                        basicCommon.basicApplyForm.find("input[name='caseBuildingId']").val(id);
                        buildingCommon.detail(basicCommon.getApplyId());
                    });
                } else {
                    console.log(result.errmsg);
                    Alert("转移失败!");
                }
            }
        })
    };

    buildingCommon.autocompleteStart = function () {
        buildingCommon.buildingForm.find('input[name=property]').apProperty();
        buildingCommon.buildingForm.find('input[name=builder]').apBuilder();
        $("#txt_building_search").apBuilding({
            caseEstateId: function () {
                return basicCommon.getCaseEstateId();
            },
            onSelect: function (id, name) {
                buildingCommon.onSelect(id);
            }
        });
    };


    window.buildingCommon = buildingCommon;
})(jQuery);