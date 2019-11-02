<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel" id="basicApplyId">
    <script src="${pageContext.request.contextPath}/js/basic/industry.js?v=${assessVersion}"></script>
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <h3>
            ${basicApply.fullName}
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div role="tabpanel" data-example-id="togglable-tabs">
            <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                <c:if test="${not empty basicApply.estatePartInMode}">
                    <li role="presentation" class="">
                        <a href="#basicEstate" role="tab" data-toggle="tab" aria-expanded="true">楼盘
                            <c:if test="${basicApply.estatePartInMode eq 'upgrade'}">
                                <small>(升级版本)</small>
                            </c:if>
                        </a>
                    </li>
                </c:if>
                <c:if test="${not empty basicApply.buildingPartInMode}">
                    <li role="presentation" class="">
                        <a href="#caseBuild" role="tab" data-toggle="tab" aria-expanded="true">楼栋
                            <c:if test="${basicApply.buildingPartInMode eq 'upgrade'}">
                                <small>(升级版本)</small>
                            </c:if>
                        </a>
                    </li>
                </c:if>
                <c:if test="${not empty basicApply.unitPartInMode}">
                    <li role="presentation" class="">
                        <a href="#caseUnit" role="tab" data-toggle="tab" aria-expanded="true">单元
                            <c:if test="${basicApply.unitPartInMode eq 'upgrade'}">
                                <small>(升级版本)</small>
                            </c:if>
                        </a>
                    </li>
                </c:if>
                <c:if test="${not empty basicApply.housePartInMode}">
                    <li role="presentation" class="">
                        <a href="#caseHouse" role="tab" data-toggle="tab" aria-expanded="true">房屋
                            <c:if test="${basicApply.housePartInMode eq 'upgrade'}">
                                <small>(升级版本)</small>
                            </c:if>
                        </a>
                    </li>
                </c:if>
            </ul>
            <div class="tab-content">
                <c:if test="${not empty basicApply.estatePartInMode}">
                    <div role="tabpanel" class="tab-pane fade" id="basicEstate" aria-labelledby="profile-tab1">
                        <%@include file="/views/basic/modelView/estateDetail.jsp" %>
                    </div>
                </c:if>
                <c:if test="${not empty basicApply.buildingPartInMode}">
                    <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
                        <%@include file="/views/basic/modelView/buildDetail.jsp" %>
                    </div>
                </c:if>
                <c:if test="${not empty basicApply.unitPartInMode}">
                    <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                        <%@include file="/views/basic/modelView/unitDetail.jsp" %>
                    </div>
                </c:if>
                <c:if test="${not empty basicApply.housePartInMode}">
                    <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                        <%@include file="/views/basic/modelView/houseDetail.jsp" %>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basic/basic.common.js?v=${assessVersion}"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        //选项卡处理
        industry.keyApp("${basicApply.type}");

        if ("${basicApply.estatePartInMode}") {
            estateCommon.detail('${basicApply.id}', function (data) {
                estateCommon.initForm({estate: data.basicEstate, land: data.basicEstateLandState});
            });
        }


        if ("${basicApply.buildingPartInMode}") {
            buildingCommon.detail('${basicApply.id}',function(data){
                buildingCommon.initForm(data);
            });
        }

        if ("${basicApply.unitPartInMode}") {
            unitCommon.detail('${basicApply.id}', function (data) {
                unitCommon.initForm(data);
            });
        }

        if ("${basicApply.housePartInMode}") {
            houseCommon.detail('${basicApply.id}', function (data) {
                if (data.basicHouseTrading) {
                    if (AssessCommon.isNumber(data.basicHouseTrading.tradingType)) {
                        AssessCommon.getDataDicInfo(data.basicHouseTrading.tradingType, function (item) {
                            houseCommon.loadTradingSellAndLeaseList(item.fieldName, false);
                        });
                    }
                }
            });
        }

        $('#caseTab a').eq(0).tab('show');
    });
</script>