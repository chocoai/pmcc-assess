<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/11/2
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <label class="col-sm-1 control-label">
        项目经理<span class="symbol required"></span>
    </label>
    <div class="x-valid">
        <div class="col-sm-11">
            <input type="hidden" id="id" name="id" value="${projectMemberVo.id}">
            <input type="hidden" id="userAccountManager" name="userAccountManager"
                   value="${projectMemberVo.userAccountManager}">
            <input type="text" required value="${projectMemberVo.userAccountManagerName}"
                   placeholder="项目经理"
                   id="userAccountManagerName" name="userAccountManagerName"
                   class="form-control" readonly="readonly"
                   maxlength="200" onclick="managerSelect()">
        </div>
    </div>
</div>
<%@include file="/views/share/model_employee_user_accounts.jsp" %>
<script type="text/javascript">
    function managerSelect() {
        var currList = getCurrList("Manager");
        if ("${managerUserAccounts}" == "") {
            Alert("当前项目所属部门没有项目经理角色人员");
            return false;
        }
        loadAccountSelectEmployee("${managerUserAccounts}", currList, true, function (data) {
            $("#userAccountManager").val(data["account"]);
            $("#userAccountManagerName").val(data["name"]);
        });
    }

    function getCurrList(obj) {
        var currList = "";
        if ($("#userAccount" + obj).val() != "") {
            var userAccount = $("#userAccount" + obj).val();
            var aUserAccount = userAccount.split(',');
            var userAccountName = $("#userAccount" + obj + "Name").val();
            var aUserAccountName = userAccountName.split(',');
            for (var i = 0; i < aUserAccount.length; i++) {
                currList += aUserAccountName[i] + "_" + aUserAccount[i] + ",";
            }
            currList = currList.substring(0, currList.length - 1);
        }
        return currList;
    }
</script>