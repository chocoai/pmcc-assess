<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                单元
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicUnitFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="单元编号"
                           name="unitNumber" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">梯户比<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="梯户比"
                           name="elevatorHouseholdRatio" class="form-control" required="required">
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/unit/sonUnitView.jsp" %>