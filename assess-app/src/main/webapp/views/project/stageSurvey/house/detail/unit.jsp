<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>单元</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        详情
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="x_title">
                        <h3>单元基本信息</h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="basicUnitFrm">
                        <input type="hidden" name="id" value="${basicUnit.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元编号</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div class="input-group">
                                        <label class="form-control" name="unitNumber">${basicUnit.unitNumber}</label>
                                        <span class="input-group-btn">
                             <c:if test="${empty isApplyBatch}">
                            <div onclick="unitCommon.mapMarker(true);" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                             </c:if>
                              <c:if test="${isApplyBatch eq 'show'}">
                            <div onclick="unitCommon.mapMarker2(true,${tableId});" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                              </c:if>
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
                            <c:if test="${formType eq 'industry'}">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型说明</label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <label class="form-control"
                                               name="huxingExplain">${basicUnit.huxingExplain}</label>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
            <c:if test="${empty isHistory}">
                <div class="x_panel">
                    <div class="x_content">
                        <%@include file="/views/project/stageSurvey/commonDetail/unitHuxing.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitHuxingPriceDetail.jsp" %>
                    </div>
                </div>
            </c:if>

            <div class="x_panel">
                <%@include file="/views/project/chksCustomize/chksSurvey.jsp" %>

            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/chksCommon.jsp" %>
<%@include file="/views/project/tool/toolMapHandleView.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>


<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
        assessmentCommonHandle.loadChksServerViewTable();
    })
</script>
</html>
