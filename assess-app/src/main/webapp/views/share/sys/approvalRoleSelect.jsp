<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/8
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="approvalRoleSelectModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">审批角色选择</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">

                        <div id="approvalRoleSelectTree"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="approvalRoleSelecOk();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var currApprovalRoleSelectNode = undefined;
    var currApprovalRoleSelectFunction = undefined;
    //加载审批角色选择树
    function loadApprovalRoleSelectTree(orgId, value, fn) {
        currApprovalRoleSelectFunction = fn;
        initBaseTreeView("approvalRoleSelectTree", "${pageContext.request.contextPath}/sysapprovalrole/getDepartmentTree/", {}, false, function (objs) {
            treeView_setValue("approvalRoleSelectTree", value);
            $("#approvalRoleSelectModal").modal();
            objs.on('nodeSelected', function (event, node) {
                currApprovalRoleSelectNode = node;
            });
        });
    }

    function approvalRoleSelecOk() {
        if (!currApprovalRoleSelectNode) {
            Alert("请选择审批角色");
            return false;
        }
        if (currApprovalRoleSelectNode.pName != "roles") {
            Alert("不允许选择部门，请选择部门下的角色！");
            return false;
        }
        if (currApprovalRoleSelectFunction) {
            if (currApprovalRoleSelectFunction(currApprovalRoleSelectNode) == false) {
                return false;
            }
        }
        $("#approvalRoleSelectModal").modal("hide");
    }
</script>
