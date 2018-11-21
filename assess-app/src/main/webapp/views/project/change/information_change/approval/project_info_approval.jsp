
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
                                <input class="form-control" id="projectName" name="projectName" readonly value="${projectInfo.projectName}">
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                项目信息变更原因
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <input class="form-control" id="changeReason" name="changeReason" readonly value="${costsProjectChangeLog.changeReason}">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<script type="application/javascript">

</script>
