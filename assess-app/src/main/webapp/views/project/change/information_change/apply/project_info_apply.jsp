<%--
  Created by IntelliJ IDEA.
  User: zly
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
        <h2><i class="fa fa-info-circle"></i> 立项信息
            <small>变更信息</small>
        </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_info_form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                项目名称<span class="symbol required"></span>
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <input class="form-control" id="projectName" name="projectName" required data-rule-maxlength="255" placeholder="">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                委托目的<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input type="hidden" id="delegatePurposeName" name="delegatePurposeName" class="form-control">
                                <select id="delegatePurpose" name="delegatePurpose" class="form-control" onchange="projectInfoApplyObj.delegatePurposeChange();">
                                    <option value="">-选择-</option>
                                    <c:forEach items="${delegatePurpose}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务开始日期<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input placeholder="开始日期" id="serviceStart" name="serviceStart"
                                       data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                       readonly="readonly" required>
                            </div>
                        </div>

                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务结束日期<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input placeholder="结束日期" id="serviceEnd" name="serviceEnd"
                                       data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                       readonly="readonly" required>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务事项
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="serviceContent" name="serviceContent" rows="4" required data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                项目信息变更原因
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="changeReason" name="changeReason" rows="4" required data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>
</div>
<script type="application/javascript">
    var projectInfoApplyObj = {
        projectInfoForm: $('#project_info_form'),
    };

    //申请提交
    projectInfoApplyObj.commit = function () {
        var projectInfo = JSON.parse('${el:toJsonString(projectInfo)}');
        var oldRecord = projectInfoApplyObj.formatDate(projectInfo);

        var data = formParams("project_info_form");
        var json = {};
        json = formParams("project_info_form");
        json.newRecord = JSON.stringify(data);
        json.projectId = "${projectInfo.id}";
        json.oldRecord = oldRecord;
//        console.log(json);
//        return false;
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/formsProjectInformationChange/applyCommit",
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
    projectInfoApplyObj.editCommit = function () {
        var data = formParams("project_info_form");
        var json = {};
        json = formParams("project_info_form");
        json.newRecord = JSON.stringify(data);

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(json);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/formsProjectInformationChange/editCommit",
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

    projectInfoApplyObj.delegatePurposeChange = function () {
        var name = $("#delegatePurpose option:selected").text();
        $("#delegatePurposeName").val(name);
    };



    $(function () {

        var json = '${el:toJsonString(projectInfo)}';
        var newRecord = '${costsProjectChangeLog.newRecord}';

        if(newRecord != ""){
            //返回修改初始化
            //变更的信息
            var projectInfo = JSON.parse(newRecord);
            projectInfoApplyObj.projectInfoForm.initForm(projectInfo);
            //更新id为新变更信息的id
            projectInfoApplyObj.projectInfoForm.find('[name="id"]').val('${costsProjectChangeLog.id}');
        }else{
            //申请页初始化
            if (json != "") {
                //原始信息
                var projectInfo = JSON.parse(json);
                projectInfo.serviceStart = projectInfoApplyObj.formatDates(projectInfo.serviceStart);
                projectInfo.serviceEnd = projectInfoApplyObj.formatDates(projectInfo.serviceEnd);

                projectInfoApplyObj.projectInfoForm.initForm(projectInfo);

            }
        }
    });

    //format oldRecord的date类型数据
    projectInfoApplyObj.formatDate = function(projectInfo) {
        projectInfo.serviceStart = projectInfoApplyObj.formatDates(projectInfo.serviceStart);
        projectInfo.serviceEnd = projectInfoApplyObj.formatDates(projectInfo.serviceEnd);
        projectInfo.created = projectInfoApplyObj.formatDateTime(projectInfo.created);
        projectInfo.modified = projectInfoApplyObj.formatDateTime(projectInfo.modified);
        var json = JSON.stringify(projectInfo);
        return json;
    }

    //日期
    projectInfoApplyObj.formatDates = function(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    //时间
    projectInfoApplyObj.formatDateTime = function(inputTime) {
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