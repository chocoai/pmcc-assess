<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divBoxUnitHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">单价调查</h3>
            </div>
            <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                <input type="hidden" name="unitHuxingId">
                <div class="modal-body">
                    <button type="button" class="btn btn-success" onclick="unitHuxingPrice.prototype.showModel()"
                            data-toggle="modal" href="#divBox"> 新增
                    </button>
                    <table class="table table-bordered" id="UnitHuxingPriceList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>





<div id="divBoxUnitHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">单价调查</h3>
            </div>
            <form id="frmUnitHuxingPrice" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="huxingId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                房号
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="房号" name="houseNumber"class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                价格
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="价格" name="price" data-rule-number='true' required
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                备注
                            </label>
                            <div class="col-sm-10">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="unitHuxingPrice.prototype.saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

</html>

