<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        单元
                    </h2>
                </div>
            </div>
            <div class="x_panel">


                <div class="x_content">
                    <form class="form-horizontal" id="basicUnitFrm">
                        <input type="hidden" name="id" value="${basicUnit.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元编号<span class="symbol required"></span></label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <div class="input-group">
                                        <label class="form-control" name="unitNumber">${basicUnit.unitNumber}</label>
                                        <div onclick="unitCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                                class="fa fa-map-marker"></i> 标注</div>
                                    </div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">梯户比<span class="symbol required"></span></label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control" name="elevatorHouseholdRatio">${basicUnit.elevatorHouseholdRatio}</label>
                                </div>
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型说明</label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <label class="form-control" name="huxingExplain">${basicUnit.huxingExplain}</label>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="x_content">
                    <%@include file="/views/project/stageSurvey/commonDetail/unitHuxing.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
                    <%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>
                </div>


            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/sonUnitView.js"></script>
<script type="text/javascript">
    $(function () {
        unitCommon.initById('${basicUnit.id}');
    })
</script>
</html>


