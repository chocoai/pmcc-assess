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
            <table id="tb_md_mc_item_list" class="table  jambo_table bulk_action">

            </table>
        </div>
    </div>
</div>

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
                    marketCompare.calculation();
                }
            });

            $(".p_weight").find('a').editable({
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                }
            });

            $(".p_weightDesc").find('a').editable({
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                }
            });
        }


        var marketCompare = {};
        //初始化 1.初始化表格的标题 2.初始化表格内容
        marketCompare.isPass = true;
        marketCompare.fields = [];
        marketCompare.init = function (options) {
            var defaluts = {
                marketCompare: undefined,//主表信息
                fields: undefined,//字段信息
                evaluation: undefined,//委估对象
                cases: undefined,//案例
                readonly: false//
            };
            defaluts = $.extend({}, defaluts, options);
            //验证
            if (!defaluts.marketCompare) {
                Alert("主信息为空！");
                return;
            }
            if (!defaluts.fields) {
                Alert("字段为空！");
                return;
            }
            marketCompare.fields = defaluts.fields;
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
            headHtml += '<th width="10%" data-type="evaluation" data-item-id="' + defaluts.evaluation.id + '">委估对象</th>';
            for (var i = 1; i <= defaluts.cases.length; i++) {
                headHtml += '<th width="10%" data-type="case" data-item-id="' + defaluts.cases[i - 1].id + '">案例' + i + '</th>';
            }
            headHtml += '</tr> </thead>';
            $("#tb_md_mc_item_list").append(headHtml);
        }

        //初始内容
        marketCompare.initBody = function (defaluts) {
            //字段内容html
            var bodyHtml = ' <tbody>';
            $.each(defaluts.fields, function (i, item) {
                var evaluationItem = getItemByName(JSON.parse(defaluts.evaluation.jsonContent), item.name);
                evaluationItem = evaluationItem == undefined ? {} : evaluationItem;
                if (item.bisOnlyView) {//只用于显示的字段
                    var trHtml = '<tr';
                    item.bisPrimaryKey == true ? trHtml += ' data-bisPrimaryKey="true" ' : '';
                    item.bisPrice == true ? trHtml += ' data-bisPrice="true" ' : '';
                    trHtml += '>'
                    var trHtml = item.bisPrice == true ? '<tr data-bisPrice="true">' : '<tr>';
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
                    rowHtml = rowHtml.replace(/{bisPrice}/g, toString(item.bisPrice)).replace(/{bisPrimaryKey}/g, toString(item.bisPrimaryKey));
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
            $("#tb_md_mc_item_list").append(bodyHtml);
        }

        //初始测算结果
        marketCompare.initResult = function (defaluts) {
            var resultHtml = getTempHtml("resultTemp", defaluts.readonly);
            var caseSpecificPrice, caseCorrectionDifference, caseCaseDifference, caseWeight, caseWeightDescription;
            for (var j = 0; j < defaluts.cases.length; j++) {
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
            resultHtml = resultHtml.replace(/{caseSpecificPrice}/g, toString(caseSpecificPrice)).replace(/{caseCorrectionDifference}/g, toString(caseCorrectionDifference));
            resultHtml = resultHtml.replace(/{caseCaseDifference}/g, toString(caseCaseDifference)).replace(/{caseWeight}/g, toString(caseWeight));
            resultHtml = resultHtml.replace(/{colspan}/g, toString(2 + defaluts.cases.length)).replace(/{evaluationId}/g, toString(defaluts.evaluation.id));
            resultHtml = resultHtml.replace(/{caseWeightDescription}/g, toString(caseWeightDescription));
            $("#tb_md_mc_item_list").append(resultHtml);
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
                var price = table.find('tr[data-bisprice="true"]').find('td[data-item-id=' + item + ']').text();
                var specificPrice = price = parseFloat(price);
                table.find('tr[data-name="ratio"]').each(function () {
                    specificPrice = specificPrice * parseFloat($(this).find('td[data-item-id=' + item + ']').text());
                })
                specificPrice = iTofixed(specificPrice, 2);
                table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text(specificPrice);

                //修正差额 	修正差额不能大于30%，如果大于30%提示修改案例，修正差额=（比准价格-成交价）/成交价 的绝对值
                var correctionDifference = iTofixed(Math.abs(specificPrice - price) / price * 100, 2);
                var correctionDifferenceTd = table.find('tr[data-name="correctionDifference"]').find('td[data-item-id=' + item + ']');
                correctionDifferenceTd.removeClass('red').text(correctionDifference + "%");
                if (correctionDifference > 30) {
                    correctionDifferenceTd.text(correctionDifferenceTd.text() + " 请调整案例").addClass('red');
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
            }

            //案例差异=（当前案例比准价-所有案例比准价中最小值）/所有案例比准价中最小值
            $.each(caseItemIdArray, function (i, item) {
                currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                currSpecificPrice = parseFloat(currSpecificPrice);
                var caseDifference = iTofixed((currSpecificPrice - minSpecificPrice) / minSpecificPrice * 100, 2);
                var caseDifferenceTd = table.find('tr[data-name="caseDifference"]').find('td[data-item-id=' + item + ']');
                caseDifferenceTd.text(caseDifference + "%");
            })

            //计算平均价
            var totalPrice = 0;//总价
            $.each(caseItemIdArray, function (i, item) {
                currSpecificPrice = table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                currSpecificPrice = parseFloat(currSpecificPrice);
                totalPrice += currSpecificPrice;
            })
            var averagePrice = iTofixed(totalPrice / caseItemIdArray.length, 2);

            table.find('tr[data-name="averagePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text(averagePrice);
        }

        //切换
        marketCompare.toggle = function (that) {
            $("#tb_md_mc_item_list").find('tr[data-name="' + $(that).val() + '"]').each(function () {
                var td = $(this).show().closest('tbody').find('tr[data-name="field"]').find('td');
                if ($(that).prop('checked')) {
                    $(this).show();
                    td.attr('rowspan', parseInt(td.attr('rowspan')) + 1);
                } else {
                    $(this).hide();
                    td.attr('rowspan', parseInt(td.attr('rowspan')) - 1);
                }
            })
        }

        //保存
        marketCompare.save = function (callback) {
            //1.委估对象主要保存 结果价格
            //2.获取到各个案例的数据
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
            //数据验证

            var data = {
                id: $("#marketCompareId").val(),
                evaluationItem: {},
                caseItemList: []
            };

            var averagePrice = table.find('tr[data-name="averagePrice"]').find('td[data-item-id=' + evaluationItemId + ']').text();
            data.evaluationItem.averagePrice = averagePrice;
            $.each(caseItemIdArray, function (i, item) {
                var caseItem = {};
                caseItem.jsonContent = [];
                $.each(marketCompare.fields, function (j, field) {
                    var fieldContent = {};
                    fieldContent.name = field.name;
                    fieldContent.value = field.value;
                    fieldContent.score = 100;
                    fieldContent.ratio = 1;

                    var trs = table.find('tr[data-group="' + field.name + '"]');
                    if (trs.length > 0) {
                        trs.each(function () {
                            if ($(this).attr('data-name') == 'text') {
                                fieldContent.value = $(this).find('td[data-item-id=' + item + ']').find('a').text();
                            }
                            if ($(this).attr('data-name') == 'score') {
                                fieldContent.score = $(this).find('td[data-item-id=' + item + ']').find('a').text();
                            }
                            if ($(this).attr('data-name') == 'ratio') {
                                fieldContent.ratio = $(this).find('td[data-item-id=' + item + ']').find('a').text();
                            }
                        })
                    }
                    caseItem.jsonContent.push(fieldContent);
                })
                caseItem.specificPrice=table.find('tr[data-name="specificPrice"]').find('td[data-item-id=' + item + ']').text();
                caseItem.correctionDifference=table.find('tr[data-name="correctionDifference"]').find('td[data-item-id=' + item + ']').text();
                caseItem.caseDifference=table.find('tr[data-name="caseDifference"]').find('td[data-item-id=' + item + ']').text();
                caseItem.weight=table.find('tr[data-name="weight"]').find('td[data-item-id=' + item + ']').text();
                caseItem.weightDescription=table.find('tr[data-name="weightDescription"]').find('td[data-item-id=' + item + ']').text();
                data.caseItemList.push(caseItem);
            })

            $.ajax({
                url: '',
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
                        Alert('保存数据异常，' + result.msg);
                    }
                }
            })


        }

        window.marketCompare = marketCompare;
    })(jQuery)
</script>

<%--测算结果模板--%>
<script type="text/html" id="resultTemp">
    <tbody id="tbody_mc_result">
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
        <td data-item-id="{evaluationId}"></td>
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
    <tbody style="background-color: #fbfbfb">
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
