<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        项目归档
                                    </div>
                                    <div class="card-tools">
                                        <button type="button" class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form class="form-horizontal">
                                    <input type="hidden" name="projectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    档案名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="档案名称"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    档案类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="fileType"
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请选择</option>
                                                        <c:forEach items="${FileArchivesTypeData}"
                                                                   var="ArchivesFileType">
                                                            <option value="${ArchivesFileType.key}">${ArchivesFileType.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="objArchives.loadTableList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_Archives_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>



                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script>

    var objArchives = {};

    objArchives.table = $("#tb_Archives_list");

    objArchives.loadTableList = function (_this) {
        var data = {projectId: '${projectInfo.id}'};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var table = $(objArchives.table.selector);
        var cols = [];
        cols.push({field: 'name', title: '档案名称', width: "10%"});
        cols.push({field: 'fileTypeName', title: '档案类型', width: "10%"});
        cols.push({field: 'fileCategoryName', title: '档案类别', width: "5%"});
        cols.push({field: 'fileSourceName', title: '档案来源', width: "5%"});
        cols.push({field: 'publicWayName', title: '公开方式', width: "5%"});
        cols.push({field: 'shelfLifeName', title: '保存期限', width: "5%"});
        // cols.push({
        //     field: 'groupId', title: '档案分组存放与否', width: "15%", formatter: function (value, row) {
        //         if (row.groupId) {
        //             return "已存放" ;
        //         }else {
        //             return "未存放" ;
        //         }
        //     }
        // });
        cols.push({field: 'saveLocation', title: '存放位置', width: "10%"});
        cols.push({field: 'number', title: '存放卷号', width: "10%"});
        cols.push({
            field: 'id', title: '文档',width: "15%",formatter: function (value, row) {
                var show = '<div id="_project_proxy' + row.id + '"></div>';
                FileUtils.getFileShows({
                    target: "project_proxy" + row.id,
                    formData: {
                        tableName: 'tb_ad_place_file_item_dto',
                        tableId: row.id,
                        fieldsName: "project_proxy" + row.id
                    },
                    showMode: 'table',
                    deleteFlag: false
                });
                return show;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/projectArchives/getAdPlaceFileItemDtoListByParam", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };


    $(document).ready(function () {
        objArchives.loadTableList();
    });

</script>


<script type="text/javascript">
    function saveform() {
        saveApprovalform("");
    }
</script>
</html>
