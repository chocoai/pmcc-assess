<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<script type="text/html" id="taskLiquidationAnalysisDiv">
    <div class="x_panel">
        <div class="x_title">
            <h3>变现分析税费（0{index}）
                <small>
                    <a href="javascript://;" class="btn btn-xs btn-warning" onclick="saveAnalysisGroup(true,'taskLiquidationAnalysisFrm_number');">保存</a>
                    <a href="javascript://;" class="btn btn-xs btn-warning" onclick="cleanHTMLData(this)">移除</a>
                </small>
            </h3>
        </div>
        <div class="x_content">
            <form class="form-horizontal" id="taskLiquidationAnalysisFrm_number">
                <input type="hidden" name="id">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权证信息<span
                                class="symbol required"></span></label>
                        <div class=" col-xs-560  col-sm-560  col-md-560  col-lg-560 " style="overflow:auto;height:60px;">
                            <select class="form-control search-select select2" multiple="multiple" required="required"
                                    name="recordIds" onchange="getGroupAndPriceVo(this);">
                                <c:forEach var="items" items="${declareRecordList}">
                                    <option value="${items.id}">${items.name}</option>
                                </c:forEach>
                            </select>
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
                        <th class="hidden-xs">单位（面积/m² 金额/元）</th>
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
                        <td class="hidden-xs" name="evaluationArea">0
                        </td>
                    </tr>
                    <tr>
                        <td class="hidden-xs">评估价(元)</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs" name="evaluationPrice">0
                        </td>
                    </tr>
                    </tbody>
                    <tbody name="tbody_data_section">

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

    </div>
</script>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div>
                    <h3>变现分析
                        <small>
                            <a href="javascript://;" class="btn btn-xs btn-success" onclick="appendHtml(false)">添加分组<i
                                    class="fa fa-plus"></i>
                            </a>
                        </small>
                    </h3>
                </div>
                <div class="x_content">
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
                    </form>
                </div>
            </div>

            <div id="taskLiquidationAnalysisAppend">

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
    var commonField = {
        taskLiquidationAnalysisFrm: "taskLiquidationAnalysisFrm",
        taskLiquidationAnalysisAppend: "taskLiquidationAnalysisAppend",
        taskLiquidationAnalysisDiv: "taskLiquidationAnalysisDiv",

    };


    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    function appendHtml(flag) {
        if (flag) {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeLiquidationAnalysisGroupList",
                type: "get",
                dataType: "json",
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#" + commonField.taskLiquidationAnalysisDiv).html();
                                var number = item.id;
                                html = html.replace(/_number/g, number).replace(/{index}/g, i+1);
                                $("#" + commonField.taskLiquidationAnalysisAppend).append(html);

                                $("#" + commonField.taskLiquidationAnalysisFrm + number).initForm(item);
                                if (item.recordIds) {
                                    $("#" + commonField.taskLiquidationAnalysisFrm + number).find("select[name='recordIds']").val(item.recordIds.split(",")).trigger("change").select2();
                                } else {
                                    $("#" + commonField.taskLiquidationAnalysisFrm + number).find("select[name='recordIds']").select2();
                                }
                                getAnalysisItemList(number);
                                setTimeout(function () {
                                    if(item.total){
                                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find('[name=total]').text(fmoney(Number(item.total).toFixed(2), 2));
                                    };
                                }, 500);


                            });
                        } else {
                            flag = false;
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        if (!flag) {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/addLiquidationAnalysisGroup",
                type: "post",
                dataType: "json",
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    areaId: '${projectPlanDetails.areaId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        var html = $("#" + commonField.taskLiquidationAnalysisDiv).html();
                        var index = $("#" + commonField.taskLiquidationAnalysisAppend).find('.x_panel').length;
                        var number = result.data.id;
                        html = html.replace(/_number/g, number).replace(/{index}/g, index+1);
                        $("#" + commonField.taskLiquidationAnalysisAppend).append(html);
                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find("select[name='recordIds']").select2();

                        $("#" + commonField.taskLiquidationAnalysisFrm + number).initForm(result.data);
                        getAnalysisItemList(number);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }

    }

    $(function () {
        appendHtml(true);
    });
    /**
     * 清除html
     * @param _this
     */
    function cleanHTMLData(_this) {
        var x_panel = $(_this).closest(".x_panel");
        var form = x_panel.find("form").eq(0);
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/removeLiquidationAnalysisGroup",
            type: "post",
            dataType: "json",
            data: {id: form.find("input[name='id']").val()},
            success: function (result) {
                if (result.ret) {
                    x_panel.remove();
                    toastr.success('移除成功');
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    /**
     * 添加税率明细
     * @param _this
     */
    function getGroupAndPriceVo(_this) {
        var recordIds = $(_this).val();
        var frmId = $(_this).closest('.form-horizontal').attr("id");
        console.log(recordIds);
        if(recordIds){
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getGroupAndPriceVo",
                type: "post",
                dataType: "json",
                data: {recordIds: JSON.stringify(recordIds)},
                success: function (result) {
                    if (result.ret) {
                        if(result.data) {

                            initResult(frmId, result.data.groupArea, result.data.groupPrice);
                            $("#" + frmId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                            $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    //初始化计算结果
    function initResult(frmId,evaluationArea,evaluationPrice) {
        var salesTax = "${salesTax}";
        var total = 0;
        var evaluationArea = evaluationArea;
        var evaluationPrice = evaluationPrice;
        $("#" + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var $taxRateValue =  $(this).find('[name^=taxRateValue]');
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
                    //地方教育税附加
                    case "data.tax.rate.allocation.local.education.tax.additional": {
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

        $("#" + frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));
    }


    function getAnalysisItemList(groupId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getAnalysisItemListByGroupId",
            data: {
                groupId: groupId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        var textPrice = fmoney(item.price, 2);
                        if(!textPrice){
                            textPrice = 0;
                        }
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
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculateBase_" + item.id + "' value='" + AssessCommon.toString(item.calculateBase) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculationFormula_" + item.id + "' value='" + AssessCommon.toString(item.calculationFormula) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='taxesBurden_" + item.id + "' value='" + AssessCommon.toString(item.taxesBurden) + "' class='form-control'>";
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
                        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanItemData(this," + item.id + ")'>" + "</span>";
                        html += "</td>";
                        html += "</tr>";
                    });

                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").append(html);

                }
            }
        });
    }

    function getThisPrice(_this) {
        var frmId = $(_this).closest('.form-horizontal').attr("id");
        //增值税率
        var salesTax = $("#"+frmId).find("input[data-key='data.tax.rate.allocation.sales.tax']").attr('data-value');
        console.log("salesTax:"+salesTax)
        //城建税率
        var constructionTax = $("#"+frmId).find("input[data-key='data.tax.rate.allocation.construction.tax']").attr('data-value');
        console.log("constructionTax:"+constructionTax)
        var constructionPrice = "price_data.tax.rate.allocation.construction.tax";
        //教育税附加率
        var educationTax = $("#"+frmId).find("input[data-key='data.tax.rate.allocation.education.fee.plus']").attr('data-value');
        console.log("educationTax:"+educationTax)
        var educationPrice = "price_data.tax.rate.allocation.education.fee.plus";
        //地方教育税附加率
        var landEducationTax = $("#"+frmId).find("input[data-key='data.tax.rate.allocation.local.education.tax.additional']").attr('data-value');
        console.log("landEducationTax:"+landEducationTax)
        var landEducationPrice = "price_data.tax.rate.allocation.local.education.tax.additional";

        var evaluationArea = $("#"+frmId).find("td[name='evaluationArea']").val();
        var evaluationPrice = $("#"+frmId).find("td[name='evaluationPrice']").val();
        console.log(evaluationArea+evaluationPrice+"ppp")
        var $taxRateValue = $(_this).parent().parent().find('[name^=taxRateValue]');
        var rate = $taxRateValue.val();
        console.log("rate:"+rate)
        var price = 0;
        if ($taxRateValue.hasClass('x-percent')) {
            rate = $taxRateValue.attr('data-value');
            console.log("rate2:"+rate)
            var key = $taxRateValue.attr('data-key');
            console.log("key:"+key);
            switch (key) {
                //增值税
                case "data.tax.rate.allocation.sales.tax": {
                    console.log(rate+"==="+Number(evaluationPrice))
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        console.log("temp"+temp)
                        price = Number(temp * rate).toFixed(2);
                        console.log("ptice:"+price)
                    }
                    $("#"+frmId).find('input[data-key="' + constructionPrice + '"]').attr("data-value", (price * constructionTax).toFixed(2));
                    $("#"+frmId).find('input[data-key="' + educationPrice + '"]').attr("data-value", (price * educationTax).toFixed(2));
                    $("#"+frmId).find('input[data-key="' + landEducationPrice + '"]').attr("data-value", (price * landEducationTax).toFixed(2));
                    $("#"+frmId).find('input[data-key="' + constructionPrice + '"]').val(fmoney(price * constructionTax, 2));
                    $("#"+frmId).find('input[data-key="' + educationPrice + '"]').val(fmoney(price * educationTax, 2));
                    $("#"+frmId).find('input[data-key="' + landEducationPrice + '"]').val(fmoney(price * landEducationTax, 2));
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
                //地方教育税附加
                case "data.tax.rate.allocation.local.education.tax.additional": {
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
        $(_this).parent().parent().find('[name^=price]').attr("data-value", price);
        $(_this).parent().parent().find('[name^=price]').val(fmoney(price, 2));
        getTotal(frmId);
    }

    //获取结果值
    function getTotal(frmId) {
        var total = 0;
        $("#"+frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var price = 0;
            price = $(this).find('[name^=price]').attr('data-value');
            if (price) {
                total += Number(price);
            }
        })
        $("#"+frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2),2));

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

    function cleanItemData(_this, id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        var frmId = $(_this).closest('.form-horizontal').attr("id");
                        $(_this).parent().parent().parent().empty();
                        console.log(frmId+"-=-=-=-=")
                        getTotal(frmId);
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
    }

    //获取单个保存的数据
    function getFormData(frmId) {
        var data = {};
        data.id = $('#'+frmId).find('[name=id]').val();
        data.recordIds = $('#'+frmId).find("select[name='recordIds']").val();
        data.total = $('#'+frmId).find('[name=total]').text().replace(/,/g,'');
        data.analysisItemList = [];
        $('#'+frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
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

    /**
     * 单个保存
     * @param _this
     * @returns {boolean}
     */
    function saveAnalysisGroup(flag, _this) {
        if (flag) {
            //单个保存
            var data = getFormData(_this);
            console.log(data);
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/saveAnalysisGroup",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {

        }
    }

    function submit() {
        if (!$("#master").valid()) {
            return false;
        }

        var formData = {};
        formData.id = $('#master').find('[name=id]').val();
        formData.liquidRatios = $('#master').find('[name=liquidRatios]').val();
        formData.liquidTime = $('#master').find('[name=liquidTime]').val();
        formData.taskLiquidationAnalysisGroups = [];
        var forms = $("#" + commonField.taskLiquidationAnalysisAppend).find("form");
        //校验
        for (var i = 0; i < forms.size(); i++) {
            if (!$(forms[i]).valid()) {
                return false;
            }
        }
        $.each(forms, function (i, n) {
            var text = $(n).attr("id");
            if (text.indexOf(commonField.taskLiquidationAnalysisFrm) != -1) {
                var item = getFormData(text);
                formData.taskLiquidationAnalysisGroups.push(item);
            }
        });
        console.log(formData);
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

</script>


</html>

