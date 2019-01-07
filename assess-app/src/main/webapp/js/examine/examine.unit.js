/**
 *
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#basicUnitFrm');

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val();
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
                    callback(result.data);
                }
            }
        })
    };

    unitCommon.initForm = function (data) {
        unitCommon.unitForm.clearAll();
        unitCommon.unitForm.initForm(data);
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
            area: ['893px', '600px'],
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
        $.ajax({
            url: getContextPath() + '/basicUnit/appWriteUnit',
            data: {
                applyId: basicCommon.getApplyId(),
                caseUnitId: id
            },
            type: 'post',
            success: function (result) {
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
        var caseBuildingId = basicCommon.getcaseBuildingId();
        if (caseBuildingId) {
            $("#txt_Unit_search").apUnit({
                caseBuildingId: caseBuildingId,
                onSelect: function (id, name) {
                    unitCommon.onSelect(id);
                }
            });
        }
    };


    window.unitCommon = unitCommon;
})(jQuery);