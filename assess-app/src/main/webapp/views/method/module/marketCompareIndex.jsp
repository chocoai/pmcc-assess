<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title ">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>
            市场比较法
            <small id="small_select_case">
                <input type="button" class="btn btn-primary btn-xs" value="选择案例"
                       onclick="marketCompare.loadCaseAll();">
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="col-sm-12 form-group">
            <span class="col-sm-1 col-sm-offset-1 checkbox-inline">
                <input id="cbxText" type="checkbox" checked="checked" value="text"
                       onclick="marketCompare.toggle(this);">
                <label for="cbxText">关系值</label>
            </span>
            <span class="col-sm-1  checkbox-inline">
                <input id="cbxScore" type="checkbox" checked="checked" value="score"
                       onclick="marketCompare.toggle(this);">
                <label for="cbxScore">修正指数</label>
            </span>
            <span class="col-sm-1  checkbox-inline">
                <input id="cbxRatio" type="checkbox" checked="checked" value="ratio"
                       onclick="marketCompare.toggle(this);">
                <label for="cbxRatio">测算值</label>
            </span>
        </div>
        <div>
            <input type="hidden" id="marketCompareId">
            <table id="tb_md_mc_item_list" class="table  tree">

            </table>
        </div>
    </div>
</div>
<%@include file="/views/project/tool/residueRatio.jsp" %>
<jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
<div id="modal_select_case" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择案例</h3>
            </div>
            <div class="modal-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th></th>
                        <th>名称</th>
                        <th>面积</th>
                        <th>面积说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="marketCompare.selectCase();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="selectCaseHtml">
    <tr>
        <th scope="row"><input type="checkbox" value="{planDetailsId}"></th>
        <td data-name="name">{name}</td>
        <td data-name="area">{area}</td>
        <td><input type="text" class="form-control" name="areaDesc" value="{areaDesc}"></td>
    </tr>
</script>


<script type="text/javascript">
    (function ($) {

        //取小数
        function iTofixed(num, fractionDigits) {
            return num.toFixed(fractionDigits);
        };

        //对象不存在则返回空串
        function toString(o) {
            if (!o)return "";
            return o;
        }

        //根据name获取对应元素
        function getItemByName(array, name) {
            if (!array || array.length <= 0)return;
            for (var i = 0; i < array.length; i++) {
                if (array[i].name == name) {
                    return array[i];
                }
            }
        }

        //获取模板的html
        function getTempHtml(id, readonly) {
            if (readonly) {
                id += "View";
            }
            return $("#" + id).html();
        }

        //设置元素可编辑
        function setElementEditable() {
            $(".p_text").find('a').each(function () {
                if ($(this).text()) {
                    $(this).editable({
                        validate: function (value) { //字段验证
                            if (!$.trim(value)) {
                                return '不能为空';
                            }
                        }
                    });
                }
            })

            $(".p_score").find('a').editable({
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                    if (!/^\d+(?=\.{0,1}\d+$|$)/ .test(value)) {
                        return '只能填数字';
                    }
                    value = parseInt(value);
                    if (value < 80 || value > 120) {
                        return '分值只能在80至120之间';
                    }
                    //验证是否必须调整交易价格
                    //1.检查是否必须调整2.取得初始成交价 3.取得当前成交价价
                    var itemId = $(this).closest('td').attr('data-item-id');
                    var thead = $(this).closest('table').find('thead');
                    var initialPrice = thead.find('[name=initialPrice]').val();//初始成交价
                    var mustAdjustPrice = thead.find('[name=mustAdjustPrice]').val();//是否必须调整
                    if (mustAdjustPrice == 'true') {
                        var currPrice = $(this).closest('table').find('tbody').find('[data-bisprice="true"]').find('td[data-item-id=' + itemId + ']').text();
                        if (AssessCommon.isNumber(initialPrice) && AssessCommon.isNumber(currPrice)) {
                            if (parseFloat(initialPrice) == parseFloat(currPrice)) {
                                return '请先调整该案例的交易价格';
                            }
                        }
                    }
                },
                url: function (params) {
                    var currScore = params.value;//修改后的值
                    var evaluationScore = $(this).closest('tr').find('[data-type="evaluation"]').text();
                    var group = $(this).closest('tr').attr('data-group');
                    var itemId = $(this).closest('td').attr('data-item-id');
                    var ratioEle = $(this).closest('tbody').find('tr[data-group="' + group + '"][data-name="ratio"]').find('td[data-item-id=' + itemId + ']');
                    if (evaluationScore && currScore) {
                        evaluationScore = parseFloat(evaluationScore);
                        currScore = parseFloat(currScore);
                        $(this).removeClass('bg-green').removeClass('bg-red');
                        if (evaluationScore > currScore)
                            $(this).addClass('bg-green');
                        if (evaluationScore < currScore)
                            $(this).addClass('bg-red');
                        ratioEle.text(iTofixed(evaluationScore / currScore, 4));
                    }
                    marketCompare.calculation();
                }
            });

            $(".p_weight").find('a').editable({
                validate: function (value) { //字段验证
                    if (value && !/^(0.\d{1,2})$/.test(value)) {
                        return '权重只能在0至1之间的小数，小数位数最多两位';
                    }
                },
                url: function (params) {
                    $(this).editable('setValue', params.value);//先将新值设置给元素
                    marketCompare.calculation();
                }
            });

            $(".p_weightDesc").find('a').editable({
                validate: function (value) { //字段验证

                }
            });
        }


        var marketCompare = {};
        //初始化 1.初始化表格的标题 2.初始化表格内容
        marketCompare.isPass = true;
        marketCompare.isLand = false;
        marketCompare.fields = [];
        marketCompare.mcId = 0;
        marketCompare.price = 0;
        marketCompare.evaluation = {};
        marketCompare.judgeObjectId = 0;
        marketCompare.casesAll = [];
        marketCompare.init = function (options) {
            var defaluts = {
                marketCompare: undefined,//主表信息
                fields: undefined,//字段信息
                evaluation: undefined,//委估对象
                cases: undefined,//案例
                casesAll: undefined,//所有案例
                mcId: undefined,
                judgeObjectId: undefined,
                isLand: false,//是否为土地比较法
                readonly: false//
            };
            defaluts = $.extend({}, defaluts, options);
            //验证
            if (!defaluts.marketCompare) {
                Alert("主信息为空！");
                return;
            }
            $("#marketCompareId").val(defaluts.marketCompare.id);
            if (!defaluts.fields) {
                Alert("字段为空！");
                return;
            }
            marketCompare.fields = defaluts.fields;
            if (!defaluts.evaluation) {
                Alert("委估对象为空！");
                return;
            }
            marketCompare.mcId = defaluts.mcId;
            marketCompare.areaId = defaluts.areaId;
            marketCompare.isLand = defaluts.isLand;
            marketCompare.evaluation = defaluts.evaluation;
            marketCompare.casesAll = defaluts.casesAll;
            marketCompare.judgeObjectId = defaluts.judgeObjectId;
            $("#tb_md_mc_item_list").empty();
            marketCompare.initHead(defaluts);
            marketCompare.initBody(defaluts);
            marketCompare.initResult(defaluts);

            $("#cbxScore,#cbxRatio").each(function () {
                if ($(this).prop('checked')) {
                    $(this).trigger('click');
                }
            })
            //分值背影颜色设置
            $("#tb_md_mc_item_list").find('[data-name="score"] a').each(function () {
                var currScore = $(this).text();//修改后的值
                var evaluationScore = $(this).closest('tr').find('[data-type="evaluation"]').text();
                if (evaluationScore && currScore) {
                    evaluationScore = parseFloat(evaluationScore);
                    currScore = parseFloat(currScore);
                    $(this).removeClass('bg-green').removeClass('bg-red');
                    if (evaluationScore > currScore)
                        $(this).addClass('bg-green');
                    if (evaluationScore < currScore)
                        $(this).addClass('bg-red');
                }
            })


            if (!defaluts.readonly) {
                setElementEditable();
                $('#tb_md_mc_item_list').find('tr[data-group="trading.time"][data-name="text"]').each(function () {
                    $(this).find('td').each(function () {
                        $(this).append('<input type="button" class="btn btn-xs btn-warning pull-right" onclick="marketCompare.tradingTimeExplain(this,' + defaluts.readonly + ');" value="修正说明">');
                    })
                })
                $('#tb_md_mc_item_list').find('tr[data-group="new.degree"][data-name="text"]').each(function () {
                    $(this).find('td').each(function () {
                        $(this).find('a').after('<input type="button" class="btn btn-xs btn-warning pull-right" onclick="marketCompare.callResidueRatio(this,' + defaluts.readonly + ');" value="成新率">');
                    })
                })
                if (marketCompare.isLand) {
                    var eleAnnualCoefficient = $('#tb_md_mc_item_list').find('tr[data-name="annualCoefficient"]').find('td:eq(1)');
                    $.ajax({
                        url: '${pageContext.request.contextPath}/marketCompare/getMarketCompareById',
                        data: {
                            id: marketCompare.mcId
                        },
                        async: false,
                        type: 'post',
                        dataType: 'json',
                        success: function (result) {
                            if (result.ret && result.data.rewardRateId) {
                                eleAnnualCoefficient.append('<input type="button" data-id="' + result.data.rewardRateId + '" class="btn btn-xs btn-warning pull-right" onclick="marketCompare.setRewardRate(this);" value="报酬率' + AssessCommon.pointToPercent(result.data.rewardRate) + '">');
                            } else {
                                eleAnnualCoefficient.append('<input type="button" class="btn btn-xs btn-warning pull-right" onclick="marketCompare.setRewardRate(this);" value="报酬率">');
                            }
                        }
                    })
                }

                //文本不一致设置背景颜色
                $("#tb_md_mc_item_list").find('[data-name="text"]').each(function () {
                    var tds = $(this).find('td');
                    var baseText = $(tds.get(0)).find('a').text();
                    for (var i = 1; i < tds.length; i++) {
                        var item = $(tds.get(i));
                        if (baseText != item.find('a').text()) {
                            item.find('a').css("color", "red");
                        }
                    }
                })

                marketCompare.calculation();//初始化后默认测算一次
            } else {
                //文本不一致设置背景颜色
                $("#tb_md_mc_item_list").find('[data-name="text"]').each(function () {
                    var tds = $(this).find('td');
                    var baseText = $(tds.get(0)).text();
                    for (var i = 1; i < tds.length; i++) {
                        var item = $(tds.get(i));
                        if (baseText != item.text()) {
                            item.css("color", "red");
                        }
                    }
                })
                $("#small_select_case").hide();
            }
        }

        //初始头部
        marketCompare.initHead = function (defaluts) {
            //头部html
            var headHtml = '<thead> <tr>';
            headHtml += '<th width="10%">项目</th>';
            headHtml += '<th width="20%" data-type="evaluation" data-item-id="' + defaluts.evaluation.id + '">估价对象';
            headHtml += '</th>';
            if (defaluts.cases && defaluts.cases.length > 0) {
                for (var i = 1; i <= defaluts.cases.length; i++) {
                    headHtml += '<th width="20%" data-type="case" data-item-id="' + defaluts.cases[i - 1].id + '">';
                    headHtml += defaluts.cases[i - 1].name;
                    headHtml += '</th>';
                }
            }
            headHtml += '</tr> </thead>';
            $("#tb_md_mc_item_list").append(headHtml);
        }

        //初始内容
        marketCompare.initBody = function (defaluts) {
            //字段内容html
            var bodyHtml = ' <tbody>';
            $.each(defaluts.fields, function (i, item) {
                var evaluationItem = getItemByName(JSON.parse(defaluts.evaluation.jsonContent), item.fieldName);
                evaluationItem = evaluationItem == undefined ? {} : evaluationItem;
                if (item.bisOnlyView) {//只用于显示的字段
                    if (item.canShrink) {
                        var colspan = 2;
                        if (defaluts.cases && defaluts.cases.length > 0) {
                            colspan += defaluts.cases.length;
                        }
                        var text = item.name;
                        if (item.remark) {
                            text += '<span style="font-size: 12px;color: red;font-weight: normal;">(' + item.remark + ')<span>';
                        }
                        bodyHtml += '<tr data-field-id="' + item.id + '" data-field-parent-id="' + item.pid + '" onclick="marketCompare.childrenToggle(this);">'
                            + '<td colspan="' + colspan + '" style="font-weight: 800;font-size: 16px;cursor: pointer;">'
                            + text + ' <i class="fa fa-angle-double-down"></i></td></tr>';
                    } else {
                        var trHtml = '<tr data-field-id="' + item.id + '" data-field-parent-id="' + item.pid + '" data-group="' + item.fieldName + '" data-name="utext"';
                        item.bisPrimaryKey == true ? trHtml += ' data-bisPrimaryKey="true" ' : '';
                        item.bisPrice == true ? trHtml += ' data-bisPrice="true" ' : '';
                        trHtml += '>'
                        trHtml += ' <td>' + toString(item.name) + '</td>';
                        if (evaluationItem) {
                            trHtml += ' <td data-type="evaluation" data-item-id="' + toString(defaluts.evaluation.id) + '">' + toString(evaluationItem.value) + '</td>';
                        }
                        if (defaluts.cases && defaluts.cases.length > 0) {
                            for (var j = 0; j < defaluts.cases.length; j++) {
                                var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.fieldName);
                                caseItem = caseItem == undefined ? {} : caseItem;
                                trHtml += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(caseItem.value) + '</td>';
                            }
                        }
                        trHtml += ' </tr>';
                        bodyHtml += trHtml;
                    }
                } else {//可能需要填写数据的字段
                    var rowHtml = getTempHtml(item.bisPrice ? "pRowPriceTemp" : "pRowTemp", defaluts.readonly);
                    rowHtml = rowHtml.replace(/{fieldId}/g, toString(item.id)).replace(/{fieldParentId}/g, toString(item.pid));
                    rowHtml = rowHtml.replace(/{fieldName}/g, toString(item.fieldName)).replace(/{fieldValue}/g, toString(item.name));
                    rowHtml = rowHtml.replace(/{evaluationText}/g, toString(evaluationItem.value)).replace(/{evaluationScore}/g, toString(evaluationItem.score));
                    rowHtml = rowHtml.replace(/{evaluationRatio}/g, toString(evaluationItem.ratio)).replace(/{itemId}/g, toString(defaluts.evaluation.id));
                    rowHtml = rowHtml.replace(/{bisPrice}/g, toString(item.bisPrice)).replace(/{bisPrimaryKey}/g, toString(item.bisPrimaryKey));
                    //取到案例相关
                    var caseText, caseScore, caseRatio;
                    if (defaluts.cases && defaluts.cases.length > 0) {
                        for (var j = 0; j < defaluts.cases.length; j++) {
                            var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.fieldName);
                            var pTextHtml = getTempHtml("pTextTemp", defaluts.readonly);
                            var pScoreHtml = getTempHtml("pScoreTemp", defaluts.readonly);
                            caseItem = caseItem == undefined ? {} : caseItem;
                            caseText += pTextHtml.replace(/{fieldValue}/g, toString(item.name)).replace(/{value}/g, toString(caseItem.value)).replace(/{itemId}/g, toString(defaluts.cases[j].id));
                            caseScore += pScoreHtml.replace(/{fieldValue}/g, toString(item.name)).replace(/{score}/g, toString(caseItem.score)).replace(/{itemId}/g, toString(defaluts.cases[j].id));
                            caseRatio += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(caseItem.ratio) + '</td>';
                        }
                    }
                    rowHtml = rowHtml.replace(/{caseText}/g, toString(caseText)).replace(/{caseScore}/g, toString(caseScore)).replace(/{caseRatio}/g, toString(caseRatio));
                    bodyHtml += rowHtml;
                }
            })
            bodyHtml += ' </tbody>';
            $("#tb_md_mc_item_list").append(bodyHtml);
        }

        //初始测算结果
        marketCompare.initResult = function (defaluts) {
            var resultHtml = getTempHtml("resultTemp", defaluts.readonly);
            var caseAnnualCoefficient, caseVolumeRatioCoefficient, caseSpecificPrice,
                caseCorrectionDifference, caseCaseDifference, caseWeight, caseWeightDescription;
            for (var j = 0; j < defaluts.cases.length; j++) {
                caseAnnualCoefficient += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(defaluts.cases[j].annualCoefficient) + '</td>';
                caseVolumeRatioCoefficient += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(defaluts.cases[j].volumeRatioCoefficient) + '</td>';
                caseSpecificPrice += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(defaluts.cases[j].specificPrice) + '</td>';
                caseCorrectionDifference += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(defaluts.cases[j].correctionDifference) + '</td>';
                caseCaseDifference += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(defaluts.cases[j].caseDifference) + '</td>';

                var weightHtml = getTempHtml("pWeightTemp", defaluts.readonly);
                weightHtml = weightHtml.replace(/{itemId}/g, toString(defaluts.cases[j].id)).replace(/{value}/g, toString(defaluts.cases[j].weight));
                caseWeight += weightHtml;

                var weightDescHtml = getTempHtml("pWeightDescTemp", defaluts.readonly);
                weightDescHtml = weightDescHtml.replace(/{itemId}/g, toString(defaluts.cases[j].id)).replace(/{value}/g, toString(defaluts.cases[j].weightDescription));
                caseWeightDescription += weightDescHtml;
            }
            resultHtml = resultHtml.replace(/{annualCoefficient}/g, toString(defaluts.evaluation.annualCoefficient)).replace(/{volumeRatioCoefficient}/g, toString(defaluts.evaluation.volumeRatioCoefficient));
            resultHtml = resultHtml.replace(/{caseAnnualCoefficient}/g, toString(caseAnnualCoefficient)).replace(/{caseVolumeRatioCoefficient}/g, toString(caseVolumeRatioCoefficient));
            resultHtml = resultHtml.replace(/{caseSpecificPrice}/g, toString(caseSpecificPrice)).replace(/{caseCorrectionDifference}/g, toString(caseCorrectionDifference));
            resultHtml = resultHtml.replace(/{caseCaseDifference}/g, toString(caseCaseDifference)).replace(/{caseWeight}/g, toString(caseWeight));
            resultHtml = resultHtml.replace(/{colspan}/g, toString(2 + defaluts.cases.length)).replace(/{evaluationId}/g, toString(defaluts.evaluation.id));
            resultHtml = resultHtml.replace(/{averagePrice}/g, toString(defaluts.evaluation.averagePrice)).replace(/{caseWeightDescription}/g, toString(caseWeightDescription));
            resultHtml = resultHtml.replace(/{deveDegree}/g, toString(defaluts.evaluation.deveDegree)).replace(/{evaluatePrice}/g, toString(defaluts.evaluation.evaluatePrice));
            $("#tb_md_mc_item_list").append(resultHtml);
            if (marketCompare.isLand) {
                $("#tb_md_mc_item_list").find('.forLand').show();
            }
        }

        //测算
        marketCompare.calculation = function () {
            //1.更新该案例下的比准价格
            //2.计算出对应的修正差额
            //3.计算出对应的案例差异
            //4.根据填写的权重计算出加权平均价
            var evaluationItemId;
            var caseItemIdArray = [];
            var table = $("#tb_md_mc_item_list");
            $("#tb_md_mc_item_list").find('thead th').each(function () {
                if ($(this).attr('data-type') == 'evaluation') {
                    evaluationItemId = $(this).attr('data-item-id');
                }
                if ($(this).attr('data-type') == 'case') {
                    caseItemIdArray.push($(this).attr('data-item-id'));
                }
            })

            //计算比准价格
            $.each(caseItemIdArray, function (i, item) {
                //先找到该案例的成交价，再将成交价与测算值依次相乘，最后将结果保留两位小数
                var price = table.find('tr[data-bisprice="true"]').closest('tbody').find('td[data-item-id=' + item + '].p_text').text();
                if (price && AssessCommon.isNumber(price)) {
                    var specificPrice = price = parseFloat(price);
                    table.find('tr[data-name="ratio"]').each(function () {
                        var ratio = $(this).find('td[data-item-id=' + item + ']').text();
                        if (ratio && AssessCommon.isNumber(ratio)) {
                            specificPrice = specificPrice * parseFloat(ratio);
                        }
                    })
                    if (marketCompare.isLand) {
                        var annualTr = table.find('tr[data-name="annualCoefficient"]');
                        var evaluationAnnual = annualTr.find('td[data-item-id=' + evaluationItemId + ']').find('span').text();
                        var caseAnnual = annualTr.find('td[data-item-id=' + item + ']').text();
                        if (AssessCommon.isNumber(evaluationAnnual) && AssessCommon.isNumber(caseAnnual)) {
                            specificPrice = parseFloat(caseAnnual) / parseFloat(evaluationAnnual) * specificPrice;
                        }
                        var volumeRatioTr = table.find('tr[data-name="volumeRatioCoefficient"]');
                        var evaluationVolumeRatio = volumeRatioTr.find('td[data-item-id=' + evaluationItemId + ']').text();
                        var caseVolumeRatio = volumeRatioTr.find('td[data-item-id=' + item + ']').text();
                        if (AssessCommon.isNumber(evaluationVolumeRatio) && AssessCommon.isNumber(caseVolumeRatio)) {
                            specificPrice = parseFloat(caseVolumeRatio) / parseFloat(evaluationVolumeRatio) * specificPrice;
                        }
                    }

                    specificPrice = iTofixed(specificPrice, 0);
                    table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text(specificPrice);

                    //修正差额 	修正差额不能大于30%，如果大于30%提示修改案例，修正差额=（比准价格-成交价）/成交价 的绝对值
                    var correctionDifference = iTofixed(Math.abs(specificPrice - price) / price * 100, 2);
                    var correctionDifferenceTd = table.find('tr[data-name="correctionDifference"]').find('td[data-item-id=' + item + ']');
                    correctionDifferenceTd.removeClass('red').text(correctionDifference + "%");
                    if (correctionDifference > 30) {
                        correctionDifferenceTd.text(correctionDifferenceTd.text() + " 请调整案例").addClass('red');
                        marketCompare.isPass = false;
                    } else {
                        correctionDifferenceTd.text(correctionDifferenceTd.text()).removeClass('red');
                        marketCompare.isPass = true;
                    }
                }
            })

            //案例比准价验证 经修正和调整后的各个可比实例价格中，最高单价不应超过最低单价的20％，
            //即（案例最高比准价-案例最低比准价）/案例最低比准价<=20%，如果大于20%则提示案例或修正指数修改错误；
            var maxSpecificPrice, minSpecificPrice, currSpecificPrice;
            $.each(caseItemIdArray, function (i, item) {
                currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                currSpecificPrice = parseFloat(currSpecificPrice);
                if (!maxSpecificPrice || currSpecificPrice > maxSpecificPrice) {
                    maxSpecificPrice = currSpecificPrice;
                }
                if (!minSpecificPrice || currSpecificPrice < minSpecificPrice) {
                    minSpecificPrice = currSpecificPrice;
                }
            })
            $("#resultMsg").text('');
            if ((maxSpecificPrice - minSpecificPrice) / minSpecificPrice > 0.2) {
                $("#resultMsg").text('案例或修正指数修改错误');
            } else {
                $("#resultMsg").text('');
            }

            //案例差异=（当前案例比准价-所有案例比准价中最小值）/所有案例比准价中最小值
            $.each(caseItemIdArray, function (i, item) {
                currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                currSpecificPrice = parseFloat(currSpecificPrice);
                var caseDifference = iTofixed((currSpecificPrice - minSpecificPrice) / minSpecificPrice * 100, 2);
                var caseDifferenceTd = table.find('tr[data-name="caseDifference"]').find('td[data-item-id=' + item + ']');
                caseDifferenceTd.text(caseDifference + "%");
            })

            //权重填写完整后则计算权重价，否则使用平均价
            var isWeightFinish = true;
            var averagePrice = 0;//平均价
            $.each(caseItemIdArray, function (i, item) {
                var weightText = table.find('tr[data-name="weight"]').find('td[data-item-id=' + item + ']').find('a').text()
                if (!weightText || weightText == '空') {
                    isWeightFinish = false;
                    return false;
                }
            })
            if (isWeightFinish) {
                $.each(caseItemIdArray, function (i, item) {
                    currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                    currSpecificPrice = parseFloat(currSpecificPrice);
                    var weight = table.find('tr[data-name="weight"]').find('td[data-item-id=' + item + ']').find('a').text();
                    weight = parseFloat(weight);
                    averagePrice += currSpecificPrice * weight;
                })
                averagePrice = iTofixed(averagePrice, 0);

            } else {
                //计算平均价
                var totalPrice = 0;//总价
                $.each(caseItemIdArray, function (i, item) {
                    currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                    currSpecificPrice = parseFloat(currSpecificPrice);
                    totalPrice += currSpecificPrice;
                })
                averagePrice = iTofixed(totalPrice / caseItemIdArray.length, 0);
            }
            table.find('tr[data-name="averagePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text(averagePrice);
            if (marketCompare.isLand) {
                var evaluatePrice = parseFloat(averagePrice);
                var deveDegree = table.find('tr[data-name="deveDegree"]').find('td[data-item-id=' + evaluationItemId + ']').find(':text').val();
                if (AssessCommon.isNumber(deveDegree)) {
                    evaluatePrice += parseFloat(deveDegree);
                }
                table.find('tr[data-name="evaluatePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text(evaluatePrice);
                marketCompare.price = evaluatePrice;
            } else {
                marketCompare.price = averagePrice;
            }
        }

        //切换
        marketCompare.toggle = function (_this) {
            $("#tb_md_mc_item_list").find('tr[data-name="' + $(_this).val() + '"]').each(function () {
                var td = $(this).show().closest('tbody').find('tr[data-name="field"]').find('td');
                if ($(_this).prop('checked')) {
                    $(this).show();
                    td.attr('rowspan', parseInt(td.attr('rowspan')) + 1);
                } else {
                    $(this).hide();
                    td.attr('rowspan', parseInt(td.attr('rowspan')) - 1);
                }
            })
        }

        //数据校验
        marketCompare.valid = function () {
            if (!marketCompare.isPass) {
                toastr.error('案例错误请检查案例');
                return false;
            }
            //数据验证
            //1.校验平均价是否计算出
            //2.校验是否必须填写权重信息
            //3.校验权重是否填写完整并且和是否为1
            //4.校验权重对应的权重描述是否填写
            var data = marketCompare.getData();
            if (!data.evaluationItem.averagePrice) {
                toastr.error('【平均价】还未计算出');
                return false;
            }

            var isWeightValid = false;//是否验证权重相关信息
            $.each(data.caseItemList, function (k, caseItem) {
                var caseDifference = parseFloat(caseItem.caseDifference.replace('%', ''));
                if (caseDifference >= 30) {
                    isWeightValid = true;
                    return false;
                }
            })
            if (isWeightValid) {
                var isWeightEmpty = false;
                $.each(data.caseItemList, function (k, caseItem) {
                    if (caseItem.weight == undefined || caseItem.weight == '' || caseItem.weightDescription == '空') {
                        isWeightEmpty = true;
                        return false;
                    }
                })
                if (isWeightEmpty) {
                    toastr.error('【权重】必须填写');
                    return false;
                }

                var weightTotal = 0;
                var isWeightDescEmpty = false;
                $.each(data.caseItemList, function (k, caseItem) {
                    var weight = parseFloat(caseItem.weight);
                    weightTotal = accAdd(weightTotal, weight);
                    if (caseItem.weightDescription == undefined || caseItem.weightDescription == '' || caseItem.weightDescription == '空') {
                        isWeightDescEmpty = true;
                    }
                })
                if (weightTotal != 1) {
                    toastr.error('权重和必须为1');
                    return false;
                }

                if (isWeightDescEmpty) {
                    toastr.error('【权重描述】必须填写');
                    return false;
                }
            }
            return true;
        }

        //获取需要保存的数据
        marketCompare.getData = function () {
            var evaluationItemId;
            var caseItemIdArray = [];
            var table = $("#tb_md_mc_item_list");
            $("#tb_md_mc_item_list").find('thead th').each(function () {
                if ($(this).attr('data-type') == 'evaluation') {
                    evaluationItemId = $(this).attr('data-item-id');
                }
                if ($(this).attr('data-type') == 'case') {
                    caseItemIdArray.push($(this).attr('data-item-id'));
                }
            })
            var averagePrice = table.find('tr[data-name="averagePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text();
            var deveDegree = table.find('tr[data-name="deveDegree"]').find('td[data-item-id=' + evaluationItemId + ']').find(':text').val();
            var evaluatePrice = table.find('tr[data-name="evaluatePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text();

            var data = {
                id: $("#marketCompareId").val(),
                evaluationItem: {},
                caseItemList: []
            };
            data.evaluationItem.jsonContent = [];
            data.evaluationItem.id = evaluationItemId;
            data.evaluationItem.averagePrice = averagePrice;
            data.evaluationItem.deveDegree = deveDegree;
            data.evaluationItem.evaluatePrice = evaluatePrice;
            $.each(marketCompare.fields, function (j, field) {
                if (field.fieldName) {
                    var fieldContent = {};
                    fieldContent.name = field.fieldName;
                    fieldContent.score = 100;
                    fieldContent.ratio = 1;

                    var trs = table.find('tr[data-group="' + field.fieldName + '"]');
                    if (trs.length > 0) {
                        trs.each(function () {
                            if ($(this).attr('data-name') == 'text' || $(this).attr('data-name') == 'utext') {
                                var td = fieldContent.value = $(this).find('td[data-item-id=' + evaluationItemId + ']');
                                if (td.find('a').length > 0) {
                                    fieldContent.value = td.find('a').text();
                                }
                                else {
                                    fieldContent.value = td.text();
                                }
                            }
                        })
                    }
                    fieldContent.value = fieldContent.value == '空' ? '' : fieldContent.value;
                    data.evaluationItem.jsonContent.push(fieldContent);
                }
            });

            $.each(caseItemIdArray, function (i, item) {
                var caseItem = {};
                caseItem.jsonContent = [];
                $.each(marketCompare.fields, function (j, field) {
                    if (field.fieldName) {
                        var fieldContent = {};
                        fieldContent.name = field.fieldName;
                        fieldContent.score = 100;
                        fieldContent.ratio = 1;

                        var trs = table.find('tr[data-group="' + field.fieldName + '"]');
                        if (trs.length > 0) {
                            trs.each(function () {
                                if ($(this).attr('data-name') == 'text' || $(this).attr('data-name') == 'utext') {
                                    var td = fieldContent.value = $(this).find('td[data-item-id=' + item + ']');
                                    if (td.find('a').length > 0) {
                                        fieldContent.value = td.find('a').text();
                                    }
                                    else {
                                        fieldContent.value = td.text();
                                    }
                                }
                                if ($(this).attr('data-name') == 'score') {
                                    fieldContent.score = $(this).find('td[data-item-id=' + item + ']').find('a').text();
                                    fieldContent.score = fieldContent.score == '空' ? '' : fieldContent.score;
                                }
                                if ($(this).attr('data-name') == 'ratio') {
                                    fieldContent.ratio = $(this).find('td[data-item-id=' + item + ']').text();
                                    fieldContent.ratio = fieldContent.ratio == '空' ? '' : fieldContent.ratio;
                                }
                            })
                        }
                        fieldContent.value = fieldContent.value == '空' ? '' : fieldContent.value;
                        caseItem.jsonContent.push(fieldContent);
                    }
                })
                caseItem.id = item;
                caseItem.specificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                caseItem.correctionDifference = table.find('tr[data-name="correctionDifference"]').find('td[data-item-id=' + item + ']').text();
                caseItem.caseDifference = table.find('tr[data-name="caseDifference"]').find('td[data-item-id=' + item + ']').text();
                caseItem.weight = table.find('tr[data-name="weight"]').find('td[data-item-id=' + item + ']').find('a').text();
                caseItem.weightDescription = table.find('tr[data-name="weightDescription"]').find('td[data-item-id=' + item + ']').find('a').text();
                if (caseItem.weight == '空') {
                    caseItem.weight = '';
                }
                if (caseItem.weightDescription == '空') {
                    caseItem.weightDescription = '';
                }
                data.caseItemList.push(caseItem);
            })
            return data;
        }

        //保存
        marketCompare.save = function (callback) {
            var data = marketCompare.getData();
            $.ajax({
                url: '${pageContext.request.contextPath}/marketCompare/saveResult',
                data: {
                    formData: JSON.stringify(data)
                },
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.ret) {
                        if (callback)
                            callback(result.data.id);
                    } else {
                        Alert('保存数据异常，' + result.errmsg);
                    }
                }
            })
        }

        //加载所有案例
        marketCompare.loadCaseAll = function () {
            var planDetailsIdArray = [];
            $.each(marketCompare.casesAll, function (i, item) {
                planDetailsIdArray.push(item.id);
            })
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/marketCompare/getCasesAll',
                data: {
                    planDetailsIds: planDetailsIdArray.join()
                },
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret && result.data) {
                        var html = '';
                        $.each(result.data, function (i, item) {
                            var htmlTemp = $("#selectCaseHtml").html();
                            htmlTemp = htmlTemp.replace(/{planDetailsId}/, item.planDetailsId).replace(/{name}/, item.name);
                            htmlTemp = htmlTemp.replace(/{area}/, item.area).replace(/{areaDesc}/, AssessCommon.toString(item.areaDesc));
                            html += htmlTemp;
                        })
                        $("#modal_select_case").find('tbody').empty().append(html);
                        $('#modal_select_case').modal();
                    }
                }
            })

        }

        //选择案例
        marketCompare.selectCase = function () {
            //如果案例的面积超过估价对象面积3倍则必须为面积添加说明
            var cbxs = $("#modal_select_case").find('input:checkbox:checked');
            if (cbxs.length <= 0) {
                Alert("还未选择任何案例");
                return false;
            }

            var errmsg = '';
            var areaDescArray = [];
            var evaluationArea = marketCompare.evaluation.area;
            $.each(cbxs, function (i, item) {
                var tr = $(item).closest('tr');
                var name = tr.find('[data-name=name]').text();
                var area = tr.find('[data-name=area]').text();
                var areaDesc = tr.find('[name=areaDesc]').val();
                if (AssessCommon.isNumber(area) && AssessCommon.isNumber(evaluationArea)) {
                    if (parseFloat(evaluationArea) * 3 < parseFloat(area) && areaDesc.length <= 0) {
                        errmsg += name + "必须填写面积说明";
                        return false;
                    }
                }
                areaDescArray.push({"planDetailsId": $(item).val(), "areaDesc": areaDesc});
            })
            if (errmsg.length > 0) {
                Alert(errmsg);
                return false;
            }
            var caseArray = [];
            $.each(cbxs, function (i, item) {
                caseArray.push($(item).val());
            })
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/marketCompare/selectCase',
                data: {
                    mcId: marketCompare.mcId,
                    isLand: marketCompare.isLand,
                    judgeObjectId: marketCompare.judgeObjectId,
                    areaDescJson: JSON.stringify(areaDescArray)
                },
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("选择成功！");
                        $('#modal_select_case').modal('hide');
                        marketCompare.init({
                            mcId: result.data.mcId,
                            judgeObjectId: result.data.judgeObjectId,
                            marketCompare: result.data.marketCompare,
                            fields: result.data.fields,
                            evaluation: result.data.evaluation,
                            casesAll: marketCompare.casesAll,
                            isLand: marketCompare.isLand,
                            areaId: marketCompare.areaId,
                            cases: result.data.cases
                        });
                    } else {
                        Alert('选择案例异常，' + result.errmsg);
                    }
                }
            })
        }

        //控制子项的显示隐藏
        marketCompare.childrenToggle = function (_this) {
            var fieldParentId = $(_this).attr('data-field-id');
            $(_this).closest('table').find('[data-field-parent-id=' + fieldParentId + ']').toggle();
        }

        //调用成新率工具
        marketCompare.callResidueRatio = function (_this, readonly) {
            //获取已使用年限,当前评估基准日-楼栋的竣工时间
            //获取可用年限，根据建筑使用年限配置而来
            var itemId = $(_this).closest('td').attr('data-item-id');
            $.ajax({
                url: '${pageContext.request.contextPath}/marketCompare/getMarketCompareItemById',
                data: {id: itemId},
                success: function (result) {
                    if (result.ret) {
                        residueRatio.init({
                            readonly: readonly,
                            residueRatioId: result.data.residueRatioId,
                            usedYear: result.data.usedYear,
                            usableYear: result.data.usableYear,
                            houseId: result.data.houseId,
                            success: function (id, resultValue) {
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/marketCompare/saveMarketCompareItem',
                                    data: {
                                        id: itemId,
                                        residueRatioId: id
                                    },
                                    success: function (result) {
                                    }
                                })
                                $(_this).closest('td').find('a').editable('setValue', resultValue);
                            }
                        });
                    }
                }
            })
            var th = $('#tb_md_mc_item_list').find('thead th[data-item-id=' + itemId + ']');
        }

        //交易时间修正说明
        marketCompare.tradingTimeExplain = function (_this, readonly) {
            var itemId = $(_this).closest('td').attr('data-item-id');
            $.ajax({
                url: '${pageContext.request.contextPath}/marketCompare/getMarketCompareItemById',
                data: {id: itemId},
                success: function (result) {
                    if (result.ret) {
                        var tradingTimeExplain = result.data.tradingTimeExplain;
                        if (readonly) {
                            layer.alert(tradingTimeExplain);
                        } else {
                            layer.prompt({
                                formType: 2,
                                value: tradingTimeExplain,
                                title: '交易时间修正说明',
                                area: ['500px', '150px']
                            }, function (value, index, elem) {
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/marketCompare/saveMarketCompareItem',
                                    data: {
                                        id: itemId,
                                        tradingTimeExplain: value
                                    },
                                    success: function (result) {
                                        if (result.ret) {
                                            toastr.success("保存成功");
                                            layer.close(index);
                                        } else {
                                            toastr.error(result.errmsg);
                                        }
                                    }
                                })
                            });
                        }
                    } else {
                        toastr.error(result.errmsg);
                    }
                }
            })
        }

        //设置报酬率
        marketCompare.setRewardRate = function (_this) {
            rewardRateFunc.calculation($(_this).attr('data-id'), function (data) {
                if (data) {
                    //更新对应数据
                    $(_this).attr('data-id', data.id);
                    $(_this).val("报酬率" + data.resultValue);
                    $.ajax({
                        url: '${pageContext.request.contextPath}/marketCompare/updateAnnualCoefficient',
                        data: {
                            judgeObjectId: marketCompare.judgeObjectId,
                            mcId: marketCompare.mcId,
                            rewardRateId: data.id,
                            rewardRate: AssessCommon.percentToPoint(data.resultValue)
                        },
                        success: function (result) {
                            if (result.ret) {
                                //更新页面相关数据 重新测算一次
                                if (result.data && result.data.length > 0) {
                                    var annualTr = $("#tb_md_mc_item_list").find('tr[data-name="annualCoefficient"]');
                                    $.each(result.data, function (i, item) {
                                        var annualTd = annualTr.find('td[data-item-id=' + item.key + ']');
                                        if (annualTd.find('span').length > 0) {
                                            annualTd.find('span').text(item.value);
                                        } else {
                                            annualTd.text(item.value);
                                        }
                                    })
                                }
                                marketCompare.calculation();
                            } else {
                                toastr.error(result.errmsg);
                            }
                        }
                    })
                }
            })
        }

        window.marketCompare = marketCompare;
    })(jQuery)
</script>

<%--测算结果模板--%>
<script type="text/html" id="resultTemp">
    <tbody id="tbody_mc_result">
    <tr data-name="annualCoefficient" class="forLand" style="display: none;">
        <td>使用年期修正系数</td>
        <td data-item-id="{evaluationId}"><span>{annualCoefficient}</span></td>
        {caseAnnualCoefficient}
    </tr>
    <tr data-name="volumeRatioCoefficient" class="forLand" style="display: none;">
        <td>容积率修正系数</td>
        <td data-item-id="{evaluationId}">{volumeRatioCoefficient}</td>
        {caseVolumeRatioCoefficient}
    </tr>
    <tr>
        <td colspan="{colspan}"><span style="font-weight: bold;font-size: 16px;">测算结果 </span>
            <span class="red" id="resultMsg"></span>
        </td>
    </tr>
    <tr data-name="specificPrice">
        <td>比准价格</td>
        <td></td>
        {caseSpecificPrice}
    </tr>
    <tr data-name="correctionDifference">
        <td>修正差额</td>
        <td></td>
        {caseCorrectionDifference}
    </tr>
    <tr data-name="caseDifference">
        <td>案例差异</td>
        <td></td>
        {caseCaseDifference}
    </tr>
    <tr data-name="weight">
        <td>权重</td>
        <td></td>
        {caseWeight}
    </tr>
    <tr data-name="weightDescription">
        <td>权重描述</td>
        <td></td>
        {caseWeightDescription}
    </tr>
    <tr data-name="averagePrice">
        <td>加权平均价</td>
        <td data-item-id="{evaluationId}">{averagePrice}</td>
        <td></td>
        <td></td>
    </tr>
    <tr data-name="deveDegree" class="forLand" style="display: none;">
        <td>开发程度</td>
        <td data-item-id="{evaluationId}">
            <input type="text" data-rule-number="true" class="form-control" value="{deveDegree}" style="width: 50%;"
                   onblur="marketCompare.calculation();">
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr data-name="evaluatePrice" class="forLand" style="display: none;">
        <td>评估价格</td>
        <td data-item-id="{evaluationId}">{evaluatePrice}</td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</script>

<%--测算结果模板只读--%>
<script type="text/html" id="resultTempView">
    <tbody>
    <tr data-name="annualCoefficient" class="forLand" style="display: none;">
        <td>使用年期修正系数</td>
        <td data-item-id="{evaluationId}"><span>{annualCoefficient}</span></td>
        {caseAnnualCoefficient}
    </tr>
    <tr data-name="volumeRatioCoefficient" class="forLand" style="display: none;">
        <td>容积率修正系数</td>
        <td data-item-id="{evaluationId}">{volumeRatioCoefficient}</td>
        {caseVolumeRatioCoefficient}
    </tr>
    <tr>
        <td colspan="{colspan}"><span style="font-weight: bold;font-size: 16px;">测算结果 </span>

        </td>
    </tr>
    <tr>
        <td>比准价格</td>
        <td></td>
        {caseSpecificPrice}
    </tr>
    <tr>
        <td>修正差额</td>
        <td></td>
        {caseCorrectionDifference}
    </tr>
    <tr>
        <td>案例差异</td>
        <td></td>
        {caseCaseDifference}
    </tr>
    <tr>
        <td>权重</td>
        <td></td>
        {caseWeight}
    </tr>
    <tr>
        <td>权重描述</td>
        <td></td>
        {caseWeightDescription}
    </tr>
    <tr>
        <td>加权平均价</td>
        <td>{averagePrice}</td>
        <td></td>
        <td></td>
    </tr>
    <tr data-name="deveDegree" class="forLand" style="display: none;">
        <td>开发程度</td>
        <td data-item-id="{evaluationId}">
            {deveDegree}
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr data-name="evaluatePrice" class="forLand" style="display: none;">
        <td>评估价格</td>
        <td data-item-id="{evaluationId}">{evaluatePrice}</td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</script>

<%--文本模板--%>
<script type="text/html" id="pTextTemp">
    <td class="p_text" data-item-id="{itemId}">
        <a href="javascript://" data-type="textarea"
           data-original-title="{fieldValue}"
           class="editable editable-click editable-pre-wrapped">{value}</a>
    </td>
</script>

<%--文本模板只读--%>
<script type="text/html" id="pTextTempView">
    <td>{value}</td>
</script>

<%--分值模板--%>
<script type="text/html" id="pScoreTemp">
    <td class="p_score" data-item-id="{itemId}">
        <a href="javascript://" data-original-title="分值"
           class="editable editable-click editable-pre-wrapped">{score}</a>
    </td>
</script>

<%--分值模板只读--%>
<script type="text/html" id="pScoreTempView">
    <td><a href="javascript://">{score}</a></td>
</script>

<%--行数据模板--%>
<script type="text/html" id="pRowTemp">
    <tbody data-field-id="{fieldId}" data-field-parent-id="{fieldParentId}" style="background-color: #fbfbfb">
    <tr data-group="{fieldName}" data-bisPrice="{bisPrice}" data-bisPrimaryKey="{bisPrimaryKey}" data-name="field">
        <td rowspan="4" style="vertical-align: middle">{fieldValue}</td>
    </tr>
    <tr data-group="{fieldName}" data-name="text">
        <td class="p_text" data-item-id="{itemId}">
            <a href="javascript://" data-type="textarea"
               data-original-title="{fieldValue}"
               class="editable editable-click editable-pre-wrapped">{evaluationText}</a>
        </td>
        {caseText}
    </tr>
    <tr data-group="{fieldName}" data-name="score">
        <td data-type="evaluation">{evaluationScore}</td>
        {caseScore}
    </tr>
    <tr data-group="{fieldName}" data-name="ratio">
        <td>{evaluationRatio}</td>
        {caseRatio}
    </tr>
    </tbody>
</script>

<%--行数据模板只读--%>
<script type="text/html" id="pRowTempView">
    <tbody data-field-id="{fieldId}" data-field-parent-id="{fieldParentId}" style="background-color: #fbfbfb">
    <tr data-name="field">
        <td rowspan="4" style="vertical-align: middle">{fieldValue}</td>
    </tr>
    <tr data-name="text">
        <td>{evaluationText}</td>
        {caseText}
    </tr>
    <tr data-name="score">
        <td data-type="evaluation">{evaluationScore}</td>
        {caseScore}
    </tr>
    <tr data-name="ratio">
        <td>{evaluationRatio}</td>
        {caseRatio}
    </tr>
    </tbody>
</script>

<%--交易价格行数据模板--%>
<script type="text/html" id="pRowPriceTemp">
    <tr data-group="{fieldName}" data-bisPrice="{bisPrice}" data-bisPrimaryKey="{bisPrimaryKey}" data-name="utext">
        <td style="vertical-align: middle">{fieldValue}</td>
        <td class="p_text" data-item-id="{itemId}">
            <a href="javascript://" data-type="textarea"
               data-original-title="{fieldValue}"
               class="editable editable-click editable-pre-wrapped">{evaluationText}</a>
        </td>
        {caseText}
    </tr>
</script>

<%--交易价格行数据模板只读--%>
<script type="text/html" id="pRowPriceTempView">
    <tr>
        <td style="vertical-align: middle">{fieldValue}</td>
        <td>{evaluationText}</td>
        {caseText}
    </tr>
</script>


<%--行显示数据模板--%>
<script type="text/html" id="pReadonlyRowTemp">
    <tr data-bisPrice="{bisPrice}">
        <td>{fieldValue}</td>
        <td>{evaluationText}</td>
        {caseText}
    </tr>
</script>

<%--权重模板--%>
<script type="text/html" id="pWeightTemp">
    <td class="p_weight" data-item-id="{itemId}">
        <a href="javascript://" data-original-title="权重"
           class="editable editable-click editable-pre-wrapped">{value}</a>
    </td>
</script>

<%--权重模板只读--%>
<script type="text/html" id="pWeightTempView">
    <td>{value}</td>
</script>

<%--权重模板--%>
<script type="text/html" id="pWeightDescTemp">
    <td class="p_weightDesc" data-item-id="{itemId}">
        <a href="javascript://" data-original-title="权重描述" data-type="textarea"
           class="editable editable-click editable-pre-wrapped">{value}</a>
    </td>
</script>

<%--权重模板只读--%>
<script type="text/html" id="pWeightDescTempView">
    <td>{value}</td>
</script>
