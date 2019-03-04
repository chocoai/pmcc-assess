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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>报告生成</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="panel-body">
                        <form id="frm_content" class="form-horizontal">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <c:forEach items="${reportTypeList}" var="item">
                                            <span class="checkbox-inline">
                                                <input type="checkbox" id="reportType${item.id}" name="reportType"
                                                       value="${item.id}">
                                                <label for="reportType${item.id}">${item.name}</label>
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <c:forEach items="${schemeAreaGroupList}" var="item">
                        <div class="x_panel area_panel">
                            <div class="x_title collapse-link" onclick="loadJudgeObjectList(this);">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                </ul>
                                <h3>
                                    <label>${item.areaName}</label>
                                </h3>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content collapse">
                                <form class="form-horizontal" id="groupForm${item.id}">
                                    <input type="hidden" name="areaGroupId" value="${item.id}">
                                    <input type="hidden" name="id">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                报告出具日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                                       class="form-control date-picker dbdate" pattern='yyyy-MM-dd'
                                                       data-date-format="yyyy-mm-dd">
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
                                                       pattern='yyyy-MM-dd'>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <div>
                                                <label class="col-sm-1 control-label">
                                                    资质类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <select class="form-control" name="qualificationType"
                                                            onchange="onChange(this)">
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
                                                <input type="text" name="investigationsStartDate" placeholder="现场查勘开始日期"
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
                                                <input type="text" name="investigationsEndDate" placeholder="现场查勘结束日期"
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
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                预评报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttypepreaudit${item.id}"></div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                技术报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttypetechnology${item.id}"></div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                结果报告
                                            </label>
                                            <div class="col-sm-3">
                                                <div id="_reporttyperesult${item.id}"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                生成报告
                                            </label>
                                            <div class="col-sm-3">
                                                <a class="btn-primary btn active"
                                                   onclick="generateReport('${item.id}',this)">生成报告<i
                                                        class="fa fa-file-word-o"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="x_content collapse">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 5%">编号</th>
                                        <th style="width: 5%">所有权人</th>
                                        <th style="width: 25%">坐落</th>
                                        <th style="width: 10%">证载用途</th>
                                        <th style="width: 10%">实际用途</th>
                                        <th style="width: 10%">设定用途</th>
                                        <th style="width: 10%">最佳利用描述</th>
                                        <th style="width: 10%">证载面积</th>
                                        <th style="width: 10%">评估面积</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
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
<!--评估对象-->
<script type="text/html" id="judgeObjectHtml">
    <tr>
        <td>
            <input type="hidden" data-name="id" value="{id}">
            <input type="hidden" data-name="bisSplit" value="{bisSplit}">
            <input type="hidden" data-name="bisMerge" value="{bisMerge}">
            <input type="hidden" data-name="number" value="{number}">
            <input type="hidden" data-name="splitNumber" value="{splitNumber}">
            <input type="hidden" data-name="declareId" value="{declareId}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <td><label class="form-control" data-name="ownership">{ownership}</label></td>
        <td><label class="form-control" data-name="seat">{seat}</label></td>
        <td><label class="form-control" data-name="certUse">{certUse}</label></td>
        <td><label class="form-control" data-name="practicalUse">{practicalUse}</label></td>
        <td><label class="form-control" data-name="setUse">{setUse}</label></td>
        <td><label class="form-control" data-name="bestUse">{bestUse}</label></td>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" readonly="readonly"
                       name="evaluationArea{id}" data-name="evaluationArea"
                       placeholder="评估面积" value="{evaluationArea}">
            </div>
        </td>
        <td>
        </td>
    </tr>
</script>
<script type="text/javascript">

    //报告附件 数组
    var schemeReportGenerationFileControlIdArray = ["reporttypepreaudit", "reporttypetechnology", "reporttyperesult"];

    function fileShow(fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
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
        dataQualificationShow(v.val(), null, frm.attr("id"));
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
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        retHtml += '<option key="' + item.qualificationTypeName + '" title="' + item.userAccountName + '" value="' + item.id + '"';
                        if (item.id == realEstateAppraiser) {
                            retHtml += 'selected="selected"';
                        }
                        retHtml += '>' + item.userAccountName + '</option>';
                    });
                    if (type) {
                        $("#" + frm).find("select[name='realEstateAppraiser']").empty().html(retHtml).trigger('change');
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
        $(frm).initForm(info);
        if (info.investigationsStartDate) {
            $("#" + frm).find("input[name='investigationsStartDate']").val(formatDate(info.investigationsStartDate));
        }
        if (info.investigationsEndDate) {
            $("#" + frm).find("input[name='investigationsEndDate']").val(formatDate(info.investigationsEndDate));
        }
        $("#" + frm).find("input[name='reportIssuanceDate']").val(formatDate(info.reportIssuanceDate));
        $("#" + frm).find("input[name='homeWorkEndTime']").val(formatDate(info.homeWorkEndTime));
        $("#" + frm).find("select[name='qualificationType']").val(info.qualificationType).attr("selected",true);
        $("#" + frm).find("input[name='id']").val(info.id);
        $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
            fileShow(n + "" + areaGroupId, false, info.id);
        });
        dataQualificationShow(info.qualificationType, info.realEstateAppraiser, frm);
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
                } else {
                    callback({});
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    /**
     * show 委估对象
     * @param _this
     */
    function loadJudgeObjectList(_this) {
        var tbody = $(_this).closest(".area_panel").find(".table").find("tbody");
        tbody.empty();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
        var formId = $(_this).parent().find("form").eq(0).attr("id");
        getSchemeReportGeneration({projectPlanId: '${projectPlan.id}', areaGroupId: areaGroupId}, function (info) {
            initFormSchemeReportGeneration(info, formId, areaGroupId);
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeObjectList",
            data: {
                areaGroupId: areaGroupId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    $.each(result.data, function (i, item) {
                        var html = $("#judgeObjectHtml").html();
                        html = html.replace(/{id}/g, item.id == undefined ? "" : item.id);
                        html = html.replace(/{bisSplit}/g, item.bisSplit == undefined ? false : item.bisSplit);
                        html = html.replace(/{bisMerge}/g, item.bisMerge == undefined ? false : item.bisMerge);
                        html = html.replace(/{number}/g, item.number == undefined ? "" : item.number);
                        html = html.replace(/{splitNumber}/g, item.splitNumber == undefined ? "" : item.splitNumber);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace(/{name}/g, item.name == undefined ? "" : item.name);
                        html = html.replace(/{declareId}/g, item.declareRecordId == undefined ? "" : item.declareRecordId);
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : item.ownership);
                        html = html.replace(/{seat}/g, item.seat == undefined ? "" : item.seat);
                        html = html.replace(/{certUse}/g, item.certUse == undefined ? "" : item.certUse);
                        html = html.replace(/{practicalUse}/g, item.practicalUse == undefined ? "" : item.practicalUse);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);

                        html = html.replace(/{setUse}/g, item.setUse == undefined ? "" : item.setUse);
                        html = html.replace(/{bestUse}/g, item.bestUse == undefined ? "" : item.bestUse);
                        tbody.append(html);
                    })
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }


    //生成报告
    function generateReport(areaId, item) {
        var formId = $(item).closest("form").attr("id");
        var data = formParams(formId);
        if (data.realEstateAppraiser) {
        } else {
            toastr.success('估价师必须选择');
            return false;
        }
        var item = $("#frm_content").find("[name=reportType]:checked");
        var ids = '';
        if (item.size() < 1) {
            toastr.success('至少选择一项报告类型!');
            return false;
        }
        $.each(item, function (i, n) {
            if (i == item.length - 1) {
                ids += $(n).val();
            } else {
                ids += $(n).val() + ",";
            }
        });
        data.ids = ids;
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
                        initFormSchemeReportGeneration(info, formId, areaId);
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