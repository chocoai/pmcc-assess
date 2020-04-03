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
                        <div class="card full-height" style="min-height: 300px;">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <button type="button" class="btn btn-sm btn-info" style="margin-left: 10px;"
                                                onclick="batchTreeTool.showAlternativeSurveyModal();">
                                            引用楼盘
                                        </button>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-sm"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row col-md-12">
                                    <div class="col-md-9">
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick=" batchTreeTool.expandAll(true);">
                                            展开
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick=" batchTreeTool.expandAll(false);">
                                            收起
                                        </button>
                                        <button type="button" class="btn btn-sm btn-success normalTool"
                                                style="margin-left: 20px;"
                                                onclick="batchTreeTool.showAddModal()">
                                            新增
                                        </button>
                                        <button class="btn btn-sm btn-primary masterTool"
                                                onclick=" batchTreeTool.getAndEditDetail();">
                                            编辑
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning masterTool"
                                                onclick=" batchTreeTool.deleteDetail();">
                                            删除
                                        </button>

                                        <button type="button" style="margin-left: 20px;"
                                                class="btn btn-sm btn-primary fillInformation masterTool"
                                                onclick="batchTreeTool.fillInformation();">
                                            填写信息
                                        </button>
                                        <button type="button"
                                                class="btn btn-sm btn-primary fillInformation limitTool"
                                                onclick="batchTreeTool.checkInfo();">
                                            查看信息
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning copy"
                                                onclick="batchTreeTool.copy();">
                                            复制
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning paste masterTool"
                                                onclick="batchTreeTool.paste();">
                                            粘贴
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning masterTool"
                                                onclick="batchTreeTool.deepCopy();">
                                            深复制
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning masterTool"
                                                style="display: none"
                                                onclick="batchTreeTool.addToAlternative();">
                                            添加到备选案例
                                        </button>
                                        <ul id="ztree" class="ztree" style="margin-top: 10px;"></ul>
                                    </div>
                                    <div class="col-md-3">
                                        <form id="basicBatchApplyFrm" class="form-horizontal">
                                            <input type="hidden" name="id" value="${applyBatch.id}">
                                            <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                            <input type="hidden" id="estateId" name="estateId"
                                                   value="${applyBatch.estateId}">
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        大类<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
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
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        类型<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
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
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_apply.jsp" %>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="batchTreeTool.updateItemData()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>
<div id="reference_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">查勘楼盘</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group ">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                名称
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" data-rule-maxlength="50" placeholder="名称"
                                                       id="queryAlternativeName" class="form-control input-full">
                                            </div>
                                            <div class="col-sm-5">
                                                <button type="button" class="btn btn-sm btn-info"
                                                        onclick="batchTreeTool.loadAlternativeSurveyList();">
                                                    <span class="btn-label"><i class="fa fa-search"></i></span>
                                                    查询
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="basicAlternativeSurveyList">
                                        </table>
                                    </div>
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
            </div>

        </div>
    </div>
</div>

</body>

</html>
<%@include file="/views/project/tool/declareRecordModeView.jsp" %>
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            if (${!empty applyBatch.referenceApplyBatchId}) {
                batchTreeTool.ztreeInit(${applyBatch.referenceApplyBatchId});
            } else {
                batchTreeTool.ztreeInit(${applyBatch.id});
            }
        }
    });

    //任务提交
    function submit(useBox) {
        var formData = {};
        formData.declareId = "${declareRecord.id}";
        formData.projectId = "${projectId}";
        formData.planDetailsId = "${projectPlanDetails.id}";
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        } else {
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
                projectId: '${projectId}',
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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

    batchTreeTool.getApplyBatchId = function () {
        return $("#basicBatchApplyFrm").find('[name=id]').val();
    }

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
    var declareRecordsHtml = "";
    batchTreeTool.showAddModal = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var type = node.type;
        var html = "";
        var declareRecordId;
        var declareRecordName;
        var pNode = node;
        while (pNode) {
            if (pNode.declareRecordId) {
                declareRecordId = pNode.declareRecordId
                declareRecordName = pNode.declareRecordName
                break;
            }
            pNode = pNode.getParentNode();
        }
        var typeHtml = "";
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTableTypeList",
            data: {type: type},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (!jQuery.isEmptyObject(result.data)) {
                        typeHtml = "<label class='col-sm-1 control-label'>";
                        typeHtml += "表单类型";
                        typeHtml += "<span class='symbol required'></span>";
                        typeHtml += "</label>";
                        typeHtml += "<div class='col-sm-5'>";
                        typeHtml += "<select name='type' required class='form-control input-full' onchange='typeChange(this)'>";
                        typeHtml += "<option value=''>--请选择--</option>";
                        $.each(result.data, function (i, item) {
                            typeHtml += "<option value='" + item.key + "'>" + item.value + "</option>";
                        })
                        typeHtml += "</select>";
                        typeHtml += "</div>";
                        console.log(typeHtml)

                        switch (type) {
                            case "estate":
                            case "building.base": {
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += "<label class='col-sm-1 control-label'>";
                                html += "名称";
                                html += "<span class='symbol required'></span>";
                                html += "</label>";
                                html += " <div class='col-sm-5'>";
                                html += "<input type='hidden'  name='tableName' value='tb_basic_building'>";
                                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                                html += "</div>";
                                html += typeHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += declareRecordsHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                break;
                            }
                            case "building":
                            case "building.monolaye":
                            case "building.difference": {
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += "<label class='col-sm-1 control-label'>";
                                html += "名称";
                                html += "<span class='symbol required'></span>";
                                html += "</label>";
                                html += " <div class='col-sm-5'>";
                                html += "<input type='hidden'  name='tableName' value='tb_basic_unit'>";
                                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                                html += "</div>";
                                html += typeHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += declareRecordsHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                break;
                            }
                            case "unit": {
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += "<label class='col-sm-1 control-label'>";
                                html += "名称";
                                html += "<span class='symbol required'></span>";
                                html += "</label>";
                                html += " <div class='col-sm-5'>";
                                html += "<input type='hidden'  name='tableName' value='tb_basic_house'>";
                                html += "<input type='text'  name='name' class='form-control input-full' required value=''>";
                                html += "</div>";
                                html += typeHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                html += '<div class="row form-group">';
                                html += '<div class="col-md-12">';
                                html += '<div class="form-inline x-valid">';
                                html += declareRecordsHtml;
                                html += "</div>";
                                html += "</div>";
                                html += "</div>";
                                break;
                            }
                            case "house": {
                                notifyInfo('提示', "房屋下无法继续添加节点。");
                                return false;
                                break;
                            }
                        }
                        $("#detailContent").empty().append(html);
                        $("#frm_detail").find("input[name='applyBatchId']").val(node.applyBatchId);
                        $("#frm_detail").find("input[name='pid']").val(node.id);
                        $("#frm_detail").find("input[name='executor']").val(node.creator);
                        $("#frm_detail").find("input[name='executorName']").val(node.creatorName);
                        $("#frm_detail").find("input[name='declareRecordId']").val(declareRecordId);
                        $("#frm_detail").find("input[name='declareRecordName']").val(declareRecordName);
                        $("#detail_modal").modal();
                    } else {
                        notifyInfo('提示', "没有下级表单");
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            }

        })


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
                    notifySuccess('成功', '保存成功');
                    var node = zTreeObj.getSelectedNodes()[0];
                    var childNode = zTreeObj.addNodes(node, {
                        id: result.data.id,
                        pid: result.data.pid,
                        tableId: result.data.tableId,
                        type: result.data.type,
                        displayName: result.data.displayName + '(' + result.data.executorName + ')',
                        textName: result.data.displayName,
                        executor: result.data.executor,
                        executorName: result.data.executorName,
                        creator: result.data.creator,
                        creatorName: result.data.creatorName,
                        bisStructure: result.data.bisStructure,
                        declareRecordId: result.data.declareRecordId,
                        applyBatchId: result.data.applyBatchId,
                        declareRecordName: result.data.declareRecordName
                    });

                    $('#detail_modal').modal('hide');
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        });
    }


    //编辑明细
    batchTreeTool.getAndEditDetail = function () {
        var node = zTreeObj.getSelectedNodes()[0];

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
        var type = node.type;
        var html = "";
        switch (type) {
            case "estate": {
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-1 control-label'>";
                html += "名称";
                html += "</label>";
                html += " <div class='col-sm-5'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += declareRecordsHtml;

                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case "building": {
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "名称";
                html += "</label>";
                html += " <div class='col-sm-5'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += declareRecordsHtml;

                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case "unit": {
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "名称";
                html += "</label>";
                html += " <div class='col-sm-5'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += declareRecordsHtml;
                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
            case "house": {
                html += '<div class="row form-group">';
                html += '<div class="col-md-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>";
                html += "名称";
                html += "</label>";
                html += " <div class='col-sm-5'>";
                html += "<input type='text'  name='name' class='form-control input-full' required>";
                html += "</div>";
                html += declareRecordsHtml;
                html += "</div>";
                html += "</div>";
                html += "</div>";
                break;
            }
        }
        $("#frm_detail_b").clearAll();
        $("#frm_detail_b").find("#detailContent_b").empty().append(html);
        $("#frm_detail_b").initForm(data);
        if (type == "house") {
            $("#frm_detail_b").find("input[name='declareRecordName']").attr("required", true);
        } else {
            $("#frm_detail_b").find("input[name='declareRecordName']").attr("required", false);
        }
        $("#bisStructure_b").val('' + data.bisStructure);
        $("#detail_modal_b").modal();
    }

    //保存编辑明细
    batchTreeTool.updateItemData = function () {
        if (!$("#frm_detail_b").valid()) {
            return false;
        }
        var formData = formParams("frm_detail_b");

        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/updateItemData",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(formData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    var node = zTreeObj.getSelectedNodes()[0];
                    node.id = result.data.id;
                    node.name = result.data.name;
                    node.displayName = result.data.displayName + '(' + result.data.executorName + ')';
                    node.textName = result.data.displayName;
                    node.pid = result.data.pid;
                    node.type = result.data.type;
                    node.executor = result.data.executor;
                    node.creator = result.data.creator;
                    node.creatorName = result.data.creatorName;
                    node.executorName = result.data.executorName;
                    node.bisStructure = result.data.bisStructure;
                    node.declareRecordId = result.data.declareRecordId;
                    node.applyBatchId = result.data.applyBatchId;
                    node.declareRecordName = result.data.declareRecordName;
                    zTreeObj.updateNode(node, false);
                    $('#detail_modal_b').modal('hide');
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //删除明细
    batchTreeTool.deleteDetail = function () {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            var node = zTreeObj.getSelectedNodes()[0];
            if (node.id == 0) {
                notifyInfo('提示', "无法删除，请重新选择。");
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
                        notifySuccess("成功", "删除成功");
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
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
        var url = '${pageContext.request.contextPath}/basicApplyBatch/informationEdit?';
        url += 'applyBatchId=' + node.applyBatchId;
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
        if (node.type == "estate") {
            notifyInfo('提示', "不能复制楼盘，重新选择");
            return false;
        }
        batchTreeTool.beCopyObject = {};
        batchTreeTool.beCopyObject.id = node.id;
        batchTreeTool.beCopyObject.type = node.type;
        notifySuccess('成功', "复制成功");
        if (node.executor == '${userAccount}') {
            $("#btnGroup").find('.paste').show();
        }
    }

    //调整因素粘贴
    batchTreeTool.paste = function () {
        if (!batchTreeTool.beCopyObject) {
            notifyInfo('提示', '请选择被复制对象');
            return false;
        }
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.id == batchTreeTool.beCopyObject.id) {
            notifyInfo('提示', '不能复制粘贴自身');
            return false;
        }
        if (node.type != batchTreeTool.beCopyObject.type) {
            notifyInfo('提示', '请选择相应节点进行粘贴');
            return false;
        }
        AlertConfirm("确认要粘贴么", "将覆盖原来数据", function (result) {
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
                        notifySuccess("成功", "粘贴成功");
                    } else {
                        AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        });
    }

    //深复制
    batchTreeTool.deepCopy = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.type == "estate") {
            notifyInfo('提示', "不能复制楼盘，重新选择")
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
                    notifySuccess('成功', "复制成功");
                    batchTreeTool.ztreeInitByPlanDetailsId('${projectPlanDetails.id}');
                } else {
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
                    notifySuccess("成功", "添加成功");
                } else {
                    AlertError("失败", "失败原因:" + result.errmsg);
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
        //batchTreeTool.getTypeHtml(node.type);
        batchTreeTool.getDeclareRecordsHtml();
        //是当前执行人时
        if (node.executor == '${userAccount}') {
            $("#btnGroup").find('.btn.masterTool').show();
            $("#btnGroup").find('.btn.limitTool').hide();

        } else {
            $("#btnGroup").find('.btn.limitTool').show();
            $("#btnGroup").find('.btn.masterTool').hide();
        }


    }

    //获取该节点下子级表单类型
    batchTreeTool.getTypeHtml = function (type) {
        typeHtml = "";
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTableTypeList",
            data: {type: type},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        typeHtml = "<label class='col-sm-1 control-label'>";
                        typeHtml += "表单类型";
                        typeHtml += "<span class='symbol required'></span>";
                        typeHtml += "</label>";
                        typeHtml += "<div class='col-sm-5'>";
                        typeHtml += "<select name='type' required class='form-control input-full' onchange='typeChange(this)'>";
                        typeHtml += "<option value=''>--请选择--</option>";
                        $.each(result.data, function (i, item) {
                            typeHtml += "<option value='" + item.key + "'>" + item.value + "</option>";
                        })
                        typeHtml += "</select>";
                        typeHtml += "</div>";
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            }

        })
    }

    //获取该节点下子级表单类型
    batchTreeTool.getDeclareRecordsHtml = function () {
        declareRecordsHtml = "<label class='col-sm-1 control-label'>";
        declareRecordsHtml += "权证";
        declareRecordsHtml += "</label>";
        declareRecordsHtml += "<div class='col-sm-5'>";
        declareRecordsHtml += "<div class='input-group'>";
        declareRecordsHtml += "<input name='declareRecordId' id='declareRecordId' type='hidden'>";
        declareRecordsHtml += "<input name='declareRecordName' id='declareRecordName'" +
            " class='form-control' readonly onclick='declareRecordModeObj.init({callback:selectRecord,this_:this});'>";

        declareRecordsHtml += '<div class="input-group-prepend">';
        declareRecordsHtml += '<button class="btn btn-warning btn-sm " style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="$(this).closest(\'.input-group\').find(\'input\').val(\'\');">';
        declareRecordsHtml += "清空</button>";
        declareRecordsHtml += "</div>";

        declareRecordsHtml += "</div>";
        declareRecordsHtml += "</div>";

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
        url += '&tbId=' + node.tableId;
        url += '&tbType=' + node.type;
        url += '&tableName=' + node.tableName;
        url += '&planDetailsId=${projectPlanDetails.id}';
        openWin(url, function () {
        })
    }

    //显示弹窗
    batchTreeTool.showAlternativeSurveyModal = function () {
        batchTreeTool.loadAlternativeSurveyList();
        $('#reference_modal').modal();
    }

    //加载备选案例数据列表
    batchTreeTool.loadAlternativeSurveyList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="batchTreeTool.referenceAlternativeSurvey(' + row.applyBatchId + ')"><i class="fa fa-check"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#basicAlternativeSurveyList").bootstrapTable('destroy');
        TableInit($("#basicAlternativeSurveyList"), "${pageContext.request.contextPath}/basicApplyBatch/getBasicAlternativeSurveyList", cols, {
            name: $('#queryAlternativeName').val(),
            planDetailsId: "${projectPlanDetails.id}"
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //引用备选案例
    batchTreeTool.referenceAlternativeSurvey = function (referenceId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/referenceEstate",
            data: {
                referenceId: referenceId,
                basicApplyBatchId: batchTreeTool.getApplyBatchId(),
                planDetailsId: "${projectPlanDetails.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    AlertSuccess('成功', '引用成功', function () {
                        window.location.href = window.location.href;
                    })
                } else {
                    AlertError('失败', '引用失败');
                }
            }
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
                } else {
                    $("#frm_detail").find("#executorName").val('');
                    $("#frm_detail").find("#executor").val('');
                    $("#frm_detail_b").find("#executorName").val('');
                    $("#frm_detail_b").find("#executor").val('');
                }
            }
        });
    }

    //选择权证
    function selectRecord(_this, id) {
        var group = $(_this).closest(".form-group");
        group.find("input[name='declareRecordId']").val(id);
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRecord/getDeclareRecordListByIds",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var arr = [];
                    $.each(result.data, function (i, item) {
                        $(_this).val(item.name);
                    });

                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function typeChange(_this) {
        if ($(_this).val() == "house") {
            $("#frm_detail").find("input[name='declareRecordName']").attr("required", true);
        } else {
            $("#frm_detail").find("input[name='declareRecordName']").attr("required", false);
        }
    }

</script>
