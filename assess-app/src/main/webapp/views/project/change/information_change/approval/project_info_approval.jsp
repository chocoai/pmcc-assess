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
                                <input class="form-control" id="projectName" name="projectName" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                委托目的<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input class="form-control" id="delegatePurposeName" name="delegatePurposeName"
                                       readonly>
                            </div>
                        </div>

                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务开始日期<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input class="form-control" id="serviceStart" name="serviceStart" readonly>
                            </div>
                        </div>

                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务结束日期<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <input class="form-control" id="serviceEnd" name="serviceEnd" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                服务事项
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <input class="form-control" id="serviceContent" name="serviceContent" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                项目信息变更原因
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <input class="form-control" id="changeReason" name="changeReason" readonly>
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
        projectInfoForm: $('#project_info_form')
    };

    $(function () {
        var json = '${costsProjectChangeLog.newRecord}';
        var projectInfo = JSON.parse(json);
        projectInfoApplyObj.projectInfoForm.initForm(projectInfo);
    });
</script>
