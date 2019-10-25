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
            <div id="viewDeclareRealtyHouseCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyHouseCert.jsp" %>
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
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    自定义附件
                                </label>
                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                    <div class="btn  btn-success" onclick="appendHTML(this)"><i class="fa fa-plus"></i></div>
                                </div>
                            </div>
                        </div>
                        <c:forTokens items="${declare.name}" delims="," var="item" varStatus="status">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        自定义名称<span class="symbol required"></span>
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <input name="name" class="form-control" placeholder="自定义名称" value="${item}" />
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        附件${status.index}
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                        <input id="other_Enclosure${status.index+1}" name="other_Enclosure${status.index+1}" type="file" multiple="false">
                                        <div id="_other_Enclosure${status.index+1}"></div>
                                    </div>
                                </div>
                                <script>
                                    $(function () {
                                        var fileId = 'other_Enclosure${status.index+1}' ;
                                        declareCommon.showFile(fileId, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, fileId);
                                        declareCommon.fileUpload(fileId, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, fileId);
                                    });
                                </script>
                                <div class="x-valid">
                                    <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X" onclick="cleanItemHTML(this)"></span>
                                </div>
                            </div>
                        </c:forTokens>
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
            <%@include file="/views/method/module/economicIndicators.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/html" id="other_EnclosureModel">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                自定义名称<span class="symbol required"></span>
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input name="name" class="form-control" placeholder="自定义名称" />
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                附件
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                <input id="other_Enclosure{number}" name="other_Enclosure{number}" type="file" multiple="false">
                <div id="_other_Enclosure{number}"></div>
            </div>
        </div>
        <div class="x-valid">
            <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X" onclick="cleanItemHTML(this)"></span>
        </div>
    </div>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.house.cert.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/house/realty.real.estate.cert.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=1.0"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">

    function appendHTML(_this) {
        var frm = $("#declareApplyForm") ;
        var target = $(_this).parent().parent().parent() ;
        var number = Number(frm.find("input[name='name']").size()) ;
        number++;
        var html = $("#other_EnclosureModel").html() ;
        var fileId = "other_Enclosure" + number;
        html = html.replace(/{number}/g,number) ;
        target.after(html) ;
        declareCommon.showFile(fileId, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, fileId);
        declareCommon.fileUpload(fileId, AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", true, fileId);
    }

    function cleanItemHTML(_this) {
        $(_this).parent().parent().parent().remove() ;
    }

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
        var number = Number(frm.find("input[name='name']").size()) ;
        if (formData.name){
            if (formData.name.split(",").length != number){
                toastr.success('自定义字段必须填写完整!');
                return false;
            }
        }
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), mustUseBox);
        }
    }
</script>

</html>

