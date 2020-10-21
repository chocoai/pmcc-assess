<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="modalEconomicIndicators" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">经济指标</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmEconomicIndicators" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="planDetailsId">
                    <input type="hidden" name="centerId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            项目名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control input-full"
                                                   required="required">
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            <c:choose>
                                                <c:when test="${projectInfo.projectCategoryName eq '土地'}">
                                                    地块编号
                                                </c:when>
                                                <c:otherwise>项目档次（楼盘)</c:otherwise>
                                            </c:choose>
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text" name="grade" class="form-control input-full">
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            土地用途
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text" placeholder="土地用途" name="certUse"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            建筑结构
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="建筑结构" name="buildingStructure"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            建筑基底<br/>占地面积（㎡)
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="建筑基底占地面积（㎡)" name="buildingBaseCoverage"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            建筑限高（m）
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="建筑限高（m）" name="buildingHeightLimit"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            规划容积率
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="容积率" name="volumetricRate"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            建筑密度
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="建筑密度" name="buildingDensity"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            绿地率
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="绿地率" name="greenSpaceRate"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            规划建设<br/>净用地面积（㎡）
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="规划建设净用地面积（㎡）" name="planNetConstructionLandArea"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            规划总建筑面积（㎡）
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="规划总建筑面积（㎡）" name="planTotalBuildArea"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            设定容积率
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="设定容积率" name="setVolumetricRate"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            评估用地面积（㎡）
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="评估用地面积（㎡）" name="assessUseLandArea"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            评估总建筑面积（㎡）
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="评估总建筑面积（㎡）" name="assessTotalBuildArea"
                                                   class="form-control input-full"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            规划日期
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input placeholder="规划日期"
                                                   name="planDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                            项目文件名称
                                        </label>
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <input type="text"
                                                   placeholder="项目文件名称" name="projectFileName"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                            宗地外设定
                                        </label>
                                        <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                            <div id="industrySupplyInfoContainer_AAAAA"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                            宗地内设定
                                        </label>
                                        <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                            <div id="developmentDegreeContentContainer_AAAAA"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <form id="frmEconomicIndicatorsItem" class="form-horizontal">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <table class=" tree" id="frmEconomicIndicatorsItemTable">
                                                <thead>
                                                <tr>
                                                    <th width="30%">规划项目名称</th>
                                                    <th width="5%">规划建筑面积</th>
                                                    <th width="5%">可出售面积</th>
                                                    <th width="5%">评估面积/个数</th>
                                                    <%--<th>个数</th>--%>
                                                    <th width="20%">单位售价(元/㎡)</th>
                                                    <th width="20%">备注</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="treegrid-1" data-key="groundBuildingArea"
                                                    data-title="地上计入容积率建筑面积"
                                                    data-role="parent" data-parent-index="1">
                                                    <td>一: 地上计入容积率建筑面积
                                                        <a class="btn btn-xs btn-warning tooltips"
                                                           data-placement="top"
                                                           onclick="economicIndicators.appendItemChildren(this);"><i
                                                                class="fa fa-plus fa-white"></i></a>
                                                    </td>
                                                    <td><label name="plannedBuildingArea"></label></td>
                                                    <td><label name="saleableArea"></label></td>
                                                    <td><label name="assessArea"></label></td>
                                                    <%--<td><label name="number"></label></td>--%>
                                                    <td><label name="unitPrice"></label></td>
                                                    <td></td>
                                                </tr>
                                                <tr class="treegrid-2" data-key="groundExcludBuildingArea"
                                                    data-title="地上不计入容积率建筑面积"
                                                    data-role="parent" data-parent-index="2">
                                                    <td>二: 地上不计入容积率建筑面积
                                                        <a class="btn btn-xs btn-warning tooltips"
                                                           data-placement="top"
                                                           onclick="economicIndicators.appendItemChildren(this);"><i
                                                                class="fa fa-plus fa-white"></i></a>
                                                    </td>
                                                    <td><label name="plannedBuildingArea"></label></td>
                                                    <td><label name="saleableArea"></label></td>
                                                    <td><label name="assessArea"></label></td>
                                                    <%--<td><label name="number"></label></td>--%>
                                                    <td><label name="unitPrice"></label></td>
                                                    <td></td>
                                                </tr>
                                                <tr class="treegrid-3" data-key="undergroundBuildingArea"
                                                    data-title="地下建筑面积"
                                                    data-role="parent" data-parent-index="3">
                                                    <td>三: 地下建筑面积
                                                        <a class="btn btn-xs btn-warning tooltips"
                                                           data-placement="top"
                                                           onclick="economicIndicators.appendItemChildren(this);"><i
                                                                class="fa fa-plus fa-white"></i></a>
                                                    </td>
                                                    <td><label name="plannedBuildingArea"></label></td>
                                                    <td><label name="saleableArea"></label></td>
                                                    <td><label name="assessArea"></label></td>
                                                    <%--<td><label name="number"></label></td>--%>
                                                    <td><label name="unitPrice"></label></td>
                                                    <td></td>
                                                </tr>
                                                <tr class="treegrid-4" data-key="undergroundIncludedBuildingArea"
                                                    data-title="地下不计入建筑面积"
                                                    data-role="parent" data-parent-index="4">
                                                    <td>四: 地下不计入建筑面积
                                                        <a class="btn btn-xs btn-warning tooltips"
                                                           data-placement="top"
                                                           onclick="economicIndicators.appendItemChildren(this);"><i
                                                                class="fa fa-plus fa-white"></i></a>
                                                    </td>
                                                    <td><label name="plannedBuildingArea"></label></td>
                                                    <td><label name="saleableArea"></label></td>
                                                    <td><label name="assessArea"></label></td>
                                                    <%--<td><label name="number"></label></td>--%>
                                                    <td><label name="unitPrice"></label></td>
                                                    <td></td>
                                                </tr>
                                                <tr class="treegrid-5" data-key="otherBuildingArea"
                                                    data-role="parent" data-parent-index="5">
                                                    <td>五: 其他
                                                        <a class="btn btn-xs btn-warning tooltips"
                                                           data-placement="top"
                                                           onclick="economicIndicators.appendItemChildren(this);"><i
                                                                class="fa fa-plus fa-white"></i></a>
                                                    </td>
                                                    <td><label name="plannedBuildingArea"></label></td>
                                                    <td><label name="saleableArea"></label></td>
                                                    <td><label name="assessArea"></label></td>
                                                    <%--<td><label name="number"></label></td>--%>
                                                    <td><label name="unitPrice"></label></td>
                                                    <td></td>
                                                </tr>
                                                </tbody>

                                                <tfoot>
                                                <tr>
                                                    <td>预期销售合计:</td>
                                                    <td class="info">规划建筑面积(㎡)<label name="plannedBuildingArea"
                                                                                     class="label "></label></td>
                                                    <td class="info">可售面积(㎡)<label name="saleableArea"
                                                                                   class="label "></label></td>
                                                    <td class="info" colspan="2">总可售面积售价(元)<label
                                                            name="totalSaleableAreaPrice"
                                                            class="label "></label></td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <code>说明:总可售面积售价 = 单位售价 x 评估面积; 可售面积 = 可出售面积 累加之和; 规划建筑面积 = 规划建筑面积
                                                累加之和</code>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="economicIndicators.save()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="text/html" id="economicIndicatorsItemTemplate" data-title="地上计入容积率建筑面积">
    <tr class="dynamic treegrid-{parentIndex}-{currentIndex} treegrid-parent-{parentIndex}"
        data-key="{dataKey}" data-role="child">
        <td>
            <input type="text" name="name" value="{name}" style="width: 100px;" placeholder="名称">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td><input type="text" value="{plannedBuildingArea}" placeholder="规划面积"
                   onblur="economicIndicators.autoSummary()"
                   name="plannedBuildingArea" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{saleableArea}" placeholder="可售面积"
                   onblur="economicIndicators.autoSummary()"
                   name="saleableArea" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{assessArea}" placeholder="评估面积/个数"
                   onblur="economicIndicators.autoSummary()"
                   name="assessArea" data-rule-number="true" style="width: 100px;">
        </td>
        <%--<td><input type="text" value="{number}"--%>
        <%--onblur="economicIndicators.autoSummary()"--%>
        <%--name="number" data-rule-number="true" style="width: 100px;">--%>
        <%--</td>--%>
        <td>
            <input type="text" value="{unitPrice}" placeholder="单价"
                   onblur="economicIndicators.autoSummary()"
                   name="unitPrice" data-rule-number="true" style="width: 100px;">
            <input type="hidden" name="mcId" value="{mcId}">
            <c:if test="${!empty projectPlanDetails.judgeObjectId}">

                <button type="button" class="btn btn-info btn-sm" onclick="economicIndicators.callCompareMethod(this);">
                    比较法
                </button>

            </c:if>
        </td>
        <td><input type="text" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>


<script type="text/javascript">
    var economicIndicators = {};
    economicIndicators.frmItem = $("#frmEconomicIndicatorsItem");
    economicIndicators.frm = $("#frmEconomicIndicators");
    economicIndicators.model = $("#modalEconomicIndicators");
    economicIndicators.template = $("#economicIndicatorsItemTemplate");
    economicIndicators.saveCallback;

    economicIndicators.initForm = function (data) {
        economicIndicators.frm.initForm(data);
        economicIndicators.frm.find("input[name='planDate']").val(formatDate(data.planDate));
        economicIndicators.frm.find("label[name='planDate']").html(formatDate(data.planDate));
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_AAAAA");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_AAAAA");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '';
            var array = [];
            if (data.parcelSettingOuter) {
                array = data.parcelSettingOuter.split(',');
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingOuter' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";

            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"economicIndicators.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"economicIndicators.checkedFun(this,'parcelSettingOuter',false)\">";
            industrySupplyInfoContainer.append(resultHtml);
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '';
            var array = [];
            if (data.parcelSettingInner) {
                array = data.parcelSettingInner.split(',');
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingInner' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";

            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"economicIndicators.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"economicIndicators.checkedFun(this,'parcelSettingInner',false)\">";
            developmentDegreeContentContainer.append(resultHtml);
        });
    };
    //初始化
    economicIndicators.init = function (options) {
        var defaluts = {
            planDetailsId: undefined,
            economicId: undefined,
            saveCallback: undefined  //经济指标保存后的回调方法
        };
        defaluts = $.extend({}, defaluts, options);
        economicIndicators.saveCallback = defaluts.saveCallback;
        //先清空
        economicIndicators.frm.clearAll();
        economicIndicators.frmItem.find('[data-role=child]').remove();
        economicIndicators.frm.find('[name=planDetailsId]').val(defaluts.planDetailsId);
        economicIndicators.frm.find('[name=centerId]').val(defaluts.centerId);
        //回显数据
        if (defaluts.economicId) {
            $.ajax({
                url: '${pageContext.request.contextPath}/mdEconomicIndicators/getEconomicIndicatorsInfo',
                data: {
                    economicId: defaluts.economicId
                },
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.ret) {
                        if (result.data.economicIndicators) {
                            economicIndicators.initForm(result.data.economicIndicators);
                        }
                        if (result.data.economicIndicatorsItemList) {
                            $.each(result.data.economicIndicatorsItemList, function (i, item) {
                                var dataKey = item.dataKey;
                                var parentTr = economicIndicators.frmItem.find('[data-key=' + dataKey + '][data-role="parent"]');
                                var childTarget = economicIndicators.template;
                                var html = childTarget.html();
                                html = html.replace(/{dataKey}/g, dataKey).replace(/{parentIndex}/g, parentTr.attr('data-parent-index'));
                                html = html.replace(/{currentIndex}/g, economicIndicators.frmItem.find('[data-key=' + dataKey + ']').length);
                                html = html.replace(/{name}/g, AssessCommon.toString(item.name)).replace(/{plannedBuildingArea}/g, AssessCommon.toString(item.plannedBuildingArea));
                                html = html.replace(/{saleableArea}/g, AssessCommon.toString(item.saleableArea)).replace(/{number}/g, AssessCommon.toString(item.number)).replace(/{remark}/g, AssessCommon.toString(item.remark));
                                html = html.replace(/{unitPrice}/g, AssessCommon.toString(item.unitPrice));
                                html = html.replace(/{assessArea}/g, AssessCommon.toString(item.assessArea));
                                html = html.replace(/{mcId}/g, AssessCommon.toString(item.mcId));
                                economicIndicators.frmItem.find('[data-key=' + dataKey + ']').last().after(html);
                                if (defaluts.attribute) {
                                    economicIndicators.frmItem.find('[data-key=' + dataKey + ']').each(function (i, n) {
                                        var target = $(n);
                                        target.find("td").first().find("a").remove();
                                    });
                                    economicIndicators.frmItem.find('[data-key=' + dataKey + ']').last().find("input").attr(defaluts.attribute);
                                }
                            });
                            economicIndicators.autoSummary();
                        }
                        economicIndicators.frmItem.find('table').treegrid();
                        if (defaluts.targetCallback) {
                            defaluts.targetCallback(economicIndicators.frmItem);
                        }
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);

                    }
                }
            })
        } else {
            economicIndicators.initForm({});
        }
        if (defaluts.attribute) {
            economicIndicators.frm.find("input").attr(defaluts.attribute);
        }
        if (defaluts.showDelHtml) {
            var html = defaluts.showDelHtml.replace(/{frm}/g, 'frmEconomicIndicators');
            html = html.replace(/{box}/g, 'modalEconomicIndicators');
            economicIndicators.model.find(".modal-footer").find(".btn-warning").remove();
            economicIndicators.model.find(".modal-footer").find("button").eq(1).before(html);
        }
        if (defaluts.targetCallback) {
            defaluts.targetCallback(economicIndicators.frmItem);
        }
        if (defaluts.attribute) {
            economicIndicators.frmItem.find("tr").each(function (i, n) {
                var target = $(n);
                target.find("td").first().find("a").remove();
            });
            economicIndicators.model.find(".modal-footer").find(".btn-primary").remove();
        }
        economicIndicators.model.modal("show");
    };

    //保存
    economicIndicators.save = function () {
        if (!economicIndicators.frm.valid()) {
            return false;
        }
        var data = {};

        data.economicIndicators = formSerializeArray(economicIndicators.frm);
        data.economicIndicatorsItemList = [];
        economicIndicators.frmItem.find('tr[data-role="child"]').each(function () {
            var economicIndicatorsItem = {};
            economicIndicatorsItem.dataKey = $(this).attr('data-key');
            economicIndicatorsItem.planDetailsId = economicIndicators.planDetailsId;
            economicIndicatorsItem.name = $(this).find('[name=name]').val().trim();
            economicIndicatorsItem.plannedBuildingArea = $(this).find('[name=plannedBuildingArea]').val().trim();
            economicIndicatorsItem.saleableArea = $(this).find('[name=saleableArea]').val().trim();
            economicIndicatorsItem.assessArea = $(this).find('[name=assessArea]').val().trim();
//            economicIndicatorsItem.number = $(this).find('[name=number]').val().trim();
            economicIndicatorsItem.unitPrice = $(this).find('[name=unitPrice]').val().trim();
            economicIndicatorsItem.remark = $(this).find('[name=remark]').val().trim();
            economicIndicatorsItem.mcId = $(this).find('[name=mcId]').val().trim();
            data.economicIndicatorsItemList.push(economicIndicatorsItem);
        });
        $.ajax({
            url: '${pageContext.request.contextPath}/mdEconomicIndicators/save',
            data: {
                formData: JSON.stringify(data)
            },
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    if (economicIndicators.saveCallback) {
                        economicIndicators.saveCallback(result.data.id);
                    }
                    economicIndicators.model.modal('hide');
                    if (data.economicIndicatorsItemList.length >= 1) {

                    }
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);

                }
            }
        })
    };

    //添加子项
    economicIndicators.appendItemChildren = function (_this) {
        var dataKey = $(_this).closest('tr').attr('data-key');
        var html = economicIndicators.template.html();
        html = html.replace(/{dataKey}/g, dataKey).replace(/{parentIndex}/g, $(_this).closest('tr').attr('data-parent-index'));
        html = html.replace(/{currentIndex}/g, economicIndicators.frmItem.find('[data-key=' + dataKey + ']').length);
        html = html.replace(/{name}/g, '').replace(/{plannedBuildingArea}/g, '');
        html = html.replace(/{saleableArea}/g, '').replace(/{number}/g, '').replace(/{remark}/g, '');
        html = html.replace(/{unitPrice}/g, '');
        html = html.replace(/{assessArea}/g, '');
        html = html.replace(/{mcId}/g, '');
        economicIndicators.frmItem.find('[data-key=' + dataKey + ']').last().after(html);
        $(_this).closest('tr').closest('table').treegrid();
    };

    /**
     * 获取计算后的总值
     * @returns {{}}
     */
    economicIndicators.getFormData = function () {
        var target = economicIndicators.frmItem.find("table").find("tfoot");
        var head = formSerializeArray(economicIndicators.frm);
        var data = {};
        data.developLandAreaTax = head.assessUseLandArea;
        data.developBuildAreaTax = head.assessTotalBuildArea;
        data.parcelSettingOuter = head.parcelSettingOuter;
        data.parcelSettingInner = head.parcelSettingInner;
        data.plannedBuildingArea = target.find("label[name='plannedBuildingArea']").html();
        var totalSaleableAreaPrice = "0";
        try {
            totalSaleableAreaPrice = target.find("label[name='totalSaleableAreaPrice']").html();
            totalSaleableAreaPrice = Number(totalSaleableAreaPrice) / 10000;
            totalSaleableAreaPrice = totalSaleableAreaPrice.toFixed(2);
        } catch (e) {
        }
        data.totalSaleableAreaPrice = totalSaleableAreaPrice;
        data.saleableArea = target.find("label[name='saleableArea']").html();
        return data;
    };

    //自动汇总
    economicIndicators.autoSummary = function () {
        var target = economicIndicators.frmItem;
        var plannedBuildingAreaValue = 0, totalSaleableAreaPriceValue = 0, saleableAreaValue = 0;
        target.find('tr[data-role="parent"]').each(function () {
            var dataKey = $(this).attr('data-key');
            var numberTotal = 0, saleableAreaTotal = 0, plannedBuildingAreaTotal = 0, unitPriceTotal = 0,
                assessAreaTotal = 0,
                totalSaleableAreaPrice = 0;
            economicIndicators.frmItem.find('tr[data-role="child"][data-key=' + dataKey + ']').each(function () {
                var plannedBuildingArea = $(this).find('[name=plannedBuildingArea]').val();
                var saleableArea = $(this).find('[name=saleableArea]').val();
//                var number = $(this).find('[name=number]').val();
                var assessArea = $(this).find('[name=assessArea]').val();
                var unitPrice = $(this).find('[name=unitPrice]').val();
                if ($.isNumeric(plannedBuildingArea)) {
                    plannedBuildingAreaTotal += parseFloat(plannedBuildingArea);
                }
                if ($.isNumeric(saleableArea)) {
                    saleableAreaTotal += parseFloat(saleableArea);
                }
                if ($.isNumeric(assessArea)) {
                    assessAreaTotal += parseFloat(assessArea);
                }
//                if ($.isNumeric(number)) {
//                    numberTotal += parseFloat(number);
//                }
                if ($.isNumeric(unitPrice)) {
                    unitPriceTotal += parseFloat(unitPrice);
                }
                if ($.isNumeric(unitPrice) && $.isNumeric(assessArea)) {
                    var temp = parseFloat(unitPrice) * assessArea;
                    totalSaleableAreaPrice += parseFloat(temp);
                }
            });

            $(this).find('[name=plannedBuildingArea]').text('');
            $(this).find('[name=saleableArea]').text('');
            $(this).find('[name=assessArea]').text('');
//            $(this).find('[name=number]').text('');
            $(this).find('[name=unitPrice]').text('');

            if (plannedBuildingAreaTotal > 0) {
                $(this).find('[name=plannedBuildingArea]').text(plannedBuildingAreaTotal.toFixed(2));
                plannedBuildingAreaValue += plannedBuildingAreaTotal;
            }
            if (saleableAreaTotal > 0) {
                $(this).find('[name=saleableArea]').text(saleableAreaTotal.toFixed(2));
                saleableAreaValue += saleableAreaTotal;
            }
//            if (numberTotal > 0) {
//                $(this).find('[name=number]').text(numberTotal.toFixed(2));
//            }
            if (assessAreaTotal > 0) {
                $(this).find('[name=assessArea]').text(assessAreaTotal.toFixed(2));
            }
            if (unitPriceTotal > 0) {
                $(this).find('[name=unitPrice]').text(unitPriceTotal.toFixed(2));
            }
            if (totalSaleableAreaPrice > 0) {
                totalSaleableAreaPriceValue += totalSaleableAreaPrice;
            }

        });
        target.find("table").find("tfoot").find("label[name='plannedBuildingArea']").html(plannedBuildingAreaValue.toFixed(2));
        target.find("table").find("tfoot").find("label[name='totalSaleableAreaPrice']").html(totalSaleableAreaPriceValue.toFixed(2));
        target.find("table").find("tfoot").find("label[name='saleableArea']").html(saleableAreaValue.toFixed(2));
    };

    economicIndicators.checkedFun = function (that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    economicIndicators.callCompareMethod = function (this_) {
        var mcId = $(this_).closest('td').find('[name=mcId]').val();
        var frame = layer.open({
            type: 2,
            title: '比较法',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/marketCompare/index?isLand=false&mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {

                }
            },
            btnAlign: 'c',
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.saveResult(function (mcId, price) {
                    $(this_).closest("td").find("[name='unitPrice']").val(price);
                    $(this_).closest("td").find("[name='mcId']").val(mcId);
                    economicIndicators.autoSummary();
                    layer.closeAll('iframe');
                });
            },
            btn2: function (index, layero) {

            }
        });
        layer.full(frame);
    };

</script>