<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form name="frm_unitinformation" id="frm_unitinformation" class="form-horizontal"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.unitInformationVo.id}">
        <div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        单位<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                        <div class="input-group">
                            <input type="hidden" name="uUseUnit"
                                   value="${projectInfo.unitInformationVo.uUseUnit}">
                            <input type="text" readonly="readonly" required="required"
                                   placeholder="单位" class="form-control" name="uUseUnitName"
                                   value="${projectInfo.unitInformationVo.uUseUnitName}"
                                   onclick="objProject.selectCustomer(this)">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-default docs-tooltip"
                                        data-toggle="tooltip"
                                        data-original-title="选择"
                                        onclick="objProject.selectCustomer(this)">
                                <i class="fa fa-search"></i>
                                </button>
                                <button type="button" class="btn btn-default docs-tooltip"
                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                        data-toggle="tooltip" data-original-title="清除">
                                <i class="fa fa-trash-o"></i>
                                </button>
                            </span>
                        </div>
                    </div>

                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                        是否写入crm<input type="checkbox" name="infoWrite" value="true"
                            <c:if test="${!empty projectInfo.unitInformationVo.infoWrite}">
                                checked
                            </c:if>
                        >
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        法定代表人<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="uLegalRepresentative"
                               value="${projectInfo.unitInformationVo.uLegalRepresentative}"
                               class="form-control" required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        证照号<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="uCertificateNumber"
                               value="${projectInfo.unitInformationVo.uCertificateNumber}"
                               class="form-control" required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        单位性质<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select name="uUnitProperties" class="form-control"
                                required>
                            <c:forEach items="${ProjectAFFILIATED}" var="item">
                                <c:choose>
                                    <c:when test="${item.id == projectInfo.unitInformationVo.uUnitProperties}">
                                        <option value="${item.id}"
                                                selected="selected">${item.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.id}">${item.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        经营范围<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="uScopeOperation"
                               value="${projectInfo.unitInformationVo.uScopeOperation}"
                               class="form-control" required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        地址<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="uAddress"
                               value="${projectInfo.unitInformationVo.uAddress}"
                               class="form-control" required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid" id="businessType" style="display: ${empty projectInfo.unitInformationVo.businessType?'none':'block'}">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        业务类型<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select name="businessType" required="required" class="form-control businessType">
                            <c:if test="${projectInfo.unitInformationVo.businessType != null}">
                                <option value="${projectInfo.unitInformationVo.businessType}" selected="selected">${projectInfo.unitInformationVo.businessType}</option>
                            </c:if>
                        </select>
                    </div>
                </div>

                <div class="x-valid" id="assessType" style="display: ${empty projectInfo.unitInformationVo.assessType?'none':'block'}">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        评估类型<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select name="assessType" required="required" class="form-control  assessType">
                            <c:if test="${projectInfo.unitInformationVo.assessType != null}">
                                <option value="${projectInfo.unitInformationVo.assessType}" selected="selected">${projectInfo.unitInformationVo.assessType}</option>
                            </c:if>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div style="display: none">
        <div class="x_title">
            <h3> 联系人
                <small>

                </small>
            </h3>
            <div class="clearfix">
            </div>
        </div>
        <div class="x_content">
            <button class="btn btn-success" data-toggle="modal"
                    onclick="objProject.unit_information.showContactModel()">新增联系人
            </button>
            <button class="btn btn-primary" data-toggle="modal"
                    onclick="objProject.commonContacts.findCRMContactShow()">
                查询更多<i class="fa fa-search"></i>
            </button>
            <table class="table table-bordered" id="tb_ListUNIT_INFORMATION">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<div id="divBoxUNIT_INFORMATIONContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">联系人</h3>
            </div>
            <form id="frmUNIT_INFORMATIONContacts" class="form-horizontal">
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="objProject.unit_information.saveContact()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
