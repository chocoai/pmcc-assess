<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-4-17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h2>
            项目信息
            <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
        </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">评估基准日</label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                    pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目类别</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectTypeName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的备注</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarkEntrustPurpose}</label></div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">紧急程度</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.urgencyName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">价值类型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.valueTypeName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">执业部门</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.departmentName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">价值类型备注</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarkValueType}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">省</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.provinceName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">市</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.cityName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">县</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.districtName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目经理</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目成员</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目说明</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarks}</label></div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">附件</label>
                    <div class="col-sm-3">
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
    <div class="x_content collapse" >
        <form class="form-horizontal">
            <div class="panel-body">
                <c:choose>
                    <c:when test="${projectInfo.consignorVo.csType eq 1}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托单位
                                </label>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${projectInfo.consignorVo.csEntrustmentUnitName != null}">
                                            <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnitName}</label>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    法定代表
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csLegalRepresentative}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    社会统一信用代码
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csSociologyCode}</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    经营范围
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csScopeOperation}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单位地址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csAddress}</label></div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单位性质
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csUnitPropertiesName}</label>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托姓名
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csName}</label></div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    身份证号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csIdcard}</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托住址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csAddress}</label></div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
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
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2> 占有人</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse" >
        <form class="form-horizontal">
            <div class="panel-body">
                <c:choose>
                    <c:when test="${projectInfo.possessorVo.pType eq 1}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占有单位
                                </label>
                                <div class="col-sm-3">
                                    <c:choose>
                                        <c:when test="${projectInfo.possessorVo.pEntrustmentUnitName != null}">
                                            <label class="form-control">${projectInfo.possessorVo.pEntrustmentUnitName}</label>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="form-control">${projectInfo.possessorVo.pEntrustmentUnit}</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占有单位法定代表
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pLegalRepresentative}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    社会统一信用代码
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pSociologyCode}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    经营范围
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pScopeOperation}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占有单位地址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单位性质
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pUnitPropertiesName}</label>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占有人姓名
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    身份证号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pIdcard}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占有人住址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            附件
                        </label>
                        <div class="col-sm-3">
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
    <div class="x_content collapse" >
        <form class="form-horizontal">
            <div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            报告使用单位
                        </label>
                        <div class="col-sm-3">
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
                        <label class="col-sm-1 control-label">
                            法定代表人
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.unitInformationVo.uLegalRepresentative}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            证照号
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.unitInformationVo.uCertificateNumber}</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            单位性质
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.unitInformationVo.uUnitPropertiesName}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            经营范围
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.unitInformationVo.uScopeOperation}</label>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            地址
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control">${projectInfo.unitInformationVo.uAddress}</label>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="x_title">
            <h3>联系人</h3>
        </div>
        <table class="table table-bordered" id="UNIT_INFORMATION_TableList">
        </table>
    </div>
</div>
<script>
    $(function () {
        //---------
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: ${projectInfo.id}
            },
            deleteFlag: false
        })
        //---------
        FileUtils.getFileShows({
            target: "pAttachmentProjectEnclosureId",
            formData: {
                tableName: AssessDBKey.InitiatePossessor,
                tableId: ${projectInfo.possessorVo.id}
            },
            deleteFlag: false
        })

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
    function loadInitContactsList(id, tb_List, flag) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        TableInit(tb_List, "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols, {
            pid: id,
            type: flag
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        console.log("id:"+id +" "+tb_List +" "+flag);
    }
    function Contacts() {
    };
    Contacts.prototype.config = function () {
        var Contacts = {};
        /**
         * 根据此处约定设置
         * com.copower.pmcc.assess.common.enums.InitiateContactsEnum
         */
        Contacts.CONSIGNOR = {key: "CONSIGNOR", name: "委托人", nodeKey: 1,table:"CONSIGNOR_TableList"};
        Contacts.POSSESSOR = {key: "POSSESSOR", name: "占有人", nodeKey: 2,table:"POSSESSOR_TableList"};
        Contacts.UNIT_INFORMATION = {key: "UNIT_INFORMATION", name: "报告使用单位", nodeKey: 3,table:"UNIT_INFORMATION_TableList"};
        return Contacts;
    };
    //选项框
    $(document).ready(function () {
        loadInitContactsList("${projectInfo.consignorVo.id}", Contacts.prototype.config().CONSIGNOR.table, Contacts.prototype.config().CONSIGNOR.nodeKey)
        loadInitContactsList("${projectInfo.possessorVo.id}", Contacts.prototype.config().POSSESSOR.table, Contacts.prototype.config().POSSESSOR.nodeKey);
        loadInitContactsList('${projectInfo.unitInformationVo.id}', Contacts.prototype.config().UNIT_INFORMATION.table, Contacts.prototype.config().UNIT_INFORMATION.nodeKey);
    });
</script>
