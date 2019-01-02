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

            <!--案例或者查勘 entity json-->
            <input type="hidden" id="surveySceneExploreJson" value='${surveySceneExploreJson}'>
            <input type="hidden" id="surveyCaseStudyJson" value='${surveyCaseStudyJson}'>

            <div class="x_panel examine">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${declareRecord.name}-调查信息-${dataExamineTask.name} ${empty surveySceneExploreJson?'查勘':''} ${empty surveyCaseStudyJson?'案例':''}</h2>
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
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_save" class="btn btn-warning" onclick="save();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
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
<script src="${pageContext.request.contextPath}/js/select/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/select/block.select.js"></script>

<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js'></script>
<script src='${pageContext.request.contextPath}/js/common.column.js'></script>

<!-- 表单js -->
<script src="${pageContext.request.contextPath}/js/examine/examine.common.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.build.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.unit.js"></script>
<script src="${pageContext.request.contextPath}/js/examine/examine.house.js"></script>

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
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js"></script>


<script type="text/javascript">
    $(function () {
        //tab注册事件
        $('.task_examine_item_tab').find('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        });
        //选中第一个tab
        taskExamineItemIndex.selectFirstTab();
    });



    //任务提交
    function submit() {
        var formData = taskExamineItemIndex.getFormData();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    //保存
    function save() {
        taskExamineItemIndex.save();
    }
</script>
<script type="text/javascript">

    var taskExamineItemIndex = {
        //选择第一个tab
        selectFirstTab: function () {
            $(".task_examine_item_tab").find('a:first').tab('show');
        },

        //验证
        valid: function () {
            return true;
        },

        //获取表单数据
        getFormData: function () {
            return "" ;
        },

        //保存
        save: function () {
            //保存只验证填写的数据合法性
            $(".examine_content [required]").removeAttr('required').attr('data-required', 'true');
            if (!taskExamineItemIndex.valid()) {
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyExamine/saveExamineDataInfo",
                data: {
                    formData: JSON.stringify(taskExamineItemIndex.getFormData())
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        toastr.success("保存成功");
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        },

        //提交
        submit: function () {
            var formData = taskExamineItemIndex.getFormData();
            var data = {};
            var url = '${pageContext.request.contextPath}/surveyExamineItem/submitExamineDataInfo';
            if ("${processInsId}" != "0") {//返回修改
                data = formApproval.getFormData();
                url = '${pageContext.request.contextPath}/surveyExamineItem/submitEditExamineDataInfo';
            }
            data.formData = JSON.stringify(formData);
            data.planDetailsId = "${projectPlanDetails.id}";
            data.responsibilityId = "${responsibilityId}";
            Loading.progressShow();
            $.ajax({
                url: url,
                data: data,
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        //保存完后其他动作
                        Alert("提交成功", 1, null, function () {
                            window.close();
                        });
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        }
    };

    $(document).ready(function () {

        //初始化方法和值
        assessEstate.detail(examineCommon.getApplyId(),function (data) {
            assessEstate.initForm({estate:data.basicEstate,land:data.basicEstateLandState}) ;
        });

        buildingCommon.detail(examineCommon.getApplyId(),function (data) {
            buildingCommon.initForm({main:data,build:{}}) ;
        });

        unitCommon.detail(examineCommon.getApplyId(),function (data) {
            unitCommon.initForm(data);
        });

        houseCommon.detail(examineCommon.getApplyId(),function (data) {
            houseCommon.initForm(data) ;
        });

        //楼盘自动填充插件
        assessEstate.autocompleteStart() ;
    });
</script>
</html>

