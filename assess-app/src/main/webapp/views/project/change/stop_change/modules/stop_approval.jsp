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
                    项目终止变更
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
                <form id="project_stop_form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    关闭时间
                                </label>
                                <div class="col-sm-3">
                                    <label name="stopTime" class="form-control input-full"></label>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    关闭原因
                                </label>
                                <div class="col-sm-11">
                                    <label name="changeReason" class="form-control input-full"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    可能影响
                                </label>
                                <div class="col-sm-11">
                                    <label name="influence" class="form-control input-full"></label>
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
    var projectStopApplyObj = {
        projectStopForm: $('#project_stop_form')
    };

    $(function () {
        var json = '${costsProjectChangeLog.newRecord}';
        console.log(json);
        var projectInfo = JSON.parse(json);
        projectStopApplyObj.projectStopForm.initForm(projectInfo);
        //附件显示
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.ProjectChangeLog,
                tableId: '${costsProjectChangeLog.id}'
            },
            editFlag: true,
            deleteFlag: false
        })

    });
</script>
