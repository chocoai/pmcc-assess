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
                                                onclick="batchTreeTool.showAlternativeCaseModal();">
                                            引用备选案例
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
                                        <button type="button" class="btn btn-sm btn-success deserveTool"
                                                style="margin-left: 20px;"
                                                onclick="batchTreeTool.showAddModal()">
                                            新增
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning deleteTool"
                                                onclick=" batchTreeTool.deleteDetail();">
                                            删除
                                        </button>

                                        <button type="button" class="btn btn-sm btn-primary fillInformation deserveTool"
                                                style="margin-left: 20px;"
                                                onclick="batchTreeTool.fillInformation();">
                                            填写信息
                                        </button>
                                        <button type="button" class="btn btn-sm btn-warning paste alternativeCase"
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
                <form id="detail_form" class="form-horizontal">
                    <input type="hidden" name="id" value="0">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="applyBatchId">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2">名称<span class="symbol required"></span></label>
                                <div class="col-sm-4">
                                    <input type="text" data-rule-maxlength="100" placeholder="名称"
                                           name="name" class="form-control input-full" required>
                                </div>
                                <label class="col-sm-2">表单类型<span class="symbol required"></span></label>
                                <div class="col-sm-4">
                                    <select name='type' required
                                            class='form-control input-full'></select>
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
<div id="reference_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">备选案例</h4>
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
                                                        onclick="batchTreeTool.loadAlternativeCaseList();">
                                                    <span class="btn-label"><i class="fa fa-search"></i></span>
                                                    查询
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="basicAlternativeCaseList">
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
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            batchTreeTool.ztreeInit(${applyBatch.id});
        }
    });

    //任务提交
    function submit(useBox) {
        var formData = {};
        formData.projectId = "${projectInfo.id}";
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
</script>
<script type="text/javascript">
    var batchApply = undefined;
    var setting = {
        data: {
            key: {
                name: "name"
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
    batchTreeTool.showAddModal = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node == null) {
            notifyInfo('提示', '还未选择节点');
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTableTypeList",
            data: {type: node.type},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    var typeHtml = "<option value=''>--请选择--</option>";
                    $.each(result.data, function (i, item) {
                        typeHtml += "<option value='" + item.key + "'>" + item.value + "</option>";
                    })
                    $("#detail_form").clearAll();
                    $("#detail_form").find("[name='type']").empty().html(typeHtml);
                    $("#detail_form").find("input[name='applyBatchId']").val(batchTreeTool.getApplyBatchId());
                    $("#detail_form").find("input[name='pid']").val(node.id);
                    $("#detail_modal").modal();
                } else {
                    notifyInfo('提示', '该节点下没有可添加的表单类型');
                }
            }
        })

    }

    //保存明细
    batchTreeTool.saveItemData = function () {
        if (!$("#detail_form").valid()) {
            return false;
        }
        var formData = formParams("detail_form");
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
                    zTreeObj.addNodes(node, {
                        id: result.data.id,
                        pid: result.data.pid,
                        tableId: result.data.tableId,
                        type: result.data.type,
                        displayName: result.data.name,
                        name: result.data.name,
                        creator: result.data.creator,
                        creatorName: result.data.creatorName
                    });
                    $('#detail_modal').modal('hide');
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
        url += 'applyBatchId=' + batchTreeTool.getApplyBatchId();
        url += '&formClassify=' + classify;
        url += '&formType=' + formType;
        url += '&tbId=' + node.tableId;
        url += '&tbType=' + node.type;
        url += '&planDetailsId=${projectPlanDetails.id}';
        var frame = layer.open({
            type: 2,
            title: node.name,
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: url,
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                batchTreeTool.ztreeInit(batchTreeTool.getApplyBatchId());
            }
        });
        layer.full(frame);
    }

    //添加到备选案例
    batchTreeTool.addToAlternative = function () {
        Loading.progressShow();
        var node = zTreeObj.getSelectedNodes()[0];
        var data = {};
        data.batchDetailId = node.id;
        data.business_id = node.tableId;
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

    //显示弹窗
    batchTreeTool.showAlternativeCaseModal = function () {
        batchTreeTool.loadAlternativeCaseList();
        $('#reference_modal').modal();
    }

    //加载备选案例数据列表
    batchTreeTool.loadAlternativeCaseList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="batchTreeTool.referenceAlternativeCase(' + row.id + ')"><i class="fa fa-check"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#basicAlternativeCaseList").bootstrapTable('destroy');
        TableInit($("#basicAlternativeCaseList"), "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
            name: $('#queryAlternativeName').val()
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
    batchTreeTool.referenceAlternativeCase = function (id) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/deleteBatchAllById',
            type: 'post',
            data: {
                applyBatchId: batchTreeTool.getApplyBatchId(),
            },
            dataType: 'json',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var data = {};
                    data.id = id;
                    data.projectId = "${projectInfo.id}";
                    data.planDetailsId = "${projectPlanDetails.id}";
                    $.ajax({
                        url: '${pageContext.request.contextPath}/basicAlternativeCase/referenceDataById',
                        type: 'post',
                        data: data,
                        dataType: 'json',
                        success: function (result) {
                            if (result.ret) {
                                AlertSuccess('成功', '引用成功', function () {
                                    window.location.href = window.location.href;
                                })
                            }else{
                                AlertError('失败', '引用失败');
                            }
                        }
                    })
                }
            }
        })
    }
</script>
