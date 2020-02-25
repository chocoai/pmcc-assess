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
                                    <button type="button" class="btn btn-success "
                                            onclick="assessCommonLand.showAddModelLand()"
                                            data-toggle="modal">
                                        <i class="fa fa-plus"></i>
                                        新增
                                    </button>
                                    <button type="button" class="btn btn-primary " onclick="assessCommonLand.editLand()">
                                        <i class="fa fa-pen"></i>
                                        编辑
                                    </button>
                                    <button type="button" class="btn btn-warning "
                                            onclick="assessCommonLand.deleteLand()">
                                        <i class="fa fa-minus"></i>
                                        删除
                                    </button>
                                    <button type="button" class="btn btn-success "
                                            onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate)">
                                        <i class="fa fa-cloud-upload-alt"></i>
                                        下载模板
                                    </button>
                                    <button type="button" class="btn btn-primary "
                                            onclick="$('#ajaxFileUploadLand').val('').trigger('click')">
                                        <i class="fa fa-cloud-upload-alt"></i>
                                        导入
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary   dropdown-toggle"
                                            data-toggle="dropdown">导入关联的房产证
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li>
                                            <a onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftHouseOwnershipCertificate)"><span>下载模板 <i class="fa-cloud-download-alt fa"></i></span></a>
                                        </li>
                                        <li>
                                            <a  onclick="$('#ajaxFileUploadLandHouse').val('').trigger('click')"><span>导入</span><i class="fa-cloud-upload-alt fa"></i></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary " onclick="assessCommonLand.copyData();"><i
                                            class="fa fa-copy" aria-hidden="true"></i> 复制
                                    </button>
                                    <button type="button" class="btn btn-primary " onclick="assessCommonLand.pasteAll();"><i
                                            class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="form-inline x-valid">
                            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                <div class="input-group " id="landHandleInputGroup">
                                    <input type="hidden" name="id">
                                    <input type="text" readonly="readonly" name="name" class="form-control"
                                           placeholder="暂无复制数据">
                                    <div class="input-group-prepend ">
                                        <button class="btn btn-warning "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="$(this).closest('.input-group').find('input').val('');">
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
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">非主数据</label>
                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                <div class="form-check" style="justify-content:left">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" name="isSource" value="false">
                                        <span class="form-check-sign">关联土地证关联的房产证</span>
                                    </label>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">

                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">启始编号</label>
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text"
                                       placeholder="必须数字" name="startNumber" class="form-control input-full">
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">截至编号</label>
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text"
                                       placeholder="必须数字" name="endNumber" class="form-control input-full">
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="x-valid form-inline">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">步长</label>
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
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
                                onclick="assessCommonLand.attachmentAutomatedWarrants(this);">关联
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
       onchange="assessCommonLand.inputFile();">

<input type="file" id="ajaxFileUploadLandHouse" name="file" style="display: none;"
       onchange="assessCommonLand.importDataHouse();">

<input type="file" id="declareRealtyLandCertNewFileId" name="declareRealtyLandCertNewFileId" style="display: none;"
       onchange="assessCommonLand.landImportHandle();" multiple="multiple">

<input type="file" id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId" style="display: none;"
       onchange="assessCommonLand.landImportHandleHouse(false,0);" multiple="multiple">


<!-- 土地证信息 -->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">土地证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            <div class="card-body">

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
                <button type="button" class="btn btn-primary btn-sm" onclick="assessCommonLand.saveAndUpdateLand()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<!-- 关联房产证信息 -->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">关联房产证信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form  class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="centerId">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">

                            <div class="card-body">

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
                <%--<input type="button" class="btn btn-warning btn-sm" value="删除"--%>
                       <%--onclick="">--%>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="assessCommonLand.saveHouseData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>



<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设工程规划许可证</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareBuildingPermitFileId3"
                                                   name="declareBuildingPermitFileId3"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingPermitFileId3"></div>
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
                <button type="button" class="btn btn-warning" onclick="assessCommonLand.declareBuildingPermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessCommonLand.declareBuildingPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建设用地规划许可证</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareLandUsePermitFileId3"
                                                   name="declareLandUsePermitFileId3"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareLandUsePermitFileId3"></div>
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
                <button type="button" class="btn btn-warning" onclick="assessCommonLand.declareLandUsePermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessCommonLand.declareLandUsePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑工程施工许可证</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareBuildingConstructionPermitFileId3"
                                                   name="declareBuildingConstructionPermitFileId3"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingConstructionPermitFileId3"></div>
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
                <button type="button" class="btn btn-warning"
                        onclick="assessCommonLand.declareBuildingConstructionPermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessCommonLand.declareBuildingConstructionPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitLandBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">商品房预售许可证</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declarePreSalePermitFileId3"
                                                   name="declarePreSalePermitFileId3"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declarePreSalePermitFileId3"></div>
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
                <button type="button" class="btn btn-warning" onclick="assessCommonLand.declarePreSalePermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="assessCommonLand.declarePreSalePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
