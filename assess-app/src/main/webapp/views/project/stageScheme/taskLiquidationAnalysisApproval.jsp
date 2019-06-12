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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${areaGroup.areaName}</h3>
                    <div class="clearfix"></div>
                </div>
                <form class="form-horizontal" id="master">
                    <input type="hidden" name="id" value="${master.id}">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">变现比率</label>
                            <div class="col-sm-3">
                                <label class="form-control">${master.liquidRatios}</label>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">变现时间</label>
                            <div class="col-sm-3">
                                <label class="form-control">${master.liquidTime}</label>
                            </div>
                        </div>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="hidden-xs">物业类型</th>
                            <th class="hidden-xs">税率</th>

                            <th class="hidden-xs" style="width: 20%;">计算基数</th>
                            <th class="hidden-xs">计算公式</th>
                            <th class="hidden-xs">税费负担方</th>

                            <th class="hidden-xs">备注</th>
                            <th class="hidden-xs"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="hidden-xs">面积(平方米)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs" id="evaluationArea">
                            </td>
                        </tr>
                        <tr>
                            <td class="hidden-xs">评估价(元)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs" id="evaluationPrice">
                            </td>
                        </tr>
                        </tbody>
                        <tbody id="tbody_data_section">

                        </tbody>
                        <tbody>
                        <tr>
                            <td class='hidden-xs' colspan='6' style='text-align:center;'>合计费用</td>
                            <td class='hidden-xs'>
                                <label class="form-control" name="total">${master.total}</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
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
        getAnalysisItemList();
        if("${groupPrice}"){
            $("#evaluationPrice").text(fmoney("${groupPrice}",2))
        }
        if("${groupArea}"){
            $("#evaluationArea").text(fmoney("${groupArea}",2))
        }
        $("#master").find('[name=total]').text(fmoney("${master.total}",2));
    });

    function getAnalysisItemList() {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getAnalysisItemList",
            data: {
                planDetailsId: "${projectPlanDetails.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                $("#tbody_data_section").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += item.taxRateName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        if (item.calculationMethod == 0) {
                            html += Number(item.taxRateValue).toFixed(2);
                        } else {
                            html += Number(item.taxRateValue*100).toFixed(2)+"%";
                        }
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html +=  AssessCommon.toString(item.calculateBase);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html +=  AssessCommon.toString(item.calculationFormula);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html +=  AssessCommon.toString(item.taxesBurden);
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html +=  AssessCommon.toString(item.remark);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<div class='x-valid'>";
                        html +=  fmoney(item.price,2);
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
            }
        });
    }
    //格式化金额
    function fmoney(s, n) {
        if(!AssessCommon.isNumber(s)) return s;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for(i = 0; i < l.length; i++ )
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r.substring(0,2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
    }
</script>
</body>
</html>

