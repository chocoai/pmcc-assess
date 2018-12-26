<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-12-24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link" onclick="damagedDegree.loadDamagedDegreeList();">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房屋完损度</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <div class="" role="tabpanel" data-example-id="togglable-tabs">
            <ul id="damagedDegreeTab" class="nav nav-tabs bar_tabs" role="tablist">
            </ul>
            <div id="damagedDegreeTabContent" class="tab-content">
            </div>
        </div>
    </div>
</div>

<!--明细项-->
<div id="damagedDegreeDetailListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">明细数据列表</h3>
            </div>
            <div class="panel-body">
                <table class="table table-bordered" id="damagedDegreeDetailList">
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="damagedDegreeTabContentHtml">
    <div role="tabpanel" class="tab-pane fade" id="tab_content_{type}" aria-labelledby="profile-tab">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th width="10%">名称</th>
                <th width="5%">标准分</th>
                <th width="10%">实例状况</th>
                <th width="60%">状况内容</th>
                <th width="5%">得分</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</script>
<script type="text/html" id="damagedDegreeTabTrHtml">
    <tr class="group">
        <td>
            <input type="hidden" name="id" value="{id}">
            {categoryName}
        </td>
        <td>{standardScore}</td>
        <td>
            {entityConditionName}
        </td>
        <td>
            {entityConditionContent}
        </td>
        <td>
            {score}
        </td>
        <td>
            <div class="btn btn-xs btn-primary" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}')">明细内容
            </div>
        </td>
    </tr>
</script>
<script type="text/javascript">
    var damagedDegree = {};

    //加载完损度数据列表
    damagedDegree.loadDamagedDegreeList = function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/caseHouseDamagedDegree/getDamagedDegreeList',
            type: 'get',
            data: {
                houseId: '${empty caseHouse.id?0:caseHouse.id}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret && result.data) {
                    $("#damagedDegreeTab,#damagedDegreeTabContent").empty();
                    var tabContentHtml = '';
                    var groupArray = [];
                    $.each(result.data, function (i, item) { //循环数据分组
                        if ($.inArray(item.type, groupArray) < 0) {
                            groupArray.push(item.type);
                            var tabHtml = '<li role="presentation"><a href="#tab_content_' + item.type + '" role="tab" data-toggle="tab" aria-expanded="true">' + item.typeName + '</a></li>';
                            $("#damagedDegreeTab").append(tabHtml);
                        }
                    })

                    $.each(groupArray, function (i, group) {//循环分组
                        var contentHtml = $('#damagedDegreeTabContentHtml').html().replace(/{type}/g, group);
                        $("#damagedDegreeTabContent").append(contentHtml);
                        var tbodyContentHtml = '';
                        $.each(result.data, function (i, item) {
                            if (item.type == group) {
                                var trHtml = $("#damagedDegreeTabTrHtml").html();
                                trHtml = trHtml.replace(/{id}/g, item.id).replace(/{categoryName}/g, AssessCommon.toString(item.categoryName));
                                trHtml = trHtml.replace(/{standardScore}/g, AssessCommon.toString(item.standardScore));
                                trHtml = trHtml.replace(/{entityConditionName}/g, AssessCommon.toString(item.entityConditionName));
                                trHtml = trHtml.replace(/{entityConditionContent}/g, AssessCommon.toString(item.entityConditionContent));
                                trHtml = trHtml.replace(/{score}/g, AssessCommon.toString(item.score)).replace(/{isShow}/g, item.hasChildren ? '' : 'style="display: none"');
                                $("#damagedDegreeTabContent").find('.tab-pane:last').find('tbody').append(trHtml);
                            }
                        })
                    })
                    $('#damagedDegreeTab a').eq(0).tab('show');
                }
            }
        })
    };

    //显示modal
    damagedDegree.damagedDegreeDetailModalShow = function (damagedDegreeId) {
        damagedDegree.loadDamagedDegreeDetailList(damagedDegreeId);
        $("#damagedDegreeDetailListModal").modal();
    };

    //加载明细项数据列表
    damagedDegree.loadDamagedDegreeDetailList = function (damagedDegreeId) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型', width: '10%'});
        cols.push({field: 'standardScore', title: '标准分', width: '10%'});
        cols.push({field: 'entityConditionName', title: '实体状况', width: '10%'});
        cols.push({field: 'entityConditionContent', title: '状况内容', width: '40%'});
        cols.push({field: 'score', title: '得分', width: '10%'});
        $("#damagedDegreeDetailList").bootstrapTable('destroy');
        TableInit("damagedDegreeDetailList", getContextPath() + "/caseHouseDamagedDegree/getDamagedDegreeDetailList", cols, {
            damagedDegreeId: damagedDegreeId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
</script>
