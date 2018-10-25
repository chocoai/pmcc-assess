<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <div class="x_panel">
                <form  class="form-horizontal">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>方法名称</th>
                            <th>试算价格</th>
                            <th>权重</th>
                        </tr>
                        </thead>
                        <tbody id="tbody_data_section1">
                        </tbody>
                    </table>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                最终单价
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="price" class="form-control" readonly="readonly">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>

<%--确定单价--%>
<div id="modal_sure_price" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">确定单价</h3>
            </div>
            <form id="frm_data_section" class="form-horizontal">
                <input type="hidden" name="judgeObjectId">
                <div class="modal-body">

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
                            <label class="col-sm-2 control-label">
                                最终单价
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="price" class="form-control" readonly="readonly">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="determinePrice.savePrice();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">调整因素</h3>
            </div>
            <div class="modal-body">
                <input type="hidden" name="judgeObjectId">
                <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                   onclick="determinePrice.addAdjustFactor();"><i
                        class="fa fa-plus fa-white"></i></a>
                <table class="table">
                    <thead>
                    <tr>
                        <th>因素</th>
                        <th>说明</th>
                    </tr>
                    </thead>
                    <tbody id="tbody_factor">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="determinePrice.saveAdjustFactor();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
</body>

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
                   onblur="determinePrice.computePrice(this);">
        </td>
    </tr>
</script>

<%--调整因素模板--%>
<script type="text/html" id="adjustFatorTemp">
    <tr>
        <td>
            <input type="text" class="form-control x-percent" name="factor" data-value="{factor}">
        </td>
        <td><input type="text" class="form-control" name="remark" value="{remark}"></td>
        <td>
            <a class="btn btn-xs btn-warning tooltips" data-placement="top" onclick="$(this).closest('tr').remove();"><i
                    class="fa fa-minus fa-white"></i></a>
        </td>
    </tr>
</script>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>

<script type="application/javascript">
    function submit() {
        var data = {};
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }


    var determinePrice = {};
    //确定单价
    determinePrice.surePrice = function (_this) {
        var tr = $(_this).closest('tr');
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getSchemeSurePriceList",
            data: {
                judgeObjectId: tr.find('[name=id]').val()
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
                    })
                    $("#tbody_data_section").find('.x-percent').each(function () {
                        AssessCommon.elementParsePercent($(this));
                    })
                    $("#frm_data_section").find('[name=price]').val(tr.find('[data-name=price]').text());
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        $("#frm_data_section").find('[name=judgeObjectId]').val(tr.find('[name=id]').val());
        $("#modal_sure_price").modal();
    }

    //计算单价
    determinePrice.computePrice = function (_this) {
        var resultPrice = 0;
        $(_this).closest('tbody').find('tr').each(function () {
            var trialPrice = $(this).find("[data-name=trialPrice]").text();
            var weight = $(this).find("[name=weight]").attr('data-value');
            if (AssessCommon.isNumber(trialPrice) && AssessCommon.isNumber(weight)) {
                resultPrice += parseFloat(trialPrice) * parseFloat(weight);
            }
        })
        $("#frm_data_section").find('[name=price]').val(resultPrice.toFixed(2));
    }

    //保存单价
    determinePrice.savePrice = function () {
        var surePriceApply = {};
        var judgeObjectId = $("#frm_data_section").find('[name=judgeObjectId]').val();
        surePriceApply.judgeObjectId = judgeObjectId;
        surePriceApply.price = $("#frm_data_section").find('[name=price]').val();
        surePriceApply.surePriceList = [];
        $("#tbody_data_section tr").each(function () {
            var schemeSurePrice = {};
            schemeSurePrice.id = $(this).find('[name=id]').val();
            schemeSurePrice.weight = $(this).find('[name=weight]').attr('data-value');
            surePriceApply.surePriceList.push(schemeSurePrice);
        })

        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/saveSurePrice",
            data: {
                formData: JSON.stringify(surePriceApply)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存成功");
                    $("#panel_judge_objet_list").find('[name=id][value=' + judgeObjectId + ']').closest('tr').find('[data-name="price"]').text(surePriceApply.price);
                    $("#modal_sure_price").modal('hide');
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //调整评估单价
    determinePrice.adjustPrice = function (_this) {
        var judgeObjectId = $(_this).closest('tr').find('[name=id]').val();
        $("#modal_adjustment_price").find('[name=judgeObjectId]').val(judgeObjectId);
        determinePrice.loadJudgeDetailList();
        $("#modal_adjustment_price").modal();
    }

    //加载合并对象的明细
    determinePrice.loadJudgeDetailList = function () {
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'originalPrice', title: '原单价'});
        cols.push({field: 'price', title: '调整单价'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="determinePrice.adjustFactor(' + row.id + ',' + row.declareRecordId + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_judge_detail_list").bootstrapTable('destroy');
        TableInit("tb_judge_detail_list", "${pageContext.request.contextPath}/schemeProgramme/getJudgeObjectListByPid", cols, {
            pid: $("#modal_adjustment_price").find('[name=judgeObjectId]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //调整因素
    determinePrice.adjustFactor = function (judgeObjectId, declareId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getCertAdjustmentFactors",
            data: {
                declareId: declareId
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
                        $("#tbody_factor").append(html);
                    })
                    $("#tbody_factor").find('.x-percent').each(function () {
                        AssessCommon.elementParsePercent($(this));
                    })
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        $("#modal_factor").find('[name=judgeObjectId]').val(judgeObjectId);
        $("#modal_factor").modal();
    }

    //添加调整因素
    determinePrice.addAdjustFactor = function () {
        var html = $("#adjustFatorTemp").html();
        html = html.replace(/{factor}/g, '');
        html = html.replace(/{remark}/g, '');
        $("#tbody_factor").append(html);
    }

    //保存调整因素
    determinePrice.saveAdjustFactor = function () {
        var factorArray = [];
        $("#tbody_factor tr").each(function () {
            var adjustFactor = {};
            adjustFactor.factor = $(this).find('[name=factor]').attr('data-value');
            adjustFactor.remark = $(this).find('[name=remark]').val();
            factorArray.push(adjustFactor);
        })
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/saveCertAdjustmentFactor",
            data: {
                judgeObjectId: $("#modal_factor").find('[name=judgeObjectId]').val(),
                formData: JSON.stringify(factorArray)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存成功");
                    $("#modal_factor").modal('hide');
                    determinePrice.loadJudgeDetailList();
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>

</html>

