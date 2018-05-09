<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
    <script src="${pageContext.request.contextPath}/excludes/assets/plugins/laydate/laydate.js" type="text/javascript"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2> 项目信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
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
                                <label class="col-sm-1 control-label">大类<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="projectClassId" name="projectClassId" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                        <c:forEach items="${listClass_assess}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="entrustPurpose" name="entrustPurpose" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                        <c:forEach items="${list_entrustment_purpose}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="评估基准日" id="completeDateStart" name="completeDateStart" data-date-format="yyyy-mm-dd"
                                           value="${projectInfo.projectName}" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="province" name="province" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                        <c:forEach items="${ProvinceList}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="district" name="district" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="urgency" name="urgency" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                        <c:forEach items="${project_initiate_urgency}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="valueType" name="valueType" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                        <c:forEach items="${value_type}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="departmentId" name="departmentId" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="项目说明" id="remarks" name="remarks" class="form-control">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="userAccountManager" name="userAccountManager" class="form-control" required="required">
                                        <option selected="selected" value="0">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">上传附件<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input id="attachmentProjectInfoId" name="attachmentProjectInfoId" required="required" placeholder="上传附件"  class="form-control" type="file">
                                    <div id="_attachmentProjectInfoId"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_consignor" class="form-horizontal" enctype="multipart/form-data">
                        <div id="changeType">
                            法人<input type="radio" name="csType" value="1" checked="checked">
                            自然人<input type="radio" name="csType" value="0" >
                        </div>
                        <div id="legal_person" class="panel-body">

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托单位
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csEntrustmentUnit" id="csEntrustmentUnit" class="form-control" required="required">
                                        <span class="input-group-btn">
                                          <button type="button" id="btn_select_customer" class="btn btn-primary">选择</button>
                                        </span>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csLegalRepresentative" id="csLegalRepresentative" placeholder="法定代表人" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        社会统一信用代码
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csSociologyCode" id="csSociologyCode" placeholder="社会统一信用代码" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csScopeOperation" id="csScopeOperation" placeholder="经营范围" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位地址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csAddress" id="csAddress" placeholder="单位地址" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="csUnitProperties" name="csUnitProperties">
                                            <option>请选择</option>
                                            <c:forEach items="${InitiateAFFILIATEDMap}" var="mymap">
                                                <option value="${mymap.key}">${mymap.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证附件
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="file" name="csAttachmentProjectEnclosureId" id="csAttachmentProjectEnclosureId" placeholder="上传附件" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="no_legal_person">
                            <div class="form-group">

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托姓名
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csName" id="csName" placeholder="委托姓名" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证号
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csIdcard" id="csIdcard" placeholder="身份证号" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托住址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csAddress" id="csAddress2" placeholder="委托住址" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证附件
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="file" name="csAttachmentProjectEnclosureId_" id="csAttachmentProjectEnclosureId_" placeholder="上传附件" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人联系人</h2>
                    <button class="btn btn-success" data-toggle="modal" onclick="addContacts()">新增联系人</button>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_ListA">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_possessor" class="form-horizontal" enctype="multipart/form-data">
                        <div id="changeType1">
                            法人<input type="radio" name="pType" value="1" checked="checked">
                            自然人<input type="radio" name="pType" value="0" >
                        </div>
                        <div id="legal_person1" class="panel-body">

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pEntrustmentUnit" id="pEntrustmentUnit" class="form-control" required="required">
                                        <span class="input-group-btn">
                                          <button type="button" id="btn_select_customer1" class="btn btn-primary">选择</button>
                                        </span>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位法定代表
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pLegalRepresentative" id="pLegalRepresentative" placeholder="占有单位法定代表" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        社会统一信用代码
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pSociologyCode" id="pSociologyCode" placeholder="社会统一信用代码" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pScopeOperation" id="pScopeOperation" placeholder="经营范围" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位地址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pAddress" id="pAddress" placeholder="占有单位地址" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="pUnitProperties" name="pUnitProperties">
                                            <option>请选择</option>
                                            <c:forEach items="${InitiateAFFILIATEDMap}" var="mymap">
                                                <option value="${mymap.key}">${mymap.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证附件
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="file" name="pAttachmentProjectEnclosureId" id="pAttachmentProjectEnclosureId" placeholder="上传附件" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="no_legal_person1">
                            <div class="form-group">

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有人姓名
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pName" id="pName" placeholder="占有人姓名" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证号
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pIdcard" id="pIdcard" placeholder="身份证号" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有人住址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="pAddress" id="pAddress2" placeholder="占有人住址" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证附件
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="file" name="pAttachmentProjectEnclosureId_" id="pAttachmentProjectEnclosureId_" placeholder="上传附件" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人联系人</h2>
                    <button class="btn btn-success" data-toggle="modal" onclick="addContacts()">新增联系人</button>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_ListB">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form name="frm_unitinformation" id="frm_unitinformation" class="form-horizontal" enctype="multipart/form-data">
                        <div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告使用单位
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uUseUnit" id="uUseUnit"  class="form-control" required="required">
                                        <span class="input-group-btn">
                                          <button type="button" id="btn_select_customer2" class="btn btn-primary">选择</button>
                                        </span>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表人
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uLegalRepresentative" id="uLegalRepresentative" placeholder="法定代表人" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        证照号
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uCertificateNumber" id="uCertificateNumber" placeholder="证照号" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="uUnitProperties" name="uUnitProperties">
                                            <option>请选择</option>
                                            <c:forEach items="${InitiateAFFILIATEDMap}" var="mymap">
                                                <option value="${mymap.key}">${mymap.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uScopeOperation" id="uScopeOperation" placeholder="经营范围" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        地址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="uAddress" id="uAddress" placeholder="地址" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位联系人</h2>
                    <div class="clearfix">
                        <button class="btn btn-success" data-toggle="modal" onclick="addContacts()">新增联系人</button>
                    </div>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_ListC">
                        <!-- cerare document add ajax data-->
                    </table>
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
<div id="divBoxContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">联系人</h4>
            </div>
            <form id="frmContacts" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            姓名
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cName" id="cName" placeholder="姓名" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            部门
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cDept" id="cDept" placeholder="部门" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            号码
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cPhone" id="cPhone" placeholder="号码" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            邮箱
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cEmail" id="cEmail" placeholder="邮箱" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="cType" id="cType" required="required">
                                                <option>请选择</option>
                                                <c:forEach items="${InitiateContactsMap}" var="mymap">
                                                    <option value="${mymap.key}">${mymap.value}</option>
                                                </c:forEach>
                                            </select>
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
<script>
    $(function () {
        FileUtils.uploadFiles({
            target: "attachmentProjectInfoId",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_info",
                tableId: 0,
                fieldsName: "attachmentProjectInfoId",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: "tb_project_info",
                tableId: 0,
                fieldsName: "attachmentProjectInfoId",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    })

</script>
<script type="text/javascript">
    /*省 市 县 js*/
    function tableNameA() {
        $("#province").change(function () {//监听 选择的省份
            //检测  然后操作
            removeChild();//删除市
            removeChild_district();//删除县
            var selected =$(this).children('option:selected').val();//获取到省
            var data = "pid="+selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/getAreaList",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    appendChildElement(result);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }
    tableNameA();
    function tableName_district() {
        $("#city").change(function () {//监听 选择的城市
            //检测  然后操作
            removeChild_district();
            var selected =$(this).children('option:selected').val();//获取到城市
            var data = "pid="+selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/getAreaList",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    appendChildElement_district(result);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }
    tableName_district();
    function removeChild() { //删除市
        var optionLen = $("#city option").size();
        if (optionLen>2){
            $("#city option").remove();//当大于2时 应该是已经选择一次了 所以删除元素
        }
    }
    function removeChild_district() {//删除县或者区
        var optionLen = $("#district option").size();
        if (optionLen>2){
            $("#district option").remove();//当大于2时 应该是已经选择一次了 所以删除元素
        }
    }
    function appendChildElement(item) {//市添加
        var TableField = $("#city");
        var TableFieldElement = document.getElementById("city");
        var len = item.length;
        for (var i = 0 ; i < len;i++){
            var optionLen = $("#city option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value",item[i].areaId);
            fieldElment.appendChild(document.createTextNode(item[i].name));
            TableFieldElement.appendChild(fieldElment);

        }
    }
    function appendChildElement_district(item) {//县或者区域添加
        var TableField = $("#district");
        var TableFieldElement = document.getElementById("district");
        var len = item.length;
        for (var i = 0 ; i < len;i++){
            var optionLen = $("#district option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value",item[i].id);
            fieldElment.appendChild(document.createTextNode(item[i].name));
            TableFieldElement.appendChild(fieldElment);

        }
    }
    //执业部门
    var departmentId = document.getElementById("departmentId");
    departmentId.onclick = function () {
        erpDepartment.select({
            onSelected:function (nodes) {
                departmentId.removeChild(departmentId.firstChild);
                var fieldElment = document.createElement("option");
                fieldElment.setAttribute("value",nodes[0].id);
                fieldElment.setAttribute("selected","selected");
                fieldElment.appendChild(document.createTextNode(nodes[0].text));
                departmentId.appendChild(fieldElment);
            }
        });
    }
    // 项目经理
    var userAccountManager = document.getElementById("userAccountManager");
    userAccountManager.onclick = function () {
        erpEmployee.select({
            onSelected:function (data) {
                userAccountManager.removeChild(userAccountManager.firstChild);
                var fieldElment = document.createElement("option");
                fieldElment.setAttribute("value",data.account);
                fieldElment.setAttribute("selected","selected");
                fieldElment.appendChild(document.createTextNode(data.name));
                userAccountManager.appendChild(fieldElment);
            }
        });
    }
    //日期控件
    laydate.render({
        elem: '#completeDateStart' //指定元素 id
    });
    //选项框
    $(document).ready(function () {
        $("#no_legal_person").hide();
        $("#changeType input[type='radio'][name='csType']").change(function () {
            if ($(this).val()==1){
                $("#no_legal_person").hide();
                $("#legal_person").show();
            }
            if ($(this).val()==0){
                $("#no_legal_person").show();
                $("#legal_person").hide();
            }
        });
        $("#no_legal_person1").hide();
        $("#changeType1 input[type='radio'][name='pType']").change(function () {
            if ($(this).val()==1){
                $("#no_legal_person1").hide();
                $("#legal_person1").show();
            }
            if ($(this).val()==0){
                $("#no_legal_person1").show();
                $("#legal_person1").hide();
            }
        });
    });
    //CRM
    document.getElementById("btn_select_customer").onclick = function () {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                console.log(nodes);
                console.log(nodes[0].name);
                $("#csEntrustmentUnit").val(nodes[0].name);
                var id = nodes[0].id ;
                loadInitContactsListA(id);
            }
        });
    }

    document.getElementById("btn_select_customer1").onclick = function () {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                console.log(nodes);
                $("#pEntrustmentUnit").val(nodes[0].name);
                var id = nodes[0].id ;
                loadInitContactsListB(id);
            }
        });
    }

    document.getElementById("btn_select_customer2").onclick = function () {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                console.info(nodes);
                $("#uUseUnit").val(nodes[0].name);
                var id = nodes[0].id ;
                loadInitContactsListC(id);
            }
        });
    }

    var flags = new Array();
    flags[0] = 1,flags[1] = 2,flags[2] = 3;
    //加载联系人列表
    function loadInitContactsListA(id) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsA(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_ListA", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols,{
            crmId: id,flag:flags[0]}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }
    function loadInitContactsListB(id) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsB(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_ListB", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols,{
            crmId: id,flag:flags[1]}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }
    function loadInitContactsListC(id) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deteteContactsC(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_ListC", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols,{
            crmId: id,flag:flags[2]}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    // 显示 联系人 view
    function addContacts() {
        $("#frmContacts").clearAll();
        $('#divBoxContacts').modal("show");
    }
    //新增  联系人
    function saveContacts() {
        var data = formParams("frmContacts");//收集参数
        data.cDept = $("#cDept").val();
        data.cName = $("#cName").val();
        data.cPhone = $("#cPhone").val();
        data.cEmail = $("#cEmail").val();
        data.cType = $("#cType option:selected").val();
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
                        if (flag==1){
                            loadInitContactsListA();
                        }else if (flag==2){
                            loadInitContactsListB();
                        }else if (flag==3){
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

    var json = "";
    function params() {
        var data = {};
        var projectInfo = formParams("frm_project_info");//项目信息
        var consignor = formParams("frm_consignor"); //委托人信息
        var possessor = formParams("frm_possessor"); //占有人信息
        var unitinformation = formParams("frm_unitinformation"); //报告使用单位信息
        data.projectInfo=projectInfo;
        data.consignor=consignor;
        data.possessor=possessor;
        data.unitinformation=unitinformation;
        //手动收集unitinformation的数据
        console.info(data);
        json = JSON.stringify(data);
        console.info(json);
    }
    function projectApply() {
        Loading.progressShow();
        params();
        $.ajax({
            type: "POST",
            url: getContextPath() + "/projectInfo/projectApplySubmit",
            data: "formData="+json,
            success: function(result){
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
</script>

