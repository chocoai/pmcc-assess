<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title">
        <h2>测算</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_task" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    实际工时
                </label>
                <div class="x-valid">
                    <div class="col-sm-3">
                        <input type="text" required
                               placeholder="实际工时" data-rule-number='true'
                               id="actualHours" name="actualHours" class="form-control" maxlength="3">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    成果描述
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" id="taskRemarks" name="taskRemarks"
                                                  class="form-control"></textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    成果文件
                </label>
                <div class="col-sm-11">
                    <input id="apply_file" name="apply_file" type="file" multiple="false">
                    <div id="_apply_file">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>