/**
 * Created by kings on 2018-12-20.
 */
var declareCommon = {};
declareCommon.declareApplyForm = $('#declareApplyForm');

declareCommon.getPlanDetailsId = function () {
    return declareCommon.declareApplyForm.find('[name=planDetailsId]').val();
};
declareCommon.getProjectId = function () {
    return declareCommon.declareApplyForm.find('[name=projectId]').val();
};
//来源 com.copower.pmcc.assess.common.enums.DeclareTypeEnum
declareCommon.masterData = "master" ;//主数据
declareCommon.branchData = "branch" ;//从数据

declareCommon.declareHouseType = "" ;//房产申报类型
declareCommon.declareLandType = "" ;//土地申报类型
declareCommon.declareRealType = "" ;//不动产申报类型
