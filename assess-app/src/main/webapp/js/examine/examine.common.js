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
        // var landLevelContent = [];
        // if (data.landFactorTotalScore) {
        //     var landFactorTotalScore = 0;
        //     data.landFactorTotalScore.split(",").forEach(function (value, index) {
        //         landFactorTotalScore += Number(value);
        //     });
        //     data.landFactorTotalScore = landFactorTotalScore;
        // }
        // estateCommon.estateLandStateForm.find("input[name='landLevelContent']").each(function (i, n) {
        //     var group = $(n).closest(".group");
        //     var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
        //     var obj = JSON.parse($(n).val());
        //     var dataObject = [];
        //     obj.forEach(function (value, index) {
        //         if (value.id == dataLandLevelAchievement) {
        //             value.modelStr = "update";
        //         }
        //         dataObject.push(value);
        //     });
        //     landLevelContent.push(dataObject);
        // });
        // if (landLevelContent.length >= 1) {
        //     data.landLevelContent = JSON.stringify(landLevelContent);
        // }
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
    if (window.damagedDegree) {
        item.basicDamagedDegree = damagedDegree.getFormData();
    }
    return item;
}


//开发商选择
examineCommon.developerSelect = function (this_) {
    assessDeveloper.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};

//土地级别选择
examineCommon.landLevelSelect = function (this_) {
    var formGroup = $(this_).closest('.form-group');
    assessLandLevelTool.select({
        province: estateCommon.estateForm.find('[name=province]').val(),
        city: estateCommon.estateForm.find('[name=city]').val(),
        district: estateCommon.estateForm.find('[name=district]').val(),
        success: function (data) {
            formGroup.find("input[name='landLevel']").val(data.id);
            formGroup.find("input[name='landLevelName']").val(data.name);
        }
    })
};
//物业选择
examineCommon.propertySelect = function (this_) {
    assessProperty.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//建造商选择
examineCommon.builderSelect = function (this_) {
    assessBuilder.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//板块选择
examineCommon.blockSelect = function (this_) {
    var $form = $(this_).closest('form');
    assessBlock.select({
        province: $form.find('[name=province]').val(),
        city: $form.find('[name=city]').val(),
        success: function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
            estateCommon.estateForm.find("#blockDescription").val(row.remark);
        }
    })
};

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

examineCommon.getMarkerAreaInHeight = '80%';
examineCommon.getMarkerAreaInWidth = '80%';

window.examineCommon = examineCommon;
