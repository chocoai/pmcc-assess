/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#basicUnitFrm');

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val();
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
        if(!caseUnitId){
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
                    unitCommon.unitForm.initLabel(result.data);
                    unitCommon.unitForm.initForm(result.data);
                }
            }
        })
    }

    window.unitCommon = unitCommon;
})(jQuery);