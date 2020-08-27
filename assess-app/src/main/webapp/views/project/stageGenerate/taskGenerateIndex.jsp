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

                    <!-- 填写表单 start -->
                    <c:forEach items="${generationVos}" var="generationVo">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                                ${generationVo.areaGroupName}
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form class="form-horizontal" id="groupForm${generationVo.areaGroupId}"
                                          enctype="multipart/form-data">
                                        <input type="hidden"  name="areaGroupId" value="${generationVo.areaGroupId}">
                                        <input type="hidden" name="id" value="${generationVo.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        作业开始时间<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="homeWorkStartTime"
                                                               placeholder="作业开始时间"
                                                               class="form-control input-full date-picker dbdate"
                                                               pattern='yyyy-MM-dd'
                                                               data-date-format="yyyy-mm-dd" required
                                                               value="<fmt:formatDate value='${generationVo.homeWorkStartTime}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        作业结束时间<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd' required
                                                               value="<fmt:formatDate value='${generationVo.homeWorkEndTime}' pattern='yyyy-MM-dd'/>">
                                                    </div>

                                                    <label class="col-sm-1 control-label">
                                                        资质类型<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full" name="qualificationType"
                                                                onchange="onChange(this)" required>
                                                            <option value="">--请选择--</option>
                                                            <c:if test="${not empty qualificationTypes}">
                                                                <c:forEach items="${qualificationTypes}" var="itemA">
                                                                    <c:if test="${generationVo.qualificationType eq itemA.key}">
                                                                        <option selected="selected"
                                                                                value="${itemA.key}">${itemA.value}</option>
                                                                    </c:if>
                                                                    <c:if test="${generationVo.qualificationType ne itemA.key}">
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
                                                               value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        现场查勘结束日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" required name="investigationsEndDate"
                                                               placeholder="现场查勘结束日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value='${generationVo.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
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
                                                        报告出具日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="reportIssuanceDate"
                                                               placeholder="报告出具日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               pattern='yyyy-MM-dd'
                                                               data-date-format="yyyy-mm-dd" required
                                                               value="<fmt:formatDate value='${generationVo.reportIssuanceDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group" style="display: none;">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        备案日期
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="recordDate" placeholder="备案日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value='${generationVo.recordDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">查询码</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="queryCode"
                                                               class="form-control input-full" placeholder="查询码">
                                                    </div>
                                                    <label class="col-sm-1 control-label">备案号</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="recordNo"
                                                               class="form-control input-full" placeholder="备案号">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label" style="display: none;">评估类型(增加封面)</label>
                                                    <div class="col-sm-3" style="display: none;">
                                                        <select name="assessCategory"
                                                                class="form-control input-full search-select select2">
                                                            <option>请选择</option>
                                                            <c:forEach items="${projectCategoryKeyValueDtoList}"
                                                                       var="projectCategoryKey">
                                                                <option value="${projectCategoryKey.key}">${projectCategoryKey.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-body">
                                    <div id="generateReportGroupTool${generationVo.areaGroupId}"></div>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                getSchemeReportGeneration({
                                    projectPlanId: '${projectPlan.id}',
                                    areaGroupId: '${generationVo.areaGroupId}'
                                }, function (info) {
                                    initFormSchemeReportGeneration(info, $('#groupForm${generationVo.areaGroupId}'), '${generationVo.areaGroupId}');
                                });
                            })
                        </script>
                    </c:forEach>
                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                    <%@include file="generateReportGroupView.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">

    var objGenerate = {};

    objGenerate.run = function (data, url, type, callback, funParams, errorCallback) {
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
    objGenerate.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                objGenerate.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            objGenerate.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    objGenerate.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        objGenerate.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    objGenerate.reportTypeChangeEvent = function (_this, reportType) {
        var frm = $(_this).closest('form');
        frm.find("div[data-name=" + reportType + "]").hide();
        var data = formSerializeArray(frm);
        if (data.reportType) {
            saveGenerateReportInfo([data], function () {
                var ids = data.reportType.split(",");
                $.each(ids, function (i, node) {
                    $("#" + reportType + node).show();
                });
            });
        }
    };

    /**
     * 获取资质
     * @param data
     * @param callback
     */
    function getAdPersonalIdentityDto(data, callback) {
        objGenerate.ajaxServerMethod(data, '/public/getAdPersonalIdentityDto', "get", callback, null);
    }

    //上传报告 临时添加zch
    function upFileLoadReport(that, fileId, id, areaGroupId) {
        var fileElementId = $(that).attr("id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: id,
                fieldsName: fileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    initFormSchemeReportGeneration({id: id}, $(that).closest('form'), areaGroupId);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

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


    function fileShow(fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: id == undefined ? 0 : id
            },
            editFlag: true,
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    function onChange(item) {
        var v = $(item).find("option:selected");
        var frm = $(item).closest("form");
        getAdPersonalIdentityDto({qualificationType: v.val()}, function (data) {
            dataQualificationShow(data, null, frm);
        });
    }

    /**
     * 资质显示
     * @param data
     * @param realEstateAppraiser
     * @param frm
     */
    function dataQualificationShow(data, realEstateAppraiser, frm) {
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
    }


    //赋值
    function initFormSchemeReportGeneration(info, frm, areaGroupId) {
        if (info) {
            getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                    fileShow(n + "" + areaGroupId, true, info.id);
                });
            });
            frm.initForm(info);
            getAdPersonalIdentityDto({qualificationType: info.qualificationType}, function (data) {
                dataQualificationShow(data, info.realEstateAppraiser, frm);
            });
            if (info.reportType) {
                frm.find("select[name=reportType]").val(info.reportType.split(",")).trigger('change');
            }
        }
    }

    function getSchemeReportGeneration(data, callback) {
        objGenerate.ajaxServerMethod(data, '/generateReport/getGenerateReportGeneration', "get", callback, null);
    }

    //重新拿号 也会替换文号相关的内容
    function reGetDocumentNumber(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        data.areaGroupId = areaId;
        data.symbolOperation = 'reset';//重新拿号 标志
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        AlertConfirm("是否确认", "请注意报告二维码无法自动替换,假如要替换类似于报告二维码这样的数据请在页面上上删除报告二维码,然后用\\${报告二维码}这样的文本放置在删除的位置", function () {
            generateReportHandle(data, reportType, form, areaId, function () {
                notifySuccess("成功", '重新拿号成功!');
            });
        })

    }

    //拿号 并替换文号
    function getReportNumber(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        data.areaGroupId = areaId;
        data.symbolOperation = 'get';//拿号 标志
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        generateReportHandle(data, reportType, form, areaId, function () {
            notifySuccess("成功", '拿号成功');
        });
    }


    //生成报告
    function generateReport(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        if (!form.valid()) {
            return false;
        }
        if (data.realEstateAppraiser) {

        } else {
            notifyInfo('提示', '估价师必须选择');
            return false;
        }
        data.areaGroupId = areaId;
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        data.symbolOperation = 'none';//不拿号 标志
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        generateReportHandle(data, reportType, form, areaId, function () {
            notifySuccess("成功", '报告生成成功');
        });
    }

    //报告替换 method
    function generateReportHandle(data, ids, form, areaId, callback) {
        objGenerate.ajaxServerMethod({
            ids: ids,
            fomData: JSON.stringify(data)
        }, '/generateReport/generate', "post", function () {
            getSchemeReportGeneration(data, function (info) {
                initFormSchemeReportGeneration(info, form, areaId);
                if (callback) {
                    callback();
                }
            });
        });
    }

    /**
     * 报告信息 修改或者添加
     * @param data
     * @param callback
     */
    function saveGenerateReportInfo(data, callback) {
        objGenerate.ajaxServerFun({fomData: JSON.stringify(data)}, '/generateReport/saveGenerateReportInfo', "post", callback);
    }

    //提交
    function submit() {
        reportGroupObj.getValidData('${projectInfo.id}' ,function (validData) {
            if (validData.length != 0){
                var message = validData.join("\n\r") ;
                AlertSuccess("提示",message );
                return false ;
            }
            var allData = [];
            $(".area_panel").each(function () {
                var form = $(this).find('form');
                allData.push(formSerializeArray(form));
            });
            saveGenerateReportInfo(allData, function () {
                var data = {};
                data.planId = '${projectPlan.id}';
                data.areaGroupId = $("#areaGroupId").val();
                if ("${processInsId}" != "0") {
                    submitEditToServer(JSON.stringify(data));
                } else {
                    submitToServer(JSON.stringify(data));
                }
            });
        }) ;
    }

    //提交
    function commitApply() {
        reportGroupObj.getValidData('${projectInfo.id}' ,function (validData) {
            if (validData.length != 0){
                var message = validData.join("\n\r") ;
                AlertSuccess("提示",message );
                return false ;
            }
            var allData = [];
            var isPass = true;
            $(".area_panel").each(function () {
                $(this).find('.x_content').show();
                var form = $(this).find('form');
                isPass = form.valid();
                allData.push(formSerializeArray(form));
            });
            if (!isPass) {
                return false;
            }
            var data = {};
            data.planId = '${projectPlan.id}';
            data.areaGroupId = $("#areaGroupId").val();
            var url = "${pageContext.request.contextPath}/generate/submitApply";
            if ("${empty processInsId?"0":processInsId}" != "0") {
                url = "${pageContext.request.contextPath}/generate/submitEditApproval";
                var approvalData = formParams("frm_approval");
                data = $.extend(data, approvalData);
            }
            saveGenerateReportInfo(allData, function () {
                //提交流程
                $.ajax({
                    url: url,
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            AlertSuccess("成功", "提交成功", function () {
                                window.close();
                            });
                        } else {
                            AlertError(result.errmsg);
                        }
                    }
                })
            });
        }) ;


    }

</script>
