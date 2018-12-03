
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
    underEngineeringDevelopment.treeViewHtml = '';
    underEngineeringDevelopment.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = ''
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
                }
            }
        })
    }

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
    }

    //获取合计值
    underEngineeringDevelopment.getTotal = function () {
        return $("#underEngineeringDevelopmentID").find('.total').text();
    }

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
        })
        $("#underEngineeringDevelopmentID").find('.total').text(total.toFixed(2));
    }

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

</script>