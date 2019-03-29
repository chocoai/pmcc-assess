<%--
  主干道
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主干道信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMainRoad.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MainRoadList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div id="divBoxMainRoad" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">主干道</h3>
            </div>
            <form id="frmMainRoad" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            收费标准
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="costStandard"
                                                   placeholder="收费标准">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否限行<span class="symbol required"></span>
                                        </label>
                                    </div>
                                    <div class="col-sm-10">
                                        <span class="checkbox-inline">
                                            <input type="radio" onchange="matchingMainRoad.prototype.showLimit(this)" id="limitBasicMatchingTraffic2" name="flag" value="true">
                                            <label for="limitBasicMatchingTraffic2">是</label>
                                        </span>
                                        <span class="checkbox-inline">
                                            <input type="radio" onchange="matchingMainRoad.prototype.showLimit(this)" checked="checked" id="limitBasicMatchingTraffic1" name="flag" value="false">
                                            <label for="limitBasicMatchingTraffic1">否</label>
                                        </span>
                                    </div>
                                </div>
                                <div style="display: none">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">
                                                限行速度
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="limitSpeed"
                                                       placeholder="限行速度">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">
                                                限行时间
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="limitTime"
                                                       placeholder="限行时间" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">
                                                特殊限行
                                            </label>
                                            <div class="col-sm-10">
                                                <select  name="limitSpeial" class="form-control search-select select2 limitSpeial">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="matchingMainRoad.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

