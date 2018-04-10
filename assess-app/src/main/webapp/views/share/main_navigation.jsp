<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="col-md-3 left_col">
        <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
                <a href="/home/main" class="site_title"><i class="fa fa-send-o"></i> <span>${baseViewDto.thisApp.name}</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
                <div class="profile_pic">
                    <img src="/assets/images/userIcon30.jpg" alt="..." class="img-circle profile_img">
                </div>
                <div class="profile_info">
                    <span>欢迎,</span>
                    <h2>${baseViewDto.thisUser.userName}</h2>
                </div>
            </div>
            <!-- /menu profile quick info -->

            <br/>

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                <div class="menu_section">
                    <ul class="nav side-menu">
                        <%--<li>--%>
                        <%--<a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>--%>
                        <%--<ul class="nav child_menu">、、current-page--%>
                        <%--<li><a href="index.html">Dashboard</a></li>--%>
                        <%--<li><a href="index2.html">Dashboard2</a></li>--%>
                        <%--<li><a href="index3.html">Dashboard3</a></li>--%>
                        <%--</ul>--%>
                        <%--</li>--%>
                        <c:forEach var="item" items="${baseViewDto.thisAppMenuTrees}">
                        <c:if test="${item.nodes!=null}">
                        <c:if test="${item.id==baseViewDto.currentMenu.pid}"> <%--如果当前菜单为选中的二级菜单的父级，则展开并选中--%>
                        <li class="active">
                            </c:if>
                            <c:if test="${item.id!=baseViewDto.currentMenu.pid}"> <%--反之不选中--%>
                        <li>
                            </c:if>
                            <a href="javascript:void(0)"><i class="fa ${item.icon}"></i>${item.name} <span class="fa fa-chevron-down"></span>
                            </a>
                            <c:if test="${item.id==baseViewDto.currentMenu.pid}"> <%--如果当前菜单为选中的二级菜单的父级，则展开并选中--%>
                            <ul class="nav child_menu" style="display: block">
                                </c:if>
                                <c:if test="${item.id!=baseViewDto.currentMenu.pid}"> <%--反之不选中--%>
                                <ul class="nav child_menu" style="display: none">
                                    </c:if>

                                    <c:forEach var="menuItem" items="${item.nodes}"> <%--循环取二级菜单--%>
                                        <c:if test="${menuItem.id==baseViewDto.currentMenu.id}">  <%--二级菜单选中则添加样式--%>
                                            <li class="current-page">
                                        </c:if>
                                        <c:if test="${menuItem.id!=baseViewDto.currentMenu.id}">
                                            <li>
                                        </c:if>
                                        <a href="${menuItem.url}?menuId=${menuItem.id}">${menuItem.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                </li>
                                </c:if>
                                </c:forEach>


                            </ul>
                </div>
            </div>
            <!-- /sidebar menu -->

        </div>
    </div>
</div>

