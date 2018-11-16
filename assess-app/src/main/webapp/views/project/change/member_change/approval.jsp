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
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>

            <div style="display: none">
                <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            </div>

            <!-- 公共模块end -->

            <div class="x_panel">
                <div class="x_title">
                    <h2 id="show-title"><i class="fa fa-edit"></i>
                        项目成员变更<small></small>
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="panel-body">

                        <table class="table table-striped jambo_table bulk_action table-bordered" id="member_change_table"></table>

                        <hr/>
                        <form class="form-horizontal" id="member_change_form">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                        变更原因
                                    </label>
                                    <div class="col-md-11 col-sm-11 col-xs-12">
                                        <label class="form-control" id="changeReason" name="changeReason">${costsProjectChangeLog.changeReason}</label>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>

                </div>

            </div>


            <!-- 公共尾部模块引用 -->
            <!-- 审批用 -->
            <%@include file="/views/share/form_approval.jsp" %>

            <%@include file="/views/share/form_log.jsp" %>
            <!-- 尾部end -->

        </div>

    </div>

</div>


<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>

<script type="text/javascript">
    var memberChangeObj = {
        projectId: "${projectInfo.id}",
        processInsId: "0",
        member_change_table: $("#member_change_table"),
        member_change_form: $("#member_change_form")
    };


    memberChangeObj.getMemberList = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/memberList",
            data: {
                projectId: memberChangeObj.projectId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    memberChangeObj.renderMemberChangeTable(result.data);
                } else {
                    Alert("获取项目成员列表失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
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



    //审批提交
    function approvalCommit(approvalModelDto) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/member.change/approvalCommit",
            type: "post",
            dataType: "json",
            data: approvalModelDto,
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
