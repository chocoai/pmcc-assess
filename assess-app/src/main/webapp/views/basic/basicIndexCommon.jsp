<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
<script src="${pageContext.request.contextPath}/js/select/developer.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/builder.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/property.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>
<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <h3>
            ${basicApply.id eq 0?'申请信息':basicApply.fullName}
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div role="tabpanel" id="contentTabPanel" data-example-id="togglable-tabs">
            <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                <li role="presentation" style="display: none;">
                    <a href="#caseEstate" role="tab" data-toggle="tab" id="profile-tab1" aria-expanded="true">
                        楼盘
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseBuilding" role="tab" data-toggle="tab" id="profile-tab2" aria-expanded="true">
                        楼栋
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseUnit" role="tab" data-toggle="tab" id="profile-tab3" aria-expanded="true">
                        单元
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseHouse" role="tab" data-toggle="tab" id="profile-tab4" aria-expanded="true">
                        房屋
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                    <div>
                        <%@include file="/views/basic/modelView/estateView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseBuilding" aria-labelledby="profile-tab2">
                    <div>
                        <%@include file="/views/basic/modelView/buildingView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                    <div>
                        <%@include file="/views/basic/modelView/unitView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                    <div>
                        <%@include file="/views/basic/modelView/houseView.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/basic.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/estate/estate.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/building/building.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/unit/unit.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/house/house.common.js"></script>
<script type="text/javascript">
    $(function () {
        $('#contentTabPanel .fa-close').click(function (e) {
            basicCommon.hideTab(this);
            e.preventDefault();
            return false;
        })

        houseCommon.changeEvent();
    })
</script>