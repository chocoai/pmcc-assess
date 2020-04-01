
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    公共部分
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
                            data-toggle="modal" onclick="unitCommonPart.prototype.showModel()"
                            href="#divBoxExamineUnitCommonPart">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="ExamineUnitCommonPartList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxExamineUnitCommonPart" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">公共部分</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmExamineUnitCommonPart" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            公共部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="text" required name="unitCommonPart"
                                                       class="form-control" list="unitCommonPartList2">
                                                <datalist id="unitCommonPartList2">

                                                </datalist>
                                                <div class="input-group-prepend">
                                                    <button class="btn btn-warning btn-sm "
                                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                            type="button"
                                                            onclick="$(this).closest('.input-group').find('input').val('');">
                                                        清空
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
                                            所在位置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="text" required name="unitLocation"
                                                       class="form-control" list="unitLocationList">
                                                <datalist id="unitLocationList">

                                                </datalist>
                                                <div class="input-group-prepend">
                                                    <button class="btn btn-warning btn-sm "
                                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                            type="button"
                                                            onclick="$(this).closest('.input-group').find('input').val('');">
                                                        清空
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
                                            单位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="unitMonad"
                                                    class="form-control input-full unitMonad">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="unitQuantity"
                                                    class="form-control input-full unitQuantity">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="unitCommonPart.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


</html>

