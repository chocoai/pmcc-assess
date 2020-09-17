<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <!-- 申报各种类型的html视图 -->
                    <%@include file="/views/project/stageDeclare/declareApplyModel.jsp" %>

                    <!-- 土地证 -->
                    <%@include file="/views/project/stageDeclare/declareLandPureModel/viewDeclareRealtyLandCert.jsp" %>

                    <!-- 不动产证 -->
                    <%@include file="/views/project/stageDeclare/declareLandPureModel/viewDeclareRealtyRealEstateCert.jsp" %>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <form class="form-horizontal" id="declareApplyForm">
                                    <input type="hidden" name="id" value="${declare.id}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估报告
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input id="assess_report_enclosure" name="assess_report_enclosure"
                                                           type="file" multiple="false">
                                                    <div id="_assess_report_enclosure"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估面积
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input name="assessArea" class="form-control input-full"
                                                           data-rule-number="true"   placeholder="上次评估面积" value='${declare.assessArea}'/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估金额
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input name="assessMoney" class="form-control input-full"
                                                           data-rule-number="true"  placeholder="上次评估金额" value='${declare.assessMoney}'/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估基准日
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input placeholder="上次评估基准日" id="dateLimit"
                                                           name="dateLimit" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate"
                                                           readonly="readonly"
                                                           value="<fmt:formatDate value='${declare.dateLimit}' pattern='yyyy-MM-dd'/>">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次委托单位
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input name="client" class="form-control input-full"
                                                           placeholder="上次委托单位" value='${declare.client}'/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    上次评估机构
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input name="assessOrganization" class="form-control input-full"
                                                           placeholder="上次评估机构" value='${declare.assessOrganization}'/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    备注
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input name="remark" class="form-control input-full"
                                                           placeholder="备注" value='${declare.remark}'/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <button type="button" class="btn btn-success btn-sm"
                                                            onclick="declareApplyExtensionCumstom.appendHTML('${declare.id}','${projectPlanDetails.projectId}',this)">
                                                        <span class="btn-label"><i class="fa fa-plus"></i></span>添加附件
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <c:forEach items="${declareApplyExtensionList}" var="itemData">
                                        <div class="row form-group">
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                        附件名称<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <input type="hidden" name="id" value="${itemData.id}">
                                                        <input name="name" class="form-control input-full"
                                                               placeholder="附件名称" value="${itemData.name}"
                                                               onblur="declareApplyExtensionCumstom.targetSave(this);"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <div class="form-inline x-valid">
                                                    <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                        附件
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <input id="other_Enclosure${itemData.id}"
                                                               name="other_Enclosure${itemData.id}" type="file"
                                                               multiple="false">
                                                        <div id="_other_Enclosure${itemData.id}"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <script>
                                                $(function () {
                                                    var fileId = 'other_Enclosure${itemData.id}';
                                                    declareCommon.showFile(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", true, fileId);
                                                    declareCommon.fileUpload(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", true, fileId);
                                                });
                                            </script>
                                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                <button type="button" class="btn btn-warning btn-sm" onclick="declareApplyExtensionCumstom.cleanItemHTML(this)"><i class="fa fa-minus"></i></button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <%@include file="/views/share/form_apply.jsp" %>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_log.jsp" %>
                    <%@include file="/views/method/module/economicIndicators.jsp" %>
                    <%@include file="/views/project/tool/declareApplyExtensionCumstomModelView.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/landPure/landPure.cert.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">


<script type="text/javascript">
    $(document).ready(function () {
        var fileArr = [AssessUploadKey.PROJECT_PROXY, AssessUploadKey.ASSESS_REPORT_Enclosure];
        $.each(fileArr, function (i, n) {
            declareCommon.showFile(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, n);
            declareCommon.fileUpload(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, n);
        });
    });

</script>
<script type="application/javascript">
    //提交
    function submit(mustUseBox) {
        //检查是否填写了申报数据
        submitForm(mustUseBox);

    }
    //提交表单
    function submitForm(mustUseBox) {
        var frm = $("#declareApplyForm") ;
        if (!frm.valid()) {
            return false;
        }

        var formData = formSerializeArray(frm);
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }
</script>

</html>

