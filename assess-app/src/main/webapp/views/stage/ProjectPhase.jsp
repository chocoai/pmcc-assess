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
                            <form id="frm" class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            项目类型:
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="classId" id="classId" class="form-control">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${projectClassList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            项目类别:
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="typeId" id="typeId" class="form-control">
                                                <option value="" selected="selected">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            项目范围:
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="categoryId" id="categoryId" class="form-control">
                                                <option value="" selected="selected">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="x_panel">
                                <div id="work_stage_wizard" class="form_wizard wizard_horizontal">
                                </div>
                                <p id="modelListToolbar">
                                    <button id="create_work_stage" type="button" class="btn btn-primary"
                                            data-toggle="modal">
                                        新增项目阶段
                                    </button>
                                    <button id="create_project_phase" type="button" class="btn btn-success"
                                            data-toggle="modal"> 新增工作事项
                                    </button>
                                </p>
                                <table id="project_phase_list_table" class="table table-bordered"></table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


<!-- start: 项目阶段编辑modal -->
<%@include file="/views/stage/share/workStageEdit.jsp" %>
<!-- end: 项目阶段编辑modal -->

<!-- start: 编辑工作事项 -->
<%@include file="/views/stage/share/workPhaseEdit.jsp" %>
<!-- end: 编辑工作事项 -->

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="/pmcc-bpm/js/bpm-approval-role-utils.js"></script>
<script type="text/javascript" src="/pmcc-bpm/js/bpm-box-utils.js"></script>
<script type="application/javascript">
    var projectWorkStagePhaseObj = {
        workStageWizard: $('#work_stage_wizard'),
        workPhaseTable: $('#project_phase_list_table')
    };

    //返回项目大类选择对象的数据结构
    projectWorkStagePhaseObj.getProjectClassSelect = function () {
        var groupKey = $("#classId option:selected").attr("groupKey");

        return groupKey;
    };

    //加载项目类别
    projectWorkStagePhaseObj.loadType = function loadCategory(classId) {
        $("#categoryId").empty();
        if (classId) {
            AssessCommon.getProjectClassifyList(classId, function (html, data) {
                $("#typeId").html(html);
            })
        }
    };

    //加载项目范围
    projectWorkStagePhaseObj.loadCategory = function loadCategory(typeId) {
        $("#categoryId").empty();
        if (typeId) {
            AssessCommon.getProjectClassifyList(typeId, function (html, data) {
                $("#categoryId").html(html);
            })
        }
    };

    $(function () {
        //-----初始化页面显示----//
        var typeId = $('#typeId').val();
        if (typeId) {
            //projectWorkStagePhaseObj.loadCategory(typeId);
            workStageObj.renderWorkStageStep(projectWorkStagePhaseObj.workStageWizard, typeId);
            workPhaseObj.rendWorkPhaseTable(projectWorkStagePhaseObj.workPhaseTable, typeId, 0);
            $('#create_work_stage').removeAttr('disabled');
        }
        //初始化一个空的表格
        workPhaseObj.rendWorkPhaseTable(projectWorkStagePhaseObj.workPhaseTable, 0, 0);
        //-----end---------//


        //-------------start: 界面事件触发 ---------//
        //项目类型
        $('#classId').change(function () {
            projectWorkStagePhaseObj.loadType($(this).val());
        })

        //项目类别下拉列表事件触发
        $('#typeId').change(function () {
            var typeId = $("#typeId").val();
            projectWorkStagePhaseObj.loadCategory(typeId);

            workStageObj.renderWorkStageStep(projectWorkStagePhaseObj.workStageWizard, typeId);
            workPhaseObj.rendWorkPhaseTable(projectWorkStagePhaseObj.workPhaseTable, typeId, 0);

            $('#create_work_stage').removeAttr('disabled');
            $('#create_project_phase').attr('disabled', true);
        });
        //项目类别下拉列表事件触发
        $('#categoryId').change(function () {
            var workStage = $("#workStageId");
            var typeId = $("#typeId").val();
            var categoryId = $('#categoryId').val();

            workPhaseObj.rendWorkPhaseTable(projectWorkStagePhaseObj.workPhaseTable, typeId, categoryId);

            workStageObj.renderWorkStageDropList(workStage, typeId);

        });


        //新增项目阶段按钮触发事件
        $('#create_work_stage').click(function () {
            var typeId = $("#typeId").val();
            workStageObj.editWorkStage(null, typeId);
        });
        //新增工作事项按钮触发事件
        $('#create_project_phase').click(function () {
            var typeId = $("#typeId").val();
            var categoryId = $('#categoryId').val();
            workPhaseObj.createWorkPhase(typeId, categoryId);
        });
        //-------------end: 界面事件触发 ---------//
    });

</script>

</html>
