/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#basicUnitFrm');
    unitCommon.unitMapiframe = undefined;

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val();
    }
    unitCommon.getUnitNumber = function () {
        return unitCommon.unitForm.find('[name=unitNumber]').val();
    }

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
                    unitCommon.unitForm.initForm(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //升级单元
    unitCommon.upgrade = function (_this, callback) {
        var caseUnitId = $(_this).closest('form').find("input[name='caseUnitId']").val();
        if (!caseUnitId) {
            toastr.info('请选择系统中已存在的单元信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicUnit/appWriteUnit',
            data: {caseUnitId: caseUnitId},
            success: function (result) {
                if (result.ret) {
                    unitCommon.unitForm.initForm(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //单元初始化by applyId
    unitCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    unitCommon.unitForm.initForm(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //单元明细
    unitCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    unitCommon.unitForm.initForm(result.data);
                }
            }
        })
    }

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
    }

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
    }

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
    }

    window.unitCommon = unitCommon;
})(jQuery);