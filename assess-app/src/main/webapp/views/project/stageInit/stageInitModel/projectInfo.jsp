<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 项目基本信息 -->
<div class="card-body">
    <form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.id}">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        项目名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-7 ">
                        <input required placeholder="项目名称" name="projectName"
                               value="${projectInfo.projectName}" class="form-control input-full">
                    </div>
                    <label class="col-sm-1 col-form-label">
                        紧急程度<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3 ">
                        <select name="urgency" class="form-control input-full search-select select2 urgency"
                                required="required">
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                    </label>
                    <div class="col-sm-3">
                        <div id="_projectPhaseWorkTemp"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline">
                    <label class="col-sm-1 col-form-label">
                        委托目的<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-3 x-valid">
                        <select name="entrustPurpose"
                                class="form-control input-full search-select select2 entrustPurpose"
                                required="required"
                                onchange="$(this).closest('.form-group').find('[name=entrustPurposeName]').val($(this).find('option:selected').text())">
                        </select>
                        <input type="hidden" name="entrustPurposeName">
                    </div>
                    <label class="col-sm-1 col-form-label">
                        委托目的类别
                    </label>
                    <div class="col-sm-3 x-valid">
                        <select name="entrustAimType"
                                class="form-control input-full search-select select2 entrustAimType_p">
                        </select>
                    </div>

                    <label class="col-sm-1 col-form-label">
                        价值类型
                    </label>
                    <div class="col-sm-3">
                        <select name="valueType" class="form-control input-full search-select select2 valueType">
                        </select>
                        <input type="hidden" name="remarkValueType">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        委托目的描述
                    </label>
                    <div class="col-sm-11">
                        <input name="remarkEntrustPurpose"
                               class="form-control input-full"
                               placeholder="委托目的描述" value="${projectInfo.remarkEntrustPurpose}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        评估范围
                    </label>
                    <div class="col-sm-3">
                        <select name="propertyScope" class="form-control input-full search-select select2 propertyScope"
                                onchange="$(this).closest('.form-group').find('[name=propertyScopeName]').val($(this).find('option:selected').text())">
                        </select>

                        <input type="hidden" name="propertyScopeName">
                    </div>
                    <label class="col-sm-1 col-form-label">
                        评估包括
                    </label>
                    <div class="col-sm-3">
                        <input name="scopeInclude"
                               class="form-control input-full"
                               placeholder="评估包括" value="${projectInfo.scopeInclude}"/>
                    </div>
                    <label class="col-sm-1 col-form-label">
                        评估不包括
                    </label>
                    <div class="col-sm-3">
                        <input name="scopeNotInclude"
                               class="form-control input-full"
                               placeholder="评估不包括" value="${projectInfo.scopeNotInclude}"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline">
                    <label class="col-sm-1 col-form-label">
                        项目负责人【<span id="lab_total" style="cursor: pointer;color: red;"
                                   onclick="objProject.checkProjectByAccount(this);"></span>】
                        <span class="symbol required"></span></label>
                    </label>
                    <div class="col-sm-3 x-valid">
                        <div class="input-group">
                            <input type="hidden" name="userAccountManager"
                                   value="${projectInfo.projectMemberVo.userAccountManager}">
                            <input type="text" class="form-control" readonly="readonly" name="userAccountManagerName"
                                   required onclick="objProject.selectUserAccountManager(this);"
                                   value="${projectInfo.projectMemberVo.userAccountManagerName}">
                            <div class="input-group-prepend">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                </button>
                            </div>
                            <div class="input-group-prepend">
                                <button class="btn btn-primary btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="objProject.selectUserAccountManager(this);">选择
                                </button>
                            </div>
                            <c:if test="${processInsId == '0' || processInsId == null || processInsId == 0}">
                                <div class="input-group-prepend" style="margin-left: 10px;">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" id="bisAssign" name="bisAssign"
                                                   value="true">
                                            <span class="form-check-sign">下分</span>
                                        </label>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <label class="col-sm-1 col-form-label">
                        项目成员
                    </label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="hidden" name="userAccountMember"
                                   value="${projectInfo.projectMemberVo.userAccountMember}">
                            <input type="text" class="form-control" readonly="readonly" name="userAccountMemberName"
                                   onclick="objProject.selectUserAccountMember(this);"
                                   value="${projectInfo.projectMemberVo.userAccountMemberName}">
                            <div class="input-group-prepend">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                </button>
                            </div>
                            <div class="input-group-prepend">
                                <button class="btn btn-primary btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="objProject.selectUserAccountMember(this);">选择
                                </button>
                            </div>
                        </div>
                    </div>

                    <label class="col-sm-1 col-form-label">
                        执业部门
                    </label>
                    <div class="col-sm-3 x-valid">
                        <div class="input-group">
                            <input type="hidden" name="departmentId"
                                   value="${projectInfo.departmentId}">
                            <input class='form-control' name="departmentName" required
                                   readonly="readonly" onclick="objProject.selectDepartment(this);"
                                   value="${projectInfo.departmentName}">
                            <div class="input-group-prepend">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                </button>
                            </div>
                            <div class="input-group-prepend">
                                <button class="btn btn-primary btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="objProject.selectDepartment(this);">选择
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        项目合同
                    </label>
                    <div class="col-sm-11">
                        <div class="input-group">
                            <input type="hidden" name="contractId" value="${projectInfo.contractId}">

                            <input type="hidden" class="form-control" readonly="readonly" name="contractName"
                                   onclick="objProject.selectContract(this);"
                                   value="${projectInfo.contractName}">

                            <label class="form-control" name="contractNameView">
                                <c:if test="${!empty projectInfo.contractList}">
                                    <c:forEach var="item" items="${projectInfo.contractList}">
                                        <a href="${sysUrl}/pmcc-contract/contractCurrency/details/${item.key}"
                                           target="_blank">${item.value} </a>
                                    </c:forEach>
                                </c:if>
                            </label>

                            <div class="input-group-prepend">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('').end().closest('.input-group').find('label[name=contractNameView]').html('').end();">
                                    清空
                                </button>
                            </div>
                            <div class="input-group-prepend">
                                <button class="btn btn-primary btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="objProject.selectContract(this);">选择
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">
                        合同金额（元）
                    </label>
                    <div class="col-sm-3">
                        <input name="contractPrice" data-rule-number="true" class="form-control input-full"
                               placeholder="合同金额"
                               value="${projectInfo.contractPrice}"/>
                    </div>
                    <label class="col-sm-1 col-form-label">
                        评估基准日
                    </label>
                    <div class="col-sm-3">
                        <input placeholder="评估基准日"
                               name="valuationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate" readonly="readonly"
                               value="<fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">
                    </div>
                    <label class="col-sm-1 col-form-label">
                        贷款类型
                    </label>
                    <div class="col-sm-3">
                        <select name="loanType" class="form-control input-full search-select select2 loanType">
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline">
                    <label class="col-sm-1 col-form-label">
                        业务来源
                    </label>
                    <div class="col-sm-3 x-valid">
                        <select name="serviceComeFrom" class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-sm-1 col-form-label">
                        业务来源说明
                    </label>
                    <div class="col-sm-3">
                        <input name="serviceComeFromExplain" class="form-control input-full" placeholder="业务来源说明"
                               value="${projectInfo.serviceComeFromExplain}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        项目说明
                    </label>
                    <div class="col-sm-11">
                        <textarea name="remarks" class="form-control input-full"
                                  placeholder="项目说明">${projectInfo.remarks}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3">
                        <input id="attachmentProjectInfoId" name="attachmentProjectInfoId"
                               placeholder="上传附件" class="form-control input-full" type="file">
                        <div id="_attachmentProjectInfoId"></div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="/pmcc-contract/js/cms_contract_utils.js?v=${assessVersion}"></script>
<div id="checkProjectsBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">项目列表</h4>
                <input type="hidden" name="housePriceId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <table class="table table-bordered" id="getProjectByAccountList">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>