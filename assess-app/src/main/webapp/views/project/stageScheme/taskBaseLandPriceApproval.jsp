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
                                        <td>${master.standardPremium}</td>
                                        <td colspan="2" id="bhouPrice"></td>
                                    </tr>
                                    <tr>
                                        <td>期日修正系数</td>
                                        <td>${master.dateAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>年期修正系数</td>
                                        <td>${master.periodAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>土地还原率</td>
                                        <td>${master.rewardRate}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>法定年限</td>
                                        <td>${master.legalAge}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>剩余使用年限</td>
                                        <td>${master.landSurplusYear}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>容积率是否修正</td>
                                        <td>
                                            <input type="checkbox" id="hasFractionAmend" name="hasFractionAmend"
                                                   value="true" disabled="disabled">
                                        </td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr id="showVolumeFractionAmend">
                                        <td>容积率修正</td>
                                        <td>${master.volumeFractionAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>区域及个别修正系数</td>
                                        <td id="areaAndSeveralAmend">${master.areaAndSeveralAmend}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>开发程度是否修正</td>
                                        <td>
                                            <input type="checkbox" id="hasDevelopCorrect" name="hasDevelopCorrect"
                                                   value="true" disabled="disabled">
                                        </td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr id="showDevelopCorrect">
                                        <td>开发程度修正</td>
                                        <td>${master.developCorrect}</td>
                                        <td colspan="2">与基准地价的开发程度一致</td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地单价（元/㎡）</td>
                                        <td>${master.parcelPrice}</td>
                                        <td colspan="2">地价＝P×K1×K2×……×（1±∑K）±L</td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地单价（万元/亩）</td>
                                        <td>${master.parcelBhouPrice}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地面积（㎡）</td>
                                        <td>${master.evaluationArea}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>委估宗地总价（万元）</td>
                                        <td>${master.parcelTotalPrice}</td>
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <td>${number}号估价对象楼面地价</td>
                                        <td>${master.floorPremium}</td>
                                        <td>委估对象容积率</td>
                                        <td>${master.volumetricRate}</td>
                                    </tr>
                                    <tr>
                                        <td>验证参数合理性</td>
                                        <td>修正差额（%）</td>
                                        <td>${master.correctionDifference}</td>
                                        <td>提示：小于30%</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
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
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        var standardBhouPrice = getBhouPrice("${master.standardPremium}", 2)
        $("#bhouPrice").text(standardBhouPrice + "万元/亩");
        //容积率是否修正
        if(${master.hasFractionAmend}){
            $("#hasFractionAmend").attr("checked","checked");
        }else {
            $("#showVolumeFractionAmend").hide();
        }
        //开发程度是否修正
        if(${master.hasDevelopCorrect}){
            $("#hasDevelopCorrect").attr("checked","checked");
        }else {
            $("#showDevelopCorrect").hide();
        }
    });

    //获取一亩的价
    function getBhouPrice(num, v) {
        return getSomePlaces(num * AssessCommon.BHOU / 10000, v);
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }
</script>
</body>
</html>

