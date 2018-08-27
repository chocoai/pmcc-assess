<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-25
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${dataList}" var="item">
    <div class="x_panel area_panel">
        <input type="hidden" name="areaGroupId" value="${item.id}">
        <div class="x_title collapse-link" onclick="firstLoadJudgeObjectList(this,${item.id});">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>
                <c:if test="${!empty item.areaName}">
                    ${item.areaName}
                </c:if>
            </h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content" style="display: none;">
            <form id="frmJudgeObject${item.id}" class="form-horizontal">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            价值时点<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="valueTimePoint" required="required" placeholder="价值时间点"
                                   data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                   readonly="readonly" pattern='yyyy-MM-dd'
                                   value="<fmt:formatDate value="${empty item.valueTimePoint?projectInfo.valuationDate:item.valueTimePoint}"
                                   pattern="yyyy-MM-dd"/>">
                        </div>
                    </div>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>估价对象编号</th>
                        <th>权证号</th>
                        <th>所有权人</th>
                        <th>最佳利用设置</th>
                        <th>合并测算序号</th>
                        <th>证载面积</th>
                        <th>评估面积</th>
                        <th>评估方法</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="form-group">
                    <div class="x-valid">
                        <div class="col-sm-4 col-sm-offset-5">
                            <input type="button" class="btn btn-success"
                                   onclick="saveJudgeObject(this,'${item.id}')" value="保存">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</c:forEach>

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
        if(data.judgeFunctionList.length>0){
            for(var i=0;i<data.judgeFunctionList.length;i++){
                if(!methodHasWriteFull(data.judgeFunctionList[i])){
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
        $("#myTabContent").find('[name="bisApplicable"]').attr("checked",false);
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
            <input type="hidden" data-name="sourceId" value="{sourceId}">
            <input type="hidden" data-name="number" value="{number}">
            <input type="hidden" data-name="splitNumber" value="{splitNumber}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <td>
            <label class="form-control" data-name="name">
                {name}
                <a href="javascript://" onclick="viewExamineInfo('{declareId}','{name}');"
                   class="btn btn-xs btn-success tooltips"><i class="fa fa-white fa-search"></i></a>
            </label>

        </td>
        <td><label class="form-control" data-name="ownership">{ownership}</label></td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="bestUseId" name="bestUseId{mergeNumber}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${bestusedescriptionList}" var="bestUse">
                        <option value="${bestUse.id}">${bestUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-digits="true"
                       name="groupNumber{mergeNumber}" data-name="groupNumber" onblur="groupNumberBlur(this);"
                       placeholder="合并测算序号" value="{groupNumber}">
            </div>
        </td>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-number="true"
                       name="evaluationArea{mergeNumber}" data-name="evaluationArea"
                       placeholder="评估面积" value="{evaluationArea}">
            </div>
        </td>
        <td>
        </td>
        <td>
            <a href="javascript://" onclick="splitJudgeObject(this);" class="btn btn-xs btn-success tooltips"><i
                    class="fa fa-white fa-exchange"></i>拆分</a>
        </td>
    </tr>
</script>