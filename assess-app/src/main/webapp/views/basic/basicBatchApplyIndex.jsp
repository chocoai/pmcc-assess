<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" id="basicApplyId" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        楼盘信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="basicBatchApplyFrm" class="form-horizontal">
                        <input type="hidden" name="id" value="${basicApplyBatch.id}">
                        <input type="hidden" id="estateId" name="estateId" value="${basicApplyBatch.estateId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    大类<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <select class="form-control" name="classify" onchange="formClassifyChange();"
                                            required>
                                        <option value="">-请选择-</option>
                                        <c:if test="${not empty formClassifyList}">
                                            <c:forEach var="item" items="${formClassifyList}">
                                                <option value="${item.id}" data-key="${item.fieldName}"
                                                    ${item.id eq basicApplyBatch.classify?'selected="selected"':''}>${item.name}</option>
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
                                    <select class="form-control" name="type" onchange="saveBasicApplyBatch();" required>
                                        <option value="">-请选择-</option>
                                        <c:if test="${not empty examineFormTypeList}">
                                            <c:forEach var="item" items="${examineFormTypeList}">
                                                <option value="${item.key}" ${item.key eq basicApplyBatch.type?'selected="selected"':''}>${item.value}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="col-md-3 col-lg-offset-1" style="max-height: 500px;overflow: auto;">
                        <ul id="ztree" class="ztree"></ul>
                    </div>
                    <div class="col-md-4">
                        <span id="btnGroup">
                            <a class="btn btn-xs btn-success baseTool" onclick="batchTreeTool.showAddModal()">
                            新增
                        </a>
                        <a class="btn btn-xs btn-warning" onclick=" batchTreeTool.deleteDetail();">
                            删除
                        </a>
                        <a class="btn btn-xs btn-primary baseTool" onclick=" batchTreeTool.expandAll(true);">
                            全部展开
                        </a>
                        <a class="btn btn-xs btn-primary baseTool" onclick=" batchTreeTool.expandAll(false);">
                            全部收起
                        </a>
                        <a class="btn btn-xs btn-primary fillInformation" onclick="batchTreeTool.fillInformation();">
                            填写信息
                        </a>
                        <a class="btn btn-xs btn-warning copy" onclick="batchTreeTool.copy();">
                            复制
                        </a>
                        <a class="btn btn-xs btn-warning paste" onclick="batchTreeTool.paste();">
                            粘贴
                        </a>
                        </span>
                    </div>
                    <c:if test="${basicApplyBatch.caseEstateId ge 0}">
                        <div class="col-md-3" style="max-height: 500px;overflow: auto;">
                            <ul id="estateCaseZtree" class="ztree"></ul>
                        </div>
                        <div class="col-md-1">
                            <a class="btn btn-xs btn-success baseTool" onclick="batchTreeTool.addFromCase()">
                                新增
                            </a>
                            <a class="btn btn-xs btn-primary caseTool"
                               onclick=" batchTreeTool.upgradeFromCase();">升级</a>
                        </div>
                    </c:if>
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
                                                    <input type="hidden" name="caseTablePid">
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
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
<%@include file="/views/share/main_footer.jsp" %>
</html>

<script type="text/javascript">
    $(function () {
        batchTreeTool.ztreeInit(${basicApplyBatch.id});
        if ('${basicApplyBatch.caseEstateId ge 0}' == 'true') {
            batchTreeTool.caseEstateZtreeInit('${basicApplyBatch.caseEstateId}');
            $("#basicBatchApplyFrm").find("select[name='classify']").attr("disabled", "disabled");
            $("#basicBatchApplyFrm").find("select[name='type']").attr("disabled", "disabled");
        }
    });

    //申请提交
    function submit() {
        if ("${processInsId}" != "0") {
            editSubmit();
        } else {
            if (!$("#basicBatchApplyFrm").valid()) {
                return false;
            }
            var id = $("#basicBatchApplyFrm").find("input[name='id']").val();
            if (!id) {
                Alert("请先添加楼盘的信息");
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatch/basicApplyBatchSubmit",
                type: "post",
                dataType: "json",
                async: false,
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });
                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
    }

    //返回修改提交
    function editSubmit() {
        var data = {};
        var approvalData = formParams('frm_approval');
        data = $.extend({}, approvalData, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/editSubmit",
            type: "post",
            dataType: "json",
            async: false,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    function getClassifyKey() {
        var dataKey = $("#basicBatchApplyFrm").find('[name=classify]').find('option:selected').attr("data-key");
        return dataKey;
    }

    //表单大类change
    function formClassifyChange() {
        var dataKey = getClassifyKey();
        if (AssessDicKey.projectSurveyFormClassifyLandOnly == dataKey) {
            $("#btnGroup").find('.btn').not('.fillInformation').hide();
            $("#btnGroup").find('.btn.caseTool').hide();
        } else {
            $("#btnGroup").find('.btn').show();
            $("#btnGroup").find('.btn.caseTool').hide();
        }
        initBasicApplyBatchCase();
    }

    //初始化信息
    function initBasicApplyBatchCase() {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/initBasicApplyBatchCase",
            type: "post",
            dataType: "json",
            data: formSerializeArray($("#basicBatchApplyFrm")),
            success: function (result) {
                if (result.ret) {
                    $("#basicBatchApplyFrm").find('[name=id]').val(result.data.id);
                    $("#basicBatchApplyFrm").find('[name=estateId]').val(result.data.estateId);
                    batchTreeTool.ztreeInit(result.data.id);
                } else {
                    Alert(result.errmsg);
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
                    Alert(result.errmsg);
                }
            }
        });
    }

    function getTableType(tableName) {
        switch (tableName) {
            case "tb_basic_estate": {
                return "estate";
            }
            case "tb_basic_building": {
                return "building";
            }
            case "tb_basic_unit": {
                return "unit";
            }
            case "tb_basic_house": {
                return "house";
            }
        }
    }
</script>
<script type="text/javascript">
    var zTreeObj, caseEstateZtreeObj;
    var setting = {
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
                batchTreeTool.showOperationBtn();
            }
        }
    };
    var caseEstateSetting = {
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

    //有案例数据时初始化tree
    batchTreeTool.caseEstateZtreeInit = function (estateId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getCaseEstateZtreeDtos',
            data: {
                estateId: estateId,
            },
            type: 'get',
            dataType: "json",
            success: function (result) {
                caseEstateZtreeObj = $.fn.zTree.init($("#estateCaseZtree"), caseEstateSetting, result);
                caseEstateZtreeObj.expandAll(true);
            }
        })
    }

    //添加数据打开modal
    batchTreeTool.showAddModal = function (node) {
        if (!node) {
            node = zTreeObj.getSelectedNodes()[0];
        }
        var html = "";
        switch (node.tableName) {
            case "tb_basic_estate": {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_building");
                html += "<label class='col-sm-2 control-label'>";
                html += "楼栋编号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required value=''>";
                html += "</div>";
                break;
            }
            case "tb_basic_building": {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_unit");
                html += "<label class='col-sm-2 control-label'>";
                html += "单元编号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required value=''>";
                html += "</div>";
                break;
            }
            case "tb_basic_unit": {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_house");
                html += "<label class='col-sm-2 control-label'>";
                html += "房号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required value=''>";
                html += "</div>";
                html += "<label class='col-sm-2 control-label'>";
                break;
            }
            case "tb_basic_house": {
                Alert("房屋下无法继续添加节点。");
                return false;
                break;
            }
        }
        $("#detailContent").empty().append(html);
        $("#frm_detail").find("input[name='applyBatchId']").val($("#basicBatchApplyFrm").find('[name=id]').val());
        $("#frm_detail").find("input[name='pid']").val(node.id);
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
                formData: JSON.stringify(formData)
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    if ('${basicApplyBatch.caseEstateId ge 0}' == 'true') {
                        batchTreeTool.ztreeInit(${basicApplyBatch.id});
                    } else {
                        var tableType = getTableType(result.data.tableName);
                        var node = zTreeObj.getSelectedNodes()[0];
                        zTreeObj.addNodes(node, {
                            id: result.data.id,
                            pid: result.data.pid,
                            tableId: result.data.tableId,
                            tableName: result.data.tableName,
                            type: tableType,
                            displayName: result.data.displayName
                        });
                    }
                    $('#detail_modal').modal('hide');
                } else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //删除明细
    batchTreeTool.deleteDetail = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.bisModify == false) {
            Alert("案例数据无法删除。");
            return false;
        }
        bootbox.confirm("确认要删除么？", function (result) {
            if (result) {
                var node = zTreeObj.getSelectedNodes()[0];
                if (node.id == 0) {
                    Alert("无法删除，请重新选择。");
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
                            Alert("删除失败：" + result.errmsg);
                        }
                    }
                })
            }
        });
    }

    //进入填写信息页面
    batchTreeTool.fillInformation = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var classify = $("#basicBatchApplyFrm").find('[name=classify]').val();
        var formType = $("#basicBatchApplyFrm").find('[name=type]').val();
        var url = '${pageContext.request.contextPath}/basicApplyBatch/fillInfo?';
        url += 'applyBatchId=' + $("#basicBatchApplyFrm").find('[name=id]').val();
        url += '&formClassify=' + classify;
        url += '&formType=' + formType;
        url += '&tbId=' + node.tableId;
        url += '&tbType=' + node.type;
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
            Alert("不能复制楼盘，重新选择")
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
            Alert('请选择被复制对象');
            return false;
        }
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.id == batchTreeTool.beCopyObject.id) {
            Alert('不能复制粘贴自身');
            return false;
        }
        if (node.level != batchTreeTool.beCopyObject.level) {
            Alert('请选择相应节点进行粘贴');
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
                        toastr.success("粘贴成功");
                    }
                    else {
                        Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        });
    }

    //全部展开或收起
    batchTreeTool.expandAll = function (flag) {
        zTreeObj.expandAll(flag);
    }

    //查看信息链接
    batchTreeTool.showOperationBtn = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node && node.tableId) {
            $("#btnGroup").show();
        } else {
            $("#btnGroup").hide();
        }
    }

    //新增
    batchTreeTool.addFromCase = function () {
        var node = caseEstateZtreeObj.getSelectedNodes()[0];
        if (!node) {
            Alert("请先选择节点");
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/initCaseEstateZtree",
            data: {
                caseBatchDetailId: node.id,
                containThis: true,
                applyBatchId: '${basicApplyBatch.id}'
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    batchTreeTool.showAddModal({id: result.data.id, tableName: result.data.tableName});
                }
            }
        })
    }

    //升级
    batchTreeTool.upgradeFromCase = function () {
        var node = caseEstateZtreeObj.getSelectedNodes()[0];
        if (!node) {
            Alert("请先选择节点");
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/initCaseEstateZtree",
            data: {
                caseBatchDetailId: node.id,
                containThis: false,
                applyBatchId: '${basicApplyBatch.id}'
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/basicApplyBatch/upgradeCase",
                        data: {
                            caseBatchDetailId: node.id,
                            pid: result.data.id,
                            applyBatchId: '${basicApplyBatch.id}'
                        },
                        type: "post",
                        dataType: "json",
                        success: function (result) {
                            batchTreeTool.ztreeInit(${basicApplyBatch.id});
                        }
                    })
                }
            }
        })
    }
</script>