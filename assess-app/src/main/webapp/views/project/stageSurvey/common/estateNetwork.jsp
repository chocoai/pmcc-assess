<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    通信网络
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
                        data-toggle="modal" onclick="estateNetwork.prototype.showModel()"
                        href="#divBoxExamineEstateNetwork">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                    新增
                </button>
                </p>
                <table class="table table-bordered" id="examineEstateNetworkList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>




<div id="divBoxExamineEstateNetwork" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">通信网络</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmExamineEstateNetwork" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                供应商名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full  supplier"
                                                        name="supplier" required></select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                服务内容
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full  serviceContent"
                                                        name="serviceContent"></select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                服务内容描述
                                            </label>
                                            <div class="col-sm-10">
                                            <textarea class="form-control input-full" name="remark"
                                                      placeholder="服务内容描述"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="estateNetwork.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>




</html>
