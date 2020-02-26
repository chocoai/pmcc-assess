<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js?v=${assessVersion}"></script>
    <link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
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

                    <!-- 引入假设开发法模块 -->
                    <%@include file="/views/method/marketDevelopmentIndex.jsp" %>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">

                                <input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>

                                <form class="form-horizontal" id="md_development_form">
                                    <input type="hidden" name="id" value="${mdDevelopment.id}">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    单价
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input type="text" class="form-control input-full" data-rule-number="true" required
                                                           name="price" value="${mdDevelopment.price}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    附件
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input id="report_file" name="report_file" type="file" multiple="false">
                                                    <div id="_report_file"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>


<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-table-editable.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>

<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdDevelopment,
                tableId: '${mdDevelopment.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdDevelopment,
                tableId: '${mdDevelopment.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });

    function submit() {
        var item = formSerializeArray($("#md_development_form")) ;
        development.valid(function () {
            var data = development.getFomData();
            if (item){
                if ($.isNumeric(item.price)){
                    data.price =  item.price;
                }
            }
            console.log(data);
            if ("${processInsId}" != "0") {
                submitEditToServer(JSON.stringify(data));
            } else {
                submitToServer(JSON.stringify(data));
            }
        });
    }
</script>

</html>

