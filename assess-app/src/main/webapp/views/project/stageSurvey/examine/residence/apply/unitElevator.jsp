<%--
 电梯
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                                            <select required="required" name="maintenance"
                                                    class="form-control search-select select2 maintenance">
                                            </select>
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
                                            准载人数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载人数(数字)" data-rule-number='true'
                                                   name="quasiLoadNumber" class="form-control" >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载重量
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载重量(数字)" data-rule-number='true'
                                                   name="quasiLoadWeight" class="form-control" >
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



</html>

