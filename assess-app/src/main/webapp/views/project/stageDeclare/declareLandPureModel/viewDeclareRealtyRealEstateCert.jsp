<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h3>
            不动产证
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">新增
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn"
                               onclick="declareRealtyRealEstateCert.showAddModel(true)">有权证</a>
                        </li>
                        <li><a class="btn"
                               onclick="declareRealtyRealEstateCert.showAddModel(false)">无权证</a>
                        </li>
                    </ul>
                </div>

                <div class="btn-group">
                    <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.editData()"> 编辑
                    </div>
                    <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.deleteData()">
                        删除
                    </div>
                </div>

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">下载模板
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftRealEstateOwnershipCertificate)">下载模板 有权证</a>
                        </li>
                        <li><a class="btn"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftRealEstateOwnershipCertificate2)">下载模板 无权证</a>
                        </li>
                    </ul>
                </div>

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">批量导入数据
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn btn-default"
                               onclick="$('#ajaxFileUploadRealEstate').val('').trigger('click')">有权证 导入</a></li>
                        <li><a class="btn btn-default"
                               onclick="$('#ajaxFileUploadRealEstateA').val('').trigger('click')">无权证 导入</a></li>
                    </ul>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyRealEstateCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadRealEstate" name="file" style="display: none;"
       onchange="declareRealtyRealEstateCert.inputFile(true);">
<input type="file" id="ajaxFileUploadRealEstateA" name="file" style="display: none;"
       onchange="declareRealtyRealEstateCert.inputFile(false);">

<input type="file" id="declareRealtyRealEstateCertFileId" name="declareRealtyRealEstateCertFileId"
       style="display: none;"
       onchange="declareRealtyRealEstateCert.enclosureFun();">
<!-- 不动产 add -->
<div id="boxDeclareRealtyRealEstateCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证信息</h3>
            </div>
            <form id="frmDeclareRealtyRealEstateCert" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="landCertGetQuestion">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            批文附件
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareRealtyRealEstateCertNewFileId"
                                                   name="declareRealtyRealEstateCertNewFileId" placeholder="批文附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyRealEstateCertNewFileId"></div>
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
                    <label class="btn btn-primary" onclick="declareRealtyRealEstateCert.saveAndUpdateData()">
                        保存
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>
