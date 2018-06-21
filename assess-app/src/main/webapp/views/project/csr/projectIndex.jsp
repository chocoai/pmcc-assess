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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>
                        项目信息
                        <small>${csrProjectInfo.projectClassName}/${csrProjectInfo.projectTypeName}/${csrProjectInfo.projectCategoryName}</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_csr_project_info" class="form-horizontal" enctype="multipart/form-data">
                        <input type="hidden" id="id" name="id" value="${csrProjectInfo.id}">
                        <input type="hidden" name="projectClassId" value="${csrProjectInfo.projectClassId}">
                        <input type="hidden" name="projectTypeId" value="${csrProjectInfo.projectTypeId}">
                        <input type="hidden" name="projectCategoryId" value="${csrProjectInfo.projectCategoryId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="项目名称" id="name" name="name"
                                           value="${csrProjectInfo.name}" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">客户类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <span class="radio-inline">
                                    <input type="radio" id="customerType_1" required name="customerType"
                                           value="1" ${csrProjectInfo.customerType == 1?"checked=\"checked\"":""} >
                                        <label for="customerType_1">法人</label></span>
                                    <span class="radio-inline">
                                    <input type="radio" id="customerType_0" name="customerType"
                                           value="0" ${csrProjectInfo.customerType == 0?"checked=\"checked\"":""}><label
                                            for="customerType_0">自然人</label></span>

                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托单位
                                </label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="hidden" name="entrustmentUnitId" id="entrustmentUnitId"
                                               value="${csrProjectInfo.entrustmentUnitId}">
                                        <input type="text" id="entrustmentUnitName" name="entrustmentUnitName"
                                               value="${csrProjectInfo.entrustmentUnitName}"
                                               placeholder="委托单位" onclick="selectEntrustmentUnit();"
                                               class="form-control" required="required" readonly="readonly">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectEntrustmentUnit();">
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


                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="entrustPurpose" name="entrustPurpose" class="form-control"
                                            required="required">
                                        <option value="">请选择</option>
                                        <c:forEach items="${list_entrustment_purpose}" var="item">
                                            <c:choose>
                                                <c:when test="${item.id == csrProjectInfo.entrustPurpose}">
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
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="评估基准日" id="valuationDate"
                                           name="valuationDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${csrProjectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">

                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目分配人<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="hidden" id="distributionUser" name="distributionUser"
                                               value="${csrProjectInfo.distributionUser}">
                                        <input type="text" class="form-control" readonly="readonly"
                                               onclick="selectDistributionUser();"
                                               value="${csrProjectInfo.distributionUserName}" required="required"
                                               id="distributionUserName" maxlength="200">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectDistributionUser()">
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
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">取行序号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="取行序号" id="startRowNumber"
                                           name="startRowNumber" class="form-control"
                                           value="${csrProjectInfo.startRowNumber}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明</label>
                                <div class="col-sm-11">
                                    <textarea id="remark" name="remark"
                                              class="form-control"
                                              placeholder="项目说明">${csrProjectInfo.remark}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">客户附件</label>
                                <div class="col-sm-11">
                                    <input id="upload_file" name="upload_file" placeholder="上传附件" class="form-control"
                                           type="file">
                                    <div id="_upload_file"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3> 无效数据规则</h3>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="x_content">
                    <button class="btn btn-success" data-toggle="modal" onclick="addInvalidRule()">新增</button>
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
                            <button id="commit_btn" class="btn btn-success" onclick="saveCsrProjectInfo();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
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
                <h3 class="modal-title">无效数据规则</h3>
            </div>
            <form id="frmInvalidRule" class="form-horizontal">
                <input type="hidden" name="csrProjectId" value="${csrProjectInfo.id}">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="columnName" id="columnName" placeholder="字段名称"
                                                   class="form-control" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            过滤值<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="columnValue" id="columnValue" placeholder="过滤值"
                                                   class="form-control" required>
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
                    <button type="button" class="btn btn-primary" onclick="saveInvalidRule()">
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

    $(document).ready(function () {
        loadInvalidRuleList();
        loadAttachmentList();
        loadProjectCategoryList('${csrProjectInfo.projectTypeId}', '${csrProjectInfo.projectCategoryId}');
        FileUtils.uploadFiles({
            showFileList: false,
            target: "upload_file",
            disabledTarget: "commit_btn",
            formData: {
                tableName: "tb_csr_project_info",
                tableId: ${empty csrProjectInfo?0:csrProjectInfo.id}
            },
            deleteFlag: true
        }, {
            onUploadComplete: function () {
                loadAttachmentList();
            }
        });

    });

    //加载项目类别
    function loadProjectCategoryList(projectTypeId, projectCategoryId) {
        $("#projectCategoryId").empty();
        if (projectTypeId) {
            AssessCommon.getProjectCategoryList(projectTypeId, function (html, data) {
                $("#projectCategoryId").append(html);
                if (projectCategoryId) {
                    $("#projectCategoryId").val(projectCategoryId);
                }
            })
        }
    }

    //加载附件列表信息
    function loadAttachmentList() {
        FileUtils.getFileShows({
            target: "upload_file",
            formData: {
                tableName: "tb_csr_project_info",
                tableId: ${empty csrProjectInfo?0:csrProjectInfo.id}
            },
            deleteFlag: true
        })
    }

    //添加无效规则
    function addInvalidRule() {
        $("#frmInvalidRule").clearAll();
        $("#invalid_rule_modal").modal();
    }

    //删除无效规则
    function delInvalidRule(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/crsInvalidRule/deleteInvalidRule",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadInvalidRuleList();
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
        })
    }

    //保存无效规则
    function saveInvalidRule() {
        if ($("#frmInvalidRule").valid()) {
            var data = formParams("frmInvalidRule");
            data.csrProjectId = '${csrProjectInfo.id}';
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/crsInvalidRule/saveInvalidRule',
                data: data,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadInvalidRuleList();
                        $('#invalid_rule_modal').modal('hide');
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

    }

    //加载无效规则列表
    function loadInvalidRuleList() {
        var cols = [];
        cols.push({field: 'columnName', title: '列名称'});
        cols.push({field: 'columnValue', title: '过滤值'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delInvalidRule(' + row.id + ');">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_invalid_rule_list").bootstrapTable('destroy');
        TableInit("tb_invalid_rule_list", "${pageContext.request.contextPath}/crsInvalidRule/getInvalidRuleList", cols, {
            csrProjectId: '${csrProjectInfo.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //选择委托单位
    function selectEntrustmentUnit() {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                $("#entrustmentUnitId").val(nodes[0].id);
                $("#entrustmentUnitName").val(nodes[0].name);
            }
        });
    }

    // 项目分配人
    //userAccountManager
    function selectDistributionUser() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#distributionUserName").val(data.name);
                $("#distributionUser").val(data.account);
            }
        });
    }

    //保存项目
    function saveCsrProjectInfo() {
        if ($("#frm_csr_project_info").valid()) {
            if ($("#_upload_file").html().length <= 0) {
                Alert("请上传客户信息附件！");
                return;
            }

            var data = {};
            data.formData = JSON.stringify(formParams("frm_csr_project_info"));
            var url = "";
            if ("${empty processInsId?"0":processInsId}" != "0") {
                url = "${pageContext.request.contextPath}/csrProjectInfo/projectEditSubmit";
                var approvalData = formParams("frm_approval");
                data = $.extend({}, approvalData, data);
            } else {
                url = "${pageContext.request.contextPath}/csrProjectInfo/projectApplySubmit";
            }
            Loading.progressShow();
            $.ajax({
                url: url,
                data: data,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交成功!", 1, null, function () {
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
    }

</script>

