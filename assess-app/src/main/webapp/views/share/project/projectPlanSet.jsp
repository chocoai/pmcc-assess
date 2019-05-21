<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-6-21
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tb" style="padding:5px;height:auto;display: none;">
    <div style=" margin-bottom:5px">
        <button type="button" onclick="addfirst()" class="btn btn-success btn-xs">
            <i class='fa fa-plus fa-white'></i> 新增第一级
        </button>
        <button type="button" onclick="move('up')" class="btn btn-primary btn-xs">
            <i class='fa fa-arrow-up fa-white'></i> 上移
        </button>
        <button type="button" onclick="move('down')" class="btn btn-primary btn-xs">
            <i class='fa fa-arrow-down fa-white'></i> 下移
        </button>
        <button type="button" onclick="keySet()" class="btn btn-warning btn-xs">
            <i class='fa fa-fire fa-white'></i> 快速设置
        </button>
    </div>
</div>

<div id="div_fastSet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">快速设置</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <form id="frm_fastset" class="form-horizontal">
                                <table class="table table-striped table-bordered table-hover table-bordered" id="sample-table-2">
                                    <thead>
                                    <tr>
                                        <th class="hidden-xs">设置内容</th>
                                        <th class="hidden-xs">值</th>
                                        <th class="hidden-xs">范围</th>
                                        <th class="hidden-xs">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">开始时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" title="开始时间" value="planStartDate">
                                            <input type="text" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">结束时间</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="planEndDate">
                                            <input type="text" data-date-format='yyyy-mm-dd' class="fast_value form-control dbdate"></td>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任人</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeUserAccount">
                                            <input type="hidden" id="fast_executeUserAccount" class="fast_value">
                                            <input type="text" id="fast_executeUserName" class="form-control" readonly="readonly"
                                                   onclick="selFastEmployee()">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_user" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">责任部门</td>
                                        <td class="hidden-xs">
                                            <input type="hidden" class="fast_fileds" value="executeDepartmentId">
                                            <input type="hidden" id="fast_executeDepartmentId" class="fast_value">
                                            <input type="text" id="fast_executeDepartmentName" class="form-control" onclick="selFastDept()"
                                                   readonly="readonly">
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" id="btn_dept" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">
                                        <td class="hidden-xs">计划工时</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="planHours">
                                                <input type="text" data-rule-number='true' maxlength="5" class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">

                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    <tr class="fast_tr">

                                        <td class="hidden-xs">权重占比</td>
                                        <td class="hidden-xs">
                                            <div class="x-valid">
                                                <input type="hidden" class="fast_fileds" value="proportion">
                                                <input type="text" data-rule-number='true' maxlength="5" class="form-control fast_value">
                                            </div>
                                        <td class="hidden-xs">
                                            <select class="form-control fast_range">
                                                <c:forEach var="item" items="${fastSet}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick="clearFastValue(this)">清除</button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveFastset()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

