<%--
  交通枢纽
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12"  tab-role="method">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    交通枢纽信息
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
                            data-toggle="modal" onclick="matchingTrafficHub.prototype.showModel()"
                            href="#divBoxMatchingTrafficHub">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <button type="button" style="margin-left: 5px" class="btn btn-warning btn-sm" onclick="matchingTrafficHub.prototype.clear()">
                        	<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                        删除
                    </button>

                    <div class="col-sm-3">
                        <input type="text" placeholder="距离(单位/m)" class="form-control input-full input-full" name="distance"
                               value="3000">
                    </div>

                    <button type="button" class="btn btn-info btn-sm" onclick="assessMatchingTrafficHub.select(this)">
                        <i class="fa fa-search"></i>搜索
                    </button>

                </div>
                <table class="table table-bordered" id="MatchingTrafficHubList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingTrafficHub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">交通枢纽</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmMatchingTrafficHub" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control input-full" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            收费标准
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control input-full" name="costStandard"
                                                   placeholder="收费标准">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="nature"
                                                    class="form-control input-full  nature">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="matchingTrafficHub.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


