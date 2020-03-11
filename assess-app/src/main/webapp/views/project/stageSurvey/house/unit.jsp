<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>单元基本信息</h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicUnitFrm">
        <input type="hidden" name="id" value="${basicUnit.id}">
        <input type="hidden" name="quoteId" value="${basicUnit.quoteId}">
        <input type="hidden" name="estateId" value="${basicUnit.estateId}">
        <input type="hidden" name="buildingId" value="${basicUnit.buildingId}">
        <div class="row">
            <div class="col-md-12">
                <div class="card-body">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1">单元编号<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" data-rule-maxlength="100" placeholder="单元编号"
                                               required="required"
                                               name="unitNumber" class="form-control" value="${basicUnit.unitNumber}"
                                               id="txt_Unit_search">
                                        <span class="input-group-btn">
                            <input type="hidden" name="mapId" value="${basicUnit.mapId}">
                             <div onclick="unitCommon.mapMarker(false);" class="btn btn-info">
                                 <i class="fa fa-map-marker"></i> 标注
                             </div>
                        </span>
                                    </div>
                                </div>
                                <label class="col-sm-1">梯户比<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="梯户比"
                                           name="elevatorHouseholdRatio" class="form-control input-full"
                                           required="required"
                                           value="${basicUnit.elevatorHouseholdRatio}">
                                </div>
                                <c:if test="${formType eq 'industry'}">
                                    <label class="col-sm-1">户型说明</label>
                                    <div class="col-sm-3">
                                        <input type="text" name="huxingExplain" class="form-control input-full"
                                               value="${basicUnit.huxingExplain}">
                                    </div>
                                </c:if>
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
    <%@include file="/views/project/stageSurvey/common/unitDecorate.jsp" %>
    <%@include file="/views/project/stageSurvey/common/unitElevator.jsp" %>
    <%@include file="/views/project/stageSurvey/common/unitHuxingPrice.jsp" %>
</div>

<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/basic/basic.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
        $("#txt_Unit_search").apUnit({
            caseBuildingId: function () {
                return '${quoteId}';
            },
            onSelect: function (id, name) {
                caseFun.caseUnit.showModel('${quoteId}', name);
            }
        });
    })
</script>