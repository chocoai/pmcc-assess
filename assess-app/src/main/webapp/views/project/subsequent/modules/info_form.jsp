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
                    项目后续事项
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_subsequent_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectSubsequent.id}">
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">
                                标题<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" placeholder="" name="title" class="form-control input-full"
                                       value="${projectSubsequent.title}" required>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                内容<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                       <textarea class="form-control input-full" name="content" id="content" rows="4" required
                                                 data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                处理意见<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                       <textarea class="form-control input-full" id="suggestion" name="suggestion" rows="4"
                                                 required
                                                 data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                附件
                            </label>
                            <div class="col-sm-11">
                                <input id="uploadFile" type="file" multiple="false">
                                <div id="_uploadFile"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="application/javascript">
    var projectSubsequent = {};

    //申请提交
    projectSubsequent.commit = function () {
        if (!$("#project_subsequent_form").valid()) {
            return false;
        }
        var data = formParams("project_subsequent_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectSubsequent/applyCommit",
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
    projectSubsequent.editCommit = function () {
        var data = formParams("project_subsequent_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectSubsequent/editCommit",
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


    $(function () {
        if ("${projectSubsequent}") {
            $("#content").text("${projectSubsequent.content}")
            $("#suggestion").text("${projectSubsequent.suggestion}")
        }

        //附件
        FileUtils.uploadFiles({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectSubsequent,
                tableId: '${empty projectSubsequent.id?0:projectSubsequent.id}'
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectSubsequent,
                tableId: '${empty projectSubsequent.id?0:projectSubsequent.id}'
            },
            editFlag: true,
            deleteFlag: true
        })

    });

</script>