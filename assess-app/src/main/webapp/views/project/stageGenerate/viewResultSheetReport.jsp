<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/common_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        结果表生成
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <input type="hidden" name="projectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    结果表
                                                </label>
                                                <div class="col-sm-9">
                                                    <div id="_resultSheetReportMoreAndMoreFile">

                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn-primary btn btn-sm"
                                                            onclick="objReport.resultSheetReport(this)">生成结果表
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12" style="text-align: center;">
                        <button class="btn btn-warning" type="button" onclick="window.close()">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>


<script type="text/javascript">
    var objReport = {};
    objReport.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (errorCallback) {
                        errorCallback(result.errmsg);
                    } else {
                        if (result.errmsg) {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                        } else {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                        }
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (errorCallback) {
                    errorCallback(result.errmsg);
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            }
        });
    };
    objReport.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                objReport.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            objReport.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    objReport.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        objReport.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    objReport.fileShow = function (fieldsName, tableId) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ProjectInfo,
                tableId: tableId
            },
            editFlag: true,
            deleteFlag: false
        })
    };

    objReport.fileId = "resultSheetReportMoreAndMoreFile";

    objReport.resultSheetReport = function (_this) {
        var projectId = '${projectInfo.id}';
        objReport.ajaxServerMethod({
            projectId: projectId,
            fieldsName: objReport.fileId,
            tableName: AssessDBKey.ProjectInfo
        }, "/generateReport/resultSheetReportNew", "post", function () {
            objReport.initForm();
        }, function (message) {
            notifyInfo('提示', "无法生成结果集" + message);
        });
    };

    objReport.initForm = function () {
        objReport.fileShow(objReport.fileId, '${projectInfo.id}');
    };

    $(document).ready(function () {
        objReport.initForm();
    });
</script>


</html>

