<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<script type="text/html" id="taskLiquidationAnalysisDiv">
    <div class="x_panel">
        <div class="x_title">
            <h3>变现分析税费（0{index}）
            </h3>
        </div>
        <div class="x_content">
            <form class="form-horizontal" id="taskLiquidationAnalysisFrm_number">
                <input type="hidden" name="id">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权证信息<span
                                class="symbol required"></span></label>
                        <div class=" col-xs-560  col-sm-560  col-md-560  col-lg-560 "
                             style="overflow:auto;height:60px;">
                            <%--<select class="form-control search-select select2" multiple="multiple" required="required"--%>
                            <%--name="recordIds" onchange="getGroupAndPriceVo(this);">--%>
                            <%--<c:forEach var="items" items="${declareRecordList}">--%>
                            <%--<option value="${items.id}">${items.name}</option>--%>
                            <%--</c:forEach>--%>
                            <%--</select>--%>
                            <label class="form-control" name="recordNames"></label>
                        </div>
                    </div>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th class="hidden-xs">物业类型</th>
                        <th class="hidden-xs">税率</th>

                        <th class="hidden-xs">计算基数</th>
                        <th class="hidden-xs">计算公式</th>
                        <th class="hidden-xs">税费负担方</th>

                        <th class="hidden-xs">备注</th>
                        <th class="hidden-xs">单位（面积/m² 金额/元）</th>
                        <th class="hidden-xs">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="hidden-xs">面积(平方米)</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs" name="evaluationArea">0
                        </td>
                    </tr>
                    <tr>
                        <td class="hidden-xs">评估价(元)</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs">/</td>
                        <td class="hidden-xs" name="evaluationPrice">0
                        </td>
                    </tr>
                    </tbody>
                    <tbody name="tbody_data_section">

                    </tbody>
                    <tbody>
                    <tr>
                        <td class='hidden-xs' colspan='6' style='text-align:center;'>合计费用</td>
                        <td class='hidden-xs' name="total">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>

    </div>
</script>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>变现分析</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="master">
                        <input type="hidden" name="id" value="${master.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">变现比率</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${master.liquidRatios}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">变现时间</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${master.liquidTime}</label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div id="taskLiquidationAnalysisAppend">

            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var commonFieldApproval = {
        taskLiquidationAnalysisFrm: "taskLiquidationAnalysisFrm",
        taskLiquidationAnalysisAppend: "taskLiquidationAnalysisAppend",
        taskLiquidationAnalysisDiv: "taskLiquidationAnalysisDiv",

    };

    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        appendHtml(true);
    });


    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    function appendHtml(flag) {
        if (flag) {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeLiquidationAnalysisGroupList",
                type: "get",
                dataType: "json",
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#" + commonFieldApproval.taskLiquidationAnalysisDiv).html();
                                var number = item.id;
                                html = html.replace(/_number/g, number).replace(/{index}/g, i + 1);
                                $("#" + commonFieldApproval.taskLiquidationAnalysisAppend).append(html);

                                $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).initForm(item);

                                if (item.recordNames) {
                                    $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).find('[name=recordNames]').text(item.recordNames);
                                }
                                getGroupAndPrice(item.recordIds, commonFieldApproval.taskLiquidationAnalysisFrm + number);
                                getAnalysisItemList(number);
                                setTimeout(function () {
                                    if (item.total) {
                                        $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).find('[name=total]').text(fmoney(Number(item.total).toFixed(2), 2));
                                    }
                                    ;
                                }, 500);


                            });
                        } else {
                            flag = false;
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    function getGroupAndPrice(recordIds, frmId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getGroupAndPrice",
            type: "post",
            dataType: "json",
            data: {recordIds: recordIds},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#" + frmId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                        $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });

    }

    function getAnalysisItemList(groupId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getAnalysisItemListByGroupId",
            data: {
                groupId: groupId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += item.taxRateName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        if (item.calculationMethod == 0) {
                            html += Number(item.taxRateValue).toFixed(2);
                        } else {
                            html += Number(item.taxRateValue * 100).toFixed(2) + "%";
                        }
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += AssessCommon.toString(item.calculateBase);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += AssessCommon.toString(item.calculationFormula);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += AssessCommon.toString(item.taxesBurden);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += AssessCommon.toString(item.remark);
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<div class='x-valid'>";
                        html += fmoney(item.price, 2);
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                    });

                    $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").append(html);

                }
            }
        });
    }

    //格式化金额
    function fmoney(s, n) {
        if (!AssessCommon.isNumber(s)) return s;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r.substring(0, 2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
    }
</script>
</body>
</html>

