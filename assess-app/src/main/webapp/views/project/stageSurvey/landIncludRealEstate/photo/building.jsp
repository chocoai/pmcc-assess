<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>附件上传页面</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        附件上传页面

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            楼栋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicBuildingFrm">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <input type="hidden" name="id" value="${basicBuilding.id}">

                                                    <div class="row form-group" id="navButtonBuildGroupFileId">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">平面图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_floor_plan" placeholder="上传附件" class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_building_floor_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">外装图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_figure_outside" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_building_figure_outside"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">外观图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_floor_Appearance_figure" placeholder="上传附件"
                                                                           class="form-control input-full" type="file">
                                                                    <div id="_building_floor_Appearance_figure"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/project/stageSurvey/common/canvasQRcodePagination.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        buildingCommon.initById('${basicBuilding.id}');
    })
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">


</script>

