/**
 *
 */

;(function ($) {
    var unitCommon = {};
    unitCommon.unitForm = $('#frm_unit');

    unitCommon.getUnitId = function () {
        return unitCommon.unitForm.find('[name=id]').val();
    };

    window.unitCommon = unitCommon;
})(jQuery);