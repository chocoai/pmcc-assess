<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                        <i class="fa ${boxprocessIcon}" style="margin-right: 20px;"></i>
                        ${boxCnName}
                        <small>
                            <label>${boxdescription}</label>
                            <label class="label label-success"><i class="fa fa-flag"
                                                                  style="margin-right: 8px"></i>出具报告</label>
                            <label class="label label-primary"><i class="fa fa-user"
                                                                  style="margin-right: 8px"></i>${currUserName}</label>
                        </small>
                    </h3>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <!--填写表单-->
            <c:forEach items="${generationVos}" var="generationVo">
                <div class="x_panel area_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h3>${generationVo.areaGroupName}</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form class="form-horizontal" id="groupForm${generationVo.areaGroupId}" enctype="multipart/form-data">
                            <input type="hidden" id="areaGroupId" name="areaGroupId"
                                   value="${generationVo.areaGroupId}">
                            <input type="hidden" name="id" value="${generationVo.id}">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告出具日期<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                               class="form-control date-picker dbdate" pattern='yyyy-MM-dd'
                                               data-date-format="yyyy-mm-dd" required
                                               value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        作业结束时间<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                               class="form-control date-picker dbdate"
                                               data-date-format="yyyy-mm-dd"
                                               pattern='yyyy-MM-dd' required
                                               value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            资质类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select class="form-control" name="qualificationType"
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
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        现场查勘开始日期<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" required name="investigationsStartDate"
                                               placeholder="现场查勘开始日期"
                                               class="form-control date-picker dbdate"
                                               data-date-format="yyyy-mm-dd"
                                               pattern='yyyy-MM-dd'
                                               value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        现场查勘结束日期<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" required name="investigationsEndDate" placeholder="现场查勘结束日期"
                                               class="form-control date-picker dbdate"
                                               data-date-format="yyyy-mm-dd"
                                               pattern='yyyy-MM-dd'
                                               value="<fmt:formatDate value='${generationVo.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">估价师<span
                                            class="symbol required"></span></label>
                                    <div class="col-sm-3">
                                        <select name="realEstateAppraiser" multiple="multiple"
                                                class="form-control search-select select2"
                                                required="required">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">评估类型(增加封面)</label>
                                    <div class="col-sm-3">
                                        <select name="assessCategory" class="form-control search-select select2">
                                            <option>请选择</option>
                                            <c:forEach items="${projectCategoryKeyValueDtoList}" var="projectCategoryKey">
                                                <option value="${projectCategoryKey.key}">${projectCategoryKey.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class="col-sm-1">
                                            <a class="btn-primary btn "
                                               onclick="reGetDocumentNumber('${projectPlan.projectId}','${generationVo.areaGroupId}','${reportType.id}')">重新拿号<i class="fa fa-undo"></i></a>
                                        </div>
                                        <label class="col-sm-1">
                                            <a class="btn-dark btn "
                                               onclick="generateReport('${generationVo.areaGroupId}','${reportType.id}',this)">生成${reportType.name}
                                                <i class="fa fa-file-word-o"></i></a>
                                        </label>
                                        <div class="col-sm-3">
                                            <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
                                        </div>

                                        <div class="col-sm-3">
                                            <c:if test="${generationVo != null}">
                                                <c:if test="${generationVo.id != null}">
                                                    <input  id="GGGGGG${reportType.fieldName}${generationVo.areaGroupId}" name="file" type="file" style="display: none"  onchange="upFileLoadReport(this,'${reportType.fieldName}${generationVo.areaGroupId}' ,'${generationVo.id}' ,'${generationVo.areaGroupId}' )">
                                                    <div class="btn btn-primary" onclick="$(this).prev().trigger('click')">上传报告</div>
                                                </c:if>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </form>
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
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="commitApply();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=1.0"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">

    //上传报告 临时添加zch
    function upFileLoadReport(that,fileId,id , areaGroupId) {
        var fileElementId = $(that).attr("id") ;
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
                    initFormSchemeReportGeneration({id:id} ,$(that).closest('form') ,areaGroupId) ;
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
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
        dataQualificationShow(v.val(), null, frm);
    }

    /**
     * 资质显示
     * @param type
     * @param realEstateAppraiser
     * @param frm
     */
    function dataQualificationShow(type, realEstateAppraiser, frm) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataQualification/findDataQualificationList",
            data: {type: type},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    var retHtml = '';
                    $.each(result.data, function (i, item) {
                        retHtml += '<option key="' + item.qualificationTypeName + '" title="' + item.userAccountName + '" value="' + item.id + '"';
                        if (realEstateAppraiser) {
                            var tempArr = realEstateAppraiser.split(",");
                            $.each(tempArr, function (i, n) {
                                if (item.id == n) {
                                    retHtml += 'selected="selected"';
                                }
                            });
                        }
                        retHtml += '>' + item.userAccountName + '</option>';
                    });
                    if (type) {
                        $(frm).find("select[name='realEstateAppraiser']").empty().html(retHtml).trigger('change');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }


    //赋值
    function initFormSchemeReportGeneration(info, frm, areaGroupId) {
        if (info) {
            getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                    fileShow(n + "" + areaGroupId, true, info.id);
                });
            });
            dataQualificationShow(info.qualificationType, info.realEstateAppraiser, frm);
        }
    }

    function getSchemeReportGeneration(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/getGenerateReportGeneration",
            data: data,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //重新拿号
    function reGetDocumentNumber(projectId, areaId, reportType) {
        $.ajax({
            url: "${pageContext.request.contextPath}/generate/reGetDocumentNumber",
            data: {
                projectId: projectId,
                areaId: areaId,
                reportType: reportType
            },
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    Alert("重新拿号成功");
                }else{
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
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
            toastr.success('估价师必须选择');
            return false;
        }
        data.ids = reportType;
        data.areaGroupId = areaId;
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)){
            data.assessCategory = null;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/generate",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    getSchemeReportGeneration(data, function (info) {
                        initFormSchemeReportGeneration(info, form, areaId);
                        Loading.progressHide();
                        toastr.success('报告生成成功!');
                    });
                } else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                console.log(result);
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //提交
    function commitApply() {
        var isPass = true;
        $(".area_panel").each(function () {
            $(this).find('.x_content').show();
            isPass = $(this).find('form').valid();
        })
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
        //提交流程
        $.ajax({
            url: url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    Alert('提交成功', 1, null, function () {
                        closeWindow();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

</script>