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
        <h3>项目后续事项
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_subsequent_form" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <div>
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                标题<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <label class="form-control">${projectSubsequent.title}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                内容
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <label class="form-control">${projectSubsequent.content}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                处理意见
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <label class="form-control">${projectSubsequent.suggestion}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                附件
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <div id="_uploadFile"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

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
            editFlag: true,
            deleteFlag: false
        })

    });
</script>
