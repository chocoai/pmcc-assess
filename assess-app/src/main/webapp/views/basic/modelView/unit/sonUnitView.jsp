<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼栋内装情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="unitDecorate.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="ExamineUnitDecorateList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>户型</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="unitHuxing.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="UnitHuxingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>配备电梯</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="unitElevator.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="ExamineUnitElevatorList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/basic/unit/sonUnitView.js"></script>

<div id="divBoxExamineUnitDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋内装情况</h3>
            </div>
            <form id="frmExamineUnitDecorate" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在位置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input name="location" class="form-control" placeholder="所在位置" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPriceRange"
                                                    class="form-control search-select select2 materialPriceRange">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitDecorate.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxUnitHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">户型</h3>
            </div>
            <form id="frmUnitHuxing" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            朝向<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="orientation" class="form-control search-select select2 orientation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" id="industrySpan" style="display: none;">
                                    <div class="x-valid" id="industrySpanLength">
                                        <label class="col-sm-1 control-label">
                                            跨长<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨长(数字)" data-rule-number='true'
                                                   name="spanLength" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid" id="industrySpanWidth">
                                        <label class="col-sm-1 control-label">
                                            跨宽<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨宽(数字)" data-rule-number='true'
                                                   name="spanWidth" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid" id="industrySpanNumber">
                                        <label class="col-sm-1 control-label">
                                            跨数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨数(数字)" data-rule-number='true'
                                                   name="spanNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (房) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">卧室</span>
                                                <input type="text" name="house" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (厅)
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">客厅</span>
                                                <input type="text" name="saloon" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (厨)
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">厨房</span>
                                                <input type="text" name="kitchen" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (卫) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">卫生间</span>
                                                <input type="text" name="toilet" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (花园)
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">花园</span>
                                                <input type="text" name="garden" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型 (阳台)
                                        </label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <span class="input-group-addon">阳台</span>
                                                <input type="text" name="balcony" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型描述<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-11">
                                            <textarea placeholder="户型描述" name="description"
                                                      class="form-control" required="required">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型图<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-11">
                                            <input id="house_latest_family_planV"  placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_house_latest_family_planV"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitHuxing.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineUnitElevator" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">配备电梯</h3>
            </div>
            <form id="frmExamineUnitElevator" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯维护情况<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="maintenance"
                                                   placeholder="电梯维护情况" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="brand"
                                                   placeholder="电梯品牌" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="电梯数量(数字)" data-rule-number='true'
                                                   name="number" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载人数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载人数(数字)" data-rule-number='true'
                                                   name="quasiLoadNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载重量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载重量(数字)" data-rule-number='true'
                                                   name="quasiLoadWeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            运行速度
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="runningSpeed"
                                                   placeholder="运行速度" >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitElevator.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
