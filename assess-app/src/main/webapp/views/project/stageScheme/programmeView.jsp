<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h1>
                        ${projectInfo.projectName}-方案编制
                    </h1>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>

            <c:forEach items="${areaGroups}" var="item">
                <div class="x_panel area_panel">
                    <input type="hidden" name="areaGroupId" value="${item.id}">
                    <div class="x_title collapse-link" onclick="programme.loadJudgeObjectList(this);">
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
                                        评估基准日
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">
                                            <fmt:formatDate value="${item.valueTimePoint}" pattern="yyyy-MM-dd"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        基准日说明
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.timePointExplain}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.entrustPurposeName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托目的描述
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.remarkEntrustPurpose}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        价值类型
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.valueDefinitionName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产范围
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.propertyScopeName}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产包括
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.scopeInclude}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        财产不包括
                                    </label>
                                    <div class="col-sm-2">
                                        <label class="form-control">${item.scopeNotInclude}</label>
                                    </div>
                                </div>
                            </div>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width: 5%">编号</th>
                                        <%--<th style="width: 10%">权证号</th>--%>
                                    <th style="width: 5%">所有权人</th>
                                    <th style="width: 15%">坐落</th>
                                    <th style="width: 6%">终止日期</th>
                                    <th style="width: 6%">剩余年限</th>
                                    <th style="width: 6%">证载用途</th>
                                    <th style="width: 6%">实际用途</th>
                                    <th style="width: 6%">设定用途</th>
                                    <th style="width: 10%">最佳利用方式</th>
                                    <th style="width: 5%">证载面积</th>
                                    <th style="width: 5%">评估面积</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <%--<div class="x_panel">--%>
            <%--<div class="x_title collapse-link">--%>
            <%--<ul class="nav navbar-right panel_toolbox">--%>
            <%--<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>--%>
            <%--</ul>--%>
            <%--<h3>他项权利</h3>--%>
            <%--<div class="clearfix"></div>--%>
            <%--</div>--%>
            <%--<div class="x_content collapse">--%>
            <%--<table class="table table-bordered" id="tb_inventory_right_list">--%>
            <%--</table>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="divBoxMethodExtend" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估方法</h3>
            </div>
            <div class="modal-body">
                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                        <c:forEach items="${dataDicMethodList}" var="item" varStatus="status">
                            <li role="presentation" ${status.index==0?'class="active"':''} >
                                <a href="#tab_content${item.id}" id="tab${item.id}" role="tab"
                                   data-toggle="tab" aria-expanded="true">${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <c:forEach items="${dataDicMethodList}" var="method" varStatus="status">
                            <div role="tabpanel" class="tab-pane fade ${status.index==0?'active in':''} "
                                 id="tab_content${method.id}"
                                 aria-labelledby="home-tab">
                                <form id="frm_method_${method.id}" class="form-horizontal" data-name="${method.name}">
                                    <input type="hidden" name="id" value="0">
                                    <input type="hidden" name="judgeObjectId">
                                    <input type="hidden" name="name" value="${method.name}">
                                    <input type="hidden" name="methodType" value="${method.id}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <div class="col-sm-10 col-sm-offset-2">
                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,true)"
                                                       readonly="readonly"
                                                       name="bisApplicable" id="rdoApplicable${method.id}" value="true">
                                                <label for="rdoApplicable${method.id}">适用</label>
                                                </span>

                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,false)"
                                                       readonly="readonly"
                                                       name="bisApplicable" id="rdoNotApplicable${method.id}"
                                                       value="false">
                                                <label for="rdoNotApplicable${method.id}">不适用</label>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group ">
                                                <label class="col-sm-2 control-label">
                                                    方法适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="方法适用原因" name="applicableReason"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="applicableReason-field">

                                            </div>
                                        </div>
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    思路适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="思路适用原因" name="applicableThinking"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="applicableThinking-field"></div>
                                        </div>
                                    </div>
                                    <div class="not-applicable" style="display: none;">
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    方法不适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                        <textarea required placeholder="方法不适用原因" name="notApplicableReason"
                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="notApplicableReason-field"></div>
                                        </div>
                                        <div class="well">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">
                                                    思路不适用原因
                                                </label>
                                                <div class="x-valid">
                                                    <div class="col-sm-10">
                                                        <textarea required placeholder="思路不适用原因"
                                                                  name="notApplicableThinking"
                                                                  class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="notApplicableThinking-field"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<!--查看他项权利信息-->
<div id="viewInventoryRightModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利信息</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">类型</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="typeName"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">类别</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="categoryName"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">他权证编号</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="number"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">登记日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerDate"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        义务人
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="obligor"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        权利人
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="obligee"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        登记金额
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerAmount"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        行权金额
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="actualAmount"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        登记面积
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="registerArea"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        他权级次
                                    </label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="rightRank"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">开始日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="beginDate"></label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">结束日期</label>
                                    <div class="col-sm-4">
                                        <label class="form-control" data-name="endDate"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        备注
                                    </label>
                                    <div class="col-sm-10">
                                        <label class="form-control" data-name="remark"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        附件
                                    </label>
                                    <div class="col-sm-10">
                                        <div id="_inventoryRightFile"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<!--查看调查信息-->
<div id="viewExamineInfoModal" class="modal fade bs-example-modal-xs" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-xs">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <div class="x_content">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<!--查看合并的委估对象明细-->
<div id="viewMergeJudgeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tb_judge_detail_list">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>
<div id="modal_method_info" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估方法</h3>
            </div>
            <div class="modal-body">
                <form id="frm_method_info" class="form-horizontal">
                    <input type="hidden" name="judgeObjectId">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="20%">适用方法</th>
                                    <th width="60%">适用原因</th>
                                </tr>
                                </thead>
                                <tbody id="applicableTbody">
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th width="20%">不适用方法</th>
                                    <th width="60%">不适用原因</th>
                                </tr>
                                </thead>
                                <tbody id="notApplicableTbody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
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
            <input type="hidden" data-name="rentalPossessionDesc" value="{rentalPossessionDesc}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <td><label class="form-control" data-name="ownership">{ownership}
            <a href="javascript://" onclick="programme.viewJudgeInfo(this);"
               class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>
        </label></td>
        <td><label class="form-control" data-name="seat">{seat}</label></td>
        <td><label class="form-control" data-name="landUseEndDate"><span>{landUseEndDate}</span></label></td>
        <td><label class="form-control" data-name="landRemainingYear"><span>{landRemainingYear}</span></label></td>
        <td><label class="form-control" data-name="certUse">{certUse}</label></td>
        <td><label class="form-control" data-name="practicalUse">{practicalUse}</label></td>
        <td><label class="form-control" data-name="setUse">{setUseName}</label></td>
        <td><label class="form-control" data-name="setUse">{bestUseName}</label></td>
        <td><label class="form-control">{floorArea}</label></td>
        <td><label class="form-control">{evaluationArea}</label></td>
        <td>
            <a href="javascript://" onclick="programmeMethod.setMethod(this);"
               class="btn btn-xs btn-primary judge-method tooltips">评估方法</a>
        </td>
    </tr>
</script>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".area_panel .x_title").each(function () {
            $(this).trigger('click');
        })
        programme.loadInventoryRightList();

    })

    //方案
    var programme = {};
    programme.config = {
        areaPopIndex: 0,//区域弹框index
        judgePopIndex: 0,//委估对象弹框index
        //区域合并项html
        areaItemHtml: '<li data-areaGroupId="{areaGroupId}"> <p> <label>{areaName}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
        //委估对象合并项html
        judgeItemHtml: '<li data-judgeId="{judgeId}"> <p> <label>{name}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
        currJudgeMethodButton: undefined //当前评估方法button
    };

    //加载区域下的委估对象列表
    programme.loadJudgeObjectList = function (_this) {
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
                        html = html.replace(/{rentalPossessionDesc}/g, item.rentalPossessionDesc == undefined ? "" : item.rentalPossessionDesc);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace(/{name}/g, item.name == undefined ? "" : item.name);
                        html = html.replace(/{declareId}/g, item.declareRecordId == undefined ? "" : item.declareRecordId);
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : AssessCommon.substring(item.ownership, 100));
                        html = html.replace(/{seat}/g, item.seat == undefined ? "" : AssessCommon.substring(item.seat, 100));
                        html = html.replace(/{landUseEndDate}/g, item.landUseEndDate == undefined ? "" : formatDate(item.landUseEndDate));
                        html = html.replace(/{landRemainingYear}/g, item.landRemainingYear == undefined ? "" : item.landRemainingYear);
                        html = html.replace(/{certUse}/g, item.certUse == undefined ? "" : item.certUse);
                        html = html.replace(/{practicalUse}/g, item.practicalUse == undefined ? "" : item.practicalUse);
                        html = html.replace(/{setUseName}/g, item.setUseName == undefined ? "" : item.setUseName);
                        html = html.replace(/{bestUseName}/g, item.bestUseName == undefined ? "" : item.bestUseName);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                        tbody.append(html);
                        //设值
                        var lastTr = tbody.find("tr:last");
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
    };


    //加载合并对象的明细
    programme.loadJudgeDetailList = function (pid) {
        var cols = [];
        cols.push({field: 'number', title: '编号'});
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'ownership', title: '所有权人'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        cols.push({field: 'practicalUse', title: '实际用途'});
        cols.push({field: 'floorArea', title: '证载面积'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="programme.viewJudgeDetailExamineInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_judge_detail_list").bootstrapTable('destroy');
        TableInit("tb_judge_detail_list", "${pageContext.request.contextPath}/schemeProgramme/getJudgeObjectListByPid", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: true,
            search: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //查看他项信息
    programme.viewInventoryRightInfo = function (index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#viewInventoryRightModal").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        $("#viewInventoryRightModal").find('[data-name=registerDate]').text(formatDate(row.registerDate, false));
        $("#viewInventoryRightModal").find('[data-name=beginDate]').text(formatDate(row.beginDate, false));
        $("#viewInventoryRightModal").find('[data-name=endDate]').text(formatDate(row.endDate, false));
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: row.id
            },
            deleteFlag: false
        });
        $("#viewInventoryRightModal").modal();
    };

    //查看委估对象明细
    programme.viewJudgeDetailExamineInfo = function (index) {
        var row = $("#tb_judge_detail_list").bootstrapTable('getData')[index];
        programme.viewExamineInfo(row.declareRecordId);
    };

    //查看委估对象相关信息
    programme.viewJudgeInfo = function (_this) {
        var tr = $(_this).closest('tr');
        var bisMerge = tr.find('[data-name=bisMerge]').val();
        if (bisMerge == 'true') {
            var pid = tr.find('[data-name=id]').val();
            programme.loadJudgeDetailList(pid);
            $("#viewMergeJudgeModal").modal();
        } else {
            programme.viewExamineInfo(tr.find('[data-name=declareId]').val());
        }
    };

    //查看其它信息
    programme.viewOtherInfo = function (_this) {
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJugdeObjectById',
            type: 'get',
            data: {
                judgeObjectId: $(_this).closest('tr').find("[data-name=id]").val()
            },
            success: function (result) {
                if (result.ret) {
                    $("#frm_other_info").clearAll().initForm(result.data);
                    $("#modal_other_info").modal();
                } else {
                    toastr.error(result.errmsg);
                }
            }
        })
    }

    //查看委估对象调查信息
    programme.viewExamineInfo = function (declareId) {
        layer.open({
            type: 1,
            title: "调查信息",
            offset: 't',
            shade: false,
            area: ['720px', '450px'], //宽高
            content: '<table id="examine_list" class="table table-bordered" style="max-height: auto;"></table>',
            success: function () {
                $("#examine_list").treegrid({
                        url: '${pageContext.request.contextPath}/schemeProgramme/getPlanDetailsByDeclareId?declareId=' + declareId,
                        method: 'get',
                        idField: 'id',
                        treeField: 'projectPhaseName',
                        datatype: 'json',
                        lines: true,
                        width: 'auto',
                        rownumbers: true,
                        onLoadSuccess: function () {
                            $(".tooltips").tooltip();
                        },
                        columns: [[
                            {
                                field: "projectPhaseName",
                                title: "工作内容",
                                width: "70%",
                                align: "left",
                                formatter: function (value, row) {
                                    return value
                                }
                            },
                            {
                                field: 'workStages', title: '操作', width: '30%', formatter: function (value, row) {
                                if (row.bisEnable) {
                                    var s = "";
                                    if (row.displayUrl) {
                                        s += " <a target='_blank' href='" + row.displayUrl + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-warning tooltips' ><i class='fa fa-search fa-white'></i></a>";
                                    }
                                    return s;
                                }
                            }
                            }
                        ]]
                    }
                );
            }
        });
    };

    //加载他项权利
    programme.loadInventoryRightList = function () {
        var cols = [];
        cols.push({field: 'certName', title: '权证号'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类型'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="programme.viewInventoryRightInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_inventory_right_list").bootstrapTable('destroy');
        TableInit("tb_inventory_right_list", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByProjectId", cols, {
            projectId: '${projectInfo.id}'
        }, {
            showColumns: false,
            showRefresh: true,
            search: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //查看描述内容
    programme.viewRentalPossessionDesc = function (_this) {
        var rentalPossessionDesc = $(_this).closest('tr').find('[data-name=rentalPossessionDesc]').val();
        layer.open({
            title: '出租或占用情况描述'
            , content: rentalPossessionDesc
        });
    }

</script>
<script type="text/javascript">
    var programmeMethod = {};

    programmeMethod.setMethod = function (_this) {
        $('#applicableTbody,#notApplicableTbody').empty();
        var judgeObjectId = $(_this).closest("tr").find('[data-name="id"]').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getJudgeFunction',
            data: {
                judgeObjectId: judgeObjectId
            },
            success: function (result) {
                if (result.ret && result.data) {
                    var htmlTemplate = '<tr data-method-type="{methodType}"><td>{methodName}</td>' +
                        '<td><div class="x-valid"> <label class="form-control">{content}</label></div></td></tr>';
                    $.each(result.data.judgeFunctions, function (i, item) {
                        var trHtml = new String(htmlTemplate);
                        trHtml = trHtml.replace(/{methodType}/g, item.methodType).replace(/{methodName}/g, item.name);
                        if (item.bisApplicable == true) {
                            $("#applicableTbody").append(trHtml.replace(/{content}/g, AssessCommon.toString(item.applicableReason)));
                        } else {
                            $("#notApplicableTbody").append(trHtml.replace(/{content}/g, AssessCommon.toString(item.notApplicableReason)));
                        }
                    })
                }
            }
        })
        $("#modal_method_info").modal();
    }
</script>


