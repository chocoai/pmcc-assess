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
            <c:forEach items="${schemeAreaGroupList}" var="areaGroup">
                <div class="x_panel area_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h3>${areaGroup.areaName}</h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form class="form-horizontal" id="groupForm${areaGroup.id}">
                            <input type="hidden" name="areaGroupId" value="${areaGroup.id}">
                            <input type="hidden" name="id">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告出具日期<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                               class="form-control date-picker dbdate" pattern='yyyy-MM-dd'
                                               data-date-format="yyyy-mm-dd" required>
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
                                               pattern='yyyy-MM-dd' required>
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
                                                        <option value="${itemA.key}">${itemA.value}</option>
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
                                               value="<fmt:formatDate value='${schemeReportGeneration.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
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
                                               value="<fmt:formatDate value='${schemeReportGeneration.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
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
                            <!-- 报告下载 -->
                            <div class="form-group">
                                <c:forEach items="${reportTypeList}" var="reportType">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            <a class="btn-dark btn btn-xs"
                                               onclick="generateReport('${areaGroup.id}','${reportType.id}',this)">生成${reportType.name}
                                                <i class="fa fa-file-word-o"></i></a>
                                        </label>
                                        <div class="col-sm-3">
                                            <div id="_reporttype${reportType.remark}${areaGroup.id}"></div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </form>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        getSchemeReportGeneration({
                            projectPlanId: '${projectPlan.id}',
                            areaGroupId: '${areaGroup.id}'
                        }, function (info) {
                            initFormSchemeReportGeneration(info, $('#groupForm${areaGroup.id}'), '${areaGroup.id}');
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
<script type="text/javascript">

    //报告附件 数组
    var schemeReportGenerationFileControlIdArray = ["${REPORT_TYPE_PREAUDIT}", "${REPORT_TYPE_TECHNOLOGY}", "${REPORT_TYPE_RESULT}"];

    function fileShow(fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportGeneration,
                tableId: id == undefined ? 0 : id
            },
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
            $(frm).initForm(info);
            if (info.investigationsStartDate) {
                $(frm).find("input[name='investigationsStartDate']").val(formatDate(info.investigationsStartDate));
            }
            if (info.investigationsEndDate) {
                $(frm).find("input[name='investigationsEndDate']").val(formatDate(info.investigationsEndDate));
            }
            $(frm).find("input[name='reportIssuanceDate']").val(formatDate(info.reportIssuanceDate));
            $(frm).find("input[name='homeWorkEndTime']").val(formatDate(info.homeWorkEndTime));
            $(frm).find("select[name='qualificationType']").val(info.qualificationType).attr("selected", true);
            $(frm).find("input[name='id']").val(info.id);
            $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                fileShow(n + "" + areaGroupId, true, info.id);
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