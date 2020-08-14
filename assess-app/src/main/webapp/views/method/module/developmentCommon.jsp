<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="architecturalBModelParent" data-title="复杂树 父级">
    <tr class="treegrid-{index}" data-key="{key}" data-role="parent">
        <td>{name}</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</script>
<script type="text/html" id="architecturalBModelChildren" data-title="复杂树 子级">
    <tr class="treegrid-{index}-{childIndex} treegrid-parent-{index}" data-key="{key}" data-role="child">
        <td>{name}</td>
        <td>
            <input type="text" onblur="developmentCommon.architecturalB.totalResult(this,'{reckon}')" placeholder=""
                   name="price" style="width: 50%;" class="form-control input-full">
        </td>
        <td>
            <input type="text" onblur="developmentCommon.architecturalB.totalResult(this,'{reckon}')" placeholder=""
                   name="area" style="width: 50%;" class="form-control input-full">
        </td>
        <td><input type="text" class="x-percent form-control input-full"
                   onblur="developmentCommon.architecturalB.totalResult(this,'{reckon}')"
                   placeholder="" name="valuationDateDegreeCompletion" style="width: 50%;">
        </td>
        <td><input type="text" name="remark" placeholder="描述" style="width: 80%;" class="form-control input-full"></td>
        <td name="result"></td>
    </tr>
</script>
<script type="text/html" id="architecturalB" data-title="树干">
    <div data-view-name="b" style="display: none;">
        <label class="label-info label">计算方式 ==></label>
        <code>单价 ✖ 面积 ✖ (1-估价时点完工程度)</code>
    </div>

    <div data-view-name="a" style="display: none;">
        <label class="label-info label">计算方式 ==></label>
        <code>单价 ✖ 面积</code>
    </div>

    <div data-view-name="c" style="display: none;">
        <label class="label-info label">计算方式 ==></label>
        <code>单价 ✖ 面积 <code data-view-name="valuationDateDegreeCompletion"> ✖ 估价时点完工程度</code>
        </code>
    </div>
    <table class=" tree">
        <thead>
        <tr>
            <th name="name">工程名称</th>
            <th name="price">单价(元/㎡)</th>
            <th name="area">面积(㎡)</th>
            <th name="valuationDateDegreeCompletion">估价时点完工程度</th>
            <th name="remark">描述</th>
            <th name="result">计算值</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        <tr class="treegrid-99" data-key="architecturalEngineering" data-role="parent">
            <td>合计</td>
            <td><input type="text" name="totalPrice" class="form-control input-full" readonly="readonly"></td>
        </tr>
        </tfoot>
    </table>
</script>

<script type="text/html" id="architecturalAModelParent" data-title="简单树 父级">
    <tr class="treegrid-{index}" data-key="{key}" data-role="parent">
        <td>{name}</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</script>
<script type="text/html" id="architecturalAModelChildren" data-title="简单树 子级">
    <tr class="treegrid-{index}-{childIndex} treegrid-parent-{index}" data-key="{key}" data-role="child">
        <td>{name}</td>
        <td style="width: 20%;"><input type="text"
                                       onblur="developmentCommon.architecturalB.totalResult(this,'{reckon}')"
                                       placeholder="单价(数字)" name="price" class="form-control input-full"></td>
        <td style="width: 10%;">
            <input type="text" onblur="developmentCommon.architecturalB.totalResult(this,'{reckon}')" placeholder="面积"
                   name="area" class="form-control input-full">
        </td>
        <td><input type="text" name="remark" placeholder="描述" class="form-control input-full"></td>
        <td name="result"></td>
    </tr>
</script>


<div id="basicMdDevelopmentInfrastructureChildrenModalTool" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基础设施配套</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="basicMdDevelopmentInfrastructureChildrenModalFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10 col-md-10">
                                            <input type="text" name="name" class="form-control input-full"
                                                   required="required"
                                                   placeholder="名称">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            金额(元/㎡)
                                        </label>
                                        <div class="col-sm-10 col-md-10">
                                            <input type="text" name="number" placeholder="金额"
                                                   class="form-control input-full"
                                                   data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
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
    <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
        关闭
    </button>
    <button type="button" class="btn btn-primary btn-sm"
            onclick="landEngineering.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>

<script type="text/html" id="underConstructionMdDevelopmentInfrastructureFooterX">
    <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
        关闭
    </button>
    <button type="button" class="btn btn-primary btn-sm"
            onclick="construction.saveMdDevelopmentInfrastructureChildrenTable(this)">
        保存
    </button>
</script>

<!-- 建筑安装工程费 -->
<div id="boxLandEngineering_Install" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建安工程完工度调查表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="constructionInstallationEngineeringFeeId">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="'{method}'">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="boxSchemeInfoModel" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">测算方法数据引用</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="row form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div class="form-inline x-valid">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                            <table class="table table-bordered" id="boxSchemeInfoList">
                                            </table>
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
            </div>
        </div>
    </div>
</div>





