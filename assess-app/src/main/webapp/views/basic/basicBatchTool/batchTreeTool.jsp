<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="form-group" id="divTool">
    <a class="btn btn-xs btn-default" onclick="batchTreeTool.showAddModal()">
        新增
    </a>
    <a class="btn btn-xs btn-success" onclick=" batchTreeTool.getAndEditDetail();">
        编辑
    </a>
    <a class="btn btn-xs btn-warning" onclick=" batchTreeTool.deleteDetail();">
        删除
    </a>
    <a class="btn btn-xs btn-default" onclick="batchTreeTool.fillInformation();">
        填写或修改信息
    </a>
    <c:if test="${!empty projectPlanDetails}">
        <a class="btn btn-xs btn-primary copy" onclick="batchTreeTool.copy();">
            复制
        </a>
    </c:if>
    <a class="btn btn-xs btn-primary paste" style="display:none;" onclick="batchTreeTool.paste();">
        粘贴
    </a>

</div>
<div class="col-md-3">
    <ul id="ztree" class="ztree"></ul>
</div>
<div class="col-md-9">
</div>


<!-- end: MAIN CONTAINER -->

<div id="detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" aria-hidden="true"
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
                    <button type="button" id="btnSave" class="btn btn-primary" onclick="batchTreeTool.saveItemData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="detail_modal_b" class="modal fade bs-example-modal-lg" data-backdrop="static" aria-hidden="true"
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
                    <button type="button" class="btn btn-primary" onclick="batchTreeTool.editItemData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>


<script type="text/javascript">
    var batchApply = undefined;
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
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTree",
            otherParam: {
                estateId: function () {
                    return $("#estateId").val();
                }
            },
            autoParam: ["id=pid"]
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {

            }
        }
    };

    var batchTreeTool = function () {

    };

    //初始化tree
    batchTreeTool.ztreeInit = function (data) {
        batchApply = data;
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{
            "id": 0,
            "pid": 0,
            "displayName": data.estateName,
            "isParent": true
        }]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);

        zTreeObj.expandNode(rootNode, true, false, true);
    }

    //添加数据打开modal
    batchTreeTool.showAddModal = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        var html = "";
        switch (level) {
            case 0: {
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
            case 1: {
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
            case 2: {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_house");
                html += "<label class='col-sm-2 control-label'>";
                html += "房号";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required value=''>";
                html += "</div>";
                html += "<label class='col-sm-2 control-label'>";
                html += "是否标准";
                html += "<span class='symbol required'></span>";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<select id='bisStandard' name='bisStandard' required class='form-control'>";
                html += "<option value=''>--请选择--</option>";
                html += "<option value='true'>是</option>";
                html += "<option value='false'>否</option>";
                html += "</select>";
                html += "</div>";
                break;
            }

            case 3: {
                Alert("房屋下无法继续添加节点。");
                return false;
                break;
            }
        }
        $("#frm_detail").find("#detailContent").empty().append(html);
        $("#frm_detail").find("input[name='applyBatchId']").val(batchApply.id);
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
                formData: JSON.stringify(formData),
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    var node = zTreeObj.getSelectedNodes()[0];
                    zTreeObj.addNodes(node, {
                        id: result.data.id,
                        pid: result.data.pid,
                        displayName: result.data.displayName
                    });
                    $('#detail_modal').modal('hide');
                } else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //编辑明细
    batchTreeTool.editItemData = function () {
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
                    //ztreeInit($("#basicBatchApplyFrm").find("input[name='estateName']").val());
                    var node = zTreeObj.getSelectedNodes()[0];
                    node.id = result.data.id;
                    node.name = result.data.name;
                    node.displayName = result.data.displayName;
                    node.pid = result.data.pid;
                    zTreeObj.updateNode(node, false);
                    $('#detail_modal_b').modal('hide');
                } else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            }
        });
    }

    //删除明细
    batchTreeTool.deleteDetail = function () {
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

    //编辑数据打开modal
    batchTreeTool.showEditModal = function (data) {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        var html = "";
        switch (level) {
            case 1: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_building");
                html += "<label class='col-sm-2 control-label'>";
                html += "楼栋编号";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required>";
                html += "</div>";
                break;
            }
            case 2: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_unit");
                html += "<label class='col-sm-2 control-label'>";
                html += "单元编号";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required>";
                html += "</div>";
                break;
            }
            case 3: {
                $("#frm_detail_b").find("input[name='tableName']").val("tb_basic_house");
                html += "<label class='col-sm-2 control-label'>";
                html += "房号";
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required>";
                html += "</div>";
                html += "<label class='col-sm-2 control-label'>";
                html += "是否标准";
                html += "</label>";
                html += "<div class='col-sm-4'>";
                html += "<select id='bisStandard_b' name='bisStandard' required class='form-control'>";
                html += "<option value=''>--请选择--</option>";
                html += "<option value='true'>是</option>";
                html += "<option value='false'>否</option>";
                html += "</select>";
                html += "</div>";
                break;
            }
        }
        $("#frm_detail_b").clearAll();
        $("#frm_detail_b").find("#detailContent_b").empty().append(html);
        $("#frm_detail_b").initForm(data);
        $("#bisStandard_b").val('' + data.bisStandard);
        $("#detail_modal_b").modal();
    }

    //编辑明细
    batchTreeTool.getAndEditDetail = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.id == 0) {
            Alert("请重新选择。");
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

    //进入填写信息页面
    batchTreeTool.fillInformation = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var estateId = 0;
        if (node.id == 0) {
            estateId = batchApply.estateId;
        }
        var type = batchApply.type;
        var planDetailsId = batchApply.planDetailsId ? batchApply.planDetailsId : 0;
        openWin('${pageContext.request.contextPath}/basicApplyBatch/fillInformation?type=' + type + "&id=" + node.id + "&buildingType=" + node.level + "&estateId=" + estateId + "&planDetailsId=" + planDetailsId, function () {
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
        $("#divTool").find('.paste').show();
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
                    pasteBatchDetailId: node.id,
                    copyBatchDetailId: batchTreeTool.beCopyObject.id,
                    displayName: node.displayName
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("粘贴成功");
                        //更新元素信息
                        $("#divTool").find('.paste').hide();
                        batchTreeTool.ztreeInit(batchApply);
                    }
                    else {
                        Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        });

    }

</script>
