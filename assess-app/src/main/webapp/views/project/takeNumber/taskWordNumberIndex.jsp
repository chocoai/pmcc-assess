<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">

            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel area_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>拿取文号</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <form class="form-horizontal" id="frmProjectTakeNumber">
                        <input type="hidden" name="id" value="${projectTakeNumber.id}">
                        <input type="hidden" name="assessProjectType" value="${projectTakeNumber.assessProjectType}">
                        <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">


                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    报告出具日期<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                           class="form-control date-picker dbdate" pattern='yyyy-MM-dd'
                                           data-date-format="yyyy-mm-dd" required
                                           value="<fmt:formatDate value='${projectTakeNumber.reportIssuanceDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    作业结束时间<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                           class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd"
                                           pattern='yyyy-MM-dd' required
                                           value="<fmt:formatDate value='${projectTakeNumber.homeWorkEndTime}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div>
                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                        资质类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                        <select class="form-control" name="qualificationType"
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
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    现场查勘开始日期<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" required name="investigationsStartDate"
                                           placeholder="现场查勘开始日期"
                                           class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd"
                                           pattern='yyyy-MM-dd'
                                           value="<fmt:formatDate value='${projectTakeNumber.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    现场查勘结束日期<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <input type="text" required name="investigationsEndDate" placeholder="现场查勘结束日期"
                                           class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd"
                                           pattern='yyyy-MM-dd'
                                           value="<fmt:formatDate value='${projectTakeNumber.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">估价师<span
                                        class="symbol required"></span></label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <select name="realEstateAppraiser" multiple="multiple"
                                            class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    二维码
                                </label>
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                    <div id="_ProjectTakeNumber_BaseOrCode"></div>
                                    <img src="">
                                </div>

                            </div>

                            <div class="x-valid">

                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <input id="ProjectTakeNumber_DocumentHandle"
                                           name="file" type="file" style="display: none"
                                           onchange="baseTakeNumber.upFileLoadReport(this)">
                                    <div class="btn btn-primary"
                                         onclick="$(this).prev().trigger('click')">上传报告
                                    </div>
                                </div>

                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    文档
                                </label>
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                    <div id="_ProjectTakeNumber_Document"></div>
                                </div>
                            </div>
                        </div>

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

                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    文号
                                </label>
                                <div class="col-md-3 col-sm-3 col-xs-12">
                                    <label class="form-control"
                                           name="numberValue">${projectTakeNumber.numberValue}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    拿号
                                </label>
                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                    <button type="button" class="btn-primary btn"
                                            onclick="baseTakeNumber.getReportNumber(this)"><i
                                            class="fa fa-dot-circle-o"></i></button>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    说明
                                </label>
                                <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <textarea class="form-control" name="remark" rows="4"
                                          data-rule-maxlength="255" placeholder=""></textarea>
                                </div>
                            </div>
                        </div>


                    </form>
                </div>

            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
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
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
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
        frm: $("#frmProjectTakeNumber")
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
            error: function (e) {
                if (errorCallback) {
                    errorCallback(e);
                } else {
                    console.log(result.errmsg);
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            }
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
        var fileArr = ["ProjectTakeNumber_BaseOrCode", "ProjectTakeNumber_Document"];
        var inputArr = ["investigationsStartDate", "investigationsEndDate", "reportIssuanceDate", "homeWorkEndTime"];
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        baseTakeNumber.baseInitFormData(frm, data, fileArr, true, AssessDBKey.ProjectTakeNumber, inputArr);
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, data.reportType, function (html, data) {
            frm.find("select[name='reportType']").empty().html(html).trigger('change');
        });
        frm.find("img").attr({src: data.imgPath});
        if (data.qualificationType) {
            baseTakeNumber.getAdPersonalIdentityDto({qualificationType: data.qualificationType}, function (item) {
                baseTakeNumber.dataQualificationShow(item, data.realEstateAppraiser, frm);
            });
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
    baseTakeNumber.getReportNumber = function () {
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        if (!frm.valid()) {
            return false;
        }
        var objData = formSerializeArray(frm);
        var queryFile = {
            tableId: objData.id,
            tableName: AssessDBKey.ProjectTakeNumber,
            fieldsName: "ProjectTakeNumber_Document"
        };
        Loading.progressShow();
        //获取上传的附件
        AssessCommon.getSysAttachmentDtoList(queryFile, function (fileArrays) {
            var ids = [];
            if (fileArrays) {
                $.each(fileArrays, function (i, item) {
                    ids.push(item.id) ;
                })
            }
            console.log(fileArrays) ;
            baseTakeNumber.ajaxServerMethod({formData: JSON.stringify(objData)}, "projectTakeNumber/getProjectWordNumber", "get", function (data) {
                baseTakeNumber.ajaxServerMethod({takeNumberId: objData.id,attachmentIds:ids.join(",")}, "projectTakeNumber/toolBaseOrCode", "get", function (sysAttachmentDto) {
                    Loading.progressHide();
                    data.imgPath = "${pageContext.request.contextPath}" + sysAttachmentDto.filePath;
                    data.attachmentId = sysAttachmentDto.id;
                    baseTakeNumber.initFormData(data);
                }, function (error) {
                    Loading.progressHide();
                    Alert(error);
                });
            }, function (data) {
                Alert(data);
                Loading.progressHide();
            });
        });
    };

    baseTakeNumber.upFileLoadReport = function () {
        var frm = baseTakeNumber.handleJquery(baseTakeNumber.config.frm);
        var data = formSerializeArray(frm);
        var fileElementId = "ProjectTakeNumber_DocumentHandle";
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.ProjectTakeNumber,
                tableId: data.id,
                fieldsName: "ProjectTakeNumber_Document"
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    baseTakeNumber.initFormData(data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
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

