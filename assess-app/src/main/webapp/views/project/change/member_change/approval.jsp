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
                                                    <label class="form-control input-full" id="changeReason" name="changeReason">${costsProjectChangeLog.changeReason}</label>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-striped jambo_table bulk_action table-bordered" id="member_change_table"></table>
                            </div>
                        </div>
                    </div>


                    <!-- 公共尾部模块引用 -->
                    <!-- 审批用 -->
                    <%@include file="/views/share/form_approval.jsp" %>
                    <!-- 尾部end -->
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
        processInsId: "${processInsId}"==null?"0":"${processInsId}",
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
                    return value;
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
            }]
        ];


        TableClient(memberChangeObj.member_change_table, cols, data, {
            toolbar: "#change_tool",
            showNumber: false,
            search: false,
            showRefresh: false,
            pageSize: 10
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


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }


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
