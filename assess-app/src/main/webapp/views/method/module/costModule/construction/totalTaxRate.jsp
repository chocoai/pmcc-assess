<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/10/12
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>合计税率 (税率配置处配置)</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        契税率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="契税率" class="form-control" readonly="readonly"
                               name="deedTax">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        交易费率
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="交易费率" class="form-control" readonly="readonly"
                               name="transactionCostTax">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        地方教育费附加
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="地方教育费附加" class="form-control" readonly="readonly"
                               name="localEducationTax">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        印花税
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="印花税" class="form-control" readonly="readonly"
                               name="stampDuty">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        营业税
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="营业税" class="form-control" readonly="readonly"
                               name="businessTax">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        城建税
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="城建税" class="form-control" readonly="readonly"
                               name="urbanMaintenanceTax">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        教育费附加
                    </label>
                    <div class="col-sm-3">
                        <input type="text"
                               placeholder="教育费附加" class="form-control" readonly="readonly"
                               name="educationTax">
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>