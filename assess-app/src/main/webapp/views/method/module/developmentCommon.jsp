<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="architecturalBModelParent" data-title="复杂树 父级">
    <tr class="treegrid-{index}" data-key="{key}" data-role="parent">
        <td>{name}</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</script>
<script type="text/html" id="architecturalBModelChildren" data-title="复杂树 子级">
    <tr class="treegrid-{index}-{childIndex} treegrid-parent-{index}" data-key="{key}" data-role="child">
        <td> {name}</td>
        <td><input type="text" onblur="developmentCommon.architecturalB.totalResult(this)" placeholder="单价(数字)"
                   name="price" style="width: 100px;"></td>
        <td><input type="text" class="x-percent" onblur="developmentCommon.architecturalB.totalResult(this)"
                   placeholder="(数字)" name="valuationDateDegreeCompletion"
                   style="width: 100px;"></td>
        <td><input type="text" name="remark"></td>
        <td></td>
    </tr>
</script>
<script type="text/html" id="architecturalB" data-title="复杂树">
    <table class="table tree" id="architecturalBHandle">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>单方造价(元/㎡)</th>
            <th>估价时点完工程度</th>
            <td>描述</td>
            <th>估价时点单价(元/㎡)</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
</script>

<script type="text/html" id="architecturalA" data-title="简单树">
    <table class="table tree" id="architecturalAHandle">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>单方造价(元/㎡)</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" class="form-control" readonly="readonly" name="totalPrice"></td>
        </tr>
        </tfoot>
    </table>
</script>
<script type="text/html" id="architecturalAModelParent" data-title="简单树 父级">
    <tr class="treegrid-{index}" data-key="{key}" data-role="parent">
        <td>{name}</td>
        <td></td>
    </tr>
</script>
<script type="text/html" id="architecturalAModelChildren" data-title="简单树 子级">
    <tr class="treegrid-{index}-{childIndex} treegrid-parent-{index}" data-key="{key}" data-role="child">
        <td>{name}</td>
        <td><input type="text" onblur="developmentCommon.architecturalA.totalResult(this)" placeholder="单价(数字)" name="price" style="width: 100px;"></td>
    </tr>
</script>

<div id="basicMdDevelopmentInfrastructureChildrenModalTool" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子类税费</h3>
            </div>
            <div class="panel-body">
                <form id="basicMdDevelopmentInfrastructureChildrenModalFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="name" class="form-control" required="required"
                                       placeholder="名称">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="number" placeholder="金额" class="form-control"
                                       data-rule-number='true'
                                       required="required">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

<script type="text/html" id="landEngineeringMdDevelopmentInfrastructureFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="landEngineering.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="underConstructionMdDevelopmentInfrastructureFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="underConstruction.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="underConstructionMdDevelopmentInfrastructureFooterX">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="construction.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>

<div id="basicMdDevelopmentIncomeCategoryModalTool" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经济指标</h3>
            </div>
            <div class="panel-body">
                <form id="basicMdDevelopmentIncomeCategoryModalFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <input type="hidden" name="planDetailsId">
                    <input type="hidden" name="type">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="name" class="form-control" required="required"
                                       placeholder="名称">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                规划建筑面积
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="plannedBuildingArea" placeholder="规划建筑面积" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                总可售面积售价(自动计算)
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" readonly="readonly" name="totalSaleableAreaPrice"
                                       placeholder="总可售面积售价(可售面积*单位售价/10000)" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                可售面积<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="saleableArea" placeholder="可售面积" class="form-control"
                                       required="required" onblur="developmentCommon.handleIncomeCategory(this)">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                个数
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="number" placeholder="个数" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                单位售价<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="unitPrice" placeholder="单位售价" class="form-control"
                                       required="required" onblur="developmentCommon.handleIncomeCategory(this)">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                评估面积
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="assessArea" placeholder="评估面积" class="form-control"
                                       data-rule-number='true'>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                说明
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <textarea name="remark" placeholder="说明" class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>
<script type="text/html" id="mdCostConstructionMdDevelopmentIncomeCategoryFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="construction.saveMdDevelopmentIncomeCategoryTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="landMdDevelopmentIncomeCategoryFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="landEngineering.saveMdDevelopmentIncomeCategoryTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="engineeringMdDevelopmentIncomeCategoryFooter">
    <button type="button" data-dismiss="modal" class="btn btn-default">
        取消
    </button>
    <button type="button" class="btn btn-primary"
            onclick="underConstruction.saveMdDevelopmentIncomeCategoryTable(this)">
        保存
    </button>
</script>

<!--  建筑安装工程费 -->
<div id="boxLandEngineering_Install" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程完工度调查表</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">

                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="constructionInstallationEngineeringFeeId">
            <input type="hidden" name="masterId">
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="'{method}'">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


