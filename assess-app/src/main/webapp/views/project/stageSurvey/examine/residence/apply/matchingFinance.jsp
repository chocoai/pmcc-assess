<%--
  金融服务
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>金融服务信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="col-sm-3">
                <button type="button" class="btn btn-success" onclick="matchingFinance.prototype.showModel()"
                        data-toggle="modal" href="#divBox"> 新增
                </button>
                <button type="button" class="btn btn-warning" onclick="matchingFinance.prototype.clear()"> 删除
                </button>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    金融类别
                </label>
                <div class="col-sm-2">
                    <select name="category" class="form-control  category">
                    </select>
                </div>
            </div>
            <div class="col-sm-3">
                <input type="text" placeholder="距离(单位/m)" class="form-control" name="distance" value="3000">
            </div>
            <div class="col-sm-1">
                <div type="button" class="btn btn-default docs-tooltip" onclick="assessMatchingFinance.select(this)">
                    <i class="fa fa-search"></i>搜索
                </div>
            </div>
            <table class="table table-bordered" id="MatchingFinanceList">
            </table>
        </form>
    </div>
</div>

<div id="divBoxMatchingFinance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">金融服务</h3>
            </div>
            <form id="frmMatchingFinance" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            与金融机构的距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="category" class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融机构性质
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="nature" class="form-control search-select select2 nature">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="serviceContent"
                                                    class="form-control search-select select2 serviceContent">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="autoServiceContent"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="matchingFinance.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>