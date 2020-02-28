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

                    <!-- 公共模块end -->

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        拿取文号
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="frmProjectTakeNumber">
                                    <input type="hidden" name="id" value="${projectTakeNumber.id}">
                                    <input type="hidden" name="assessProjectType" value="${projectTakeNumber.assessProjectType}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">


                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                报告出具日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                                       class="form-control input-full date-picker dbdate" pattern='yyyy-MM-dd'
                                                       data-date-format="yyyy-mm-dd" required
                                                       value="<fmt:formatDate value='${projectTakeNumber.reportIssuanceDate}' pattern='yyyy-MM-dd'/>">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                作业结束时间<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd' required
                                                       value="<fmt:formatDate value='${projectTakeNumber.homeWorkEndTime}' pattern='yyyy-MM-dd'/>">
                                            </div>

                                                <label class="col-sm-1 control-label">
                                                    资质类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full" name="qualificationType"
                                                            onchange="baseTakeNumber.onChange(this)" required>
                                                        <option value="">--请选择--</option>
                                                        <c:if test="${not empty qualificationTypes}">
                                                            <c:forEach items="${qualificationTypes}" var="itemA">
                                                                <c:if test="${projectTakeNumber.qualificationType eq itemA.key}">
                                                                    <option selected="selected"
                                                                            value="${itemA.key}">${itemA.value}</option>
                                                                </c:if>
                                                                <c:if test="${projectTakeNumber.qualificationType ne itemA.key}">
                                                                    <option value="${itemA.key}">${itemA.value}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                现场查勘开始日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" required name="investigationsStartDate"
                                                       placeholder="现场查勘开始日期"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd'
                                                       value="<fmt:formatDate value='${projectTakeNumber.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                现场查勘结束日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" required name="investigationsEndDate" placeholder="现场查勘结束日期"
                                                       class="form-control input-full date-picker dbdate"
                                                       data-date-format="yyyy-mm-dd"
                                                       pattern='yyyy-MM-dd'
                                                       value="<fmt:formatDate value='${projectTakeNumber.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                            </div>
                                            <label class="col-sm-1 control-label">估价师<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-3">
                                                <select name="realEstateAppraiser" multiple="multiple"
                                                        class="form-control input-full search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-11">
                                <textarea class="form-control input-full" name="remark" rows="4"
                                          data-rule-maxlength="255" placeholder="说明"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-4  col-sm-4  col-md-4  col-lg-4 control-label">
                                                每个文件都可以生成自己的文号和二维码(可以一次性上传多个)
                                            </label>

                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-info btn-sm"
                                                     onclick="$(this).prev().trigger('click')">	<span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>上传报告
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    </div>


                                </form>

                            </div>
                        </div>
                    </div>

                    <!-- 公共尾部模块引用 -->
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <c:choose>
                                <c:when test="${projectPhase.bisUseBox eq false}">
                                    <button id="btn_submit" class="btn btn-success"
                                            onclick="submit(false);">
                                        直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                    </button>
                                    <button id="btn_submit" class="btn btn-primary"
                                            onclick="submit(true);">
                                        提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button id="btn_submit" class="btn btn-success"
                                            onclick="submit();">
                                        提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
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

<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script type="text/html" id="projectTakeNumberDetailHtml">

    <div>
        <div class=" col-sm-1">
        </div>
        <div class=" col-sm-11 ">
            <div class="row panel">
                <form>
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12  panel-heading  text-center">
                        <h2>第{index}拿号
                            <button class="btn-primary btn" type="button"
                                    onclick="baseTakeNumber.removeProjectTakeNumberDetail(this);">移除<i
                                    class="fa fa-minus-circle"></i></button>
                        </h2>
                    </div>
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12  panel-body ">
                        <div class="row">
                            <input type="hidden" name="id" value="{id}">
                            <input type="hidden" name="masterId">
                            <div class=" col-sm-1 control-label">
                                二维码
                            </div>
                            <div class=" col-sm-11 ">
                                <div id="_ProjectTakeNumber_BaseOrCode{id}"></div>
                                <img src="">
                            </div>
                            <div class=" col-sm-1 control-label">
                                报告类型<span class="symbol required"></span>
                            </div>
                            <div class=" col-sm-11 ">
                                <select name="reportType" required="required"
                                        class="form-control input-full search-select select2">
                                </select>
                            </div>
                            <div class=" col-sm-1 control-label">
                                上传的文档
                            </div>
                            <div class=" col-sm-11 ">
                                <input id="projectTakeNumberDetailSysAttachmentDto{id}" placeholder="继续上传待替换附件"
                                       class="form-control input-full"
                                       type="file">
                                <div id="_projectTakeNumberDetailSysAttachmentDto{id}"></div>
                            </div>
                            <div class="form-inline x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    文号
                                </label>
                                <div class=" col-sm-11 ">
                                    <input type="text" class="form-control input-full" name="numberValue" readonly="readonly">
                                </div>
                            </div>
                            <div class="col-sm-1 control-label">
                                拿取文号
                            </div>
                            <div class=" col-sm-11 ">
                                <button type="button" class="btn-primary btn"
                                        onclick="baseTakeNumber.getReportNumber(this)"><i
                                        class="fa fa-dot-circle-o"></i></button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


</script>

<script>

    var baseTakeNumber = {};

    baseTakeNumber.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    baseTakeNumber.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    baseTakeNumber.config = {
        frm: $("#frmProjectTakeNumber"),
        detailFileId: "projectTakeNumberDetailSysAttachmentDto"
    };

    baseTakeNumber.fileUpload = function (target, tableName, id, deleteFlag) {
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

    baseTakeNumber.showFile = function (target, tableName, id, deleteFlag) {
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

    baseTakeNumber.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}/' + url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (errorCallback) {
                        errorCallback(result.errmsg);
                    } else {
                        console.log(result.errmsg);
                    }
                }
            },
            error: function (result) {
                if (errorCallback) {
                    errorCallback(e);
                } else {
                    console.log(result.errmsg);
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        });
    };

    baseTakeNumber.saveAndUpdateProjectTakeNumberDetail = function (data, callback) {
        baseTakeNumber.ajaxServerMethod({formData: JSON.stringify(data)}, "projectTakeNumber/saveAndUpdateProjectTakeNumberDetail", "post", callback, function (message) {
            notifyInfo('提示',message);
        });
    };


    //公共  赋值 方法
    baseTakeNumber.baseInitFormData = function (form, item, fileArr, bisDetail, tableName, inputArr) {
        var frm = $(form.selector);
        frm.clearAll();
        frm.initForm(item);
        frm.validate();
        if (fileArr) {
            $.each(fileArr, function (i, n) {
                if (bisDetail == false) {
                    baseTakeNumber.showFile(n, tableName, baseTakeNumber.isNotBlank(item.id) ? item.id : '0', false);
                    baseTakeNumber.fileUpload(n, tableName, baseTakeNumber.isNotBlank(item.id) ? item.id : '0', false);
                } else {
                    baseTakeNumber.showFile(n, tableName, baseTakeNumber.isNotBlank(item.id) ? item.id : '0', true);
                    baseTakeNumber.fileUpload(n, tableName, baseTakeNumber.isNotBlank(item.id) ? item.id : '0', true);
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

    baseTakeNumber.initFormData = function (data) {
        var fileArr = [];
        var inputArr = ["investigationsStartDate", "investigationsEndDate", "reportIssuanceDate", "homeWorkEndTime"];
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        baseTakeNumber.baseInitFormData(frm, data, fileArr, true, AssessDBKey.ProjectTakeNumber, inputArr);
        if (data.qualificationType) {
            baseTakeNumber.getAdPersonalIdentityDto({qualificationType: data.qualificationType}, function (item) {
                baseTakeNumber.dataQualificationShow(item, data.realEstateAppraiser, frm);
            });
        }
    };

    baseTakeNumber.initFormProjectTakeNumberDetailData = function (data, frm, init) {
        try {
            if (init) {
                frm.find(".select2").each(function () {
                    $(this).select2();
                });
            }
        } catch (e) {
        }
        var fileArr = ["ProjectTakeNumber_BaseOrCode" + data.id, "projectTakeNumberDetailSysAttachmentDto" + data.id];
        if (data.imgPath) {
            frm.find("img").attr({src: data.imgPath});
        }
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, data.reportType, function (html, data) {
            frm.find("select[name='reportType']").empty().html(html).trigger('change');
        });
        baseTakeNumber.baseInitFormData(frm, data, fileArr, true, AssessDBKey.ProjectTakeNumberDetail, []);
        if (data.numberValue) {
            frm.find("input[name='numberValue']").val(data.numberValue);
        }
        if (data.masterId) {
            frm.find("input[name='masterId']").val(data.masterId);
        }
    };

    /**
     * 获取资质
     * @param data
     * @param callback
     */
    baseTakeNumber.getAdPersonalIdentityDto = function (data, callback) {
        baseTakeNumber.ajaxServerMethod(data, "public/getAdPersonalIdentityDto", "get", callback);
    };

    baseTakeNumber.dataQualificationShow = function (data, realEstateAppraiser, frm) {
        var retHtml = '';
        $.each(data, function (i, item) {
            retHtml += '<option key="' + item.name + '" title="' + item.name + '" value="' + item.userAccount + '"';
            if (realEstateAppraiser) {
                var tempArr = realEstateAppraiser.split(",");
                $.each(tempArr, function (i, n) {
                    if (item.userAccount == n) {
                        retHtml += 'selected="selected"';
                    }
                });
            }
            retHtml += '>' + item.name + '</option>';
        });
        $(frm).find("select[name='realEstateAppraiser']").empty().html(retHtml).trigger('change');
    };


    /*拿取文号*/
    baseTakeNumber.getReportNumber = function (_this) {
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        var form = $(_this).closest("form");
        if (!frm.valid()) {
            return false;
        }
        if (!form.valid()) {
            return false;
        }
        var objData = formSerializeArray(frm);
        var defaultObj = formSerializeArray(form);
        jQuery.extend(objData, defaultObj);
        var queryFile = {
            tableId: objData.id,
            tableName: AssessDBKey.ProjectTakeNumberDetail,
            fieldsName: baseTakeNumber.config.detailFileId + objData.id
        };
        Loading.progressShow();
        //获取上传的附件
        AssessCommon.getSysAttachmentDtoList(queryFile, function (fileArrays) {
            var ids = [];
            if (fileArrays) {
                $.each(fileArrays, function (i, item) {
                    ids.push(item.id);
                })
            }
            baseTakeNumber.ajaxServerMethod({formData: JSON.stringify(objData)}, "projectTakeNumber/getProjectWordNumber", "get", function (data) {
                baseTakeNumber.initFormProjectTakeNumberDetailData(data, form, false);
                baseTakeNumber.ajaxServerMethod({
                    takeNumberDetailId: objData.id,
                    masterId: objData.masterId,
                    attachmentIds: ids.join(",")
                }, "projectTakeNumber/toolBaseOrCode", "get", function (sysAttachmentDto) {
                    Loading.progressHide();
                    data.imgPath = "${pageContext.request.contextPath}" + sysAttachmentDto.filePath;
                    data.attachmentId = sysAttachmentDto.id;
                    baseTakeNumber.initFormProjectTakeNumberDetailData(data, form, false);
                }, function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                });
            }, function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                Loading.progressHide();
            });
        });
    };

    baseTakeNumber.upFileLoadReport = function () {
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        var parentData = formSerializeArray(frm);
        var fileElementId = "ProjectTakeNumber_DocumentHandle";
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.ProjectTakeNumber,
                tableId: parentData.id,
                fieldsName: "ProjectTakeNumber_Document"
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    var ids = result.data.split(",");
                    $.each(ids, function (count, attachmentId) {
                        var html = $("#projectTakeNumberDetailHtml").html();
                        var target = frm.closest(".x_panel");
                        baseTakeNumber.saveAndUpdateProjectTakeNumberDetail({masterId: parentData.id}, function (data) {
                            html = html.replace(/{id}/g, data.id);
                            html = html.replace(/{index}/g, $(document).find("input[name=id]").size() - 1);
                            target.append(html);
                            AssessCommon.getSysAttachmentDto(attachmentId, function (sysAttachmentDto) {
                                sysAttachmentDto.fieldsName = baseTakeNumber.config.detailFileId + data.id;
                                sysAttachmentDto.tableId = data.id;
                                sysAttachmentDto.tableName = AssessDBKey.ProjectTakeNumberDetail;
                                AssessCommon.saveAndUpdateSysAttachmentDto(sysAttachmentDto, function () {
                                    baseTakeNumber.initFormData(parentData);
                                    var form = $("#" + baseTakeNumber.config.detailFileId + data.id).closest("form");
                                    data.masterId = parentData.id;
                                    baseTakeNumber.initFormProjectTakeNumberDetailData(data, form, true);
                                });
                            });
                        });
                    });
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errrmsg);
            }
        });
    };

    baseTakeNumber.removeProjectTakeNumberDetail = function (_this) {
        var form = $(_this).closest("form");
        var defaultObj = formSerializeArray(form);
        baseTakeNumber.ajaxServerMethod(defaultObj, "projectTakeNumber/deleteProjectTakeNumberDetailById", "post", function () {
            form.parent().parent().parent().remove();
            notifySuccess('成功','移除成功!');
        });
    };

    baseTakeNumber.onChange = function (item) {
        var v = $(item).find("option:selected");
        var frm = $(item).closest("form");
        baseTakeNumber.getAdPersonalIdentityDto({qualificationType: v.val()}, function (data) {
            baseTakeNumber.dataQualificationShow(data, null, frm);
        });
    };

    $(function () {
        if ("${projectTakeNumber}") {
            var obj = '${el:toJsonString(projectTakeNumber)}';
            baseTakeNumber.initFormData(JSON.parse(obj));
        }
        if ("${takeNumberDetailIdList}") {
            var takeNumberDetailIdList = '${takeNumberDetailIdList}';
            var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
            takeNumberDetailIdList = takeNumberDetailIdList.split(",");
            var target = frm.closest(".x_panel");
            $.each(takeNumberDetailIdList, function (i, id) {
                var html = $("#projectTakeNumberDetailHtml").html();
                html = html.replace(/{id}/g, id);
                html = html.replace(/{index}/g, $(document).find("input[name=id]").size() - 1);
                target.append(html);
                var form = $("#" + baseTakeNumber.config.detailFileId + id).closest("form");
                baseTakeNumber.ajaxServerMethod({id: id}, "projectTakeNumber/getProjectTakeNumberDetailById", "get", function (item) {
                    baseTakeNumber.initFormProjectTakeNumberDetailData(item, form, true);
                });
            });
        }
    });

</script>

<script type="application/javascript">


    //提交表单
    function submit(mustUseBox) {
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data), mustUseBox);
        }
    }


</script>

</html>

