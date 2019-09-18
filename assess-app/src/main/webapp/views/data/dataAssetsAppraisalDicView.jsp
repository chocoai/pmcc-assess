<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <div id="toolbar">
                        <div class="container-fluid">
                            <div class="pull-left">
                                <button type="button" class="btn btn-default" οnclick="addVideoShow();">
                                                   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                               
                                </button>
                                <button type="button" class="btn btn-default" οnclick="editMemberInfoShow();">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                                </button>
                                <button type="button" class="btn btn-default" οnclick="delMemberVideo();">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </div>


                            <div class="pull-right" id="frmQuery" style="padding-bottom:10px;">
                                <input name="name" type="text" placeholder="名称" class="form-control"
                                       style="float:left;width:150px;margin-right:5px;">
                                <input name="fieldName" type="text" placeholder="字段key" class="form-control"
                                       style="float:left;width:150px;margin-right:5px;">

                                <button onclick="dataAssetsAppraisalDic.loadBootstrapTable();" type="button"
                                        class="btn btn-primary btn-space">
                                    搜索<span class="fa fa-search" aria-hidden="true" class="btn-icon-space"></span>
                                </button>

                                <button onclick="dataAssetsAppraisalDic.resetSearch();" type="button"
                                        class="btn btn-default btn-space">
                                    重置<span class="fa fa-eraser" aria-hidden="true" class="btn-icon-space"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered" id="tableDataAssetsAppraisalDic">
                        <!-- cerare document add ajax data-->
                    </table>

                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript">

    var dataAssetsAppraisalDic = {};

    dataAssetsAppraisalDic.frmQuery = $("#frmQuery");

    dataAssetsAppraisalDic.frm = $("#frmDataAssetsAppraisalDic");

    dataAssetsAppraisalDic.table = $("#tableDataAssetsAppraisalDic");

    dataAssetsAppraisalDic.loadBootstrapTable = function () {
        var method = {};
        method.toolbar = "#toolbar";
        method.search = false;
        var select = dataAssetsAppraisalDic.getQueryData();
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '名称', width: "5%"});
        cols.push({field: 'fieldName', title: '字段', width: "5%"});
        cols.push({field: 'sorting', title: '排序', width: "5%"});
        dataAssetsAppraisalDic.table.bootstrapTable('destroy');
        TableInit(dataAssetsAppraisalDic.table.attr("id"), "${pageContext.request.contextPath}/dataAssetsAppraisalDic/getBootstrapTableVo", cols, select, method);
    };

    dataAssetsAppraisalDic.resetSearch = function () {
        dataAssetsAppraisalDic.frmQuery.find("input[name]").val('');
        dataAssetsAppraisalDic.frmQuery.find("input[fieldName]").val('');
    };

    dataAssetsAppraisalDic.getQueryData = function () {
        var data = {};
        data.name = dataAssetsAppraisalDic.frmQuery.find("input[name]").val();
        data.fieldName = dataAssetsAppraisalDic.frmQuery.find("input[fieldName]").val();
        return data;
    };

    $(document).ready(function () {
        dataAssetsAppraisalDic.loadBootstrapTable();
    });


</script>

</html>
