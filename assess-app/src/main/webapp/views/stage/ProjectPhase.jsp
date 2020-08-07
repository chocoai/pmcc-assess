<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>阶段事项</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <form id="frmQuery" class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-group form-inline">
                                                        <label class="col-md-1 col-form-label">项目大类:</label>
                                                        <div class="col-sm-3">
                                                            <select name="classId" id="classId"
                                                                    class="form-control input-full">
                                                                <option value="">-请选择-</option>
                                                                <c:forEach var="item" items="${projectClassList}">
                                                                    <option value="${item.id}">${item.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <label class="col-md-1 col-form-label">项目类型:</label>
                                                        <div class="col-sm-3">
                                                            <select name="typeId" id="typeId"
                                                                    class="form-control input-full">
                                                                <option value="" selected="selected">-请选择-</option>
                                                            </select>
                                                        </div>
                                                        <label class="col-md-1 col-form-label">项目类别:</label>
                                                        <div class="col-sm-3">
                                                            <select name="categoryId" id="categoryId"
                                                                    class="form-control input-full">
                                                                <option value="" selected="selected">-请选择-</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="work_stage_wizard" class="form_wizard wizard_horizontal"></div>
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <p id="modelListToolbar">
                                                        <button style="margin-left: 10px" class="btn btn-success btn-sm" type="button" id="create_work_stage" data-toggle="modal"><span class="btn-label"><i class="fa fa-plus"></i></span>新增项目阶段</button>
                                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button" id="create_project_phase" data-toggle="modal"><span class="btn-label"><i class="fa fa-plus"></i></span>新增工作事项</button>
                                                    </p>
                                                    <table class="table table-bordered" id="project_phase_list_table">
                                                        <!-- cerare document add ajax data-->
                                                    </table>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<!-- start: 项目阶段编辑modal -->
<%@include file="/views/stage/share/workStageEdit.jsp" %>
<!-- end: 项目阶段编辑modal -->

<!-- start: 编辑工作事项 -->
<%@include file="/views/stage/share/workPhaseEdit.jsp" %>
<!-- end: 编辑工作事项 -->

<script type="text/javascript" src="/pmcc-bpm/js/bpm-approval-role-utils.js?v=${assessVersion}"></script>
<script type="text/javascript" src="/pmcc-bpm/js/bpm-box-utils.js?v=${assessVersion}"></script>
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
            projectWorkStagePhaseObj.loadCategory(typeId);
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
