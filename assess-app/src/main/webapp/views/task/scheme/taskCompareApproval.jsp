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
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>


            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <div class="form-group">
                        <div class="x-valid">

                            <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                    <li role="presentation" class="active">
                                        <a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab"
                                           aria-expanded="true">
                                            估价对象与可比实例比较因素说明表
                                        </a>
                                    </li>
                                    <li role="presentation" class="">
                                        <a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab"
                                           aria-expanded="false">
                                            估价对象与可比实例修正指数表
                                        </a>
                                    </li>
                                    <li role="presentation" class="">
                                        <a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab"
                                           aria-expanded="false">
                                            比准价格测算表
                                        </a>
                                    </li>
                                    <li role="presentation" class="">
                                        <a href="#tab_content4" role="tab" id="profile-tab3" data-toggle="tab"
                                           aria-expanded="false">
                                            房价指数表
                                        </a>
                                    </li>
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade active in" id="tab_content1"
                                         aria-labelledby="home-tab">
                                        <%--第一页表--%>
                                        <table class="table table-bordered leftfloat" id="oneTable" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th class="gray">项目</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items" varStatus="s">
                                                    <th>
                                                        <span>${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content2"
                                         aria-labelledby="profile-tab">
                                        <%--第二页表--%>
                                        <table class="table table-bordered leftfloat" id="twoTable" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th class="gray">项目</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span>${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content3"
                                         aria-labelledby="profile-tab">
                                        <%--第三页表--%>
                                        <table class="table table-bordered leftfloat" id="threeTable"
                                               style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th class="gray">项目</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span>${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr id="trList">
                                                <th scope="row" class="gray">比准价格</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">加权平均价</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <th>
                                                        <span></span>
                                                    </th>
                                                </c:forEach>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content4"
                                         aria-labelledby="profile-tab">
                                        <%--时间指数表--%>
                                        <table class="table table-bordered leftfloat" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th>年份</th>
                                                <th>指数</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th>2017年10月</th>
                                                <td>1</td>

                                            </tr>
                                            <tr>
                                                <th>2017年11月</th>
                                                <td>2</td>

                                            </tr>
                                            <tr>
                                                <th>2017年12月</th>
                                                <td>3</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%--固定表格格式用--%>
                                <label class="col-sm-2 control-label rightfloat"></label>

                                <%--右侧固定表格 第四张表--%>
                                <table class="table table-bordered rightfloat" id="rightTable" style="width: 30%">
                                    <thead>
                                    <tr>
                                        <th class="gray">项目</th>
                                        <th class="gray evaluation" evaluation-id="${schemeEvaluationObject.id}">${schemeEvaluationObject.name}</th>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items" varStatus="s">
                                            <th class="gray data-th case" name="project" data-id="${items.id}">实例${s.index + 1}</th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row" class="gray">楼盘名称</th>
                                        <td>
                                            <span name="realEstateName" evaluation-id="${schemeEvaluationObject.id}">${surveyLocaleExploreDetail.houseName}</span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span class="case" name="realEstateName" data-id="${items.id}">${items.houseName}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">比准价格</th>
                                        <td>
                                            <span name="specificPrice" evaluation-id="${schemeEvaluationObject.id}"></span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span class="case" data-id="${items.id}" name="specificPrice"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">修正差额</th>
                                        <td>
                                            <span name="correctionDifference" evaluation-id="${schemeEvaluationObject.id}"></span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span class="case" data-id="${items.id}" name="correctionDifference"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">案例差异</th>
                                        <td>
                                            <span name="caseDifference" evaluation-id="${schemeEvaluationObject.id}"></span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span class="case" data-id="${items.id}" name="caseDifference"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">权重</th>
                                        <td>
                                            <span name="weight" evaluation-id="${schemeEvaluationObject.id}"></span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <input type="text" class="input case" data-id="${items.id}" name="weight"
                                                       min="0.0" max="1"
                                                       step="0.1"
                                                       style="width: 80px">
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">加权平均价</th>
                                        <td>
                                            <span name="weightedAveragePrice" evaluation-id="${schemeEvaluationObject.id}"></span>
                                        </td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span class="case"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    </tbody>
                                </table>


                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}工作成果</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control">${projectPlanDetails.actualHours}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectPlanDetails.taskRemarks}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <div id="_file_upload_task"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        getGray();

        GetFileShows("file_upload_task",
            {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}",
                processInsId: "${processInsId}"
            }, "0");
    })
    function saveform() {
        saveApprovalform("");
    }

    //颜色加灰
    function getGray() {
        $('.gray').css('background', '#C9C9C9');
        $('.gray').css('color', 'black');
        $('th,td').css('text-align', 'center');
//        $('table,td').css('border','1px,black');
        $('.leftfloat').css('float', 'left');
        $('.rightfloat').css('float', 'right');
    }

</script>
</body>
</html>

