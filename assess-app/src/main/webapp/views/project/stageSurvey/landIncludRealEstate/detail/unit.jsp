<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>单元基本信息</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        单元基本信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="basicUnitFrm">
                                    <input type="hidden" name="id" value="${basicUnit.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">单元编号<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input class="form-control" name="unitNumber" readonly
                                                               value="${basicUnit.unitNumber}">
                                                        <span class="input-group-btn">
                                        <div onclick="unitCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                                class="fa fa-map-marker"></i> 标注</div>
                                        </span>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">梯户比<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="elevatorHouseholdRatio">${basicUnit.elevatorHouseholdRatio}</label>
                                                </div>

                                                <label class="col-sm-1 control-label">户型说明</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"
                                                           name="huxingExplain">${basicUnit.huxingExplain}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:if test="${empty isHistory}">
                        <%@include file="/views/project/stageSurvey/commonDetail/unitHuxing.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitHuxingPriceDetail.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>
                    </c:if>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
    })
</script>
</html>


