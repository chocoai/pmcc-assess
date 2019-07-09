<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>开发信息</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
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
                    <div class="input-group">
                        <input type="text" name="landPurchasePriceTax" placeholder="土地购买价格" data-rule-number="true"
                               class="form-control" required="required">
                        <span class="input-group-btn">
                                        <input type="button" class="btn btn-primary" value="市场比较法"
                                               onclick="construction.callCompareMethod(this);">
                                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

