<%--
  房屋配套设备设施
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    房屋配套设备设施信息
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
                            data-toggle="modal" onclick="houseCorollaryEquipment.prototype.showModel()"
                            href="#divBoxHouseCorollaryEquipment">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseCorollaryEquipmentList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxHouseCorollaryEquipment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房屋配套设备设施</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseCorollaryEquipment" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="type"
                                                        class="form-control input-full type">
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
                                                        class="form-control input-full category">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="name" class="form-control input-full"
                                                       required="required">
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
                                                                onclick="houseCorollaryEquipment.prototype.openLocationModal(this);">
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
                                                设备用途<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="equipmentUse"
                                                        class="form-control input-full equipmentUse">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                维护状况<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="maintenanceStatus"
                                                        class="form-control input-full maintenanceStatus">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                参数指标
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="parameterIndex"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                价格
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="price" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                附件<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input id="positionDiagramFileID" placeholder="上传附件"
                                                       class="form-control input-full"
                                                       type="file">
                                                <div id="_positionDiagramFileID"></div>
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="houseCorollaryEquipment.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divHouseCorollaryEquipmentBoxDecoratePart" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">所在位置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <button type="button" class="btn btn-primary btn-sm" onclick="equipmentDecorateLocation.selectAll(this);">全选
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="equipmentDecorateLocation.selectInvert(this);">反选
                </button>
                <form id="frmHouseCorollaryEquipmentLocation" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12 customer_body"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="houseCorollaryEquipment.prototype.saveDecoratePart()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    var equipmentDecorateLocation = {};
    //全选
    equipmentDecorateLocation.selectAll = function (_this) {
        $(_this).closest('.modal-body').find('.customer_body').find(':checkbox').prop('checked', true);
    }
    //反选
    equipmentDecorateLocation.selectInvert = function (_this) {
        $(_this).closest('.modal-body').find('.customer_body').find(':checkbox').each(function () {
            $(this).prop('checked', !$(this).prop('checked'));
        });
    }
</script>