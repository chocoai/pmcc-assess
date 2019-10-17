<%--
  房屋完损度
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
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
                <div type="button" class="btn btn-success" onclick="damagedDegree.addDamagedDegreeDetail()"> 新增
                </div>
                <input type="hidden" id="damagedDegreeId">
                <table class="table table-bordered" id="damagedDegreeDetailList">
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 模态框中的 明细数据 -->
<div id="damagedDegreeDetailModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">明细数据</h3>
            </div>
            <form id="damagedDegreeDetailForm" class="form-horizontal">
                <input type="hidden" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div>
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select class="form-control" name="type"
                                                    onchange="damagedDegree.autoFillEntityConditionContent();">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            实体状况
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select class="form-control" name="entityCondition"
                                                    onchange="damagedDegree.autoFillEntityConditionContent();">
                                                <option value="">-请选择-</option>
                                                <option value="intact">完好</option>
                                                <option value="basicallyIntact">基本完好</option>
                                                <option value="generalDamage">一般损坏</option>
                                                <option value="seriousDamage">严重损坏</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            状况内容
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <span data-name="entityConditionContent" style="color: red;"></span>
                                            <textarea placeholder="状况内容" name="entityConditionContent"
                                                      class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="damagedDegree.saveDamagedDegreeDetail()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 选项卡 内容之一 -->
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
<!-- 结构部分下面4列 的 支撑html -->
<script type="text/html" id="damagedDegreeTabTrHtml">
    <tr class="group">
        <td>
            <input type="hidden" name="id" value="{id}">
            <input type="hidden" name="category" value="{category}">
            {categoryName}
            <div class="btn btn-xs btn-primary pull-right" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}','{category}')">明细内容
            </div>
        </td>
        <td>
            <select class="form-control" data-role="required" required="required" name="entityCondition" onchange="damagedDegree.entityConditionChange(this);"
                    data-intact="{intact}" data-basicallyIntact="{basicallyIntact}"
                    data-generalDamage="{generalDamage}" data-seriousDamage="{seriousDamage}">
                <option value="">-请选择-</option>
                <option value="intact">完好</option>
                <option value="basicallyIntact">基本完好</option>
                <option value="generalDamage">一般损坏</option>
                <option value="seriousDamage">严重损坏</option>
            </select>
        </td>
        <td>
            <span data-name="entityConditionContent" style="color: red;"></span>
            <textarea class="form-control" name="entityConditionContent"></textarea>
        </td>
        <td>
            {standardScore}
        </td>
        <td>
            <input type="text"  class="form-control" name="score" value="{score}">
        </td>
    </tr>
</script>
