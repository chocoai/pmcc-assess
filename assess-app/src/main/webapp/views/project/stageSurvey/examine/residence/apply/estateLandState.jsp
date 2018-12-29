<%--
 土地实体情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">

    <div class="x_title">
        <h3>土地实体情况 </h3>
        <div class="clearfix"></div>
    </div>

    <form id="frm_estateLandState" class="form-horizontal">
        <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="name"
                           placeholder="名称" value="${basicEstateLandState.name}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型</label>
                <div class="col-sm-3">
                    <select class="form-control landUseType" name="landUseType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别</label>
                <div class="col-sm-3">
                    <select class="form-control landUseCategory"
                            name="landUseCategory">
                        <option>请先选择土地用途类型</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="landLevel" value="${basicEstateLandState.landLevel}">
                        <input type="text" readonly="readonly" onclick="basicCommon.landLevelSelect(this);"
                               placeholder="土地级别" class="form-control" name="landLevelName"
                               value="${basicEstateLandState.landLevelName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="examineCommon.landLevelSelect(this);">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至"
                           name="eastTo" value="${basicEstateLandState.eastTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至"
                           name="southTo" value="${basicEstateLandState.southTo}">
                </div>
            </div>
        </div>

        <div class="form-group">

            <div class="x-valid">
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至"
                           name="westTo" value="${basicEstateLandState.westTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至"
                           name="northTo" value="${basicEstateLandState.northTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状</label>
                <div class="col-sm-3">
                    <select class="form-control shapeState" name="shapeState">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状备注</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="shapeStateRemark"></textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" data-rule-number='true'
                           placeholder="土地面积(请输入数字)" name="landArea" value="${basicEstateLandState.landArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 planeness" name="planeness">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">地势</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 topographicTerrain" name="topographicTerrain">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">
                    <select class="form-control developmentDegree" name="developmentDegree">
                    </select>
                </div>
            </div>
            <div class="col-sm-6 col-sm-offset-1" id="developmentDegreeContentContainer">
            </div>
        </div>
        <div class="x_title">开发限制条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="容积率"
                           name="plotRatio" value="${basicEstateLandState.plotRatio}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑密度</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="建筑密度"
                           name="buildingDensity" value="${basicEstateLandState.buildingDensity}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">绿地率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="绿地率"
                           name="greenSpaceRate" value="${basicEstateLandState.greenSpaceRate}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">兼容比例</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="兼容比例"
                           name="compatibleRatio" value="${basicEstateLandState.compatibleRatio}">
                </div>
            </div>
        </div>
        <div class="x_title">土壤</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承载力</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="承载力"
                           name="bearingCapacity" value="${basicEstateLandState.bearingCapacity}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">污染</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="污染"
                           name="contaminated" value="${basicEstateLandState.contaminated}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">酸碱度</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="酸碱度"
                           name="ph" value="${basicEstateLandState.ph}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">肥力</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="肥力"
                           name="fertility" value="${basicEstateLandState.fertility}">
                </div>
            </div>
        </div>
    </form>

</div>
