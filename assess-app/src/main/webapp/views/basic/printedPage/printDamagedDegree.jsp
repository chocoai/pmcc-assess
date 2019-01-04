<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-12-24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <table class="table table-bordered" id="tbDamageDegreeList">
        <thead>
        <tr>
            <th colspan="3" style="font-size: 14px;">房屋完损度</th>
        </tr>
        <tr>
            <td width="10%">名称</td>
            <td width="10%">实例状况</td>
            <td width="60%">状况内容</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<script type="text/html" id="damagedDegreeTabTrHtml">
    <tr class="group">
        <td>
            {categoryName}
        </td>
        <td>
            {entityConditionName}
        </td>
        <td>
            {entityConditionContent}
        </td>
    </tr>
</script>
<script type="text/javascript">
    $(function () {
        damagedDegree.loadDamagedDegreeList();
    })
    var damagedDegree = {};

    //加载完损度数据列表
    damagedDegree.loadDamagedDegreeList = function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicHouseDamagedDegree/getDamagedDegreeList',
            type: 'get',
            data: {
                houseId: '${empty basicHouse.id?0:basicHouse.id}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret && result.data) {
                    var groupArray = [];
                    var groupNameArray = [];
                    $.each(result.data, function (i, item) { //循环数据分组
                        if ($.inArray(item.type, groupArray) < 0) {
                            groupArray.push(item.type);
                            groupNameArray.push(item.typeName);
                        }
                    })
                    $.each(groupArray, function (i, group) {//循环分组
                        $("#tbDamageDegreeList").find('tbody').append('<tr><td colspan="3"><h5>'+groupNameArray[i]+'</h5></td></tr>');
                        $.each(result.data, function (i, item) {
                            if (item.type == group) {
                                var trHtml = $("#damagedDegreeTabTrHtml").html();
                                trHtml = trHtml.replace(/{id}/g, item.id).replace(/{categoryName}/g, AssessCommon.toString(item.categoryName));
                                trHtml = trHtml.replace(/{entityConditionName}/g, AssessCommon.toString(item.entityConditionName));
                                trHtml = trHtml.replace(/{entityConditionContent}/g, AssessCommon.toString(item.entityConditionContent));
                                trHtml = trHtml.replace(/{isShow}/g, item.hasChildren ? '' : 'style="display: none"');
                                $("#tbDamageDegreeList").find('tbody').append(trHtml);
                            }
                        })
                    })
                }
            }
        })
    };
</script>
