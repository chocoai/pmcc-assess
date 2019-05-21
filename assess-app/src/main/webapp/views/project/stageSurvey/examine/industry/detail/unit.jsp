<%--
单元基本情况
--%>
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
                <label class="col-sm-1 control-label">单元编号</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <label class="form-control">${basicUnit.unitNumber}</label>
                        <span class="input-group-btn">
                            <div onclick="unitCommon.mapMarker(true);" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">梯户比</label>
                <div class="col-sm-3">
                    <label class="form-control">${basicUnit.elevatorHouseholdRatio}</label>
                </div>
            </div>
        </div>
    </form>
</div>