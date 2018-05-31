<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
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
                    <form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="项目名称" id="projectName" name="projectName"
                                           value="${projectInfo.projectName}" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="entrustPurpose" name="entrustPurpose" class="form-control"
                                            required="required">
                                        <option value="">请选择</option>
                                        <c:forEach items="${list_entrustment_purpose}" var="item">
                                            <c:choose>
                                                <c:when test="${item.id == projectInfo.entrustPurpose}">
                                                    <option value="${item.id}" selected="selected">${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="评估基准日" id="completeDateStart"
                                           name="completeDateStart" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${projectInfo.completeDateStart}' pattern='yyyy-MM-dd'/>">

                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="hidden" id="userAccountManagerID" name="userAccountManager">
                                        <input type="text" class="form-control" readonly="readonly"
                                               value="${projectInfo.userAccountManagerName}" required="required"
                                               id="userAccountManager" maxlength="200">
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
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明</label>
                                <div class="col-sm-11">
                                    <textarea id="remarks" name="remarks"
                                              class="form-control" placeholder="项目说明">${projectInfo.remarks}</textarea>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="x_title">
                    <h3> 无效数据规则</h3>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="x_content">
                    <button class="btn btn-success" data-toggle="modal" onclick="addContacts(3)">新增</button>
                    <table class="table table-bordered" id="tb_invalid_rule_list">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="projectApply();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="invalid_rule_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">规则</h4>
            </div>
            <form id="frmInvalidRule" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="columnName" id="columnName" placeholder="字段名称"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            过滤值
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="columnValue" id="columnValue" placeholder="过滤值"
                                                   class="form-control" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="saveContacts()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script type="text/javascript">
    //选项框
    $(document).ready(function () {

    });

    //添加无效规则
    function addInvalidRule() {

        $("#frmInvalidRule").modal();
    }

    //删除无效规则
    function delInvalidRule() {

    }

    //加载无效规则列表
    function loadInvalidRuleList() {

    }

    // 项目分配人
    //userAccountManager
    function selectDistributionUser() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#userAccountManager").val(data.name);
                $("#userAccountManagerID").val(data.account);
            }
        });
    }

   


    //修改专用
    function loadInitContactsList(id, tb_List, flag) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        TableInit("" + tb_List, "${pageContext.request.contextPath}/projectInfo/getProjectContactsVosX", cols, {
            pid: id, flag: flag
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }


</script>

