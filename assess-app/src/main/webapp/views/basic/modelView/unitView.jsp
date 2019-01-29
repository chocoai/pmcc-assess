<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>单元基本信息</h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicUnitFrm">
        <input type="hidden" name="id" value="${basicUnit.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" data-rule-maxlength="100" placeholder="单元编号" required="required"
                               name="unitNumber" class="form-control" value="${basicUnit.unitNumber}">
                        <span class="input-group-btn">
                            <div onclick="unitCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">梯户比<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="梯户比"
                           name="elevatorHouseholdRatio" class="form-control" required="required" value="${basicUnit.elevatorHouseholdRatio}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-3">
                    <input id="unit_appearance" placeholder="上传附件" class="form-control" type="file">
                    <div id="_unit_appearance"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/unit/sonUnitView.jsp" %>
