<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="divBoxCase" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加或升级案例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmCase" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <ul id="caseZtree" class="ztree" style="max-height: 260px;overflow: auto;"></ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="submitCase()">
                    提交
                </button>
            </div>

        </div>
    </div>
</div>

<%--案例快速申请--%>
<script type="application/javascript">
    var caseFastApplyZtree=undefined;
    var caseSetting = {
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"Y": "", "N": ""}//
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
        }
    };

    function openCaseBox() {
        if (${!empty applyBatch}) {
            if (${!empty applyBatch.referenceApplyBatchId}) {
                caseZtreeInit(${applyBatch.referenceApplyBatchId});
            } else {
                caseZtreeInit(${applyBatch.id});
            }
        }
        $('#divBoxCase').modal("show");
    }

    function caseZtreeInit(basicApplyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {
                basicApplyBatchId: basicApplyBatchId,
                showTag: true
            },
            type: 'get',
            dataType: "json",
            success: function (result) {
                caseFastApplyZtree = $.fn.zTree.init($("#caseZtree"), caseSetting, result);
                var nodesSys = caseFastApplyZtree.getNodes(); //可以获取所有的父节点
                var nodesSysAll = caseFastApplyZtree.transformToArray(nodesSys); //获取树所有节点
                for (var i = 0; i < nodesSysAll.length; i++) {
                    if (nodesSysAll[i].displayName.indexOf('新增') != -1) {
                        nodesSysAll[i].checked = true;
                        caseFastApplyZtree.updateNode(nodesSysAll[i]);
                    }
                }
                //展开第一级，选中根节点
                var rootNode = caseFastApplyZtree.getNodes()[0];
                caseFastApplyZtree.selectNode(rootNode);
                caseFastApplyZtree.expandAll(true);
            }
        })
    }

    //申请案例提交
    function submitCase() {
        var sourceApplyBatchId = '${applyBatch.id}';
        var caseFastApplyZtree = $.fn.zTree.getZTreeObj($("#caseZtree").prop("id"));
        var nodes = caseFastApplyZtree.getCheckedNodes(true);
        if (nodes.length == 0) {
            notifyInfo('提示', '勾选至少一个节点');
            return false;
        }
        $.each(nodes, function (i, node) {
            checkParentNodeRecursion(node);
        });
        setHalfCheck();
        nodes = caseFastApplyZtree.getNodesByFilter(function (node) {
            return node.getCheckStatus().checked || node.getCheckStatus().half;
        }, false);
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/basicApplyBatchSurveySubmit",
            data: {
                sourceApplyBatchId: sourceApplyBatchId,
                zTreeData: JSON.stringify(nodes)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "申请成功");
                    $('#divBoxCase').modal("hide");
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    }

    function setHalfCheck() {
        var halfCheckNodes = caseFastApplyZtree.getNodesByFilter(function (node) {
            return node.getCheckStatus().half;
        }, false);
        $.each(halfCheckNodes, function (i, node) {
            node.halfCheck = true;
            caseFastApplyZtree.updateNode(node);
        });
    }

    //在勾选的节点中，当前节点的上级节点为新增节点则该节点必须选中，递归执行
    function checkParentNodeRecursion(node) {
        if (!node) return;
        var parentNode = node.getParentNode();
        if (parentNode && parentNode.bisAdd && !parentNode.checked) {
            parentNode.checked = true;
            caseFastApplyZtree.updateNode(parentNode);
        }
        checkParentNodeRecursion(parentNode);
    }
</script>