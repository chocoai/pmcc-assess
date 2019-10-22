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
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">新增
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="assessLand.showAddModelLand(true)">有权证</a>
                            </li>
                            <li><a class="btn"
                                   onclick="assessLand.showAddModelLand(false)">无权证</a>
                            </li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <div type="button" class="btn btn-primary" onclick="assessLand.editLand()"> 编辑</div>
                        <div type="button" class="btn btn-primary" onclick="assessLand.deleteLand()"> 删除</div>
                    </div>
                </div>
                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">土地证 下载模板
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate)">下载模板 有权证与无权证一致</a>
                            </li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入土地证数据
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn btn-default"
                                   onclick="$('#ajaxFileUploadLand').val('').trigger('click')">导入 有权证</a>
                            </li>
                            <li><a class="btn btn-default"
                                   onclick="$('#ajaxFileUploadLandA').val('').trigger('click')">导入 无权证</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
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

                    <div class="btn-group">
                        <div class="btn btn-primary" onclick="assessLand.copyData();"> <i class="fa fa-files-o" aria-hidden="true"></i> 复制</div>
                        <div class="btn btn-primary" onclick="assessLand.pasteAll();"> <i class="fa fa-clipboard" aria-hidden="true"></i>粘贴</div>
                    </div>
                </div>

                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                    <div class="input-group" id="landHandleInputGroup">
                        <input type="hidden" name="id">
                        <input type="text" readonly="readonly" name="name" class="form-control" placeholder="暂无复制数据">
                        <span class="input-group-btn">
                                <button type="button" class="btn btn-default docs-tooltip"
                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                        data-toggle="tooltip" data-original-title="清除">
                                <i class="fa fa-trash-o"></i>
                                </button>
                                </span>
                    </div>
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
       onchange="assessLand.inputFile(true);">
<input type="file" id="ajaxFileUploadLandA" name="file" style="display: none;"
       onchange="assessLand.inputFile(false);">
<input type="file" id="ajaxFileUploadLandHouse" name="file" style="display: none;"
       onchange="assessLand.inputFileHouse();">
<input type="file" id="declareRealtyLandCertNewFileId" name="declareRealtyLandCertNewFileId" style="display: none;"
       onchange="assessLand.landImportHandle();">
<input type="file" id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       onchange="assessLand.houseImportHandle();" style="display: none">

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
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <input type="hidden" name="id">
                            <input type="hidden" name="landCertGetQuestion">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareRealtyLandCertFileId"
                                                   name="declareRealtyLandCertFileId" placeholder="上传附件" class="form-control"
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
                    <button type="button" class="btn btn-primary" onclick="assessLand.saveAndUpdateLand();">
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
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
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
                <input type="button" class="btn btn-warning" value="删除"  onclick="assessLand.deleteHouseCenter()">
                <button type="button" class="btn btn-primary"
                        onclick="assessLand.saveAndUpdateHouse();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
