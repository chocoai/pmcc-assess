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
                                        <table class="table table-bordered leftfloat" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th class="gray">项目</th>
                                                <th class="gray">${schemeEvaluationObject.name}</th>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items" varStatus="s">
                                                    <th class="gray">实例${s.index + 1}</th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <td>${surveyLocaleExploreDetail.houseName}</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.houseName}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.caseType}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.price}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.dealCaondition}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <td>评估基准日</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <fmt:formatDate value="${items.dealTime}" pattern="yyyy-MM-dd"/>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.paymentMethod}</td>
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
                                                <th class="gray">${schemeEvaluationObject.name}</th>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items" varStatus="s">
                                                    <th class="gray">实例${s.index + 1}</th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <td>${surveyLocaleExploreDetail.houseName}</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.houseName}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <td>100</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.caseType}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.price}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <td>100</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <input type="hidden" value="${items.id}">
                                                        <input type="text"  class=""
                                                               name="dealCaondition" required
                                                               data-rule-digits="true"
                                                               min="80" max="120" step="1" style="width: 80px">
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <td>100</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <input type="hidden" value="${items.id}">
                                                        <input type="text" class="" name="dealTime"
                                                               required
                                                               data-rule-digits="true"
                                                               min="80" max="120" step="1" style="width: 80px">
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <td>100</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <input type="hidden" value="${items.id}">
                                                        <input type="text" name="paymentMethod"
                                                               required
                                                               data-rule-digits="true"
                                                               min="80" max="120" step="1" style="width: 80px">
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content3"
                                         aria-labelledby="profile-tab">
                                        <%--第三页表--%>
                                        <table class="table table-bordered leftfloat" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th class="gray">项目</th>
                                                <th class="gray">${schemeEvaluationObject.name}</th>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items" varStatus="s">
                                                    <th class="gray">实例${s.index + 1}</th>
                                                </c:forEach>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row" class="gray">楼盘名称</th>
                                                <td>${surveyLocaleExploreDetail.houseName}</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.houseName}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">案例类型</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>${items.caseType}</td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">单价（元/㎡）</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                    <span id="price${items.id}">${items.price}</span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span id="#threeDealCaondition${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span id="#threeDealTime${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span id="#threePaymentMethod${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr id="trList">
                                                <th scope="row" class="gray">比准价格</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span id="threeAffirmPrice${items.id}"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">加权平均价</th>
                                                <td>
                                                    <span id="threeMiddlePrice"></span>
                                                </td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td id=""></td>
                                                </c:forEach>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tab_content4"
                                         aria-labelledby="profile-tab">
                                        <%--第四页表--%>
                                        <table class="table table-bordered leftfloat" style="width: 40%">
                                            <thead>
                                            <tr>
                                                <th>年份</th>
                                                <th>指数</th>
                                                <th>年份</th>
                                                <th>指数</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th>2017年10月</th>
                                                <td>Mark</td>
                                                <td>Otto</td>
                                                <td>@mdo</td>
                                            </tr>
                                            <tr>
                                                <th>2017年11月</th>
                                                <td>Jacob</td>
                                                <td>Thornton</td>
                                                <td>@fat</td>
                                            </tr>
                                            <tr>
                                                <th>2017年12月</th>
                                                <td>Larry</td>
                                                <td>the Bird</td>
                                                <td>@twitter</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%--固定表格格式用--%>
                                <label class="col-sm-2 control-label rightfloat"></label>

                                <%--右侧固定表格--%>
                                <table class="table table-bordered rightfloat" style="width: 30%">
                                    <thead>
                                    <tr>
                                        <th class="gray">项目</th>
                                        <th class="gray">${schemeEvaluationObject.name}</th>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items" varStatus="s">
                                            <th class="gray">实例${s.index + 1}</th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row" class="gray">楼盘名称</th>
                                        <td>${surveyLocaleExploreDetail.houseName}</td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>${items.houseName}</td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">比准价格</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span id="rightAffirmPrice${items.id}" ></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">修正差额</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td></td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">案例差异</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td></td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">权重</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <input type="text" name="" min="0.0" max="1" step="0.1"
                                                       style="width: 80px">
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">加权平均价</th>
                                        <td>6</td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td></td>
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
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3">
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
                                                  class="form-control"></textarea>
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


    function submit() {
        if (!$("#frm_task").valid()) {
            return false;
        }

        if ("${processInsId}" != "0") {
            submitEditToServer("", $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer("", $("#taskRemarks").val(), $("#actualHours").val());
        }
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




    $('input[name=dealCaondition]').blur(function () {
        var number = $(this).val();                                         //取input输入的值
        var twoId = $(this).closest("td").find("input").first().val();     //第二张表循环的id
        var temp = 100 / number;                                            //相应计算
        var threeId = "#threeDealCaondition" + twoId;                     //第三章表循环的id
        var re = /^([89]\d|120)$/;

        if(re.test(number)){
            document.getElementById(threeId).innerHTML = temp.toFixed(4);                    //把值赋给第三张表
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

        if(temp < 1){
            console.log("temp小于1");
            $(threeId).append('<i class="fa fa-arrow-up btn-danger"></i>');
        }
        if(temp > 1){
            console.log("temp大于1");
            $(threeId).after('<i class="fa fa-arrow-down btn-info"></i>');
        }

        var s1 = document.getElementById(threeId).innerHTML;
        var s2 = document.getElementById("#threeDealTime"+twoId).innerHTML;
        var s3 = document.getElementById("#threePaymentMethod"+twoId).innerHTML;
        var s4 = $("#price"+twoId).html();
        var s0 = s1*s2*s3*s4;
        var reg = /^[1-9]\d*$/;
        if(reg.test(s0)){
            $("#threeAffirmPrice"+twoId).html(s0.toFixed(2));
            $("#rightAffirmPrice"+twoId).html(s0.toFixed(2));
        }

        var list = $("#trList").find("span");
//        console.log(list.size());
        var total = 0;
        $.each(list,function(i,span){
            total += parseInt($(span).html());
        })
        var middleRate = total/list.size();
        $("#threeMiddlePrice").html(middleRate.toFixed(0));


    });


    $('input[name=dealTime]').blur(function () {
        var number = $(this).val();                                         //取input输入的值
        var twoId = $(this).closest("td").find("input").first().val();     //第二张表循环的id
        var temp = 100 / number;                                            //相应计算
        var threeId = "#threeDealTime" + twoId;                     //第三章表循环的id
        var re = /^([89]\d|120)$/;
        if(re.test(number)){
            document.getElementById(threeId).innerHTML = temp.toFixed(4);                    //把值赋给第三张表
        }

        if (number > 100) {
            console.log("大于");
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-up btn-danger"></i>');
        }
        if (number < 100) {
            console.log("小于");
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-down btn-info"></i>');
        }
        if (number == "" || number == 100) {
            console.log("等于");
            $(this).closest("td").find("i").remove();
        }

        var s1 = document.getElementById(threeId).innerHTML;
        var s2 = document.getElementById("#threeDealTime"+twoId).innerHTML;
        var s3 = document.getElementById("#threePaymentMethod"+twoId).innerHTML;
        var s4 = $("#price"+twoId).html();
        var s0 = s1*s2*s3*s4;
        var reg = /^[1-9]\d*$/;
        if(reg.test(s0)){
            $("#threeAffirmPrice"+twoId).html(s0.toFixed(2));
            $("#rightAffirmPrice"+twoId).html(s0.toFixed(2));
        }

        var list = $("#trList").find("span");
//        console.log(list.size());
        var total = 0;
        $.each(list,function(i,span){
            total += parseInt($(span).html());
        })
        var middleRate = total/list.size();
        $("#threeMiddlePrice").html(middleRate.toFixed(0));

    });

    $('input[name=paymentMethod]').blur(function () {
        var number = $(this).val();                                         //取input输入的值
        var twoId = $(this).closest("td").find("input").first().val();     //第二张表循环的id
        var temp = 100 / number;                                            //相应计算
        var threeId = "#threePaymentMethod" + twoId;                     //第三章表循环的id
        var re = /^[1-9]\d*$/;
        if(re.test(number)){
            document.getElementById(threeId).innerHTML = temp.toFixed(4);                    //把值赋给第三张表
        }
        if (number > 100) {
            console.log("大于");
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-up btn-danger"></i>');
        }
        if (number < 100) {
            console.log("小于");
            $(this).closest("td").find("i").remove();
            $(this).closest("td").find("input").last().after('<i class="fa fa-arrow-down btn-info"></i>');
        }
        if (number == "" || number == 100) {
            console.log("等于");
            $(this).closest("td").find("i").remove();
        }

        var s1 = document.getElementById(threeId).innerHTML;
        var s2 = document.getElementById("#threeDealTime"+twoId).innerHTML;
        var s3 = document.getElementById("#threePaymentMethod"+twoId).innerHTML;
        var s4 = $("#price"+twoId).html();
        var s0 = s1*s2*s3*s4;
        var reg = /^[0-9]+.?[0-9]*$/;
        if(reg.test(s0)){
            $("#threeAffirmPrice"+twoId).html(s0.toFixed(2));
            $("#rightAffirmPrice"+twoId).html(s0.toFixed(2));
        }

        var list = $("#trList").find("span");
//        console.log(list.size());
        var total = 0;
        $.each(list,function(i,span){
            total += parseInt($(span).html());
        })
        var middleRate = total/list.size();
        $("#threeMiddlePrice").html(middleRate.toFixed(0));
    });


</script>

</html>

