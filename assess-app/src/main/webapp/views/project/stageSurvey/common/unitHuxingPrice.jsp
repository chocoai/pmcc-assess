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
                <h4 class="modal-title">调查信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                    <input type="hidden" name="unitHuxingId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline form-inline x-valid">
                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="unitHuxingPrice.prototype.showModel()"
                                                    data-toggle="modal" href="#divBoxUnitHuxingPrice">
                                                <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                                            </button>
                                            <div class="btn-group"> 
                                                <button type="button" class="btn btn-info btn-sm dropdown-toggle"
                                                        data-toggle="dropdown">
                                                     <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
                                                         </span>导入数据
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a class="btn"
                                                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftBasicEstateInvestigation)">下载模板</a>
                                                    </li>
                                                    <li>
                                                        <a class="btn"
                                                           onclick="$('#ajaxFileUpload').val('').trigger('click')"><span>导入</span></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="UnitHuxingPriceList">
                                            <!-- cerare document add ajax data-->
                                        </table>
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
            </div>

        </div>
    </div>
</div>

<%--
<div id="divBoxUnitHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">调查信息</h3>
            </div>
            <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                <input type="hidden" name="unitHuxingId">
                <div class="modal-body">
                    <button type="button" class="btn btn-success" onclick="unitHuxingPrice.prototype.showModel()"
                            data-toggle="modal" href="#divBox"> 新增
                    </button>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入数据
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftBasicEstateInvestigation)">下载模板</a>
                            </li>
                            <li>
                                <a class="btn"
                                   onclick="$('#ajaxFileUpload').val('').trigger('click')"><span>导入</span></a>
                            </li>
                        </ul>
                    </div>
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
--%>

<div id="divBoxUnitHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">调查信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmUnitHuxingPrice" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="huxingId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            楼栋号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber" class="form-control input-full">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            单元号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="单元号" name="unitNumber" class="form-control input-full">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            房号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="房号" name="houseNumber" class="form-control input-full">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            建筑面积
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="建筑面积" name="buildingArea" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            价格
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="价格" name="price" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            规划用途
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="planningUse" class="form-control input-full search-select select2 planningUse">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            结构
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="结构" name="construction" class="form-control input-full">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            备注
                                        </label>
                                        <div class="col-sm-11">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="unitHuxingPrice.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<%--
<div id="divBoxUnitHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">调查信息</h3>
            </div>
            <form id="frmUnitHuxingPrice" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="huxingId">
                <div class="modal-body">
                    <div class="row form-group">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                楼栋号
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="楼栋号" name="buildingNumber" class="form-control input-full">
                            </div>
                        </div>
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                单元号
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="单元号" name="unitNumber" class="form-control input-full">
                            </div>
                        </div>
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                房号
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="房号" name="houseNumber" class="form-control input-full">
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                建筑面积
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="建筑面积" name="buildingArea" data-rule-number='true'
                                       class="form-control input-full">
                            </div>
                        </div>
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                价格
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="价格" name="price" data-rule-number='true'
                                       class="form-control input-full">
                            </div>
                        </div>
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                规划用途
                            </label>
                            <div class="col-sm-3">
                                <select name="planningUse" class="form-control input-full search-select select2 planningUse">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                结构
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="结构" name="construction" class="form-control input-full">
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                备注
                            </label>
                            <div class="col-sm-11">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control input-full"></textarea>
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
--%>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;"
       onchange="unitHuxingPrice.prototype.importData();">
</html>

