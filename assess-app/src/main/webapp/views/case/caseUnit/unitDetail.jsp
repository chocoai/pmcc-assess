<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>单元基本信息 </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal" id="frm_Unit">
            <input type="hidden" name="id" value="${caseUnit.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">单元编号</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <label class="form-control">${caseUnit.unitNumber}</label>
                            <span class="input-group-btn">
                            <div onclick="caseCommon.viewMapMarker('${caseUnit.id}','unit','');" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">梯户比</label>
                    <div class="col-sm-3">
                        <label class="form-control">${caseUnit.elevatorHouseholdRatio}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">外观图</label>
                    <div class="col-sm-3">
                        <div id="_unit_appearance"></div>
                    </div>
                </div>
            </div>
        </form>

        <!-- 楼栋内装信息	 -->
        <div style="display: ${hasUnitDecorateData?'block':'none'};">
            <%@include file="/views/case/caseUnit/caseUnitDecorate.jsp" %>
        </div>

        <!-- 户型信息 -->
        <div style="display: ${hasUnitHuxingData?'block':'none'};">
            <%@include file="/views/case/caseUnit/caseUnitHuxing.jsp" %>
        </div>

        <!-- 电梯信息 -->
        <div style="display: ${hasUnitElevatorData?'block':'none'};">
            <%@include file="/views/case/caseUnit/caseUnitElevator.jsp" %>
        </div>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/case/case.common.js"></script>