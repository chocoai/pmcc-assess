<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <h3>
                        详情信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <c:if test="${buildingType eq 'estate'}">
                                <li role="presentation" class="">
                                    <a href="#basicEstate" role="tab" data-toggle="tab" aria-expanded="true">楼盘
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${buildingType eq 'building'}">
                                <li role="presentation" class="">
                                    <a href="#caseBuild" role="tab" data-toggle="tab" aria-expanded="true">楼栋
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${buildingType eq 'unit'}">
                                <li role="presentation" class="">
                                    <a href="#caseUnit" role="tab" data-toggle="tab" aria-expanded="true">单元
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${buildingType eq 'house'}">
                                <li role="presentation" class="">
                                    <a href="#caseHouse" role="tab" data-toggle="tab" aria-expanded="true">房屋
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                        <div class="tab-content">
                            <c:if test="${buildingType eq 'estate'}">
                                <div role="tabpanel" class="tab-pane fade" id="basicEstate"
                                     aria-labelledby="profile-tab1">
                                    <%@include file="/views/basic/modelView/estateDetail.jsp" %>
                                </div>
                            </c:if>
                            <c:if test="${buildingType eq 'building'}">
                                <div role="tabpanel" class="tab-pane fade" id="caseBuild"
                                     aria-labelledby="profile-tab2">
                                    <%@include file="/views/basic/modelView/buildDetail.jsp" %>
                                </div>
                            </c:if>
                            <c:if test="${buildingType eq 'unit'}">
                                <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                    <%@include file="/views/basic/modelView/unitDetail.jsp" %>
                                </div>
                            </c:if>
                            <c:if test="${buildingType eq 'house'}">
                                <div role="tabpanel" class="tab-pane fade" id="caseHouse"
                                     aria-labelledby="profile-tab4">
                                    <%@include file="/views/basic/modelView/houseDetail.jsp" %>
                                </div>
                            </c:if>
                        </div>
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
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/basic.common.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
</html>
<script type="text/javascript">
    $(function () {
        //选项卡处理
        industry.keyApp("${type}");
        $('#caseTab a').eq(0).tab('show');



console.log("buildingType"+"${buildingType}"+" id:"+"${tableId}");

        if ("estate"=="${buildingType}") {
            $.each(estateCommon.estateFileControlIdArray, function (i, n) {
                estateCommon.fileShow(n, AssessDBKey.BasicEstate, "${tableId}");
            });
        }
        if ("building"=="${buildingType}") {
            buildingCommon.tableId = "${tableId}";
            $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                buildingCommon.fileShow(item);
            });
        }
        if ("unit"=="${buildingType}") {
            unitCommon.tableId = "${tableId}";
            $.each(unitCommon.unitFileControlIdArray, function (i, item) {
                unitCommon.fileShow(item);
            });
        }
        if ("house"=="${buildingType}") {
            houseCommon.tableId = "${tableId}";
            $.each(houseCommon.houseFileControlIdArray, function (i, item) {
                houseCommon.fileShow(item, false);
            })
        }
    });
</script>