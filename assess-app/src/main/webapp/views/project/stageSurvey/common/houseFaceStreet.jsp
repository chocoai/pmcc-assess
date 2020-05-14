<%--
 临街（路）状况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    临街（路）状况
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
                <p>
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="houseFaceStreet.prototype.showModel()"
                            href="#divBoxHouseFaceStreet">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseFaceStreetList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxHouseFaceStreet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">临街（路）状况</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseFaceStreet" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道（道路）名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-sm btn-success"
                                                    onclick="houseFaceStreet.prototype.appendHTML('streetName',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="row form-group" style=" margin-top: 8px;">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 control-label">街道（道路）名称</label>
                                                <div class="col-sm-10 ">
                                                    <div class="input-group">
                                                        <input class="form-control" name="streetName" required="required"
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
                                            街道级别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="streetLevel"
                                                    class="form-control input-full  streetLevel">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            人流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="visitorsFlowrate"
                                                    class="form-control input-full  visitorsFlowrate">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            方位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="position"
                                                    class="form-control input-full  position">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            交通流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="trafficFlow"
                                                    class="form-control input-full  trafficFlow">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseFaceStreet.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

