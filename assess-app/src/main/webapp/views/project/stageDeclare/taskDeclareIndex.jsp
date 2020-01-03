<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApplyModel.jsp" %>

            <!-- 房产证 -->
            <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyHouseCert.jsp" %>

            <!-- 土地证 -->
            <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyLandCert.jsp" %>

            <!-- 不动产证 -->
            <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyRealEstateCert.jsp" %>

            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <form class="form-horizontal" id="declareApplyForm">
                        <input type="hidden" name="id" value="${declare.id}">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    估价委托书
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <input id="project_proxy" name="project_proxy" type="file" multiple="false">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估报告
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <input id="assess_report_enclosure" name="assess_report_enclosure" type="file" multiple="false">
                                    <div id="_assess_report_enclosure"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估面积
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input name="assessArea" class="form-control" placeholder="上次评估面积" value='${declare.assessArea}'/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估金额
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input name="assessMoney" class="form-control" placeholder="上次评估金额" value='${declare.assessMoney}'/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估基准日
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input placeholder="上次评估基准日" id="dateLimit"
                                           name="dateLimit" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${declare.dateLimit}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次委托单位
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input name="client" class="form-control" placeholder="上次委托单位" value='${declare.client}'/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    上次评估机构
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input name="assessOrganization" class="form-control" placeholder="上次评估机构" value='${declare.assessOrganization}'/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    备注
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input name="remark" class="form-control" placeholder="备注" value='${declare.remark}'/>
                                </div>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    自定义附件
                                </label>
                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                    <div class="btn  btn-success" onclick="declareApplyExtensionCumstom.appendHTML('${declare.id}','${projectPlanDetails.projectId}',this)"><i class="fa fa-plus"></i></div>
                                </div>
                            </div>
                        </div>

                        <c:forEach items="${declareApplyExtensionList}" var="itemData">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        自定义名称<span class="symbol required"></span>
                                    </label>
                                    <input type="hidden" name="id" value="${itemData.id}">
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <input name="name" class="form-control" placeholder="自定义名称" value="${itemData.name}" onblur="declareApplyExtensionCumstom.targetSave(this);" />
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        附件
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                        <input id="other_Enclosure${itemData.id}" name="other_Enclosure${itemData.id}" type="file" multiple="false">
                                        <div id="_other_Enclosure${itemData.id}"></div>
                                    </div>
                                </div>
                                <script>
                                    $(function () {
                                        var fileId = 'other_Enclosure${itemData.id}' ;
                                        declareCommon.showFile(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", true, fileId);
                                        declareCommon.fileUpload(fileId, AssessDBKey.DeclareApplyExtension, "${itemData.id}", true, fileId);
                                    });
                                </script>
                                <div class="x-valid">
                                    <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X" onclick="declareApplyExtensionCumstom.cleanItemHTML(this)"></span>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                </div>
            </div>
            <%@include file="/views/share/form_apply.jsp" %>
            <%@include file="/views/method/module/economicIndicators.jsp" %>
            <%@include file="/views/project/tool/declareApplyExtensionCumstomModelView.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.house.cert.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.real.estate.cert.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.land.cert.js?v=${assessVersion}"></script>



<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=1.0"></script>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;">


<script type="text/javascript">

    $(document).ready(function () {
        var fileArr = [AssessUploadKey.PROJECT_PROXY ,AssessUploadKey.ASSESS_REPORT_Enclosure ] ;
        $.each(fileArr,function (i,n) {
            declareCommon.showFile(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, n);
            declareCommon.fileUpload(n, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, n);
        });
    });

</script>
<script type="application/javascript">
    //提交
    function submit(mustUseBox) {
        //检查是否填写了申报数据
        var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm(mustUseBox);
            return false;
        }
        rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm(mustUseBox);
            return false;
        }
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
        } else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }
</script>

</html>

