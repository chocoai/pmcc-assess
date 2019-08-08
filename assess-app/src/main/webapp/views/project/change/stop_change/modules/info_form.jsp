<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>项目终止变更
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_stop_form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                关闭时间
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input placeholder="选择时间" id="stopTime" name="stopTime"
                                       data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                       readonly="readonly">
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                关闭原因
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="changeReason" name="changeReason" rows="4" required
                                          data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                可能影响
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="influence" name="influence" rows="4" required
                                          data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                附件
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input id="uploadFile" type="file" multiple="false">
                                <div id="_uploadFile"></div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>
</div>
<script type="application/javascript">
    var projectStopApplyObj = {
        projectStopForm: $('#project_stop_form'),
    };

    //申请提交
    projectStopApplyObj.commit = function () {
        var projectInfo = JSON.parse('${el:toJsonString(projectInfo)}');
        var oldRecord = projectStopApplyObj.formatDate(projectInfo);

        var data = formParams("project_stop_form");
        var json = {};
        json = formParams("project_stop_form");
        json.projectId = "${projectInfo.id}";
        json.oldRecord = oldRecord;
        json.newRecord = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectStop/applyCommit",
            type: "post",
            data: json,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //返回修改
    projectStopApplyObj.editCommit = function () {
        var data = formParams("project_stop_form");
        var json = {};
        json = formParams("project_stop_form");
        json.newRecord = JSON.stringify(data);

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(json);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectStop/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
            projectStopApplyObj.projectStopForm.initForm(projectInfo);
            //更新id为新变更信息的id
            projectStopApplyObj.projectStopForm.find('[name="id"]').val('${costsProjectChangeLog.id}');
        }

        //附件
        FileUtils.uploadFiles({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectChangeLog,
                tableId: '${empty costsProjectChangeLog.id?0:costsProjectChangeLog.id}'
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectChangeLog,
                tableId: '${empty costsProjectChangeLog.id?0:costsProjectChangeLog.id}'
            },
            editFlag: true,
            deleteFlag: true
        })


    });

    //format oldRecord的date类型数据
    projectStopApplyObj.formatDate = function (projectInfo) {
        projectInfo.serviceStart = projectStopApplyObj.formatDates(projectInfo.serviceStart);
        projectInfo.serviceEnd = projectStopApplyObj.formatDates(projectInfo.serviceEnd);
        projectInfo.created = projectStopApplyObj.formatDateTime(projectInfo.created);
        projectInfo.modified = projectStopApplyObj.formatDateTime(projectInfo.modified);
        var json = JSON.stringify(projectInfo);
        return json;
    }

    //日期
    projectStopApplyObj.formatDates = function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    //时间
    projectStopApplyObj.formatDateTime = function (inputTime) {
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

</script>