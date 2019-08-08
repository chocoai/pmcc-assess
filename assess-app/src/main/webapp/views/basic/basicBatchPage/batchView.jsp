<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="basicBatchApplyFrm" class="form-horizontal">
    <input type="hidden" name="id" value="${applyBatch.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                省
            </label>
            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                <select name="province" class="form-control search-select select2" required>
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                市
            </label>
            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                <select name="city" class="form-control search-select select2" required>
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                楼盘名称
            </label>
            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                <input type="hidden" id="estateId" name="estateId" value="${applyBatch.estateId}">
                <input type="text" class="form-control" name="estateName" placeholder="楼盘名称"
                       required value="${applyBatch.estateName}">
            </div>
        </div>
    </div>
    <div class="form-group">
                            <span class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 checkbox-inline">
                                <input type="radio" id="applyFormType0" name="type" value="0">
                                <label for="applyFormType0">非工业交通仓储</label>
                            </span>

        <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType1" name="type" value="1">
                                <label for="applyFormType1">工业交通仓储</label>
                            </span>
        <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType2" name="type" value="2">
                                <label for="applyFormType2">构筑物</label>
                            </span>
        <a id="saveApplyInfoBtn" class="btn btn-warning" onclick="saveApplyInfo(this);">
            <c:if test="${empty applyBatch}">
                添加楼栋单元房屋
            </c:if>
            <c:if test="${!empty applyBatch}">
                查看楼栋单元房屋
            </c:if>
        </a>
    </div>
    <div id="showTree" style="display: none">

        <div class="form-group">
            <a class="btn btn-xs btn-default" onclick="showAddModal()">
                新增
            </a>
            <a class="btn btn-xs btn-success" onclick="getAndEditDetail();">
                编辑
            </a>
            <a class="btn btn-xs btn-warning" onclick="deleteDetail();">
                删除
            </a>
            <a class="btn btn-xs btn-default" onclick="fillInformation();">
                填写或修改信息
            </a>
        </div>
        <div class="col-md-3">
            <ul id="ztree" class="ztree"></ul>
        </div>
        <div class="col-md-9">
        </div>

    </div>
</form>


<!-- end: MAIN CONTAINER -->

<div id="detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加</h4>
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
                    <button type="button" id="btnSave" class="btn btn-primary" onclick="saveItemData()">
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
                    <button type="button" class="btn btn-primary" onclick="editItemData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>

</html>
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#basicBatchApplyFrm").find('[name=province]'),
                cityTarget: $("#basicBatchApplyFrm").find('[name=city]'),
                provinceValue: '${applyBatch.province}',
                cityValue: '${applyBatch.city}'
            });

            $("#basicBatchApplyFrm").find("input[type='radio'][name='type'][value='${applyBatch.type}']").trigger('click');
            ztreeInit("${applyBatch.estateName}");
        } else {
            //定位成功回调方法
            mapPosition.getCurrentCity(function (province, city) {
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#basicBatchApplyFrm").find('[name=province]'),
                    cityTarget: $("#basicBatchApplyFrm").find('[name=city]'),
                    provinceDefaultText: province,
                    cityDefaultText: city
                });
            });
        }
    });

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

    //初始化
    function ztreeInit(estateName) {
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{
            "id": 0,
            "pid": 0,
            "displayName": estateName,
            "isParent": true
        }]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);

        zTreeObj.expandNode(rootNode, true, false, true);
    }
</script>

<script type="text/javascript">

    //添加楼栋等信息
    function saveApplyInfo(_this) {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var radioValue = $("#basicBatchApplyFrm").find("input[type='radio']:checked").val();
        if (!radioValue) {
            Alert("请选择类型");
            return false;
        }
        var formData = formParams("basicBatchApplyFrm");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveApplyInfo",
            type: "post",
            dataType: "json",
            data: formData,
            success: function (result) {
                if (result.ret) {
                    $(_this).hide();
                    $("#basicBatchApplyFrm").find("input[name='id']").val(result.data.id);
                    $("#estateId").val(result.data.estateId);
                    $("#basicBatchApplyFrm").find("input").attr("readonly", "readonly");
                    $("#basicBatchApplyFrm").find("select").attr("disabled", "disabled");
                    $("#basicBatchApplyFrm").find("input[type='radio']").on('click', function () {
                        return false;
                    });
                    $("#showTree").show();
                    ztreeInit(result.data.estateName);
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    //添加数据打开modal
    function showAddModal() {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        var html = "";
        switch (level) {
            case 0: {
                $("#frm_detail").find("input[name='tableName']").val("tb_basic_building");
                html += "<label class='col-sm-2 control-label'>";
                html += "楼栋编号";
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
                html += "</label>";
                html += " <div class='col-sm-4'>";
                html += "<input type='text'  name='name' class='form-control' required value=''>";
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
        var applyBatchId = $("#basicBatchApplyFrm").find("input[name='id']").val();
        $("#frm_detail").find("input[name='applyBatchId']").val(applyBatchId);
        $("#frm_detail").find("input[name='pid']").val(node.id);
        $("#detail_modal").modal();
    }

    //保存明细
    function saveItemData() {
        if (!$("#frm_detail").valid()) {
            return false;
        }
        var formData = formParams("frm_detail");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveItemData",
            type: "post",
            data: {
                formData:JSON.stringify(formData),
            },
            success: function (result) {
                if (result.ret) {
                    console.log("id:" + result.data.id + "pid:" + result.data.pid);
                    toastr.success('保存成功');
                    //ztreeInit($("#basicBatchApplyFrm").find("input[name='estateName']").val());
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
    function editItemData() {
        if (!$("#frm_detail").valid()) {
            return false;
        }
        var formData = formParams("frm_detail_b");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveItemData",
            type: "post",
            dataType: "json",
            data: {
                formData:JSON.stringify(formData),
            },
            success: function (result) {
                if (result.ret) {
                    console.log("id:" + result.data.id + "pid:" + result.data.pid);
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
    function deleteDetail() {
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
    function showEditModal(data) {
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
                break;
            }
        }
        $("#frm_detail_b").clearAll();
        $("#frm_detail_b").find("#detailContent_b").empty().append(html);
        $("#frm_detail_b").initForm(data);
        $("#detail_modal_b").modal();
    }

    //编辑明细
    function getAndEditDetail() {
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
                    showEditModal(result.data);
                }
            }
        })
    }

    //进入填写信息页面
    function fillInformation() {
        var node = zTreeObj.getSelectedNodes()[0];
        var estateId = 0;
        if (node.id == 0) {
            estateId = $("#basicBatchApplyFrm").find("input[name='estateId']").val();
        }
        var type = $("#basicBatchApplyFrm").find("input[type='radio'][name='type']:checked").val();
        window.open('${pageContext.request.contextPath}/basicApplyBatch/fillInformation?type=' + type + "&id=" + node.id + "&buildingType=" + node.level + "&estateId=" + estateId);
    }

</script>
