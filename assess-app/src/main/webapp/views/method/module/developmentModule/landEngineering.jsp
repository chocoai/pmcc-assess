<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="x_panel">

    <input type="text"
           placeholder="f18" class="form-control" readonly="readonly"
           name="f18">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>单位成本或费率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="勘察设计和前期工程费率" class="form-control x-percent"
                           required="required"
                           name="f20" onblur="landEngineering.calculationD20()">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                建筑安装工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <div class="input-group">
                        <input type="text" readonly="readonly"
                               placeholder="建筑安装工程费"  class="form-control"
                               name="f21" onblur="landEngineering.calculationD21()">

                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="landEngineering.constructionInstallationEngineeringFeeEvent.show()">
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
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                基础设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <select name="f22"
                            class="form-control search-select select2" onchange="landEngineering.calculationD22(this)">
                        <option>请选择</option>
                        <option value="10">2009-2032  金额 10</option>
                        <c:forEach items="${dataInfrastructureList}" var="item">
                            <c:if test="${item.infrastructureSupportingFacilities != 0}">
                                <option value="${item.infrastructureSupportingFacilities}">${item.timeSlot} 金额:${item.infrastructureSupportingFacilities}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                公共配套设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <select name="f23"
                            class="form-control search-select select2 " onchange="landEngineering.calculationD23(this)">
                        <option>请选择</option>
                        <option value="10">2009-2032  金额 10</option>
                        <c:forEach items="${dataInfrastructureList}" var="item">
                            <c:if test="${item.communalFacilities != 0}">
                                <option value="${item.communalFacilities}">${item.timeSlot} 金额:${item.communalFacilities}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <select name="f24"
                            class="form-control search-select select2 " onchange="landEngineering.calculationD24()">
                        <option>请选择</option>
                        <option value="10">2009-2032  金额 10</option>
                        <c:forEach items="${dataInfrastructureList}" var="item">
                            <c:if test="${item.devTaxTotal != 0}">
                                <option value="${item.devTaxTotal}">${item.timeSlot} 金额:${item.devTaxTotal}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="其它工程费率" class="form-control x-percent" name="f25" onblur="landEngineering.calculationD25()">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                不可预见费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="不可预见费率" class="form-control x-percent" name="f27" onblur="landEngineering.calculationD27()">
                </div>
            </div>
        </div>
    </div>

    <input type="text"
           placeholder="勘察设计和前期工程费" class="form-control" readonly="readonly"
           name="d20" onblur="landEngineering.calculationD20()">

    <input type="text"
           placeholder="建筑安装工程费" class="form-control" readonly="readonly"
           name="d21"  onblur="landEngineering.calculationD21()">

    <input type="text"
           placeholder="基础设施费用" class="form-control" readonly="readonly"
           name="d22" >

    <input type="text"
           placeholder="公共配套设施建设费" class="form-control" readonly="readonly"
           name="d23">

    <input type="text"
           placeholder="开发期间税费" class="form-control" readonly="readonly"
           name="d24">

    <input type="text"
           placeholder="其它工程费" class="form-control" readonly="readonly"
           name="d25">

    <input type="text"
           placeholder="不可预见费" class="form-control" readonly="readonly"
           name="d27">


</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>设计费参数比率</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    契税率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="契税率" class="form-control x-percent"  required="required"
                           name="f29" onblur="landEngineering.calculationD28()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="f29Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交易费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="交易费率" class="form-control x-percent" required="required"
                           name="f30" onblur="landEngineering.calculationD28()">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="f30Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="管理费率" class="form-control x-percent" required="required"
                           name="g32" onblur="landEngineering.calculationD32();landEngineering.calculationF32()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="费率说明" class="form-control"
                           name="g32Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地取得附加成本" class="form-control" required="required" data-rule-number='true'
                           name="f31" onblur="landEngineering.calculationF32();landEngineering.calculationF35()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地取得附加成本说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地取得附加成本说明" class="form-control" name="f31Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售费用率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="销售费用率" class="form-control x-percent" required="required"
                           name="g33" onblur="landEngineering.calculationF33()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="g33Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="投资利息率" class="form-control x-percent" required="required"
                           name="g34" onblur="landEngineering.calculationD34();landEngineering.calculationF34()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="g34Explain">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    投资利润率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="投资利润率" class="form-control x-percent" required="required"
                           name="g35" onblur="landEngineering.calculationD35();landEngineering.calculationF35()">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="费率说明" class="form-control" name="g34Explain">
                </div>
            </div>
        </div>



    </div>

    <input type="text"
           placeholder="单元格d28" class="form-control" readonly="readonly"
           name="d28">

    <input type="text"
           placeholder="管理费" class="form-control" readonly="readonly"
           name="d32">

    <input type="text"
           placeholder="单元格f32" class="form-control" readonly="readonly"
           name="f32">

    <input type="text"
           placeholder="单元格f33" class="form-control" readonly="readonly"
           name="f33">

    <input type="text"
           placeholder="单元格d34" class="form-control" readonly="readonly"
           name="d34" onblur="landEngineering.calculationD34()">

    <input type="text"
           placeholder="单元格d35" class="form-control" readonly="readonly"
           name="d35" onblur="landEngineering.calculationD35()">

    <input type="text"
           placeholder="单元格h40" class="form-control" readonly="readonly"
           name="h40" onblur="landEngineering.calculationH40()">

</div>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>测算结果</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="form-group">
            <div class="col-md-12 col-sm-12">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <td> 总建筑面积小计（㎡）</td>
                        <td class="totalGrossFloorArea"></td>
                    </tr>
                    <tr>
                        <td> 工程建设成本小计</td>
                        <td class="d26" onblur="landEngineering.calculationD26()"></td>
                    </tr>
                    <tr>
                        <td> 投资利息</td>
                        <td class="f34"></td>
                    </tr>
                    <tr>
                        <td> 投资利润</td>
                        <td class="f35"></td>
                    </tr>
                    <tr>
                        <td> 委估土地单价（元/㎡）</td>
                        <td class="landPriceCorrecting"></td>
                    </tr>
                    <tr>
                        <td> 测算单价</td>
                        <td class="estimateUnitPriceLandC33"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!--  建筑安装工程费 -->
<div id="boxLandEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程费</h3>
            </div>
            <form id="boxLandEngineeringFrm" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="landEngineering.constructionInstallationEngineeringFeeEvent.save()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>



