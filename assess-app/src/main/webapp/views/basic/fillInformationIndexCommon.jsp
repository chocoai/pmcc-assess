<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
<script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/developer.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/builder.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/property.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/new.wind.brand.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/heating.brand.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/roomType.js"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/huxing.select.js"></script>

<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js"></script>
<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <h3>
            填写信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div role="tabpanel" id="contentTabPanel" data-example-id="togglable-tabs">
            <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                <li role="presentation" style="display: none;">
                    <a href="#caseEstate" role="tab" data-toggle="tab" id="profile-tab1" aria-expanded="true">
                        楼盘
                        <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseBuilding" role="tab" data-toggle="tab" id="profile-tab2" aria-expanded="true">
                        楼栋
                        <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseUnit" role="tab" data-toggle="tab" id="profile-tab3" aria-expanded="true">
                        单元
                        <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseHouse" role="tab" data-toggle="tab" id="profile-tab4" aria-expanded="true">
                        房屋
                        <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                    <div>
                        <jsp:include page="/views/basic/modelView/estateView.jsp" ></jsp:include>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseBuilding" aria-labelledby="profile-tab2">
                    <div>
                        <jsp:include page="/views/basic/modelView/buildingView.jsp" ></jsp:include>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                    <div>
                        <jsp:include page="/views/basic/modelView/unitView.jsp" ></jsp:include>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                    <div>
                        <jsp:include page="/views/basic/modelView/houseView.jsp" ></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/basic.common.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
<script type="text/javascript">
    $(function () {


        houseCommon.changeEvent();


        $('#contentTabPanel .fa-close').click(function (e) {
            basicCommon.hideTab(this);
            e.preventDefault();
            return false;
        });
    })
</script>