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
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="hidden-xs">物业类型</th>
                            <th class="hidden-xs">税率</th>

                            <th class="hidden-xs" style="width: 20%;">计算基数</th>
                            <th class="hidden-xs">计算公式</th>
                            <th class="hidden-xs">税费负担方</th>

                            <th class="hidden-xs">备注</th>
                            <th class="hidden-xs">商业</th>
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
                                ${groupArea}
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
                                ${groupPrice}
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
    });

    function getAnalysisItemList() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getAnalysisItemList",
            data: {
                planDetailsId: "${projectPlanDetails.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
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
                        html +=  AssessCommon.toString(item.price);
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
            }
        });
    }
</script>
</body>
</html>

