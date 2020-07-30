<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    变更信息
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
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">
                                    项目信息变更原因
                                </label>
                                <div class="col-sm-11">
                                    <textarea class="form-control input-full" id="changeReason" name="changeReason" rows="4" required data-rule-maxlength="255" placeholder="项目信息变更原因">${costsProjectChangeLog.changeReason}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
    </div>
    </div>
</div>