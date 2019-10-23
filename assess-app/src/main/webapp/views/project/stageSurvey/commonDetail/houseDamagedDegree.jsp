

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
                <th width="10%">实例状况</th>
                <th width="60%">状况内容</th>
                <th width="10%">标准得分</th>
                <th width="10%">分数</th>
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
            <div class="btn btn-xs btn-primary pull-right" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}')">明细项
            </div>
        </td>
        <td>
            {entityConditionName}
        </td>
        <td>
            {entityConditionContent}
        </td>
        <td>
            {standardScore}
        </td>
        <td>
            {score}
        </td>
    </tr>
</script>

