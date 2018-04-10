<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  对头部文件进行分离，并对样式进行调整，清除不需要的样式信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <img src="/assets/images/userIcon30.jpg" alt="">${baseViewDto.thisUser.userName}
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="#main_head_userInfo" data-toggle="modal"><i class="fa fa-sign-book pull-right"></i>个人信息</a></li>
                        <li><a href="/views/share/change_password.jsp" data-toggle="modal"><i class="fa fa-sign-book pull-right"></i>修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="/sysOperationManual/index"><i class="fa fa-sign-book pull-right"></i>在线手册</a></li>
                        <li><a href="/home/logout"><i class="fa fa-sign-out pull-right"></i>退出</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" onclick="getUserProcessList();">
                        <i class="fa fa-life-ring tooltips" style="font-size: 26px;" data-placement='bottom' data-original-title='流程地图'></i>
                    </a>
                    <ul id="mainHeadProcessMap" class="dropdown-menu list-unstyled  msg_list" role="menu" style="width: 780px; height: 400px;overflow: auto;padding: 10px;">

                        <li>
                            <div class="input-group"><input type="text" id="processMapSearch" onkeypress="processMapItemQuery();" class="form-control" placeholder="查询">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="button" onclick="processMapItemQuery();"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                            <section id="processMapPanel"></section>
                        </li>
                    </ul>
                </li>

                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-envelope-o tooltips" style="font-size: 26px"  data-placement='bottom' data-original-title='系统消息'></i>
                        <span class="badge bg-green">${sysRemindUnReadCount}</span>
                    </a>
                    <ul class="dropdown-menu list-unstyled msg_list" role="menu">
                        <c:forEach var="item" items="${sysRemindUnRead}">
                            <li>
                                <a>
                                    <span class="label ${item.iconColor}"><i class="fa ${item.msgsIcon}"></i></span>
                                    <span>
                                    <span>John Smith</span>
                                    <span class="time"> <fmt:formatDate value="${item.remindDate}" pattern="MM-dd HH:mm"/></span>
                                    </span>
                                    <span class="message">
                                         <c:if test="${fn:length(item.remindTitle)>10 }">
                                             ${fn:substring(item.remindTitle, 0, 10)}...
                                         </c:if>
                                        <c:if test="${fn:length(item.remindTitle)<=10 }">
                                            ${item.remindTitle }
                                        </c:if>
                                    </span>
                                </a>
                            </li>
                        </c:forEach>

                        <li>
                            <div class="text-center">
                                <a href="/SysRemind/sysRemindIndex">
                                    <strong>查看所有</strong>
                                    <i class="fa fa-angle-right "></i>
                                </a>
                            </div>
                        </li>
                    </ul>
                </li>

                <li>
                    <a class="dropdown-toggle" href="/home/app">
                        <i class="fa fa-exchange tooltips " style="font-size: 26px"  data-placement='bottom' data-original-title='切换应用'></i>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>


<div id="main_head_userInfo" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">个人信息</h4>
            </div>
            <div class="modal-body" style="height: 400px;">
                <div class="user-left">
                    <div class="center">
                        <h4>${baseViewDto.thisUser.userName}(${baseViewDto.thisUser.userAccount})</h4>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="user-image">
                                <div class="fileupload-new thumbnail"><img src="/assets/images/userIcon200.jpg" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-condensed table-hover table-bordered">
                        <thead>
                        <tr>
                            <th colspan="3">联系方法</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>邮箱:</td>
                            <td>
                                <a href="">
                                    ${vwOrgUserInfo.email}
                                </a></td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        <tr>
                            <td>电话:</td>
                            <td>${vwOrgUserInfo.mobile}</td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table table-condensed table-hover table-bordered" >
                        <thead>
                        <tr>
                            <th colspan="4">职位信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>岗位:</td>
                            <td>${vwOrgUserInfo.position}</td>
                            <td></td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        <tr>
                            <td>部门:</td>
                            <td>${vwOrgUserInfo.deptname}</td>
                            <td></td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        <tr>
                            <td>直接上级:</td>
                            <td>${vwOrgUserInfo.superiorname}</td>
                            <td></td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table table-condensed table-hover table-bordered">
                        <thead>
                        <tr>
                            <th colspan="3">附件信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>生日:</td>
                            <td>${vwOrgUserInfo.birthday}</td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        <tr>
                            <td>身份证:</td>
                            <td>${vwOrgUserInfo.ids}</td>
                            <td><a href="#panel_edit_account" class="show-tab"><i
                                    class="fa fa-pencil edit-user-info"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


<script type="application/javascript">
    var isLoadProcessMap = false;
    var userProcessMapItem = [];
    $(function () {
        //阻止事件冒泡
        $("#mainHeadProcessMap").click(function (e) {
            e.stopPropagation();
        })
    })
    function changePassword() {
        if ($("#old_password").val() == "") {
            Alert("原始密码不能为空.", 1, null, null);
            return false;
        }
        if ($("#new_password").val() != $("#re_password").val()) {
            Alert("新密码和确认密码不一致.", 1, null, null);
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "/orguser/changePassword",
            data: {
                oldPassword: $.md5($("#old_password").val()),
                newPassword: $.md5($("#new_password").val()),
                rePassword: $.md5($("#re_password").val())
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("修改密码成功，确认将退出系统.", 1, null, function () {
                        location.href = "/home/logout";
                    });
                }
                else {
                    Alert("删除数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //获取用户流程数据
    function getUserProcessList() {
        if (isLoadProcessMap) return;
        $("#processMapPanel").empty();
        $.ajax({
            url: "${pageContext.request.contextPath}/home/getUserProcessList",
            data: {},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        proceeMapAppendHtml(result.data);
                        isLoadProcessMap = true;
                        userProcessMapItem = result.data;
                    }
                }
                else {
                    Alert("删除数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //流程地图数据查询
    function processMapItemQuery() {
        var search = $("#processMapSearch").val();
        if (!search || search.length <= 0) {
            proceeMapAppendHtml(userProcessMapItem);
            return;
        }
        var tempProcessMapItem = [];
        if (userProcessMapItem && userProcessMapItem.length > 0) {
            $.each(userProcessMapItem, function (i, item) {
                if (item.cnName.indexOf(search) > -1) {
                    tempProcessMapItem.push(item);
                }
            })
        }
        proceeMapAppendHtml(tempProcessMapItem);
    }

    //加载html
    function proceeMapAppendHtml(processMapItem) {
        $("#processMapPanel").empty();
        var html = "";
        if (processMapItem && processMapItem.length > 0) {
            var appkey = "";
            $.each(processMapItem, function (i, item) {
                if (item.appKey != appkey) {
                    if (appkey != "") {
                        html += '</div>';
                    }
                    html += '<h2 class="">' + item.appKeyName + '</h2>';
                    html += '<div class="row fontawesome-icon-list">';
                    appkey = item.appKey;
                }
                html += '<div class="fa-hover col-md-3 ">';
                html += '<a href="' + item.url + '" style="padding: 0px;" target="_blank">';
//                html += '<i class="fa fa-circle-o-notch"></i>';
                html += item.cnName;
                html += '</a>';
                html += '</div>';

            })
            if (html != "") {

                html += '</div>';
                html += '</div>';
            }
        }else {
            html = "没有找到相应的流程!";
        }
        $("#processMapPanel").append(html);
    }
</script>