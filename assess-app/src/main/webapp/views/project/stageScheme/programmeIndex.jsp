<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <h2>
                            <label>${item.areaName}</label>
                            <small>
                                <c:if test="${item.bisMerge eq true}">
                                    <button class="btn btn-xs btn-warning btn-area-merge-cancel">
                                        取消合并
                                    </button>
                                </c:if>
                                <c:if test="${item.bisMerge ne true}">
                                    <button class="btn btn-xs btn-warning btn-area-merge">
                                        合并
                                    </button>
                                </c:if>
                            </small>
                        </h2>
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
                                    <th>编号</th>
                                    <th>权证号</th>
                                    <th>所有权人</th>
                                    <th>坐落</th>
                                    <th>证载用途</th>
                                    <th>实际用途</th>
                                    <th>设定用途</th>
                                    <th>最佳利用描述</th>
                                    <th>证载面积</th>
                                    <th>评估面积</th>
                                    <th>操作</th>
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
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="saveProgramme();">
                            保存方案<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="divBoxMethodExtend" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="360">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估方法</h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="currAreaGroupId">
                <input type="hidden" id="currGroupNumber">
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
                                    <input type="hidden" name="name" value="${method.name}">
                                    <input type="hidden" name="methodType" value="${method.id}">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <div class="col-sm-10 col-sm-offset-2">
                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,true)"
                                                       name="bisApplicable" id="rdoApplicable${method.id}" value="true">
                                                <label for="rdoApplicable${method.id}">适用</label>
                                                </span>

                                                <span class="radio-inline">
                                                <input type="radio" required onclick="applicableChange(this,false)"
                                                       name="bisApplicable" id="rdoNotApplicable${method.id}"
                                                       value="false">
                                                <label for="rdoNotApplicable${method.id}">不适用</label>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">
                                            方法模板
                                        </label>
                                        <div class="x-valid">
                                            <div class="col-sm-4">
                                                <select class="form-control" name="methodTemplate"
                                                        onchange="evaluationMethodChange(this);">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${evaluationMethodMap.get(method.id)}"
                                                               var="evaluationMethod">
                                                        <option value="${evaluationMethod.id}"
                                                                data-applicable="${evaluationMethod.applicableReason}"
                                                                data-not-applicable="${evaluationMethod.notApplicableReason}">
                                                                ${evaluationMethod.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            思路模板
                                        </label>
                                        <div class="x-valid">
                                            <div class="col-sm-4">
                                                <select class="form-control" name="thinkingTemplate"
                                                        onchange="evaluationThinkingChange(this);">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${evaluationThinkingMap.get(method.id)}"
                                                               var="evaluationThinking">
                                                        <option value="${evaluationThinking.id}"
                                                                data-applicable="${evaluationThinking.applicableReason}"
                                                                data-not-applicable="${evaluationThinking.notApplicableReason}">
                                                                ${evaluationThinking.name}</option>
                                                    </c:forEach>
                                                </select>
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
                <button type="button" class="btn btn-primary" onclick="savesEvaluationMethod()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
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
            <input type="hidden" data-name="number" value="{number}">
            <input type="hidden" data-name="splitNumber" value="{splitNumber}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <td>
            <label class="form-control" data-name="name">
                <span>{name}</span>
                <a href="javascript://" onclick="viewExamineInfo('{declareId}','{name}');"
                   class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>
            </label>

        </td>
        <td><label class="form-control" data-name="ownership">{ownership}</label></td>
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
                <select class="form-control" required data-name="bestUseId" name="bestUse{id}">
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
            <a href="javascript://" onclick="programme.splitJudge(this);"
               class="btn btn-xs btn-success judge-split tooltips">拆分</a>
            <a href="javascript://" onclick="programme.delSplitJudge(this);"
               class="btn btn-xs btn-warning judge-remove tooltips">移除</a>
            <a href="javascript://" onclick="programme.mergeJudge(this);"
               class="btn btn-xs btn-warning judge-merge tooltips">合并</a>
            <a href="javascript://" onclick="programme.mergeJudgeCancel(this);"
               class="btn btn-xs btn-warning judge-merge-cancel tooltips">取消合并</a>
            <a href="javascript://" onclick="programme.splitJudge(this);"
               class="btn btn-xs btn-success judge-method tooltips">评估方法</a>
        </td>
    </tr>
</script>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {

        //阻止合并按钮的冒泡
        $(".btn-area-merge").click(function (e) {
            programme.areaMerge(this);
            e.stopPropagation();
        })
        //阻止合并按钮的冒泡
        $(".btn-area-merge-cancel").click(function (e) {
            programme.areaMergeCancel($(this).closest('.area_panel').find('[name=areaGroupId]').val());
            e.stopPropagation();
        })
    })

    //方案
    var programme = {
            config: {
                areaPopIndex: 0,//区域弹框index
                judgePopIndex: 0,//委估对象弹框index
                //区域合并项html
                areaItemHtml: '<li data-areaGroupId="{areaGroupId}"> <p> <label>{areaName}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>',
                //委估对象合并项html
                judgeItemHtml: '<li data-judgeId="{judgeId}"> <p> <label>{name}</label> <a href="javascript://" onclick="programme.mergeItemRemove(this);" class="btn btn-xs btn-warning tooltips" style="float: right;"><i class="fa fa-minus fa-white" ></i></a> </p> </li>'
            },

            //加载区域下的委估对象列表
            loadJudgeObjectList: function (_this) {
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
                                html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                                html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                                tbody.append(html);
                                //设值
                                var lastTr = tbody.find("tr:last");
                                lastTr.find('td:last').find(item.bisSplit ? '.judge-split' : '.judge-remove').remove();
                                lastTr.find('td:last').find(item.bisMerge ? '.judge-merge' : '.judge-merge-cancel').remove();
                                tbody.find("tr:last").find('[data-name="bestUseId"]').val(item.bestUseId);
                            })
                        }
                    },
                    error: function (result) {
                        alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },

            //合并项移除
            mergeItemRemove: function (_this) {
                $(_this).closest('li').remove();
            },

            //区域合并
            areaMerge: function (_this) {
                var areaName = $(_this).closest('h2').find('label').text();
                var areaGroupId = $(_this).closest('.area_panel').find('[name=areaGroupId]').val();
                var html = programme.config.areaItemHtml;
                if (programme.config.areaPopIndex <= 0) {
                    layer.closeAll();
                    programme.config.areaPopIndex = layer.open({
                        title: "区域合并",
                        offset: 't',
                        shade: false,
                        zIndex: 998,
                        area: ['320px', '300px'], //宽高
                        content: '<ul id="area-merge-ul" class="to_do"></ul>',
                        yes: function (index, layero) {
                            programme.areaMergeSubmit();
                        },
                        end: function () {
                            programme.config.areaPopIndex = 0;
                        },
                        success: function () {
                            $("#area-merge-ul").prepend(html.replace(/{areaName}/g, areaName).replace(/{areaGroupId}/g, areaGroupId));
                        }
                    });
                } else {
                    //该区域已添加则直接返回
                    var isExist = false;
                    $("#area-merge-ul").find('li').each(function () {
                        if ($(this).attr('data-areaGroupId') == areaGroupId) {
                            isExist = true;
                            return;
                        }
                    })
                    if (!isExist) {
                        $("#area-merge-ul").prepend(html.replace(/{areaName}/g, areaName).replace(/{areaGroupId}/g, areaGroupId));
                    }
                }
            },

            //区域合并提交
            areaMergeSubmit: function () {
                var areaGroupIdArray = [];
                $("#area-merge-ul").find('li').each(function () {
                    areaGroupIdArray.push($(this).attr('data-areaGroupId'));
                })
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/areaGroupMerge',
                    data: {
                        projectId: '${projectInfo.id}',
                        areaGroupIds: areaGroupIdArray.join()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            Alert("区域合并成功", 1, null, function () {
                                window.location.href = window.location.href;
                            })
                        } else {
                            Alert("区域合并失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });
            },

            //区域合并取消
            areaMergeCancel: function (id) {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/areaGroupMergeCancel',
                    data: {
                        id: id
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            Alert("区域合并取消成功", 1, null, function () {
                                window.location.href = window.location.href;
                            })
                        } else {
                            Alert("区域合并取消失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });
            },

            //委估对象拆分
            splitJudge: function (_this) {
                //后台添加数据
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/splitJudge',
                    data: {
                        projectId: '${projectInfo.id}',
                        areaGroupId: $(_this).closest('.area_panel').find('[name=areaGroupId]').val(),
                        id: $(_this).closest('tr').find('[data-name="id"]').val()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                        } else {
                            Alert("委估对象拆分失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });


            },

            //删除拆分出来的委估对象
            delSplitJudge: function (_this) {
                //后台添加数据
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/delSplitJudge',
                    data: {
                        projectId: '${projectInfo.id}',
                        areaGroupId: $(_this).closest('.area_panel').find('[name=areaGroupId]').val(),
                        id: $(_this).closest('tr').find('[data-name="id"]').val()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                        } else {
                            Alert("委估对象删除失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });
            },

            //委估对象合并
            mergeJudge: function (_this) {
                var name = $(_this).closest('tr').find('[data-name="name"]').find('span').text();
                var judgeId = $(_this).closest('tr').find('[data-name="id"]').val();
                var html = programme.config.judgeItemHtml;
                if (programme.config.judgePopIndex <= 0) {
                    programme.config.judgePopIndex = layer.open({
                        title: "委估对象合并",
                        offset: 't',
                        shade: false,
                        zIndex: 999,
                        area: ['320px', '300px'], //宽高
                        content: '<ul id="judge-merge-ul" class="to_do"></ul>',
                        yes: function (index, layero) {
                            programme.mergeJudgeSubmit(_this,$(_this).closest('.area_panel'));
                        },
                        end: function () {
                            programme.config.judgePopIndex = 0;
                        },
                        success: function () {
                            $("#judge-merge-ul").prepend(html.replace(/{name}/g, name).replace(/{judgeId}/g, judgeId));
                        }
                    });
                } else {
                    //该区域已添加则直接返回
                    var isExist = false;
                    $("#judge-merge-ul").find('li').each(function () {
                        if ($(this).attr('data-judgeId') == judgeId) {
                            isExist = true;
                            return;
                        }
                    })
                    if (!isExist) {
                        $("#judge-merge-ul").prepend(html.replace(/{name}/g, name).replace(/{judgeId}/g, judgeId));
                    }
                }
            },

            //委估对象合并提交
            mergeJudgeSubmit: function (_this,panel) {
                var judgeIdArray = [];
                $("#judge-merge-ul").find('li').each(function () {
                    judgeIdArray.push($(this).attr('data-judgeId'));
                })
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/mergeJudge',
                    data: {
                        ids: judgeIdArray.join()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('委估对象合并成功');
                            layer.close(programme.config.judgePopIndex);
                            programme.loadJudgeObjectList(panel);
                        } else {
                            Alert("委估对象合并失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });


            },

            //取消委估对象合并
            mergeJudgeCancel: function (_this) {
                //后台添加数据
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/schemeProgramme/mergeJudgeCancel',
                    data: {
                        id: $(_this).closest('tr').find('[data-name="id"]').val()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('委估对象取消合并成功');
                            programme.loadJudgeObjectList($(_this).closest('.area_panel'));
                        } else {
                            Alert("权证拆分失败:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                    }
                });
            }


        }
    ;

</script>
<script type="text/javascript">

    /*
     *------------------------------------------------------------------------------------------------------
     *委估对象设置相关
     *------------------------------------------------------------------------------------------------------
     */

    //保存估价对象
    function saveJudgeObject(_this, areaGroupId) {
        //先验证数据必填
        var form = $(_this).closest(".x_panel").find("form");
        if (!form.valid()) {
            return false;
        }

        var tbody = $(_this).closest(".x_panel").find(".table").find("tbody");
        var data = {}; //找出需要保存的数据
        data.valueTimePoint = form.find('[name="valueTimePoint"]').val();
        data.planId = '${projectPlan.id}';
        data.areaGroupId = areaGroupId;
        data.schemeJudgeObjects = [];

        var trs = tbody.find('tr');
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var schemeJudgeObject = {};
                schemeJudgeObject.id = $(tr).find('[data-name="id"]').val();
                schemeJudgeObject.bisSplit = $(tr).find('[data-name="bisSplit"]').val();
                schemeJudgeObject.number = $(tr).find('[data-name="number"]').val();
                schemeJudgeObject.sourceId = $(tr).find('[data-name="sourceId"]').val();
                schemeJudgeObject.splitNumber = $(tr).find('[data-name="splitNumber"]').val();
                schemeJudgeObject.bestUseId = $(tr).find('[data-name="bestUseId"]').val();
                schemeJudgeObject.groupNumber = $(tr).find('[data-name="groupNumber"]').val();
                schemeJudgeObject.evaluationArea = $(tr).find('[data-name="evaluationArea"]').val();
                data.schemeJudgeObjects.push(schemeJudgeObject);
            })
        }
        //验证相同测算序号的最佳利用描述是否一致
        var keyValueArray = [];
        for (var j = 0; j < data.schemeJudgeObjects.length; j++) {
            var judge = data.schemeJudgeObjects[j];
            var keyValue = {};
            var isExist = false;
            if (keyValueArray.length > 0) {
                for (var k = 0; k < keyValueArray.length; k++) {
                    if (keyValueArray[k].key == judge.groupNumber) {
                        if (keyValueArray[k].value == judge.bestUseId) {
                            isExist = true;
                        } else {
                            Alert('合并测算序号为【' + judge.groupNumber + "】的最佳利用设置不一致");
                            return false;
                        }
                    }
                }

            }
            if (!isExist) {
                keyValue.key = judge.groupNumber;
                keyValue.value = judge.bestUseId;
                keyValueArray.push(keyValue);
            }
        }

        //验证评估面积是否合理--证载面积与评估面积必须一致么，待确认

        var url = "${pageContext.request.contextPath}/projectplanschemeassist/saveEvaluationObject";
        $.ajax({
            url: url,
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    loadJudgeObjectList(tbody, areaGroupId);
                    //刷新treegride
                    getPlanItemList();
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //估价对象编号一致的评估面积总和是否与证载面积相等
    function areaIsAgreement(tbody) {
        var trs = tbody.find('tr');
        var agreement = true;
        if (trs && trs.length > 0) {
            var evaluationAreaTotal = 0;
            var floorArea = $(trs[0])
            $.each(trs, function (i, tr) {
                var evaluationArea = tr.find('[data-name="evaluationArea"]').val();
                evaluationAreaTotal += parseFloat(evaluationArea);
            })
            agreement = parseFloat(floorArea) == parseFloat(evaluationAreaTotal);
        }
        return agreement;
    }

    //检测是否可以设置估价方法
    function canSetJudgeFunction(tbody) {
        var trs = tbody.find('tr');
        var canSet = true;
        $.each(trs, function (i, tr) {
            var id = $(tr).find('[data-name="id"]').val();
            var groupNumber = $(tr).find('[data-name="groupNumber"]').val();
            if (!id || id <= 0) {
                canSet = false;
                return false;
            }
            if (groupNumber == "") {
                canSet = false;
                return false;
            }
        })
        return canSet;
    }
    //检查哪些位置可以设置评估方法
    function whereCanSetJudgeFunction(tbody) {
        //先分组 在设置分组第一个可设评估方法
        tbody.find('tr').find('td:eq(-2)').html('');
        var trs = tbody.find('tr');
        var groupArray = [];
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var groupNumber = $(tr).find('[data-name="groupNumber"]').val();
                if (groupNumber) {
                    if ($.inArray(groupNumber, groupArray) < 0) {
                        groupArray.push(groupNumber);
                    }
                }
            })
            $.each(groupArray, function (i, groupNumer) {
                var groupNumberEles = tbody.find('[data-name="groupNumber"]');
                if (groupNumberEles && groupNumberEles.length > 0) {
                    $.each(groupNumberEles, function (j, item) {
                        if ($(item).val() == groupNumer) {
                            $(item).closest('tr').find('td:eq(-2)')
                                .html('<a href="javascript://" onclick="setEvaluationMethod(this);" class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-recorder"></i>评估方法</a>');
                            return false;
                        }
                    })
                }
            })
        }
    }

    //合并测试号移开光标时调用
    function groupNumberBlur(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        tbody.find('tr').find('td:eq(-2)').html('');
        return;//

        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //拆分估价对象
    function splitJudgeObject(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        var number = tr.find('[data-name="number"]').val();
        var numberEleLast = tbody.find('[data-name="number"][value=' + number + ']:last');//相同编号的最后一个元素添加拆分对象
        var splitTr = $(numberEleLast).closest("tr").after("<tr>" + tr.html() + "</tr>").next();
        splitTr.find('[data-name="bisSplit"]').val("true");
        splitTr.find('[data-name="id"]').val("0");
        splitTr.find('[data-name="sourceId"]').val(tr.find('[data-name="id"]').val());
        splitTr.find("td:last").html('<a href="javascript://" onclick="removeJudgeObject(this);" class="btn btn-xs btn-warning tooltips"><i class="fa fa-white fa-minus"></i>移除</a>');
        computeSplitNumber(tbody, splitTr.find('[data-name="number"]').val());

        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //移除拆分的估价对象
    function removeJudgeObject(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        var number = tr.find('[data-name="number"]').val();
        tr.remove();
        computeSplitNumber(tbody, number);

        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //换算拆分号
    function computeSplitNumber(tbody, number) {
        var inputs = tbody.find('[data-name="number"][value=' + number + ']');
        if (inputs.length == 1) {
            var tr = $(inputs[0]).closest('tr');
            var mergeNumber = tr.find('[data-name="number"]').val();
            tr.find('[data-name="mergeNumber"]').text(mergeNumber);
            tr.find('[data-name="splitNumber"]').val(0);
            tr.find('[data-name="bestUseId"]').attr('name', 'bestUseId' + mergeNumber);
            tr.find('[data-name="groupNumber"]').attr('name', 'groupNumber' + mergeNumber);
            tr.find('[data-name="evaluationArea"]').attr('name', 'evaluationArea' + mergeNumber);
        } else {
            $.each(inputs, function (i, input) {
                var tr = $(input).closest('tr');
                var splitNumber = i + 1;
                var mergeNumber = tr.find('[data-name="number"]').val() + "-" + splitNumber;
                tr.find('[data-name="mergeNumber"]').text(mergeNumber);
                tr.find('[data-name="splitNumber"]').val(splitNumber);
                tr.find('[data-name="bestUseId"]').attr('name', 'bestUseId' + mergeNumber);
                tr.find('[data-name="groupNumber"]').attr('name', 'groupNumber' + mergeNumber);
                tr.find('[data-name="evaluationArea"]').attr('name', 'evaluationArea' + mergeNumber);
            })
        }
    }

    //初次加载估价对象信息
    function firstLoadJudgeObjectList(_this, areaGroupId) {
        var tbody = $(_this).closest(".x_panel").find(".table").find("tbody");
        var trs = tbody.find("tr");
        if (!trs || trs.length <= 0) {
            loadJudgeObjectList(tbody, areaGroupId);
        }
    }

    //加载估价对象列表
    function loadJudgeObjectList(tbody, areaGroupId) {
        tbody.empty();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/schemeAreaGroupVoList",
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
                        html = html.replace(/{number}/g, item.number == undefined ? "" : item.number);
                        html = html.replace(/{splitNumber}/g, item.splitNumber == undefined ? "" : item.splitNumber);
                        html = html.replace(/{sourceId}/g, item.sourceId == undefined ? "" : item.sourceId);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace(/{name}/g, item.name == undefined ? "" : item.name);
                        html = html.replace(/{declareId}/g, item.declareRecordId == undefined ? "" : item.declareRecordId);
                        html = html.replace(/{ownership}/g, item.ownership == undefined ? "" : item.ownership);
                        html = html.replace(/{groupNumber}/g, item.groupNumber == undefined ? "" : item.groupNumber);
                        html = html.replace(/{floorArea}/g, item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace(/{evaluationArea}/g, item.evaluationArea == undefined ? "" : item.evaluationArea);
                        tbody.append(html);
                        tbody.find("tr:last").find('[data-name="bestUseId"]').val(item.bestUseId);
                    })

                    if (canSetJudgeFunction(tbody)) {
                        whereCanSetJudgeFunction(tbody);
                    } else {
                        tbody.find('tr').find('td:eq(-2)').html('');
                    }
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //查看估计对象调查信息
    function viewExamineInfo(declareId, name) {
        if (declareId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyExamine/getPlanDetailsByDeclareId",
                data: {
                    declareId: declareId
                },
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        $("#viewExamineInfoModal .x_content").empty();
                        $.each(result.data, function (i, item) {
                            var html = ' <button type="button" class="btn btn-link" onclick="window.open(\'${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?planDetailsId=' + item.id + '\')">' + item.projectPhaseName + '</button>';
                            $("#viewExamineInfoModal .x_content").append(html);
                        })
                        $("#viewExamineInfoModal").find('.modal-title').text(name);
                        $("#viewExamineInfoModal").modal();
                    }
                }
            })
        }
    }
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

    //保存评估方法
    function savesEvaluationMethod() {
        //验证数据是否填写完整
        var isPass = true;
        $("#myTabContent").find('.tab-pane').find('form').each(function () {
            if (!$(this).valid("请检查【" + $(this).attr('data-name') + "】是否填写完整！")) {
                isPass = false;
                return false;
            }
        });
        if (!isPass) return false;
        var data = {};
        data.judgeFunctionList = [];
        $("#myTabContent").find('.tab-pane').each(function () {
            var judgeFunction = {};
            judgeFunction.areaGroupId = $("#currAreaGroupId").val();
            judgeFunction.groupNumber = $("#currGroupNumber").val();
            judgeFunction.id = $(this).find('[name="id"]').val();
            judgeFunction.name = $(this).find('[name="name"]').val();
            judgeFunction.methodType = $(this).find('[name="methodType"]').val();
            judgeFunction.bisApplicable = $(this).find('[name="bisApplicable"]:checked').val();
            judgeFunction.applicableReason = $(this).find('[name="applicableReason"]').val();
            judgeFunction.notApplicableReason = $(this).find('[name="notApplicableReason"]').val();
            judgeFunction.applicableThinking = $(this).find('[name="applicableThinking"]').val();
            judgeFunction.notApplicableThinking = $(this).find('[name="notApplicableThinking"]').val();
            data.judgeFunctionList.push(judgeFunction);
        })
        data.areaGroupId = $("#currAreaGroupId").val();
        data.groupNumber = $("#currGroupNumber").val();
        //检查各个方法数据是否填写完整
        if (data.judgeFunctionList.length > 0) {
            for (var i = 0; i < data.judgeFunctionList.length; i++) {
                if (!methodHasWriteFull(data.judgeFunctionList[i])) {
                    toastr.info("请检查【" + data.judgeFunctionList[i].name + "】是否填写完整！");
                    return false;
                }
            }
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/projectplanschemeassist/saveJudgeFunction',
            data: {
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    //刷新treegride
                    getPlanItemList();
                    $('#divBoxMethodExtend').modal('hide');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
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
        var groupNumber = $(_this).closest("tr").find('[data-name="groupNumber"]').val();
        var areaGroupId = $(_this).closest(".area_panel").find('[name="areaGroupId"]').val();
        $("#currGroupNumber").val(groupNumber);
        $("#currAreaGroupId").val(areaGroupId);
        //还原数据状态
        cleanEvaluationMethod();
        //如果该估计对象已经设置过评估方法，则将数据填充回去
        $.ajax({
            url: '${pageContext.request.contextPath}/projectplanschemeassist/getSchemeJudgeFunctions',
            data: {
                areaGroupId: areaGroupId,
                groupNumber: groupNumber
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


