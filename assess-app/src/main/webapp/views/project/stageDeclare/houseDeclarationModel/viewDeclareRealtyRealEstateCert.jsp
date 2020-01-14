<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>
            不动产证
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">
                <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="declareRealtyRealEstateCert.showAddModel()"
                                data-toggle="modal"> 新增
                        </button>
                        <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.editData()"> 编辑
                        </div>
                        <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.deleteData()">
                            删除
                        </div>
                    </div>
                </div>

                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入不动产证
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftRealEstateOwnershipCertificate)">下载模板</a>
                            </li>
                            <li><a class="btn btn-default"
                                   onclick="$('#ajaxFileUploadRealEstate').val('').trigger('click')">导入</a></li>
                        </ul>
                    </div>
                </div>


                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                    <div class="btn-group">
                        <div class="btn btn-primary" onclick="declareRealtyRealEstateCert.copyData();"> <i class="fa fa-files-o" aria-hidden="true"></i> 复制</div>
                        <div class="btn btn-primary" onclick="declareRealtyRealEstateCert.pasteAll();"> <i class="fa fa-clipboard" aria-hidden="true"></i>粘贴</div>
                    </div>
                </div>


                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                    <div class="input-group" id="realtyRealEstateHandleInputGroup">
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
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <div class="row">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号前缀</label>
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
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">步长</label>
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text" placeholder="每个权证关联的图片个数" name="step" class="form-control" value="">
                            </div>
                        </div>

                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <input id="RealEstateAttachmentAutomatedWarrantsPDF" name="RealEstateAttachmentAutomatedWarrantsPDF" placeholder="pdf自动关联权证附件" class="form-control"
                                   type="file">
                            <div id="_RealEstateAttachmentAutomatedWarrantsPDF"></div>
                        </div>

                        <div class="x-valid">
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <button type="button" class="btn-primary btn" onclick="declareRealtyRealEstateCert.attachmentAutomatedWarrants(this);">关联</button>
                            </div>
                        </div>
                    </div>
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
       onchange="declareRealtyRealEstateCert.inputFile();">

<input type="file" id="declareRealtyRealEstateCertFileId" name="declareRealtyRealEstateCertFileId"
       style="display: none;"
       onchange="declareRealtyRealEstateCert.enclosureFun();" multiple="multiple">
<!-- 不动产 add -->
<div id="boxDeclareRealtyRealEstateCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证信息</h3>
            </div>
            <form id="frmDeclareRealtyRealEstateCert" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            上传不动产证
                                        </label>
                                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                            <input id="declareRealtyRealEstateCertNewFileId"
                                                   name="declareRealtyRealEstateCertNewFileId"
                                                   required="required" placeholder="上传不动产证" class="form-control"
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

<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
                                            <input id="declareBuildingPermitFileId2"
                                                   name="declareBuildingPermitFileId2"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingPermitFileId2"></div>
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
                <button type="button" class="btn btn-warning" onclick="declareRealtyRealEstateCert.declareBuildingPermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="declareRealtyRealEstateCert.declareBuildingPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
                                            <input id="declareLandUsePermitFileId2"
                                                   name="declareLandUsePermitFileId2"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareLandUsePermitFileId2"></div>
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
                <button type="button" class="btn btn-warning" onclick="declareRealtyRealEstateCert.declareLandUsePermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="declareRealtyRealEstateCert.declareLandUsePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
                                            <input id="declareBuildingConstructionPermitFileId2"
                                                   name="declareBuildingConstructionPermitFileId2"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingConstructionPermitFileId2"></div>
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
                <button type="button" class="btn btn-warning" onclick="declareRealtyRealEstateCert.declareBuildingConstructionPermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="declareRealtyRealEstateCert.declareBuildingConstructionPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitRealtyRealBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
                                            <input id="declarePreSalePermitFileId2"
                                                   name="declarePreSalePermitFileId2"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declarePreSalePermitFileId2"></div>
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
                <button type="button" class="btn btn-warning" onclick="declareRealtyRealEstateCert.declarePreSalePermitRemove()">
                    删除
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="declareRealtyRealEstateCert.declarePreSalePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


