<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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

                    <!-- 填写表单 start -->
                  <%--  <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${areaGroup.areaName}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th class="hidden-xs">估价对象</th>
                                                    <th class="hidden-xs">已抵押担保的债权数额总价(元)</th>
                                                    <th class="hidden-xs">拖欠的建设工程价款总价(元)</th>
                                                    <th class="hidden-xs">其它法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">估价师知悉的法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">他权明细</th>
                                                </tr>
                                                </thead>
                                                <tbody id="tbody_data_section">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 control-label">
                                                    附件
                                                </label>
                                                <div class="col-md-11">
                                                    <div id="_apply_file">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>--%>
                    <jsp:include page="/views/project/stageCompile/module/compileInfoModule.jsp"></jsp:include>

                    <%@include file="/views/share/form_approval.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<input type="hidden" id="compileReportDetailsJSON" value='${compileReportDetailsJSON}'>

<script type="text/javascript">
    $(function () {
        //初始化
        compileInfoModule.init({
            readonly: true,
            compileInfo: JSON.parse($("#compileReportDetailsJSON").val())
        });
    })
</script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>
</html>

