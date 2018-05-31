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
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmGroupProject" class="form-horizontal">
                        <div class="form-group">
                            <div>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50" placeholder="二级分行 名称" id="queryName" name="queryName" class="form-control">
                                </div>
                                <div class="col-sm-6">
                                    <button type="button" class="btn btn-primary" onclick="loadCsrGroupPrpject();">
                                        查询
                                    </button>
                                </div>
                                <div class="col-sm-4">
                                    <label class="col-sm-2">全选</label>
                                    <div class="col-sm-2"><input type="checkbox" onclick="checkFun()"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <table class="table table-bordered" id="tb_List">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="button" class="btn btn-success" onclick="addGrpupProject()" value="创建项目组">
                                <input type="button" class="btn btn-success"  value="分派提交" onclick="submitGroupSelect()">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" data-height="240">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">项目组添加</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="projectName" id="projectName" placeholder="项目名称"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            文号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="number" id="number" placeholder="文号"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目经理<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="hidden" id="projectManagerID" name="projectManager">
                                                <input type="text" class="form-control" readonly="readonly" required="required"
                                                       id="projectManager" maxlength="200">
                                                <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        data-toggle="tooltip"
                                                        data-original-title="选择" onclick="selectUserAccountManager()">
                                                <i class="fa fa-search"></i>
                                                </button>
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div  class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目成员<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="hidden" id="projectMemberID" name="projectMember">
                                                <input type="text" class="form-control" readonly="readonly" required="required"
                                                       id="projectMember" maxlength="200">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择" onclick="selectUserAccountMember()">
                                                    <i class="fa fa-search"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            onclick="$(this).closest('.input-group').find('input').val('');"
                                                            data-toggle="tooltip" data-original-title="清除">
                                                    <i class="fa fa-trash-o"></i>
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveGroupProject()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxGroup" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" data-height="135">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">项目组选择</h3>
            </div>
            <form id="groupProject" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目选择<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control">
                                                <option>请选择项目组</option>
                                                <c:forEach items="${groupList}" var="item">
                                                    <option value="${item.id}">${item.projectName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/datadic-select.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    loadCsrGroupPrpject();

    function addGrpupProject() {
        $("#frm").clearAll();
        $('#divBox').modal();
    }

    //分派选择
    function submitGroupSelect() {
        var projectGroup = formParams("frmGroupProject");
        console.log(projectGroup);
        $('#divBoxGroup').modal();
    }

    //项目组 保存
    function saveGroupProject() {
        var data = formParams("frm")
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/csrTempGroupProject/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadCsrGroupPrpject();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    // 项目经理
    function selectUserAccountManager() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#projectManager").val(data.name);
                $("#projectManagerID").val(data.account);
            }
        });
    }
    //项目成员
    function selectUserAccountMember() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#projectMember").val(data.name+","+$("#projectMember").val());
                $("#projectMemberID").val(data.account+","+$("#projectMemberID").val());
            }
        });
    }

    function checkFun() {
        $(document).ready(function () {
            var size = $("#tb_List :checkbox").size();
            var checked = $("#tb_List :checkbox:checked").size();
            var checkedNot = $("#tb_List :checkbox:not(:checked)").size();
            if (checked!=size){
                $("#tb_List :checkbox").attr("checked", true);
                // $("#tb_List :checkbox").prop("checked",true);
            }else if (size==checked){
                console.log("dd");
                $("#tb_List :checkbox").attr("checked", false);
                // $("#tb_List :checkbox").removeProp("checked");
            }
        });
    }

    //加载 客户信息 数据列表
    function loadCsrGroupPrpject() {
        var cols = [];
        cols.push({field: 'name', title: '名字'});
        cols.push({field: 'maritalStatus', title: '婚否'});
        cols.push({field: 'workUnit', title: '职务'});
        cols.push({field: 'presentAddress', title: '地址'});
        cols.push({
            field: 'id', title: '分派', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<input type="checkbox" name="csrBorrowerID" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="是否选中"  value="' + row.id + '"></input>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var queryName = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/csrTempGroupProject/list", cols, {
            displayName: queryName
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }


</script>
</html>
