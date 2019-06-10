<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <form id="basicApplyFrm">
                <!--案例或者查勘 entity json-->
                <input type="hidden" id="surveySceneExploreJson" value='${surveySceneExploreJson}'>
                <input type="hidden" id="surveyCaseStudyJson" value='${surveyCaseStudyJson}'>
                <input type="hidden" name="caseEstateId" value="${basicApply.caseEstateId}">
                <input type="hidden" name="caseBuildingId" value="${basicApply.caseBuildingId}">
                <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
                <input type="hidden" name="caseHouseId" value="${basicApply.caseHouseId}">
                <input type="hidden" name="id" value="${basicApply.id}">
                <input type="hidden" id="declareId" name="declareId" value="${declareRecord.id}">
            </form>
            <!--填写表单-->
            <input type="hidden" id="planDetailsId" name="planDetailsId" value="${projectPlanDetails.pid}">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${empty declareRecord?projectPlanDetails.projectPhaseName: declareRecord.name} ${basicApply.typeName} </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content examine">
                    <ul class="nav nav-tabs bar_tabs task_examine_item_tab">
                        <c:if test="${not empty estateTaskList}">
                            <li class="tab_estate">
                                <a href="#tab_content_estate" data-name="estate" data-toggle="tab">楼盘</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty buildingTaskList}">
                            <li class="tab_building">
                                <a href="#tab_content_building" data-name="building" data-toggle="tab">楼栋</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty unitTaskList}">
                            <li class="tab_unit">
                                <a href="#tab_content_unit" data-name="unit" data-toggle="tab">单元</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty houseTaskList}">
                            <li class="tab_house">
                                <a href="#tab_content_house" data-name="house" data-toggle="tab">房屋</a>
                            </li>
                        </c:if>
                    </ul>
                    <div class="tab-content">
                        <c:if test="${not empty estateTaskList}">
                            <div class="tab-pane tab_estate" id="tab_content_estate">
                                <c:forEach items="${estateTaskList}" var="item">
                                    <jsp:include page="${item.detailUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty buildingTaskList}">
                            <div class="tab-pane tab_building" id="tab_content_building">
                                <c:forEach items="${buildingTaskList}" var="item">
                                    <jsp:include page="${item.detailUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty unitTaskList}">
                            <div class="tab-pane tab_unit" id="tab_content_unit">
                                <c:forEach items="${unitTaskList}" var="item">
                                    <jsp:include page="${item.detailUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty houseTaskList}">
                            <div class="tab-pane tab_house" id="tab_content_house">
                                <c:forEach items="${houseTaskList}" var="item">
                                    <jsp:include page="${item.detailUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<!-- 从表 -->
<script src='${pageContext.request.contextPath}/js/common.column.js'></script>
<!-- 表单js -->
<script src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.detail.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js"></script>
<script type="text/javascript">


    $(document).ready(function () {
        $(".task_examine_item_tab").find('a:first').tab('show');

        //附件显示
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileShow(item);
        });

        $.each(estateCommon.estateFileControlIdArray, function (i, n) {
            estateCommon.fileShow(n, AssessDBKey.BasicEstate, estateCommon.getEstateId());

        });
        var obj = {} ;
        try {
            obj = JSON.parse('${basicEstateLandState.landLevelContent}') ;
            if (estateCommon.isNotBlankObject(obj)){
                var objData = estateCommon.landLevelFilter(obj) ;
                estateCommon.landLevelLoadHtml(objData);
            }
        } catch (e) {
        }

        $.each(unitCommon.unitFileControlIdArray, function (i, n) {
            unitCommon.fileShow(n, true);
        });

        //初始化上传控件
        $.each(houseCommon.houseFileControlIdArray, function (i, item) {
            houseCommon.fileShow(item);
        });
        var tradingType = undefined;
        if ('${basicHouseTrading.tradingTypeName}' == '出租') {
            tradingType = AssessDicKey.examineHouseTransactionTypeLease;
        }
        if ('${basicHouseTrading.tradingTypeName}' == '出售') {
            tradingType = AssessDicKey.examineHouseTransactionTypeSell;
        }
        houseCommon.loadTradingSellAndLeaseList(tradingType, true);
        if(${not empty houseTaskList}) {
            houseCommon.detail(basicCommon.getApplyId(), function (data) {
                houseCommon.showUseCondition(data);
            });
        }
        //显示土地位置等字段
        houseCommon.showReplenishLand("${projectInfo.projectCategoryId}");
    });

    //审批提交
    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>

