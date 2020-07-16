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
                    <div class="col-md-12">
                        <div class=" card" style="min-height: 300px;">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        楼盘信息
                                    </div>
                                </div>
                            </div>
                            <div class=" card-body">
                                <div class="row col-md-12">
                                    <div class="col-md-8">
                                        <ul id="ztree" class="ztree"></ul>
                                    </div>
                                    <div class="col-md-4">
                                        <form id="basicBatchApplyFrm" class="form-horizontal">
                                            <input type="hidden" name="id" value="${applyBatch.id}">
                                            <input type="hidden" id="estateId" name="estateId"
                                                   value="${applyBatch.estateId}">
                                            <input type="hidden" id="estateName" name="estateName"
                                                   value="${applyBatch.estateName}">
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        大类<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <select class="form-control input-full" name="classify" disabled="disabled" onchange="formClassifyChange();" required>
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
                                                        <select class="form-control input-full" name="type" disabled="disabled"
                                                                onchange="saveBasicApplyBatch();" required>
                                                            <option value="">-请选择-</option>
                                                            <c:if test="${not empty examineFormTypeList}">
                                                                <c:forEach var="item"
                                                                           items="${examineFormTypeList}">
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
                                <div id="detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static"
                                     aria-hidden="true"
                                     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <div class="modal-title"><h4>添加</h4></div>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close"><span
                                                        aria-hidden="true">&times;</span></button>
                                            </div>
                                            <form id="frm_detail" class="form-horizontal">
                                                <input type="hidden" name="id" value="0">
                                                <div class="modal-body" style="overflow: auto; ">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="card-body">
                                                                <input type="hidden" name="pid">
                                                                <input type="hidden" name="applyBatchId">
                                                                <input type="hidden" name="tableName">
                                                                <input type="hidden" name="tableId">
                                                                <input type="hidden" name="caseTablePid">
                                                                <div class="row form-group">
                                                                    <div id="detailContent" class="form-inline">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" data-dismiss="modal"
                                                            class="btn btn-sm btn-default">
                                                        取消
                                                    </button>
                                                    <button type="button" id="btnSave"
                                                            class="btn btn-sm btn-primary"
                                                            onclick="batchTreeTool.saveItemData()">
                                                        保存
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div id="detail_modal_b" class="modal fade bs-example-modal-lg"
                                     data-backdrop="static"
                                     aria-hidden="true"
                                     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">编辑</h4>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close"><span
                                                        aria-hidden="true">&times;</span></button>
                                            </div>
                                            <form id="frm_detail_b" class="form-horizontal">
                                                <input type="hidden" name="id" value="0">
                                                <div class="modal-body" style="overflow: auto; ">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="card-body">
                                                                <input type="hidden" name="pid">
                                                                <input type="hidden" name="applyBatchId">
                                                                <input type="hidden" name="tableName">
                                                                <input type="hidden" name="tableId">
                                                                <div class="row form-group">
                                                                    <div id="detailContent_b" class="form-inline">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" data-dismiss="modal"
                                                            class="btn btn-default btn-sm">
                                                        取消
                                                    </button>
                                                    <button type="button" class="btn btn-primary btn-sm"
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
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

</html>
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            ztreeInit(${applyBatch.id});

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
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                informationDetail();
            }
        }
    };

    //初始化tree
    function ztreeInit(basicApplyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {
                basicApplyBatchId: basicApplyBatchId,
                showTag:true,
                bisDetail:'${applyBatch.status eq 'finish'}'
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

    //信息详情页面
    function informationDetail() {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.bisModify == false) {
            var href = "${pageContext.request.contextPath}/";
            switch (node.type) {
                case "estate": {
                    href += "caseEstate/detailView";
                    break;
                }
                case "building": {
                    href += "caseBuilding/detailView";
                    break;
                }
                case "unit": {
                    href += "caseUnit/detailView";
                    break;
                }
                case "house": {
                    href += "caseHouse/detailView";
                    break;
                }
            }
            href += "?id=" + node.id;
            window.open(href, "");
        } else {
            var classify = ${applyBatch.classify};
            var formType = ${applyBatch.type};
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += 'applyBatchId=' +  ${applyBatch.id};
            url += '&formClassify=' + classify;
            url += '&formType=' + formType;
            url += '&tbId=' + node.tableId;
            url += '&tbType=' + node.type;
            url += '&applyBatchDetailId=' + node.id;
            openWin(url, function () {
            })
        }
    }


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/basicApprovalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //有案例数据初始化tree
    function caseEstateZtreeInit(applyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getCaseApprovalZtreeDto',
            data: {applyBatchId: applyBatchId},
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
</script>

