/**
 * 土地的查勘表
 */

(function ($) {
    var purenessLand = {};

    purenessLand.landForm = $("#surveyExaminePurenessLandFrm");

    //附件上传
    purenessLand.fileUpload = function (fieldsName, tableName, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: id == undefined ? "0" : id
            },
            deleteFlag: true
        });
    };

    //附件显示
    purenessLand.fileShow = function (fieldsName, tableName, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: id == undefined ? "0" : id
            },
            deleteFlag: true
        })
    };

    //附件
    purenessLand.getFileId = function (key) {
        var map = new Map;
        map.set("a", "SurveyExaminePurenessLandFileA");
        map.set("b", "SurveyExaminePurenessLandFileB");
        if (key) {
            return map.get(key);
        } else {
            return map;
        }
    };

    //土地级别选择
    purenessLand.landLevelSelect = function (this_) {
        var $form = $(this_).closest('form');
        assessLandLevel.select({
            province: $form.find('[name=province]').val(),
            city: $form.find('[name=city]').val(),
            success: function (data) {
                $(this_).parent().prev().val(data.name);
                $(this_).parent().prev().prev().val(data.id);
            }
        })
    };

    //初始化或者赋值
    purenessLand.initForm = function (data) {
        this.landForm.clearAll();
        this.landForm.initForm(data);
        AssessCommon.initAreaInfo({
            provinceTarget: purenessLand.landForm.find("select[name='province']"),
            cityTarget: purenessLand.landForm.find("select[name='city']"),
            districtTarget: purenessLand.landForm.find("select[name='district']"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.supplyGas, function (html, data) {
            purenessLand.landForm.find('select.supplyGas').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.supplyPower, function (html, data) {
            purenessLand.landForm.find('select.supplyPower').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.supplyWater, function (html, data) {
            purenessLand.landForm.find('select.supplyWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.drainWater, function (html, data) {
            purenessLand.landForm.find('select.drainWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.supplyHeating, function (html, data) {
            purenessLand.landForm.find('select.supplyHeating').empty().html(html).trigger('change');
        }, true);

        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.landUseType, function (html, data) {
            purenessLand.landForm.find('select.landUseType').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadDataDicByKey(AssessDicKey.estatePlaneness, data.planeness, function (html, data) {
            purenessLand.landForm.find('select.planeness').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degree, data.developmentDegree, function (html, data) {
            purenessLand.landForm.find('select.developmentDegree').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateShape_state, data.shapeState, function (html, data) {
            purenessLand.landForm.find('select.shapeState').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateTopographic_terrain, data.topographicTerrain, function (html, data) {
            purenessLand.landForm.find('select.topographicTerrain').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_infrastructureCompleteness, data.infrastructureCompleteness, function (html, data) {
            purenessLand.landForm.find('select.infrastructureCompleteness').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandContaminated, data.contaminated, function (html, data) {
            purenessLand.landForm.find("select[name='contaminated']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandPh, data.ph, function (html, data) {
            purenessLand.landForm.find("select[name='ph']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandFertility, data.fertility, function (html, data) {
            purenessLand.landForm.find("select[name='fertility']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingCapacity, data.bearingCapacity, function (html, data) {
            purenessLand.landForm.find("select[name='bearingCapacity']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingHoldOn, data.holdOn, function (html, data) {
            purenessLand.landForm.find("select[name='holdOn']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePriceConnotation, data.priceConnotation, function (html, data) {
            purenessLand.landForm.find("select.priceConnotation").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.informationType, function (html, data) {
            purenessLand.landForm.find("select.informationType").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.informationCategory, function (html, data) {
            purenessLand.landForm.find("select.informationCategory").empty().html(html).trigger('change');
        });
        //绑定变更事件
        purenessLand.landForm.find("select.landUseType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.landUseCategory, function (html, data) {
                purenessLand.landForm.find('select.landUseCategory').empty().html(html).trigger('change');
            });
        });
        //土地开发程度为熟地时选择几通几平
        purenessLand.landForm.find('select.developmentDegree').off('change').on('change', function () {
            $("#developmentDegreeContentContainer").empty();
            purenessLand.landForm.find("input[name='developmentDegreeRemark']").parent().parent().show();
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.estateDevelopment_degreePrepared_land) {
                AssessCommon.loadDataDicByPid($(this).val(), '', function (html, resultData) {
                    if (resultData) {
                        var resultHtml = '';
                        var array = [];
                        if (data.developmentDegreeContent) {
                            array = data.developmentDegreeContent.split(',');
                        }
                        $.each(resultData, function (i, item) {
                            resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                            if ($.inArray(item.id.toString(), array) > -1) {
                                resultHtml += ' checked="checked" ';
                            }
                            resultHtml += ' id="developmentDegreeContent' + item.id + '" name="developmentDegreeContent" value="' + item.id + '">';
                            resultHtml += '<label for="developmentDegreeContent' + item.id + '">' + item.name + '</label></span>';
                        })
                        $("#developmentDegreeContentContainer").html(resultHtml);
                    }
                });
                purenessLand.landForm.find("input[name='developmentDegreeRemark']").parent().parent().hide();
            }
        });
        if (data.id) {
            purenessLand.fileShow(purenessLand.getFileId("a"), AssessDBKey.SurveyExaminePurenessLand, data.id);
            purenessLand.fileUpload(purenessLand.getFileId("a"), AssessDBKey.SurveyExaminePurenessLand, data.id);
        }else {
            purenessLand.fileShow(purenessLand.getFileId("a"), AssessDBKey.SurveyExaminePurenessLand, "0");
            purenessLand.fileUpload(purenessLand.getFileId("a"), AssessDBKey.SurveyExaminePurenessLand, "0");
        }
    };

    window.purenessLand = purenessLand;
})(jQuery);