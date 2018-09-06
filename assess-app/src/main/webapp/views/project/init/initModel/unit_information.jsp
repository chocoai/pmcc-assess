<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/16
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
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
                            <input type="hidden" name="uUseUnit" id="uUseUnit" value="${projectInfo.unitInformationVo.uUseUnit}">
                            <input type="text" id="uUseUnitX" readonly="readonly" required
                                   placeholder="单位" class="form-control" onclick="UNIT_INFORMATION.prototype.selectCustomer();"
                                   value="${projectInfo.unitInformationVo.uUseUnitName}">
                            <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="UNIT_INFORMATION.prototype.selectCustomer();">
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
        <button class="btn btn-success" data-toggle="modal" onclick="Contacts.prototype.UNIT_INFORMATION().showModel();">新增联系人</button>
        <button type="button" class="btn btn-default docs-tooltip" onclick="Contacts.prototype.UNIT_INFORMATION().crmContacts.showModel();">
            查询crm中联系人<i class="fa fa-search"></i>
        </button>
        <table class="table table-bordered" id="tb_ListUNIT_INFORMATION">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
</body>

<div id="divBoxCRMContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">CRM联系人</h3>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class="col-sm-6">
                                            <input type="text" name="name" placeholder="联系人名字"
                                                   class="form-control" >
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <div class="col-sm-6">
                                            <input type="button" onclick="Contacts.prototype.UNIT_INFORMATION().crmContacts.findCRMContacts();" class="btn btn-success" value="查询">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <table class="table table-bordered" id="tb_ListCRMContacts">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>

                                <div class="form-group">
                                    <ul class="pager">
                                        <li><a href="#" onclick="Contacts.prototype.UNIT_INFORMATION().crmContacts.previous()">上一页</a></li>
                                        <li><span class="label label-info"><font color="black">当前页数:<div class="pageIndex">1</div> </font></span></li>
                                        <li><a href="#" onclick="Contacts.prototype.UNIT_INFORMATION().crmContacts.next()">下一页</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxUNIT_INFORMATIONContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">联系人</h3>
            </div>
            <form id="frmUNIT_INFORMATIONContacts" class="form-horizontal">
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
                                            电话号码<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cPhone" data-rule-number='true' name="number" placeholder="号码（请输入数字）"
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
                    <button type="button" class="btn btn-primary" onclick="Contacts.prototype.UNIT_INFORMATION().save();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</html>
