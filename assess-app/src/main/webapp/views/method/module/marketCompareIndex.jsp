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

                <tbody id="tbody_mc_result">
                <tr >
                    <td colspan="4"><span style="font-weight: bold;font-size: 16px;">测算结果</span></td>
                </tr>
                <tr>
                    <td>楼盘名称</td>
                    <td>香瑞福</td>
                    <td>彩叠园</td>
                    <td>合能琥珀</td>
                </tr>
                </tbody>
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

        //设置table可编辑
        function setTableEditAble() {

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
            //头部html
            var headHtml = '<thead> <tr>';
            headHtml += '<th width="10%">项目</th>';
            headHtml += '<th width="10%">委估对象</th>';
            for (var i = 1; i <= defaluts.cases.length; i++) {
                headHtml += '<th width="10%">案例' + i + '</th>';
            }
            headHtml += '</tr> </thead>';
            $(defaluts.element).append(headHtml);

            //字段内容html
            var bodyHtml = ' <tbody>';
            $.each(defaluts.fields, function (i, item) {
                var evaluationItem = getItemByName(JSON.parse(defaluts.evaluation.jsonContent), item.name);
                if (item.bisOnlyView) {//只用于显示的字段
                    var trHtml = ' <tr>';
                    trHtml += ' <td>' + item.value + '</td>';
                    if (evaluationItem) {
                        trHtml += ' <td>' + evaluationItem.value + '</td>';
                    }
                    for (var j = 0; j < defaluts.cases.length; j++) {
                        var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.name);
                        if (caseItem) {
                            trHtml += ' <td>' + caseItem.value + '</td>';
                        }
                    }
                    trHtml += ' </tr>';
                    bodyHtml += trHtml;
                } else {//可能需要填写数据的字段
                    var rowHtml = getTempHtml("pRowTemp", defaluts.readonly);
                    rowHtml = rowHtml.replace(/{fieldName}/g, item.name).replace(/{fieldValue}/g, item.value);
                    rowHtml = rowHtml.replace(/{evaluationText}/g, evaluationItem.value).replace(/{evaluationScore}/g, evaluationItem.score);
                    rowHtml = rowHtml.replace(/{evaluationRatio}/g, evaluationItem.ratio);
                    //取到案例相关
                    var caseText, caseScore, caseRatio;
                    for (var j = 0; j < defaluts.cases.length; j++) {
                        var caseItem = getItemByName(JSON.parse(defaluts.cases[j].jsonContent), item.name);
                        if (caseItem) {
                            var pTextHtml = getTempHtml("pTextTemp", defaluts.readonly);
                            var pScoreHtml = getTempHtml("pScoreTemp", defaluts.readonly);
                            caseText += pTextHtml.replace(/{fieldValue}/g, item.value).replace(/{value}/g, caseItem.value);
                            caseScore += pScoreHtml.replace(/{fieldValue}/g, item.value).replace(/{score}/g, caseItem.score);
                            caseRatio += ' <td>' + caseItem.ratio + '</td>';
                        }
                    }
                    rowHtml = rowHtml.replace(/{caseText}/g, caseText).replace(/{caseScore}/g, caseScore).replace(/{caseRatio}/g, caseRatio);
                    bodyHtml += rowHtml;
                }
            })
            bodyHtml += ' </tbody>';
            $(defaluts.element).append(bodyHtml);

            //设置表格可编辑
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
                    console.log($(this));
                }
            });
        }

        window.marketCompare = marketCompare;
    })(jQuery)
</script>

<%--文本模板--%>
<script type="text/html" id="pTextTemp">
    <td class="p_text">
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
    <td class="p_score">
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
    <tr data-group="{fieldName}">
        <td rowspan="4" style="vertical-align: middle">{fieldValue}</td>
    </tr>
    <tr data-group="{fieldName}" data-name="text">
        <td class="p_text">
            <a href="javascript://" data-type="textarea"
               data-original-title="{fieldValue}"
               class="editable editable-click editable-pre-wrapped">{evaluationText}</a>
        </td>
        {caseText}
    </tr>
    <tr data-group="{fieldName}" data-name="sroce">
        <td>{evaluationScore}</td>
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



