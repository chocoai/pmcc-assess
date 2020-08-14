<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
    <div class="card full-height">
        <div class="card-header">
            <div class="card-head-row">
                <div class="card-title">土地证</div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <div class="row form-group">
                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success btn-sm dropdown-toggle"
                                            data-toggle="dropdown">新增
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
                                <button type="button" class="btn btn-primary btn-sm" onclick="assessLand.editLand()">
                                    <i class="fa fa-pen"></i>
                                    编辑
                                </button>
                                <button type="button" class="btn btn-warning btn-sm"
                                        onclick="assessLand.deleteLand()">
                                    <i class="fa fa-minus"></i>
                                    删除
                                </button>
                                <div class="dropdown" style="display: inline;margin-left: 5px;">
                                    <button type="button" class="btn btn-info dropdown-toggle btn-sm"
                                            data-toggle="dropdown"
                                            aria-expanded="false">
                                        导入土地证
                                    </button>
                                    <div class="dropdown-menu" role="menu">
                                        <a href="javascript://" class="dropdown-item"
                                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate);">下载模板
                                            有权证</a>
                                        <a href="javascript://" class="dropdown-item"
                                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificateNot);">下载模板
                                            无权证</a>
                                        <a href="javascript://;" class="dropdown-item"
                                           onclick="$('#ajaxFileUploadLand').val('').trigger('click')">导入 有权证</a>
                                        <a href="javascript://;" class="dropdown-item"
                                           onclick="$('#ajaxFileUploadLandA').val('').trigger('click')">导入 无权证</a>
                                    </div>
                                </div>

                                <div class="dropdown" style="display: inline;margin-left: 5px;">
                                    <button type="button" class="btn btn-info dropdown-toggle btn-sm"
                                            data-toggle="dropdown"
                                            aria-expanded="false">
                                        导入房产证
                                    </button>
                                    <div class="dropdown-menu" role="menu">
                                        <a href="javascript://" class="dropdown-item"
                                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftHouseOwnershipCertificate);">下载模板</a>
                                        <a href="javascript://;" class="dropdown-item"
                                           onclick="$('#ajaxFileUploadLandHouse').val('').trigger('click')">导入</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <button type="button" class="btn btn-info btn-sm" onclick="assessLand.copyData();"><i
                                        class="fa fa-copy" aria-hidden="true"></i> 复制
                                </button>
                                <button type="button" class="btn btn-warning btn-sm" onclick="assessLand.pasteAll();"><i
                                        class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="input-group " id="landHandleInputGroup">
                                    <input type="hidden" name="id">
                                    <input type="text" readonly="readonly" name="name"
                                           class="form-control form-control-sm"
                                           placeholder="暂无复制数据">
                                    <div class="input-group-prepend ">
                                        <button class="btn btn-warning btn-sm"
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button"
                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                            清空
                                            <i class="fa "></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row form-group">
                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                        <div class="x-valid form-inline">
                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                <div class="form-check" style="justify-content:left">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" name="isSource"
                                               value="isSource">
                                        <span class="form-check-sign">房产证</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">启始编号</label>
                            <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                                <input type="text" placeholder="必须数字" name="startNumber"
                                       class="form-control input-full">
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">截至编号</label>
                            <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                                <input type="text" placeholder="必须数字" name="endNumber" class="form-control input-full">
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">步长</label>
                            <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                                <input type="text" placeholder="每个权证关联的图片个数" name="step" class="form-control input-full"
                                       value="">
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                <input id="landAttachmentAutomatedWarrantsPDF"
                                       name="landAttachmentAutomatedWarrantsPDF" placeholder="pdf自动关联权证附件"
                                       class="form-control input-full"
                                       type="file">
                                <div id="_landAttachmentAutomatedWarrantsPDF"></div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                        <button type="button" class="btn-primary btn btn-sm"
                                onclick="assessLand.attachmentAutomatedWarrants(this);">关联
                        </button>
                    </div>
                </div>

                <div class="row form-group">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
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
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">土地证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="autoInitNumber">
                    <input type="hidden" name="landCertGetQuestion">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            <div class="customer_body">

                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                上传土地证附件
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <input id="declareRealtyLandCertFileId"
                                                       name="declareRealtyLandCertFileId"
                                                       required="required" placeholder="上传土地证附件"
                                                       class="form-control input-full"
                                                       type="file">
                                                <div id="_declareRealtyLandCertFileId"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="assessLand.saveAndUpdateLand()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!-- 关联房产证信息 -->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 65%;">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">关联房产证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="declareRealtyLandCert_HouseCert_frm">
                    <input type="hidden" name="id">
                    <input type="hidden" name="centerId">
                    <input type="hidden" name="autoInitNumber">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            <div class="customer_body">

                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                上传房产证
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <input id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"
                                                       name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"
                                                       required="required" placeholder="上传房产证"
                                                       class="form-control input-full"
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
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-warning btn-sm" onclick="assessLand.deleteHouseCenter()">
                    删除
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessLand.saveAndUpdateHouse();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
