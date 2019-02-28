<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h3>
            土地证
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="btn-group">
                    <button type="button" class="btn btn-success" onclick="assessCommonLand.showAddModelLand()"
                            data-toggle="modal"> 新增
                    </button>
                    <div type="button" class="btn btn-primary" onclick="assessCommonLand.editLand()"> 编辑</div>
                    <div type="button" class="btn btn-primary" onclick="assessCommonLand.deleteLand()"> 删除</div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入土地证数据
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate)">下载模板</a>
                        </li>
                        <li><a class="btn btn-default"
                               onclick="$('#ajaxFileUploadLand').val('').trigger('click')">导入</a>
                        </li>
                    </ul>
                </div>

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入房产证数据
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftHouseOwnershipCertificate)">下载模板</a>
                        </li>
                        <li><a class="btn btn-default"
                               onclick="$('#ajaxFileUploadLandHouse').val('').trigger('click')">导入</a></li>
                    </ul>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadLand" name="file" style="display: none;"
       onchange="assessCommonLand.inputFile();">
<input type="file" id="ajaxFileUploadLandHouse" name="file" style="display: none;"
       onchange="assessCommonLand.inputFileHouse();">
<input type="file" id="declareRealtyLandCertNewFileId" name="declareRealtyLandCertNewFileId" style="display: none;"
       onchange="assessCommonLand.landImportHandle();">
<input type="file" id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       onchange="assessCommonLand.houseImportHandle();" style="display: none">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.land.cert.js"></script>

<!-- 土地证信息 -->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                                <input type="hidden" name="id">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传土地证附件
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyLandCertFileId"
                                                   name="declareRealtyLandCertFileId"
                                                   required="required" placeholder="上传土地证附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCertFileId"></div>
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
                    <button type="button" class="btn btn-primary" onclick="assessCommonLand.saveAndUpdateLand();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 房产证信息 -->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">关联房产证信息</h3>
            </div>
            <form id="declareRealtyLandCert_HouseCert_frm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"
                                                   name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"
                                                   placeholder="上传附件"
                                                   class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessCommonLand.saveAndUpdateHouse();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
