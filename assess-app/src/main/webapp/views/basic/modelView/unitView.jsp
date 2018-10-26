<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h2>

            <small>
                单元
            </small>
        </h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <div class="x_title">
            <h3>单元基本信息 </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <input type="hidden" name="id">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="单元编号" required="required"
                               name="unitNumber" class="form-control">
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">户梯比<span class="symbol required"></span></label>
                    <div class="col-sm-3">
                        <input type="text" placeholder="户梯比" required="required"
                               name="elevatorHouseholdRatio" class="form-control">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>