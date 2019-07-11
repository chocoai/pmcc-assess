<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn_project" %>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>
            项目信息
            <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称</label>
                    <div class=" col-xs-7  col-sm-7  col-md-7  col-lg-7 ">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">紧急程度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.urgencyName}</label>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的类别</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.entrustAimTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的描述</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.remarkEntrustPurpose}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价值类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.valueTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估基准日</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                    pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执业部门</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.departmentName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估范围</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.propertyScopeName}</label></div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估包括</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.scopeInclude}</label></div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估不包括</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.scopeNotInclude}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目经理</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目成员</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">贷款类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.loanTypeName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目合同</label>
                    <c:if test="${!empty projectInfo.contractId}">
                        <c:forEach var="item" items="${projectInfo.contractList}">
                            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <label class="form-control">
                                    <a href="${sysUrl}/pmcc-contract/contractCurrency/details/${item.key}"
                                       target="_blank">${item.value}</a>
                                </label>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty projectInfo.contractId}">
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <div class="input-group">
                                <input type="hidden" name="contractId" value="${projectInfo.contractId}">
                                <input type="text" class="form-control" readonly="readonly" name="contractName"
                                       onclick="selectContract(this);"
                                       value="${projectInfo.contractName}">
                                <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="selectContract(this);">
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
                    </c:if>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同金额</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.contractPrice}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">业务来源</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.serviceComeFromName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">业务来源说明</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.serviceComeFromExplain}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目说明</label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <label class="form-control">${projectInfo.remarks}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div id="_attachmentProjectInfoId"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 委托人</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content ">
        <form class="form-horizontal">
            <div class="panel-body">
                <c:choose>
                    <c:when test="${projectInfo.consignorVo.csType eq 1}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    委托单位
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    法定代表
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csLegalRepresentative}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    社会统一信用代码
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csSociologyCode}</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    经营范围
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csScopeOperation}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    单位地址
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csAddress}</label></div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    单位性质
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csUnitPropertiesName}</label>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    委托姓名
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csName}</label></div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    身份证号
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csIdcard}</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    委托住址
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.consignorVo.csAddress}</label></div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <div id="_csAttachmentProjectEnclosureId"></div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="x_title">
            <h3>联系人</h3>
        </div>
        <table class="table table-bordered" id="CONSIGNOR_TableList">
        </table>
    </div>
</div>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 占有人</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content ">
        <form class="form-horizontal">
            <div class="panel-body">
                <c:choose>
                    <c:when test="${projectInfo.possessorVo.pType eq 1}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    占有单位
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pEntrustmentUnit}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    占有单位法定代表
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pLegalRepresentative}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    社会统一信用代码
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pSociologyCode}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    经营范围
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pScopeOperation}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    占有单位地址
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    单位性质
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pUnitPropertiesName}</label>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    占有人姓名
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    身份证号
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pIdcard}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    占有人住址
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <div id="_pAttachmentProjectEnclosureId"></div>
                        </div>
                    </div>
                </div>

            </div>
        </form>
        <div class="x_title">
            <h3>联系人</h3>
        </div>
        <table class="table table-bordered" id="POSSESSOR_TableList">
        </table>
    </div>
</div>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 报告使用单位</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal">
            <div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            报告使用单位
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <c:choose>
                                <c:when test="${projectInfo.unitInformationVo.uUseUnitName != null}">
                                    <label class="form-control">${projectInfo.unitInformationVo.uUseUnitName}</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-control">${projectInfo.unitInformationVo.uUseUnit}</label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            法定代表人
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.uLegalRepresentative}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            证照号
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.uCertificateNumber}</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            单位性质
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.uUnitPropertiesName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            经营范围
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.uScopeOperation}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            地址
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.uAddress}</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid"
                         style="display: ${empty projectInfo.unitInformationVo.businessType?'none':'block'}">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            业务类型
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.businessType}</label>
                        </div>
                    </div>

                    <div class="x-valid"
                         style="display: ${empty projectInfo.unitInformationVo.assessType?'none':'block'}">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            评估类型
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <label class="form-control">${projectInfo.unitInformationVo.assessType}</label>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="x_title">
            <h3>联系人
                <small>
                    <button type="button" class="btn btn-xs btn-primary docs-tooltip"
                            onclick="showModelCrmContacts()">
                        <i class="fa fa-search"></i>查询更多
                    </button>
                </small>
            </h3>
        </div>
        <table class="table table-bordered" id="UNIT_INFORMATION_TableList">
        </table>
    </div>
</div>
<script src="/pmcc-contract/js/cms_contract_utils.js"></script>

<script>

    function selectContract(this_) {
        cmsContract.select({
            multi: true,//是否允许多选
            appkey: "pmcc-assess",
            onSelected: function (data) {
                var uuids = [];
                var names = [];
                data.forEach(function (node, i) {
                    if (node.uuid) {
                        uuids.push(node.uuid);
                    }
                    if (node.name) {
                        names.push(node.name);
                    }
                });
                if (uuids.length == 0) {
                    Alert('有效合同为0');
                    return false;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/projectInfo/projectDataUpdate",
                    type: "post",
                    dataType: "json",
                    data: {id: '${projectInfo.id}' , contractId : uuids.join(",") , contractName : names.join(",")},
                    success: function (result) {
                        if (result.ret) {
                            window.location.reload();
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            }
        });
    }

    $(function () {
        //---------
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                fieldsName: "attachmentProjectInfoId",
                tableId: ${projectInfo.id}
            },
            deleteFlag: false
        });
        //---------
        FileUtils.getFileShows({
            target: "pAttachmentProjectEnclosureId",
            formData: {
                tableName: AssessDBKey.InitiatePossessor,
                tableId: ${projectInfo.possessorVo.id}
            },
            deleteFlag: false
        });

        //---------
        FileUtils.getFileShows({
            target: "csAttachmentProjectEnclosureId",
            formData: {
                tableName: AssessDBKey.InitiateConsignor,
                tableId: ${projectInfo.consignorVo.id}
            },
            deleteFlag: false
        })
        //---------
    });

    function loadInitContactsList(cPid, tb_List, cType) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cPhone', title: '电话号码'});
        cols.push({field: 'cEmail', title: '邮箱'});
        TableInit(tb_List, "${pageContext.request.contextPath}/initiateContacts/getBootstrapTableVo", cols, {
            cPid: cPid,
            cType: cType,
            approval: true
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    var config = {
        /**
         * 根据此处约定设置
         * com.copower.pmcc.assess.common.enums.InitiateContactsEnum
         */
        CONSIGNOR: {
            key: "CONSIGNOR", name: "委托人", nodeKey: 1, table: "CONSIGNOR_TableList"
        },
        POSSESSOR: {
            key: "POSSESSOR", name: "占有人", nodeKey: 2, table: "POSSESSOR_TableList"
        },
        UNIT_INFORMATION: {
            key: "UNIT_INFORMATION",
            name: "报告使用单位",
            nodeKey: 3,
            table: "UNIT_INFORMATION_TableList"
        }
    };

    function showModelCrmContacts() {
        $('#divBoxCRMContacts').modal("show");
        findCRMContacts($("#divBoxCRMContacts").find("input[name='name']")[0]);
    }

    function findCRMContacts(that) {
        var text = $(that).parent().parent().prev().find("input[name='name']").val();
        var id = '${projectInfo.unitInformationVo.uUseUnit}';
        var data = {customerId: id, searchCrm: text};
        if (id) {
            var cols = [];
            cols.push({field: 'name', title: '姓名', searchable: true});
            cols.push({field: 'department', title: '部门'});
            cols.push({field: 'phoneNumber', title: '电话号码'});
            cols.push({field: 'email', title: '邮箱'});
            cols.push({field: 'id', visible: false, title: "id"});
            $("#tb_ListCRMContacts").bootstrapTable("destroy");
            TableInit('tb_ListCRMContacts', "${pageContext.request.contextPath}/initiateCrmCustomer/getCustomerLinkmanPageList", cols, data, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        } else {
            Alert("未选择单元");
        }
    }


    //选项框
    $(document).ready(function () {
        loadInitContactsList("${projectInfo.consignorVo.id}", config.CONSIGNOR.table, config.CONSIGNOR.nodeKey);
        loadInitContactsList("${projectInfo.possessorVo.id}", config.POSSESSOR.table, config.POSSESSOR.nodeKey);
        loadInitContactsList('${projectInfo.unitInformationVo.id}', config.UNIT_INFORMATION.table, config.UNIT_INFORMATION.nodeKey);

    });
</script>

<div id="divBoxCRMContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">crm联系人</h3>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="text" name="name" placeholder="联系人名字、电话"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="button"
                                                   onclick="findCRMContacts(this)"
                                                   class="btn btn-success" value="查询">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <table class="table table-bordered" id="tb_ListCRMContacts">
                                        <!-- cerare document add ajax data-->
                                    </table>
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
