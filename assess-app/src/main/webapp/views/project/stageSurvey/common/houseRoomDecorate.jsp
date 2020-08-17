<%--
 装修情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12" id="showHouseDecorate">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    装修情况
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
                            data-toggle="modal" onclick="houseRoomDecorate.prototype.showModel()"
                            href="#divBoxHouseRoomDecorate">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseRoomDecorateList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseRoomDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">装修情况</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseRoomDecorate" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="part"
                                                        class="form-control input-full  part">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                部位描述
                                            </label>
                                            <div class="col-sm-10">
                                                <textarea name="remark" class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                所在位置<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input type="text" placeholder="所在位置" required
                                                           name="location"
                                                           class="form-control">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseRoomDecorate.prototype.openLocationModal(this);">
                                                            编辑
                                                        </button>
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
                                                装修材料
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="material" class="form-control input-full  material">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                施工工艺
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="constructionTechnology"
                                                        class="form-control input-full constructionTechnology">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修材料价格区间
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="materialPrice"
                                                        class="form-control input-full materialPrice">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修档次
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="level" class="form-control input-full level">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoomDecorate.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<div id="divBoxLocation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">所在位置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmLocation" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="customer_body">

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoomDecorate.prototype.spliceLocation()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


