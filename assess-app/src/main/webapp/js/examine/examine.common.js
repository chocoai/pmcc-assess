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
    if (window.houseCommon && houseCommon.landCategoryInfoForm.length > 0) {
        item.landCategoryInfo = formSerializeArray(houseCommon.landCategoryInfoForm);
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

examineCommon.convertArray = function (lnglatarr) {
    var arr = [];
    $.each(lnglatarr, function (i, item) {
        arr.push({lng: item[0], lat: item[1]});
    });
    return arr;
};

/**
 * 获取区域中心
 * @param lnglatarr
 * @returns {{lng: number, lat: number}}
 */
examineCommon.getTheAreaCenter = function (lnglatarr) {
    lnglatarr = this.convertArray(lnglatarr);
    var total = lnglatarr.length;
    var X = 0, Y = 0, Z = 0;
    $.each(lnglatarr, function (index, lnglat) {
        var lng = lnglat.lng * Math.PI / 180;
        var lat = lnglat.lat * Math.PI / 180;
        var x, y, z;
        x = Math.cos(lat) * Math.cos(lng);
        y = Math.cos(lat) * Math.sin(lng);
        z = Math.sin(lat);
        X += x;
        Y += y;
        Z += z;
    });

    X = X / total;
    Y = Y / total;
    Z = Z / total;

    var Lng = Math.atan2(Y, X);
    var Hyp = Math.sqrt(X * X + Y * Y);
    var Lat = Math.atan2(Z, Hyp);
    return {lng: Lng * 180 / Math.PI, lat: Lat * 180 / Math.PI};
};

examineCommon.saveDrawPolygon = function (json, option, callback) {
    if (! json){
        return false;
    }
    if (!$.isArray(json)) {
        return false;
    }
    if (json.length == 0){
        return false;
    } 
    var data = json[0];
    var path = data.path;
    var lnglat = examineCommon.getTheAreaCenter(path);
    if (option.lng){
        jQuery.extend(option, lnglat);
    }
    option.pathArray = JSON.stringify(json);
    if (option.pathArray) {
        examineCommon.addBasicEstateTagging(option,callback) ;
    }
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
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        },
        error: function (result) {
            Loading.progressHide();
            if (result.errmsg) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
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

examineCommon.settingTab = function(roleFilter){
    //找到任何元素包含  属性tab-role的集合
    $(document).find("[tab-role]").each(function (i,ele) {
        var target = $(ele) ;
        var role = target.attr("tab-role");
        //相同的角色显示  而不相同的则隐藏
        if (role == roleFilter) {
            target.show() ;
        }else {
            target.hide() ;
        }
    }) ;
} ;

window.examineCommon = examineCommon;
