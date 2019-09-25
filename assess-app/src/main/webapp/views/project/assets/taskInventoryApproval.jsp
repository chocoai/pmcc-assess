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

            <div class="form-horizontal">
                <div class="x_panel">
                    <div class="x_title">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>申报编辑文件
                            <small>
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
                                            <label class="form-control">
                                                    ${item.name}
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <div id="_assetsCustomizeDataField_Fixed_file${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'assetsCustomizeDataField_Fixed_file${item.id}';
                                            inventory.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false, fileId);
                                        });
                                    </script>
                                </div>
                            </c:forEach>
                        </div>

                        <div id="assetsCustomizeDataField_Fixed_fieldId">
                            <c:forEach items="${fixedDataField}" var="item">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control">
                                                    ${item.name}
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                                            inventory.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false, fileId);
                                        });
                                    </script>
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

                        <div id="assetsCustomizeDataOther_fieldId">
                            <c:forEach items="${customizeDataField}" var="item">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            自定义名称<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class=" form-control">
                                                    ${item.name}
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            附件
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                            <div id="_other_Enclosure${item.id}"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        $(function () {
                                            var fileId = 'other_Enclosure${item.id}';
                                            inventory.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false, fileId);
                                        });
                                    </script>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <form class="form-horizontal" id="declareApplyForm">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    附件
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/declare/inventory.common.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.each(inventory.fileArray, function (i, n) {
            inventory.showFile2(n, AssessDBKey.ProjectPlanDetails, '${projectPlanDetails.id}', false,false, n);
        });
    });

</script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

