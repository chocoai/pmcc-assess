<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <%@include file="/views/project/stageInit/stageInitModel/otherProjectIndexJs.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%--<%@include file="/views/share/form_head.jsp" %>--%>
            <div class="page-inner">
                <div class="row mt--2">
                    <%--项目基本信息--%>
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
                                    <form id="frm_project_info" class="form-horizontal">
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
                                                    <label class="col-sm-1 col-form-label">
                                                        项目经理<span class="symbol required"></span></label>
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
                                                            <div class="input-group-prepend">
                                                                <button class="btn btn-success btn-sm " id="lab_total"
                                                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                        type="button" onclick="objProject.checkProjectByAccount(this);">0个
                                                                </button>
                                                            </div>
                                                        </div>
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
                                                    <div class="col-sm-4">
                                                        <div class="form-check">
                                                            <label class="form-check-label">
                                                                <input class="form-check-input" type="checkbox"
                                                                       id="bisAssign"
                                                                       name="bisAssign" value="true"
                                                                       checked="checked">
                                                                <span class="form-check-sign">是否分派</span>
                                                            </label>
                                                        </div>
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
                                    </form>
                                </div>
                            </div>
                        </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button type="button" id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button type="button" class="btn btn-primary" onclick="projectApply();">
                                提交
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        objProject.getProjectNumber('${projectInfo.projectMemberVo.userAccountManager}');
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

    });

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

    //项目提交立项
    function projectApply() {
        if (!$("#frm_project_info").valid()) {
            return false;
        }
        var data = {};
        data.formData = JSON.stringify(objProject.getFormData());
        data.bisAssign = $("#bisAssign").prop("checked");//注意这是是否分派标志

        var url = "${pageContext.request.contextPath}/projectInfo/assignTaskSubmit";

        Loading.progressShow();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    AlertSuccess("提交成功", "数据已成功保存到数据库", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


</script>
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