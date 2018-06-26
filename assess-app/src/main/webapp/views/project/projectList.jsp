<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>

</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <%@include file="/views/project/projectCenterHeader.jsp" %>
                            <table id="tb_projectList" class="table table-bordered">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="div_more" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="h4_modeTitle">更多提交工作成果</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <div class="input-group">
                                <input type="text" id="moreSearch" onkeypress="reloadMorePanel();" class="form-control" placeholder="查询">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="button" onclick="reloadMorePanel();"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                            <div class="drop-down-wrapper ps-container">
                                <div class="panel-body" id="morePanel">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var taskDataList = {};
    var planDataList = {};
    var allTaskDataList = {};
    var moreType = "";
    var currIndex = "";
    $(function () {
        loadProjectList();

    })

    function loadMoreTask(type, index) {
        reloadMorePanel(type, index);
        $('#div_more').modal({backdrop: 'static', keyboard: false});
    }

    function reloadMorePanel(type, index) {
        if (index == "") {
            return false;
        }
        var moreSearch = $("#moreSearch").val();


        var url = "";
        switch (type + "") {
            case "0": {
                var html = "";
                var taskData = taskDataList["rowIndex" + index];
                for (var i = 0; i < taskData.length; i++) {
                    var j = taskData[i];
                    if (moreSearch) {
                        if (j.value.indexOf(moreSearch) >= 0) {
                            html += bottonHtml(i, j.id, j, "btn-warning");
                        }
                    }
                    else {
                        html += bottonHtml(i, j.id, j, "btn-warning");
                    }
                }
                $("#morePanel").html(html);
                break;
            }
            case "1":
            case "3": {
                var html = "";
                var taskData = planDataList["rowIndex" + index];
                for (var i = 0; i < taskData.length; i++) {
                    var j = taskData[i];
                    if (moreSearch) {
                        if (j.value.indexOf(moreSearch) >= 0) {
                            html += bottonHtml(i, j.planId, j, "btn-info");
                        }
                    }
                    else {
                        html += bottonHtml(i, j.planId, j, "btn-info");
                    }
                }
                $("#morePanel").html(html);
                break;
            }
            case "2": {
                var html = "";
                var taskData = allTaskDataList["rowIndex" + index];
                for (var i = 0; i < taskData.length; i++) {
                    var j = taskData[i];
                    if (moreSearch) {
                        if (j.value.indexOf(moreSearch) >= 0) {
                            html += bottonHtml(i, j.planId, j, "btn-success");
                        }
                    }
                    else {
                        html += bottonHtml(i, j.planId, j, "btn-success");
                    }
                }
                $("#morePanel").html(html);
                break;
            }
            default: {
                break;
            }
        }

    }
    function bottonHtml(i, id, j, css) {
        var html = "<button type='button' style='margin-left: 5px;margin-bottom: 5px;'";
        html += "class='btn " + css + " btn-sm'";
        html += " onclick='loadPage(" + id + "," + j.model + ")'> ";
        html += (i + 1) + " | " + j.planDetailsName;
        html += " </button>";
        return html;
    }
    function loadProjectList() {
        var cols = [];
        cols.push({field: 'id', title: '项目编号'});
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({field: 'projectClasssName', title: '项目类型'});
        cols.push({field: 'projectTypeName', title: '项目类别'});
        cols.push({field: 'projectCategoryName', title: '项目范围'});
        cols.push({field: 'projectStatus', title: '项目状态'});
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        TableInit("tb_projectList", "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {}, {
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //审批
    function loadPage(id, model) {
        var url = "";
        switch (model + "") {
            case "0": {
                url = "${pageContext.request.contextPath}/ProjectTask/projectTaskIndex?responsibilityId=";
                url = url + id;
                break;
            }
            case "1":
            case "3": {
                url = "${pageContext.request.contextPath}/ProjectPlan/planIndex?planId=";
                url = url + id;
                break
            }
            case "2": {
                url = "${pageContext.request.contextPath}/projectTaskAll/projectTaskAllIndex?planId=";
                url = url + id;
                break;
            }
            default: {
                break;
            }
        }
        openWin(url, function () {
            search();
        });
    }
    function search() {
        TableReload("tb_projectList", "${pageContext.request.contextPath}/projectCenter/getProjectList", {projectName: $("#projectName").val()});
    }
</script>
</body>
</html>
