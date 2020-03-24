<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
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
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${judgeObject.name}
                                        <small>(${judgeObject.evaluationArea}㎡)</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="sure_price_form">
                                    <input type="hidden" name="id" value="${schemeSurePrice.id}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>方法名称</th>
                                            <th>试算价格</th>
                                            <th>权重</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tbody_data_section">
                                        </tbody>
                                    </table>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                权重说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-11">
                                <textarea name="weightExplain" required
                                          class="form-control">${schemeSurePrice.weightExplain}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group form-inline">
                                        <label class="col-sm-1 control-label">
                                            最终单价
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="price" class="form-control" readonly="readonly"
                                                   value="${schemeSurePrice.price}">
                                        </div>
                                        <div class="col-sm-3">
                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="updatePrice(this)">更新单价
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        调整单价
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <button style="margin-left: 10px" type="button"
                                                    class="btn btn-sm btn-warning"
                                                    onclick="surePrice.pasteBatch();"><span class="btn-label">
												<i class="fa fa-paste"></i>
											</span>粘贴
                                            </button>
                                            <button style="margin-left: 5px" type="button"
                                                    class="btn btn-sm btn-success"
                                                    onclick="surePrice.exportData();"><span class="btn-label">
												<i class="fa fa-cloud-download-alt"></i>
											</span>生成下载模板
                                            </button>
                                            <button style="margin-left: 5px" type="button" class="btn btn-sm btn-info"
                                                    onclick="$('#ajaxFileUploadBtn').val('').trigger('click')"><span
                                                    class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>导入
                                            </button>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table id="adjust_factor_table" class="table">
                                                <thead>
                                                <tr>
                                                    <th width="5%"><input type="checkbox"
                                                                          onclick="surePrice.checkboxToggle(this);">
                                                    </th>
                                                    <th width="10%">权证号</th>
                                                    <th width="10%">证载面积</th>
                                                    <th width="10%">评估面积</th>
                                                    <th width="10%">楼层</th>
                                                    <th width="10%">房号</th>
                                                    <th width="10%">价格</th>
                                                    <th width="50%">因素</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        确认单价记录
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="tb_surePriceRecordList">
                                            </table>
                                        </div>
                                    </div>
                                </form>
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
<input type="file" id="ajaxFileUploadBtn" name="file" style="display: none;"
       onchange="surePrice.importData();">

<%--确定单价模板--%>
<script type="text/html" id="surePriceTemp">
    <tr>
        <td>
            <input type="hidden" name="id" value="{id}">
            {methodName}
        </td>
        <td data-name="trialPrice">{trialPrice}</td>
        <td>
            <input type="text" class="form-control x-percent" name="weight" data-value="{weight}"
                   onblur="surePrice.computePrice(this);">
        </td>
    </tr>
</script>

<!-- 估价对象模板 -->
<script type="text/html" id="adjust_JudgeObject_Model">
    <tr data-id="{id}">
        <td><input type="checkbox"></td>
        <td>{name}</td>
        <td>{floorArea}</td>
        <td>{evaluationArea}</td>
        <td>{floor}</td>
        <td>{roomNumber}</td>
        <td data-name="price">{price}</td>
        <td data-name="coefficient">{coefficient}</td>
        <td>
            <div class="btn btn-xs btn-primary"
                 onclick="surePrice.adjustFactor('{id}','{declareRecordId}')">调整
            </div>
            <div class="btn btn-xs btn-primary copy"
                 onclick="surePrice.copy(this);">复制
            </div>
            <div class="btn btn-xs btn-primary cancel-copy" style="display:none;"
                 onclick="surePrice.cancelCopy(this)">取消复制
            </div>
            <div class="btn btn-xs btn-warning paste" style="display:none;"
                 onclick="surePrice.paste(this)">粘贴
            </div>
            <div class="btn btn-xs btn-primary"
                 onclick="surePrice.getHuxingId('{id}')">单价调整
            </div>
        </td>
    </tr>
</script>

<%--调整评估价--%>
<div id="modal_adjustment_price" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">调整评估价</h3>
            </div>
            <div class="modal-body">
                <input type="hidden" name="judgeObjectId">
                <table id="tb_judge_detail_list" class="table">
                </table>
            </div>
        </div>
    </div>
</div>

<%--调整因素--%>
<div id="modal_factor" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><h4>调整因素</h4></div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="judgeObjectId">
                <button type="button" class="btn btn-sm btn-success tooltips" data-placement="top"
                        onclick="surePrice.addAdjustFactor();"><i class="fa fa-plus fa-white"></i>添加
                </button>
                <table class="table">
                    <thead>
                    <tr>
                        <th>因素</th>
                        <th>说明</th>
                        <th>类型</th>
                        <th>系数</th>
                    </tr>
                    </thead>
                    <tbody id="tbody_factor">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    取消
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="surePrice.saveAdjustFactor();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<%--调整因素模板--%>
<script type="text/html" id="adjustFatorTemp">
    <tr>
        <td>
            <input type="text" class="form-control " name="factor" value="{factor}">
        </td>
        <td><input type="text" class="form-control" name="remark" value="{remark}"></td>
        <td>
            <select name="type" class="form-control" onchange="surePrice.changeType(this);" required>
                <option value="0">绝对值</option>
                <option value="1" selected="selected">相对值</option>
            </select>
        </td>
        <td>
            <input type="text" class="form-control" data-rule-number='true' name="coefficient-absolute"
                   value="{coefficient}" style="display: none;">
            <input type="text" class="form-control x-percent" name="coefficient-relative" data-value="{coefficient}">
        </td>
        <td>
            <a class="btn btn-xs btn-warning tooltips" data-placement="top" onclick="$(this).closest('tr').remove();"><i
                    class="fa fa-minus fa-white"></i></a>
        </td>
    </tr>
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script type="application/javascript">
    $(function () {
        surePrice.surePrice('${projectPlanDetails.judgeObjectId}', true);
        surePrice.loadAdjustJudgeObject('${projectPlanDetails.judgeObjectId}');
        loadDocumentSend();
    });

    function submit() {
        var surePriceApply = {};
        var form = $("#sure_price_form");
        if (!form.valid()) {
            return false;
        }
        surePriceApply.id = form.find('[name=id]').val();
        surePriceApply.judgeObjectId = '${projectPlanDetails.judgeObjectId}';
        surePriceApply.weightExplain = form.find('[name=weightExplain]').val();
        surePriceApply.price = form.find('[name=price]').val();
        surePriceApply.surePriceItemList = [];
        $("#tbody_data_section tr").each(function () {
            var schemeSurePriceItem = {};
            schemeSurePriceItem.id = $(this).find('[name=id]').val();
            schemeSurePriceItem.weight = $(this).find('[name=weight]').attr('data-value');
            surePriceApply.surePriceItemList.push(schemeSurePriceItem);
        });

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(surePriceApply));
        }
        else {
            submitToServer(JSON.stringify(surePriceApply));
        }
    }

    function updatePrice() {
        var surePriceApply = {};
        var form = $("#sure_price_form");
        if (!form.valid()) {
            return false;
        }
        surePriceApply.id = form.find('[name=id]').val();
        surePriceApply.judgeObjectId = '${projectPlanDetails.judgeObjectId}';
        surePriceApply.weightExplain = form.find('[name=weightExplain]').val();
        surePriceApply.price = form.find('[name=price]').val();
        surePriceApply.surePriceItemList = [];
        $("#tbody_data_section tr").each(function () {
            var schemeSurePriceItem = {};
            schemeSurePriceItem.id = $(this).find('[name=id]').val();
            schemeSurePriceItem.weight = $(this).find('[name=weight]').attr('data-value');
            surePriceApply.surePriceItemList.push(schemeSurePriceItem);
        });
        AlertConfirm("确认更新么", "将同步更新与标准估价对象关联的其他对象的初始单价", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/schemeSurePrice/updateCalculationSchemeSurePrice",
                data: {
                    fomData: JSON.stringify(surePriceApply), planDetailsId: '${projectPlanDetails.id}'
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
//                        window.location.reload(true);
                        surePrice.surePrice('${projectPlanDetails.judgeObjectId}', false);
                        surePrice.loadAdjustJudgeObject('${projectPlanDetails.judgeObjectId}');
                    } else {
                        AlertError("失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                }
            });
        });
    }


    //确认单价记录
    function loadDocumentSend() {
        var cols = [];
        cols.push({field: 'recordPrice', title: '单价记录'});
        cols.push({
            field: 'gmtCreated', title: '日期', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        $("#tb_documentSendList").bootstrapTable('destroy');
        TableInit("tb_surePriceRecordList", "${pageContext.request.contextPath}/schemeSurePriceRecord/getSchemeSurePriceRecordList", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    var surePrice = {};

    /**
     * 估价对象 table list
     * @param judgeObjectId
     */
    surePrice.loadAdjustJudgeObject = function (judgeObjectId) {
        var target = $("#adjust_factor_table");
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getAdjustObjectListByPid",
            data: {judgeObjectId: judgeObjectId},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (!data) {
                        target.closest(".x_panel card").hide();
                        return false;
                    }
                    if (data.length == 0) {
                        target.closest(".x_panel card").hide();
                        return false;
                    }
                    var ele = target.find("tbody");
                    ele.empty();
                    $.each(data, function (i, item) {
                        var model = $("#adjust_JudgeObject_Model");
                        var html = model.html();
                        html = html.replace(/{id}/g, item.id);
                        html = html.replace(/{name}/g, item.name);
                        html = html.replace(/{floorArea}/g, item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea);
                        html = html.replace(/{floor}/g, item.floor);
                        html = html.replace(/{price}/g, item.price);
                        html = html.replace(/{roomNumber}/g, item.roomNumber);
                        html = html.replace(/{price}/g, item.price);
                        html = html.replace(/{coefficient}/g, item.factor);
                        html = html.replace(/{declareRecordId}/g, item.declareRecordId);
                        ele.append(html);
                    });
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });

    };

    //确定单价
    surePrice.surePrice = function (judgeObjectId, isUpdatePrice) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getSchemeSurePriceItemList",
            data: {
                judgeObjectId: judgeObjectId,
                isUpdatePrice: isUpdatePrice
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#tbody_data_section").empty();
                    $.each(result.data, function (i, item) {
                        var html = $("#surePriceTemp").html();
                        html = html.replace(/{id}/g, AssessCommon.toString(item.id));
                        html = html.replace(/{methodName}/g, AssessCommon.toString(item.methodName));
                        html = html.replace(/{trialPrice}/g, AssessCommon.toString(item.trialPrice));
                        html = html.replace(/{weight}/g, AssessCommon.toString(item.weight));
                        $("#tbody_data_section").append(html);
                    });
                    $("#tbody_data_section").find('.x-percent').each(function () {
                        AssessCommon.elementParsePercent($(this));
                    });
                    surePrice.computePrice($("#tbody_data_section").find(':text[name=weight]:first'));
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    };

    //计算单价
    surePrice.computePrice = function (_this) {
        //自动计算最后一个方法的权重
        var i = 0;
        var lastWeightElement = undefined;
        var lastDataValue = 1;
        $(_this).closest('tbody').find('[name=weight]').each(function () {
            var val = $(this).val();
            if (val) {
                lastDataValue = lastDataValue - parseFloat(AssessCommon.percentToPoint(val));
            } else {
                lastWeightElement = $(this);
                i++;
            }
        })
        if (i == 1) {
            lastWeightElement.attr('data-value', lastDataValue);
            AssessCommon.elementParsePercent(lastWeightElement);
        }

        //如果权重一致则无需填写权重说明
        var isAverage = true;
        var preWight = 0;
        var resultPrice = 0;
        $(_this).closest('tbody').find('tr').each(function () {
            var trialPrice = $(this).find("[data-name=trialPrice]").text();
            var weight = $(this).find("[name=weight]").attr('data-value');
            if (AssessCommon.isNumber(trialPrice) && AssessCommon.isNumber(weight)) {
                if (preWight != 0 && preWight != parseFloat(weight)) {
                    isAverage = false;
                }
                preWight = parseFloat(weight);
                resultPrice += parseFloat(trialPrice) * parseFloat(weight);
            }
        })
        $("#sure_price_form").find('[name=price]').val(resultPrice.toFixed(2));
        if (isAverage) {
            $("#sure_price_form").find('[name=weightExplain]').closest('.form-group').hide();
        } else {
            $("#sure_price_form").find('[name=weightExplain]').closest('.form-group').show();
        }
    }

    //调整因素
    surePrice.adjustFactor = function (judgeObjectId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getSurePriceFactors",
            data: {
                judgeObjectId: judgeObjectId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#tbody_factor").empty();
                    $.each(result.data, function (i, item) {
                        var html = $("#adjustFatorTemp").html();
                        html = html.replace(/{factor}/g, AssessCommon.toString(item.factor));
                        html = html.replace(/{remark}/g, AssessCommon.toString(item.remark));
                        html = html.replace(/{coefficient}/g, AssessCommon.toString(item.coefficient));
                        $("#tbody_factor").append(html);
                        $("#tbody_factor").find('tr:last').find('[name=type]').val(item.type).trigger('change');
                    })
                    $("#tbody_factor").find('.x-percent').each(function () {
                        AssessCommon.elementParsePercent($(this));
                    })
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
        $("#modal_factor").find('[name=judgeObjectId]').val(judgeObjectId);
        $("#modal_factor").modal();
    }

    //类型调整
    surePrice.changeType = function (_this) {
        if ($(_this).val() == 0) {
            $(_this).closest('tr').find('[name=coefficient-absolute]').show();
            $(_this).closest('tr').find('[name=coefficient-relative]').hide();
        } else {
            $(_this).closest('tr').find('[name=coefficient-absolute]').hide();
            $(_this).closest('tr').find('[name=coefficient-relative]').show();
        }
    }

    //添加调整因素
    surePrice.addAdjustFactor = function () {
        var html = $("#adjustFatorTemp").html();
        html = html.replace(/{factor}/g, '');
        html = html.replace(/{remark}/g, '');
        html = html.replace(/{coefficient}/g, '');
        $("#tbody_factor").append(html);
    }

    //保存调整因素
    surePrice.saveAdjustFactor = function () {
        var factorArray = [];
        $("#tbody_factor tr").each(function () {
            var adjustFactor = {};
            adjustFactor.factor = $(this).find('[name=factor]').val();
            adjustFactor.remark = $(this).find('[name=remark]').val();
            adjustFactor.type = $(this).find('[name=type]').val();
            if (adjustFactor.type == 0) {
                adjustFactor.coefficient = $(this).find('[name=coefficient-absolute]').val();
            } else {
                adjustFactor.coefficient = $(this).find('[name=coefficient-relative]').attr('data-value');
            }
            factorArray.push(adjustFactor);
        });
        var judgeObjectId = $("#modal_factor").find('[name=judgeObjectId]').val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/saveSurePriceFactor",
            data: {
                judgeObjectId: judgeObjectId,
                price: $('#sure_price_form').find('[name=price]').val(),
                formData: JSON.stringify(factorArray)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "保存成功");
                    var tr = $('#adjust_factor_table').find('[data-id=' + judgeObjectId + ']');
                    tr.find('[data-name=price]').text(result.data.price);
                    tr.find('[data-name=coefficient]').text(result.data.factor);
                    $("#modal_factor").modal('hide');
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //checkbox选择切换
    surePrice.checkboxToggle = function (_this) {
        $(_this).closest('table').find(':checkbox').prop('checked', $(_this).prop('checked'));
    }

    //被复制对象id
    surePrice.beCopyJudgeObject = undefined;

    //调整因素复制
    surePrice.copy = function (_this) {
        //1.设置被复制元素的id
        //2.隐藏当前复制按钮，显示取消复制按钮
        //3.其它权证数据的复制按钮隐藏，显示出粘贴按钮
        var tr = $(_this).closest('tr');
        surePrice.beCopyJudgeObject = {};
        surePrice.beCopyJudgeObject.id = tr.attr('data-id');
        surePrice.beCopyJudgeObject.price = tr.find('[data-name=price]').text();
        surePrice.beCopyJudgeObject.coefficient = tr.find('[data-name=coefficient]').text();
        $(_this).hide();
        var td = $(_this).closest('td');
        td.find('.cancel-copy').show();
        td.find('.paste').hide();
        tr.siblings().each(function () {
            $(this).find('.copy,.cancel-copy').hide();
            $(this).find('.paste').show();
        })
    }

    //调整因素取消复制
    surePrice.cancelCopy = function (_this) {
        surePrice.beCopyJudgeObject = undefined;
        var tr = $(_this).closest('tr');
        $(_this).hide();
        var td = $(_this).closest('td');
        td.find('.copy').show();
        td.find('.paste,.cancel-copy').hide();
        tr.siblings().each(function () {
            $(this).find('.copy').show();
            $(this).find('.paste,.cancel-copy').hide();
        })
    }

    //调整因素粘贴
    surePrice.paste = function (_this) {
        if (!surePrice.beCopyJudgeObject) {
            notifyInfo('提示', '请选择被复制对象');
            return false;
        }
        var judgeObjectId = $(_this).closest('tr').attr('data-id');
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/copySurePriceFactor",
            data: {
                judgeObjectId: judgeObjectId,
                beCopyJudgeObjectId: surePrice.beCopyJudgeObject.id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "保存成功");
                    //更新元素信息
                    $(_this).closest('tr').find('[data-name=price]').text(surePrice.beCopyJudgeObject.price);
                    $(_this).closest('tr').find('[data-name=coefficient]').text(surePrice.beCopyJudgeObject.coefficient);
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    }

    //调整因素批量粘贴
    surePrice.pasteBatch = function () {
        if (!surePrice.beCopyJudgeObject) {
            notifyInfo('提示', '请选择被复制对象');
            return false;
        }
        var judgeObjectIdArray = [];
        $("#adjust_factor_table tbody").find(':checkbox:checked').each(function () {
            var id = $(this).closest('tr').attr('data-id');
            if (id != surePrice.beCopyJudgeObject.id) {
                judgeObjectIdArray.push(id);
            }
        })
        if (judgeObjectIdArray.length <= 0) {
            notifyInfo('提示', '请选择勾选参与复制对象');
            return false;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/copySurePriceFactorBatch",
            data: {
                judgeObjectIds: judgeObjectIdArray.join(),
                beCopyJudgeObjectId: surePrice.beCopyJudgeObject.id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "保存成功");
                    //更新元素信息
                    $("#adjust_factor_table tbody").find(':checkbox:checked').each(function () {
                        $(this).closest('tr').find('[data-name=price]').text(surePrice.beCopyJudgeObject.price);
                        $(this).closest('tr').find('[data-name=coefficient]').text(surePrice.beCopyJudgeObject.coefficient);
                    })
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    }

    //生成并下载模板
    surePrice.exportData = function () {
        var pid = ${projectPlanDetails.judgeObjectId};
        var href = "${pageContext.request.contextPath}/schemeSurePrice/generateAndExport";
        href += "?pid=" + pid;

        window.open(href, "");
    }
    //导入
    surePrice.importData = function () {
        Loading.progressShow();
        $.ajaxFileUpload({
            type: "POST",
            url: getContextPath() + "/schemeSurePrice/importData",
            data: {
                pid: ${projectPlanDetails.judgeObjectId}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadBtn',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", result.data);
                    surePrice.loadAdjustJudgeObject('${projectPlanDetails.judgeObjectId}');
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    surePrice.getHuxingId = function (judgeObjectId) {
        $.ajax({
            url: getContextPath() + "/schemeSurePrice/getUnitHuxing",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: judgeObjectId},
            success: function (result) {
                if (result.ret) {
                    if (houseHuxingPrice.prototype.isNotNull(result.data)) {
                        $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val(result.data.id);
                        houseHuxingPrice.prototype.showTableModel();
                    } else {
                        notifyInfo("提示", "标准房号未关联单价表")
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>

<div id="divBoxUnitHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form id="frmUnitHuxingPriceTable" class="form-horizontal">
                    <input type="hidden" name="unitHuxingId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline form-inline x-valid">
                                            <button type="button" style="margin-left: 5px" class="btn btn-success btn-sm"
                                                    onclick="houseHuxingPrice.prototype.showModel()"
                                                    data-toggle="modal" href="#divBoxUnitHuxingPrice">
                                                <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                                            </button>
                                            <div class="btn-group">
                                                <button type="button" style="margin-left: 5px" class="btn btn-info btn-sm dropdown-toggle"
                                                        data-toggle="dropdown">
                                                     <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
                                                         </span>导入数据
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a class="btn"
                                                           onclick="houseHuxingPrice.prototype.generateHuxingPrice()">下载模板</a>
                                                    </li>
                                                    <li>
                                                        <a class="btn"
                                                           onclick="$('#ajaxFileUpload').val('').trigger('click')"><span>导入</span></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="UnitHuxingPriceList">
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
            </div>

        </div>
    </div>
</div>
<div id="divBoxUnitHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                <form id="frmUnitHuxingPrice" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="huxingId">
                    <input type="hidden" name="declareName">
                    <input type="hidden" name="declareId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房号
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="房号" name="houseNumber"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                面积
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="建筑面积" name="area"
                                                       data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                价格
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="价格" name="price" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                因素
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="因素" name="adjustFactor"
                                                       class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseHuxingPrice.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;"
       onchange="houseHuxingPrice.prototype.importHuxingPrice();">
</html>
<script type="application/javascript">
    houseHuxingPrice = function () {
    };
    houseHuxingPrice.prototype = {
        config: function () {
            var data = {};
            data.table = "UnitHuxingPriceList";
            data.box = "divBoxUnitHuxingPrice";
            data.frm = "frmUnitHuxingPrice";
            data.tableBox = "divBoxUnitHuxingPriceTable";
            data.tableFrm = "frmUnitHuxingPriceTable";
            return data;
        },
        loadDataDicList: function (unitHuxingId) {
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'price', title: '价格'});
            cols.push({field: 'adjustFactor', title: '因素'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseHuxingPrice.prototype.getAndInit(' + row.id + ',\'' + unitHuxingId + '\',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseHuxingPrice.prototype.removeData(' + row.id + ',\'' + unitHuxingId + '\',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHuxingPrice.prototype.config().table, getContextPath() + "/basicUnitHuxingPrice/getUnitHuxingPriceList", cols, {
                unitHuxingId: unitHuxingId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id, unitHuxingId) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxingPrice/deleteBasicUnitHuxingPrice",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        houseHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showTableModel: function () {
            var unitHuxingId = $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
            houseHuxingPrice.prototype.loadDataDicList(unitHuxingId);
            $('#' + houseHuxingPrice.prototype.config().tableBox).modal("show");
        },
        showModel: function () {
            $("#" + houseHuxingPrice.prototype.config().frm).clearAll();
            var unitHuxingId = $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
            $("#" + houseHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);

            $('#' + houseHuxingPrice.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + houseHuxingPrice.prototype.config().frm).valid()) {
                return false;
            }
            var unitHuxingId = $("#" + houseHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val()
            var data = formParams(houseHuxingPrice.prototype.config().frm, true);
            $.ajax({
                url: getContextPath() + "/basicUnitHuxingPrice/saveAndUpdateBasicUnitHuxingPrice",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + houseHuxingPrice.prototype.config().box).modal('hide');
                        houseHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                    }
                    else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getAndInit: function (id, unitHuxingId) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxingPrice/getBasicUnitHuxingPriceById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (houseHuxingPrice.prototype.isNotNull(data)) {
                            houseHuxingPrice.prototype.init(data, unitHuxingId);
                        } else {
                            houseHuxingPrice.prototype.init({});
                        }
                        $('#' + houseHuxingPrice.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item, unitHuxingId) {
            $("#" + houseHuxingPrice.prototype.config().frm).clearAll();
            $("#" + houseHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);
            $("#" + houseHuxingPrice.prototype.config().frm).initForm(item);

        },
        importHuxingPrice: function () {
            var unitHuxingId = $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/schemeSurePrice/importHuxingPrice",
                data: {
                    huxingId: unitHuxingId
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUpload',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    if (result.ret) {
                        houseHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                        notifySuccess("成功", result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        //生成并下载模板
        generateHuxingPrice: function () {
            var href = "${pageContext.request.contextPath}/schemeSurePrice/generateHuxingPrice";
            window.open(href, "");
        }

    }
</script>
</html>

