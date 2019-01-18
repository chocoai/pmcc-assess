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
                                        评估基准日<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="valueTimePoint" required="required" placeholder="评估基准日"
                                               data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                               readonly="readonly" pattern='yyyy-MM-dd'
                                               value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}"
                                   pattern="yyyy-MM-dd"/>">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        基准日说明<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="timePointExplain" required="required"
                                               placeholder="基准日说明" class="form-control"
                                               value="${item.timePointExplain}">
                                    </div>
                                </div>
                            </div>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width: 5%">编号</th>
                                    <%--<th style="width: 10%">权证号</th>--%>
                                    <%--<th style="width: 5%">所有权人</th>--%>
                                    <th style="width: 15%">坐落</th>
                                    <th style="width: 10%">证载用途</th>
                                    <th style="width: 10%">实际用途</th>
                                    <th style="width: 10%">设定用途</th>
                                    <th style="width: 10%">最佳利用描述</th>
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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>他项权利</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <table class="table table-bordered" id="tb_inventory_right_list">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-warning" onclick="programme.saveProgrammeAll();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="programme.submitProgramme();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
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
                                                <input type="radio" required onclick="applicableChange(this,true)" readonly="readonly"
                                                       name="bisApplicable" id="rdoApplicable${method.id}" value="true">
                                                <label for="rdoApplicable${method.id}">适用</label>
                                                </span>

                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,false)" readonly="readonly"
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
                                                    方法适用原因<span class="symbol required"></span>
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
                                                    思路适用原因<span class="symbol required"></span>
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
                                                    方法不适用原因<span class="symbol required"></span>
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
                                                    思路不适用原因<span class="symbol required"></span>
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
<!--动态字段-->
<script type="text/html" id="dynamicFieldHtml">
    <label class="col-sm-2 control-label">
        {name}
    </label>
    <div class="x-valid">
        <div class="col-sm-4">
            <input type="text" class="form-control" data-name="{name}" onkeyup="{functionName}(this);">
        </div>
    </div>
</script>
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
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <%--<td>--%>
            <%--<label class="form-control" data-name="name">--%>
                <%--<span>{name}</span>--%>
                <%--<a href="javascript://" onclick="programme.viewJudgeInfo(this);"--%>
                   <%--class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>--%>
            <%--</label>--%>

        <%--</td>--%>
        <%--<td><label class="form-control" data-name="ownership">{ownership}</label></td>--%>
        <td><label class="form-control" data-name="seat">{seat}</label></td>
        <td><label class="form-control" data-name="certUse">{certUse}</label></td>
        <td><label class="form-control" data-name="practicalUse">{practicalUse}</label></td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="setUse" name="setUse{id}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${setUseList}" var="setUse">
                        <option value="${setUse.id}">${setUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="bestUse" name="bestUse{id}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${bestUseList}" var="bestUse">
                        <option value="${bestUse.id}">${bestUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-number="true"
                       name="evaluationArea{id}" data-name="evaluationArea"
                       placeholder="评估面积" value="{evaluationArea}">
            </div>
        </td>
        <td>
            <a href="javascript://" onclick="setEvaluationMethod(this);"
               class="btn btn-xs btn-success judge-method tooltips">评估方法</a>
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
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace(/{name}/g, item.name == undefined ? "" : item.name);
                        html = html.replace(/{declareId}/g, item.declareRecordId == undefined ? "" : item.declareRecordId);
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : item.ownership);
                        html = html.replace(/{seat}/g, item.seat == undefined ? "" : item.seat);
                        html = html.replace(/{certUse}/g, item.certUse == undefined ? "" : item.certUse);
                        html = html.replace(/{practicalUse}/g, item.practicalUse == undefined ? "" : item.practicalUse);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                        tbody.append(html);
                        //设值
                        var lastTr = tbody.find("tr:last");
                        lastTr.find('[data-name="setUse"]').val(item.setUse);
                        lastTr.find('[data-name="bestUse"]').val(item.bestUse);
                        lastTr.find('td:last').find(item.bisSplit ? '.judge-split' : '.judge-remove').remove();
                        lastTr.find('td:last').find(item.bisMerge ? '.judge-merge' : '.judge-merge-cancel').remove();
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

</script>
<script type="text/javascript">
    /*
     *------------------------------------------------------------------------------------------------------
     *评估方法设置相关
     *------------------------------------------------------------------------------------------------------
     */

    //方法适用原因字段替换
    function methodApplicableFieldReplace(_this) {
        //1.先找到模板 2.再依次找到字段填写的信息
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="methodTemplate"]').find('option:selected').attr("data-applicable");
        tabPane.find('.applicableReason-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="applicableReason"]').val(template);
    }

    //方法不适用原因字段替换
    function methodNotApplicableFieldReplace(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="methodTemplate"]').find('option:selected').attr("data-not-applicable");
        tabPane.find('.notApplicableReason-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="notApplicableReason"]').val(template);
    }

    //思路适用原因字段替换
    function thinkingApplicableFieldReplace(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="thinkingTemplate"]').find('option:selected').attr("data-applicable");
        tabPane.find('.applicableThinking-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="applicableThinking"]').val(template);
    }

    //思路不适用原因字段替换
    function thinkingNotApplicableFieldReplace(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var template = tabPane.find('[name="thinkingTemplate"]').find('option:selected').attr("data-not-applicable");
        tabPane.find('.notApplicableThinking-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        tabPane.find('[name="notApplicableThinking"]').val(template);
    }

    //创建动态字段html
    function createDynaicFieldHtml(fieldArray, functionName) {
        if (fieldArray) {
            var resultHtml = '<div class="form-group">';
            $.each(fieldArray, function (i, item) {
                if (i > 0 && i % 2 == 0) {
                    resultHtml += '</div><div class="form-group">';
                }
                var templateHtml = $("#dynamicFieldHtml").html();
                templateHtml = templateHtml.replace(/{name}/g, item).replace(/{functionName}/, functionName);
                resultHtml += templateHtml;
            })
            resultHtml += '</div>';
            return resultHtml;
        } else {
            return '';
        }
    }

    //方法信息是否填写完整
    function methodHasWriteFull(judgeFunction) {
        if (judgeFunction) {
            if (judgeFunction.bisApplicable == undefined) return false;
            if (judgeFunction.bisApplicable == 'true') {
                if (!judgeFunction.applicableReason || !judgeFunction.applicableThinking) return false;
            } else {
                if (!judgeFunction.notApplicableReason || !judgeFunction.notApplicableThinking) return false;
            }
        }
        return true;
    }

    //设置评估方法
    function setEvaluationMethod(_this) {
        var judgeObjectId = $(_this).closest("tr").find('[data-name="id"]').val();
        $("#myTabContent").find('[name="judgeObjectId"]').val(judgeObjectId);
        programme.config.currJudgeMethodButton = $(_this);
        //还原数据状态
        cleanEvaluationMethod();
        //如果该估计对象已经设置过评估方法，则将数据填充回去
        $.ajax({
            url: '${pageContext.request.contextPath}/schemeProgramme/getSchemeJudgeFunctions',
            data: {
                judgeObjectId: judgeObjectId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    $.each(result.data, function (i, item) {
                        var methodTypeEle = $("#myTabContent").find('.tab-pane').find('[name="methodType"][value="' + item.methodType + '"]')
                        var tabPane = $(methodTypeEle).closest(".tab-pane");
                        tabPane.find('[name="id"]').val(item.id);
                        if (item.bisApplicable) {
                            tabPane.find('[name="bisApplicable"][value="true"]').prop('checked', true);
                            tabPane.find('.applicable').show();
                        } else {
                            tabPane.find('[name="bisApplicable"][value="false"]').prop('checked', true);
                            tabPane.find('.not-applicable').show();
                        }
                        tabPane.find('[name="applicableReason"]').val(item.applicableReason);
                        tabPane.find('[name="notApplicableReason"]').val(item.notApplicableReason);
                        tabPane.find('[name="applicableThinking"]').val(item.applicableThinking);
                        tabPane.find('[name="notApplicableThinking"]').val(item.notApplicableThinking);
                    })
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
        $("#divBoxMethodExtend").modal();
    }

    //清空
    function cleanEvaluationMethod() {
        $("#myTab").find('a:first').tab('show');
        $("#myTabContent").find('form').each(function () {
            $(this).clearValid();
        })
        $("#myTabContent").find('[name="id"]').val('0');
        $("#myTabContent").find('[name="bisApplicable"]').attr("checked", false);
        $("#myTabContent").find('[name="applicableReason"]').val('');
        $("#myTabContent").find('[name="notApplicableReason"]').val('');
        $("#myTabContent").find('[name="applicableThinking"]').val('');
        $("#myTabContent").find('[name="notApplicableThinking"]').val('');

        $("#myTabContent").find('[name="methodTemplate"]').val('');
        $("#myTabContent").find('[name="thinkingTemplate"]').val('');

        $("#myTabContent").find('.applicable').hide();
        $("#myTabContent").find('.not-applicable').hide();
        $("#myTabContent").find('.applicableReason-field').empty();
        $("#myTabContent").find('.applicableThinking-field').empty();
        $("#myTabContent").find('.notApplicableReason-field').empty();
        $("#myTabContent").find('.notApplicableThinking-field').empty();
    }

    //评估方法模板选项change
    function evaluationMethodChange(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var bisApplicable = tabPane.find('[name=bisApplicable]:checked').val();
        var option = $(_this).find('option:selected');
        tabPane.find('.applicableReason-field').empty();
        tabPane.find('.notApplicableReason-field').empty();
        if (bisApplicable == "true") {
            tabPane.find('[name="applicableReason"]').val(option.attr("data-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodApplicableFieldReplace');
                tabPane.find('.applicableReason-field').append(html);
            }
        } else if (bisApplicable == "false") {
            tabPane.find('[name="notApplicableReason"]').val(option.attr("data-not-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-not-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodNotApplicableFieldReplace');
                tabPane.find('.notApplicableReason-field').append(html);
            }
        }
    }

    //评估思路模板选项change
    function evaluationThinkingChange(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var bisApplicable = tabPane.find('[name=bisApplicable]:checked').val();
        var option = $(_this).find('option:selected');
        tabPane.find('.applicableThinking-field').empty();
        tabPane.find('.notApplicableThinking-field').empty();
        if (bisApplicable == "true") {
            tabPane.find('[name="applicableThinking"]').val(option.attr("data-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'thinkingApplicableFieldReplace');
                tabPane.find('.applicableThinking-field').append(html);
            }
        } else if (bisApplicable == "false") {
            tabPane.find('[name="notApplicableThinking"]').val(option.attr("data-not-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-not-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'thinkingNotApplicableFieldReplace');
                tabPane.find('.notApplicableThinking-field').append(html);
            }
        }
    }

    //适用切换
    function applicableChange(_this, isApplicable) {
        var tabPane = $(_this).closest(".tab-pane");
        var thisRadio = $(_this).find('input:radio');
        thisRadio.attr('checked', true);
        $(_this).closest('.btn-group').find('input:radio').not(thisRadio).attr('checked', false);
        if (isApplicable) {
            tabPane.find('.applicable').show();
            tabPane.find('.not-applicable').hide();
        } else {
            tabPane.find('.applicable').hide();
            tabPane.find('.not-applicable').show();
        }
    }
</script>


