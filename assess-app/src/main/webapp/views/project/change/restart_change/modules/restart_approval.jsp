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
                                <label name="restartTime" class="form-control input-full"></label>
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
                                <label name="changeReason" class="form-control input-full"></label>
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
        projectRestartForm: $('#project_restart_form')
    };

    $(function () {
        var json = '${costsProjectChangeLog.newRecord}';
        var projectInfo = JSON.parse(json);
        projectRestartApplyObj.projectRestartForm.initForm(projectInfo);
    });
</script>
