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
                                <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <label class="form-control">${csrProjectInfo.name}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">客户类型<span class="symbol required"></span></label>
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
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.entrustPurposeName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate value='${csrProjectInfo.valuationDate}' pattern='yyyy-MM-dd'/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目分配人<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${csrProjectInfo.distributionUserName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">取行序号<span class="symbol required"></span></label>
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
                    <h3> 无效数据规则</h3>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_invalid_rule_list">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">

    $(document).ready(function () {
        loadInvalidRuleList();
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


    //加载无效规则列表
    function loadInvalidRuleList() {
        var cols = [];
        cols.push({field: 'columnName', title: '列名称'});
        cols.push({field: 'columnValue', title: '过滤值'});
        $("#tb_invalid_rule_list").bootstrapTable('destroy');
        TableInit("tb_invalid_rule_list", "${pageContext.request.contextPath}/crsInvalidRule/getInvalidRuleList", cols, {
            csrProjectId: '${csrProjectInfo.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    function saveform() {
        if (!$("#frm_approval").valid()) {
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
