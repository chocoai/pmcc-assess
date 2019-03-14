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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${judgeObjectName}</h3>
                    <div class="clearfix"></div>
                </div>
                <form class="form-horizontal" id="sure_price_form">
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
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                权重说明
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${schemeSurePrice.weightExplain}</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                最终单价
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control">${schemeSurePrice.price}</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <c:if test="${not empty subJudgeObjectList}">
                <div class="x_panel">
                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h3>
                            调整单价
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <table id="adjust_factor_table" class="table">
                        <thead>
                        <tr>
                            <th width="20%">权证号</th>
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
                                <td>${item.floor}</td>
                                <td>${item.roomNumber}</td>
                                <td data-name="price">${item.price}</td>
                                <td data-name="coefficient">${item.coefficient}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="application/javascript">
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

