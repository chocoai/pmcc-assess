<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    立项信息
                    <small>变更信息</small>
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_info_form" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                项目名称
                            </label>
                            <div class="col-sm-10">
                                <input class="form-control input-full" id="projectName" name="projectName" readonly
                                       value="${oldProjectInfo.projectName}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                项目信息变更原因
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${costsProjectChangeLog.changeReason}</label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                项目信息变更内容
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${changeContent}</label>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="application/javascript">

</script>
