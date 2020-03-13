<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
                    <!-- 公共模块end -->
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>方法名称</th>
                                            <th>试算价格</th>
                                            <th>权重</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tbody_data_section">
                                        <c:forEach items="${surePriceItemList}" var="item">
                                            <tr>
                                                <td>${item.methodName}</td>
                                                <td>${item.trialPrice}</td>
                                                <td><fmt:formatNumber value="${item.weight*100}" type="currency" pattern=".00"/>%</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                        </div>
                                    </div>
                                    <c:if test="${not empty schemeSurePrice.weightExplain}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    权重说明
                                                </label>
                                                <div class="col-sm-11">
                                                    <label class="form-control input-full">${schemeSurePrice.weightExplain}</label>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                最终单价
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${schemeSurePrice.price}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:if test="${not empty subJudgeObjectList}">
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
                                                <table id="adjust_factor_table" class="table">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%">权证号</th>
                                                        <th width="10%">证载面积</th>
                                                        <th width="10%">楼层</th>
                                                        <th width="10%">房号</th>
                                                        <th width="10%">价格</th>
                                                        <th width="50%">因素</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${subJudgeObjectList}" var="item">
                                                        <tr data-id="${item.id}">
                                                            <td>${item.name}</td>
                                                            <td>${item.floorArea}</td>
                                                            <td>${item.floor}</td>
                                                            <td>${item.roomNumber}</td>
                                                            <td data-name="price">${item.price}</td>
                                                            <td data-name="coefficient">${item.factor}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </c:if>
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
                    <!-- 公共尾部模块引用 -->
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="application/javascript">
    $(function () {
        loadDocumentSend ();
    })

    function saveform() {
        saveApprovalform("");
    }


    var determinePrice = {};
    //加载合并对象的明细
    determinePrice.loadJudgeDetailList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'originalPrice', title: '原单价'});
        cols.push({field: 'price', title: '调整单价'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="determinePrice.adjustFactor(' + row.id + ',' + row.declareRecordId + ')"><i class="fa fa-search fa-white"></i></a>';
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
                        html = html.replace(/{factor}/g, AssessCommon.toString(AssessCommon.pointToPercent(item.factor)));
                        html = html.replace(/{remark}/g, AssessCommon.toString(item.remark));
                        $("#tbody_factor").append(html);
                    })
                    $("#judgeObjectId").val(judgeObjectId);
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
        $("#modal_factor").modal();
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

</script>
</body>
</html>

