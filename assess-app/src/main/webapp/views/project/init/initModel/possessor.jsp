<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/16
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="x_content">
    <form id="frm_possessor" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
            <div id="changeType1" class="col-sm-3 col-sm-offset-1">
                                <span class="radio-inline">
                                    <input type="radio" required name="pType" id="pType1"
                                           value="1" ${projectInfo.possessorVo.pType != 0?'checked="checked"':''}  >
                                    <label for="pType1">法人</label></span>
                <span class="radio-inline">
                                    <input type="radio" name="pType" id="pType0"
                                           value="0" ${projectInfo.possessorVo.pType == 0?'checked="checked"':''}  >
                                    <label for="pType0">自然人</label></span>
            </div>
        </div>
        <div id="legal_person1">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        单位<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pEntrustmentUnit" id="pEntrustmentUnit"
                               class="form-control" required="required" placeholder="单位"
                               value="${projectInfo.possessorVo.pEntrustmentUnitName}">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        法定代表<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pLegalRepresentative"
                               value="${projectInfo.possessorVo.pLegalRepresentative}"
                               id="pLegalRepresentative" class="form-control" required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        社会统一信用代码<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pSociologyCode" id="pSociologyCode"
                               value="${projectInfo.possessorVo.pSociologyCode}"
                               class="form-control" required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        经营范围<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pScopeOperation" id="pScopeOperation"
                               value="${projectInfo.possessorVo.pScopeOperation}"
                               class="form-control" required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        单位地址<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pAddress" id="pAddress"
                               value="${projectInfo.possessorVo.pAddress}" class="form-control"
                               required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        单位性质<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <select class="form-control" id="pUnitProperties" name="pUnitProperties"
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

        <div id="no_legal_person1" style="display: none;">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        姓名<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pName" id="pName"
                               value="${projectInfo.possessorVo.pName}" class="form-control"
                               required="required">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        身份证号<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pIdcard" id="pIdcard"
                               value="${projectInfo.possessorVo.pIdcard}" class="form-control"
                               required="required">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        住址
                    </label>
                    <div class="col-sm-3">
                        <input type="text" name="pAddress" id="pAddress2"
                               value="${projectInfo.possessorVo.pAddress}" class="form-control">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <input type="file" name="pAttachmentProjectEnclosureId"
                           id="pAttachmentProjectEnclosureId" placeholder="上传附件" class="form-control"
                           required="required">
                    <div id="_pAttachmentProjectEnclosureId"></div>
                </div>
            </div>
        </div>
    </form>
</div>



<div class="x_title">
    <h3> 联系人</h3>
    <div class="clearfix"></div>
</div>
<div class="x_content">
    <button class="btn btn-success" data-toggle="modal" onclick="Contacts.prototype.POSSESSOR().showModel()">新增联系人</button>
    <table class="table table-bordered" id="tb_ListPossessor">
        <!-- cerare document add ajax data-->
    </table>
</div>
</body>
<div id="divBoxPossessorContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">联系人</h3>
            </div>
            <form id="frmPossessorContacts" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            姓名<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cName"  placeholder="姓名"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            部门<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cDept" placeholder="部门"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            号码<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cPhone" placeholder="号码"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            邮箱
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cEmail" placeholder="邮箱"
                                                   class="form-control">
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
                    <button type="button" class="btn btn-primary" onclick="Contacts.prototype.POSSESSOR().save();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        FileUtils.uploadFiles({
            target: "pAttachmentProjectEnclosureId",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.InitiatePossessor,
                tableId: ${empty projectInfo.possessorVo?0:projectInfo.possessorVo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "pAttachmentProjectEnclosureId",
            formData: {
                tableName: AssessDBKey.InitiatePossessor,
                tableId: ${empty projectInfo.possessorVo?0:projectInfo.possessorVo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    });
</script>
</html>
