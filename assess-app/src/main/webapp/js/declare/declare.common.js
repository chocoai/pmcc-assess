/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};
declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
}

