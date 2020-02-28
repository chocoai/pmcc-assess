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
                        <small>
                            <button href="javascript://;" class="btn btn-sm btn-warning"
                               onclick="saveAnalysisGroup(true,'taskLiquidationAnalysisFrm_number');">保存</button>
                            <button href="javascript://;" class="btn btn-sm btn-warning" onclick="cleanHTMLData(this)">移除</button>
                        </small>
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
                            <label class="col-sm-1 control-label">估价对象信息<span
                                    class="symbol required"></span></label>
                            <div class="col-sm-5">

                                <input type="hidden" name="judgeObjectIds">
                                <button class="btn-primary btn btn-sm"
                                     onclick="schemeJudgeObj.init({callback:selectRecord,this_:this});">
                                    选择估价对象
                                    <span class="glyphicon  glyphicon-new-window" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">估价对象名称</label>
                            <div class="col-sm-2">
                                <input class="form-control input-full" type="text" name="name"
                                       placeholder="估价对象名称">
                            </div>
                      
                            <label class="col-sm-1 control-label">权证名称</label>
                            <div class="col-sm-2">
                                <input class="form-control input-full" type="text" name="certName"
                                       placeholder="权证名称">
                            </div>
                        
                            <label class="col-sm-1 control-label">所有权人</label>
                            <div class="col-sm-2">
                                <input class="form-control input-full" type="text" name="ownership"
                                       placeholder="所有权人">
                            </div>
                        
                            <div class="col-sm-2">
                                    <input class="form-control" type="text" name="seat"
                                           placeholder="坐落">
                            </div>
                            <button class="btn btn-info btn-sm"
                                    onclick="searchLiquidationJudgeData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索</button>
                        </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <input type="button" class="btn btn-sm btn-primary" value="批量删除"
                                   onclick="deleteJudgeItemByIds(this);">
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <table class="table table-bordered" id="schemeLiquidationJudgeList_number">
                            </table>
                        </div>
                    </div>
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
                            <td class='hidden-xs'>
                                <label class="form-control input-full" name="total"></label>
                            </td>
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

                                            <button href="javascript://;" class="btn btn-sm btn-success"
                                               onclick="appendHtml(false)">
                                                <span class="btn-label"><i
                                                    class="fa fa-plus"></i>
                                                </span>
                                                添加分组
                                            </button>

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
                                                    <input name="liquidRatios" class="form-control input-full x-percent"
                                                           required
                                                           placeholder="变现比率" value="${master.liquidRatios}"/>
                                                </div>
                                                <label class="col-sm-1 control-label">变现时间</label>
                                                <div class="col-sm-3">
                                                    <input name="liquidTime" class="form-control input-full" required
                                                           placeholder="例60-90天" value="${master.liquidTime}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="taskLiquidationAnalysisAppend"></div>
                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

<%--<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div>
                    <h3>
                        ${projectPlanDetails.projectPhaseName}
                        <small>${areaGroup.areaName}</small>
                        <small>
                            <a href="javascript://;" class="btn btn-xs btn-success" onclick="appendHtml(false)">添加分组<i
                                    class="fa fa-plus"></i>
                            </a>
                        </small>
                    </h3>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="master">
                        <input type="hidden" name="id" value="${master.id}">
                        <div class="row form-group">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">变现比率</label>
                                <div class="col-sm-3">
                                    <input name="liquidRatios" class="form-control input-full x-percent" required
                                           placeholder="变现比率" value="${master.liquidRatios}"/>
                                </div>
                            </div>
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">变现时间</label>
                                <div class="col-sm-3">
                                    <input name="liquidTime" class="form-control input-full" required
                                           placeholder="例60-90天" value="${master.liquidTime}"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div id="taskLiquidationAnalysisAppend"></div>
            <%@include file="/views/share/form_apply.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>--%>
</body>
<div id="boxSchemeJudgeObj" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">权证估价对象</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">估价对象名称</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="name"
                                                   placeholder="估价对象名称">
                                        </div>
                                        <label class="col-sm-1 control-label">权证名称</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="certName"
                                                   placeholder="权证名称">
                                        </div>
                                        <label class="col-sm-1 control-label">所有权人</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="ownership"
                                                   placeholder="所有权人">
                                        </div>
                                        <div class="col-sm-2">
                                                <input class="form-control input-full" type="text" name="seat"
                                                       placeholder="坐落">

                                        </div>

                                        <button class="btn btn-info btn-sm"
                                                onclick="schemeJudgeObj.searchData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索</button>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered" id="boxSchemeJudgeObjList">
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="schemeJudgeObj.select()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>


<%--
<div id="boxSchemeJudgeObj" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">权证估价对象</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="row form-group">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">估价对象名称</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="name"
                                                   placeholder="估价对象名称">
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">权证名称</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="certName"
                                                   placeholder="权证名称">
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">所有权人</label>
                                        <div class="col-sm-2">
                                            <input class="form-control input-full" type="text" name="ownership"
                                                   placeholder="所有权人">
                                        </div>
                                    </div>
                                    <div class="form-inline x-valid">
                                        <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <div class="input-group">
                                                <input class="form-control input-full" type="text" name="seat"
                                                       placeholder="坐落">
                                                <span class="input-group-addon"
                                                      onclick="schemeJudgeObj.searchData(this);">搜索<i
                                                        class="fa fa-search"></i> </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-bordered" id="boxSchemeJudgeObjList">
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="schemeJudgeObj.select()">
                    确定
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<%@include file="/views/project/tool/declareRecordModeView.jsp" %>
<script type="application/javascript">
    var commonField = {
        taskLiquidationAnalysisFrm: "taskLiquidationAnalysisFrm",
        taskLiquidationAnalysisAppend: "taskLiquidationAnalysisAppend",
        taskLiquidationAnalysisDiv: "taskLiquidationAnalysisDiv",

    };

    function selectRecord(_this, id) {
        var group = $(_this).closest(".form-group");
        group.find("input[name='judgeObjectIds']").val(id);
        getGroupAndPriceVo(_this);
    }


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
                                var html = $("#" + commonField.taskLiquidationAnalysisDiv).html();
                                var number = item.id;
                                html = html.replace(/_number/g, number).replace(/{index}/g, i + 1);
                                $("#" + commonField.taskLiquidationAnalysisAppend).append(html);

                                $("#" + commonField.taskLiquidationAnalysisFrm + number).initForm(item);
                                getGroupAndPrice(item.id, commonField.taskLiquidationAnalysisFrm + number);
                                loadSchemeLiquidationJudgeTable(item.id, {groupId: item.id});
                                getAnalysisItemList(number);
                                setTimeout(function () {
                                    if (item.total) {
                                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find('[name=total]').text(fmoney(Number(item.total).toFixed(2), 2));
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
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        if (!flag) {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/addLiquidationAnalysisGroup",
                type: "post",
                dataType: "json",
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    areaId: '${projectPlanDetails.areaId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        var html = $("#" + commonField.taskLiquidationAnalysisDiv).html();
                        var index = $("#" + commonField.taskLiquidationAnalysisAppend).find('.col-md-12').length;
                        var number = result.data.id;
                        html = html.replace(/_number/g, number).replace(/{index}/g, index + 1);
                        $("#" + commonField.taskLiquidationAnalysisAppend).append(html);
                        // $("#" + commonField.taskLiquidationAnalysisFrm + number).find("select[name='judgeObjectIds']").select2();
                        $("#" + commonField.taskLiquidationAnalysisFrm + number).initForm(result.data);
                        loadSchemeLiquidationJudgeTable(result.data.id, {groupId: result.data.id});
                        getAnalysisItemList(number);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        }

    }

    $(function () {
        appendHtml(true);
    });

    /**
     * 清除html
     * @param _this
     */
    function cleanHTMLData(_this) {
        var x_panel = $(_this).closest(".col-md-12");
        var form = x_panel.find("form").eq(0);
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/removeLiquidationAnalysisGroup",
                type: "post",
                dataType: "json",
                data: {id: form.find("input[name='id']").val()},
                success: function (result) {
                    if (result.ret) {
                        x_panel.remove();
                        notifySuccess("成功", "删除数据成功");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        });
    }

    /**
     * 添加税率明细
     * @param _this
     */
    function getGroupAndPriceVo(_this) {
        var group = $(_this).closest(".form-group");
        var judgeObjectIds = group.find("input[name='judgeObjectIds']").val();
        var frmId = $(_this).closest('.form-horizontal').attr("id");
        var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
        if (judgeObjectIds) {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getGroupAndPriceVo",
                type: "post",
                dataType: "json",
                data: {
                    judgeObjectIds: judgeObjectIds,
                    areaGroupId: '${areaGroup.id}',
                    groupId: groupId
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            notifySuccess("成功","成功");
                            initResult(frmId, result.data.groupArea, result.data.groupPrice);
                            $("#" + frmId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                            $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                            loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                        }
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
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
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });

    }

    //初始化计算结果
    function initResult(frmId, evaluationArea, evaluationPrice) {
        var salesTax = "${salesTax}";
        var total = 0;
        var evaluationArea = evaluationArea;
        var evaluationPrice = evaluationPrice;
        $("#" + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var $taxRateValue = $(this).find('[name^=taxRateValue]');
            var rate = $taxRateValue.val();
            var price = 0;
            if ($taxRateValue.hasClass('x-percent')) {
                rate = $taxRateValue.attr('data-value');
                var key = $taxRateValue.attr('data-key');
                switch (key) {
                    //增值税
                    case "data.tax.rate.allocation.sales.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * rate).toFixed(2);
                        }
                        break;
                    }
                    //城建税
                    case "data.tax.rate.allocation.construction.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                        }
                        break;
                    }
                    //教育税附加
                    case "data.tax.rate.allocation.education.fee.plus": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                        }
                        break;
                    }
                    //地方教育税附加
                    case "data.tax.rate.allocation.local.education.tax.additional": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                        }
                        break;
                    }
                    //印花税
                    case "data.tax.rate.allocation.stamp.duty": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //土地增值税
                    case "data.tax.rate.allocation.land.increment.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //其它税费
                    case "data.tax.rate.allocation.other.taxes.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //企业所得税
                    case "data.tax.rate.allocation.corporate.income.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //契税
                    case "data.tax.rate.allocation.deed.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                    //预计处置费用
                    case "data.tax.rate.allocation.disposal.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        break;
                    }
                }
            } else {
                if (rate && evaluationArea) {
                    price = Number(evaluationArea * rate).toFixed(2);
                }
            }
            total += Number(price);
            $(this).find('[name^=price]').val(fmoney(price, 2));
            $(this).find('[name^=price]').attr("data-value", price);
        })

        $("#" + frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));
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
                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        var textPrice = fmoney(item.price, 2);
                        if (!textPrice) {
                            textPrice = 0;
                        }
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += '<input type="hidden" name="id" value="' + item.id + '">';
                        html += item.taxRateName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        if (item.calculationMethod == 0) {
                            html += "<input type='text' required onblur='getThisPrice(this);' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue).toFixed(2) + "' class='form-control input-full' data-key='" + item.typeKey + "'>";
                        } else {
                            html += "<input type='text' required onblur='getThisPrice(this);' data-value='" + item.taxRateValue + "' name='taxRateValue_" + item.id + "' value='" + Number(item.taxRateValue * 100).toFixed(2) + "%' class='form-control input-full x-percent' data-key='" + item.typeKey + "'>";
                        }
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculateBase_" + item.id + "' value='" + AssessCommon.toString(item.calculateBase) + "' class='form-control input-full'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='calculationFormula_" + item.id + "' value='" + AssessCommon.toString(item.calculationFormula) + "' class='form-control input-full'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='taxesBurden_" + item.id + "' value='" + AssessCommon.toString(item.taxesBurden) + "' class='form-control input-full'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='remark_" + item.id + "' value='" + AssessCommon.toString(item.remark) + "' class='form-control input-full'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  data-key='price_" + item.typeKey + "' name='price_" + item.id + "' data-value='" + item.price + "' value='" + textPrice + "' class='form-control input-full' readonly>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanItemData(this," + item.id + ")'>" + "</span>";
                        html += "</td>";
                        html += "</tr>";
                    });

                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").append(html);

                }
            }
        });
    }

    function getThisPrice(_this) {
        var frmId = $(_this).closest('.form-horizontal').attr("id");
        //增值税率
        var salesTax = $("#" + frmId).find("input[data-key='data.tax.rate.allocation.sales.tax']").attr('data-value');
        //城建税率
        var constructionTax = $("#" + frmId).find("input[data-key='data.tax.rate.allocation.construction.tax']").attr('data-value');
        var constructionPrice = "price_data.tax.rate.allocation.construction.tax";
        //教育税附加率
        var educationTax = $("#" + frmId).find("input[data-key='data.tax.rate.allocation.education.fee.plus']").attr('data-value');
        var educationPrice = "price_data.tax.rate.allocation.education.fee.plus";
        //地方教育税附加率
        var landEducationTax = $("#" + frmId).find("input[data-key='data.tax.rate.allocation.local.education.tax.additional']").attr('data-value');
        var landEducationPrice = "price_data.tax.rate.allocation.local.education.tax.additional";
        //交易手续费
        var transactionCharges = $("#" + frmId).find("input[data-key='data.tax.rate.allocation.transaction.charges']");
        transactionCharges.attr("data-value", transactionCharges.val());

        var evaluationArea = $("#" + frmId).find("td[name='evaluationArea']").val();
        var evaluationPrice = $("#" + frmId).find("td[name='evaluationPrice']").val();
        var $taxRateValue = $(_this).parent().parent().find('[name^=taxRateValue]');
        var rate = $taxRateValue.val();
        var price = 0;
        if ($taxRateValue.hasClass('x-percent')) {
            rate = $taxRateValue.attr('data-value');
            var key = $taxRateValue.attr('data-key');
            switch (key) {
                //增值税
                case "data.tax.rate.allocation.sales.tax": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * rate).toFixed(2);
                    }
                    $("#" + frmId).find('input[data-key="' + constructionPrice + '"]').attr("data-value", (price * constructionTax).toFixed(2));
                    $("#" + frmId).find('input[data-key="' + educationPrice + '"]').attr("data-value", (price * educationTax).toFixed(2));
                    $("#" + frmId).find('input[data-key="' + landEducationPrice + '"]').attr("data-value", (price * landEducationTax).toFixed(2));
                    $("#" + frmId).find('input[data-key="' + constructionPrice + '"]').val(fmoney(price * constructionTax, 2));
                    $("#" + frmId).find('input[data-key="' + educationPrice + '"]').val(fmoney(price * educationTax, 2));
                    $("#" + frmId).find('input[data-key="' + landEducationPrice + '"]').val(fmoney(price * landEducationTax, 2));
                    break;
                }
                //城建税
                case "data.tax.rate.allocation.construction.tax": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                    }
                    break;
                }
                //教育税附加
                case "data.tax.rate.allocation.education.fee.plus": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                    }
                    break;
                }
                //地方教育税附加
                case "data.tax.rate.allocation.local.education.tax.additional": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                    }
                    break;
                }
                //印花税
                case "data.tax.rate.allocation.stamp.duty": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //土地增值税
                case "data.tax.rate.allocation.land.increment.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //其它税费
                case "data.tax.rate.allocation.other.taxes.fee": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //企业所得税
                case "data.tax.rate.allocation.corporate.income.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //契税
                case "data.tax.rate.allocation.deed.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
                //预计处置费用
                case "data.tax.rate.allocation.disposal.fee": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    break;
                }
            }
        } else {
            if (rate && evaluationArea) {
                price = Number(evaluationArea * rate).toFixed(2);
            }
        }
        $(_this).parent().parent().find('[name^=price]').attr("data-value", price);
        $(_this).parent().parent().find('[name^=price]').val(fmoney(price, 2));
        getTotal(frmId);
    }

    //获取结果值
    function getTotal(frmId) {
        var total = 0;
        $("#" + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var price = 0;
            price = $(this).find('[name^=price]').attr('data-value');
            if (price) {
                total += Number(price);
            }
        })
        $("#" + frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));

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

    function cleanItemData(_this, id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        var frmId = $(_this).closest('.form-horizontal').attr("id");
                        $(_this).parent().parent().parent().empty();
                        getTotal(frmId);
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //获取单个保存的数据
    function getFormData(frmId) {
        var data = {};
        data.id = $('#' + frmId).find('[name=id]').val();

        data.total = $('#' + frmId).find('[name=total]').text().replace(/,/g, '');
        data.analysisItemList = [];
        $('#' + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var analysisItem = {};
            analysisItem.id = $(this).find('[name=id]').val();
            analysisItem.price = $(this).find('[name^=price]').attr('data-value');
            analysisItem.taxRateValue = $(this).find('[name^=taxRateValue]').attr('data-value');
            analysisItem.remark = $(this).find('[name^=remark]').val();
            analysisItem.calculateBase = $(this).find('[name^=calculateBase]').val();
            analysisItem.calculationFormula = $(this).find('[name^=calculationFormula]').val();
            analysisItem.taxesBurden = $(this).find('[name^=taxesBurden]').val();
            data.analysisItemList.push(analysisItem);
        })
        return data;
    }

    /**
     * 单个保存
     * @param _this
     * @returns {boolean}
     */
    function saveAnalysisGroup(flag, _this) {
        if (flag) {
            //单个保存
            var data = getFormData(_this);
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/saveAnalysisGroup",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {

        }
    }

    function submit() {
        if (!$("#master").valid()) {
            return false;
        }

        var formData = {};
        formData.id = $('#master').find('[name=id]').val();
        formData.liquidRatios = $('#master').find('[name=liquidRatios]').val();
        formData.liquidTime = $('#master').find('[name=liquidTime]').val();
        formData.taskLiquidationAnalysisGroups = [];
        var forms = $("#" + commonField.taskLiquidationAnalysisAppend).find("form");
        //校验
        for (var i = 0; i < forms.size(); i++) {
            if (!$(forms[i]).valid()) {
                return false;
            }
        }
        $.each(forms, function (i, n) {
            var text = $(n).attr("id");
            if (text.indexOf(commonField.taskLiquidationAnalysisFrm) != -1) {
                var item = getFormData(text);
                formData.taskLiquidationAnalysisGroups.push(item);
            }
        });
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    //已选择估价对象
    function loadSchemeLiquidationJudgeTable(groupId, options) {
        var cols = [];
        cols.push({field: 'name', title: '估价对象名称', width: "22%"});
        cols.push({field: 'certName', title: '权证名称', width: "22%"});
        cols.push({field: 'ownership', title: '所有权人', width: "22%"});
        cols.push({field: 'seat', title: '坐落', width: "22%"});
        cols.push({
            field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="deleteJudgeItem(' + row.id + ',\'' + groupId + '\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        };
        $("#schemeLiquidationJudgeList" + groupId).bootstrapTable('destroy');
        TableInit("schemeLiquidationJudgeList" + groupId, "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeLiquidationJudgeList", cols, options, method, true);
    };

    //查询已选择估价对象
    function searchLiquidationJudgeData(_this) {
        var group = $(_this).closest(".form-group");
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

    function deleteJudgeItem(id, groupId) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteJudgeItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }

    function deleteJudgeItemByIds(_this) {
        var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
        var rows = $("#schemeLiquidationJudgeList" + groupId).bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            var ids = idArray.join(",");
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteJudgeItemByIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                        }
                        else {
                            AlertError("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });

        } else {
            notifyInfo("至少选择一个");
        }
    }

</script>
<script type="application/javascript">
    //选择估价对象
    var schemeJudgeObj = {};

    schemeJudgeObj.targetTable = $("#boxSchemeJudgeObjList");
    schemeJudgeObj.targetBox = $("#boxSchemeJudgeObj");
    schemeJudgeObj.callback = undefined;
    schemeJudgeObj.this_ = undefined;
    schemeJudgeObj.groupId = undefined;

    schemeJudgeObj.init = function (options) {
        var defaultObj = {};
        jQuery.extend(defaultObj, options);
        if (defaultObj.callback) {
            schemeJudgeObj.callback = defaultObj.callback;
        }
        if (defaultObj.this_) {
            schemeJudgeObj.this_ = defaultObj.this_;
        }
        schemeJudgeObj.loadSchemeJudgeObjTable({areaGroupId: '${areaGroup.id}'});
        schemeJudgeObj.targetBox.modal('show');
    };

    schemeJudgeObj.loadSchemeJudgeObjTable = function (options) {
        var table = schemeJudgeObj.targetTable;
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
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeJudgeObjList", cols, options, method, true);
    };

    schemeJudgeObj.select = function () {
        var rows = schemeJudgeObj.targetTable.bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })

            var ids = idArray.join(",");
            if (schemeJudgeObj.callback) {
                schemeJudgeObj.callback(schemeJudgeObj.this_, ids);
                schemeJudgeObj.targetBox.modal('hide');
            }

        } else {
            notifyInfo('至少选择一个');
        }
    }

    schemeJudgeObj.searchData = function (_this) {
        var group = $(_this).closest(".form-group");
        var name = group.find("[name='name']").val();
        var certName = group.find("[name='certName']").val();
        var ownership = group.find("[name='ownership']").val();
        var seat = group.find("[name='seat']").val();
        var data = {areaGroupId: '${areaGroup.id}'};
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

        schemeJudgeObj.loadSchemeJudgeObjTable(data);
    };


</script>

</html>

