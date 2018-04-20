/**
 * Created by Calvin on 2017/7/19.
 * 处理新增和编辑角色
 */

//编辑角色
function editRole() {
    $("#h4_modeTitle").html("编辑角色");
    $("#div_isAdmin").hide();
    $("#opation_type").val("edit");
}
//新增角色
function addRole() {
    $("#h4_modeTitle").html("新增角色");
    $("#roleName").val("");
    $("#isAdmin").prop("checked", false);
    $("#roleId").val(0);
    $("#div_isAdmin").show();
    $("#opation_type").val("add");
}

//复制角色
function copyRole() {
    if ($("#roleName").val() == "") {
        Alert("请选择要复制的角色!", 1, null, null);
        return false;
    }
    $("#h4_modeTitle").html("复制角色");
    $('#div_role').modal({backdrop: 'static', keyboard: false});
    $("#roleName").val($("#roleName").val() + "复本");
    $("#div_isAdmin").hide();
    $("#opation_type").val("copy");
}


//移交角色
function transformRole() {
    if ($("#roleName").val() == "") {
        Alert("请选择一个要移交的角色!", 1, null, null);
        return false;
    }
    $('#div_transform').modal({backdrop: 'static', keyboard: false});
}


function transformEmployee(data) {
    $("#trans_user_name").val(data.name);
    $("#trans_userId").val(data.account);
}