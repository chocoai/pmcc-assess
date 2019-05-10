<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${empty declareRecord?projectPlanDetails.projectPhaseName: declareRecord.name} 土地的查勘表</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="surveyExaminePurenessLandFrm">
                        <input type="hidden" name="id" value="${surveyExaminePurenessLand.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省
                                    <span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select name="province" required
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select name="city" required class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县</label>
                                <div class="col-sm-3">
                                    <select name="district" required class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地名称</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" name="name"
                                           placeholder="名称">
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
                                        <input type="hidden" name="landLevel">
                                        <input type="text" readonly="readonly" onclick="purenessLand.landLevelSelect(this);"
                                               placeholder="土地级别" class="form-control" name="landLevelName">
                                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="purenessLand.landLevelSelect(this);">
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
                                           name="eastTo">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">南至</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="南至"
                                           name="southTo">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">西至</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="西至"
                                           name="westTo">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">北至</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="北至"
                                           name="northTo">
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
                                           placeholder="土地面积(请输入数字)" name="landArea">
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
                                    <select class="form-control search-select select2 infrastructureCompleteness" name="infrastructureCompleteness" required>
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
                                           name="developmentDegreeRemark">
                                </div>
                            </div>
                            <div class="col-sm-6 col-sm-offset-1" id="developmentDegreeContentContainer">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">土地实体结论</label>
                            <div class="col-sm-11">
                        <textarea class="form-control" name="conclusion"
                                  placeholder="土地实体结论"></textarea>
                            </div>
                        </div>
                        <div class="x_title">开发限制条件</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">容积率</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control x-percent" placeholder="容积率"
                                           name="plotRatio">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">建筑密度</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="建筑密度"
                                           name="buildingDensity">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">绿地率</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control x-percent" placeholder="绿地率"
                                           name="greenSpaceRate">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">兼容比例</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="兼容比例"
                                           name="compatibleRatio">
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

                        <div class="x_title">供应信息</div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供气信息</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 supplyGas" name="supplyGas">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供电信息</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 supplyPower" name="supplyPower">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供水情况</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 supplyWater" name="supplyWater">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">排水情况</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 drainWater" name="drainWater">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供热信息</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 supplyHeating" name="supplyHeating">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="x_title">交易信息</div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input placeholder="交易时间" required="required"
                                           name="tradingTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate tradingTime">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易总价（元）</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="交易总价（元）" class="form-control" name="tradingTotalPrice">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">单价内涵<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 priceConnotation" name="priceConnotation" required>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易单价（元）</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="交易单价（元）" class="form-control" name="tradingUnitPrice" required>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源类型</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 informationType" name="informationType">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源类别</label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 informationCategory" name="informationCategory">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附件</label>
                                <div class="col-sm-5">
                                    <input id="SurveyExaminePurenessLandFileA" placeholder="上传附件" class="form-control"
                                           type="file">
                                    <div id="_SurveyExaminePurenessLandFileA"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消<i style="margin-left: 10px" class="fa fa-close"></i>
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/survey/examinePurenessLand.js"></script>
<script type="text/javascript">
    var obj = JSON.parse('${surveyExaminePurenessLandJSON}');
    purenessLand.initForm(obj);
</script>
<script type="text/javascript">
    //任务提交
    function submit(mustUseBox) {
        var frm = purenessLand.landForm;
        //数据校验
        if (!frm.valid()) {
            return false;
        }
        var data = {"surveyExaminePurenessLand":formSerializeArray(frm)};
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        } else {
            submitToServer(JSON.stringify(data), mustUseBox);
        }
    }

</script>
</html>

