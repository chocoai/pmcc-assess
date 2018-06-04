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
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <input type="hidden" data-id="${items.id}" data-json='${items.jsonContent}'>
                                                    <th class="gray data-th" data-id="${items.id}" data-type="${items.type}">
                                                        <span name="project" data-id="${items.id}">${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="houseName" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="caseType" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="price" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="dealCaondition" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="dealTime" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareFactors}" var="items">
                                                    <td>
                                                        <span name="paymentMethod" data-id="${items.id}"></span>
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
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <input type="hidden" data-id="${items.id}" data-json='${items.jsonContent}'>
                                                    <th class="gray data-th" data-id="${items.id}" data-type="${items.type}">
                                                        <span name="project" data-id="${items.id}">${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="houseName" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="caseType" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="price" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <c:choose>
                                                        <c:when test="${s.index==0}">
                                                            <td>
                                                                <span name="dealCaondition" data-id="${items.id}"></span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <input type="text" class="allMethod input" data-id="${items.id}"
                                                                       name="dealCaondition" required
                                                                       data-rule-digits="true"
                                                                       min="80" max="120" step="1" style="width: 80px">
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <c:choose>
                                                        <c:when test="${s.index==0}">
                                                            <td>
                                                                <span name="dealTime" data-id="${items.id}"></span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <input type="text" class="allMethod input" data-id="${items.id}"
                                                                       name="dealTime" required
                                                                       data-rule-digits="true"
                                                                       min="80" max="120" step="1" style="width: 80px">
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items" varStatus="s">
                                                    <c:choose>
                                                        <c:when test="${s.index==0}">
                                                            <td>
                                                                <span name="paymentMethod" data-id="${items.id}"></span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <input type="text" class="allMethod showData" data-id="${items.id}"
                                                                       name="paymentMethod" required
                                                                       data-rule-digits="true"
                                                                       min="80" max="120" step="1" style="width: 80px">
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
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
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <input type="hidden" class="data" data-id="${items.compareIndexId}" data-json='${items.jsonContent}'>
                                                    <th class="gray data-th" data-id="${items.id}" data-index="${items.compareIndexId}" data-type="${items.type}">
                                                        <span name="project" data-id="${items.id}" data-index="${items.compareIndexId}">${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="houseName" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="caseType" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="price" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="dealCaondition" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="dealTime" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="paymentMethod" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr id="trList">
                                                <th scope="row" class="gray">比准价格</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <c:choose>
                                                        <c:when test="${s.index==0}">
                                                            <td>
                                                                <span></span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <span name="affirmPrice" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">加权平均价</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items" varStatus="s">
                                                    <c:choose>
                                                        <c:when test="${s.index==0}">
                                                            <td>
                                                                <span name="threeMiddlePrice" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"></span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <span></span>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content4"
                                         aria-labelledby="profile-tab">
                                        <%--时间指数表--%>
                                        <table class="table table-bordered leftfloat" style="width: 20%">
                                            <thead>
                                            <tr >
                                                <th class="gray">年份</th>
                                                <th class="gray">指数</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${housePriceIndexs}" var="items">
                                                <tr>
                                                    <td>
                                                        <span>
                                                            <fmt:formatDate value="${items.yearMonthCalendar}" pattern="yyyy-MM"/>
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <span>${items.indexCalendar}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
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
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <th class="gray data-th" name="project" data-id="${items.id}" data-index="${items.compareIndexId}" data-type="${items.type}">
                                                ${items.name}
                                            </th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row" class="gray">楼盘名称</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span name="realEstateName" data-index="${items.compareIndexId}" data-id="${items.id}">${items.realEstateName}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">比准价格</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items" varStatus="s">
                                            <c:choose>
                                                <c:when test="${s.index==0}">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <span data-id="${items.compareIndexId}" data-index="${items.compareIndexId}" name="specificPrice">${items.specificPrice}</span>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">修正差额</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items" varStatus="s">
                                            <c:choose>
                                                <c:when test="${s.index==0}">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <span data-id="${items.compareIndexId}" data-index="${items.compareIndexId}" name="correctionDifference">${items.correctionDifference}</span>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">案例差异</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items" varStatus="s">
                                            <c:choose>
                                                <c:when test="${s.index==0}">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <span data-id="${items.compareIndexId}" data-index="${items.compareIndexId}" name="caseDifference">${items.caseDifference}</span>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">权重</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items" varStatus="s">
                                            <c:choose>
                                                <c:when test="${s.index==0}">
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <input type="text" class="allMethod input" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}"
                                                               name="weight" required data-rule-digits="true" value="${items.weight}"
                                                               min="80" max="120" step="1" style="width: 80px">
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">加权平均价</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items" varStatus="s">
                                            <c:choose>
                                                <c:when test="${s.index==0}">
                                                    <td>
                                                        <span name="weightedAveragePrice" data-index="${items.compareIndexId}" data-id="${items.compareIndexId}">${items.weightedAveragePrice}</span>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <span></span>
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>
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
                    <h2>${projectPlanDetails.projectPhaseName}成果提交</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_task" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="实际工时" data-rule-number='true'
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3"
                                    value="${projectPlanDetails.actualHours}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" id="taskRemarks" name="taskRemarks"
                                                  class="form-control">${projectPlanDetails.taskRemarks}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <input id="apply_file" name="apply_file" type="file" multiple="false">
                                <div id="_apply_file">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
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
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {

        getGray();

        showData();

        $("#frm_task").validate();

        loadUploadFiles();
        //上传附件
        FileUtils.uploadFiles({
            target: "apply_file",
            showFileList: false,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        }, {
            onUploadComplete: function () {
                loadUploadFiles();
            }
        });
    });
    //显示附件
    function loadUploadFiles() {
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })

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

    //处理第二三四张表业务
    $("#twoTable").find("input:text").blur(function () {
        var reg = /^[0-9]+.?[0-9]*$/;   //小数整数
        var re = /^[1-9]\d*$/;  //正整数
        //1.判断大小
        var number = $(this).val();
//        console.log(number);
        if ((number < 80 || number > 120) || !re.test(number)) {
            Alert("请填写80-120范围内的数字", 1, null, function () {
            });
        }

        if (number > 100) {
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-up btn-danger"></i>');
        }
        if (number < 100) {
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-down btn-info"></i>');
        }
        if (number == "" || number == 100) {
            $(this).closest("td").find("i").remove();
        }

        //2.判断第二张表是否全部填完
        var allFill = true;
        $("#twoTable").find("input:text").each(function () {
            var dataId = $(this).attr('data-id');
            var name = $(this).attr('name');
            if (!$(this).val()) {
                allFill = false;
                $("#threeTable").find('[name="' + name + '"][data-id="' + dataId + '"]').text("");
                return false;
            }
        })

        //计算
        if (allFill) {
            $("#twoTable").find("input:text").each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var result = ($(this).val() / 100).toFixed(4);
//                console.log(dataId);
//                console.log(name);
//                console.log($(this).val());
                $("#threeTable").find('[name="' + name + '"][data-id="' + dataId + '"]').text(result);

            })

            $("#threeTable").find("span").each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var price = $("#threeTable").find('[name="price"][data-id="' + dataId + '"]').text();   //取单价
                var dealCaondition = $("#threeTable").find('[name="dealCaondition"][data-id="' + dataId + '"]').text(); //取交易类型
                var dealTime = $("#threeTable").find('[name="dealTime"][data-id="' + dataId + '"]').text(); //取交易时间
                var paymentMethod = $("#threeTable").find('[name="paymentMethod"][data-id="' + dataId + '"]').text();   //去付款方式
                var result = (price * dealCaondition * dealTime * paymentMethod).toFixed(2);

                $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text(result);   //显示第三张表比准价格

                $("#rightTable").find('[name="specificPrice"][data-id="' + dataId + '"]').text(result);   //显示第四张表比准价格

//                if(result > 1){
//                    $(this).closest("td").find("i").remove();
//                    $(this).closest("td").find('[name="' + name + '"][data-id="' + dataId + '"]').append('<i class="fa fa-arrow-up btn-danger"></i>');
//                }
//                if(result > 1){
//                    $(this).closest("td").find("i").remove();
//                    $(this).closest("td").find('[name="' + name + '"][data-id="' + dataId + '"]').append('<i class="fa fa-arrow-down btn-info"></i>');
//                }
//                if(result == 1){
//                    $(this).closest("td").find("i").remove();
//                }
            })

            var middlePrice = 0;
            var i = 0;
            $("#threeTable").find('[name="affirmPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   //获取第三张表比准价格
                if (reg.test(affirmPrice)) {
                    middlePrice += parseInt(affirmPrice);
                }
                i++;
            })
//            console.log(i);
            $("#threeTable").find('[name="threeMiddlePrice"]').text((middlePrice / i).toFixed(0));    //显示第三张表平均加权价

            var min = 1000000;
            var max = 0;
            $("#threeTable").find("span").each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   ////获取第三张表比准价格
                var price = $("#threeTable").find('[name="price"][data-id="' + dataId + '"]').text();
                var tempCorrection = Math.abs((affirmPrice - price) / price);
                var correction = (Math.round(tempCorrection * 10000)/100).toFixed(2) + '%';
                $("#rightTable").find('[name="correctionDifference"][data-id="' + dataId + '"]').text(correction);    //显示第四张表修正差额
                if (max < affirmPrice) {
                    max = affirmPrice;
                }
                if (min > affirmPrice && reg.test(affirmPrice)) {
                    min = affirmPrice;
                }
            })
            var temp = (max - min) / min;       //（案例最高比准价-案例最低比准价）/案例最低比准价<=20%，如果大于20%则提示案例或修正指数修改错误
            if (temp > 0.2) {
                Alert("案例或修正指数修改错误", 1, null, function () {
                });
            }

            $("#threeTable").find('[name="affirmPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   //获取第三张表比准价格
                var temp = (((affirmPrice - min) / min)*10000/100).toFixed(2) + "%";
                $("#rightTable").find('[name="caseDifference"][data-id="' + dataId + '"]').text(temp);      //显示案例差异
            })

            var caseMax = 0;
            $("#rightTable").find('[name="caseDifference"]').each(function () {
                var dataId = $(this).attr('data-id');
                var caseDifference = $("#rightTable").find('[name="caseDifference"][data-id="' + dataId + '"]').text();
                caseDifference = (caseDifference.replace("%",""))/100;
//                console.log(caseDifference);
                if (caseMax < caseDifference) {
                    caseMax = caseDifference;
                }
            })
            if (caseMax >= 0.3) {
                Alert("采用加权平均价,请填写权重和相应的权重说明", 1, null, function () {
                });
            }
        }
    })

    //处理第四张表业务
    $("#rightTable").find("input:text").blur(function () {
        var reg = /^[0-9]+.?[0-9]*$/;
        var allFill = true;
        $("#rightTable").find("input:text").each(function () {
            if (!$(this).val()) {
                allFill = false;
                $("#rightTable").find('[name="weightedAveragePrice"]').text("");
                return false;
            }
        })

        if (allFill) {
            var middlePrice = 0;
            var tempWeight = 0;
            $("#rightTable").find('[name="specificPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var affirmPrice = $("#rightTable").find('[name="specificPrice"][data-id="' + dataId + '"]').text();   //取第四张表比准价格
                var weight = $("#rightTable").find('[name="weight"][data-id="' + dataId + '"]').val();              //取第四张表权重
                if (reg.test(weight)) {
                    tempWeight += parseFloat(weight);
                    middlePrice += weight * affirmPrice;
                }
            })

            if (tempWeight == 1) {
                $("#rightTable").find('[name="weightedAveragePrice"]').text(middlePrice);    //第四张表加权平均价
            } else {
                Alert("权重之和不为1", 1, null, function () {
                });
            }
        }
    })


    function getData() {
        var items1 = [];
        var items2 = [];
        var items3 = [];
        var items4 = [];
        //因素表
        $("#oneTable").find('thead tr').find('th').each(function () {
            if ($(this).hasClass('data-th')) {
                var dataId = $(this).attr('data-id');
                var type = $(this).attr('data-type');
                var keyValueArray=[];
                var compareFactor={};
                compareFactor.name=$("#oneTable").find('[name="project"][data-id="' + dataId + '"]').text();
                compareFactor.type=type;
                compareFactor.id=dataId;
                compareFactor.evaluationObjectId = ${schemeEvaluationObjectId};
                $("#oneTable").find('[data-id=' + dataId + ']').each(function () {
                    var keyValue = {};
                    keyValue.key = $(this).attr('name');
                    keyValue.value = $(this).text();
                    keyValueArray.push(keyValue);
                })
                compareFactor.jsonContent=JSON.stringify(keyValueArray);
                items1.push(compareFactor);
            }
        })

        //指数表
        $("#twoTable").find('thead tr').find('th').each(function () {
            if ($(this).hasClass('data-th')) {
                var dataId = $(this).attr('data-id');
                var type = $(this).attr('data-type');
                var keyValueArray = [];
                var compareIndex = {};
                compareIndex.name=$("#twoTable").find('[name="project"][data-id="' + dataId + '"]').text();
                compareIndex.type=type;
                compareIndex.id=dataId;
                compareIndex.evaluationObjectId = ${schemeEvaluationObjectId};
                $("#twoTable").find('[data-id=' + dataId + ']').each(function () {
                    var keyValue = {};
                    keyValue.key = $(this).attr('name');
                    if ($(this).hasClass('input')) {
                        keyValue.value = $(this).val();
                    } else {
                        keyValue.value = $(this).text();
                    }
                    keyValueArray.push(keyValue);
                })
                compareIndex.jsonContent=JSON.stringify(keyValueArray);
                items2.push(compareIndex);
            }
        })

        //测算表   实例对象
        $("#threeTable").find('thead tr').find('th').each(function () {
            if ($(this).hasClass('data-th')) {
                var id = $(this).attr('data-id');
                var dataIndexId = $(this).attr('data-index');
                var type = $(this).attr('data-type');
                var keyValueArray = [];
                var compareCalculation = {};
                compareCalculation.name=$("#threeTable").find('[name="project"][data-id="' + id + '"]').text();
                compareCalculation.type=type;
                compareCalculation.id=id;
                compareCalculation.evaluationObjectId = ${schemeEvaluationObjectId};
                $("#threeTable").find('[data-index=' + dataIndexId + ']').each(function () {
                    var keyValue = {};
                    keyValue.key = $(this).attr('name');
                    keyValue.value = $(this).text();
                    keyValueArray.push(keyValue);
                })
                compareCalculation.jsonContent = JSON.stringify(keyValueArray);
                items3.push(compareCalculation);
            }
        })

        //结果表
        $("#rightTable").find('thead tr').find('th').each(function () {
            if ($(this).hasClass('data-th')) {
                var id = $(this).attr('data-id');
                var dataIndex = $(this).attr('data-index');
                var type = $(this).attr('data-type');
                var compareresult = {};
                compareresult.evaluationObjectId = ${schemeEvaluationObjectId};
                compareresult.id = id;
                compareresult.type = type;
                compareresult.name = $("#rightTable").find('[name="project"][data-index="' + dataIndex + '"]').text();
                compareresult.realEstateName =$("#rightTable").find('[name="realEstateName"][data-index="' + dataIndex + '"]').text();
                compareresult.specificPrice =$("#rightTable").find('[name="specificPrice"][data-index="' + dataIndex + '"]').text();
                compareresult.correctionDifference =$("#rightTable").find('[name="correctionDifference"][data-index="' + dataIndex + '"]').text();
                compareresult.caseDifference =$("#rightTable").find('[name="caseDifference"][data-index="' + dataIndex + '"]').text();
                compareresult.weight =$("#rightTable").find('[name="weight"][data-index="' + dataIndex + '"]').val();
                compareresult.weightedAveragePrice =$("#rightTable").find('[name="weightedAveragePrice"][data-index="' + dataIndex + '"]').text();
                items4.push(compareresult);
            }
        })

        var data = {};
        data.methodMarketCompareFactorDtos = items1;
        data.methodMarketCompareIndexDtos = items2;
        data.methodMarketCompareCalculationDtos = items3;
        data.methodMarketCompareResultDtos = items4;

        var json = JSON.stringify(data);
        console.log(json);
        return json;
    }

    function submit() {

        if (!$("#frm_task").valid()) {
            return false;
        }
        if ("${processInsId}" != "0") {
            submitEditToServer(getData(), $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer(getData(), $("#taskRemarks").val(), $("#actualHours").val());
        }
    }

    //显示数据

    function showData(){
        //因素表
        $("#oneTable").find('thead tr').find('input:hidden').each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for(var i in json){
                var name = json[i].key;
                var value = json[i].value;
                $("#oneTable").find('[name='+name+'][data-id='+dataId+']').text(value);
            }
        })

        //指数表

        $("#twoTable").find('thead tr').find('input:hidden').each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for(var i in json){
                var name = json[i].key;
                var value = json[i].value;
                $("#twoTable").find('[name='+name+'][data-id='+dataId+']').val(value);
                $("#twoTable").find('[name='+name+'][data-id='+dataId+']').text(value);
            }
        })

        //测算表
        $("#threeTable").find('thead tr').find('input:hidden').each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for(var i in json){
                var name = json[i].key;
                var value = json[i].value;
                $("#threeTable").find('[name='+name+'][data-id='+dataId+']').text(value);
            }
        })
    }
</script>

</html>

