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
        <h3>项目后续事项
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_subsequent_form" class="form-horizontal">
                    <input type="hidden" name="id" value="${projectSubsequent.id}">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                标题<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input type="text" placeholder="" name="title" class="form-control"
                                       value="${projectSubsequent.title}" required>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                内容<span class="symbol required"></span>
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" name="content" id="content" rows="4"  required
                                          data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                处理意见<span class="symbol required"></span>
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="suggestion" name="suggestion" rows="4" required
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
    var projectSubsequent = {
       
    };

    //申请提交
    projectSubsequent.commit = function () {
        if(!$("#project_subsequent_form").valid()){
            return false;
        }
        var data = formParams("project_subsequent_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectSubsequent/applyCommit",
            type: "post",
            data: data,
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
        if("${projectSubsequent}"){
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