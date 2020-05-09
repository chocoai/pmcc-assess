<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目成员变更</title>

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
                    <!-- 公共模块end -->

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        项目成员变更
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="member_change_form">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    变更原因
                                                </label>
                                                <div class="col-sm-11">
                                                    <textarea class="form-control input-full" id="changeReason"
                                                              name="changeReason"
                                                              required>${costsProjectChangeLog.changeReason}</textarea>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <p id="change_tool">
                                    <button type="button" class="btn btn-success btn-sm"
                                            onclick="memberChangeObj.addMember();"><span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增成员
                                    </button>
                                </p>
                                <table class="table table-striped jambo_table bulk_action table-bordered"
                                       id="member_change_table"></table>
                                <hr/>


                            </div>
                        </div>
                    </div>


                    <!-- 公共尾部模块引用 -->
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close();">
                                取消
                            </button>
                            <button id="commit_btn" style="margin-left: 10px;" type="button" class="btn btn-primary" onclick="memberChangeObj.submit();">
                                提交
                            </button>

                        </div>
                    </div>
                    <%--返回修改--%>
                    <c:if test="${processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    var memberChangeObj = {
        projectId: "${projectInfo.id}",
        processInsId: "${processInsId}" == null ? "0" : "${processInsId}",
        member_change_table: $("#member_change_table"),
        member_change_form: $("#member_change_form")
    };


    memberChangeObj.getMemberList = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/memberList",
            data: {
                projectId: memberChangeObj.projectId,
                processInsId: memberChangeObj.processInsId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    memberChangeObj.renderMemberChangeTable(result.data);
                } else {
                    AlertError("获取项目成员列表失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    memberChangeObj.renderMemberChangeTable = function (data) {
        memberChangeObj.member_change_table.bootstrapTable('destroy');
        var cols = [
            [{
                title: '变更前成员',
                colspan: 2,
                align: 'center'
            }, {
                title: '变更后成员',
                colspan: 3,
                align: 'center'
            }],
            [{
                field: 'originalManagerName',
                title: '项目经理',
                align: 'center'
            }, {
                field: 'originalMemberName',
                title: '项目成员',
                align: 'center'
            }, {
                field: 'newManagerName',
                title: '项目经理',
                align: 'center',
                formatter: function (value, row, index) {
                    var customStr = String.format("<div><span>{0}<span><button type='button' class='btn btn-xs btn-primary' style='margin-left: 5px;' onclick='memberChangeObj.replaceManage(\"{1}\")'><i class='fa fa-pen'></i></button></div>", value, row.newManagerName);
                    return customStr;
                }
            }, {
                field: 'newMemberName',
                title: '项目成员',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return value;
                    }

                    return "";
                }
            }, {
                field: "op",
                title: "操作",
                align: 'center',
                formatter: function (value, row, index) {

                    if (row.newMemberAccount) {
                        var str = '<div class="btn-margin">';
                        //str += '<a id="item_edit" class="re btn btn-xs btn-primary" href="javascript:void(0);"><i class="fa fa-edit"></i>变更</a>';
                        str += '<button type="button" id="item_edit"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="变更">';
                        str += '<i class="fa fa-pen"></i>';
                        str += '</button>';
                        str += '<button type="button" id="item_delete"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                        str += '<i class="fa fa-minus"></i>';
                        str += '</button>';
                        //str += '<a id="item_delete" class="re btn btn-xs btn-warning" href="javascript:void(0);"><i class="fa fa-trash"></i>删除</a>';
                        str += '</div>';
                        return str;
                    } else {
                        return "";
                    }

                },
                events: {
                    'click #item_edit': function (e, value, row, index) {
                        memberChangeObj.replaceMember(row);
                    },
                    'click #item_delete': function (e, value, row, index) {
                        AlertConfirm("是否确认删除", String.format("确定要移除成员【{0}】?", row.newMemberName), function () {
                            memberChangeObj.removeMember(row);
                        })
                    }
                }
            }]
        ];


        TableClient(memberChangeObj.member_change_table, cols, data, {
            toolbar: "#change_tool",
            showNumber: false,
            search: false,
            showRefresh: false,
            pageSize: 10,
        }, false);

        memberChangeObj.member_change_table.bootstrapTable('mergeCells', {
            index: 0,
            field: 'originalManagerName',
            rowspan: data.length
        });

        memberChangeObj.member_change_table.bootstrapTable('mergeCells', {
            index: 0,
            field: 'newManagerName',
            rowspan: data.length
        });

    };

    memberChangeObj.addMember = function () {
        erpEmployee.select({
            multi: true,
            currOrgId: '${projectInfo.departmentId}',
            value: "",
            onSelected: function (data) {
                if (data.base) {
                    var selectUsers = data.account;

                    $.ajax({
                        url: "${pageContext.request.contextPath}/member.change/addMember",
                        data: {
                            projectId: memberChangeObj.projectId,
                            processInsId: memberChangeObj.processInsId,
                            members: selectUsers
                        },
                        type: "post",
                        dataType: "json",
                        success: function (result) {
                            if (result.ret) {
                                memberChangeObj.getMemberList();
                            } else {
                                AlertError("添加成员失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    });
                } else {
                    AlertError("未选择成员!");
                }
            }
        });
    };

    memberChangeObj.removeMember = function (row) {

        Loading.progressShow("正在移除成员...");
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/removeMember",
            data: {
                projectId: memberChangeObj.projectId,
                processInsId: memberChangeObj.processInsId,
                member: row.newMemberAccount
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    memberChangeObj.getMemberList();
                } else {
                    AlertError("移除成员失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    memberChangeObj.replaceMember = function (row) {
        erpEmployee.select({
            multi: false,
            currOrgId: '${projectInfo.departmentId}',
            value: row.newMemberName,
            onSelected: function (data) {
                if (data.base) {
                    var selectUser = data.account;

                    $.ajax({
                        url: "${pageContext.request.contextPath}/member.change/replaceMember",
                        data: {
                            projectId: memberChangeObj.projectId,
                            processInsId: memberChangeObj.processInsId,
                            oldMember: row.newMemberAccount,
                            newMember: selectUser
                        },
                        type: "post",
                        dataType: "json",
                        success: function (result) {
                            if (result.ret) {
                                memberChangeObj.getMemberList();
                            } else {
                                AlertError("替换成员失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    });
                } else {
                    AlertError("未选择成员!");
                }
            }
        });
    };


    memberChangeObj.replaceManage = function (oldManager) {
        var src = oldManager;
        var oldManagerAccount = src.split("_").pop();
        erpEmployee.select({
            multi: false,
            currOrgId: '${projectInfo.departmentId}',
            value: oldManager,
            onSelected: function (data) {
                if (data.account) {
                    var selectUser = data.account;
                    memberChangeObj.replaceManageAccount(oldManagerAccount, selectUser);
                } else {
                    AlertError("未选择成员!");
                }
            }
        });
    };

    memberChangeObj.replaceManageAccount = function (oldManagerAccount, selectUser) {
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/replaceManage",
            data: {
                projectId: memberChangeObj.projectId,
                processInsId: memberChangeObj.processInsId,
                oldManager: oldManagerAccount,
                newManage: selectUser
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    memberChangeObj.getMemberList();
                } else {
                    AlertError("替换项目经理失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * 提交数据
     * @returns {*}
     */
    memberChangeObj.submit = function () {
        var changeReason = $("#changeReason").val();
        if (!changeReason) {
            notifyInfo('警告',"变更原因必须填写!");
            $("#changeReason").focus();
            return false;
        }
        if ("${processInsId}" == "0") {
            //申请
            memberChangeObj.apply();
        } else {
            //修改提交
            memberChangeObj.editCommit();
        }
    };


    memberChangeObj.apply = function () {

        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: getContextPath() + "/member.change/applyCommit",
            type: "post",
            dataType: "json",
            data: {
                projectId: memberChangeObj.projectId,
                processInsId: memberChangeObj.processInsId,
                changeReason: $("#changeReason").val()
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "变更申请提交成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("提交数据失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    memberChangeObj.editCommit = function () {
        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));

        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: getContextPath() + "/member.change/editCommit",
            type: "post",
            dataType: "json",
            data: {
                projectId: memberChangeObj.projectId,
                changeReason: $("#changeReason").val(),
                approvalModelDto: JSON.stringify(approvalModelDto)
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("提交数据失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    $(function () {
        //如果流程实例存在
        var processInsId = "${processInsId}";
        if (processInsId) {
            memberChangeObj.processInsId = processInsId;
        }

        //加载表格
        memberChangeObj.getMemberList();
    });
</script>
