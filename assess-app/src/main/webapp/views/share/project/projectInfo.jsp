<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn_project" %>
<html lang="en" class="no-js">
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson"
                           value='${projectInfoVoJson}'>
                    项目信息
                    <small>${projectInfo.projectCategoryName}</small>
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="form-horizontal">
                <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">项目名称</label>
                            <div class="col-sm-7">
                                <label class="form-control input-full">${projectInfo.projectName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">紧急程度</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.urgencyName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">委托目的</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.entrustPurposeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                委托目的类别
                            </label>
                            <div class="col-sm-3 x-valid">
                                <label class="form-control input-full">${projectInfo.entrustAimTypeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">价值类型</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.valueTypeName}</label>
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
                                <label class="form-control input-full">${projectInfo.remarkEntrustPurpose}</label>
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
                                <label class="form-control input-full">${projectInfo.propertyScopeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估包括
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.scopeInclude}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估不包括
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.scopeNotInclude}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">项目经理</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">项目成员</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                            </div>

                            <label class="col-sm-1 col-form-label">
                                执业部门
                            </label>
                            <div class="col-sm-3 x-valid">
                                <label class="form-control input-full">${projectInfo.departmentName}</label>
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
                                <label class="form-control input-full" name="contractName">
                                        <c:if test="${!empty projectInfo.contractId}">
                                            <c:forEach var="item" items="${projectInfo.contractList}">
                                                <a href="${sysUrl}/pmcc-contract/contractCurrency/details/${item.key}"
                                                   target="_blank">${item.value}     </a>
                                            </c:forEach>
                                        </c:if>
                                </label>
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
                                <label class="form-control input-full">${projectInfo.contractPrice}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估基准日
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full"><fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/></label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                贷款类型
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.loanTypeName}</label>
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
                                <label class="form-control input-full">${projectInfo.serviceComeFromName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                业务来源说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.serviceComeFromExplain}</label>
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
                                <label class="form-control input-full">${projectInfo.remarks}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">附件</label>
                            <div class="col-md-3">
                                <div id="_attachmentProjectInfoId"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    委托人
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <c:choose>
                    <c:when test="${projectInfo.consignorVo.csType eq 1}">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 col-form-label">
                                        委托单位
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csEntrustmentUnit}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        法定代表
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csLegalRepresentative}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        社会统一信用代码
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csSociologyCode}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 col-form-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csScopeOperation}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        单位地址
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csAddress}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csUnitPropertiesName}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 col-form-label">
                                        姓名
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csName}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        身份证号
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csIdcard}</label>
                                    </div>
                                    <label class="col-sm-1 col-form-label">
                                        住址
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control input-full">${projectInfo.consignorVo.csAddress}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                附件
                            </label>
                            <div class="col-sm-3">
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
</div>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    占有人
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${projectInfo.possessorVo.pType eq 1}">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            占有单位
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pEntrustmentUnit}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            占有单位法定代表
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pLegalRepresentative}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            社会统一信用代码
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pSociologyCode}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            经营范围
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pScopeOperation}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            占有单位地址
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pAddress}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            单位性质
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pUnitPropertiesName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            占有人姓名
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pName}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            身份证号
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pIdcard}</label>
                                        </div>
                                        <label class="col-sm-1 col-form-label">
                                            占有人住址
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${projectInfo.possessorVo.pAddress}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    附件
                                </label>
                                <div class="col-sm-3">
                                    <div id="_pAttachmentProjectEnclosureId"></div>
                                </div>
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
</div>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    报告使用单位
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    报告使用单位
                                </label>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${projectInfo.unitInformationVo.uUseUnitName != null}">
                                            <label class="form-control input-full">${projectInfo.unitInformationVo.uUseUnitName}</label>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="form-control input-full">${projectInfo.unitInformationVo.uUseUnit}</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    法定代表人
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.uLegalRepresentative}</label>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    证照号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.uCertificateNumber}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    单位性质
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.uUnitPropertiesName}</label>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    经营范围
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.uScopeOperation}</label>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    地址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.uAddress}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid" style="display: ${empty projectInfo.unitInformationVo.businessType?'none':'block'}">
                                <label class="col-sm-1 col-form-label">
                                    业务类型
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.businessType}</label>
                                </div>
                                <label class="col-sm-1 col-form-label">
                                    评估类型
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectInfo.unitInformationVo.assessType}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="x_title">
                <h3>联系人
                    <button type="button" class="btn btn-xs btn-info btn-sm"
                            onclick="showModelCrmContacts()">
                        <i class="fa fa-search"></i>查询更多
                    </button>
                </h3>
            </div>
            <table class="table table-bordered" id="UNIT_INFORMATION_TableList">
            </table>
        </div>
    </div>
</div>
<script src="/pmcc-contract/js/cms_contract_utils.js?v=${assessVersion}"></script>

<script>


    /**
     * 文字溢出 情况 超过规定的就执行
     */
    function settingContract() {
        var textMax = 30;
        var projectId = $("#projectId");
        var form = projectId.closest(".form-horizontal") ;
        var contractName = form.find("label[name='contractName']") ;
        var len = 0;
        var attribute = {'overflow':'scroll','-webkit-box-orient':'vertical',display:'-webkit-box'} ;//'text-overflow':'ellipsis'
        contractName.find("a").each(function (i,a) {
            var text = $.trim($(a).text()) ;
            len += text.length ;
            if (len > textMax){
                $(a).hide() ;
            }
        });
        if (len > textMax){
            contractName.html(contractName.html()+"...") ;
//            contractName.css(attribute) ;
        }

    }



    $(function () {
        settingContract() ;
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
        findCRMContacts($("#divBoxCRMContacts").find("input[name='name']")[0], function () {
            $('#divBoxCRMContacts').modal("show");
        });
    }

    function findCRMContacts(that, callback) {
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
            if (callback) {
                callback();
            }
        } else {
            AlertError("失败","未选择单元");
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
                <h4 class="modal-title">crm联系人</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-10">
                                                <input type="text" name="name" placeholder="联系人名字、电话"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">

                                        <div class="form-inline x-valid">
                                            <button type="button" onclick="findCRMContacts(this)"
                                                    class="btn btn-success btn-sm" value="查询">
                                                查询
                                            </button>
                                        </div>

                                    </div>
                                </div>

                                <table class="table table-bordered" id="tb_ListCRMContacts">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<%--
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

                                <div class="row form-group">
                                    <div class="form-inline x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="text" name="name" placeholder="联系人名字、电话"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="button"
                                                   onclick="findCRMContacts(this)"
                                                   class="btn btn-success" value="查询">
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
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
--%>
