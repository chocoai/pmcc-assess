/**
 * Created by Calvin on 2017/7/19.
 * 用于处理角色菜单权限相关脚本
 */

function loadRoleMenu() {
    $("#btn_editRoleMenu").show();
    $("#btn_saveRoleMenu").hide();
    beatSetChkStatus(true);
}
//设置元素属性
function  roleMenuToggle() {
    $("#btn_editRoleMenu").toggle();
    $("#btn_saveRoleMenu").toggle();
}
//编辑菜单权限
function editRoleMenu() {
    roleMenuToggle();
    beatSetChkStatus(false);
}

//设置所有菜单相关的复选框不可用用或启用，True为不可用反之为可用
function beatSetChkStatus(status) {
    var chks = $("input[name='chk_menu']");
    //清除所有复选框的选中状态
    $.each(chks, function (i, j) {
        $(j).attr("disabled", status);
    });
}

