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
                                <input name="liquidRatios" class="form-control x-percent" required
                                       placeholder="变现比率" value="${master.liquidRatios}"/>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">变现时间</label>
                            <div class="col-sm-3">
                                <input name="liquidTime" class="form-control" required
                                       placeholder="例60-90天" value="${master.liquidTime}"/>
                            </div>
                        </div>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="hidden-xs">物业类型</th>
                            <th class="hidden-xs">税率</th>

                            <th class="hidden-xs">计算基数</th>
                            <th class="hidden-xs">计算公式</th>
                            <th class="hidden-xs">税费负担方</th>

                            <th class="hidden-xs">备注</th>
                            <th class="hidden-xs">商业</th>
                            <th class="hidden-xs">操作</th>
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
                            <td class="hidden-xs" id="evaluationArea" value="${groupArea}">
                            </td>
                        </tr>
                        <tr>
                            <td class="hidden-xs">评估价(元)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs" id="evaluationPrice" value="${groupPrice}">
                            </td>
                        </tr>
                        </tbody>
                        <tbody id="tbody_data_section">

                        </tbody>
                        <tbody>
                        <tr>
                            <td class='hidden-xs' colspan='6' style='text-align:center;'>合计费用</td>
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
        if ("${groupPrice}") {
            $("#evaluationPrice").text(fmoney("${groupPrice}", 2))
        }
        if ("${groupArea}") {
            $("#evaluationArea").text(fmoney("${groupArea}", 2))
        }
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

    //初始化计算结果
    function initResult() {
        var salesTax = "${salesTax}";
        var total = 0;
        var evaluationArea = $("#evaluationArea").attr('value');
        var evaluationPrice = $("#evaluationPrice").attr('value');
        $("#tbody_data_section").find('tr').each(function () {
            var $taxRateValue = $(this).find('[name^=taxRateValue]');
            var rate = $taxRateValue.val();
            var price = 0;
            if ($taxRateValue.hasClass('x-percent')) {
                rate = $taxRateValue.attr('data-value');
                var key = $taxRateValue.attr('data-key');
                switch (key) {
                    //增值税
                    case "data.tax.rate.allocation.sales.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * rate).toFixed(2);
                        }
                        break;
                    }
                    //城建税
                    case "data.tax.rate.allocation.construction.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                        }
                        break;
                    }
                    //教育税附加
                    case "data.tax.rate.allocation.education.fee.plus": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                        }
                        break;
                    }
                    //印花税
                    case "data.tax.rate.allocation.stamp.duty": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //土地增值税
                    case "data.tax.rate.allocation.land.increment.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //其它税费
                    case "data.tax.rate.allocation.other.taxes.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //企业所得税
                    case "data.tax.rate.allocation.corporate.income.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //契税
                    case "data.tax.rate.allocation.deed.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //预计处置费用
                    case "data.tax.rate.allocation.disposal.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                }
            } else {
                if (rate && evaluationArea) {
                    price = Number(evaluationArea * rate).toFixed(2);
                }
            }
            total += Number(price);
            $(this).find('[name^=price]').val(fmoney(price, 2));
            $(this).find('[name^=price]').attr("data-value", price);
        })
        $('#master').find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));
    }

    //获取需要保存的数据
    function getFormData() {
        var data = {};
        data.id = $('#master').find('[name=id]').val();
        data.total = $('#master').find('[name=total]').text().replace(/,/g,'');
        data.liquidRatios = $('#master').find('[name=liquidRatios]').val();
        data.liquidTime = $('#master').find('[name=liquidTime]').val();
        console.log(data);
        data.analysisItemList = [];
        $("#tbody_data_section").find('tr').each(function () {
            var analysisItem = {};
            analysisItem.id = $(this).find('[name=id]').val();
            analysisItem.price = $(this).find('[name^=price]').attr('data-value');
            analysisItem.taxRateValue = $(this).find('[name^=taxRateValue]').attr('data-value');
            analysisItem.remark = $(this).find('[name^=remark]').val();
            analysisItem.calculateBase = $(this).find('[name^=calculateBase]').val();
            analysisItem.calculationFormula = $(this).find('[name^=calculationFormula]').val();
            analysisItem.taxesBurden = $(this).find('[name^=taxesBurden]').val();
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
                    console.log(result.data)
                    var html = "";
                    $.each(result.data, function (i, item) {
                        var textPrice = fmoney(item.price, 2);
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += '<input type="hidden" name="id" value="' + item.id + '">';
                        html += item.taxRateName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        if (item.calculationMethod == 0) {
                            html += "<input type='text' required onblur='getThisPrice(this);' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue).toFixed(2) + "' class='form-control' data-key='" + item.typeKey + "'>";
                        } else {
                            html += "<input type='text' required onblur='getThisPrice(this);' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue * 100).toFixed(2) + "%' class='form-control x-percent' data-key='" + item.typeKey + "'>";
                        }

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculateBase_" + item.id + "' value='" + AssessCommon.toString(item.calculateBase) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculationFormula_" + item.id + "' value='" + AssessCommon.toString(item.calculationFormula) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='taxesBurden_" + item.id + "' value='" + AssessCommon.toString(item.taxesBurden) + "' class='form-control'>";
                        html += "</td>";

                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='remark_" + item.id + "' value='" + AssessCommon.toString(item.remark) + "' class='form-control'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<div class='x-valid'>";
                        html += "<input type='text' required  data-key='price_" + item.typeKey + "' name='price_" + item.id + "' data-value='" + item.price + "' value='" + textPrice + "' class='form-control' data-rule-number='true' readonly>";
                        html += "</div>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this," + item.id + ")'>" + "</span>";
                        html += "</td>";
                        html += "</tr>";
                    });

                    $("#tbody_data_section").append(html);
                    if ('${master.total}') {
                        $('#master').find('[name=total]').text('${master.total}');
                    } else {
                        initResult();
                    }
                }
            }
        });
    }

    function cleanHTMLData(_this, id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        $(_this).parent().parent().parent().empty();
                        getTotal();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
        // $(_this).parent().parent().parent().empty();
        // getTotal();
    }

    function getThisPrice(_this) {
        //增值税率
        var salesTax = $("input[data-key='data.tax.rate.allocation.sales.tax']").attr('data-value');
        //城建税率
        var constructionTax = $("input[data-key='data.tax.rate.allocation.construction.tax']").attr('data-value');
        var constructionPrice = "price_data.tax.rate.allocation.construction.tax";
        //地方教育税附加率
        var educationTax = $("input[data-key='data.tax.rate.allocation.education.fee.plus']").attr('data-value');
        var educationPrice = "price_data.tax.rate.allocation.education.fee.plus";
        var evaluationArea = $("#evaluationArea").attr('value');
        var evaluationPrice = $("#evaluationPrice").attr('value');
        var $taxRateValue = $(_this).parent().parent().find('[name^=taxRateValue]');
        var rate = $taxRateValue.val();
        var price = 0;
        if ($taxRateValue.hasClass('x-percent')) {
            rate = $taxRateValue.attr('data-value');
            var key = $taxRateValue.attr('data-key');
            switch (key) {
                //增值税
                case "data.tax.rate.allocation.sales.tax": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * rate).toFixed(2);
                    }
                    $('input[data-key="' + constructionPrice + '"]').attr("data-value", (price * constructionTax).toFixed(2));
                    $('input[data-key="' + educationPrice + '"]').attr("data-value", (price * educationTax).toFixed(2));
                    $('input[data-key="' + constructionPrice + '"]').val(fmoney(price * constructionTax, 2));
                    $('input[data-key="' + educationPrice + '"]').val(fmoney(price * educationTax, 2));
                    break;
                }
                //城建税
                case "data.tax.rate.allocation.construction.tax": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                    }
                    break;
                }
                //教育税附加
                case "data.tax.rate.allocation.education.fee.plus": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                    }
                    break;
                }
                //印花税
                case "data.tax.rate.allocation.stamp.duty": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //土地增值税
                case "data.tax.rate.allocation.land.increment.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //其它税费
                case "data.tax.rate.allocation.other.taxes.fee": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //企业所得税
                case "data.tax.rate.allocation.corporate.income.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
            }
        } else {
            if (rate && evaluationArea) {
                price = Number(evaluationArea * rate).toFixed(2);
            }
        }
        $(_this).parent().parent().find('[name^=price]').attr("data-value", price);
        $(_this).parent().parent().find('[name^=price]').val(fmoney(price, 2));
        getTotal();
    }

    //获取结果值
    function getTotal() {
        var total = 0;
        $("#tbody_data_section").find('tr').each(function () {
            var price = 0;
            price = $(this).find('[name^=price]').attr('data-value');
            if (price) {
                total += Number(price);
            }
        })
        $('#master').find('[name=total]').text(Number(total).toFixed(2));
    }


    //格式化金额
    function fmoney(s, n) {
        if(!AssessCommon.isNumber(s)) return s;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r.substring(0, 2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
    }

</script>

</html>

