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


            <!--填写表单-->
            <input type="hidden" id="declareId" name="declareId" value="${declareRecord.id}">
            <input type="hidden" id="examineType" name="examineType" value="${examineType}">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${declareRecord.name}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content examine">
                    <ul class="nav nav-tabs bar_tabs task_examine_item_tab">
                        <c:if test="${not empty blockTaskList}">
                            <li class="tab_block">
                                <a href="#tab_content_block" data-name="block" data-toggle="tab">版块</a>
                            </li>
                        </c:if>
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
                        <c:if test="${not empty blockTaskList}">
                            <div class="tab-pane active" id="tab_content_block">
                                <c:forEach items="${blockTaskList}" var="item">
                                    <jsp:include page="${item.detailUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
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
<script type="text/javascript">
    $(function () {
        //tab注册事件
        $('.task_examine_item_tab').find('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var dataName = $(this).attr('data-name');
            if ($.inArray(dataName, ContainerFunForInitRecord) < 0) {
                for (var i = 0; i < ContainerFunForInit[dataName].length; i++) {
                    ContainerFunForInit[dataName][i]();
                }
                ContainerFunForInitRecord.push(dataName);
            }
        });

        taskExamineItemApproval.selectFirstTab();
    })
</script>
<script type="text/javascript">
    var ContainerFunForInit = {"block": [], "estate": [], "building": [], "unit": [], "house": []};//数据初始化方法容器
    var ContainerFunForInitRecord = [];//数据初始化记录

    var taskExamineItemApproval = {
        //选择第一个tab
        selectFirstTab:function () {
            $(".task_examine_item_tab").find('a:first').tab('show');
        }
    };

    //审批提交
    function saveform() {
        if (!formApproval.valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyExamineItem/taskApproval",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
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
        })
    }
</script>
</body>
</html>

