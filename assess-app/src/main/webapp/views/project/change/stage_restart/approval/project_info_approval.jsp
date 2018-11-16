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
        <h2>重启信息</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        当前阶段
                    </label>
                    <div class="col-sm-3">
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
                        <label class="form-control">${costsWorkStageRestart.projectRestartStageName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        重启原因<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-11">
                        <label class="form-control">${costsWorkStageRestart.restartReason}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

