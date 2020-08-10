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
                    项目重启变更
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>

        <div class="card-body">
            <form id="project_restart_form" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                重启时间
                            </label>
                            <div class="col-sm-3">
                                <input placeholder="重启时间" id="restartTime" name="restartTime"
                                       data-date-format="yyyy-mm-dd" class="form-control input-full date-picker dbdate"
                                       readonly="readonly">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                重启原因
                            </label>
                            <div class="col-sm-11">
                                        <textarea class="form-control input-full" id="changeReason" name="changeReason" rows="4"
                                                  required
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
    var projectRestartApplyObj = {
        projectRestartForm: $('#project_restart_form'),
    };

    //申请提交
    projectRestartApplyObj.commit = function () {
        var projectInfo = JSON.parse('${el:toJsonString(projectInfo)}');
        var oldRecord = projectRestartApplyObj.formatDate(projectInfo);

        var data = formParams("project_restart_form");
        var json = {};
        json = formParams("project_restart_form");
        json.projectId = "${projectInfo.id}";
        json.oldRecord = oldRecord;
        json.newRecord = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectRestart/applyCommit",
            type: "post",
            data: json,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                } else {
                    AlertError("失败","提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","提交数据失败，失败原因:" + result.errmsg);
            }
        });
    };

    //返回修改
    projectRestartApplyObj.editCommit = function () {
        var data = formParams("project_restart_form");
        var projectInfo = JSON.parse('${el:toJsonString(projectInfo)}');
        var json = {};
        json = formParams("project_restart_form");
        json.newRecord = JSON.stringify(data);

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(json);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectRestart/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功",function(){
                        window.close();
                    });
                } else {
                    AlertError("失败","提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });

    };


    $(function () {

        //var json = '${el:toJsonString(projectInfo)}';
        var newRecord = '${costsProjectChangeLog.newRecord}';

        if (newRecord != "") {
            //变更的信息
            var projectInfo = JSON.parse(newRecord);
            console.log(projectInfo);
            projectRestartApplyObj.projectRestartForm.initForm(projectInfo);
            //更新id为新变更信息的id
            projectRestartApplyObj.projectRestartForm.find('[name="id"]').val('${costsProjectChangeLog.id}');
        }

    });

    //format oldRecord的date类型数据
    projectRestartApplyObj.formatDate = function (projectInfo) {
        projectInfo.serviceStart = projectRestartApplyObj.formatDates(projectInfo.serviceStart);
        projectInfo.serviceEnd = projectRestartApplyObj.formatDates(projectInfo.serviceEnd);
        projectInfo.created = projectRestartApplyObj.formatDateTime(projectInfo.created);
        projectInfo.modified = projectRestartApplyObj.formatDateTime(projectInfo.modified);
        var json = JSON.stringify(projectInfo);
        return json;
    }

    //日期
    projectRestartApplyObj.formatDates = function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    //时间
    projectRestartApplyObj.formatDateTime = function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    }

    //判断重启时间与服务结束时间
    function checkEndTime(projectInfo) {
        var serviceEnd = projectRestartApplyObj.formatDates(projectInfo.serviceEnd);
        var end = new Date(serviceEnd.replace("-", "/").replace("-", "/"));
        var restartTime = $("#restartTime").val();
        var restart = new Date(restartTime.replace("-", "/").replace("-", "/"));
        if (restart < end) {
            return true;
        }
        return false;
    }

</script>