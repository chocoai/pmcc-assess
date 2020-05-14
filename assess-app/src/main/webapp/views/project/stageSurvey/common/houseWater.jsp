<%--
 供水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    供水情况
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
                            data-toggle="modal" onclick="houseWater.prototype.showModel()"
                            href="#divBoxHouseWater">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseWaterList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">供水情况</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseWater" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                给水方式<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="supplyMode"
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
                                            供水分类<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="fireWaterSupply"
                                                    class="form-control input-full  fireWaterSupply">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水管道布置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="pipingLayout"
                                                    class="form-control input-full  pipingLayout">

                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水管材料<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="pipeMaterial"
                                                    class="form-control input-full  pipeMaterial">

                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            档次<span class="symbol required"></span>
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
                                            给水升压设备
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="boosterEquipment"
                                                    class="form-control input-full  boosterEquipment">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="pretreatedWater"
                                                    class="form-control input-full  pretreatedWater">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水设备价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="purificationEquipmentPrice"
                                                    class="form-control input-full  purificationEquipmentPrice">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseWater.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


