<%--
  休闲娱乐
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>休闲娱乐信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <div type="button" class="btn btn-success"
                     onclick="matchingRecreation.prototype.showModel()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <div type="button" class="btn btn-warning"
                     onclick="matchingRecreation.prototype.clear()"> 删除
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    休闲娱乐类别
                </label>
                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                    <select name="category" class="form-control  category">
                    </select>
                </div>
            </div>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" placeholder="距离(单位/m)" class="form-control" name="distance" value="3000">
            </div>
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                <div type="button" class="btn btn-default docs-tooltip"
                     onclick="assessMatchingRecreation.select(this)">
                    <i class="fa fa-search"></i>搜索
                </div>
            </div>
            <table class="table table-bordered" id="MatchingRecreationList">
                <!-- cerare document add ajax data-->
            </table>
        </form>
    </div>
</div>

<div id="divBoxMatchingRecreation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">休闲娱乐</h3>
            </div>
            <form id="frmMatchingRecreation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            休闲娱乐名称
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 0">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingRecreation.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <div class="x-valid">
                                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">休闲娱乐名称</label>
                                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 0 ">
                                                <div class="input-group">
                                                    <input class="form-control" name="name" required="required"
                                                           type="text">
                                                    <span class="input-group-btn">
                                                         <input type="button" class="btn btn-warning" value="X"
                                                                onclick="matchingRecreation.prototype.cleanHTMLData(this)"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            休闲娱乐类别<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 0">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            休闲娱乐距离<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 0">
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
                    <button type="button" class="btn btn-primary" onclick="matchingRecreation.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
