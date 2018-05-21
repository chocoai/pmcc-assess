<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/17
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>报告模板管理</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div id="tree" class="col-md-3">

                            </div>

                            <div class="col-md-9">
                                <div class="x_content">

                                    <div class="col-xs-10">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <c:forEach var="item" items="${entrust}">
                                                <div class="tab-pane" id="settings-${item.id}">
                                                    <label class="label label-info">${item.name}</label>
                                                    <c:forEach var="reportItem" items="${reportType}">
                                                        <label class="radio-inline"><input type="radio" value="${reportItem.id}" name="conclusion" checked="checked"
                                                                                           onclick="chkRadioClick()"> ${reportItem.name}</label>
                                                    </c:forEach>
                                                    <table id="tb_fileds_list_${item.id}" class="table table-bordered"></table>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="col-xs-2">
                                        <!-- required for floating -->
                                        <!-- Nav tabs -->
                                        <ul class="nav nav-tabs tabs-right">
                                            <c:forEach var="item" items="${entrust}">
                                                <li id="settings-li-${item.id}" class=""><a href="#settings-${item.id}" data-toggle="tab" aria-expanded="false">${item.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>


<script type="text/javascript">
    $(function () {
        loadTree();
        $("#settings-${firstEntrust}").addClass("active");
        $("#settings-li-${firstEntrust}").addClass("active");
        loadDatagrid(${firstEntrust});
    })
    function loadTree() {
        initBaseTreeView("tree", "${pageContext.request.contextPath}/templateSet/queryCustomerTree", {pid: -1}, false, function (objs) {
            // treeView_setValue("tree", baseValue);
            objs.on('nodeSelected', function (event, node) {

            });
        });
    }

    function loadDatagrid(id) {
        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({field: 'userAccount', title: '书签名称'});
        cols.push({field: 'userName', title: '字段类型'});
        cols.push({field: 'email', title: '字段值'});
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editUser(" + index + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='删除' onclick='delUser(index)' style='margin-left: 5px'><i class='fa fa-minus fa-white' title='删除'></i></a>";
                return s;
            }
        });

        TableInit("tb_fileds_list_" + id, "${pageContext.request.contextPath}/sysUser/getUserInfoByDepartmentId?departmentId=", cols,
            {
                customId: 0,
                entrustId: 0,
                reportId: 0
            }, {
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
</script>




