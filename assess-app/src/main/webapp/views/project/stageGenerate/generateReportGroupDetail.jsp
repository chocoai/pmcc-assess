<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-5-14
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="divBoxGenerateReportGroup" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">报告分组</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="areaGroupId">
                    <input type="hidden" name="reportInfoId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <label class="form-control input-full" name="name"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                报告类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <label class="form-control input-full" name="reportTypeName"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid" style="margin-top: 30px;">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                                估价对象列表
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <table class="table table-bordered"
                                                       id="tb_GenerateReportItem_list"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/html" id="generateReportGroupToolView">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    {name}
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal" enctype="multipart/form-data">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <input type="hidden" data-name="id" value="{id}">
                            <label class="col-sm-1 control-label">估价对象</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">{fullName}</label>
                            </div>
                            <div class="col-sm-7">
                                <button type="button" class="btn btn-sm  btn-info"
                                        onclick="reportGroupObj.editData(this ,'{id}') ;">
                                    <i class="fa fa-search fa-white"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                    <div class="row form-group" style="display: none;" id="reportType{areaGroupId}{id}${reportType.id}"
                         data-name="reportType{areaGroupId}{id}">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1">
                                        ${reportType.name}
                                </label>
                                <div class="col-sm-3">
                                    <!-- 报告附件id -->
                                    <div id="_${reportType.fieldName}{areaGroupId}{id}"></div>
                                </div>
                                <label class="col-sm-1">
                                        ${reportType.name} 附件
                                </label>
                                <div class="col-sm-3">
                                    <!-- 报告附件id -->
                                    <div id="_${reportType.fieldName}_Attachment{areaGroupId}{id}"></div>
                                </div>
                                <div class="col-sm-2">
                                    <c:if test="${flog=='approval'}">
                                        <input id="archiveReport_{areaGroupId}_{id}_${reportType.fieldName}"
                                               name="archiveReport_{areaGroupId}_{id}_${reportType.fieldName}"
                                               placeholder="归档报告"
                                               class="form-control input-full"
                                               type="file">
                                    </c:if>
                                    <div id="_archiveReport_{areaGroupId}_{id}_${reportType.fieldName}"></div>
                                </div>
                                <div class="col-sm-2">
                                    <c:if test="${flog=='approval'}">
                                        <input id="archiveReportFile_{areaGroupId}_{id}_${reportType.fieldName}"
                                               name="archiveReportFile_{areaGroupId}_{id}_${reportType.fieldName}"
                                               placeholder="归档报告附件"
                                               class="form-control input-full"
                                               type="file">
                                    </c:if>
                                    <div id="_archiveReportFile_{areaGroupId}_{id}_${reportType.fieldName}"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </form>
        </div>
    </div>


</script>

<div>
    <input type="hidden" id="reportGroupObjJson" value='${el:toJsonString(generationVos)}'>
</div>


<script type="text/javascript">

    /**
     * @author:  zch
     * 描述:报告附件 数组  拼接为REPORT_TYPE_PREAUDIT
     * @date:  2019-05-27
     **/
    function getSchemeReportGenerationFileControlIdArray(callback) {
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '', function (html, data) {
            var fileArray = [];
            var underline = "_";
            data.forEach(function (value, index) {
                var fieldName = value.fieldName;
                if (fieldName) {
                    var strArray = fieldName.split(".");
                    var tempArray = [];
                    if (strArray.length >= 1) {
                        strArray.forEach(function (item, i) {
                            tempArray.push(item.toUpperCase());
                        });
                    }
                    if (tempArray.length >= 1) {
                        fileArray.push(tempArray.join(underline));
                    }
                }
            });
            if (callback) {
                callback(fileArray);
            }
        });
    }


    var reportGroupObj = {};

    reportGroupObj.box = $("#divBoxGenerateReportGroup");
    reportGroupObj.reportItemList = $("#tb_GenerateReportItem_list");

    reportGroupObj.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    reportGroupObj.run = function (data, url, type, callback, funParams, errorCallback) {
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
    reportGroupObj.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                reportGroupObj.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            reportGroupObj.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    reportGroupObj.replaceHtml = function (data) {
        var html = $("#generateReportGroupToolView").html();
        html = html.replace(/{id}/g, data.id);
        html = html.replace(/{reportInfoId}/g, data.reportInfoId);
        html = html.replace(/{areaGroupId}/g, data.areaGroupId);
        if (data.fullName) {
            html = html.replace(/{fullName}/g, data.fullName);
        } else {
            html = html.replace(/{fullName}/g, '');
        }
        if (data.name) {
            html = html.replace(/{name}/g, data.name);
        } else {
            html = html.replace(/{name}/g, '');
        }
        if (data.reportTypeName) {
            html = html.replace(/{reportTypeName}/g, data.reportTypeName);
        } else {
            html = html.replace(/{reportTypeName}/g, '');
        }
        return html;
    };

    reportGroupObj.getGenerateReportGroupList = function (areaGroupId, callback) {
        reportGroupObj.ajaxServerFun({
            areaGroupId: areaGroupId,
            projectId: '${projectPlan.projectId}'
        }, "/generateReportGroup/getGenerateReportGroupList", "get", callback, null, null);
    };

    reportGroupObj.getGenerateReportGroupById = function (id, callback) {
        reportGroupObj.ajaxServerFun({id: id}, "/generateReportGroup/getGenerateReportGroupById", "get", callback, null, null);
    };


    reportGroupObj.initData = function () {
        var arr = JSON.parse($("#reportGroupObjJson").val());
        if (!arr) {
            return false;
        }
        if (!$.isArray(arr)) {
            return false;
        }
        if (arr.length == 0) {
            return false;
        }

        $.each(arr, function (i, item) {
            reportGroupObj.init(item);
        });
    };

    reportGroupObj.loadGenerateReportItemList = function (masterId) {
        var table = reportGroupObj.handleJquery(reportGroupObj.reportItemList);
        var cols = [];
        cols.push({field: 'name', title: '委估对象名称', width: "35%"});
        cols.push({field: 'number', title: '委估对象编号', width: "35%"});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/generateReportItem/getBootstrapTableVo", cols, {masterId: masterId}, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    reportGroupObj.editData = function (_this, id) {
        var box = reportGroupObj.handleJquery(reportGroupObj.box);
        var frm = box.find("form");
        frm.clearAll();
        reportGroupObj.getGenerateReportGroupById(id, function (data) {
            frm.initForm(data);
            reportGroupObj.loadGenerateReportItemList(id);
            box.modal('show');
        });
    };

    reportGroupObj.reportTypeChangeEvent = function (data) {
        var target = $("#generateReportGroupTool" + data.areaGroupId);
        var tempName = 'reportType' + data.areaGroupId + data.id;
        target.find("div[data-name=" + tempName + "]").hide();
        if (data.reportType) {
            var ids = data.reportType.split(",");
            if ($.isArray(ids)) {
                $.each(ids, function (i, node) {
                    $("#" + tempName + node).show();
                });
            }
        }
    };


    reportGroupObj.fileShow = function (fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportGroup,
                tableId: id == undefined ? 0 : id,
            },
            editFlag: true,
            deleteFlag: false,
            signatureFlag: '${activityCnName}'.indexOf("盖章") > -1
        })
    };

    reportGroupObj.fileShow2 = function (fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportGroup,
                tableId: id == undefined ? 0 : id,
            },
            editFlag: true,
            deleteFlag: false,
            signatureFlag: false
        })
    };

    reportGroupObj.init = function (generationVo) {
        var reportInfoId = generationVo.id;
        var areaGroupId = generationVo.areaGroupId;
        var html = "";
        html += "<button class='btn btn-sm btn-success' type='button' onclick='reportGroupObj.appendHTML(reportInfoId,areaGroupId);'>";
        html += "<i class='fa fa-plus'></i>";
        html += "</button>";
        html = html.replace(/reportInfoId/g, reportInfoId);
        html = html.replace(/areaGroupId/g, areaGroupId);
        (function (target) {
            target.empty();
            reportGroupObj.getGenerateReportGroupList(areaGroupId, function (data) {
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        target.append(reportGroupObj.replaceHtml(item));
                        reportGroupObj.reportTypeChangeEvent(item);
                        getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                            $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                                reportGroupObj.fileShow(n + "" + areaGroupId + "" + item.id, true, item.id);
                                reportGroupObj.fileShow(n + "_Attachment" + areaGroupId + "" + item.id, true, item.id);

                                FileUtils.uploadFiles({
                                    buttonText:'归档报告',
                                    target: "archiveReport_" + areaGroupId + "_" + item.id + "_" + n,
                                    disabledTarget: "btn_submit",
                                    formData: {
                                        fieldsName: "archiveReport_" + areaGroupId + "_" + item.id + "_" + n,
                                        tableName: AssessDBKey.GenerateReportItem,
                                        tableId: item.id
                                    },
                                    deleteFlag: true
                                });
                                FileUtils.getFileShows({
                                    target: "archiveReport_" + areaGroupId + "_" + item.id + "_" + n,
                                    formData: {
                                        fieldsName: "archiveReport_" + areaGroupId + "_" + item.id + "_" + n,
                                        tableName: AssessDBKey.GenerateReportItem,
                                        tableId: item.id
                                    },
                                    deleteFlag: ${flog=='approval'?true:false}
                                });

                                FileUtils.uploadFiles({
                                    buttonText:'归档报告附件',
                                    target: "archiveReportFile_" + areaGroupId + "_" + item.id + "_" + n,
                                    disabledTarget: "btn_submit",
                                    formData: {
                                        fieldsName: "archiveReportFile_" + areaGroupId + "_" + item.id + "_" + n,
                                        tableName: AssessDBKey.GenerateReportItem,
                                        tableId: item.id
                                    },
                                    deleteFlag: true
                                });
                                FileUtils.getFileShows({
                                    target: "archiveReportFile_" + areaGroupId + "_" + item.id + "_" + n,
                                    formData: {
                                        fieldsName: "archiveReportFile_" + areaGroupId + "_" + item.id + "_" + n,
                                        tableName: AssessDBKey.GenerateReportItem,
                                        tableId: item.id
                                    },
                                    deleteFlag: ${flog=='approval'?true:false}
                                });
                            });
                        });
                    });
                    setTimeout(function () {
                        var group = target.find(".form-group").first();
                        var btnX = group.find(".btn-warning");
                        var parent = btnX.parent();
                        btnX.remove();
                        parent.append(html);
                    }, 100);
                } else {
                    var item = {reportInfoId: reportInfoId, areaGroupId: areaGroupId, name: "组1"};
                    reportGroupObj.saveAndUpdateGenerateReportGroup(item, function () {
                        reportGroupObj.init(generationVo);
                    });
                }
            });
        }($("#generateReportGroupTool" + areaGroupId)));
    };


    $(document).ready(function () {
        reportGroupObj.initData();
    });


</script>