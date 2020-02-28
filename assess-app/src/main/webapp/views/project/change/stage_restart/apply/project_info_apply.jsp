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
        <h2><i class="fa fa-info-circle"></i> 阶段重启
            <small>重启信息</small>
        </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_info_form" class="form-horizontal">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                当前阶段
                            </label>
                            <div class="col-sm-3">
                                <input type="hidden" id="projectName" name="projectName"
                                       value="${costsWorkStageRestart.projectName}">
                                <input type="hidden" id="projectThisWorkStage" name="projectThisWorkStage"
                                       value="${costsWorkStageRestart.projectThisWorkStage}">
                                <label class="form-control">${costsWorkStageRestart.projectThisWorkStage}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                重启阶段<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                <input type="hidden" id="projectPlanOldIdName" name="projectPlanOldIdName">
                                <select placeholder="重启阶段"
                                        class='form-control' required id="projectPlanOldId"
                                        name="projectPlanOldId" onclick="projectInfoApplyObj.projectPlanOldIdChange()">
                                    <option value="">请选择</option>
                                    <c:forEach var="item" items="${keyValueDtos}">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                重启原因<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                        <textarea required placeholder="重启原因" id="restartReason" name="restartReason"
                                                  class="form-control">${costsWorkStageRestart.restartReason}</textarea>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="id" name="id" value="${costsWorkStageRestart.id}">
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
        var data = formParams("project_info_form");
        data.projectId = "${projectId}";
//            console.log(data);
//            return false;
        var url = "${pageContext.request.contextPath}/costsWorkStageRestart/applyCommit";
        Loading.progressShow();
        $.ajax({
            url: url,
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //返回修改
    projectInfoApplyObj.editCommit = function () {
        var data = formParams("project_info_form");
        data.projectRestartStageId = "${costsWorkStageRestart.projectRestartStageId}";
        data.projectId = "${projectId}";

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        var url = "${pageContext.request.contextPath}/costsWorkStageRestart/editCommit";
        Loading.progressShow();
        $.ajax({
            url: url,
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    projectInfoApplyObj.projectPlanOldIdChange = function () {
        var name = $("#projectPlanOldId option:selected").text();
        $("#projectPlanOldIdName").val(name);
    };


    $(function () {
        projectInfoApplyObj.projectInfoForm.validate();
        if (parseInt(${costsWorkStageRestart.projectPlanOldId}) > 0) {
            $("#projectPlanOldId").val("${costsWorkStageRestart.projectPlanOldId}").trigger("change");
        }
        else {
            $("#projectPlanOldId");
        }

    });

</script>