/**
 * Created by kings on 2018-12-20.
 */
var basicCommon = {};
basicCommon.basicApplyForm = $('#basicApplyFrm');
basicCommon.contentTabPanel = $('#contentTabPanel');

basicCommon.getPlanDetailsId = function () {
    return basicCommon.basicApplyForm.find('[name=id]').val();
}

