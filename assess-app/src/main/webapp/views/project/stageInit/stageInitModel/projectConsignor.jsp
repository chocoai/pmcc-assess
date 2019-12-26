<%--
  委托人
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form id="frm_consignor" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.consignorVo.id}">
        <div class="form-group">
            <div class="x-valid">
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3   col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1">
                    <span class="radio-inline">
                        <input type="radio" required name="csType" id="csType0"
                               value="1" ${projectInfo.consignorVo.csType == 1?'checked="checked"':''}  >
                        <label for="csType0">法人</label>
                    </span>
                    <span class="radio-inline">
                        <input type="radio" name="csType" id="csType1"
                               value="0" ${projectInfo.consignorVo.csType == 0?'checked="checked"':''}  >
                        <label for="csType1">自然人</label>
                    </span>
                </div>
            </div>
        </div>
        <div id="consignor_tab_unit">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        单位<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csEntrustmentUnit"
                               placeholder="单位" class="form-control" required="required"
                               value="${projectInfo.consignorVo.csEntrustmentUnit}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        法定代表<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csLegalRepresentative" placeholder="法定代表人"
                               class="form-control" required="required"
                               value="${projectInfo.consignorVo.csLegalRepresentative}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        社会统一信用代码<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csSociologyCode"
                               value="${projectInfo.consignorVo.csSociologyCode}"
                               placeholder="社会统一信用代码" class="form-control"  name="number" required="required">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        经营范围<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csScopeOperation"
                               placeholder="经营范围" class="form-control" required="required"
                               value="${projectInfo.consignorVo.csScopeOperation}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        单位地址<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csAddress" placeholder="单位地址"
                               class="form-control" required="required"
                               value="${projectInfo.consignorVo.csAddress}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        单位性质<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <select class="form-control" name="csUnitProperties"
                                required>
                            <c:forEach items="${ProjectAFFILIATED}" var="item">
                                <c:choose>
                                    <c:when test="${item.id == projectInfo.consignorVo.csUnitProperties}">
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
        <div id="consignor_tab" style="display: none;">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        姓名<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csName" placeholder="姓名"
                               value="${projectInfo.consignorVo.csName}" class="form-control"
                               required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        身份证号<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csIdcard" placeholder="身份证号"
                               value="${projectInfo.consignorVo.csIdcard}" class="form-control"
                               required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        住址<span class="symbol required"></span>
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" name="csAddress"
                               value="${projectInfo.consignorVo.csAddress}" class="form-control">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    附件
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="file" name="csAttachmentProjectEnclosureId"
                           id="csAttachmentProjectEnclosureId" placeholder="上传附件"
                           class="form-control" required="required">
                    <div id="_csAttachmentProjectEnclosureId"></div>
                </div>
            </div>
        </div>
    </form>
    <div style="display: none">
        <div class="x_title">
            <h3> 联系人</h3>
        </div>
        <div class="x_content">
            <button class="btn btn-success" data-toggle="modal" onclick="objProject.consignor.showContactModel()">新增联系人
            </button>
            <table class="table table-bordered" id="tb_ListConsignor">
            </table>
        </div>
    </div>
</div>

<div id="divBoxConsignorContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">联系人</h3>
            </div>
            <form name="frmConsignorContacts" id="frmConsignorContacts" class="form-horizontal">
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="objProject.consignor.saveContact()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
