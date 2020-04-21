<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    收入类
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <table class="table table-bordered" id="tb_lease_income_list">
                </table>
            </form>

        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    成本类
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <table class="table table-bordered" id="tb_lease_cost_list">
                </table>
            </form>

        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    参数
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frm_lease" class="form-horizontal" enctype="multipart/form-data">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label" title="报酬率">
                                报酬率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" required class="form-control x-percent" name="rewardRate"
                                           placeholder="报酬率" readonly="readonly"
                                           data-value="${mdIncome.rewardRate}" onblur="lease.computeNetProfit();">
                                    <span class="input-group-btn">
                                    <input type="hidden" name="rewardRateId" value="${mdIncome.rewardRateId}">
                              <input type="button" class="btn btn-primary" value="报酬率测算"
                                     onclick="lease.getRewardRate(this);"/>
                            </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tb_lease_parameter_list">
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="col-md-12 lease_info" style="display: none;">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    测算结果
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="leaseResult" class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                估价对象的价格
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full" data-name="price">${mdIncome.price}</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row row form-group">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>收益期限(n)</th>
                                <th>毛收入</th>
                                <th>运营费</th>
                                <th>房地产年净收益</th>
                                <th>年期修正系数(h)</th>
                                <th>收益现值系数(k)</th>
                                <th>房地产收益价格</th>
                            </tr>
                            </thead>
                            <tbody id="leaseResultBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modal_lease_income" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">收入</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_lease_income" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <input type="hidden" name="mcId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                月租金收入(元/m²)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" name="rentalIncome" placeholder="月租金收入(元/m²)"
                                                           data-rule-number="true"
                                                           class="form-control" required="required">
                                                    <span class="input-group-btn">
                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                               onclick="lease.callCompareMethod(this);"/>
                                    </span>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                全年月份数<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="monthNumber" placeholder="全年月份数"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                月租金收入说明
                                            </label>
                                            <div class="col-sm-10">
                                <textarea name="rentalIncomeRemark" placeholder="月租金收入说明"
                                          class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                出租率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="rentals" placeholder="出租率"
                                                       class="form-control input-full x-percent"
                                                       required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                出租率说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="rentalsRemark" placeholder="出租率说明"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                年押金(元/m²)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="deposit" data-rule-number="true"
                                                       placeholder="年押金(元/m²)"
                                                       onblur="lease.computeOtherIncome(this);"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                押金说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="depositRemark" placeholder="押金说明"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                押金利率(一年期定期存款利率)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="depositRate" placeholder="押金利率(一年期定期存款利率)"
                                                       onblur="lease.computeOtherIncome(this);"
                                                       class="form-control input-full x-percent"
                                                       required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                利率说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="depositRateRemark" placeholder="利率说明"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                年其他收入<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="otherIncome" placeholder="年其他收入"
                                                       readonly="readonly"
                                                       class="form-control input-full" required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                其它收入说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="otherIncomeRemark" placeholder="其它收入说明"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                有效收缴率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="additionalCapture" placeholder="有效收缴率"
                                                       class="form-control input-full x-percent"
                                                       required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                有效收缴率说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="additionalCaptureRemark" placeholder="有效收缴率说明"
                                                       class="form-control input-full"
                                                       required="required">
                                            </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="lease.saveLease()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="modal_lease_cost" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">成本</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_lease_cost" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                管理费率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="managementCostRatio" placeholder="管理费率"
                                                       class="form-control input-full x-percent" required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                重置价格(元/m²)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="replacementValue" placeholder="重置价格(元/m²)"
                                                       data-rule-number="true"
                                                       class="form-control input-full " required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                维护保养费率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="maintenanceCostRatio" placeholder="维护保养费率"
                                                       class="form-control input-full x-percent" required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                保险费率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="insurancePremiumRatio" placeholder="保险费率"
                                                       class="form-control input-full x-percent" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                土地使用税<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="landUseTax" placeholder="土地使用税"
                                                       class="form-control input-full"
                                                       data-rule-number="true"
                                                       required="required">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                其它相关税费率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="transactionTaxeFeeRatio" placeholder="其它相关税费率"
                                                       onblur="lease.replaceTransactionTaxeFeeRatio();"
                                                       class="form-control input-full x-percent"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                其它相关税费率说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                <textarea name="transactionTaxeFeeExplain" placeholder="其它相关税费率说明"
                                          data-template="${transactionTaxeFeeRatioEditable}"
                                          class="form-control input-full"
                                          required="required"></textarea>
                                                <span id="transactionTaxeFeeRatioReadonly"
                                                      data-template="${transactionTaxeFeeRatioReadonly}"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房产税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="propertyTaxRatio" placeholder="房产税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                印花税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="stampDutyRatio" placeholder="印花税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                增值税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="salesTaxRatio" placeholder="增值税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                城建税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="constructionTaxRatio" placeholder="城建税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                地方教育费附加税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="localEducationRatio" placeholder="地方教育费附加税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                教育费附加税率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="educationRatio" placeholder="教育费附加税率"
                                                       onblur="lease.computeAdditionalRatio(this);"
                                                       class="form-control input-full x-percent">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                租赁税费率
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="additionalRatio" readonly="readonly"
                                                       placeholder="租赁税费率"
                                                       class="form-control input-full x-percent">
                                            </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="lease.saveLeaseCost()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="modal_lease_parameter" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">参数</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_lease_parameter" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="sectionId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                租金增长率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" name="rentalGrowthRate" placeholder="租金增长率"
                                                       onblur="lease.replaceRentalGrowthRate();"
                                                       class="form-control input-full x-percent"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                增长率说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                <textarea name="rentalGrowthRateExplain" placeholder="租金增长率说明"
                                          class="form-control input-full"
                                          data-template="${rentalGrowthRateExplainEditable}" required></textarea>
                                                <span id="rentalGrowthRateExplainReadonly"
                                                      data-template="${rentalGrowthRateExplainReadonly}"></span>
                                            </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="lease.saveLeaseParameter()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="text/html" id="leaseResultHtml">
    <tr data-id="{id}">
        <td>
            <input type="hidden" data-name="rentalGrowthRate" value="{rentalGrowthRate}">
            <label>{beginDate}</label>
        </td>
        <td><label data-name="endDate">{endDate}</label></td>
        <td><label data-name="yearCount">{yearCount}</label></td>
        <td><label data-name="incomeTotal">{incomeTotal}</label></td>
        <td><label data-name="costTotal">{costTotal}</label></td>
        <td><label data-name="netProfit">{netProfit}</label></td>
        <td><label data-name="correctionFactor">{correctionFactor}</label></td>
        <td><label data-name="presentValueFactor">{presentValueFactor}</label></td>
        <td><label data-name="incomePrice">{incomePrice}</label></td>
    </tr>
</script>

<script type="text/javascript">
    var lease = {};
    var leaseCostSourceId = undefined;
    //调用市场比较法
    lease.callCompareMethod = function (_this) {
        var mcId = $("#frm_lease_income").find('[name=mcId]').val();
        var frame = layer.open({
            type: 2,
            title: '市场比较法',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/marketCompare/index?mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.saveResult(function (mcId, price) {
                    $(_this).closest('form').find('[name=mcId]').val(mcId);
                    $(_this).closest('form').find('[name=rentalIncome]').val(price);
                    layer.closeAll('iframe');
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    $(_this).closest('form').find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            }
        });
        layer.full(frame);
    }

    //获取报酬率
    lease.getRewardRate = function (_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue));
                lease.computeNetProfit();
            }
        })
    }

    //编辑收入信息
    lease.editLease = function (index) {
        var row = $("#tb_lease_income_list").bootstrapTable('getData')[index];
        $("#frm_lease_income").clearAll();
        $("#frm_lease_income").initForm(row);
        $("#frm_lease_income").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        $('#modal_lease_income').modal();
    }

    //保存收入信息
    lease.saveLease = function () {
        if (!$("#frm_lease_income").valid()) {
            return false;
        }
        var data = formParams("frm_lease_income");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateLease",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    lease.loadLeaseList();
                    lease.loadCalculationResult();
                    $('#modal_lease_income').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //加载收入列表信息
    lease.loadLeaseList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'rentalIncome', title: '月租金收入'});
        cols.push({
            field: 'rentals', title: '出租率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'monthNumber', title: '全年月份数'});
        cols.push({field: 'deposit', title: '押金'});
        cols.push({
            field: 'depositRate', title: '押金利率(一年定期存款利率)', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'otherIncome', title: '年其他收入'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-primary tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLease(' + index + ');" ><i class="fa fa-pen fa-white"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_income_list").bootstrapTable('destroy');
        TableInit("tb_lease_income_list", "${pageContext.request.contextPath}/income/getLeaseList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                lease.computeNetProfit();
            }
        });
    }

    //编辑成本信息
    lease.editLeaseCost = function (index) {
        var row = $("#tb_lease_cost_list").bootstrapTable('getData')[index];
        var form = $("#frm_lease_cost");
        form.clearAll().initForm(row);
        form.find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        lease.replaceTransactionTaxeFeeRatio();
        var transactionTaxeFeeExplain = form.find('[name=transactionTaxeFeeExplain]');
        if (!transactionTaxeFeeExplain.val()) {
            transactionTaxeFeeExplain.val(transactionTaxeFeeExplain.attr('data-template'));
        }
        //重置价格特殊处理
        var $replacementValue = form.find('[name=replacementValue]');
        if (!$replacementValue.val()) {
            $replacementValue.val("${replacementValue}");
        }
        //房产税率
        var $propertyTaxRatio = form.find('[name=propertyTaxRatio]');
        if (!$propertyTaxRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($propertyTaxRatio.attr('data-value', "${propertyTaxRatio}"));
        }
        //印花税率
        var $stampDutyRatio = form.find('[name=stampDutyRatio]');
        if (!$stampDutyRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($stampDutyRatio.attr('data-value', "${stampDutyRatio}"));
        }
        //增值税率
        var $salesTaxRatio = form.find('[name=salesTaxRatio]');
        if (!$salesTaxRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($salesTaxRatio.attr('data-value', "${salesTaxRatio}"));
        }
        //城建税率
        var $constructionTaxRatio = form.find('[name=constructionTaxRatio]');
        if (!$constructionTaxRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($constructionTaxRatio.attr('data-value', "${constructionTaxRatio}"));
        }
        //地方教育费附加税率
        var $localEducationRatio = form.find('[name=localEducationRatio]');
        if (!$localEducationRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($localEducationRatio.attr('data-value', "${localEducationRatio}"));
        }
        //教育费附加税率
        var $educationRatio = form.find('[name=educationRatio]');
        if (!$educationRatio.attr('data-value')) {
            AssessCommon.elementParsePercent($educationRatio.attr('data-value', "${educationRatio}"));
        }
        lease.computeAdditionalRatio($educationRatio);//计算一次租赁税费
        $('#modal_lease_cost').modal();
    }

    //保存成本信息
    lease.saveLeaseCost = function () {
        var form = $("#frm_lease_cost");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        data.transactionTaxeFeeExplainSupplement = form.find('[id=transactionTaxeFeeRatioReadonly]').text();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateLeaseCost",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    lease.loadLeaseCostList();
                    lease.loadCalculationResult();
                    $('#modal_lease_cost').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //加载成本列表信息
    lease.loadLeaseCostList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({
            field: 'managementCostRatio', title: '管理费率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'replacementValue', title: '重置价格'});
        cols.push({
            field: 'maintenanceCostRatio', title: '维修保养费率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'additionalRatio', title: '租赁税费率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'insurancePremiumRatio', title: '保险费率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'landUseTax', title: '土地使用税'});
        cols.push({
            field: 'transactionTaxeFeeRatio', title: '交易税费率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-primary tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLeaseCost(' + index + ');" ><i class="fa fa-pen fa-white"></i></button>';
                str += " <button type='button' style='margin-left: 5px;' onclick='lease.copyLeaseCostItem(" + row.id + ");' data-placement='top' data-original-title='复制' class='btn btn-xs btn-info btn-copy tooltips' ><i class='fa fa-copy fa-white'></i></button>";
                str += " <button type='button' style='margin-left: 5px;' onclick='lease.pasteLeaseCostItem(" + row.id + ");' data-placement='top' data-original-title='粘贴' class='btn btn-xs btn-warning tooltips tooltips' ><i class='fa fa-paste fa-white'></i></button>";
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_cost_list").bootstrapTable('destroy');
        TableInit("tb_lease_cost_list", "${pageContext.request.contextPath}/income/getLeaseCostList", cols, {
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                lease.computeNetProfit();
            }
        });
    }

    //复制
    lease.copyLeaseCostItem = function (sourceId) {
        leaseCostSourceId = sourceId
        notifySuccess("成功", "复制成功");
    }

    //粘贴
    lease.pasteLeaseCostItem = function (targetId) {
        if (leaseCostSourceId) {
            if (leaseCostSourceId == targetId) {
                notifyInfo("提示", "不能复制粘贴自身");
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/income/pasteLeaseCost",
                data: {
                    sourceId: leaseCostSourceId,
                    targetId: targetId,
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        lease.loadLeaseCostList();
                        notifyInfo("提示", "粘贴成功");
                    } else {
                        AlertError("复制失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        } else {
            notifyInfo("提示", "选择被复制的对象");
        }
    }

    //编辑参数信息
    lease.editLeaseParameter = function (index) {
        var row = $("#tb_lease_parameter_list").bootstrapTable('getData')[index];
        $("#frm_lease_parameter").clearAll().initForm(row);
        $("#frm_lease_parameter").find('.x-percent').each(function () {
            $(this).attr('data-value', row[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        lease.replaceRentalGrowthRate();
        var rentalGrowthRateExplain = $("#frm_lease_parameter").find('[name=rentalGrowthRateExplain]');
        if (!rentalGrowthRateExplain.val()) {
            rentalGrowthRateExplain.val(rentalGrowthRateExplain.attr('data-template'));
        }
        $('#modal_lease_parameter').modal();
    }

    //保存参数信息
    lease.saveLeaseParameter = function () {
        var form = $("#frm_lease_parameter");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        data.rentalGrowthExplainSupplement = form.find('[id=rentalGrowthRateExplainReadonly]').text();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/updateDateSection",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    lease.loadLeaseParameterList();
                    lease.loadCalculationResult();
                    $('#modal_lease_parameter').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //加载参数列表信息
    lease.loadLeaseParameterList = function () {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '时间段', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false) + "至" + formatDate(row.endDate, false);
            }
        });
        cols.push({
            field: 'rentalGrowthRate', title: '租金增长率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'rentalGrowthRateExplain', title: '增长率说明'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-primary tooltips" data-placement="top" data-original-title="编辑" onclick="lease.editLeaseParameter(' + index + ');" ><i class="fa fa-pen fa-white"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_lease_parameter_list").bootstrapTable('destroy');
        TableInit("tb_lease_parameter_list", "${pageContext.request.contextPath}/income/getDateSectionList", cols, {
            operationMode: incomeIndex.getOperationMode(),
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //加载测算结果数据
    lease.loadCalculationResult = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/income/getDateSectionList",
            type: "get",
            dataType: "json",
            data: {
                incomeId: $("#frm_income").find('[name=id]').val(),
                limit: 100,
                operationMode: incomeIndex.getOperationMode()
            },
            success: function (result) {
                if (result.rows) {
                    $("#leaseResultBody").empty();
                    $(result.rows).each(function (i, item) {
                        var html = $('#leaseResultHtml').html();
                        html = html.replace(/{id}/g, item.id).replace(/{beginDate}/g, formatDate(item.beginDate, false));
                        html = html.replace(/{endDate}/g, formatDate(item.endDate, false)).replace(/{yearCount}/g, item.yearCount);
                        html = html.replace(/{incomeTotal}/g, AssessCommon.toString(item.incomeTotal)).replace(/{costTotal}/g, AssessCommon.toString(item.costTotal));
                        html = html.replace(/{rentalGrowthRate}/g, AssessCommon.toString(item.rentalGrowthRate));
                        html = html.replace(/{netProfit}/g, AssessCommon.toString(item.netProfit)).replace(/{correctionFactor}/g, AssessCommon.toString(item.correctionFactor));
                        html = html.replace(/{presentValueFactor}/g, AssessCommon.toString(item.presentValueFactor)).replace(/{incomePrice}/g, AssessCommon.toString(item.incomePrice));
                        $("#leaseResultBody").append(html);
                    })
                    lease.computeNetProfit();
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //获取表单内容
    lease.getData = function () {
        var formData = {};
        formData.mdIncome = {};
        formData.mdIncome.id = $("#frm_income").find('[name=id]').val();
        formData.mdIncome.houseRemainingYear = $("#frm_income").find('[name=houseRemainingYear]').val();
        formData.mdIncome.landRemainingYear = $("#frm_income").find('[name=landRemainingYear]').val();
        formData.mdIncome.name = $("#frm_income").find('[name=name]').val();
        formData.mdIncome.restrictionExplain = $("#frm_income").find('[name=restrictionExplain]').val();
        formData.mdIncome.price = $("#leaseResult").find('[data-name=price]').text();
        formData.mdIncome.operationMode = incomeIndex.getOperationMode();
        formData.mdIncome.leaseMode = $("#frm_income").find('[name=leaseMode]:checked').val();
        formData.mdIncome.rewardRate = $("#frm_lease").find('[name=rewardRate]').attr('data-value');
        formData.mdIncome.rewardRateId = $("#frm_lease").find('[name=rewardRateId]').val();
        formData.dateSectionList = [];
        $("#leaseResultBody").find('tr').each(function () {
            var section = {};
            section.id = $(this).attr('data-id');
            section.netProfit = $(this).find('[data-name=netProfit]').text();
            section.correctionFactor = $(this).find('[data-name=correctionFactor]').text();
            section.presentValueFactor = $(this).find('[data-name=presentValueFactor]').text();
            section.incomePrice = $(this).find('[data-name=incomePrice]').text();
            formData.dateSectionList.push(section);
        })
        return formData;
    }

    //替换租金增长率
    lease.replaceRentalGrowthRate = function () {
        var form = $("#frm_lease_parameter");
        var value = form.find('[name=rentalGrowthRate]').val();
        var span = form.find('[id=rentalGrowthRateExplainReadonly]');
        span.text(span.attr('data-template').replace('{收益法租金增长率}', value));
    }

    //替换其它相关费率
    lease.replaceTransactionTaxeFeeRatio = function () {
        var form = $("#frm_lease_cost");
        var value = form.find('[name=transactionTaxeFeeRatio]').val();
        var span = form.find('[id=transactionTaxeFeeRatioReadonly]');
        span.text(span.attr('data-template').replace('{收益法交易费率}', value));
    }
</script>
<%--测算--%>
<script type="text/javascript">
    //计算租赁税费 房产税+印花税+增值税*(1+城建税+地方教育费附加+教育费附加)
    lease.computeAdditionalRatio = function (_this) {
        var form = $(_this).closest('form');
        var propertyTaxRatio = form.find('[name=propertyTaxRatio]').attr('data-value');
        var stampDutyRatio = form.find('[name=stampDutyRatio]').attr('data-value');
        var salesTaxRatio = form.find('[name=salesTaxRatio]').attr('data-value');
        var constructionTaxRatio = form.find('[name=constructionTaxRatio]').attr('data-value');
        var localEducationRatio = form.find('[name=localEducationRatio]').attr('data-value');
        var educationRatio = form.find('[name=educationRatio]').attr('data-value');
        propertyTaxRatio = AssessCommon.isNumber(propertyTaxRatio) ? parseFloat(propertyTaxRatio) : 0;//房产税费
        stampDutyRatio = AssessCommon.isNumber(stampDutyRatio) ? parseFloat(stampDutyRatio) : 0;//印花税率
        salesTaxRatio = AssessCommon.isNumber(salesTaxRatio) ? parseFloat(salesTaxRatio) : 0;//增值税率
        constructionTaxRatio = AssessCommon.isNumber(constructionTaxRatio) ? parseFloat(constructionTaxRatio) : 0;//城建税率
        localEducationRatio = AssessCommon.isNumber(localEducationRatio) ? parseFloat(localEducationRatio) : 0;//地方教育税附加税率
        educationRatio = AssessCommon.isNumber(educationRatio) ? parseFloat(educationRatio) : 0;//教育费附加税率

        salesTaxRatio = salesTaxRatio / (1 + 0.05);
        propertyTaxRatio = propertyTaxRatio / (1 + 0.05);
        var result = propertyTaxRatio + stampDutyRatio + salesTaxRatio * (1 + constructionTaxRatio + localEducationRatio + educationRatio);
        AssessCommon.elementParsePercent(form.find('[name=additionalRatio]').attr('data-value', result.toFixed(4)));
    }

    //计算其它收入
    lease.computeOtherIncome = function (_this) {
        //押金*押金利率
        var form = $(_this).closest('form');
        var deposit = form.find('[name=deposit]').val();
        var depositRate = form.find('[name=depositRate]').attr('data-value');
        if (AssessCommon.isNumber(deposit) && AssessCommon.isNumber(depositRate)) {
            deposit = parseFloat(deposit);
            depositRate = parseFloat(depositRate);
            form.find('[name=otherIncome]').val((deposit * depositRate).toFixed(2));
        }
    }

    //计算净收益
    lease.computeNetProfit = function () {
        $("#leaseResultBody").find('tr').each(function () {
            var incomeTotal = $(this).find('[data-name=incomeTotal]').text();
            var costTotal = $(this).find('[data-name=costTotal]').text();
            if (!AssessCommon.isNumber(incomeTotal)) return false;
            if (!AssessCommon.isNumber(costTotal)) return false;
            incomeTotal = parseFloat(incomeTotal);
            costTotal = parseFloat(costTotal);
            //净收益
            var netProfit = incomeTotal - costTotal;
            $(this).find('[data-name=netProfit]').text(netProfit.toFixed(2));
        })

        lease.computePrice();
    }

    //计算年期修正系数[(h)=(1-((1+g)/(1+r))^n]、收益现值系数[(k)=h/(r-g)]、房地产收益价格[房地产收益价格*收益现值系数]
    lease.computePrice = function () {
        var r = $("#frm_lease").find('[name=rewardRate]').attr('data-value');//报酬率
        if (!AssessCommon.isNumber(r)) return false;
        r = parseFloat(r);
        var incomePriceTotal = 0;//收益价格合计
        $("#leaseResultBody").find('tr').each(function () {
            //判断有无结束时间，确定其计算方法
            var endDate = $(this).find('[data-name=endDate]').text();
            var netProfit = $(this).find('[data-name=netProfit]').text();//净收益
            if (!AssessCommon.isNumber(netProfit)) return false;
            netProfit = parseFloat(netProfit);
            var incomePrice = 0;

            var n = 0;
            $(this).prevAll().each(function () {
                var yearCount = $(this).find('[data-name=yearCount]').text();
                if (AssessCommon.isNumber(yearCount)) {
                    n += parseFloat(yearCount);
                }
            })
            if (endDate) {
                var yc = $(this).find('[data-name=yearCount]').text();
                if (!AssessCommon.isNumber(yc)) return false;
                yc = parseFloat(yc);//期限

                var g = $(this).find('[data-name=rentalGrowthRate]').val();
                if (!AssessCommon.isNumber(g)) return false;
                g = parseFloat(g);//租金增长率
                var h = (1 - Math.pow((1 + g) / (1 + r), yc)).toFixed(6);//年期修正系数
                var k = 0;
                k = (h / (r - g) / Math.pow((1 + r), n)).toFixed(6);//收益现值系数
                $(this).find('[data-name=correctionFactor]').text(h);
                $(this).find('[data-name=presentValueFactor]').text(k);
                incomePrice = (netProfit * k).toFixed(2);
            } else {
                //找出前几段的年限总和，如果为0则不参与运算
                if (n == 0) {
                    incomePrice = ((netProfit / r) / (1 + r)).toFixed(2);
                } else {
                    incomePrice = ((netProfit / r) / Math.pow((1 + r), n)).toFixed(2);
                }
            }
            incomePriceTotal = incomePriceTotal + parseFloat(incomePrice);
            $(this).find('[data-name=incomePrice]').text(incomePrice);//收益价格
        })
        //计算委估对象单价 （单价=收益价格合计\委估对象面积）
        $("#leaseResult").find('[data-name=price]').text(incomePriceTotal.toFixed(0));
    }
</script>



