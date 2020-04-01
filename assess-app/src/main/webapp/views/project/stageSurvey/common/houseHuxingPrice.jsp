<%--
 单价调查
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    单价调查
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
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline form-inline x-valid">
                            <button class="btn btn-success btn-sm" type="button"
                                    data-toggle="modal" onclick="houseHuxingPrice.prototype.showModel()"
                                    href="#divBoxHouseHuxingPrice">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                新增
                            </button>
                            <div class="btn-group">
                                <button type="button" style="margin-left: 5px"
                                        class="btn btn-info btn-sm dropdown-toggle"
                                        data-toggle="dropdown">
                                                     <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
                                                         </span>导入数据
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="btn"
                                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftBasicUnitPriceInvestigation)">下载模板</a>
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
                <table class="table table-bordered" id="HouseHuxingPriceList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">单价调查</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHuxingPrice" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="declareName">
                    <input type="hidden" name="declareId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房号
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="房号" name="houseNumber"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                面积
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                       class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseHuxingPrice.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;"
       onchange="houseHuxingPrice.prototype.importData(${planDetailsId});">
</html>

