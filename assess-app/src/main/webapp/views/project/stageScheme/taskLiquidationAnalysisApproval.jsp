<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<script type="text/html" id="taskLiquidationAnalysisDiv">


    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header collapse-link">
                <div class="card-head-row">
                    <div class="card-title">
                        变现分析税费（{index}）
                    </div>
                    <div class="card-tools">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="taskLiquidationAnalysisFrm_number">
                    <input type="hidden" name="id">

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">估价对象名称</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="name"
                                           placeholder="估价对象名称">
                                </div>

                                <label class="col-sm-1 control-label">权证名称</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="certName"
                                           placeholder="权证名称">
                                </div>

                                <label class="col-sm-1 control-label">所有权人</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="ownership"
                                           placeholder="所有权人">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">坐落</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="seat"
                                           placeholder="坐落">
                                </div>
                                <button class="btn btn-info btn-sm" type="button"
                                        onclick="searchLiquidationJudgeData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <table class="table table-bordered" id="schemeLiquidationJudgeList_number">
                            </table>
                        </div>
                    </div>
                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color="#6f5499" size="10"/>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="houseNumber"
                                           placeholder="房号">
                                </div>
                                <button class="btn btn-info btn-sm" style="margin-left: 10px" type="button"
                                        onclick="houseHuxingPrice.prototype.queryHuxingPriceList(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <table class="table table-bordered" id="houseHuxingPriceList_number">
                            </table>
                        </div>
                    </div>
                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color="#6f5499" size="10"/>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="hidden-xs">物业类型</th>
                                    <th class="hidden-xs">税率</th>

                                    <th class="hidden-xs">计算基数</th>
                                    <th class="hidden-xs">计算公式</th>
                                    <th class="hidden-xs">税费负担方</th>
                                    <th class="hidden-xs">比例</th>

                                    <th class="hidden-xs">备注</th>
                                    <th class="hidden-xs">单位（面积/m² 金额/元）</th>
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
                                    <td class="hidden-xs">/</td>
                                    <td class="hidden-xs" name="evaluationPrice">0
                                    </td>
                                </tr>
                                </tbody>
                                <tbody name="tbody_data_section">

                                </tbody>
                                <tbody>
                                <tr>
                                    <td class='hidden-xs' colspan='7' style='text-align:center;'>合计费用</td>
                                    <td class='hidden-xs' name="total"></td>
                                </tr>
                                <tr>
                                    <td class='hidden-xs' colspan='7' style='text-align:center;'>买方费用</td>
                                    <td class='hidden-xs' name="buyerTotal"></td>
                                </tr>
                                <tr>
                                    <td class='hidden-xs' colspan='7' style='text-align:center;'>卖方费用</td>
                                    <td class='hidden-xs' name="sellerTotal"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</script>
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
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${areaGroup.areaName}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="master" class="form-horizontal">
                                    <input type="hidden" name="id" value="${master.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">变现比率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.liquidRatios}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">变现时间</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.liquidTime}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">变现说明</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.remark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                                <div id="taskLiquidationAnalysisAppend"></div>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>

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
                                loadSchemeLiquidationJudgeTable(item.id, {groupId: item.id});
                                getGroupAndPrice(item.id, commonFieldApproval.taskLiquidationAnalysisFrm + number);
                                getAnalysisItemList(number);
                                houseHuxingPrice.prototype.loadListByGroupId(item.id, "");
                                setTimeout(function () {
                                    if (item.total) {
                                        $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).find('[name=total]').text(fmoney(Number(item.total).toFixed(2), 2));
                                        $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).find('[name=buyerTotal]').text(fmoney(Number(item.buyerTotal).toFixed(2), 2));
                                        $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + number).find('[name=sellerTotal]').text(fmoney(Number(item.sellerTotal).toFixed(2), 2));
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    function getGroupAndPrice(groupId, frmId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getGroupAndPrice",
            type: "post",
            dataType: "json",
            data: {groupId: groupId},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#" + frmId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                        $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                    }
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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

                        if (item.taxesBurden == '双方承担') {
                            var s = '';
                            if (item.buyerScale) {
                                s += '买方承担' + AssessCommon.pointToPercent(item.buyerScale) + ";";
                            }
                            if (item.sellerScale) {
                                s += '卖方承担' + AssessCommon.pointToPercent(item.sellerScale);
                            }
                            html += "<td class='hidden-xs'>";
                            html += s;
                            html += "</td>";
                        } else {
                            html += "<td class='hidden-xs'>";
                            html += "100%";
                            html += "</td>";
                        }
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

    //已选择估价对象
    function loadSchemeLiquidationJudgeTable(groupId, options) {
        var cols = [];
        cols.push({field: 'name', title: '估价对象名称', width: "22%"});
        cols.push({field: 'certName', title: '权证名称', width: "22%"});
        cols.push({field: 'ownership', title: '所有权人', width: "22%"});
        cols.push({field: 'seat', title: '坐落', width: "22%"});
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        };
        $("#schemeLiquidationJudgeList" + groupId).bootstrapTable('destroy');
        TableInit("schemeLiquidationJudgeList" + groupId, "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeLiquidationJudgeList", cols, options, method);
    };

    //查询已选择估价对象
    function searchLiquidationJudgeData(_this) {
        var group = $(_this).closest(".form-horizontal");
        var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
        var name = group.find("[name='name']").val();
        var certName = group.find("[name='certName']").val();
        var ownership = group.find("[name='ownership']").val();
        var seat = group.find("[name='seat']").val();
        var data = {areaGroupId: '${areaGroup.id}'};
        var data = {groupId: groupId};
        if (name) {
            data.name = name;
        }
        if (certName) {
            data.certName = certName;
        }
        if (ownership) {
            data.ownership = ownership;
        }
        if (seat) {
            data.seat = seat;
        }

        loadSchemeLiquidationJudgeTable(groupId, data);
    };
</script>
<script type="application/javascript">
    houseHuxingPrice = function () {
    };
    houseHuxingPrice.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseHuxingPriceList";
            data.tableBox = "divBoxHouseHuxingPriceTable";
            data.tableFrm = "frmHouseHuxingPriceTable";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadListByGroupId: function (groupId, houseNumber) {
            var houseId = $("#master").find("input[name='houseId']").val();
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'price', title: '价格'});
            $("#houseHuxingPriceList" + groupId).bootstrapTable('destroy');
            TableInit("houseHuxingPriceList" + groupId, getContextPath() + "/schemeLiquidationAnalysis/getHuxingPricesByGroupId", cols, {
                groupId: groupId,
                houseNumber: houseNumber
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            }, true);
        },
        queryHuxingPriceList:function(_this){
            var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
            var houseNumber = $("#" + commonFieldApproval.taskLiquidationAnalysisFrm + groupId).find('[name=houseNumber]').val();
            houseHuxingPrice.prototype.loadListByGroupId(groupId, houseNumber);

        },
    }
</script>
</body>
</html>

