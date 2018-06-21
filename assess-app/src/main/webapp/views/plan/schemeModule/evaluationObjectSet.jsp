<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-25
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${dataList}" var="item">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>
                <c:if test="${!empty item.provinceCityDistrictStr}">
                    ${item.provinceCityDistrictStr}
                </c:if>
            </h2>
            <ul class="nav navbar-right panel_toolbox">
                <li>
                    <a class="collapse-link" onclick="firstLoadJudgeObjectList(this,${item.id});">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content" style="display: none;">
            <form id="frmJudgeObject${item.id}" class="form-horizontal">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            价值时点
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="valueTimePoint" required="required" placeholder="价值时间点"
                                   data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                   readonly="readonly" pattern='yyyy-MM-dd'
                                   value="<fmt:formatDate value="${item.valueTimePoint}"  pattern="yyyy-MM-dd"/>">
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
                <input type="hidden" id="judgeObjectId">
                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                        <c:forEach items="${dataDicMethodList}" var="item" varStatus="status">
                            <li role="presentation" ${status.index==0?'class="active"':''} >
                                <a href="#tab_content${item.id}" id="tab${item.id}" role="tab"
                                   data-toggle="tab" aria-expanded="true">${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    <div id="myTabContent" class="tab-content form-horizontal">

                        <c:forEach items="${dataDicMethodList}" var="method" varStatus="status">
                            <div role="tabpanel" class="tab-pane fade ${status.index==0?'active in':''} "
                                 id="tab_content${method.id}"
                                 aria-labelledby="home-tab">
                                <input type="hidden" name="id" value="0">
                                <input type="hidden" name="methodType" value="${method.id}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">

                                    </label>
                                    <div class="x-valid">
                                        <div class="col-sm-10">
                                            <div class="btn-group" data-toggle="buttons">
                                                <label class="btn btn-default" data-toggle-class="btn-primary"
                                                       data-toggle-passive-class="btn-primary"
                                                       onclick="applicableChange(this,true)">
                                                    <input type="radio" name="bisApplicable" value="true"> 适用
                                                </label>
                                                <label class="btn btn-default" data-toggle-class="btn-primary"
                                                       data-toggle-passive-class="btn-primary"
                                                       onclick="applicableChange(this,false)">
                                                    <input type="radio" name="bisApplicable" value="false"> 不适用
                                                </label>
                                            </div>
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
                                    <div class="form-group">
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
                                <div class="not-applicable" style="display: none;">
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
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">
                                            思路不适用原因
                                        </label>
                                        <div class="x-valid">
                                            <div class="col-sm-10">
                                        <textarea required placeholder="思路不适用原因" name="notApplicableThinking"
                                                  class="form-control"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="notApplicableThinking-field"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="savesEvaluationMethod()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

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
        var data = {};
        data.judgeFunctionList = [];
        $("#myTabContent").find('.tab-pane').each(function () {
            var judgeFunction = {};
            judgeFunction.judgeObjectId = $("#judgeObjectId").val();
            judgeFunction.id = $(this).find('[name="id"]').val();
            judgeFunction.methodType = $(this).find('[name="methodType"]').val();
            judgeFunction.bisApplicable = $(this).find('[name="bisApplicable"]:checked').val();
            judgeFunction.applicableReason = $(this).find('[name="applicableReason"]').val();
            judgeFunction.notApplicableReason = $(this).find('[name="notApplicableReason"]').val();
            judgeFunction.applicableThinking = $(this).find('[name="applicableThinking"]').val();
            judgeFunction.notApplicableThinking = $(this).find('[name="notApplicableThinking"]').val();
            data.judgeFunctionList.push(judgeFunction);
        })

        $.ajax({
            url: '${pageContext.request.contextPath}/projectplanschemeassist/saveJudgeFunction',
            data: {formData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //设置评估方法
    function setEvaluationMethod(_this) {
        var judgeObjectId = $(_this).closest("tr").find('[data-name="id"]').val();
        $("#judgeObjectId").val(judgeObjectId);
        //还原数据状态
        cleanEvaluationMethod();
        //如果该估计对象已经设置过评估方法，则将数据填充回去
        $.ajax({
            url: '${pageContext.request.contextPath}/projectplanschemeassist/getListByJudgeObjectId',
            data: {judgeObjectId: judgeObjectId},
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    $.each(result.data, function (i, item) {
                        var methodTypeEle = $("#myTabContent").find('.tab-pane').find('[name="methodType"][value="' + item.methodType + '"]')
                        var tabPane = $(methodTypeEle).closest(".tab-pane");
                        tabPane.find('[name="id"]').val(item.id);
                        if (item.bisApplicable) {
                            tabPane.find('[name="bisApplicable"][value="true"]').prop('checked', true).parent().get(0).click();
                            tabPane.find('.applicable').show();
                        } else {
                            tabPane.find('[name="bisApplicable"][value="false"]').prop('checked', true).parent().get(0).click();
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
        $("#myTabContent").find('[name="id"]').val('0');
        $("#myTabContent").find('[name="bisApplicable"]').parent().removeClass('active')
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
        var bisApplicable = tabPane.find('[name=bisApplicable][checked="checked"]').val();
        var option = $(_this).find('option:selected');
        if (bisApplicable == "true") {
            tabPane.find('[name="applicableReason"]').val(option.attr("data-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodApplicableFieldReplace');
                tabPane.find('.applicableReason-field').empty().append(html);
            }
        } else if (bisApplicable == "false") {
            tabPane.find('[name="notApplicableReason"]').val(option.attr("data-not-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-not-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'methodNotApplicableFieldReplace');
                tabPane.find('.notApplicableReason-field').empty().append(html);
            }
        }
    }

    //评估思路模板选项change
    function evaluationThinkingChange(_this) {
        var tabPane = $(_this).closest(".tab-pane");
        var bisApplicable = tabPane.find('[name=bisApplicable][checked="checked"]').val();
        var option = $(_this).find('option:selected');
        if (bisApplicable == "true") {
            tabPane.find('[name="applicableThinking"]').val(option.attr("data-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'thinkingApplicableFieldReplace');
                tabPane.find('.applicableThinking-field').empty().append(html);
            }
        } else if (bisApplicable == "false") {
            tabPane.find('[name="notApplicableThinking"]').val(option.attr("data-not-applicable"));
            var fieldArray = AssessCommon.extractField(option.attr("data-not-applicable"));
            if (fieldArray && fieldArray.length > 0) {
                var html = createDynaicFieldHtml(fieldArray, 'thinkingNotApplicableFieldReplace');
                tabPane.find('.notApplicableThinking-field').empty().append(html);
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

    //保存估价对象
    function saveJudgeObject(_this, areaGroupId) {
        //先验证数据必填
        var form = $(_this).closest(".x_panel").find("form");
        if (!form.valid()) {
            return false;
        }
        var tbody = $(_this).closest(".x_panel").find(".table").find("tbody");
        //验证评估面积是否合理

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
                                .html('<input class="btn btn-success" type="button" value="评估方法" onclick="setEvaluationMethod(this)">');
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
        splitTr.find("td:last").html('<input class="btn btn-warning" type="button" value="移除" onclick="removeJudgeObject(this)">');
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
                if (result) {
                    $.each(result, function (i, item) {
                        var html = $("#judgeObjectHtml").html();
                        html = html.replace("{id}", item.id == undefined ? "" : item.id);
                        html = html.replace("{bisSplit}", item.bisSplit == undefined ? false : item.bisSplit);
                        html = html.replace("{number}", item.number == undefined ? "" : item.number);
                        html = html.replace("{splitNumber}", item.splitNumber == undefined ? "" : item.splitNumber);
                        html = html.replace("{sourceId}", item.sourceId == undefined ? "" : item.sourceId);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace("{name}", item.name == undefined ? "" : item.name);
                        html = html.replace("{ownership}", item.ownership == undefined ? "" : item.ownership);
                        html = html.replace("{groupNumber}", item.groupNumber == undefined ? "" : item.groupNumber);
                        html = html.replace("{floorArea}", item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace("{evaluationArea}", item.evaluationArea == undefined ? "" : item.evaluationArea);
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
</script>

<!--动态字段-->
<script type="text/html" id="dynamicFieldHtml">
    <label class="col-sm-2 control-label">
        {name}
    </label>
    <div class="x-valid">
        <div class="col-sm-4">
            <input type="text" class="form-control" data-name="{name}" onblur="{functionName}(this);">
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
        <td><label class="form-control" data-name="name">{name}</label></td>
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
            <input class="btn btn-success" type="button" value="拆分" onclick="splitJudgeObject(this)">
        </td>
    </tr>
</script>