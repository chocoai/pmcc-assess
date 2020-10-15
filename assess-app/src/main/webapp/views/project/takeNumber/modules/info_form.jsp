<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    项目拿号
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_takeNumber_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectTakeNumber.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline">
                            <label class="col-sm-1 col-form-label">
                                报告类型<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <select name="reportType" onchange="projectTakeNumber.reportTypeChange(this);"
                                        class="form-control input-full" required>
                                </select>
                            </div>
                            <c:if test="${companyName eq 'xiehe'}">
                                <div class="col-sm-3">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name="bisQrcode"
                                                ${true eq projectTakeNumber.bisQrcode?'checked="checked"':''}
                                                   onclick="projectTakeNumber.triggerQrcode(this)" value="true">
                                            <span class="form-check-sign">生成二维码</span>
                                        </label>
                                    </div>
                                </div>
                                <label class="col-sm-1 col-form-label report-group" style="display: none;">
                                    报告分组<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3 report-group"
                                     style="display: ${true eq projectTakeNumber.bisQrcode?'':'none'}">
                                    <input type="hidden" name="areaGroupId" value="${projectTakeNumber.areaGroupId}">
                                    <input type="hidden" name="reportGroupId"
                                           value="${projectTakeNumber.reportGroupId}">
                                    <input type="hidden" name="reportGroupName"
                                           value="${projectTakeNumber.reportGroupName}">
                                    <button type="button" class="btn btn-sm btn-primary"
                                            onclick="projectTakeNumber.showReportGroupModal()">选择分组
                                    </button>
                                    <span data-name="reportGroupName">${projectTakeNumber.reportGroupName}</span>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                文号规则<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <input type="hidden" name="erpNumberRule" value="${projectTakeNumber.erpNumberRule}">
                                <select name="erpRuleId" onchange="projectTakeNumber.erpRuleChange(this);"
                                        class="form-control input-full" required></select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                    <textarea class="form-control input-full" id="remark" name="remark" rows="4"
                                              required
                                              data-rule-maxlength="255"
                                              placeholder="">${projectTakeNumber.remark}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%--选择报告组--%>
<div id="reportGroupModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><h4>选择报告组</h4></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tbReportGroupList"></table>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    var projectTakeNumber = {};

    //申请提交
    projectTakeNumber.commit = function () {
        if (!$("#project_takeNumber_form").valid()) {
            return false;
        }
        var data = formParams("project_takeNumber_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTakeNumber/applyCommit",
            type: "post",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //返回修改
    projectTakeNumber.editCommit = function () {
        var data = formParams("project_takeNumber_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTakeNumber/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //撤销
    projectTakeNumber.closeProcess = function () {
        AlertConfirm("是否确认撤销", "流程撤销后将不可恢复", function () {
            var approvalModelDto = formSerializeArray($("#process_variable_form"));
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/public/closeProcess",
                type: "post",
                data: approvalModelDto,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "流程撤销成功", function () {
                            window.close();
                        });
                    } else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        })
    };

    //生成二维码切换
    projectTakeNumber.triggerQrcode = function (_this) {
        if ($(_this).prop('checked')) {
            $('#project_takeNumber_form').find('.report-group').show();
        } else {
            $('#project_takeNumber_form').find('.report-group').hide();
        }
    }

    //显示选择报告组弹窗
    projectTakeNumber.showReportGroupModal = function () {
        projectTakeNumber.loadReportGroupList();
        $('#reportGroupModal').modal();
    }

    //加载报告组列表
    projectTakeNumber.loadReportGroupList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="projectTakeNumber.selectReportGroup(' + row.areaGroupId + ',' + row.id + ',\'' + row.name + '\')" style="margin-left: 5px;" class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="选择">';
                str += '<i class="fa fa-check"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbReportGroupList").bootstrapTable('destroy');
        TableInit($('#tbReportGroupList'), "${pageContext.request.contextPath}/generateReportGroup/getBootstrapTableVo", cols, {
            projectId: "${projectInfo.id}"
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //选择报告组
    projectTakeNumber.selectReportGroup = function (areaGroupId, reportGroupId, name) {
        var form = $("#project_takeNumber_form");
        form.find('[name=areaGroupId]').val(areaGroupId);
        form.find('[name=reportGroupId]').val(reportGroupId);
        form.find('[name=reportGroupName]').val(name);
        form.find('[data-name=reportGroupName]').text(name);
        $('#reportGroupModal').modal('hide');
    }

    //报告类型change
    projectTakeNumber.reportTypeChange = function (_this, callback) {
        var reportType = $(_this).val();
        var erpRuleElement = $("#project_takeNumber_form").find("select[name='erpRuleId']");
        erpRuleElement.empty();
        $.ajax({
            url: '${pageContext.request.contextPath}/numberRule/getDataNumberRuleList',
            data: {
                projectType: '${projectType}',
                reportType: reportType
            },
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.ret && result.data) {
                    var html = '';
                    $.each(result.data, function (i, item) {
                        if (i == 0) {
                            html += '<option selected="selected" value="' + item.erpRuleId + '">' + item.erpNumberRule + '</option>';
                        } else {
                            html += '<option value="' + item.erpRuleId + '">' + item.erpNumberRule + '</option>';
                        }
                    })
                    erpRuleElement.append(html).trigger('change');
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //文号规则change
    projectTakeNumber.erpRuleChange = function (_this) {
        $(_this).closest('form').find("input[name='erpNumberRule']").val($(_this).find('option:selected').text());
    }

    $(function () {
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '${projectTakeNumber.reportType}', function (html, data) {
            $("#project_takeNumber_form").find("select[name='reportType']").empty().html(html).trigger('change');

            if('${projectTakeNumber.erpRuleId}'.length>0){
                $("#project_takeNumber_form").find("select[name='erpRuleId']").val('${projectTakeNumber.erpRuleId}').trigger('change');
            }
        });
    });
</script>