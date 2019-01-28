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
                    <h3>${judgeObject.name}</h3>
                    <div class="clearfix"></div>
                </div>
                <form class="form-horizontal" id="master">
                    <input type="hidden" name="id" value="${master.id}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="hidden-xs">物业类型</th>
                            <th class="hidden-xs">税率</th>
                            <th class="hidden-xs">备注</th>
                            <th class="hidden-xs">商业</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="hidden-xs">面积(平方米)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs" id="evaluationArea">
                                ${judgeObject.evaluationArea}
                            </td>
                        </tr>
                        <tr>
                            <td class="hidden-xs">评估价(元)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs" id="evaluationPrice">
                                ${judgeObject.price}
                            </td>
                        </tr>
                        </tbody>
                        <tbody id="tbody_data_section">

                        </tbody>
                        <tbody>
                        <tr>
                            <td class='hidden-xs' colspan='3' style='text-align:center;'>合计费用</td>
                            <td class='hidden-xs'>
                                <label class="form-control" name="total"></label>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </form>
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
        getAnalysisItemList();
    });

    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = getFormData();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    //计算结果
    function computeResult() {
        var total = 0;
        var evaluationArea = $("#evaluationArea").text();
        var evaluationPrice = $("#evaluationPrice").text();
        $("#tbody_data_section").find('tr').each(function () {
            var $taxRateValue = $(this).find('[name^=taxRateValue]');
            var rate = $taxRateValue.val();
            var price;
            if ($taxRateValue.hasClass('x-percent')) {
                rate = $taxRateValue.attr('data-value');
                if (rate && evaluationPrice) {
                    price = Number(evaluationPrice * rate).toFixed(2);

                }
            } else {
                if (rate && evaluationArea) {
                    price = Number(evaluationArea * rate).toFixed(2);
                }
            }
            total += Number(price);
            $(this).find('[name^=price]').val(price);
        })
        $('#master').find('[name=total]').text(total);
    }

    //获取需要保存的数据
    function getFormData() {
        var data = {};
        data.id = $('#master').find('[name=id]').val();
        data.total = $('#master').find('[name=total]').text();
        data.analysisItemList = [];
        $("#tbody_data_section").find('tr').each(function () {
            var analysisItem = {};
            analysisItem.id = $(this).find('[name=id]').val();
            analysisItem.price = $(this).find('[name^=price]').val();
            analysisItem.remark = $(this).find('[name^=remark]').val();
            data.analysisItemList.push(analysisItem);
        })
        return data;
    }

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
                        html += '<input type="hidden" name="id" value="' + item.id + '">';
                        html += item.taxRateName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        if (item.calculationMethod == 0) {
                            html += "<input type='text' required onblur='computeResult();' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue).toFixed(2) + "' class='form-control'>";
                        } else {
                            html += "<input type='text' required onblur='computeResult();' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue * 100).toFixed(2) + "%' class='form-control x-percent'>";
                        }
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='remark_" + item.id + "' value='" + AssessCommon.toString(item.remark) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<div class='x-valid'>";
                        html += "<input type='text' required  name='price_" + item.id + "' value='" + AssessCommon.toString(item.price) + "'  class='form-control' data-rule-number='true'>";
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                    computeResult();
                }
            }
        });
    }


</script>

</html>

