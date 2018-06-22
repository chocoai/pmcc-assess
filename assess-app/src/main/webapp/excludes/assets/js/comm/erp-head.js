/**
 * Created by Calvin on 2018/4/21.
 * 导航菜单功能
 */
var isLoadProcessMap = false;
var userProcessMapItem = [];

var PMCC_MAIN = {
    head: function (baseViewDto, sysRemindUnReadCount, sysRemindUnRead) {
        //json要序列化成对象
        baseViewDto = JSON.parse(baseViewDto); //fix

        var html = "";
        html += ' <div class="nav_menu">';
        html += ' <nav>';
        //收缩菜单
        html += '  <div class="nav toggle">';
        html += ' <a id="menu_toggle"><i class="fa fa-bars"></i></a>';
        html += ' </div>';

        html += ' <ul class="nav navbar-nav navbar-right">';

        //个人信息
        html += ' <li class="">';
        html += ' <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">';
        html += ' <img src="/assets/images/userIcon30.jpg" alt="">' + baseViewDto.thisUser.userName;
        html += ' <span class=" fa fa-angle-down"></span>';
        html += ' </a>';
        html += ' <ul class="dropdown-menu dropdown-usermenu pull-right">';
        html += ' <li><a target="_blank" href="/pmcc-hr/orguser/userInfo"><i class="fa fa-user pull-right"></i>个人信息</a></li>';
        html += ' <li><a href="/views/share/change_password.jsp" data-toggle="modal"><i class="fa fa-adjust pull-right"></i>修改密码</a></li>';
        html += ' <li class="divider"></li>';
        html += ' <li><a href="/home/logout"><i class="fa fa-sign-out pull-right"></i>退出</a></li>';
        html += ' </ul>';
        html += ' </li>';
        //流程地图
        html += ' <li role="presentation" class="dropdown">';
        html += ' <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" onclick="getUserProcessList();">';
        html += ' <i class="fa fa-life-ring tooltips" style="font-size: 26px;" data-placement="bottom" data-original-title="流程地图"></i>';
        html += ' </a>';
        html += ' <ul id="mainHeadProcessMap" class="dropdown-menu list-unstyled  msg_list" role="menu" style="width: 780px; height: 400px;overflow: auto;padding: 10px;">';

        html += ' <li>';
        html += ' <div class="input-group"><input type="text" id="processMapSearch" onkeypress="processMapItemQuery();" class="form-control" placeholder="查询">';
        html += ' <div class="input-group-btn">';
        html += ' <button class="btn btn-primary" type="button" onclick="processMapItemQuery();"><i class="fa fa-search"></i></button>';
        html += ' </div>';
        html += ' </div>';
        html += ' <section id="processMapPanel"></section>';
        html += ' </li>';
        html += ' </ul>';
        html += ' </li>';
//系统消息
        html += ' <li role="presentation" class="dropdown">';
        html += ' <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">';
        html += ' <i class="fa fa-envelope-o tooltips" style="font-size: 26px"  data-placement="bottom" data-original-title="系统消息"></i>';
        html += ' <span class="badge bg-green">' + sysRemindUnReadCount + '</span>';
        html += ' </a>';
        html += ' <ul class="dropdown-menu list-unstyled msg_list" role="menu">';
        for (var i = 0; i < sysRemindUnRead.length; i++) {
            var item = sysRemindUnRead[i];
            html += '<li>';
            html += '<a>';
            html += '<span class="label ' + item.iconColor + ' "><i class="fa ' + item.msgsIcon + '"></i></span>';
            html += '<span>';
            html += '<span>John Smith</span>';
            html += '<span class="time">' + item.remindDate + '</span>';
            html += '</span>';
            html += '<span class="message">';
            if (item.remindTitle.length > 10) {
                html += item.remindTitle.substring(0, 10) + '...';
            }
            else {
                html += item.remindTitle;
            }
            html += '</span>';
            html += '</a>';
            html += '</li>';
        }


        html += '<li>';
        html += '<div class="text-center">';
        html += '<a href="/SysRemind/sysRemindIndex">';
        html += '<strong>查看所有</strong>';
        html += '<i class="fa fa-angle-right "></i>';
        html += '</a>';
        html += '</div>';
        html += ' </li>';
        html += ' </ul>';
        html += ' </li>';
//切换应用
        html += ' <li>';
        html += ' <a class="dropdown-toggle" href="/home/app">';
        html += ' <i class="fa fa-exchange tooltips " style="font-size: 26px"  data-placement="bottom" data-original-title="切换应用"></i>';
        html += ' </a>';
        html += ' </li>';

        html += ' </ul>';


        html += ' </nav>';
        html += ' </div>';
        return html;
    }
};




