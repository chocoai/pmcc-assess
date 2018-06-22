/**
 * Created by Calvin on 2018/4/21.
 * 导航菜单功能
 */
function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}
var PMCC_MAIN = {
    navigation: function (baseViewDto) {
        var html = "";
        html += '<div class="left_col scroll-view">';

        //应用名称
        html += '<div class="navbar nav_title" style="border: 0;">';
        if (baseViewDto.thisApp.appKey == 'pmcc-erp') {
            html += '<a href="/home/main" class="site_title"> <i style="border: none" class="fa '+baseViewDto.thisApp.icon+'"></i><span>' + baseViewDto.thisApp.name + '</span></a>';
        }
        else {
            html += '<a href="' + getContextPath() + '/home/main" class="site_title"> <i style="border: none" class="fa '+baseViewDto.thisApp.icon+'"></i><span>' + baseViewDto.thisApp.name + '</span></a>';
        }
        html += '</div>';
//分割符
        html += '<div class="clearfix"></div>';
//人员信息
        html += '<div class="profile clearfix">';
        html += '<div class="profile_pic">';
        html += '<img src="/assets/images/timg.jpg" alt="..." class="img-circle profile_img">';
        html += '</div>';
        html += '<div class="profile_info">';
        html += '<span>欢迎,</span>';
        html += '<h2>' + baseViewDto.thisUser.userName + '</h2>';
        html += '</div>';
        html += '</div>';
        html += '<br/>';
//菜单

        html += '<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">';
        html += ' <div class="menu_section">';
        html += '<ul class="nav side-menu">';
        var data = baseViewDto.thisAppMenuTrees;
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            if (item.nodes != null) {
                if (item.id == baseViewDto.currentMenu.pid) {
                    html += '<li class="active">';
                }
                else {
                    html += '<li>';
                }
                html += '<a href="javascript:void(0)"><i class="fa ' + item.icon + '"></i>' + item.name + '<span class="fa fa-chevron-down"></span></a>';

                if (item.id == baseViewDto.currentMenu.pid) {
                    html += '<ul class="nav child_menu" style="display: block">';
                }
                else {
                    html += '<ul class="nav child_menu" style="display: none">';
                }

                for (var j = 0; j < item.nodes.length; j++) {
                    var menuItem = item.nodes[j];
                    if (menuItem.id == baseViewDto.currentMenu.id) {
                        html += '<li class="current-page">';
                    }
                    else {
                        html += '<li>';
                    }

                    var target = menuItem.target;
                    if(target == "" || target == null || target == undefined){
                        target = "_self";
                    }
                    html += '<a href="' + menuItem.url + '?menuId=' + menuItem.id + '" target="' + target + '">' + menuItem.name + '</a>';
                    html += '</li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        }
        html += '</div>';
        html += '</div>';

        html += '</div>';
        return html;

    }
};
