<%--
  餐饮
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12"   tab-role="method">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    餐饮信息
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <div class="row form-group form-inline">
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="matchingRestaurant.prototype.showModel()"
                            href="#divBoxMatchingRestaurant">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <button type="button" style="margin-left: 5px" class="btn btn-warning btn-sm" onclick="matchingRestaurant.prototype.clear()">
                        	<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                        删除
                    </button>

                    <label class="col-sm-1 control-label">
                        餐饮类别
                    </label>
                    <div class="col-sm-2">
                        <select name="category" class="form-control input-full category">
                        </select>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" placeholder="距离(单位/m)" class="form-control input-full" name="distance"
                               value="3000">
                    </div>

                    <button type="button" class="btn btn-info btn-sm" onclick="assessMatchingRestaurant.select(this)">
                        <i class="fa fa-search"></i>搜索
                    </button>

                </div>
                <table class="table table-bordered" id="MatchingRestaurantList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingRestaurant" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">餐饮</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmMatchingRestaurant" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-sm btn-success"
                                                    onclick="matchingRestaurant.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="row form-group" style=" margin-top: 8px;">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 control-label">餐饮名称</label>
                                                <div class="col-sm-10 ">
                                                    <div class="input-group">
                                                        <input class="form-control" name="name" required="required"
                                                               type="text">
                                                        <span class="input-group-btn">
                                                         <input type="button" class="btn btn-warning" value="X"
                                                                onclick="matchingRestaurant.prototype.cleanHTMLData(this)"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control input-full  category">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮档次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control input-full  grade">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control input-full  distance">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="matchingRestaurant.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



