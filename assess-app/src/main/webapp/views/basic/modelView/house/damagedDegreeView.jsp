<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-12-24
  Time: 15:35
  To change this template use File | Settings | File Templates.
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
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div>
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="type"
                                                    onchange="damagedDegree.autoFillEntityConditionContent();">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            实体状况
                                        </label>
                                        <div class="col-sm-10">
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
                                        <label class="col-sm-2 control-label">
                                            状况内容
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="状况内容" name="entityConditionContent"
                                                      class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            得分
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="score" class="form-control" placeholder="得分">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="damagedDegree.saveDamagedDegreeDetail()">
                        保存
                    </button>
                </div>
            </form>
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
            <select class="form-control" name="entityCondition" onchange="damagedDegree.entityConditionChange(this);"
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
            <textarea class="form-control" name="entityConditionContent"></textarea>
        </td>
        <td>
            <input type="text" name="score" class="form-control">
        </td>
        <td>
            <div class="btn btn-xs btn-primary" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}','{category}')">明细内容
            </div>
        </td>
    </tr>
</script>
