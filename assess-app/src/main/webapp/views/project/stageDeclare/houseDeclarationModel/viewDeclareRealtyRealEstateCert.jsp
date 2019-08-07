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
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">批量导入数据
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

<!-- 经济指标 add -->
<div id="boxDeclareEconomicIndicatorsHeadRealtyRealEstate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">经济指标</h3>
            </div>
            <form id="frmDeclareEconomicIndicatorsHeadRealtyRealEstate" class="form-horizontal" style="display: block;margin-bottom: 0px;padding-bottom: 0px;">
                <input type="hidden" name="id">
                <input type="hidden" name="centerId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form id="frmDeclareEconomicIndicatorsContentRealtyRealEstate" class="form-horizontal" style="display: block;margin-top: -4px;padding-top: -4px;">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <form>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <input type="button" class="btn btn-warning" value="删除"  onclick="declareRealtyRealEstateCert.deleteDeclareEconomicIndicatorsCenter()">
                    <button type="button" class="btn btn-primary"
                            onclick="declareRealtyRealEstateCert.saveDeclareEconomicIndicatorsData(this)">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
