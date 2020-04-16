<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/html" id="unitLocationTextModelHtml">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 col-form-label">
                    名称 <span class="symbol required"></span>
                </label>
                <div class="col-sm-2">
                    <input type="text" class="form-control input-full" name="name{index}" required data-name="name"
                           value="{name}">
                </div>
                <label class="col-sm-1 col-form-label">
                    所在位置 <span class="symbol required"></span>
                    <input type="hidden" name="index{index}" data-name="index" value="{index}">
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" required="required" name="unitLocation{index}" data-name="unitLocation"
                               class="form-control" list="unitLocationList" value="{unitLocation}">
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
                <label class="col-sm-1 col-form-label">
                    描述
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text"  name="description{index}" data-name="description"
                               class="form-control" list="unitDescriptionList" value="{description}">
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
                <div class="col-sm-1">
                    <button  class="btn btn-warning btn-sm" onclick="UnitCommonPartFun.clearHtml(this)" type="button">
                       X
                    </button>
                </div>
            </div>
        </div>
    </div>
</script>

<script>


</script>

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
                            data-toggle="modal" onclick="UnitCommonPartFun.showModel()"
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
                                            <label class="col-sm-1 control-label">
                                                部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="text" required="required" name="unitCommonPart"
                                                           onblur=";"
                                                           placeholder="部位名称" class="form-control form-control-sm"
                                                           list="unitCommonPart_datalist">
                                                    <datalist id="unitCommonPart_datalist">
                                                        <option value="" selected="">-请选择-</option>
                                                    </datalist>


                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>

                                            <label class="col-sm-1 control-label">
                                                数量<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">

                                                <div class="input-group">
                                                    <input type="text" required="required" name="unitQuantity"
                                                           onblur=" ;"
                                                           placeholder="数量 (选择后生成类似楼梯间1)"
                                                           class="form-control form-control-sm"
                                                           list="unitQuantity_datalist">
                                                    <datalist id="unitQuantity_datalist">
                                                        <option value="" selected="">-请选择-</option>
                                                    </datalist>

                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>

                                                </div>

                                            </div>
                                            <label class="col-sm-1 control-label">
                                                单位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">

                                                <div class="input-group">
                                                    <input type="text" required="required" name="unitMonad"
                                                           onblur=" ;"
                                                           placeholder="单位" class="form-control form-control-sm"
                                                           list="unitMonad_datalist">
                                                    <datalist id="unitMonad_datalist">
                                                        <option value="" selected="">-请选择-</option>
                                                    </datalist>

                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <datalist id="unitLocationList">
                                    <option value="" selected="">-请选择-</option>
                                </datalist>

                                <datalist id="unitDescriptionList">
                                    <option value="" selected="">-请选择-</option>
                                </datalist>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">

                                            <div class="col-sm-10">
                                                <button onclick="UnitCommonPartFun.appendRecording(this);"
                                                        class="btn btn-sm btn-success" type="button">
                                                    生成记录<i
                                                        class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="unitLocationTextModel">

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
                <button type="button" class="btn btn-primary btn-sm" onclick="UnitCommonPartFun.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxExamineUnitPartItem" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">公共部分明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmExamineUnitPartItem" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

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
                        onclick="UnitCommonPartFun.splicePartItem()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<script type="text/javascript">
    function openPartItemModal() {
        unitCommonPart.prototype.openPartItemModal();
    }
</script>
