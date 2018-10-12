<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/10/12
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>开发信息</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开发土地面积(㎡)
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text" name="developLandAreaTax"
                               placeholder=" 开发土地面积(㎡)" class="form-control" data-rule-number='true' required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开发建筑面积(㎡)
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text" name="developBuildAreaTax"
                               placeholder="开发建筑面积(㎡)" class="form-control" data-rule-number='true' required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    开发期（年）
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text" name="developYearNumberTax"
                               placeholder="开发期（年）" class="form-control" data-rule-number='true' required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    土地购买价格
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text" name="landPurchasePriceTax"
                               placeholder="土地购买价格" class="form-control" data-rule-number='true' required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    土地取得相关税费
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <input type="text" name="landGetRelevantTax"
                               placeholder="土地取得相关税费" class="form-control" >
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>