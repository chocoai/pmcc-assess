<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/10/8
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>建设成本</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    勘察设计和前期工程费率
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text"
                               placeholder="勘察设计和前期工程费率" class="form-control x-percent" data-rule-number='true' required="required"
                               name="reconnaissanceDesignTax">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    建筑安装工程费
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <input type="text" readonly="readonly"
                                   placeholder="建筑安装工程费" value="0" class="form-control" name="constructionInstallationEngineeringFeeTax">
                            <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="landEngineering.constructionInstallationEngineeringFeeEvent.show();">
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
                    基础设施建设费
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <select name="infrastructureCostTax"
                                class="form-control search-select select2 infrastructureCostTax">
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    公共配套设施建设费
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <select name="infrastructureMatchingCostTax"
                                class="form-control search-select select2 infrastructureMatchingCostTax">
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开发期间税费
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text"
                               placeholder="开发期间税费" class="form-control" name="devDuringTax">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    其它工程费率
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text"
                               placeholder="其它工程费率" class="form-control x-percent" name="otherEngineeringCostTax">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    不可预见费率
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text"
                               placeholder="不可预见费率" class="form-control x-percent" name="unforeseenExpensesTax">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>