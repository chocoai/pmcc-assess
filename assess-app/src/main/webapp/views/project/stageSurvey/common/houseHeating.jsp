<%--
 供暖情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12" tab-role="method">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    供暖情况
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
                            data-toggle="modal" onclick="houseHeating.prototype.showModel()"
                            href="#divBoxHouseHeating">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseHeatingList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">供暖情况</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHeating" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备品牌" class="form-control input-full"
                                                   required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            档次
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="grade" class="form-control input-full  grade">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
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
                                            供应方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="supplyMode"
                                                    class="form-control input-full  supplyMode">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control input-full  equipmentPrice">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseHeating.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

