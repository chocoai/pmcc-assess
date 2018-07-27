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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
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
                                                    <th class="gray">
                                                        <span>${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <input type="hidden" data-json='${str}'>
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
                                                    <th class="gray">
                                                        <span>${items.name}</span>
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
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="dealCaondition" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="dealTime" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareIndexs}" var="items">
                                                    <td>
                                                        <span name="paymentMethod" data-id="${items.id}"></span>
                                                    </td>
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
                                                    <input type="hidden" data-id="${items.id}" data-json='${items.jsonContent}'>
                                                    <th class="gray">
                                                        <span>${items.name}</span>
                                                    </th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th class="gray">楼盘名称</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="houseName" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="caseType" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="price" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="dealCaondition" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="dealTime" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="paymentMethod" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr id="trList">
                                                <th scope="row" class="gray">比准价格</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="affirmPrice" data-id="${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">加权平均价</th>
                                                <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                                    <td>
                                                        <span name="threeMiddlePrice" data-id="${items.id}"></span>
                                                    </td>
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
                                            <tr>
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
                                <label class="col-sm-1 control-label rightfloat"></label>

                                <%--右侧固定表格 第四张表--%>
                                <table class="table table-bordered rightfloat" id="rightTable" style="width: 40%">
                                    <thead>
                                    <tr>
                                        <th class="gray">项目</th>
                                        <c:forEach items="${methodMarketCompareCalculations}" var="items">
                                            <th class="gray data-th" name="project">${items.name}</th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row" class="gray">楼盘名称</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span name="realEstateName" data-id="${items.id}">${items.realEstateName}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">比准价格</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span data-id="${items.id}" name="specificPrice">${items.specificPrice}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">修正差额</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span data-id="${items.id}" name="correctionDifference">${items.correctionDifference}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">案例差异</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span name="caseDifference">${items.caseDifference}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">权重</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span>${items.weight}</span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">加权平均价</th>
                                        <c:forEach items="${methodMarketCompareResults}" var="items">
                                            <td>
                                                <span name="weightedAveragePrice">${items.weightedAveragePrice}</span>
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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
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
        getDynamic();
        showData();

        FileUtils.getFileShows({
            target: "file_upload_task",
            formData: {
                tableName: AssessDBKey.ProjectPlanDetails,
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: false
        })
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


    //显示数据
    function showData() {
        //因素表
        $("#oneTable").find("thead tr").find("input:hidden").each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for (var i in json) {
                var name = json[i].key;
                var value = json[i].value;
                $("#oneTable").find('[name=' + name + '][data-id=' + dataId + ']').text(value);
            }
        })
        //指数表
        $("#twoTable").find("thead tr").find("input:hidden").each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for (var i in json) {
                var name = json[i].key;
                var value = json[i].value;
                $("#twoTable").find('[name=' + name + '][data-id=' + dataId + ']').text(value);
            }
        })
        //测算表
        $("#threeTable").find("thead tr").find("input:hidden").each(function () {
            var dataId = $(this).attr('data-id');
            var dataJson = $(this).attr('data-json');
            var json = JSON.parse(dataJson);
            for (var i in json) {
                var name = json[i].key;
                var value = json[i].value;
                $("#threeTable").find('[name=' + name + '][data-id=' + dataId + ']').text(value);
            }
        })
    }


    function getDynamic() {
        var num = $("#oneTable").find('thead').find('input:hidden');
        var dataJson = $("#oneTable").find('tbody').find('input:hidden').attr('data-json');
        var jsonArr = JSON.parse(dataJson);

        var caseNum = num.length - jsonArr.length;
        $.each(jsonArr, function (i, array) {
            if (i == 0) {
                //第一个字段 第二个查勘的值
                $.each(array, function (j, item) {
                    var tr = $("#oneTable").find('tbody').find('tr:last');
                    $("#oneTable").find('tbody').append('<tr>' + tr.html() + '</tr>');
                    tr = $("#oneTable").find('tbody').find('tr:last');
                    tr.find('th:eq(' + i + ')').text(item.explain); //字段
                    tr.find('td:eq(' + i + ')').find('span').attr('name', item.key);    //第一张表
                })

                $.each(array, function (j, item) {
                    var tr = $("#twoTable").find('tbody').find('tr:last');
                    $("#twoTable").find('tbody').append('<tr>' + tr.html() + '</tr>');
                    tr = $("#twoTable").find('tbody').find('tr:last');
                    tr.find('th:eq(' + i + ')').text(item.explain); //字段
                    tr.find('td:eq(' + i + ')').find('span').attr('name', item.key);    //第二张表
                })

                $.each(array, function (j, item) {
                    var tr = $("#threeTable").find('tbody').find('tr:eq(-3)');
                    $("#threeTable").find('tbody').find('tr:eq(-3)').after('<tr>' + tr.html() + '</tr>');
                    tr = $("#threeTable").find('tbody').find('tr:eq(-3)');
                    tr.find('th:eq(' + i + ')').text(item.explain); //字段
                    tr.find('td:eq(' + i + ')').find('span').attr('name', item.key);    //第三张表
                })

            } else {
                $.each(array, function (j, item) {
                    var td = $("#oneTable").find('tbody').find('tr:eq(' + (-jsonArr.length + j + 1) + ')');
                    td.find('td:eq(' + i + ')').find('span').attr('name', item.key);   //第一张表
                    for (var m = 1; m <= caseNum; m++) {
                        td.find('td:eq(' + (i + m) + ')').find('span').attr('name', item.key);    //新增案例从后台json取不到,采用案例差给案例赋值name
                    }
                })

                $.each(array, function (j, item) {
                    var td = $("#twoTable").find('tbody').find('tr:eq(' + (-jsonArr.length + j + 1) + ')');
                    td.find('td:eq(' + i + ')').find('span').attr('name', item.key);   //第二张表
                    for (var m = 1; m <= caseNum; m++) {
                        td.find('td:eq(' + (i + m) + ')').find('span').attr('name', item.key);
                    }
                })

                $.each(array, function (j, item) {
                    var td = $("#threeTable").find('tbody').find('tr:eq(' + (-jsonArr.length + j - 1) + ')');
                    td.find('td:eq(' + i + ')').find('span').attr('name', item.key);    //第三张表
                    for (var m = 1; m <= caseNum; m++) {
                        td.find('td:eq(' + (i + m) + ')').find('span').attr('name', item.key);
                    }
                })

            }

        })

    }

</script>
</body>
</html>

