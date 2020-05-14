<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card-body">
    <form name="frm_unitinformation" id="frm_unitinformation" class="form-horizontal"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.unitInformationVo.id}">
        <div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            单位<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <input type="hidden" name="uUseUnit"
                                       value="${projectInfo.unitInformationVo.uUseUnit}">
                                <input type="text" required="required"
                                       placeholder="单位" class="form-control" name="uUseUnitName"
                                       value="${projectInfo.unitInformationVo.uUseUnitName}">
                                <div class="input-group-prepend">
                                    <button type="button" class="btn btn-warning btn-sm " style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="$(this).closest('.input-group').find('input').val('');">清空
                                    </button>
                                </div>
                                <div class="input-group-prepend">
                                    <button type="button" class="btn btn-primary btn-sm " style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="objProject.selectCustomer(this);">选择
                                    </button>
                                </div>
                            </div>
                        </div>
                        <label class="col-sm-1 col-form-label">
                            法定代表人
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="uLegalRepresentative" value="${projectInfo.unitInformationVo.uLegalRepresentative}" class="form-control input-full" >
                        </div>
                        <label class="col-sm-1 col-form-label">
                            证照号
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="uCertificateNumber"
                                   value="${projectInfo.unitInformationVo.uCertificateNumber}"
                                   class="form-control input-full" >
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            单位性质<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <select name="uUnitProperties" class="form-control input-full">
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
                        <label class="col-sm-1 col-form-label">
                            经营范围
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="uScopeOperation"
                                   value="${projectInfo.unitInformationVo.uScopeOperation}"
                                   class="form-control input-full">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            地址
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="uAddress"
                                   value="${projectInfo.unitInformationVo.uAddress}"
                                   class="form-control input-full">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12" id="businessType"
                     style="display: ${empty projectInfo.unitInformationVo.businessType?'none':'block'}">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            业务类型
                        </label>
                        <div class="col-sm-3">
                            <select name="businessType" class="form-control input-full businessType">
                                <c:if test="${projectInfo.unitInformationVo.businessType != null}">
                                    <option value="${projectInfo.unitInformationVo.businessType}"
                                            selected="selected">${projectInfo.unitInformationVo.businessType}</option>
                                </c:if>
                            </select>
                        </div>
                        <label class="col-sm-1 col-form-label">
                            评估类型
                        </label>
                        <div class="col-sm-3">
                            <select name="assessType" class="form-control input-full  assessType">
                                <c:if test="${projectInfo.unitInformationVo.assessType != null}">
                                    <option value="${projectInfo.unitInformationVo.assessType}"
                                            selected="selected">${projectInfo.unitInformationVo.assessType}</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div style="display: none">
        <div class="x_title">
            <h3> 客户经理
                <small>

                </small>
            </h3>
        </div>
        <div class="x_content">
            <p>
                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                        data-toggle="modal" onclick="objProject.unit_information.showContactModel()"
                        href="#divBoxUNIT_INFORMATIONContacts">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                    新增客户经理
                </button>
                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                        onclick="objProject.commonContacts.findCRMContactShow()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                    查询更多
                </button>
            </p>
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
                <h4 class="modal-title">客户经理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>


            <form name="frmUNIT_INFORMATIONContacts" id="frmUNIT_INFORMATIONContacts" class="form-horizontal">

            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="objProject.unit_information.saveContact()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
