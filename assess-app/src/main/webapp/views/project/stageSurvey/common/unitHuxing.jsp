<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    户型
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
                            data-toggle="modal" onclick="unitHuxing.prototype.showModel()"
                            href="#divBoxUnitHuxing">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                <%--<button type="button" class="btn btn-info btn-sm" onclick="unitHuxing.prototype.importHouseBtn()">
                    <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>导入数据</button>--%>

                </p>
                <table class="table table-bordered" id="UnitHuxingList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxUnitHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">户型</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmUnitHuxing" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group" id="huxingType" style="display: none">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control input-full type">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                卧室
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="house" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                客厅
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="saloon" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                厨房
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="kitchen" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                卫生间
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="toilet" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                花园
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="garden" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                阳台
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="个数" name="balcony" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                
                                <div class="row form-group" id="huxingTypeProduction" style="display: none">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            跨长<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨长(m)" data-rule-number='true'
                                                   name="spanLength" class="form-control input-full" required="required">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            跨宽<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨宽(m)" data-rule-number='true'
                                                   name="spanWidth" class="form-control input-full" required="required">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            跨数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="跨数(m)" data-rule-number='true'
                                                   name="spanNumber" class="form-control input-full" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group" id="huxingTypeOffice" style="display: none">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            开间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="开间(m)" data-rule-number='true'
                                                   name="bay" class="form-control input-full" required="required">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            进深<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="进深(m)" data-rule-number='true'
                                                   name="deep" class="form-control input-full" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                          
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                                   class="form-control input-full" required="required">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            朝向<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="orientation" class="form-control input-full search-select select2 orientation">
                                            </select>
                                        </div>
                                        <label class="col-sm-1 control-label">空间布局</label>
                                        <div class="col-sm-3">
                                            <select class="form-control input-full search-select select2 spatialDistribution"
                                                    name="spatialDistribution">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                标准房号
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="标准房号" name="standardHouseNumber"
                                                       class="form-control input-full" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型描述
                                        </label>
                                        <div class="col-sm-11">
                                            <textarea placeholder="户型描述" name="description" class="form-control input-full">
                                            </textarea>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            户型图<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-11">
                                            <input id="house_latest_family_planV" placeholder="上传附件" class="form-control input-full"
                                                   type="file">
                                            <div id="_house_latest_family_planV"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="unitHuxing.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<input type="file" id="ajaxFileUploadHouse" name="file" style="display: none;"
       onchange="unitHuxing.prototype.importHouse();">
</html>

