<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 占有人 -->
<div class="card-body">
    <form id="frm_possessor" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.possessorVo.id}">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="col-sm-10">
                       <span class="radio-inline">
                                    <input type="radio" required name="pType" id="pType1"
                                           value="1" ${projectInfo.possessorVo.pType == 1?'checked="checked"':''}  >
                                    <label for="pType1">法人</label></span>
                    <span class="radio-inline">
                                    <input type="radio" name="pType" id="pType0"
                                           value="0" ${projectInfo.possessorVo.pType == 0?'checked="checked"':''}  >
                                    <label for="pType0">自然人</label></span>
                </div>
            </div>
        </div>

        <div id="possessor_tab_unit">
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            单位<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pEntrustmentUnit"
                                   class="form-control input-full" required="required" placeholder="单位"
                                   value="${projectInfo.possessorVo.pEntrustmentUnit}">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            法定代表<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pLegalRepresentative"
                                   value="${projectInfo.possessorVo.pLegalRepresentative}" class="form-control input-full" required="required">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            社会统一信用代码<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pSociologyCode"
                                   value="${projectInfo.possessorVo.pSociologyCode}"
                                   class="form-control input-full"  name="number" required="required">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            经营范围<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pScopeOperation"
                                   value="${projectInfo.possessorVo.pScopeOperation}"
                                   class="form-control input-full" required="required">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            单位地址<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pAddress"
                                   value="${projectInfo.possessorVo.pAddress}" class="form-control input-full"
                                   required="required">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            单位性质<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <select class="form-control input-full" name="pUnitProperties"
                                    required>
                                <option value="">请选择</option>
                                <c:forEach items="${ProjectAFFILIATED}" var="item">
                                    <c:choose>
                                        <c:when test="${item.id == projectInfo.possessorVo.pUnitProperties}">
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
                </div>
            </div>
        </div>

        <div id="possessor_tab" style="display: none;">
            <div class="row form-group">
                <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 col-form-label">
                            姓名<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pName"
                                   value="${projectInfo.possessorVo.pName}" class="form-control input-full"
                                   required="required">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            身份证号<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pIdcard"
                                   value="${projectInfo.possessorVo.pIdcard}" class="form-control input-full"
                                   required="required">
                        </div>
                        <label class="col-sm-1 col-form-label">
                            住址<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="pAddress"
                                   value="${projectInfo.possessorVo.pAddress}" class="form-control input-full">
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        附件
                    </label>
                    <div class="col-sm-3">
                        <input type="file" name="pAttachmentProjectEnclosureId"
                               id="pAttachmentProjectEnclosureId" placeholder="上传附件" class="form-control input-full"
                               required="required">
                        <div id="_pAttachmentProjectEnclosureId"></div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <div style="display: none">
        <div class="x_title">
            <h3> 联系人</h3>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <p>
                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                        data-toggle="modal" onclick="objProject.possessor.showContactModel()"
                        href="#divBoxPossessorContacts">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                    新增联系人
                </button>
            </p>
            <table class="table table-bordered" id="tb_ListPossessor">
            </table>
        </div>
    </div>
</div>

<div id="divBoxPossessorContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">联系人</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>


            <form name="frmPossessorContacts" id="frmPossessorContacts" class="form-horizontal">

            </form>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="objProject.possessor.saveContact()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



