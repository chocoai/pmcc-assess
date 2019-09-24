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


            <form class="form-horizontal" id="declareApplyForm">

                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                <input type="hidden" name="assetsSettingId" value="${projectPhase.assetsSettingId}">

            </form>

            <div class="form-horizontal">
                <div class="x_panel">
                    <div class="x_title">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>申报编辑文件
                            <small><input type="button" class="btn btn-xs btn-primary" value="获取模板"
                                          onclick="reprot.writeDeclarationHtml(2);">
                            </small>
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div id="assetsCustomizeDataField_Fixed_FileId">
                            <c:forEach items="${fixedDataFieldAndFile}" var="item">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input name="name" class="form-control" placeholder="名称" readonly="readonly"
                                                   value="${item.name}" onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <input id="assetsCustomizeDataField_Fixed_file${item.id}"
                                                   name="assetsCustomizeDataField_Fixed_file${item.id}" type="file" multiple="false">
                                            <div id="_assetsCustomizeDataField_Fixed_file${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'assetsCustomizeDataField_Fixed_file${item.id}';
                                            reprot.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,true, fileId);
                                            reprot.fileUpload2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, true,fileId);
                                        });
                                    </script>
                                    <div class="x-valid">
                                        <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                                             onclick="reprot.cleanItemHTML(this,'assetsCustomizeDataField_Fixed_file${item.id}',2,'${item.id}');"></span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="x_panel">
                    <div class="x_title">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>申报编辑字段
                            <small><input type="button" class="btn btn-xs btn-primary" value="获取配置字段"
                                          onclick="reprot.writeDeclarationHtml(1);">
                            </small>
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div id="assetsCustomizeDataField_Fixed_fieldId">
                            <c:forEach items="${fixedDataField}" var="item">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input name="name" class="form-control" placeholder="名称"
                                                   value="${item.name}" onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <input id="assetsCustomizeDataField_Fixed${item.id}"
                                                   name="assetsCustomizeDataField_Fixed${item.id}" type="file" multiple="false">
                                            <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                                            reprot.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                                            reprot.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                                        });
                                    </script>
                                    <div class="x-valid">
                                        <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                                             onclick="reprot.cleanItemHTML(this,'assetsCustomizeDataField_Fixed${item.id}',1,'${item.id}');"></span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>自定义附件</h3>
                        <div class="clearfix"></div>
                    </div>

                    <div class="x_content">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    自定义操作
                                </label>
                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                    <div class="btn  btn-success" onclick="reprot.writeDeclarationHtml(0)"><i class="fa fa-plus"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="assetsCustomizeDataOther_fieldId">
                            <c:forEach items="${customizeDataField}" var="item">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            自定义名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input name="name" class="form-control" placeholder="自定义名称"
                                                   value="${item.name}" onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <input id="other_Enclosure${item.id}"
                                                   name="other_Enclosure${item.id}" type="file" multiple="false">
                                            <div id="_other_Enclosure${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'other_Enclosure${item.id}';
                                            reprot.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                                            reprot.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                                        });
                                    </script>
                                    <div class="x-valid">
                                        <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                                             onclick="reprot.cleanItemHTML(this,'other_Enclosure${item.id}',0,'${item.id}');"></span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="x_panel">
                    <div class="x_content">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    附件
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <input id="project_proxy" name="project_proxy" type="file" multiple="false">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <%@include file="./commonFieldHtmlModel.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <c:if test="${projectPhase.bisUseBox eq true}">
                            <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/declare/reprot.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/dataAssetsAppraisalDic.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.each(reprot.fileArray, function (i, n) {
            reprot.fileUpload2(n, AssessDBKey.ProjectPlanDetails, '${projectPlanDetails.id}', false,false, n);
            reprot.showFile2(n, AssessDBKey.ProjectPlanDetails, '${projectPlanDetails.id}', false,false, n);
        });
    });

</script>
<script type="application/javascript">

    //提交表单
    function submit(mustUseBox) {
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

