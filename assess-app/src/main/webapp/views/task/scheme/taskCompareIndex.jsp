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
                                                    <td><span name="price" data-id="${items.id}">${items.price}</span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <td>100</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <input type="text" class="allMethod" data-id="${items.id}"
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
                                                        <input type="text" class="allMethod" name="dealTime"
                                                               required data-id="${items.id}"
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
                                                        <input type="text" class="allMethod" data-id="${items.id}"
                                                               name="paymentMethod" required
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
                                        <table class="table table-bordered leftfloat" id="threeTable"
                                               style="width: 40%">
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
                                                        <span data-id="${items.id}" name="price">${items.price}</span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易情况</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span data-id="${items.id}" name="dealCaondition"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">交易时间</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span data-id="${items.id}" name="dealTime"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">付款方式</th>
                                                <td>1</td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span data-id="${items.id}" name="paymentMethod"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr id="trList">
                                                <th scope="row" class="gray">比准价格</th>
                                                <td></td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td>
                                                        <span data-id="${items.id}" name="affirmPrice"></span>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th scope="row" class="gray">加权平均价</th>
                                                <td>
                                                    <span name="threeMiddlePrice"></span>
                                                </td>
                                                <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                                    <td></td>
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
                                <table class="table table-bordered rightfloat" id="rightTable" style="width: 30%">
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
                                                <span data-id="${items.id}" name="affirmPrice"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">修正差额</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span data-id="${items.id}" name="correction"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">案例差异</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <span data-id="${items.id}" name="caseDifference"></span>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">权重</th>
                                        <td></td>
                                        <c:forEach items="${surveyCaseStudyDetails}" var="items">
                                            <td>
                                                <input type="text" data-id="${items.id}" name="weight" min="0.0" max="1" step="0.1"
                                                       style="width: 80px">
                                            </td>
                                        </c:forEach>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="gray">加权平均价</th>
                                        <td>
                                            <span data-id="${items.id}" name="middlePrice"></span>
                                        </td>
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

    //处理第二三四张表业务
    $("#twoTable").find("input:text").blur(function () {
        var reg = /^[0-9]+.?[0-9]*$/;
        //1.判断大小
        var number = $(this).val();
        if ((number < 80 || number > 120) && reg.test(number)) {
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
            if (!$(this).val()) {
                allFill = false;
                return false;
            }
        })

        //计算
        if (allFill) {
            $("#twoTable").find("input:text").each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var result = ($(this).val() / 100).toFixed(4);

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
                $("#rightTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text(result);   //显示第四张表比准价格
            })

            var middlePrice = 0;
            var i = 0;
            $("#threeTable").find('[name="affirmPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   //获取第三张表比准价格
                middlePrice += parseInt(affirmPrice);
                i++;
            })
            $("#threeTable").find('[name="threeMiddlePrice"]').text((middlePrice / i).toFixed(0));    //显示第三张表平均加权价

            var min = 1000000;
            var max = 0;
            $("#threeTable").find("span").each(function () {
                var dataId = $(this).attr('data-id');
                var name = $(this).attr('name');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   ////获取第三张表比准价格
                var price = $("#threeTable").find('[name="price"][data-id="' + dataId + '"]').text();
                var correction = Math.abs((affirmPrice - price) / price);
                $("#rightTable").find('[name="correction"][data-id="' + dataId + '"]').text(correction);    //显示第四张表修正差额

                if (max < affirmPrice) {
                    max = affirmPrice;

                }

                if (min > affirmPrice && reg.test(affirmPrice)) {
                    min = affirmPrice;

                }
            })
            var temp = (max - min) / min;       //（案例最高比准价-案例最低比准价）/案例最低比准价<=20%，如果大于20%则提示案例或修正指数修改错误
            console.log(temp);
//            if (temp > 0.2) {
//                Alert("案例或修正指数修改错误", 1, null, function () {
//                });
//            }

            $("#threeTable").find('[name="affirmPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var affirmPrice = $("#threeTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   //获取第三张表比准价格
                var temp = ((affirmPrice - min) / min).toFixed(2);
                $("#rightTable").find('[name="caseDifference"][data-id="' + dataId + '"]').text(temp);
            })

            var caseMax = 0;
            $("#rightTable").find('[name="caseDifference"]').each(function () {
                var dataId = $(this).attr('data-id');
                var caseDifference = $("#rightTable").find('[name="caseDifference"][data-id="' + dataId + '"]').text();

                if(caseMax < caseDifference){
                    caseMax = caseDifference;
                }
            })
            if(caseMax >= 0.3){
                Alert("采用加权平均价,请填写权重和相应的权重说明", 1, null, function () {
                });
            }

        }
    })

        //处理第四张表业务
    $("#rightTable").find("input:text").blur(function () {

        var allFill = true;
        $("#rightTable").find("input:text").each(function () {
            if (!$(this).val()) {
                allFill = false;
                return false;
            }
        })

        if(allFill){
            var middlePrice = 0;
            var tempWeight = 0;
            $("#rightTable").find('[name="affirmPrice"]').each(function () {
                var dataId = $(this).attr('data-id');
                var affirmPrice = $("#rightTable").find('[name="affirmPrice"][data-id="' + dataId + '"]').text();   //取第四张表比准价格
                var weight = $("#rightTable").find('[name="weight"][data-id="' + dataId + '"]').val();              //取第四张表权重
                tempWeight += parseFloat(weight);
                middlePrice += weight * affirmPrice;
            })

            if(tempWeight == 1){
                $("#rightTable").find('[name="middlePrice"]').text(middlePrice);    //第四张表加权平均价
            }else{
                Alert("权重之和不为1", 1, null, function () {
                });
            }

        }

    })



</script>

</html>

