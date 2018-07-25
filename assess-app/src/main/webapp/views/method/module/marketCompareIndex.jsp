<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>市场比较法</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="col-sm-12 form-group">
            <span class="col-sm-1 col-sm-offset-1 checkbox-inline">
                <input type="checkbox" checked="checked" value="text">关系值
            </span>
            <span class="col-sm-1  checkbox-inline">
                <input type="checkbox" checked="checked" value="score">修正指数
            </span>
            <span class="col-sm-1  checkbox-inline">
                <input type="checkbox" checked="checked" value="ratio">测算值
            </span>
        </div>
        <div>
            <table id="tb_mc_item" class="table table-striped jambo_table bulk_action">


            </table>
        </div>
    </div>
</div>

<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>
<script type="text/javascript">
    $(function () {
        marketCompare.init({
            element: $("#tb_mc_item"),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            cases: JSON.parse($("#casesJSON").val())
        });
    })
</script>

<script type="text/javascript">
    (function ($) {

        //四舍五入取小数
        function iTofixed(num, fractionDigits) {
            return (Math.round(num * Math.pow(10, fractionDigits)) / Math.pow(10, fractionDigits) + Math.pow(10, -(fractionDigits + 1))).toString().slice(0, -1);
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
            $(".p_text").find('a').editable({
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                }
            });

            $(".p_score").find('a').editable({
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                    if (!/^\+?[1-9][0-9]*$/.test(value)) {
                        return '只能填数字';
                    }
                    value = parseInt(value);
                    if (value < 80 || value > 120) {
                        return '分值只能在80至120之间';
                    }
                },
                url: function (params) {
                    var currScore = params.value;//修改后的值
                    var evaluationScore = $(this).closest('tr').find('[data-type="evaluation"]').text();
                    var group = $(this).closest('tr').attr('data-group');
                    var itemId = $(this).closest('td').attr('data-item-id');
                    var ratioEle = $(this).closest('tbody').find('tr[data-group=' + group + '][data-name="ratio"]').find('td[data-item-id=' + itemId + ']');
                    if (evaluationScore && currScore) {
                        evaluationScore = parseFloat(evaluationScore);
                        currScore = parseFloat(currScore);
                        $(this).removeClass('green').removeClass('red');
                        if (evaluationScore > currScore)
                            $(this).addClass('green');
                        if (evaluationScore < currScore)
                            $(this).addClass('red');
                        ratioEle.text(iTofixed(evaluationScore / currScore, 4));
                    }

                    //1.更新该案例下的比准价格
                    //2.计算出对应的修正差额
                    //3.计算出对应的案例差异
                    //4.根据填写的权重计算出加权平均价
                }
            });
        }

        //测算
        function calculation() {
            var aa = $("#").val();
        }

        var marketCompare = {};
        //初始化 1.初始化表格的标题 2.初始化表格内容
        marketCompare.init = function (options) {
            var defaluts = {
                element: undefined,//html元素
                fields: undefined,//字段信息
                evaluation: undefined,//委估对象
                cases: undefined,//案例
                readonly: false//
            };
            defaluts = $.extend({}, defaluts, options);
            //验证
            if (!defaluts.element) {
                Alert("元素为空！");
                return;
            }
            if (!defaluts.fields) {
                Alert("字段为空！");
                return;
            }
            if (!defaluts.evaluation) {
                Alert("委估对象为空！");
                return;
            }
            if (!defaluts.cases) {
                Alert("案例为空！");
                return;
            }
            marketCompare.initHead(defaluts);
            marketCompare.initBody(defaluts);
            marketCompare.initResult(defaluts);
            setElementEditable();
        }

        //初始头部
        marketCompare.initHead = function (defaluts) {
            //头部html
            var headHtml = '<thead> <tr>';
            headHtml += '<th width="5%">项目</th>';
            headHtml += '<th width="10%">委估对象</th>';
            for (var i = 1; i <= defaluts.cases.length; i++) {
                headHtml += '<th width="10%">案例' + i + '</th>';
            }
            headHtml += '</tr> </thead>';
            $(defaluts.element).append(headHtml);
        }

        //初始内容
        marketCompare.initBody = function (defaluts) {
            //字段内容html
            var bodyHtml = ' <tbody>';
            $.each(defaluts.fields, function (i, item) {
                var evaluationItem = getItemByName(JSON.parse(defaluts.evaluation.jsonContent), item.name);
                evaluationItem = evaluationItem == undefined ? {} : evaluationItem;
                if (item.bisOnlyView) {//只用于显示的字段
                    var trHtml = ' <tr>';
                    trHtml += ' <td>' + toString(item.value) + '</td>';
                    if (evaluationItem) {
                        trHtml += ' <td data-item-id="' + toString(defaluts.evaluation.id) + '">' + toString(evaluationItem.value) + '</td>';
                    }
                    for (var j = 0; j < defaluts.cases.length; j++) {
                        var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.name);
                        if (caseItem) {
                            trHtml += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + toString(caseItem.value) + '</td>';
                        }
                    }
                    trHtml += ' </tr>';
                    bodyHtml += trHtml;
                } else {//可能需要填写数据的字段
                    var rowHtml = getTempHtml("pRowTemp", defaluts.readonly);
                    rowHtml = rowHtml.replace(/{fieldName}/g, toString(item.name)).replace(/{fieldValue}/g, toString(item.value));
                    rowHtml = rowHtml.replace(/{evaluationText}/g, toString(evaluationItem.value)).replace(/{evaluationScore}/g, toString(evaluationItem.score));
                    rowHtml = rowHtml.replace(/{evaluationRatio}/g, toString(evaluationItem.ratio)).replace(/{itemId}/g, toString(defaluts.evaluation.id));
                    //取到案例相关
                    var caseText, caseScore, caseRatio;
                    for (var j = 0; j < defaluts.cases.length; j++) {
                        var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.name);
                        if (caseItem) {
                            var pTextHtml = getTempHtml("pTextTemp", defaluts.readonly);
                            var pScoreHtml = getTempHtml("pScoreTemp", defaluts.readonly);
                            caseText += pTextHtml.replace(/{fieldValue}/g, toString(item.value)).replace(/{value}/g, toString(caseItem.value)).replace(/{itemId}/g, toString(defaluts.cases[j].id));
                            caseScore += pScoreHtml.replace(/{fieldValue}/g, toString(item.value)).replace(/{score}/g, toString(caseItem.score)).replace(/{itemId}/g, toString(defaluts.cases[j].id));
                            caseRatio += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + caseItem.ratio + '</td>';
                        }
                    }
                    rowHtml = rowHtml.replace(/{caseText}/g, toString(caseText)).replace(/{caseScore}/g, toString(caseScore)).replace(/{caseRatio}/g, toString(caseRatio));
                    bodyHtml += rowHtml;
                }
            })
            bodyHtml += ' </tbody>';
            $(defaluts.element).append(bodyHtml);
        }

        //初始测算结果
        marketCompare.initResult = function (defaluts) {
            var resultHtml = getTempHtml("resultTemp", defaluts.readonly);
            var caseSpecificPrice, caseCorrectionDifference, caseCaseDifference,caseWeight;
            for (var j = 0; j < defaluts.cases.length; j++) {
                caseSpecificPrice += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + defaluts.cases[j].specificPrice + '</td>';
                caseCorrectionDifference += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + defaluts.cases[j].correctionDifference + '</td>';
                caseCaseDifference += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + defaluts.cases[j].caseDifference + '</td>';
                caseWeight += ' <td data-item-id="' + toString(defaluts.cases[j].id) + '">' + defaluts.cases[j].weight + '</td>';
            }
            resultHtml = resultHtml.replace(/{caseSpecificPrice}/g, toString(caseSpecificPrice)).replace(/{caseCorrectionDifference}/g, toString(caseCorrectionDifference));
            resultHtml = resultHtml.replace(/{caseCaseDifference}/g, toString(caseCaseDifference)).replace(/{caseWeight}/g, toString(caseWeight));
            resultHtml = resultHtml.replace(/{colspan}/g, toString(2+defaluts.cases.length));
            $(defaluts.element).append(resultHtml);
        }

        //测算
        marketCompare.calculation = function () {

        }

        window.marketCompare = marketCompare;
    })(jQuery)
</script>

<%--测算结果模板--%>
<script type="text/html" id="resultTemp">
    <tbody id="tbody_mc_result">
    <tr>
        <td colspan="{colspan}"><span style="font-weight: bold;font-size: 16px;">测算结果</span></td>
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
    <tr data-name="averagePrice">
        <td>加权平均价</td>
        <td data-item-id="{itemId}">100</td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</script>

<%--测算结果模板只读--%>
<script type="text/html" id="">

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
    <td>{score}</td>
</script>

<%--行数据模板--%>
<script type="text/html" id="pRowTemp">
    <tr data-group="{fieldName}" data-bisPrice="{bisPrice}">
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
    <tr data-group="{fieldName}" data-name="sroce">
        <td data-type="evaluation">{evaluationScore}</td>
        {caseScore}
    </tr>
    <tr data-group="{fieldName}" data-name="ratio">
        <td>{evaluationRatio}</td>
        {caseRatio}
    </tr>
</script>

<%--行数据模板只读--%>
<script type="text/html" id="pRowTempView">
    <tr>
        <td rowspan="4" style="vertical-align: middle">{fieldValue}</td>
    </tr>
    <tr>
        <td>{evaluationText}</td>
        {caseText}
    </tr>
    <tr>
        <td>{evaluationScore}</td>
        {caseScore}
    </tr>
    <tr>
        <td>{evaluationRatio}</td>
        {caseRatio}
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


