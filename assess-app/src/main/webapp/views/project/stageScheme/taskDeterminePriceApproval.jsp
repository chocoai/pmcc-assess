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
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <c:forEach items="${areaJudgeObjectVos}" var="item">
                <div class="x_panel area_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                        </ul>
                        <h2>
                            <label>${item.areaGroupName}</label>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content collapse">
                        <form class="form-horizontal">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width: 5%">编号</th>
                                    <th style="width: 10%">权证号</th>
                                    <th style="width: 5%">所有权人</th>
                                    <th style="width: 15%">坐落</th>
                                    <th style="width: 10%">证载用途</th>
                                    <th style="width: 10%">实际用途</th>
                                    <th style="width: 10%">设定用途</th>
                                    <th style="width: 10%">最佳利用描述</th>
                                    <th style="width: 5%">评估单价</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${item.judgeObjectVoList}" var="judgeObject">
                                    <tr>
                                        <td>
                                            <input type="hidden" name="id" value="${judgeObject.id}">
                                            <label class="form-control">${judgeObject.number}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.name}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.ownership}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.seat}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.certUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.practicalUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.setUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control">${judgeObject.bestUse}</label>
                                        </td>
                                        <td>
                                            <label class="form-control" data-name="price">${judgeObject.price}</label>
                                        </td>
                                        <td>
                                            <a href="javascript://" onclick="determinePrice.surePrice(this);"
                                               class="btn btn-xs btn-success judge-split tooltips">查看单价</a>
                                            <c:if test="${judgeObject.bisMerge}">
                                                <a href="javascript://" onclick="determinePrice.adjustPrice(this);"
                                                   class="btn btn-xs btn-success judge-split tooltips">查看评估价</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
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
                <table id="tb_judge_detail_list" class="table">
                </table>
            </div>
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
                </div>
            </form>
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
                <input type="hidden" id="judgeObjectId">
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
            </div>
        </div>
    </div>
</div>

<%--确定单价模板--%>
<script type="text/html" id="surePriceTemp">
    <tr>
        <td>
            <input type="hidden" name="id" value="{id}">
            {methodName}
        </td>
        <td data-name="trialPrice">{trialPrice}</td>
        <td>
            {weight}
        </td>
    </tr>
</script>

<%--调整因素模板--%>
<script type="text/html" id="adjustFatorTemp">
    <tr>
        <td>
            <label>{factor}</label>
        </td>
        <td><label>{remark}</label></td>
    </tr>
</script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
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
                        html = html.replace(/{weight}/g, AssessCommon.toString(AssessCommon.pointToPercent(item.weight) ));
                        $("#tbody_data_section").append(html);
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

    //调整评估单价
    determinePrice.adjustPrice = function (_this) {
        var tr = $(_this).closest('tr');
        determinePrice.loadJudgeDetailList(tr.find('[name=id]').val());
        $("#modal_adjustment_price").modal();
    }

    //加载合并对象的明细
    determinePrice.loadJudgeDetailList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'originalPrice', title: '原单价'});
        cols.push({field: 'price', title: '调整单价'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="determinePrice.adjustFactor('+row.id+',' + row.declareRecordId + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_judge_detail_list").bootstrapTable('destroy');
        TableInit("tb_judge_detail_list", "${pageContext.request.contextPath}/schemeProgramme/getJudgeObjectListByPid", cols, {
            pid: pid
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
    determinePrice.adjustFactor = function (judgeObjectId,declareId) {
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
                        html = html.replace(/{factor}/g, AssessCommon.toString(AssessCommon.pointToPercent(item.factor)));
                        html = html.replace(/{remark}/g, AssessCommon.toString(item.remark));
                        $("#tbody_factor").append(html);
                    })
                    $("#judgeObjectId").val(judgeObjectId);
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
        $("#modal_factor").modal();
    }


</script>
</body>
</html>

