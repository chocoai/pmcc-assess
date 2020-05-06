<%--
 楼栋内装
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    装饰装修
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
                            data-toggle="modal" onclick="unitDecorate.prototype.showModel()"
                            href="#divBoxExamineUnitDecorate">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="ExamineUnitDecorateList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxExamineUnitDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">装饰装修</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmExamineUnitDecorate" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修材料<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="decoratingMaterial"
                                                        class="form-control input-full decoratingMaterial">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                材料档次<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="materialGrade"
                                                        class="form-control input-full materialGrade">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                施工工艺<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="constructionTechnology"
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
                                                材料价格区间
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="materialPriceRange"
                                                        class="form-control input-full materialPriceRange">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control input-full decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                公共部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">


                                                <div class="input-group">
                                                    <input type="text" required name="unitCommonPart"
                                                           class="form-control">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="unitDecorate.prototype.openPartItemModal();">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>

                                                <%--<div class="input-group">--%>
                                                    <%--<input type="text" required="required" name="unitCommonPart"--%>
                                                           <%--placeholder="公共部位(从公共部分列表中获取)" class="form-control form-control-sm"--%>
                                                           <%--list="unitDecorate_datalist">--%>
                                                    <%--<datalist id="unitDecorate_datalist">--%>
                                                        <%--<option value="" selected="">-请选择-</option>--%>
                                                    <%--</datalist>--%>


                                                    <%--<div class="input-group-prepend ">--%>
                                                        <%--<button class="btn btn-warning btn-sm "--%>
                                                                <%--style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"--%>
                                                                <%--type="button"--%>
                                                                <%--onclick="$(this).closest('.input-group').find('input').val('');">--%>
                                                            <%--清空--%>
                                                            <%--<i class="fa "></i>--%>
                                                        <%--</button>--%>
                                                    <%--</div>--%>

                                                <%--</div>--%>

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
                <button type="button" class="btn btn-primary btn-sm" onclick="unitDecorate.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxUnitDecoratePartItem" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form id="frmUnitDecoratePartItem" class="form-horizontal">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="unitDecorate.prototype.splicePartItem()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
</html>

