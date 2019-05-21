<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <!--填写表单-->
            <c:forEach items="${surveyAssetInventoryRightRecordList}" var="item" varStatus="status">
                <div class="x_panel ">
                    <div class="x_title collapse-link" onclick="loadSurveyAssetInventoryRightList(this,'${item.id}')">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h3>
                            他权分组（0${status.count}）
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content collapse">
                        <form class="form-horizontal" id="surveyAssetInventoryRightRecordForm${item.id}">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        权证信息
                                    </label>
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <label class="form-control">${item.recordNames}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-1  col-xs-111  col-sm-111  col-md-111  col-lg-111 ">
                                    <table class="table table-bordered" id="tbList${item.id}">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        FileUtils.getFileShows({
                            target: "specialCaseFile${status.count}",
                            formData: {
                                tableName: AssessDBKey.SurveyAssetInventoryRightRecord,
                                tableId: ${item.id}
                            },
                            deleteFlag: false
                        })
                    })
                </script>
            </c:forEach>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">

    //加载 他项权利列表
    function loadAssetRightList(table, inventoryRightRecordId) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'remark', title: '他项权利描述', width: '40%'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#" +table).bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}', inventoryRightRecordId: inventoryRightRecordId
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, false);
    }

    function loadSurveyAssetInventoryRightList(that, id) {
        var item = $(that).next();
        var table = item.find("table").eq(0);
        loadAssetRightList(table.attr("id"),id);
    }

    function saveform() {
        saveApprovalform("");
    }
</script>
</body>
</html>
