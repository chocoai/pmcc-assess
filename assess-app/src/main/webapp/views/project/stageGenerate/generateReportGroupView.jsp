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
                                                <input type="text" class="form-control input-full" name="name"
                                                       placeholder="名称" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                报告类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="reportType" required multiple="multiple"
                                                        class="form-control input-full search-select select2">
                                                    <option>请选择</option>
                                                    <c:forEach items="${reportTypeList}" var="reportType"
                                                               varStatus="status">
                                                        <option value="${reportType.id}">${reportType.name}</option>
                                                    </c:forEach>
                                                </select>
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
                                            <span class="toolGenerateReportItem">
                                            <button type="button" class="btn btn-warning  btn-sm"
                                                    onclick="reportGroupObj.removeGenerateReportItem() ;">
                                                <i class="fa fa-minus"></i>
                                                移除
                                            </button>

                                                <button class="btn-primary btn btn-sm" type="button"
                                                        onclick="schemeJudgeObj.init({callback:reportGroupObj.selectJudgeObj,this_:this ,areaGroupId: $(this).closest('form').find('[name=areaGroupId]').val()});">
                                    选择估价对象
                                </button>
                                                </span>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="reportGroupObj.saveData()">
                    保存
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
                            <input type="hidden" data-name="areaGroupId" value="{areaGroupId}">

                            <label class="col-sm-1 control-label">估价对象</label>

                            <div class="col-sm-3">
                                <label class="form-control input-full">{fullName}</label>
                            </div>
                            <label class="col-sm-1 control-label">操作</label>
                            <div class="col-sm-7">
                                <button type="button" class="btn btn-sm btn-primary"
                                        onclick="reportGroupObj.editData(this ,'{id}') ;">
                                    <i class="fa fa-pen"></i>
                                </button>
                                <button class="btn btn-warning btn-sm" type="button"
                                        onclick="reportGroupObj.cleanHTMLData(this ,'{id}')"><span class="btn-label"><i
                                        class="fa fa-minus"></i></span>
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
                                    <!-- 报告附件方法 -->
                                    <button type="button" class="btn-dark btn btn-sm"
                                            onclick="reportGroupObj.generateReport('${reportType.id}','{id}')">
                                        生成${reportType.name}
                                        <i class="fa fa-file-word-o"></i></button>
                                </label>
                                <div class="col-sm-3">
                                    <!-- 报告附件id -->
                                    <div id="_${reportType.fieldName}{areaGroupId}{id}"></div>
                                </div>
                                <label class="col-sm-1">
                                    <!-- 报告附件方法 -->
                                    <button type="button" class="btn-dark btn btn-sm"
                                            onclick="reportGroupObj.generateReportAttachment('${reportType.id}','{id}')">
                                        生成${reportType.name}附件
                                        <i class="fa fa-file-word-o"></i></button>
                                </label>
                                <div class="col-sm-2">
                                    <!-- 报告附件id -->
                                    <div id="_${reportType.fieldName}_Attachment{areaGroupId}{id}"></div>
                                </div>
                                <div class="col-sm-1">
                                    <div class="input-group-append">
                                        <button class="btn btn-info btn-sm dropdown-toggle"
                                                type="button"
                                                data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="false">
                                            意见稿
                                        </button>
                                        <div class="dropdown-menu" x-placement="bottom-start"
                                             style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                            <c:forEach var="item" items="${documentTemplateList}">
                                                <a class="dropdown-item"
                                                   href="${pageContext.request.contextPath}/documentOpinion/applyIndex/${item.id}&${projectInfo.id}&${reportType.fieldName}&{areaGroupId}&${reportType.id}&{reportInfoId}"
                                                   target="_blank">${item.templateName}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-1">
                                    <input id="GGGGGG${reportType.fieldName}{areaGroupId}{id}" name="file" type="file"
                                           style="display: none"
                                           onchange="reportGroupObj.upFileLoadReport(this,'${reportType.fieldName}' ,'{id}' ,'{areaGroupId}' )">
                                    <div class="btn btn-primary btn-sm"
                                         onclick="$(this).prev().trigger('click')">上传报告
                                    </div>
                                </div>

                                <div class="col-sm-1">
                                    <button type="button" class="btn-primary btn btn-sm"
                                            onclick="reportGroupObj.getReportNumber('${reportType.id}','{id}')">
                                        拿号<i
                                            class="fa fa-dot-circle-o"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </form>
        </div>
    </div>


</script>
<%@include file="/views/project/tool/selectSchemeJudgeTool.jsp" %>
<div>
    <input type="hidden" id="reportGroupObjJson" value='${el:toJsonString(generationVos)}'>
</div>


<script type="text/javascript">

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

    reportGroupObj.saveAndUpdateGenerateReportGroup = function (data, callback) {
        data.projectId = '${projectPlan.projectId}';
        reportGroupObj.ajaxServerFun({
            formData: JSON.stringify(data),
            updateNull: false
        }, "/generateReportGroup/saveAndUpdateGenerateReportGroup", "post", callback, null, null);
    };

    reportGroupObj.saveAndUpdateGenerateReportItemArray = function (array, callback) {
        reportGroupObj.ajaxServerFun({
            formData: JSON.stringify(array),
            updateNull: false
        }, "/generateReportItem/saveAndUpdateGenerateReportItemArray", "post", callback, null, null);
    };

    reportGroupObj.deleteGenerateReportItem = function (id, callback) {
        reportGroupObj.ajaxServerFun({id: id}, "/generateReportItem/deleteGenerateReportItem", "post", callback, null, null);
    };

    reportGroupObj.getGenerateReportItemByJudgeObjectIds = function (areaGroupId, ids, callback) {
        reportGroupObj.ajaxServerFun({
            areaGroupId: areaGroupId,
            ids: ids
        }, "/generateReportItem/getGenerateReportItemByJudgeObjectIds", "get", callback, null, null);
    };

    reportGroupObj.deleteGenerateReportGroup = function (id, callback) {
        reportGroupObj.ajaxServerFun({id: id}, "/generateReportGroup/deleteGenerateReportGroup", "post", callback, 'delete', null);
    };

    reportGroupObj.getGenerateReportGroupById = function (id, callback) {
        reportGroupObj.ajaxServerFun({id: id}, "/generateReportGroup/getGenerateReportGroupById", "get", callback, null, null);
    };

    reportGroupObj.getValidData = function (projectId, callback) {
        reportGroupObj.ajaxServerFun({projectId: projectId}, "/generateReportGroup/getValidData", "get", callback, null, null);
    };

    reportGroupObj.cleanHTMLData = function (_this, id) {
        var group = $(_this).closest(".card");
        reportGroupObj.deleteGenerateReportGroup(id, function () {
            group.remove();
        });
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

    reportGroupObj.selectJudgeObj = function (_this, id) {
        var ids = id.split(",");
        var arr = [];
        var frm = $(_this).closest("form");
        var masterId = frm.find("[name=id]").val();
        var areaGroupId = frm.find("[name=areaGroupId]").val();
        var form = $("#groupForm" + areaGroupId);
        var dataInfo = formSerializeArray(form);
        $.each(ids, function (k, judgeObjectId) {
            var item = {judgeObjectId: judgeObjectId, masterId: masterId, areaGroupId: areaGroupId};
            arr.push(item);
        });
        reportGroupObj.getGenerateReportItemByJudgeObjectIds(areaGroupId, id, function (data) {
            if (data.length == 0) {
                reportGroupObj.saveAndUpdateGenerateReportItemArray(arr, function () {
                    notifySuccess("成功", "估价对象选择成功!");
                    reportGroupObj.init(dataInfo);
                    reportGroupObj.handleJquery(reportGroupObj.reportItemList).bootstrapTable('refresh');
                });
            } else {
                var tempArr = [];
                $.each(data, function (i, item) {
                    tempArr.push("估价对象" + item.number);
                });
                var message = tempArr.join(",") + "已经添加到组中了,请检查后重新添加";
                AlertSuccess("提示", message);
            }
        });
    };

    reportGroupObj.removeGenerateReportItem = function () {
        var table = reportGroupObj.handleJquery(reportGroupObj.reportItemList);
        var rows = table.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选需要移除的数据!");
            return false;
        }
        var arr = [];
        $.each(rows, function (i, item) {
            arr.push(item.id);
        });
        reportGroupObj.deleteGenerateReportItem(arr.join(","), function () {
            notifySuccess("成功", "移除成功!");
            reportGroupObj.initData();
            reportGroupObj.handleJquery(reportGroupObj.reportItemList).bootstrapTable('refresh');
        });
    };

    reportGroupObj.loadGenerateReportItemList = function (masterId) {
        var table = reportGroupObj.handleJquery(reportGroupObj.reportItemList);
        var cols = [];
        cols.push({field: 'name', title: '委估对象名称', width: "85%"});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/generateReportItem/getBootstrapTableVo", cols, {masterId: masterId}, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            toolbar: ".toolGenerateReportItem",
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    };

    reportGroupObj.editData = function (_this, id) {
        var box = reportGroupObj.handleJquery(reportGroupObj.box);
        var frm = box.find("form");
        frm.clearAll();
        reportGroupObj.getGenerateReportGroupById(id, function (data) {
            frm.initForm(data);
            if (data.reportType) {
                frm.find("select[name=reportType]").val(data.reportType.split(",")).trigger('change');
            }
            reportGroupObj.loadGenerateReportItemList(id);
            box.modal('show');
        });
    };

    reportGroupObj.saveData = function () {
        var box = reportGroupObj.handleJquery(reportGroupObj.box);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        reportGroupObj.saveAndUpdateGenerateReportGroup(data, function () {
            box.modal('hide');
            notifySuccess("成功", "修改数据成功!");
            reportGroupObj.initData();
            reportGroupObj.reportTypeChangeEvent(data);
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

    reportGroupObj.appendHTML = function (reportInfoId, areaGroupId) {
        reportGroupObj.getGenerateReportGroupList(areaGroupId, function (data) {
            var item = {reportInfoId: reportInfoId, areaGroupId: areaGroupId, name: "组" + (data.length + 1)};
            reportGroupObj.saveAndUpdateGenerateReportGroup(item, function (result) {
                reportGroupObj.init({id: reportInfoId, areaGroupId: areaGroupId});
            });
        });
    };

    reportGroupObj.fileShow = function (fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportGroup,
                tableId: id == undefined ? 0 : id
            },
            editFlag: true,
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    };

    reportGroupObj.upFileLoadReport = function (that, fileId, id, areaGroupId) {
        var fileElementId = $(that).attr("id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.GenerateReportGroup,
                tableId: id,
                fieldsName: fileId + areaGroupId + "" + id
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                        $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                            reportGroupObj.fileShow(n + "" + areaGroupId + "" + id, true, id);
                        });
                    });
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * 生成报告
     * @param reportType
     * @param id
     */
    reportGroupObj.generateReport = function (reportType, id) {
        reportGroupObj.getGenerateReportGroupById(id, function (group) {
            var form = $("#groupForm" + group.areaGroupId);
            var data = formSerializeArray(form);
            if (!form.valid()) {
                return false;
            }
            if (data.realEstateAppraiser) {

            } else {
                notifyInfo('提示', '估价师必须选择');
                return false;
            }
            data.areaGroupId = group.areaGroupId;
            data.projectPlanId = '${projectPlan.id}';
            data.projectId = '${projectPlan.projectId}';
            group.symbolOperation = 'none';//不拿号 标志
            if (!AssessCommon.isNumber(data.assessCategory)) {
                data.assessCategory = null;
            }
            reportGroupObj.generateReportHandle(form, group, data, reportType, function () {
                reportGroupObj.init(data);
                notifySuccess("成功", '报告生成成功');
            });
        });
    };

    /**
     * 生成报告
     * @param reportType
     * @param id
     */
    reportGroupObj.generateReportAttachment = function (reportType, id) {
        reportGroupObj.getGenerateReportGroupById(id, function (group) {
            var form = $("#groupForm" + group.areaGroupId);
            var data = formSerializeArray(form);
            if (!form.valid()) {
                return false;
            }
            if (data.realEstateAppraiser) {

            } else {
                notifyInfo('提示', '估价师必须选择');
                return false;
            }
            data.areaGroupId = group.areaGroupId;
            data.projectPlanId = '${projectPlan.id}';
            data.projectId = '${projectPlan.projectId}';
            if (!AssessCommon.isNumber(data.assessCategory)) {
                data.assessCategory = null;
            }
            objGenerate.ajaxServerMethod({
                ids: reportType,
                info: JSON.stringify(data),
                group: JSON.stringify(group)
            }, '/generateReport/generateReportAttachment', "post", function (data) {
                if (data) {
                    getSchemeReportGeneration(data, function (info) {
                        initFormSchemeReportGeneration(info, form, group.areaGroupId);
                        reportGroupObj.init(data);
                        notifySuccess("成功", '报告附件生成成功');
                    });
                }
                if (!data) {
                    notifyInfo("提示", '报告附件没有配置');
                }
            });
        });
    };

    /**
     * @param reportType
     * @param id
     */
    reportGroupObj.getReportNumber = function (reportType, id) {
        reportGroupObj.getGenerateReportGroupById(id, function (group) {
            var form = $("#groupForm" + group.areaGroupId);
            var data = formSerializeArray(form);
            if (!form.valid()) {
                return false;
            }
            if (data.realEstateAppraiser) {

            } else {
                notifyInfo('提示', '估价师必须选择');
                return false;
            }
            data.areaGroupId = group.areaGroupId;
            data.projectPlanId = '${projectPlan.id}';
            data.projectId = '${projectPlan.projectId}';
            group.symbolOperation = 'get';//拿号 标志
            if (!AssessCommon.isNumber(data.assessCategory)) {
                data.assessCategory = null;
            }
            reportGroupObj.generateReportHandle(form, group, data, reportType, function () {
                reportGroupObj.init(data);
                notifySuccess("成功", '拿号成功');
            });
        });
    };

    /**
     * 重新拿号
     * @param reportType
     * @param id
     */
    reportGroupObj.reGetDocumentNumber = function (reportType, id) {
        reportGroupObj.getGenerateReportGroupById(id, function (group) {
            var form = $("#groupForm" + group.areaGroupId);
            var data = formSerializeArray(form);
            if (!form.valid()) {
                return false;
            }
            data.areaGroupId = group.areaGroupId;
            data.projectPlanId = '${projectPlan.id}';
            data.projectId = '${projectPlan.projectId}';
            group.symbolOperation = 'reset';//重新拿号 标志
            if (!AssessCommon.isNumber(data.assessCategory)) {
                data.assessCategory = null;
            }
            AlertConfirm("是否确认", "请注意报告二维码无法自动替换,假如要替换类似于报告二维码这样的数据请在页面上上删除报告二维码,然后用\\${报告二维码}这样的文本放置在删除的位置", function () {
                reportGroupObj.generateReportHandle(form, group, data, reportType, function () {
                    reportGroupObj.init(data);
                    notifySuccess("成功", '重新拿号成功!');
                });
            })
        });
    };

    /**
     * 报告替换 method
     * @param group
     * @param info
     * @param ids
     * @param callback
     */
    reportGroupObj.generateReportHandle = function (form, group, info, ids, callback) {
        objGenerate.ajaxServerMethod({
            ids: ids,
            info: JSON.stringify(info),
            group: JSON.stringify(group)
        }, '/generateReport/generate', "post", function (data) {
            getSchemeReportGeneration(data, function (info) {
                initFormSchemeReportGeneration(info, form, group.areaGroupId);
                if (callback) {
                    callback();
                }
            });
        });
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