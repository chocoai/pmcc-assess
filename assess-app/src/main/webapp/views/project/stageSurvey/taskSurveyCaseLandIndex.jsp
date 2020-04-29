<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <style>
        .ui-autocomplete {
            z-index: 2147483647;
        }
    </style>
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
        var data = formSerializeArray($("#basicBatchApplyFrm"));
        data.planDetailsId = '${projectPlanDetails.id}';
        data.projectId = '${projectId}';
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/initBasicApplyBatchInfo",
            type: "post",
            dataType: "json",
            data: data,
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
        view: {
            fontCss: function (treeId, treeNode) {
                if (treeNode.executor != '${userAccount}') {
                    return {color: "grey"};//灰色 业务规定的
                } else {
                    return {color: "black"};
                }
            }
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
        if (node.executor != '${userAccount}') {
            notifyWarning("提示", "此节点不属于当前登陆人的,无操作权限!");
            return false;
        }
        openWin(url, function () {
        })
    }


    batchTreeTool.showFunctionBtn = function () {
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

    //删除备选案例
    batchTreeTool.deleteAlternativeCase = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        batchTreeTool.loadAlternativeCaseList();
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
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
                str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="删除" onclick="batchTreeTool.deleteAlternativeCase(' + row.id + ')"><i class="fa fa-minus"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#basicAlternativeCaseList").bootstrapTable('destroy');
        TableInit($("#basicAlternativeCaseList"), "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
            name: $('#queryAlternativeName').val(),
            projectId:'${projectId}'
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
        notifyInfo('提示', '请耐心等待....');
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
                            } else {
                                AlertError('失败', '引用失败');
                            }
                        }
                    })
                }
            }
        })
    }
</script>
