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
                    <button type="button" class="btn btn-success" onclick="declareRealtyRealEstateCert.showAddModel()"
                            data-toggle="modal"> 新增
                    </button>
                    <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.editData()"> 编辑
                    </div>
                    <c:if test="${projectPlanDetails.bisRestart ne true}">
                        <div type="button" class="btn btn-primary" onclick="declareRealtyRealEstateCert.deleteData()">
                            删除
                        </div>
                    </c:if>
                </div>

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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传不动产证
                                        </label>
                                        <div class="col-sm-5">
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