<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        项目信息
                        <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
                    </h2>
                    <input type="hidden" value="${projectInfo.id}" id="projectinfoid">
                    <input type="hidden" value="${projectInfo.consignorVo.id}" id="consignorid">
                    <input type="hidden" value="${projectInfo.possessorVo.id}" id="possessorid">
                    <input type="hidden" value="${projectInfo.unitInformationVo.id}" id="unitInformationid">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                        <input type="hidden" name="projectClassId" value="${projectInfo.projectClassId}">
                        <input type="hidden" name="projectTypeId" value="${projectInfo.projectTypeId}">
                        <input type="hidden" name="projectCategoryId" value="${projectInfo.projectCategoryId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="项目名称" id="projectName" name="projectName"
                                           value="${projectInfo.projectName}" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="entrustPurpose" name="entrustPurpose" class="form-control"
                                            onchange="entrustPurposeChange(this);"
                                            required="required">
                                        <option value="">请选择</option>
                                        <c:forEach items="${list_entrustment_purpose}" var="item">
                                            <c:choose>
                                                <c:when test="${item.id == projectInfo.entrustPurpose}">
                                                    <option value="${item.id}" selected="selected">${item.name}</option>
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
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="评估基准日" id="valuationDate"
                                           name="valuationDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的描述</label>
                                <div class="col-sm-11">
                                     <textarea id="remarkEntrustPurpose" name="remarkEntrustPurpose"
                                               class="form-control"
                                               placeholder="委托目的描述">${projectInfo.remarkEntrustPurpose}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="valueType" name="valueType" class="form-control" required="required"
                                            onchange="valueTypeChange(this);">
                                        <option selected="selected" value="">请选择</option>
                                        <c:forEach items="${value_type}" var="item">
                                            <c:choose>
                                                <c:when test="${item.id == projectInfo.valueType}">
                                                    <option value="${item.id}" selected="selected">${item.name}</option>
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
                                <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="urgency" name="urgency" class="form-control" required="required">
                                        <option value="">请选择</option>
                                        <c:forEach items="${project_initiate_urgency}" var="item">
                                            <c:choose>
                                                <c:when test="${item.id==projectInfo.urgency}">
                                                    <option value="${item.id}" selected="selected">${item.name}</option>
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
                                <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="hidden" id="departmentId" name="departmentId"
                                               value="${projectInfo.departmentId}">
                                        <input id='departmentName' class='form-control' required="required"
                                               readonly="readonly" maxlength="200" onclick="selectDepartment();"
                                               value="${projectInfo.departmentName}">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="selectDepartment();" data-toggle="tooltip"
                                                        data-original-title="选择">
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
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价值类型备注</label>
                                <div class="col-sm-11">
                                     <textarea id="remarkValueType" name="remarkValueType"
                                               class="form-control"
                                               placeholder="委托目的描述">${projectInfo.remarkValueType}</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="province" name="province" class="form-control search-select select2"
                                            required="required">
                                        <option value="" name="province">-请选择-</option>
                                        <c:forEach items="${ProvinceList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.areaId == projectInfo.province}">
                                                    <option value="${item.areaId}"
                                                            selected="selected">${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.areaId}">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">

                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县</label>
                                <div class="col-sm-3">
                                    <select id="district" name="district" class="form-control search-select select2">

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理</label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="hidden" id="userAccountManager" name="userAccountManager"
                                               value="${projectInfo.projectMemberVo.userAccountManager}">
                                        <input type="text" class="form-control" readonly="readonly"
                                               value="${projectInfo.projectMemberVo.userAccountManagerName}"
                                               onclick="selectUserAccountManager();"
                                               id="userAccountManagerName" maxlength="200">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectUserAccountManager();">
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
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    <span class="checkbox-inline">
                                        <input type="checkbox" id="userAccountMemberCheckBox">
                                        <label for="userAccountMemberCheckBox">下级分派</label>
                                    </span>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明</label>
                                <div class="col-sm-11">
                                    <textarea id="remarks" name="remarks"
                                              class="form-control" placeholder="项目说明">${projectInfo.remarks}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目成员</label>
                                <div class="col-sm-11">
                                    <input type="hidden" id="userAccountMember" name="userAccountMember"
                                           value="${projectInfo.projectMemberVo.userAccountMember}">
                                    <input type="text" id="userAccountMemberName" class="form-control"
                                           readonly="readonly"
                                           onclick="selectUserAccountMember();"
                                           value="${projectInfo.projectMemberVo.userAccountMemberName}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附件</label>
                                <div class="col-sm-3">
                                    <input id="attachmentProjectInfoId" name="attachmentProjectInfoId"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_attachmentProjectInfoId"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2> 委托人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_consignor" class="form-horizontal" enctype="multipart/form-data">
                        <div class="form-group">
                            <div id="changeType" class="col-sm-3 col-sm-offset-1">
                                <span class="radio-inline">
                                    <input type="radio" required name="csType" id="csType0"
                                           value="1" ${projectInfo.consignorVo.csType != 0?'checked="checked"':''}  >
                                    <label for="csType0">法人</label></span>
                                <span class="radio-inline">
                                    <input type="radio" name="csType" id="csType1"
                                           value="0" ${projectInfo.consignorVo.csType == 0?'checked="checked"':''}  >
                                    <label for="csType1">自然人</label></span>
                            </div>
                        </div>
                        <div id="legal_person">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" id="csEntrustmentUnit" name="csEntrustmentUnit"
                                               placeholder="单位" class="form-control" required="required"
                                               value="${projectInfo.consignorVo.csEntrustmentUnit}">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csLegalRepresentative"
                                               id="csLegalRepresentative" placeholder="法定代表人"
                                               class="form-control" required="required"
                                               value="${projectInfo.consignorVo.csLegalRepresentative}">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        社会统一信用代码<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csSociologyCode" id="csSociologyCode"
                                               value="${projectInfo.consignorVo.csSociologyCode}"
                                               placeholder="社会统一信用代码" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csScopeOperation" id="csScopeOperation"
                                               placeholder="经营范围" class="form-control" required="required"
                                               value="${projectInfo.consignorVo.csScopeOperation}">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位地址<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csAddress" id="csAddress" placeholder="单位地址"
                                               class="form-control" required="required"
                                               value="${projectInfo.consignorVo.csAddress}">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="csUnitProperties" name="csUnitProperties"
                                                required>
                                            <option value="">请选择</option>
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
                        <div id="no_legal_person" style="display: none;">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        姓名<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csName" id="csName" placeholder="姓名"
                                               value="${projectInfo.consignorVo.csName}" class="form-control"
                                               required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证号<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csIdcard" id="csIdcard" placeholder="身份证号"
                                               value="${projectInfo.consignorVo.csIdcard}" class="form-control"
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
                                        <input type="text" name="csAddress" id="csAddress2"
                                               value="${projectInfo.consignorVo.csAddress}" class="form-control">
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
                                    <input type="file" name="csAttachmentProjectEnclosureId"
                                           id="csAttachmentProjectEnclosureId" placeholder="上传附件"
                                           class="form-control" required="required">
                                    <div id="_csAttachmentProjectEnclosureId"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="x_title">
                        <h3> 联系人</h3>
                    </div>
                    <div class="x_content">
                        <button class="btn btn-success" data-toggle="modal" onclick="addContacts(1)">新增联系人</button>
                        <table class="table table-bordered" id="tb_ListA">
                        </table>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <h2> 占有人</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
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
                    <button class="btn btn-success" data-toggle="modal" onclick="addContacts(2)">新增联系人</button>
                    <table class="table table-bordered" id="tb_ListB">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form name="frm_unitinformation" id="frm_unitinformation" class="form-horizontal"
                          enctype="multipart/form-data">
                        <div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input type="hidden" name="uUseUnit" id="uUseUnit" class="form-control"
                                                   required="required"
                                                   value="${projectInfo.unitInformationVo.uUseUnit}">
                                            <input type="text" id="uUseUnitX" readonly="readonly"
                                                   placeholder="单位" class="form-control" onclick="selectCustomer();"
                                                   value="${projectInfo.unitInformationVo.uUseUnitName}">
                                            <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectCustomer();">
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
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表人<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uLegalRepresentative" id="uLegalRepresentative"
                                               value="${projectInfo.unitInformationVo.uLegalRepresentative}"
                                               class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        证照号<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uCertificateNumber" id="uCertificateNumber"
                                               value="${projectInfo.unitInformationVo.uCertificateNumber}"
                                               class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <select name="uUnitProperties" id="uUnitProperties" class="form-control"
                                                required>
                                            <option value="" name="uUnitProperties">请选择</option>
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
                                    <label class="col-sm-1 control-label">
                                        经营范围<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uScopeOperation" id="uScopeOperation"
                                               value="${projectInfo.unitInformationVo.uScopeOperation}"
                                               class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        地址<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uAddress" id="uAddress"
                                               value="${projectInfo.unitInformationVo.uAddress}"
                                               class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="x_title">
                        <h3> 联系人</h3>
                        <div class="clearfix">
                        </div>
                    </div>
                    <div class="x_content">
                        <button class="btn btn-success" data-toggle="modal" onclick="addContacts(3)">新增联系人</button>
                        <table class="table table-bordered" id="tb_ListC">
                            <!-- cerare document add ajax data-->
                        </table>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="projectApply();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="divBoxContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">联系人</h3>
            </div>
            <form id="frmContacts" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <input type="hidden" name="cType" id="cType">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            姓名<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cName" id="cName" placeholder="姓名"
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
                                            <input type="text" name="cDept" id="cDept" placeholder="部门"
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
                                            <input type="text" name="cPhone" id="cPhone" placeholder="号码"
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
                                            <input type="text" name="cEmail" id="cEmail" placeholder="邮箱"
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
                    <button type="button" class="btn btn-primary" onclick="saveContacts()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(function () {
        //---------
        FileUtils.uploadFiles({
            target: "attachmentProjectInfoId",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: ${empty projectInfo?0:projectInfo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: ${empty projectInfo?0:projectInfo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
        //---------
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
        //----------
        FileUtils.uploadFiles({
            target: "csAttachmentProjectEnclosureId",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.InitiateConsignor,
                tableId: ${empty projectInfo.consignorVo?0:projectInfo.consignorVo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "csAttachmentProjectEnclosureId",
            formData: {
                tableName: AssessDBKey.InitiateConsignor,
                tableId: ${empty projectInfo.consignorVo?0:projectInfo.consignorVo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })

    })
</script>
<script type="text/javascript">
    //加载价值类型描述
    function valueTypeChange(_this) {
        AssessCommon.getDataDicInfo($(_this).val(), function (data) {
            if (data) {
                $("#remarkValueType").val(data.remark);
            }
        })
    }

    //加载委托目的描述
    function entrustPurposeChange(_this) {
        AssessCommon.getDataDicInfo($(_this).val(), function (data) {
            if (data) {
                $("#remarkEntrustPurpose").val(data.remark);
            }
        })
    }

    //执业部门
    function selectDepartment() {
        erpDepartment.select({
            onSelected: function (nodes) {
                $("#departmentId").val(nodes[0].id);
                $("#departmentName").val(nodes[0].text);
            }
        });
    }

    // 项目经理
    function selectUserAccountManager() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#userAccountManagerName").val(data.name);
                $("#userAccountManager").val(data.account);
            }
        });
    }

    // 项目成员
    function selectUserAccountMember() {
        var value = formatToUnderline($("#userAccountMemberName").val(), $("#userAccountMember").val());
        erpEmployee.select({
            multi: true,
            value: value,
            onSelected: function (data) {
                $("#userAccountMember").val(data.account);
                $("#userAccountMemberName").val(data.name);
            }
        });
    }

    //选项框  自动校验并且填写
    $(document).ready(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            provinceValue: '${projectInfo.province}',
            cityValue: '${projectInfo.city}',
            districtValue: '${projectInfo.district}'
        })

        $("#changeType input[type='radio'][name='csType']").change(function () {
            if ($(this).val() == 1) {
                $("#changeType input[type='radio'][name='csType'][value='0']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person").hide();
                $("#legal_person").show();
            }
            if ($(this).val() == 0) {
                $("#changeType input[type='radio'][name='csType'][value='1']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person").show();
                $("#legal_person").hide();
            }
        });

        $("#changeType1 input[type='radio'][name='pType']").change(function () {
            var value = $("#changeType :radio:checked").val();
            if ($(this).val() == 1) {
                $("#changeType1 input[type='radio'][name='pType'][value='0']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person1").hide();
                $("#legal_person1").show();

                $("#changeType1 input[type='radio'][name='pType'][value='1']").attr("checked", true);
                $("#changeType1 input[type='radio'][name='pType'][value='0']").removeAttr("checked");
                if ($("#pEntrustmentUnitX").length > 0) {
                    $("#pEntrustmentUnitX").val($("#csEntrustmentUnitX").val());
                }
                $("#pEntrustmentUnit").val($("#csEntrustmentUnit").val());
                $("#pLegalRepresentative").val($("#csLegalRepresentative").val());
                $("#pSociologyCode").val($("#csSociologyCode").val());
                $("#pScopeOperation").val($("#csScopeOperation").val());
                $("#pAddress").val($("#csAddress").val());
                var selectV = $("#csUnitProperties option:selected").val();
                if (selectV != null && selectV != '') {
                    $("#pUnitProperties").val(selectV);
                }
            }
            if ($(this).val() == 0) {
                $("#changeType1 input[type='radio'][name='pType'][value='1']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person1").show();
                $("#legal_person1").hide();

                $("#pName").val($("#csName").val());
                $("#pIdcard").val($("#csIdcard").val());
                $("#pAddress2").val($("#csAddress2").val());
            }
        });
    });

    //获取crm 信息
    function getCRM(id) {
        var info;
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/projectInfo/getCRMList",
            data: "crmId=" + id,
            success: function (msg) {
                info = msg;
                return info;
            }
        });
    }

    function writeCRM(msg, id1, id2, id3, id4) {
        var legalRepresentative = msg.legalRepresentative;//法定代表人
        var address = msg.address;//地址
        var businessScope = msg.businessScope;//经营范围
        var certificateNumber = msg.certificateNumber;//社会统一信用代码
        $("#" + id1).val(legalRepresentative);
        $("#" + id2).val(address);
        $("#" + id3).val(businessScope);
        $("#" + id4).val(certificateNumber);
    }

    //CRM
    if ($("#btn_select_customer").length > 0) {
        document.getElementById("btn_select_customer").onclick = function () {
            crmCustomer.select({
                multi: false,//是否允许多选
                onSelected: function (nodes) {
                    if ($("#csEntrustmentUnit").length > 0) {
                        $("#csEntrustmentUnit").val(nodes[0].id);
                    }
                    if ($("#csEntrustmentUnitX").length > 0) {
                        $("#csEntrustmentUnitX").val(nodes[0].name);
                    }
                    var id = nodes[0].id;
                    loadInitContactsListA(id);
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/projectInfo/getCRMList",
                        data: "crmId=" + id,
                        success: function (msg) {
                            console.log(msg);
                            writeCRM(msg, "csLegalRepresentative", "csAddress", "csScopeOperation", "csSociologyCode");
                        }
                    });
                }
            });
        }
    }

    //选择客户
    function selectCustomer() {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                if ($("#uUseUnitX").length > 0) {
                    $("#uUseUnitX").val(nodes[0].name);
                }
                if ($("#uUseUnit").length > 0) {
                    $("#uUseUnit").val(nodes[0].id);
                }
                var id = nodes[0].id;
                loadInitContactsListC(id);
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/projectInfo/getCRMList",
                    data: "crmId=" + id,
                    success: function (msg) {
                        console.log(msg);
                        writeCRM(msg, "uLegalRepresentative", "uAddress", "uScopeOperation", "uCertificateNumber");
                    }
                });
            }
        });
    }

    var flags = new Array();
    flags[0] = 1, flags[1] = 2, flags[2] = 3;

    //加载联系人列表
    function loadInitContactsListA(id) {
        var cols = [];
        var cPid = "${projectInfo.consignorVo.id}";
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'id', visible: false, title: "id"});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="updateContacts(' + row.id + ',\'tb_ListA\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsA(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        var pid = "${projectInfo.consignorVo.id}";
        $("#tb_ListA").bootstrapTable("destroy");
        if (pid == null || pid == '') {
            TableInit("tb_ListA", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                crmId: id, flag: flags[0], pid: cPid
            }, {
                showColumns: false,
                showRefresh: true,
                search: true
            });
        } else {
            TableInit("tb_ListA", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                flag: flags[0], pid: cPid
            }, {
                showColumns: false,
                showRefresh: true,
                search: true
            });
        }
    }

    function loadInitContactsListB(id) {
        var cols = [];
        var cPid = "${projectInfo.possessorVo.id}";
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'id', visible: false, title: "id"});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="updateContacts(' + row.id + ',\'tb_ListB\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsB(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_ListB").bootstrapTable("destroy");
        if (cPid == null || cPid == '') {
            TableInit("tb_ListB", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                crmId: id, flag: flags[1], pid: cPid
            }, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        } else {
            TableInit("tb_ListB", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                flag: flags[1], pid: cPid
            }, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        }
    }

    function loadInitContactsListC(id) {
        var cols = [];
        var cPid = "${projectInfo.unitInformationVo.id}";
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'id', visible: false, title: "id"});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="updateContacts(' + row.id + ',\'tb_ListC\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsC(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_ListC").bootstrapTable("destroy");
        if (cPid == null || cPid == '') {
            TableInit("tb_ListC", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                crmId: id, flag: flags[2]
            }, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        } else {
            TableInit("tb_ListC", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
                flag: flags[2], pid: cPid
            }, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        }
    }

    //修改专用
    function loadInitContactsList(id, tb_List, flag) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cPhone', title: '电话'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:removeUpdateContacts(' + "'" + id + "','" + tb_List + "','" + flag + "','" + row.id + "'" + ')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("" + tb_List, "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
            pid: id, flag: flag
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //修改专用
    function removeUpdateContacts(pid, tb_List, flag, id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadInitContactsList(pid, tb_List, flag);
                        window.onload;
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    $(function () {//修改专用
        var projectInfo = '${projectInfo}';
        if (projectInfo != null && projectInfo != '') {
            var csType = ${projectInfo.consignorVo.csType}+"";
            var pType = ${projectInfo.possessorVo.pType}+"";
            loadInitContactsList("${projectInfo.consignorVo.id}", "tb_ListA", flags[0]);
            loadInitContactsList("${projectInfo.possessorVo.id}", "tb_ListB", flags[1]);
            loadInitContactsList("${projectInfo.unitInformationVo.id}", "tb_ListC", flags[2]);
        }
    });

    // 显示 联系人 view
    function addContacts(contactsEnum) {
        var str = "";
        <c:forEach items="${InitiateContactsMap}" var="mymap">
        if ('${mymap.key}' == contactsEnum) {
            str = '${mymap.value}';
        }
        </c:forEach>
        $("#frmContacts").clearAll();
        $("#cType").val(contactsEnum);
        $("#cTypeShow").text(str);
        $('#divBoxContacts').modal("show");
    }

    //新增  联系人
    function saveContacts() {
        var data = formParams("frmContacts");//收集参数
        data.cDept = $("#cDept").val();
        data.cName = $("#cName").val();
        data.cPhone = $("#cPhone").val();
        data.cEmail = $("#cEmail").val();
        var cType = $("#cType").val();
        if (cType == 1) {//修改 添加委托联系人
            var consignorid = document.getElementById("consignorid").value;
            if (consignorid != null && consignorid != "") {
                data.cPid = consignorid;
            }
        } else if (cType == 2) {//修改 添加 占有人 联系人
            var possessorid = document.getElementById("possessorid").value;
            if (possessorid != null && possessorid != "") {
                data.cPid = possessorid;
            }
        } else if (cType == 3) {//修改 添加 报告使用单位 联系人
            var unitInformationid = document.getElementById("unitInformationid").value;
            if (unitInformationid != null && unitInformationid != "") {
                data.cPid = unitInformationid;
            }
        }
        if ($("#frmContacts").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#divBoxContacts').modal('hide');
                        var flag = data.cType;
                        if (flag == 1) {
                            loadInitContactsListA();
                        } else if (flag == 2) {
                            loadInitContactsListB();
                        } else if (flag == 3) {
                            loadInitContactsListC();
                        }
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //删除 联系人
    function deteteContactsA(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        //tb_ListA
                        loadInitContactsListA();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    function deteteContactsB(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadInitContactsListB();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    function deteteContactsC(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadInitContactsListC();
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    function params() {
        var data = {};
        var projectInfo = formParams("frm_project_info");//项目信息
        var consignor = formParams("frm_consignor"); //委托人信息
        var possessor = formParams("frm_possessor"); //占有人信息
        var unitinformation = formParams("frm_unitinformation"); //报告使用单位信息
        data.projectInfo = projectInfo;
        data.consignor = consignor;
        data.possessor = possessor;
        data.unitinformation = unitinformation;
        //合并json
        var json = JSON.stringify(data);
        return json;
    }

    function projectApply() {
        //js校验
        if (!$("#frm_project_info").valid()) {
            return false;
        }
        if (!$("#frm_consignor").valid()) {
            return false;
        }
        if (!$("#frm_possessor").valid()) {
            return false;
        }
        if (!$("#frm_unitinformation").valid()) {
            return false;
        }
        if (!hasLinkman('tb_ListA')) {
            Alert('还未填写委托人联系人信息');
            return false;
        }
        if (!hasLinkman('tb_ListB')) {
            Alert('还未填写占有人联系人信息');
            return false;
        }
        if (!hasLinkman('tb_ListC')) {
            Alert('还未填写报告使用单位联系人信息');
            return false;
        }

        var bisNextUser = "0";
        //如果没有设置项目经理则必须先进行下级分派
        if ($("#userAccountMemberCheckBox").is(':checked') || !$("#userAccountManager").val()) {
            bisNextUser = "1";
        }
        var formData = params();
        Loading.progressShow();
        $.ajax({
            type: "POST",
            url: getContextPath() + "/projectInfo/projectApplySubmit",
            data: {
                formData: formData,
                projectinfoid: $("#projectinfoid").val(),
                bisNextUser: bisNextUser
            }, success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    //是否填写了联系人
    function hasLinkman(tbListId) {
        var rows = $("#" + tbListId).bootstrapTable('getData');
        if (rows == null || rows.length <= 0) return false;
        return true;
    }
</script>

