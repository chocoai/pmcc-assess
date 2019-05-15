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
        <input type="hidden" name="id" value="${basicEstateLandState.id}">
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至"
                           name="westTo" value="${basicEstateLandState.westTo}">
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
                                onclick="basicCommon.landLevelSelect(this);">
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
                <label class="col-sm-1 control-label">土地形状</label>
                <div class="col-sm-3">
                    <select class="form-control shapeState" name="shapeState">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至"
                           name="northTo" value="${basicEstateLandState.northTo}">
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
                <label class="col-sm-1 control-label">基础设施完备度<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 infrastructureCompleteness"
                            name="infrastructureCompleteness" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度</label>
                <div class="col-sm-3">
                    <select class="form-control developmentDegree" name="developmentDegree">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid" style="display: none">
                <label class="col-sm-1 control-label">土地开发程度备注</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度备注"
                           name="developmentDegreeRemark" value="${basicEstateLandState.developmentDegreeRemark}">
                </div>
            </div>
            <div class="col-sm-6 col-sm-offset-1" id="developmentDegreeContentContainer">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">土地实体结论</label>
            <div class="col-sm-11">
                        <textarea class="form-control" name="conclusion"
                                  placeholder="土地实体结论">${basicEstateLandState.conclusion}</textarea>
            </div>
        </div>
        <div class="x_title">开发限制条件</div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">容积率</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control x-percent" placeholder="容积率"
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
                    <input type="text" class="form-control x-percent" placeholder="绿地率"
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
                <label class="col-sm-1 control-label">污染</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2" name="contaminated">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">酸碱度</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2" name="ph">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">肥力</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2" name="fertility">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">稳定性</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2" name="holdOn">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">承载力</label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2" name="bearingCapacity">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-12">
                <table class="table table-striped table-bordered" style="display: none">
                    <thead>
                    <tr>
                        <th width="10%">土地级别类别</th>
                        <th width="10%">土地级别类型</th>
                        <th width="10%">土地级别等级</th>
                        <th width="20%">说明</th>
                        <th width="10%">分值</th>
                        <th width="5%"></th>
                    </tr>
                    </thead>
                    <tbody id="landLevelTabContent">

                    </tbody>
                </table>
            </div>
        </div>
    </form>

</div>

<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td colspan="2" class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            {landLevelCategoryName}
        </td>
        <td>
            <select class="form-control" name="landLevelGrade" onchange="estateCommon.landLevelHandle(this,'{landLevelCategory}');">
                {landLevelGrade}
            </select>
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <input type="text" class="form-control" name="landLevelAchievement" value="{achievement}">
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="estateCommon.landLevelEmpty(this)">
        </td>
    </tr>
</script>

