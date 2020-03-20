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
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        报告信息
                                    </div>
                                    <div class="card-tools">
                                        <button type="button" class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form class="form-horizontal" id="frmXlxReportIndividual">
                                    <input type="hidden" name="id" value="${projectXlxReportIndividual.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectCategory"
                                           value="${projectInfo.projectCategoryId}">
                                    <input type="hidden" name="assessType" value="${assessProjectType}">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1  control-label">
                                                    项目名称
                                                </label>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control input-full"
                                                           readonly="readonly"
                                                           name="projectName" value="${projectInfo.projectName}">
                                                </div>

                                                <label class="col-md-1  control-label">
                                                    文号
                                                </label>
                                                <div class="col-md-3">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-sm"
                                                               name="numberValue"
                                                               value="${projectXlxReportIndividual.numberValue}">
                                                        <div class="input-group-prepend ">
                                                            <button class="btn btn-info btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="xlxReportIndividual.getReportNumber(this);">
                                                                提取文号
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-md-1  control-label">
                                                    委托单位<span class="symbol required"></span>
                                                </label>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control input-full"
                                                           placeholder="委托单位"
                                                           name="entrustedUnit" required
                                                           value="${projectXlxReportIndividual.entrustedUnit}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                        color="#6f5499" size="10"/>

                                    <c:if test="${assessProjectType == 'assets'}">
                                        <%@include file="apply/assets.jsp" %>
                                    </c:if>


                                    <c:if test="${assessProjectType == 'house'}">
                                        <%@include file="apply/house.jsp" %>
                                    </c:if>


                                    <c:if test="${assessProjectType == 'land'}">
                                        <%@include file="apply/land.jsp" %>
                                    </c:if>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1  control-label">
                                                    承办表<span class="symbol required"></span>
                                                </label>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control input-full"
                                                           placeholder="承办表" required name="undertakeSheet"
                                                           value="${projectXlxReportIndividual.undertakeSheet}">
                                                </div>
                                                <label class="col-md-1  control-label">
                                                    备注<span class="symbol required"></span>
                                                </label>
                                                <div class="col-md-7">
                                                    <textarea class="form-control input-full"
                                                              placeholder="备注" required
                                                              name="remark">${projectXlxReportIndividual.remark}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:choose>
                                <c:when test="${projectPhase.bisUseBox eq false}">
                                    <button type="button" class="btn btn-success" style="margin-left: 10px;"
                                            onclick="submit(false);">直接提交
                                    </button>
                                    <button type="button" class="btn btn-primary" style="margin-left: 10px;"
                                            onclick="submit(true);">提交审批
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-primary" style="margin-left: 10px;"
                                            onclick="submit();">提交
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <%--返回修改--%>
                    <c:if test="${processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>


<script>

    var xlxReportIndividual = {};

    xlxReportIndividual.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    xlxReportIndividual.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    xlxReportIndividual.config = {
        frm: $("#frmXlxReportIndividual"),
    };

    xlxReportIndividual.fileUpload = function (target, tableName, id, deleteFlag) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
                // projectId: id
            },
            deleteFlag: deleteFlag
        });
    };

    xlxReportIndividual.showFile = function (target, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: target
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };

    xlxReportIndividual.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}/' + url,
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
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };
    xlxReportIndividual.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                xlxReportIndividual.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            xlxReportIndividual.run(data, url, type, callback, funParams, errorCallback);
        }
    };


    //公共  赋值 方法
    xlxReportIndividual.baseInitFormData = function (form, item, fileArr, bisDetail, tableName, inputArr) {
        var frm = $(form.selector);
        frm.clearAll();
        frm.initForm(item);
        frm.validate();
        if (fileArr) {
            $.each(fileArr, function (i, n) {
                if (bisDetail == false) {
                    xlxReportIndividual.showFile(n, tableName, xlxReportIndividual.isNotBlank(item.id) ? item.id : '0', false);
                    xlxReportIndividual.fileUpload(n, tableName, xlxReportIndividual.isNotBlank(item.id) ? item.id : '0', false);
                } else {
                    xlxReportIndividual.showFile(n, tableName, xlxReportIndividual.isNotBlank(item.id) ? item.id : '0', true);
                    xlxReportIndividual.fileUpload(n, tableName, xlxReportIndividual.isNotBlank(item.id) ? item.id : '0', true);
                }
            });
        }
        if (inputArr) {
            $.each(inputArr, function (i, n) {
                frm.find("input[name='" + n + "']").val(formatDate(item[n]));
                frm.find("label[name='" + n + "']").html(formatDate(item[n]));
            });
        }
    };

    xlxReportIndividual.initFormData = function (data) {
        var fileArr = [];
        var inputArr = ["investigationsStartDate", "investigationsEndDate", "reportIssuanceDate", "homeWorkEndTime"];
        var frm = xlxReportIndividual.handleJquery(xlxReportIndividual.config.frm);
        xlxReportIndividual.baseInitFormData(frm, data, fileArr, true, AssessDBKey.ProjectTakeNumber, inputArr);

    };


    /*拿取文号*/
    xlxReportIndividual.getReportNumber = function (_this) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        xlxReportIndividual.ajaxServerFun(data, "/projectXlxReportIndividual/getReportNumber", "get", function (data) {
            notifySuccess("成功", "提取文号成功!");
            var numberValue = frm.find("[name=numberValue]").val(data.join(","));
        });
    };

    $(document).ready(function () {
        xlxReportIndividual.handleJquery(xlxReportIndividual.config.frm).validate();
    });


</script>

<script type="application/javascript">


    //提交表单
    function submit(mustUseBox) {
        var frm = xlxReportIndividual.handleJquery(xlxReportIndividual.config.frm);
        var data = formSerializeArray(frm);
        if (!frm.valid()) {
            return false;
        }
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data), mustUseBox);
        }
    }


</script>

</html>

