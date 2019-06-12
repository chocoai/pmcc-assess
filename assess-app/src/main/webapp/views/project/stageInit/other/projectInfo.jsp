<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 项目基本信息 -->
<div class="x_content">
    <form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectInfo.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-7  col-sm-7  col-md-7  col-lg-7 ">
                    <input required placeholder="项目名称" name="projectName"
                           value="${projectInfo.projectName}" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">紧急程度<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="urgency" class="form-control search-select select2 urgency" required="required">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="entrustPurpose" class="form-control search-select select2 entrustPurpose"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的类别<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="entrustAimType" class="form-control search-select select2 entrustAimType_p"
                            required="required" >
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input name="remarkEntrustPurpose"
                           class="form-control"
                           placeholder="委托目的描述" value="${projectInfo.remarkEntrustPurpose}"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价值类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="valueType" class="form-control search-select select2 valueType" required="required">
                    </select>
                    <input type="hidden" name="remarkValueType" class="form-control"
                           placeholder="价值定义" value="${projectInfo.remarkValueType}"/>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估基准日<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input required="required" placeholder="评估基准日"
                           name="valuationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate" readonly="readonly"
                           value="<fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执业部门<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="departmentId"
                               value="${projectInfo.departmentId}">
                        <input class='form-control' name="departmentName" required
                               readonly="readonly" onclick="objProject.selectDepartment(this);"
                               value="${projectInfo.departmentName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="objProject.selectDepartment(this);"
                                data-toggle="tooltip"
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估范围</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyScope" class="form-control search-select select2 propertyScope">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估包括<span  class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input name="scopeInclude" required
                           class="form-control"
                           placeholder="评估包括" value="${projectInfo.scopeInclude}"/>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估不包括<span  class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input name="scopeNotInclude" required
                           class="form-control"
                           placeholder="评估不包括" value="${projectInfo.scopeNotInclude}"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目经理<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="userAccountManager"
                               value="${projectInfo.projectMemberVo.userAccountManager}">
                        <input type="text" class="form-control" readonly="readonly" name="userAccountManagerName"
                               required onclick="objProject.selectUserAccountManager(this);"
                               value="${projectInfo.projectMemberVo.userAccountManagerName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="objProject.selectUserAccountManager(this);">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目成员</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="userAccountMember"
                               value="${projectInfo.projectMemberVo.userAccountMember}">
                        <input type="text" class="form-control" readonly="readonly" name="userAccountMemberName"
                               onclick="objProject.selectUserAccountMember(this);"
                               value="${projectInfo.projectMemberVo.userAccountMemberName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="objProject.selectUserAccountMember(this);">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">贷款类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="loanType" required class="form-control search-select select2 loanType">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目合同</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="hidden" name="contractId" value="${projectInfo.contractId}">
                        <input type="text" class="form-control" readonly="readonly" name="contractName"
                               onclick="objProject.selectContract(this);"
                               value="${projectInfo.contractName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="objProject.selectContract(this);">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同金额（单位/元）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input name="contractPrice" data-rule-number="true" class="form-control" placeholder="合同金额"
                           value="${projectInfo.contractPrice}"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目说明</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                    <textarea name="remarks"
                                              class="form-control" placeholder="项目说明">${projectInfo.remarks}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="attachmentProjectInfoId" name="attachmentProjectInfoId"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_attachmentProjectInfoId"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="/pmcc-contract/js/cms_contract_utils.js"></script>