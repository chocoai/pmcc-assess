<%--
  公交
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>公交信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <button type="button" class="btn btn-success" onclick="matchingTransit.prototype.showModel()"
                        data-toggle="modal" href="#divBox"> 新增
                </button>
                <button type="button" class="btn btn-warning" onclick="matchingTransit.prototype.clear()"> 删除
                </button>
            </div>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" placeholder="距离(单位/m)" class="form-control" name="distance" value="3000">
            </div>
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                <div type="button" class="btn btn-default docs-tooltip"
                     onclick="assessTransit.select(this)">
                    <i class="fa fa-search"></i>搜索
                </div>
            </div>
            <table class="table table-bordered" id="TransitList">
            </table>
        </form>
    </div>
</div>

<div id="divBoxTransit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">公交</h3>
            </div>
            <form id="frmTransit" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            公交站名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            收费标准
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="costStandard"
                                                   placeholder="收费标准">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            距大门距离<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            所在线路<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingTransit.prototype.appendHTML('theLine',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="theLine">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class=" col-xs-2212  col-sm-2212  col-md-2212  col-lg-2212  col-sm-2 col-xs-12 control-label">所在线路</label>
                                        <div class=" col-xs-101012  col-sm-101012  col-md-101012  col-lg-101012  col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="theLine" required="required" type="text">
                                            <span class="input-group-btn">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="matchingTransit.prototype.cleanHTMLData(this)">
                                            </span>
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
                <button type="button" class="btn btn-primary" onclick="matchingTransit.prototype.saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
