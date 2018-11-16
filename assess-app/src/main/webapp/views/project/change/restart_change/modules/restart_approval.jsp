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
        <h2><i class="fa fa-info-circle"></i>项目重启变更
        </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_restart_form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                重启时间<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input id="restartTime" name="restartTime" class="form-control" readonly="readonly">
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                重启原因
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="changeReason" name="changeReason" rows="4" required data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                可能影响
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="influence" name="influence" rows="4" required data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

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
        $("#project_restart_form textarea").attr("readonly",true);
    });
</script>
