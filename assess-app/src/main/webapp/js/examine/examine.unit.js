/**
 *
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#frm_unit');

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val();
    };


    //单元明细
    unitCommon.detail = function (applyId,callback) {
        $.ajax({
            url: getContextPath() + '/basicUnit/getBasicUnitByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    callback(result.data) ;
                }
            }
        })
    };

    unitCommon.initForm = function (data) {
        unitCommon.unitForm.clearAll();
        unitCommon.unitForm.initForm(data);
    };

    window.unitCommon = unitCommon;
})(jQuery);