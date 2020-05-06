<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<script type="text/html" id="taskLiquidationAnalysisDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        变现分析税费（{index}）
                        <small>
                            <button href="javascript://;" class="btn btn-sm btn-warning" type="button"
                                    onclick="saveAnalysisGroup(true,'taskLiquidationAnalysisFrm_number');">保存
                            </button>
                            <button type="button" href="javascript://;" class="btn btn-sm btn-warning"
                                    onclick="cleanHTMLData(this)">
                                移除
                            </button>
                        </small>
                    </div>
                    <div class="card-tools collapse-link">
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
                                    <button class="btn-primary btn btn-sm" type="button"
                                            onclick="schemeJudgeObj.init({callback:selectRecord,this_:this});">
                                        选择估价对象
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
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
                                <button class="btn btn-info btn-sm" style="margin-left: 10px" type="button"
                                        onclick="searchLiquidationJudgeData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                </button>
                                <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                        onclick="deleteJudgeItemByIds(this)">
											<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                                    批量删除
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

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">户型差异调查表<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-5">

                                    <input type="hidden" name="judgeIds">
                                    <button class="btn-primary btn btn-sm" type="button"
                                            onclick="houseHuxingPrice.prototype.showTableModel(this)">
                                        选择单价
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">房号</label>
                                <div class="col-sm-3">
                                    <input class="form-control input-full" type="text" name="houseNumber"
                                           placeholder="房号">
                                </div>
                                <button class="btn btn-info btn-sm" style="margin-left: 10px" type="button"
                                        onclick="houseHuxingPrice.prototype.queryHuxingPriceList(this);"><span
                                        class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                </button>
                                <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                        onclick="houseHuxingPrice.prototype.batchCutHuxingPrice(this)">
											<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                                    批量删除
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

                    <div class="row form-group">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="hidden-xs" style="width:10%">物业类型</th>
                                    <th class="hidden-xs" style="width:10%">税率</th>

                                    <th class="hidden-xs" style="width:11%">计算基数</th>
                                    <th class="hidden-xs" style="width:11%">计算公式</th>
                                    <th class="hidden-xs" style="width:10%">税费负担方</th>
                                    <th class="hidden-xs" style="width:18%">比例</th>

                                    <th class="hidden-xs" style="width:10%">备注</th>
                                    <th class="hidden-xs" style="width:10%">单位（面积/m² 金额/元）</th>
                                    <th class="hidden-xs" style="width:8%">操作</th>
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
                                    <td class="hidden-xs" rowspan="3">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="getTotal(this)">计算
                                        </button>
                                    </td>
                                    <td class='hidden-xs' colspan='6' style='text-align:center;'>合计费用</td>
                                    <td class='hidden-xs'>
                                        <label class="form-control input-full" name="total"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='hidden-xs' colspan='6' style='text-align:center;'>买方费用</td>
                                    <td class='hidden-xs'>
                                        <label class="form-control input-full" name="buyerTotal"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='hidden-xs' colspan='6' style='text-align:center;'>卖方费用</td>
                                    <td class='hidden-xs'>
                                        <label class="form-control input-full" name="sellerTotal"></label>
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
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${areaGroup.areaName}
                                            <button class="btn btn-sm btn-success" style="margin-left: 5px"
                                                    type="button" onclick="appendHtml(false)">
                                                <span class="btn-label"><i
                                                        class="fa fa-plus"></i>
                                                </span>
                                                添加分组
                                            </button>
                                        </small>
                                    </div>
                                    <div class="card-tools collapse-link">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="master" class="form-horizontal">
                                    <input type="hidden" name="id" value="${master.id}">
                                    <input type="hidden" name="houseId" value="3315">
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
                                                <label class="col-sm-1 control-label">变现说明</label>
                                                <div class="col-sm-3">
                                                    <input name="remark" class="form-control input-full"
                                                           placeholder="变现说明" value="${master.remark}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                                <div id="taskLiquidationAnalysisAppend"></div>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

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
                                                    onclick="schemeJudgeObj.searchData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                            </button>
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

<div id="burdenScaleBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">承担方比例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="burdenScaleFrm">
                    <input type="hidden" name="id">
                    <input type="hidden" name="groupId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">买方承担<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-4">
                                                <input type="text" name="buyerScale" required
                                                       class="form-control input-full x-percent">
                                            </div>
                                            <label class="col-sm-2 control-label">卖方承担<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-4">
                                                <input type="text" name="sellerScale" required
                                                       class="form-control input-full x-percent">
                                            </div>
                                        </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveBurdenScale()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="divBoxHouseHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">调查信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHuxingPriceTable" class="form-horizontal">
                    <input type="hidden" name="groupId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-group form-inline">
                                            <div class="col-md-3">
                                                <input type="text" data-rule-maxlength="50"
                                                       placeholder="房号" id="queryHouseNumber" name="queryHouseNumber"
                                                       class="form-control input-full">
                                            </div>
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="houseHuxingPrice.prototype.queryDataInBox(this)">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                        </div>
                                    </div>

                                </div>
                                <div class="row  form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="HouseHuxingPriceList">
                                            <!-- cerare document add ajax data-->
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseHuxingPrice.prototype.select()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>

<%@include file="/views/project/tool/declareRecordModeView.jsp" %>
<script type="application/javascript">
    var commonField = {
        taskLiquidationAnalysisFrm: "taskLiquidationAnalysisFrm",
        taskLiquidationAnalysisAppend: "taskLiquidationAnalysisAppend",
        taskLiquidationAnalysisDiv: "taskLiquidationAnalysisDiv",

    };

    function getJudgeIdsByGroupId(groupId) {
        $.ajax({
            url: getContextPath() + "/schemeLiquidationAnalysis/getJudgeIdsByGroupId",
            type: "get",
            dataType: "json",
            data: {groupId: groupId},
            success: function (result) {
                if (result.ret) {
                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='judgeIds']").val(result.data)
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }


    function selectRecord(_this, id) {
        var group = $(_this).closest(".form-horizontal");
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
                                houseHuxingPrice.prototype.loadListByGroupId(item.id, "");
                                getJudgeIdsByGroupId(item.id);
                                setTimeout(function () {
                                    if (item.total) {
                                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find('[name=total]').text(fmoney(Number(item.total).toFixed(2), 2));
                                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find('[name=buyerTotal]').text(fmoney(Number(item.buyerTotal).toFixed(2), 2));
                                        $("#" + commonField.taskLiquidationAnalysisFrm + number).find('[name=sellerTotal]').text(fmoney(Number(item.sellerTotal).toFixed(2), 2));
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                        $("#" + commonField.taskLiquidationAnalysisFrm + number).initForm(result.data);
                        loadSchemeLiquidationJudgeTable(result.data.id, {groupId: result.data.id});
                        getAnalysisItemList(number);
                        houseHuxingPrice.prototype.loadListByGroupId(result.data.id, "");
                        getJudgeIdsByGroupId(result.data.id);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        }

    }

    $(function () {
        appendHtml(true);
        //getHouseId();
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        });
    }

    /**
     * 添加税率明细
     * @param _this
     */
    function getGroupAndPriceVo(_this) {
        var group = $(_this).closest(".form-horizontal");
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
                            notifySuccess("成功", "成功");
                            initResult(frmId, result.data.groupArea, result.data.groupPrice);
                            $("#" + frmId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                            $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                            $("#" + frmId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                            loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                            getJudgeIdsByGroupId(groupId);
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                        $("#" + frmId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                        $("#" + frmId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                        $("#" + frmId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        });

    }


    //初始化计算结果
    function initResult(frmId, evaluationArea, evaluationPrice) {
        var salesTax = "${salesTax}";
        var total = 0;
        var buyerTotal = 0;
        var sellerTotal = 0;
        var evaluationArea = evaluationArea;
        var evaluationPrice = evaluationPrice;
        $("#" + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var $taxRateValue = $(this).find('[name^=taxRateValue]');
            //买方比例
            var buyerScale = $(this).find('[name^=buyerScale]').val();
            //卖方比例
            var sellerScale = $(this).find('[name^=sellerScale]').val();
            var rate = $taxRateValue.val();
            var price = 0;
            var buyerPrice = 0;
            var sellerPrice = 0;
            if ($taxRateValue.hasClass('x-percent')) {
                rate = $taxRateValue.attr('data-value');
                var key = $taxRateValue.attr('data-key');
                switch (key) {
                    //增值税
                    case "data.tax.rate.allocation.sales.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * rate).toFixed(2);
                            buyerPrice = Number(temp * rate * buyerScale).toFixed(2);
                            sellerPrice = Number(temp * rate * sellerScale).toFixed(2);
                        }
                        break;
                    }
                    //城建税
                    case "data.tax.rate.allocation.construction.tax": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                            buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                            sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                        }
                        break;
                    }
                    //教育税附加
                    case "data.tax.rate.allocation.education.fee.plus": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                            buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                            sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                        }
                        break;
                    }
                    //地方教育税附加
                    case "data.tax.rate.allocation.local.education.tax.additional": {
                        if (rate && evaluationPrice) {
                            var temp = evaluationPrice / 1.05;
                            price = Number(temp * salesTax * rate).toFixed(2);
                            buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                            sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                        }
                        break;
                    }
                    //印花税
                    case "data.tax.rate.allocation.stamp.duty": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                    //土地增值税
                    case "data.tax.rate.allocation.land.increment.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                    //其它税费
                    case "data.tax.rate.allocation.other.taxes.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                    //企业所得税
                    case "data.tax.rate.allocation.corporate.income.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                    //契税
                    case "data.tax.rate.allocation.deed.tax": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                    //预计处置费用
                    case "data.tax.rate.allocation.disposal.fee": {
                        price = Number(evaluationPrice * rate).toFixed(2);
                        buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                        break;
                    }
                }
            } else {
                if (rate && evaluationArea) {
                    price = Number(evaluationArea * rate).toFixed(2);
                    buyerPrice = Number(evaluationArea * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationArea * rate * sellerScale).toFixed(2);
                }
            }
            total += Number(price);
            buyerTotal += Number(buyerPrice);
            sellerTotal += Number(sellerPrice);
            $(this).find('[name^=price]').val(fmoney(price, 2));
            $(this).find('[name^=price]').attr("data-value", price);
            $(this).find('[name^=buyerPrice]').attr("data-value", buyerPrice);
            $(this).find('[name^=sellerPrice]').attr("data-value", sellerPrice);
        })
        $("#" + frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));
        $("#" + frmId).find('[name=buyerTotal]').text(fmoney(Number(buyerTotal).toFixed(2), 2));
        $("#" + frmId).find('[name=sellerTotal]').text(fmoney(Number(sellerTotal).toFixed(2), 2));
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

                        html += "<select name='taxesBurden_" + item.id + "' class='form-control input-full' onchange='burdenTypeChange(this ,\"" + item.taxesBurden + "\"," + groupId + ")'>";
                        html += "<option value=''>--请选择--</option>"
                        html += '<c:forEach var="burdenItem" items="${taxesBurdenList}">';
                        html += '<option value="${burdenItem.name}">${burdenItem.name}</option>';
                        html += '</c:forEach>';
                        html += '</select>';
                        html += "</td>";

                        html += "<td class='hidden-xs burdenScale" + item.id + "'>";
                        if (item.taxesBurden == '双方承担') {
                            var s = '';
                            if (item.buyerScale) {
                                s += '买方承担' + AssessCommon.pointToPercent(item.buyerScale) + ";";
                            }
                            if (item.sellerScale) {
                                s += '卖方承担' + AssessCommon.pointToPercent(item.sellerScale);
                            }
                            html += '<div class="input-group">';
                            html += '<input type="text" name="burdenScale_' + item.id + '" class="form-control" value="' + s + '">';
                            html += '<div class="input-group-prepend">';
                            html += '<button class="btn btn-primary btn-sm" style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="editBurdenScale(this,' + groupId + ');">';
                            html += '编辑 </button>';
                            html += '</div>';
                            html += '</div>';
                        } else {
                            html += '<input type="text" class="form-control" value="100%" readonly>';
                        }
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  name='remark_" + item.id + "' value='" + AssessCommon.toString(item.remark) + "' class='form-control input-full'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text'  data-key='price_" + item.typeKey + "' name='price_" + item.id + "' data-value='" + item.price + "' value='" + textPrice + "' class='form-control input-full' readonly>";
                        html += "<input type='hidden' name='buyerPrice_" + item.id + "' data-value='" + item.price + "'>";
                        html += "<input type='hidden' name='buyerScale_" + item.id + "' value='" + item.buyerScale + "'>";
                        html += "<input type='hidden'  name='sellerPrice_" + item.id + "' data-value='" + item.price + "'>";
                        html += "<input type='hidden'  name='sellerScale_" + item.id + "' value='" + item.sellerScale + "'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<span class='input-group-btn'>";
                        html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanItemData(this)'>";
                        html += "<input class='btn btn-sm btn-info copy' type='button' value='复制' style='margin-left: 5px;' onclick='copyItem(this," + groupId + ")'>";
                        html += "</span>";
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").append(html);
                    $.each(result.data, function (i, item) {
                        $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("select[name='taxesBurden_" + item.id + "']").val(item.taxesBurden);
                        getThisPrice($("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='taxRateValue_" + item.id + "']"))
                    });

                }
            }
        });
    }

    function copyItem(_this, groupId) {
        var id = $(_this).closest('tr').find("input[name='id']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/copyItem",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        reloadAnalysisItem(groupId, result.data);
                        notifyInfo("提示", "复制完成");
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    function reloadAnalysisItem(groupId, item) {
        var html = "";

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

        html += "<select name='taxesBurden_" + item.id + "' class='form-control input-full' onchange='burdenTypeChange(this ,\"" + item.taxesBurden + "\"," + groupId + ")'>";
        html += "<option value=''>--请选择--</option>"
        html += '<c:forEach var="burdenItem" items="${taxesBurdenList}">';
        html += '<option value="${burdenItem.name}">${burdenItem.name}</option>';
        html += '</c:forEach>';
        html += '</select>';
        html += "</td>";

        html += "<td class='hidden-xs burdenScale" + item.id + "'>";
        if (item.taxesBurden == '双方承担') {
            var s = '';
            if (item.buyerScale) {
                s += '买方承担' + AssessCommon.pointToPercent(item.buyerScale) + ";";
            }
            if (item.sellerScale) {
                s += '卖方承担' + AssessCommon.pointToPercent(item.sellerScale);
            }
            html += '<div class="input-group">';
            html += '<input type="text" name="burdenScale_' + item.id + '" class="form-control" value="' + s + '">';
            html += '<div class="input-group-prepend">';
            html += '<button class="btn btn-primary btn-sm" style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="editBurdenScale(this,' + groupId + ');">';
            html += '编辑 </button>';
            html += '</div>';
            html += '</div>';
        } else {
            html += '<input type="text" class="form-control" value="100%" readonly>';
        }
        html += "</td>";

        html += "<td class='hidden-xs'>";
        html += "<input type='text'  name='remark_" + item.id + "' value='" + AssessCommon.toString(item.remark) + "' class='form-control input-full'>";
        html += "</td>";

        html += "<td class='hidden-xs'>";
        html += "<input type='text'  data-key='price_" + item.typeKey + "' name='price_" + item.id + "' data-value='" + item.price + "' value='" + textPrice + "' class='form-control input-full' readonly>";
        html += "<input type='hidden' name='buyerPrice_" + item.id + "' data-value='" + item.price + "'>";
        html += "<input type='hidden' name='buyerScale_" + item.id + "' value='" + item.buyerScale + "'>";
        html += "<input type='hidden'  name='sellerPrice_" + item.id + "' data-value='" + item.price + "'>";
        html += "<input type='hidden'  name='sellerScale_" + item.id + "' value='" + item.sellerScale + "'>";
        html += "</td>";
        html += "<td class='hidden-xs'>";
        html += "<span class='input-group-btn'>";
        html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanItemData(this,\" + item.id + \")'>";
        html += "<input class='btn btn-sm btn-info copy' type='button' value='复制' style='margin-left: 5px;' onclick='copyItem(this," + groupId + "," + item.id + ")'>";
        html += "</span>";
        html += "</td>";
        html += "</tr>";
        $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("tbody[name='tbody_data_section']").append(html);
        $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("select[name='taxesBurden_" + item.id + "']").val(item.taxesBurden);
        getThisPrice($("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='taxRateValue_" + item.id + "']"))
    }

    //承担方改变
    function burdenTypeChange(_this, oldValue, groupId) {
        var id = $(_this).closest('tr').find("input[name='id']").val();
        var type = $(_this).val();
        var sellerScale = 0;
        var buyerScale = 0;
        $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find(".burdenScale" + id).empty();
        var html = '';
        if (type == '双方承担') {
            sellerScale = 0.5;
            buyerScale = 0.5;
            var s = "买方承担50%;卖方承担50%";
            html += '<div class="input-group">';
            html += '<input type="text" name="burdenScale_' + id + '" class="form-control" value="' + s + '">';
            html += '<div class="input-group-prepend">';
            html += '<button class="btn btn-primary btn-sm" style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;" type="button" onclick="editBurdenScale(this,' + groupId + ');">';
            html += '编辑 </button>';
            html += '</div>';
            html += '</div>';
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='buyerScale_" + id + "']").val(0.5);
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='sellerScale_" + id + "']").val(0.5);
        }
        if (type == '买方承担') {
            sellerScale = 0;
            buyerScale = 1;
            html += '<input type="text" class="form-control" value="100%" readonly>';
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='buyerScale_" + id + "']").val(1);
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='sellerScale_" + id + "']").val(0);
        }
        if (type == '卖方承担') {
            sellerScale = 1;
            buyerScale = 0;
            html += '<input type="text" class="form-control" value="100%" readonly>';
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='buyerScale_" + id + "']").val(0);
            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='sellerScale_" + id + "']").val(1);
        }
        var data = {};
        data.id = id;
        data.taxesBurden = type;
        data.sellerScale = sellerScale;
        data.buyerScale = buyerScale;
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/saveBurdenScale",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    getThisPrice($("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='taxRateValue_" + id + "']"))
                }
                else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
        $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find(".burdenScale" + id).append(html);
    }

    //编辑承担方比例
    function editBurdenScale(_this, groupId) {
        var id = $(_this).closest('tr').find("input[name='id']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getAnalysisItem",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#burdenScaleFrm").clearAll().initForm(result.data);
                    //百分字段
                    $("#burdenScaleFrm").find('[name=sellerScale]').attr('data-value', result.data.sellerScale);
                    AssessCommon.elementParsePercent($("#burdenScaleFrm").find('[name=sellerScale]'));
                    $("#burdenScaleFrm").find('[name=buyerScale]').attr('data-value', result.data.buyerScale);
                    AssessCommon.elementParsePercent($("#burdenScaleFrm").find('[name=buyerScale]'));
                    $("#burdenScaleFrm").find("[name=groupId]").val(groupId);
                    $('#burdenScaleBox').modal("show");
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })

    }

    function saveBurdenScale() {
        if (!$("#burdenScaleFrm").valid()) {
            return false;
        }
        var data = formParams("burdenScaleFrm");
        var result = Number(data.sellerScale) + Number(data.buyerScale);
        if (result != 1) {
            notifyInfo("提示", "请正确填写比例且相加应为100%");
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/saveBurdenScale",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    var s = '';
                    if (result.data.buyerScale) {
                        s += '买方承担' + AssessCommon.pointToPercent(result.data.buyerScale) + ";";
                    }
                    if (result.data.sellerScale) {
                        s += '卖方承担' + AssessCommon.pointToPercent(result.data.sellerScale);
                    }
                    var groupId = $("#burdenScaleFrm").find("[name=groupId]").val();
                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='burdenScale_" + data.id + "']").val(s);
                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='buyerScale_" + data.id + "']").val(result.data.buyerScale);
                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("[name='sellerScale_" + data.id + "']").val(result.data.sellerScale);
                    getThisPrice($("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='taxRateValue_" + data.id + "']"))
                    $('#burdenScaleBox').modal("hide");
                    notifyInfo("成功", "设置比例完成");
                }
                else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
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
        //买方比例
        var buyerScale = $(_this).parent().parent().find('[name^=buyerScale]').val();
        //卖方比例
        var sellerScale = $(_this).parent().parent().find('[name^=sellerScale]').val();
        var rate = $taxRateValue.val();
        var price = 0;
        var buyerPrice = 0;
        var sellerPrice = 0;
        if ($taxRateValue.hasClass('x-percent')) {
            rate = $taxRateValue.attr('data-value');
            var key = $taxRateValue.attr('data-key');
            switch (key) {
                //增值税
                case "data.tax.rate.allocation.sales.tax": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * rate).toFixed(2);
                        buyerPrice = Number(temp * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(temp * rate * sellerScale).toFixed(2);
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
                        buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                    }
                    break;
                }
                //教育税附加
                case "data.tax.rate.allocation.education.fee.plus": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                        buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                    }
                    break;
                }
                //地方教育税附加
                case "data.tax.rate.allocation.local.education.tax.additional": {
                    if (rate && evaluationPrice) {
                        var temp = evaluationPrice / 1.05;
                        price = Number(temp * salesTax * rate).toFixed(2);
                        buyerPrice = Number(temp * salesTax * rate * buyerScale).toFixed(2);
                        sellerPrice = Number(temp * salesTax * rate * sellerScale).toFixed(2);
                    }
                    break;
                }
                //印花税
                case "data.tax.rate.allocation.stamp.duty": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
                //土地增值税
                case "data.tax.rate.allocation.land.increment.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
                //其它税费
                case "data.tax.rate.allocation.other.taxes.fee": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
                //企业所得税
                case "data.tax.rate.allocation.corporate.income.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
                //契税
                case "data.tax.rate.allocation.deed.tax": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
                //预计处置费用
                case "data.tax.rate.allocation.disposal.fee": {
                    price = Number(evaluationPrice * rate).toFixed(2);
                    buyerPrice = Number(evaluationPrice * rate * buyerScale).toFixed(2);
                    sellerPrice = Number(evaluationPrice * rate * sellerScale).toFixed(2);
                    break;
                }
            }
        } else {
            if (rate && evaluationArea) {
                price = Number(evaluationArea * rate).toFixed(2);
                buyerPrice = Number(evaluationArea * rate * buyerScale).toFixed(2);
                sellerPrice = Number(evaluationArea * rate * sellerScale).toFixed(2);
            }
        }
        $(_this).parent().parent().find('[name^=price]').attr("data-value", price);
        $(_this).parent().parent().find('[name^=buyerPrice]').attr("data-value", buyerPrice);
        $(_this).parent().parent().find('[name^=sellerPrice]').attr("data-value", sellerPrice);
        $(_this).parent().parent().find('[name^=price]').val(fmoney(price, 2));
    }

    //获取结果值
    function getTotal(_this) {
        var frmId = $(_this).closest('.form-horizontal').attr("id");
        var total = 0;
        var buyerTotal = 0;
        var sellerTotal = 0;
        $("#" + frmId).find("tbody[name='tbody_data_section']").find('tr').each(function () {
            var price = 0;
            var buyerPrice = 0;
            var sellerPrice = 0;
            price = $(this).find('[name^=price]').attr('data-value');
            buyerPrice = $(this).find('[name^=buyerPrice]').attr('data-value');
            sellerPrice = $(this).find('[name^=sellerPrice]').attr('data-value');
            if (price) {
                total += Number(price);
            }
            if (buyerPrice) {
                buyerTotal += Number(buyerPrice);
            }
            if (sellerPrice) {
                sellerTotal += Number(sellerPrice);
            }
        })
        $("#" + frmId).find('[name=total]').text(fmoney(Number(total).toFixed(2), 2));
        $("#" + frmId).find('[name=buyerTotal]').text(fmoney(Number(buyerTotal).toFixed(2), 2));
        $("#" + frmId).find('[name=sellerTotal]').text(fmoney(Number(sellerTotal).toFixed(2), 2));

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

    function cleanItemData(_this) {
        var id = $(_this).closest('tr').find("input[name='id']").val();
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteItem",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        $(_this).parent().parent().parent().empty();
                    }
                    else {
                        AlertError("失败", "删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //获取单个保存的数据
    function getFormData(frmId) {
        var data = {};
        data.id = $('#' + frmId).find('[name=id]').val();

        data.total = $('#' + frmId).find('[name=total]').text().replace(/,/g, '');
        data.buyerTotal = $('#' + frmId).find('[name=buyerTotal]').text().replace(/,/g, '');
        data.sellerTotal = $('#' + frmId).find('[name=sellerTotal]').text().replace(/,/g, '');
        data.analysisItemList = [];
        var flag = false;
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
            if ($(this).find('[name^=price]').val() == 0) {
                flag = true;
            }
        })
        if (flag == true) {
            notifyInfo("提示", "点击每行数据税率，确保单价不为0");
        }
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
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
        formData.remark = $('#master').find('[name=remark]').val();
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
                str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="deleteJudgeItem(' + row.id + ',\'' + groupId + '\')"><i class="fa fa-minus fa-white"></i></button>';
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

    function deleteJudgeItem(id, groupId) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/deleteJudgeItem",
                type: "post",
                dataType: "json",
                data: {id: id, groupId: groupId},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        if (result.data) {
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                            loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                            initResult(commonField.taskLiquidationAnalysisFrm + groupId, result.data.groupArea, result.data.groupPrice);
                            getJudgeIdsByGroupId(groupId);
                        }

                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                    data: {ids: ids, groupId: groupId},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                            $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                            loadSchemeLiquidationJudgeTable(groupId, {groupId: groupId});
                            initResult(commonField.taskLiquidationAnalysisFrm + groupId, result.data.groupArea, result.data.groupPrice);
                            getJudgeIdsByGroupId(groupId);
                        }
                        else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                })
            });

        } else {
            notifyInfo('提示', "至少选择一个");
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
            notifyInfo('提示', '至少选择一个');
        }
    }

    schemeJudgeObj.searchData = function (_this) {
        var group = $(_this).closest(".form-horizontal");
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
        queryDataInBox: function (_this) {
            var houseNumber = $(_this).closest(".form-horizontal").find("input[name='queryHouseNumber']").val();
            var groupId = $('#' + houseHuxingPrice.prototype.config().tableFrm).find("input[name='groupId']").val();
            houseHuxingPrice.prototype.loadDataDicList(groupId, houseNumber);
        },
        loadDataDicList: function (groupId, houseNumber) {
            var judgeIds = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find("input[name='judgeIds']").val()
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'price', title: '价格'});
            $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHuxingPrice.prototype.config().table, getContextPath() + "/basicHouseHuxingPrice/getListByQuery", cols, {
                houseNumber: houseNumber,
                judgeIds: judgeIds
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            }, true);
        },
        showTableModel: function (_this) {
            var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
            $('#' + houseHuxingPrice.prototype.config().tableFrm).find("input[name='groupId']").val(groupId);
            houseHuxingPrice.prototype.loadDataDicList(groupId, "");
            $('#' + houseHuxingPrice.prototype.config().tableBox).modal("show");
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        select: function () {
            var rows = $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('getSelections');
            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    idArray.push(item.id);
                })
                var huxingPriceIds = idArray.join(",");
                var groupId = $('#' + houseHuxingPrice.prototype.config().tableFrm).find("input[name='groupId']").val();
                var oldGroupArea = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val();
                var oldGroupPrice = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val();

                $.ajax({
                    url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/augmentHuxingAreaAndPrice",
                    type: "post",
                    dataType: "json",
                    data: {
                        huxingPriceIds: huxingPriceIds,
                        groupId: groupId,
                        oldGroupArea: oldGroupArea,
                        oldGroupPrice: oldGroupPrice
                    },
                    success: function (result) {
                        if (result.ret) {
                            if (result.data) {
                                notifySuccess("成功", "添加单价查询成功");
                                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                                $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                                houseHuxingPrice.prototype.loadListByGroupId(groupId, "");
                                initResult(commonField.taskLiquidationAnalysisFrm + groupId, result.data.groupArea, result.data.groupPrice);
                                $('#' + houseHuxingPrice.prototype.config().tableBox).modal("hide");
                            }
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                });

            } else {
                notifyInfo('提示', '至少选择一个');
            }
        },
        loadListByGroupId: function (groupId, houseNumber) {
            var houseId = $("#master").find("input[name='houseId']").val();
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'price', title: '价格'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="houseHuxingPrice.prototype.cutHuxingPrice(' + row.id + ',\'' + groupId + '\')"><i class="fa fa-minus fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
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
        cutHuxingPrice: function (id, groupId) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                var oldGroupArea = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val();
                var oldGroupPrice = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/schemeLiquidationAnalysis/cutHuxingPrice",
                    type: "post",
                    dataType: "json",
                    data: {
                        huxingPriceId: id,
                        groupId: groupId,
                        oldGroupArea: oldGroupArea,
                        oldGroupPrice: oldGroupPrice
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            if (result.ret) {
                                if (result.data) {
                                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').text(fmoney(Number(result.data.groupArea).toFixed(2), 2));
                                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationArea]').val(Number(result.data.groupArea));
                                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').text(fmoney(Number(result.data.groupPrice).toFixed(2), 2));
                                    $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=evaluationPrice]').val(Number(result.data.groupPrice));
                                    houseHuxingPrice.prototype.loadListByGroupId(groupId, "");
                                    initResult(commonField.taskLiquidationAnalysisFrm + groupId, result.data.groupArea, result.data.groupPrice);
                                }
                            }
                        }
                        else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        queryHuxingPriceList: function (_this) {
            var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
            var houseNumber = $("#" + commonField.taskLiquidationAnalysisFrm + groupId).find('[name=houseNumber]').val();
            houseHuxingPrice.prototype.loadListByGroupId(groupId, houseNumber);

        },
        batchCutHuxingPrice: function (_this) {
            var groupId = $(_this).closest('.form-horizontal').find("input[name='id']").val();
            var rows = $("#houseHuxingPriceList" + groupId).bootstrapTable('getSelections');
            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    idArray.push(item.id);
                })
                var huxingPriceIds = idArray.join(",");
                houseHuxingPrice.prototype.cutHuxingPrice(huxingPriceIds, groupId);
            } else {
                notifyInfo('提示', '至少选择一个');
            }
        }
    }
</script>
</html>

