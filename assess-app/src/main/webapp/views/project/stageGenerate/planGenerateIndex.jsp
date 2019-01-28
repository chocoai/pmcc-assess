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
                    <h2>报告选择</h2>
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
                                                <input type="checkbox" name="reportType" value="${item.id}">
                                                <label>${item.name}</label>
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <c:forEach items="${schemeAreaGroupList}" var="item">
                        <div class="x_panel area_panel">
                            <input type="hidden" name="areaGroupId" value="${item.id}">
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
                                            <label class="col-sm-1 control-label">估价师选择</label>
                                            <div class="col-sm-3">
                                                <div class="input-group">
                                                    <input type="hidden" name="realEstateAppraiser">
                                                    <input type="text" class="form-control" readonly="readonly"
                                                           name="realEstateAppraiserName"
                                                           onclick="selectUserAccount(this);">
                                                    <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择"
                                                    onclick="selectUserAccount(this);">
                                            <i class="fa fa-search"></i>
                                            </button>
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="$(this).closest('.input-group').find('input').val('');"
                                                    data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                                            </span>
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
                                                       pattern='yyyy-MM-dd'>
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
                                                       pattern='yyyy-MM-dd'>
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
                                        <th style="width: 15%">坐落</th>
                                        <th style="width: 10%">证载用途</th>
                                        <th style="width: 10%">实际用途</th>
                                        <th style="width: 10%">设定用途</th>
                                        <th style="width: 10%">最佳利用描述</th>
                                        <th style="width: 5%">证载面积</th>
                                        <th style="width: 5%">评估面积</th>
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
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
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
    var schemeReportGenerationFileControlIdArray = ["reporttypepreaudit","reporttypetechnology","reporttyperesult"] ;

    function fileShow(fieldsName, deleteFlag,id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.SchemeReportGeneration,
                tableId: id == undefined ? 0 : id
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    /**
     * 获取资质
     * @param userAccount
     * @param callback
     */
    function getAdPersonalIdentityDto(userAccount, callback) {
        var qualificationType = "${PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS}";
        if ('${projectInfo.projectCategoryName}' == '房产') {
            qualificationType = '${PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS}';
        }
        var data = {
            userAccount: userAccount,
            qualificationType: qualificationType
        };
        $.ajax({
            url: "${pageContext.request.contextPath}/public/getAdPersonalIdentityDto",
            data: data,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                } else {
                    Alert("异常");
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function getSchemeReportGeneration(data,callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/getSchemeReportGeneration",
            data: data,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                } else {
                    Alert("异常");
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    /**
     * 人选选择
     * @param this_
     */
    function selectUserAccount(this_) {
        erpEmployee.select({
            multi: true,
            onSelected: function (data) {
                getAdPersonalIdentityDto(data.account, function (item) {
                    if (item.length >= 1) {
                        $(this_).closest('.input-group').find("input[name='realEstateAppraiser']").val(data.account);
                        $(this_).closest('.input-group').find("input[name='realEstateAppraiserName']").val(data.name);
                    } else {
                        Alert("该人员未有《注册房地产估价师》资格!");
                    }
                });
            }
        });
    }

    function loadJudgeObjectList(_this) {
        var tbody = $(_this).closest(".area_panel").find(".table").find("tbody");
        tbody.empty();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
        var formId = $(_this).parent().find("form").eq(0).attr("id") ;
        getSchemeReportGeneration({projectPlanId:'${projectPlan.id}',areaGroupId:areaGroupId},function (info) {
            $(formId).initForm(info);
            $("#" + formId).find("input[name='investigationsStartDate']").val(formatDate(info.investigationsStartDate));
            $("#" + formId).find("input[name='investigationsEndDate']").val(formatDate(info.investigationsEndDate));
            $("#" + formId).find("input[name='reportIssuanceDate']").val(formatDate(info.reportIssuanceDate));
            $("#" + formId).find("input[name='homeWorkEndTime']").val(formatDate(info.homeWorkEndTime));
            $.each(schemeReportGenerationFileControlIdArray,function (i,n) {
                fileShow(n+""+areaGroupId,false,info.id);
            });
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
                        //设值
                        var lastTr = tbody.find("tr:last");
                        lastTr.find('td:last').find(item.bisSplit ? '.judge-split' : '.judge-remove').remove();
                        lastTr.find('td:last').find(item.bisMerge ? '.judge-merge' : '.judge-merge-cancel').remove();
                        if (item.bisSetFunction) {
                            lastTr.find('td:last').find('.judge-method').removeClass('btn-success').addClass('btn-primary');
                        }
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
        var formId = $(item).closest("form").attr("id") ;
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
                if (result.ret) {
                    getSchemeReportGeneration(data,function (info) {
                        $(formId).initForm(info);
                        $("#" + formId).find("input[name='investigationsStartDate']").val(formatDate(info.investigationsStartDate));
                        $("#" + formId).find("input[name='investigationsEndDate']").val(formatDate(info.investigationsEndDate));
                        $("#" + formId).find("input[name='reportIssuanceDate']").val(formatDate(info.reportIssuanceDate));
                        $("#" + formId).find("input[name='homeWorkEndTime']").val(formatDate(info.homeWorkEndTime));
                        $.each(schemeReportGenerationFileControlIdArray,function (i,n) {
                            fileShow(n+""+areaId,false,info.id);
                        });
                        Loading.progressHide();
                        toastr.success('报告生成成功!');
                    });
                } else {
                    Alert("异常");
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //提交
    function commitApply() {
        if (!$("#frm_content").valid()) {
            return false;
        }
    }

</script>