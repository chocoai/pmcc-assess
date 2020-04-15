var examineCommon = {};

examineCommon.getFormData = function () {
    var item = {};
    if (window.estateCommon && estateCommon.estateForm.length > 0) {
        item.basicEstate = formSerializeArray(estateCommon.estateForm);
        item.basicEstate.id = estateCommon.estateForm.find("input[name='id']").val();
        item.basicEstate.name = estateCommon.estateForm.find("input[name='name']").val();
    }
    if (window.estateCommon && estateCommon.estateLandStateForm.length > 0) {
        var data = formSerializeArray(estateCommon.estateLandStateForm);
        data.landLevelContent = estateCommon.estateLandStateForm.find("input[name=landLevelContentResult]").val();
        data.landFactorTotalScore = estateCommon.estateLandStateForm.find("input[name=landFactorTotalScoreResult]").val();
        item.basicEstateLandState = data;
    }
    if (window.buildingCommon && buildingCommon.buildingForm.length > 0) {
        item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
        item.basicBuilding.vSpecifications = [];
        buildingCommon.buildingForm.find('.form-group').each(function () {
            var vSpecification = {};
            var specificationName = $(this).find('[name^=specificationName]').val();
            var specificationContent = $(this).find('[name^=specificationContent]').val();
            if (specificationName && specificationContent) {
                vSpecification.specificationName = specificationName;
                vSpecification.specificationContent = specificationContent;
                item.basicBuilding.vSpecifications.push(vSpecification);
            }
        });
    }
    if (window.unitCommon && unitCommon.unitForm.length > 0) {
        item.basicUnit = formSerializeArray(unitCommon.unitForm);
    }
    if (window.houseCommon && houseCommon.houseForm.length > 0) {
        item.basicHouse = formSerializeArray(houseCommon.houseForm);
    }
    if (window.houseCommon && houseCommon.houseTradingForm.length > 0) {
        item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
    }
    if (window.houseCommon && houseCommon.houseHuxingForm.length > 0) {
        item.basicHouseHuxing = formSerializeArray(houseCommon.houseHuxingForm);
    }
    if (window.damagedDegree) {
        item.basicDamagedDegree = damagedDegree.getFormData();
    }
    return item;
}

examineCommon.getBasicApplyBatchDetailList = function (obj, callback) {
    $.ajax({
        url: getContextPath() + '/basicApplyBatch/getBasicApplyBatchDetailList',
        data: obj,
        method: "get",
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            }
        }
    })
};

/**
 * 注意只遍历一个层级
 * @param param
 * @returns {string}
 */
examineCommon.parseParam = function (param) {
    var arr = [];
    var keys = Object.keys(param);
    for (var i = 0; i < keys.length; i++) {
        var key = keys[i];
        var value = param[key];
        if (!value) {
            // continue ;
        }
        var paramStr = key + "=" + value;
        arr.push(paramStr)
    }
    return arr.join("&");
};

examineCommon.getApplyBatchEstateTaggingsByTableId = function (data, callback) {
    $.ajax({
        url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
        data: data,
        method: "post",
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            }
        }
    })
};


examineCommon.addBasicEstateTagging = function (data, callback) {
    $.ajax({
        url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
        data: data,
        method: "post",
        success: function (result) {
            if (result.ret) {//标注成功后，刷新地图上的标注
                if (callback) {
                    callback(result.data);
                }
            } else {
                Alert(result.errmsg);
            }
        }
    })
};

examineCommon.referenceValue = function ($source, $target) {
    if ($source && $target) {
        var sourceValue = $source.val();
        var targetValue = $target.val();
        if (!sourceValue || targetValue) return;
        $target.val(sourceValue);
    }
}


//土地级别选择
examineCommon.landLevelSelect = function (this_) {
    var $form = $(this_).closest('form');
    var formGroup = $(this_).closest('.input-group');
    assessLandLevelTool.select({
        province: $form.find('[name=province]').val(),
        city: $form.find('[name=city]').val(),
        success: function (data) {
            formGroup.find("input[data-name='landLevelName']").val(data.name);
            formGroup.find("input[data-name='landLevel']").val(data.id).trigger('onblur');
            formGroup.find("input[name='landLevel']").val(data.id);
            formGroup.find("input[name='landLevelName']").val(data.name);
        }
    })
};

examineCommon.getMarkerAreaInHeight = '80%';
examineCommon.getMarkerAreaInWidth = '80%';

window.examineCommon = examineCommon;
