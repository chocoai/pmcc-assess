<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-horizontal" id="landCategoryInfoFrm">
    <input type="hidden" name="id" value="${landCategoryInfo.id}">
    <input type="hidden" name="houseId" value="${landCategoryInfo.houseId}">
    <div class="col-md-12">
        <div class="card-body">
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">土地用途类型<span
                                class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" required="required" name="landUseType"
                                       placeholder="土地用途类型" value="${landCategoryInfo.landUseType}"
                                       class="form-control form-control-sm">
                                <div class="input-group-append">
                                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">选择</button>
                                    <div class="dropdown-menu" id="landUseTypeList">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <label class="col-sm-1 control-label">土地用途类别<span
                                class="symbol required"></span></label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" required="required" name="landUseCategory"
                                       class="form-control form-control-sm" value="${landCategoryInfo.landUseCategory}"
                                       placeholder="土地用途类别">
                                <div class="input-group-append">
                                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onfocus="houseCommon.landUseTypeChange();">选择</button>
                                    <div class="dropdown-menu" id="landUseCategoryList">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <label class="col-sm-1 control-label">土地级别</label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="hidden" name="landLevel" value="${landCategoryInfo.landLevel}">
                                <input type="hidden" name="landFactorTotalScore" value="${landCategoryInfo.landFactorTotalScore}">
                                <input type="hidden" name="landLevelContentResult" value="${landCategoryInfo.landLevelContentResult}">
                                <input type="text" readonly="readonly" name="landLevelName"
                                       onclick="examineCommon.landLevelSelect(this);" value="${landCategoryInfo.landLevelName}"
                                       placeholder="土地级别" class="form-control">

                                <div class="input-group-prepend">
                                    <button class="btn btn-warning btn-sm "
                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                            type="button"
                                            onclick="$(this).closest('.input-group').find('input').val('');">
                                        清空
                                    </button>
                                </div>
                                <div class="input-group-prepend">
                                    <button class="btn btn-primary btn-sm "
                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                            type="button"
                                            onclick="examineCommon.landLevelSelect(this);">
                                        选择
                                    </button>
                                </div>
                                <div class="input-group-prepend">
                                    <button class="btn btn-info btn-sm "
                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                            type="button"
                                            onclick="openLevelDetailModal(this);">
                                        因素
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">土地级别说明</label>
                        <div class="col-sm-11">
                            <input name="landLevelRemark"
                                   placeholder="土地级别说明"
                                   class="form-control input-full " value="${landCategoryInfo.landLevelRemark}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">土地取得时间</label>
                        <div class="col-sm-3">
                            <input name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                   placeholder="土地取得时间"
                                   class="form-control input-full date-picker dbdate">
                        </div>
                        <label class="col-sm-1 control-label">土地终止日期</label>
                        <div class="col-sm-3">
                            <input name="terminationData" data-date-format="yyyy-mm-dd"
                                   placeholder="土地取得时间"
                                   class="form-control input-full date-picker dbdate">
                        </div>
                        <%--<label class="col-sm-1 control-label">土地使用年限</label>--%>
                        <%--<div class="col-sm-3">--%>
                        <%--<input type="text" data-rule-number="true" data-rule-maxlength="50"--%>
                        <%--name="landUseYear" class="form-control input-full"--%>
                        <%--placeholder="土地使用年限" value="${landCategoryInfo.landUseYear}">--%>
                        <%--</div>--%>
                        <label class="col-sm-1 control-label">土地形状</label>
                        <div class="col-sm-3">
                            <input type="text" name="landShape" class="form-control input-full"
                                   placeholder="土地形状" value="${landCategoryInfo.landShape}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">开发时间</label>
                        <div class="col-sm-3">
                            <input name="developTime" data-date-format="yyyy-mm-dd"
                                   placeholder="开发时间"
                                   class="form-control input-full date-picker dbdate">
                        </div>
                        <label class="col-sm-1 control-label">容积率</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="容积率" name="plotRatio" data-rule-number="true"
                                   class="form-control input-full" value="${landCategoryInfo.plotRatio}">
                        </div>
                        <label class="col-sm-1 control-label">容积率说明</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="容积率说明" name="plotRatioRemark"
                                   class="form-control input-full" value="${landCategoryInfo.plotRatioRemark}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">建筑密度</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="建筑密度" name="buildingDensity"
                                   class="form-control input-full" value="${landCategoryInfo.buildingDensity}">
                        </div>
                        <label class="col-sm-1 control-label">绿地率</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="绿化率" name="greeningRate"
                                   class="form-control input-full" value="${landCategoryInfo.greeningRate}">
                        </div>
                        <label class="col-sm-1 control-label">兼容类型</label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="text" name="compatibilityType"
                                       class="form-control"  value="${landCategoryInfo.compatibilityType}"
                                       placeholder="兼容类型">
                                <div class="input-group-append">
                                    <button class="btn btn-warning btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">选择</button>
                                    <div class="dropdown-menu" id="compatibilityTypeList">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">兼容比例</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="兼容比例" name="compatibilityRate"
                                   class="form-control input-full" value="${landCategoryInfo.compatibilityRate}">
                        </div>
                        <label class="col-sm-1 control-label">建筑限高</label>
                        <div class="col-sm-3">
                            <input type="text" placeholder="建筑限高" name="heightPermitted"
                                   class="form-control input-full" value="${landCategoryInfo.heightPermitted}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</form>


<%@include file="/views/project/tool/landLevelModalView.jsp" %>
<script type="text/javascript">
    var landLevel = {};

    landLevel.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    //土地因素
    function openLevelDetailModal(this_) {
        var group = $(this_).closest('.input-group');
        var form = $(this_).closest(".form-horizontal");
        var id = form.find('input[name="id"]').val();
        var landLevelId = group.find('input[name="landLevel"]').val();
        if (!landLevelId) {
            notifyWarning("提示", "请选择土地级别!");
            return false;
        }
        var projectId = '${applyBatchDetail.projectId}' ;
        var option = {projectId:projectId,levelDetailId:landLevelId ,dataTableId:id,dataTableName:AssessDBKey.BasicEstateLandCategoryInfo} ;
        landAchievementGroup.openLevelDetailModal(option,true ) ;
    };

    landLevel.getBasicEstateLandCategoryInfoById = function (id, callback) {
        AssessCommon.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };

</script>


