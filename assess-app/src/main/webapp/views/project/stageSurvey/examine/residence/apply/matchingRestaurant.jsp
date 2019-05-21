<%--
  餐饮
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>餐饮信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="col-sm-3">
                <div type="button" class="btn btn-success"
                     onclick="matchingRestaurant.prototype.showModel()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <div type="button" class="btn btn-warning"
                     onclick="matchingRestaurant.prototype.clear()"> 删除
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    餐饮类别
                </label>
                <div class="col-sm-2">
                    <select  name="category" class="form-control  category"></select>
                </div>
            </div>
            <div class="col-sm-3">
                <input type="text" placeholder="距离(单位/m)" class="form-control" name="distance" value="3000">
            </div>
            <div class="col-sm-1">
                <div type="button" class="btn btn-default docs-tooltip"
                     onclick="assessMatchingRestaurant.select(this)">
                    <i class="fa fa-search"></i>搜索
                </div>
            </div>
            <table class="table table-bordered" id="MatchingRestaurantList">
                <!-- cerare document add ajax data-->
            </table>
        </form>
    </div>
</div>

<div id="divBoxMatchingRestaurant" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">餐饮</h3>
            </div>
            <form id="frmMatchingRestaurant" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingRestaurant.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">餐饮名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="name" required="required" type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="matchingRestaurant.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮档次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮距离<span class="symbol required"></span>
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
                    <button type="button" class="btn btn-primary" onclick="matchingRestaurant.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>