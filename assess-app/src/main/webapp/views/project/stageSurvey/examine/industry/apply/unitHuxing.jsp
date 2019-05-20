<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <div class="form-group" id="huxingType" style="display: none">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                类型<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <select name="type" class="form-control type">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="x_title">
                        <div class="clearfix"></div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                面积<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                朝向<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <select name="orientation" class="form-control search-select select2 orientation">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                户型描述
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 1">
                                            <textarea placeholder="户型描述" name="description" class="form-control" >
                                            </textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                户型图<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 1">
                                <input id="house_latest_family_planV"  placeholder="上传附件" class="form-control"
                                       type="file">
                                <div id="_house_latest_family_planV"></div>
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

</html>

