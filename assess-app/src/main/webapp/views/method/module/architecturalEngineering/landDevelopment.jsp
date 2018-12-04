
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <form id="landEngineeringDevelopmentForm">
        <div class="col-sm-12">
            <table id="landEngineeringDevelopmentID" class="table tree">
                <thead>
                <tr>
                    <th>工程名称</th>
                    <th>单方造价(元/㎡)</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
                <tfoot>
                <tr>
                    <th>合计</th>
                    <th class="total"></th>
                </tr>
                </tfoot>
            </table>
        </div>
    </form>
</div>
<script type="text/javascript">
    var landEngineeringDevelopment = {};
    landEngineeringDevelopment.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    landEngineeringDevelopment.treeViewHtml = '';
    landEngineeringDevelopment.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = ''
                    $.each(result.data.nodes, function (i, item) {
                        landEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + '">';
                        landEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td><td>';
                        if (!item.nodes) {
                            landEngineeringDevelopment.treeViewHtml += '<input type="text" name="price' + item.id + '" data-rule-number="true" onblur="landEngineeringDevelopment.sumTotal()">';
                        }
                        landEngineeringDevelopment.treeViewHtml += '</td></tr>';
                        if (item.nodes) {
                            landEngineeringDevelopment.recursionTreeView(item.nodes);
                        }
                    });
                    $("#landEngineeringDevelopmentID").find('tbody').empty().append(landEngineeringDevelopment.treeViewHtml);
                    $("#landEngineeringDevelopmentID").treegrid();
                    $("#landEngineeringDevelopmentForm").validate();
                    var mdCostAndDevelopmentOtherArchitecturalJSON = "${mdCostAndDevelopmentOtherArchitectural}";
                    if (landEngineeringDevelopment.isNotBlank(mdCostAndDevelopmentOtherArchitecturalJSON)) {
                        try {
                            mdCostAndDevelopmentOtherArchitecturalJSON = $("#mdCostAndDevelopmentOtherArchitecturalJSON").val();
                            mdCostAndDevelopmentOtherArchitecturalJSON = JSON.parse(mdCostAndDevelopmentOtherArchitecturalJSON);
                            landEngineeringDevelopment.setTreeValue(mdCostAndDevelopmentOtherArchitecturalJSON);
                        } catch (e) {
                            console.log("设置从表数据 失败!");
                        }
                    }
                }
            }
        })
    };

    //递归设置treeview的html
    landEngineeringDevelopment.recursionTreeView = function (nodes) {
        if (nodes && nodes.length > 0) {
            $.each(nodes, function (i, item) {
                landEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + ' treegrid-parent-' + item.level + '">';
                landEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td>';
                landEngineeringDevelopment.treeViewHtml += '<td><input type="text" name="price' + item.id + '" data-rule-number="true" onblur="landEngineeringDevelopment.sumTotal()"></td>';
                landEngineeringDevelopment.treeViewHtml += '</tr>';
                if (item.nodes) {
                    landEngineeringDevelopment.recursionTreeView(item.nodes);
                }
            })
        }
    };

    //获取合计值
    landEngineeringDevelopment.getTotal = function () {
        return $("#landEngineeringDevelopmentID").find('.total').text();
    };

    //计算合计值
    landEngineeringDevelopment.sumTotal = function () {
        var total = 0;
        $("#landEngineeringDevelopmentID").find(':text').each(function () {
            if (AssessCommon.isNumber($(this).val())) {
                total += parseFloat($(this).val());
            }
        });
        $("#landEngineeringDevelopmentID").find('.total').text(total.toFixed(2));
    };

    /**
     * 设置树的值
     * @param data
     */
    landEngineeringDevelopment.setTreeValue = function (data) {
        if (this.isNotBlank(data)) {
            $("#landEngineeringDevelopmentID").find(':text').each(function () {
                var item = $(this);
                $.each(data, function (i, n) {
                    if (n.name == item.attr("name")) {
                        item.val(n.value);
                    }
                });
            });
        }
    };

    landEngineeringDevelopment.getJsonValue = function () {
        var arr = [];
        $("#landEngineeringDevelopmentID").find(':text').each(function () {
            arr.push({name:$(this).attr("name"),value:$(this).val()});
        });
        return arr;
    };

</script>