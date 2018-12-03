
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <form id="buildingEngineeringForm">
        <div class="col-sm-12">
            <table id="buildingEngineeringID" class="table tree">
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
    var buildingEngineering = {};
    buildingEngineering.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    buildingEngineering.treeViewHtml = '';
    buildingEngineering.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = '';
                    $.each(result.data.nodes, function (i, item) {
                        buildingEngineering.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + '">';
                        buildingEngineering.treeViewHtml += '<td>' + item.text + '</td><td>';
                        if (!item.nodes) {
                            buildingEngineering.treeViewHtml += '<input type="text" name="price' + item.id + '" data-rule-number="true" onblur="buildingEngineering.sumTotal()">';
                        }
                        buildingEngineering.treeViewHtml += '</td></tr>';
                        if (item.nodes) {
                            buildingEngineering.recursionTreeView(item.nodes);
                        }
                    });
                    $("#buildingEngineeringID").find('tbody').empty().append(buildingEngineering.treeViewHtml);
                    $("#buildingEngineeringID").treegrid();
                    $("#buildingEngineeringForm").validate();
                    var mdCostAndDevelopmentOtherBuildingJSON = "${mdCostAndDevelopmentOtherBuilding}";
                    if (buildingEngineering.isNotBlank(mdCostAndDevelopmentOtherBuildingJSON)) {
                        mdCostAndDevelopmentOtherBuildingJSON = $("#mdCostAndDevelopmentOtherBuildingJSON").val();
                        mdCostAndDevelopmentOtherBuildingJSON = JSON.parse(mdCostAndDevelopmentOtherBuildingJSON);
                        buildingEngineering.setTreeValue(mdCostAndDevelopmentOtherBuildingJSON);
                    }
                }
            }
        });
    };

    //递归设置treeview的html
    buildingEngineering.recursionTreeView = function (nodes) {
        if (nodes && nodes.length > 0) {
            $.each(nodes, function (i, item) {
                buildingEngineering.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + ' treegrid-parent-' + item.level + '">';
                buildingEngineering.treeViewHtml += '<td>' + item.text + '</td>';
                buildingEngineering.treeViewHtml += '<td><input type="text" name="price' + item.id + '" data-rule-number="true" onblur="buildingEngineering.sumTotal()"></td>';
                buildingEngineering.treeViewHtml += '</tr>';
                if (item.nodes) {
                    buildingEngineering.recursionTreeView(item.nodes);
                }
            })
        }
    };

    //获取合计值
    buildingEngineering.getTotal = function () {
        return $("#buildingEngineeringID").find('.total').text();
    };

    //计算合计值
    buildingEngineering.sumTotal = function () {
        var total = 0;
        $("#buildingEngineeringID").find(':text').each(function () {
            if (AssessCommon.isNumber($(this).val())) {
                total += parseFloat($(this).val());
            }
        });
        $("#buildingEngineeringID").find('.total').text(total.toFixed(2));
    };

    /**
     * 设置树的值
     * @param data
     */
    buildingEngineering.setTreeValue = function (data) {
        if (this.isNotBlank(data)) {
            $("#buildingEngineeringID").find(':text').each(function () {
                var item = $(this);
                $.each(data, function (i, n) {
                    if (n.name == item.attr("name")) {
                        item.val(n.value);
                    }
                });
            });
        }
    };

    /**
     * 获取树结构
     * @returns {Array}
     */
    buildingEngineering.getJsonValue = function () {
        var arr = [];
        $("#buildingEngineeringID").find(':text').each(function () {
            arr.push({name: $(this).attr("name"), value: $(this).val()});
        });
        return arr;
    };


</script>