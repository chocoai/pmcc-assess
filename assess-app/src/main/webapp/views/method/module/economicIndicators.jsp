<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalEconomicIndicators" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经济指标</h3>
            </div>
            <form id="frmEconomicIndicators" class="form-horizontal"
                  style="display: block;margin-bottom: 0px;padding-bottom: 0px;">
                <input type="hidden" name="id">
                <input type="hidden" name="planDetailsId">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="项目名称" name="name" class="form-control"
                                       required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目档次（楼盘)</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="项目档次（楼盘)" name="grade" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="土地用途" name="certUse" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑结构</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="建筑结构" name="buildingStructure" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑基底占地面积（㎡)</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="建筑基底占地面积（㎡)" name="buildingBaseCoverage" class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑限高（m）</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="建筑限高（m）" name="buildingHeightLimit" class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="容积率" name="volumetricRate" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="建筑密度" name="buildingDensity" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="绿地率" name="greenSpaceRate" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规划建设净用地面积（㎡）</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="规划建设净用地面积（㎡）" name="planNetConstructionLandArea"
                                       class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规划总建筑面积（㎡）</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="规划总建筑面积（㎡）" name="planTotalBuildArea" class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设定容积率</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="设定容积率" name="setVolumetricRate" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估用地面积（㎡）</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="评估用地面积（㎡）" name="assessUseLandArea" class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'
                                >
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估总建筑面积（㎡）</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text"
                                       placeholder="评估总建筑面积（㎡）" name="assessTotalBuildArea" class="form-control"
                                       data-rule-maxlength="100" data-rule-number='true'>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规划日期</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input placeholder="规划日期"
                                       name="planDate" data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate roomTime">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form id="frmEconomicIndicatorsItem" class="form-horizontal">
                <div class="modal-body">
                    <table class="table tree" id="frmEconomicIndicatorsItemTable">
                        <thead>
                        <tr>
                            <th>规划项目名称</th>
                            <th>规划建筑面积 ㎡</th>
                            <th>可出售面积 ㎡</th>
                            <th>个数</th>
                            <th>单位售价(元/㎡)</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="treegrid-1" data-key="groundBuildingArea" data-title="地上计入容积率建筑面积"
                            data-role="parent" data-parent-index="1">
                            <td>一: 地上计入容积率建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.appendItemChildren(this);"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td><label name="plannedBuildingArea"></label></td>
                            <td><label name="saleableArea"></label></td>
                            <td><label name="number"></label></td>
                            <td><label name="unitPrice"></label></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-2" data-key="groundExcludBuildingArea" data-title="地上不计入容积率建筑面积"
                            data-role="parent" data-parent-index="2">
                            <td>二: 地上不计入容积率建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.appendItemChildren(this);"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td><label name="plannedBuildingArea"></label></td>
                            <td><label name="saleableArea"></label></td>
                            <td><label name="number"></label></td>
                            <td><label name="unitPrice"></label></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-3" data-key="undergroundBuildingArea" data-title="地下建筑面积"
                            data-role="parent" data-parent-index="3">
                            <td>三: 地下建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.appendItemChildren(this);"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td><label name="plannedBuildingArea"></label></td>
                            <td><label name="saleableArea"></label></td>
                            <td><label name="number"></label></td>
                            <td><label name="unitPrice"></label></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-4" data-key="undergroundIncludedBuildingArea" data-title="地下不计入建筑面积"
                            data-role="parent" data-parent-index="4">
                            <td>四: 地下不计入建筑面积
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.appendItemChildren(this);"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td><label name="plannedBuildingArea"></label></td>
                            <td><label name="saleableArea"></label></td>
                            <td><label name="number"></label></td>
                            <td><label name="unitPrice"></label></td>
                            <td></td>
                        </tr>
                        <tr class="treegrid-5" data-key="otherBuildingArea" data-role="parent" data-parent-index="5">
                            <td>五: 其他
                                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                                   onclick="economicIndicators.appendItemChildren(this);"><i
                                        class="fa fa-plus fa-white"></i></a>
                            </td>
                            <td><label name="plannedBuildingArea"></label></td>
                            <td><label name="saleableArea"></label></td>
                            <td><label name="number"></label></td>
                            <td><label name="unitPrice"></label></td>
                            <td></td>
                        </tr>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td>预期销售合计:</td>
                            <td class="info">规划建筑面积(㎡)<label name="plannedBuildingArea"
                                                             class="label label-default"></label></td>
                            <td class="info">可售面积(㎡)<label name="saleableArea" class="label label-default"></label></td>
                            <td class="info">总可售面积售价(元)<label name="totalSaleableAreaPrice"
                                                              class="label label-default"></label></td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="economicIndicators.save();">
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
            <input type="text" name="name" value="{name}" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td><input type="text" value="{plannedBuildingArea}"
                   onblur="economicIndicators.autoSummary()"
                   name="plannedBuildingArea" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{saleableArea}"
                   onblur="economicIndicators.autoSummary()"
                   name="saleableArea" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{number}"
                   onblur="economicIndicators.autoSummary()"
                   name="number" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{unitPrice}"
                   onblur="economicIndicators.autoSummary()"
                   name="unitPrice" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/javascript">
    var economicIndicators = {};
    economicIndicators.saveCallback;
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
        $("#frmEconomicIndicators").clearAll();
        $("#frmEconomicIndicatorsItem").find('[data-role=child]').remove();
        $("#frmEconomicIndicators").find('[name=planDetailsId]').val(defaluts.planDetailsId);
        $("#frmEconomicIndicators").find('[name=centerId]').val(defaluts.centerId);
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
                        $("#frmEconomicIndicators").initForm(result.data.economicIndicators);
                        $("#frmEconomicIndicators").find("input[name='planDate']").val(formatDate(result.data.economicIndicators.planDate));
                        $("#frmEconomicIndicators").find("label[name='planDate']").html(formatDate(result.data.economicIndicators.planDate));
                        if (result.data.economicIndicatorsItemList) {
                            $.each(result.data.economicIndicatorsItemList, function (i, item) {
                                var dataKey = item.dataKey;
                                var parentTr = $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + '][data-role="parent"]');
                                var childTarget = $("#economicIndicatorsItemTemplate");
                                var html = childTarget.html();
                                html = html.replace(/{dataKey}/g, dataKey).replace(/{parentIndex}/g, parentTr.attr('data-parent-index'));
                                html = html.replace(/{currentIndex}/g, $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').length);
                                html = html.replace(/{name}/g, AssessCommon.toString(item.name)).replace(/{plannedBuildingArea}/g, AssessCommon.toString(item.plannedBuildingArea));
                                html = html.replace(/{saleableArea}/g, AssessCommon.toString(item.saleableArea)).replace(/{number}/g, AssessCommon.toString(item.number)).replace(/{remark}/g, AssessCommon.toString(item.remark));
                                html = html.replace(/{unitPrice}/g, AssessCommon.toString(item.unitPrice));
                                $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').last().after(html);
                                if (defaluts.attribute) {
                                    $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').each(function (i, n) {
                                        var target = $(n);
                                        target.find("td").first().find("a").remove();
                                    });
                                    $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').last().find("input").attr(defaluts.attribute);
                                }
                            });
                            economicIndicators.autoSummary();
                        }
                        if (defaluts.targetCallback) {
                            defaluts.targetCallback($("#frmEconomicIndicatorsItem"));
                        }
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        }
        if (defaluts.attribute) {
            $("#frmEconomicIndicators").find("input").attr(defaluts.attribute);
        }
        if (defaluts.showDelHtml) {
            var html = defaluts.showDelHtml.replace(/{frm}/g, 'frmEconomicIndicators');
            html = html.replace(/{box}/g, 'modalEconomicIndicators');
            $("#modalEconomicIndicators").find(".modal-footer").find(".btn-warning").remove();
            $("#modalEconomicIndicators").find(".modal-footer").find("button").eq(1).before(html);
        }
        if (defaluts.targetCallback) {
            defaluts.targetCallback($("#frmEconomicIndicatorsItem"));
        }
        if (defaluts.attribute) {
            $("#frmEconomicIndicatorsItem").find("tr").each(function (i, n) {
                var target = $(n);
                target.find("td").first().find("a").remove();
            });
            $("#modalEconomicIndicators").find(".modal-footer").find(".btn-primary").remove();
        }
        $("#modalEconomicIndicators").modal();
    };

    //保存
    economicIndicators.save = function () {
        if (!$("#frmEconomicIndicators").valid()) {
            return false;
        }
        var data = {};

        data.economicIndicators = formSerializeArray($("#frmEconomicIndicators"));
        data.economicIndicatorsItemList = [];
        $("#frmEconomicIndicatorsItem").find('tr[data-role="child"]').each(function () {
            var economicIndicatorsItem = {};
            economicIndicatorsItem.dataKey = $(this).attr('data-key');
            economicIndicatorsItem.planDetailsId = economicIndicators.planDetailsId;
            economicIndicatorsItem.name = $(this).find('[name=name]').val();
            economicIndicatorsItem.plannedBuildingArea = $(this).find('[name=plannedBuildingArea]').val();
            economicIndicatorsItem.saleableArea = $(this).find('[name=saleableArea]').val();
            economicIndicatorsItem.number = $(this).find('[name=number]').val();
            economicIndicatorsItem.unitPrice = $(this).find('[name=unitPrice]').val();
            economicIndicatorsItem.remark = $(this).find('[name=remark]').val();
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
                    if (economicIndicators.saveCallback)
                        economicIndicators.saveCallback(result.data.id);
                    $("#modalEconomicIndicators").modal('hide');
                    if (data.economicIndicatorsItemList.length >= 1) {

                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    //添加子项
    economicIndicators.appendItemChildren = function (_this) {
        var dataKey = $(_this).closest('tr').attr('data-key');
        var html = $("#economicIndicatorsItemTemplate").html();
        html = html.replace(/{dataKey}/g, dataKey).replace(/{parentIndex}/g, $(_this).closest('tr').attr('data-parent-index'));
        html = html.replace(/{currentIndex}/g, $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').length);
        html = html.replace(/{name}/g, '').replace(/{plannedBuildingArea}/g, '');
        html = html.replace(/{saleableArea}/g, '').replace(/{number}/g, '').replace(/{remark}/g, '');
        html = html.replace(/{unitPrice}/g, '');
        $("#frmEconomicIndicatorsItem").find('[data-key=' + dataKey + ']').last().after(html);
    };

    /**
     * 获取计算后的总值
     * @returns {{}}
     */
    economicIndicators.getFormData = function () {
        var target = $("#frmEconomicIndicatorsItem").find("table").find("tfoot");
        var head = formSerializeArray($("#frmEconomicIndicators"));
        var data = {};
        data.developLandAreaTax = head.assessUseLandArea;
        data.developBuildAreaTax = head.assessTotalBuildArea;
        data.plannedBuildingArea = target.find("label[name='plannedBuildingArea']").html();
        var totalSaleableAreaPrice = "0";
        try {
            totalSaleableAreaPrice = target.find("label[name='totalSaleableAreaPrice']").html();
            totalSaleableAreaPrice = Number(totalSaleableAreaPrice) / 10000;
            totalSaleableAreaPrice = totalSaleableAreaPrice.toFixed(2) ;
        } catch (e) {
        }
        data.totalSaleableAreaPrice = totalSaleableAreaPrice;
        data.saleableArea = target.find("label[name='saleableArea']").html();
        return data;
    };

    //自动汇总
    economicIndicators.autoSummary = function () {
        var target = $("#frmEconomicIndicatorsItem");
        var plannedBuildingAreaValue = 0, totalSaleableAreaPriceValue = 0, saleableAreaValue = 0;
        target.find('tr[data-role="parent"]').each(function () {
            var dataKey = $(this).attr('data-key');
            var numberTotal = 0, saleableAreaTotal = 0, plannedBuildingAreaTotal = 0, unitPriceTotal = 0,
                totalSaleableAreaPrice = 0;
            $("#frmEconomicIndicatorsItem").find('tr[data-role="child"][data-key=' + dataKey + ']').each(function () {
                var plannedBuildingArea = $(this).find('[name=plannedBuildingArea]').val();
                var saleableArea = $(this).find('[name=saleableArea]').val();
                var number = $(this).find('[name=number]').val();
                var unitPrice = $(this).find('[name=unitPrice]').val();
                if ($.isNumeric(plannedBuildingArea)) {
                    plannedBuildingAreaTotal += parseFloat(plannedBuildingArea);
                }
                if ($.isNumeric(saleableArea)) {
                    saleableAreaTotal += parseFloat(saleableArea);
                }
                if ($.isNumeric(number)) {
                    numberTotal += parseFloat(number);
                }
                if ($.isNumeric(unitPrice)) {
                    unitPriceTotal += parseFloat(unitPrice);
                }
                var algsValue = 0;
                if ($.isNumeric(unitPrice)) {
                    algsValue += parseFloat(unitPrice);
                }
                if ($.isNumeric(number)) {
                    algsValue += parseFloat(number);
                }
                if ($.isNumeric(saleableArea) && algsValue != 0) {
                    var temp = parseFloat(saleableArea) * algsValue;
                    totalSaleableAreaPrice += parseFloat(temp);
                }
            });

            $(this).find('[name=plannedBuildingArea]').text('');
            $(this).find('[name=saleableArea]').text('');
            $(this).find('[name=number]').text('');
            $(this).find('[name=unitPrice]').text('');

            if (plannedBuildingAreaTotal > 0) {
                $(this).find('[name=plannedBuildingArea]').text(plannedBuildingAreaTotal.toFixed(2));
                plannedBuildingAreaValue += plannedBuildingAreaTotal;
            }
            if (saleableAreaTotal > 0) {
                $(this).find('[name=saleableArea]').text(saleableAreaTotal.toFixed(2));
                saleableAreaValue += saleableAreaTotal;
            }
            if (numberTotal > 0) {
                $(this).find('[name=number]').text(numberTotal.toFixed(2));
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
    }
</script> 