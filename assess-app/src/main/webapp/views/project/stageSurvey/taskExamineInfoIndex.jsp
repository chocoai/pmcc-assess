<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.css">
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
            </form>


            <div class="x_panel examine">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${empty declareRecord?projectPlanDetails.projectPhaseName: declareRecord.name} ${basicApply.typeName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content examine_content">
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
                                    <jsp:include page="${item.applyUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty buildingTaskList}">
                            <div class="tab-pane tab_building" id="tab_content_building">
                                <c:forEach items="${buildingTaskList}" var="item">
                                    <jsp:include page="${item.applyUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty unitTaskList}">
                            <div class="tab-pane tab_unit" id="tab_content_unit">
                                <c:forEach items="${unitTaskList}" var="item">
                                    <jsp:include page="${item.applyUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${not empty houseTaskList}">
                            <div class="tab-pane tab_house" id="tab_content_house">
                                <c:forEach items="${houseTaskList}" var="item">
                                    <jsp:include page="${item.applyUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消<i style="margin-left: 10px" class="fa fa-close"></i>
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-warning" onclick="saveData();">
                                    保存<i style="margin-left: 10px" class="fa fa-save"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <%@include file="/views/share/form_log.jsp" %>

        </div>
    </div>
</div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js"></script>
<!-- 控件js -->
<script src="${pageContext.request.contextPath}/js/autocomplete/developer.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/builder.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/property.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/new.wind.brand.js"></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/heating.brand.js"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/building.case.js'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/unit.case.js'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/house.case.js'></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/roomType.js'></script>

<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>
<script src="${pageContext.request.contextPath}/js/basic/huxing.select.js"></script>

<!-- 表单js -->
<script src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>

<!-- 从表 -->
<script src='${pageContext.request.contextPath}/js/common.column.js'></script>
<script src="${pageContext.request.contextPath}/js/basic/estate/sonEstateView.js"></script>
<script src="${pageContext.request.contextPath}/js/basic/building/sonBuildView.js"></script>
<script src="${pageContext.request.contextPath}/js/basic/unit/sonUnitView.js"></script>
<script src="${pageContext.request.contextPath}/js/basic/house/sonHouseView.js"></script>

<!-- 高德抓取周边数据 -->
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js"></script>


<script type="text/javascript">
    //任务提交
    function submit(mustUseBox) {
        //数据校验
        var formData = basicCommon.getFormData();
        console.log(formData) ;
        if (!basicCommon.valid()) {
            return false;
        }


        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".task_examine_item_tab").find('a:first').tab('show');
        //初始化方法和值
        estateCommon.detail(basicCommon.getApplyId(), function (data) {
            estateCommon.initForm({estate: data.basicEstate, land: data.basicEstateLandState});
        });

        buildingCommon.detail(basicCommon.getApplyId());

        unitCommon.detail(basicCommon.getApplyId(), function (data) {
            unitCommon.initForm(data);
        });

        houseCommon.detail(basicCommon.getApplyId(), function (data) {
            houseCommon.initForm(data);

            //房屋评估范围
            getProjectInfo("${projectPlanDetails.projectId}");
        });

        //启动自动填充控件

        //楼盘自动填充插件
        estateCommon.autocompleteStart();

        //楼栋自动填充插件
        buildingCommon.autocompleteStart();

        //单元自动填充插件
        unitCommon.autocompleteStart();

        //房屋自动填充插件
        houseCommon.autocompleteStart();

    });

    function saveData() {
        var formData = basicCommon.getFormData();

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTaskExamine/saveData",
            data: {
                formData: JSON.stringify(formData),
                projectDetailsId:${projectPlanDetails.id},
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {

                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function getProjectInfo(projectId){
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectTaskExamine/getProjectInfo",
            type: "post",
            dataType: "json",
            data: {
                projectId:projectId,
            },
            success: function (result) {
                if (result.ret) {
                    if(result.data){
                        var propertyScope = result.data.propertyScope;
                        houseCommon.houseTradingForm.find("input[name='scopeInclude']").val(result.data.scopeInclude);
                        houseCommon.houseTradingForm.find("input[name='scopeNotInclude']").val(result.data.scopeNotInclude);
                        houseCommon.houseTradingForm.find("select.scopeProperty").val(propertyScope).trigger('change');
                    }
                }
                else {
                    toastr.warning(result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>
</html>

