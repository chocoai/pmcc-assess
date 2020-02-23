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
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${declareRecord.name}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn btn-icon btn-link btn-primary btn-sm"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="basicBatchApplyFrm" class="form-horizontal">
                                    <input type="hidden" name="id" value="${applyBatch.id}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" id="estateId" name="estateId" value="${applyBatch.estateId}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">

                                                <label class="col-sm-1">
                                                    大类<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-2">
                                                    <select class="form-control input-full" name="classify"
                                                            onchange="formClassifyChange();"
                                                            required>
                                                        <option value="">-请选择-</option>
                                                        <c:if test="${not empty formClassifyList}">
                                                            <c:forEach var="item" items="${formClassifyList}">
                                                                <option value="${item.id}"
                                                                        data-key="${item.fieldName}"
                                                                    ${item.id eq applyBatch.classify?'selected="selected"':''}>${item.name}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">
                                                    类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-2">
                                                    <select class="form-control input-full" name="type"
                                                            onchange="saveBasicApplyBatch();" required>
                                                        <option value="">-请选择-</option>
                                                        <c:if test="${not empty examineFormTypeList}">
                                                            <c:forEach var="item" items="${examineFormTypeList}">
                                                                <option value="${item.key}" ${item.key eq applyBatch.type?'selected="selected"':''}>${item.value}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>

                                                <c:if test="${not empty declareRecord}">
                                                    <label class="col-sm-1">
                                                        建筑状态<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control input-full" name="buildingStatus"
                                                                onchange="editBuildingStatus();" required>
                                                            <option value="">-请选择-</option>
                                                            <c:if test="${not empty buildingStatusList}">
                                                                <c:forEach var="item" items="${buildingStatusList}">
                                                                    <option value="${item.id}" ${item.id eq applyBatch.buildingStatus?'selected="selected"':''}>${item.name}</option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-md-3" style="max-height: 500px;overflow: auto;">
                                                <ul id="ztree" class="ztree"></ul>
                                            </div>
                                            <div class="col-md-8" id="btnGroup">
                                                <button class="btn btn-sm btn-success deserveTool"
                                                   onclick="batchTreeTool.showAddModal()">
                                                    新增
                                                </button>
                                                <button class="btn btn-sm btn-primary masterTool"
                                                   onclick=" batchTreeTool.getAndEditDetail();">
                                                    编辑
                                                </button>
                                                <button class="btn btn-sm btn-warning deleteTool"
                                                   onclick=" batchTreeTool.deleteDetail();">
                                                    删除
                                                </button>
                                                <button class="btn btn-sm btn-primary"
                                                   onclick=" batchTreeTool.expandAll(true);">
                                                    全部展开
                                                </button>
                                                <button class="btn btn-sm btn-primary"
                                                   onclick=" batchTreeTool.expandAll(false);">
                                                    全部收起
                                                </button>
                                                <button class="btn btn-sm btn-primary fillInformation deserveTool"
                                                   onclick="batchTreeTool.fillInformation();">
                                                    填写信息
                                                </button>
                                                <button style="display: none"
                                                   class="btn btn-sm btn-primary fillInformation limitTool"
                                                   onclick="batchTreeTool.checkInfo();">
                                                    查看信息
                                                </button>
                                                <button class="btn btn-sm btn-warning copy" onclick="batchTreeTool.copy();">
                                                    复制
                                                </button>
                                                <button class="btn btn-sm btn-warning paste deserveTool"
                                                   onclick="batchTreeTool.paste();">
                                                    粘贴
                                                </button>
                                                <button class="btn btn-sm btn-warning paste masterTool"
                                                   onclick="batchTreeTool.deepCopy();">
                                                    深复制
                                                </button>
                                                <c:if test="${not empty declareRecord}">
                                                    <button class="btn btn-sm btn-warning paste alternativeCase"
                                                       style="display: none"
                                                       onclick="batchTreeTool.addToAlternative();">
                                                        添加到备选案例
                                                    </button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%--<%@include file="/views/share/form_log.jsp" %>--%>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
<div id="detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_detail" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <input type="hidden" name="pid">
                                <input type="hidden" name="applyBatchId">
                                <input type="hidden" name="tableName">
                                <input type="hidden" name="tableId">
                                <div id="detailContent">
                                </div>

                            </div>


                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="batchTreeTool.saveItemData()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>
<div id="detail_modal_b" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_detail_b" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <input type="hidden" name="pid">
                                <input type="hidden" name="applyBatchId">
                                <input type="hidden" name="tableName">
                                <input type="hidden" name="tableId">
                                <div id="detailContent_b">
                                </div>
                            </div>


                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="batchTreeTool.saveItemData()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>



<%--
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>
                        ${projectPlanDetails.projectPhaseName}
                        <small>${declareRecord.name}</small>
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content" style="min-height: 300px;">
                    <form id="basicBatchApplyFrm" class="form-horizontal">
                        <input type="hidden" name="id" value="${applyBatch.id}">
                        <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                        <input type="hidden" id="estateId" name="estateId" value="${applyBatch.estateId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    大类<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <select class="form-control input-full" name="classify" onchange="formClassifyChange();"
                                            required>
                                        <option value="">-请选择-</option>
                                        <c:if test="${not empty formClassifyList}">
                                            <c:forEach var="item" items="${formClassifyList}">
                                                <option value="${item.id}" data-key="${item.fieldName}"
                                                    ${item.id eq applyBatch.classify?'selected="selected"':''}>${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    类型<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <select class="form-control input-full" name="type" onchange="saveBasicApplyBatch();" required>
                                        <option value="">-请选择-</option>
                                        <c:if test="${not empty examineFormTypeList}">
                                            <c:forEach var="item" items="${examineFormTypeList}">
                                                <option value="${item.key}" ${item.key eq applyBatch.type?'selected="selected"':''}>${item.value}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <c:if test="${not empty declareRecord}">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        建筑状态<span class="symbol required"></span>
                                    </label>
                                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                        <select class="form-control input-full" name="buildingStatus"
                                                onchange="editBuildingStatus();" required>
                                            <option value="">-请选择-</option>
                                            <c:if test="${not empty buildingStatusList}">
                                                <c:forEach var="item" items="${buildingStatusList}">
                                                    <option value="${item.id}" ${item.id eq applyBatch.buildingStatus?'selected="selected"':''}>${item.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </form>
                    <div class="col-md-3 col-lg-offset-1" style="max-height: 500px;overflow: auto;">
                        <ul id="ztree" class="ztree"></ul>
                    </div>
                    <div class="col-md-8" id="btnGroup">
                        <a class="btn btn-sm btn-success deserveTool" onclick="batchTreeTool.showAddModal()">
                            新增
                        </a>
                        <a class="btn btn-sm btn-primary masterTool" onclick=" batchTreeTool.getAndEditDetail();">
                            编辑
                        </a>
                        <a class="btn btn-sm btn-warning deleteTool" onclick=" batchTreeTool.deleteDetail();">
                            删除
                        </a>
                        <a class="btn btn-sm btn-primary" onclick=" batchTreeTool.expandAll(true);">
                            全部展开
                        </a>
                        <a class="btn btn-sm btn-primary" onclick=" batchTreeTool.expandAll(false);">
                            全部收起
                        </a>
                        <a class="btn btn-sm btn-primary fillInformation deserveTool"
                           onclick="batchTreeTool.fillInformation();">
                            填写信息
                        </a>
                        <a style="display: none" class="btn btn-sm btn-primary fillInformation limitTool"
                           onclick="batchTreeTool.checkInfo();">
                            查看信息
                        </a>
                        <a class="btn btn-sm btn-warning copy" onclick="batchTreeTool.copy();">
                            复制
                        </a>
                        <a class="btn btn-sm btn-warning paste deserveTool" onclick="batchTreeTool.paste();">
                            粘贴
                        </a>
                        <a class="btn btn-sm btn-warning paste masterTool" onclick="batchTreeTool.deepCopy();">
                            深复制
                        </a>
                        <c:if test="${not empty declareRecord}">
                            <a class="btn btn-sm btn-warning paste alternativeCase" style="display: none"
                               onclick="batchTreeTool.addToAlternative();">
                                添加到备选案例
                            </a>
                        </c:if>
                    </div>
                    <div id="detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static"
                         aria-hidden="true"
                         role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title">添加</h3>
                                </div>
                                <form id="frm_detail" class="form-horizontal">
                                    <input type="hidden" name="id" value="0">
                                    <div class="modal-body" style="overflow: auto; ">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="x_content">
                                                    <input type="hidden" name="pid">
                                                    <input type="hidden" name="applyBatchId">
                                                    <input type="hidden" name="tableName">
                                                    <input type="hidden" name="tableId">
                                                    <div class="form-group">
                                                        <div class="x-valid">
                                                            <div id="detailContent">
                                                            </div>
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
                                        <button type="button" id="btnSave" class="btn btn-primary"
                                                onclick="batchTreeTool.saveItemData()">
                                            保存
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="detail_modal_b" class="modal fade bs-example-modal-lg" data-backdrop="static"
                         aria-hidden="true"
                         role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">编辑</h4>
                                </div>
                                <form id="frm_detail_b" class="form-horizontal">
                                    <input type="hidden" name="id" value="0">
                                    <div class="modal-body" style="overflow: auto; ">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="x_content">
                                                    <input type="hidden" name="pid">
                                                    <input type="hidden" name="applyBatchId">
                                                    <input type="hidden" name="tableName">
                                                    <input type="hidden" name="tableId">
                                                    <div class="form-group">
                                                        <div class="x-valid">
                                                            <div id="detailContent_b">
                                                            </div>
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
                                        <button type="button" class="btn btn-primary"
                                                onclick="batchTreeTool.saveEditItemData()">
                                            保存
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_apply.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
--%>
</body>

</html>
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            batchTreeTool.ztreeInit(${applyBatch.id});
        }
    });

    //任务提交
    function submit(useBox) {
        // if (AssessDicKey.projectSurveyFormClassifyMultiple == getClassifyKey()) {
        //     if (getStandardCount() <= 0) {
        //         Alert("申请中至少包含一个标准对象");
        //         return;
        //     }
        // }
        var formData = {};
        formData.declareId = "${declareRecord.id}";
        formData.projectId = "${projectInfo.id}";
        formData.planDetailsId = "${projectPlanDetails.id}";
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData), useBox);
        }
    }

    function getClassifyKey() {
        var dataKey = $("#basicBatchApplyFrm").find('[name=classify]').find('option:selected').attr("data-key");
        return dataKey;
    }

    //表单大类change
    function formClassifyChange() {
        var dataKey = getClassifyKey();
        if (AssessDicKey.projectSurveyFormClassifySingel == dataKey || AssessDicKey.projectSurveyFormClassifyLandOnly == dataKey) {
            $("#formClassifySingel").show();
            $("#btnGroup").find('.btn').not('.fillInformation').hide();
            $("#formClassifyMultiple").hide();
        } else {
            $("#formClassifySingel").hide();
            $("#formClassifyMultiple").show();
            $("#btnGroup").find('.btn').show();
        }
        initBasicApplyBatchInfo();
    }

    //初始化信息
    function initBasicApplyBatchInfo() {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/initBasicApplyBatchInfo",
            type: "post",
            dataType: "json",
            data: {
                planDetailsId: '${projectPlanDetails.id}',
                classify: $("#basicBatchApplyFrm").find('[name=classify]').val(),
                type: $("#basicBatchApplyFrm").find('[name=type]').val(),
                buildingStatus: $("#basicBatchApplyFrm").find('[name=buildingStatus]').val()
            },
            success: function (result) {
                if (result.ret) {
                    $("#basicBatchApplyFrm").find('[name=id]').val(result.data.id);
                    $("#basicBatchApplyFrm").find('[name=estateId]').val(result.data.estateId);
                    batchTreeTool.ztreeInit(result.data.id);
                } else {
                    AlertError("初始化失败，失败原因:"+result.errmsg);
                }
            }
        });
    }

    //保存主表信息
    function saveBasicApplyBatch() {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveBasicApplyBatch",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(formSerializeArray($("#basicBatchApplyFrm")))
            },
            success: function (result) {
                if (result.ret) {
                    $("#basicBatchApplyFrm").find('[name=id]').val(result.data.id);
                } else {
                    AlertError("保存失败,失败原因:"+result.errmsg);
                }
            }
        });
    }

    //修改权证建筑状态
    function editBuildingStatus() {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/editBuildingStatus",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(formSerializeArray($("#basicBatchApplyFrm")))
            },
            success: function (result) {
                if (result.ret) {

                } else {
                    AlertError("失败,失败原因:"+result.errmsg);
                }
            }
        });
    }
</script>
<script type="text/javascript">
    var batchApply = undefined;
    var setting = {
        view: {
            fontCss: setFontCss,
        },
        data: {
            key: {
                name: "displayName"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },// 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                batchTreeTool.showFunctionBtn();
            }
        }
    };

    var batchTreeTool = function () {
    };

    //初始化tree
    batchTreeTool.ztreeInit = function (basicApplyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {basicApplyBatchId: basicApplyBatchId},
            type: 'get',
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                //展开第一级，选中根节点
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(rootNode);
                zTreeObj.expandAll(true);
                zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, rootNode);//调用事件
            }
        })
    }
    //初始化treeByPlanDetailsId
    batchTreeTool.ztreeInitByPlanDetailsId = function (planDetailsId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getTreeByPlanDetailsId',
            data: {
                planDetailsId: planDetailsId
            },
            type: 'get',
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                //展开第一级，选中根节点
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(rootNode);
                zTreeObj.expandAll(true);
            }
        })
    }

    //添加数据打开modal
    batchTreeTool.showAddModal = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        console.log(node.bisStructure + "=====bisStructure")
        if (node.bisStructure) {
            notifyInfo("构筑物下无法继续添加节点。");
            return false;
        }
        var html = "";
        switch (level) {
            case 0: {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_building");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-1 control-label'>";
                html += "楼栋编号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "构筑物";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<select id='bisStructure' name='bisStructure' required class='form-control input-full'>";
                html += "<option value=''>--请选择--</option>";
                html += "<option value='true'>是</option>";
                html += "<option value='false'>否</option>";
                html += "</select>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case 1: {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_unit");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-1 control-label'>";
                html += "单元编号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case 2: {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_house");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "房号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }

            case 3: {
                notifyInfo("房屋下无法继续添加节点。");
                return false;
                break;
            }

        }
        $("#detailContent").empty().append(html);
        $("#frm_detail").find("input[name='applyBatchId']").val($("#basicBatchApplyFrm").find('[name=id]').val());
        $("#frm_detail").find("input[name='pid']").val(node.id);
        $("#frm_detail").find("input[name='executor']").val(node.creator);
        $("#frm_detail").find("input[name='executorName']").val(node.creatorName);
        $("#detail_modal").modal();
    }

    //保存明细
    batchTreeTool.saveItemData = function () {
        if (!$("#frm_detail").valid()) {
            return false;
        }
        var formData = formParams("frm_detail");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveItemData",
            type: "post",
            data: {
                formData: JSON.stringify(formData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    var node = zTreeObj.getSelectedNodes()[0];
                    var childNode = zTreeObj.addNodes(node, {
                        id: result.data.id,
                        pid: result.data.pid,
                        tableId: result.data.tableId,
                        type: result.data.tableName.replace('tb_basic_', ''),
                        displayName: result.data.displayName + '(' + result.data.executorName + ')',
                        executor: result.data.executor,
                        executorName: result.data.executorName,
                        creator: result.data.creator,
                        creatorName: result.data.creatorName,
                        bisStructure: result.data.bisStructure
                    });

                    $('#detail_modal').modal('hide');
                } else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //编辑明细
    batchTreeTool.getAndEditDetail = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.level == 0) {
            notifyInfo("楼盘信息请在【填写信息】中修改。");
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/getAndEditDetail",
            data: {id: node.id},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    batchTreeTool.showEditModal(result.data);
                }
            }
        })
    }

    //编辑数据打开modal
    batchTreeTool.showEditModal = function (data) {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        var html = "";
        switch (level) {
            case 1: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_building");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "楼栋编号";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "构筑物";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<select id='bisStructure_b' name='bisStructure' required class='form-control input-full'>";
                html += "<option value=''>--请选择--</option>";
                html += "<option value='true'>是</option>";
                html += "<option value='false'>否</option>";
                html += "</select>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case 2: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_unit");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "单元编号";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case 3: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_house");
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-1 control-label'>";
                html += "房号";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>";
                html += "执行人";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-3'>";
                html += " <input type='hidden' id='executor' name='executor'>";
                html += "<input type='text' name='executorName' id='executorName' data-rule-maxlength='50' readonly class='form-control input-full' onclick='personSelect()'>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
        }
        $("#frm_detail_b").clearAll();
        $("#frm_detail_b").find("#detailContent_b").empty().append(html);
        $("#frm_detail_b").initForm(data);
        $("#bisStructure_b").val('' + data.bisStructure);
        $("#detail_modal_b").modal();
    }

    //保存编辑明细
    batchTreeTool.saveEditItemData = function () {
        if (!$("#frm_detail").valid()) {
            return false;
        }
        var formData = formParams("frm_detail_b");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveItemData",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(formData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    var node = zTreeObj.getSelectedNodes()[0];
                    node.id = result.data.id;
                    node.name = result.data.name;
                    node.displayName = result.data.displayName + '(' + result.data.executorName + ')';
                    node.pid = result.data.pid;
                    node.executor = result.data.executor;
                    node.creator = result.data.creator;
                    node.creatorName = result.data.creatorName;
                    node.executorName = result.data.executorName;
                    node.bisStructure = result.data.bisStructure;
                    zTreeObj.updateNode(node, false);
                    $('#detail_modal_b').modal('hide');
                } else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //删除明细
    batchTreeTool.deleteDetail = function () {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            if (result) {
                var node = zTreeObj.getSelectedNodes()[0];
                if (node.id == 0) {
                    notifyInfo("无法删除，请重新选择。");
                    return false;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicApplyBatch/deleteDetail",
                    data: {id: node.id},
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            //刷新树 如果存在同级节点 则移除当前节点 否则刷新上上级节点  最后默认选中上级节点
                            var parentNode = node.getParentNode();
                            zTreeObj.removeNode(node);
                            if (parentNode) {
                                zTreeObj.selectNode(parentNode);
                            }
                        } else {
                            AlertError("删除失败：" + result.errmsg);
                        }
                    }
                })
            }
        });
    }

    //进入填写信息页面
    batchTreeTool.fillInformation = function () {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var node = zTreeObj.getSelectedNodes()[0];
        var classify = $("#basicBatchApplyFrm").find('[name=classify]').val();
        var formType = $("#basicBatchApplyFrm").find('[name=type]').val();
        var url = '${pageContext.request.contextPath}/basicApplyBatch/fillInfo?';
        url += 'applyBatchId=' + $("#basicBatchApplyFrm").find('[name=id]').val();
        url += '&formClassify=' + classify;
        url += '&formType=' + formType;
        url += '&tbId=' + node.tableId;
        url += '&tbType=' + node.type;
        url += '&planDetailsId=${projectPlanDetails.id}';
        openWin(url, function () {
        })
    }

    //被复制对象
    batchTreeTool.beCopyObject = undefined;

    //调整因素复制
    batchTreeTool.copy = function (_this) {
        //设置被复制元素的id
        //显示出粘贴按钮
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.level == 0) {
            notifyInfo("不能复制楼盘，重新选择");
            return false;
        }
        batchTreeTool.beCopyObject = {};
        batchTreeTool.beCopyObject.id = node.id;
        batchTreeTool.beCopyObject.level = node.level;
        toastr.success("复制成功");
        $("#btnGroup").find('.paste').show();
    }

    //调整因素粘贴
    batchTreeTool.paste = function () {
        if (!batchTreeTool.beCopyObject) {
            notifyInfo('请选择被复制对象');
            return false;
        }
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.id == batchTreeTool.beCopyObject.id) {
            notifyInfo('不能复制粘贴自身');
            return false;
        }
        if (node.level != batchTreeTool.beCopyObject.level) {
            notifyInfo('请选择相应节点进行粘贴');
            return false;
        }
        bootbox.confirm("将覆盖原来数据，确认要粘贴么？", function (result) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatch/paste",
                data: {
                    sourceBatchDetailId: batchTreeTool.beCopyObject.id,
                    targeBatchDetailId: node.id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("粘贴成功");
                    }
                    else {
                        AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        });
    }

    //深复制
    batchTreeTool.deepCopy = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.level == 0) {
            notifyInfo("不能复制楼盘，重新选择")
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/deepCopy",
            data: {
                sourceBatchDetailId: node.id,
                planDetailsId: '${projectPlanDetails.id}'
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("复制成功");
                    batchTreeTool.ztreeInitByPlanDetailsId('${projectPlanDetails.id}');
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //添加到备选案例
    batchTreeTool.addToAlternative = function () {
        Loading.progressShow();
        var node = zTreeObj.getSelectedNodes()[0];
        var data = {};
        data.business_id = node.id;
        data.business_key = node.type;
        $.ajax({
            url: "${pageContext.request.contextPath}/basicAlternativeCase/addToAlternative",
            data: {
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功,添加成功");
                }
                else {
                    AlertError("添加失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //全部展开或收起
    batchTreeTool.expandAll = function (flag) {
        zTreeObj.expandAll(flag);
    }

    batchTreeTool.showFunctionBtn = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        //子任务控制按钮
        if ('${projectTaskMasterId}' != '${responsibilityId}') {
            $("#btnGroup").find('.btn.masterTool').hide();
            //是当前执行人时
            if (node.executor == '${userAccount}') {
                $("#btnGroup").find('.btn.deserveTool').show();
                $("#btnGroup").find('.btn.limitTool').hide();
                //删除按钮控制
                if (node.executor == node.creator) {
                    $("#btnGroup").find('.btn.deleteTool').show();
                } else {
                    $("#btnGroup").find('.btn.deleteTool').hide();
                }
            } else {
                $("#btnGroup").find('.btn.limitTool').show();
                $("#btnGroup").find('.btn.deserveTool').hide();
                $("#btnGroup").find('.btn.deleteTool').hide();
            }
        }
        if (node.executor == '${userAccount}') {
            $("#btnGroup").find('.btn.alternativeCase').show();
        } else {
            $("#btnGroup").find('.btn.alternativeCase').hide();
        }

    }

    //信息详情页面
    batchTreeTool.checkInfo = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var classify = $("#basicBatchApplyFrm").find('[name=classify]').val();
        var formType = $("#basicBatchApplyFrm").find('[name=type]').val();
        var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
        url += 'applyBatchId=' + $("#basicBatchApplyFrm").find('[name=id]').val();
        url += '&formClassify=' + classify;
        url += '&formType=' + formType;
        url += '&tableId=' + node.tableId;
        url += '&tbType=' + node.type;
        url += '&tableName=' + node.tableName;
        url += '&planDetailsId=${projectPlanDetails.id}';
        openWin(url, function () {
        })
    }

    function setFontCss(treeId, treeNode) {
        if (treeNode.executor != '${userAccount}') {
            return {color: "#AAAAAA"};
        } else {
            return {color: "black"};
        }
    }

    //选择人员
    function personSelect() {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $("#frm_detail").find("#executorName").val(data.name);
                    $("#frm_detail").find("#executor").val(data.account);
                    $("#frm_detail_b").find("#executorName").val(data.name);
                    $("#frm_detail_b").find("#executor").val(data.account);
                }
                else {
                    $("#frm_detail").find("#executorName").val('');
                    $("#frm_detail").find("#executor").val('');
                    $("#frm_detail_b").find("#executorName").val('');
                    $("#frm_detail_b").find("#executor").val('');
                }
            }
        });
    }
</script>
