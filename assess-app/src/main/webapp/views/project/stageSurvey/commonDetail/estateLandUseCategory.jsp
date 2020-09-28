<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-horizontal" id="landCategoryInfoFrm">
    <input type="hidden" name="id">
    <div class="row">
        <div class="col-md-12">
            <div class="card-body">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">土地用途类型</label>
                            <div class="col-sm-3">
                                <label type="text" name="landUseType"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">土地用途类别</label>
                            <div class="col-sm-3">
                                <label type="text" name="landUseCategory"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">土地级别</label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="hidden" name="landLevel">
                                    <label type="text" name="landLevelName"
                                           class="form-control"></label>
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
                                <label  name="landLevelRemark"
                                       class="form-control input-full">${landCategoryInfo.landLevelRemark}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">土地取得时间</label>
                            <div class="col-sm-3">
                                <label name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate"></label>
                            </div>
                            <label class="col-sm-1 control-label">土地终止日期</label>
                            <div class="col-sm-3">
                                <label name="terminationData" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate"></label>
                            </div>
                            <%--<label class="col-sm-1 control-label">土地使用年限</label>--%>
                            <%--<div class="col-sm-3">--%>
                                <%--<label type="text" name="landUseYear"--%>
                                       <%--class="form-control input-full"></label>--%>
                            <%--</div>--%>
                            <label class="col-sm-1 control-label">土地形状</label>
                            <div class="col-sm-3">
                                <label type="text" name="landShape"
                                       class="form-control input-full"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">开发时间</label>
                            <div class="col-sm-3">
                                <label name="developTime" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate"></label>
                            </div>
                            <label class="col-sm-1 control-label">容积率</label>
                            <div class="col-sm-3">
                                <label type="text" name="plotRatio"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">容积率说明</label>
                            <div class="col-sm-3">
                                <label type="text" name="plotRatioRemark"
                                       class="form-control input-full"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">建筑密度</label>
                            <div class="col-sm-3">
                                <label type="text" name="buildingDensity"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">绿地率</label>
                            <div class="col-sm-3">
                                <label type="text" name="greeningRate"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">兼容类型</label>
                            <div class="col-sm-3">
                                <label type="text" name="compatibilityType"
                                       class="form-control input-full"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">兼容比例</label>
                            <div class="col-sm-3">
                                <label type="text" name="compatibilityRate"
                                       class="form-control input-full"></label>
                            </div>
                            <label class="col-sm-1 control-label">建筑限高</label>
                            <div class="col-sm-3">
                                <label type="text" name="heightPermitted"
                                       class="form-control input-full"></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</form>

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
        landAchievementGroup.openLevelDetailModal(option,false ) ;
    };

    landLevel.getBasicEstateLandCategoryInfoById = function (id, callback) {
        AssessCommon.ajaxServerFun({id: id}, "/basicEstateLandCategoryInfo/getBasicEstateLandCategoryInfoById", "get", callback);
    };


</script>


