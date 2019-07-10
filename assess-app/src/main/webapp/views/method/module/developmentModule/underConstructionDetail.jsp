<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="x_panel">
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
                    <label class="form-control">${mdDevelopment.f20}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                建筑安装工程费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f21}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                基础设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f22}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                公共配套设施建设费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f23}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                开发期间税费<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f24}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                其它工程费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f25}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                不可预见费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <label class="form-control">${mdDevelopment.f27}</label>
                </div>
            </div>
        </div>
    </div>
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
                    <label class="form-control">${mdDevelopment.f29}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f29Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交易费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f30}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f30Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    续建管理费率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g32}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g32Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    在建工程修复费用<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f31}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    在建工程修复费用说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f31Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售费用率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g33}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g33Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    续建投资利息率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g34}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g34Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    续建投资利润（万元）<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g35}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g35Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    销售环节增值税及附加<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f37}</label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f37Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地增值税<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f38}</label>

                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f38Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目开发所得税<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f39}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    费率说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f39Explain}</label>
                </div>
            </div>
        </div>
    </div>



</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>地价修正</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地还原率<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${mdDevelopment.e43}</label>
                        <span class="input-group-btn">
                                    <input type="hidden" name="rewardRateId" value="${mdDevelopment.rewardRateId}">
                              <input type="button" class="btn btn-primary" value="报酬率测算"
                                     onclick="rewardRateDetail.calculationDetail('${mdDevelopment.rewardRateId}');"/>
                            </span>
                    </div>
                </div>
            </div>


        </div>

        <div class="form-group">
            <label class="col-sm-1 control-label">
                法定年限<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.f43}</label>
                </div>
            </div>

            <label class="col-sm-1 control-label">
                剩余年限<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.g43}</label>
                </div>
            </div>
        </div>



        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    权利状况修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d44}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d44Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    其他修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d45}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d45Explain}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开发程度修正<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d46}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    说明
                </label>
                <div class="col-sm-3">
                    <label class="form-control">${mdDevelopment.d46Explain}</label>
                </div>
            </div>
        </div>


    </div>
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
                        <td> 续建建设成本小计</td>
                        <td class="d26">${mdDevelopment.constructionCostSubtotal}</td>
                    </tr>
                    <tr>
                        <td> 续建投资利息</td>
                        <td class="f34">${mdDevelopment.interestInvestment}</td>
                    </tr>
                    <tr>
                        <td> 续建投资利润</td>
                        <td class="f35">${mdDevelopment.investmentProfit}</td>
                    </tr>
                    <tr>
                        <td> 在建工程单价（元/㎡）</td>
                        <td class="d41">${mdDevelopment.assessPrice}</td>
                    </tr>
                    <tr>
                        <td> 测算单价</td>
                        <td class="d47">${mdDevelopment.price}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>




