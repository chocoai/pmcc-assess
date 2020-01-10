<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>
            土地证
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">

        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <form class="form-horizontal">
                <div class="form-group">

                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div class="btn-group">
                            <button type="button" class="btn btn-success" onclick="assessCommonLand.showAddModelLand()"
                                    data-toggle="modal"> 新增
                            </button>
                            <div type="button" class="btn btn-primary" onclick="assessCommonLand.editLand()"> 编辑</div>
                            <div type="button" class="btn btn-primary" onclick="assessCommonLand.deleteLand()"> 删除</div>
                        </div>
                    </div>

                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">

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
                            <div class="btn btn-primary" onclick="assessCommonLand.copyData();"> <i class="fa fa-files-o" aria-hidden="true"></i> 复制</div>
                            <div class="btn btn-primary" onclick="assessCommonLand.pasteAll();"> <i class="fa fa-clipboard" aria-hidden="true"></i>粘贴</div>
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
            </form>
        </div>

        <div class="form-group">
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <div class="row">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号前缀(可以不填,不填那么就是纯数字)</label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                            <input type="text"
                                   placeholder="编号前缀" name="prefixNumber" class="form-control">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">启始编号</label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                            <input type="text"
                                   placeholder="必须数字" name="startNumber" class="form-control">
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">截至编号</label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                            <input type="text"
                                   placeholder="必须数字" name="endNumber" class="form-control">
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">步长(每个权证关联的图片个数)</label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                            <input type="text"
                                   placeholder="必须数字" name="step" class="form-control" value="1">
                        </div>
                    </div>

                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input id="landAttachmentAutomatedWarrantsPDF" name="landAttachmentAutomatedWarrantsPDF" placeholder="pdf自动关联权证附件" class="form-control"
                               type="file">
                        <div id="_landAttachmentAutomatedWarrantsPDF"></div>
                    </div>

                    <div class="x-valid">
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                            <button type="button" class="btn-primary btn" onclick="assessCommonLand.attachmentAutomatedWarrants(this);">关联</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<input type="file" id="ajaxFileUploadLand" name="file" style="display: none;"
       onchange="assessCommonLand.inputFile();">

<input type="file" id="declareRealtyLandCertNewFileId" name="declareRealtyLandCertNewFileId" style="display: none;"
       onchange="assessCommonLand.landImportHandle();" multiple="multiple">


<!-- 土地证信息 -->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
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
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传土地证附件
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
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
            <form  class="form-horizontal">
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
            <form  class="form-horizontal">
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
            <form  class="form-horizontal">
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
                <button type="button" class="btn btn-warning" onclick="assessCommonLand.declareBuildingConstructionPermitRemove()">
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
            <form  class="form-horizontal">
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
