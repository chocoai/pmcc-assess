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
    unitCommon.add = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/addUnit',
            data: {
                unitNumber: $form.find('[name=unitNumber]').val()
            },
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

    //编辑单元
    unitCommon.edit = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/appWriteUnit',
            data: {caseUnitId: $form.find("input[name='caseUnitId']").val()},
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