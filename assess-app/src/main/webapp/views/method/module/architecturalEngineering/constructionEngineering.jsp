<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <form id="constructionEngineeringForm">
        <div class="col-sm-12">
            <table id="constructionEngineeringID" class="table tree">
                <thead>
                <tr>
                    <th>工程名称</th>
                    <th>单方造价(元/㎡)</th>
                    <th>估价时点完工程度</th>
                    <th>估价时点单价(元/㎡)</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                <tr>
                    <th>合计</th>
                    <th></th>
                    <th></th>
                    <th class="total"></th>
                </tr>
                </tfoot>
            </table>
        </div>
    </form>
</div>
<script type="text/javascript">
    var constructionEngineering = {};
    constructionEngineering.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    constructionEngineering.treeViewHtml = '';
    constructionEngineering.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = '';
                    $.each(result.data.nodes, function (i, item) {
                        constructionEngineering.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + '">';
                        constructionEngineering.treeViewHtml += '<td>' + item.text + '</td>';
                        if (item.nodes) {
                            constructionEngineering.treeViewHtml += '<td></td><td></td><td></td>';
                        } else {
                            constructionEngineering.treeViewHtml += '<td><input type="text" name="currency' + item.id + '" data-rule-number="true" onblur="constructionEngineering.sumTotal()"></td>';
                            constructionEngineering.treeViewHtml += '<td><input type="text" name="valuationDateDegreeCompletion' + item.id + '" class="x-percent" onblur="constructionEngineering.sumTotal()"></td>';
                            constructionEngineering.treeViewHtml += '<td></td>';
                        }
                        constructionEngineering.treeViewHtml += '</tr>';
                        if (item.nodes) {
                            constructionEngineering.recursionTreeView(item.nodes);
                        }
                    });
                    $("#constructionEngineeringID").find('tbody').empty().append(constructionEngineering.treeViewHtml);
                    $("#constructionEngineeringID").treegrid();
                    $("#constructionEngineeringForm").validate();
                    var mdCostAndDevelopmentOtherConstructionJSON = "${mdCostAndDevelopmentOtherConstruction}";
                    if (constructionEngineering.isNotBlank(mdCostAndDevelopmentOtherConstructionJSON)) {
                        mdCostAndDevelopmentOtherConstructionJSON = $("#mdCostAndDevelopmentOtherConstructionJSON").val();
                        mdCostAndDevelopmentOtherConstructionJSON = JSON.parse(mdCostAndDevelopmentOtherConstructionJSON);
                        constructionEngineering.setTreeValue(mdCostAndDevelopmentOtherConstructionJSON);
                    }
                }
            }
        })
    };

    //递归设置treeview的html
    constructionEngineering.recursionTreeView = function (nodes) {
        if (nodes && nodes.length > 0) {
            $.each(nodes, function (i, item) {
                constructionEngineering.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + ' treegrid-parent-' + item.level + '">';
                constructionEngineering.treeViewHtml += '<td>' + item.text + '</td>';
                if (item.nodes) {
                    constructionEngineering.treeViewHtml += '<td></td><td></td><td></td>';
                } else {
                    constructionEngineering.treeViewHtml += '<td><input type="text" name="currency' + item.id + '" data-rule-number="true" onblur="constructionEngineering.sumTotal()"></td>';
                    constructionEngineering.treeViewHtml += '<td><input type="text" name="valuationDateDegreeCompletion' + item.id + '" class="x-percent" onblur="constructionEngineering.sumTotal()"></td>';
                    constructionEngineering.treeViewHtml += '<td></td>';
                }
                constructionEngineering.treeViewHtml += '</tr>';
                if (item.nodes) {
                    constructionEngineering.recursionTreeView(item.nodes);
                }
            })
        }
    };

    //获取合计值
    constructionEngineering.getTotal = function () {
        return $("#constructionEngineeringID").find('.total').text();
    };

    //计算合计值
    constructionEngineering.sumTotal = function () {
        var total = 0;
        $("#constructionEngineeringID").find('tbody tr').each(function () {
            var currency = $(this).find('td:eq(1)').find(':text').val();
            var completionDegree = $(this).find('td:eq(2)').find(':text').attr('data-value');
            if (AssessCommon.isNumber(currency) && AssessCommon.isNumber(completionDegree)) {
                currency = parseFloat(currency);
                var price = currency * parseFloat(completionDegree);//估价时点单价
                $(this).find('td:eq(3)').text(price.toFixed(2));
                total += price;
            }
        });
        $("#constructionEngineeringID").find('.total').text(total.toFixed(2));
    };

    /**
     * 获取树结构
     * @returns {Array}
     */
    constructionEngineering.getJsonValue = function () {
        var arr = [];
        $("#constructionEngineeringID").find('tbody tr').each(function () {
            var currency = $(this).find('td:eq(1)').find(':text').val();
            var completionDegree = $(this).find('td:eq(2)').find(':text').attr('data-value');
            arr.push({
                currency: {
                    name: $(this).find('td:eq(1)').find(':text').attr("name"),
                    value: $(this).find('td:eq(1)').find(':text').val()
                },
                completionDegree: {
                    name: $(this).find('td:eq(2)').find(':text').attr("name"),
                    value: $(this).find('td:eq(2)').find(':text').val()
                }
            });
        });
        return arr;
    };

    constructionEngineering.screen = function (data, item) {
        if (this.isNotBlank(item)) {
            $.each(data, function (i, n) {
                if ($(item).find('td:eq(2)').find(':text').attr("name") == n.completionDegree.name) {
                    var a = 0, b = 0, c = 0;
                    if (constructionEngineering.isNotBlank(n.completionDegree.value)) {
                        b = parseFloat(n.completionDegree.value);
                    } else {
                        b = 0;
                    }
                    if (constructionEngineering.isNotBlank(n.currency.value)) {
                        a = Number(n.currency.value);
                    } else {
                        a = 0;
                    }
                    c = (Number(a) * Number(b)) / 100;
                    $(item).find('td:eq(1)').find(':text').val(n.currency.value);
                    $(item).find('td:eq(2)').find(':text').val(n.completionDegree.value);
                    if (AssessCommon.isNumber(n.completionDegree.value)) {
                        $(item).find('td:eq(2)').find(':input').attr('data-value', Number(n.completionDegree.value) / 100);
                    }
                    if (AssessCommon.isNumber(c)) {
                        $(item).find('td:eq(3)').html(c);
                    }
                }
            });
        }
    };

    /**
     * 设置树的值
     * @param data
     */
    constructionEngineering.setTreeValue = function (data) {
        if (this.isNotBlank(data)) {
            $("#constructionEngineeringID").find('tbody tr').each(function () {
                constructionEngineering.screen(data, $(this)[0]);
            });
            constructionEngineering.sumTotal();
        }
    };


</script>