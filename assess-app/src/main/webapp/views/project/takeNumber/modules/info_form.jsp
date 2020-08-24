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
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                报告类型<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <select name="reportType" class="form-control input-full search-select select2" required>
                                </select>
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
                                    <textarea class="form-control input-full" id="remark" name="remark" rows="4" required
                                              data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

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
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
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
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                }
                else {
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
                        AlertSuccess("成功", "流程撤销成功",function(){
                            window.close();
                        });
                    }
                    else {
                        AlertError("提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        })
    };


    $(function () {
        if ("${projectTakeNumber}") {
            $("#remark").text("${projectTakeNumber.remark}")
        }
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '${projectTakeNumber.reportType}', function (html, data) {
            $("#project_takeNumber_form").find("select[name='reportType']").empty().html(html).trigger('change');
        });
    });

</script>