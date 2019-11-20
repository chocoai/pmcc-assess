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
        <input type="hidden" name="applyBatchId" value="${basicApplyBatch.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input class="form-control" name="unitNumber" readonly value="${basicUnit.unitNumber}">
                        <span class="input-group-btn">
                            <input type="hidden" name="mapId" value="${basicUnit.mapId}">
                             <div onclick="unitCommon.mapMarker(true);" class="btn btn-info">
                                 <i class="fa fa-map-marker"></i> 标注
                             </div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">梯户比</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control"
                           name="elevatorHouseholdRatio">${basicUnit.elevatorHouseholdRatio}</label>
                </div>
            </div>
            <c:if test="${basicApplyBatch.type == 1}">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型说明</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="huxingExplain">${basicUnit.huxingExplain}</label>
                    </div>
                </div>
            </c:if>
        </div>
    </form>
</div>

<div class="x_content">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_unit_appearance"></div>
                </div>
            </div>
        </div>
    </form>
</div>
