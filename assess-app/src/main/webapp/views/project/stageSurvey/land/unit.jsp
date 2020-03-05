<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>单元基本信息</h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicUnitFrm">
        <input type="hidden" name="id" value="${basicUnit.id}">
        <div class="row">
            <div class="col-md-12">
                <div class="card-body">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">单元编号<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" data-rule-maxlength="100" placeholder="单元编号"
                                               required="required"
                                               name="unitNumber" class="form-control"
                                               value="${basicUnit.unitNumber}"
                                               id="txt_Unit_search">
                                        <span class="input-group-btn">
                            <c:if test="${empty isApplyBatch}">
                            <div onclick="unitCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </c:if>
                              <c:if test="${isApplyBatch eq 'show'}">
                            <div onclick="unitCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                              </c:if>
                        </span>
                                    </div>
                                </div>

                                <label class="col-sm-1 control-label">梯户比<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="梯户比"
                                           name="elevatorHouseholdRatio" class="form-control input-full"
                                           required="required"
                                           value="${basicUnit.elevatorHouseholdRatio}">
                                </div>

                                <label class="col-sm-1 control-label">户型说明</label>
                                <div class="col-sm-3">
                                    <input type="text" name="huxingExplain" class="form-control input-full"
                                           value="${basicUnit.huxingExplain}">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="x_content">
    <%@include file="/views/project/stageSurvey/common/unitHuxing.jsp" %>
    <%@include file="/views/project/stageSurvey/common/unitHuxingPrice.jsp" %>
    <%@include file="/views/project/stageSurvey/common/unitDecorate.jsp" %>
    <%@include file="/views/project/stageSurvey/common/unitElevator.jsp" %>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
    })
</script>