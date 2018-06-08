<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2> 项目信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_csr_project_info" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" id="id" name="id" value="${csrProjectInfo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${csrProjectInfo.name}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">客户类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.customerTypeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托单位
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.entrustmentUnitName}</label>
                                </div>
                            </div>


                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.entrustPurposeName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日</label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate value='${csrProjectInfo.valuationDate}'
                                                                                pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目分配人</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.distributionUserName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">取行序号</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.startRowNumber}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${csrProjectInfo.remark}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">客户附件</label>
                                <div class="col-sm-11">
                                    <div id="_upload_file"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="x_title">
                    <h3> 项目组分派</h3>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="x_content">
                    <form id="frmCsrBorrower" class="form-horizontal">
                        <div class="form-group">
                            <div>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50" placeholder="项目 名称" id="projectNameV"
                                           name="projectNameV" class="form-control">

                                </div>
                                <div class="col-sm-5">
                                    <button type="button" class="btn btn-primary"
                                            onclick="loadGroupProjectTableList();">
                                        查询
                                    </button>
                                    <button type="button" class="btn btn-primary" onclick="addGrpupProject();">
                                        创建项目组
                                    </button>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <table class="table table-bordered" id="groupProjectTableList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<div id="divBoxGroupProject" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="240">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">项目组添加</h3>
            </div>
            <form id="frmGroupProject" class="form-horizontal">
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
                                            <input type="hidden" name="id">
                                            <input type="hidden" id="csrProjectId" name="csrProjectId">
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
                                            <input type="text" data-rule-number='true' name="number" placeholder="文号（请输入数字）"
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
                                                <input type="text" class="form-control" readonly="readonly"
                                                       required="required" onclick="selectUserAccountManager()"
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

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目成员
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="hidden" id="projectMemberID" name="projectMember">
                                                <input type="text" class="form-control" readonly="readonly"
                                                       required="required" onclick="selectUserAccountMember()"
                                                       id="projectMember" maxlength="200">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-default docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="selectUserAccountMember()">
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

<div id="divBoxCsrBorrowerSelect" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" data-height="335">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">客户分派</h3>
            </div>
            <form id="csrBorrowerSelect" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">


                                <div class="form-group">
                                    <input type="hidden" name="csrProjectInfoGroupID" id="csrProjectInfoGroupID">
                                    <input type="hidden" name="csrProjectId" id="csrProjectIdV">
                                    <div class="x-valid">
                                        <div>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-maxlength="50" placeholder="二级分行"
                                                       id="secondLevelBranch" name="secondLevelBranch" class="form-control">
                                            </div>
                                        </div>
                                        <div>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       placeholder="一级分行" id="firstLevelBranch" name="firstLevelBranch" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="button" class="btn btn-primary" value="查询" onclick="loadBorrowerList('','csrBorrowerTableList')">
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-sm-2">
                                        </div>
                                    </div>


                                    <div class="x-valid">
                                        <div>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-maxlength="50" placeholder="二级分行"
                                                       id="secondLevelBranchEdit" name="secondLevelBranch" class="form-control">
                                            </div>
                                        </div>
                                        <div>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       placeholder="一级分行" id="firstLevelBranchEdit" name="firstLevelBranch" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="button" class="btn btn-primary" value="查询" onclick="loadBorrowerList('','csrBorrowerTableListEdit')">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <table class="table table-bordered" id="csrBorrowerTableList">
                                                <caption>未分派</caption>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-sm-2 bs-glyphicons" style="padding-top:140px;padding-left:30px;">
                                            <a href="#/chevron-left" class="btn btn-primary" onclick="submitGroupProject()"><i class="fa fa-chevron-right"></i></a>
                                            <a href="#/chevron-left" class="btn btn-primary" onclick="cancelCsrBorrower()"><i class="fa fa-chevron-left"></i></a>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <table class="table table-bordered" id="csrBorrowerTableListEdit">
                                                <caption>已经分派</caption>
                                            </table>
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
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(function () {
        loadGroupProjectTableList();
    });

    //加载 客户信息 数据列表
    function loadBorrowerList(id,tableID) {
        var cols = [];
        cols.push({field: 'checkbox', checkbox: true});
        cols.push({field: 'secondLevelBranch', title: '二级分行'});
        cols.push({field: 'firstLevelBranch', title: '一级分行'});
        cols.push({field: 'name', title: '名字'});
        cols.push({field: 'id', visible: false, title: "id"});
        // cols.push({field: 'maritalStatus', title: '婚否'});
        // cols.push({field: 'workUnit', title: '职务'});
        // cols.push({field: 'presentAddress', title: '地址'});
        $("#"+tableID).bootstrapTable('destroy');
        if (tableID=='csrBorrowerTableList'){//未分派的列表
            TableInit(""+tableID, "${pageContext.request.contextPath}/csrProjectInfo/borrowerLists", cols, {
                secondLevelBranch: $("#secondLevelBranch").val(),
                firstLevelBranch: $("#firstLevelBranch").val(),
                csrProjectInfoID: '${csrProjectInfo.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
                onDblClickRow: function (row) {
                    oneSubmitGroupProject(row);
                }
            });
        }else {// 编辑客户列表
            if (id==''){
                id = $("#csrProjectInfoGroupID").val();
            }
            TableInit(""+tableID, "${pageContext.request.contextPath}/csrProjectInfo/borrowerLists", cols, {
                secondLevelBranch: $("#secondLevelBranchEdit").val(),
                firstLevelBranch: $("#firstLevelBranchEdit").val(),
                csrProjectInfoID: '${csrProjectInfo.id}',
                csrProjectInfoGroupID:id
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
                onDblClickRow: function (row) {
                    oneCancelCsrBorrower(row);
                }
            });
        }
    }

    //刷新客户列表
    function loadRefreshBorrower(tableID) {
        if (tableID=='csrBorrowerTableList'){
            var opt = {
                url: "${pageContext.request.contextPath}/csrProjectInfo/borrowerLists",
                query: {
                    csrProjectInfoID: '${csrProjectInfo.id}'
                }
            };
            $("#"+tableID).bootstrapTable("refresh", opt);
        }else {
            var opt = {
                url: "${pageContext.request.contextPath}/csrProjectInfo/borrowerLists",
                query: {
                    csrProjectInfoID: '${csrProjectInfo.id}',
                    csrProjectInfoGroupID:$("#csrProjectInfoGroupID").val()
                }
            };
            $("#"+tableID).bootstrapTable("refresh", opt);
        }
    }

    //分派选择
    function submitGroupSelect(id) {
        var result = $("#groupProjectTableList").bootstrapTable('getSelections');
        $("#csrProjectInfoGroupID").val(id);
        $("#csrProjectIdV").val('${csrProjectInfo.id}');
        loadBorrowerList(id,'csrBorrowerTableListEdit');
        loadBorrowerList('','csrBorrowerTableList');
        $('#divBoxCsrBorrowerSelect').modal();
    }

    //项目组 客户信息 分派 取消
    function cancelCsrBorrower() {
        var data = formParams("csrBorrowerSelect");
        var result = $("#csrBorrowerTableListEdit").bootstrapTable('getSelections');
        var csrBorrowerIDS = "";
        for (var i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                csrBorrowerIDS += result[i].id;
            } else {
                csrBorrowerIDS += result[i].id + ",";
            }
        }
        var flag = (result.length>0);
        data.csrBorrowerID = csrBorrowerIDS;
        if (flag){
            $.ajax({
                url: "${pageContext.request.contextPath}/csrProjectInfo/cancelCsrBorrower",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success(' 项目组分派取消成功');
                        loadRefreshBorrower('csrBorrowerTableList');
                        loadRefreshBorrower('csrBorrowerTableListEdit');
                    }
                    else {
                        Alert("项目组分派取消失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }else {
            Alert("没有要取消分派的客户!");
        }
    }

    //项目组 客户信息 单个分派 取消
    function oneCancelCsrBorrower(row) {
        var data = {
            csrProjectId:row.csrProjectId,
            csrBorrowerID:row.id,
            csrProjectInfoGroupID:row.groupId
        };
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/cancelCsrBorrower",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success(' 项目组分派取消成功');
                    loadRefreshBorrower('csrBorrowerTableList');
                    loadRefreshBorrower('csrBorrowerTableListEdit');
                }
                else {
                    Alert("项目组分派取消失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //项目组 客户信息 单个分派
    function oneSubmitGroupProject(row) {
        var data = formParams("csrBorrowerSelect");
        data.csrBorrowerIDS = row.id;
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/submitGroupProject",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success(' 项目组分派成功');
                    loadRefreshBorrower('csrBorrowerTableList');
                    loadRefreshBorrower('csrBorrowerTableListEdit');
                }
                else {
                    Alert("项目组分派失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //项目组 客户信息 多个分派
    function submitGroupProject() {
        var data = formParams("csrBorrowerSelect");
        var result = $("#csrBorrowerTableList").bootstrapTable('getSelections');
        var csrBorrowerIDS = "";
        for (var i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                csrBorrowerIDS += result[i].id;
            } else {
                csrBorrowerIDS += result[i].id + ",";
            }
        }
        data.csrBorrowerIDS = csrBorrowerIDS;
        var flag = (result.length>0);
        if (flag){
            $.ajax({
                url: "${pageContext.request.contextPath}/csrProjectInfo/submitGroupProject",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success(' 项目组分派成功');
                        loadRefreshBorrower('csrBorrowerTableList');
                        loadRefreshBorrower('csrBorrowerTableListEdit');
                    }
                    else {
                        Alert("项目组分派失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }else {
            Alert("没有要分派的数据!");
        }

    }

    //项目组 保存
    function saveGroupProject() {
        var data = formParams("frmGroupProject")
        if ($("#frmGroupProject").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/csrProjectInfo/saveAndUpdateGroupProject",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadGroupProjectTableList();
                        $('#divBoxGroupProject').modal('hide');
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
    //项目分组  删除
    function deleteGrpupProject(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/groupProject/delete",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('删除成功');
                    loadGroupProjectTableList();//重载
                }
                else {
                    Alert("删除数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //项目组 编辑
    function editGrpupProject(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/groupProject/get",
            type: "GET",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $("#frmGroupProject").clearAll();
                $("#frmGroupProject").initForm(result);
                $("#projectManagerID").val(result.projectManager);
                $("#projectManager").val(result.projectManagerName);
                $("#projectMemberID").val(result.projectMember);
                $("#projectMember").val(result.projectMemberName);
                $('#divBoxGroupProject').modal();
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //显示项目 添加页面
    function addGrpupProject() {
        var id = '${csrProjectInfo.id}';
        $("#frmGroupProject").clearAll();
        $("#csrProjectId").val(id);
        $("#projectName").val('${csrProjectInfo.name}');
        $('#divBoxGroupProject').modal();
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
                $("#projectMember").val(data.name);
                $("#projectMemberID").val(data.account);
            },
            multi: true
        });
    }

    //加载项目组列表信息
    function loadGroupProjectTableList() {
        var cols = [];
        // cols.push({field: 'radio', radio:true});
        cols.push({field: 'projectName', title: '项目组名字'});
        cols.push({field: 'id', visible: false, title: "id"});
        cols.push({field: 'projectManagerName', title: '项目经理'});
        cols.push({field: 'projectMemberName', title: '项目成员'});
        cols.push({field: 'number', title: '文号'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editGrpupProject(' + row.id + ',\'groupProjectTableList\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除"  onclick="deleteGrpupProject(' + row.id + ',\'groupProjectTableList\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:submitGroupSelect(' + row.id + ');" >分派</i></a>';
                // str += '<a class="btn btn-xs btn-success" href="javascript:submitGroupgEdit(' + row.id + ');" >分派编辑</i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#groupProjectTableList").bootstrapTable('destroy');
        TableInit("groupProjectTableList", "${pageContext.request.contextPath}/csrProjectInfo/groupVoList", cols, {
            projectID: '${csrProjectInfo.id}',
            projectName: $("#projectNameV").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            // undefinedText:"没有",
            undefinedText:"",
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
</script>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">

    $(document).ready(function () {
        loadAttachmentList();
    });

    //加载附件列表信息
    function loadAttachmentList() {
        FileUtils.getFileShows({
            target: "upload_file",
            formData: {
                tableName: "tb_csr_project_info",
                tableId: ${csrProjectInfo.id}
            },
            deleteFlag: false
        })
    }


    //检查是否完全分配
    function checkCsrBorrower() {
        var isAllDistribution = true;
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/checkCsrBorrower",
            type: "post",
            dataType: "json",
            async: false,
            data: {csrProjectInfoID: '${csrProjectInfo.id}'},
            success: function (result) {
                console.log(result);
                isAllDistribution = result.ret;
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
        return isAllDistribution;
    }


    //数据提交
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var rdoValue = $("input[name='conclusion']:checked").val();
        //检测是否分派完成!
        if (rdoValue == "Approval" && !checkCsrBorrower()) {//审批结论为同意才验证
            Alert("还有未分配的借款人或者项目组中有项目没有分派客户!");
            return false;
        }
        var data = formParams("frm_approval");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/csrProjectInfo/projectApprovalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

</script>
</html>
