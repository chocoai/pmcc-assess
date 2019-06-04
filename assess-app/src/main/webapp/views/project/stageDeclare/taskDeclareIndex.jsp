<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
            <div id="viewDeclareRealtyHouseCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyHouseCert.jsp" %>
            </div>

            <!-- 土地证 -->
            <div id="viewDeclareRealtyLandCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyLandCert.jsp" %>
            </div>

            <!-- 不动产证 -->
            <div id="viewDeclareRealtyRealEstateCert">
                <%@include
                        file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyRealEstateCert.jsp" %>
            </div>
            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <form class="form-horizontal" id="declareApplyForm">
                        <input type="hidden" name="id" value="${declare.id}">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    委托单位<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <c:choose>
                                        <c:when test="${empty declare.client}">
                                            <input name="client" class="form-control" placeholder="委托单位"
                                                   required value='${consignor}'/>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="client" class="form-control" placeholder="委托单位"
                                                   required value='${declare.client}'/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    完成时限<span class="symbol required"></span>
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input required="required" placeholder="完成时限" id="dateLimit"
                                           name="dateLimit" data-date-format="yyyy-mm-dd" required
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${declare.dateLimit}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1112  col-sm-1112  col-md-1112  col-lg-1112  col-sm-1 col-xs-12 control-label">
                                    估价委托书
                                </label>
                                <div class=" col-xs-5512  col-sm-5512  col-md-5512  col-lg-5512  col-sm-5 col-xs-12">
                                    <input id="project_proxy" name="project_proxy" type="file" multiple="false">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.land.cert.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.house.cert.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.real.estate.cert.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">
    $(document).ready(function () {
        declareCommon.showFile(AssessUploadKey.PROJECT_PROXY, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, AssessUploadKey.PROJECT_PROXY);
        declareCommon.fileUpload(AssessUploadKey.PROJECT_PROXY, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, AssessUploadKey.PROJECT_PROXY);
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
        rows = $("#" + assessCommonLand.config.table).bootstrapTable('getData');
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
        if (!$("#declareApplyForm").valid()) {
            return false;
        }
        var formData = formParams("declareApplyForm");;

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }
</script>

</html>

