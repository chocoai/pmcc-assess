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
        <h3>项目拿号
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">
            <div class="panel-body">
                <form id="project_takeNumber_form" class="form-horizontal">
                    <input type="hidden" name="id" value="${projectTakeNumber.id}">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                报告类型<span class="symbol required"></span>
                            </label>
                            <div class="col-md-3 col-sm-3 col-xs-12">
                                <select name="reportType" class="form-control search-select select2" required>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-md-11 col-sm-11 col-xs-12">
                                <textarea class="form-control" id="remark" name="remark" rows="4" required
                                          data-rule-maxlength="255" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>
</div>
<script type="application/javascript">
    var projectTakeNumber = {
       
    };

    //申请提交
    projectTakeNumber.commit = function () {
        if(!$("#project_takeNumber_form").valid()){
            return false;
        }
        var data = formParams("project_takeNumber_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTakeNumber/applyCommit",
            type: "post",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //返回修改
    projectTakeNumber.editCommit = function () {
        var data = formParams("project_takeNumber_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTakeNumber/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };


    $(function () {
        if("${projectTakeNumber}"){
            $("#remark").text("${projectTakeNumber.remark}")
        }
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '${projectTakeNumber.reportType}', function (html, data) {
            $("#project_takeNumber_form").find("select[name='reportType']").empty().html(html).trigger('change');
        });
    });

</script>