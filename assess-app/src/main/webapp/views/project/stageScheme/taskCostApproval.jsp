<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js?v=${assessVersion}"></script>
    <link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 引入成本法 详情模块 -->
            <!-- 引入成本法模块 -->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>成本法</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="costMethodFrm">
                        <div class="col-sm-12 form-group">
                            <span class="col-sm-1">
                                <label>建筑形态</label>
                            </span>
                            <c:if test="${mdCostVo.type == '1'}">
                                <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                    <input type="radio" id="building" name="type" value="1" checked="checked">
                                    <label for="building">建筑物</label>
                                </span>
                            </c:if>
                            <c:if test="${mdCostVo.type == '2'}">
                                <span class="col-sm-2  checkbox-inline">
                                    <input type="radio" id="construction" name="type" value="2" checked="checked">
                                    <label for="construction">在建工程</label>
                                </span>
                            </c:if>
                        </div>
                    </form>
                </div>
                <%@include file="/views/method/module/developmentCommon.jsp" %>
                <script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
                <%@include file="/views/project/tool/residueRatio.jsp" %>
                <%@include file="/views/method/module/economicIndicators.jsp" %>
                <div class="x_content">
                    <form class="form-horizontal" id="constructionFrm">
                        <%@include file="/views/method/module/costModule/constructionDetail.jsp" %>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="md_cost_form">
                        <input type="hidden" name="id" value="${mdCostVo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${mdCostVo.price}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                附件
                            </label>
                            <div class="col-sm-3">
                                <div id="_report_file"></div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--填写表单-->
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="application/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCostVo.id}'
            },
            editFlag: false,
            deleteFlag: false
        })
    });

    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>

