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
            <form id="frm_content" class="form-horizontal">
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
                                    <label id="lbl_report_count" class="col-sm-1 control-label" style="display: none;">
                                        报告份数：<span style="color: red;font-size: large;">0</span>
                                    </label>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告下载<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4" id="reportDownloadWord">

                                    </div>
                                </div>
                            </div>
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
                                    <form id="frmJudgeObject${item.id}" class="form-horizontal">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    评估基准日<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" name="valueTimePoint" placeholder="评估基准日"
                                                           class="form-control"
                                                           readonly="readonly" pattern='yyyy-MM-dd'
                                                           value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}" pattern="yyyy-MM-dd"/>">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    基准日说明<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" name="timePointExplain" readonly="readonly"
                                                           class="form-control"
                                                           value="${item.timePointExplain}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    生成报告
                                                </label>
                                                <div class="col-sm-3">
                                                    <a class="btn-primary btn active"
                                                       onclick="generateReport('${item.id}')">生成报告<i
                                                            class="fa fa-file-word-o"></i></a>
                                                </div>
                                            </div>
                                        </div>
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
                                    </form>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="x_panel">

                            <div class="x_title collapse-link">
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                </ul>
                                <h2>
                                    <small>权证信息</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>


                            <div class="x_content">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <td>
                                        </td>
                                        <th>序号</th>
                                        <th>名称</th>
                                        <th>证载面积</th>
                                        <th>评估面积</th>
                                        <th>已出面积</th>
                                        <th>报告面积</th>
                                        <th>报告附件</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${reportRecordList}" var="item" varStatus="i">
                                        <tr>
                                            <td></td>
                                            <th>${i.index+1}</th>
                                            <td>${item.name}</td>
                                            <td>${item.floorArea}</td>
                                            <td>${item.assessArea}</td>
                                            <td>${item.alreadyOutArea}</td>
                                            <td>
                                                <div class="x-valid">
                                                    <input type="text" class="form-control" name="reportArea"
                                                           data-rule-digits="true"
                                                           style="width: 120px;height: 30px;"></div>
                                            </td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
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
    function loadJudgeObjectList(_this) {
        var tbody = $(_this).closest(".area_panel").find(".table").find("tbody");
        tbody.empty();
        var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
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
    function generateReport(areaId) {
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
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/generate",
            data: {
                ids: ids,
                projectPlanId: '${projectPlan.id}',
                areaId: areaId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    AssessCommon.getSysAttachmentViewHtml(result.data, function (data) {
                        $("#reportDownloadWord").empty();
                        $("#reportDownloadWord").append(data);
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