<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h3>
            房产证
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="btn-group">
                    <div type="button" class="btn btn-success" onclick="assessCommonHouse.showAddModelHouse()"
                         data-toggle="modal"> 新增
                    </div>
                    <div type="button" class="btn btn-primary" onclick="assessCommonHouse.editHouse()"> 编辑</div>
                    <c:if test="${projectPlanDetails.bisRestart ne true}">
                        <div type="button" class="btn btn-primary" onclick="assessCommonHouse.deleteHouse()"> 删除</div>
                    </c:if>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入房产证
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftHouseOwnershipCertificate)">下载模板</a>
                        </li>
                        <li><a onclick="$('#ajaxFileUploadHouse').val('').trigger('click')">导入</a>
                        </li>
                    </ul>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入土地证
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate)"><span>下载模板</span></a>
                        </li>
                        <li><a onclick="$('#ajaxFileUploadHouseLand').val('').trigger('click')"><span>导入</span></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyHouseCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadHouse" name="file" style="display: none;"
       onchange="assessCommonHouse.inputFile();">
<input type="file" id="ajaxFileUploadHouseLand" name="file" style="display: none;"
       onchange="assessCommonHouse.inputFileLand();">
<input type="file" id="declareRealtyHouseCertFileIdNew" name="declareRealtyHouseCertFileIdNew" style="display: none"
       onchange="assessCommonHouse.houseImportHandle();">
<input type="file" id="sonDeclareRealtyLandCertFileId" name="file" style="display: none"
       onchange="assessCommonHouse.landImportHandle()">

<!-- 房产证信息 -->
<div id="boxDeclareRealtyHouseCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房产证信息</h3>
            </div>
            <form id="frmDeclareRealtyHouseCert" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传房产证
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareRealtyHouseCertFileId" name="declareRealtyHouseCertFileId"
                                                   required="required" placeholder="上传房产证" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyHouseCertFileId"></div>
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
                        onclick="assessCommonHouse.saveAndUpdateHouse();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 土地证 add -->
<div id="boxSonDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmSonDeclareRealtyLandCert" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传土地证附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareRealtyHouseCert_land_FileId"
                                                   name="declareRealtyHouseCert_land_FileId"
                                                   required="required" placeholder="上传土地证附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyHouseCert_land_FileId"></div>
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
                    <button type="button" class="btn btn-primary"
                            onclick="assessCommonHouse.saveAndUpdateLand();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
