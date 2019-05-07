<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>委估对象${number}号基准地价系数修正法价格测算表</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="master">
                        <input type="hidden" name="id" value="${master.id}">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="width:30%">项目</th>
                                        <th style="width:30%">参数</th>
                                        <th colspan="2" style="width:40%">参数与备注</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>基准地价（元/㎡）</td>
                                        <td id="standardPremium">${standardPremium}</td>
                                        <td colspan="2" id="bhouPrice"></td>
                                    </tr>
                                    <tr>
                                        <td>期日修正系数</td>
                                        <td id="DateAmend">${DateAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>年期修正系数</td>
                                        <td id="periodAmend"></td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>土地还原率</td>
                                        <td>
                                            <div class="input-group">
                                                <input type="text" required class="form-control x-percent"
                                                       name="rewardRate" placeholder="报酬率" readonly="readonly"
                                                       value="${master.rewardRate}">
                                                <span class="input-group-btn">
                                                <input type="hidden" name="rewardRateId" value="${master.rewardRateId}">
                                                <input type="button" class="btn btn-primary" value="报酬率测算"
                                                       onclick="getRewardRate(this);"/>
                                                </span>
                                            </div>
                                        </td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>法定年限</td>
                                        <td>${legalAge}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>剩余使用年限</td>
                                        <td>${landSurplusYear}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>容积率修正</td>
                                        <td id="volumeFractionAmend">${volumeFractionAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>区域及个别修正系数</td>
                                        <td id="areaAndSeveralAmend">0.05</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>开发程度修正</td>
                                        <td>
                                            <div class="x-valid">
                                                <input type="text" name="developCorrect" class="form-control"
                                                       id="developCorrect" required
                                                       onblur="getParcelPrice()" placeholder="请输入数字"
                                                       data-rule-number="true" value="${master.developCorrect}">
                                            </div>
                                        </td>
                                        <td colspan="2">与基准地价的开发程度一致</td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地单价（元/㎡）</td>
                                        <td id="parcelPrice">${master.parcelPrice}</td>
                                        <td colspan="2">地价＝P×K1×K2×……×（1±∑K）±L</td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地单价（万元/亩）</td>
                                        <td id="parcelBhouPrice">${master.parcelBhouPrice}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地面积（㎡）</td>
                                        <td>${floorArea}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地总价（万元）</td>
                                        <td id="parcelTotalPrice">${master.parcelTotalPrice}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>${number}号估价对象楼面地价</td>
                                        <td id="floorPremium">${master.floorPremium}</td>
                                        <td>委估对象容积率</td>
                                        <td id="volumetricRate"></td>
                                    </tr>
                                    <tr>
                                        <td>验证参数合理性</td>
                                        <td>修正差额（%）</td>
                                        <td id="correctionDifference">${master.correctionDifference}</td>
                                        <td>提示：小于30%</td>
                                    </tr>

                                    </tbody>
                                </table>
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
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="application/javascript">
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = {};
        formData.id = $('#master').find('[name=id]').val();
        formData.rewardRate = $('#master').find('[name=rewardRate]').val();
        formData.rewardRateId = $('#master').find('[name=rewardRateId]').val();
        formData.developCorrect = $('#master').find('[name=developCorrect]').val();
        formData.periodAmend = $("#periodAmend").text();

        formData.parcelPrice = $("#parcelPrice").text();
        formData.parcelBhouPrice = $("#parcelBhouPrice").text();
        formData.parcelTotalPrice = $("#parcelTotalPrice").text();
        formData.floorPremium = $("#floorPremium").text();
        formData.correctionDifference = $("#correctionDifference").text();

        console.log(formData);
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    $(function () {
        var standardBhouPrice = getBhouPrice("${standardPremium}", 2)
        $("#bhouPrice").text(standardBhouPrice + "万元/亩");
        $("#volumetricRate").text(getSomePlaces("${volumetricRate}", 2));
        if ("${master.rewardRate}") {
            getPeriodAmend("${master.rewardRate}");
        }
    });

    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue));

                getPeriodAmend(data.resultValue);
            }
        })
    }

    //获取委估宗地单价（元/㎡）
    function getParcelPrice() {
        //基准地价
        var standardPremium = parseFloat($("#standardPremium").text());
        console.log(standardPremium + "==1");
        //期日修正系数
        var DateAmend = parseFloat($("#DateAmend").text());
        console.log(DateAmend + "==2");
        //年期修正系数
        var periodAmend = parseFloat($("#periodAmend").text());
        console.log(periodAmend + "==3");
        //容积率修正
        var volumeFractionAmend = parseFloat($("#volumeFractionAmend").text());
        console.log(volumeFractionAmend + "==4");
        //区域及个别修正系数
        var areaAndSeveralAmend = parseFloat($("#areaAndSeveralAmend").text());
        console.log(areaAndSeveralAmend + "==5");
        //开发程度修正
        var developCorrect = parseFloat($("#developCorrect").val());
        console.log(developCorrect + "=6");

        if (standardPremium && DateAmend && periodAmend && volumeFractionAmend && areaAndSeveralAmend && developCorrect) {
            var money = standardPremium * DateAmend * periodAmend * volumeFractionAmend * (1 + areaAndSeveralAmend) + developCorrect;
            console.log(money);
            if (money) {
                //宗地单价
                $("#parcelPrice").text(getSomePlaces(money, 2));
                //宗地亩价
                $("#parcelBhouPrice").text(getBhouPrice(money, 2));
                //宗地总价
                var area = "${floorArea}";
                $("#parcelTotalPrice").text(getSomePlaces(area * money / 10000, 2));
                //楼面地价
                var volumetricRate = "${volumetricRate}";
                $("#floorPremium").text(getSomePlaces(money / volumetricRate, 2));
                //修正差额
                $("#correctionDifference").text(getSomePlaces((money / standardPremium - 1) * 100, 2) + "%");
            }
        }
    }

    //获取一亩的价
    function getBhouPrice(num, v) {
        return getSomePlaces(num * 666.67 / 10000, v);
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //获取年期修正系数
    function getPeriodAmend(rewardRatePercent) {
        var rewardRate = AssessCommon.percentToPoint(rewardRatePercent);
        var landSurplusYear = "${landSurplusYear}";
        console.log(landSurplusYear + "landSurplusYear");
        console.log(rewardRate + "rewardRate");
        if (rewardRate) {
            var temp = 1 / Math.pow(1 + parseFloat(rewardRate), landSurplusYear);
            console.log(temp);
            var temp2 = 1 / Math.pow(1 + parseFloat(rewardRate), rewardRate);
            console.log(temp2);
            var result = (1 - temp) / (1 - temp2);
            $("#periodAmend").text(getSomePlaces(result, 2))
        }
    }
</script>

</html>

