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
                                标题
                            </label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectSubsequent.title}</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                内容
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${projectSubsequent.content}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                处理意见<
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${projectSubsequent.suggestion}</label>
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
    $(function () {
        //附件显示
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectSubsequent,
                tableId: '${projectSubsequent.id}'
            },
            editFlag: false,
            deleteFlag: false
        })

    });
</script>
