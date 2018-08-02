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
            <div class="x_panel examine">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${declareRecord.name}-查勘信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <ul class="nav nav-tabs bar_tabs">
                        <c:if test="${not empty blockTaskList}">
                            <li class="tab_block">
                                <a href="#tab_content_block" data-toggle="tab">版块</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty estateTaskList}">
                            <li class="tab_estate">
                                <a href="#tab_content_estate" data-toggle="tab">楼盘</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty buildingTaskList}">
                            <li class="tab_building">
                                <a href="#tab_content_building" data-toggle="tab">楼栋</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty unitTaskList}">
                            <li class="tab_unit">
                                <a href="#tab_content_unit" data-toggle="tab">单元</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty houseTaskList}">
                            <li class="tab_house">
                                <a href="#tab_content_house" data-toggle="tab">房屋</a>
                            </li>
                        </c:if>
                    </ul>
                    <div class="tab-content">
                        <c:if test="${not empty blockTaskList}">
                            <div class="tab-pane active" id="tab_content_block">
                                <c:forEach items="${blockTaskList}" var="item">
                                    <jsp:include page="${item.applyUrl}">
                                        <jsp:param value="${item.fieldName}" name="fieldName"/>
                                    </jsp:include>
                                </c:forEach>
                            </div>
                        </c:if>
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

                <div class="col-sm-12" style="text-align: center;">
                    <button class="btn btn-default" onclick="window.close();">
                        取 消
                    </button>
                    <button class="btn btn-primary" onclick="taskExamineItemIndex.save();">
                        保 存
                    </button>
                    <button class="btn btn-success" onclick="taskExamineItemIndex.submit();">
                        提 交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                    </button>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
</div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        taskExamineItemIndex.selectFirstTab();
    })
</script>
<script type="text/javascript">
    var ContainerFunForValid = [];//数据验证方法容器
    var ContainerFunForGetData = [];//获取数据方法容器
    var ContainerFunInit  = function () {
        
    }
    //用作 选项框的初始化
    ContainerFunInit.prototype = {
        estate:function () {
            estateFun.prototype.init();
            estateFun.prototype.select2Init();//必须在init之后
            estateFun.prototype.viewFiles();
        },
        building:function () {
            examineBuilding_.prototype.viewInit();
            examineBuilding_.prototype.uploadFiles();
        },
        estateLandState:function () {
            estateLandState.prototype.init();
            estateLandState.prototype.select2Init();//必须在init之后
        },
        houseTrading:function () {
            examineHouseTrading.prototype.init();//此方法可以在加载选择框时才初始化 (方法已经加入同步否则select2无法赋值)
            examineHouseTrading.prototype.select2Init();//必须在init之后
        },
        house:function () {
            houseFun.prototype.files();
            houseFun.prototype.init(); //同步加载数据之后才能够select2赋值
            houseFun.prototype.select2Init();//处理select2赋值 必须在init之后
        }
    }
    ContainerFunInit.prototype.estate();
    ContainerFunInit.prototype.building();
    ContainerFunInit.prototype.estateLandState();
    ContainerFunInit.prototype.houseTrading();
    ContainerFunInit.prototype.house();
    var taskExamineItemIndex = {
        //选择第一个tab
        selectFirstTab: function () {
            $(".examine .nav-tabs").find('a:first').tab('show');
        },

        //验证
        valid: function () {
            console.log(ContainerFunForValid);
            if (ContainerFunForValid.length > 0) {
                for(var i=0;i<ContainerFunForValid.length;i++){
                    if(!ContainerFunForValid[i]()){
                        return false;
                    }
                }
            }
            return false;
        },

        //获取表单数据
        getFormData: function () {
            //读取到各个子表单提供的数据
            if (ContainerFunForGetData.length <= 0) {
                return false;
            }
            var formDataArray = [];
            $.each(ContainerFunForGetData, function (i, fn) {
                var data = fn();
                if (!data) return true;
                if (Array.isArray(data)) {
                    $.each(data, function (k, info) {
                        formDataArray.push(info);
                    })
                } else {
                    formDataArray.push(data);
                }
            })
            return formDataArray;
        },

        //保存
        save: function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyExamineItem/saveExamineDataInfo",
                data: {
                    formData: JSON.stringify(taskExamineItemIndex.getFormData())
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        console.info(taskExamineItemIndex.getFormData());
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
//            if (!taskExamineItemIndex.valid()) {
//                return false;
//            }
            var formData = taskExamineItemIndex.getFormData();
            var data = {};
            var url='${pageContext.request.contextPath}/surveyExamineItem/submitExamineDataInfo';
            if ("${processInsId}" != "0") {
                data = formParams("frm_approval");
                url='${pageContext.request.contextPath}/surveyExamineItem/submitEditExamineDataInfo';
            }
            data.formData=JSON.stringify(formData);
            data.planDetailsId= "${projectPlanDetails.id}";
            data.responsibilityId= "${responsibilityId}";
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
</script>
</html>

