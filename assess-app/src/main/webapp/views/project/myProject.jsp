<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="项目名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>

                                        <label for="queryName" class="col-md-1 col-form-label">状态</label>
                                        <div class="col-md-3 p-0">
                                            <select id="status" class="form-control input-full">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${statusEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="loadProjectList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_myProject">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="application/javascript">
    $(function () {
        loadProjectList();

    })
    function loadProjectList() {
        var cols = [];
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称',width:'30%'});
        cols.push({field: 'useUnitName', title: '使用报告单位',width:'10%'});
        cols.push({field: 'departmentName', title: '评估部门',width:'5%'});
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '10%', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<label style='padding: 5px;' class='label label-info'>" + row.userAccountManagerName.split("_")[0] + "</label>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '项目类型',width:'5%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '项目状态',width:'5%'});
        cols.push({field: 'entrustPurposeName', title: '委托目的',width:'5%'});
        cols.push({field: 'loanTypeName', title: '贷款类型',width:'5%'});
        cols.push({
            field: 'finishPre', title: '项目进度',width:'10%', formatter: function (value, row, index) {
                var s = "<div class='progress progress_sm' style='margin-bottom: 0px;'>";
                if (value == "100") {
                    s += "<div class='progress-bar progress-bar-success' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                else {
                    s += "<div class='progress-bar progress-bar-warning' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '立项时间',width:'10%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (row.projectStatus) {
                    if (row.projectStatus == '草稿') {
                        str += '<button onclick="editHref(' + index + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="重新申请">';
                        str += '<i class="fa fa-pen"></i>';
                        str += '</button>';
                    } else {
                        str += '<button onclick="checkDetail(' + row.id + ')" style="margin-left: 5px;" class="btn btn-icon btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                        str += '<i class="fa fa-search"></i>';
                        str += '</button>';
                    }
                }
                return str;

                var str = '<button onclick="dataObjFun.showDataHousePriceIndexDetail(' + row.id + ')" style="margin-left: 5px;" class="btn btn-icon btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.editMasterById(' + index + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteMasterById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;

            }
        });
        $("#tb_myProject").bootstrapTable('destroy');
        TableInit("tb_myProject", "${pageContext.request.contextPath}/projectCenter/getMyProjectList", cols, {
            queryName: $("#queryName").val(),
            projectStatus:$("#status").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    function checkDetail(id) {
        var href = "${pageContext.request.contextPath}/projectCenter/projectInfo";
        href += "?projectId=" + id;
        window.open(href, "");
    }

    function editHref(id) {
        var href = "${pageContext.request.contextPath}/projectInfo/projectInfoEdit";
        href += "?projectId=" + id;
        window.open(href, "");
    }
</script>
</body>
</html>
