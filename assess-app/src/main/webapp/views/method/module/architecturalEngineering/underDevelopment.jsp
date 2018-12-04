
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <form id="underEngineeringDevelopmentForm">
        <div class="col-sm-12">
            <table id="underEngineeringDevelopmentID" class="table tree">
                <thead>
                <tr>
                    <th>工程名称</th>
                    <th>单方造价(元/㎡)</th>
                    <th>估价时点完工程度</th>
                    <th>估价时点单价(元/㎡)</th>
                    <th>续建投入单价(元/㎡)</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                <tr>
                    <th>合计</th>
                    <th></th>
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
    var underEngineeringDevelopment = {};
    underEngineeringDevelopment.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    underEngineeringDevelopment.treeViewHtml = '';
    underEngineeringDevelopment.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = '' ;
                    $.each(result.data.nodes, function (i, item) {
                        underEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + '">';
                        underEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td>';
                        if (item.nodes) {
                            underEngineeringDevelopment.treeViewHtml += '<td></td><td></td><td></td><td></td>';
                        } else {
                            underEngineeringDevelopment.treeViewHtml += '<td><input type="text" name="currency' + item.id + '" data-rule-number="true" onblur="underEngineeringDevelopment.sumTotal()"></td>';
                            underEngineeringDevelopment.treeViewHtml += '<td><input type="text" name="valuationDateDegreeCompletion' + item.id + '" class="x-percent" onblur="underEngineeringDevelopment.sumTotal()"></td>';
                            underEngineeringDevelopment.treeViewHtml += '<td></td><td></td>';
                        }
                        underEngineeringDevelopment.treeViewHtml += '</tr>';
                        if (item.nodes) {
                            underEngineeringDevelopment.recursionTreeView(item.nodes);
                        }
                    });
                    $("#underEngineeringDevelopmentID").find('tbody').empty().append(underEngineeringDevelopment.treeViewHtml);
                    $("#underEngineeringDevelopmentID").treegrid();
                    $("#underEngineeringDevelopmentForm").validate();
                    //设置从表数据
                    var mdCostAndDevelopmentOtherHypothesisJSON = "${mdCostAndDevelopmentOtherHypothesis}";
                    if (underEngineeringDevelopment.isNotBlank(mdCostAndDevelopmentOtherHypothesisJSON)) {
                        try {
                            mdCostAndDevelopmentOtherHypothesisJSON = $("#mdCostAndDevelopmentOtherHypothesisJSON").val();
                            mdCostAndDevelopmentOtherHypothesisJSON = JSON.parse(mdCostAndDevelopmentOtherHypothesisJSON);
                            underEngineeringDevelopment.setTreeValue(mdCostAndDevelopmentOtherHypothesisJSON);
                        } catch (e) {
                            console.log("设置从表数据 失败!");
                        }
                    }
                }
            }
        })
    };

    //递归设置treeview的html
    underEngineeringDevelopment.recursionTreeView = function (nodes) {
        if (nodes && nodes.length > 0) {
            $.each(nodes, function (i, item) {
                underEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + ' treegrid-parent-' + item.level + '">';
                underEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td>';
                if (item.nodes) {
                    underEngineeringDevelopment.treeViewHtml += '<td></td><td></td><td></td><td></td>';
                } else {
                    underEngineeringDevelopment.treeViewHtml += '<td><input type="text" name="currency' + item.id + '" data-rule-number="true" onblur="underEngineeringDevelopment.sumTotal()"></td>';
                    underEngineeringDevelopment.treeViewHtml += '<td><input type="text" name="valuationDateDegreeCompletion' + item.id + '" class="x-percent" onblur="underEngineeringDevelopment.sumTotal()"></td>';
                    underEngineeringDevelopment.treeViewHtml += '<td></td><td></td>';
                }
                underEngineeringDevelopment.treeViewHtml += '</tr>';
                if (item.nodes) {
                    underEngineeringDevelopment.recursionTreeView(item.nodes);
                }
            })
        }
    };

    //获取合计值
    underEngineeringDevelopment.getTotal = function () {
        return $("#underEngineeringDevelopmentID").find('.total').text();
    };

    //计算合计值
    underEngineeringDevelopment.sumTotal = function () {
        var total = 0;
        $("#underEngineeringDevelopmentID").find('tbody tr').each(function () {
            var currency = $(this).find('td:eq(1)').find(':text').val();
            var completionDegree = $(this).find('td:eq(2)').find(':text').attr('data-value');
            if (AssessCommon.isNumber(currency) && AssessCommon.isNumber(completionDegree)) {
                currency = parseFloat(currency);
                var price = currency * parseFloat(completionDegree);//估价时点单价
                $(this).find('td:eq(3)').text(price.toFixed(2));
                var renewalPrice = currency - price;
                total += renewalPrice;
                $(this).find('td:eq(4)').text(renewalPrice.toFixed(2));
            }
        });
        $("#underEngineeringDevelopmentID").find('.total').text(total.toFixed(2));
    };

    //获取要保存的json数据
    underEngineeringDevelopment.getJsonValue = function () {
        var arr = [];
        $("#underEngineeringDevelopmentID").find('tbody tr').each(function () {
            var currency = $(this).find('td:eq(1)').find(':text').val();
            var completionDegree = $(this).find('td:eq(2)').find(':text').attr('data-value');
            arr.push({
                currency:{
                    name:$(this).find('td:eq(1)').find(':text').attr("name"),
                    value:$(this).find('td:eq(1)').find(':text').val()
                },
                completionDegree:{
                    name:$(this).find('td:eq(2)').find(':text').attr("name"),
                    value:$(this).find('td:eq(2)').find(':text').val()
                }
            });
        });
        return arr;
    };

    underEngineeringDevelopment.screen = function (data, item) {
        if (this.isNotBlank(item)) {
            $.each(data, function (i, n) {
                if ($(item).find('td:eq(2)').find(':text').attr("name") == n.completionDegree.name) {
                    var a = 0, b = 0, c = 0,d = 0;
                    if (underEngineeringDevelopment.isNotBlank(n.completionDegree.value)) {
                        b = parseFloat(n.completionDegree.value);
                    } else {
                        b = 0;
                    }
                    if (underEngineeringDevelopment.isNotBlank(n.currency.value)) {
                        a = Number(n.currency.value);
                    } else {
                        a = 0;
                    }
                    c = (Number(a) * Number(b)) / 100;
                    d = a - c;
                    $(item).find('td:eq(1)').find(':text').val(n.currency.value);
                    $(item).find('td:eq(2)').find(':text').val(n.completionDegree.value);
                    if (AssessCommon.isNumber(n.completionDegree.value)) {
                        $(item).find('td:eq(2)').find(':input').attr('data-value', Number(n.completionDegree.value) / 100);
                    }
                    if (AssessCommon.isNumber(c)) {
                        $(item).find('td:eq(3)').html(c);
                    }
                    if (AssessCommon.isNumber(d)) {
                        $(item).find('td:eq(4)').html(d);
                    }
                }
            });
        }
    };

    /**
     * 设置树的值
     * @param data
     */
    underEngineeringDevelopment.setTreeValue = function (data) {
        if (this.isNotBlank(data)) {
            $("#underEngineeringDevelopmentID").find('tbody tr').each(function () {
                underEngineeringDevelopment.screen(data, $(this)[0]);
            });
            underEngineeringDevelopment.sumTotal();
        }
    };

</script>